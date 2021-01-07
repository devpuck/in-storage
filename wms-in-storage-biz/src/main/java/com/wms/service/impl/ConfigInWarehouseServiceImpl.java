package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wms.api.config.ConfigInWarehouseVo;
import com.xac.core.constant.CoreConstant;
import com.wms.model.entity.ConfigInWarehouseEntity;
import com.wms.mapper.config.ConfigInWarehouseMapper;
import com.wms.service.ConfigInWarehouseService;
import com.wms.api.config.ConfigInWarehouseQueryParam;
import com.wms.model.bo.config.ConfigInWarehouseBo;
import com.xac.core.service.impl.BaseServiceImpl;
import com.xac.core.api.Paging;
import com.xac.core.util.BeanListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 * 入库配置 服务实现类
 * </pre>
 *
 * @author puck
 * @since 2020-12-22
 */
@Slf4j
@Service
public class ConfigInWarehouseServiceImpl extends BaseServiceImpl<ConfigInWarehouseMapper, ConfigInWarehouseEntity> implements ConfigInWarehouseService {

    @Autowired
    private ConfigInWarehouseMapper configInWarehouseMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveConfigInWarehouse(ConfigInWarehouseBo configInWarehouse) {
        ConfigInWarehouseEntity entity = new ConfigInWarehouseEntity();
        BeanUtils.copyProperties(configInWarehouse , entity);
        return super.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateConfigInWarehouse(ConfigInWarehouseBo configInWarehouse) {
        ConfigInWarehouseEntity entity = new ConfigInWarehouseEntity();
        BeanUtils.copyProperties(configInWarehouse , entity);
        return super.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteConfigInWarehouse(Long id) {
        return super.removeById(id);
    }

    @Override
    public ConfigInWarehouseBo getConfigInWarehouseById(Serializable id) {
        return configInWarehouseMapper.getConfigInWarehouseById(id);
    }

    @Override
    public Paging<ConfigInWarehouseBo> getConfigInWarehousePageList(ConfigInWarehouseQueryParam configInWarehouseQueryParam) {
        Page page = setPageParam(configInWarehouseQueryParam, OrderItem.desc(CoreConstant.CREATED_DATE));
        IPage<ConfigInWarehouseBo> iPage = configInWarehouseMapper.getConfigInWarehousePageList(page, configInWarehouseQueryParam);
        return new Paging(iPage);
    }

    @Override
    public ConfigInWarehouseBo queryConfigWarehouse(String warehouseCode, String workCode, String dealCode)
    {
        ConfigInWarehouseEntity configInWarehouseEntity = configInWarehouseMapper.selectOne(new QueryWrapper<ConfigInWarehouseEntity>().lambda()
                .eq(ConfigInWarehouseEntity::getWarehouseCode,warehouseCode)
                .eq(ConfigInWarehouseEntity::getWarehouseWorkCode,workCode)
                .eq(ConfigInWarehouseEntity::getWarehouseDealCode,dealCode));
        if(null != configInWarehouseEntity)
        {
            ConfigInWarehouseBo configInWarehouseBo = new ConfigInWarehouseBo();
            BeanUtils.copyProperties(configInWarehouseEntity,configInWarehouseBo);
        }
        return null;
    }

    @Override
    public List<ConfigInWarehouseBo> queryWarehouseConfig(String warehouseCode) throws Exception
    {
        List<ConfigInWarehouseEntity> entityList = configInWarehouseMapper.selectList(new QueryWrapper<ConfigInWarehouseEntity>().lambda()
                .eq(ConfigInWarehouseEntity::getWarehouseCode,warehouseCode));
        if(null != entityList)
        {
            entityList = new ArrayList<ConfigInWarehouseEntity>();
        }
        List<ConfigInWarehouseBo> configInWarehouseBoList = BeanListUtil.copyListProperties(entityList,ConfigInWarehouseBo.class);
        return configInWarehouseBoList;
    }

}
