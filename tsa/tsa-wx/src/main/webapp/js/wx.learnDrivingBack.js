var flag=0;
window.onload = function() {
	
	
	Utils.ajax({
		url : 'learnDrivingBack/findVehicleAndCoach.action',
		data : {
			"flag" : flag
		},
		success : function(data) {
			var vehicles = [];
			$.each(data.vs, function(j, i) {
				var row = {
					'title' : i.vehicleLicense + "/" + i.vehicleTypeName
							+ "/" + i.vehicleBrandName,
					'value' : i.vehicleLicense
				};
				vehicles.push(row);
			});
			$("#chooseVehicle").select({
				title : "请选择车辆",
				items : vehicles
			});
			$("#vehicleDiv").show();
			
			
		}
	});
}



/**
 * 审核通过
 */
function audit(obj) {
	$(obj).attr("disabled", true);
	var vl = $("#chooseVehicle").attr("data-values");
	var dr = $("#chooseDriver").attr("data-values");
	if(flag==0){
		dr=coach;
	}
	if (vl == "") {
		$.toast("请选择车辆", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	if (dr == "") {
		$.toast("请选择教练", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	Utils.ajax({
		url : 'learnDrivingBack/audit.action',
		data : {
			"vehicleLicense" : vl,
			"coach" : dr,
			"id" : backId,
			"userAccount" : backAccount
		},
		success : function(data) {
			$(obj).attr("disabled", false);
			if (data.errCode == "SUC") {
				$.toast("操作成功");
				setTimeout(function() {
					jumpPage('wx/jumpPage.action?viewName=learnDriving.jsp&idNum=3');
				}, 2000);
			} else {
				$.toast(data.errMsg, "forbidden");
			}
		}
	});
}

/**
 * 驳回
 */
function reject(obj) {
	$(obj).attr("disabled", true);

	$.prompt({
		title : "驳回预约单",
		text : '请输入理由',
		input : '',
		empty : false, // 是否允许为空
		onOK : function(input) {
			Utils.ajax({
				url : 'learnDrivingBack/reject.action',
				data : {
					"id" : backId,
					"reason" : input,
					"userAccount" : backAccount
				},
				success : function(data) {
					$(obj).attr("disabled", false);
					if (data.errCode == "SUC") {
						$.toast("操作成功！");
						setTimeout(function() {
							jumpPage('wx/jumpPage.action?viewName=learnDriving.jsp&idNum=3');
						}, 2000);
					} else {
						$.toast(data.errMsg, "forbidden");
					}
				}
			});
		},
		onCancel : function() {

		}
	});
}

/**
 * 取消
 */
function cancel(obj) {
	$(obj).attr("disabled", true);

	$.prompt({
		title : "取消预约单",
		text : '请输入理由',
		input : '',
		empty : false, // 是否允许为空
		onOK : function(input) {
			Utils.ajax({
				url : 'learnDrivingBack/reject.action',
				data : {
					"id" : backId,
					"reason" : input,
					"userAccount" : backAccount
				},
				success : function(data) {
					$(obj).attr("disabled", false);
					if (data.errCode == "SUC") {
						$.toast("操作成功！");
						setTimeout(function() {
							jumpPage('wx/jumpPage.action?viewName=learnDriving.jsp&idNum=3');
						}, 2000);
					} else {
						$.toast(data.errMsg, "forbidden");
					}
				}
			});
		},
		onCancel : function() {

		}
	});
}