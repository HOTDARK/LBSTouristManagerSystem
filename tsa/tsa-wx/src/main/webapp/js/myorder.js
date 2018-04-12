/**
 * 我的订单页面脚本
 */
var totalPages;
var currentPage=1;
$(function() {
	FastClick.attach(document.body);
	$("#times").select({
		title: "请选择",
		input:function(){
			$("#name").html("近三个月订单");
			$("#value").val(1);
			refresh();
		},
		items: [
		  	{
		      title: "近三个月订单",
		      value: 1
		    },
		    {
		      title: "近半个月订单",
		      value: 2
		    },
		    {
		      title: "近10天订单",
		      value: 3
		    },
		    {
		      title: "一周内订单",
		      value: 4
		    },
		    {
		      title: "近3天订单",
	    	  value: 5
		    }
		],
		onChange:function(d){
			currentPage=1;
			totalPages=0;
			$("#name").html(d.titles);
			$("#value").val(d.values);
		},
		onClose:function(d){
			$(document.body).destroyInfinite();
			refresh();
		},
	});
	$(document.body).pullToRefresh(function() {
		currentPage=1;
		totalPages=0;
		$(document.body).destroyInfinite();
		refresh();
        setTimeout(function() {
        	$(document.body).pullToRefreshDone();
        }, 2000);
	});
	if (flag=='failure') {
		setTimeout(function() {
			$.alert("重新生成订单失败");
		},1500);
	}
});

function refresh(){
	$.showLoading();
	var timeType = $("#value").val();
	Utils.ajax({
		url: 'dietOrder/getMyOrder.action',
		data: {"openid":openid,"timeType":timeType,"pageNum":currentPage},
		success: function(data){
			if (data.success) {
				totalPages=data.pageNums;
				var html = getHtml(data.list)
				if (currentPage==1) {
					$("#orderList").empty();
					$("#orderList").html(html);
					$(document.body).pullToRefresh();
					if (totalPages > 1) {
						var backLoading = false;
						$(document.body).infinite().on("infinite", function() {
							if (backLoading) return;
							backLoading = true;
							refresh();
							setTimeout(function() {
								backLoading = false;
							}, 1000);
						});
					}
				}else{
					$("#orderList").append(html);
					if (totalPages <= currentPage) {
						$("#wxBackLoadmore").hide();
						$(document.body).destroyInfinite();
					}
				}
				currentPage = parseInt(currentPage) + parseInt(1);
				$.hideLoading();
			}
		}
	});
}

getHtml=function(data){
	var html='';
	if (totalPages==0) {
		html += '<div class="text-center" style="margin-top: 15%;">';
		html += '	<img src="images/nodata.png" width="70%"/>';
		html +=	'	<p class="ft18 ft-grey-999">亲，您还没有订单数据</p>';
		html += '	<div style="height:100px;">&nbsp;</div>';
		html += '</div>';
	}else{
		for (var i = 0; i < data.length; i++) {
			var state=data[i].state;
			html += '<div class="bg-white ft12">';
			html += '	<p class="hei-6 bg-grey-eee"></p>';
			html += '	<div class="line-d1e">';
			html += '		<div class="pull-right ft-grey-999 mar-r15 mar-t10">';
			html += '			<a type="button" class="btn-code bg-blue-grass" onclick="jumpPage(\'dietOrder/goOrderdetails.action?storeId='+data[i].storeId+'&orderNum='+data[i].orderNum+'\')">详情</a>';
			if (state==0) {
				html += '		<a type="button" class="btn-code bg-red" onclick="jumpPage(\'dietOrder/goPayment.action?storeId='+data[i].storeId+'&orderNum='+data[i].orderNum+'\')">付款</a>';
			}
			if (state==2 && data[i].evaluate==0) {
				html += '		<a type="button" class="btn-code bg-yellow" onclick="jumpPage(\'dietOrder/goOrderEvaluate.action?id='+data[i].orderNum+'&storeId='+data[i].storeId+'\')">评价</a>';
			}
			html += '		</div>';
			html += '		<div class="ft-grey-999 weui-cell" >';
			html += '			<div class=""><span class="">订单编号：</span>'+data[i].orderNum+'</div>';
			html += '		</div>';
			html += '	</div>';
			html += '	<div class="weui-media-box pad-15-0 line-b weui-media-box_appmsg">';
			html += '		<div class="weui-media-box__hd">';
			html += '			<img class="weui-media-box__thumb" src="fileoper/downFile.action?filepath='+data[i].logoPath+'">';
			html += '		</div>';
			html += '		<div class="weui-media-box__bd pad-r15">';
			html += '			<h4 class="weui-media-box__title ft-bold600">'+data[i].storeName;
			if (state == 2 || state==3 || state==4) {
				html += '<span class="pull-right ft-bold400 ft12 ft-orange" onclick="jumpPage(\'dietOrder/onceAgain.action?storeId='+data[i].storeId+'&orderNum='+data[i].orderNum+'\')">再来一份</span>';
			}
			html += '			</h4>';
			var foodNames = data[i].foodNames;
			if (foodNames!=null && foodNames!='') {
				if (foodNames.length>15) {
					foodNames=foodNames.substring(0,15)+"...";
				}
			}else{
				foodNames='';
			}
			html += '			<p class="ft12">'+foodNames+'</p>';
			html += '			<div>';
			var price = data[i].price;
			if (price==null) {
				price = '0.00';
			}else{
				price = parseFloat(price).toFixed(2);
			}
			if (state==0) {
				html += '			<p class="pull-left"><span class="ft18 text-number ft-red">¥'+price+'</span>&nbsp;&nbsp;<span class="ft-red1">未付款</span><span class="ft12 ft-grey-999">(暂未支付)</span></p>';
			}else if (state==1) {
				html += '			<p class="pull-left"><span class="ft18 text-number ft-red">¥'+price+'</span>&nbsp;&nbsp;<span class="ft-blue">已付款</span><span class="ft12 ft-grey-999">('+formateState(data[i].payType)+')</span></p>';
			}else if (state==2) {
				html += '			<p class="pull-left"><span class="ft18 text-number ft-red">¥'+price+'</span>&nbsp;&nbsp;<span class="ft-grey-666">已接单</span></p>';
			}else if (state==3) {
				html += '			<p class="pull-left"><span class="ft18 text-number ft-red">¥'+price+'</span>&nbsp;&nbsp;<span class="ft-yellow">派送中</span></p>';
			}else if (state==4) {
				html += '			<p class="pull-left"><span class="ft18 text-number ft-red">¥'+price+'</span>&nbsp;&nbsp;<span class="ft-green">已收货</span></p>';
			}else if (state==5) {
				html += '			<p class="pull-left"><span class="ft18 text-number ft-red">¥'+price+'</span>&nbsp;&nbsp;<span class="ft-orange">已取消</span></p>';
			}
			html += '				<div class="ft12 ft-grey-999 mar-t10  pull-right text-number">'+new Date(data[i].orderDate).format("yyyy-MM-dd hh:mm")+'</div>';
			html += '			</div>';
			html += '			<p class="clearfix"></p>';
			html += '		</div>';
			html += '	</div>';
			html += '</div>';
		}
		if (totalPages == currentPage) {
			html += '<div class="weui-loadmore weui-loadmore_line">';
			html +=	'	<span class="weui-loadmore__tips">没有更多数据了</span>';
			html +=	'</div>';
		}
		if (totalPages > currentPage && currentPage == 1) {
			var htmls = '';
			htmls += '<div class="weui-loadmore" id="wxBackLoadmore">';
			htmls += '	<i class="weui-loading"></i>';
			htmls += '	<span class="weui-loadmore__tips">正在加载</span>';
			htmls += '</div>';
			$("#loadMore").html(htmls);
		}
	}
	return html;
}


function formateState(value){
	var str = '';
	if (value==1) {9
		str="支付宝支付";
	}else if (value==2) {
		str="微信支付";
	}else if (value==3) {
		str="校园卡支付";
	}else if (value=4) {
		str="到付";
	}
	return str;
}