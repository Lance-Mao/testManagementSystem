package io.renren.modules.questionManagement.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 知识点
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-26 21:35:46
 */
@TableName("question_knowledge_point")
public class QuestionKnowledgePointEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 知识点id
	 */
	@TableId
	private Long id;
	/**
	 * 知识点名称
	 */
	private String knowledgePoint;
	/**
	 * 课程名称id
	 */
	private Integer courseTitleId;

	/**
	 * 创建者
	 */
	private Long creator;

	/**
	 * 设置：知识点id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：知识点id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：知识点名称
	 */
	public void setKnowledgePoint(String knowledgePoint) {
		this.knowledgePoint = knowledgePoint;
	}
	/**
	 * 获取：知识点名称
	 */
	public String getKnowledgePoint() {
		return knowledgePoint;
	}
	/**
	 * 设置：课程名称id
	 */
	public void setCourseTitleId(Integer courseTitleId) {
		this.courseTitleId = courseTitleId;
	}
	/**
	 * 获取：课程名称id
	 */
	public Integer getCourseTitleId() {
		return courseTitleId;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}
}
