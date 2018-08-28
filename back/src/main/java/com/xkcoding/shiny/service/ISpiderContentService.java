package com.xkcoding.shiny.service;

/**
 * <p>
 * 采集内容接口
 * </p>
 *
 * @package: com.xkcoding.shiny.service
 * @description： 采集内容接口
 * @author: yangkai.shen
 * @date: Created in 2018/8/27 下午9:48
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface ISpiderContentService {
	/**
	 * 删除今天采集的所有软件信息
	 */
	void deleteAllToday();

	/**
	 * 删除今天采集的某个软件信息
	 *
	 * @param configId 采集配置 id
	 */
	void deleteToday(Integer configId);
}
