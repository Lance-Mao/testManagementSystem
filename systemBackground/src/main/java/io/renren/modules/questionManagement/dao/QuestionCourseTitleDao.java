package io.renren.modules.questionManagement.dao;

import io.renren.modules.questionManagement.entity.QuestionCourseTitleEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 课程名称
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-26 21:35:46
 */
@Mapper
public interface QuestionCourseTitleDao extends BaseMapper<QuestionCourseTitleEntity> {

    List<Map<String,Object>> selectAll();
}
