package com.xkcoding.shiny.common.status;

import lombok.Getter;

/**
 * <p>
 * 状态码枚举
 * </p>
 *
 * @package: com.xkcoding.shiny.common.status
 * @description： 状态码枚举
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午8:37
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Getter
public enum Status {
	/**
	 * 操作成功
	 */
	SUCCESS(200, "操作成功"),

	/**
	 * 请求错误
	 */
	BAD_REQUEST(400, "请求错误"),

	/**
	 * 尚未登录
	 */
	UNAUTHORIZED(401, "尚未登录"),

	/**
	 * 权限不够
	 */
	FORBIDDEN(403, "权限不够"),

	/**
	 * 请求不存在
	 */
	REQUEST_NOT_FOUND(404, "请求不存在"),

	/**
	 * 服务器内部错误
	 */
	INTERNAL_SERVER_ERROR(500, "服务器内部错误"),

	/**
	 * 请求参数错误
	 */
	REQUEST_PARAMS_ERROR(50000, "请求参数错误"),

	/**
	 * 采集配置不存在
	 */
	CONFIG_NOT_EXIST(50001, "采集配置不存在"),

	/**
	 * 日志id列表不能为空
	 */
	LOG_LIST_NOT_EMPTY(50002, "日志id列表不能为空");

	private Integer code;
	private String msg;

	Status(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
