package com.xkcoding.shiny.service;

import com.xkcoding.shiny.model.SpiderLogDO;

/**
 * <p>
 * 采集日志服务接口
 * </p>
 *
 * @package: com.xkcoding.shiny.service
 * @description： 采集日志服务接口
 * @author: yangkai.shen
 * @date: Created in 2018/8/28 下午8:54
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface ISpiderLogService {
	/**
	 * 保存采集日志
	 *
	 * @param spiderLogDO 采集日志 DO
	 */
	void saveSpiderLog(SpiderLogDO spiderLogDO);
}
