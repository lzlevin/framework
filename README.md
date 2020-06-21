![image](logo-simple.png)

![image](https://img.shields.io/badge/jdk-8%2B-blue.svg) ![image](https://img.shields.io/badge/mybatis-plus-orange.svg?link=https://github.com/baomidou/mybatis-plus) <img src="jetbrains-variant-3.png" width="100px" height="115px" onclick ="window.open('http://https://www.jetbrains.com/?from=https://github.com/lzlevin/framework');"/>

## 简介

提供常用框架的扩展及业务的快速开发，提供日志、快速接口开发、任务等功能，详见[中文文档](https://lzlevin.github.io/framework/) 。

## 模块介绍

+ framework-admin 提供基础项目功能，如字典、参数、菜单（功能）、权限、用户等基础接口及相关实现。
+ framework-code-generator 代码生成器，主要为基于framework-mvc的项目提供POJO及相关服务的快速生成。
+ framework-common 提供一些基础的接口等供其他模块使用
+ framework-core 提供框架核心类及接口，供其他子模块应用及实现相应方法等。
+ framework-log 提供基础的日志切面，方便对于方法的日志打印，可方便的扩展各种记录器。
+ framework-mvc 基于spring-mvc、mybatis-plus做的mvc抽象，提供一些基础的实现类及功能。
+ framework-mybatis 扩展封装mybatis plus，扩展methods、mapper等。
+ framework-task 提供多种任务类，可方便的应用于日常开发及数据同步中。
+ framework-utils 常用工具类。
+ framework-validator 基于hibernate-validator提供更多功能的验证。
+ framework-starter 此项目的各种spring boot starter，提供开箱即用：
  + spring-boot-starter-mybatis 提供framework-mybatis的开箱即用，包含了mybatis plus及mybatis
  + spring-boot-starter-log 提供framework-log的开箱即用，默认启用sfl4j日志监听器
  + spring-boot-starter-admin 提供framework-admin的开箱即用，项目引用它即可实现基础的权限、用户、菜单等功能

## 快速开始

```xml
<dependency>
  <groupId>com.vin</groupId>
  <artifactId>spring-boot-starter-somefunction....</artifactId>
  <version>${latest.version}</version>
</dependency>
```