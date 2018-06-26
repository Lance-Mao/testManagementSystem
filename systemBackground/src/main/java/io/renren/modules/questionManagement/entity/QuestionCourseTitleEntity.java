package io.renren.modules.questionManagement.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程名称
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-26 21:35:46
 */
@TableName("question_course_title")
public class QuestionCourseTitleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 课程名称id
	 */
	@TableId
	private Integer id;
	/**
	 * 课程名称
	 */
	private String courseTitle;

	/**
	 * 设置：课程名称id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：课程名称id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：课程名称
	 */
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	/**
	 * 获取：课程名称
	 */
	public String getCourseTitle() {
		return courseTitle;
	}
}
