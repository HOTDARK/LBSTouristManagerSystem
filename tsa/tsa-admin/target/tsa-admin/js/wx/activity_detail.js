/**
 * 职位审批脚本
 */
$(function() {
	$('.selectpicker').selectpicker();
	c_tableFunction.tableFunction();
	bulidDetailData();
	$('#searchBotton').click(bulidDetailData);
});

/**
 * 初始化审批信息
 */
function bulidDetailData() {
	dataTable({
		id : "detailTable",
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
		url : "wxActivityDetail/activityDetailList.action",
		data : Utils.serializeObj($("#searchFrom")),
		columns : [ {
			title : "素材主题",
			data : "detailName"
//		}, {
//			title : "素材次题",
//			data : "detailSubName"
//		}, {
//			title : "素材链接",
//			data : "detailUrl"
		}, {
			title : "可用标记",
			data : "useFlag",
			formatter : convertUseFlag
		}, {
			title : "创建人",
			data : "createUserName"
		}, {
			title : "创建时间",
			data : "createDate",
			formatter : formatDate
		}, {
			title : "修改人",
			data : "modifyUserName",
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

function convertUseFlag(value, type, data){
	if(value==1){
		return "<span class='label label-success'>激活</span>";
	} else {
		return "<span class='label label-danger'>冻结</span>";
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
		operateHtml += '<a onclick="useOperation(\''+obj.id+'\',0);" href="javascript:void(0);"><i class="fa fa-ban"></i>冻结</a>&nbsp;';
	}else{
		operateHtml += '<a onclick="useOperation(\''+obj.id+'\',1);" href="javascript:void(0);"><i class="fa fa-gavel"></i>激活</a>&nbsp;';
		operateHtml += '<a onclick="editActivityDetail(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-edit"></i>'+getProp("public.txt.edit")+'</a>&nbsp;';
	}
//	operateHtml += '<a onclick="lookActivityDetail(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-book"></i>'+getProp("public.txt.look")+'</a>&nbsp;';
	operateHtml += '<a onclick="deleteActivityDetail(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-ban"></i>'+getProp("public.txt.delete")+'</a>&nbsp;';
	operateHtml += '</div>';
	operateHtml += '</div>';
	return operateHtml;
}

/**
 * 跳转活动素材编辑页面
 */
function editActivityDetail(id){
	var title = "";
	var url = "wxActivityDetail/forwardEditDetail.action";
	if(id == null || id == ""){ //新增
		title = "新增活动素材";
	}else{ //修改
		title = "修改活动素材";
		url += "?id="+id;
	}
	Utils.showModel({
		id : "editActivityDetailDiv",
		title : title,
		url : url,
		btns : [ [ "saveActivityDetailBtn", getProp("public.btn.save") ] ],
		onload : function(){
			$('.selectpicker').selectpicker();
			$("#editActivityDetailForm").validation(function(obj, params) {}, {reqmark : true});
			$("#saveActivityDetailBtn").click(saveActivityDetail);
			initFileUpload();
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
		data : {model : "wxActivityDetail"},//模块名 即上传目录下新建的文件夹名称
		preview : false, //是否显示预览
		upload : false,//是否显示上传按钮
		remove : false,//显示移除按钮
		caption :false,//是否显示标题
		success: function(data){//回调函数
			$("#showPic").attr("src", "fileoper/downFile.action?filepath="+data.filePath);
			$("#detailPicture").val(data.filePath);
		}
	});
}

/**
 * 保存活动素材信息
 * @returns
 */
function saveActivityDetail(){
	if ($("#editActivityDetailForm").valid(this) == false) {
		return false;
	}
	var detailPicture = $("#detailPicture").val();
	if(detailPicture == null || detailPicture == ""){
		Utils.showMsg("warning", "请上传活动素材图片");
		return false;
	}
	var id = $("#id").val();
	var url, sucMsg, errMsg;
	if(id == null || id == ""){
		url = "wxActivityDetail/saveDetail.action";
		sucMsg = "新增活动素材信息成功";
		errMsg = "新增活动素材信息失败";
	}else{
		url = "wxActivityDetail/updateDetail.action";
		sucMsg = "修改活动素材信息成功";
		errMsg = "修改活动素材信息失败";
	}
	Utils.ajax({
		url : url,
		data : $('#editActivityDetailForm').serialize(),
		success : function(data){
			if(data){
				Utils.closeModel('editActivityDetailDiv');
				dataTable.reload("detailTable");
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
 * 激活、冻结操作
 * @param id
 * @param releaseFlag
 * @returns
 */
function useOperation(id, useFlag){
	var pubMsg, sucMsg, errMsg;
	if(useFlag == 0){
		pubMsg = "确认冻结该活动素材";
		sucMsg = "冻结活动素材成功";
		errMsg = "冻结活动素材失败";
	}else{
		pubMsg = "确认激活该活动素材";
		sucMsg = "激活活动素材成功";
		errMsg = "激活活动素材失败";
	}
	Utils.confirm({
		msg : pubMsg + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "wxActivityDetail/updateDetail.action",
				data : {
					id : id,
					useFlag : useFlag
				},
				success : function(data){
					if(data){
						dataTable.reload("detailTable");
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
		msg : "确认删除该活动素材" + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "wxActivityDetail/updateDetail.action",
				data : {
					id : id,
					useFlag : 0,
					deleteFlag : 1
				},
				success : function(data){
					if(data){
						dataTable.reload("detailTable");
						Utils.showMsg("success", "删除活动素材成功");
					}else{
						Utils.showMsg("error", "删除活动素材失败");
					}
				},
				error : function(){
					Utils.showMsg("error", "删除活动素材失败");
				}
			});
		}
	});
}
