function Utils (){};
var bc = {
		"def":"btn-default",// 标准的按钮
		"pri":"btn-primary",// 提供额外的视觉效果，标识一组按钮中的原始动作
		"suc":"btn-success",// 表示一个成功的或积极的动作
		"info":"btn-info",// 信息警告消息的上下文按钮
		"war":"btn-warning",// 表示应谨慎采取的动作
		"dan":"btn-danger",// 表示一个危险的或潜在的负面动作
		"col":"btn-cancel",// 提供额外的视觉效果[灰色]
		"link":"btn-link"// 并不强调是一个按钮，看起来像一个链接，但同时保持按钮的行为
};
function getProjectName(){
	var pathName=window.document.location.pathname;
	return pathName.substring(0,pathName.substr(1).indexOf('/')+1);
}

/**
 * 获取当前项目路径
 * @returns 项目路径
 */
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取当前网址，如： localhost:8083
    var curPath=window.location.host;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
//    var pathName=window.document.location.pathname;
//    var pos=curWwwPath.indexOf(pathName);
//    //获取主机地址，如： http://localhost:8083
//    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
//    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    //return(localhostPaht+projectName);
    return curPath+getProjectName();
}

/**
 * ajax封装
 * 
 * @param type 默认POST,可传GET,POST
 * @param url访问地址
 * @param data 参数{username:'zhangsan',password:'123'}
 * @param dataType 默认JSON
 * @param timeout 默认600000
 * @param success 成功回调方法,必须传
 * @param error 有默认方法
 */
var limitPendingRequests = {};
Utils.ajax = function(param){
	var error = function(e, xhr){
		Utils.showMsg('error', '向服务器提交数据失败!', 	1000);
	};
	var beforeSend = function(xhr, settings){
		//发送前默认事件
		// ajax 请求限制
		var url = settings.url;
		if(limitPendingRequests[url]) {
            console.log("重复提交，放弃："+url);
			return false;
		}
		limitPendingRequests[url] = true;
		xhr.complete(function() {
			delete limitPendingRequests[url];
		});
	};
	var complete = function(){
		//完成后默认事件
	};
	param.error = (param.error == undefined) ? error : param.error;
	param.type = (param.type == undefined) ? 'POST' : param.type;
	param.dataType = (param.dataType == undefined) ? 'JSON' : param.dataType;
	param.timeout = (param.timeout == undefined) ? 60000 : param.timeout;
	param.async = (param.async == undefined) ? true : param.async;
	param.beforeSend = (param.beforeSend == undefined) ? beforeSend : param.beforeSend;
	param.complete = (param.complete == undefined) ? complete : param.complete;
	param.contentType = (param.contentType == undefined) ? "application/x-www-form-urlencoded; charset=UTF-8" : param.contentType;
	
	$.ajax({
		type: param.type,
		url: param.url,
		contentType: param.contentType,
		data: param.data,
		dataType: param.dataType,
		async: param.async,
		timeout: param.timeout,
		success: param.success,
		error: param.error,
		beforeSend: param.beforeSend,		
		complete: param.complete
	});
};

/**
 *  日期加减
 *  @param date 时间 2014-12-04/毫秒数/date
 *  @days 天数 正为加负为减
 */
Utils.addDays = function(date,days){  
	var nd = new Date(date);  
	nd = nd.valueOf();  
	nd = nd + days * 24 * 60 * 60 * 1000;  
	nd = new Date(nd);  
   // alert(nd.getFullYear() + "年" + (nd.getMonth() + 1) + "月" + nd.getDate() +   "日");  
	var y = nd.getFullYear();  
	var m = nd.getMonth()+1;  
	var d = nd.getDate();  
	if(m <= 9) m = "0"+m;  
	if(d <= 9) d = "0"+d;   
	var cdate = y+"-"+m+"-"+d;  
	return cdate;  
};

/**
 * 日期格式化
 * alert(new Date().format("yyyy年MM月dd日")); 
 * alert(new Date().format("MM/dd/yyyy")); 
 * alert(new Date().format("yyyyMMdd")); 
 * alert(new Date().format("yyyy-MM-dd hh:mm:ss"));
 * @param format 格式化格式
 * @returns 格式化后日期
 */
Date.prototype.format = function(format){
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
	};
	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 

	for(var k in o) { 
	if(new RegExp("("+ k +")").test(format)) { 
		format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		} 
	}
	return format; 
};

/**
 * 消息提示框 使用页面需要引入/pages/common/tips.jsp
 * @param type 消息提示框类型 success,info,warning,error
 * @param msg 所需要显示的消息
 * @param time 消息提示框显示时间，默认1000
 */
Utils.showMsg = function (type,msg,time) {
	$("#alert" + type).html("<span class='icons close'></span>"+msg);
	$("#alert" + type).show();
	setTimeout(function(){$("#alert" + type).slideUp(500);},(time==undefined?2000:time));
};

/**
 * 弹出对话框
 * @param title 对话框标题
 * @param msg 对话框内容
 * @param modalSure 确定按钮执行方法
 * 
 * Utils.confirm({title:"系统提示",msg:"确定删除？",modalSure:function () {alert(1)}});
 */
Utils.confirm = function (param) {
	
	var html = '';
	html += '<div class="modal-dialog" style="width:400px;" id="showModal">';
	html += '	<div class="modal-content">';
	html += '		<div class="modal-header">';
	html += '		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>';
	html += '		<h4 class="modal-title">' + param.title == '' || param.title == undefined ? getProp("public.confirm.title") : param.title + '</h4>';
	html += '		</div>';
	html += '		<div class="modal-body">'+ param.msg +'</div>';
	html += '		<div class="modal-footer">';
	html += '			<button type="button" id="modalSure" class="btn btn-primary">'+getProp("public.btn.sure")+'</button>';
	html += '			<button type="button" id="modalCancel" class="btn btn-cancel" data-dismiss="modal" onclick="$(\'#showModal\').trigger(\'close\');">'+getProp("public.btn.cancel")+'</button>';
	html += '		</div>';
	html += '	</div>';
	html += '</div>';
	$('#showModal_Div').html(html);	
	
	$("#modalSure").click(function(){
		param.modalSure.call();
		$("#showModal").trigger("close");
	});
	
	$('#showModal').lightbox_me({ 
		destroyOnClose:true,
		centered:true,
		closeClick:true,
		lightboxSpeed:0,
		overlaySpeed:0
	});
};
/**
 * 弹出对话框2
 * @param title 对话框标题
 * @param msg 对话框内容
 * @param modalSure 确定按钮执行方法
 * 
 * Utils.confirm({title:"系统提示",msg:"确定删除？",modalSure:function () {alert(1)}});
 */
Utils.confirm2 = function (param) {
	
	var html = '';
	html += '<div class="modal-dialog" style="width:400px;" id="showModal">';
	html += '	<div class="modal-content">';
	html += '		<div class="modal-header">';
	html += '		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>';
	html += '		<h4 class="modal-title">' + param.title == '' || param.title == undefined ? '温馨提示' : param.title + '</h4>';
	html += '		</div>';
	html += '		<div class="modal-body">'+ param.msg +'</div>';
	html += '		<div class="modal-footer">';
	html += '			<button type="button" id="modalSure" class="btn btn-primary">确定</button>';
	html += '			<button type="button" id="modalCancel" class="btn btn-cancel">取消</button>';
	html += '		</div>';
	html += '	</div>';
	html += '</div>';
	$('#showModal_Div').html(html);	
	
	$("#modalSure").click(function(){
		param.modalSure.call();
		$("#showModal").trigger("close");
	});
	$("#modalCancel").click(function(){
		$("#showModal").trigger("close");
		$("#cpuSpanId").html($("#cputest").val()+"%");
		$("#cpuSpanId").show();
		$("#cputest").attr("hidden","true");
	});
	
	$('#showModal').lightbox_me({ 
		destroyOnClose:true,
		centered:true,
		closeClick:true,
		lightboxSpeed:0,
		overlaySpeed:0
	});
};

/**
 * 弹出框   页面
 * @param id 对话框DIVID
 * @param title 对话框标题
 * @param btns 按钮名称 如：btns:[['btnId','btnText','btnColor']]注：'btnId','btnText'参数必传，'btnColor'可不传，默认为'btn-primary'
 * @param url 对话框显示的页面地址
 * @param onload 对话框加载事件，可以为弹出的页面赋初始值
 * 
 * js调用
 * 
 * Utils.showModel({
 *		id:"divId",
 *		title: "modal-title", 
 *		btns:[['save_divId','保存']], 
 *		url: "system/org_edit.jsp",
 *		onload: () {
 *			var parentOrgId = $('#parentOrgId').val();
 *			$('#editOrg').find('#parentOrgId').val(parentOrgId);
 *		}
 *	});function
 */
Utils.showModel = function(param) {
	$.ajax({
        type: "POST",
        cache: false,
        url: param.url,
        dataType: "html",
        data : param.data,
        success: function (res) {
        	if(typeof(param.mheight) != "undefined" && param.mheight != ""){
        		param.mheight = 'style="overflow-y: hidden; min-height: '+param.mheight+';"';
			} else {
				param.mheight = '';
			}
        	var html = '';
        	var style = "";
        	if(param.width){
        		style = 'style="width:' + param.width + ';"';
        	}
        	html += '<div class="modal-dialog" ' + style + ' id="' + param.id + '_Model_">';
        	html += '	<div class="modal-content">';
        	html += '		<div class="modal-header">';
        	html += '		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>';
        	html += '		<h4 class="modal-title">' + param.title + '</h4>';
        	html += '		</div>';
        	html += '		<div class="modal-body" '+param.mheight+'>'+ res +'</div>';
        	html += '		<div class="modal-footer">';
        	if(typeof(param.btns) == "undefined"){
        		html += '<button type="button" class="btn btn-cancel" data-dismiss="modal" onclick="$(\'#'+ param.id +'_Model_\').trigger(\'close\');">'+getProp("public.btn.cancel")+'</button>';
        	} else {
        		$.each(param.btns, function(i, o){
        			var obc = "btn-primary";
        			if (typeof(o[2]) != "undefined") {
						obc = o[2];
					}
        			html += '<button type="button" id="'+o[0]+'" class="btn '+obc+'">'+o[1]+'</button>';
        		});
        		html += '<button type="button" class="btn btn-cancel" data-dismiss="modal" onclick="$(\'#'+ param.id +'_Model_\').trigger(\'close\');">'+getProp("public.btn.cancel")+'</button>';
			}
        	html += '		</div>';
        	html += '	</div>';
        	html += '</div>';
        	$('#'+param.id).html(html);	
        },
        error: function (xhr, ajaxOptions, thrownError) {
        	var pageContent = $('.page-content');
        	pageContent.html('<h4>Could not load the requested content.</h4>');
        },
        async: false
    });
	if(!param.onload){//如果没有load方法,则默认一个
		param.onload = function(){
			
		}
	}
	if(!param.onclose){//如果没有关闭方法,则默认一个
		param.onclose = function(){
			
		}
	}
	$('#'+param.id+"_Model_").lightbox_me({ 
		destroyOnClose:true,
		centered:true,
		closeClick:true,
		lightboxSpeed:0,
		overlaySpeed:0,
		onLoad: param.onload,
		onClose: param.onclose
	});
	Utils.moveModel(param.id + '_Model_');
};


/**
 * 弹出框   层
 * @param id 对话框DIV ID
 * @param onload 对话框加载事件，可以为弹出的页面赋初始值
 * 
 * 使用这种方式弹出层需要在自己页面上隐藏一个DIV 如：
 * <!-- 弹出层开始   -->
<div class="modal-dialog" id="test">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" id="modalCancel" class="close"
				data-dismiss="modal">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title">标题</h4>
		</div>
		<div class="modal-body">
			内容
		</div>
		<div class="modal-footer">
			<button type="button" id="modalCancel" class="btn btn-default">取消</button>
			<button type="button" id="modalSure" class="btn btn-primary">确定</button>
		</div>
	</div>
</div>
 * <!-- 弹出层结束   -->
 * 
 * js调用
 * 
 * Utils.showModelDiv({
 * 		id:"editOrg",
 *		onload: function() {
 *			var parentOrgId = $('#parentOrgId').val();
 *			$('#editOrg').find('#parentOrgId').val(parentOrgId);
 *		}
 *	});
 */
Utils.showModelDiv = function(param) {
	var html = '';
	var style = "";
	if(param.width){
		style = 'style="width:' + param.width + ';"';
	}
	html += '<div class="modal-dialog" ' + style + ' id="' + param.id + '_DIV_">';
	html += '	<div class="modal-content">';
	html += '		<div class="modal-header">';
	html += '		<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>';
	html += '		<h4 class="modal-title">' + param.title + '</h4>';
	html += '		</div>';
	html += '		<div class="modal-body">'+ $('#' + param.id).html() +'</div>';
	html += '		<div class="modal-footer">';
	if(typeof(param.btns)!="undefined"){
		if(param.btns.length > 1) {
			html += '			<button type="button" id="save_'+param.id+'" class="btn btn-primary">' + param.btns[0] + '</button>';
			html += '			<button type="button" id="cancel_'+param.id+'" class="btn btn-cancel" data-dismiss="modal" onclick="$(\'#'+ param.id +'_DIV_\').trigger(\'close\');">' + param.btns[1] + '</button>';
		} else {
			html += '			<button type="button" id="cancel_'+param.id+'" class="btn btn-cancel" data-dismiss="modal" onclick="$(\'#'+ param.id +'_DIV_\').trigger(\'close\');">' + param.btns[0] + '</button>';
		}
	}
	html += '		</div>';
	html += '	</div>';
	html += '</div>';
	$('#showModal_Div').html(html);
	$('#save_'+param.id).click(function() {
		//param.save();
	});
	$('#'+param.id + '_DIV_').lightbox_me({ 
		destroyOnClose:true,
		centered:true,
		closeClick:true,
		lightboxSpeed:1000,
		overlaySpeed:0,
		onLoad: param.onload
	});
};

/**
 * 关闭弹出框
 * @param divID 要关闭的DIV ID
 * 
 * js调用
 * 
 * Utils.closeModel('divID');
 * 
 */
Utils.closeModel = function(divID) {
	$("#"+divID+"_Model_").trigger("close");
};


Utils.moveModel = function(divID){
	/**
	 * 弹出窗口拖拽
	 */
	var _move = false; //移动标记
	var _modalx, _modaly; //鼠标离控件左上角的相对位置
	$("#" + divID).find(".modal-header").on("mousedown",function(e) {
		$(this).css("cursor","move");
	    _move = true;
	    _modalLeft = $("#" + divID).find(".modal-content").css("left");
	    _modalTop = $("#" + divID).find(".modal-content").css("top");
	    if(_modalLeft == "auto"){
	    	_modalLeft = 0;
	    	_modalTop = 0;
	    }
		_modalx = e.pageX - parseInt(_modalLeft);
	    _modaly = e.pageY - parseInt(_modalTop);
	    $("#" + divID).find(".modal-content").fadeTo(20, 0.5); //点击后开始拖动并透明显示
	});
	 $(document).mousemove(function(e) {
	    if (_move) {
	        var modalx = e.pageX - _modalx; //移动时根据鼠标位置计算控件左上角的绝对位置
	        var modaly = e.pageY - _modaly;
	        if(modalx<-($(window).width()- $("#" + divID).find(".modal-content").width())/2)
	        {
	        modalx=-($(window).width()- $("#" + divID).find(".modal-content").width())/2
	        }
	        if(modalx>($(window).width()- $("#" + divID).find(".modal-content").width())/2)
	        {
	        modalx=($(window).width()- $("#" + divID).find(".modal-content").width())/2
	        } 
	        if(modaly<-($(window).height()- $("#" + divID).find(".modal-content").height())/2)
	        {
	        modaly=-($(window).height()- $("#" + divID).find(".modal-content").height())/2;
	        }
	        if(modaly>($(window).height()- $("#" + divID).find(".modal-content").height())/2)
	        {
	        modaly=($(window).height()- $("#" + divID).find(".modal-content").height())/2;
	        }
	       
	      //  $(".modal-header").html("modalx:"+modalx+"modaly:"+modaly+"winheight:"+$(window).height()+"modal-contentw:"+ $(".modal-content").width()+"modal-contenth:"+ $(".modal-content").height());
	        $("#" + divID).find(".modal-content").css({ top: modaly, left: modalx }); //控件新位置
	    }
	 }).mouseup(function() {
	     _move = false;
	     $("#" + divID).find(".modal-content").fadeTo("fast", 1); //松开鼠标后停止移动并恢复成不透明
	});
};

/**
 * 表单数据填充
 * @param data 填充表单数据
 * @param form 表单对象
 */
Utils.fillForm = function(data, form) {
	var fel = form.find("input,textarea,select");
	var dataLower = {};
	for (var each in data) {
		dataLower[each.toLowerCase()] = data[each]; 
	}
	for (var i = 0; i < fel.length; i++) {
		var el = fel[i];
		if (!el.name)
			continue;
		var type = el.type && el.type.toLowerCase();
		var value = dataLower[el.name.toLowerCase()]; 
		if (value === null || value === undefined)
			value = "";
		// log( [el.name.toLowerCase(),value] );
		if (type == "text"|| type == "number" || type == "textarea" || type == "hidden") {
			el.value = value;
			$(el).change();
		} else if (type == "checkbox" ){
			if( $.isArray(value) ){
				for(var k=0;k<value.length;k++){
					if( el.value == value[k] ){
						el.checked = true;
					}else{
						el.checked = false;
					}
				}
			}else{
				el.checked = el.value == (""+value);					
			}
		} else if( type == "radio") {
			if( el.value == value)
				$(el).click();
		} else if (el.tagName.toLowerCase() == "select") { 
			el.value = value; 
			$(el).change();
		}
	}
	// form表单内元素添加此属性，实现填充
	var fieldElements = form.find("[data-field]");
	fieldElements.each(function(){
		var fieldName = this.getAttribute( "data-field");
		if(fieldName==null || fieldName==""){
			return;
		} 
		var fieldValue = dataLower[ fieldName.toLowerCase()];
		$(this).text( fieldValue );
	});
};

/**
 * 获取表单数据序列化对象
 * @param form
 * @returns {___anonymous16694_16695}
 */
Utils.serializeObj = function(form) {
	"use strict";
	var result = {};
	var extend = function(i, element) {
		var node = result[element.name];
		// If node with same name exists already, need to
		// convert it to an array as it
		// is a multi-value field (i.e., checkboxes)
		if ('undefined' !== typeof node && node !== null) {
			if ($.isArray(node)) {
				node.push(element.value);
			} else {
				result[element.name] = [node, element.value];
			}
		} else {
			result[element.name] = element.value;
		}
	};
	$.each(form.serializeArray(), extend);
	return result;
};
