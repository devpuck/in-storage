package com.wms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * 入库配置
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("CONFIG_IN_WAREHOUSE")
public class ConfigInWarehouseEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    /**
     * 配置编号
     */
    private String configCode;

    /**
     * 库房编码
     */
    private String warehouseCode;

    /**
     * 入库事务编号
     */
    private String warehouseWorkCode;

    /**
     * 入库流程编号
     */
    private String warehouseDealCode;

    /**
     * 入库后质量状态
     */
    private String productionState;

    private String needQuality;

    /**
     * 配置状态
     */
    private String status;

    /**
     * 版本
     */
    private String version;

}
