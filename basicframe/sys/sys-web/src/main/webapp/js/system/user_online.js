/**
 * 登录管理脚本
 */
$(function() {
	var account = $.trim($("#accountUserName").val());
	bulidData(account);
	$('#searchBotton').on("click", function() {
		var account = $.trim($("#accountUserName").val());
		bulidData(account);
	});
});
function bulidData(account) {
	dataTable({
		id : "onlineTable",
		lengthChange : true,
		sort : false,
		info : true,
		paginate : true,
		autoWidth : true,
		processing : true,
		primaryKey : "id",
		sAjaxDataProp : "data",
		check : false,
		singleSelect : false,
		url : "sysUser/onlineUsers.action",
		data : {
			"userName" : account
		},
		columns : [ {
			title : getProp("sys.signIn.userName"),
			data : "userName"
		}, {
			title : getProp("sys.signIn.userId"),
			data : "userId"
		}, {
			title : getProp("sys.signIn.loginTime"),
			data : "loginTime"
		}, {
			title : getProp("sys.signIn.loginIp"),
			data : "ip"
		},  {
			title : getProp("public.txt.operation"),
			data : "id",
			formatter : appCoperzionInit
		} ]
	});
}

function appCoperzionInit(data, type, obj) {
	var operateHtml = '';
     operateHtml += '<button   class="btn btn-default btn-xs icon-logout-new" onclick="logout(\''+ obj.id+ '\')">'+getProp("sys.signIn.logoff")+'</button> ';
	 operateHtml += '';
	return operateHtml;
}

function logout(id) {
	if (id != null) {
		Utils.confirm({
			msg : getProp("sys.signIn.msg1")+getProp("public.txt.question"),
			modalSure : function() {
				Utils.ajax({
					url : "sysUser/logoutByManual.action",
					data : {
						sid : id
					},
					success : function(data) {
						var state = data.state;
						if (state) {
						Utils.showMsg("success", getProp("sys.signIn.msg2")+getProp("public.txt.sigh"));
						if(data.refrsh){
						window.location.reload(true);
						//window.location.href = "index/toLoginPage.action";
						}else{
							dataTable.reload('onlineTable');
						 }
						} else {
							Utils.showMsg("error", getProp("sys.signIn.msg3")+getProp("public.txt.comma") + data.msg);
						}
					},
					error : function() {
						Utils.showMsg("error", getProp("sys.signIn.msg4")+getProp("public.txt.sigh"));
					}
				});
			}
		});
	}
}