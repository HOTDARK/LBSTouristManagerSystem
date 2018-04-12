/**
 * 订单详情页面脚本
 */
$(function() {
    FastClick.attach(document.body);
    getOrderInfo();
    getStoreInfo()
});

/**
 * 获取订单信息
 */
function getOrderInfo(){
	Utils.ajax({
		url: 'dietOrder/getOrderInfo.action',
		data: {"orderNum":orderNum},
		success: function(data){
			if (data.success) {
				$("#state").hide();
				var order = data.order;
				$("#orderNum").html(order.orderNum);
				$("#price").html(order.price);
				var payType = formatType(order.payType);
				$("#payType").html(payType);
				$("#dispatching").html(order.dispatching);
				$("#receiveName").html(order.receiveName);
				$("#orderPhone").html(order.orderPhone);
				$("#address").html(order.address);
				$("#remarks").html(order.remarks);
				if (order.state==5) {
					$("#state div").html("于"+new Date(order.cancelDate).format("yyyy-MM-dd hh:mm")+"取消订单");
					$("#state").show();
				}
				var list = data.foodList;
				var html = '';
				for (var i = 0; i < list.length; i++) {
					html += '<div class="weui-cell line-d1e bg-grey-f6">';
					html += '	<div class="weui-cell__hd" style="width:70%">'+list[i].foodName+'</div>';
					html += '	<div class="weui-cell__bd">x'+list[i].count+'</div>';
					html += '	<div class="weui-cell__ft ft-bold600 ft-black">¥'+list[i].price+'</div>';
					html += '</div>';
				}
				$("#foodList").html(html);
			}
		}
	});
}

/**
 * 获取店铺信息
 */
function getStoreInfo(){
	Utils.ajax({
		url: 'dietOrder/getStoreInfo.action',
		data: {"id":storeId},
		success: function(data){
			if (data.success) {
				var store = data.store;
				$("#sotreName").html(store.storeName);
				$("#dphPrice").html(store.dphPrice);
			}
		}
	})
}

function formatType(value){
	var str = '';
	if (value==1) {
		str='支付宝支付';
	}else if (value==2) {
		str='微信支付';
	}else if (value==3) {
		str='校园卡支付';
	}else if (value==4) {
		str='货到付款';
	}else{
		str='暂无';
	}
	return str;
}
