package io.renren.modules.questionManagement.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.questionManagement.dao.QuestionQuestionBankDao;
import io.renren.modules.questionManagement.entity.QuestionQuestionBankEntity;
import io.renren.modules.questionManagement.service.QuestionQuestionBankService;


@Service("questionQuestionBankService")
public class QuestionQuestionBankServiceImpl extends ServiceImpl<QuestionQuestionBankDao, QuestionQuestionBankEntity> implements QuestionQuestionBankService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QuestionQuestionBankEntity> page = this.selectPage(
                new Query<QuestionQuestionBankEntity>(params).getPage(),
                new EntityWrapper<QuestionQuestionBankEntity>()
        );

        return new PageUtils(page);
    }

}
