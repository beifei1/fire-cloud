[![GitHub forks](https://img.shields.io/github/forks/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/network)    [![GitHub stars](https://img.shields.io/github/stars/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/stargazers)   [![GitHub issues](https://img.shields.io/github/issues/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/issues)



## 项目概述：

基于Spring Cloud Netflix和Spring Cloud Alibaba的微服务脚手架，目前处于开发阶段。在过程中可能对结构随时进行调整，同时会记录设计过程中的典型错误及修正方式。

## 运行环境：

**Java SDK:** 1.8 +

**Maven:** 3.10.0

## 技术选型：

|                    |                               |                               |   |
| ------------------ | ----------------------------- |---|---|
| 网关               | Spring Cloud Gateway 2.0      | [官网](https://spring.io/projects/spring-cloud-gateway) | ✔ |
| 权限控制           | Spring Cloud Security Oauth2  | [官网](https://spring.io/projects/spring-cloud-security) | ✔ |
| 任务调度           | XXL-Job                       | [官网](https://www.xuxueli.com/xxl-job/) | - |
| 服务治理及配置中心 | Spring Cloud Alibaba Nacos    | [官网](https://nacos.io/en-us/) | ✔ |
| 熔断及限流         | Spring Cloud Alibaba Sentinel | [GitHub](https://github.com/alibaba/Sentinel) | - |
| 分布式事务         | Seata                         | [官网](https://github.com/seata/seata) | - |
| ORM框架      | MyBatis-Plus        | [官网](https://baomidou.com/) | ✔ |
| 消息队列           | RocketMQ                         | [官网](http://rocketmq.apache.org/)                        | - |
| 服务调用           | Spring Cloud OpenFeign                     | [官网](https://spring.io/projects/spring-cloud-openfeign)                    | ✔ |
| API文档           | Knife4j                     | [官网](https://doc.xiaominfo.com/guide/useful.html) | ✔ |
| 持续交付       | Jenkins,Ansible | [Jenkinsfile](https://github.com/beifei1/fire-cloud/blob/master/Jenkinsfile) / [Ansiable](http://www.ansible.com.cn/docs/playbooks.html) | ✔ |
| 缓存           | Redisson,Spring Cache  | [GitHub](https://github.com/redisson/redisson) | ✔|
| 数据库连接池 | Hikari | [GitHub](https://github.com/brettwooldridge/HikariCP) | ✔ |

## 调用关系：

![image](https://github.com/beifei1/fire-cloud/blob/master/assets/flow.png)

## 服务说明：

|                       |              |      |
| --------------------- | ------------ | ---- |
| fire-service-gateway          | 网关服务     | ✔    |
| fire-service-oauth            | 授权服务     | ✔    |
| fire-user-consumer    | 用户对外服务 | ✔    |
| fire-user-producer    | 用户基础服务 | ✔    |
| fire-service-job              | 任务调度     | -    |
| fire-message-consumer | 消息服务     | -    |
| fire-message-producer | 消息基础服务 | -    |

## 快速预览：

Spring Cloud Security OAuth2：

- [在JwtToken中加入用户信息](https://github.com/beifei1/fire-cloud/blob/master/fire-service-oauth/src/main/java/cn/fire/oauth/config/AuthServerConfig.java)

- [自定义OAuth2授权模式](https://github.com/beifei1/fire-cloud/tree/master/fire-service-oauth/src/main/java/cn/fire/oauth/granter)

- [在OAuth2中使用自定义异常响应](https://github.com/beifei1/fire-cloud/tree/master/fire-service-oauth/src/main/java/cn/fire/oauth/config/enhancer)

- [在微服务中自动注入用户信息](https://github.com/beifei1/fire-cloud/blob/master/fire-module-user/fire-user-consumer/src/main/java/cn/fire/user/controller/UserController.java)

CI/CD：

- [利用Jenkinsfile，Ansiable，Gitlab等实现持续集成与交付](https://github.com/beifei1/fire-cloud/tree/master/deploy)

Spring Cloud Gateway2.0：

- [Knife4j集成Spring Cloud Gateway2.0统一管理微服务API文档](https://github.com/beifei1/fire-cloud/blob/master/fire-service-gateway/src/main/java/cn/fire/gateway/config/SwaggerConfig.java)

- [Spring Cloud Gateway2.0防重放](https://github.com/beifei1/fire-cloud/blob/master/fire-service-gateway/src/main/java/cn/fire/gateway/filter/VerificationFilter.java)

- [Spring Cloud Gateway2.0网关统一认证授权](https://github.com/beifei1/fire-cloud/tree/master/fire-service-gateway)

Spring Cloud Alibaba Nacos:

- [Nacos Config使用多配置文件](https://github.com/beifei1/fire-cloud/blob/master/fire-service-oauth/src/main/resources/bootstrap.yml)

MyBatis-Plus:

- [使用MyBatis-Plus快速实现乐观锁，逻辑删除等功能](https://github.com/beifei1/fire-cloud/blob/master/fire-common/src/main/java/cn/fire/common/core/BaseDO.java)
