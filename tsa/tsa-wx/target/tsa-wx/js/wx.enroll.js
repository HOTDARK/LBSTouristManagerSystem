window.onload = function() {
	$.showLoading();
	anchor(idNum);
}
function anchor(divId) {
	$.showLoading();
	for (var i = 0; i < 3; i++) {
		$("#anchor" + (i + 1)).removeClass("weui-bar__item--on");
		$("#tab" + (i + 1)).removeClass("weui-tab__bd-item--active");
	}
	$("#anchor" + divId).addClass("weui-bar__item--on");
	$("#tab" + divId).addClass("weui-tab__bd-item--active");
	if (divId == 1) {
		ajaxRefresh();
	} else if (divId == 2) {
		getBaseData();
	} else if(divId == 3){
		gdcl();
	}
}

function ajaxRefresh() {
	Utils.ajax({
		url : 'enroll/getMyEnroll.action',
		data : {
			"openid" : openid
		},
		success : function(data) {
			var tab1Html = getHtml(data);
			$("#tab1").empty();
			$("#tab1").html(tab1Html);
			$("#tab1").pullToRefresh();
			$("#tab1").on("pull-to-refresh", function() {
				ajaxRefresh();
			});
			$("#tab1").pullToRefreshDone();

			$.hideLoading();
		},
		error : function() {
			$.hideLoading();
		}
	});
}

getHtml = function(data) {
	var tab1Html = "";
	tab1Html += "<div class='weui-pull-to-refresh__layer'>"
			+ "<div class='weui-pull-to-refresh__arrow'></div>"
			+ "<div class='weui-pull-to-refresh__preloader'></div>"
			+ "<div class='down'>下拉刷新</div>" + "<div class='up'>释放刷新</div>"
			+ "<div class='refresh'>正在刷新</div>" + "</div>";
	if (data.data.length == 0) {
		tab1Html += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有报名数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";

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
									+ j.sourceName + "</span>" + "</div>"
									+ "<div class='pull-right mar-r10'>";

							/*if (j.state == '0') {
								tab1Html += "<i class='bg-deep-blue tip-state' onclick='closeEnroll("
										+ j.id + ")' >撤销报名</i>";
							}
							if (j.state == '1') {
								tab1Html += "<i class='bg-deep-blue tip-state' onclick='appraiseVehicleOrder("
										+ j.id + ")' >评价</i>";
							}*/
							tab1Html += "</div>"
									+ "<p class='clearfix line-1eee'></p>"
									+ "<a href='javascript:void(0)' onclick='jumpPage(\"enroll/getDetail.action?id="+j.id+"\")'  class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
									+ "<div class='weui-media-box__hd' style='position: relative'>"
									+ "<i class='bg-green-merald tip-state' style='position: absolute;'>";
							if(j.state=="0"){
								tab1Html+="待审核";
							} else if (j.state=="1"){
								tab1Html+="审核通过";
							} else if (j.state=="2"){
								tab1Html+="审核不通过";
							} else if (j.state=="3"){
								tab1Html+="已过期";
							}
							tab1Html +="</i>";
							tab1Html += "<img class='weui-media-box__thumb' src='images/nopic.jpg'>";
							tab1Html += "</div>"
									+ "<div class='weui-media-box__bd'>"
									+ "<h4 class='weui-media-box__title ft-bold400'></i>"
									+ j.applyName
									+ "</h4>"
									+ "<p class='line-x1eee'></p>"
									+ "<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"
									+ j.driveName
									+ "</p>"
									+ "<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"
									+ new Date(j.createDate)
											.format("yyyy-MM-dd hh:mm")
									+ "</p>" + "</div>" + "</a>" + "</div>";
						});
		tab1Html += "<div class='weui-loadmore weui-loadmore_line'>"
				+ "<span class='weui-loadmore__tips'>没有更多数据了</span>" + "</div>";
		tab1Html += "</div>";
		tab1Html += "<div style='height:68px;'>&nbsp;</div>";
	}

	return tab1Html;
}


var userPhone;
function getBaseData() {
	Utils.ajax({
		url : 'enroll/enrollBase.action',
		data : {
			"openid" : openid
		},
		success : function(data) {
			userPhone=data.userInfo.sjhm;
			$("#sname").val(data.userInfo.xm);
			$("#telephone").val(data.userInfo.sjhm);
			$("#ssex").val(data.userInfo.xbm);
			$("#enrollUser").val(data.userInfo.xgh);
			var applyModel = [];
			$.each(data.applyModel, function(j, i) {
				var row = {
					'title' : i.dictName,
					'value' : i.dictCode
				};
				applyModel.push(row);
			});
			$("#applyModel").select({
				title : "请选择报考车型",
				items : applyModel
			});

			var driveModel = [];
			/*$.each(data.driveModel, function(j, i) {
				if(i.dictCode=="003003"){
					continue;
				}
				var row = {
					'title' : i.dictName,
					'value' : i.dictCode
				};
				driveModel.push(row);
			});*/
			
			for(var i=0;i<data.driveModel.length;i++){
				if(data.driveModel[i].dictCode=="003003"){
					continue;
				}
				var row = {
						'title' : data.driveModel[i].dictName,
						'value' : data.driveModel[i].dictCode
					};
					driveModel.push(row);
			}
			$("#driveModel").select({
				title : "请选择学车方式",
				items : driveModel
			});
			$.hideLoading();
		}
	});
}

/**
 * 报名
 */
function enroll(obj) {
	$(obj).attr("disabled", true);
	var sname = $("#sname").val().trim();
	var telephone = $("#telephone").val().trim();
	var ssex = $("#ssex").val();
	var applyModel = $("#applyModel").attr("data-values");
	var driveModel = $("#driveModel").attr("data-values");
	var enrollUser = $("#enrollUser").val();
	if (sname == "") {
		$.toast("姓名不能为空", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	var reg = /^1[0-9]{10}$/;
	if (telephone == "") {
		$.toast("联系电话不能为空", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	if (!reg.test(telephone)) {
		$.toast("请输入正确的手机号码", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	if (applyModel == "") {
		$.toast("请选择报考车型", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	if (driveModel == "") {
		$.toast("请选择学车方式", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	$.showLoading();
	Utils.ajax({
		url : 'enroll/wxEnroll.action',
		data : {
			"sname" : sname,
			"telephone" : telephone,
			"sex" : ssex,
			"applyModel" : applyModel,
			"driveMode" : driveModel,
			"enrollUser" : enrollUser
		},
		success : function(data) {
			$.hideLoading();
			if (data.success) {
				$.toast("报名成功");
				setTimeout(function() {
					location.reload();
				}, 2000);
			} else {
				$.toast(data.msg, "forbidden");
			}
			$(obj).attr("disabled", false);

		},
		error : function() {
			$.hideLoading();
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
		url : 'enrollBack/getEnrollBackList.action',
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
							tabHtml+="<div class=' bg-white mar-t6' onclick='jumpPage(\"enrollBack/getDetail.action?id="+j.id+"&backAccount="+backAccount+"&orgCode="+orgCode+"\")'>"
									+"<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
									+"<i class='icon-number pull-left mar-t6'></i>"
									+"<span class='text-number pull-left mar-t6 '>"+j.sourceName+"</span>"
									+"</div>"
									+"<div class='pull-right mar-r10'><i class='bg-green-merald tip-state'>";
							if(j.state=="0"){
								tabHtml+="待审核";
							} else if (j.state=="1"){
								tabHtml+="审核通过";
							} else if (j.state=="2"){
								tabHtml+="审核不通过";
							} else if (j.state=="3"){
								tabHtml+="已过期";
							}
							tabHtml+="</i></div>"
									+"<p class='clearfix line-1eee'></p>"
									+"<a href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
									+"<div class='weui-media-box__hd'>";
							tabHtml += "<img class='weui-media-box__thumb' src='images/nopic.jpg'>";
							tabHtml+="</div>"
									+"<div class='weui-media-box__bd'>"
									+"<h4 class='weui-media-box__title ft-bold400'><i class='icon-position'></i>"+j.applyName+"</h4>"
									+"<p class='line-x1eee'></p>"
									+"<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"+j.driveName+"</p>"
									+"<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"+new Date(j.createDate).format("yyyy-MM-dd hh:mm")+"</p>"
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