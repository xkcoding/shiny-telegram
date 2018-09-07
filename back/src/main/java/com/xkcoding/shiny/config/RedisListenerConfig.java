package com.xkcoding.shiny.config;

import com.xkcoding.shiny.common.ShinyConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * Redis 发布/订阅配置
 * </p>
 *
 * @package: com.xkcoding.shiny.config
 * @description: Redis 发布/订阅配置
 * @author: yangkai.shen
 * @date: Created in 2018/9/6 下午4:15
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Configuration
@Slf4j
public class RedisListenerConfig {

	/**
	 * 初始化监听器
	 *
	 * @param connectionFactory 连接工厂
	 * @param listenerAdapter   监听器
	 * @return 监听器容器
	 */
	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		log.info("【Redis】启动监听......");
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic(ShinyConst.MAIL_CHANNEL));
		return container;
	}

	/**
	 * 利用反射来创建监听到消息之后的执行方法
	 *
	 * @param receiver 接收到消息的对象
	 * @return {@link MessageListenerAdapter}
	 */
	@Bean
	public MessageListenerAdapter listenerAdapter(RedisReceiverConfig receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	@Bean
	public RedisReceiverConfig receiver(CountDownLatch latch) {
		return new RedisReceiverConfig(latch);
	}

	@Bean
	public CountDownLatch latch() {
		return new CountDownLatch(1);
	}

	/**
	 * 使用默认的工厂初始化redis操作模板
	 *
	 * @param connectionFactory 连接工厂
	 * @return {@link StringRedisTemplate}
	 */
	@Bean
	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}

}