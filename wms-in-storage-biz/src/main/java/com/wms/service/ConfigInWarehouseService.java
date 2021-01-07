package com.wms.service;

import com.wms.api.config.ConfigInWarehouseVo;
import com.wms.model.entity.ConfigInWarehouseEntity;
import com.xac.core.service.BaseService;
import com.wms.api.config.ConfigInWarehouseQueryParam;
import com.wms.model.bo.config.ConfigInWarehouseBo;
import com.xac.core.api.Paging;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 入库配置 服务类
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
public interface ConfigInWarehouseService extends BaseService<ConfigInWarehouseEntity> {

    /**
     * 保存
     *
     * @param configInWarehouse
     * @return
     * @throws Exception
     */
    boolean saveConfigInWarehouse(ConfigInWarehouseBo configInWarehouse);

    /**
     * 修改
     *
     * @param configInWarehouse
     * @return
     * @throws Exception
     */
    boolean updateConfigInWarehouse(ConfigInWarehouseBo configInWarehouse);

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteConfigInWarehouse(Long id);

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    ConfigInWarehouseBo getConfigInWarehouseById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param configInWarehouseQueryParam
     * @return
     * @throws Exception
     */
    Paging<ConfigInWarehouseBo> getConfigInWarehousePageList(ConfigInWarehouseQueryParam configInWarehouseQueryParam);

    /**
     * 查询库房
     * @param warehouseCode
     * @param workCode
     * @param dealCode
     * @return
     */
    ConfigInWarehouseBo queryConfigWarehouse(String warehouseCode,String workCode,String dealCode);

    /**
     * 查询库房事务配置
     * @param warehouseCode
     * @return
     */
    List<ConfigInWarehouseBo> queryWarehouseConfig(String warehouseCode) throws Exception;

}
