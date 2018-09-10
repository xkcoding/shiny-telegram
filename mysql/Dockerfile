FROM mysql:5.7

MAINTAINER yangkai.shen 237497819@qq.com

#设置免密登录
ENV MYSQL_ALLOW_EMPTY_PASSWORD yes

#将所需文件放到容器中
COPY setup.sh /mysql/setup.sh
COPY sql/shiny.sql /mysql/shiny.sql
COPY sql/shiny-data.sql /mysql/shiny-data.sql
COPY sql/privileges.sql /mysql/privileges.sql
COPY ./mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf

#设置容器启动时执行的命令
CMD ["sh", "/mysql/setup.sh"]