package io.renren.modules.questionManagement.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 题库
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-27 14:35:40
 */
@TableName("question_question_bank")
public class QuestionQuestionBankEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 课程名称
	 */
	private Integer courseTitleId;
	/**
	 * 知识点
	 */
	private Long knowledgeTitleId;
	/**
	 * 题型
	 */
	private String questionType;
	/**
	 * 录入者
	 */
	private Long enterer;
	/**
	 * 录入时间
	 */
	private Date entryTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 题目描述
	 */
	private String titleDescription;
	/**
	 * 答案
	 */
	private String answer;
	/**
	 * 选项A
	 */
	private String answerA;
	/**
	 * 选项B
	 */
	private String answerB;
	/**
	 * 选项C
	 */
	private String answerC;
	/**
	 * 选项D
	 */
	private String answerD;
	/**
	 * 选项E
	 */
	private String answerE;
	/**
	 * 选项F
	 */
	private String answerF;

	/**
	 * 设置：id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：课程名称
	 */
	public void setCourseTitleId(Integer courseTitleId) {
		this.courseTitleId = courseTitleId;
	}
	/**
	 * 获取：课程名称
	 */
	public Integer getCourseTitleId() {
		return courseTitleId;
	}
	/**
	 * 设置：知识点
	 */
	public void setKnowledgeTitleId(Long knowledgeTitleId) {
		this.knowledgeTitleId = knowledgeTitleId;
	}
	/**
	 * 获取：知识点
	 */
	public Long getKnowledgeTitleId() {
		return knowledgeTitleId;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	/**
	 * 设置：录入者
	 */
	public void setEnterer(Long enterer) {
		this.enterer = enterer;
	}
	/**
	 * 获取：录入者
	 */
	public Long getEnterer() {
		return enterer;
	}
	/**
	 * 设置：录入时间
	 */
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	/**
	 * 获取：录入时间
	 */
	public Date getEntryTime() {
		return entryTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：题目描述
	 */
	public void setTitleDescription(String titleDescription) {
		this.titleDescription = titleDescription;
	}
	/**
	 * 获取：题目描述
	 */
	public String getTitleDescription() {
		return titleDescription;
	}
	/**
	 * 设置：答案
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * 获取：答案
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * 设置：选项A
	 */
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	/**
	 * 获取：选项A
	 */
	public String getAnswerA() {
		return answerA;
	}
	/**
	 * 设置：选项B
	 */
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	/**
	 * 获取：选项B
	 */
	public String getAnswerB() {
		return answerB;
	}
	/**
	 * 设置：选项C
	 */
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	/**
	 * 获取：选项C
	 */
	public String getAnswerC() {
		return answerC;
	}
	/**
	 * 设置：选项D
	 */
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	/**
	 * 获取：选项D
	 */
	public String getAnswerD() {
		return answerD;
	}
	/**
	 * 设置：选项E
	 */
	public void setAnswerE(String answerE) {
		this.answerE = answerE;
	}
	/**
	 * 获取：选项E
	 */
	public String getAnswerE() {
		return answerE;
	}
	/**
	 * 设置：选项F
	 */
	public void setAnswerF(String answerF) {
		this.answerF = answerF;
	}
	/**
	 * 获取：选项F
	 */
	public String getAnswerF() {
		return answerF;
	}
}
