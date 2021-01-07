package com.wms.thread;

import com.alibaba.fastjson.JSONObject;
import com.wms.api.account.AccountResult;
import com.wms.api.account.InWarehouseAccountVo;
import com.wms.api.config.ConfigAfterDealVo;
import com.wms.api.requirements.InRequirementVo;
import com.wms.sdk.config.AfterDealManager;
import com.wms.sdk.requirements.InRequirementManager;
import com.xac.core.api.ApiResult;
import com.xac.core.util.ApiResultUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * @author puck
 * @date 2021/1/6 10:32 上午
 */
public class AfterAccountThread implements Runnable
{
    private AccountResult accountResult;
    private InWarehouseAccountVo inWarehouseAccountVo;

    public AfterAccountThread(AccountResult accountResult,InWarehouseAccountVo inWarehouseAccountVo)
    {
        this.accountResult = accountResult;
        this.inWarehouseAccountVo = inWarehouseAccountVo;
    }

    @Override
    public void run()
    {
        String requirementID = inWarehouseAccountVo.getInWarehouseBillVo().getRequirementId();
        String requirementSubID = inWarehouseAccountVo.getInWarehouseBillSubVo().getRequirementSubId();

        accountResult.setRequirementID(requirementID);
        accountResult.setRequirementSubID(requirementSubID);

//        InRequirementManager inRequirementManager = new InRequirementManager();
//        InRequirementVo inRequirementVo = inRequirementManager.queryRequirementById(requirementID);
//
//        System.out.println("IIIIIiiiiiiiiiiiiii");
//
//
//        String testURL = "http://localhost:8080/XacSdkTest/test";
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<AccountResult> requestEntity = new HttpEntity<AccountResult>(accountResult, requestHeaders);
//
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(testURL, requestEntity, String.class);

        AfterDealManager afterDealManager = new AfterDealManager();
        String systemFrom = inWarehouseAccountVo.getInWarehouseBillVo().getSystemFrom();
        String workCode = inWarehouseAccountVo.getInWarehouseBillVo().getWorkCode();
        String dealCode = inWarehouseAccountVo.getInWarehouseBillVo().getDealCode();
        List<ConfigAfterDealVo> configAfterDealVoList = afterDealManager.queryConfigAfterDealVoList(systemFrom,workCode,dealCode);

        if(null != configAfterDealVoList)
        {
            Iterator<ConfigAfterDealVo> it = configAfterDealVoList.iterator();
            while(it.hasNext())
            {
                ConfigAfterDealVo configAfterDealVo = it.next();

                RestTemplate restTemplate = new RestTemplate();

                HttpHeaders requestHeaders = new HttpHeaders();
                requestHeaders.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<AccountResult> requestEntity = new HttpEntity<AccountResult>(accountResult, requestHeaders);

                ResponseEntity<String> responseEntity = restTemplate.postForEntity(configAfterDealVo.getRebackUrl(), requestEntity, String.class);
//                ApiResult result = ApiResultUtil.jsonToBo(responseEntity.getBody(),ApiResult.class);
                JSONObject json = JSONObject.parseObject(responseEntity.getBody());
                int code = Integer.parseInt(json.get("code").toString());
            }

        }



    }
}
