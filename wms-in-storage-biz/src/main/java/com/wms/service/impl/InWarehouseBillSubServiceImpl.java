package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.api.instorage.query.QueryInStorageSubAttribute;
import com.wms.api.instorage.query.QueryInStorageSubAttributeByState;
import com.wms.api.query.ExcludeEmptyQueryWrapper;
import com.wms.bill.BillState;
import com.xac.core.constant.CoreConstant;
import com.wms.model.entity.InWarehouseBillSubEntity;
import com.wms.mapper.instorage.InWarehouseBillSubMapper;
import com.wms.service.InWarehouseBillSubService;
import com.wms.api.instorage.InWarehouseBillSubQueryParam;
import com.wms.model.bo.instorage.InWarehouseBillSubBo;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 * 入库单子表 服务实现类
 * </pre>
 *
 * @author puck
 * @since 2020-12-24
 */
@Slf4j
@Service
public class InWarehouseBillSubServiceImpl extends BaseServiceImpl<InWarehouseBillSubMapper, InWarehouseBillSubEntity> implements InWarehouseBillSubService {

    @Autowired
    private InWarehouseBillSubMapper inWarehouseBillSubMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveInWarehouseBillSub(InWarehouseBillSubBo inWarehouseBillSub) {
        InWarehouseBillSubEntity entity = new InWarehouseBillSubEntity();
        BeanUtils.copyProperties(inWarehouseBillSub , entity);
        return super.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateInWarehouseBillSub(InWarehouseBillSubBo inWarehouseBillSub) {
        InWarehouseBillSubEntity entity = new InWarehouseBillSubEntity();
        BeanUtils.copyProperties(inWarehouseBillSub , entity);
        return super.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteInWarehouseBillSub(Long id) {
        return super.removeById(id);
    }

    @Override
    public InWarehouseBillSubBo getInWarehouseBillSubById(Serializable id) {
        return inWarehouseBillSubMapper.getInWarehouseBillSubById(id);
    }

    @Override
    public Paging<InWarehouseBillSubBo> getInWarehouseBillSubPageList(InWarehouseBillSubQueryParam inWarehouseBillSubQueryParam) {
        Page page = setPageParam(inWarehouseBillSubQueryParam, OrderItem.desc(CoreConstant.CREATED_DATE));
        IPage<InWarehouseBillSubBo> iPage = inWarehouseBillSubMapper.getInWarehouseBillSubPageList(page, inWarehouseBillSubQueryParam);
        return new Paging(iPage);
    }

    @Override
    public List<InWarehouseBillSubBo> queryInWarehouseBillSubByBillCode(String billCode)  throws Exception
    {
        List<InWarehouseBillSubEntity> inWarehouseBillSubEntityList = inWarehouseBillSubMapper.selectList(new QueryWrapper<InWarehouseBillSubEntity>().lambda()
                .eq(InWarehouseBillSubEntity::getBillCode,billCode));
        if(null == inWarehouseBillSubEntityList)
        {
            inWarehouseBillSubEntityList = new ArrayList<InWarehouseBillSubEntity>();
        }

        return BeanListUtil.copyListProperties(inWarehouseBillSubEntityList,InWarehouseBillSubBo.class);
    }

    @Override
    public List<InWarehouseBillSubBo> queryInWarehouseBillSubByCondition(QueryInStorageSubAttributeByState queryInStorageSubAttribute) throws Exception
    {
        //查询时，如果为null，条件自动匹配不查询
//        List<InWarehouseBillSubEntity> inWarehouseBillSubEntityList = inWarehouseBillSubMapper.selectList(new ExcludeEmptyQueryWrapper<InWarehouseBillSubEntity>()
//                .eq(InWarehouseBillSubEntity::getProductionCode,queryInStorageSubAttribute.getProductionCode())
//                .eq(InWarehouseBillSubEntity::getAircraftCode,queryInStorageSubAttribute.getAircraftCode())
//                .eq(InWarehouseBillSubEntity::getBatch,queryInStorageSubAttribute.getBatch())
//                .eq(InWarehouseBillSubEntity::getStatus,queryInStorageSubAttribute.getBillState())
//                .eq(InWarehouseBillSubEntity::getBatch,queryInStorageSubAttribute.getBrand())
//                .eq(InWarehouseBillSubEntity::getCertificateNo,queryInStorageSubAttribute.getCertificateNo())
//                .eq(InWarehouseBillSubEntity::getConstructionCode,queryInStorageSubAttribute.getConstructionCode())
//                .eq(InWarehouseBillSubEntity::getExpirationDate,queryInStorageSubAttribute.getExpirationDate())
//                .eq(InWarehouseBillSubEntity::getModelCode,queryInStorageSubAttribute.getModelCode())
//                .eq(InWarehouseBillSubEntity::getProduceStatus,queryInStorageSubAttribute.getProduceStatus())
//                .eq(InWarehouseBillSubEntity::getProjectCode,queryInStorageSubAttribute.getProjectCode())
//                .eq(InWarehouseBillSubEntity::getQualityStatus,queryInStorageSubAttribute.getQualityStatus())
//                .eq(InWarehouseBillSubEntity::getSortieCode,queryInStorageSubAttribute.getSortieCode())
//                .eq(InWarehouseBillSubEntity::getSplysotCode,queryInStorageSubAttribute.getSplysotCode())
//                .eq(InWarehouseBillSubEntity::getSupplierBy,queryInStorageSubAttribute.getSupplierBy())
//                .eq(InWarehouseBillSubEntity::getSupplierType,queryInStorageSubAttribute.getSupplierType())
//                .eq(InWarehouseBillSubEntity::getWarehouseCode,queryInStorageSubAttribute.getWarehouseCode())
//                .eq(InWarehouseBillSubEntity::getWarehouseLocationCode,queryInStorageSubAttribute.getWarehouseLocationCode())
//                .eq(InWarehouseBillSubEntity::getManufactureDate,queryInStorageSubAttribute.getManufactureDate())
//                .eq(InWarehouseBillSubEntity::getAttribute1,queryInStorageSubAttribute.getAttribute1())
//                .eq(InWarehouseBillSubEntity::getAttribute2,queryInStorageSubAttribute.getAttribute2())
//                .eq(InWarehouseBillSubEntity::getAttribute3,queryInStorageSubAttribute.getAttribute3())
//                .eq(InWarehouseBillSubEntity::getAttribute4,queryInStorageSubAttribute.getAttribute4())
//                .eq(InWarehouseBillSubEntity::getAttribute5,queryInStorageSubAttribute.getAttribute5()));

        List<InWarehouseBillSubEntity> inWarehouseBillSubEntityList = inWarehouseBillSubMapper.selectList(new ExcludeEmptyQueryWrapper<InWarehouseBillSubEntity>()
                .eq("PRODUCTION_CODE",queryInStorageSubAttribute.getProductionCode())
                .eq("AIRCRAFT_CODE",queryInStorageSubAttribute.getAircraftCode())
                .eq("BATCH",queryInStorageSubAttribute.getBatch())
                .eq("STATUS",queryInStorageSubAttribute.getBillState())
                .eq("BATCH",queryInStorageSubAttribute.getBrand())
                .eq("CERTIFICATE_NO",queryInStorageSubAttribute.getCertificateNo())
                .eq("CONSTRUCTION_CODE",queryInStorageSubAttribute.getConstructionCode())
                .eq("EXPIRATION_DATE",queryInStorageSubAttribute.getExpirationDate())
                .eq("MODEL_CODE",queryInStorageSubAttribute.getModelCode())
                .eq("PRODUCE_STATUS",queryInStorageSubAttribute.getProduceStatus())
                .eq("PROJECT_CODE",queryInStorageSubAttribute.getProjectCode())
                .eq("QUALITY_STATUS",queryInStorageSubAttribute.getQualityStatus())
                .eq("SORTIE_CODE",queryInStorageSubAttribute.getSortieCode())
                .eq("SPLYSOT_CODE",queryInStorageSubAttribute.getSplysotCode())
                .eq("SUPPLIER_BY",queryInStorageSubAttribute.getSupplierBy())
                .eq("SUPPLIER_TYPE",queryInStorageSubAttribute.getSupplierType())
                .eq("WAREHOUSE_CODE",queryInStorageSubAttribute.getWarehouseCode())
                .eq("WAREHOUSE_LOCATION_CODE",queryInStorageSubAttribute.getWarehouseLocationCode())
                .eq("MANUFACTURE_DATE",queryInStorageSubAttribute.getManufactureDate())
                .eq("ATTRIBUTE1",queryInStorageSubAttribute.getAttribute1())
                .eq("ATTRIBUTE2",queryInStorageSubAttribute.getAttribute2())
                .eq("ATTRIBUTE3",queryInStorageSubAttribute.getAttribute3())
                .eq("ATTRIBUTE4",queryInStorageSubAttribute.getAttribute4())
                .eq("ATTRIBUTE5",queryInStorageSubAttribute.getAttribute5()));

        if(null == inWarehouseBillSubEntityList)
        {
            inWarehouseBillSubEntityList = new ArrayList<InWarehouseBillSubEntity>();
        }

        return BeanListUtil.copyListProperties(inWarehouseBillSubEntityList,InWarehouseBillSubBo.class);
    }

    @Override
    public boolean increaseBillQuantity(Long id, BigDecimal increaseQuantity)
    {
        boolean result = false;
        result =  inWarehouseBillSubMapper.increaseBillQuantity(id,increaseQuantity);
        if(!result)
        {
            return result;
        }

        InWarehouseBillSubBo inWarehouseBillSubBo = inWarehouseBillSubMapper.getInWarehouseBillSubById(id);
        if(null == inWarehouseBillSubBo)
        {
            return result;
        }

        if(inWarehouseBillSubBo.getAlreadyInQuantity().compareTo(inWarehouseBillSubBo.getQuantity())>=0)
        {
            return inWarehouseBillSubMapper.updateState(id,BillState.FINISHED);
        }
        return result;
    }

    @Override
    public BigDecimal queryAlreadyInQuantity(Long id)
    {
        return inWarehouseBillSubMapper.queryAlreadyInQuantityBigDecimal(id);
    }


    @Override
    public List<InWarehouseBillSubBo> queryUnFinishedInWarehouseBillSubByCondition(QueryInStorageSubAttribute queryInStorageSubAttribute) throws Exception
    {
        //查询时，如果为null，条件自动匹配不查询,查询所有未关闭的入库子单
        List<InWarehouseBillSubEntity> inWarehouseBillSubEntityList = inWarehouseBillSubMapper.selectList(new ExcludeEmptyQueryWrapper<InWarehouseBillSubEntity>()
                .eq("PRODUCTION_CODE",queryInStorageSubAttribute.getProductionCode())
                .eq("AIRCRAFT_CODE",queryInStorageSubAttribute.getAircraftCode())
                .eq("BATCH",queryInStorageSubAttribute.getBatch())
                .eq("STATUS",BillState.FINISHED)
                .eq("BATCH",queryInStorageSubAttribute.getBrand())
                .eq("CERTIFICATE_NO",queryInStorageSubAttribute.getCertificateNo())
                .eq("CONSTRUCTION_CODE",queryInStorageSubAttribute.getConstructionCode())
                .eq("EXPIRATION_DATE",queryInStorageSubAttribute.getExpirationDate())
                .eq("MODEL_CODE",queryInStorageSubAttribute.getModelCode())
                .eq("PRODUCE_STATUS",queryInStorageSubAttribute.getProduceStatus())
                .eq("PROJECT_CODE",queryInStorageSubAttribute.getProjectCode())
                .eq("QUALITY_STATUS",queryInStorageSubAttribute.getQualityStatus())
                .eq("SORTIE_CODE",queryInStorageSubAttribute.getSortieCode())
                .eq("SPLYSOT_CODE",queryInStorageSubAttribute.getSplysotCode())
                .eq("SUPPLIER_BY",queryInStorageSubAttribute.getSupplierBy())
                .eq("SUPPLIER_TYPE",queryInStorageSubAttribute.getSupplierType())
                .eq("WAREHOUSE_CODE",queryInStorageSubAttribute.getWarehouseCode())
                .eq("WAREHOUSE_LOCATION_CODE",queryInStorageSubAttribute.getWarehouseLocationCode())
                .eq("MANUFACTURE_DATE",queryInStorageSubAttribute.getManufactureDate())
                .eq("ATTRIBUTE1",queryInStorageSubAttribute.getAttribute1())
                .eq("ATTRIBUTE2",queryInStorageSubAttribute.getAttribute2())
                .eq("ATTRIBUTE3",queryInStorageSubAttribute.getAttribute3())
                .eq("ATTRIBUTE4",queryInStorageSubAttribute.getAttribute4())
                .eq("ATTRIBUTE5",queryInStorageSubAttribute.getAttribute5()));
        if(null == inWarehouseBillSubEntityList)
        {
            inWarehouseBillSubEntityList = new ArrayList<InWarehouseBillSubEntity>();
        }

        return BeanListUtil.copyListProperties(inWarehouseBillSubEntityList,InWarehouseBillSubBo.class);
    }

}
