package com.wms.controller;

import com.wms.api.account.InWarehouseAccountVo;
import com.wms.service.AccountService;
import com.xac.core.api.ApiResult;
import com.xac.core.api.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author puck
 * @date 2020/12/25 4:09 下午
 */

@Slf4j
@RestController
@RequestMapping("/in/account")
@Api("入库单据 API")
public class AccountController extends BaseController
{

    @Autowired
    AccountService service;

    /**
     * 添加入库单据
     */
    @PostMapping("/create")
    @ApiOperation(value = "入库过账", notes = "入库过账", response = ApiResult.class)
    public ApiResult<Boolean> account(@Valid @RequestBody InWarehouseAccountVo inWarehouseAccountVo) throws Exception
    {

        boolean result = service.createAccount(inWarehouseAccountVo);

        return ApiResult.result(result);
    }




}
