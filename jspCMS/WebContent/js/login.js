layui.config({
    base: $config.resUrl+'lib/'//定义基目录
}).extend({
    $tool:'tool',
    $api:'api'
}).use([ 'form', 'layer', 'jquery','$tool','$api' ], function() {
	var form = layui.form, 
	layer = layui.layer,
	$ = layui.jquery;
	$tool=layui.$tool,
    $api = layui.$api;

	// 自定义验证规则
	form.verify({
		username : function(value) {
			if (value.length < 5 || value.length>20) {
				return '用户名必须6到20位';
			}
		},
		password : [ /^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格' ]
	});

	// 登录按钮
	form.on("submit(login)", function(data) {
		$(this).text("登录中...").attr("disabled", "disabled").addClass(
				"layui-disabled");
		console.log(data.field)
		//let {username,password,code} = data.field
		var _this=this;
		var password = $tool.encode64(data.field.password)
		console.log(password)
		$api.Login({username:data.field.username,password:password,code:data.field.code},function (res) {
			//let message = JSON.parse(res);
			var message=eval("("+res+")"); 
			
			if(message.errno){
				delete message.t.password
				delete message.t.salt
				window.sessionStorage.setItem("user",JSON.stringify(message.t));
				window.location = "index.jsp"
			}else{
				layer.msg(message.msg, {
				  icon: 2,
				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
				}, function(){
					$(_this).text("登录").attr("disabled", false).removeClass(
					"layui-disabled");
					changeImg();
				}); 
				
			}
       },function(err){
    	   layer.msg('网络错误', {
			  icon: 2,
			  time: 2000 //2秒关闭（如果不配置，默认是3秒）
			}, function(){
				$(_this).text("登录").attr("disabled", false).removeClass(
				"layui-disabled");
				changeImg();
			});
       });
		
		return false;
	})
	
	$("#imgObj").on("click",function(){
		console.log(1)
		changeImg();
	})
	
	function chgUrl(url) {
		console.log(1)
        var timestamp = (new Date()).valueOf();
        url = url.substring(0, 20);
        if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }
	
	function changeImg(){
		console.log(1)
        var imgSrc = $("#imgObj");    
        var src = imgSrc.attr("src");        
        imgSrc.attr("src", chgUrl(src));
    }

	// 表单输入效果
	$(".loginBody .input-item").click(function(e) {
		e.stopPropagation();
		$(this).addClass("layui-input-focus").find(".layui-input").focus();
	})
	$(".loginBody .layui-form-item .layui-input").focus(function() {
		$(this).parent().addClass("layui-input-focus");
	})
	$(".loginBody .layui-form-item .layui-input").blur(function() {
		$(this).parent().removeClass("layui-input-focus");
		if ($(this).val() != '') {
			$(this).parent().addClass("layui-input-active");
		} else {
			$(this).parent().removeClass("layui-input-active");
		}
	})
	
})
