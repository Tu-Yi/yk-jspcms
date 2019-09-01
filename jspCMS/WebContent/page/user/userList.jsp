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
<form class="layui-form">
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" class="layui-input searchVal" placeholder="请输入搜索的内容" />
				</div>
				<div class="layui-input-inline">
					<ul id="selTree1" class="dtree" data-id="0"></ul>
				</div>
				<a class="layui-btn search_btn" data-type="reload">搜索</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addNews_btn">添加用户</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-warm export_btn">导出数据</a>
			</div>
		</form>
	</blockquote>
	<table id="userList" lay-filter="userList"></table>

	<!--操作-->
	<script type="text/html" id="userListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="enable">
		{{#  if(d.enabled == 1){ }}
    		已启用
  		{{#  } else { }}
    		已禁用
  		{{#  } }}
		</a>
		<a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="initPwd">重置密码</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
</form>
<script type="text/javascript" src="${ctx }/vendor/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/lib/config.js"></script>
<script type="text/javascript" src="${ctx }/js/userList.js"></script>
</body>
</html>