package com.xkcoding.shiny.mapper;

import com.xkcoding.shiny.common.MyMapper;
import com.xkcoding.shiny.model.SpiderLogDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 采集日志Mapper类
 * </p>
 *
 * @package: com.xkcoding.shiny.mapper
 * @description： 采集日志Mapper类
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午6:47
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
public interface SpiderLogMapper extends MyMapper<SpiderLogDO> {
	/**
	 * 根据查询条件模糊查询采集日志列表
	 *
	 * @param spiderName 采集名称
	 * @param version    采集版本
	 * @param status     采集状态
	 * @param errorMsg   错误信息
	 * @param startTime  开始时间
	 * @param endTime    结束时间
	 * @return 采集日志列表
	 */
	List<SpiderLogDO> selectSpiderLogByParam(@Param("spiderName") String spiderName, @Param("version") String version, @Param("status") Integer status, @Param("errorMsg") String errorMsg, @Param("startTime") String startTime, @Param("endTime") String endTime);
}