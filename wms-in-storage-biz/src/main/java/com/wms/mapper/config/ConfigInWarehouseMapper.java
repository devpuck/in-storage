package com.wms.mapper.config;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wms.model.entity.ConfigInWarehouseEntity;
import com.wms.api.config.ConfigInWarehouseQueryParam;
import com.wms.model.bo.config.ConfigInWarehouseBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <pre>
 * 入库配置 Mapper 接口
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Repository
public interface ConfigInWarehouseMapper extends BaseMapper<ConfigInWarehouseEntity> {

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     */
    ConfigInWarehouseBo getConfigInWarehouseById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param page
     * @param configInWarehouseQueryParam
     * @return
     */
    IPage<ConfigInWarehouseBo> getConfigInWarehousePageList(@Param("page") Page page, @Param("param") ConfigInWarehouseQueryParam configInWarehouseQueryParam);

}
