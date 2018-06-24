package io.renren.modules.questionManagement.dao;

import io.renren.modules.questionManagement.entity.QuestionPaperEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 试题文件
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-19 10:24:01
 */
@Mapper
public interface QuestionPaperDao extends BaseMapper<QuestionPaperEntity> {
	
}
