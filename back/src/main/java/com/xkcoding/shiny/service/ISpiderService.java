package com.xkcoding.shiny.service;

import com.xkcoding.shiny.model.SpiderConfigDO;

import java.util.List;

/**
 * <p>
 * 爬虫接口
 * </p>
 *
 * @package: com.xkcoding.shiny.service
 * @description： 爬虫接口
 * @author: yangkai.shen
 * @date: Created in 2018/8/27 下午6:35
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface ISpiderService {
	/**
	 * 根据配置列表采集信息
	 *
	 * @param configDOList 采集配置列表
	 */
	void spider(List<SpiderConfigDO> configDOList) throws InterruptedException;

	/**
	 * 重新爬取今天采集的所有软件信息
	 */
	void reSpiderAllToday() throws InterruptedException;

	/**
	 * 重新爬取今天采集的某个软件信息
	 *
	 * @param configId 采集配置id
	 */
	void reSpiderToday(Integer configId) throws InterruptedException;
}
