package com.xkcoding.shiny.common.status;

import lombok.Getter;

/**
 * <p>
 * 操作状态枚举
 * </p>
 *
 * @package: com.xkcoding.shiny.common.status
 * @description： 操作状态枚举
 * @author: yangkai.shen
 * @date: Created in 2018/8/28 下午9:04
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Getter
public enum OperateStatus {
	/**
	 * 成功
	 */
	SUCCESS(1),

	/**
	 * 失败
	 */
	ERROR(0);

	OperateStatus(Integer code) {
		this.code = code;
	}

	private Integer code;
}
