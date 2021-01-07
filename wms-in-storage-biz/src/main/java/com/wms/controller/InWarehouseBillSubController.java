package com.wms.controller;

import com.wms.api.instorage.InWarehouseBillVo;
import com.wms.api.instorage.query.QueryInStorageSubAttribute;
import com.wms.api.instorage.query.QueryInStorageSubAttributeByState;
import com.xac.core.util.BeanListUtil;
import com.wms.model.entity.InWarehouseBillSubEntity;
import com.wms.service.InWarehouseBillSubService;
import com.wms.api.instorage.InWarehouseBillSubQueryParam;
import com.wms.api.instorage.InWarehouseBillSubVo;
import com.wms.model.bo.instorage.InWarehouseBillSubBo;
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
 * 入库单子表 前端控制器
 * </pre>
 *
 * @author puck
 * @since 2020-12-24
 */
@Slf4j
@RestController
@RequestMapping("/instoragesub")
@Api("入库单子表 API")
public class InWarehouseBillSubController extends BaseController {

    @Autowired
    private InWarehouseBillSubService inWarehouseBillSubService;

//    /**
//     * 添加入库单子表
//     */
//    @PostMapping("/add")
//    @ApiOperation(value = "添加InWarehouseBillSub对象", notes = "添加入库单子表", response = ApiResult.class)
//    public ApiResult<Boolean> addInWarehouseBillSub(@Valid @RequestBody InWarehouseBillSubVo inWarehouseBillSub) throws Exception {
//         InWarehouseBillSubBo bo = new InWarehouseBillSubBo();
//        BeanUtils.copyProperties(inWarehouseBillSub,bo);
//
//        boolean flag = inWarehouseBillSubService.saveInWarehouseBillSub(bo);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 修改入库单子表
//     */
//    @PostMapping("/update")
//    @ApiOperation(value = "修改InWarehouseBillSub对象", notes = "修改入库单子表", response = ApiResult.class)
//    public ApiResult<Boolean> updateInWarehouseBillSub(@Valid @RequestBody InWarehouseBillSubVo inWarehouseBillSub) throws Exception {
//        InWarehouseBillSubBo bo = new InWarehouseBillSubBo();
//        BeanUtils.copyProperties(inWarehouseBillSub,bo);
//
//        boolean flag = inWarehouseBillSubService.updateInWarehouseBillSub(bo);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 删除入库单子表
//     */
//    @PostMapping("/delete/{id}")
//    @ApiOperation(value = "删除InWarehouseBillSub对象", notes = "删除入库单子表", response = ApiResult.class)
//    public ApiResult<Boolean> deleteInWarehouseBillSub(@PathVariable("id") Long id) throws Exception {
//        boolean flag = inWarehouseBillSubService.deleteInWarehouseBillSub(id);
//        return ApiResult.result(flag);
//    }
//
//    /**
//     * 获取入库单子表
//     */
//    @GetMapping("/info/{id}")
//    @ApiOperation(value = "获取InWarehouseBillSub对象详情", notes = "查看入库单子表", response = InWarehouseBillSubVo.class)
//    public ApiResult<InWarehouseBillSubVo> getInWarehouseBillSub(@PathVariable("id") Long id) throws Exception {
//        InWarehouseBillSubBo inWarehouseBillSubBo = inWarehouseBillSubService.getInWarehouseBillSubById(id);
//        InWarehouseBillSubVo queryVo = null;
//        if (inWarehouseBillSubBo != null) {
//            queryVo = new InWarehouseBillSubVo();
//            BeanUtils.copyProperties(inWarehouseBillSubBo , queryVo);
//        }
//        return ApiResult.ok(queryVo);
//    }
//
//    /**
//     * 入库单子表分页列表
//     */
//    @PostMapping("/pagelist")
//    @ApiOperation(value = "获取InWarehouseBillSub分页列表", notes = "入库单子表分页列表", response = InWarehouseBillSubVo.class)
//    public ApiResult<Paging<InWarehouseBillSubVo>> getInWarehouseBillSubPageList(@Valid @RequestBody InWarehouseBillSubQueryParam inWarehouseBillSubQueryParam) throws Exception {
//        Paging<InWarehouseBillSubBo> paging = inWarehouseBillSubService.getInWarehouseBillSubPageList(inWarehouseBillSubQueryParam);
//        Paging<InWarehouseBillSubVo> resultPage = new Paging<>();
//        resultPage.setTotal(paging.getTotal());
//        resultPage.setRecords(BeanListUtil.copyListProperties(paging.getRecords(), InWarehouseBillSubVo.class));
//        return ApiResult.ok(resultPage);
//    }
    /**
     * 根据条件查询入库子单
     */
    @PostMapping("/query/sub/queryUnfinishedByCondition")
    @ApiOperation(value = "根据条件查询入库子单", notes = "根据条件查询入库子单", response = InWarehouseBillVo.class)
    public ApiResult<List<InWarehouseBillSubVo>> queryPreInWarehouseBillSubByCondition(@Valid @RequestBody QueryInStorageSubAttribute queryInStorageSubAttribute) throws Exception
    {
        List<InWarehouseBillSubBo> inWarehouseBillBoList = inWarehouseBillSubService.queryUnFinishedInWarehouseBillSubByCondition(queryInStorageSubAttribute);
        List<InWarehouseBillSubVo> inWarehouseBillVoList = BeanListUtil.copyListProperties(inWarehouseBillBoList,InWarehouseBillSubVo.class);
        return ApiResult.ok(inWarehouseBillVoList);
    }

    /**
     * 根据条件查询入库子单
     */
    @PostMapping("/query/sub/queryByCondition")
    @ApiOperation(value = "根据条件查询入库子单", notes = "根据条件查询入库子单", response = InWarehouseBillVo.class)
    public ApiResult<List<InWarehouseBillSubVo>> queryInWarehouseBillSubByCondition(@Valid @RequestBody QueryInStorageSubAttributeByState queryInStorageSubAttribute) throws Exception
    {
        List<InWarehouseBillSubBo> inWarehouseBillBoList = inWarehouseBillSubService.queryInWarehouseBillSubByCondition(queryInStorageSubAttribute);
        List<InWarehouseBillSubVo> inWarehouseBillVoList = BeanListUtil.copyListProperties(inWarehouseBillBoList,InWarehouseBillSubVo.class);
        return ApiResult.ok(inWarehouseBillVoList);
    }
}

