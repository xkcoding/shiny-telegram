package com.xkcoding.shiny.common;

/**
 * <p>
 * Shiny常量池
 * </p>
 *
 * @package: com.xkcoding.shiny.common
 * @description： Shiny常量池
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午10:04
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface ShinyConst {
	/**
	 * 默认当前页
	 */
	Integer DEFAULT_CURRENT_PAGE = 1;

	/**
	 * 默认每页条数
	 */
	Integer DEFAULT_PAGE_SIZE = 20;

	/**
	 * Redis 中监听的邮件通道
	 */
	String MAIL_CHANNEL = "channel_mail";
}
