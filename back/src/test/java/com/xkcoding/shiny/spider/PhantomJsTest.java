package com.xkcoding.shiny.spider;

import com.xkcoding.shiny.ShinyApplicationTests;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * <p>
 * 测试 PhantomJs <br>
 * PhantomJs是一个基于webkit内核的无头浏览器，即没有UI界面，即它就是一个浏览器，只是其内的点击、翻页等人为相关操作需要程序设计实现;
 * 因为爬虫如果每次爬取都调用一次谷歌浏览器来实现操作,在性能上会有一定影响,而且连续开启十几个浏览器简直是内存噩梦,
 * 因此选用phantomJs来替换chromeDriver
 * PhantomJs在本地开发时候还好，如果要部署到服务器，就必须下载linux版本的PhantomJs,相比window操作繁琐
 * </p>
 *
 * @package: com.xkcoding.shiny.spider
 * @description： 测试 PhantomJs
 * @author: yangkai.shen
 * @date: Created in 2018/8/21 下午10:38
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class PhantomJsTest extends ShinyApplicationTests {

	private PhantomJSDriver getPhantomJSDriver() {
		//设置必要参数
		DesiredCapabilities dcaps = new DesiredCapabilities();
		//ssl证书支持
		dcaps.setCapability("acceptSslCerts", true);
		//截屏支持
		dcaps.setCapability("takesScreenshot", false);
		//css搜索支持
		dcaps.setCapability("cssSelectorsEnabled", true);
		//js支持
		dcaps.setJavascriptEnabled(true);
		//驱动支持
		dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "D:\\chromedriver\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");

		PhantomJSDriver driver = new PhantomJSDriver(dcaps);
		return driver;
	}

	@Test
	public void test() {
		WebDriver driver = getPhantomJSDriver();
		driver.get("http://www.baidu.com");
		System.out.println(driver.getCurrentUrl());
		driver.quit();
	}
}
