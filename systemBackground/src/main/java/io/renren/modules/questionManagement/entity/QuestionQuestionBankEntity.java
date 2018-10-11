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
	 * 选项
	 */
	private String options;

	/**
	 * 正确答案解析
	 */
	private String correctAnswerAnalysis;

	/**
	 * 错误答案解析
	 */
	private String wrongAnswerAnalysis;

	/**
	 * 难易度
	 */
	private Integer degreeOfDifficulty;

	/**
	 * 题目相关信息
	 */
	private String titleRelatedInformation;

	/**
	 * 正确答案
	 */
	private String correctAnswer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCourseTitleId() {
		return courseTitleId;
	}

	public void setCourseTitleId(Integer courseTitleId) {
		this.courseTitleId = courseTitleId;
	}

	public Long getKnowledgeTitleId() {
		return knowledgeTitleId;
	}

	public void setKnowledgeTitleId(Long knowledgeTitleId) {
		this.knowledgeTitleId = knowledgeTitleId;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public Long getEnterer() {
		return enterer;
	}

	public void setEnterer(Long enterer) {
		this.enterer = enterer;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTitleDescription() {
		return titleDescription;
	}

	public void setTitleDescription(String titleDescription) {
		this.titleDescription = titleDescription;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getCorrectAnswerAnalysis() {
		return correctAnswerAnalysis;
	}

	public void setCorrectAnswerAnalysis(String correctAnswerAnalysis) {
		this.correctAnswerAnalysis = correctAnswerAnalysis;
	}

	public String getWrongAnswerAnalysis() {
		return wrongAnswerAnalysis;
	}

	public void setWrongAnswerAnalysis(String wrongAnswerAnalysis) {
		this.wrongAnswerAnalysis = wrongAnswerAnalysis;
	}

	public Integer getDegreeOfDifficulty() {
		return degreeOfDifficulty;
	}

	public void setDegreeOfDifficulty(Integer degreeOfDifficulty) {
		this.degreeOfDifficulty = degreeOfDifficulty;
	}

	public String getTitleRelatedInformation() {
		return titleRelatedInformation;
	}

	public void setTitleRelatedInformation(String titleRelatedInformation) {
		this.titleRelatedInformation = titleRelatedInformation;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
}
