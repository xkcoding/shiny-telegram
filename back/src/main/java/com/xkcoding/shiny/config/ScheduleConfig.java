package com.xkcoding.shiny.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * <p>
 * 定时任务配置
 * </p>
 *
 * @package: com.xkcoding.shiny.config
 * @description： 定时任务配置
 * @author: yangkai.shen
 * @date: Created in 2018/8/22 下午9:59
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.xkcoding.shiny.task")
public class ScheduleConfig implements SchedulingConfigurer {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}

	@Bean
	public Executor taskExecutor() {
		return new ScheduledThreadPoolExecutor(50, new BasicThreadFactory.Builder().namingPattern("Schedule-Thread-%d").build());
	}
}
