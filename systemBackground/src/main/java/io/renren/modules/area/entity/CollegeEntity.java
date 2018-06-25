package io.renren.modules.area.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 高校
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:04
 */
@TableName("tb_college")
public class CollegeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 高校编码
	 */
	@TableId
	private Integer coid;
	/**
	 * 高校名称
	 */
	private String name;
	/**
	 * 所属省份
	 */
	private Integer provinceid;

	/**
	 * 设置：高校编码
	 */
	public void setCoid(Integer coid) {
		this.coid = coid;
	}
	/**
	 * 获取：高校编码
	 */
	public Integer getCoid() {
		return coid;
	}
	/**
	 * 设置：高校名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：高校名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：所属省份
	 */
	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}
	/**
	 * 获取：所属省份
	 */
	public Integer getProvinceid() {
		return provinceid;
	}
}
