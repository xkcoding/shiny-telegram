package com.xkcoding.shiny.spider;

import cn.hutool.core.util.RandomUtil;
import com.xkcoding.shiny.ShinyApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.xsoup.Xsoup;

/**
 * <p>
 * 测试 ChromeDriver<br>
 * chromeDriver是谷歌的浏览器驱动，用来适配Selenium,有图形页面存在，在调试爬虫下载运行的功能的时候会相对方便
 * </p>
 *
 * @package: com.xkcoding.shiny.spider
 * @description： 测试 ChromeDriver
 * @author: yangkai.shen
 * @date: Created in 2018/8/21 下午8:08
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class ChromeDriverTest extends ShinyApplicationTests {

	@Test
	public void testThings() {
		System.setProperty("webdriver.chrome.driver", "/Users/yangkai.shen/Desktop/chromedriver"); // 此处PATH替换为你的chromedriver所在路径
		WebDriver driver = new ChromeDriver();
		// 让浏览器访问 xclient.info
		driver.get("http://xclient.info/s/things.html?t=" + RandomUtil.simpleUUID());
		// 用下面代码也可以实现
		//driver.navigate().to("http://www.baidu.com");
		// 获取 网页的 title
		System.out.println(" Page title is: " + driver.getTitle());
		Document document = Jsoup.parse(driver.getPageSource());

		String summary = Xsoup.compile("//*[@id=\"main\"]/div/article/div[3]/p/text()").evaluate(document).get();
		log.debug("【summary】: {}", summary);
		String postContentHtml = Xsoup.compile("//*[@id=\"post-content\"]").evaluate(document).get();
		log.debug("【content】: {}", postContentHtml);

		String versionsHTML = Xsoup.compile("//*[@id=\"versions\"]/table").evaluate(document).get();
		log.debug("【versions】: {}", versionsHTML);
		Document versionsDocument = Jsoup.parse(versionsHTML);
		Elements trs = versionsDocument.select("table").select("tbody").select("tr");

		WebElement element = driver.findElement(By.xpath("//*[@id=\"versions\"]/table/tbody/tr[1]/td[5]/a[2]"));
		element.click();
		log.debug(driver.getCurrentUrl());
		Document bdDocument = Jsoup.parse(driver.getPageSource());
		String bdLink = Xsoup.compile("//*[@id=\"body\"]/div[1]/div[2]/a/@data-link").evaluate(bdDocument).get();
		String bdKey = Xsoup.compile("//*[@id=\"body\"]/div[1]/div[2]/a/@data-clipboard-text").evaluate(bdDocument).get();
		log.debug("【百度】{}【提取码】{}", bdLink, bdKey);

//		for (Element tr : trs) {
//			Elements tds = tr.select("td");
//
//			String version = tds.get(0).text();
//			String language = tds.get(1).text();
//			String date = tds.get(2).text();
//			String size = tds.get(3).text();
//			Element linkElement = tds.get(4);
//			Elements downloadElements = linkElement.select(".btn-download");
//			for (int k = 0; k < downloadElements.size(); k++) {
//				if (StrUtil.containsIgnoreCase(downloadElements.get(k).text(), "城通网盘")) {
//					newDriver.get(downloadElements.get(k).attr("href"));
//					Document ctDocument = Jsoup.parse(newDriver.getPageSource());
//					String ctLink = Xsoup.compile("//*[@id=\"body\"]/div[1]/div[2]/a/@href").evaluate(ctDocument).get();
//					log.debug("【城通】{}", ctLink);
//				} else if (StrUtil.containsIgnoreCase(downloadElements.get(k).text(), "百度云盘")) {
//					newDriver.get(downloadElements.get(k).attr("href"));
//					Document bdDocument = Jsoup.parse(newDriver.getPageSource());
//					String bdLink = Xsoup.compile("//*[@id=\"body\"]/div[1]/div[2]/a/@data-link").evaluate(bdDocument).get();
//					String bdKey = Xsoup.compile("//*[@id=\"body\"]/div[1]/div[2]/a/@data-clipboard-text").evaluate(bdDocument).get();
//					log.debug("【百度】{}【提取码】{}", bdLink, bdKey);
//				}
//			}
//			log.info("【版本】{}，【语言】{}，【更新日期】{}，【文件大小】{}，【城通】{}，【百度】{}", version, language, date, size, null, null);
//		}

		// 关闭浏览器
		driver.quit();
	}
}
