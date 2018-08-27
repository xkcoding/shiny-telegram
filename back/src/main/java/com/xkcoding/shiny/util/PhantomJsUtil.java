package com.xkcoding.shiny.util;

import cn.hutool.core.io.resource.ClassPathResource;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * <p>
 * PhantomJs 工具类
 * </p>
 *
 * @package: com.xkcoding.shiny.util
 * @description： PhantomJs 工具类
 * @author: yangkai.shen
 * @date: Created in 2018/8/25 下午1:48
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class PhantomJsUtil {

	/**
	 * 获取 PhantomJs 驱动
	 *
	 * @return PhantomJs 驱动
	 */
	public static PhantomJSDriver getPhantomJSDriver() {
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
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, new ClassPathResource(getPhantomJsPath()).getAbsolutePath());

		return new PhantomJSDriver(capabilities);
	}

	/**
	 * 获取适合本机系统的 PhantomJs 路径
	 *
	 * @return PhantomJs 路径
	 */
	private static String getPhantomJsPath() {
		if (ShinyUtil.isMac()) {
			return "phantomjs/macos/phantomjs";
		} else if (ShinyUtil.isLinux()) {
			return "phantomjs/linux/X64/phantomjs";
		} else {
			return "phantomjs/windows/phantomjs.exe";
		}
	}
}
