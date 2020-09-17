[![GitHub license](https://img.shields.io/github/license/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/blob/master/LICENSE) [![GitHub forks](https://img.shields.io/github/forks/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/network)    [![GitHub stars](https://img.shields.io/github/stars/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/stargazers)   [![GitHub issues](https://img.shields.io/github/issues/beifei1/fire-cloud?style=flat-square)](https://github.com/beifei1/fire-cloud/issues)



## 项目简介：

基于Spring Cloud Netflix和Spring Cloud Alibaba的微服务业务框架。如果有不对或者不合理的地方，欢迎随时指出。给我发邮件或者提issue都可以,也希望对你有所帮助

## 开发环境：

**Java SDK:** 1.8 +

**Intellij IDEA** [官网](https://www.jetbrains.com/) 

Jetbrains对于开源项目也有免费的LICENSE支持。如果你有开源项目，并且符合相关条件，可以到官网申请免费的全家桶授权码

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
| 消息队列           | RocketMQ                         | [官网](http://rocketmq.apache.org/)                        | - |
| 服务调用           | Spring Cloud OpenFeign                     | [官网](https://spring.io/projects/spring-cloud-openfeign)                    | ✔ |
| API文档           | Knife4j                     | [官网](https://doc.xiaominfo.com/guide/useful.html) | ✔ |
| 持续交付       | Jenkins,Ansible | [Jenkinsfile](https://github.com/beifei1/fire-cloud/blob/master/Jenkinsfile) / [Ansiable](http://www.ansible.com.cn/docs/playbooks.html) | ✔ |
| 缓存           | Redisson,Spring Cache  | [GitHub](https://github.com/redisson/redisson) | ✔|
| 数据库中间件           | ShardingSphere  | [官网](http://shardingsphere.apache.org/index_zh.html) | ✔|
| 健康监控           | Spring boot Admin  | [GitHub](https://github.com/codecentric/spring-boot-admin) | ✔|

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

## 服务说明：

|                       |              |      |
| --------------------- | ------------ | ---- |
| fire-service-gateway          | 网关服务     | ✔    |
| fire-service-oauth            | 授权服务     | ✔    |
| fire-user-consumer    | 用户对外服务 | ✔    |
| fire-user-producer    | 用户基础服务 | ✔    |
| fire-admin-job              | 任务调度管理     |  ✔   |
| fire-admin-monitor              | 应用健康监控     |  ✔   |
| fire-message-consumer | 消息服务     | -    |
| fire-message-producer | 消息基础服务 | -    |

## 快速预览

```
- 利用Jenkinsfile，Ansiable，Gitlab等实现持续集成与交付
- Nacos Config使用多配置文件
- 在Jwt中加入自定义元信息
- OAuth2增加自定义授权模式
- 网关鉴权后，向下游服务传递用户信息，并在微服务中API中注入
- 通过OpenFeign提供的继承特性减少重复编码
- Spring Cloud Gateway统一管理微服务API文档
- Spring Cloud Gateway作为ResourceServer统一认证及权限控制，保护下游微服务应用
- 使用ResponseBodyAdvice处理响应，减少重复的Resp.ok(T)等类似操作
- 使用Jackson处理LocalDateTime响应为时间戳，前后端通过时间戳进行时间传输
- 使用ShardingSphere分库分表
- 使用DistributedLock接口用于拓展不同分布式锁实现，见RedisDistributedLock，基于Redisson
- 利用Mybatis-Plus快速实现乐观锁，逻辑删除
- 使用Spring Boot Admin监控微服务应用
- 利用ControllerAdvice实现Producer->Consumer->前端的自动业务及系统异常传递
- 通过配置中心实现网关白名单控制
- 通过timestamp, nonce等实现简单的防重放
  持续更新中...

```

## Jenkinsfile及Jenkins插件

在项目中使用jenksinfile需要安装几个jenkins插件，这里是jenkinsfile的[简介](https://www.cnblogs.com/stulzq/p/10115589.html)。如果希望深入学习，推荐[《Jenkins 2.X实践指南》](https://item.jd.com/12512889.html)。作者翟志军

#### Pipeline：

提供Jenkins Pipeline项目支持

#### Ansible plugin：

提供Ansible的jenkins支持

#### Config File Provider Plugin：

配置文件管理插件，支持多种类型

```javascript
configFileProvider([configFile(fileId: 'd4231502-faae-45f4-b0d9-c4bff6e15692',targetLocation: 'setting.xml', variable: 'MAVEN_GLOBALE_SETTING')]) {
    sh "mvn -f ${params.pom_path} -s $MAVEN_GLOBALE_SETTING package -Dmaven.test.skip=true"
}
```

#### Credentials：

凭证管理插件，在Jenkins中管理凭证，如SSH Key，UserName/Password等

```javascript
git credentialsId: "${_github_credentialsId}", url: "${params.repo_addr}", branch: "${params.repo_branch}"
```

#### Email Extension Plugin：

邮件增强，用于发送高级邮件模板，例如Html
