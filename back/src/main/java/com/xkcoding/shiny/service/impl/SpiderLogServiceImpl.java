package com.xkcoding.shiny.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.mapper.SpiderLogMapper;
import com.xkcoding.shiny.model.SpiderLogDO;
import com.xkcoding.shiny.model.query.SpiderLogPageQuery;
import com.xkcoding.shiny.service.ISpiderLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

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

	/**
	 * 采集日志列表
	 *
	 * @param query 查询条件
	 * @return 采集日志列表
	 */
	@Override
	public PageResult<SpiderLogDO> listSpiderLog(SpiderLogPageQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());

		String spiderName = StrUtil.trim(query.getSpiderName());
		String version = StrUtil.trim(query.getVersion());
		Integer status = query.getStatus();
		String errorMsg = StrUtil.trim(query.getErrorMsg());
		String startTime = StrUtil.trim(query.getStartTime());
		String endTime = StrUtil.trim(query.getEndTime());

		if (StrUtil.isBlank(spiderName)) {
			spiderName = null;
		}
		if (StrUtil.isBlank(version)) {
			version = null;
		}
		if (StrUtil.isBlank(errorMsg)) {
			errorMsg = null;
		}
		if (StrUtil.isBlank(startTime)) {
			startTime = null;
		}
		if (StrUtil.isBlank(endTime)) {
			endTime = null;
		}

		List<SpiderLogDO> spiderLogDOList = spiderLogMapper.selectSpiderLogByParam(spiderName, version, status, errorMsg, startTime, endTime);

		Long total = ((Page) spiderLogDOList).getTotal();

		return new PageResult<>(total, spiderLogDOList);
	}

	/**
	 * 根据 id 删除日志
	 *
	 * @param id 日志 id
	 */
	@Override
	public void deleteLogById(Integer id) {
		spiderLogMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 批量删除日志
	 *
	 * @param ids 日志 id 列表
	 */
	@Override
	public void deleteBatch(List<Integer> ids) {
		spiderLogMapper.deleteBatch(ids);
	}
}
