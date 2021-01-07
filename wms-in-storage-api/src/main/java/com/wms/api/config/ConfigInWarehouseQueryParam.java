package com.wms.api.config;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.xac.core.api.SortQueryParam;

/**
 * <pre>
 * 入库配置 查询参数对象
 * </pre>
 *
 * @author puck
 * @date 2020-12-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ConfigInWarehouseQueryParam对象", description = "入库配置查询参数")
public class ConfigInWarehouseQueryParam extends SortQueryParam {
    private static final long serialVersionUID = 1L;
}
