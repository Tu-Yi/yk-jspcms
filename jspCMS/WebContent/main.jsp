<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>首页--jspCMS 1.0</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="vendor/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/public.css" media="all" />
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote layui-bg-green">
		<div id="nowTime"></div>
	</blockquote>
	<blockquote class="layui-elem-quote main_btn">
		<p>本模板基于jsp servlet mysql Layui实现，兼容IE8，支持除LayIM外所有的Layui组件。
		<p class="layui-red">郑重提示：本模版使用技术非常基础，只作为基础学习复习，技术交流使用，MIT开源协议。不管以何种形式获取的源码，请勿进行出售或者上传到任何素材网站，否则将追究相应的责任。</p>
		<p class="layui-blue">PS：这只是模版而不是定制开发，不能覆盖所有功能很正常，数据全部为动态数据，谢谢大家</p>
	</blockquote>
	<div class="layui-row layui-col-space10">
		<div class="layui-col-lg6 layui-col-md12" style="width:100%;">
			<blockquote class="layui-elem-quote title">发展历程&更新日志</blockquote>
			<div class="layui-elem-quote layui-quote-nm history_box magb0">
				<ul class="layui-timeline">
					<li class="layui-timeline-item">
						<i class="layui-icon layui-timeline-axis">&#xe756;</i>
						<div class="layui-timeline-content layui-text">
							<div class="layui-timeline-title">
								<h3 class="layui-inline">jspCMS 1.0基础版发布　</h3>
								<span class="layui-badge-rim">2019-08-22</span>
							</div>
							<ul>
								<li class="layui-blue">由于本人对设计和色差之类的不太感冒，所以一些布局和颜色搭配不是太完美，在此跟大家说声抱歉，大家可以根据自己的喜好进行一些调整。</li>
								
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="vendor/layui/layui.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
</body>
</html>