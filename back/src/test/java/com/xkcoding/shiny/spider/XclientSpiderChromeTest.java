package com.xkcoding.shiny.spider;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.xkcoding.shiny.ShinyApplicationTests;
import com.xkcoding.shiny.model.SpiderContentDO;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.xsoup.Xsoup;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * xclient.info 采集测试
 * </p>
 *
 * @package: com.xkcoding.shiny.spider
 * @description： xclient.info 采集测试
 * @author: yangkai.shen
 * @date: Created in 2018/8/24 下午6:42
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class XclientSpiderChromeTest extends ShinyApplicationTests {

	@Test
	public void test() {
		long start = System.currentTimeMillis();
		List<SpiderContentDO> data = Lists.newArrayList();

		String url = "http://xclient.info/s/things.html";
		// String url = "http://xclient.info/s/parallels-desktop.html";
		// String url = "http://xclient.info/s/mweb.html";
		System.setProperty("webdriver.chrome.driver", "/Users/yangkai.shen/Desktop/chromedriver"); // 此处PATH替换为你的chromedriver所在路径
		WebDriver driver = new ChromeDriver();
		// 已访问的 TAB 集合，用户存放已经访问过的 TAB
		Set<String> windowSet = Sets.newHashSet();

		// 让浏览器访问 xclient.info
		driver.get(url + "?t=" + RandomUtil.simpleUUID());

		// 软件首页 TAB
		String oldWindow = driver.getWindowHandle();
		windowSet.addAll(driver.getWindowHandles());

		// 软件名
		String title = driver.findElement(By.cssSelector("h1.post-title")).getText();

		// summary
		String summary = driver.findElement(By.cssSelector("div.post-summary > p")).getText();

		try {
			Document document = Jsoup.parse(driver.getPageSource());
			String versionsHTML = Xsoup.compile("//*[@id=\"versions\"]/table").evaluate(document).get();
			Document versionsDocument = Jsoup.parse(versionsHTML);
			Elements trElements = versionsDocument.select("table").select("tbody").select("tr");

			List<WebElement> trs = driver.findElements(By.cssSelector("#versions > table > tbody > tr"));
			for (int i = 0; i < trs.size(); i++) {
				Elements tdElement = trElements.get(i).select("td");

				String version = tdElement.get(0).text();
				String language = tdElement.get(1).text();
				String date = tdElement.get(2).text();
				String size = tdElement.get(3).text();
				SpiderContentDO trData = SpiderContentDO.builder().title(title).content(summary).language(language).updateTime(date).size(size).build();
				List<WebElement> tds = trs.get(i).findElements(By.cssSelector("td"));
				List<WebElement> linkElement = tds.get(4).findElements(By.cssSelector("a"));
				for (WebElement link : linkElement) {
					String linkText = link.getText();
					if (StrUtil.containsIgnoreCase(linkText, "城通网盘")) {
						processLink(false, data, driver, windowSet, oldWindow, trData, link);
					} else if (StrUtil.containsIgnoreCase(linkText, "百度云盘")) {
						processLink(true, data, driver, windowSet, oldWindow, trData, link);
					}
				}
				data.add(trData);
				log.info("软件：{} 版本：{} 语言：{} 更新时间：{} 软件大小：{} 城通：{} 百度：{}", title, version, language, date, size, trData.getCtPanUrl(), trData.getBdPanUrl());
			}
		} catch (NoSuchElementException e) {
			log.error("{}，暂无版本信息", title);
		} catch (Exception e) {
			log.error("采集发生异常，", e);
		} finally {
			driver.quit();
		}

		long end = System.currentTimeMillis();

		System.out.println("耗时：" + (end - start) / 1000 + " 秒");

		log.info(JSONUtil.toJsonStr(data));
	}

	public void processLink(Boolean isBD, List<SpiderContentDO> data, WebDriver driver, Set<String> windowSet, String oldWindow, SpiderContentDO trData, WebElement link) throws InterruptedException {
		link.click();
		Thread.sleep(1000);
		// 浏览器所有 TAB
		Set<String> windowHandles = driver.getWindowHandles();
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
		Document document = Jsoup.parse(driver.getPageSource());
		if (isBD) {
			String bdPan = Xsoup.compile("//a[contains(@class, 'btn_down_link')]/@data-link").evaluate(document).get();
			String bdKey = Xsoup.compile("//a[contains(@class, 'btn_down_link')]//@data-clipboard-text").evaluate(document).get();
			trData.setBdPanUrl(bdPan);
			trData.setBdPanCode(bdKey);
		} else {
			String ctPan = Xsoup.compile("//a[contains(@class, 'btn_down_link')]/@href").evaluate(document).get();
			trData.setCtPanUrl(ctPan);
		}
		Thread.sleep(1000);
		if (windowHandles.size() > 1) {
			driver.close();
		}
		// 回到原来的 TAB 页
		driver.switchTo().window(oldWindow);
	}
}
