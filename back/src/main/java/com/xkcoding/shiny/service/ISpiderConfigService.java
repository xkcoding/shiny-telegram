package com.xkcoding.shiny.service;

import com.xkcoding.shiny.common.PageResult;
import com.xkcoding.shiny.exception.ShinyException;
import com.xkcoding.shiny.model.SpiderConfigDO;
import com.xkcoding.shiny.model.query.SpiderConfigPageQuery;
import com.xkcoding.shiny.model.vo.SpiderConfigVO;

import java.util.List;

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
	 * 分页采集配置列表
	 *
	 * @param query 查询条件
	 * @return 分页采集配置列表信息
	 */
	PageResult<SpiderConfigVO> listSpiderConfig(SpiderConfigPageQuery query);

	/**
	 * 全部采集配置列表
	 *
	 * @return 全部采集配置列表
	 */
	List<SpiderConfigDO> listAllSpiderConfig();

	/**
	 * 根据配置id获取采集配置
	 *
	 * @param id 配置 id
	 * @return 采集配置 DO
	 */
	SpiderConfigDO selectSpiderConfig(Integer id);

	/**
	 * 保存采集配置
	 *
	 * @param spiderConfigVO 采集配置 VO
	 * @return 采集配置 DO
	 */
	SpiderConfigDO saveConfig(SpiderConfigVO spiderConfigVO);

	/**
	 * 更新采集配置
	 *
	 * @param id             配置 id
	 * @param spiderConfigVO 采集配置 VO
	 * @return 采集配置 DO
	 * @throws ShinyException 采集配置不存在
	 */
	SpiderConfigDO updateConfig(Integer id, SpiderConfigVO spiderConfigVO) throws ShinyException;

	/**
	 * 根据配置 id 删除采集配置
	 *
	 * @param id 配置 id
	 */
	void deleteConfig(Integer id);

	/**
	 * 获取采集配置详情
	 *
	 * @param id 配置 id
	 * @return 采集配置 VO
	 * @throws ShinyException 采集配置不存在
	 */
	SpiderConfigVO getConfig(Integer id) throws ShinyException;

	/**
	 * 批量删除采集配置
	 *
	 * @param ids 采集 id 列表
	 */
	void deleteBatch(List<Integer> ids);
}
