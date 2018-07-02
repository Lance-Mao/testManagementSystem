package io.renren.modules.questionManagement.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.questionManagement.dao.QuestionChapterDao;
import io.renren.modules.questionManagement.entity.QuestionChapterEntity;
import io.renren.modules.questionManagement.service.QuestionChapterService;


@Service("questionChapterService")
public class QuestionChapterServiceImpl extends ServiceImpl<QuestionChapterDao, QuestionChapterEntity> implements QuestionChapterService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QuestionChapterEntity> page = this.selectPage(
                new Query<QuestionChapterEntity>(params).getPage(),
                new EntityWrapper<QuestionChapterEntity>()
        );

        return new PageUtils(page);
    }

}
