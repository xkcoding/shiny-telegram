package com.xkcoding.shiny.controller;

import cn.hutool.core.util.ObjectUtil;
import com.xkcoding.shiny.common.ApiResponse;
import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.common.status.Status;
import com.xkcoding.shiny.exception.ShinyException;
import com.xkcoding.shiny.model.SpiderConfigDO;
import com.xkcoding.shiny.model.query.SpiderConfigPageQuery;
import com.xkcoding.shiny.model.vo.SpiderConfigVO;
import com.xkcoding.shiny.service.ISpiderConfigService;
import com.xkcoding.shiny.util.ShinyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	 * 保存采集配置
	 *
	 * @param spiderConfigVO 采集配置 VO
	 * @return 采集配置 DO
	 * @throws ShinyException 参数异常
	 */
	@PostMapping("")
	public ApiResponse addConfig(@RequestBody SpiderConfigVO spiderConfigVO) throws ShinyException {
		if (ShinyUtil.isEmpty(spiderConfigVO, SpiderConfigVO.class)) {
			throw new ShinyException(Status.REQUEST_PARAMS_ERROR);
		}
		SpiderConfigDO spiderConfigDO = spiderConfigService.saveConfig(spiderConfigVO);
		return ApiResponse.ofSuccess(spiderConfigDO);
	}

	/**
	 * 更新采集配置
	 *
	 * @param id             配置 id
	 * @param spiderConfigVO 采集配置 VO
	 * @return 采集配置 DO
	 * @throws ShinyException 参数异常
	 */
	@PutMapping("/{id}")
	public ApiResponse updateConfig(@PathVariable Integer id, @RequestBody SpiderConfigVO spiderConfigVO) throws ShinyException {
		if (ShinyUtil.isEmpty(spiderConfigVO, SpiderConfigVO.class) || !ObjectUtil.equal(id, spiderConfigVO.getId())) {
			throw new ShinyException(Status.REQUEST_PARAMS_ERROR);
		}

		SpiderConfigDO spiderConfigDO = spiderConfigService.updateConfig(id, spiderConfigVO);
		return ApiResponse.ofSuccess(spiderConfigDO);
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
