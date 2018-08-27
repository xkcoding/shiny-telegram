package com.xkcoding.shiny.util;

import cn.hutool.core.io.resource.ClassPathResource;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * <p>
 * ChromeDriver 工具类
 * </p>
 *
 * @package: com.xkcoding.shiny.util
 * @description： ChromeDriver 工具类
 * @author: yangkai.shen
 * @date: Created in 2018/8/25 下午1:48
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class ChromeDriverUtil {

	/**
	 * 获取 ChromeDriver 驱动
	 *
	 * @return ChromeDriver 驱动
	 */
	public static ChromeDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", new ClassPathResource(getChromeDriverPath()).getAbsolutePath());

		return new ChromeDriver();
	}

	/**
	 * 获取适合本机系统的 ChromeDriver 路径
	 *
	 * @return ChromeDriver 路径
	 */
	private static String getChromeDriverPath() {
		if (ShinyUtil.isMac()) {
			return "chrome-driver/macos/chromedriver";
		} else if (ShinyUtil.isLinux()) {
			return "chrome-driver/linux/chromedriver";
		} else {
			return "chrome-driver/windows/chromedriver.exe";
		}
	}
}
