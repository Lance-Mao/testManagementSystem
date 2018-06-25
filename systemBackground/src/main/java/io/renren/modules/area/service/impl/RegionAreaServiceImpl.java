package io.renren.modules.area.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.area.dao.RegionAreaDao;
import io.renren.modules.area.entity.RegionAreaEntity;
import io.renren.modules.area.service.RegionAreaService;


@Service("regionAreaService")
public class RegionAreaServiceImpl extends ServiceImpl<RegionAreaDao, RegionAreaEntity> implements RegionAreaService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RegionAreaEntity> page = this.selectPage(
                new Query<RegionAreaEntity>(params).getPage(),
                new EntityWrapper<RegionAreaEntity>()
        );

        return new PageUtils(page);
    }

}
