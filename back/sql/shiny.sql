-- 创建数据库
create database IF NOT EXISTS `shiny` default character set utf8 collate utf8_general_ci;

use `shiny`;

-- ----------------------------
-- 1、参数配置表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(100) DEFAULT '' COMMENT '参数键值',
  `config_type` int(2) DEFAULT 0 COMMENT '系统内置（0否 1是）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数配置表';

-- ----------------------------
-- 2、系统用户表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` int(2) DEFAULT 0 COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像路径',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` int(2) DEFAULT 1 COMMENT '帐号状态（0停用 1正常）',
  `del_flag` int(2) DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
  `login_ip` varchar(20) DEFAULT '' COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- 3、角色信息表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` int(2) DEFAULT 1 COMMENT '角色状态（0停用 1正常）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- 4、用户和角色关联表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';

-- ----------------------------
-- 5、采集配置
-- ----------------------------
CREATE TABLE IF NOT EXISTS `spider_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配置主键',
  `spider_name` varchar(100) DEFAULT '' COMMENT '采集名称',
  `spider_url` varchar(500) DEFAULT '' COMMENT '采集URL',
  `last_spider_time` datetime DEFAULT NULL COMMENT '上次采集时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采集配置';

-- ----------------------------
-- 6、采集内容
-- ----------------------------
CREATE TABLE IF NOT EXISTS `spider_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '内容主键',
  `config_id` int(11) NOT NULL COMMENT '采集配置id',
  `config_name` varchar(100) NOT NULL COMMENT '采集配置名称',
  `title` varchar(100) DEFAULT '' COMMENT '软件名称',
  `content` text COMMENT '软件详细信息',
  `version` varchar(100) DEFAULT '' COMMENT '软件版本',
  `language` varchar(100) DEFAULT '' COMMENT '软件语言',
  `update_time` date DEFAULT NULL COMMENT '软件更新时间',
  `size` varchar(100) DEFAULT '' COMMENT '软件大小',
  `ct_pan_url` varchar(500) DEFAULT '' COMMENT '城通网盘链接',
  `ct_pan_code` varchar(500) DEFAULT '' COMMENT '城通网盘提取码',
  `bd_pan_url` varchar(500) DEFAULT '' COMMENT '百度网盘链接',
  `bd_pan_code` varchar(500) DEFAULT '' COMMENT '百度网盘提取码',
  `spider_time` datetime DEFAULT NULL COMMENT '采集时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采集内容';

-- ----------------------------
-- 7、采集日志记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS `spider_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `config_id` int(11) NOT NULL COMMENT '采集配置 id',
  `spider_name` varchar(100) DEFAULT '' COMMENT '采集名称',
  `version` varchar(100) DEFAULT '' COMMENT '采集版本',
  `spider_url` varchar(500) DEFAULT '' COMMENT '采集URL',
  `status` int(2) DEFAULT 1 COMMENT '采集状态（0异常 1正常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `spider_time` datetime DEFAULT NULL COMMENT '采集时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采集日志记录';

-- ----------------------------
-- 8、邮件记录
-- ----------------------------
CREATE TABLE IF NOT EXISTS `email_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '邮件主键',
  `to` text NOT NULL COMMENT '收件人邮箱地址（多个逗号分隔）',
  `subject` varchar(100) DEFAULT '' COMMENT '邮件主题',
  `content` text NOT NULL COMMENT '邮件内容',
  `is_template` int(2) DEFAULT 1 COMMENT '是否是模板邮件（0否 1是）',
  `template_path` varchar(100) DEFAULT NULL COMMENT '模板路径',
  `template_name` varchar(100) DEFAULT NULL COMMENT '模板名称',
  `send_time` datetime DEFAULT NULL COMMENT '邮件发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件记录';