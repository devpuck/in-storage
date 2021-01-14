package com.wms.service.impl;

import com.wms.account.AccountStatus;
import com.wms.api.account.AccountResult;
import com.wms.api.account.InWarehouseAccountVo;
import com.wms.api.instorage.InWarehouseBillSubVo;
import com.wms.api.instorage.InWarehouseBillVo;
import com.wms.bill.BillState;
import com.wms.model.bo.config.ConfigInWarehouseBo;
import com.wms.model.bo.config.ConfigSpecInWarehouseBo;
import com.wms.model.bo.instorage.InWarehouseBillBo;
import com.wms.model.bo.instorage.InWarehouseBillSubBo;
import com.wms.sdk.account.InAccountManager;
import com.wms.service.InAccountService;
import com.wms.service.ConfigInWarehouseService;
import com.wms.service.ConfigSpecInWarehouseService;
import com.wms.service.InWarehouseBillSubService;
import com.wms.thread.AfterAccountThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * @author puck
 * @date 2020/12/25 4:25 下午
 */
@Slf4j
@Service
public class InAccountServiceImpl implements InAccountService
{
    @Autowired
    private ConfigInWarehouseService configInWarehouseService;
    @Autowired
    private ConfigSpecInWarehouseService configSpecInWarehouseService;
    @Autowired
    private InWarehouseBillServiceImpl inWarehouseBillService;
    @Autowired
    private InWarehouseBillSubService inWarehouseBillSubService;

    @Override
    public boolean createAccount(InWarehouseAccountVo inWarehouseAccountVo) throws Exception
    {
        boolean result = false;

        InWarehouseBillVo inWarehouseBillVo = inWarehouseAccountVo.getInWarehouseBillVo();
        InWarehouseBillSubVo inWarehouseBillSubVo = inWarehouseAccountVo.getInWarehouseBillSubVo();

        //如果参数中没有入库单据，则通过子单查询入库单据
        if(null == inWarehouseBillVo)
        {
            InWarehouseBillBo inWarehouseBillBo = inWarehouseBillService.queryInWarehouseBillByBillCode(inWarehouseBillSubVo.getBillCode());
            if(null == inWarehouseBillBo)
            {
                return result;
            }

            BeanUtils.copyProperties(inWarehouseBillBo,inWarehouseBillVo);
        }

        String warehouseCode = inWarehouseBillVo.getWarehouseCode();
        String workCode = inWarehouseBillVo.getWorkCode();
        String dealCode = inWarehouseBillVo.getDealCode();
        String productionCode = inWarehouseBillSubVo.getProductionCode();

        // 此处最好用类的继承实现，因为框架原因，暂时只能这样，后期改造框架
        ConfigSpecInWarehouseBo configSpecInWarehouseBo = configSpecInWarehouseService.queryConfigSpecInWarehouse(warehouseCode,workCode,dealCode,productionCode);
        if(null != configSpecInWarehouseBo)
        {
            return createAccountUseSpecConfig(inWarehouseAccountVo,configSpecInWarehouseBo);
        }
        ConfigInWarehouseBo configInWarehouseBo = configInWarehouseService.queryConfigWarehouse(warehouseCode,workCode,dealCode);
        if(null != configInWarehouseBo)
        {
           return createAccountUseConfig(inWarehouseAccountVo,configInWarehouseBo);
        }

        return createAccountDefault(inWarehouseAccountVo);
    }

    /**
     * 特殊配置的入库过账
     * @param inWarehouseAccountVo
     * @param configSpecInWarehouseBo
     * @return
     */
    public  boolean createAccountUseSpecConfig(InWarehouseAccountVo inWarehouseAccountVo,ConfigSpecInWarehouseBo configSpecInWarehouseBo)  throws Exception
    {
        inWarehouseAccountVo.setStatus(configSpecInWarehouseBo.getStatus());
        return accounting(inWarehouseAccountVo);
    }

    /**
     * 根据普通配置入库过账
     * @param inWarehouseAccountVo
     * @param configInWarehouseBo
     * @return
     */
    public boolean createAccountUseConfig(InWarehouseAccountVo inWarehouseAccountVo, ConfigInWarehouseBo configInWarehouseBo)  throws Exception
    {
        inWarehouseAccountVo.setStatus(configInWarehouseBo.getStatus());
        return accounting(inWarehouseAccountVo);
    }

    /**
     * 根据默认入库过账
     * @param inWarehouseAccountVo
     * @return
     */
    public boolean createAccountDefault(InWarehouseAccountVo inWarehouseAccountVo)  throws Exception
    {
        //默认为非限制性库存
        inWarehouseAccountVo.setStatus(AccountStatus.UNRESTRICTED_INVENTORY);

        return accounting(inWarehouseAccountVo);
    }

    /**
     * 基本入库过账
     * @param inWarehouseAccountVo
     * @return
     */
    public boolean accounting(InWarehouseAccountVo inWarehouseAccountVo) throws Exception
    {
        boolean result = false;
        InAccountManager inAccountManager = new InAccountManager();
        AccountResult accountResult = inAccountManager.createInStorageAccount(inWarehouseAccountVo);
        //入库成功
        if(null != accountResult && accountResult.isSuccess())
        {
            result = checkAndCloseBill(inWarehouseAccountVo);

            AfterAccountThread afterAccountThread = new AfterAccountThread(accountResult,inWarehouseAccountVo);
            Thread thread = new Thread(afterAccountThread);
            thread.start();
            return result;
        }

        return true;
    }

    /**
     * 检查是否可以关闭入库单据，如果可以关闭入库单据和入库子单
     * @param inWarehouseAccountVo
     * @return
     */
    public boolean checkAndCloseBill(InWarehouseAccountVo inWarehouseAccountVo) throws Exception
    {
        boolean result = false;
        boolean flag = true;
        //更新已经入库数量，并更新子表状态
        result = inWarehouseBillSubService.increaseBillQuantity(inWarehouseAccountVo.getInWarehouseBillSubVo().getId(),inWarehouseAccountVo.getIncreaseQuantity());
        List<InWarehouseBillSubBo> inWarehouseBillSubBoList = inWarehouseBillSubService.queryInWarehouseBillSubByBillCode(inWarehouseAccountVo.getInWarehouseBillVo().getBillCode());
        if(null != inWarehouseBillSubBoList)
        {
            Iterator<InWarehouseBillSubBo> it = inWarehouseBillSubBoList.iterator();
            while(it.hasNext())
            {
                InWarehouseBillSubBo inWarehouseBillSubBo = it.next();
                if(!BillState.FINISHED.equals(inWarehouseBillSubBo.getStatus()))
                {
                    flag = false;
                    break;
                }
            }

            //如果全部子单为已完结，则关闭所有子单
            if(flag)
            {
                result = inWarehouseBillService.updateInWarehouseBillState(inWarehouseAccountVo.getInWarehouseBillVo().getId(),BillState.FINISHED);
            }
        }
        return result;
    }

}
