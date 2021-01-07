package com.wms.api.instorage;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.xac.core.api.SortQueryParam;

/**
 * <pre>
 * 入库单子表 查询参数对象
 * </pre>
 *
 * @author puck
 * @date 2020-12-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "InWarehouseBillSubQueryParam对象", description = "入库单子表查询参数")
public class InWarehouseBillSubQueryParam extends SortQueryParam {
    private static final long serialVersionUID = 1L;
}
