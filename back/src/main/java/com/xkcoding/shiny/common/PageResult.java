package com.xkcoding.shiny.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>
 * 分页结果
 * </p>
 *
 * @package: com.xkcoding.shiny.common
 * @description： 分页结果
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午10:00
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
	/**
	 * 总条数
	 */
	private Long total;

	/**
	 * 列表数据
	 */
	private List<T> list;
}
