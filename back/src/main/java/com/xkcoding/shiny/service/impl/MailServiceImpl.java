package com.xkcoding.shiny.service.impl;

import cn.hutool.extra.template.engine.beetl.BeetlUtil;
import com.xkcoding.shiny.common.property.ShinyProperties;
import com.xkcoding.shiny.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.beetl.core.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * <p>
 * 邮件接口实现
 * </p>
 *
 * @package: com.xkcoding.shiny.service.impl
 * @description: 邮件接口实现
 * @author: yangkai.shen
 * @date: Created in 2018/9/6 下午8:56
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Service
@Slf4j
public class MailServiceImpl implements IMailService {
	private final JavaMailSender mailSender;

	private final ShinyProperties shinyProperties;

	@Autowired
	public MailServiceImpl(JavaMailSender mailSender, ShinyProperties shinyProperties) {
		this.mailSender = mailSender;
		this.shinyProperties = shinyProperties;
	}

	/**
	 * 发送简单邮件
	 *
	 * @param to      收件人
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	@Override
	public void sendSimpleMail(String to, String subject, String content) {

	}

	/**
	 * 发送简单 HTML 邮件
	 *
	 * @param to      收件人
	 * @param subject 邮件主题
	 * @param html    HTML 内容
	 */
	@Override
	public void sendHtmlMail(String to, String subject, String html) {

	}

	/**
	 * 发送 HTML 模板邮件
	 *
	 * @param to           收件人
	 * @param subject      邮件主题
	 * @param params       模板参数
	 * @param templatePath 模板路径
	 * @param templateName 模板名称
	 */
	@Override
	public void sendHtmlTemplateMail(String to, String subject, Map<String, Object> params, String templatePath, String templateName) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(shinyProperties.getMailFrom());
		helper.setTo(to);
		helper.setSubject(subject);
		Template template = BeetlUtil.getClassPathTemplate(templatePath, templateName);
		//渲染模板
		String result = BeetlUtil.render(template, params);
		helper.setText(result, true);
		mailSender.send(message);
	}

	/**
	 * 发送附件邮件
	 *
	 * @param to       收件人
	 * @param subject  邮件主题
	 * @param content  邮件内容
	 * @param filePath 附件路径
	 */
	@Override
	public void sendAttachmentsMail(String to, String subject, String content, String filePath) {

	}

	/**
	 * 发送静态资源邮件
	 *
	 * @param to      收件人
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param rscPath 静态资源路径
	 * @param rscId   静态资源 id
	 */
	@Override
	public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId) {

	}
}
