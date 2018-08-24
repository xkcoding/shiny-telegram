package com.xkcoding.shiny.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * 采集内容
 * </p>
 *
 * @package: com.xkcoding.shiny.model
 * @description： 采集内容
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午6:51
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Table(name = "spider_content")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpiderContentDO {
	/**
	 * 内容主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 采集配置id
	 */
	@Column(name = "config_id")
	private Integer configId;

	/**
	 * 软件名称
	 */
	@Column(name = "title")
	private String title;

	/**
	 * 软件版本
	 */
	@Column(name = "version")
	private String version;

	/**
	 * 软件语言
	 */
	@Column(name = "language")
	private String language;

	/**
	 * 软件更新时间
	 */
	@Column(name = "update_time")
	private String updateTime;

	/**
	 * 软件大小
	 */
	@Column(name = "size")
	private String size;

	/**
	 * 城通网盘链接
	 */
	@Column(name = "ct_pan_url")
	private String ctPanUrl;

	/**
	 * 城通网盘提取码
	 */
	@Column(name = "ct_pan_code")
	private String ctPanCode;

	/**
	 * 百度网盘链接
	 */
	@Column(name = "bd_pan_url")
	private String bdPanUrl;

	/**
	 * 百度网盘提取码
	 */
	@Column(name = "bd_pan_code")
	private String bdPanCode;

	/**
	 * 采集时间
	 */
	@Column(name = "spider_time")
	private Date spiderTime;

	/**
	 * 软件详细信息
	 */
	@Column(name = "content")
	private String content;

	/**
	 * 获取内容主键
	 *
	 * @return id - 内容主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置内容主键
	 *
	 * @param id 内容主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取采集配置id
	 *
	 * @return config_id - 采集配置id
	 */
	public Integer getConfigId() {
		return configId;
	}

	/**
	 * 设置采集配置id
	 *
	 * @param configId 采集配置id
	 */
	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	/**
	 * 获取软件名称
	 *
	 * @return title - 软件名称
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置软件名称
	 *
	 * @param title 软件名称
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取软件版本
	 *
	 * @return version - 软件版本
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 设置软件版本
	 *
	 * @param version 软件版本
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * 获取软件语言
	 *
	 * @return language - 软件语言
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * 设置软件语言
	 *
	 * @param language 软件语言
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 获取软件更新时间
	 *
	 * @return update_time - 软件更新时间
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置软件更新时间
	 *
	 * @param updateTime 软件更新时间
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取软件大小
	 *
	 * @return size - 软件大小
	 */
	public String getSize() {
		return size;
	}

	/**
	 * 设置软件大小
	 *
	 * @param size 软件大小
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * 获取城通网盘链接
	 *
	 * @return ct_pan_url - 城通网盘链接
	 */
	public String getCtPanUrl() {
		return ctPanUrl;
	}

	/**
	 * 设置城通网盘链接
	 *
	 * @param ctPanUrl 城通网盘链接
	 */
	public void setCtPanUrl(String ctPanUrl) {
		this.ctPanUrl = ctPanUrl;
	}

	/**
	 * 获取城通网盘提取码
	 *
	 * @return ct_pan_code - 城通网盘提取码
	 */
	public String getCtPanCode() {
		return ctPanCode;
	}

	/**
	 * 设置城通网盘提取码
	 *
	 * @param ctPanCode 城通网盘提取码
	 */
	public void setCtPanCode(String ctPanCode) {
		this.ctPanCode = ctPanCode;
	}

	/**
	 * 获取百度网盘链接
	 *
	 * @return bd_pan_url - 百度网盘链接
	 */
	public String getBdPanUrl() {
		return bdPanUrl;
	}

	/**
	 * 设置百度网盘链接
	 *
	 * @param bdPanUrl 百度网盘链接
	 */
	public void setBdPanUrl(String bdPanUrl) {
		this.bdPanUrl = bdPanUrl;
	}

	/**
	 * 获取百度网盘提取码
	 *
	 * @return bd_pan_code - 百度网盘提取码
	 */
	public String getBdPanCode() {
		return bdPanCode;
	}

	/**
	 * 设置百度网盘提取码
	 *
	 * @param bdPanCode 百度网盘提取码
	 */
	public void setBdPanCode(String bdPanCode) {
		this.bdPanCode = bdPanCode;
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

	/**
	 * 获取软件详细信息
	 *
	 * @return content - 软件详细信息
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置软件详细信息
	 *
	 * @param content 软件详细信息
	 */
	public void setContent(String content) {
		this.content = content;
	}
}