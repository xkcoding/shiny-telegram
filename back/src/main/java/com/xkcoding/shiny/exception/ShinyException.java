package com.xkcoding.shiny.exception;

import com.xkcoding.shiny.common.status.Status;
import lombok.Getter;

/**
 * <p>
 * 通用全局异常
 * </p>
 *
 * @package: com.xkcoding.shiny.exception
 * @description： 通用全局异常
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午8:39
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Getter
public class ShinyException extends Exception {
	/**
	 * 异常码
	 */
	private Integer code;

	/**
	 * 错误信息
	 */
	private String msg;

	/**
	 * 返回内容
	 */
	private Object data;

	public ShinyException(Integer code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public ShinyException(Integer code, String msg, Object data) {
		super(msg);
		this.code = code;
		this.msg = msg;
		this.data = data;
	}


	public ShinyException(String msg, Integer code, Object data) {
		super(msg);
		this.msg = msg;
		this.code = code;
		this.data = data;
	}

	public ShinyException(Status status) {
		super(status.getMsg());
		this.code = status.getCode();
		this.msg = status.getMsg();
	}

	public ShinyException(Status status, Object data) {
		super(status.getMsg());
		this.code = status.getCode();
		this.msg = status.getMsg();
		this.data = data;
	}
}
