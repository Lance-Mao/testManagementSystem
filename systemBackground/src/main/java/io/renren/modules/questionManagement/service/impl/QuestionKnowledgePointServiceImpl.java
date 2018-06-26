package io.renren.modules.questionManagement.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.questionManagement.dao.QuestionKnowledgePointDao;
import io.renren.modules.questionManagement.entity.QuestionKnowledgePointEntity;
import io.renren.modules.questionManagement.service.QuestionKnowledgePointService;


@Service("questionKnowledgePointService")
public class QuestionKnowledgePointServiceImpl extends ServiceImpl<QuestionKnowledgePointDao, QuestionKnowledgePointEntity> implements QuestionKnowledgePointService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QuestionKnowledgePointEntity> page = this.selectPage(
                new Query<QuestionKnowledgePointEntity>(params).getPage(),
                new EntityWrapper<QuestionKnowledgePointEntity>()
        );

        return new PageUtils(page);
    }

}
