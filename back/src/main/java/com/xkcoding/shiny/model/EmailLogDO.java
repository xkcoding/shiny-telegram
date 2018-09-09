package com.xkcoding.shiny.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>
 * 邮件日志记录 DO
 * </p>
 *
 * @package: com.xkcoding.shiny.model
 * @description: 邮件日志记录 DO
 * @author: yangkai.shen
 * @date: Created in 2018/9/9 下午2:56
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Data
@Table(name = "email_log")
public class EmailLogDO {
	/**
	 * 邮件主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "JDBC")
	private Integer id;

	/**
	 * 邮件主题
	 */
	@Column(name = "subject")
	private String subject;

	/**
	 * 是否是模板邮件（0否 1是）
	 */
	@Column(name = "is_template")
	private Integer isTemplate;

	/**
	 * 模板路径
	 */
	@Column(name = "template_path")
	private String templatePath;

	/**
	 * 模板名称
	 */
	@Column(name = "template_name")
	private String templateName;

	/**
	 * 邮件发送时间
	 */
	@Column(name = "send_time")
	private Date sendTime;

	/**
	 * 收件人邮箱地址（多个逗号分隔）
	 */
	@Column(name = "to")
	private String to;

	/**
	 * 邮件内容
	 */
	@Column(name = "content")
	private String content;

	/**
	 * 获取邮件主键
	 *
	 * @return id - 邮件主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置邮件主键
	 *
	 * @param id 邮件主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取邮件主题
	 *
	 * @return subject - 邮件主题
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置邮件主题
	 *
	 * @param subject 邮件主题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 获取是否是模板邮件（0否 1是）
	 *
	 * @return is_template - 是否是模板邮件（0否 1是）
	 */
	public Integer getIsTemplate() {
		return isTemplate;
	}

	/**
	 * 设置是否是模板邮件（0否 1是）
	 *
	 * @param isTemplate 是否是模板邮件（0否 1是）
	 */
	public void setIsTemplate(Integer isTemplate) {
		this.isTemplate = isTemplate;
	}

	/**
	 * 获取模板路径
	 *
	 * @return template_path - 模板路径
	 */
	public String getTemplatePath() {
		return templatePath;
	}

	/**
	 * 设置模板路径
	 *
	 * @param templatePath 模板路径
	 */
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	/**
	 * 获取模板名称
	 *
	 * @return template_name - 模板名称
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 设置模板名称
	 *
	 * @param templateName 模板名称
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * 获取邮件发送时间
	 *
	 * @return send_time - 邮件发送时间
	 */
	public Date getSendTime() {
		return sendTime;
	}

	/**
	 * 设置邮件发送时间
	 *
	 * @param sendTime 邮件发送时间
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * 获取收件人邮箱地址（多个逗号分隔）
	 *
	 * @return to - 收件人邮箱地址（多个逗号分隔）
	 */
	public String getTo() {
		return to;
	}

	/**
	 * 设置收件人邮箱地址（多个逗号分隔）
	 *
	 * @param to 收件人邮箱地址（多个逗号分隔）
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * 获取邮件内容
	 *
	 * @return content - 邮件内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置邮件内容
	 *
	 * @param content 邮件内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
}