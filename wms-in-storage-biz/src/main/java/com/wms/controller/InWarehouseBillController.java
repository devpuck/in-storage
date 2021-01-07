package com.wms.controller;

import com.wms.api.instorage.InWarehouseBillSubVo;
import com.wms.api.instorage.query.QueryInStorageAttribute;
import com.wms.api.instorage.query.QueryInStorageAttributeByState;
import com.wms.api.instorage.query.QueryInStorageSubAttribute;
import com.wms.api.instorage.query.QueryInStorageSubAttributeByState;
import com.wms.errorcode.ErrorCode;
import com.wms.model.bo.instorage.InWarehouseBillSubBo;
import com.wms.service.InWarehouseBillSubService;
import com.wms.util.CheckParameter;
import com.xac.core.api.ApiResultCode;
import com.xac.core.util.BeanListUtil;
import com.wms.service.InWarehouseBillService;
import com.wms.api.instorage.InWarehouseBillQueryParam;
import com.wms.api.instorage.InWarehouseBillVo;
import com.wms.model.bo.instorage.InWarehouseBillBo;
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
 * 入库单据 前端控制器
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Slf4j
@RestController
@RequestMapping("/instorage")
@Api("入库单据 API")
public class InWarehouseBillController extends BaseController
{

    @Autowired
    private InWarehouseBillService inWarehouseBillService;

    @Autowired
    private InWarehouseBillSubService inWarehouseBillSubService;

    /**
     * 添加入库单据
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加InWarehouseBill对象", notes = "添加入库单据", response = ApiResult.class)
    public ApiResult<Boolean> addInWarehouseBill(@Valid @RequestBody InWarehouseBillVo inWarehouseBill) throws Exception
    {
        String requirementID = inWarehouseBill.getRequirementId();
        if(null != requirementID && (!"".equals(requirementID)))
        {
            String queryBillID = inWarehouseBillService.queryInWarehouseBillIDByRequirementID(requirementID);
            if(null != queryBillID)
            {
                ApiResult apiResult = new ApiResult();
                apiResult.setCode(ErrorCode.WAREHOUSE_REQUIREMENT_CODE_REPEAT);
                apiResult.setMsg(ErrorCode.WAREHOUSE_REQUIREMENT_CODE_REPEAT_MESSAGE);
                apiResult.setSuccess(false);
                return apiResult;
            }
        }

        InWarehouseBillBo inWarehouseBillBo = new InWarehouseBillBo();
        BeanUtils.copyProperties(inWarehouseBill,inWarehouseBillBo);

        List<InWarehouseBillSubVo> inWarehouseBillSubVoList = inWarehouseBill.getInWarehouseBillSubVoList();
        if(null != inWarehouseBillSubVoList)
        {
            List<InWarehouseBillSubBo> inWarehouseBillSubBoList = BeanListUtil.copyListProperties(inWarehouseBillSubVoList,InWarehouseBillSubBo.class);
            inWarehouseBillBo.setInWarehouseBillSubBoList(inWarehouseBillSubBoList);
        }

        boolean flag = inWarehouseBillService.saveInWarehouseBill(inWarehouseBillBo);
        return ApiResult.result(flag);
    }

    /**
     * 修改入库单据
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改InWarehouseBill对象", notes = "修改入库单据", response = ApiResult.class)
    public ApiResult<Boolean> updateInWarehouseBill(@Valid @RequestBody InWarehouseBillVo inWarehouseBill) throws Exception {
        InWarehouseBillBo bo = new InWarehouseBillBo();
        BeanUtils.copyProperties(inWarehouseBill,bo);

        boolean flag = inWarehouseBillService.updateInWarehouseBill(bo);
        return ApiResult.result(flag);
    }

    /**
     * 删除入库单据
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除InWarehouseBill对象", notes = "删除入库单据", response = ApiResult.class)
    public ApiResult<Boolean> deleteInWarehouseBill(@PathVariable("id") Long id) throws Exception {
        boolean flag = inWarehouseBillService.deleteInWarehouseBill(id);
        return ApiResult.result(flag);
    }

    /**
     * 获取入库单据
     */
    @GetMapping("/info/{id}")
    @ApiOperation(value = "获取InWarehouseBill对象详情", notes = "查看入库单据", response = InWarehouseBillVo.class)
    public ApiResult<InWarehouseBillVo> getInWarehouseBill(@PathVariable("id") Long id) throws Exception {
        InWarehouseBillBo inWarehouseBillBo = inWarehouseBillService.getInWarehouseBillById(id);
        InWarehouseBillVo queryVo = null;
        if (inWarehouseBillBo != null)
        {
            queryVo = new InWarehouseBillVo();
            BeanUtils.copyProperties(inWarehouseBillBo , queryVo);

            List<InWarehouseBillSubBo> inWarehouseBillSubBoList = inWarehouseBillBo.getInWarehouseBillSubBoList();
            if(null != inWarehouseBillSubBoList)
            {
                queryVo.setInWarehouseBillSubVoList(BeanListUtil.copyListProperties(inWarehouseBillSubBoList,InWarehouseBillSubVo.class));
            }
        }
        return ApiResult.ok(queryVo);
    }

    /**
     * 获取入库单据
     */
    @PostMapping("/query/queryByBillCode")
    @ApiOperation(value = "根据入库单据号，查询入库订单", notes = "根据入库单据号，查询入库订单", response = InWarehouseBillVo.class)
    public ApiResult<InWarehouseBillVo> queryWarehouseBillByBillCode(@RequestParam String billCode) throws Exception
    {
        InWarehouseBillBo inWarehouseBillBo = inWarehouseBillService.queryInWarehouseBillByBillCode(billCode);
        InWarehouseBillVo queryVo = null;
        if (inWarehouseBillBo != null)
        {
            queryVo = new InWarehouseBillVo();
            BeanUtils.copyProperties(inWarehouseBillBo , queryVo);

            List<InWarehouseBillSubBo> inWarehouseBillSubBoList = inWarehouseBillBo.getInWarehouseBillSubBoList();
            if(null != inWarehouseBillSubBoList)
            {
                queryVo.setInWarehouseBillSubVoList(BeanListUtil.copyListProperties(inWarehouseBillSubBoList,InWarehouseBillSubVo.class));
            }
        }
        return ApiResult.ok(queryVo);
    }

    /**
     * 获取入库单据
     */
    @PostMapping("/query/queryByRequirementID")
    @ApiOperation(value = "根据入库单据号，查询入库订单", notes = "根据入库单据号，查询入库订单", response = InWarehouseBillVo.class)
    public ApiResult<InWarehouseBillVo> queryWarehouseBillByRequirementID(@RequestParam String requirementID) throws Exception
    {
        InWarehouseBillBo inWarehouseBillBo = inWarehouseBillService.queryInWarehouseBillByRequirementID(requirementID);
        InWarehouseBillVo queryVo = null;
        if (inWarehouseBillBo != null) {
            queryVo = new InWarehouseBillVo();
            BeanUtils.copyProperties(inWarehouseBillBo , queryVo);
        }
        return ApiResult.ok(queryVo);
    }

    /**
     * 入库单据分页列表
     */
    @PostMapping("/pagelist")
    @ApiOperation(value = "获取InWarehouseBill分页列表", notes = "入库单据分页列表", response = InWarehouseBillVo.class)
    public ApiResult<Paging<InWarehouseBillVo>> getInWarehouseBillPageList(@Valid @RequestBody InWarehouseBillQueryParam inWarehouseBillQueryParam) throws Exception {
        Paging<InWarehouseBillBo> paging = inWarehouseBillService.getInWarehouseBillPageList(inWarehouseBillQueryParam);
        Paging<InWarehouseBillVo> resultPage = new Paging<>();
        resultPage.setTotal(paging.getTotal());
        resultPage.setRecords(BeanListUtil.copyListProperties(paging.getRecords(), InWarehouseBillVo.class));
        return ApiResult.ok(resultPage);
    }

    /**
     * 查询入库单据
     */
    @PostMapping("/query/queryByProduction")
    @ApiOperation(value = "查询入库单据", notes = "查询入库单据", response = InWarehouseBillVo.class)
    public ApiResult<List<InWarehouseBillVo>> queryInWarehouseBillByProduction(@RequestParam String productionCode, @RequestParam String state)  throws Exception
    {
        List<InWarehouseBillBo> inWarehouseBillBoList = inWarehouseBillService.queryInWarehouseBillByProductionCode(productionCode,state);
        List<InWarehouseBillVo> inWarehouseBillVoList = BeanListUtil.copyListProperties(inWarehouseBillBoList,InWarehouseBillVo.class);
        return ApiResult.ok(inWarehouseBillVoList);
    }

    /**
     * 查询入库单据
     */
    @PostMapping("/query/queryByWarehouseCode")
    @ApiOperation(value = "查询入库单据", notes = "查询入库单据", response = InWarehouseBillVo.class)
    public ApiResult<List<InWarehouseBillVo>> queryInWarehouseBillByWarehouse(@RequestParam String warehouseCode, @RequestParam String state)  throws Exception
    {
        if(ErrorCode.OK!= CheckParameter.checkParameter(warehouseCode))
        {
            return ApiResult.result(ApiResultCode.PARAMETER_EXCEPTION);
        }

        List<InWarehouseBillBo> inWarehouseBillBoList = null;
        List<InWarehouseBillVo> inWarehouseBillVoList = null;
        if(null == state || "".equals(state))
        {
            inWarehouseBillBoList = inWarehouseBillService.queryInWarehouseBill(warehouseCode);
        }else
        {
            inWarehouseBillBoList = inWarehouseBillService.queryInWarehouseBill(warehouseCode,state);
        }

        inWarehouseBillVoList=BeanListUtil.copyListProperties(inWarehouseBillBoList,InWarehouseBillVo.class);
        return ApiResult.ok(inWarehouseBillVoList);
    }

    /**
     * 根据条件查询入库单据,查询未完结的入库单据
     */
    @PostMapping("/query/queryUnfinishedByCondition")
    @ApiOperation(value = "根据条件查询入库单据,查询未完结的入库单据", notes = "根据条件查询入库单据,查询未完结的入库单据", response = InWarehouseBillVo.class)
    public ApiResult<List<InWarehouseBillVo>> queryUnfinishedInBill(@Valid @RequestBody QueryInStorageAttribute queryInStorageAttribute) throws Exception
    {
        List<InWarehouseBillBo> inWarehouseBillBoList = inWarehouseBillService.queryUnFinishedInWarehouseBill(queryInStorageAttribute);
        List<InWarehouseBillVo> inWarehouseBillVoList = BeanListUtil.copyListProperties(inWarehouseBillBoList,InWarehouseBillVo.class);
        return ApiResult.ok(inWarehouseBillVoList);
    }

    /**
     * 根据条件查询入库单据,根据入库单据状态查询
     */
    @PostMapping("/query/queryByCondition")
    @ApiOperation(value = "根据条件查询入库单据,根据入库单据状态查询", notes = "根据条件查询入库单据,根据入库单据状态查询", response = InWarehouseBillVo.class)
    public ApiResult<List<InWarehouseBillVo>> queryUnfinishedInBill(@Valid @RequestBody QueryInStorageAttributeByState queryInStorageAttribute) throws Exception
    {
        List<InWarehouseBillBo> inWarehouseBillBoList = inWarehouseBillService.queryInWarehouseBill(queryInStorageAttribute);
        List<InWarehouseBillVo> inWarehouseBillVoList = BeanListUtil.copyListProperties(inWarehouseBillBoList,InWarehouseBillVo.class);
        return ApiResult.ok(inWarehouseBillVoList);
    }

}

