# travel
```
是一个在线旅游订票系统
提供景点门票、酒店预订、旅游套餐、交通预订等功能。
采用微服务架构，支持高并发、分布式事务、智能搜索、个性化推荐。
```

### 技术选型
```
技术层          核心技术
前端            Vue3
后端            Spring Boot3 + Spring Cloud
网关            Spring Cloud Gateway
微服务治理      Nacos、Sentinel、Seata
数据库          MySQL + Redis + MongoDB + Mybatis-Plus
搜索            Elasticsearch
消息队列        Pulsar
支付系统        微信支付 / 支付宝 / Stripe
部署            Docker + Kubernetes
监控            Prometheus + Loki + OpenTelemetry
```
### 数据库设计
```
1.用户表
2.旅游产品表
3.订单表
4.库存表
5.支付表
6.评价表
```




阶段	        模块	            任务内容	                             优先级	        状态
基础工程搭建	项目脚手架	         多模块父子工程搭建，Gradle配置	           高	
基础工程搭建	公共模块	         统一返回类R、状态码枚举ResultCodeEnum	   高	
基础工程搭建	配置模块	         数据源配置、MyBatis-Plus通用配置	       高	
基础工程搭建	网关模块	         Gateway路由转发配置、CORS跨域处理	       高	
基础业务开发	用户模块	         注册、登录、JWT生成与解析、密码加密	    高	
基础业务开发	验证码功能	         短信验证码生成与验证、Redis存储	        高	
旅游产品模块	产品管理	         旅游线路CRUD、分页查询、状态管理	        高	
购物车模块	    购物车功能	         Redis存储购物车数据，增删改查	            高	
订单模块	    订单管理	         订单提交、支付逻辑、名额锁定	            高
安全与优化	    Gateway鉴权	         JWT校验过滤器、Token过期处理	            高	
