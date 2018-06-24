package io.renren.modules.questionManagement.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.questionManagement.dao.QuestionPaperDao;
import io.renren.modules.questionManagement.entity.QuestionPaperEntity;
import io.renren.modules.questionManagement.service.QuestionPaperService;


@Service("questionPaperService")
public class QuestionPaperServiceImpl extends ServiceImpl<QuestionPaperDao, QuestionPaperEntity> implements QuestionPaperService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QuestionPaperEntity> page = this.selectPage(
                new Query<QuestionPaperEntity>(params).getPage(),
                new EntityWrapper<>()
        );

        return new PageUtils(page);
    }

}
