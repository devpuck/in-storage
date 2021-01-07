package com.wms.service.impl;

import com.wms.account.AccountStatus;
import com.wms.api.account.AccountResult;
import com.wms.api.account.AccountVo;
import com.wms.api.account.InWarehouseAccountVo;
import com.wms.api.config.ConfigInWarehouseVo;
import com.wms.api.instorage.InWarehouseBillSubVo;
import com.wms.api.instorage.InWarehouseBillVo;
import com.wms.errorcode.ErrorCode;
import com.wms.model.bo.config.ConfigInWarehouseBo;
import com.wms.model.bo.config.ConfigSpecInWarehouseBo;
import com.wms.model.bo.instorage.InWarehouseBillBo;
import com.wms.sdk.account.InAccountManager;
import com.wms.service.AccountService;
import com.wms.service.ConfigInWarehouseService;
import com.wms.service.ConfigSpecInWarehouseService;
import com.wms.thread.AfterAccountThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author puck
 * @date 2020/12/25 4:25 下午
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService
{
    @Autowired
    private ConfigInWarehouseService configInWarehouseService;
    @Autowired
    private ConfigSpecInWarehouseService configSpecInWarehouseService;
    @Autowired
    private InWarehouseBillServiceImpl inWarehouseBillService;

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
    public  boolean createAccountUseSpecConfig(InWarehouseAccountVo inWarehouseAccountVo,ConfigSpecInWarehouseBo configSpecInWarehouseBo)
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
    public boolean createAccountUseConfig(InWarehouseAccountVo inWarehouseAccountVo, ConfigInWarehouseBo configInWarehouseBo)
    {
        inWarehouseAccountVo.setStatus(configInWarehouseBo.getStatus());
        return accounting(inWarehouseAccountVo);
    }

    /**
     * 根据默认入库过账
     * @param inWarehouseAccountVo
     * @return
     */
    public boolean createAccountDefault(InWarehouseAccountVo inWarehouseAccountVo)
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
    public boolean accounting(InWarehouseAccountVo inWarehouseAccountVo)
    {
        InAccountManager inAccountManager = new InAccountManager();
        AccountResult accountResult = inAccountManager.createInStorageAccount(inWarehouseAccountVo);
        if(null != accountResult && accountResult.isSuccess())
        {
            AfterAccountThread afterAccountThread = new AfterAccountThread(accountResult,inWarehouseAccountVo);
            Thread thread = new Thread(afterAccountThread);
            thread.start();
            return true;
        }

        return false;
    }

}
