package com.xkcoding.shiny.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * <p>
 * 异步任务配置
 * </p>
 *
 * @package: com.xkcoding.shiny.config
 * @description： 异步任务配置
 * @author: yangkai.shen
 * @date: Created in 2018/8/27 下午6:41
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
@EnableAsync
public class AsyncConfig {

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(200);
		executor.setMaxPoolSize(250);
		executor.setQueueCapacity(50);
		executor.setThreadNamePrefix("Async-Thread-");
		executor.initialize();
		return executor;
	}

}
