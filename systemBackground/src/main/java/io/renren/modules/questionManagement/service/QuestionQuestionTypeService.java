package io.renren.modules.questionManagement.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.questionManagement.entity.QuestionQuestionTypeEntity;

import java.util.Map;

/**
 * 题型
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-26 21:35:46
 */
public interface QuestionQuestionTypeService extends IService<QuestionQuestionTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

