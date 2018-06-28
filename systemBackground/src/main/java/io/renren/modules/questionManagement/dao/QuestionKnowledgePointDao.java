package io.renren.modules.questionManagement.dao;

import io.renren.modules.questionManagement.entity.QuestionKnowledgePointEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 知识点
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-26 21:35:46
 */
@Mapper
public interface QuestionKnowledgePointDao extends BaseMapper<QuestionKnowledgePointEntity> {

    List<Map<String,Object>> selectPage(@Param("pageInfo") Map<String,Object> pageInfo);

    int selectTotalCount(@Param("pageInfo") Map<String, Object> pageInfo);

    List<Map<String,Object>> selectAll(int id);
}
