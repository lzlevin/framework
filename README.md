![image](./logo.png)

![image](https://img.shields.io/badge/jdk-8%2B-blue.svg)

## 简介

提供常用框架的扩展及业务的快速开发。

+ 扩展mybatis plus，扩展Entity及Mapper，实现业务严格统一，支持spring boot starter方式快速启动。

## 模块介绍

+ framework-core 提供框架核心类及接口，供其他子模块应用及实现相应方法等。
+ framework-mybatis 扩展封装mybatis plus，扩展methods、mapper等
+ framework-utils 常用工具类
+ framework-starter 此项目的各种spring boot starter，提供开箱即用
  + spring-boot-starter-mybatis 提供framework-mybatis的开箱即用，包含了mybatis plus及mybatis

## 文档

[中文文档](https://doc.lzlevin.cn/framework) | 各个模块详见各README

## 快速开始

+ 增加依赖
  + framework-mybatis
  ```xml
  <dependency>
    <groupId>com.vin</groupId>
    <artifactId>spring-boot-starter-mybatis</artifactId>
    <version>1.0.0</version>
  </dependency>
  ```

## 其他