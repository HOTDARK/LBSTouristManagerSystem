var backInfoId;
window.onload = function() {
	if (operState == "101") {
		$("#auditBtn").show();
		$("#rejectBtn").show();
		$("#vehicleDiv").show();
		$("#driverDiv").show();
		$("#messageDiv").show();
		$("#sendMessage").select({
			title : "请选择短信发送对象",
			multi: true,
			items : [{title:"预约人",value:"1"},{title:"乘车人",value:"2"}]
		});
		Utils.ajax({
			url : 'vehicleBack/findDriverAndCar.action',
			data : {

			},
			success : function(data) {
				var vehicles = [];
				$.each(data.vehicles, function(j, i) {
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

				var drivers = [];
				$.each(data.drivers, function(j, i) {
					var row = {
						'title' : i.userName,
						'value' : i.userAccount
					};
					drivers.push(row);
				});
				$("#chooseDriver").select({
					title : "请选择司机",
					items : drivers
				});
			}
		});
	} else if (operState == "103") {
		$("#depart").show();
	} else if (operState == "105") {
		$("#back").show();
		$("#passengerDiv").show();
//		$("#dispatherDiv").show();
		$("#parkDiv").show();
		$("#tollDiv").show();
		$("#milestoneDiv").show();
		$("#sectionDiv").show();
		$("#beforeDiv").show();
		$("#drivingDiv").show();
		$("#afterDiv").show();
		$("#startDiv").show();
		$("#endDiv").show();
		$("#backInfo").show();
		
		Utils.ajax({
			url : 'vehicleBack/getBackInfo.action',
			data : {
				"orderNum" : backOrderNum
			},
			success : function(data) {
				$("#passenger").val(data.back.rideUser);
//				$("#dispather").val(data.back.vehicleDealer);
				$("#park").val(data.back.parkingRate);
				$("#toll").val(data.back.roadToll);
				$("#milestone").val(data.back.mileage);
				$("#section").val(data.back.xc);
				$("#before").val(data.back.dispatchInfo);
				$("#driving").val(data.back.drivingInfo);
				$("#after").val(data.back.receivingInfo);
				backInfoId=data.back.id;
				console.log("开始id:"+backInfoId);
				var stime=data.back.startDate;
				var etime=data.back.endDate;
				var sdate;
				var edate;
				if(stime!=null && stime!=""){
					sdate=new Date(stime);
					$("#sTime").val(sdate.format("yyyy-MM-dd hh:mm"));
				} else {
					sdate=new Date();
				}
				if(etime!=null && etime!=""){
					edate=new Date(etime);
					$("#eTime").val(edate.format("yyyy-MM-dd hh:mm"));
				} else {
					edate=new Date();
				}
				$("#startTime").datetimePicker(
						{
							title : '出发时间',
							yearSplit : '年',
							monthSplit : '月',
							dateSplit : '日',
							times : function() {
								return [ // 自定义的时间
								{
									values : (function() {
										var hours = [];
										for (var i = 0; i < 24; i++)
											hours.push(i < 10 ? ("0" + i) : i);
										return hours;
									})()
								}, {
									divider : true, // 这是一个分隔符
									content : ':'
								}, {
									values : (function() {
										var minutes = [ '00', '15', '30', '45' ];
										return minutes;
									})()
								} ];
							},
							onChange : function(picker, values, displayValues) {
								$("#sTime").val(
										values[0] + "-" + values[1] + "-" + values[2]
												+ " " + values[3] + ":" + values[4]
												+ ":00");
							}
						});

				$("#endTime").datetimePicker(
						{
							title : '收车时间',
							min : new Date($("#sTime").val()).format("yyyy-MM-dd hh:mm"),
							yearSplit : '年',
							monthSplit : '月',
							dateSplit : '日',
							times : function() {
								return [ // 自定义的时间
								{
									values : (function() {
										var hours = [];
										for (var i = 0; i < 24; i++)
											hours.push(i < 10 ? ("0" + i) : i);
										return hours;
									})()
								}, {
									divider : true, // 这是一个分隔符
									content : ':'
								}, {
									values : (function() {
										var minutes = [ '00', '15', '30', '45' ];
										return minutes;
									})()
								} ];
							},
							onChange : function(picker, values, displayValues) {
								$("#eTime").val(
										values[0] + "-" + values[1] + "-" + values[2]
												+ " " + values[3] + ":" + values[4]
												+ ":00");
							}
						});
				$("#startTime").val(sdate.format("yyyy年MM月dd日 hh:mm"));
				$("#endTime").val(edate.format("yyyy年MM月dd日 hh:mm"));
			}
		});

	}
}

/**
 * 审核通过
 */
function audit(obj) {
	$.showLoading();
	$(obj).attr("disabled", true);
	var vl = $("#chooseVehicle").attr("data-values");
	var dr = $("#chooseDriver").attr("data-values");
	var sendMessage=$("#sendMessage").attr("data-values");
	if (vl == "") {
		$.toast("请选择车辆", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	if (dr == "") {
		$.toast("请选择司机", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	Utils.ajax({
		url : 'vehicleBack/auditOrder.action',
		data : {
			"vehicleLicense" : vl,
			"driver" : dr,
			"id" : backId,
			"userAccount" : backAccount,
			"sendMessage":sendMessage
		},
		success : function(data) {
			$.hideLoading();
			$(obj).attr("disabled", false);
			if (data.errCode == "SUC") {
				$.toast("操作成功");
				setTimeout(function() {
					jumpPage('wx/jumpPage.action?viewName=useVehicle.jsp&idNum=3');
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
function rejectOrder(obj) {
	$(obj).attr("disabled", true);

	$.prompt({
		title : "驳回预约单",
		text : '请输入理由',
		input : '',
		empty : false, // 是否允许为空
		onOK : function(input) {
			Utils.ajax({
				url : 'vehicleBack/rejectOrder.action',
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
							jumpPage('wx/jumpPage.action?viewName=useVehicle.jsp&idNum=3');
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

function depart(obj) {
	$(obj).attr("disabled", true);
	$.confirm("确认出车吗？", function() {
		Utils.ajax({
			url : 'vehicleBack/depart.action',
			data : {
				"id" : backId,
				"userAccount" : backAccount
			},
			success : function(data) {
				$(obj).attr("disabled", false);
				if (data.errCode == "SUC") {
					$.toast("操作成功！");
					setTimeout(function() {
						jumpPage('wx/jumpPage.action?viewName=useVehicle.jsp&idNum=3');
					}, 2000);
				} else {
					$.toast(data.errMsg, "forbidden");
				}
			}
		});
	}, function() {
		// 点击取消后的回调函数
	});
}
function goBack(obj) {
	$(obj).attr("disabled", true);
	var passenger = $("#passenger").val();
//	var dispather = $("#dispather").val();
	var park = $("#park").val();
	var toll = $("#toll").val();
	var milestone = $("#milestone").val();
	var section = $("#section").val();
	var before = $("#before").val();
	var driving = $("#driving").val();
	var after = $("#after").val();
	var startTime=$("#sTime").val();
	var endTime=$("#eTime").val();
	if(isNaN(park)){
		$(obj).attr("disabled", false);
		$.toast("停车费只能输入数字","forbidden");
		return;
	}
	if(isNaN(toll)){
		$(obj).attr("disabled", false);
		$.toast("过路费只能输入数字","forbidden");
		return;
	}
	if(isNaN(milestone)){
		$(obj).attr("disabled", false);
		$.toast("行驶里程只能输入数字","forbidden");
		return;
	}
	console.log(backInfoId);
	$.confirm("确认回场吗？", function() {
		Utils.ajax({
			url : 'vehicleBack/getBack.action',
			data : {
				"orderNum" : backOrderNum,
				"driver" : backAccount,
				"id" : backInfoId,
				"orderNum" : backOrderNum,
				"userAccount" : backAccount,
				"rideUser" : passenger,
				"parkingRate" : park,
				"roadToll" : toll,
				"mileage" : milestone,
				"driveRange" : section,
				"dispatchInfo" : before,
				"drivingInfo" : driving,
				"receivingInfo" : after,
				"startDate":startTime,
				"endDate":endTime,
				"vehicleLicense":vehicleLicense
			},
			success : function(data) {
				$(obj).attr("disabled", false);
				if (data.errCode == "SUC") {
					$.toast("操作成功！");
					setTimeout(function() {
						jumpPage('wx/jumpPage.action?viewName=useVehicle.jsp&idNum=3');
					}, 2000);
				} else {
					$.toast(data.errMsg, "forbidden");
				}
			}
		});
	}, function() {
		// 点击取消后的回调函数
	});
}

function saveBack(obj){
	$(obj).attr("disabled", true);
	var passenger = $("#passenger").val();
//	var dispather = $("#dispather").val();
	var park = $("#park").val();
	var toll = $("#toll").val();
	var milestone = $("#milestone").val();
	var section = $("#section").val();
	var before = $("#before").val();
	var driving = $("#driving").val();
	var after = $("#after").val();
	var startTime=$("#sTime").val();
	var endTime=$("#eTime").val();
	if(isNaN(park)){
		$(obj).attr("disabled", false);
		$.toast("停车费只能输入数字","forbidden");
		return;
	}
	if(isNaN(toll)){
		$(obj).attr("disabled", false);
		$.toast("过路费只能输入数字","forbidden");
		return;
	}
	if(isNaN(milestone)){
		$(obj).attr("disabled", false);
		$.toast("行驶里程只能输入数字","forbidden");
		return;
	}
	Utils.ajax({
		url : 'vehicleBack/saveBackInfo.action',
		data : {
			"id" : backInfoId,
			"orderNum" : backOrderNum,
			"userAccount" : backAccount,
			"diver" : backAccount,
			"rideUser" : passenger,
			"parkingRate" : park,
			"roadToll" : toll,
			"mileage" : milestone,
			"driveRange" : section,
			"dispatchInfo" : before,
			"drivingInfo" : driving,
			"receivingInfo" : after,
			"sDate":startTime,
			"eDate":endTime,
			"vehicleLicense":vehicleLicense
		},
		success : function(data) {
			console.log(data);
			$(obj).attr("disabled", false);
			if (data.errCode == "SUC") {
				$.toast("操作成功！");
				setTimeout(function() {
					jumpPage('wx/jumpPage.action?viewName=useVehicle.jsp&idNum=3');
				}, 2000);
			} else {
				$.toast(data.errMsg, "forbidden");
			}
		}
	});
}