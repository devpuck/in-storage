package com.wms.service;

import com.wms.model.entity.ConfigSpecInWarehouseEntity;
import com.xac.core.service.BaseService;
import com.wms.api.config.ConfigSpecInWarehouseQueryParam;
import com.wms.model.bo.config.ConfigSpecInWarehouseBo;
import com.xac.core.api.Paging;
import sun.security.krb5.Config;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 入库配置 服务类
 * </pre>
 *
 * @author puck
 * @since 2020-12-28
 */
public interface ConfigSpecInWarehouseService extends BaseService<ConfigSpecInWarehouseEntity> {

    /**
     * 保存
     *
     * @param configSpecInWarehouse
     * @return
     * @throws Exception
     */
    boolean saveConfigSpecInWarehouse(ConfigSpecInWarehouseBo configSpecInWarehouse);

    /**
     * 修改
     *
     * @param configSpecInWarehouse
     * @return
     * @throws Exception
     */
    boolean updateConfigSpecInWarehouse(ConfigSpecInWarehouseBo configSpecInWarehouse);

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteConfigSpecInWarehouse(Long id);

    /**
     * 根据ID获取查询对象
     *
     * @param id
     * @return
     * @throws Exception
     */
    ConfigSpecInWarehouseBo getConfigSpecInWarehouseById(Serializable id);

    /**
     * 获取分页对象
     *
     * @param configSpecInWarehouseQueryParam
     * @return
     * @throws Exception
     */
    Paging<ConfigSpecInWarehouseBo> getConfigSpecInWarehousePageList(ConfigSpecInWarehouseQueryParam configSpecInWarehouseQueryParam);

    /**
     * 查询特殊的入库配置
     * @param warehouseCode
     * @param workCode
     * @param dealCode
     * @param productionCode
     * @return
     */
    ConfigSpecInWarehouseBo queryConfigSpecInWarehouse(String warehouseCode,String workCode,String dealCode,String productionCode);

    /**
     * 查询库房特殊入库配置
     * @param warehouseCode
     * @return
     */
    List<ConfigSpecInWarehouseBo> queryWarehouseSpecConfig(String warehouseCode) throws Exception;

}
