var isStore = false;
var backAccount;
var permitStr;
var orgCode;
var uphone;
var storeId;
$(function() {
	gdcl();
});

function anchor(divId) {
	$.showLoading();
	for (var i = 0; i < 3; i++) {
		$("#anchor" + (i + 1)).removeClass("weui-bar__item--on");
		if (isStore) {
			$("#tab" + (i + 1)).removeClass("weui-tab__bd-item--active");
		}
	}
	$("#anchor" + divId).addClass("weui-bar__item--on");
	if (isStore) {

		$("#tab" + divId).addClass("weui-tab__bd-item--active");
		if (divId == 1) {
			getPendingOrder();
		} else if (divId == 2) {
			getProcessedOrder();
		} else if (divId == 3) {
			gdcl();
		}
	} else {
		$.hideLoading();
	}
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
	$(obj).removeClass("bg-orange");
	var bindTime = 60;
	var bindInterval = setInterval(function() {
		if (bindTime == 0) {
			backFlag = false;
			$(obj).attr("disabled", false);
			$(obj).removeClass("bg-grey-999");
			$(obj).addClass("bg-orange");
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
			"backType" : "diet"
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

/**
 * 查询是否绑定后台帐号，如果已绑定，显示待处理列表
 */
function gdcl() {
	$.showLoading();
	Utils.ajax({
		url : 'wxUserBase/isBindBackground.action',
		data : {
			"openid" : openid,
			"backType" : "diet"
		},
		success : function(data) {
			$.hideLoading();
			uphone = data.uphone;
			var tab3Html = "";
			if (data.backOrgCode == null || data.backOrgCode == "") {
				isStore = false;
				$("#sourceL").show();
				$("#sourceK").hide();
				$("#bindUserPhone").val(uphone);
			} else {
				isStore = true;
				$("#sourceL").hide();
				$("#sourceK").show();
				backAccount = data.backAccount;
				permitStr = data.permitString;
				orgCode = data.backOrgCode;
				for (var i = 0; i < 3; i++) {
					$("#anchor" + (i + 1)).removeClass("weui-bar__item--on");
					$("#tab" + (i + 1)).removeClass("weui-tab__bd-item--active");
				}
				$("#anchor3").addClass("weui-bar__item--on");
				$("#tab3").addClass("weui-tab__bd-item--active");
				Utils.ajax({
					url: 'storeManage/getStoreInfo.action',
					data: {account:backAccount},
					success: function(data){
						if (data.success) {
							var store = data.store;
							storeId = store.id;
							var operHtml = getOperHtml(permitStr);
							$("#sourceK").empty();
							$("#sourceK").html(operHtml);
						}else{
							jumpPage('userCenter/userCenterHome.action');
						}
					}
				});
			}
		}
	});
}

getOperHtml = function(permitStr) {
	var operHtml = "<div class='bg-white'>";
	if (permitStr.indexOf("004001") >= 0) {
		operHtml += "<div class='weui-cell line-d1e' onclick='jumpPage(\"storeManage/goStoreInfo.action?account="
				+ backAccount
				+ "\")'>"
				+ "<div class='weui-cell__bd'>"
				+ "<a href='javascript:;' class='ft-grey-666'>餐厅信息</a>"
				+ "</div>"
				+ "<div class='weui-cell__ft'><img src='images/icon05.png' width='10' height='18' alt=''/></div>"
				+ "</div>";
	}
	if (permitStr.indexOf("004002") >= 0) {
		operHtml += "<div class='weui-cell line-d1e' onclick='jumpPage(\"storeManage/goProductMange.action?storeId="
				+ storeId
				+ "\")'>"
				+ "<div class='weui-cell__bd'>"
				+ "<a href='javascript:;' class='ft-grey-666'>餐品管理</a>"
				+ "</div>"
				+ "<div class='weui-cell__ft'><img src='images/icon05.png' width='10' height='18' alt=''/></div>"
				+ "</div>";
	}
	if (permitStr.indexOf("004003") >= 0) {
		operHtml += "<div class='weui-cell line-d1e' onclick='jumpPage(\"storeManage/goFoodManage.action?storeId="
				+ storeId+"&account="+backAccount
				+ "\")'>"
				+ "<div class='weui-cell__bd'>"
				+ "<a href='javascript:;' class='ft-grey-666'>菜品管理</a>"
				+ "</div>"
				+ "<div class='weui-cell__ft'><img src='images/icon05.png' width='10' height='18' alt=''/></div>"
				+ "</div>";
	}
	if (permitStr.indexOf("006001") >= 0) {
		operHtml += "<div class='weui-cell line-d1e' onclick='jumpPage(\"storeManage/goCommentManage.action?storeId="
				+ storeId
				+ "\")'>"
				+ "<div class='weui-cell__bd'>"
				+ "<a href='javascript:;' class='ft-grey-666'>评价管理</a>"
				+ "</div>"
				+ "<div class='weui-cell__ft'><img src='images/icon05.png' width='10' height='18' alt=''/></div>"
				+ "</div>";
	}
	operHtml += "</div>";
	return operHtml;
}
var currentPendingPage = 1;
function getPendingOrder() {
	var pendingHtml = "";
	if (permitStr.indexOf("005001") >= 0) {
		Utils.ajax({
			url : 'dietOrderManager/getOrderList.action',
			data : {
				"backAccount" : backAccount,
				"pageNum" : currentPendingPage,
				"displayLength" : 10,
				"receiveFlag" : 0
			},
			success : function(data) {
				$.hideLoading();
				pendingHtml = getBackHtml(data, currentPendingPage);
				$("#tab1").pullToRefreshDone();
				if (currentPendingPage == 1) {
					$("#tab1").empty();
					$("#tab1").html(pendingHtml);
					$("#tab1").pullToRefresh();
					$("#tab1").on("pull-to-refresh", function() {
						currentPendingPage = 1;
						getPendingOrder();
					});
					$("#tab1").pullToRefreshDone();
					if (data.pageNums > 1) {
						var backLoading = false;
						$("#tab1").infinite().on("infinite", function() {
							if (backLoading)
								return;
							backLoading = true;
							currentPendingPage += 1;
							getPendingOrder();
							setTimeout(function() {
								backLoading = false;
							}, 1000);
						});
					}
				} else {
					$("#sourceAdd").append(pendingHtml);
					if (data.pageNums <= currentPendingPage) {
						$("#pendingLoadmore").hide();
						$("#tab1").destroyInfinite();
					}
				}
			},
			error : function() {
				$("#tab1").pullToRefreshDone();
			}

		});
	} else {
		$.hideLoading();
		pendingHtml = "<div id='sourceList' class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有相关权限</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";
		$("#tab1").empty();
		$("#tab1").html(pendingHtml);
	}

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
	if (data.pageNums==undefined || data.pageNums == 0) {
		tabHtml += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有待处理数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";

	} else {
		tabHtml += "<div id='sourceAdd'>";
		$
				.each(
						data.list,
						function(i, j) {
							var payTypeStr="";
							if(j.payType==1){
								payTypeStr="支付宝";
							} else if (j.payType==2){
								payTypeStr="微信";
							} else if (j.payType==3){
								payTypeStr="校园卡";
							} else if (j.payType==4){
								payTypeStr="到付";
							}
							tabHtml += "<div class=' bg-white mar-t6'>"
									+ "<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
									+ "<i class='icon-number-a pull-left mar-t6'></i>"
									+ "<span class='text-number pull-left mar-t6 ft-grey-666'>"+j.orderNum+"</span>"
									+ "</div>";
							if(j.state==1){
								tabHtml += "<div class='pull-right mar-r10' onclick='acceptOrder(\""+j.orderNum+"\")'><i class='bg-orange tip-state'>接单</i></div>&nbsp;";
							}
							tabHtml += "<div class='pull-right mar-r10' onclick='jumpPage(\"dietOrderManager/goOrderDetail.action?backAccount="+backAccount+"&orderNum="+j.orderNum+"\")'><i class='bg-green-merald tip-state'>详情</i></div>"
									+ "<p class='clearfix line-1eee'></p>"
									+ "<div class='weui-media-box pad-15-0 line-b weui-media-box_appmsg'>"
									+ "<div class='weui-media-box__hd'><img class='weui-media-box__thumb' src='fileoper/downFile.action?filepath="
													+ j.foodPath + "' onerror='javascript:this.src=\"images/nopic.jpg\";'></div>"
									+ "<div class='weui-media-box__bd'>"
									+ "<h4 class='weui-media-box__title ft-bold600 ft14'>"+j.foodNames+"</h4>"
									+ "<p class='ft-grey-666 ft12'><span>"+j.receiveName+"</span>&nbsp;<span>"+j.orderPhone+"</span></p>"
									+ "<p class='ft-grey-999 ft12'>"+j.orderDate+"</p>"
									+ "</div>"
									+ "<div class='pull-right mar-r15 ft12'>共<span class='ft22 text-number ft-red'>¥"+j.price+"</span></div>"
									+ "<p class='clearfix'></p>"
									+ "</div>"
									+ "</div>"
									+ "<div class='weui-cell line-d1e bg-white ft12'>"
									+ "<div class='weui-cell__hd'><i class='icon-position'></i></div>"
									+ "<div class='weui-cell__bd ft-grey-999'>"+j.address+"</div>"
									+ "<div class='weui-cell__ft'>"+payTypeStr+" 已付款</div>"
									+ "</div>";

						});
		if (data.pageNums <= currentPage) {
			tabHtml += "<div class='weui-loadmore weui-loadmore_line'>"
					+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
					+ "</div>";
		}
		tabHtml += "</div>";
		if (data.pageNums > currentPage && currentPage == 1) {
			tabHtml += "<div class='weui-loadmore' id='pendingLoadmore'>"
					+ "<i class='weui-loading'></i>"
					+ "<span class='weui-loadmore__tips'>正在加载</span>"
					+ "</div>";
		}
		
	}

	return tabHtml;
}

function acceptOrder(orderNum){
	$.confirm("确认接单？", function() {
		$.showLoading();
		Utils.ajax({
			url : 'dietOrderManager/acceptOrder.action',
			data : {
				"backAccount" : backAccount,
				"orderNum" : orderNum
			},
			success : function(data) {
				if(data){
					$.toast("接单成功");
					setTimeout(function(){
						currentPendingPage=1;
						getPendingOrder();
					},2000);
				}
			}
		});
		
		
		  }, function() {
		  //点击取消后的回调函数
		  });
}



var currentProcessedPage = 1;
function getProcessedOrder() {
	var processedHtml = "";
	if (permitStr.indexOf("005001") >= 0) {
		Utils.ajax({
			url : 'dietOrderManager/getOrderList.action',
			data : {
				"backAccount" : backAccount,
				"pageNum" : currentProcessedPage,
				"displayLength" : 10,
				"receiveFlag" : 1
			},
			success : function(data) {
				$.hideLoading();
				processedHtml = getProcessedHtml(data, currentProcessedPage);
				$("#tab2").pullToRefreshDone();
				if (currentProcessedPage == 1) {
					$("#tab2").empty();
					$("#tab2").html(processedHtml);
					$("#tab2").pullToRefresh();
					$("#tab2").on("pull-to-refresh", function() {
						currentProcessedPage = 1;
						getProcessedOrder();
					});
					$("#tab2").pullToRefreshDone();
					if (data.pageNums > 1) {
						var backLoading = false;
						$("#tab2").infinite().on("infinite", function() {
							if (backLoading)
								return;
							backLoading = true;
							currentProcessedPage += 1;
							getProcessedOrder();
							setTimeout(function() {
								backLoading = false;
							}, 1000);
						});
					}
				} else {
					$("#processedSource").append(processedHtml);
					if (data.pageNums <= currentProcessedPage) {
						$("#processedLoadmore").hide();
						$("#tab2").destroyInfinite();
					}
				}
			},
			error : function() {
				$("#tab2").pullToRefreshDone();
			}

		});
	} else {
		$.hideLoading();
		processedHtml = "<div id='sourceList' class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有相关权限</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";
		$("#tab2").empty();
		$("#tab2").html(processedHtml);
	}

}
getProcessedHtml = function(data, currentPage) {
	var tabHtml = "";
	if (currentPage == 1) {
		tabHtml += "<div class='weui-pull-to-refresh__layer'>"
				+ "<div class='weui-pull-to-refresh__arrow'></div>"
				+ "<div class='weui-pull-to-refresh__preloader'></div>"
				+ "<div class='down'>下拉刷新</div>" + "<div class='up'>释放刷新</div>"
				+ "<div class='refresh'>正在刷新</div>" + "</div>";
	}
	if (data.pageNums==undefined || data.pageNums == 0) {
		tabHtml += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有待处理数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";

	} else {
		tabHtml += "<div id='processedSource'>";
		$
				.each(
						data.list,
						function(i, j) {
							var payTypeStr="";
							if(j.payType==1){
								payTypeStr="支付宝";
							} else if (j.payType==2){
								payTypeStr="微信";
							} else if (j.payType==3){
								payTypeStr="校园卡";
							} else if (j.payType==4){
								payTypeStr="到付";
							}
							//（0 代付款 1已付款 2 商家接单 3 派送中 4已完成 5 已取消）
							var stateStr="";
							if(j.state==0){
								stateStr="待付款";
							} else if(j.state==1){
								stateStr="已付款";
							} else if(j.state==2){
								stateStr="已接单";
							} else if(j.state==3){
								stateStr="派送中";
							} else if(j.state==4){
								stateStr="已完成";
							} else if(j.state==5){
								stateStr="已取消";
							}
							tabHtml += "<div class=' bg-white mar-t6' onclick='jumpPage(\"dietOrderManager/goOrderDetail.action?backAccount="+backAccount+"&orderNum="+j.orderNum+"\")'>"
									+ "<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
									+ "<i class='icon-number-a pull-left mar-t6'></i>"
									+ "<span class='text-number pull-left mar-t6 ft-grey-666'>"+j.orderNum+"</span>"
									+ "</div>"
									+ "<div class='pull-right mar-r10'><i class='bg-green-merald tip-state'>"+stateStr+"</i></div>"
									+ "<p class='clearfix line-1eee'></p>"
									+ "<div class='weui-media-box pad-15-0 line-b weui-media-box_appmsg'>"
									+ "<div class='weui-media-box__hd'><img class='weui-media-box__thumb' src='fileoper/downFile.action?filepath="
													+ j.foodPath + "' onerror='javascript:this.src=\"images/nopic.jpg\";'></div>"
									+ "<div class='weui-media-box__bd'>"
									+ "<h4 class='weui-media-box__title ft-bold600 ft14'>"+j.foodNames+"</h4>"
									+ "<p class='ft-grey-666 ft12'><span>"+j.receiveName+"</span>&nbsp;<span>"+j.orderPhone+"</span></p>"
									+ "<p class='ft-grey-999 ft12'>"+j.orderDate+"</p>"
									+ "</div>"
									+ "<div class='pull-right mar-r15 ft12'>共<span class='ft22 text-number ft-red'>¥"+j.price+"</span></div>"
									+ "<p class='clearfix'></p>"
									+ "</div>"
									+ "</div>"
									+ "<div class='weui-cell line-d1e bg-white ft12'>"
									+ "<div class='weui-cell__hd'><i class='icon-position'></i></div>"
									+ "<div class='weui-cell__bd ft-grey-999'>"+j.address+"</div>"
									+ "<div class='weui-cell__ft'>"+payTypeStr+" 已付款</div>"
									+ "</div>";

						});
		if (data.pageNums <= currentPage) {
			tabHtml += "<div class='weui-loadmore weui-loadmore_line'>"
					+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
					+ "</div>";
		}
		tabHtml += "</div>";
		if (data.pageNums > currentPage && currentPage == 1) {
			tabHtml += "<div class='weui-loadmore' id='processedLoadmore'>"
					+ "<i class='weui-loading'></i>"
					+ "<span class='weui-loadmore__tips'>正在加载</span>"
					+ "</div>";
		}
		
	}

	return tabHtml;
}