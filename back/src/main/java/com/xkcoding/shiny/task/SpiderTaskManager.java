package com.xkcoding.shiny.task;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 采集任务管理器
 * </p>
 *
 * @package: com.xkcoding.shiny.task
 * @description： 采集任务管理器
 * @author: yangkai.shen
 * @date: Created in 2018/8/22 下午9:46
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class SpiderTaskManager {
	/**
	 * 采集操作延时
	 */
	private static final int SPIDER_DELAY_TIME = 2000;

	/**
	 * 异步操作采集的线程池
	 */
	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50, new BasicThreadFactory.Builder().namingPattern("Spider-Thread-%d").build());

	private SpiderTaskManager() {
	}

	private static SpiderTaskManager manager = new SpiderTaskManager();

	public static SpiderTaskManager me() {
		return manager;
	}

	public void execute(TimerTask task) {
		executor.schedule(task, SPIDER_DELAY_TIME, TimeUnit.MILLISECONDS);
	}
}
