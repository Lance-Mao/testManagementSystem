package io.renren.modules.questionManagement.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.questionManagement.dao.QuestionQuestionTypeDao;
import io.renren.modules.questionManagement.entity.QuestionQuestionTypeEntity;
import io.renren.modules.questionManagement.service.QuestionQuestionTypeService;


@Service("questionQuestionTypeService")
public class QuestionQuestionTypeServiceImpl extends ServiceImpl<QuestionQuestionTypeDao, QuestionQuestionTypeEntity> implements QuestionQuestionTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QuestionQuestionTypeEntity> page = this.selectPage(
                new Query<QuestionQuestionTypeEntity>(params).getPage(),
                new EntityWrapper<QuestionQuestionTypeEntity>()
        );

        return new PageUtils(page);
    }

}
