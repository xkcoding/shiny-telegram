package com.xkcoding.shiny.controller;

import cn.hutool.core.util.ObjectUtil;
import com.xkcoding.shiny.common.ApiResponse;
import com.xkcoding.shiny.common.status.Status;
import com.xkcoding.shiny.exception.ShinyException;
import com.xkcoding.shiny.model.SpiderConfigDO;
import com.xkcoding.shiny.service.ISpiderConfigService;
import com.xkcoding.shiny.service.ISpiderContentService;
import com.xkcoding.shiny.service.ISpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 采集 Controller
 * </p>
 *
 * @package: com.xkcoding.shiny.controller
 * @description： 采集 Controller
 * @author: yangkai.shen
 * @date: Created in 2018/8/27 下午2:28
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping("/api/spider")
@Slf4j
public class SpiderController {
	private final ISpiderConfigService spiderConfigService;

	private final ISpiderContentService spiderContentService;

	private final ISpiderService spiderService;

	@Autowired
	public SpiderController(ISpiderConfigService spiderConfigService, ISpiderContentService spiderContentService, ISpiderService spiderService) {
		this.spiderConfigService = spiderConfigService;
		this.spiderContentService = spiderContentService;
		this.spiderService = spiderService;
	}

	/**
	 * 重新爬取今天采集的所有软件信息
	 */
	@GetMapping("")
	public ApiResponse reSpiderAllToday() throws InterruptedException {
		// 删除所有今天采集的内容
		spiderContentService.deleteAllToday();

		// 重新采集
		spiderService.reSpiderAllToday();
		return ApiResponse.ofSuccess();
	}

	/**
	 * 重新爬取今天采集的某个软件信息
	 */
	@GetMapping("/{id}")
	public ApiResponse reSpiderToday(@PathVariable Integer id) throws ShinyException, InterruptedException {
		SpiderConfigDO spiderConfigDO = spiderConfigService.selectSpiderConfig(id);
		if (ObjectUtil.isNull(spiderConfigDO)) {
			throw new ShinyException(Status.CONFIG_NOT_EXIST);
		}

		// 删除当前配置 id 对应的今天采集的内容
		spiderContentService.deleteToday(id);

		// 重新采集
		spiderService.reSpiderToday(id);
		return ApiResponse.ofSuccess();
	}
}
