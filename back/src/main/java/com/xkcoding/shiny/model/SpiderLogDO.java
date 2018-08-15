package com.xkcoding.shiny.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * 采集日志
 * </p>
 *
 * @package: com.xkcoding.shiny.model
 * @description： 采集日志
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午6:51
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Table(name = "spider_log")
public class SpiderLogDO {
	/**
	 * 日志主键
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
	 * 采集状态（0异常 1正常）
	 */
	@Column(name = "status")
	private Integer status;

	/**
	 * 错误消息
	 */
	@Column(name = "error_msg")
	private String errorMsg;

	/**
	 * 采集时间
	 */
	@Column(name = "spider_time")
	private Date spiderTime;

	/**
	 * 获取日志主键
	 *
	 * @return id - 日志主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置日志主键
	 *
	 * @param id 日志主键
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
	 * 获取采集状态（0异常 1正常）
	 *
	 * @return status - 采集状态（0异常 1正常）
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置采集状态（0异常 1正常）
	 *
	 * @param status 采集状态（0异常 1正常）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取错误消息
	 *
	 * @return error_msg - 错误消息
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * 设置错误消息
	 *
	 * @param errorMsg 错误消息
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * 获取采集时间
	 *
	 * @return spider_time - 采集时间
	 */
	public Date getSpiderTime() {
		return spiderTime;
	}

	/**
	 * 设置采集时间
	 *
	 * @param spiderTime 采集时间
	 */
	public void setSpiderTime(Date spiderTime) {
		this.spiderTime = spiderTime;
	}
}