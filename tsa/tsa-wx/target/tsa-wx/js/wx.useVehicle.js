var totalPages;
window.onload=function(){
	$.showLoading();
	anchor(idNum);
	
	
	$("#startTime").datetimePicker({
        title: '出发时间',
        min:new Date().format("yyyy-MM-dd"),
        yearSplit:'年',
        monthSplit:'月',
        dateSplit:'日',
        onChange: function (picker, values, displayValues) {
          $("#stTime").val(values[0]+"-"+values[1]+"-"+values[2]+" "+values[3]+":"+values[4]+":00");
        }
      });
	//estimateTime
	$("#estimateTime").datetimePicker({
        title: '预估返回时间',
        min:new Date().format("yyyy-MM-dd"),
        yearSplit:'年',
        monthSplit:'月',
        dateSplit:'日',
        onChange: function (picker, values, displayValues) {
          $("#emTime").val(values[0]+"-"+values[1]+"-"+values[2]+" "+values[3]+":"+values[4]+":00");
        }
      });
}
var formatNumber = function (n) {
    return n < 10 ? "0" + n : n;
  }

var userPhone;
var cp=1;
function ajaxRefresh(currentPage){
	cp=currentPage;
	Utils.ajax({
		url : 'wxUseVehicle/getMyUseVehicleList.action',
		data:{"openid":openid,"pageNum":currentPage},
		success : function(data) {
			userPhone=data.userInfo.sjhm;
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
				+ "<div class='down'>下拉刷新</div>" + "<div class='up'>释放刷新</div>"
				+ "<div class='refresh'>正在刷新</div>" + "</div>";
	}
	if (data.totalPages == 0) {
		tab1Html += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有用车数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";

	} else {
		tab1Html += "<div id='sourceList'>";
		$
				.each(
						data.data,
						function(i, j) {
							var cs=j.useCause;
							if(cs==null){
								cs="";
							}
							tab1Html += "<div class=' bg-white mar-t6'>"
								+ "<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
								+ "<i class='icon-number pull-left mar-t6'></i>"
								+ "<span class='text-number pull-left mar-t6 '>"
								+ j.orderNum
								+ "</span>"
								+ "</div>"
								+ "<div class='pull-right mar-r10'>";
						
						if (j.state == '101' || j.state=='103') {
							tab1Html += "<i class='bg-deep-blue tip-state' onclick='closeVehicleOrder("
									+ j.id + ")' >撤销预约单</i>";
						}
						if (j.state == '106' && j.evaluate=='0') {
							tab1Html += "<i class='bg-deep-blue tip-state open-popup' onclick='openPup("
									+ j.id + ",\""+j.departPlace+"\",\""+j.destination+"\")'>评价</i>";
						}
						tab1Html += "</div>"
								+ "<p class='clearfix line-1eee'></p>"
								+ "<a href='javascript:void(0)' onclick='jumpPage(\"wxUseVehicle/getDetail.action?orderNum="+j.orderNum+"\")' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
								+ "<div class='weui-media-box__hd' style='position: relative'>"
								+ "<i class='bg-green-merald tip-state' style='position: absolute;'>"
								+ j.stateName + "</i>";
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
								+ "从 "+j.departPlace+" 到 "+j.destination
								+ "</h4>"
								+ "<p class='line-x1eee'></p>"
								+ "<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"
								+ cs
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
function getBaseData(){
	Utils.ajax({
		url : 'wxUseVehicle/useVehicleBase.action',
		data:{"openid":openid},
		success : function(data) {
			userPhone=data.sjhm;
			xgh=data.xgh;
			$("#orderUserName").val(data.xm);
			$("#orderTelephone").val(data.sjhm);
			$("#useCompany").select({
				title: "请选择二级单位"
			});
			var company=[];
			$.each(data.companys,function(j,i){
				var row={'title':i.orgName,'value':i.orgCode};
				company.push(row);
			});
			$("#useCompanyOne").select({
				title: "请选择用车单位",
				items: company,
				onClose: function (d) {
					$.showLoading();
					var useCompanyOne=$("#useCompanyOne").attr("data-values");
					$("#useCompany").val("");
					$("#useCompany").attr("data-values","");
					Utils.ajax({
		    			url : 'wxUseVehicle/getCompanysByParentCode.action',
		    			data:{"parentCode":useCompanyOne},
		    			success : function(data) {
		    				var proje=[];
			    			$.each(data.companys,function(j,i){
			    				var row={'title':i.orgName,'value':i.orgCode};
			    				proje.push(row);
			    			});
			    			
		    				$("#useCompany").select("update", { items: proje });
		    				$.hideLoading();
		    			},
		    			error:function(){
		    				$.hideLoading();
		    			}
		    		});
				}
			});
			
			var gearbox=[];
			$.each(data.dicts,function(j,i){
				var row={'title':i.dictName,'value':i.dictCode};
				gearbox.push(row);
			});
			$("#vehicleGearbox").select({
				title: "请选择车型",
				items: gearbox
			});
			$.hideLoading();
		}
	});
}
function changeCauseNum(obj){
	$("#causeNum").text($(obj).val().length);
}
/**
 * 我要用车
 */
function useVehicle(obj){
	$(obj).attr("disabled",true);
	var orderUserName=$("#orderUserName").val().trim();
	var orderTelephone=$("#orderTelephone").val().trim();
	var useCompanyOne=$("#useCompanyOne").attr("data-values");
	var useCompany=$("#useCompany").attr("data-values");
	var startTime=$("#stTime").val();
	var emTime=$("#emTime").val();
	var boardLocation=$("#boardLocation").val().trim();
	var departPlace=$("#departPlace").val().trim();
	var destination=$("#destination").val().trim();
	var rideNum=$("#rideNum").val().trim();
	var vehicleGearbox=$("#vehicleGearbox").attr("data-values");
	var useCause=$("#useCause").val().trim();
	var busRider=$("#busRider").val();
	var riderPhone=$("#riderPhone").val();
	if(orderUserName==""){
		$.toast("用车人不能为空","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	var reg = /^1[0-9]{10}$/;
	if(orderTelephone==""){
		$.toast("联系电话不能为空","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if (!reg.test(orderTelephone)) {
		$.toast("请输入正确的手机号码", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(useCompany=="" || useCompany==null || useCompany==undefined){
		$.toast("请选择用车单位", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(startTime==""){
		$.toast("请选择出发时间", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(emTime==""){
		$.toast("请选择预估返回时间", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(emTime<=startTime){
		$.toast("预估返回时间必须大于出发时间", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(boardLocation==""){
		$.toast("请输入上车地点", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	
	if(busRider==""){
		$.toast("请输入乘车人姓名", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(riderPhone==""){
		$.toast("请输入乘车人电话", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(!reg.test(riderPhone)){
		$.toast("乘车人手机格式不正确", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	
	if(departPlace==""){
		$.toast("请输入出发地", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(destination==""){
		$.toast("请输入目的地", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(rideNum==""){
		$.toast("请输入乘坐人数", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(isNaN(rideNum)){
		$.toast("请输入正确的人数", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(vehicleGearbox==""){
		$.toast("请选择车型", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(useCause==""){
		$.toast("请输入用车事由", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	$.showLoading();
	Utils.ajax({
		url : 'wxUseVehicle/useVehicle.action',
		data:{"orderUser":xgh,"orderUserName":orderUserName,"orderTelephone":orderTelephone,"useCompany":useCompany,"startOrderDate":startTime,
			"estimateDate":emTime,"boardLocation":boardLocation,"departPlace":departPlace,"destination":destination,"rideNum":rideNum,
			"vehicleGearbox":vehicleGearbox,"useCause":useCause,"busRider":busRider,"riderPhone":riderPhone},
		success : function(data) {
			if(data.result=="success"){
				$.toast("提交成功，等待审核");
				setTimeout(function(){location.reload();},2000);
			} else {
				$.toast("提交失败，请重试","forbidden");
			}
			$(obj).attr("disabled",false);
			$.hideLoading();
		},
		error:function(){
			$.hideLoading();
		}
	});
	
}

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
	$.showLoading();
	var commentStr=$("#commentStr").val();
	//Long id,int orderStar,String evaluateInfo,
	//int personStar,int vehicleStar,String openid
	Utils.ajax({
		url : 'wxUseVehicle/evaluateOrder.action',
		data:{"id":orderId,"openid":openid,"orderStar":zhpf,"evaluateInfo":commentStr,"personStar":jsypf,"vehicleStar":clpf},
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
						getBackList(data.permitString, data.backOrgCode);
					}
				}
			});
}

getBackList = function(permitStr, orgCode) {
	Utils.ajax({
		url : 'vehicleBack/getVehicleBackList.action',
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
							var cs=j.useCause;
							if(cs==null){
								cs="";
							}
							tabHtml+="<div class=' bg-white mar-t6' onclick='jumpPage(\"vehicleBack/getDetail.action?orderNum="+j.orderNum+"&state="+j.state+"&backAccount="+backAccount+"&orgCode="+orgCode+"\")'>"
									+"<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
									+"<i class='icon-number pull-left mar-t6'></i>"
									+"<span class='text-number pull-left mar-t6 '>"+j.orderNum+"</span>"
									+"</div>"
									+"<div class='pull-right mar-r10'><i class='bg-green-merald tip-state'>"+j.stateName+"</i></div>"
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
									+"<h4 class='weui-media-box__title ft-bold400'><i class='icon-position'></i>从"+j.departPlace+"到"+j.destination+"</h4>"
									+"<p class='line-x1eee'></p>"
									+"<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"+cs+"</p>"
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