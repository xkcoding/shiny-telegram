# shiny-telegram

> 译为：闪耀的电报，为什么要取这个名字？因为 github 新建项目的时候有个随机名。。。

## 主要功能
1. 解析 http://xclient.info 网站获取对应软件历史版本
2. 软件名和对应在 xclient.info 的网页地址维护管理接口
3. 列表展示软件最近更新版本，以及最近更新时间
4. 点击列表页可以查看某个软件具体信息，包括软件基本信息，所有版本信息，所有版本更新时间，所有版本下载地址及提取码
5. 每天9点定时检查所有配置软件，若有最近两天更新的软件，则把最近更新的软件列表统一发邮件通知

## 使用技术
1. Spring Boot 后台框架
2. Spring Task 定时任务
3. Spring Email 邮件通知
4. Selenium + ChromeDriver( https://sites.google.com/a/chromium.org/chromedriver/downloads )/PhantomJs( http://phantomjs.org/download.html ) 模拟浏览器行为处理反爬
5. Jsoup + Xsoup 解析 DOM 节点
6. Mysql 存储
7. Vue + iView 前端框架

## 项目目录
```bash
.
├── README.md       项目介绍
├── back            项目后端
├── front           项目前端
└── mysql           项目 mysql docker镜像构建文件
```

## 项目构建与运行
### 项目构建
#### mysql 镜像构建
1. 构建 `mysql` 镜像
```bash
$ cd mysql
$ docker build -t shiny-mysql .
Sending build context to Docker daemon  24.06kB
Step 1/9 : FROM mysql:5.7
 ---> 0d16d0a97dd1
Step 2/9 : MAINTAINER yangkai.shen 237497819@qq.com
 ---> Running in 7f5fa09ba5dd
Removing intermediate container 7f5fa09ba5dd
 ---> 7188872d8492
Step 3/9 : ENV MYSQL_ALLOW_EMPTY_PASSWORD yes
 ---> Running in f11ed6362744
Removing intermediate container f11ed6362744
 ---> dbc977df5488
Step 4/9 : COPY setup.sh /mysql/setup.sh
 ---> 7f31fff84f9e
Step 5/9 : COPY sql/shiny.sql /mysql/shiny.sql
 ---> 44e65ddf7fa9
Step 6/9 : COPY sql/shiny-data.sql /mysql/shiny-data.sql
 ---> 2a86658ba4f9
Step 7/9 : COPY sql/privileges.sql /mysql/privileges.sql
 ---> 44b44deb7d07
Step 8/9 : COPY ./mysqld.cnf /etc/mysql/mysql.conf.d/mysqld.cnf
 ---> 14a88aec77c0
Step 9/9 : CMD ["sh", "/mysql/setup.sh"]
 ---> Running in e0e78e73e646
Removing intermediate container e0e78e73e646
 ---> 8ea97c1bf792
Successfully built 8ea97c1bf792
Successfully tagged shiny-mysql:latest
```
2. 运行 `mysql` 镜像

```bash
$ mkdir -p $HOME/docker/shiny/mysql/data
$ mkdir -p $HOME/docker/shiny/mysql/logs
$ docker run -d --name shiny-mysql -p 13306:3306 -v $HOME/shiny/mysql/data:/var/lib/mysql -v $HOME/shiny/mysql/logs:/logs shiny-mysql
974de62c11fb294b2330af339f8b07299a69f956f2a0df1ee5d2e8cbab1dd485
```
3. 查看镜像构建日志
```bash
$ docker logs shiny-mysql
0.设置时区为东八区....
2018-09-10 22:59:56
MySQL Community Server 5.7.22 is not running.
1.启动mysql....
..
MySQL Community Server 5.7.22 is started.
MySQL Community Server 5.7.22 is running.
2.开始创建数据库和表结构....
3.创建数据库和表结构完毕....
4.开始导入数据....
5.导入数据完毕....
MySQL Community Server 5.7.22 is running.
6.开始修改密码....
host	user
localhost	mysql.session
localhost	mysql.sys
localhost	root
7.修改密码完毕....
MySQL Community Server 5.7.22 is running.
/mysql/setup.sh: 1: /mysql/setup.sh: mysql容器启动完毕,且数据导入成功: not found
```

### 项目运行
#### 源码运行

#### 镜像运行
1. `mysql` 镜像
    - 使用上面自己构建的镜像
    - 拉取远端镜像，`docker pull registry.cn-hangzhou.aliyuncs.com/xkcoding/shiny-telegram-mysql:latest`

## 项目截图

## 联系我
- 邮箱: 237497819@qq.com
- 扣扣: 237497819