package com.xkcoding.shiny.service;

import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.model.query.SpiderConfigPageQuery;
import com.xkcoding.shiny.model.vo.SpiderConfigVO;

/**
 * <p>
 * 采集配置Service接口
 * </p>
 *
 * @package: com.xkcoding.shiny.service
 * @description： 采集配置Service接口
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 下午9:48
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
public interface ISpiderConfigService {

	/**
	 * 分页采集列表
	 *
	 * @param query 查询条件
	 * @return 分页采集列表信息
	 */
	PageResult<SpiderConfigVO> listSpiderConfig(SpiderConfigPageQuery query);
}
