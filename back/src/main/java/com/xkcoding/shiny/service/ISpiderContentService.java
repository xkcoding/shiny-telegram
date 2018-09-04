package com.xkcoding.shiny.service;

import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.exception.ShinyException;
import com.xkcoding.shiny.model.SpiderContentDO;
import com.xkcoding.shiny.model.query.SpiderContentPageQuery;
import com.xkcoding.shiny.model.vo.SpiderContentVO;

import java.util.List;

/**
 * <p>
 * 采集内容接口
 * </p>
 *
 * @package: com.xkcoding.shiny.service
 * @description： 采集内容接口
 * @author: yangkai.shen
 * @date: Created in 2018/8/27 下午9:48
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface ISpiderContentService {
	/**
	 * 删除今天采集的所有软件信息
	 */
	void deleteAllToday();

	/**
	 * 删除今天采集的某个软件信息
	 *
	 * @param configId 采集配置 id
	 */
	void deleteToday(Integer configId);

	/**
	 * 查看单个软件的采集信息
	 *
	 * @param configId 配置id
	 * @param query    查询条件
	 * @return 分页信息
	 * @throws ShinyException 采集配置不存在
	 */
	PageResult<SpiderContentVO> getSpiderContent(Integer configId, SpiderContentPageQuery query) throws ShinyException;

	/**
	 * 查询采集信息列表
	 *
	 * @param query 查询条件
	 * @return 采集信息列表
	 */
	PageResult<SpiderContentVO> listSpiderContent(SpiderContentPageQuery query);

	/**
	 * 获取今天采集的最近更新时间在2天内的所有软件信息
	 *
	 * @return 今天采集的最近更新时间在2天内的所有软件信息
	 */
	List<SpiderContentDO> listLatestSoftware();
}
