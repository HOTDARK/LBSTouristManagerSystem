$(function() {
	getStore($("#swiper-slide1"),null);
});
var cid;
function getStore(obj,id) {
	$.showLoading();
	cid = id;
	$("#wxBackLoadmore").hide();
	$("#tab1").destroyInfinite();
	ajaxRefresh(1);
	for(var i=1;i<=(parseInt(clength)+1);i++){
		var swiperDiv=$("#swiper-slide"+i);
		if(swiperDiv==undefined){
			break;
		}
		$(swiperDiv).removeClass("swipper-item-on");
	}
	$(obj).addClass("swipper-item-on");
}

var cp=1;
function ajaxRefresh(currentPage) {
	getLocalTrolley();
	cp=currentPage;
	Utils.ajax({
		url : 'diet/getStore.action',
		data : {
			"pageNum" : currentPage,
			"iDisplayLength" : 10,
			"id" : cid==undefined?null:cid
		},
		success : function(data) {
			var tab1Html = getHtml(data, currentPage);
			if (currentPage == 1) {
				$("#tab1").empty();
				$("#tab1").html(tab1Html);
				$("#tab1").pullToRefresh();
				$("#tab1").on("pull-to-refresh", function() {
					ajaxRefresh(1);
				});
				$("#tab1").pullToRefreshDone();
				if (data.pageNums > 1) {
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
		error : function() {
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
				+ "<div class='refresh'>正在刷新</div></div>"
				+ "<div id='sourceList'>"
	}
	if (data.pageNums == 0) {
		tab1Html += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>没有检索到数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div></div>";

	} else {
		$
				.each(
						data.list,
						function(i, j) {
							tab1Html += "<div class='bg-white mar-t1 pad-10 weui-media-box weui-media-box_appmsg' onclick='jumpPage(\"restaurant/goRestaurant.action?id="+j.id+"\")'>"
									+ "<div class='weui-media-box__hd'>"
									+ "<img class='weui-media-box__thumb' onerror='javascript:this.src=\"images/nopic.jpg\";' src='fileoper/downFile.action?filepath="+j.logo+"'>"
									+ "</div>"
									+ "<div class='weui-media-box__bd'>"
									+ "<h4 class='weui-media-box__title ft-bold400'>"+j.storeName+"</h4>"
									+ "<div class='center-block icon-score mar-t6'>"
									+ "<div>"
									+ "<i style='width:"+(16*j.storeScore)+"px;z-index:1;'></i>"
									+ "</div>"
									+ "<span class='txt ft-grey-666 ft12'>月售"+j.sales+"单</span>"
									+ "</div>"
									+ "<p class='clearfix'></p>"
									+ "<p class='weui-media-box__desc ft12 ft-grey-999 mar-t10 pull-right text-number'>¥"+j.initiatePrice+"起送 | 配送费¥"+j.dphPrice+"</p>"
									+ "</div></div>";
						});
		if (data.pageNums == currentPage) {
			tab1Html += "<div class='weui-loadmore'>"
					+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
					+ "</div>";
		}
		if(currentPage==1){
			tab1Html+="</div>";
		}
		if (data.pageNums > currentPage && currentPage == 1) {
			tab1Html += "<div class='weui-loadmore' id='wxBackLoadmore'>"
					+ "<i class='weui-loading'></i>"
					+ "<span class='weui-loadmore__tips'>正在加载</span>"
					+ "</div>";
		}
		
	}

	return tab1Html;
}

/**
 * 获取全局Cookie/全局购物车
 */
function getLocalTrolley(){
	var count = 0;
	if(getCookie('local')){
		var map = JSON.parse(getCookie('local'));
		for ( var key in map) { 
			var list = map[key];
			for (var i = 0; i < list.length; i++) {
				count=parseInt(count)+parseInt(list[i].count);
			}
		}
	}
	$("#totalCount").html(count);
}

/**
 * 获取cookie
 * @param name
 * @returns
 */
function getCookie(name){
	var reg = RegExp(name+'=([^;]+)');
    var arr = document.cookie.match(reg);
	if(arr){
		return unescape(arr[1]);
    }else{
      	return '';
    }
}