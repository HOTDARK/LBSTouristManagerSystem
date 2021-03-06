// JavaScript Document
function getProjectName(){
	var pathName=window.document.location.pathname;
	return pathName.substring(0,pathName.substr(1).indexOf('/')+1);
}
$(function(){
	// 加载资源文件
	$.i18n.properties({
        name : 'globalMessages', //资源文件名称
        path : getProjectName()+'/i18n/', //资源文件路径
        mode : 'map', //用Map的方式使用资源文件中的值
        language : 'zh_CN',
        callback : function(){} //加载成功后设置显示内容
    });	
	$.i18n.properties({
        name : 'temp', //资源文件名称
        path : getProjectName()+'/i18n/', //资源文件路径
        mode : 'map', //用Map的方式使用资源文件中的值
        language : 'zh_CN',
        callback : function(){} //加载成功后设置显示内容
    });
});
// 根据key获取资源文件值
function getProp(propKey){
	return $.i18n.prop(propKey);
}
var paraData={};//接查询参数的
function  newTable(table,column,url,dataString,defaultSort){
	paraData = dataString;
	var sort = eval("[[ '0', 'asc' ]]");
	if(""!=defaultSort && null !=defaultSort){
		sort =defaultSort;
	}
	var dTable =  table.dataTable( 
			   {   
				   "bServerSide": true,  
				   "bPaginate":true,
				   "iDisplayLength":10,
				   "bFilter" : false,
				   "bRetrieve": true,
				   "bDestroy": true,
				   "bScrollCollapse":true,
				   "sPaginationType": "full_numbers",  
				   "bSort":true,
				   "bAutoWidth":true,
				   "aaSorting":sort,
				   "oLanguage": {//下面是一些汉语翻译
					    "sProcessing": "正在加载中...",
				        "sLengthMenu": "每页显示 _MENU_ 条记录",
				        "sZeroRecords": "对不起，查询不到相关数据！",
				        "sInfo": "显示 _START_ 至 _END_ 条 &nbsp;&nbsp;共 _TOTAL_ 条",
				                "oPaginate":
				                {
				                    "sFirst": "首页",
				                    "sPrevious": "前一页",
				                    "sNext": "后一页",
				                    "sLast": "末页"
				                }
				    }, 
	                "sAjaxSource": url,
	                "fnServerData" : function(sSource, aoData, fnCallback) {
	                	if(paraData == null){
	                		paraData = {};
	                	}
	                	paraData.aoData = JSON.stringify(aoData);
						$.ajax({
							"type" : 'post',
							"url" : sSource,
							"dataType" : "json",
							"data" :paraData,
							"success" : function(resp) {
								fnCallback(resp);
							}
						});
					},
				    "aoColumns":column
			   });
	return dTable;
}

/**
 * 根据元素删除数组中的该元素，只匹配第一个元素
 * @param element
 * @returns
 */
Array.prototype.remove = function(element) { //element表示要删除的元素
	var idx = -1;
	for(var i = 0; i< this.length; i ++) {
		if(this[i] == element) {
			idx = i;
			break;
		}
	}
	if (idx < 0) {
		return this;
	} else {
		return this.slice(0, idx).concat(this.slice(idx + 1, this.length));
	}
	/*
	 * concat方法：返回一个新数组，这个新数组是由两个或更多数组组合而成的。
	 * 这里就是返回this.slice(0,n)/this.slice(n+1,this.length) 组成的新数组，这中间，刚好少了第n项。
	 * slice方法： 返回一个数组的一段，两个参数，分别指定开始和结束的位置。
	 */
}

/**
 * 控制输入框只能输入数字
 * @param obj
 */
function onlyNumber(obj) {
	//得到第一个字符是否为负号  
	var t = obj.value.charAt(0);
	//先把非数字的都替换掉，除了数字和.   
	obj.value = obj.value.replace(/[^\d\.]/g, '');
	//必须保证第一个为数字而不是.     
	obj.value = obj.value.replace(/^\./g, '');
	//保证只有出现一个.而没有多个.     
	obj.value = obj.value.replace(/\.{2,}/g, '.');
	//保证.只出现一次，而不能出现两次以上     
	obj.value = obj.value.replace('.', '$#$').replace(/\./g, '').replace(
			'$#$', '.');
	//如果第一位是负号，则允许添加  
	if (t == '-') {
		obj.value = '-' + obj.value;
	}
}

/**
 * 16进制颜色转换为rgb格式
 */
String.prototype.colorRgb = function(o){
	var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
	var sColor = this.toLowerCase();
	if(sColor && reg.test(sColor)){
		if(sColor.length === 4){
			var sColorNew = "#";
			for(var i=1; i<4; i+=1){
				sColorNew += sColor.slice(i,i+1).concat(sColor.slice(i,i+1));	
			}
			sColor = sColorNew;
		}
		var sColorChange = [];
		for(var i=1; i<7; i+=2){
			sColorChange.push(parseInt("0x"+sColor.slice(i,i+2)));	
		}
		if(o){
			return "rgba(" + sColorChange.join(",") + "," + o + ")";
		} else {
			return "rgba(" + sColorChange.join(",") + ")";
		}
	}else{
		return sColor;	
	}
}

/**
 * 加载页面内容
 */
function load_page(url,data,pageC) {
	if(!url) {
		url = "baseAction/browse.action";
	}
	var pageContent = $("#"+pageC);
	$.ajax({
		type: "GET",
        cache: false,
        url: url,
        dataType: "html",
        data : {"path":data},
        success: function (res) {
            pageContent.html(res);
        },
        error: function (xhr, ajaxOptions, thrownError) {
			pageContent.html(xhr.responseText);
        },
        async: false
	});
}

/**
 * 加载页面内容
 * @param action Action地址
 * @param pageUrl 页面地址
 * @param param 数据参数,{}格式
 * @param pageContent 页面容器Id
 */
function loadPage(action,pageUrl,param,pageContent) {
	if(!action) {
		action = "baseAction/browse.action";
	}
	if(!param){
		param = {};
		param.path=pageUrl;
	}
	else{
		param.path=pageUrl;
	}
	var pageContent = $("#"+pageContent);
   	$.ajax({
           type: "POST",
           cache: false,
           url: action,
           dataType: "html",
           data : param,
           success: function (res) {
           	   pageContent.removeData();
               pageContent.html(res);
           },
           error: function (xhr, ajaxOptions, thrownError) {
           	pageContent.html('<h4>Could not load the requested content.</h4>');
           },
           async: false
       });
}

/**
 * 判断值是否为空
 * @param val 值
 * @returns {Boolean} true：为空，false：不为空
 */
function isEmpty(val) {
	if ($.trim(val).length == 0) {
		return true;
	} else {
		return false;
	}
}

/**
 * 判断值是否为空
 * @param val 值
 * @returns {Boolean}true：不为空，false：为空
 */
function isNotEmpty(val) {
	return !isEmpty(val);
}