package com.xkcoding.shiny.mapper;

import com.xkcoding.shiny.common.MyMapper;
import com.xkcoding.shiny.model.SpiderContentDO;
import com.xkcoding.shiny.model.query.SpiderContentPageQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 采集内容Mapper类
 * </p>
 *
 * @package: com.xkcoding.shiny.mapper
 * @description： 采集内容Mapper类
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午6:45
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
public interface SpiderContentMapper extends MyMapper<SpiderContentDO> {
	/**
	 * 查询单个采集内容
	 *
	 * @param configId 采集配置 id
	 * @param query    查询条件
	 * @return 单个采集内容列表
	 */
	List<SpiderContentDO> selectSingleSpiderContent(@Param("configId") Integer configId, @Param("query") SpiderContentPageQuery query);

	/**
	 * 查询采集信息列表
	 *
	 * @param query 查询条件
	 * @return 采集信息列表
	 */
	List<SpiderContentDO> selectSpiderContent(@Param("query") SpiderContentPageQuery query);
}