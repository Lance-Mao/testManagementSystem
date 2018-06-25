package io.renren.modules.area.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 区县
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:03
 */
@TableName("tb_region_area")
public class RegionAreaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer aid;
	/**
	 * 编码
	 */
	private Integer areaid;
	/**
	 * 县名称
	 */
	private String area;
	/**
	 * 所属城市
	 */
	private Integer cityid;

	/**
	 * 设置：ID
	 */
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	/**
	 * 获取：ID
	 */
	public Integer getAid() {
		return aid;
	}
	/**
	 * 设置：编码
	 */
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	/**
	 * 获取：编码
	 */
	public Integer getAreaid() {
		return areaid;
	}
	/**
	 * 设置：县名称
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * 获取：县名称
	 */
	public String getArea() {
		return area;
	}
	/**
	 * 设置：所属城市
	 */
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	/**
	 * 获取：所属城市
	 */
	public Integer getCityid() {
		return cityid;
	}
}
