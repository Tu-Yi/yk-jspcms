layui.config({
    base: $config.resUrl+'lib/'//定义基目录
}).extend({
    $tool: 'tool',
    $api:'api',
    dtree:'dtree/dtree'
}).use(['form', 'layer', 'tree','$api', 'jquery', '$tool', 'dtree'],function(){
	var form = layui.form,
    layer = parent.layer === undefined ? layui.layer : parent.layer,
    $ = layui.jquery,
    $tool = layui.$tool,
    $api = layui.$api,
    dtree = layui.dtree;
	
	var selectRole;
	var userInfo;
	var userEnabled;
	var queryArgs = $tool.getQueryParam(); //获取查询参数
    var userId = queryArgs["id"];
	
	/**
     * 初始化用户信息
     * */
    function initUserInfo() {

      var req = {
        id: userId
      };

      $api.GetUser(req,{async: false}, function(res) {
   	   console.log(res)
   	   userInfo = eval("("+res+")");
   	   $("[name='username']").val(userInfo.username);
   	   $("[name='realname']").val(userInfo.realname);
   	   $("input[name='enabled']").prop("checked",userInfo.enabled);
   	   form.render("checkbox");
      });
    }
    initUserInfo()
    
    
	var DemoTree =dtree.renderSelect({
		elem: "#selTree1",
		width: "80%",
		height: "full-600",
		line: true,
		ficon: ["2","2"],
		icon: "-1",
		url: $tool.getContext() + 'DepartmentServlet?method=getDepartmentTree',
		async: false,
		dataStyle: "layuiStyle",  //使用layui风格的数据格式
		response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
		toolbar: false,
		menubar: false,
		checkbar: false,
		load:false,
		selectValue: userInfo.deptName,
		done: function(){
		    dtree.dataInit("selTree1", userInfo.department_id);
		}
	});
	 function loadRoleList() {
		 var req = {
            page: 1,
            limit: 999
        };
		 $api.GetRoleList(req,function (res) {
	         var data = eval("("+res+")");
	         if (data.length > 0) {
	             var roleHtml = "";
	             for (var i = 0; i < data.length; i++) {
	            	 roleHtml += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
	             }

	             $("select[name='role']").append(roleHtml);
	             console.log("select: ",userInfo.role_id)
	             selectRole=userInfo.role_id
	             $("select[name='role']").val(userInfo.role_id);
	             form.render('select');//重新绘制表单，让修改生效
	         }
	     });
	 }
	 loadRoleList();

	 form.on('switch(switchEnabled)', function (data) {
		 userEnabled = data.elem.checked?1:0;
	    });
	 form.verify({
		username : function(value) {
			if (value.length < 5 || value.length>20) {
				return '用户名必须6到20位';
			}
		},
		realname : function(value) {
			if (value.length > 10) {
				return '真实姓名不能大于10位';
			}
		}
	});
	 form.on('select(role)', function(data){
	  selectRole=data.value
	});
	 
    form.on("submit(editUser)",function(data){
    	
    	
    	var curNode = dtree.getNowParam(DemoTree)
    	console.log(curNode)
    	if($.isEmptyObject(curNode)){
    		layer.msg("请选择所属组织机构");
            return false;
    	}
    	
    	if(!selectRole){
    		layer.msg("请选择用户角色");
            return false;
    	}
    	
    	var req = {
    		id:userId,
    		username:data.field.username,
    		password:$tool.getDefaultPassword(),
    		realname:data.field.realname,
    		enabled:userEnabled,
    		department_id:curNode.nodeId,
    		role_id:selectRole
    	}
    	
    	console.log(req)
    	
    	
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	
    	$api.UpdateUser(req,function (res) {
    		
    		var message=eval("("+res+")"); 
			if(message.errno){
				top.layer.close(index);
	            top.layer.msg(message.msg);
	            layer.closeAll("iframe");
	            //刷新父页面
	            parent.location.reload();
			}else{
				top.layer.close(index);
				layer.msg(message.msg, {
				  icon: 2,
				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
				}); 
				
			}
   		 	
        });
    	 
        return false;
    })

})