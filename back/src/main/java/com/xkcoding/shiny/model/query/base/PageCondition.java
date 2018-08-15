package com.xkcoding.shiny.model.query.base;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * <p>
 * 分页基本条件
 * </p>
 *
 * @package: com.xkcoding.shiny.model.query.base
 * @description： 分页基本条件
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午10:08
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
public class PageCondition {
	/**
	 * 当前页
	 */
	private Integer currentPage;

	/**
	 * 每页条数
	 */
	private Integer pageSize;
}
