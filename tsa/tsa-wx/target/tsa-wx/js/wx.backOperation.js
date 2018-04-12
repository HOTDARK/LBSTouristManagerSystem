window.onload = function(){
	$.showLoading();
	if(operType=="1"){
		
		var cjdw=[];
		Utils.ajax({
			url : 'back/getCjdw.action',
			data : {
				
			},
			success : function(data) {
				$.hideLoading();
				$.each(data,function(j,i){
					var row={'title':i.orgName,'value':i.orgCode};
					cjdw.push(row);
				});
				$("#cjdw").select({
					title: "请选择承接单位",
					items:cjdw
				});
			}
		});
		$("#firstAuditBtn").show();
		$("#rejectBtn").show();
		$("#closeBtn").show();
		$("#cjdwDiv").show();
		
	} else if(operType=="2"){
		var fflx=[];
		var wxxm=[];
		Utils.ajax({
			url : 'back/getSecondAuditBaseData.action',
			data : {
				
			},
			success : function(data) {
				$.hideLoading();
				$.each(data.fflx,function(j,i){
					var row={'title':i.typeDictName,'value':i.typeDictCode};
					fflx.push(row);
				});
				$("#fflx").select({
					title: "请选择付费类型",
					items:fflx
				});
				$("#wxzxm").select({
					title: "请选择维修子项目"
				});
				$.each(data.wxxm,function(j,i){
					var row={'title':i.typeDictName,'value':i.typeDictCode};
					wxxm.push(row);
				});
				$("#wxxm").select({
					title: "请选择维修项目",
					items:wxxm,
					input:repairProjectOneStr,
					onClose:function(d){
						var wxxmParent=$("#wxxm").attr("data-values");
						$("#wxzxm").val("");
						$("#wxzxm").attr("data-values","");
						Utils.ajax({
			    			url : 'wxRepair/findSysDictByParent.action',
			    			data:{"parentCode":wxxmParent},
			    			success : function(data) {
			    				var proje=[];
				    			$.each(data,function(j,i){
				    				var row={'title':i.typeDictName,'value':i.typeDictCode};
				    				proje.push(row);
				    			});
				    			
			    				$("#wxzxm").select("update", { items: proje });
			    			}
			    		});
					}
				});
				$("#wxlb").select({
					title: "请选择维修类别",
					items:[{title:"直接维修",value:"01"},{title:"需要维修方案",value:"02"}]
				});
				
			}
		});
		
		
		var cjdw=[];
		Utils.ajax({
			url : 'back/getCjdw.action',
			data : {
				
			},
			success : function(data) {
				
				$.each(data,function(j,i){
					var row={'title':i.orgName,'value':i.orgCode};
					cjdw.push(row);
				});
				$("#cjdw").select({
					title: "请选择转工单位",
					items:cjdw
				});
			}
		});
		
		$("#zgBtn").show();
		$("#cjdwDiv").show();
		$("#secondAuditBtn").show();
		$("#rejectBtn").show();
		$("#closeBtn").show();
		
		if(orgCode=="010705"){
			$("#secondAuditBtn").html("缴费确认");
		} else if(orgCode=="010711"){
			$("#secondAuditBtn").html("审核");
			$("#wxxmDiv").show();
			$("#wxzxmDiv").show();
		} else {
			$("#fflxDiv").show();
			$("#wxlbDiv").show();
			$("#wxxmDiv").show();
			$("#wxzxmDiv").show();
		}
	} else if(operType=="3" && backRepairState == "003011"){
		$("#confirmBtn").show();
	} else if(operType=="3" && backRepairState == "003005"){
		$("#fashBtn").show();
	} else if(operType=="4"){
		Utils.ajax({
			url : 'back/getWxry.action',
			data:{"orgCode":orgCode},
			success : function(data) {
				var proje=[];
    			$.each(data,function(j,i){
    				var row={'title':i.userName,'value':i.userAccount};
    				proje.push(row);
    			});
    			$("#wxry").select({
					title: "请选择维修人员",
					items:proje,
					multi:true
				});
			}
		});
		$("#pgBtn").show();
		$("#wxryDiv").show();
	} else if(operType=="5"){
		$("#wgBtn").show();
		$("#wgDiv").show();
		if(orgCode=="010712"){
			$("#qzhz").show();
			$("#huizhi").select({
				title: "请选择是否有签字回执",
				items:[{title:"有签字回执",value:"1"},{title:"无签字回执",value:"2"}]
			});
			
		}
	}
	$.hideLoading();
}

function showPb(i){
	var item=[];
	var filepath1=$("#filepath1").val();
	var filepath2=$("#filepath2").val();
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
/**
 * 后台关闭或驳回
 */
function closeOrReject(obj){
	var operTitle="";
	if(obj==1){
		operTitle="驳回维修单";
	} else if(obj==2){
		operTitle="关闭维修单";
	}
	$.prompt({
		  title: operTitle,
		  text: '请输入理由',
		  input: '',
		  empty: false, // 是否允许为空
		  onOK: function (input) {
			  $.showLoading();
			  Utils.ajax({
					url : 'back/closeOrReject.action',
					data : {
						"id" : backId,
						"repairNum" : backRepairNum,
						"repairState":backRepairState,
						"operReason":input,
						"backAccount":backAccount,
						"operType":obj
					},
					success : function(data) {
						$.hideLoading();
						if(data.errCode=="SUC"){
							$.toast("操作成功！");
							setTimeout(function(){jumpPage('wxRepair/getMyRepairList.action?idNum=3');},1000);
						} else {
							$.toast(data.errMsg,"forbidden");
						}
					}
			  });
		  },
		  onCancel: function () {
		    
		  }
		});
}
/**
 * 一级审核
 */
function firstAudit(){
	var cjdw=$("#cjdw").attr("data-values");
	if(cjdw==""){
		$.toast("请选择承接单位","forbidden");
		return;
	}
	$.confirm({
		  title: '一级审核',
		  text: '确认通过一级审核？',
		  onOK: function () {
			  $.showLoading();
			  Utils.ajax({
					url : 'back/declareAuditing.action',
					data : {
						"id" : backId,
						"repairNum" : backRepairNum,
						"repairState":backRepairState,
						"serviceCompany":cjdw,
						"backAccount":backAccount,
					},
					success : function(data) {
						$.hideLoading();
						if(data.errCode=="SUC"){
							$.toast("操作成功！");
							setTimeout(function(){jumpPage('wxRepair/getMyRepairList.action?idNum=3');},1000);
						} else {
							$.toast(data.errMsg,"forbidden");
						}
					}
			  });
		  },
		  onCancel: function () {
		  }
		});
	
}

/**
 * 维修单确认（二级审核）
 */
function secondAudit(){
	var fflx=$("#fflx").attr("data-values");
	if(fflx=="" && orgCode =="010712"){
		$.toast("请选择付费类型","forbidden");
		return;
	}
	
	var wxlb=$("#wxlb").attr("data-values");
	if(wxlb=="" && orgCode =="010712"){
		$.toast("请选择维修类别","forbidden");
		return;
	}
	var wxxm=$("#wxxm").attr("data-values");
	if(wxxm=="" &&( orgCode =="010712" ||  orgCode =="010711")){
		$.toast("请选择维修项目","forbidden");
		return;
	}
	var wxzxm=$("#wxzxm").attr("data-values");
	if(wxzxm=="" &&( orgCode =="010712" ||  orgCode =="010711")){
		$.toast("请选择维修子项目","forbidden");
		return;
	}
	
	$.confirm({
		  title: '维修单确认',
		  text: '确认通过？',
		  onOK: function () {
			  $.showLoading();
			  Utils.ajax({
					url : 'back/secondAuditing.action',
					data : {
						"id" : backId,
						"repairNum" : backRepairNum,
						"repairState":backRepairState,
						"paymentType":fflx,
						"repairType":wxlb,
						"repairProjectOne":wxxm,
						"repairProjectTwo":wxzxm,
						"backAccount":backAccount,
						"serviceCompany":serviceCompany,
						"userPhone":userPhone
					},
					success : function(data) {
						$.hideLoading();
						if(data.errCode=="SUC"){
							$.toast("操作成功！");
							setTimeout(function(){jumpPage('wxRepair/getMyRepairList.action?idNum=3');},1000);
						} else {
							$.toast(data.errMsg,"forbidden");
						}
					}
			  });
		  },
		  onCancel: function () {
		  }
		});
	
}
function userConfirm(){
	$.showLoading();
	Utils.ajax({
		url : 'back/saveServiceOper.action',
		data : {
			"id" : backId,
			"repairNum" : backRepairNum,
			"repairState":backRepairState,
			"backAccount":backAccount
		},
		success : function(data) {
			$.hideLoading();
			if(data.errCode=="SUC"){
				$.toast("操作成功！");
				setTimeout(function(){jumpPage('wxRepair/getMyRepairList.action?idNum=3');},1000);
			} else {
				$.toast(data.errMsg,"forbidden");
			}
		}
  });
}

function paigong(){
	if($("#wxry").attr("data-values")=="" || $("#wxry").attr("data-values")==null){
		$.toast("请选择维修人员","forbidden");
		return;
	}
	$.showLoading();
	Utils.ajax({
		url : 'back/paigong.action',
		data : {
			"id" : backId,
			"repairNum" : backRepairNum,
			"backAccount":backAccount,
			"repairPerson":$("#wxry").attr("data-values")
		},
		success : function(data) {
			$.hideLoading();
			if(data.errCode=="SUC"){
				$.toast("操作成功！");
				setTimeout(function(){jumpPage('wxRepair/getMyRepairList.action?idNum=3');},1000);
			} else {
				$.toast(data.errMsg,"forbidden");
			}
		}
  });
}

function wangong(){
	if(($("#huizhi").attr("data-values")=="" || $("#wxry").attr("data-values")==null) && orgCode=="010712"){
		$.toast("请选择是否有签字回执","forbidden");
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
		url : 'back/wangong.action',
		data : {
			"id" : backId,
			"repairNum" : backRepairNum,
			"backAccount":backAccount,
			"isReceipt":$("#huizhi").attr("data-values"),
			"serviceCompany":serviceCompany,
			"filePaths":filePaths,
			"oldFileNames":oldFileNames,
			"fileNames":fileNames,
			"fileSizes":fileSizes,
			"fileExtensions":fileExtensions
		},
		success : function(data) {
			$.hideLoading();
			if(data.errCode=="SUC"){
				$.toast("操作成功！");
				resetImg('repair/repair_declare');
				setTimeout(function(){jumpPage('wxRepair/getMyRepairList.action?idNum=3');},1000);
			} else {
				$.toast(data.errMsg,"forbidden");
			}
		}
  });
}

function zhuangong(){
	if($("#cjdw").attr("data-values")=="" ){
		$.toast("请选择转工单位","forbidden");
		return;
	}
	$.showLoading();
	Utils.ajax({
		url : 'back/zhuangong.action',
		data : {
			"id" : backId,
			"repairNum" : backRepairNum,
			"backAccount":backAccount,
			"serviceCompany":$("#cjdw").attr("data-values")
		},
		success : function(data) {
			$.hideLoading();
			if(data.errCode=="SUC"){
				$.toast("操作成功！");
				setTimeout(function(){jumpPage('wxRepair/getMyRepairList.action?idNum=3');},1000);
			} else {
				$.toast(data.errMsg,"forbidden");
			}
		}
  });
}

