package com.xkcoding.shiny.util;

import com.xkcoding.shiny.common.property.ShinyProperties;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * <p>
 * DriverUtil 工具类
 * </p>
 *
 * @package: com.xkcoding.shiny.util
 * @description： DriverUtil 工具类
 * @author: yangkai.shen
 * @date: Created in 2018/8/25 下午1:48
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
public class DriverUtil {
	@Autowired
	private ShinyProperties shinyProperties;

	/**
	 * 获取 ChromeDriver 驱动
	 *
	 * @return ChromeDriver 驱动
	 */
	public ChromeDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", getChromeDriverPath());

		return new ChromeDriver();
	}

	/**
	 * 获取适合本机系统的 ChromeDriver 路径
	 *
	 * @return ChromeDriver 路径
	 */
	private String getChromeDriverPath() {
		if (ShinyUtil.isMac()) {
			return shinyProperties.getDriverPath() + File.separator + "chrome-driver/macos/chromedriver";
		} else if (ShinyUtil.isLinux()) {
			return shinyProperties.getDriverPath() + File.separator + "chrome-driver/linux/chromedriver";
		} else {
			return shinyProperties.getDriverPath() + File.separator + "chrome-driver/windows/chromedriver.exe";
		}
	}

	/**
	 * 获取 PhantomJs 驱动
	 *
	 * @return PhantomJs 驱动
	 */
	public PhantomJSDriver getPhantomJSDriver() {
		//设置必要参数
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//ssl证书支持
		capabilities.setCapability("acceptSslCerts", true);
		//截屏支持
		capabilities.setCapability("takesScreenshot", false);
		//css搜索支持
		capabilities.setCapability("cssSelectorsEnabled", true);
		//js支持
		capabilities.setJavascriptEnabled(true);
		//驱动支持
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, getPhantomJsPath());

		return new PhantomJSDriver(capabilities);
	}

	/**
	 * 获取适合本机系统的 PhantomJs 路径
	 *
	 * @return PhantomJs 路径
	 */
	private String getPhantomJsPath() {
		if (ShinyUtil.isMac()) {
			return shinyProperties.getDriverPath() + File.separator + "phantomjs/macos/phantomjs";
		} else if (ShinyUtil.isLinux()) {
			return shinyProperties.getDriverPath() + File.separator + "phantomjs/linux/X64/phantomjs";
		} else {
			return shinyProperties.getDriverPath() + File.separator + "phantomjs/windows/phantomjs.exe";
		}
	}
}
