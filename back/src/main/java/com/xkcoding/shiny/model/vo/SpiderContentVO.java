package com.xkcoding.shiny.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 采集内容VO
 * </p>
 *
 * @package: com.xkcoding.shiny.model.vo
 * @description： 采集内容VO
 * @author: yangkai.shen
 * @date: Created in 2018/9/3 下午9:46
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpiderContentVO implements Serializable {

	private static final long serialVersionUID = 1230401625684294310L;
	/**
	 * 内容主键
	 */
	private Integer id;

	/**
	 * 采集配置id
	 */
	private Integer configId;

	/**
	 * 软件名称
	 */
	private String title;

	/**
	 * 软件版本
	 */
	private String version;

	/**
	 * 软件语言
	 */
	private String language;

	/**
	 * 软件更新时间
	 */
	private Date updateTime;

	/**
	 * 软件大小
	 */
	private String size;

	/**
	 * 城通网盘链接
	 */
	private String ctPanUrl;

	/**
	 * 城通网盘提取码
	 */
	private String ctPanCode;

	/**
	 * 百度网盘链接
	 */
	private String bdPanUrl;

	/**
	 * 百度网盘提取码
	 */
	private String bdPanCode;

	/**
	 * 采集时间
	 */
	private Date spiderTime;

	/**
	 * 软件详细信息
	 */
	private String content;

}