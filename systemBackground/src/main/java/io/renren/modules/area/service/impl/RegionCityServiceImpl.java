package io.renren.modules.area.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.area.dao.RegionCityDao;
import io.renren.modules.area.entity.RegionCityEntity;
import io.renren.modules.area.service.RegionCityService;


@Service("regionCityService")
public class RegionCityServiceImpl extends ServiceImpl<RegionCityDao, RegionCityEntity> implements RegionCityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RegionCityEntity> page = this.selectPage(
                new Query<RegionCityEntity>(params).getPage(),
                new EntityWrapper<RegionCityEntity>()
        );

        return new PageUtils(page);
    }

}
