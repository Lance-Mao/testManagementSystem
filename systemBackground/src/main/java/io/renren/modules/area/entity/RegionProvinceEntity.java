package io.renren.modules.area.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 省份
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:03
 */
@TableName("tb_region_province")
public class RegionProvinceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 省份ID
	 */
	@TableId
	private Integer pid;
	/**
	 * 编码
	 */
	private Integer provinceid;
	/**
	 * 省份名称
	 */
	private String pname;

	/**
	 * 设置：省份ID
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取：省份ID
	 */
	public Integer getPid() {
		return pid;
	}
	/**
	 * 设置：编码
	 */
	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}
	/**
	 * 获取：编码
	 */
	public Integer getProvinceid() {
		return provinceid;
	}
	/**
	 * 设置：省份名称
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}
	/**
	 * 获取：省份名称
	 */
	public String getPname() {
		return pname;
	}
}
