window.onload=function(){
	anchor(idNum);
}
var currentPage = 1;
$("#tab1").pullToRefresh();
$("#tab1").on("pull-to-refresh", function() {
	ajaxRefresh();
});
if (totalPages > 1) {
	var loading = false;
	$("#tab1").infinite().on("infinite", function() {
		if (loading)
			return;
		loading = true;
		getMyRepairListData();
		setTimeout(function() {
			loading = false;
		}, 1000);
	});
}
getMyRepairListData = function() {
	$.showLoading();
	Utils
			.ajax({
				url : 'wxRepair/getMyJsonRepairList.action',
				data : {
					"openid" : openid,
					"currentPage" : currentPage + 1
				},
				success : function(data) {
					$.hideLoading();
					totalPages = data.totalPages;
					var tab1Html = "";
					$
							.each(
									data.repairInfoList,
									function(i, j) {
										tab1Html += "<div class=' bg-white mar-t6'>"
												+ "<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
												+ "<i class='icon-number pull-left mar-t6'></i>"
												+ "<span class='text-number pull-left mar-t6 '>"
												+ j.repairNum
												+ "</span>"
												+ "</div>"
												+ "<div class='pull-right mar-r10'>";
										if (j.repairState == '003011') {
											tab1Html += "<i class='bg-deep-blue tip-state' onclick='confirmRepair("
													+ j.id + ")' >确认维修</i>";
										}
										if (j.repairState == '003001') {
											tab1Html += "<i class='bg-deep-blue tip-state' onclick='closeRepair("
													+ j.id + ")' >关闭维修</i>";
										}
										tab1Html += "</div>"
												+ "<p class='clearfix line-1eee'></p>"
												+ "<a href='wxRepair/getRepairDetail.action?openid="
												+ openid
												+ "&id="
												+ j.id
												+ "' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
												+ "<div class='weui-media-box__hd' style='position: relative'>"
												+ "<i class='bg-green-merald tip-state' style='position: absolute;'>"
												+ j.repairStateStr + "</i>";
										if (j.picPath == null
												|| j.picPath == '') {
											tab1Html += "<img class='weui-media-box__thumb' src='images/nopic.jpg'>";
										} else {
											tab1Html += "<img class='weui-media-box__thumb' src='fileoper/downFile.action?filepath="
													+ j.picPath + "' onerror='javascript:this.src=\"images/nopic.jpg\";'>";
										}

										tab1Html += "</div>"
												+ "<div class='weui-media-box__bd'>"
												+ "<h4 class='weui-media-box__title ft-bold400'><i class='icon-position'></i>"
												+ j.repairAreaStr
												+ "</h4>"
												+ "<p class='line-x1eee'></p>"
												+ "<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"
												+ j.repairContent
												+ "</p>"
												+ "<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"
												+ new Date(j.repairDate)
														.format("yyyy-MM-dd hh:mm")
												+ "</p>" + "</div>" + "</a>"
												+ "</div>";
									});
					$("#sourceAdd").append(tab1Html);
					currentPage++;
					if (totalPages == currentPage) {
						$("#tab1").destroyInfinite();
						$("#wxLoadmore").hide();
						var tab1Html1 = "<div class='weui-loadmore weui-loadmore_line'>"
								+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
								+ "</div>";
						$("#sourceAdd").append(tab1Html1);
					}

				}
			});
}

function ajaxRefresh() {
	$.showLoading();
	Utils
			.ajax({
				url : 'wxRepair/getMyJsonRepairList.action',
				data : {
					"openid" : openid,
					"currentPage" : 1
				},
				success : function(data) {
					$.hideLoading();
					currentPage = 1;
					totalPages = data.totalPages;
					var tab1Html = "<div class='weui-pull-to-refresh__layer'>"
							+ "<div class='weui-pull-to-refresh__arrow'></div>"
							+ "<div class='weui-pull-to-refresh__preloader'></div>"
							+ "<div class='down'>下拉刷新</div>"
							+ "<div class='up'>释放刷新</div>"
							+ "<div class='refresh'>正在刷新</div>" + "</div>";
					if (totalPages == 0) {
						tab1Html += "<div id='sourceList' class='text-center' style='margin-top: 15%;'>"
								+ "<img src='images/nodata.png' width='70%' alt=''/>"
								+ "<p class='ft18 ft-grey-999'>亲，您还没有用车数据</p>"
								+ "<div style='height:100px;'>&nbsp;</div>"
								+ "</div>";
					} else {
						tab1Html += "<div id='sourceAdd'>";
						$
								.each(
										data.repairInfoList,
										function(i, j) {
											tab1Html += "<div class=' bg-white mar-t6'>"
													+ "<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
													+ "<i class='icon-number pull-left mar-t6'></i>"
													+ "<span class='text-number pull-left mar-t6 '>"
													+ j.repairNum
													+ "</span>"
													+ "</div>"
													+ "<div class='pull-right mar-r10'>";
											if (j.repairState == '003011') {
												tab1Html += "<i class='bg-deep-blue tip-state' onclick='confirmRepair("
														+ j.id + ")' >确认维修</i>";
											}
											if (j.repairState == '003001') {
												tab1Html += "<i class='bg-deep-blue tip-state' onclick='closeRepair("
														+ j.id + ")' >关闭维修</i>";
											}
											tab1Html += "</div>"
													+ "<p class='clearfix line-1eee'></p>"
													+ "<a href='wxRepair/getRepairDetail.action?openid="
													+ openid
													+ "&id="
													+ j.id
													+ "' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
													+ "<div class='weui-media-box__hd' style='position: relative'>"
													+ "<i class='bg-green-merald tip-state' style='position: absolute;'>"
													+ j.repairStateStr + "</i>";
											if (j.picPath == null
													|| j.picPath == '') {
												tab1Html += "<img class='weui-media-box__thumb' src='images/nopic.jpg'>";
											} else {
												tab1Html += "<img class='weui-media-box__thumb' src='fileoper/downFile.action?filepath="
														+ j.picPath + "'>";
											}

											tab1Html += "</div>"
													+ "<div class='weui-media-box__bd'>"
													+ "<h4 class='weui-media-box__title ft-bold400'><i class='icon-position'></i>"
													+ j.repairAreaStr
													+ "</h4>"
													+ "<p class='line-x1eee'></p>"
													+ "<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"
													+ j.repairContent
													+ "</p>"
													+ "<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"
													+ new Date(j.repairDate)
															.format("yyyy-MM-dd hh:mm")
													+ "</p>" + "</div>"
													+ "</a>" + "</div>";

										});
						if (totalPages == 1) {
							tab1Html += "<div class='weui-loadmore weui-loadmore_line'>"
									+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
									+ "</div>";
							$("#tab1").destroyInfinite();
						}
						tab1Html += "</div>";
						if (totalPages > 1) {
							tab1Html += "<div class='weui-loadmore' id='wxLoadmore'>"
									+ "<i class='weui-loading'></i>"
									+ "<span class='weui-loadmore__tips'>正在加载</span>"
									+ "</div>";
						}

					}
					tab1Html += "<div style='height:68px;'>&nbsp;</div>";
					$("#tab1").empty();
					$("#tab1").html(tab1Html);
					if (totalPages > 1) {
						var loading = false;
						$("#tab1").infinite().on("infinite", function() {
							if (loading)
								return;
							loading = true;
							getMyRepairListData();
							setTimeout(function() {
								loading = false;
							}, 1000);
						});
					}
					$("#tab1").pullToRefreshDone();
				},
				error : function() {
					$("#tab1").pullToRefreshDone();
				}
			});
}
function confirmRepair(id) {
	$.confirm("您是否确认维修？", function() {
		$.showLoading();
		Utils.ajax({
			url : 'wxRepair/confirmRepair.action',
			data : {
				"openid" : openid,
				"id" : id
			},
			success : function(data) {
				$.hideLoading();
				if (data.success) {
					$.toast("操作成功");
					ajaxRefresh();
				}
			}
		});
	}, function() {

	});
}
function closeRepair(id) {
	$.confirm("您是否关闭维修？", function() {
		$.showLoading();
		Utils.ajax({
			url : 'wxRepair/closeRepair.action',
			data : {
				"openid" : openid,
				"id" : id
			},
			success : function(data) {
				$.hideLoading();
				if (data.success) {
					$.toast("操作成功");
					ajaxRefresh();
				}
			}
		});
	}, function() {

	});
}

function limitLength(obj) {
	$("#currentLength").empty();
	$("#currentLength").text($(obj).val().length);
}
var flag = false;
function wxRepair(obj) {
	if (flag) {
		return;
	}
	flag = true;
	var reg = /^1[0-9]{10}$/;
	var repairUserName = $("#repairUserName").val();
	var userPhone = $("#userPhone").val();
	var repairArea = $("#repairAreaPicker").attr("data-values");
	var repairProject = $("#repairProjectsPicker").attr("data-values");
	var orderRepairDate = $("#ort").val();
	var serviceCompany = $("#serviceCompanyPicker").attr("data-values");
	var secondeCompany = $("#secondeCompanyPicker").attr("data-values");
	var detailLocation = $("#detailLocation").val();
	var repairContent = $("#repairContent").val();
	var shoufxm = $("#sfxmPicker").attr("data-values");
	var xuesss = $("#xsss").attr("data-values");
	var xuesssTwo = $("#xsssTwo").attr("data-values");
	if (repairUserName == "") {
		$.toast("报修人不能为空", "forbidden");
		flag = false;
		return;
	}
	if (userPhone == "") {
		$.toast("联系电话不能为空", "forbidden");
		flag = false;
		return;
	}

	if (!reg.test(userPhone)) {
		$.toast("请输入正确的手机号码", "forbidden");
		flag = false;
		return;
	}
	if (repairArea == "") {
		$.toast("请选择维修区域", "forbidden");
		flag = false;
		return;
	}

	if (repairArea != "006002") {
		if (serviceCompany == "") {
			$.toast("请选择申报单位", "forbidden");
			flag = false;
			return;
		}
		if (secondeCompany == "") {
			$.toast("请选择二级单位", "forbidden");
			flag = false;
			return;
		}
	} else {
		if (shoufxm == "") {
			$.toast("请选择维修子项", "forbidden");
			flag = false;
			return;
		}
		if (xuesss == "") {
			$.toast("请选择宿舍", "forbidden");
			flag = false;
			return;
		}
		if (xuesssTwo == "") {
			$.toast("请选择楼栋", "forbidden");
			flag = false;
			return;
		}
	}

	if (repairProject == "") {
		$.toast("请选择维修类别", "forbidden");
		flag = false;
		return;
	}

	if (detailLocation == "") {
		$.toast("详细地址不能为空", "forbidden");
		flag = false;
		return;
	}
	/*if (repairContent == "") {
		$.toast("维修内容不能为空", "forbidden");
		flag = false;
		return;
	}*/
	if (orderRepairDate == "") {
		orderRepairDate == null;
	}
	detailLocation=locationStr+"/"+detailLocation;
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
		url : 'wxRepair/repair.action',
		data : {
			"openid" : openid,
			"repairUserName" : repairUserName,
			"userPhone" : userPhone,
			"repairArea" : repairArea,
			"repairProjectOne" : repairProject,
			"orderRepairDate" : orderRepairDate,
			"repairCompany" : secondeCompany,
			"detailLocation" : detailLocation,
			"repairContent" : repairContent,
			"filePaths" : filePaths,
			"oldFileNames" : oldFileNames,
			"fileNames" : fileNames,
			"fileSizes" : fileSizes,
			"fileExtensions" : fileExtensions,
			"sfxm" : shoufxm,
			"xsss" : xuesss,
			"xsssTwo" : xuesssTwo
		},
		success : function(data) {
			$.hideLoading();
			if (data.success) {
				flag = false;
				
				$.toast("报修成功");
				//setTimeout(location.reload(), 1000);
				$(obj).text("继续申报");
				$("#repairProjectsPicker").val("");
				$("#repairProjectsPicker").attr("data-values","");
				$("#sfxmPicker").val("");
				$("#sfxmPicker").attr("data-values","");
				resetImg('repair/repair_declare');
			}
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
	// if(!valibackFlag){
	// $.toptip("请先获取验证码", 'warning');
	// return;
	// }
	if ($("#bindUserPhone").val() == "") {
		$.toast("请输入手机号", "forbidden");
		return;
	}
	if (!reg.test($("#bindUserPhone").val())) {
		$.toast("请输入正确的手机号", "forbidden");
		return;
	}
	$(obj).attr("disabled", true);
	$.showLoading();
	Utils.ajax({
		url : 'wxUserBase/bindRepairBackground.action',
		data : {
			"phone" : $("#bindUserPhone").val(),
			"openId" : openid,
			"valiCode" : $("#phoneCode").val(),
			"backAccount" : $("#backAccount").val(),
			"backType" : "repair"
		},
		success : function(data) {
			$.hideLoading();
			if (data.errCode == "SUC") {

				$.toast("绑定成功，3秒钟后即将跳转");
				setTimeout(function() {
					gdcl();
				}, 3000);
			} else {
				$.toast(data.errMsg, "forbidden");
				// if(data.msg=="微信终端已绑定，无法重复绑定!"){
				// setTimeout(function(){
				// jumpPage(url);
				// },1000);
				// } else if (data.msg=="找不到该手机号的用户信息，请核对!"){
				// $.toptip("您还没有注册，即将跳转注册页面", 'error');
				// setTimeout(function(){
				// jumpPage("wx/jumpPage.action?viewName=userRegister.jsp");
				// },1000);
				// }
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
	$.showLoading();
	Utils
			.ajax({
				url : 'wxUserBase/isBindBackground.action',
				data : {
					"openid" : openid,
					"backType" : "repair"
				},
				success : function(data) {
					$.hideLoading();
					var tab3Html = "";
					if (data.backOrgCode == null || data.backOrgCode == "") {
						tab3Html = "<div class='weui-cells bg-white mar-t6 '>"
								+ "<div class='center-text hei-36 line-hei36 ft-grey-999 line-d1e'>"
								+ "<span class='line-mid'>&nbsp;&nbsp;</span> "
								+ "<span class='ft-bold400'>工号绑定</span><span class='ft14'>（微报修工单处理）</span>"
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
	$.showLoading();
	Utils.ajax({
		url : 'wxUserBase/getBackgroundList.action',
		data : {
			"backAccount":backAccount,
			"permitStr" : permitStr,
			"orgCode" : orgCode,
			"currentPage" : currentBackPage,
			"iDisplayLength" : 10
		},
		success : function(data) {
			$.hideLoading();
			var tab3Html = getHtml(data, currentBackPage);
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

getHtml = function(data, currentPage) {
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
						data.repairInfoList,
						function(i, j) {
							tabHtml+="<div class=' bg-white mar-t6' onclick='jumpPage(\"back/operPage.action?id="+j.id+"&repairNum="+j.repairNum+"&repairState="+j.repairState+"&backAccount="+backAccount+"&orgCode="+orgCode+"\")'>"
									+"<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
									+"<i class='icon-number pull-left mar-t6'></i>"
									+"<span class='text-number pull-left mar-t6 '>"+j.repairNum+"</span>"
									+"</div>"
									+"<div class='pull-right mar-r10'><i class='bg-green-merald tip-state'>"+j.repairStateStr+"</i></div>"
									+"<p class='clearfix line-1eee'></p>"
									+"<a href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
									+"<div class='weui-media-box__hd'>";
							if (j.picPath == null || j.picPath == '') {
								tabHtml += "<img class='weui-media-box__thumb' src='images/nopic.jpg'>";
							} else {
								tabHtml += "<img class='weui-media-box__thumb' src='fileoper/downFile.action?filepath="
										+ j.picPath + "'>";
							}
							tabHtml+="</div>"
									+"<div class='weui-media-box__bd'>"
									+"<h4 class='weui-media-box__title ft-bold400'><i class='icon-position'></i>"+j.repairAreaStr+"</h4>"
									+"<p class='line-x1eee'></p>"
									+"<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"+j.repairContent+"</p>"
									+"<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"+new Date(j.repairDate).format("yyyy-MM-dd hh:mm")+"</p>"
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

function anchor (divId){
	$.showLoading();
	for(var i=0;i<3;i++){
		$("#anchor"+(i+1)).removeClass("weui-bar__item--on");
		$("#tab"+(i+1)).removeClass("weui-tab__bd-item--active");
	}
	$("#anchor"+divId).addClass("weui-bar__item--on");
	$("#tab"+divId).addClass("weui-tab__bd-item--active");
	if(divId==3){
		gdcl();
	}
}



var locationStr;
function changeLocation(){
	locationStr="";
	if($("#repairAreaPicker").attr("data-values")!="006002"){
		if(!$("#repairAreaPicker").attr("data-values")=="")
			locationStr+=$("#repairAreaPicker").val();
	} else {
		if($("#repairAreaPicker").attr("data-values")!=""){
			locationStr+=$("#repairAreaPicker").val();
			
		}
		if($("#xsss").attr("data-values")!=""){
			locationStr+="/"+$("#xsss").val();
		}
		if($("#xsssTwo").attr("data-values")!=""){
			locationStr+="/"+$("#xsssTwo").val();
		}
	}
	$("#areaLocation").text(locationStr);
}
