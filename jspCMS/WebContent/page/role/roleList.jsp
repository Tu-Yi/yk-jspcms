<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户中心--layui后台管理模板 2.0</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${ctx }/vendor/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/css/public.css" media="all" />
	<link rel="stylesheet" href="${ctx }/lib/dtree/dtree.css" media="all" />
	<link rel="stylesheet" href="${ctx }/lib/dtree/font/dtreefont.css" media="all" />
</head>
<body class="childrenBody">
	<table id="roleList" lay-filter="roleList"></table>

	<!--操作-->
	<script type="text/html" id="roleListBar">
		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="assignPermissions">分配权限</a>
	</script>
<script type="text/javascript" src="${ctx }/vendor/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/lib/config.js"></script>
<script type="text/javascript" src="${ctx }/js/roleList.js"></script>
</body>
</html>