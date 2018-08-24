package com.xkcoding.shiny.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.system.SystemUtil;
import com.xkcoding.shiny.common.ShinyConst;
import com.xkcoding.shiny.model.query.base.PageCondition;

/**
 * <p>
 * Shiny常用工具类
 * </p>
 *
 * @package: com.xkcoding.shiny.util
 * @description： Shiny常用工具类
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午10:06
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public class ShinyUtil {

	/**
	 * 校验分页参数，为NULL，设置分页参数默认值
	 *
	 * @param condition 查询参数
	 * @param clazz     类
	 * @param <T>       {@link PageCondition}
	 * @return T
	 */
	public static <T extends PageCondition> T checkPageCondition(T condition, Class<T> clazz) {
		if (ObjectUtil.isNull(condition)) {
			condition = ReflectUtil.newInstance(clazz);
		}
		if (ObjectUtil.isNull(condition.getCurrentPage())) {
			condition.setCurrentPage(ShinyConst.DEFAULT_CURRENT_PAGE);
		}
		if (ObjectUtil.isNull(condition.getPageSize())) {
			condition.setPageSize(ShinyConst.DEFAULT_PAGE_SIZE);
		}
		return condition;
	}

	/**
	 * 是否是 Mac 系统
	 *
	 * @return <code>true</code> 是 <code>false</code> 否
	 */
	private static Boolean isMac() {
		return SystemUtil.getOsInfo().isMac() || SystemUtil.getOsInfo().isMacOsX();
	}

	/**
	 * 是否是 Linux 系统
	 *
	 * @return <code>true</code> 是 <code>false</code> 否
	 */
	private static Boolean isLinux() {
		return SystemUtil.getOsInfo().isLinux();
	}

	/**
	 * 是否是 Windows 系统
	 *
	 * @return <code>true</code> 是 <code>false</code> 否
	 */
	private static Boolean isWindows() {
		return SystemUtil.getOsInfo().isWindows();
	}
}
