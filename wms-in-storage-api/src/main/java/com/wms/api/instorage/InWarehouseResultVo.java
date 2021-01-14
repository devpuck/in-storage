package com.wms.api.instorage;

import com.wms.api.account.AccountCertificateVo;
import com.wms.api.account.AccountVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author puck
 * @date 2021/1/13 7:11 下午
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "入库第三方通知", description = "入库第三方通知")
public class InWarehouseResultVo
{
    @ApiModelProperty("是否成功")
    boolean isSuccess;
    @ApiModelProperty("台账列表")
    List<AccountVo> accountVoList;
    @ApiModelProperty("流水列表")
    List<AccountCertificateVo> accountCertificateVoList;
    @ApiModelProperty("入库单")
    InWarehouseBillVo inWarehouseBillVo;
    @ApiModelProperty("入库子单")
    InWarehouseBillSubVo inWarehouseBillSubVo;
}
