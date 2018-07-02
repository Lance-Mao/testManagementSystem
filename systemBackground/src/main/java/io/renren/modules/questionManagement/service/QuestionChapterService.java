package io.renren.modules.questionManagement.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.questionManagement.entity.QuestionChapterEntity;

import java.util.List;
import java.util.Map;

/**
 * 章节
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-07-01 21:37:39
 */
public interface QuestionChapterService extends IService<QuestionChapterEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> listByCourseTitle(Map<String,Object> params);
}

