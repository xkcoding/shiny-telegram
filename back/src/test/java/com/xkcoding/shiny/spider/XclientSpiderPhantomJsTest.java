package com.xkcoding.shiny.spider;

import cn.hutool.json.JSONUtil;
import com.xkcoding.shiny.ShinyApplicationTests;
import com.xkcoding.shiny.mapper.SpiderConfigMapper;
import com.xkcoding.shiny.model.SpiderConfigDO;
import com.xkcoding.shiny.model.SpiderContentDO;
import com.xkcoding.shiny.task.SpiderTaskFactory;
import com.xkcoding.shiny.util.DriverUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * xclient.info PhantomJs 采集测试
 * </p>
 *
 * @package: com.xkcoding.shiny.spider
 * @description： xclient.info PhantomJs 采集测试
 * @author: yangkai.shen
 * @date: Created in 2018/8/24 下午6:42
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class XclientSpiderPhantomJsTest extends ShinyApplicationTests {
	@Autowired
	private SpiderConfigMapper spiderConfigMapper;

	@Autowired
	private DriverUtil driverUtil;

	@Test
	public void test() throws InterruptedException {
		long start = System.currentTimeMillis();

		WebDriver driver = driverUtil.getPhantomJSDriver();

		SpiderConfigDO spiderConfigDO = spiderConfigMapper.selectByPrimaryKey(3);

		List<SpiderContentDO> spiderContentDOList = SpiderTaskFactory.executeSpider(driver, spiderConfigDO);

		long end = System.currentTimeMillis();

		System.out.println("耗时：" + (end - start) / 1000 + " 秒");

		log.info(JSONUtil.toJsonStr(spiderContentDOList));
	}

}
