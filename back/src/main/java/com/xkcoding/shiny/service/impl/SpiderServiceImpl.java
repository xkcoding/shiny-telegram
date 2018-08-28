package com.xkcoding.shiny.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.xkcoding.shiny.common.property.ShinyProperties;
import com.xkcoding.shiny.mapper.SpiderConfigMapper;
import com.xkcoding.shiny.model.SpiderConfigDO;
import com.xkcoding.shiny.service.ISpiderService;
import com.xkcoding.shiny.task.SpiderTaskFactory;
import com.xkcoding.shiny.task.SpiderTaskManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * 爬虫接口实现
 * </p>
 *
 * @package: com.xkcoding.shiny.service.impl
 * @description： 爬虫接口实现
 * @author: yangkai.shen
 * @date: Created in 2018/8/27 下午6:36
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
@Slf4j
public class SpiderServiceImpl implements ISpiderService {
	private final SpiderConfigMapper spiderConfigMapper;

	private final ShinyProperties shinyProperties;

	@Autowired
	public SpiderServiceImpl(SpiderConfigMapper spiderConfigMapper, ShinyProperties shinyProperties) {
		this.spiderConfigMapper = spiderConfigMapper;
		this.shinyProperties = shinyProperties;
	}

	/**
	 * 根据配置列表采集信息
	 *
	 * @param configDOList 采集配置列表
	 */
	@Override
	public void spider(List<SpiderConfigDO> configDOList) throws InterruptedException {
		log.info("【采集任务】开始采集软件信息......");
		// 按照同时启动浏览器的数量分隔列表
		List<List<SpiderConfigDO>> spiderList = CollUtil.split(configDOList, shinyProperties.getSpiderNum());
		for (List<SpiderConfigDO> spider : spiderList) {
			// 初始化任务数量
			CountDownLatch lock = new CountDownLatch(spider.size());
			// 遍历，每个页面调取线程采集
			for (SpiderConfigDO spiderConfigDO : spider) {
				SpiderTaskManager.me().execute(SpiderTaskFactory.spiderTaskWithLock(spiderConfigDO, lock));
			}

			// 加锁，等待所有任务完成
			lock.await();
		}
		log.info("【采集任务】采集软件信息，全部任务采集完成");
	}

	/**
	 * 重新爬取今天采集的所有软件信息
	 */
	@Async("asyncExecutor")
	@Override
	public void reSpiderAllToday() throws InterruptedException {
		log.info("【异步任务】开始重新采集所有软件信息......");
		// 获取所有软件名称-采集页面配置
		List<SpiderConfigDO> configDOList = spiderConfigMapper.selectAll();

		if (CollUtil.isNotEmpty(configDOList)) {
			spider(configDOList);
		}
	}

	/**
	 * 重新爬取今天采集的某个软件信息
	 *
	 * @param configId 采集配置id
	 */
	@Async("asyncExecutor")
	@Override
	public void reSpiderToday(Integer configId) throws InterruptedException {
		log.info("【异步任务】开始重新采集配置 id 为 {} 的软件信息......", configId);
		// 获取采集配置id的软件名称-采集页面配置
		SpiderConfigDO query = SpiderConfigDO.builder().id(configId).build();
		List<SpiderConfigDO> configDOList = spiderConfigMapper.select(query);

		if (CollUtil.isNotEmpty(configDOList)) {
			spider(configDOList);
		}
	}
}
