var totalPages;
window.onload=function(){
	$.showLoading();
	getBackAccount();
	$("#repairDate").datetimePicker({
        title: '维保时间',
        min:new Date().format("yyyy-MM-dd"),
        yearSplit:'年',
        monthSplit:'月',
        dateSplit:'日',
        onChange: function (picker, values, displayValues) {
          $("#rTime").val(values[0]+"-"+values[1]+"-"+values[2]+" "+values[3]+":"+values[4]+":00");
        }
      });
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
		getBackList();
	}
}


var MAX = 99, MIN = 1;
function decrease(obj){
	var $input=$(obj).next();
	var number = parseInt($input.val() || "0") - 1;
	if (number < MIN) number = MIN;
	$input.val(number);
}
function increase(obj){
	var $input = $(obj).prev();
	  var number = parseInt($input.val() || "0") + 1;
	  if (number > MAX) number = MAX;
	  $input.val(number);
}
function deleteSwipe(obj){
	$(obj).parents('.weui-cell').remove();
}
function closeSwipe(obj){
	$(obj).parents('.weui-cell').swipeout('close');
}


var clid=0;
function addCl(){
	clid++;
	var clhtml="<div class='weui-cell weui-cell_swiped'>"
			            +"<div class='weui-cell__bd'>"
			            +"<div class='weui-cell'>"
			            +"<div class='weui-cell__bd'>"
			            +"<p><input class='weui-input' type='text' placeholder='请选择材料' id='cl"+clid+"' style='width:50%'>" +
			            		"</p>"
			            +"</div>"
			            +"<div class='weui-cell__ft'><span class='weui-input' style='font-size:6px;color:#ddd;'>左滑删除</span>&nbsp;"
			            +"<div class='weui-count'>"
			            +"<a class='weui-count__btn weui-count__decrease' onclick='decrease(this)'></a>"
			            +"<input class='weui-count__number' type='number' id='clnum"+clid+"' value='1' />"
			            +"<a class='weui-count__btn weui-count__increase' onclick='increase(this)'></a>"
			            +"</div>"
			            +"</div>"
			            +"</div>"
			            +"</div>"
			            +"<div class='weui-cell__ft'>"
			            +"<a class='weui-swiped-btn weui-swiped-btn_warn delete-swipeout' href='javascript:' onclick='deleteSwipe(this)'>删除</a>"
			            +"<a class='weui-swiped-btn weui-swiped-btn_default close-swipeout' href='javascript:' onclick='closeSwipe(this)'>关闭</a>"
			            +"</div>"
			            +"</div>";
	$("#cailiao").append(clhtml);
	$('.weui-cell_swiped').swipeout();
	var cl=[];
	$.each(clList,function(j,i){
		var row={'title':i.dictName,'value':i.dictCode};
		cl.push(row);
	});
	$("#cl"+clid).select({
		title: "请选择材料",
		items: cl
	});
}

var cp=1;
function ajaxRefresh(currentPage){
	cp=currentPage;
	Utils.ajax({
		url : 'maintain/findMyList.action',
		data:{"applicant":backAccount,"pageNum":currentPage,"iDisplayLength":10},
		success : function(data) {
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
				+ "<p class='ft18 ft-grey-999'>亲，您还没有维保数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";
	} else {
		tab1Html += "<div id='sourceList'>";
		$
				.each(
						data.list,
						function(i, j) {
							var stateName="";
							switch(j.state){
							case 0:
								stateName="待审核";
								break;
							case 1:
								stateName="部门审核通过";
								break;
							case 2:
								stateName="安全审核通过";
								break;
							case 3:
								stateName="领导审核通过";
								break;
							case 4:
								stateName="部门审核不通过";
								break;
							case 5:
								stateName="安全审核不通过";
								break;
							case 6:
								stateName="领导审核不通过";
								break;
							case 7:
								stateName="维保完成";
								break;
							}
							tab1Html += "<div class=' bg-white mar-t6' onclick='jumpPage(\"maintain/goDetail.action?id="+j.id+"\")'>"
								+ "<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
								+ "<i class='icon-number pull-left mar-t6'></i>"
								+ "<span class='text-number pull-left mar-t6 '>"
								+ j.vehicleLicense
								+ "</span>"
								+ "</div>"
								+ "<div class='pull-right mar-r10'>";
						
						tab1Html += "</div>"
								+ "<p class='clearfix line-1eee'></p>"
								+ "<a href='javascript:void(0)'  class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
								+ "<div class='weui-media-box__hd' style='position: relative'>"
								+ "<i class='bg-green-merald tip-state' style='position: absolute;'>"
								+ stateName + "</i>";
							tab1Html += "<img class='weui-media-box__thumb' src='images/nopic.jpg'>";

						tab1Html += "</div>"
								+ "<div class='weui-media-box__bd'>"
								+ "<h4 class='weui-media-box__title ft-bold400'>"
								+ j.repairCause
								+ "</h4>"
								+ "<p class='line-x1eee'></p>"
								+ "<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"
								+ "</p>"
								+ "<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"
								+ new Date(j.repairDate)
										.format("yyyy-MM-dd hh:mm")
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


var clList;
function getBaseData(){
	Utils.ajax({
		url : 'maintain/findConfig.action',
		data:{},
		success : function(data) {
			$.hideLoading();
			var vehicle=[];
			$.each(data.vehicelList,function(j,i){
				var row={'title':i.vehicleLicense,'value':i.vehicleLicense};
				vehicle.push(row);
			});
			$("#vehicleLicense").select({
				title: "请选择维保车辆",
				items: vehicle
			});
			
			var manu=[];
			$.each(data.manuList,function(j,i){
				var row={'title':i.manuName,'value':i.manuCode};
				manu.push(row);
			});
			$("#repairFactory").select({
				title: "请选择送保厂家",
				items: manu
			});
			clList=data.materialList;
			
		}
	});
}
function changeCauseNum(obj){
	$("#causeNum").text($(obj).val().length);
}

var backAccount;
var permitStr;
var orgCode;
/**
 * 获取后台绑定帐号
 */
function getBackAccount() {
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
 * 新增维保
 */
function saveMaintain(obj){
	$(obj).attr("disabled",true);
	var repairDate=$("#rTime").val().trim();
	var vehicleLicense=$("#vehicleLicense").attr("data-values");
	var repairFactory=$("#repairFactory").attr("data-values");
	var budgetCost=$("#budgetCost").val();
	var materialCost=$("#materialCost").val();
	var useCause=$("#useCause").val();
	
	
	if(repairDate==""){
		$.toast("请选择维保时间","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(vehicleLicense==""){
		$.toast("请选择维保车辆", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(repairFactory==""){
		$.toast("请选择送保厂家", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(budgetCost==""){
		$.toast("请输入预算费用", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(materialCost==""){
		$.toast("请输入自带材料费用", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(useCause==""){
		$.toast("请输入维保原因", "forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	
	var clin=$("#cailiao").find("input[class='weui-input']");
	var clnm=$("#cailiao").find("input[class='weui-count__number']");
	var mcode="",mcount="";
	for(var i=0;i<clin.length;i++){
		if($(clin[i]).attr("data-values") != undefined && $(clin[i]).attr("data-values") !=""){
			mcode+=$(clin[i]).attr("data-values")+",";
			mcount+=$(clnm[i]).val()+",";
		} else {
			$.toast("添加的材料项必须选择", "forbidden");
			$(obj).attr("disabled",false);
			return;
		}
	}
	if(mcode!="" && mcount != ""){
		mcode=mcode.substring(0,mcode.length-1);
		mcount=mcount.substring(0,mcount.length-1);
	}
	$.showLoading();
	Utils.ajax({
		url : 'maintain/saveMaintain.action',
		data:{"applicant":backAccount,"repairDate":repairDate,"vehicleLicense":vehicleLicense,"repairFactory":repairFactory,
			"budgetCost":budgetCost,"materialCost":materialCost,"repairCause":useCause,"mcode":mcode,"mcount":mcount},
		success : function(data) {
			$.hideLoading();
			if(data){
				$.toast("提交成功，等待审核");
				setTimeout(function(){location.reload();},2000);
			}
			
		},
		error:function(){
			$.hideLoading();
		}
	});
	
}


var currentBackPage=1;
getBackList = function() {
	Utils.ajax({
		url : 'maintain/findMyPermitList.action',
		data : {
			"permitCode" : permitStr,
			"pageNum" : currentBackPage,
			"pageLength" : 10
		},
		success : function(data) {
			$.hideLoading();
			var tab3Html = getBackHtml(data, currentBackPage);
			$("#tab3").pullToRefreshDone();
			if (currentBackPage == 1) {
				$("#tab3").empty();
				$("#tab3").html(tab3Html);
				$("#tab3").pullToRefresh();
				$("#tab3").on("pull-to-refresh", function() {
					currentBackPage=1;
					getBackList();
				});
				$("#tab3").pullToRefreshDone();

				if (data.pageNums > 1) {
					var backLoading = false;
					$("#tab3").infinite().on("infinite", function() {
						if (backLoading)
							return;
						backLoading = true;
						currentBackPage = currentBackPage + 1;
						getBackList();
						setTimeout(function() {
							backLoading = false;
						}, 1000);
					});
				}
			} else {
				$("#sourceBack").append(tab3Html);
				if (data.pageNums <= currentBackPage) {
					$("#wxadBackLoadmore").hide();
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
	if (data.pageNums == 0) {
		tabHtml += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>亲，您还没有待处理数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div>" + "</div>";

	} else {
		tabHtml += "<div id='sourceBack'>";
		$
				.each(
						data.list,
						function(i, j) {
							var stateName="";
							switch(j.state){
							case 0:
								stateName="待审核";
								break;
							case 1:
								stateName="部门审核通过";
								break;
							case 2:
								stateName="安全审核通过";
								break;
							case 3:
								stateName="领导审核通过";
								break;
							case 4:
								stateName="部门审核不通过";
								break;
							case 5:
								stateName="安全审核不通过";
								break;
							case 6:
								stateName="领导审核不通过";
								break;
							case 7:
								stateName="维保完成";
								break;
							}
							tabHtml+="<div class=' bg-white mar-t6' onclick='jumpPage(\"maintain/goBack.action?id="+j.id+"&userAccount="+backAccount+"\")'>"
									+"<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>"
									+"<i class='icon-number pull-left mar-t6'></i>"
									+"<span class='text-number pull-left mar-t6 '>"+j.vehicleLicense+"</span>"
									+"</div>"
									+"<div class='pull-right mar-r10'><i class='bg-green-merald tip-state'>"+stateName+"</i></div>"
									+"<p class='clearfix line-1eee'></p>"
									+"<a href='javascript:void(0);' class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
									+"<div class='weui-media-box__bd'>"
									+"<h4 class='weui-media-box__title ft-bold400'>"+j.repairCause+"</h4>"
									+"<p class='line-x1eee'></p>"
									+"<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'></p>"
									+"<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>"+new Date(j.repairDate).format("yyyy-MM-dd hh:mm")+"</p>"
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
			tabHtml += "<div class='weui-loadmore' id='wxadBackLoadmore'>"
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