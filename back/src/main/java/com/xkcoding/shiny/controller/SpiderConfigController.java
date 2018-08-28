package com.xkcoding.shiny.controller;

import com.xkcoding.shiny.common.ApiResponse;
import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.model.query.SpiderConfigPageQuery;
import com.xkcoding.shiny.model.vo.SpiderConfigVO;
import com.xkcoding.shiny.service.ISpiderConfigService;
import com.xkcoding.shiny.util.ShinyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 采集配置Controller
 * </p>
 *
 * @package: com.xkcoding.shiny.controller
 * @description： 采集配置Controller
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午8:33
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping("/api/spider/config")
@Slf4j
public class SpiderConfigController {
	private final ISpiderConfigService spiderConfigService;

	@Autowired
	public SpiderConfigController(ISpiderConfigService spiderConfigService) {
		this.spiderConfigService = spiderConfigService;
	}

	/**
	 * 返回采集配置列表
	 *
	 * @param query 查询条件
	 * @return 采集配置列表
	 */
	@GetMapping("")
	public ApiResponse listConfig(SpiderConfigPageQuery query) {
		query = ShinyUtil.checkPageCondition(query, SpiderConfigPageQuery.class);
		PageResult<SpiderConfigVO> pageResult = spiderConfigService.listSpiderConfig(query);
		return ApiResponse.ofSuccess(pageResult);
	}

}
