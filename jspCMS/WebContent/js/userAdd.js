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
	var DemoTree =dtree.renderSelect({
		elem: "#selTree1",
		width: "80%",
		height: "full-600",
		line: true,
		ficon: ["2","2"],
		icon: "-1",
		url: $tool.getContext() + 'DepartmentServlet?method=getDepartmentTree',
		dataStyle: "layuiStyle",  //使用layui风格的数据格式
		response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
		toolbar: false,
		menubar: false,
		checkbar: false,
		load:false
	});
	 function loadRoleList() {
		 var req = {
            page: 1,
            limit: 999
        };
		 $api.GetRoleList(req,function (res) {
			 console.log(res)
	         var data = eval("("+res+")");
	         if (data.length > 0) {
	             var roleHtml = "";
	             for (var i = 0; i < data.length; i++) {
	            	 roleHtml += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
	             }

	             $("select[name='role']").append(roleHtml);
	             form.render('select');//重新绘制表单，让修改生效
	         }
	     });
	 }
	 loadRoleList();

	 form.on('switch(switchEnabled)', function (data) {
	        $("#enabled").val(data.elem.checked?1:0);
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
	  console.log(data.value); //得到被选中的值
	  selectRole=data.value
	});
	 
    form.on("submit(addUser)",function(data){
    	
    	
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
    		username:data.field.username,
    		realname:data.field.realname,
    		enabled:$("#enabled").val(),
    		department_id:curNode.nodeId,
    		role_id:selectRole
    	}
    	
    	
        //弹出loading
        var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
    	
    	$api.AddUser(req,function (res) {
    		
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

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})