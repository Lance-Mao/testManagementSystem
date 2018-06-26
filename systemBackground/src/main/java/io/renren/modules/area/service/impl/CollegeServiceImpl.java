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

import io.renren.modules.area.dao.CollegeDao;
import io.renren.modules.area.entity.CollegeEntity;
import io.renren.modules.area.service.CollegeService;


@Service("collegeService")
public class CollegeServiceImpl extends ServiceImpl<CollegeDao, CollegeEntity> implements CollegeService {

    @Autowired
    private CollegeDao collegeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CollegeEntity> page = this.selectPage(
                new Query<CollegeEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectByProvinceId(Integer params) {
        List<Map<String,Object>> datas = collegeDao.selectByProvinceId(params);
        for (Map<String,Object> data : datas) {
            data.put("children", new ArrayList<>());
        }
        return datas;
    }

}
