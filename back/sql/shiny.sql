-- ----------------------------
-- 1、参数配置表
-- ----------------------------
drop table if exists sys_config;
CREATE TABLE sys_config (
 id INT ( 5 ) NOT NULL auto_increment COMMENT '参数主键',
 config_name VARCHAR ( 100 ) DEFAULT '' COMMENT '参数名称',
 config_key VARCHAR ( 100 ) DEFAULT '' COMMENT '参数键名',
 config_value VARCHAR ( 100 ) DEFAULT '' COMMENT '参数键值',
 config_type INT ( 2 ) DEFAULT 0 COMMENT '系统内置（0否 1是）',
 create_by VARCHAR ( 64 ) DEFAULT '' COMMENT '创建者',
 create_time datetime COMMENT '创建时间',
 update_by VARCHAR ( 64 ) DEFAULT '' COMMENT '更新者',
 update_time datetime COMMENT '更新时间',
 remark VARCHAR ( 500 ) DEFAULT '' COMMENT '备注',
 PRIMARY KEY ( id )
) ENGINE = INNODB DEFAULT charset = utf8 COMMENT = '参数配置表';

-- ----------------------------
-- 2、系统用户表
-- ----------------------------
drop table if exists sys_user;
CREATE TABLE sys_user (
 id INT ( 11 ) NOT NULL auto_increment COMMENT '用户ID',
 dept_id INT ( 11 ) DEFAULT NULL COMMENT '部门ID',
 login_name VARCHAR ( 30 ) NOT NULL COMMENT '登录账号',
 user_name VARCHAR ( 30 ) NOT NULL COMMENT '用户昵称',
 user_type VARCHAR ( 2 ) DEFAULT '00' COMMENT '用户类型（00系统用户）',
 email VARCHAR ( 50 ) DEFAULT '' COMMENT '用户邮箱',
 phonenumber VARCHAR ( 11 ) DEFAULT '' COMMENT '手机号码',
 sex INT ( 2 ) DEFAULT 0 COMMENT '用户性别（0男 1女 2未知）',
 avatar VARCHAR ( 100 ) DEFAULT '' COMMENT '头像路径',
 password VARCHAR ( 100 ) DEFAULT '' COMMENT '密码',
 status INT ( 2 ) DEFAULT 1 COMMENT '帐号状态（0停用 1正常）',
 del_flag INT ( 2 ) DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
 login_ip VARCHAR ( 20 ) DEFAULT '' COMMENT '最后登陆IP',
 login_date datetime COMMENT '最后登陆时间',
 create_by VARCHAR ( 64 ) DEFAULT '' COMMENT '创建者',
 create_time datetime COMMENT '创建时间',
 update_by VARCHAR ( 64 ) DEFAULT '' COMMENT '更新者',
 update_time datetime COMMENT '更新时间',
 remark VARCHAR ( 500 ) DEFAULT '' COMMENT '备注',
 PRIMARY KEY ( id )
) ENGINE = INNODB DEFAULT charset = utf8 COMMENT = '系统用户表';

-- ----------------------------
-- 3、角色信息表
-- ----------------------------
drop table if exists sys_role;
CREATE TABLE sys_role (
 id INT ( 11 ) NOT NULL auto_increment COMMENT '角色ID',
 role_name VARCHAR ( 30 ) NOT NULL COMMENT '角色名称',
 role_key VARCHAR ( 100 ) NOT NULL COMMENT '角色权限字符串',
 role_sort INT ( 4 ) NOT NULL COMMENT '显示顺序',
 status INT ( 2 ) DEFAULT 1 COMMENT '角色状态（0停用 1正常）',
 create_by VARCHAR ( 64 ) DEFAULT '' COMMENT '创建者',
 create_time datetime COMMENT '创建时间',
 update_by VARCHAR ( 64 ) DEFAULT '' COMMENT '更新者',
 update_time datetime COMMENT '更新时间',
 remark VARCHAR ( 500 ) DEFAULT '' COMMENT '备注',
 PRIMARY KEY ( id )
) ENGINE = INNODB DEFAULT charset = utf8 COMMENT = '角色信息表';

-- ----------------------------
-- 4、用户和角色关联表
-- ----------------------------
drop table if exists sys_user_role;
CREATE TABLE sys_user_role (
 user_id INT ( 11 ) NOT NULL COMMENT '用户ID',
 role_id INT ( 11 ) NOT NULL COMMENT '角色ID',
 PRIMARY KEY ( user_id, role_id )
) ENGINE = INNODB DEFAULT charset = utf8 COMMENT = '用户和角色关联表';

-- ----------------------------
-- 5、采集配置
-- ----------------------------
drop table if exists spider_config;
CREATE TABLE spider_config (
 id INT ( 11 ) NOT NULL auto_increment COMMENT '配置主键',
 spider_name VARCHAR ( 50 ) DEFAULT '' COMMENT '采集名称',
 spider_url VARCHAR ( 255 ) DEFAULT '' COMMENT '采集URL',
 last_spider_time datetime COMMENT '上次采集时间',
 create_by VARCHAR ( 64 ) DEFAULT '' COMMENT '创建者',
 create_time datetime COMMENT '创建时间',
 update_by VARCHAR ( 64 ) DEFAULT '' COMMENT '更新者',
 update_time datetime COMMENT '更新时间',
 remark VARCHAR ( 500 ) DEFAULT '' COMMENT '备注',
 PRIMARY KEY ( id )
) ENGINE = INNODB DEFAULT charset = utf8 COMMENT = '采集配置';

-- ----------------------------
-- 6、采集内容
-- ----------------------------
drop table if exists spider_content;
CREATE TABLE spider_content (
 id INT ( 11 ) NOT NULL auto_increment COMMENT '内容主键',
 config_id INT ( 11 ) NOT NULL COMMENT '采集配置id',
 title VARCHAR ( 50 ) DEFAULT '' COMMENT '软件名称',
 content TEXT DEFAULT '' COMMENT '软件详细信息',
 version VARCHAR ( 25 ) DEFAULT '' COMMENT '软件版本',
 language VARCHAR ( 25 ) DEFAULT '' COMMENT '软件语言',
 update_time DATE COMMENT '软件更新时间',
 size VARCHAR ( 25 ) DEFAULT '' COMMENT '软件大小',
 ct_pan_url VARCHAR ( 255 ) DEFAULT '' COMMENT '城通网盘链接',
 ct_pan_code VARCHAR ( 255 ) DEFAULT '' COMMENT '城通网盘提取码',
 bd_pan_url VARCHAR ( 255 ) DEFAULT '' COMMENT '百度网盘链接',
 bd_pan_code VARCHAR ( 255 ) DEFAULT '' COMMENT '百度网盘提取码',
 spider_time datetime COMMENT '采集时间',
 PRIMARY KEY ( id )
) ENGINE = INNODB DEFAULT charset = utf8 COMMENT = '采集内容';
