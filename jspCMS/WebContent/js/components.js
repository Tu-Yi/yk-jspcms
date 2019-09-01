layui.config({
    base: $config.resUrl+'lib/'//定义基目录
}).extend({
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'tree','$api', 'jquery', '$tool', 'laydate','upload'],function(){
	var form = layui.form,
    layer = parent.layer === undefined ? layui.layer : parent.layer,
    $ = layui.jquery,
    $tool = layui.$tool,
    $api = layui.$api,
    laydate = layui.laydate,
    upload = layui.upload;
	
	laydate.render({
	    elem: '#test6',
	    range: true,
	    done: function(value, date1,date2){
	    	console.log(value,date1,date2)
	      layer.alert('你选择的日期是：' + value + '<br>获得的对象1是' + JSON.stringify(date1)+'<br>获得的对象2是' + JSON.stringify(date2));
	    }
	  });
	
	 //普通图片上传
	  var uploadInst = upload.render({
	    elem: '#test1'
	    ,url: 'http://118.24.175.34:3000/api/file/upload'
	    ,field:'files',
	    before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#demo1').attr('src', result); //图片链接（base64）
	      });
	    }
	    ,done: function(res){
	      console.log(res)
	    }
	    ,error: function(){
	      //演示失败状态，并实现重传
	      var demoText = $('#demoText');
	      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
	      demoText.find('.demo-reload').on('click', function(){
	        uploadInst.upload();
	      });
	    }
	  });
	
	//多图片上传
	  upload.render({
	    elem: '#test2'
	    ,url: 'http://118.24.175.34:3000/api/file/uploadMultiple',
	    field:'files'
	    ,multiple: true
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#demo2').append('<img width=100px height=100px src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
	      });
	    }
	    ,done: function(res){
	      console.log(res)
	    }
	  });
	  
	//图片上传腾讯云
	  var uploadInst3 = upload.render({
	    elem: '#test3'
	    ,url: 'http://118.24.175.34:3000/api/cos/upload'
	    ,field:'clouds',
	    before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#demo3').attr('src', result); //图片链接（base64）
	      });
	    }
	    ,done: function(res){
	      console.log(res)
	    }
	    ,error: function(){
	      //演示失败状态，并实现重传
	      var demoText3 = $('#demoText3');
	      demoText3.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
	      demoText3.find('.demo-reload').on('click', function(){
	        uploadInst3.upload();
	      });
	    }
	  });

	form.verify({
		
	});
	 
    form.on("submit(addUser)",function(data){
    	
    	
    	
    	 
        return false;
    })

})