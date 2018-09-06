package com.xkcoding.shiny.service;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * <p>
 * 邮件接口
 * </p>
 *
 * @package: com.xkcoding.shiny.service
 * @description： 邮件接口
 * @author: yangkai.shen
 * @date: Created in 2018/9/5 下午10:37
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface IMailService {

	/**
	 * 发送简单邮件
	 *
	 * @param to      收件人
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 */
	void sendSimpleMail(String to, String subject, String content);

	/**
	 * 发送简单 HTML 邮件
	 *
	 * @param to      收件人
	 * @param subject 邮件主题
	 * @param html    HTML 内容
	 */
	void sendHtmlMail(String to, String subject, String html);

	/**
	 * 发送 HTML 模板邮件
	 *
	 * @param to           收件人
	 * @param subject      邮件主题
	 * @param params       模板参数
	 * @param templatePath 模板路径
	 * @param templateName 模板名称
	 */
	void sendHtmlTemplateMail(String to, String subject, Map<String, Object> params, String templatePath, String templateName) throws MessagingException;

	/**
	 * 发送附件邮件
	 *
	 * @param to       收件人
	 * @param subject  邮件主题
	 * @param content  邮件内容
	 * @param filePath 附件路径
	 */
	void sendAttachmentsMail(String to, String subject, String content, String filePath);

	/**
	 * 发送静态资源邮件
	 *
	 * @param to      收件人
	 * @param subject 邮件主题
	 * @param content 邮件内容
	 * @param rscPath 静态资源路径
	 * @param rscId   静态资源 id
	 */
	void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
