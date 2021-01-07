package com.wms.controller;

import com.xac.core.util.BeanListUtil;
import com.wms.model.entity.ConfigSpecInWarehouseEntity;
import com.wms.service.ConfigSpecInWarehouseService;
import com.wms.api.config.ConfigSpecInWarehouseQueryParam;
import com.wms.api.config.ConfigSpecInWarehouseVo;
import com.wms.model.bo.config.ConfigSpecInWarehouseBo;
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

/**
 * <pre>
 * 入库配置 前端控制器
 * </pre>
 *
 * @author puck
 * @since 2020-12-28
 */
@Slf4j
@RestController
@RequestMapping("/configspc")
@Api("入库配置 API")
public class ConfigSpecInWarehouseController extends BaseController {

    @Autowired
    private ConfigSpecInWarehouseService configSpecInWarehouseService;

    /**
     * 添加入库配置
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加ConfigSpecInWarehouse对象", notes = "添加入库配置", response = ApiResult.class)
    public ApiResult<Boolean> addConfigSpecInWarehouse(@Valid @RequestBody ConfigSpecInWarehouseVo configSpecInWarehouse) throws Exception {
         ConfigSpecInWarehouseBo bo = new ConfigSpecInWarehouseBo();
        BeanUtils.copyProperties(configSpecInWarehouse,bo);

        boolean flag = configSpecInWarehouseService.saveConfigSpecInWarehouse(bo);
        return ApiResult.result(flag);
    }

    /**
     * 修改入库配置
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改ConfigSpecInWarehouse对象", notes = "修改入库配置", response = ApiResult.class)
    public ApiResult<Boolean> updateConfigSpecInWarehouse(@Valid @RequestBody ConfigSpecInWarehouseVo configSpecInWarehouse) throws Exception {
        ConfigSpecInWarehouseBo bo = new ConfigSpecInWarehouseBo();
        BeanUtils.copyProperties(configSpecInWarehouse,bo);

        boolean flag = configSpecInWarehouseService.updateConfigSpecInWarehouse(bo);
        return ApiResult.result(flag);
    }

    /**
     * 删除入库配置
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除ConfigSpecInWarehouse对象", notes = "删除入库配置", response = ApiResult.class)
    public ApiResult<Boolean> deleteConfigSpecInWarehouse(@PathVariable("id") Long id) throws Exception {
        boolean flag = configSpecInWarehouseService.deleteConfigSpecInWarehouse(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取入库配置
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取ConfigSpecInWarehouse对象详情", notes = "查看入库配置", response = ConfigSpecInWarehouseVo.class)
    public ApiResult<ConfigSpecInWarehouseVo> getConfigSpecInWarehouse(@PathVariable("id") Long id) throws Exception {
        ConfigSpecInWarehouseBo configSpecInWarehouseBo = configSpecInWarehouseService.getConfigSpecInWarehouseById(id);
        ConfigSpecInWarehouseVo queryVo = null;
        if (configSpecInWarehouseBo != null) {
            queryVo = new ConfigSpecInWarehouseVo();
            BeanUtils.copyProperties(configSpecInWarehouseBo , queryVo);
        }
        return ApiResult.ok(queryVo);
    }

    /**
     * 入库配置分页列表
     */
    @PostMapping("/pagelist")
    @ApiOperation(value = "获取ConfigSpecInWarehouse分页列表", notes = "入库配置分页列表", response = ConfigSpecInWarehouseVo.class)
    public ApiResult<Paging<ConfigSpecInWarehouseVo>> getConfigSpecInWarehousePageList(@Valid @RequestBody ConfigSpecInWarehouseQueryParam configSpecInWarehouseQueryParam) throws Exception {
        Paging<ConfigSpecInWarehouseBo> paging = configSpecInWarehouseService.getConfigSpecInWarehousePageList(configSpecInWarehouseQueryParam);
        Paging<ConfigSpecInWarehouseVo> resultPage = new Paging<>();
        resultPage.setTotal(paging.getTotal());
        resultPage.setRecords(BeanListUtil.copyListProperties(paging.getRecords(), ConfigSpecInWarehouseVo.class));
        return ApiResult.ok(resultPage);
    }

}

