package com.xkcoding.shiny.spider;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Maps;
import com.xkcoding.shiny.ShinyApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import us.codecraft.xsoup.Xsoup;

import java.util.Map;

/**
 * <p>
 * 爬虫测试
 * </p>
 *
 * @package: com.xkcoding.shiny.spider
 * @description： 爬虫测试
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午11:44
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class SpiderTest extends ShinyApplicationTests {

	@Test
	public void thingsTest() {
//		String url = "http://xclient.info/s/things.html";
		String url = "http://xclient.info/s/vmware-fusion.html";
		Map<String, Object> params = Maps.newHashMap();
		params.put("t", RandomUtil.simpleUUID());
		String html = HttpUtil.get(url, params);

		// 获取 summary
//		List<String> summaries = ReUtil.findAll("<div class=\"post-summary\">(.*?)</div>", html, 1);
//		log.debug("【summary】: {}", HtmlUtil.cleanHtmlTag(CollUtil.getFirst(summaries)));

		Document document = Jsoup.parse(html);
		String summary = Xsoup.compile("//*[@id=\"main\"]/div/article/div[3]/p/text()").evaluate(document).get();
		log.debug("【summary】: {}", summary);
		String postContentHtml = Xsoup.compile("//*[@id=\"post-content\"]").evaluate(document).get();
		log.debug("【content】: {}", postContentHtml);

		String versionsHTML = Xsoup.compile("//*[@id=\"versions\"]/table").evaluate(document).get();
		log.debug("【versions】: {}", versionsHTML);
		Document versionsDocument = Jsoup.parse(versionsHTML);
		Elements trs = versionsDocument.select("table").select("tbody").select("tr");

		for (int i = 0; i < trs.size(); i++) {
			Element tr = trs.get(i);
			Elements tds = tr.select("td");

			String version = tds.get(0).text();
			String language = tds.get(1).text();
			String date = tds.get(2).text();
			String size = tds.get(3).text();
			Element linkElement = tds.get(4);
			Elements downloadElements = linkElement.select(".btn-download");
			for (int k = 0; k < downloadElements.size(); k++) {
				if (StrUtil.containsIgnoreCase(downloadElements.get(k).text(),"城通网盘" )){
					log.debug("【城通】{}", downloadElements.get(k).attr("href"));
				} else if (StrUtil.containsIgnoreCase(downloadElements.get(k).text(),"百度云盘" )){
					log.debug("【百度】{}", downloadElements.get(k).attr("href"));
				}
			}
			log.info("【版本】{}，【语言】{}，【更新日期】{}，【文件大小】{}，【城通】{}，【百度】{}", version, language, date, size, null, null);
		}

	}

	@Test
	public void testSoftInfo(){
		String url  = "http://xclient.info/s/things.html";
		Map<String, Object> params = Maps.newLinkedHashMap();
		params.put("a", "dl");
		params.put("v", "3.6.1");
		params.put("k", "1");
		params.put("t", RandomUtil.simpleUUID());
		String html = HttpUtil.get(url, params);
		System.out.println(html);
	}
}
