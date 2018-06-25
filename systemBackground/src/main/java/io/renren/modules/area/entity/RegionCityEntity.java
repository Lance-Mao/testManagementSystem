package io.renren.modules.area.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 城市
 * 
 * @author maoliangwei@ThoughtWorks-Eurasia
 * @email mlw6198@gmail.com
 * @date 2018-06-25 14:52:03
 */
@TableName("tb_region_city")
public class RegionCityEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer cid;
	/**
	 * 编码
	 */
	private Integer cityid;
	/**
	 * 城市名称
	 */
	private String city;
	/**
	 * 所属省份
	 */
	private Integer provinceid;

	/**
	 * 设置：ID
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：ID
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：编码
	 */
	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}
	/**
	 * 获取：编码
	 */
	public Integer getCityid() {
		return cityid;
	}
	/**
	 * 设置：城市名称
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：城市名称
	 */
	public String getCity() {
		return city;
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
