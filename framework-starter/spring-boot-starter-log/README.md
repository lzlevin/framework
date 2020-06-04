![image](./logo-simple.png)

![image](https://img.shields.io/badge/jdk-8%2B-blue.svg)

## 简介

提供framework-log快速启动，快速引入相关内容及功能。

## Quick Start

+ 引入`starter-log`依赖

```xml
<dependency>
	<groupId>com.vin</groupId>
	<artifactId>spring-boot-starter-log</artifactId>
	<version>版本号</version>
</dependency>
```

+ 引入`spring-aop`依赖

```xml
 <dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
	<version>版本号</version>
</dependency>
```

+ 需要记录日志的类或者方法上加入`@Log`注解即可，如下示例：

```java
@RestController
@Log(function = "组织机构")//类级别引入log
public class OrgController {
    @RequestMapping("list")
    @Log(action = "集合查询")//方法级别引入log
    public ApiResponse list(OrgDTO dto) {
        //业务代码
    }
}
```

## 配置

目前默认支持`StdoutLogListener`和`Slf4jLogListener`及`DatasourceLogListener`三种方式，使用者可根据情况实现自己的监听器并实现`LogListener`并注入`ApplicationContext`即可，或使用`Spring`的`@EventListener`机制，如下：

```java
@EventListener
public void myLogListener(LogBeforeEvent event)//支持LogBeforeEvent/LogAfterEvent/LogAfterThrowingEvent三种
{
	//do something
}
```

其中`Slf4jLogListener`默认启动，可通过以下配置启用或禁用默认的三种监听器，`yml`配置如下：

```yaml
vin:
  log:
    database: false #启用或禁用默认DatasourceLogListener
    stdout: false  #启用或禁用默认StdoutLogListener
    slf4j: true #启用或禁用默认Slf4jLogListener
```

并且还可以配置异步监听器的线程池，控制超时时间、最大线程、核心线程，在实现监听器的时候，两个方法在必要时需要重写，`getOrder()`和`isAsync()`，前者主要控制同步监听器的执行顺序，值越小则越先执行，后者主要控制是否为异步监听，异步监听则使用线程池异步触发，减轻日志记录压力。

```yaml
vin:
  log:
    timeout: 5 #超时时间为5秒
    core-pool-size: 2 #核心线程数为2
    max-pool-size: 8 #最大线程数为8
```

使用`@EventListener`则需要根据情况使用`@Async`注解控制是否异步。

`@Log`的几个属性介绍

+ `function`为模块/功能注解，如果方法此属性为空，则采用类级别`@Log`注解的属性
+ `action`操作描述，如果方法有`@Log`注解则采用方法的信息。
+ `description`描述信息
+ `ignore`是否忽略记录日志，默认为`false`，类级别优先，即类级别配置忽略日志，则忽略方法级配置，否则根据方法级配置来决定是否忽略日志
+ `request`是否忽略请求日志，默认为`true`及日志事件中不保存`request`请求信息
+ `response`同理，是否忽略响应日志