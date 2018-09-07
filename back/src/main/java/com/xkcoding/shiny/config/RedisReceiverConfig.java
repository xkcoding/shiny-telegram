package com.xkcoding.shiny.config;

import cn.hutool.core.lang.Dict;
import cn.hutool.json.JSONUtil;
import com.xkcoding.shiny.model.SpiderContentDO;
import com.xkcoding.shiny.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * redis 接收消息接口 实现
 * </p>
 *
 * @package: com.xkcoding.shiny.service.impl
 * @description: redis 接收消息接口 实现
 * @author: yangkai.shen
 * @date: Created in 2018/9/6 下午4:20
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class RedisReceiverConfig {
	private CountDownLatch latch;

	@Autowired
	private IMailService mailService;

	@Autowired
	public RedisReceiverConfig(CountDownLatch latch) {
		this.latch = latch;
	}

	/**
	 * 接收消息执行的方法
	 *
	 * @param message 消息
	 */
	public void receiveMessage(Object message) throws MessagingException {
		log.info("【收到消息】消息内容：{}", JSONUtil.toJsonStr(message));
		mailService.sendHtmlTemplateMail("237497819@qq.com", "软件更新通知", Dict.create().set("trDataList", JSONUtil.toList(JSONUtil.parseArray(message), SpiderContentDO.class)), "email", "software-update.html");
		latch.countDown();
	}
}
