package com.xkcoding.shiny.spider;

import cn.hutool.core.util.RandomUtil;
import com.meterware.httpunit.*;
import com.xkcoding.shiny.ShinyApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * <p>
 * HttpUnit模拟浏览器
 * </p>
 *
 * @package: com.xkcoding.shiny.spider
 * @description： HttpUnit模拟浏览器
 * @author: yangkai.shen
 * @date: Created in 2018/8/16 下午7:48
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class HttpUnitTest extends ShinyApplicationTests {

	@Test
	public void testSimulateBrowser() throws IOException, SAXException {
		WebConversation wc = new WebConversation();
		// 向指定的URL发出请求
		WebRequest req = new GetMethodWebRequest("http://xclient.info/s/things.html");
		// 给请求加上参数
		req.setParameter("a", "dl");
		req.setParameter("v", "3.6.1");
		req.setParameter("k", "1");
		req.setParameter("t", RandomUtil.simpleUUID());
		// 获取响应对象
		WebResponse resp = wc.getResponse(req);
		// 用getText方法获取相应的全部内容
		// 用System.out.println将获取的内容打印在控制台上
		System.out.println(resp.getText());

	}

	@Test
	public void testThings() throws IOException, SAXException {
		WebConversation wc = new WebConversation();
		// 向指定的URL发出请求
		WebRequest req = new GetMethodWebRequest("http://xclient.info/s/things.html");
		// 给请求加上参数
		req.setParameter("t", RandomUtil.simpleUUID());
		// 获取响应对象
		WebResponse resp = wc.getResponse(req);
		// 用getText方法获取相应的全部内容
		// 用System.out.println将获取的内容打印在控制台上
		System.out.println(resp.getText());
	}

	@Test
	public void testBaidu() throws IOException, SAXException {
		System.out.println("获取页面中链接指向页面的内容：");
		// 建立一个WebConversation实例
		WebConversation wc = new WebConversation();
		// 获取响应对象
		WebResponse resp = wc.getResponse("http://www.265.com/");
		// 获得页面链接对象
		WebLink link = resp.getLinkWith("百度");
		// 模拟用户单击事件
		link.click();
		// 获得当前的响应对象
		WebResponse nextLink = wc.getCurrentPage();

		// 用getText方法获取相应的全部内容
		// 用System.out.println将获取的内容打印在控制台上
		System.out.println(nextLink.getText());
	}
}
