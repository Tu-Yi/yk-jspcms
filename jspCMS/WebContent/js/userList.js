layui.config({
    base: $config.resUrl+'lib/'//定义基目录
}).extend({
    $tool: 'tool',
    $api:'api',
    dtree:'dtree/dtree'
}).use(['form', 'layer', '$api','jquery', 'table', 'laytpl', '$tool', 'dtree'],function(){
	var form = layui.form,
    layer = parent.layer === undefined ? layui.layer : parent.layer,
    $ = layui.jquery,
    laytpl = layui.laytpl,
    $tool = layui.$tool,
    table = layui.table,
    $api = layui.$api,
    dtree = layui.dtree;

	var selectDept=[];
	
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
	
    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : $tool.getContext() + 'UserServlet?method=findAll',
        cellMinWidth : 95,
        page : true,
        limits : [10,20],
        limit : 10,
        loading: true,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'username', title: '用户名', minWidth:100, align:"center"},
            {field: 'imapath', title: '用户头像', minWidth:200, align:'center',templet:function(d){
                return '<img width=30px height=30px src="../../assets/face.jpg" />';
            }},
            {field: 'realname', title: '姓名', align:'center'},
            {field: 'enabled', title: '用户状态',  align:'center',templet:function(d){
                return d.enabled == "1" ? "正常使用" : "限制使用";
            }},
            {field: 'deptName', title: '部门', align:'center'},
            {field: 'roleName', title: '角色', align:'center'},
            {title: '操作', minWidth:270, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });
    
    function serverArray(){
		selectDept=[]
		var curNode = dtree.getNowParam(DemoTree);
		selectDept.push(curNode.nodeId)
		var arr = dtree.getChildParam(DemoTree, curNode.nodeId)
		if(arr.length>0){
			for(var item = 0;item < arr.length;item++){
		  		  selectDept.push(arr[item].nodeId)
		  	  }
		}
  	  
  	}

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
    	
        if($(".searchVal").val() != '' || dtree.getNowParam(DemoTree)){
        	serverArray();
        	var ids = selectDept.join()
        	console.log(ids)
        	tableIns.reload({
        		method: 'post',
                where:{
                    username: $(".searchVal").val(),
                    deptIds: ids
                },
                page:{
		            curr:1
		        }
            });
        }else{
        	tableIns.reload({
                where:{},
                page:{
		            curr:1
		        }
            });
        }
    });
    
    //导出
    $(".export_btn").on("click",function(){
    	
    	var username = $(".searchVal").val();
    	serverArray();
    	var ids = selectDept.join()
    	
    	console.log(username,ids)
    	location.href="/jspCMS/UserServlet?method=exportData&username="+username+"&deptIds="+ids;
    	
    });
    
    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加用户",
            type : 2,
            content : "userAdd.html",
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        addUser();
    })
    
  //添加用户
    function editUser(edit){
        var index = layui.layer.open({
            title : "编辑用户",
            type : 2,
            content : "userEdit.html?id="+edit,
            success : function(layero, index){
                setTimeout(function(){
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            ids = [];
        if(data.length > 0) {
            for (var i in data) {
            	ids.push(data[i].id);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
            	var delIds = ids.join()
            	var req = {
                    ids: delIds
                };

                $api.DeleteById(req,function (data) {
                    layer.msg("删除成功",{time:1000},function(){
                    	tableIns.reload();
                        layer.close(index);
                    });
                });
                
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
    	var row = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        if(layEvent === 'edit'){ //编辑
        	editUser(row.id);
        }else if(layEvent === 'initPwd'){
        	initPwd(row.id);
        }else if(layEvent === 'del'){ //删除
        	delUser(row.id)
        }else if(layEvent === 'enable'){ //启用禁用
            enableUser(row.id,$(this));
        }
    });
    
    function enableUser(id,_this){
    	var enableText = "是否确定禁用此用户？",btnText = "已禁用",enable=0;
	    if(_this.text()=="已禁用"){
	    	enableText = "是否确定启用此用户？",
	        btnText = "已启用";
	    	enable=1
	    }
	    layer.confirm(enableText,{
	        icon: 3,
	        title:'系统提示',
	        cancel : function(index){
	            layer.close(index);
	        }
	    },function(index){
            //向服务端发送删除指令
            var req = {
                id: id,
                enable:enable
            };
	    	$api.EnableUser(req,function (res) {
            	//let message = JSON.parse(res)
            	var message=eval("("+res+")"); 
    			
    			if(message.errno){
    				layer.msg(message.msg, {
      				  icon: 1,
      				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
      				},function(){
      					_this.text(btnText);
      				});
    			}else{
    				layer.msg(message.msg, {
    				  icon: 2,
    				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
    				}); 
    				
    			}
            });
	        layer.close(index);
	    },function(index){
	        layer.close(index);
	    });
    }
    
    function delUser(id){
    	layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.DeleteUser(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }
    
    function initPwd(id){
    	layer.confirm('确认初始化密码吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.InitPwd(req,function (res) {
            	//let message = JSON.parse(res)
    			var message=eval("("+res+")"); 
    			if(message.errno){
    				layer.msg(message.msg, {
      				  icon: 1,
      				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
      				});
    			}else{
    				layer.msg(message.msg, {
    				  icon: 2,
    				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
    				}); 
    				
    			}
            });
        });
    }
    

})
