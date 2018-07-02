package io.renren.modules.questionManagement.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 章节
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-07-01 21:37:39
 */
@TableName("question_chapter")
public class QuestionChapterEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 章节
	 */
	private String chapter;
	/**
	 * 章节标题
	 */
	private String title;
	/**
	 * 课程名称
	 */
	private Integer courseTitle;

	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：章节
	 */
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	/**
	 * 获取：章节
	 */
	public String getChapter() {
		return chapter;
	}
	/**
	 * 设置：章节标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：章节标题
	 */
	public String getTitle() {
		return title;
	}
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
}
