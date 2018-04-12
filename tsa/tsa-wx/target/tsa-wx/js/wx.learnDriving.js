var totalPages;
window.onload=function(){
	$.showLoading();
	anchor(idNum);
}
function anchor(divId){
	$.showLoading();
	for(var i=0;i<3;i++){
		$("#anchor"+(i+1)).removeClass("weui-bar__item--on");
		$("#tab"+(i+1)).removeClass("weui-tab__bd-item--active");
	}
	$("#anchor"+divId).addClass("weui-bar__item--on");
	$("#tab"+divId).addClass("weui-tab__bd-item--active");
	if(divId==1){
		ajaxRefresh(1);
	} else if(divId==2){
		getBaseData();
	} else if(divId==3){
		gdcl();
	}
}
var cp=1;
function ajaxRefresh(currentPage){
	cp=currentPage;
	Utils.ajax({
		url : 'wxLearnDriving/getMyList.action',
		data:{"openid":openid,"pageNum":currentPage},
		success : function(data) {
			totalPages=data.totalPages;
			var tab1Html = getHtml(data, currentPage);
			if (currentPage == 1) {
				$("#tab1").empty();
				$("#tab1").html(tab1Html);
				$("#tab1").pullToRefresh();
				$("#tab1").on("pull-to-refresh", function() {
					ajaxRefresh(1);
				});
				$("#tab1").pullToRefreshDone();

				if (totalPages > 1) {
					var backLoading = false;
					$("#tab1").infinite().on("infinite", function() {
						if (backLoading)
							return;
						backLoading = true;
						cp += 1;
						ajaxRefresh(cp);
						setTimeout(function() {
							backLoading = false;
						}, 1000);
					});
				}
			} else {
				$("#sourceList").append(tab1Html);
				if (data.totalPages <= currentPage) {
					$("#wxBackLoadmore").hide();
					$("#tab1").destroyInfinite();
				}
			}
			$.hideLoading();
		},
		error:function(){
			$.hideLoading();
		}
	});
}

getHtml = function(data, currentPage) {
	var tab1Html = "";
	if (currentPage == 1) {
		tab1Html += "<div class='weui-pull-to-refresh__layer'>"
				+ "<div class='weui-pull-to-refresh__arrow'></div>"
				+ "<div class='weui-pull-to-refresh__preloader'></div>"
				+ "<div class='down'>下拉刷新</div><div class='up'>释放刷新</div>"
				+ "<div class='refresh'>正在刷新</div></div>";
	}
	if (data.totalPages == 0) {
		tab1Html += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有用车数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div></div>";
	} else {
		tab1Html += "<div id='sourceList'>";
		$
				.each(
						data.data,
						function(i, j) {
							
							tab1Html += "<div class=' bg-white mar-t6'>"
								+ "<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
								+ "<i class='icon-number pull-left mar-t6'></i>"
								+ "<span class='text-number pull-left mar-t6 '>"
								+ j.subjectCodeName
								+ "</span>"
								+ "</div>"
								+ "<div class='pull-right mar-r10'>";
						
						if (j.state == '1' && j.ftable=='2' && j.realStartTime==null) {
							tab1Html += "<i class='bg-deep-blue tip-state' onclick='getOn("
									+ j.id + ")' >确认上车</i>";
						}
						if (j.state == '1' && j.ftable=='2' && j.realStartTime!=null) {
							tab1Html += "<i class='bg-deep-blue tip-state open-popup' onclick='getOff("
									+ j.id + ")'>确认下车</i>";
						}
						if (j.state == '4' && j.evaluate == '0') {
							tab1Html += "<i class='bg-deep-blue tip-state open-popup' onclick='learnEvaluate("
								+ j.id + ",\""+j.coach+"\",\""+j.vehicleLicense+"\")'>评价</i>";
						}
						tab1Html += "</div>"
								+ "<p class='clearfix line-1eee'></p>"
								+ "<a href='javascript:void(0)' onclick='jumpPage(\"wxLearnDriving/getDetail.action?id="+j.id+"&ftable="+j.ftable+"\")' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
								+ "<div class='weui-media-box__hd' style='position: relative'>"
								+ "<i class='bg-green-merald tip-state' style='position: absolute;'>"
								+ j.stateName + "</i>";
						tab1Html += "<img class='weui-media-box__thumb' src='images/nopic.jpg'>";
						
						tab1Html += "</div>"
								+ "<div class='weui-media-box__bd'>"
								+ "<h4 class='weui-media-box__title ft-bold400'><i class='icon-position'></i>"
								+ j.orderTimes+"课时,共"+j.stuTime/60+"小时"
								+ "</h4>"
								+ "<p class='line-x1eee'></p>"
								+ "<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"
								+ "开始时间："+new Date(j.startTime).format("yyyy-MM-dd hh:mm")
								+ "</p>"
								+ "<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"
								+ "申请时间："+new Date(j.createDate).format("yyyy-MM-dd hh:mm")
								+ "</p>" + "</div>"
								+ "</a>" + "</div>";
						});
		if (data.totalPages == currentPage) {
			tab1Html += "<div class='weui-loadmore weui-loadmore_line'>"
					+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
					+ "</div>";
		}
		tab1Html += "</div>";
		if (data.totalPages > currentPage && currentPage == 1) {
			tab1Html += "<div class='weui-loadmore' id='wxBackLoadmore'>"
					+ "<i class='weui-loading'></i>"
					+ "<span class='weui-loadmore__tips'>正在加载</span>"
					+ "</div>";
		}
		if(currentPage==1){
			tab1Html+="<div style='height:68px;'>&nbsp;</div>";
		}
	}

	return tab1Html;
}

var enrollUser;
var driveMode;
var stuName;
var myCoach;
var subjectCode;
var unitTime;
var ad;
var mad;
var userPhone="";
var limitTime;
var pm;
function getBaseData(){
	Utils.ajax({
		url : 'wxLearnDriving/goLearnDriving.action',
		data:{"openid":openid},
		success : function(data) {
			$.hideLoading();
			userPhone=data.userInfo.sjhm;
			var practiceTimes=[];
			$.each(data.applyTime,function(j,i){
				var row={'title':i.startTime+"--"+i.endTime,'value':i.startTime+"--"+i.endTime};
				practiceTimes.push(row);
			});
			$("#practiceTime").select({
				title: "请选择练车时段",
				items: practiceTimes
			});
			$("#sTimeDiv").hide();
			$("#ksDiv").hide();
			if(data.suc){
				$("#practiceMode").select({
					title: "请选择练车模式",
					items: []
				});
				$("#currentSub").select({
					title: "请选择练习科目",
					items: [{"title":"科目二","value":"001002"},{"title":"科目三","value":"001003"}],
					onClose: function (d) {
						subjectCode=$("#currentSub").attr("data-values");
						$.showLoading();
						Utils.ajax({
							url : 'wxLearnDriving/getDms.action',
							data:{"driveCode":driveMode,
								"subCode":subjectCode},
							success : function(data) {
								$.hideLoading();
								var practiceMode;
								var practiceModej=[];
								$.each(data.dms,function(j,i){
									var row={'title':i.modelCodeName,'value':i.id};
									practiceModej.push(row);
								});
								$("#practiceMode").val("");
								$("#practiceMode").select("update",{
									items: practiceModej,
									onClose:function(d){
										practiceMode=$("#practiceMode").attr("data-values");
										Utils.ajax({
											url : 'wxLearnDriving/getDms.action',
											data:{"driveCode":driveMode,
												"subCode":subjectCode},
											success : function(data) {
												pm=data.dms[0].modelCode;
												unitTime=data.dms[0].unitTime;
												ad=data.dms[0].allowDays;
												mad=data.dms[0].maxAllowDays;
												limitTime=data.dms[0].limitTime;
												var curDate=new Date();
												var minDate=new Date(curDate.getTime()+24*60*60*1000*ad).format("yyyy-MM-dd");
												var maxDate=new Date(curDate.getTime()+24*60*60*1000*mad).format("yyyy-MM-dd");
												$("#startTime").datetimePicker({
													title: '开始时间',
													min:minDate,
													max:maxDate,
												    yearSplit:'年',
												    monthSplit:'月',
												    dateSplit:'日',
														onChange: function (picker, values, displayValues) {
															var orderRepairTime=values[0]+"-"+values[1]+"-"+values[2]+" "+values[3]+":"+values[4]+":00";
												            $("#sTime").val(orderRepairTime);
												          }});
												
												$("#startTime").attr("placeholder","开始时间（提前"+ad+"至"+mad+"天）");
												var ks=[];
												for(var i=1;i<=limitTime;i++){
													var row={"title":i+"课时","value":i};
													ks.push(row);
												}
												$("#practiceKs").attr("placeholder","预约课时（1课时="+unitTime+"分钟）");
												$("#practiceKs").select({
													title:"请选择预约课时",
													items:ks
												});
												$("#sTimeDiv").show();
												$("#ksDiv").show();
											}
										});
									}
								});
							}
						});
					}
				});
				driveMode=data.studentInfo.driveMode;
				enrollUser=data.studentInfo.enrollUser;
				stuName=data.studentInfo.sname;
				myCoach=data.studentInfo.coach;
				
				
				$("#sname").text(stuName);
				$("#driveMode").text(data.studentInfo.driveModeStr);
				
				if(driveMode=='003001'){
					var coach=[];
					$.each(data.coaches,function(j,i){
						var row={'title':i.userName,'value':i.userAccount};
						coach.push(row);
					});
					$("#coach").select({
						title: "请选择教练",
						items: coach,
						onClose: function (d) {
							myCoach=$("#coach").attr("data-values");
					    }
					});
				} else if (driveMode=='003002'){
					$("#coachDiv").hide();
					$("#cstr").text(data.studentInfo.coachStr);
					$("#coachStr").show();
				}
				
				
				
			} else {
				
				if(data.flag==2){
					$("#practiceMode").select({
						title: "请选择练车模式",
						
					});
					$("#sub").select({
						title: "请选择练习科目",
						items: [{"title":"科目二","value":"001002"},{"title":"科目三","value":"001003"}],
						onClose: function (d) {
							subjectCode=$("#sub").attr("data-values");
							if(subjectCode!="" && subjectCode!=null){
								Utils.ajax({
									url : 'wxLearnDriving/getDms.action',
									data:{"subCode":subjectCode,"driveCode":"003003"},
									success : function(data) {
										var practiceMode;
										var practiceModej=[];
										$.each(data.dms,function(j,i){
											var row={'title':i.modelCodeName,'value':i.id};
											practiceModej.push(row);
										});
										$("#practiceMode").select("update", { items: practiceModej,
											onClose:function(d){
												practiceMode=$("#practiceMode").attr("data-values");
												Utils.ajax({
													url : 'wxLearnDriving/getDms.action',
													data:{"id":practiceMode},
													success : function(data) {
														pm=data.dms[0].modelCode;
														unitTime=data.dms[0].unitTime;
														ad=data.dms[0].allowDays;
														mad=data.dms[0].maxAllowDays;
														limitTime=data.dms[0].limitTime;
														var curDate=new Date();
														var minDate=new Date(curDate.getTime()+24*60*60*1000*ad).format("yyyy-MM-dd");
														var maxDate=new Date(curDate.getTime()+24*60*60*1000*mad).format("yyyy-MM-dd");
														$("#startTime").datetimePicker({
															title: '开始时间',
															min:minDate,
															max:maxDate,
														    yearSplit:'年',
														    monthSplit:'月',
														    dateSplit:'日',
																onChange: function (picker, values, displayValues) {
														            var orderRepairTime=values[0]+"-"+values[1]+"-"+values[2]+" "+values[3]+":"+values[4]+":00";
														            $("#sTime").val(orderRepairTime);
														          }});
														
														$("#startTime").attr("placeholder","开始时间（提前"+ad+"至"+mad+"天）");
														var ks=[];
														for(var i=1;i<=limitTime;i++){
															var row={"title":i+"课时","value":i};
															ks.push(row);
														}
														$("#practiceKs").attr("placeholder","预约课时（1课时="+unitTime+"分钟）");
														$("#practiceKs").select({
															title:"请选择预约课时",
															items:ks
														});
														$("#sTimeDiv").show();
														$("#ksDiv").show();
													}
												});
											} 
										});
									
									}
								});
							}
							
					      }
					});
					
					stuName=data.userInfo.xm;
					enrollUser=data.userInfo.xgh;
					$("#sname").text(stuName);
					$("#driveMode").text("陪练预约(购买学时)");
					$("#subDiv").show();
					$("#stuSub").hide();
					$("#sTimeDiv").hide();
					$("#ksDiv").hide();
					var coach=[];
					$.each(data.coaches,function(j,i){
						var row={'title':i.userName,'value':i.userAccount};
						coach.push(row);
					});
					$("#coach").select({
						title: "请选择教练",
						items: coach,
						onClose: function (d) {
							myCoach=$("#coach").attr("data-values");
					      }
					});
					driveMode="003003";
					
				} else if(data.flag==3){
					$.toast(data.msg,"forbidden");
					setTimeout(function(){
						jumpPage('wx/jumpPage.action?viewName=userBind.jsp');
					},2000);
				} else if(data.flag==4){
					$.toast(data.msg,"forbidden");
					setTimeout(function(){
						jumpPage('wx/jumpPage.action?viewName=index.jsp');
					},2000);
				}
			}
		}
	});
}

function learnDriving(obj){
	$(obj).attr("disabled",true);
	var practiceMode=pm;
	var practiceTime=$("#practiceTime").attr("data-values");
	var sTime=$("#sTime").val();
	var practiceKs=$("#practiceKs").attr("data-values");//coach
	if(driveMode=='003001' || driveMode=='003003'){
		myCoach=$("#coach").attr("data-values");
		if(practiceMode==""){
			$.toast("请选择练车模式","forbidden");
			$(obj).attr("disabled",false);
			return;
		}
		if(myCoach==""){
			$.toast("请选择教练","forbidden");
			$(obj).attr("disabled",false);
			return;
		}
		if(driveMode=='003003'){
			subjectCode=$("#sub").attr("data-values");
			if(subjectCode==""){
				$.toast("请选择科目","forbidden");
				$(obj).attr("disabled",false);
				return;
			}
		}
	}
	if(driveMode=='003002'){
		console.log(subjectCode);
		if(subjectCode=="" || subjectCode==null || subjectCode==undefined){
			$.toast("请选择科目","forbidden");
			$(obj).attr("disabled",false);
			return;
		}
	}
	if(practiceTime==""){
		$.toast("请选择学车时段","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(sTime==""){
		$.toast("请选择开始时间","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	var dt=new Date();
	var yyt=new Date(sTime);
	if(yyt.getDate()-dt.getDate()<ad){
		$.toast("开始时间至少提前"+ad+"天","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(practiceKs==""){
		$.toast("请选择课时","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	var ptArr = $("#practiceTime").attr("data-values").split("--");
	var sDate=new Date(sTime).format("hh:mm");
	var ksDate=new Date("2017-12-12 "+ptArr[0]).format("hh:mm");
	var jsDate=new Date("2017-12-12 "+ptArr[1]).format("hh:mm");
	if(sDate<ksDate || sDate>jsDate){
		$.toast("开始时间必须在练车时段之间","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	
	$.showLoading();
	Utils.ajax({
		url : 'wxLearnDriving/learnOrderApply.action',
		data:{"stuName":stuName,"stuUser":enrollUser,"coach":myCoach,"driveMode":driveMode,"subjectCode":subjectCode,
			"subjectModel":practiceMode,"orderTimes":practiceKs,"stuTime":unitTime*practiceKs,"startTime":sTime,"createUser":enrollUser},
		success : function(data) {
			$.hideLoading();
			if(data.success){
				$.toast("提交成功，等待审核");
				setTimeout(function(){location.reload();},2000);
			} else {
				$.toast(data.msg,"forbidden");
				if(data.flag==-1){
					jumpPage('wx/jumpPage.action?viewName=buytime.jsp&idNum=2');
				}
			}
			$(obj).attr("disabled",false);
			
		},
		error:function(){
			$.hideLoading();
			$(obj).attr("disabled",false);
		}
	});
	
}


/**
 * 获取手机验证码
 */
var backFlag = false;
var valibackFlag = false;
var reg = /^1[0-9]{10}$/;
function getValiCode(obj) {
	if (backFlag) {
		return;
	}
	if ($("#backAccount").val() == "") {
		$.toast("请输入工号", "forbidden");
		return;
	}
	if ($("#bindUserPhone").val() == "") {
		$.toast("请输入手机号", "forbidden");
		return;
	}

	if (!reg.test($("#bindUserPhone").val())) {
		$.toast("请输入正确的手机号", "forbidden");
		return;
	}
	backFlag = true;
	$(obj).attr("disabled", true);
	$(obj).addClass("bg-grey-999");
	$(obj).removeClass("bg-deep-blue");
	var bindTime = 60;
	var bindInterval = setInterval(function() {
		if (bindTime == 0) {
			backFlag = false;
			$(obj).attr("disabled", false);
			$(obj).removeClass("bg-grey-999");
			$(obj).addClass("bg-deep-blue");
			$(obj).html("点击重新发送");
			clearInterval(bindInterval);
		} else {
			$(obj).html("重新发送（" + bindTime + "）");
			bindTime--;
		}
	}, 1000);
	Utils.ajax({
		url : 'wxUserBase/getValiCode.action',
		data : {
			"phone" : $("#bindUserPhone").val()
		},
		success : function(data) {
			$.toptip(data.message, 'warning');
			if (data.success) {

				valibackFlag = true;
				
			} else {
				$.toptip(data.msg, 'warning');
			}
		}
	});
}

/**
 * 绑定后台帐号
 * 
 * @param obj
 */
function bindPhone(obj) {
	if ($("#backAccount").val() == "") {
		$.toast("请输入工号", "forbidden");
		return;
	}
	if ($("#phoneCode").val() == "") {
		$.toast("请输入手机验证码", "forbidden");
		return;
	}
	if ($("#bindUserPhone").val() == "") {
		$.toast("请输入手机号", "forbidden");
		return;
	}
	if (!reg.test($("#bindUserPhone").val())) {
		$.toast("请输入正确的手机号", "forbidden");
		return;
	}
	$(obj).attr("disabled", true);
	Utils.ajax({
		url : 'wxUserBase/bindRepairBackground.action',
		data : {
			"phone" : $("#bindUserPhone").val(),
			"openId" : openid,
			"valiCode" : $("#phoneCode").val(),
			"backAccount" : $("#backAccount").val(),
			"backType" : "vehicle"
		},
		success : function(data) {
			if (data.errCode == "SUC") {
				$.toast("绑定成功，3秒钟后即将跳转");
				setTimeout(function() {
					gdcl();
				}, 3000);
			} else {
				$.toast(data.errMsg, "forbidden");
			}
		}
	});
}

var currentBackPage;
var backAccount;
var permitStr;
var orgCode;
/**
 * 查询是否绑定后台帐号，如果已绑定，显示待处理列表
 */
function gdcl() {
	Utils
			.ajax({
				url : 'wxUserBase/isBindBackground.action',
				data : {
					"openid" : openid,
					"backType" : "vehicle"
				},
				success : function(data) {
					$.hideLoading();
					var tab3Html = "";
					if (data.backOrgCode == null || data.backOrgCode == "") {
						tab3Html = "<div class='weui-cells bg-white mar-t6 '>"
								+ "<div class='center-text hei-36 line-hei36 ft-grey-999 line-d1e'>"
								+ "<span class='line-mid'>&nbsp;&nbsp;</span> "
								+ "<span class='ft-bold400'>工号绑定</span><span class='ft14'></span>"
								+ "<span class='line-mid' >&nbsp;&nbsp;</span>"
								+ "</div>"
								+ "<div class='weui-cell line-d1e'>"
								+ "<div class='weui-cell__hd'><img src='images/icon-order01.png' width='24' height='26' alt=''/></div>"
								+ "<div class='weui-cell__bd'>"
								+ "<p><input class='weui-input' type='text' placeholder='请输入工号' id='backAccount'></p>"
								+ "</div>"
								+ "</div>"
								+ "<div class='weui-cell line-d1e'>"
								+ "<div class='weui-cell__hd'><img src='images/icon-order02.png' width='24' height='26' alt=''/></div>"
								+ "<div class='weui-cell__bd'>"
								+ "<p><input class='weui-input' type='tel' placeholder='手机号' id='bindUserPhone' value='"+userPhone+"' readonly></p>"
								+ "</div>"
								+ "<div class='weui-cell__ft'><a type='button' href='javascript:void(0)' class='btn-code bg-deep-blue ft14' value='获取验证码' onclick='getValiCode(this)'>获取验证码</a></div>"
								+ "</div>"
								+ "<div class='weui-cell line-d1e'>"
								+ "<div class='weui-cell__hd'><img src='images/icon-order03.png' width='24' height='26' alt=''/></div>"
								+ "<div class='weui-cell__bd'>"
								+ "<p><input class='weui-input' type='tel' placeholder='手机验证码' id='phoneCode'></p>"
								+ "</div>"
								+ "</div>"
								+ "</div>"
								+ "<div class='demos-content-padded'>"
								+ "<a href='javascript:;' class='weui-btn bg-deep-blue' onclick='bindPhone(this)'> 立 即 绑 定</a>"
								+ "</div>"
								+ "<div style='height:68px;'>&nbsp;</div>";
						$("#tab3").empty();
						$("#tab3").html(tab3Html);
					} else {
						currentBackPage = 1;
						backAccount=data.backAccount;
						permitStr=data.permitString;
						orgCode=data.backOrgCode;
						getBackList(data.permitString, data.backOrgCode);
					}
				}
			});
}

getBackList = function(permitStr, orgCode) {
	Utils.ajax({
		url : 'learnDrivingBack/getBackList.action',
		data : {
			"backAccount":backAccount,
			"permitCode" : permitStr,
			"orgCode" : orgCode,
			"pageNum" : currentBackPage,
			"displayLength" : 10
		},
		success : function(data) {
			var tab3Html = getBackHtml(data, currentBackPage);
			$("#tab3").pullToRefreshDone();
			if (currentBackPage == 1) {
				$("#tab3").empty();
				$("#tab3").html(tab3Html);
				$("#tab3").pullToRefresh();
				$("#tab3").on("pull-to-refresh", function() {
					currentBackPage=1;
					getBackList(permitStr, orgCode);
				});
				$("#tab3").pullToRefreshDone();

				if (data.totalPages > 1) {
					var backLoading = false;
					$("#tab3").infinite().on("infinite", function() {
						if (backLoading)
							return;
						backLoading = true;
						currentBackPage = currentBackPage + 1;
						getBackList(permitStr, orgCode);
						setTimeout(function() {
							backLoading = false;
						}, 1000);
					});
				}
			} else {
				$("#sourceBack").append(tab3Html);
				if (data.totalPages <= currentBackPage) {
					$("#wxBackLoadmore").hide();
					$("#tab3").destroyInfinite();
				}
			}
		}
		,
		error : function() {
			$("#tab3").pullToRefreshDone();
		}
	});
}

getBackHtml = function(data, currentPage) {
	var tabHtml = "";
	if (currentPage == 1) {
		tabHtml += "<div class='weui-pull-to-refresh__layer'>"
				+ "<div class='weui-pull-to-refresh__arrow'></div>"
				+ "<div class='weui-pull-to-refresh__preloader'></div>"
				+ "<div class='down'>下拉刷新</div>" + "<div class='up'>释放刷新</div>"
				+ "<div class='refresh'>正在刷新</div>" + "</div>";
	}
	if (data.totalPages == 0) {
		tabHtml += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有待处理数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";

	} else {
		tabHtml += "<div id='sourceBack'>";
		$
				.each(
						data.data,
						function(i, j) {
							tabHtml+="<div class=' bg-white mar-t6' onclick='jumpPage(\"learnDrivingBack/getDetail.action?id="+j.id+"&backAccount="+backAccount+"&orgCode="+orgCode+"&ftable="+1+"\")'>"
									+"<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
									+"<i class='icon-number pull-left mar-t6'></i>"
									+"<span class='text-number pull-left mar-t6 '>"+j.subjectCodeName+"</span>"
									+"</div>"
									+"<div class='pull-right mar-r10'><i class='bg-green-merald tip-state'>"+j.stateName+"</i></div>"
									+"<p class='clearfix line-1eee'></p>"
									+"<a href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
									+"<div class='weui-media-box__hd'>"
									+"<img class='weui-media-box__thumb' src='images/nopic.jpg'>"
									+"</div>"
									+"<div class='weui-media-box__bd'>"
									+"<h4 class='weui-media-box__title ft-bold400'>"+j.stuName+","+j.orderTimes+"课时,共"+j.stuTime/60+"小时</h4>"
									+"<p class='line-x1eee'></p>"
									+"<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"+j.driveModeName+"</p>"
									+"<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"+new Date(j.startTime).format("yyyy-MM-dd hh:mm")+"</p>"
									+"</div>"
									+"</a>"
									+"</div>";
						});
		if (data.totalPages <= currentPage) {
			tabHtml += "<div class='weui-loadmore weui-loadmore_line'>"
					+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
					+ "</div>";
		}
		tabHtml += "</div>";
		if (data.totalPages > currentPage && currentPage == 1) {
			tabHtml += "<div class='weui-loadmore' id='wxBackLoadmore'>"
					+ "<i class='weui-loading'></i>"
					+ "<span class='weui-loadmore__tips'>正在加载</span>"
					+ "</div>";
		}
		if(currentPage==1){
			tabHtml+="<div style='height:68px;'>&nbsp;</div>";
		}
	}

	return tabHtml;
}

function getOn(id){
	$.confirm("确认已经上车吗？", function() {
		$.showLoading();
		Utils.ajax({
			url : 'wxLearnDriving/getOn.action',
			data : {
				"id":id
			},
			success : function(data) {
				$.hideLoading();
				if(data){
					$.toast("操作成功");
					setTimeout(function(){ajaxRefresh(1);},2000);
				} else {
					$.toast("操作失败，请重试","forbidden");
				}
			}
		});
		  }, function() {
		  //点击取消后的回调函数
		  });
}

function getOff(id){
	$.prompt("请输入学时（单位分钟）", function(text) {
		$.showLoading();
		Utils.ajax({
			url : 'wxLearnDriving/getOff.action',
			data : {
				"id":id,
				"realStuTime":text
			},
			success : function(data) {
				$.hideLoading();
				if(data){
					$.toast("操作成功");
					setTimeout(function(){ajaxRefresh(1);},2000);
				} else {
					$.toast("操作失败，请重试","forbidden");
				}
			}
		});
		}, function() {
		  //点击取消后的回调函数
		});
}
var jlpf=1;
var clpf=1;
var zhpf=1;
var applyId;
var coach;
var vehicleLicense;
function jl(obj){
	jlpf=obj;
	$("#jlpf").width(obj*26);
}
function cl(obj){
	clpf=obj;
	$("#clpf").width(obj*26);
}
function zh(obj){
	zhpf=obj;
	$("#zhpf").width(obj*26);
}
/**
 * 学车评价
 * @param id
 */
function learnEvaluate(id,person,license){
	$("#half").popup();
	applyId=id;
	coach=person;
	vehicleLicense=license;
	$("#jsypf").width(26);
	$("#clpf").width(26);
	$("#zhpf").width(26);
}

/**
 * 保存评价
 */
function saveLearnEvaluate(){
	$.showLoading();
	var appraiseInfo=$("#appraiseInfo").val();
	Utils.ajax({
		url : 'wxLearnDriving/evaluateLearn.action',
		data:{"subjectId":applyId,"vehicleLicense":vehicleLicense,"evaluateUser":coach,"subjectScore":zhpf,"commentInfo":appraiseInfo,"personScore":jlpf,"vehicleScore":clpf,"openid":openid},
		success : function(data) {
			$.hideLoading();
			if(data.success){
				$.toast("评价成功");
				setTimeout(function(){
					ajaxRefresh(1);
					$.closePopup();
				},1000);
			} else {
				$.toast("评价失败，请重试","forbidden");
			}
		}
	});
}