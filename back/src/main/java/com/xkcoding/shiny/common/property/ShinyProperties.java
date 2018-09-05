package com.xkcoding.shiny.common.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 自定义配置
 * </p>
 *
 * @package: com.xkcoding.shiny.common.property
 * @description： 自定义配置
 * @author: yangkai.shen
 * @date: Created in 2018/8/27 上午9:57
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@ConfigurationProperties(prefix = "shiny")
@Data
public class ShinyProperties {
	/**
	 * 软件名称
	 */
	private String name;

	/**
	 * 软件版本
	 */
	private String version;

	/**
	 * 版权年份
	 */
	private String copyrightYear;

	/**
	 * 作者
	 */
	private String developer;

	/**
	 * driver 路径
	 */
	private String driverPath;

	/**
	 * 并发软件采集的数量
	 */
	private Integer spiderNum;

	/**
	 * 邮件发送人
	 */
	private String mailFrom;

}
