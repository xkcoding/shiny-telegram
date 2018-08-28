package com.xkcoding.shiny.service.impl;

import cn.hutool.core.date.DateUtil;
import com.xkcoding.shiny.mapper.SpiderContentMapper;
import com.xkcoding.shiny.model.SpiderContentDO;
import com.xkcoding.shiny.service.ISpiderContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	public SpiderContentServiceImpl(SpiderContentMapper spiderContentMapper) {
		this.spiderContentMapper = spiderContentMapper;
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
}
