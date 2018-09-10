#!/bin/bash
#set -e

#修改时区为东八区
echo '0.设置时区为东八区....'
cp /usr/share/zoneinfo/PRC /etc/localtime
date "+%Y-%m-%d %H:%M:%S"

#查看mysql服务的状态，方便调试，这条语句可以删除
echo `service mysql status`

echo '1.启动mysql....'
#启动mysql
service mysql start
sleep 3
echo `service mysql status`

echo '2.开始创建数据库和表结构....'
#导入数据
mysql < /mysql/shiny.sql
echo '3.创建数据库和表结构完毕....'

echo '4.开始导入数据....'
#导入数据
mysql < /mysql/shiny-data.sql
echo '5.导入数据完毕....'

sleep 3
echo `service mysql status`

#重新设置mysql密码
echo '6.开始修改密码....'
mysql < /mysql/privileges.sql
echo '7.修改密码完毕....'

#sleep 3
echo `service mysql status`
echo `mysql容器启动完毕,且数据导入成功`

tail -f /dev/null