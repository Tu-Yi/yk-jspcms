var $, tab, dataStr, layer;
layui.use([ "bodyTab", "form", "element", "layer", "jquery" ], function() {
	var form = layui.form, element = layui.element;
	$ = layui.$;
	layer = parent.layer === undefined ? layui.layer : top.layer;

	element.on('nav(demo)', function(elem) {
		console.log(elem)
		if(window.ActiveXObject){
			$("#pageFrame").attr("src",elem[0]["data-url"]);
		}else{
			$("#pageFrame").attr("src",elem[0].dataset.url);
		}
	});
});
