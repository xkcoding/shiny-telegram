package com.xkcoding.shiny.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.system.SystemUtil;
import com.xkcoding.shiny.common.ShinyConst;
import com.xkcoding.shiny.model.query.base.PageCondition;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

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
	public static Boolean isMac() {
		return SystemUtil.getOsInfo().isMac() || SystemUtil.getOsInfo().isMacOsX();
	}

	/**
	 * 是否是 Linux 系统
	 *
	 * @return <code>true</code> 是 <code>false</code> 否
	 */
	public static Boolean isLinux() {
		return SystemUtil.getOsInfo().isLinux();
	}

	/**
	 * 是否是 Windows 系统
	 *
	 * @return <code>true</code> 是 <code>false</code> 否
	 */
	public static Boolean isWindows() {
		return SystemUtil.getOsInfo().isWindows();
	}

	/**
	 * 判断对象是否为空对象，属性都为<code>null</code>
	 *
	 * @param object 对象
	 * @param clazz  对象类型
	 * @return <code>true</code> - 空 / <code>false</code> - 非空
	 */
	public static Boolean isEmpty(Object object, Class clazz) {
		if (ObjectUtil.isNull(object)) {
			return true;
		}
		Field[] fields = ReflectUtil.getFields(clazz);
		for (Field field : fields) {
			Object fieldValue = ReflectUtil.getFieldValue(object, field);
			if (ObjectUtil.isNotNull(fieldValue)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 插入前操作
	 *
	 * @param obj   对象
	 * @param clazz 对象类型
	 */
	public static void beforeInsert(Object obj, Class clazz) {
		Method setCreateBy = ReflectUtil.getMethod(clazz, "setCreateBy");
		Method getCreateBy = ReflectUtil.getMethod(clazz, "getCreateBy");
		Method setCreateTime = ReflectUtil.getMethod(clazz, "setCreateTime");
		Method getCreateTime = ReflectUtil.getMethod(clazz, "getCreateTime");

		if (setCreateBy != null) {
			if (ObjectUtil.isNull(ReflectUtil.invoke(obj, getCreateBy))) {
				ReflectUtil.invoke(obj, setCreateBy, "管理员");
			}
		}
		if (setCreateTime != null) {
			if (ObjectUtil.isNull(ReflectUtil.invoke(obj, getCreateTime))) {
				ReflectUtil.invoke(obj, setCreateTime, new Date());
			}
		}

		beforeUpdate(obj, clazz);
	}

	/**
	 * 更新前操作
	 *
	 * @param obj   对象
	 * @param clazz 对象类型
	 */
	public static void beforeUpdate(Object obj, Class clazz) {
		Method setUpdateBy = ReflectUtil.getMethod(clazz, "setUpdateBy");
		Method getUpdateBy = ReflectUtil.getMethod(clazz, "getUpdateBy");
		Method setUpdateTime = ReflectUtil.getMethod(clazz, "setUpdateTime");
		Method getUpdateTime = ReflectUtil.getMethod(clazz, "getUpdateTime");

		if (setUpdateBy != null) {
			if (ObjectUtil.isNull(ReflectUtil.invoke(obj, getUpdateBy))) {
				ReflectUtil.invoke(obj, setUpdateBy, "管理员");
			}
		}
		if (setUpdateTime != null) {
			if (ObjectUtil.isNull(ReflectUtil.invoke(obj, getUpdateTime))) {
				ReflectUtil.invoke(obj, setUpdateTime, new Date());
			}
		}
	}
}
