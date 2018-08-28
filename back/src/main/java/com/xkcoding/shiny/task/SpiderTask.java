package com.xkcoding.shiny.task;

import com.xkcoding.shiny.mapper.SpiderConfigMapper;
import com.xkcoding.shiny.model.SpiderConfigDO;
import com.xkcoding.shiny.service.ISpiderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

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
	private final SpiderConfigMapper spiderConfigMapper;

	private final ISpiderService spiderService;

	@Autowired
	public SpiderTask(SpiderConfigMapper spiderConfigMapper, ISpiderService spiderService) {
		this.spiderConfigMapper = spiderConfigMapper;
		this.spiderService = spiderService;
	}

	/**
	 * 每天凌晨2点执行一次
	 */
	@Scheduled(cron = "0 0 2 1/1 1/1 ?")
	public void spider() throws InterruptedException {
		log.info("【定时任务】开始采集软件信息......");
		// 获取所有软件名称-采集页面配置
		List<SpiderConfigDO> configDOList = spiderConfigMapper.selectAll();

		spiderService.spider(configDOList);
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
