package com.xkcoding.shiny.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * <p>
 * 通用 Mapper
 * </p>
 *
 * @package: com.xkcoding.shiny.common
 * @description： 通用 Mapper
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午6:43
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
