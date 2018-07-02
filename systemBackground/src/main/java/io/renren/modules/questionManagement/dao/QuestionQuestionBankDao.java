package io.renren.modules.questionManagement.dao;

import io.renren.modules.questionManagement.entity.QuestionQuestionBankEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 题库
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-27 14:35:40
 */
@Mapper
public interface QuestionQuestionBankDao extends BaseMapper<QuestionQuestionBankEntity> {

    List<Map<String,Object>> selectPage(@Param("pageInfo") Map<String,Object> pageInfo);

    int selectTotalCount(@Param("pageInfo") Map<String,Object> pageInfo);
}
