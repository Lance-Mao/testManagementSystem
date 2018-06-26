package io.renren.modules.area.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private RegionProvinceDao regionProvinceDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<RegionProvinceEntity> page = this.selectPage(
                new Query<RegionProvinceEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectAll() {
        List<Map<String,Object>> datas = regionProvinceDao.selectAll();
        for (Map<String,Object> data : datas) {
            data.put("children", new ArrayList<>());
        }
        return datas;
    }

}
