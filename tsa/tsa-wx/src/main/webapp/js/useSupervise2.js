window.onload = function() {
	if (adopt1==1) {
		$("#switchCP").prop("checked","checked");
	}else{
		$("#switchCP").removeAttr("checked");
	}
	//指派
	if(track==2&&(menuCode=="003001"||menuCode=="003002")){
		$("#assign").show();
		$("#org").show();
		$("#orgDiv").show();
	}
	//发布
	if(state==1&&track==1&&(menuCode=="003001"||menuCode=="003002")){
		$("#release").show();
	}
	//撤回
	if(state==0&&(menuCode=="003001"||menuCode=="003002")){
		$("#withdraw").show();
	}
	//驳回，确认
	if(track==3){
		$("#reject").show();
		$("#suer").show();
	}
	if(track==5){
		$("#adopt").show();
		$("#uploadImg").show();
		var id=$("#id").val().trim();
		var viewName='';
		if (type==0) {
			viewName="useSuperviseDetail2.jsp";
		}else if (type==1){
			viewName="useProposalDetail2.jsp";
		}
		Utils.ajax({
			url:"complaintProposal/initializeUploadImg.action",
			data:{id:id,openid:openid,viewName:viewName},
			success:function(data){
				var timeStamp=data.config.timestamp;
				wx.config({
					debug: false,
					appId: data.config.appid,
					timestamp:timeStamp,
					nonceStr: data.config.noncestr,
					signature: data.config.signature,
					jsApiList: ['chooseImage', 'uploadImage']
				});
			}
		})
	}
	Utils.ajax({
		url : 'complaintProposal/findOrg.action',
		data : {
			openid:openid
		},
		success : function(data) {
			var org = [];
			$.each(data, function(j, i) {
				var row = {
					'title' : i.orgName,
					'value' : i.orgCode
				};
				org.push(row);
			});
			$("#chooseOrg").select({
				title : "请选择部门",
				items : org
			});
		}
	});
}
//采纳考评
adopt = function(obj){
	$(obj).attr("disabled",true);
	var adoptEvaluate=$("#feedback").val().trim();
	var id=$("#id").val().trim();
	var adopt = $("#addrDefault").val();
	if(adoptEvaluate==""){
		$.toast("请输入处理意见", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	$.showLoading();
	var filePaths = "", oldFileNames = "", fileNames = "", fileSizes = "", fileExtensions = "";
	if (imgData != undefined && imgData != null && imgData.length != 0) {
		for(var k in imgData){
			var file = imgData[k];
			filePaths += file.filePath + ",";
			oldFileNames += file.oldFileName + ",";
			fileNames += file.fileName + ",";
			fileSizes += file.fileSize + ",";
			fileExtensions += file.fileExtension + ",";
		}
		filePaths = filePaths.substring(0, filePaths.length - 1);
		oldFileNames = oldFileNames.substring(0, oldFileNames.length - 1);
		fileNames = fileNames.substring(0, fileNames.length - 1);
		fileSizes = fileSizes.substring(0, fileSizes.length - 1);
		fileExtensions = fileExtensions.substring(0, fileExtensions.length - 1);
	}
	Utils.ajax({
		url:"complaintProposal/updateComplaintProposal.action",
		data:{
			id:id,
			adoptEvaluate:adoptEvaluate,
			adopt:adopt,
			openid:openid,
			is : 1,
			filePaths : filePaths,
			oldFileNames : oldFileNames,
			fileNames : fileNames,
			fileSizes : fileSizes,
			fileExtensions : fileExtensions
		},
		success:function(data){
			$.hideLoading();
			if (data.success) {
				$.toast("操作成功,3秒钟后即将跳转");
				setTimeout(function() {
					//建议
					if(type==1){
						jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useProposal.jsp&idNum=3&type="+type);
					}else{
						jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useSupervise.jsp&idNum=3&type="+type);
					}
				}, 3000);
			} else {
				$.toast("操作失败");
			}
		}
	})
}
//反馈
feedback = function(obj){
	$(obj).attr("disabled",true);
	var feedback=$("#feedback").val().trim();
	var id=$("#id").val().trim();
	var adopt = $("#addrDefault").val();
	if(feedback==""){
		$.toast("请输入处理意见", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	$.showLoading("数据上传中");
	var filePaths = "", oldFileNames = "", fileNames = "", fileSizes = "", fileExtensions = "";
	if (imgData != undefined && imgData != null && imgData.length != 0) {
		for(var k in imgData){
			var file = imgData[k];
			filePaths += file.filePath + ",";
			oldFileNames += file.oldFileName + ",";
			fileNames += file.fileName + ",";
			fileSizes += file.fileSize + ",";
			fileExtensions += file.fileExtension + ",";
		}
		filePaths = filePaths.substring(0, filePaths.length - 1);
		oldFileNames = oldFileNames.substring(0, oldFileNames.length - 1);
		fileNames = fileNames.substring(0, fileNames.length - 1);
		fileSizes = fileSizes.substring(0, fileSizes.length - 1);
		fileExtensions = fileExtensions.substring(0, fileExtensions.length - 1);
	}
	Utils.ajax({
		url:"complaintProposal/updateComplaintProposal.action",
		data:{
			id:id,
			feedback:feedback,
			adopt:adopt,
			jurisdictionCode: "01",
			type:type,
			openid:openid,
			is : 2,
			filePaths : filePaths,
			oldFileNames : oldFileNames,
			fileNames : fileNames,
			fileSizes : fileSizes,
			fileExtensions : fileExtensions
		},
		success:function(data){
			$.hideLoading();
			if (data.success) {
				$.toast("操作成功,3秒钟后即将跳转");
				resetImg('supervise/supervise');
				setTimeout(function() {
					//建议
					if(type==1){
						jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useProposal.jsp&idNum=3&type="+type);
					}else{
						jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useSupervise.jsp&idNum=3&type="+type);
					}
				}, 3000);
			} else {
				$.toast("操作失败");
			}
		}
	})
}
function changeCauseNum(obj){
	$("#causeNum").text($(obj).val().length);
}
/**
 * 发布
 * @param obj
 */
function releaseCp(obj){
	$(obj).attr("disabled", true);
	$.confirm("确认发布吗？", function() {
		Utils.ajax({
			url : 'complaintProposal/updateComplaintProposal.action',
			data : {
				"id" : $("#id").val(),
				"state" : 0,
				"openid" : openid,
				is : 3
			},
			success : function(data) {
				$(obj).attr("disabled", false);
				if (data.success) {
					$.toast("操作成功,3秒钟后即将跳转");
					setTimeout(function() {
						//建议
						if(type==1){
							jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useProposal.jsp&idNum=3&type="+type);
						}else{
							jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useSupervise.jsp&idNum=3&type="+type);
						}
					}, 3000);
				} else {
					$.toast("操作失败");
				}
			}
		});
	}, function() {
		// 点击取消后的回调函数
	});
}
/**
 * 撤回
 * @param obj
 */
function withdrawCp(obj){
	$(obj).attr("disabled", true);
	$.confirm("确认撤回吗？", function() {
		Utils.ajax({
			url : 'complaintProposal/updateComplaintProposal.action',
			data : {
				"id" : $("#id").val(),
				"state" : 1,
				"openid" : openid,
				is : 3
			},
			success : function(data) {
				$(obj).attr("disabled", false);
				if (data.success) {
					$.toast("操作成功,3秒钟后即将跳转");
					setTimeout(function() {
						//建议
						if(type==1){
							jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useProposal.jsp&idNum=3&type="+type);
						}else{
							jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useSupervise.jsp&idNum=3&type="+type);
						}
					}, 3000);
				} else {
					$.toast("操作失败");
				}
			}
		});
	}, function() {
		// 点击取消后的回调函数
	});
}
//指派
function assignCp(obj){
	$(obj).attr("disabled", true);
	var org = $("#chooseOrg").attr("data-values");
	var id=$("#id").val().trim();
	var feedback=$("#feedback").val().trim();
	if (org == "") {
		$.toast("请选择部门", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	if(feedback==""){
		$.toast("请输入处理意见", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	Utils.ajax({
		url : 'complaintProposal/updateComplaintProposal.action',
		data : {
			"id" : id,
			"jurisdictionCode" : org,
			"menuCode" :"003003",
			"reason" : feedback ,
			"track" : "3",
			"openid" : openid,
			is : 4
		},
		success : function(data) {
			$(obj).attr("disabled", false);
			if (data.success) {
				$.toast("操作成功,3秒钟后即将跳转");
				setTimeout(function() {
					//建议
					if(type==1){
						jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useProposal.jsp&idNum=3&type="+type);
					}else{
						jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useSupervise.jsp&idNum=3&type="+type);
					}
				}, 3000);
			} else {
				$.toast("操作失败");
			}
		}
	});
}
/**
 * 验证默认地址
 */
function checkDefault(){
	var result = $("#switchCP").is(':checked');
	if (result) {
		$("#addrDefault").val(1);
	}else{
		$("#addrDefault").val(0);
	}
}

function showPb(i){
	var item=[];
	var filepath1=$("#filepath"+0).val();
	var filepath2=$("#filepath"+1).val();
	if(filepath1!=undefined){
		item.push("fileoper/downFile.action?filepath="+filepath1);
	}
	if(filepath2!=undefined){
		item.push("fileoper/downFile.action?filepath="+filepath2);
	}
	var pb = $.photoBrowser({
		  items: item,
		  initIndex:i
		});
	pb.open();
}
//确认
function suerCp(){
	var feedback=$("#feedback").val().trim();
	$.confirm("是否确认该信息？", function() {
		$.showLoading();
		Utils.ajax({
			url : 'complaintProposal/updateComplaintProposal.action',
			data : {
				"id" : $("#id").val(),
				"track" : "5",
				"openid" : openid,
				"reason" : feedback ,
				is : 5
			},
			success : function(data) {
				$.hideLoading();
				if (data.success) {
					$.toast("操作成功,3秒钟后即将跳转");
					setTimeout(function() {
						//建议
						if(type==1){
							jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useProposal.jsp&idNum=3&type="+type);
						}else{
							jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useSupervise.jsp&idNum=3&type="+type);
						}
					}, 3000);
				} else {
					$.toast("操作失败");
				}
			}
		});
	})
}
//驳回
function rejectCp(obj){
	$(obj).attr("disabled",true);
	var feedback=$("#feedback").val().trim();
	var id=$("#id").val().trim();
	if(feedback==""){
		$.toast("请输入处理意见", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	$.showLoading();
	Utils.ajax({
		url : 'complaintProposal/updateComplaintProposal.action',
		data : {
			"id" : $("#id").val(),
			"reason" : feedback ,
			"type" : type ,
			"openid" : openid,
			is : 6
		},
		success : function(data) {
			$.hideLoading();
			if (data.success) {
				$.toast("操作成功,3秒钟后即将跳转");
				setTimeout(function() {
					//建议
					if(type==1){
						jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useProposal.jsp&idNum=3&type="+type);
					}else{
						jumpPage("complaintProposal/findSuperviseDetail.action?viewName=useSupervise.jsp&idNum=3&type="+type);
					}
				}, 3000);
			} else {
				$.toast("操作失败");
			}
		}
	});
}