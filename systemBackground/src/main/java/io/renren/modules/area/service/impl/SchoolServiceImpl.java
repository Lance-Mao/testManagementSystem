package io.renren.modules.area.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.area.dao.SchoolDao;
import io.renren.modules.area.entity.SchoolEntity;
import io.renren.modules.area.service.SchoolService;


@Service("schoolService")
public class SchoolServiceImpl extends ServiceImpl<SchoolDao, SchoolEntity> implements SchoolService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SchoolEntity> page = this.selectPage(
                new Query<SchoolEntity>(params).getPage(),
                new EntityWrapper<SchoolEntity>()
        );

        return new PageUtils(page);
    }

}
