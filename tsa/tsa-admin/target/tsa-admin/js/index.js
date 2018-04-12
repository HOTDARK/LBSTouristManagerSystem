$(function(){
	App.init();
	initFunction();
	toMenu("",'index/welcome.action', '首页');
});

function onbeforeunload(){ 
	var n=window.event.screenX-window.screenLeft; 
	var b=n>document.documentElement.scrollWidth-20; 
	if(b&&window.event.clientY<0||window.event.altKey){ 
		var url = "index/removeSeesion.action";
		$.ajax({
			url: url,
			type: 'POST'
		});
	}
}

/**
 * 加载功能菜单
 */
 var menuselected;
function initFunction() {
	Utils.ajax({
		url : 'sysFunc/getPermFunctionTree.action',
		success : function(data) {
			if(data != null && data != '') {
				var menuHtml = '';
				menuHtml += '<li class="start active">';
				menuHtml += '	<a href="javascript:void(0)" class="left-menu" onclick="toMenu(event,\'index/welcome.action\',\'首页\')">';
				menuHtml += '		<i class="fa fa-home"></i> <span class="title">首页</span><span class="selected"></span>';
				menuHtml += '	</a>';
				menuHtml += '</li>';
				for(var i=0; i<data.length; i++) {
					menuHtml += '<li>';
					menuHtml += '	<a href="javascript:void(0)" class="dropdown-toggle">';
					menuHtml += '		<i class="fa '+ data[i].icoName +'"></i>';
					menuHtml += '		<span class="title"> '+ data[i].functionName +' </span>';
					menuHtml += '		<b class="arrow icon-angle-down"></b>';
					menuHtml += '	</a>';
					menuHtml += recursionFunc(data[i].sysFunctionList);
					menuHtml += '</li>';
				}
				$('#my_menu').html(menuHtml);
				menuselected =$(".page-sidebar-menu li.active");
			} else {
				//Utils.showMsg('error', '加载权限菜单出错!');
			}
		}
	});
}

function recursionFunc(funcList){
	if(funcList != null && funcList != '' && funcList.length > 0) {
		var menuHtml = '<ul class="sub-menu">';
		for(var f=0; f<funcList.length; f++) {
			if (funcList[f].functionLeafNode == 0 && funcList[f].sysFunctionList.length > 0) {
				menuHtml += '<li>';
				menuHtml += '	<a href="javascript:void(0)" class="dropdown-toggle">';
				menuHtml += '		<i class="fa '+funcList[f].icoName+'"></i>';
				menuHtml += '		<span class="title"> '+ funcList[f].functionName +' </span>';
				menuHtml += '		<b class="arrow icon-angle-down"></b>';
				menuHtml += '	</a>';
				menuHtml += recursionFunc(funcList[f].sysFunctionList);
				menuHtml += '</li>';
			} else {
				var icon = "fa-file-text-o";
				if (funcList[f].icoName != null && funcList[f].icoName != "") {
					icon = funcList[f].icoName;
				}
				menuHtml += '<li>';
				menuHtml += '	<a href="javascript:void(0)" class="left-menu submenu-item" onclick="toMenu(event,\''+ funcList[f].functionUrl +'\',\''+ funcList[f].functionName +'\')"> ';
				menuHtml += '		<i class="fa '+icon+'"></i>'+funcList[f].functionName;
				menuHtml += '	</a>';
				menuHtml += '</li>';
			}
		}
		menuHtml += '</ul>';
		return menuHtml;
	}
	return '';
}

/**
 * 跳转页面
 */		
function toMenu(target, url, functionName) {
	if (!url) {
		url = "error404.jsp"
	}
	//添加导航条
	var naviHtml = '';
	if(functionName == '首页') {
		naviHtml += '<li >';
		naviHtml += '<i class="fa fa-home"></i>';
		naviHtml += '	<a href="javascript:void(0)" onclick="toMenu(event,\''+url+'\',\''+functionName+'\')">'+ functionName +'</a>';
		naviHtml += '</li>';
		$('#my_nav').html(naviHtml);
		$('#desc span').html("首页");
		$('#desc small').html("系统管理描述");
	} else {
		naviHtml += '<li>';
		naviHtml += '<i class="fa fa-home"></i>';
		naviHtml += '	<a href="javascript:void(0)" onclick="toMenu(event,\'index/welcome.action\',\'首页\')">首页</a><i class="fa fa-angle-right"></i>';
		naviHtml += '</li>';
		naviHtml += '<li class="active">';
		naviHtml += '	<a href="javascript:void(0)" onclick="toMenu(event,\''+url+'\',\''+functionName+'\')">'+ functionName +'</a>';
		naviHtml += '</li>';
		$('#my_nav').html(naviHtml);
		$('#desc span').html(functionName);
		$('#desc small').html(functionName);
	}
	$('#my_nav').html(naviHtml);

	//改变左侧菜单选中样式	
 	if(target!=""){
 		$(".page-sidebar-menu li a.selected").remove();
 	 	menuselected.removeClass("active");
 	 	if(menuselected.parent().hasClass("sub-menu")){
 	 		menuselected.parent().parent().removeClass("active");
 		}
 	 	menuselected=$(target.target).parent();	 

 		$(".page-sidebar-menu li.active").removeClass("active");
 		$(".page-sidebar-menu li.open .sub-menu").hide();
 		$(".page-sidebar-menu li.open").removeClass("open");
 		if(menuselected.parent().parent().parent().hasClass("sub-menu")){ 
 	 		menuselected.parent().parent().parent().parent().addClass("active");
 	 		menuselected.parent().parent().parent().parent().addClass("open");
 	 		menuselected.parent().parent().parent().show();
 	 		menuselected.parent().parent().addClass("active");
 	 		menuselected.parent().show();
 	 		menuselected.parent().parent().parent().prev().append('<span class="selected"></span>');
 	 	} else {
 	 		if (menuselected.parent().hasClass("sub-menu")) {
			    menuselected.parent().parent().addClass("active");
			    menuselected.parent().parent().addClass("open");
			    menuselected.parent().show();
			    menuselected.parent().prev().append('<span class="selected"></span>');
			} else {
			    menuselected.children().append('<span class="selected"></span>');
			}
		}
 	 	menuselected.addClass("active");
 	}
 	buildPage(url, "contentHTML");
}

function buildPage(url, pageC) {
	var pageContent = $("#"+pageC);
	pageContent.empty();
	//关闭地图定时器
	if (typeof(maptimer) != "undefined") {
			clearInterval(maptimer);
	}
	if (typeof(nodedatilmaptimer) != "undefined") {
			clearInterval(nodedatilmaptimer);
	}
	if (typeof(nodetimer) != "undefined") {
		clearInterval(nodetimer);
	}
	//关闭地图webSocket
	if (typeof(mapws) != "undefined") {
		if (mapws) {
			mapws.close();
		}
	}
	Utils.ajax({
		type: "GET",
        cache: false,
        url: url,
        dataType: "html",
        success: function (res) {
            pageContent.html(res);
        },
        error: function (xhr, ajaxOptions, thrownError) {
			pageContent.html(xhr.responseText);
        }
	});
}

/**用户退出系统跳转到登录页面**/
function loginOut(close) {
	if (close) {
		Utils.confirm({msg:getProp("platform.index.exit.hint"),modalSure:function () {
		
			exitSystem(function(data, status) {
				window.opener = null;  
				window.open('', '_top', '');  
			    window.close();  
			});
		}});
	} else {
	Utils.confirm({msg:getProp("platform.index.exit.hint"),modalSure:function () {
		exitSystem(function(data, status) {
			window.location.reload();
		});
			}});
	}
	function exitSystem(success) {
		$.ajax({
			type : "POST",
			url : "index/userLoginOut.action",
			timeout : 12000,
			dataType : "JSON",
			success: function (res) {
                if(res.state){
                window.location.href = "forwardLogin.action";
                }else{
                alert(res.error);
                }
           }
		});
	}
}

//修改密码
function editPwd(){
	Utils.showModel({
		id: "modifyPwd",
		title: getProp("sys.user.pwd.reset"), 
		btns: [["save_modifyPwd", getProp("public.btn.save")]], 
		url : "index/userinitpwd.action",
		onload: function() {
			$("#pwdForm").validation();
			$("#save_modifyPwd").click(modifyPwd);
		}
	});
}

function modifyPwd(){
	if (!$("#pwdForm").valid(this)){
       	return false;
     }
	Utils.ajax({
		type: "POST",
		url: "sysUser/editUser.action",
		data: {
			userId:$("#loginUserId").val(),
			userPwd:$("#userPwdInput").val()
		},
		async: false,
		success: function(data){
			if(data){
				Utils.showMsg("success","密码重置成功");
				Utils.closeModel("modifyPwd");
			}
			else{
				Utils.showMsg("error","密码重置失败");
			}
		},
		error: function(){
			Utils.showMsg("error","请求异常");
		}
     });
}