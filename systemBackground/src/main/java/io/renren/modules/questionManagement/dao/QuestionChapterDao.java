package io.renren.modules.questionManagement.dao;

import io.renren.modules.questionManagement.entity.QuestionChapterEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 章节
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-07-01 21:37:39
 */
@Mapper
public interface QuestionChapterDao extends BaseMapper<QuestionChapterEntity> {

    List<Map<String,Object>> selectAll(int id);
}
