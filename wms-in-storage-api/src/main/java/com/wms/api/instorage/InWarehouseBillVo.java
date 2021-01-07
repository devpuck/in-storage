package com.wms.api.instorage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.xac.core.vo.BaseVo;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * 入库单据 查询结果对象
 * </pre>
 *
 * @author puck
 * @date 2020-12-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "InWarehouseBillVo对象", description = "入库单据查询参数")
public class InWarehouseBillVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "表id")
    private Long id;

    @ApiModelProperty(value = "需求单ID")
    private String requirementId;

    @ApiModelProperty(value = "合同单号")
    private String contractCode;

    @ApiModelProperty(value = "需求来源系统，隐藏字段，页面不展示")
    private String systemFrom;

    @ApiModelProperty(value = "入库单号")
    private String billCode;

    @ApiModelProperty(value = "库房编号")
    private String warehouseCode;

    @ApiModelProperty(value = "入库单类型，对应事务类型")
    private String workCode;

    @ApiModelProperty(value = "处理类型编号，如合同到货、紧急到货。相当于WORK_CODE的一个补充，实际操作只需要配置即可，无需理会，主要用于处理老事务处理不规范情况")
    private String dealCode;

    @ApiModelProperty(value = "入库人，负责入库单人，非操作员。通常为负责入库货物单的负责人，如计划员或者生产人")
    private String inPerson;

    @ApiModelProperty(value = "入库部门，负责入库货物的部门")
    private String inDept;

    @ApiModelProperty(value = "操作人")
    private String inOperator;

    @ApiModelProperty(value = "操作人部门")
    private String inOperatorDept;

    @ApiModelProperty(value = "操作时间")
    private Date inOperatorDate;

    @ApiModelProperty(value = "入库单状态")
    private String status;

    @ApiModelProperty(value = "版本")
    private String version;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "入库子单详情")
    private List<InWarehouseBillSubVo> inWarehouseBillSubVoList;

}
