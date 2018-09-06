package com.xkcoding.shiny.service.impl;

import cn.hutool.core.lang.Dict;
import com.xkcoding.shiny.ShinyApplicationTests;
import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.model.query.SpiderContentPageQuery;
import com.xkcoding.shiny.model.vo.SpiderContentVO;
import com.xkcoding.shiny.service.IMailService;
import com.xkcoding.shiny.service.ISpiderContentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.util.List;

/**
 * <p>
 * 邮件发送测试类
 * </p>
 *
 * @package: com.xkcoding.shiny.service.impl
 * @description: 邮件发送测试类
 * @author: yangkai.shen
 * @date: Created in 2018/9/6 下午9:06
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class MailServiceImplTest extends ShinyApplicationTests {
	@Autowired
	private IMailService mailService;

	@Autowired
	private ISpiderContentService spiderContentService;

	@Test
	public void sendHtmlTemplateMail() throws MessagingException {
		SpiderContentPageQuery query = new SpiderContentPageQuery();
		query.setCurrentPage(1);
		query.setPageSize(10);
		PageResult<SpiderContentVO> pageResult = spiderContentService.listSpiderContent(query);
		List<SpiderContentVO> list = pageResult.getList();
		mailService.sendHtmlTemplateMail("237497819@qq.com", "软件更新通知", Dict.create().set("trDataList", list), "email", "software-update.tag");
	}
}