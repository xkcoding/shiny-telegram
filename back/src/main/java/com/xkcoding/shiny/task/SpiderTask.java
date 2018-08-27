package com.xkcoding.shiny.task;

import cn.hutool.core.collection.CollUtil;
import com.xkcoding.shiny.common.property.ShinyProperties;
import com.xkcoding.shiny.mapper.SpiderConfigMapper;
import com.xkcoding.shiny.model.SpiderConfigDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * 定时采集任务
 * </p>
 *
 * @package: com.xkcoding.shiny.task
 * @description： 定时采集任务
 * @author: yangkai.shen
 * @date: Created in 2018/8/22 下午9:58
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
@Slf4j
public class SpiderTask {
	@Autowired
	private SpiderConfigMapper spiderConfigMapper;

	@Autowired
	private ShinyProperties shinyProperties;

	/**
	 * 一小时执行一次
	 * TODO：每天凌晨执行一次
	 */
	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void spider() throws InterruptedException {
		log.info("【定时任务】开始采集软件信息......");
		// 获取所有软件名称-采集页面配置
		List<SpiderConfigDO> configDOList = spiderConfigMapper.selectAll();
		// 同时启动浏览器的熟练
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

		log.info("【定时任务】采集软件信息，全部任务采集完成");
	}

	/**
	 * 每天9点定时检测是否存在新版本
	 */
	@Scheduled(cron = "0 0 9 1/1 1/1 ?")
	public void email() {
		log.info("【定时任务】开始检测是否存在新版本......");
		// 检测是否存在最近2天的软件新版本
		// 发邮件
		log.info("【定时任务】检测是否存在新版本，检测完成");
	}
}
