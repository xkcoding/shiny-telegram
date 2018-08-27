package com.xkcoding.shiny.task;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.xkcoding.shiny.mapper.SpiderContentMapper;
import com.xkcoding.shiny.model.SpiderConfigDO;
import com.xkcoding.shiny.model.SpiderContentDO;
import com.xkcoding.shiny.util.ChromeDriverUtil;
import com.xkcoding.shiny.util.PhantomJsUtil;
import com.xkcoding.shiny.util.SpringContextHolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import us.codecraft.xsoup.Xsoup;

import java.util.List;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 采集任务工厂
 * </p>
 *
 * @package: com.xkcoding.shiny.task
 * @description： 采集任务工厂
 * @author: yangkai.shen
 * @date: Created in 2018/8/22 下午9:55
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class SpiderTaskFactory {
	private static SpiderContentMapper spiderContentMapper = SpringContextHolderUtil.getBean(SpiderContentMapper.class);

	/**
	 * 爬虫采集任务加锁
	 *
	 * @param spiderConfigDO 爬虫配置
	 * @param lock           锁
	 * @return 采集任务
	 */
	public static TimerTask spiderTaskWithLock(SpiderConfigDO spiderConfigDO, CountDownLatch lock) {
		return new TimerTask() {
			@Override
			public void run() {
				log.info("开始采集，当前时间：{}", DateUtil.now());
				try {
					TimeUnit.SECONDS.sleep(RandomUtil.randomInt(3, 20));
//					WebDriver driver = PhantomJsUtil.getPhantomJSDriver();
					WebDriver driver = ChromeDriverUtil.getChromeDriver();
					List<SpiderContentDO> spiderContentDOList = executeSpider(driver, spiderConfigDO);
					if (CollUtil.isNotEmpty(spiderContentDOList)) {
						spiderContentMapper.insertList(spiderContentDOList);
					}
				} catch (Exception e) {
					log.error("【采集】出现异常", e);
				} finally {
					log.info("采集完成，当前时间：{}", DateUtil.now());
					// 释放计数器
					lock.countDown();
				}
			}
		};
	}

	/**
	 * 爬虫采集任务
	 *
	 * @param spiderConfigDO 爬虫配置
	 * @return 采集任务
	 */
	public static TimerTask spiderTask(SpiderConfigDO spiderConfigDO) {
		return new TimerTask() {
			@Override
			public void run() {
				log.info("开始采集，当前时间：{}", DateUtil.now());
				try {
					WebDriver driver = PhantomJsUtil.getPhantomJSDriver();
					List<SpiderContentDO> spiderContentDOList = executeSpider(driver, spiderConfigDO);
					if (CollUtil.isNotEmpty(spiderContentDOList)) {
						spiderContentMapper.insertList(spiderContentDOList);
					}
				} catch (Exception e) {
					log.error("【采集】出现异常", e);
				} finally {
					log.info("采集完成，当前时间：{}", DateUtil.now());
				}
			}
		};
	}

	/**
	 * 处理爬虫
	 *
	 * @param driver         selenium 浏览器驱动
	 * @param spiderConfigDO 爬虫配置 DO
	 * @return 爬取的内容
	 */
	public static List<SpiderContentDO> executeSpider(WebDriver driver, SpiderConfigDO spiderConfigDO) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		List<SpiderContentDO> data = Lists.newArrayList();

		// 已访问的 TAB 集合，用户存放已经访问过的 TAB
		Set<String> windowSet = Sets.newHashSet();

		// 让浏览器访问 xclient.info
		driver.get(spiderConfigDO.getSpiderUrl() + "?t=" + RandomUtil.simpleUUID());
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
			// 如果存在版本信息
			if (StrUtil.isNotBlank(versionsHTML)) {
				Document versionsDocument = Jsoup.parse(versionsHTML);
				Elements trElements = versionsDocument.select("table").select("tbody").select("tr");

				List<WebElement> trs = driver.findElements(By.cssSelector("#versions > table > tbody > tr"));
				for (int i = 0; i < trs.size(); i++) {
					Elements tdElement = trElements.get(i).select("td");

					String version = tdElement.get(0).text();
					String language = tdElement.get(1).text();
					String date = tdElement.get(2).text();
					String size = tdElement.get(3).text();
					SpiderContentDO trData = SpiderContentDO.builder().configId(spiderConfigDO.getId()).title(title).content(summary).version(version).language(language).updateTime(DateUtil.parseDate(date)).size(size).spiderTime(DateUtil.parseDate(DateUtil.today())).build();
					List<WebElement> tds = trs.get(i).findElements(By.cssSelector("td"));
					List<WebElement> linkElement = tds.get(4).findElements(By.cssSelector("a"));
					for (WebElement link : linkElement) {
						String linkText = link.getText();
						if (StrUtil.containsIgnoreCase(linkText, "城通网盘")) {
							processLink(false, driver, windowSet, oldWindow, trData, link);
						} else if (StrUtil.containsIgnoreCase(linkText, "百度云盘")) {
							processLink(true, driver, windowSet, oldWindow, trData, link);
						}
					}
					data.add(trData);
					log.info("软件：{} 版本：{} 语言：{} 更新时间：{} 软件大小：{} 城通：{} 百度：{}", title, version, language, date, size, trData.getCtPanUrl(), trData.getBdPanUrl());
				}
			} else {
				SpiderContentDO trData = SpiderContentDO.builder().configId(spiderConfigDO.getId()).title(title).content(summary).version("暂无版本信息").spiderTime(DateUtil.parseDate(DateUtil.today())).build();
				data.add(trData);
			}
		} catch (NoSuchElementException e) {
			log.error("{}，暂无版本信息", title);
			SpiderContentDO trData = SpiderContentDO.builder().configId(spiderConfigDO.getId()).title(title).content(summary).version("暂无版本信息").spiderTime(DateUtil.parseDate(DateUtil.today())).build();
			data.add(trData);
		} catch (Exception e) {
			log.error("采集发生异常，", e);
			SpiderContentDO trData = SpiderContentDO.builder().configId(spiderConfigDO.getId()).title(title).content(summary).version("暂无版本信息").spiderTime(DateUtil.parseDate(DateUtil.today())).build();
			data.add(trData);
		} finally {
			TimeUnit.SECONDS.sleep(2);
			driver.quit();
		}
		return data;
	}

	/**
	 * 处理下载链接
	 *
	 * @param isBD      是否是百度云
	 * @param driver    selenium 浏览器驱动
	 * @param windowSet 已访问的 TAB 集合
	 * @param oldWindow 软件初始页面 TAB
	 * @param trData    行数据
	 * @param link      链接地址
	 */
	private static void processLink(Boolean isBD, WebDriver driver, Set<String> windowSet, String oldWindow, SpiderContentDO trData, WebElement link) throws InterruptedException {
		link.click();
		TimeUnit.SECONDS.sleep(2);
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
			// 有些百度链接是弹窗里才会显示所以废弃
			// String bdPan = Xsoup.compile("//a[contains(@class, 'btn_down_link')]/@data-link").evaluate(document).get();

			// 打开弹窗
			driver.findElement(By.xpath("//a[contains(@class, 'btn_down_link')]")).click();
			TimeUnit.SECONDS.sleep(2);
			// 获取链接
			String bdPan = Xsoup.compile("//a[contains(@class, 'go_down_btn')]/@href").evaluate(Jsoup.parse(driver.getPageSource())).get();
			bdPan = StrUtil.subBefore(bdPan, "#", true);
			String bdKey = Xsoup.compile("//a[contains(@class, 'btn_down_link')]//@data-clipboard-text").evaluate(document).get();
			trData.setBdPanUrl(bdPan);
			trData.setBdPanCode(bdKey);
		} else {
			String ctPan = Xsoup.compile("//a[contains(@class, 'btn_down_link')]/@href").evaluate(document).get();
			trData.setCtPanUrl(ctPan);
		}
		TimeUnit.SECONDS.sleep(2);
		if (windowHandles.size() > 1) {
			driver.close();
		}
		// 回到原来的 TAB 页
		driver.switchTo().window(oldWindow);
	}

}
