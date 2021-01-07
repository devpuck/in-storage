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
public class QueryInStorageAttribute
{
    @ApiModelProperty(value = "库房编号")
    protected String warehouseCode ;

    @ApiModelProperty(value = "合同编号")
    protected String contractCode;

    @ApiModelProperty("入库单类型，对应事务类型")
    protected String workCode;

    @ApiModelProperty("处理类型编号，如合同到货、紧急到货。相当于WORK_CODE的一个补充，实际操作只需要配置即可，无需理会，主要用于处理老事务处理不规范情况")

    protected String dealCode;
    @ApiModelProperty("入库人，负责入库单人，非操作员。通常为负责入库货物单的负责人，如计划员或者生产人")

    protected String inPerson;
    @ApiModelProperty("入库部门，负责入库货物的部门")
    protected String inDept;

}
