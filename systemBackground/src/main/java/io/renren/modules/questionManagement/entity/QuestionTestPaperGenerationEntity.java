package io.renren.modules.questionManagement.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 试卷生成
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-07-01 16:38:29
 */
@TableName("question_test_paper_generation")
public class QuestionTestPaperGenerationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 课程名称
	 */
	@TableId
	private Integer courseTitle;
	/**
	 * 试卷名称
	 */
	private String testName;
	/**
	 * 出题人
	 */
	private Long examiner;
	/**
	 * 出题时间
	 */
	private Date timeOfQuestion;
	/**
	 * 试卷内容
	 */
	private String testContent;

	/**
	 * 设置：课程名称
	 */
	public void setCourseTitle(Integer courseTitle) {
		this.courseTitle = courseTitle;
	}
	/**
	 * 获取：课程名称
	 */
	public Integer getCourseTitle() {
		return courseTitle;
	}
	/**
	 * 设置：试卷名称
	 */
	public void setTestName(String testName) {
		this.testName = testName;
	}
	/**
	 * 获取：试卷名称
	 */
	public String getTestName() {
		return testName;
	}
	/**
	 * 设置：出题人
	 */
	public void setExaminer(Long examiner) {
		this.examiner = examiner;
	}
	/**
	 * 获取：出题人
	 */
	public Long getExaminer() {
		return examiner;
	}
	/**
	 * 设置：出题时间
	 */
	public void setTimeOfQuestion(Date timeOfQuestion) {
		this.timeOfQuestion = timeOfQuestion;
	}

	/**
	 * 获取：出题时间
	 */
	public Date getTimeOfQuestion() {
		return timeOfQuestion;
	}

	/**
	 * 设置：试卷内容
	 */
	public void setTestContent(String testContent) {
		this.testContent = testContent;
	}
	/**
	 * 获取：试卷内容
	 */
	public String getTestContent() {
		return testContent;
	}
}
