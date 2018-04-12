
var backAccount;
var permitStr;
var orgCode;
var uphone;
var tlid="";
window.onload=function(){
	$.showLoading();
	var currentDate=new Date().format('yyyy-MM-dd');
	var prevDate=new Date(new Date()-24*60*60*1000*2).format('yyyy-MM-dd');
	$("#createTime").calendar({
		minDate:prevDate,
		maxDate:currentDate,
		dateFormat:'yyyy-mm-dd',
		onClose:function(p){
			$.showLoading();
			getBaseData();
		}
	});
	$("#createTime").val(currentDate);
	gdcl();
	
	
}

/**
 * tab切换
 * @param divId
 */
function anchor(divId){
	$.showLoading();
	for(var i=0;i<2;i++){
		$("#anchor"+(i+1)).removeClass("weui-bar__item--on");
		$("#tab"+(i+1)).removeClass("weui-tab__bd-item--active");
	}
	$("#anchor"+divId).addClass("weui-bar__item--on");
	$("#tab"+divId).addClass("weui-tab__bd-item--active");
	if(divId==1){
		ajaxRefresh(1);
	} else if(divId==2){
		getBaseData();
	}
}

/**
 * 获取后台绑定帐号
 */
function gdcl() {
	$.showLoading();
	Utils.ajax({
		url : 'wxUserBase/isBindBackground.action',
		data : {
			"openid" : openid,
			"backType" : "vehicle"
		},
		success : function(data) {
			$.hideLoading();
			if (data.backOrgCode != null && data.backOrgCode != "") {
				backAccount = data.backAccount;
				permitStr = data.permitString;
				orgCode = data.backOrgCode;
				anchor(idNum);
			}
		}
	});
}

/**
 * 根据当前选择日期查询是否已写日志
 */
function getBaseData(){
	Utils.ajax({
		url : 'trainningLog/findByDateAndOrg.action',
		data : {
			"queryDate" : $("#createTime").val(),
			"orgCode" : orgCode,
			"userAccount" : backAccount
		},
		success : function(data) {
			$.hideLoading();
			if(data.data!=""){
				tlid=data.data.id;
				$("#firstSecurityCheck").val(data.data.firstSecurityCheck);
				$("#lastSecurityCheck").val(data.data.lastSecurityCheck);
				$("#maintenance").val(data.data.maintenance);
				$("#oilNum").val(data.data.oilNum);
				$("#oilGasNum").val(data.data.oilGasNum);
				$("#morningRoute").val(data.data.morningRoute);
				$("#morningStuName").val(data.data.morningStuName);
				$("#afternoonRoute").val(data.data.afternoonRoute);
				$("#afternoonStuName").val(data.data.afternoonStuName);
				$("#subjectTwoApplyName").val(data.data.subjectTwoApplyName);
				$("#subjectTwoQualifiedName").val(data.data.subjectTwoQualifiedName);
				$("#subjectThreeApplyName").val(data.data.subjectThreeApplyName);
				$("#subjectThreeQualifiedName").val(data.data.subjectThreeQualifiedName);
			} else {
				tlid="";
				$("#firstSecurityCheck").val("");
				$("#lastSecurityCheck").val("");
				$("#maintenance").val("");
				$("#oilNum").val("");
				$("#oilGasNum").val("");
				$("#morningRoute").val("");
				$("#morningStuName").val("");
				$("#afternoonRoute").val("");
				$("#afternoonStuName").val("");
				$("#subjectTwoApplyName").val("");
				$("#subjectTwoQualifiedName").val("");
				$("#subjectThreeApplyName").val("");
				$("#subjectThreeQualifiedName").val("");
			}
		}
	});
}

/**
 * 提交更改或新增
 * @param obj
 */
function submitLog(obj){
	$(obj).attr("disabled",true);
	var createTime=$("#createTime").val();
	var firstSecurityCheck=$("#firstSecurityCheck").val();
	var lastSecurityCheck=$("#lastSecurityCheck").val();
	var maintenance=$("#maintenance").val();
	var oilNum=$("#oilNum").val();
	var oilGasNum=$("#oilGasNum").val();
	var morningRoute=$("#morningRoute").val();
	var morningStuName=$("#morningStuName").val();
	var afternoonRoute=$("#afternoonRoute").val();
	var afternoonStuName=$("#afternoonStuName").val();
	var subjectTwoApplyName=$("#subjectTwoApplyName").val();
	var subjectTwoQualifiedName=$("#subjectTwoQualifiedName").val();
	var subjectThreeApplyName=$("#subjectThreeApplyName").val();
	var subjectThreeQualifiedName=$("#subjectThreeQualifiedName").val();
	if(oilNum!="" && isNaN(oilNum)){
		$(obj).attr("disabled",false);
		$.toast("加油数量只能输入数字","forbidden");
		return;
	}
	if(oilGasNum!="" && isNaN(oilGasNum)){
		$(obj).attr("disabled",false);
		$.toast("加气数量只能输入数字","forbidden");
		return;
	}
	$.showLoading();
	Utils.ajax({
		url : 'trainningLog/updateTrainingLog.action',
		data : {
			"orgCode" : orgCode,
			"userAccount" : backAccount,
			createTime :createTime,
			firstSecurityCheck:firstSecurityCheck,
			lastSecurityCheck:lastSecurityCheck,
			maintenance:maintenance,
			oilNum:oilNum,
			oilGasNum:oilGasNum,
			morningRoute:morningRoute,
			morningStuName:morningStuName,
			afternoonRoute:afternoonRoute,
			afternoonStuName:afternoonStuName,
			subjectTwoApplyName:subjectTwoApplyName,
			subjectTwoQualifiedName:subjectTwoQualifiedName,
			subjectThreeApplyName:subjectThreeApplyName,
			subjectThreeQualifiedName:subjectThreeQualifiedName,
			id:(tlid==""?null:tlid)
		},
		success : function(data) {
			$.hideLoading();
			if(data.success){
				$.toast("提交成功");
				setTimeout(function(){location.reload();},2000);
			}
		}
	});
}

/**
 * 获取列表数据
 */
var cp=1;
function ajaxRefresh(currentPage){
	cp=currentPage;
	Utils.ajax({
		url : 'trainningLog/findTrainingLog.action',
		data:{"userAccount":backAccount,"orgCode":orgCode,"currentPage":currentPage,"displayLength":10},
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

/**
 * 拼接列表html
 * @param data
 * @param currentPage
 * @returns {String}
 */
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
				+ "<p class='ft18 ft-grey-999'>亲，您还没有日志数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";

	} else {
		tab1Html += "<div id='sourceList'>";
		$
				.each(
						data.data,
						function(i, j) {
							
							tab1Html += "<div class=' bg-white mar-t6' style='height:50px;' onclick='jumpPage(\"trainningLog/goDetail.action?id="+j.id+"\")'>"
								+ "<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
								+ "<i class='icon-number pull-left mar-t16'></i>"
								+ "<span class='text-number pull-left mar-t14'>"
								+ j.logName
								+ "</span>"
								+ "</div>"
								+ "<div class='pull-right mar-r10'>"
								+ "</div>"
								+ "</div>";
						
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