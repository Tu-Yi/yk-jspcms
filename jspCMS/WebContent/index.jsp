<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>layui后台管理模板 2.0</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="icon" href="${ctx }/assets/favicon.ico">
<link rel="stylesheet" href="vendor/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="css/index.css" media="all" />
<link rel="stylesheet" href="${ctx }/lib/dtree/dtree.css" media="all" />
<link rel="stylesheet" href="${ctx }/lib/dtree/font/dtreefont.css" media="all" />
</head>
<body class="main_body">
	<div class="layui-layout layui-layout-admin">
		<!-- 顶部 -->
		<div class="layui-header header">
			<div class="layui-main mag0">
				<a href="#" class="logo">jspCMS 1.0</a>
				<!-- 顶部右侧菜单 -->
				<ul class="layui-nav top_menu">
					<li class="layui-nav-item" id="userInfo"><a
						href="javascript:;"><img src="${ctx }/assets/face.jpg"
							class="layui-nav-img userAvatar" width="35" height="35"><cite
							class="adminName">灰喵</cite> <span class="layui-nav-more"></span></a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;" data-url="page/user/userInfo.html"><i
									class="seraph icon-ziliao" data-icon="icon-ziliao"></i><cite>个人资料</cite></a>
							</dd>
							<dd>
								<a href="javascript:;" data-url="page/user/changePwd.html"><i
									class="seraph icon-xiugai" data-icon="icon-xiugai"></i><cite>修改密码</cite></a>
							</dd>
							<dd>
								<a href="page/login/login.html" class="signOut"><i
									class="seraph icon-tuichu"></i><cite>退出</cite></a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>
		<!-- 左侧导航 -->
		<div class="layui-side layui-bg-black">
			<div class="user-photo">
				<a class="img" title="我的头像"><img src="${ctx }/assets/face.jpg"
					class="userAvatar"></a>
				<p>
					你好！<span class="userName">灰喵</span>, 欢迎登录
				</p>
			</div>
			<div class="navBar layui-side-scroll" id="navBar">
				<ul class="layui-nav layui-nav-tree" lay-filter="demo">
					<li class="layui-nav-item"><a data-url="main.jsp" target="pageFrame">后台首页</a></li>
					<li class="layui-nav-item"><a
						href="javascript:;">系统管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a data-url="page/user/userList.jsp">用户管理</a>
							</dd>
							<dd>
								<a data-url="page/role/roleList.jsp">角色管理</a>
							</dd>
							<dd>
								<a data-url="page/demo/components.html">其他组件</a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</div>
		<!-- 右侧内容 -->
		<div class="layui-body layui-form">
			<div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
				<div class="layui-tab-content clildFrame">
					<div class="layui-tab-item layui-show">
						<iframe frameborder="no" border="0" id="pageFrame" src="main.jsp"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 移动导航 -->
	<div class="site-tree-mobile">
		<i class="layui-icon">&#xe602;</i>
	</div>
	<div class="site-mobile-shade"></div>

	<script type="text/javascript" src="vendor/layui/layui.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
</body>
</html>