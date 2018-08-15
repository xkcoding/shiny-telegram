package com.xkcoding.shiny.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 采集配置VO
 * </p>
 *
 * @package: com.xkcoding.shiny.model.vo
 * @description： 采集配置VO
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午9:51
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class SpiderConfigVO implements Serializable {
	/**
	 * 配置主键
	 */
	private Integer id;

	/**
	 * 采集名称
	 */
	private String spiderName;

	/**
	 * 采集URL
	 */
	private String spiderUrl;

	/**
	 * 上次采集时间
	 */
	private Date lastSpiderTime;

	/**
	 * 创建者
	 */
	private String createBy;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新者
	 */
	private String updateBy;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 备注
	 */
	private String remark;
}
