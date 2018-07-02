package io.renren.modules.questionManagement.dao;

import io.renren.modules.questionManagement.entity.QuestionPaperEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 试题文件
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-19 10:24:01
 */
@Mapper
public interface QuestionPaperDao extends BaseMapper<QuestionPaperEntity> {

    List<Map<String,Object>> selectPage(@Param("pageInfo") Map<String,Object> pageInfo);

    int selectTotalCount(@Param("pageInfo") Map<String, Object> pageInfo);

    void insertQuestionBank(@Param("bankData") List<Map<String,Object>> bankData);
}
