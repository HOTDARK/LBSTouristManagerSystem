<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'accessTest.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="wfplugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
</head>
<body style="padding: 20px !important;">
<br/>
<table style="margin-left: 10px;">
		<tr>
			<!-- <td>帐号：</td> -->
			<td><input type="text" id="account" class="form-control" placeholder="请输入帐号" >
			</td> 
			<!-- <td>&emsp;操作人：</td> -->
			<td ><input type="text" id="userName" class="form-control" placeholder="请输入操作人" style="margin-left: 8px;">
			</td>
			<td>
				<button type="button" id="queryTypeDict" class="btn blue"
					style="margin-left: 17px;">
					<i class="fa fa-search">查询</i>
				</button></td>
		</tr>
	</table>
	<br />
	<div class="row">
		<div class="col-xm-12">
			<table id="userTable" class="display" cellspacing="0" width="100%"></table>
		</div>
		<div id="queryDetail"></div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var accountId = $.trim($("#accountId").val());
		zhuangzaiList(accountId);
		$('#queryTypeDict').on("click", function() {
			var account = $.trim($("#account").val());
			var userName = $.trim($("#userName").val());
			zhuangzaiList(account,userName);
		});
	});
	function zhuangzaiList(account,userName) {
		dataTable({
			id : "userTable",
			lengthChange : false,
			sort : false,
			info : false,
			paginate : true,
			autoWidth : true,
			processing : true,
			primaryKey : "id",
			sAjaxDataProp : "data",
			check : false,
			singleSelect : false,
			url : "interfaceLogAction/getInterfaceLogList.action",
			data : {
				"account" : account,
				"userName" : userName
			},
			columns : [ {
				title : "日志ID",
				data : "id"
			}, {
				title : "帐号",
				data : "account",
				formatter : accountInit
			}, {
				title : "流程名",
				data : "process_name"
			}, {
				title : "开始时间",
				data : "startTime"
			}, {
				title : "结束时间",
				data : "endTime"
			}, {
				title : "耗时(毫秒)",
				data : "total_time"
			},{
				title : "操作人",
				data : "userName"
			}, {
				title : "操作",
				data : "id",
				formatter : appCoperzionInit
			} ]
		});
	}
	function accountInit(data, type, obj) {
	if(obj.account=='null'){
	return "";
	}else{
	return obj.account;
	}
   }

function appCoperzionInit(data, type, obj) {
	var operateHtml = '<div class="side" id="side">';
	operateHtml+="<a target='_blank' href='workflow/log/trace.do?id="+obj.id+"'><button class='btn btn-default btn-xs'><i class='fa fa-angle-double-right'></i>执行轨迹</button></a>";
    operateHtml += "<button  class='btn btn-default btn-xs' onclick='queryDetail("+obj.id+")'><i class='fa fa-search'></i>查看详情</button>";
	operateHtml += '</div>';
	return operateHtml;
}
	
function queryDetail(id) {
	Utils.showModel({
		id : "queryDetail",
		title : "查看详情",
		btns : [["save_queryDetail",'取消']],
		url : "interfaceLogAction/toDetailPage.action?id=" + id
	});
}
	

</script>

</html>
