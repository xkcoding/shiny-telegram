package com.xkcoding.shiny.model.query;

import com.xkcoding.shiny.model.query.base.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 采集日志查询条件
 * </p>
 *
 * @package: com.xkcoding.shiny.model.query
 * @description： 采集日志查询条件
 * @author: yangkai.shen
 * @date: Created in 2018/8/29 下午9:11
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SpiderLogPageQuery extends PageCondition {
	/**
	 * 采集名称
	 */
	private String spiderName;

	/**
	 * 采集版本
	 */
	private String version;

	/**
	 * 采集状态（0异常 1正常）
	 */
	private Integer status;

	/**
	 * 错误消息
	 */
	private String errorMsg;

	/**
	 * 采集时间 - 起始时间
	 */
	private String startTime;

	/**
	 * 采集时间 - 结束时间
	 */
	private String endTime;
}