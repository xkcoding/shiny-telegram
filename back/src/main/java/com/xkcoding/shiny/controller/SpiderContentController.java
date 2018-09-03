package com.xkcoding.shiny.controller;

import cn.hutool.core.util.ObjectUtil;
import com.xkcoding.shiny.common.ApiResponse;
import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.common.status.Status;
import com.xkcoding.shiny.exception.ShinyException;
import com.xkcoding.shiny.model.query.SpiderContentPageQuery;
import com.xkcoding.shiny.model.vo.SpiderContentVO;
import com.xkcoding.shiny.service.ISpiderContentService;
import com.xkcoding.shiny.util.ShinyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 采集内容 Controller
 * </p>
 *
 * @package: com.xkcoding.shiny.controller
 * @description： 采集内容 Controller
 * @author: yangkai.shen
 * @date: Created in 2018/9/3 下午9:38
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@RestController
@RequestMapping("/api/spider/content")
@Slf4j
public class SpiderContentController {
	private final ISpiderContentService spiderContentService;

	@Autowired
	public SpiderContentController(ISpiderContentService spiderContentService) {
		this.spiderContentService = spiderContentService;
	}

	/**
	 * 查看单个采集内容详情
	 *
	 * @param configId 采集配置id
	 * @return 单个采集内容
	 * @throws ShinyException 参数错误
	 */
	@GetMapping("/{configId}")
	public ApiResponse listSpiderContent(@PathVariable Integer configId, SpiderContentPageQuery query) throws ShinyException {
		if (ObjectUtil.isNull(configId)) {
			throw new ShinyException(Status.REQUEST_PARAMS_ERROR);
		}
		query = ShinyUtil.checkPageCondition(query, SpiderContentPageQuery.class);
		PageResult<SpiderContentVO> pageResult = spiderContentService.getSpiderContent(configId, query);
		return ApiResponse.ofSuccess(pageResult);
	}
}
