var totalPages;
window.onload=function(){
	anchor(idNum);
}
var formatNumber = function (n) {
    return n < 10 ? "0" + n : n;
  }

var userPhone;
var cp=1;
function ajaxRefresh(currentPage){
	$.showLoading();
	cp=currentPage;
	Utils.ajax({
		url : 'complaintProposal/findMyComplaintProposalList.action',
		data:{"openid":openid,"pageNum":currentPage,type:$("#type").val()},
		success : function(data) {
			userPhone=data.userInfo.sjhm;
			totalPages=data.pageNums;
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
				if (data.pageNums <= currentPage) {
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
	if (data.pageNums == 0) {
		tab1Html += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有信息</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";

	} else {
		tab1Html += "<div id='sourceList'>";
		$.each(data.list,function(i, j) {
				tab1Html += "<div class=' bg-white mar-t6'>"
					+ "<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
					+ "<i class='icon-number pull-left mar-t6'></i>"
					+ "<span class='text-number pull-left mar-t6 '>"
					+ j.id
					+ "</span>"
					+ "</div>"
					+ "<div class='pull-right mar-r10'>";
			
			var track=j.track==1?"已处理":"未处理";
			var type = $("#type").val();
			//建议
			if(type==1){
				tab1Html += "</div>"
					+ "<p class='clearfix line-1eee'></p>"
					+ "<a href='javascript:void(0)' onclick='jumpPage(\"complaintProposal/findSuperviseDetail.action?id="+j.id+"&type="+type+"&viewName=useProposalDetail.jsp\")' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
					+ "<div class='weui-media-box__hd' style='position: relative'>"
					+ "<i class='bg-green-merald tip-state' style='position: absolute;'>"
					+ track + "</i>";
			}else{
				tab1Html += "</div>"
					+ "<p class='clearfix line-1eee'></p>"
					+ "<a href='javascript:void(0)' onclick='jumpPage(\"complaintProposal/findSuperviseDetail.action?id="+j.id+"&type="+type+"&viewName=useSuperviseDetail.jsp\")' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
					+ "<div class='weui-media-box__hd' style='position: relative'>"
					+ "<i class='bg-green-merald tip-state' style='position: absolute;'>"
					+ track + "</i>";
			}
				tab1Html += "<img class='weui-media-box__thumb' src='images/nopic.jpg'>";
			tab1Html += "</div>"
					+ "<div class='weui-media-box__bd'>"
					+ "<h4 class='weui-media-box__title ft-bold400'><i class='icon-position'></i>"
					+ j.title
					+ "</h4>"
					+ "<p class='line-x1eee'></p>"
					+ "<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"
					+ "</p>"
					+ "<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"
					+ new Date(j.createTime).format("yyyy-MM-dd hh:mm")
					+ "</p>" + "</div>"
					+ "</a>" + "</div>";
			});
		if (data.pageNums == currentPage) {
			tab1Html += "<div class='weui-loadmore weui-loadmore_line'>"
					+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
					+ "</div>";
		}
		tab1Html += "</div>";
		if (data.pageNums > currentPage && currentPage == 1) {
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
	$.showLoading();
	var type = $("#type").val();
	var viewName;
	//投诉
	if(type==0){
		viewName="useSupervise.jsp";
	}else{
		viewName="useProposal.jsp";
	}
	Utils.ajax({
		url:"complaintProposal/forwardComplaintProposal.action",
		data:{openid:openid,viewName:viewName,type:type,idNum:idNum},
		success:function(data){
			if(data.config!=null){
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
		}
	})
	$.hideLoading();
}
function changeCauseNum(obj){
	$("#causeNum").text($(obj).val().length);
}
/**
 * 我要投诉
 */
function useSupervise(obj){
	$.showLoading();
	$(obj).attr("disabled",true);
	var boardLocation=$("#boardLocation").val().trim();
	var useCause=$("#useCause").val().trim();
	var type= $("#type").val();
	if(boardLocation==""){
		$.toast("请输入主题", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(useCause==""){
		$.toast("请输入内容", "forbidden");
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
		url : 'complaintProposal/insertComplaintProposal.action',
		data:{
			title:boardLocation,
			content:useCause,
			type:type,
			openid:openid,
			filePaths:filePaths,
			oldFileNames:oldFileNames,
			fileNames:fileNames,
			fileSizes:fileSizes,
			fileExtensions:fileExtensions
			},
		success : function(data) {
			if(data.success){
				$.hideLoading();
				$.toast("操作成功");
				resetImg('supervise/wx_supervise');
				setTimeout(function(){location.reload()},3000);
			} else {
				$.toast("操作失败，请重试","forbidden");
			}
			$(obj).attr("disabled",false);
		},
		error:function(){
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
			"backType" : "supervise"
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
	$.showLoading();
	Utils.ajax({
		url : 'wxUserBase/isBindBackground.action',
		data : {
			"openid" : openid,
			"backType" : "supervise"
		},
		success : function(data) {
			$.hideLoading();
			var tab3Html = "";
			if (data.backOrgCode == null || data.backOrgCode == "") {
				tab3Html = "<div class='weui-cells bg-white mar-t6 '>"
						+ "<div class='center-text hei-36 line-hei36 ft-grey-999 line-d1e'>"
						+ "<span class='line-mid'>&nbsp;&nbsp;</span> "
						+ "<span class='ft-bold400'>工号绑定</span><span class='ft14'>（微报服务监督工单处理）</span>"
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
	$.showLoading();
	var type = $("#type").val();
	Utils.ajax({
		url : 'complaintProposal/findComplaintProposalList.action',
		data : {
			"backAccount":backAccount,
			"permitCode" : permitStr,
			"jurisdictionCode":orgCode,
			"pageNum" : currentBackPage,
			"type" : type,
			"openid":openid
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
				if (data.pageNums <= currentBackPage) {
					$("#wxBackLoadmore").hide();
					$("#tab3").destroyInfinite();
				}
			}
			$.hideLoading();
		},
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
	if (data.pageNums == 0) {
		tabHtml += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有待处理数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";

	} else {
		tabHtml += "<div id='sourceBack'>";
		$.each(data.list,function(i, j) {
				var track=j.track==1?"已处理":"未处理";
				var type = $("#type").val();
				//建议
				if(type==1){
					tabHtml+="<div class=' bg-white mar-t6' onclick='jumpPage(\"complaintProposal/findSuperviseDetail.action?id="+j.id+"&viewName=useProposalDetail2.jsp\")'>"
						+"<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
						+"<i class='icon-number pull-left mar-t6'></i>"
						+"<span class='text-number pull-left mar-t6 '>"+j.id+"</span>"
						+"</div>"
						+"<div class='pull-right mar-r10'><i class='bg-green-merald tip-state'>"+track+"</i></div>"
						+"<p class='clearfix line-1eee'></p>"
						+"<a href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
						+"<div class='weui-media-box__hd'>";
				}else{
					tabHtml+="<div class=' bg-white mar-t6' onclick='jumpPage(\"complaintProposal/findSuperviseDetail.action?id="+j.id+"&viewName=useSuperviseDetail2.jsp\")'>"
						+"<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
						+"<i class='icon-number pull-left mar-t6'></i>"
						+"<span class='text-number pull-left mar-t6 '>"+j.id+"</span>"
						+"</div>"
						+"<div class='pull-right mar-r10'><i class='bg-green-merald tip-state'>"+track+"</i></div>"
						+"<p class='clearfix line-1eee'></p>"
						+"<a href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
						+"<div class='weui-media-box__hd'>";
				}
					tabHtml += "<img class='weui-media-box__thumb' src='images/nopic.jpg'>";
				tabHtml+="</div>"
					+"<div class='weui-media-box__bd'>"
					+"<h4 class='weui-media-box__title ft-bold400'><i class='icon-position'></i>"+j.title+"</h4>"
					+"<p class='line-x1eee'></p>"
					+"<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"+new Date(j.createTime).format("yyyy-MM-dd hh:mm")+"</p>"
					+"</div>"
					+"</a>"
					+"</div>";
			});
		if (data.pageNums <= currentPage) {
			tabHtml += "<div class='weui-loadmore weui-loadmore_line'>"
					+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
					+ "</div>";
		}
		tabHtml += "</div>";
		if (data.pageNums > currentPage && currentPage == 1) {
			tabHtml += "<div class='weui-loadmore' id='wxBackLoadmore'>"
					+ "<i class='weui-loading'></i>"
					+ "<span class='weui-loadmore__tips'>正在加载</span>"
					+ "</div>";
		}
		if(currentPage==1){
			tabHtml+="<div style='height:68px;'>&nbsp;</div>";
		}
	}
	$.hideLoading();
	return tabHtml;
}