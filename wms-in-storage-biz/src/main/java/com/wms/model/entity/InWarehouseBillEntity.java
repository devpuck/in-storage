package com.wms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.xac.core.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <pre>
 * 入库单据
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("WMS_IN_WAREHOUSE_BILL")
public class InWarehouseBillEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 需求单ID
     */
    private String requirementId;

    /**
     * 合同单号
     */
    private String contractCode;

    /**
     * 需求来源系统，隐藏字段，页面不展示
     */
    private String systemFrom;

    /**
     * 入库单号
     */
    private String billCode;

    /**
     * 库房编号
     */
    private String warehouseCode;

    /**
     * 入库单类型，对应事务类型
     */
    private String workCode;

    /**
     * 处理类型编号，如合同到货、紧急到货。相当于WORK_CODE的一个补充，实际操作只需要配置即可，无需理会，主要用于处理老事务处理不规范情况
     */
    private String dealCode;

    /**
     * 入库人，负责入库单人，非操作员。通常为负责入库货物单的负责人，如计划员或者生产人
     */
    private String inPerson;

    /**
     * 入库部门，负责入库货物的部门
     */
    private String inDept;

    /**
     * 操作人
     */
    private String inOperator;

    /**
     * 操作人部门
     */
    private String inOperatorDept;

    /**
     * 操作时间
     */
    private Date inOperatorDate;

    /**
     * 入库单状态
     */
    private String status;

    /**
     * 版本
     */
    private String version;

    /**
     * 备注
     */
    private String note;

}
