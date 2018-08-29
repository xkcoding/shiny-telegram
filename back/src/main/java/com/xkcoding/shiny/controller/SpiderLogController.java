package com.xkcoding.shiny.controller;

import com.xkcoding.shiny.common.ApiResponse;
import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.model.SpiderLogDO;
import com.xkcoding.shiny.model.query.SpiderLogPageQuery;
import com.xkcoding.shiny.service.ISpiderLogService;
import com.xkcoding.shiny.util.ShinyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
