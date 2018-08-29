package com.xkcoding.shiny.controller;

import cn.hutool.core.collection.CollUtil;
import com.xkcoding.shiny.common.ApiResponse;
import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.common.status.Status;
import com.xkcoding.shiny.exception.ShinyException;
import com.xkcoding.shiny.model.SpiderLogDO;
import com.xkcoding.shiny.model.query.SpiderLogPageQuery;
import com.xkcoding.shiny.service.ISpiderLogService;
import com.xkcoding.shiny.util.ShinyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 采集日志 Controller
 * </p>
 *
 * @package: com.xkcoding.shiny.controller
 * @description： 采集日志 Controller
 * @author: yangkai.shen
 * @date: Created in 2018/8/29 下午9:09
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping("/api/spider/log")
@Slf4j
public class SpiderLogController {
	private final ISpiderLogService spiderLogService;

	@Autowired
	public SpiderLogController(ISpiderLogService spiderLogService) {
		this.spiderLogService = spiderLogService;
	}

	/**
	 * 返回采集日志列表
	 *
	 * @param query 查询条件
	 * @return 采集配置日志列表
	 */
	@GetMapping("")
	public ApiResponse listLog(SpiderLogPageQuery query) {
		query = ShinyUtil.checkPageCondition(query, SpiderLogPageQuery.class);
		PageResult<SpiderLogDO> pageResult = spiderLogService.listSpiderLog(query);
		return ApiResponse.ofSuccess(pageResult);
	}

	/**
	 * 根据 id 删除日志
	 *
	 * @param id 日志id
	 */
	@DeleteMapping("/{id}")
	public ApiResponse deleteLog(@PathVariable Integer id) {
		spiderLogService.deleteLogById(id);
		return ApiResponse.ofSuccess();
	}

	/**
	 * 批量删除日志
	 *
	 * @param ids 日志 id 列表
	 * @throws ShinyException 参数不能为空
	 */
	@DeleteMapping("")
	public ApiResponse deleteLogBatch(@RequestBody List<Integer> ids) throws ShinyException {
		if (CollUtil.isEmpty(ids)) {
			throw new ShinyException(Status.LOG_LIST_NOT_EMPTY);
		}
		spiderLogService.deleteBatch(ids);
		return ApiResponse.ofSuccess();
	}

	/**
	 * 清空采集日志
	 */
	@DeleteMapping(value = "", params = "today")
	public ApiResponse deleteTodayLog() {
		spiderLogService.deleteTodayLog();
		return ApiResponse.ofSuccess();
	}
}
