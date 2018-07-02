package io.renren.modules.questionManagement.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.questionManagement.entity.QuestionPaperEntity;

import java.util.List;
import java.util.Map;

/**
 * 试题文件
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-19 10:24:01
 */
public interface QuestionPaperService extends IService<QuestionPaperEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

