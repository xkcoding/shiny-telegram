package com.xkcoding.shiny.task;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 采集任务工厂
 * </p>
 *
 * @package: com.xkcoding.shiny.task
 * @description： 采集任务工厂
 * @author: yangkai.shen
 * @date: Created in 2018/8/22 下午9:55
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class SpiderTaskFactory {

	public static TimerTask spider(CountDownLatch lock) {
		return new TimerTask() {
			@Override
			public void run() {
				log.info("开始采集，当前时间：{}", DateUtil.now());
				// 模拟采集任务
				try {
					TimeUnit.MILLISECONDS.sleep(RandomUtil.randomLong(1000, 10000));
				} catch (InterruptedException e) {
					log.error("【采集】出现异常", e);
				} finally {
					log.info("采集完成，当前时间：{}", DateUtil.now());
					// 释放计数器
					lock.countDown();
				}
			}
		};
	}
}
