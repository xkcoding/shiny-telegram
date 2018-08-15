package com.xkcoding.shiny.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * 采集配置
 * </p>
 *
 * @package: com.xkcoding.shiny.model
 * @description： 采集配置
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午6:50
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Table(name = "spider_config")
public class SpiderConfigDO {
	/**
	 * 配置主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 采集名称
	 */
	@Column(name = "spider_name")
	private String spiderName;

	/**
	 * 采集URL
	 */
	@Column(name = "spider_url")
	private String spiderUrl;

	/**
	 * 上次采集时间
	 */
	@Column(name = "last_spider_time")
	private Date lastSpiderTime;

	/**
	 * 创建者
	 */
	@Column(name = "create_by")
	private String createBy;

	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 更新者
	 */
	@Column(name = "update_by")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@Column(name = "update_time")
	private Date updateTime;

	/**
	 * 备注
	 */
	@Column(name = "remark")
	private String remark;

	/**
	 * 获取配置主键
	 *
	 * @return id - 配置主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置配置主键
	 *
	 * @param id 配置主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取采集名称
	 *
	 * @return spider_name - 采集名称
	 */
	public String getSpiderName() {
		return spiderName;
	}

	/**
	 * 设置采集名称
	 *
	 * @param spiderName 采集名称
	 */
	public void setSpiderName(String spiderName) {
		this.spiderName = spiderName;
	}

	/**
	 * 获取采集URL
	 *
	 * @return spider_url - 采集URL
	 */
	public String getSpiderUrl() {
		return spiderUrl;
	}

	/**
	 * 设置采集URL
	 *
	 * @param spiderUrl 采集URL
	 */
	public void setSpiderUrl(String spiderUrl) {
		this.spiderUrl = spiderUrl;
	}

	/**
	 * 获取上次采集时间
	 *
	 * @return last_spider_time - 上次采集时间
	 */
	public Date getLastSpiderTime() {
		return lastSpiderTime;
	}

	/**
	 * 设置上次采集时间
	 *
	 * @param lastSpiderTime 上次采集时间
	 */
	public void setLastSpiderTime(Date lastSpiderTime) {
		this.lastSpiderTime = lastSpiderTime;
	}

	/**
	 * 获取创建者
	 *
	 * @return create_by - 创建者
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * 设置创建者
	 *
	 * @param createBy 创建者
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取更新者
	 *
	 * @return update_by - 更新者
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * 设置更新者
	 *
	 * @param updateBy 更新者
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 获取更新时间
	 *
	 * @return update_time - 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置更新时间
	 *
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取备注
	 *
	 * @return remark - 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 *
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
}