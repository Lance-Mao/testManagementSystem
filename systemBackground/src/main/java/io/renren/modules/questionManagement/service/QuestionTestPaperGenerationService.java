package io.renren.modules.questionManagement.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.questionManagement.entity.QuestionTestPaperGenerationEntity;

import java.util.Map;

/**
 * 试卷生成
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-07-01 16:38:29
 */
public interface QuestionTestPaperGenerationService extends IService<QuestionTestPaperGenerationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

