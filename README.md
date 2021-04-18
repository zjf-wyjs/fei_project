**项目说明**
- 适用于前后端分离项目，能够快速开发后台管理系统+客户端接口，内置app端接口、微信小程序接口、微信支付、支付宝支付
- 高效率开发，使用代码生成器可以一键生成后台前后端代码。

<br>

**技术选型：**
- 核心框架：Spring Boot 2.2.4.RELEASE
- 安全框架：Apache Shiro 1.4
- 视图框架：Spring MVC 5.0
- 持久层框架：MyBatis-plus 3.3
- 定时器：Quartz 2.3
- 中央缓存：redis
- 数据库连接池：Druid 1.0
- 日志管理：SLF4J 1.7、Log4j
- 后台管理页面：Vue2.x + element-ui
  <br>

**项目结构**
```
fei-project
├─db  项目SQL语句
│
├─fei-api  app端接口
│  ├─adnnotation 客户端相关注解
│  ├─config 配置
│  ├─controller 控制包
│
├─fei-common 公共模块
│  ├─validator 后台校验
│  ├─utils 通用工具模块
│  └─xss XSS过滤
│
├─fei-core 核心模块
│  └─ApplicationLauncher 启动类
│
├─fei-data 业务模块
│  ├─service 业务层
│  ├─dao 持久层
│  ├─entity 实体类
│  └─vo 业务传输对象
│
├─fei-generator 代码生成模块
│
├─fei-pay 支付模块(待开发)
│
│
├─fei-quartz 定时任务模块
```
