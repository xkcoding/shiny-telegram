package com.xkcoding.shiny.model.query;

import com.xkcoding.shiny.model.query.base.PageCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 采集配置查询条件
 * </p>
 *
 * @package: com.xkcoding.shiny.model.query
 * @description： 采集配置查询条件
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午10:10
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SpiderConfigPageQuery extends PageCondition {
	/**
	 * 采集配置名称 / 采集配置备注
	 */
	private String text;
}
