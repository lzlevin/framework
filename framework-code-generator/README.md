#代码生成器
通过数据库提供基本的代码接口及增删改查等功能。
##java后端代码
生成以下基础代码：
+ entity实体类
+ DTO类
+ VO类
+ mapper类
+ mapper xml文件
+ business接口
+ businessImpl实现类
+ controller层
+ service接口
+ serviceImpl实现类

只需要配置`config.properties`文件然后运行`Generator.main`即可，如下所示:
```properties
##数据源配置
jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/framework?useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT%2B8
jdbc.username=framework
jdbc.password=framework
##包名配置
package=com.vin.framework.admin
##注释配置
author=levin
version=1.0.0
##输出配置
dir=
##数据库配置
tables=org
```
##特点
+ 如果具有父子关系的表结构(包含id和parent_id)自动生成树形结构接口
+ 如果具有名称的表结构(包含id,name)自动生成简单查询接口（仅返回这两个属性，方便下拉框等展示）
+ 如果具有排序的表结构(包含seq)自动在接口中加入排序字段
+ 表字段注释包含枚举类型说明自动生成枚举类，如userType字段注释为1:微信用户,2:QQ用户，则生成如下代码
```java
@AllArgsConstructor
public enum  UserType implements IEnum<String,String>
{
   WXYH("1","微信用户"),
   QQYH("2","QQ用户");
   @Getter
   private String key;
   @Getter
   private String value;
}
```