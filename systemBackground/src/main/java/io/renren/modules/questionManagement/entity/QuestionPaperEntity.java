package io.renren.modules.questionManagement.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 试题文件
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-19 10:24:01
 */
@TableName("question_paper")
public class QuestionPaperEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 文档类型
	 */
	private String documentType;
	/**
	 * 上传地址
	 */
	private String uploadAddress;
	/**
	 * 课程名称
	 */
	private int courseTitleId;
	/**
	 * 知识点
	 */
	private Long knowledgePointId;
	/**
	 * 上传者
	 */
	private Long uploadBy;
	/**
	 * 上传时间
	 */
	private Date uploadTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：文档类型
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	/**
	 * 获取：文档类型
	 */
	public String getDocumentType() {
		return documentType;
	}
	/**
	 * 设置：上传地址
	 */
	public void setUploadAddress(String uploadAddress) {
		this.uploadAddress = uploadAddress;
	}
	/**
	 * 获取：上传地址
	 */
	public String getUploadAddress() {
		return uploadAddress;
	}
	/**
	 * 设置：上传时间
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	/**
	 * 获取：上传时间
	 */
	public Date getUploadTime() {
		return uploadTime;
	}

	public int getCourseTitleId() {
		return courseTitleId;
	}

	public void setCourseTitleId(int courseTitleId) {
		this.courseTitleId = courseTitleId;
	}

	public Long getKnowledgePointId() {
		return knowledgePointId;
	}

	public void setKnowledgePointId(Long knowledgePointId) {
		this.knowledgePointId = knowledgePointId;
	}

	public Long getUploadBy() {
		return uploadBy;
	}

	public void setUploadBy(Long uploadBy) {
		this.uploadBy = uploadBy;
	}
}
