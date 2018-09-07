package com.xkcoding.shiny.model.query;

import com.xkcoding.shiny.model.query.base.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 采集内容查询条件
 * </p>
 *
 * @package: com.xkcoding.shiny.model.query
 * @description： 采集内容查询条件
 * @author: yangkai.shen
 * @date: Created in 2018/9/3 下午9:56
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SpiderContentPageQuery extends PageCondition {
	/**
	 * 采集配置名称
	 */
	private String configName;
	
	/**
	 * 软件名称
	 */
	private String title;

	/**
	 * 软件描述内容
	 */
	private String content;

	/**
	 * 软件版本
	 */
	private String version;

	/**
	 * 软件语言
	 */
	private String language;

	/**
	 * 软件更新日期
	 */
	private String updateTime;

	/**
	 * 采集时间
	 */
	private String spiderTime;
}
