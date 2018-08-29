package com.xkcoding.shiny.service;

import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.model.SpiderLogDO;
import com.xkcoding.shiny.model.query.SpiderLogPageQuery;

import java.util.List;

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

	/**
	 * 采集日志列表
	 *
	 * @param query 查询条件
	 * @return 采集日志列表
	 */
	PageResult<SpiderLogDO> listSpiderLog(SpiderLogPageQuery query);

	/**
	 * 根据 id 删除日志
	 *
	 * @param id 日志 id
	 */
	void deleteLogById(Integer id);

	/**
	 * 批量删除日志
	 *
	 * @param ids 日志 id 列表
	 */
	void deleteBatch(List<Integer> ids);
}
