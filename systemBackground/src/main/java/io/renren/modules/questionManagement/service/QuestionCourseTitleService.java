package io.renren.modules.questionManagement.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.questionManagement.entity.QuestionCourseTitleEntity;

import java.util.List;
import java.util.Map;

/**
 * 课程名称
 *
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-26 21:35:46
 */
public interface QuestionCourseTitleService extends IService<QuestionCourseTitleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<Map<String,Object>> selectAll(Map<String, Object> params);
}

