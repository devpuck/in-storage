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
@ApiModel(value = "查询入库子单条件", description = "查询入库子单条件，如果未空，不查询")
public class QueryInStorageSubAttribute
{
    @ApiModelProperty(value = "物料编码")
    protected String productionCode;

    @ApiModelProperty(value = "库房编号")
    protected String warehouseCode ;

    @ApiModelProperty(value = "库位号")
    protected String warehouseLocationCode;

    @ApiModelProperty(value = "物料制造处理状态，produce 在制品，clout 余料，waste废料,正常normal")
    protected String produceStatus;

    @ApiModelProperty(value = "项目编码，批次属性")
    protected String projectCode;

    @ApiModelProperty(value = "牌号，本为物料属性，但是部分为1：n关系，目前无后期逻辑处理，仅仅记录")
    protected String brand;

    @ApiModelProperty(value = "过期日期，批次属性")
    protected Date expirationDate;

    @ApiModelProperty(value = "生产日期，批次属性")
    protected Date manufactureDate;

    @ApiModelProperty(value = "批次号，批次属性")
    protected String batch;

    @ApiModelProperty(value = "炉批号或小号，批次属性")
    protected String splysotCode;

    @ApiModelProperty(value = "机型，批次属性")
    protected String aircraftCode;

    @ApiModelProperty(value = "架次，批次属性")
    protected String sortieCode;

    @ApiModelProperty(value = "版号，批次属性")
    protected String modelCode;

    @ApiModelProperty(value = "构型号，批次属性")
    protected String constructionCode;

    @ApiModelProperty(value = "质检状态，批次属性")
    protected String qualityStatus;

    @ApiModelProperty(value = "合格证号，批次属性")
    protected String certificateNo;

    @ApiModelProperty(value = "供方合格证号")
    protected String supplyCertificateNo;

    @ApiModelProperty(value = "提供者类型")
    protected String supplierType;

    @ApiModelProperty(value = "提供者")
    protected String supplierBy;

    @ApiModelProperty(value = "自定义批次属性")
    protected String attribute1;

    @ApiModelProperty(value = "自定义批次属性")
    protected String attribute2;

    @ApiModelProperty(value = "自定义批次属性")
    protected String attribute3;

    @ApiModelProperty(value = "自定义批次属性")
    protected String attribute4;

    @ApiModelProperty(value = "自定义批次属性")
    protected String attribute5;
}
