package com.wms.service;

import com.wms.api.instorage.query.QueryInStorageAttribute;
import com.wms.api.instorage.query.QueryInStorageAttributeByState;
import com.wms.model.entity.InWarehouseBillEntity;
import com.xac.core.service.BaseService;
import com.wms.api.instorage.InWarehouseBillQueryParam;
import com.wms.model.bo.instorage.InWarehouseBillBo;
import com.xac.core.api.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 入库单据 服务类
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
public interface InWarehouseBillService extends BaseService<InWarehouseBillEntity>
{

    /**
     * 保存
     *
     * @param inWarehouseBill
     * @return
     * @throws Exception
     */
    boolean saveInWarehouseBill(InWarehouseBillBo inWarehouseBill) throws Exception;

    /**
     * 修改
     *
     * @param inWarehouseBill
     * @return
     * @throws Exception
     */
    boolean updateInWarehouseBill(InWarehouseBillBo inWarehouseBill);

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteInWarehouseBill(Long id);

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    InWarehouseBillBo getInWarehouseBillById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param inWarehouseBillQueryParam
     * @return
     * @throws Exception
     */
    Paging<InWarehouseBillBo> getInWarehouseBillPageList(InWarehouseBillQueryParam inWarehouseBillQueryParam);

    /**
     * 根据需求单ID查询入库单ID，作为判断是否重复单据使用
     *
     * @param requirementID
     * @return
     */
    String queryInWarehouseBillIDByRequirementID(String requirementID);

    /**
     * 根据需求单ID查询入库单
     *
     * @param requirementID
     * @return
     */
    InWarehouseBillBo queryInWarehouseBillByRequirementID(String requirementID) throws Exception;

    /**
     * 根据入库单号查询入库订单
     * @param billCode
     * @return
     * @throws Exception
     */
    InWarehouseBillBo queryInWarehouseBillByBillCode(String billCode)  throws Exception;

    /**
     * 根据物料编码、入库单据状态查询入库单据
     *
     * @param productionCode
     * @param billState
     * @return
     */
    List<InWarehouseBillBo> queryInWarehouseBillByProductionCode(String productionCode, String billState) throws Exception;

    /**
     * 根据库房查询入库单据
     *
     * @param warehouseCode
     * @return
     */
    List<InWarehouseBillBo> queryInWarehouseBill(String warehouseCode) throws Exception;

    /**
     * 根据库房编码、入库单状态查询入库单据
     *
     * @param warehouseCode
     * @param billState
     * @return
     */
    List<InWarehouseBillBo> queryInWarehouseBill(String warehouseCode, String billState) throws Exception;

    /**
     * 根据查询条件查询入库订单
     * @param queryAttribute
     * @return
     * @throws Exception
     */
    List<InWarehouseBillBo> queryInWarehouseBill(QueryInStorageAttributeByState queryAttribute) throws Exception;

    /**
     * 根据查询条件查询未完成入库订单
     * @param queryAttribute
     * @return
     * @throws Exception
     */
    List<InWarehouseBillBo> queryUnFinishedInWarehouseBill(QueryInStorageAttribute queryAttribute) throws Exception;

}