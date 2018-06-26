package io.renren.modules.questionManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.questionManagement.dao.QuestionCourseTitleDao;
import io.renren.modules.questionManagement.entity.QuestionCourseTitleEntity;
import io.renren.modules.questionManagement.service.QuestionCourseTitleService;


@Service("questionCourseTitleService")
public class QuestionCourseTitleServiceImpl extends ServiceImpl<QuestionCourseTitleDao, QuestionCourseTitleEntity> implements QuestionCourseTitleService {

    @Autowired
    private QuestionCourseTitleDao questionCourseTitleDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QuestionCourseTitleEntity> page = this.selectPage(
                new Query<QuestionCourseTitleEntity>(params).getPage(),
                new EntityWrapper<QuestionCourseTitleEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<Map<String, Object>> selectAll() {
        return questionCourseTitleDao.selectAll();
    }

}
