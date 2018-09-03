package com.xkcoding.shiny.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.common.status.Status;
import com.xkcoding.shiny.exception.ShinyException;
import com.xkcoding.shiny.mapper.SpiderConfigMapper;
import com.xkcoding.shiny.mapper.SpiderContentMapper;
import com.xkcoding.shiny.model.SpiderConfigDO;
import com.xkcoding.shiny.model.SpiderContentDO;
import com.xkcoding.shiny.model.query.SpiderContentPageQuery;
import com.xkcoding.shiny.model.vo.SpiderContentVO;
import com.xkcoding.shiny.service.ISpiderContentService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 采集内容接口实现
 * </p>
 *
 * @package: com.xkcoding.shiny.service.impl
 * @description： 采集内容接口实现
 * @author: yangkai.shen
 * @date: Created in 2018/8/27 下午9:50
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
@Slf4j
public class SpiderContentServiceImpl implements ISpiderContentService {
	private final SpiderContentMapper spiderContentMapper;

	private final SpiderConfigMapper spiderConfigMapper;

	private final ModelMapper modelMapper;

	@Autowired
	public SpiderContentServiceImpl(SpiderContentMapper spiderContentMapper, SpiderConfigMapper spiderConfigMapper, ModelMapper modelMapper) {
		this.spiderContentMapper = spiderContentMapper;
		this.spiderConfigMapper = spiderConfigMapper;
		this.modelMapper = modelMapper;
	}

	/**
	 * 删除今天采集的所有软件信息
	 */
	@Override
	public void deleteAllToday() {
		SpiderContentDO query = SpiderContentDO.builder().spiderTime(DateUtil.parseDate(DateUtil.today())).build();
		int delete = spiderContentMapper.delete(query);
		log.info("【删除采集内容】已删除 {} 条采集日期为 {} 的软件信息", delete, DateUtil.parseDate(DateUtil.today()));
	}

	/**
	 * 删除今天采集的某个软件信息
	 *
	 * @param configId 采集配置 id
	 */
	@Override
	public void deleteToday(Integer configId) {
		SpiderContentDO query = SpiderContentDO.builder().configId(configId).spiderTime(DateUtil.parseDate(DateUtil.today())).build();
		int delete = spiderContentMapper.delete(query);
		log.info("【删除采集内容】已删除 {} 条，配置 id 为 {} 并且采集日期为 {} 的软件信息", delete, configId, DateUtil.parseDate(DateUtil.today()));
	}

	/**
	 * 查看单个软件的采集信息
	 *
	 * @param configId 配置id
	 * @param query    查询条件
	 * @return 分页信息
	 * @throws ShinyException 采集配置不存在
	 */
	@Override
	public PageResult<SpiderContentVO> getSpiderContent(Integer configId, SpiderContentPageQuery query) throws ShinyException {
		// 判断配置是否存在
		SpiderConfigDO exist = spiderConfigMapper.selectByPrimaryKey(configId);
		if (ObjectUtil.isNull(exist)) {
			throw new ShinyException(Status.CONFIG_NOT_EXIST);
		}

		// 分页
		PageHelper.startPage(query.getCurrentPage(), query.getPageSize());

		// 查询采集信息
		List<SpiderContentDO> spiderContentDOList = spiderContentMapper.selectSingleSpiderContent(configId, query);
		Long total = ((Page) spiderContentDOList).getTotal();

		// DO -> VO
		List<SpiderContentVO> spiderContentVOList = spiderContentDOList.stream().map(spiderContentDO -> modelMapper.map(spiderContentDO, SpiderContentVO.class)).collect(Collectors.toList());
		return new PageResult<>(total, spiderContentVOList);
	}

}
