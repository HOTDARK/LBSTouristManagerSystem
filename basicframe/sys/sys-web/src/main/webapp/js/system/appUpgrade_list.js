$(function() {
	appUpgradeTable();
});

function appUpgradeTable() {
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
		url : "appUpgradeAction/getAppUpgradeList.action",
		columns : [ {
			title : " 应用名称",
			data : "appName"
		}, {
			title : "应用版本",
			data : "appVersion"
		}, {
			title : "URL地址",
			data : "appUrl"
		}, {
			title : "上传时间",
			data : "uploadTime",
			formatter : appDateInit
		}, {
			title : "操作",
			data : "appName",
			formatter : appCoperzionInit
		} ]
	});
}

function appDateInit(data, type, obj) {
	var operateHtml = moment(obj.uploadTime).format('YYYY-MM-DD HH:mm:ss');
	return operateHtml;
}

function appCoperzionInit(data, type, obj) {
	var operateHtml = '<div class="side" id="side">';
	operateHtml += '<button  class="side-control btn btn-default btn-xs">'
			+ '<a href="javascript:editAppUpgrade(\'' + obj.id
			+ '\');" title="修改" ><i class="fa fa-edit"></i>修改</a>'
			+ '</button>';
	operateHtml += '<button  class="side-control btn btn-default btn-xs">'
			+ '<a href="javascript:deleteApp(\'' + obj.id
			+ '\');" title="删除" ><i class="fa  fa-trash-o"></i>删除</a>'
			+ '</button>';
	operateHtml += '</div>';
	return operateHtml;
}

/**
 * 修改App
 * 
 * @param userId
 *            用户id
 */
function editAppUpgrade(id) {
	Utils.showModel({
		id : "editApp",
		title : "修改App",
		btns : [ '保存', '取消' ],
		url : "appUpgradeAction/toAppEdit.action?id=" + id
	});
}

/**
 * 添加升级
 */
function addApp() {
	Utils.showModel({
		id : "editApp",
		title : "新增升级",
		btns : [ '保存', '取消' ],
		url : "appUpgradeAction/toAppEdit.action"
	});
}

/**
 * 单个删除
 */
function deleteApp(id) {
	if (id != null) {
		Utils.confirm({
			title : "温馨提示",
			msg : "确认删除此数据吗？",
			modalSure : function() {
				Utils.ajax({
					url : "appUpgradeAction/deleteApp.action",
					data : {
						appIds : id
					},
					success : function(data) {
						var state = data.success;
						if (state) {
							Utils.showMsg("success", "删除成功！");
							dataTable.reload('userTable');
						} else {
							Utils.showMsg("error", "删除失败," + data.msg);
						}
					},
					error : function() {
						Utils.showMsg("error", "请求异常");
					}
				});
			}
		});
	}
}

/**
 * 批量删除
 */
function deleteBachApp() {
	var checkedApp = [];
	var appIds = "";
	var userChecked = $("[name='checkbox']:checkbox:checked");
	if (userChecked.length > 0) {
		userChecked.each(function(index, ele) {
			if ($(ele).attr("value") != undefined) {
				checkedApp.push($(ele).attr("value"));
			}
		});
	}

	if (checkedApp.length == 0) {
		Utils.showMsg("warning", "请选择数据");
		return;
	}
	appIds = checkedApp.join(",");

	Utils.confirm({
		title : "温馨提示",
		msg : "确认删除选中的数据吗？",
		modalSure : function() {
			Utils.ajax({
				url : "appUpgradeAction/deleteApp.action",
				data : {
					appIds : appIds
				},
				success : function(data) {
					var state = data.success;
					if (state) {
						Utils.showMsg("success", "删除成功！");
						dataTable.reload('userTable');
					} else {
						Utils.showMsg("error", "删除失败," + data.msg);
					}
				},
				error : function() {
					Utils.showMsg("error", "请求异常");
				}
			});
		}
	});

}