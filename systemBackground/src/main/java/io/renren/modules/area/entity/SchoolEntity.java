package io.renren.modules.area.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 学院
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:04
 */
@TableName("tb_school")
public class SchoolEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer scid;
	/**
	 * 学员名称
	 */
	private String name;
	/**
	 * 所属高校
	 */
	private Integer collegeid;

	/**
	 * 设置：ID
	 */
	public void setScid(Integer scid) {
		this.scid = scid;
	}
	/**
	 * 获取：ID
	 */
	public Integer getScid() {
		return scid;
	}
	/**
	 * 设置：学员名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：学员名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：所属高校
	 */
	public void setCollegeid(Integer collegeid) {
		this.collegeid = collegeid;
	}
	/**
	 * 获取：所属高校
	 */
	public Integer getCollegeid() {
		return collegeid;
	}
}
