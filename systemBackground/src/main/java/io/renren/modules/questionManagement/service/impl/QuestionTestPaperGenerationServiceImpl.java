package io.renren.modules.questionManagement.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.questionManagement.dao.QuestionTestPaperGenerationDao;
import io.renren.modules.questionManagement.entity.QuestionTestPaperGenerationEntity;
import io.renren.modules.questionManagement.service.QuestionTestPaperGenerationService;


@Service("questionTestPaperGenerationService")
public class QuestionTestPaperGenerationServiceImpl extends ServiceImpl<QuestionTestPaperGenerationDao, QuestionTestPaperGenerationEntity> implements QuestionTestPaperGenerationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QuestionTestPaperGenerationEntity> page = this.selectPage(
                new Query<QuestionTestPaperGenerationEntity>(params).getPage(),
                new EntityWrapper<QuestionTestPaperGenerationEntity>()
        );

        return new PageUtils(page);
    }

}
