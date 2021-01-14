package com.wms.mapper.instorage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.model.entity.InWarehouseBillEntity;
import com.wms.api.instorage.InWarehouseBillQueryParam;
import com.wms.model.bo.instorage.InWarehouseBillBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 入库单据 Mapper 接口
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Repository
public interface InWarehouseBillMapper extends BaseMapper<InWarehouseBillEntity> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    InWarehouseBillBo getInWarehouseBillById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param inWarehouseBillQueryParam
     * @return
     */
    IPage<InWarehouseBillBo> getInWarehouseBillPageList(@Param("page") Page page, @Param("param") InWarehouseBillQueryParam inWarehouseBillQueryParam);

    public String queryInWarehouseBillIDByRequirementID(String requirementID);

    /**
     * 根据单据编号查询单据ID
     * @param billCode
     * @return
     */
    public String queryInWarehouseBillIDByBillCode(String billCode);

    List<InWarehouseBillBo> queryInWarehouseBillByProductionCode(String productionCode, String status);

    /**
     * 更新入库单据状态
     * @param id
     * @param status
     * @return
     */
    public boolean updateInWarehouseBillStatus(Long id,String status);

}
