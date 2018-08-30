package com.xkcoding.shiny.mapper;

import com.xkcoding.shiny.common.MyMapper;
import com.xkcoding.shiny.model.SpiderConfigDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 采集参数配置Mapper类
 * </p>
 *
 * @package: com.xkcoding.shiny.mapper
 * @description： 采集参数配置Mapper类
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午6:45
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
public interface SpiderConfigMapper extends MyMapper<SpiderConfigDO> {

	/**
	 * 根据采集名称查询采集配置列表
	 *
	 * @param text 采集配置名称 / 采集配置备注
	 * @return 采集配置列表
	 */
	List<SpiderConfigDO> selectSpiderConfigByText(@Param("text") String text);

	/**
	 * 批量删除采集配置
	 *
	 * @param ids 采集 id 列表
	 */
	void deleteBatch(@Param("ids") List<Integer> ids);
}