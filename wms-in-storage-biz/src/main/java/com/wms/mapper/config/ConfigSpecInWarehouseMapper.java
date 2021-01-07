package com.wms.mapper.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.model.entity.ConfigSpecInWarehouseEntity;
import com.wms.api.config.ConfigSpecInWarehouseQueryParam;
import com.wms.model.bo.config.ConfigSpecInWarehouseBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 * 入库配置 Mapper 接口
 * </pre>
 *
 * @author puck
 * @since 2020-12-28
 */
@Repository
public interface ConfigSpecInWarehouseMapper extends BaseMapper<ConfigSpecInWarehouseEntity> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    ConfigSpecInWarehouseBo getConfigSpecInWarehouseById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param configSpecInWarehouseQueryParam
     * @return
     */
    IPage<ConfigSpecInWarehouseBo> getConfigSpecInWarehousePageList(@Param("page") Page page, @Param("param") ConfigSpecInWarehouseQueryParam configSpecInWarehouseQueryParam);

}
