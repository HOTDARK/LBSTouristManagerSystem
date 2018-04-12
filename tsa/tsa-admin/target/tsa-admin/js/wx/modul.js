/**
 * 职位审批脚本
 */
$(function() {
	$('.selectpicker').selectpicker();
	c_tableFunction.tableFunction();
	bulidWxModulData();
	$('#searchBotton').click(bulidWxModulData);
});

/**
 * 初始化审批信息
 */
function bulidWxModulData() {
	dataTable({
		id : "modulInfoTable",
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
		url : "wxModulInfo/modulInfoList.action",
		data : Utils.serializeObj($("#searchFrom")),
		columns : [ {
			title : "模块名称",
			data : "modulName"
//		}, {
//			title : "模块链接",
//			data : "modulUrl"
		}, {
			title : "发布标记",
			data : "releaseFlag",
			formatter : convertReleaseFlag
		}, {
			title : "创建人",
			data : "createUserName"
		}, {
			title : "创建时间",
			data : "createDate",
			formatter : formatDate
		}, {
			title : "修改人",
			data : "modifyUserName"
		}, {
			title : "修改时间",
			data : "modifyDate",
			formatter : formatDate
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

function convertReleaseFlag(value, type, data){
	if(value==1){
		return "<span class='label label-success'>已投放</span>";
	} else {
		return "<span class='label label-danger'>未投放</span>";
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
	if(obj.releaseFlag == 1){
		operateHtml += '<a onclick="releaseOperation(\''+obj.id+'\',0);" href="javascript:void(0);"><i class="fa fa-ban"></i>撤回</a>&nbsp;';
	}else{
		operateHtml += '<a onclick="releaseOperation(\''+obj.id+'\',1);" href="javascript:void(0);"><i class="fa fa-gavel"></i>投放</a>&nbsp;';
		operateHtml += '<a onclick="editModulInfo(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-edit"></i>'+getProp("public.txt.edit")+'</a>&nbsp;';
	}
//	operateHtml += '<a onclick="lookModulInfo(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-book"></i>'+getProp("public.txt.look")+'</a>&nbsp;';
	operateHtml += '<a onclick="deleteModulInfo(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-ban"></i>'+getProp("public.txt.delete")+'</a>&nbsp;';
	operateHtml += '</div>';
	operateHtml += '</div>';
	return operateHtml;
}

/**
 * 跳转模块信息编辑页面
 */
function editModulInfo(id){
	var title = "";
	var url = "wxModulInfo/forwardModulEdit.action";
	if(id == null || id == ""){ //新增
		title = "新增模块";
	}else{ //修改
		title = "修改模块";
		url += "?id="+id;
	}
	Utils.showModel({
		id : "editWxModulInfoDiv",
		title : title,
		url : url,
		btns : [ [ "saveWxModulInfoBtn", getProp("public.btn.save") ] ],
		onload : function(){
			$('.selectpicker').selectpicker();
			$("#editWxModulInfoForm").validation(function(obj, params) {}, {reqmark : true});
			$("#saveWxModulInfoBtn").click(saveWxModulInfo);
			initFileUpload();
			var modulCss = $("#modulCss").val();
			$("#showPic").css("background-color", "#"+modulCss);
		}
	});
}

/**
 * 初始化图片上传方法
 * @returns
 */
function initFileUpload(){
	UplaodFile({
		id : "uploadfile",//上传input文件框的id
		fileExtension : [ 'png', 'jpg','bpm','gif','jpeg' ],//接收的文件后缀  
		data : {model : "wxModul"},//模块名 即上传目录下新建的文件夹名称
		preview : false, //是否显示预览
		upload : false,//是否显示上传按钮
		remove : false,//显示移除按钮
		caption :false,//是否显示标题
		success: function(data){//回调函数
			$("#showPic").attr("src", "fileoper/downFile.action?filepath="+data.filePath);
			$("#modulPicture").val(data.filePath);
		}
	});
}

/**
 * 保存微信模块信息
 * @returns
 */
function saveWxModulInfo(){
	if ($("#editWxModulInfoForm").valid(this) == false) {
		return false;
	}
	var modulPicture = $("#modulPicture").val();
	if(modulPicture == null || modulPicture == ""){
		Utils.showMsg("warning", "请上传广告图片");
		return false;
	}
	var id = $("#id").val();
	var url, sucMsg, errMsg;
	if(id == null || id == ""){
		url = "wxModulInfo/saveModulInfo.action";
		sucMsg = "新增微信模块信息成功";
		errMsg = "新增微信模块信息失败";
	}else{
		url = "wxModulInfo/updateModulInfo.action";
		sucMsg = "修改微信模块信息成功";
		errMsg = "修改微信模块信息失败";
	}
	Utils.ajax({
		url : url,
		data : $('#editWxModulInfoForm').serialize(),
		success : function(data){
			if(data){
				Utils.closeModel('editWxModulInfoDiv');
				dataTable.reload("modulInfoTable");
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
 * 投放、撤回操作
 * @param id
 * @param releaseFlag
 * @returns
 */
function releaseOperation(id, releaseFlag){
	var pubMsg, sucMsg, errMsg;
	if(releaseFlag == 0){
		pubMsg = "确认撤回该微信模块";
		sucMsg = "撤回微信模块成功";
		errMsg = "撤回微信模块失败";
	}else{
		pubMsg = "确认投放该微信模块";
		sucMsg = "投放微信模块成功";
		errMsg = "投放微信模块失败";
	}
	Utils.confirm({
		msg : pubMsg + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "wxModulInfo/updateModulInfo.action",
				data : {
					id : id,
					releaseFlag : releaseFlag
				},
				success : function(data){
					if(data){
						dataTable.reload("modulInfoTable");
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
	});
}

/**
 * 删除操作
 * @param id
 * @returns
 */
function deleteModulInfo(id){
	Utils.confirm({
		msg : "确认删除该微信模块" + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "wxModulInfo/updateModulInfo.action",
				data : {
					id : id,
					releaseFlag : 0,
					deleteFlag : 1
				},
				success : function(data){
					if(data){
						dataTable.reload("modulInfoTable");
						Utils.showMsg("success", "删除微信模块成功");
					}else{
						Utils.showMsg("error", "删除微信模块失败");
					}
				},
				error : function(){
					Utils.showMsg("error", "删除微信模块失败");
				}
			});
		}
	});
}

function setImgBg(){
	var modulCss = $("#modulCss").val();
	$("#showPic").css("background-color", "#"+modulCss);
}