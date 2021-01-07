package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xac.core.constant.CoreConstant;
import com.wms.model.entity.ConfigSpecInWarehouseEntity;
import com.wms.mapper.config.ConfigSpecInWarehouseMapper;
import com.wms.service.ConfigSpecInWarehouseService;
import com.wms.api.config.ConfigSpecInWarehouseQueryParam;
import com.wms.model.bo.config.ConfigSpecInWarehouseBo;
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
 * @since 2020-12-28
 */
@Slf4j
@Service
public class ConfigSpecInWarehouseServiceImpl extends BaseServiceImpl<ConfigSpecInWarehouseMapper, ConfigSpecInWarehouseEntity> implements ConfigSpecInWarehouseService {

    @Autowired
    private ConfigSpecInWarehouseMapper configSpecInWarehouseMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveConfigSpecInWarehouse(ConfigSpecInWarehouseBo configSpecInWarehouse)
    {
        ConfigSpecInWarehouseEntity entity = new ConfigSpecInWarehouseEntity();
        BeanUtils.copyProperties(configSpecInWarehouse , entity);
        return super.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateConfigSpecInWarehouse(ConfigSpecInWarehouseBo configSpecInWarehouse)
    {
        ConfigSpecInWarehouseEntity entity = new ConfigSpecInWarehouseEntity();
        BeanUtils.copyProperties(configSpecInWarehouse , entity);
        return super.updateById(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteConfigSpecInWarehouse(Long id)
    {
        return super.removeById(id);
    }

    @Override
    public ConfigSpecInWarehouseBo getConfigSpecInWarehouseById(Serializable id)
    {
        return configSpecInWarehouseMapper.getConfigSpecInWarehouseById(id);
    }

    @Override
    public Paging<ConfigSpecInWarehouseBo> getConfigSpecInWarehousePageList(ConfigSpecInWarehouseQueryParam configSpecInWarehouseQueryParam)
    {
        Page page = setPageParam(configSpecInWarehouseQueryParam, OrderItem.desc(CoreConstant.CREATED_DATE));
        IPage<ConfigSpecInWarehouseBo> iPage = configSpecInWarehouseMapper.getConfigSpecInWarehousePageList(page, configSpecInWarehouseQueryParam);
        return new Paging(iPage);
    }

    @Override
    public ConfigSpecInWarehouseBo queryConfigSpecInWarehouse(String warehouseCode, String workCode, String dealCode, String productionCode)
    {
        ConfigSpecInWarehouseEntity configSpecInWarehouseEntity = configSpecInWarehouseMapper.selectOne(new QueryWrapper<ConfigSpecInWarehouseEntity>().lambda()
                .eq(ConfigSpecInWarehouseEntity::getWarehouseCode,warehouseCode)
                .eq(ConfigSpecInWarehouseEntity::getWarehouseWorkCode,workCode)
                .eq(ConfigSpecInWarehouseEntity::getWarehouseDealCode,dealCode)
                .eq(ConfigSpecInWarehouseEntity::getProductionCode,productionCode));
        if(null != configSpecInWarehouseEntity)
        {
            ConfigSpecInWarehouseBo configSpecInWarehouseBo = new ConfigSpecInWarehouseBo();
            BeanUtils.copyProperties(configSpecInWarehouseEntity,configSpecInWarehouseBo);
            return configSpecInWarehouseBo;
        }
        return null;
    }

    @Override
    public List<ConfigSpecInWarehouseBo> queryWarehouseSpecConfig(String warehouseCode) throws Exception
    {
        List<ConfigSpecInWarehouseEntity> configSpecInWarehouseEntityList = configSpecInWarehouseMapper.selectList(new QueryWrapper<ConfigSpecInWarehouseEntity>().lambda()
                .eq(ConfigSpecInWarehouseEntity::getWarehouseCode,warehouseCode));
        if(null == configSpecInWarehouseEntityList)
        {
            configSpecInWarehouseEntityList = new ArrayList<ConfigSpecInWarehouseEntity>();
        }

        return BeanListUtil.copyListProperties(configSpecInWarehouseEntityList,ConfigSpecInWarehouseBo.class);
    }

}
