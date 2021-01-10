package com.wms.service;

import com.wms.api.account.AccountVo;
import com.wms.api.account.InWarehouseAccountVo;

/**
 * @author puck
 * @date 2020/12/25 4:25 下午
 */
public interface InAccountService
{
    public boolean createAccount(InWarehouseAccountVo inWarehouseAccountVo) throws Exception;
}
