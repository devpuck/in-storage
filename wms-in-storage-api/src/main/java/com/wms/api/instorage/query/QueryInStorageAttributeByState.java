package com.wms.api.instorage.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author puck
 * @date 2020/12/28 10:07 上午
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "InWarehouseBillVo对象", description = "入库单据查询参数")
public class QueryInStorageAttributeByState extends QueryInStorageAttribute
{
    @ApiModelProperty(value = "订单状态")
    protected String billState;
}
