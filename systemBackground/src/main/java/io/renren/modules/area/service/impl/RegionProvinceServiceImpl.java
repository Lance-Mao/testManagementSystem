package io.renren.modules.area.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.area.dao.RegionProvinceDao;
import io.renren.modules.area.entity.RegionProvinceEntity;
import io.renren.modules.area.service.RegionProvinceService;


@Service("regionProvinceService")
public class RegionProvinceServiceImpl extends ServiceImpl<RegionProvinceDao, RegionProvinceEntity> implements RegionProvinceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RegionProvinceEntity> page = this.selectPage(
                new Query<RegionProvinceEntity>(params).getPage(),
                new EntityWrapper<RegionProvinceEntity>()
        );

        return new PageUtils(page);
    }

}
