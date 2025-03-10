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
数据库          MySQL + Redis + MongoDB
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