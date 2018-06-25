package io.renren.modules.area.service.impl;

import org.springframework.stereotype.Service;
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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CollegeEntity> page = this.selectPage(
                new Query<CollegeEntity>(params).getPage(),
                new EntityWrapper<CollegeEntity>()
        );

        return new PageUtils(page);
    }

}
