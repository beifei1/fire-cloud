[![GitHub forks](https://img.shields.io/github/forks/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/network)    [![GitHub stars](https://img.shields.io/github/stars/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/stargazers)   [![GitHub issues](https://img.shields.io/github/issues/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/issues)



## 项目概述：

基于Spring Cloud Netflix和Spring Cloud Alibaba的微服务脚手架，目前处于开发阶段。在项目中会标注一些在实际应用中自己总结的解决方案，如果有意见或建议，望不吝赐教，大家一起探讨。我的个人邮箱 wangzhichaomin@163.com。

最近也在看《微服务架构设计模式》，在设计过程中可能对结构随时进行调整，同时会记录结构设计过程中的典型错误及修正方式。

## 技术选型：

|                    |                               |                               |   |
| ------------------ | ----------------------------- |---|---|
| 网关               | Spring Cloud Gateway 2.0      | [官网](https://spring.io/projects/spring-cloud-gateway) | ✔ |
| 权限控制           | Spring Cloud Security Oauth2  | - | ✔ |
| 任务调度           | Xxl-Job                       | [官网](https://www.xuxueli.com/xxl-job/) | - |
| 服务治理及配置中心 | Spring Cloud Alibaba Nacos    | [官网](https://nacos.io/en-us/) | ✔ |
| 熔断及限流         | Spring Cloud Alibaba Sentinel | [GitHub](https://github.com/alibaba/Sentinel) | - |
| 分布式事务         | Seata                         | [官网](https://github.com/seata/seata) | - |
| ORM框架      | MyBatis-Plus        | [官网](https://baomidou.com/) | ✔ |
| 消息队列           | RocketMQ                         | -                        | - |
| 服务调用           | OpenFeign                     | -                    | ✔ |
| API文档           | Knife4j                     | [官网](https://doc.xiaominfo.com/guide/useful.html) | ✔ |
| 持续交付       | Jenkins(jenkinsfile方式),ansible | [Jenkinsfile](https://github.com/beifei1/fire-cloud/blob/master/Jenkinsfile) [Ansiable](http://www.ansible.com.cn/docs/playbooks.html) | ✔ |
| 持续集成 | Github, Nexus | - | ✔ |
| 缓存           | Redisson，J2cache(计划)  | - | ✔|
| 数据库连接池 | Hikari | - | ✔ |

## 调用关系：

![image](https://github.com/beifei1/fire-cloud/blob/master/deploy/Untitled%20Diagram.png)

## 服务说明：

|                    |              |      |
| ------------------ | ------------ | ---- |
| fire-gateway       | 网关服务     | ✔    |
| fire-oauth         | 授权服务     | ✔    |
| fire-user-consumer | 用户对外服务 | ✔    |
| fire-user-producer | 用户基础服务 | ✔    |
| fire-job           | 任务调度     | -    |

