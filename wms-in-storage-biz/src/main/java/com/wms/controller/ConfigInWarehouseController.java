package com.wms.controller;

import com.wms.errorcode.ErrorCode;
import com.wms.util.CheckParameter;
import com.xac.core.api.ApiResultCode;
import com.xac.core.util.BeanListUtil;
import com.wms.service.ConfigInWarehouseService;
import com.wms.api.config.ConfigInWarehouseQueryParam;
import com.wms.api.config.ConfigInWarehouseVo;
import com.wms.model.bo.config.ConfigInWarehouseBo;
import com.xac.core.api.ApiResult;
import com.xac.core.api.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;


import javax.validation.Valid;

import com.xac.core.api.Paging;

import java.util.List;

/**
 * <pre>
 * 入库配置 前端控制器
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Slf4j
@RestController
@RequestMapping("/config")
@Api("入库配置 API")
public class ConfigInWarehouseController extends BaseController {

    @Autowired
    private ConfigInWarehouseService configInWarehouseService;

    /**
     * 添加入库配置
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加ConfigInWarehouse对象", notes = "添加入库配置", response = ApiResult.class)
    public ApiResult<Boolean> addConfigInWarehouse(@Valid @RequestBody ConfigInWarehouseVo configInWarehouse) throws Exception
    {
        String warehouseCode = configInWarehouse.getWarehouseCode();
        String workCode = configInWarehouse.getWarehouseWorkCode();
        String dealCode = configInWarehouse.getWarehouseDealCode();
        if(ErrorCode.OK!= CheckParameter.checkParameter(warehouseCode,workCode,dealCode))
        {
            return ApiResult.result(ApiResultCode.PARAMETER_EXCEPTION);
        }

        ConfigInWarehouseBo beforeConfigInWarehouseBo = configInWarehouseService.queryConfigWarehouse(warehouseCode,workCode,dealCode);
        if(null != beforeConfigInWarehouseBo)
        {
            ApiResult apiResult = new ApiResult();
            apiResult.setCode(ErrorCode.WAREHOUSE_CONFIG_IN_REPEAT);
            apiResult.setMsg(ErrorCode.WAREHOUSE_CONFIG_IN_REPEAT_MESSAGE);
            apiResult.setSuccess(false);
            return apiResult;
        }

        ConfigInWarehouseBo bo = new ConfigInWarehouseBo();
        BeanUtils.copyProperties(configInWarehouse,bo);

        boolean flag = configInWarehouseService.saveConfigInWarehouse(bo);
        return ApiResult.result(flag);
    }

    /**
     * 修改入库配置
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改ConfigInWarehouse对象", notes = "修改入库配置", response = ApiResult.class)
    public ApiResult<Boolean> updateConfigInWarehouse(@Valid @RequestBody ConfigInWarehouseVo configInWarehouse) throws Exception {
        ConfigInWarehouseBo bo = new ConfigInWarehouseBo();
        BeanUtils.copyProperties(configInWarehouse,bo);

        boolean flag = configInWarehouseService.updateConfigInWarehouse(bo);
        return ApiResult.result(flag);
    }

    /**
     * 删除入库配置
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除ConfigInWarehouse对象", notes = "删除入库配置", response = ApiResult.class)
    public ApiResult<Boolean> deleteConfigInWarehouse(@PathVariable("id") Long id) throws Exception {
        boolean flag = configInWarehouseService.deleteConfigInWarehouse(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取入库配置
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取ConfigInWarehouse对象详情", notes = "查看入库配置", response = ConfigInWarehouseVo.class)
    public ApiResult<ConfigInWarehouseVo> getConfigInWarehouse(@PathVariable("id") Long id) throws Exception {
        ConfigInWarehouseBo configInWarehouseBo = configInWarehouseService.getConfigInWarehouseById(id);
        ConfigInWarehouseVo queryVo = null;
        if (configInWarehouseBo != null) {
            queryVo = new ConfigInWarehouseVo();
            BeanUtils.copyProperties(configInWarehouseBo , queryVo);
        }
        return ApiResult.ok(queryVo);
    }

    /**
     * 入库配置分页列表
     */
    @PostMapping("/pagelist")
    @ApiOperation(value = "获取ConfigInWarehouse分页列表", notes = "入库配置分页列表", response = ConfigInWarehouseVo.class)
    public ApiResult<Paging<ConfigInWarehouseVo>> getConfigInWarehousePageList(@Valid @RequestBody ConfigInWarehouseQueryParam configInWarehouseQueryParam) throws Exception {
        Paging<ConfigInWarehouseBo> paging = configInWarehouseService.getConfigInWarehousePageList(configInWarehouseQueryParam);
        Paging<ConfigInWarehouseVo> resultPage = new Paging<>();
        resultPage.setTotal(paging.getTotal());
        resultPage.setRecords(BeanListUtil.copyListProperties(paging.getRecords(), ConfigInWarehouseVo.class));
        return ApiResult.ok(resultPage);
    }

    /**
     * 根据库房查询入库配置列表
     */
    @PostMapping("/query/queryByWarehouseList")
    @ApiOperation(value = "根据库房查询入库配置列表", notes = "根据库房查询入库配置列表", response = ConfigInWarehouseVo.class)
    public ApiResult<List<ConfigInWarehouseVo>> queryConfigInWarehouseList(@RequestParam String warehouseCode) throws Exception
    {
        List<ConfigInWarehouseBo> boList = configInWarehouseService.queryWarehouseConfig(warehouseCode);
        List<ConfigInWarehouseVo> voList = BeanListUtil.copyListProperties(boList,ConfigInWarehouseVo.class);
        return ApiResult.ok(voList);
    }

    /**
     * 根据库房查询入库配置
     */
    @PostMapping("/query/queryInfo")
    @ApiOperation(value = "根据库房查询入库配置", notes = "根据库房查询入库配置", response = ConfigInWarehouseVo.class)
    public ApiResult<ConfigInWarehouseVo> queryInfo(@RequestParam String warehouseCode,@RequestParam String workCode,@RequestParam String dealCode) throws Exception {
        ConfigInWarehouseBo configInWarehouseBo = configInWarehouseService.queryConfigWarehouse(warehouseCode,workCode,dealCode);
        ConfigInWarehouseVo queryVo = null;
        if (configInWarehouseBo != null)
        {
            queryVo = new ConfigInWarehouseVo();
            BeanUtils.copyProperties(configInWarehouseBo , queryVo);
        }
        return ApiResult.ok(queryVo);
    }

}

