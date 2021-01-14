package com.wms.mapper.instorage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.api.instorage.query.QueryInStorageSubAttribute;
import com.wms.model.entity.InWarehouseBillSubEntity;
import com.wms.api.instorage.InWarehouseBillSubQueryParam;
import com.wms.model.bo.instorage.InWarehouseBillSubBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <pre>
 * 入库单子表 Mapper 接口
 * </pre>
 *
 * @author puck
 * @since 2020-12-24
 */
@Repository
public interface InWarehouseBillSubMapper extends BaseMapper<InWarehouseBillSubEntity> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    InWarehouseBillSubBo getInWarehouseBillSubById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param inWarehouseBillSubQueryParam
     * @return
     */
    IPage<InWarehouseBillSubBo> getInWarehouseBillSubPageList(@Param("page") Page page, @Param("param") InWarehouseBillSubQueryParam inWarehouseBillSubQueryParam);

    /**
     * 更新已经入库数量
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
    BigDecimal queryAlreadyInQuantityBigDecimal(Long id);

    /**
     * 更新子单状态
     * @param id
     * @param status
     * @return
     */
    public boolean updateState(Long id,String status);

}
