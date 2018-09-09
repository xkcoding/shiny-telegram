package com.xkcoding.shiny.mapper;

import com.xkcoding.shiny.common.MyMapper;
import com.xkcoding.shiny.model.EmailLogDO;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 邮件记录 Mapper
 * </p>
 *
 * @package: com.xkcoding.shiny.mapper
 * @description: 邮件记录 Mapper
 * @author: yangkai.shen
 * @date: Created in 2018/9/9 下午2:57
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
public interface EmailLogMapper extends MyMapper<EmailLogDO> {
}