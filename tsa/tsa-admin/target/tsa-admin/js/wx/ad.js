/**
 * 职位审批脚本
 */
$(function() {
	$('.selectpicker').selectpicker();
	c_tableFunction.tableFunction();
	bulidWxAdData();
	$('#searchBotton').click(bulidWxAdData);
});

/**
 * 初始化审批信息
 */
function bulidWxAdData() {
	dataTable({
		id : "adTable",
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
		url : "wxAdInfo/adInfoList.action",
		data : Utils.serializeObj($("#searchFrom")),
		columns : [ {
			title : "广告名称",
			data : "adName"
		}, {
			title : "广告位",
			data : "adPositionName"
//		}, {
//			title : "开始日期",
//			data : "bdate",
//			formatter : formatDate
//		}, {
//			title : "结束日期",
//			data : "edate",
//			formatter : formatDate
		}, {
			title : "广告宽度(px)",
			data : "adWidth"
		}, {
			title : "广告高度(px)",
			data : "adHeight"
		}, {
			title : "投放标记",
			data : "deliveryFlag",
			formatter : convertDeliveryFlag
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

function convertDeliveryFlag(value, type, data){
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
	if(obj.deliveryFlag == 1){
		operateHtml += '<a onclick="deliveryOperation(\''+obj.id+'\',\''+obj.adPosition+'\',0);" href="javascript:void(0);"><i class="fa fa-ban"></i>撤回</a>&nbsp;';
	}else{
		operateHtml += '<a onclick="deliveryOperation(\''+obj.id+'\',\''+obj.adPosition+'\',1);" href="javascript:void(0);"><i class="fa fa-gavel"></i>投放</a>&nbsp;';
		operateHtml += '<a onclick="editAdInfo(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-edit"></i>'+getProp("public.txt.edit")+'</a>&nbsp;';
	}
//	operateHtml += '<a onclick="lookAdInfo(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-book"></i>'+getProp("public.txt.look")+'</a>&nbsp;';
	operateHtml += '<a onclick="deleteAdInfo(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-ban"></i>'+getProp("public.txt.delete")+'</a>&nbsp;';
	operateHtml += '</div>';
	operateHtml += '</div>';
	return operateHtml;
}

/**
 * 跳转微信广告编辑页面
 */
function editAdInfo(id){
	var title = "";
	var url = "wxAdInfo/forwardEditAdInfo.action";
	if(id == null || id == ""){ //新增
		title = "新增微信广告";
	}else{ //修改
		title = "修改微信广告";
		url += "?id="+id;
	}
	Utils.showModel({
		id : "editWxAdInfoDiv",
		title : title,
		url : url,
		btns : [ [ "saveWxAdInfoBtn", getProp("public.btn.save") ] ],
		onload : function(){
			var formatDate = {
 	 				language: 'zh-CN',
 	 				format: 'yyyy-mm-dd hh:mm:ss',
 	 				autoclose: true,
 	 				todayBtn: true,
 	 				startView: 'month',
 	 				minView:'month',
 	 				maxView:'decade'
 	 			};
			$('#bdate').datetimepicker(formatDate).on('changeDate', function(){
				$('#bdate').blur();
			});
			$('#edate').datetimepicker(formatDate).on('changeDate', function(){
				$('#edate').blur();
			});
			$('.selectpicker').selectpicker();
			$("#editWxAdInfoForm").validation(function(obj, params) {}, {reqmark : true});
			$("#saveWxAdInfoBtn").click(saveWxAdInfo);
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
		data : {model : "wxAdInfo"},//模块名 即上传目录下新建的文件夹名称
		preview : false, //是否显示预览
		upload : false,//是否显示上传按钮
		remove : false,//显示移除按钮
		caption :false,//是否显示标题
		success: function(data){//回调函数
			$("#showPic").attr("src", "fileoper/downFile.action?filepath="+data.filePath);
			$("#adPicture").val(data.filePath);
		}
	});
}

/**
 * 保存微信广告
 * @returns
 */
function saveWxAdInfo(){
	if ($("#editWxAdInfoForm").valid(this) == false) {
		return false;
	}
	var adPicture = $("#adPicture").val();
	if(adPicture == null || adPicture == ""){
		Utils.showMsg("warning", "请上传广告图片");
		return false;
	}
	var id = $("#id").val();
	var url, sucMsg, errMsg;
	if(id == null || id == ""){
		url = "wxAdInfo/saveWxAdInfo.action";
		sucMsg = "新增微信广告成功";
		errMsg = "新增微信广告失败";
	}else{
		url = "wxAdInfo/updateWxAdInfo.action";
		sucMsg = "修改微信广告成功";
		errMsg = "修改微信广告失败";
	}
	Utils.ajax({
		url : url,
		data : $('#editWxAdInfoForm').serialize(),
		success : function(data){
			if(data){
				Utils.closeModel('editWxAdInfoDiv');
				dataTable.reload("adTable");
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
 * @param adPosition
 * @param deliveryFlag
 * @returns
 */
function deliveryOperation(id, adPosition, deliveryFlag){
	var pubMsg, sucMsg, errMsg;
	if(deliveryFlag == 0){
		pubMsg = "确认撤回该微信广告";
		sucMsg = "撤回微信广告成功";
		errMsg = "撤回微信广告失败";
	}else{
		pubMsg = "确认投放该微信广告";
		sucMsg = "投放微信广告成功";
		errMsg = "投放微信广告失败";
	}
	Utils.confirm({
		msg : pubMsg + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "wxAdInfo/updateWxAdInfo.action",
				data : {
					id : id,
					adPosition : adPosition,
					deliveryFlag : deliveryFlag
				},
				success : function(data){
					if(data){
						dataTable.reload("adTable");
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
function deleteAdInfo(id){
	Utils.confirm({
		msg : "确认删除该微信广告" + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "wxAdInfo/updateWxAdInfo.action",
				data : {
					id : id,
					deliveryFlag : 0,
					deleteFlag : 1
				},
				success : function(data){
					if(data){
						dataTable.reload("adTable");
						Utils.showMsg("success", "删除微信广告成功");
					}else{
						Utils.showMsg("error", "删除微信广告失败");
					}
				},
				error : function(){
					Utils.showMsg("error", "删除微信广告失败");
				}
			});
		}
	});
}
