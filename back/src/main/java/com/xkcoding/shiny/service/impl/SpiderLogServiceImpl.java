package com.xkcoding.shiny.service.impl;

import com.xkcoding.shiny.mapper.SpiderLogMapper;
import com.xkcoding.shiny.model.SpiderLogDO;
import com.xkcoding.shiny.service.ISpiderLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 采集日志接口实现
 * </p>
 *
 * @package: com.xkcoding.shiny.service.impl
 * @description： 采集日志接口实现
 * @author: yangkai.shen
 * @date: Created in 2018/8/28 下午8:55
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
@Slf4j
public class SpiderLogServiceImpl implements ISpiderLogService {
	private final SpiderLogMapper spiderLogMapper;

	@Autowired
	public SpiderLogServiceImpl(SpiderLogMapper spiderLogMapper) {
		this.spiderLogMapper = spiderLogMapper;
	}

	/**
	 * 保存采集日志
	 *
	 * @param spiderLogDO 采集日志 DO
	 */
	@Async("asyncExecutor")
	@Override
	public void saveSpiderLog(SpiderLogDO spiderLogDO) {
		spiderLogMapper.insertUseGeneratedKeys(spiderLogDO);
	}
}
