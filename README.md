[![GitHub license](https://img.shields.io/github/license/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/blob/master/LICENSE) [![GitHub forks](https://img.shields.io/github/forks/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/network)    [![GitHub stars](https://img.shields.io/github/stars/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/stargazers)   [![GitHub issues](https://img.shields.io/github/issues/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/issues)



## 项目简介：

基于Spring Cloud Netflix和Spring Cloud Alibaba的微服务业务框架。如果有不合理或需要讨论的地方，欢迎随时指出

Nacos: [http://192.144.176.31/nacos](http://192.144.176.31/nacos)  nacos/nacos666666

Api docs: http://gateway/doc.html

## 开发环境：

**Java SDK:** 1.8 +

**Maven**：3.10 + 

**Intellij IDEA** [官网](https://www.jetbrains.com/) 

###### 开发工具需要安装lombok插件

## 技术选型：

|                    |                               |                               |   |
| ------------------ | ----------------------------- |---|---|
| 网关               | Spring Cloud Gateway 2.0      | [官网](https://spring.io/projects/spring-cloud-gateway) | ✔ |
| 权限控制           | Spring Cloud Security Oauth2  | [官网](https://spring.io/projects/spring-cloud-security) | ✔ |
| 任务调度           | Xxl-Job                       | [官网](https://www.xuxueli.com/xxl-job/) | ✔ |
| 服务治理及配置中心 | Spring Cloud Alibaba Nacos    | [官网](https://nacos.io/en-us/) | ✔ |
| 熔断及限流         | Spring Cloud Alibaba Sentinel | [GitHub](https://github.com/alibaba/Sentinel) | ✔ |
| 分布式事务         | Seata                         | [官网](https://github.com/seata/seata) | - |
| ORM框架      | MyBatis-Plus        | [官网](https://baomidou.com/) | ✔ |
| 消息队列           | Redis                         | [官网](https://redis.io/)                        | - |
| 服务调用           | Spring Cloud OpenFeign                     | [官网](https://spring.io/projects/spring-cloud-openfeign)                    | ✔ |
| API文档           | Knife4j                     | [官网](https://doc.xiaominfo.com/guide/useful.html) | ✔ |
| 持续集成       | Jenkins,Ansible | [Jenkinsfile](https://github.com/beifei1/fire-cloud/blob/master/Jenkinsfile) / [Ansiable](http://www.ansible.com.cn/docs/playbooks.html) | ✔ |
| 缓存           | Redisson,Spring Cache  | [GitHub](https://github.com/redisson/redisson) | ✔|
| 数据库中间件           | ShardingSphere  | [官网](http://shardingsphere.apache.org/index_zh.html) | ✔|
| 健康监控           | Spring boot Admin  | [GitHub](https://github.com/codecentric/spring-boot-admin) | ✔|
| 日志           | Logback  | [官网](http://logback.qos.ch/) | ✔|

## 组件版本：

- **Spring Cloud**：Hoxton.SR3
- **Spring Cloud Alibaba**：2.2.1.RELEASE
- **Spring Boot**：2.2.5.RELEASE
- **Lombok**：1.18.12
- **MyBatis-Plus**：3.3.2
- **Knife4j**：2.0.4
- **Redisson**：3.13.4
- **ShardingSphere**：4.0.0-RC2

## 调用关系：

![image](https://github.com/beifei1/fire-cloud/blob/master/assets/flow.png)

## 目录结构：

|                       |              |      |
| --------------------- | ------------ | ---- |
| fire-service-gateway          | 网关服务     | ✔    |
| fire-service-uaa            | 授权服务     | ✔    |
| fire-user-consumer    | 用户对外服务 | ✔    |
| fire-user-producer    | 用户基础服务 | ✔    |
| fire-admin-job              | 任务调度管理     |  ✔   |
| fire-admin-boot              | 应用监控     |  ✔   |
| fire-message-consumer | 消息服务     | -    |
| fire-message-producer | 消息基础服务 | -    |

## 快速预览

```
- 利用Jenkinsfile，Ansiable，Gitlab等实现持续集成与交付
- Nacos Config使用多配置文件
- 在Jwt中加入自定义元信息
- OAuth2增加自定义授权模式实现短信,微信登录等特定业务场景
- 网关鉴权后，向下游服务传递用户信息，并在微服务API中注入
- 通过OpenFeign提供的继承特性减少重复编码
- Spring Cloud Gateway统一管理微服务API文档
- 使用knife4j提供的API增强在文档中进行接口排序, 标注常用信息等
- 使用knife4j自动加载自定义业务异常码
- Spring Cloud Gateway作为ResourceServer统一认证及权限控制，保护下游微服务应用
- 使用ResponseBodyAdvice处理响应，减少重复的Resp.ok(T)等类似操作
- 使用Jackson处理LocalDateTime响应为时间戳，前后端通过时间戳进行时间传输
- 使用ShardingSphere分库分表
- 拓展不同分布式锁实现
- 利用Mybatis-Plus快速实现乐观锁，逻辑删除
- 使用Spring Boot Admin监控微服务应用
- 自动业务异常及系统异常传递及处理
- 实现网关API白名单控制
- 在网关实现API防重放
- 利用Redis特性实现实验版本的消息队列
- 通过消息队列及Mysql实现最终一致性,并提供统一API服务
- 使用雪花Id根据构造函数生成分布式Id
  ...

```

## Jenkinsfile

在项目中使用Jenksinfile需要安装下列的Jenkins插件，[Jenkinsfile简介](https://www.cnblogs.com/stulzq/p/10115589.html)

**Pipeline：** 提供Jenkins Pipeline项目支持

**Ansible plugin：** 提供Ansible的Jenkins支持

**Config File Provider Plugin：** 配置文件管理插件，在pipeline中使用

**Credentials：** 凭证管理，在Jenkins中管理凭证，如SSH Key，UserName/Password等

**Email Extension Plugin：** 邮件增强，用于发送高级邮件模板，例如Html
