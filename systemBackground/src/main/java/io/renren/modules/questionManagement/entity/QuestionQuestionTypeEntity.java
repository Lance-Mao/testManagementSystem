package io.renren.modules.questionManagement.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 题型
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-26 21:35:46
 */
@TableName("question_question_type")
public class QuestionQuestionTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 题型id
	 */
	@TableId
	private Integer id;
	/**
	 * 题型名称
	 */
	private String questionType;
	/**
	 * 知识点id
	 */
	private Long knowledgePointId;

	/**
	 * 设置：题型id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：题型id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：题型名称
	 */
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	/**
	 * 获取：题型名称
	 */
	public String getQuestionType() {
		return questionType;
	}
	/**
	 * 设置：知识点id
	 */
	public void setKnowledgePointId(Long knowledgePointId) {
		this.knowledgePointId = knowledgePointId;
	}
	/**
	 * 获取：知识点id
	 */
	public Long getKnowledgePointId() {
		return knowledgePointId;
	}
}
