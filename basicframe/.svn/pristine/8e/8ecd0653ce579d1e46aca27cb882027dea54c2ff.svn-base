$(function() {
	interfaceLogTable(null);
	$('#queryTypeDict').on("click", function() {
		var account = $.trim($("#accountId").val());
		interfaceLogTable(account);
	});
});

function interfaceLogTable(account) {
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
		check : true,
		singleSelect : false,
		url : "interfaceLogAction/getInterfaceLogList.action",
		data : {
			"account" : account
		},
		columns : [ {
			title : "日志ID",
			data : "id"
		}, {
			title : "帐号",
			data : "account"
		}, {
			title : "流程名",
			data : "process_name"
		}, {
			title : "开始时间",
			data : "start_time",
			formatter : appStartDateInit
		}, {
			title : "结束时间",
			data : "end_time",
			formatter : appEndDateInit
		}, {
			title : "耗时(毫秒)",
			data : "total_time",
		}, {
			title : "操作",
			data : "appName",
			formatter : appCoperzionInit
		} ]
	});
}

function appStartDateInit(data, type, obj) {
	var start_time = moment(obj.start_time).format('YYYY-MM-DD HH:mm:ss');
	return start_time;
}

function appEndDateInit(data, type, obj) {
	var end_time = moment(obj.end_time).format('YYYY-MM-DD HH:mm:ss');
	return end_time;
}

function appCoperzionInit(data, type, obj) {
	var operateHtml = '<div class="side" id="side">';
	operateHtml += '<button  class="side-control btn btn-default btn-xs">'
			+ '<a href="javascript:queryDetail(\'' + obj.id
			+ '\');" title="查看详情" ><i class="fa fa-edit"></i>查看详情</a>'
			+ '</button>';
	operateHtml += '</div>';
	return operateHtml;
}
/**
 * 查看详情
 * 
 * @param id
 */
function queryDetail(id) {
	Utils.showModel({
		id : "editApp",
		title : "查看详情",
		btns : [ '取消' ],
		url : "appUpgradeAction/toAppEdit.action?id=" + id
	});
}
