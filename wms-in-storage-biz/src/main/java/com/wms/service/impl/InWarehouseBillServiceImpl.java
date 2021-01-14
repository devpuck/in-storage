package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.api.instorage.query.QueryInStorageAttribute;
import com.wms.api.instorage.query.QueryInStorageAttributeByStatus;
import com.wms.api.query.ExcludeEmptyQueryWrapper;
import com.wms.bill.BillState;
import com.wms.model.bo.instorage.InWarehouseBillSubBo;
import com.wms.model.entity.InWarehouseBillSubEntity;
import com.wms.service.InWarehouseBillSubService;
import com.xac.core.constant.CoreConstant;
import com.wms.model.entity.InWarehouseBillEntity;
import com.wms.mapper.instorage.InWarehouseBillMapper;
import com.wms.service.InWarehouseBillService;
import com.wms.api.instorage.InWarehouseBillQueryParam;
import com.wms.model.bo.instorage.InWarehouseBillBo;
import com.xac.core.service.impl.BaseServiceImpl;
import com.xac.core.api.Paging;
import com.xac.core.util.BeanListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.*;


/**
 * <pre>
 * 入库单据 服务实现类
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Slf4j
@Service
public class InWarehouseBillServiceImpl extends BaseServiceImpl<InWarehouseBillMapper, InWarehouseBillEntity> implements InWarehouseBillService {

    @Autowired
    private InWarehouseBillMapper inWarehouseBillMapper;

    @Autowired
    private InWarehouseBillSubService subService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveInWarehouseBill(InWarehouseBillBo inWarehouseBill)  throws Exception
    {
        String billCode = inWarehouseBill.getBillCode();
        if(null == billCode || "".equals(billCode))
        {
            generateBillCode(inWarehouseBill);
        }

        InWarehouseBillEntity entity = new InWarehouseBillEntity();
        BeanUtils.copyProperties(inWarehouseBill , entity);
        boolean result = super.save(entity);
        if(!result)
        {
            return result;
        }

        List<InWarehouseBillSubBo> inWarehouseBillSubBoList = inWarehouseBill.getInWarehouseBillSubBoList();
        if(null != inWarehouseBillSubBoList)
        {
            List<InWarehouseBillSubEntity> inWarehouseBillSubEntityList = BeanListUtil.copyListProperties(inWarehouseBillSubBoList,InWarehouseBillSubEntity.class);
            result = subService.saveBatch(inWarehouseBillSubEntityList);
        }
        return result;
    }

    public void generateBillCode(InWarehouseBillBo inWarehouseBill)
    {
        String billCode = UUID.randomUUID().toString().replaceAll("-", "");
        inWarehouseBill.setBillCode(billCode);
        Iterator<InWarehouseBillSubBo> it = inWarehouseBill.getInWarehouseBillSubBoList().iterator();
        while(it.hasNext())
        {
            InWarehouseBillSubBo inWarehouseBillSubBo = it.next();
            inWarehouseBillSubBo.setBillCode(billCode);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateInWarehouseBill(InWarehouseBillBo inWarehouseBill)
    {
        InWarehouseBillEntity entity = new InWarehouseBillEntity();
        BeanUtils.copyProperties(inWarehouseBill , entity);
        return super.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteInWarehouseBill(Long id)
    {
        return super.removeById(id);
    }

    @Override
    public InWarehouseBillBo getInWarehouseBillById(Serializable id) {
        return inWarehouseBillMapper.getInWarehouseBillById(id);
    }

    @Override
    public Paging<InWarehouseBillBo> getInWarehouseBillPageList(InWarehouseBillQueryParam inWarehouseBillQueryParam) {
        Page page = setPageParam(inWarehouseBillQueryParam, OrderItem.desc(CoreConstant.CREATED_DATE));
        IPage<InWarehouseBillBo> iPage = inWarehouseBillMapper.getInWarehouseBillPageList(page, inWarehouseBillQueryParam);
        return new Paging(iPage);
    }

    @Override
    public String queryInWarehouseBillIDByRequirementID(String requirementID)
    {
        return inWarehouseBillMapper.queryInWarehouseBillIDByRequirementID(requirementID);
    }

    @Override
    public String queryInWarehouseBillIDByBillCode(String billCode)
    {
        return inWarehouseBillMapper.queryInWarehouseBillIDByBillCode(billCode);
    }

    @Override
    public InWarehouseBillBo queryInWarehouseBillByRequirementID(String requirementID)  throws Exception
    {
        InWarehouseBillEntity inWarehouseBillEntity = inWarehouseBillMapper.selectOne(new QueryWrapper<InWarehouseBillEntity>().lambda()
                .eq(InWarehouseBillEntity::getRequirementId,requirementID));
        if(null != inWarehouseBillEntity)
        {
            InWarehouseBillBo inWarehouseBillBo = new InWarehouseBillBo();
            BeanUtils.copyProperties(inWarehouseBillEntity,inWarehouseBillBo);

            List<InWarehouseBillSubBo> inWarehouseBillSubBoList = subService.queryInWarehouseBillSubByBillCode(inWarehouseBillBo.getBillCode());
            inWarehouseBillBo.setInWarehouseBillSubBoList(inWarehouseBillSubBoList);
            return inWarehouseBillBo;
        }
        return null;
    }

    @Override
    public InWarehouseBillBo queryInWarehouseBillByBillCode(String billCode) throws Exception
    {
        InWarehouseBillEntity inWarehouseBillEntity = inWarehouseBillMapper.selectOne(new QueryWrapper<InWarehouseBillEntity>().lambda()
                .eq(InWarehouseBillEntity::getBillCode,billCode));
        if(null != inWarehouseBillEntity)
        {
            InWarehouseBillBo inWarehouseBillBo = new InWarehouseBillBo();
            BeanUtils.copyProperties(inWarehouseBillEntity,inWarehouseBillBo);

            List<InWarehouseBillSubBo> inWarehouseBillSubBoList = subService.queryInWarehouseBillSubByBillCode(inWarehouseBillBo.getBillCode());
            inWarehouseBillBo.setInWarehouseBillSubBoList(inWarehouseBillSubBoList);
            return inWarehouseBillBo;
        }
        return null;
    }


    @Override
    public List<InWarehouseBillBo> queryInWarehouseBillByProductionCode(String productionCode, String status) throws Exception
    {
        List<InWarehouseBillBo> inWarehouseBillBoList = inWarehouseBillMapper.queryInWarehouseBillByProductionCode(productionCode,status);
        if(null != inWarehouseBillBoList)
        {
            Iterator<InWarehouseBillBo> it = inWarehouseBillBoList.iterator();
            while(it.hasNext())
            {
                InWarehouseBillBo inWarehouseBillBo = it.next();
                List<InWarehouseBillSubBo> inWarehouseBillSubBoList = subService.queryInWarehouseBillSubByBillCode(inWarehouseBillBo.getBillCode());
                inWarehouseBillBo.setInWarehouseBillSubBoList(inWarehouseBillSubBoList);
            }

        }
        return inWarehouseBillBoList;
    }

    @Override
    public List<InWarehouseBillBo> queryInWarehouseBill(String warehouseCode) throws Exception
    {
        List<InWarehouseBillEntity> inWarehouseBillEntityList = inWarehouseBillMapper.selectList(new QueryWrapper<InWarehouseBillEntity>().lambda()
                .eq(InWarehouseBillEntity::getWarehouseCode, warehouseCode));

        if (null == inWarehouseBillEntityList)
        {
            inWarehouseBillEntityList = new ArrayList<InWarehouseBillEntity>();
        }
        return BeanListUtil.copyListProperties(inWarehouseBillEntityList,InWarehouseBillBo.class);
    }

    @Override
    public List<InWarehouseBillBo> queryInWarehouseBill(String warehouseCode, String status) throws Exception
    {
        List<InWarehouseBillEntity> inWarehouseBillEntityList = inWarehouseBillMapper.selectList(new QueryWrapper<InWarehouseBillEntity>().lambda()
                .eq(InWarehouseBillEntity::getWarehouseCode, warehouseCode)
                .eq(InWarehouseBillEntity::getStatus,status));

        if (null == inWarehouseBillEntityList)
        {
            inWarehouseBillEntityList = new ArrayList<InWarehouseBillEntity>();
        }
        return BeanListUtil.copyListProperties(inWarehouseBillEntityList,InWarehouseBillBo.class);
    }

    @Override
    public List<InWarehouseBillBo> queryInWarehouseBill(QueryInStorageAttributeByStatus queryAttribute) throws Exception
    {
//        List<InWarehouseBillEntity> inWarehouseBillEntityList = inWarehouseBillMapper.selectList(new ExcludeEmptyQueryWrapper<InWarehouseBillEntity>().lambda()
//                .eq(InWarehouseBillEntity::getWarehouseCode, queryAttribute.getWarehouseCode())
//                .eq(InWarehouseBillEntity::getStatus, queryAttribute.getBillState())
//                .eq(InWarehouseBillEntity::getContractCode, queryAttribute.getContractCode())
//                .eq(InWarehouseBillEntity::getWorkCode,queryAttribute.getWorkCode())
//                .eq(InWarehouseBillEntity::getDealCode,queryAttribute.getDealCode())
//                .eq(InWarehouseBillEntity::getInPerson,queryAttribute.getInDept())
//                .eq(InWarehouseBillEntity::getInDept,queryAttribute.getInPerson()));
        List<InWarehouseBillEntity> inWarehouseBillEntityList = inWarehouseBillMapper.selectList(new ExcludeEmptyQueryWrapper<InWarehouseBillEntity>()
                .eq("WAREHOUSE_CODE", queryAttribute.getWarehouseCode())
                .eq("STATUS", queryAttribute.getBillStatus())
                .eq("CONTRACT_CODE", queryAttribute.getContractCode())
                .eq("WORK_CODE",queryAttribute.getWorkCode())
                .eq("DEAL_CODE",queryAttribute.getDealCode())
                .eq("IN_DEPT",queryAttribute.getInDept())
                .eq("IN_OPERATOR",queryAttribute.getInPerson()));

        if (null == inWarehouseBillEntityList)
        {
            inWarehouseBillEntityList = new ArrayList<InWarehouseBillEntity>();
        }
        return BeanListUtil.copyListProperties(inWarehouseBillEntityList,InWarehouseBillBo.class);
    }

    @Override
    public List<InWarehouseBillBo> queryUnFinishedInWarehouseBill(QueryInStorageAttribute queryAttribute) throws Exception
    {
//        List<InWarehouseBillEntity> inWarehouseBillEntityList = inWarehouseBillMapper.selectList(new ExcludeEmptyQueryWrapper<InWarehouseBillEntity>().lambda()
//                .eq(InWarehouseBillEntity::getWarehouseCode, queryAttribute.getWarehouseCode())
//                .ne(InWarehouseBillEntity::getStatus, BillState.FINISHED)
//                .eq(InWarehouseBillEntity::getContractCode, queryAttribute.getContractCode())
//                .eq(InWarehouseBillEntity::getWorkCode,queryAttribute.getWorkCode())
//                .eq(InWarehouseBillEntity::getDealCode,queryAttribute.getDealCode())
//                .eq(InWarehouseBillEntity::getInPerson,queryAttribute.getInDept())
//                .eq(InWarehouseBillEntity::getInDept,queryAttribute.getInPerson()));
        List<InWarehouseBillEntity> inWarehouseBillEntityList = inWarehouseBillMapper.selectList(new ExcludeEmptyQueryWrapper<InWarehouseBillEntity>()
                .eq("WAREHOUSE_CODE", queryAttribute.getWarehouseCode())
                .eq("STATUS", BillState.FINISHED)
                .eq("CONTRACT_CODE", queryAttribute.getContractCode())
                .eq("WORK_CODE",queryAttribute.getWorkCode())
                .eq("DEAL_CODE",queryAttribute.getDealCode())
                .eq("IN_DEPT",queryAttribute.getInDept())
                .eq("IN_OPERATOR",queryAttribute.getInPerson()));
        if (null == inWarehouseBillEntityList)
        {
            inWarehouseBillEntityList = new ArrayList<InWarehouseBillEntity>();
        }

        return BeanListUtil.copyListProperties(inWarehouseBillEntityList,InWarehouseBillBo.class);
    }

    @Override
    public boolean updateInWarehouseBillState(Long id, String state)
    {
        return inWarehouseBillMapper.updateInWarehouseBillStatus(id,state);
    }

}
