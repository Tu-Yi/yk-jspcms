# jspCMS
基于java技术研发的后台管理系统框架，代码完全开源，MIT授权协议。

愿景：打造一个高性能、高安全、高兼容的后台框架，用于JAVA类技术的学习和总结。

## 演示地址

http://118.24.175.34:8080/jspCMS/login.jsp

用户名：niliv1

密码：123456

运行环境：centos7.5 tomcat9 jdk1.8 mysql5.7 nodejs pm2

## 下版本升级(暂定)

ssm，rbac，shiro，日志，异常，layui框架更换

## 目前版本 v0.0.1 2019-9-1

**技术选型**   

mysql5.7、javaSDK1.8、JSP、servlet、jquery、layui  

此版本后端使用最原始的jsp+servlet开发，下版本改为ssm

**主要控件**  

apache.poi：实现excel导入导出

google.gson：JSON解析

apache.commons.codec：解析base64 url传输加密

apache.commons.lang3：字符串处理 用于生成密码加盐

dtree：基于layui的第三方树型控件

layer：基于layui的弹层控件

upload： 基于layui的上传控件

laydate：基于layui的日期控件

cos-nodejs-sdk-v5：腾讯云COS-SDK

**已实现功能**  

登录、验证码、url传输加密、用户密码加盐加密、过滤器验证登录、session

用户列表、分页、搜索、下拉组织结构树、添加编辑、批量删除、导出数据、禁用启用、重置密码、删除

角色管理、分配权限、权限树

单文件上传、多文件上传、文件上传腾讯云COS






