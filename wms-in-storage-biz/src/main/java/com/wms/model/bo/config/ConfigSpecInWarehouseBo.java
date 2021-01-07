package com.wms.model.bo.config;

import com.xac.core.bo.BaseBo;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

import java.util.Date;

/**
 * <pre>
 * 入库配置 查询结果业务对象
 * </pre>
 *
 * @author puck
 * @date 2020-12-28
 */
@Data
@Accessors(chain = true)
public class ConfigSpecInWarehouseBo extends BaseBo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
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
     * 物料编码
     */
    private String productionCode;

    /**
     * 入库后库存状态
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
