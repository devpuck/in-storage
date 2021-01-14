package com.wms.service;

import com.wms.api.instorage.query.QueryInStorageSubAttribute;
import com.wms.api.instorage.query.QueryInStorageSubAttributeByState;
import com.wms.model.entity.InWarehouseBillSubEntity;
import com.xac.core.service.BaseService;
import com.wms.api.instorage.InWarehouseBillSubQueryParam;
import com.wms.model.bo.instorage.InWarehouseBillSubBo;
import com.xac.core.api.Paging;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * 入库单子表 服务类
 * </pre>
 *
 * @author puck
 * @since 2020-12-24
 */
public interface InWarehouseBillSubService extends BaseService<InWarehouseBillSubEntity> {

    /**
     * 保存
     *
     * @param inWarehouseBillSub
     * @return
     * @throws Exception
     */
    boolean saveInWarehouseBillSub(InWarehouseBillSubBo inWarehouseBillSub);

    /**
     * 修改
     *
     * @param inWarehouseBillSub
     * @return
     * @throws Exception
     */
    boolean updateInWarehouseBillSub(InWarehouseBillSubBo inWarehouseBillSub);

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteInWarehouseBillSub(Long id);

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    InWarehouseBillSubBo getInWarehouseBillSubById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param inWarehouseBillSubQueryParam
     * @return
     * @throws Exception
     */
    Paging<InWarehouseBillSubBo> getInWarehouseBillSubPageList(InWarehouseBillSubQueryParam inWarehouseBillSubQueryParam);

    /**
     * 根据入库单号查询入库子单详情
     * @param billCode
     * @return
     */
    List<InWarehouseBillSubBo> queryInWarehouseBillSubByBillCode(String billCode)  throws Exception;

    /**
     * 查询入库子单
     * @param queryInStorageSubAttribute
     * @return
     */
    public List<InWarehouseBillSubBo> queryUnFinishedInWarehouseBillSubByCondition(QueryInStorageSubAttribute queryInStorageSubAttribute) throws Exception;

    /**
     * 查询未完结的入库子单
     * @param queryInStorageSubAttribute
     * @return
     * @throws Exception
     */
    public List<InWarehouseBillSubBo> queryInWarehouseBillSubByCondition(QueryInStorageSubAttributeByState queryInStorageSubAttribute) throws Exception;

    /**
     * 增加入库单据已经入库数量
     * @param id
     * @param increaseQuantity
     * @return
     */
    public boolean increaseBillQuantity(Long id, BigDecimal increaseQuantity);

    /**
     * 查询已经入库数量
     * @param id
     * @return
     */
    public BigDecimal queryAlreadyInQuantity(Long id);


}
