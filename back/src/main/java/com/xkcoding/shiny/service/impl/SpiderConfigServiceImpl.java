package com.xkcoding.shiny.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.mapper.SpiderConfigMapper;
import com.xkcoding.shiny.model.SpiderConfigDO;
import com.xkcoding.shiny.model.query.SpiderConfigPageQuery;
import com.xkcoding.shiny.model.vo.SpiderConfigVO;
import com.xkcoding.shiny.service.ISpiderConfigService;
import com.xkcoding.shiny.util.ShinyUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 采集配置Service接口实现
 * </p>
 *
 * @package: com.xkcoding.shiny.service.impl
 * @description： 采集配置Service接口实现
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午10:02
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
public class SpiderConfigServiceImpl implements ISpiderConfigService {
	private final SpiderConfigMapper spiderConfigMapper;

	private final ModelMapper modelMapper;

	@Autowired
	public SpiderConfigServiceImpl(SpiderConfigMapper spiderConfigMapper, ModelMapper modelMapper) {
		this.spiderConfigMapper = spiderConfigMapper;
		this.modelMapper = modelMapper;
	}

	/**
	 * 分页采集配置列表
	 *
	 * @param query 查询条件
	 * @return 分页采集配置列表信息
	 */
	@Override
	public PageResult<SpiderConfigVO> listSpiderConfig(SpiderConfigPageQuery query) {
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());

		String spiderName = StrUtil.trim(query.getText());
		if (StrUtil.isBlank(spiderName)) {
			spiderName = null;
		}
		List<SpiderConfigDO> spiderConfigDOList = spiderConfigMapper.selectSpiderConfigByText(spiderName);

		Long total = ((Page) spiderConfigDOList).getTotal();
		List<SpiderConfigVO> spiderConfigVOList = spiderConfigDOList.stream().map(spiderConfigDO -> modelMapper.map(spiderConfigDO, SpiderConfigVO.class)).collect(Collectors.toList());
		return new PageResult<>(total, spiderConfigVOList);
	}

	/**
	 * 全部采集配置列表
	 *
	 * @return 全部采集配置列表
	 */
	@Override
	public List<SpiderConfigDO> listAllSpiderConfig() {
		return spiderConfigMapper.selectAll();
	}

	/**
	 * 根据配置id获取采集配置
	 *
	 * @param id 配置 id
	 * @return 采集配置 DO
	 */
	@Override
	public SpiderConfigDO selectSpiderConfig(Integer id) {
		return spiderConfigMapper.selectByPrimaryKey(id);
	}

	/**
	 * 保存采集配置
	 *
	 * @param spiderConfigVO 采集配置 VO
	 * @return 采集配置 DO
	 */
	@Override
	public SpiderConfigDO saveConfig(SpiderConfigVO spiderConfigVO) {
		// VO -> DO
		SpiderConfigDO spiderConfigDO = modelMapper.map(spiderConfigVO, SpiderConfigDO.class);
		ShinyUtil.beforeInsert(spiderConfigDO, SpiderConfigDO.class);
		spiderConfigMapper.insertUseGeneratedKeys(spiderConfigDO);
		return spiderConfigDO;
	}
}
