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
└── front           项目前端
```

## 项目截图

## 联系我
- 邮箱: 237497819@qq.com
- 扣扣: 237497819