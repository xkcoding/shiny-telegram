package com.xkcoding.shiny.spider;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.xkcoding.shiny.ShinyApplicationTests;
import org.assertj.core.util.Sets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import us.codecraft.xsoup.Xsoup;

import java.util.Set;

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
		dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/Users/yangkai.shen/Desktop/phantomjs-2.1.1-macosx/bin/phantomjs");

		PhantomJSDriver driver = new PhantomJSDriver(dcaps);
		return driver;
	}

	@Test
	public void test() throws InterruptedException {
		WebDriver driver = getPhantomJSDriver();
		// 已访问的 TAB 集合，用户存放已经访问过的 TAB
		Set<String> windowSet = Sets.newHashSet();

		// 让浏览器访问 xclient.info
		driver.get("http://xclient.info/s/things.html?t=" + RandomUtil.simpleUUID());
		// 软件首页 TAB
		String oldWindow = driver.getWindowHandle();
		windowSet.addAll(driver.getWindowHandles());
		// 用下面代码也可以实现
		//driver.navigate().to("http://www.baidu.com");
		// 获取 网页的 title
		System.out.println(" Page title is: " + driver.getTitle());
		Document document = Jsoup.parse(driver.getPageSource());

		String summary = Xsoup.compile("//*[@id=\"main\"]/div/article/div[3]/p/text()").evaluate(document).get();
		System.err.println("【summary】: " + summary);

		WebElement element = driver.findElement(By.xpath("//*[@id=\"versions\"]/table/tbody/tr[1]/td[5]/a[2]"));
		element.click();
		Thread.sleep(1000);

		// 浏览器所有 TAB
		Set<String> windowHandles = driver.getWindowHandles();
		System.err.println(JSONUtil.toJsonStr(windowHandles));

		// 切换 TAB，只切换到未访问过的 TAB
		for (String windowHandle : windowHandles) {
			if (!windowSet.contains(windowHandle)) {
				// 切换 TAB
				driver.switchTo().window(windowHandle);
				// 已访问的 TAB 里添加一条记录
				windowSet.add(windowHandle);
				break;
			}
		}
		System.err.println(driver.getCurrentUrl());
		Document bdDocument = Jsoup.parse(driver.getPageSource());
		String bdLink = Xsoup.compile("//*[@id=\"body\"]/div[1]/div[2]/a/@data-link").evaluate(bdDocument).get();
		String bdKey = Xsoup.compile("//*[@id=\"body\"]/div[1]/div[2]/a/@data-clipboard-text").evaluate(bdDocument).get();
		System.err.println("【百度】" + bdLink + "【提取码】" + bdKey);

		// 回到原来的 TAB 页
		driver.switchTo().window(oldWindow);
		System.err.println(driver.getCurrentUrl());

		Set<String> windows = driver.getWindowHandles();
		System.err.println(JSONUtil.toJsonStr(windows));
		driver.quit();
	}
}
