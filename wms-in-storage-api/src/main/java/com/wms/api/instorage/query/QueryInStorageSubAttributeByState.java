package com.wms.api.instorage.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author puck
 * @date 2020/12/28 11:20 上午
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "查询未入库子单条件", description = "查询入库子单条件，如果未空，不查询")
public class QueryInStorageSubAttributeByState extends QueryInStorageSubAttribute
{

    @ApiModelProperty(value = "订单状态")
    protected String billState;
}
