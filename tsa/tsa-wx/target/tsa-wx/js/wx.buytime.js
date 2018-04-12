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
		url : 'buytime/getMyList.action',
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
						cp+=1;
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
				+ "<div class='down'>下拉刷新</div>" + "<div class='up'>释放刷新</div>"
				+ "<div class='refresh'>正在刷新</div>" + "</div>";
	}
	if (data.totalPages == 0) {
		tab1Html += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有购买记录</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";

	} else {
		tab1Html += "<div id='sourceList'>";
		$
				.each(
						data.data,
						function(i, j) {
							var sn="";
							if(j.state=="0"){
								sn="待审核";
							} else if(j.state=="1"){
								sn="审核通过";
							} else if(j.state=="2"){
								sn="审核不通过";
							}
							var isPay="";
							if(j.hasPay=="0"){
								isPay="待缴费";
							} else if(j.hasPay=="1"){
								isPay="已缴费";
							} else if(j.hasPay=="2"){
								isPay="未缴费";
							}
							var amount="";
							if(j.amount==null || j.amount==""){
								amount="暂未定价";
							} else {
								amount="￥"+j.amount
							}
							tab1Html += "<div class=' bg-white mar-t6'>"
								+ "<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
								+ "<i class='icon-number pull-left mar-t6'></i>"
								+ "<span class='text-number pull-left mar-t6 '>"
								+ j.invoiceNum
								+ "</span>"
								+ "</div>"
								+ "<div class='pull-right mar-r10'>";
						
						/*if (j.state == '101' || j.state=='103') {
							tab1Html += "<i class='bg-deep-blue tip-state' onclick='closeVehicleOrder("
									+ j.id + ")' >撤销预约单</i>";
						}
						if (j.state == '106' && j.evaluate=='0') {
							tab1Html += "<i class='bg-deep-blue tip-state open-popup' onclick='openPup("
									+ j.id + ",\""+j.departPlace+"\",\""+j.destination+"\")'>评价</i>";
						}*/
						tab1Html += "</div>"
								+ "<p class='clearfix line-1eee'></p>"
								+ "<a href='javascript:void(0)'  class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
								+ "<div class='weui-media-box__hd' style='position: relative'>"
								+ "<i class='bg-green-merald tip-state' style='position: absolute;'>"
								+ isPay + "</i>";
						tab1Html += "<img class='weui-media-box__thumb' src='images/nopic.jpg'>";
						

						tab1Html += "</div>"
								+ "<div class='weui-media-box__bd'>"
								+ "<h4 class='weui-media-box__title ft-bold400'><i class='icon-position'></i>"
								+ j.buyTime/60+"学时"
								+ "</h4>"
								+ "<p class='line-x1eee'></p>"
								+ "<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"
								+ amount
								+ "</p>"
								+ "<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"
								+ new Date(j.createDate)
										.format("yyyy-MM-dd hh:mm")
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

var xgh;
var userName;
var telephone;
var userSex;

function getBaseData(){
	Utils.ajax({
		url : 'buytime/getBaseData.action',
		data:{"openid":openid},
		success : function(data) {
			$.hideLoading();
			if(!data.suc){
				$.toast(data.msg,"forbidden");
				setTimeout(function(){jumpPage("wx/jumpPage.action?viewName=index.jsp");},2000);
				return;
			}
			var buyTime=data.total.buyTime/60,usedTime=data.total.usedTime/60,surplusTime=data.total.surplusTime/60;
			if(buyTime==null){
				buyTime=0;
			}
			if(usedTime==null){
				usedTime=0;
			}
			if(surplusTime==null){
				surplusTime=0;
			}
			xgh=data.userInfo.xgh;
			userName=data.userInfo.xm;
			telephone=data.userInfo.sjhm;
			userSex=data.userInfo.xbm;
			$("#totalTime").text("总共购买学时："+buyTime+"小时");
			$("#useTime").text("已用学时："+usedTime+"小时");
			$("#leftTime").text("剩余学时："+surplusTime+"小时");
		}
	});
}

/**
 * 购买学时
 */
function buytime(obj){
	$(obj).attr("disabled",true);
	var timeNum=$("#timeNum").val().trim();
	
	if(timeNum==""){
		$.toast("请输入学时","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	var reg = /^[1-9]\d*$/;
	if (!reg.test(timeNum)) {
		$.toast("学时输入有误,请输入正整数","forbidden");
		return  false;
	}
	$.showLoading();
	Utils.ajax({
		url : 'buytime/buyTime.action',
		data:{"user":xgh,"userName":userName,"buyTelephone":telephone,"userSex":userSex,"buyTime":timeNum*60},
		success : function(data) {
			$.hideLoading();
			if(data.success){
				$.toast("购买申请已提交，请等待审核");
				setTimeout(function(){location.reload();},2000);
			} else {
				$.toast("购买申请提交失败，请重试","forbidden");
			}
			$(obj).attr("disabled",false);
		},
		error:function(a,b,c){
			
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
								+ "<p><input class='weui-input' type='tel' placeholder='手机号' id='bindUserPhone' value='"+data.uphone+"' readonly></p>"
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
						getBackList(permitStr, orgCode);
					}
				}
			});
}

getBackList = function(permitStr, orgCode) {
	Utils.ajax({
		url : 'buytimeBack/getBackList.action',
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
							var sn="";
							if(j.state=="0"){
								sn="待审核";
							} else if(j.state=="1"){
								sn="审核通过";
							} else if(j.state=="2"){
								sn="审核不通过";
							}
							var isPay="";
							if(j.hasPay=="0"){
								isPay="待缴费";
							} else if(j.hasPay=="1"){
								isPay="已缴费";
							} else if(j.hasPay=="2"){
								isPay="未缴费";
							}
							var amount="";
							if(j.amount==null || j.amount==""){
								amount="暂未定价";
							} else {
								amount="￥"+j.amount
							}
							tabHtml+="<div class=' bg-white mar-t6' onclick='jumpPage(\"buytimeBack/getDetail.action?id="+j.id+"&backAccount="+backAccount+"&orgCode="+orgCode+"\")'>"
									+"<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
									+"<i class='icon-number pull-left mar-t6'></i>"
									+"<span class='text-number pull-left mar-t6 '>"+isPay+"</span>"
									+"</div>"
									+"<div class='pull-right mar-r10'><i class='bg-green-merald tip-state'>"+sn+"</i></div>"
									+"<p class='clearfix line-1eee'></p>"
									+"<a href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
									+"<div class='weui-media-box__hd'>"
									+"<img class='weui-media-box__thumb' src='images/nopic.jpg'>";
							
							tabHtml+="</div>"
									+"<div class='weui-media-box__bd'>"
									+"<h4 class='weui-media-box__title ft-bold400'><i class='icon-position'></i>"+j.buyTime/60+"小时</h4>"
									+"<p class='line-x1eee'></p>"
									+"<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"+amount+"</p>"
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


/*
function closeVehicleOrder(id){
	$.confirm({
		  title: '撤销预约单',
		  text: '确定撤销该预约单？',
		  onOK: function () {
			  Utils.ajax({
					url : 'wxUseVehicle/closeVehicleOrder.action',
					data:{"id":id,"openid":openid},
					success : function(data) {
						if(data.success){
							$.toast("撤销成功");
							setTimeout(ajaxRefresh(1),1000);
						} else {
							$.toast("撤销失败，请重试","forbidden");
						}
					}
				});
		  },
		  onCancel: function () {
		  }
		});
}
var jsypf=1;
var clpf=1;
var zhpf=1;
var orderId;
function openPup(id,from,to){
	orderId=id;
	$("#half").popup();
	$("#spanFrom").text(from);
	$("#spanTo").text(to);
	$("#jsypf").width(26);
	$("#clpf").width(26);
	$("#zhpf").width(26);
}

function jsy(obj){
	jsypf=obj;
	$("#jsypf").width(obj*26);
}
function cl(obj){
	clpf=obj;
	$("#clpf").width(obj*26);
}
function zh(obj){
	zhpf=obj;
	$("#zhpf").width(obj*26);
}
function appraiseVehicleOrder(){
	var commentStr=$("#commentStr").val();
	//Long id,int orderStar,String evaluateInfo,
	//int personStar,int vehicleStar,String openid
	Utils.ajax({
		url : 'wxUseVehicle/evaluateOrder.action',
		data:{"id":orderId,"openid":openid,"orderStar":zhpf,"evaluateInfo":commentStr,"personStar":jsypf,"vehicleStar":clpf},
		success : function(data) {
			if(data.success){
				$.toast("撤销成功");
				setTimeout(ajaxRefresh(1),1000);
			} else {
				$.toast("撤销失败，请重试","forbidden");
			}
		}
	});
}*/