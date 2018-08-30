package com.xkcoding.shiny.handler;

import com.xkcoding.shiny.common.ApiResponse;
import com.xkcoding.shiny.common.status.Status;
import com.xkcoding.shiny.exception.ShinyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @package: com.xkcoding.shiny.handler
 * @description： 全局异常处理
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午8:40
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ApiResponse handlerException(Exception e) {
		if (e instanceof NoHandlerFoundException) {
			log.error("【全局异常拦截】NoHandlerFoundException: 请求方法 {}, 请求路径 {}", ((NoHandlerFoundException) e).getRequestURL(), ((NoHandlerFoundException) e).getHttpMethod());
			return ApiResponse.ofStatus(Status.REQUEST_NOT_FOUND);
		} else if (e instanceof MethodArgumentNotValidException) {
			log.error("【全局异常拦截】MethodArgumentNotValidException", e);
			return ApiResponse.ofStatus(Status.REQUEST_PARAMS_ERROR);
		} else if (e instanceof MethodArgumentTypeMismatchException) {
			log.error("【全局异常拦截】MethodArgumentTypeMismatchException: 参数名 {}, 异常信息 {}", ((MethodArgumentTypeMismatchException) e).getName(), ((MethodArgumentTypeMismatchException) e).getMessage());
			return ApiResponse.ofStatus(Status.REQUEST_PARAMS_ERROR);
		} else if (e instanceof HttpMessageNotReadableException) {
			log.error("【全局异常拦截】HttpMessageNotReadableException: 错误信息 {}", ((HttpMessageNotReadableException) e).getMessage());
			return ApiResponse.ofStatus(Status.REQUEST_PARAMS_ERROR);
		} else if (e instanceof ShinyException) {
			log.error("【全局异常拦截】ShinyException: 状态码 {}, 异常信息 {}", ((ShinyException) e).getCode(), e.getMessage());
			return new ApiResponse(((ShinyException) e).getCode(), e.getMessage(), ((ShinyException) e).getData());
		}
		log.error("【全局异常拦截】: 异常信息 {} ", e.getMessage());
		return ApiResponse.ofStatus(Status.INTERNAL_SERVER_ERROR);
	}
}
