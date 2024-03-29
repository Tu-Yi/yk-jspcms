/**
 * 工具模块 Created by gameloft9 on 2017/12/6.
 */
layui.define([ 'jquery', 'layer' ], function(exports) {
	// 拿到模块变量
	var $ = layui.jquery, layer = parent.layer === undefined ? layui.layer
			: parent.layer;

	// 工具函数
	/**
	 * 格式化时间
	 * 
	 * @param _time
	 *            Date类型时间
	 * @return yyyy-mm-dd hh:mm
	 */
	function formatTime(_time) {
		var year = _time.getFullYear();
		var month = _time.getMonth() + 1 < 10 ? "0" + (_time.getMonth() + 1)
				: _time.getMonth() + 1;
		var day = _time.getDate() < 10 ? "0" + _time.getDate() : _time
				.getDate();
		var hour = _time.getHours() < 10 ? "0" + _time.getHours() : _time
				.getHours();
		var minute = _time.getMinutes() < 10 ? "0" + _time.getMinutes() : _time
				.getMinutes();
		return year + "-" + month + "-" + day + " " + hour + ":" + minute;
	}

	/**
	 * 判断是否为空
	 * 
	 * @param str
	 *            字符串
	 */
	function isBlank(str) {
		if (typeof str === 'undefined' || str === null || $.trim(str) === '') {
			return true;
		}

		return false;
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param arr
	 */
	function isEmpty(arr) {
		if (typeof arr === 'undefined' || arr === null || arr.length === 0) {
			return true;
		}

		return false;
	}
	function encode64(input) {
		var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef"
				+ "ghijklmnopqrstuv" + "wxyz0123456789+/" + "=";
		var output = "";
		var chr1, chr2, chr3 = "";
		var enc1, enc2, enc3, enc4 = "";
		var i = 0;
		do {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)
					+ keyStr.charAt(enc3) + keyStr.charAt(enc4);
			chr1 = chr2 = chr3 = "";
			enc1 = enc2 = enc3 = enc4 = "";
		} while (i < input.length);
		return output;
	}

	/**
	 * 获取应用上下文
	 */
	function getContext() {
		return $config.apiContext;
	}

	/**
	 * 获取资源上下文
	 */
	function getResUrl() {
		return $config.resUrl;
	}

	function getDefaultPassword() {
		return $config.default_password;
	}

	/**
	 * 获取查询字符串参数
	 */
	function getQueryParam() {
		var args = new Object();
		var query = location.search.substring(1);// 获取查询串
		var pairs = query.split("&");// 在逗号处断开
		for (var i = 0; i < pairs.length; i++) {
			var pos = pairs[i].indexOf('=');// 查找name=value
			if (pos == -1)
				continue;// 如果没有找到就跳过
			var argname = pairs[i].substring(0, pos);// 提取name
			var value = pairs[i].substring(pos + 1);// 提取value
			args[argname] = value;// 存为属性
		}
		return args;
	}

	/**
	 * json对象深拷贝
	 */
	function jsonClone(obj) {
		return JSON.parse(JSON.stringify(obj));
	}

	var $tool = {
		formatTime : formatTime,
		isBlank : isBlank,
		getContext : getContext,
		getResUrl : getResUrl,
		getDefaultPassword : getDefaultPassword,
		getQueryParam : getQueryParam,
		isEmpty : isEmpty,
		jsonClone : jsonClone,
		encode64:encode64
	};
	// 输出扩展模块
	exports('$tool', $tool);
});
