package io.renren.modules.questionManagement.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.questionManagement.entity.QuestionQuestionBankEntity;

import java.util.Map;

/**
 * 题库
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-27 14:35:40
 */
public interface QuestionQuestionBankService extends IService<QuestionQuestionBankEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

