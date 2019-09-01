layui.config({
	base: $config.resUrl + 'lib/' //定义基目录
}).extend({
	$tool: 'tool',
	$api: 'api',
    dtree:'dtree/dtree'
}).use(['form', 'layer', '$api', 'jquery', 'table', 'laytpl', '$tool', 'dtree'], function () {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		$tool = layui.$tool,
		table = layui.table,
		$api = layui.$api,
		dtree = layui.dtree;

	//用户列表
	var tableIns = table.render({
		elem: '#roleList',
		url: $tool.getContext() + 'RoleServlet?method=findRole',
		cellMinWidth: 95,
		page: true,
		limits: [10, 20],
		limit: 999,
		loading: true,
		id: "roleListTable",
		cols: [
			[{
					field: 'name',
					title: '角色名',
					minWidth: 100,
					align: "center"
				},
				{
					field: 'remark',
					title: '备注',
					align: 'center'
				},
				{
					field: 'enabled',
					title: '角色状态',
					align: 'center',
					templet: function (d) {
						return d.enabled == "1" ? "正常使用" : "限制使用";
					}
				},
				{
					title: '操作',
					minWidth: 270,
					templet: '#roleListBar',
					fixed: "right",
					align: "center"
				}
			]
		]
	});


	//列表操作
	table.on('tool(roleList)', function (obj) {
		var row = obj.data; //获得当前行数据
		var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
		var tr = obj.tr; //获得当前行 tr 的DOM对象

		if (layEvent === 'assignPermissions') { //编辑
			assignPermissions(row.id);
		}
	});

	function assignPermissions(rid) {
		layer.open({
			type: 1, //type:0 也行
			title: "选择权限",
			offset: '100px',
			content: '<ul id="permissionTree" class="dtree" data-id="0"></ul>',
			btn: ['确认选择'],
			success: function (layero, index) {
				var DTree = dtree.render({
					obj: $(layero).find("#permissionTree"),
					url: $tool.getContext() + "PermissionServlet?method=getPermissionTree",
					dataStyle: "layuiStyle",  //使用layui风格的数据格式
					response:{message:"msg",statusCode:0},  //修改response中返回数据的定义
					checkbar: true, // 开启复选框
					done: function (data, obj) { //使用异步加载回调
						var curUser = window.sessionStorage.getItem('user');
						if(curUser){
							var user=eval("("+curUser+")"); 
							var permissions = user.permissions
							dtree.chooseDataInit("permissionTree", permissions);
						}
					}
				});
			},
			yes: function (index, layero) {
				var flag = true;
				var params = dtree.getCheckbarNodesParam("permissionTree"); // 获取选中值
				if (params.length == 0) {
					layer.msg("请至少选择一个节点", {
						icon: 2
					});
					flag = false;
					return false;
				}

				var ids = [],
					names = [];
				for (var key in params) {
					var param = params[key];
					ids.push(param.nodeId);
					names.push(param.context);
				}
				console.log(ids);
				console.log(names);
				var permissions = ids.join()
				var req = {
					rid:rid,
					permissions:permissions
				}
				$api.UpdateRP(req,function (res) {
	            	//let message = JSON.parse(res)
	    			var message=eval("("+res+")"); 
	    			if(message.errno){
	    				layer.msg(message.msg, {
	      				  icon: 1,
	      				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
	      				},function(){
	      					if (flag) {
	      						layer.close(index);
	      					}
	      				});
	    			}else{
	    				layer.msg(message.msg, {
	    				  icon: 2,
	    				  time: 2000 //2秒关闭（如果不配置，默认是3秒）
	    				}); 
	    				
	    			}
	            });
				
			}
		});
	}


})