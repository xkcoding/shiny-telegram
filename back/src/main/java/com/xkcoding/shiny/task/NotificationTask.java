package com.xkcoding.shiny.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.xkcoding.shiny.common.ShinyConst;
import com.xkcoding.shiny.model.SpiderContentDO;
import com.xkcoding.shiny.service.ISpiderContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 通知任务
 * </p>
 *
 * @package: com.xkcoding.shiny.task
 * @description： 通知任务
 * @author: yangkai.shen
 * @date: Created in 2018/9/4 下午8:18
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
@Slf4j
public class NotificationTask {
	private final ISpiderContentService spiderContentService;

	private final StringRedisTemplate stringRedisTemplate;

	@Autowired
	public NotificationTask(ISpiderContentService spiderContentService, StringRedisTemplate stringRedisTemplate) {
		this.spiderContentService = spiderContentService;
		this.stringRedisTemplate = stringRedisTemplate;
	}

	/**
	 * 每天早晨9点执行一次
	 */
	@Scheduled(fixedDelay = 60 * 1000)
//	@Scheduled(cron = "0 0 9 1/1 1/1 ?")
	public void notification() {
		log.info("【定时任务】检查今天采集的软件版本更新情况......");
		boolean hasUpdate = checkContent();
		log.info("【定时任务】今天采集的软件版本更新情况，检查完成，{}", hasUpdate ? "软件有更新，邮件已发送" : "软件近两天无更新");
	}

	/**
	 * 检查采集内容
	 *
	 * @return 是否存在更新
	 */
	private boolean checkContent() {
		List<SpiderContentDO> spiderContentDOList = spiderContentService.listLatestSoftware();
		if (CollUtil.isNotEmpty(spiderContentDOList)) {
			stringRedisTemplate.convertAndSend(ShinyConst.MAIL_CHANNEL, JSONUtil.toJsonStr(spiderContentDOList));
			return true;
		}
		return false;
	}
}
