package com.wms.api.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.xac.core.vo.BaseVo;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;

/**
 * <pre>
 * 入库配置 查询结果对象
 * </pre>
 *
 * @author puck
 * @date 2020-12-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ConfigInWarehouseVo对象", description = "入库配置查询参数")
public class ConfigInWarehouseVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表id")
    private Long id;

    @ApiModelProperty(value = "配置编号")
    private String configCode;

    @ApiModelProperty(value = "库房编码")
    private String warehouseCode;

    @ApiModelProperty(value = "入库事务编号")
    private String warehouseWorkCode;

    @ApiModelProperty(value = "入库流程编号")
    private String warehouseDealCode;

    @ApiModelProperty(value = "入库后质量状态")
    private String productionState;

    @ApiModelProperty(value = "是否需要质检")
    private String needQuality;

    @ApiModelProperty(value = "配置状态")
    private String status;

    @ApiModelProperty(value = "版本")
    private String version;

}
