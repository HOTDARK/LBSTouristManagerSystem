/**
 * 职位审批脚本
 */
$(function() {
	$('.selectpicker').selectpicker();
	c_tableFunction.tableFunction();
	bulidActivityData();
	$('#searchBotton').click(bulidActivityData);
});

/**
 * 初始化审批信息
 */
function bulidActivityData() {
	dataTable({
		id : "activityTable",
		lengthChange : true,
		sort : false,
		info : true,
		paginate : true,
		autoWidth : true,
		processing : true,
		primaryKey : "id",
		sAjaxDataProp : "data",
		check : true,
		singleSelect : false,
		url : "wxActivityInfo/activityList.action",
		data : Utils.serializeObj($("#searchFrom")),
		columns : [ {
			title : "活动名称",
			data : "activityName"
		}, {
			title : "活动位",
			data : "activityPositionName"
//		}, {
//			title : "排版简介",
//			data : "activityLayout"
		}, {
			title : "应用标记",
			data : "useFlag",
			formatter : convertUseFlag
		}, {
			title : "创建人",
			data : "createUserName"
		}, {
			title : "创建时间",
			data : "createDate",
			formatter : formatDate
//		}, {
//			title : "修改人",
//			data : "modifyUser",
//		}, {
//			title : "修改时间",
//			data : "modifyDate",
//			formatter : formatDate
		}, {
			title : "操作",
			formatter : initOperation
		} ]
	});
}

/**
 * 时间格式化
 * @param e
 * @returns
 */
function formatDate(value, type, data){
	if(value == null){
		return "";
	}
	return moment(value).format("YYYY-MM-DD");
}

function convertUseFlag(value, type, data){
	if(value==1){
		return "<span class='label label-success'>在用</span>";
	} else {
		return "<span class='label label-danger'>未用</span>";
	}
}

/**
 * 初始化操作
 * @param data
 * @param type
 * @param obj
 * @returns {String}
 */
function initOperation(data, type, obj) {
	var operateHtml = '<div class="side" id="side">';
	operateHtml +='<button class="side-control btn btn-default btn-xs"><i class="fonticon icon-bianji"></i>'+getProp("public.txt.operation")+'</button>';
	operateHtml += '<div class="sidebox">';
	if(obj.useFlag == 1){
		operateHtml += '<a onclick="frozenOperation(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-ban"></i>冻结</a>&nbsp;';
	}else{
		operateHtml += '<a onclick="activityOperation(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-gavel"></i>激活</a>&nbsp;';
		operateHtml += '<a onclick="editActivityInfo(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-edit"></i>'+getProp("public.txt.edit")+'</a>&nbsp;';
		operateHtml += '<a onclick="lookActivityLayout(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-edit"></i>编辑行列</a>&nbsp;';
	}
	operateHtml += '<a onclick="lookActivityInfo(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-book"></i>'+getProp("public.txt.look")+'</a>&nbsp;';
	operateHtml += '<a onclick="deleteActivityInfo(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-ban"></i>'+getProp("public.txt.delete")+'</a>&nbsp;';
	operateHtml += '</div>';
	operateHtml += '</div>';
	return operateHtml;
}

/**
 * 跳转活动信息编辑页面
 */
function editActivityInfo(id){
	var title = "";
	var url = "wxActivityInfo/forwardEditWxActivity.action";
	if(id == null || id == ""){ //新增
		title = "新增活动信息";
	}else{ //修改
		title = "修改活动信息";
		url += "?id="+id;
	}
	Utils.showModel({
		id : "editActivityInfoDiv",
		title : title,
		url : url,
		btns : [ [ "saveActivityInfoBtn", getProp("public.btn.save") ] ],
		onload : function(){
			$('.selectpicker').selectpicker();
			$("#editActivityInfoForm").validation(function(obj, params) {}, {reqmark : true});
			$("#saveActivityInfoBtn").click(saveActivityInfo);
		}
	});
}

/**
 * 保存活动信息信息
 * @returns
 */
function saveActivityInfo(){
	if ($("#editActivityInfoForm").valid(this) == false) {
		return false;
	}
	var id = $("#id").val();
	var url, sucMsg, errMsg;
	if(id == null || id == ""){
		url = "wxActivityInfo/saveWxActivity.action";
		sucMsg = "新增活动信息成功";
		errMsg = "新增活动信息失败";
	}else{
		url = "wxActivityInfo/updateWxActivity.action";
		sucMsg = "修改活动信息成功";
		errMsg = "修改活动信息失败";
	}
	Utils.ajax({
		url : url,
		data : $('#editActivityInfoForm').serialize(),
		success : function(data){
			if(data){
				Utils.closeModel('editActivityInfoDiv');
				dataTable.reload("activityTable");
				Utils.showMsg("success", sucMsg);
			}else{
				Utils.showMsg("error", errMsg);
			}
		},
		error : function(){
			Utils.showMsg("error", errMsg);
		}
	});
}

/**
 * 冻结操作
 * @param id
 * @param releaseFlag
 * @returns
 */
function frozenOperation(id){
	Utils.confirm({
		msg : "确认冻结该活动信息" + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "wxActivityInfo/updateWxActivity.action",
				data : {
					id : id,
					useFlag : 0
				},
				success : function(data){
					if(data){
						dataTable.reload("activityTable");
						Utils.showMsg("success", "冻结活动信息成功");
					}else{
						Utils.showMsg("error", "冻结活动信息失败");
					}
				},
				error : function(){
					Utils.showMsg("error", "冻结活动信息失败");
				}
			});
		}
	});
}

/**
 * 激活操作
 * @param id
 * @param releaseFlag
 * @returns
 */
function activityOperation(id){
	Utils.ajax({
		url : "wxActivityInfo/checkWxActivity.action?id="+id,
		success : function(data){
			if(data){
				Utils.confirm({
					msg : "确认应用该活动信息" + getProp("public.txt.question"),
					modalSure : function() {
						Utils.ajax({
							url : "wxActivityInfo/updateWxActivity.action",
							data : {
								id : id,
								useFlag : 1
							},
							success : function(data){
								if(data){
									dataTable.reload("activityTable");
									Utils.showMsg("success", "应用活动信息成功");
								}else{
									Utils.showMsg("error", "应用活动信息失败");
								}
							},
							error : function(){
								Utils.showMsg("error", "应用活动信息失败败");
							}
						});
					}
				});
			}else{
				Utils.showMsg("error", "活动未添加行,或者活动有模块未添加素材");
			}
		},
		error : function(){
			Utils.showMsg("error", "验证活动行列信息失败");
		}
	});
}

/**
 * 删除操作
 * @param id
 * @returns
 */
function deleteModulInfo(id){
	Utils.confirm({
		msg : "确认删除该活动信息" + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "wxActivityInfo/updateWxActivity.action",
				data : {
					id : id,
					useFlag : 0,
					deleteFlag : 1
				},
				success : function(data){
					if(data){
						dataTable.reload("activityTable");
						Utils.showMsg("success", "删除活动信息成功");
					}else{
						Utils.showMsg("error", "删除活动信息失败");
					}
				},
				error : function(){
					Utils.showMsg("error", "删除活动信息失败");
				}
			});
		}
	});
}

/**
 * 跳转查看活动行列页面
 * @param id
 * @returns
 */
function lookActivityLayout(id){
	Utils.showModel({
		id : "lookActivityLayoutRelDiv",
		title : "编辑活动行列",
		url : "wxActivityInfo/forwardLookWxActivityLayout.action",
		width : "800px",
		onload : function(){
			bulidActivityLayout(id);
		}
	});
}

/**
 * 查看活动页面
 * @param id
 * @returns
 */
function lookActivityInfo(id){
	Utils.showModel({
		id : "lookActivityDiv",
		url : "wxActivityInfo/forwardLookActivity.action?id="+id,
		title : "微信活动",
		onload : function(){
			
		}
	});
}