<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="loginHtml">
<head>
	<meta charset="utf-8">
	<title>jspCMS</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="assets/favicon.ico">
	<link rel="stylesheet" href="vendor/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/public.css" media="all" />
	<style>
	.container:after {
	    content: '\20';
	    clear: both;
	    *zoom: 1;
	    display: block;
	    height: 0;
    }
	</style>
</head>
<body class="loginBody">
<!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
<!--[if lt IE 9]>
  <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
  <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->  
	<form class="layui-form">
		<div class="login_face"><img src="assets/face.jpg" class="userAvatar"></div>
		<div class="layui-form-item input-item">
			<label for="userName">用户名</label>
			<input type="text" placeholder="请输入用户名" autocomplete="off" id="username" name="username" class="layui-input" lay-verify="username">
		</div>
		<div class="layui-form-item input-item">
			<label for="password">密码</label>
			<input type="password" placeholder="请输入密码" autocomplete="off" id="password" name="password" class="layui-input" lay-verify="password">
		</div>
		<div class="container">
			<div style="float:left;width:calc(100% - 90px);" class="layui-form-item input-item" id="imgCode">
				<label for="code">验证码</label>
				<input type="text" placeholder="请输入验证码" autocomplete="off" id="code" name="code" class="layui-input" lay-verify="required" lay-reqtext="验证码不能为空">
			</div>
			<div>
				<img id="imgObj" alt="验证码" src="${ctx }/getCode">
			</div>
		</div>
		<div class="layui-form-item">
			<button class="layui-btn layui-block" lay-filter="login" lay-submit="">登录</button>
		</div>
	</form>
	<script type="text/javascript" src="vendor/layui/layui.js"></script>
	<script type="text/javascript" src="lib/config.js"></script>
	<script type="text/javascript" src="lib/md5.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>