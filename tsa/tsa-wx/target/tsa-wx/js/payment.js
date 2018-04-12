/**
 * 订单支付管理脚本
 */
$(function() {
    FastClick.attach(document.body);
    getAddress();
    getStore();
    getOrder();
});

/**
 * 获取默认地址
 */
function getAddress(){
	if (addressId!=null && addressId!='') {
		Utils.ajax({
			url : 'receiveGoods/getUserAddr.action',
			data : {id : addressId},
			success : function(data){
				if (data) {
					var html='';
					$("#address").val(data.id);
					html += '<p class="ft-bold600 ft14">'+data.addrArea+'&nbsp;&nbsp;'+data.addrDetail+'</p>';
					html += '<p class="ft12 ft-grey-999">'+data.addrConsignee+'&nbsp;&nbsp;'+data.addrContact+'</p>';
					$("#Address").html(html);
				}else{
					$("#Address").html('<p class="ft14 ft-yellow">请选择收货地址</p>');
					$.alert("获取收货地址失败，请重新尝试");
				}
			}
		});
	}else{
		Utils.ajax({
			url:'receiveGoods/findReceiveGoodsList.action',
			data:{openid : openid},
			success: function(data){
				if (data.success && data.loginFlag) {
					if (data.callPort) {
						var list=data.list;
						if (list!=null && list.length>0) {
							var html='';
							for (var i = 0; i < list.length; i++) {
								if (list[i].addrDefault==1) {
									$("#address").val(list[i].id);
									html += '<p class="ft-bold600 ft14">'+list[i].addrArea+'&nbsp;&nbsp;'+list[i].addrDetail+'</p>';
									html += '<p class="ft12 ft-grey-999">'+list[i].addrConsignee+'&nbsp;&nbsp;'+list[i].addrContact+'</p>';
								}
							}
							if (html=='') {
								html += '<p class="ft14 ft-yellow">请选择收货地址</p>';
							}
							$("#Address").html(html);
						}else{
							$("#Address").html('<p class="ft14 ft-yellow">请选择收货地址</p>');
						}
					}else{
						$("#Address").html('<p class="ft14 ft-yellow">请选择收货地址</p>');
					}
				}else{
					jumpPage(data.msg);
				}
			}
		});
	}
}

/**
 * 跳转地址列表页面
 */
function goAddressList(){
	jumpPage('dietOrder/goAddList.action?orderNum='+orderNum+'&storeId='+storeId);
}

/**
 * 获取订单信息
 */
function getOrder(){
	Utils.ajax({
		url : 'dietOrder/getOrderInfo.action',
		data : {orderNum : orderNum},
		success : function(data){
			if (data.success) {
				var order = data.order;
				var list = data.foodList;
				var html = '';
				for (var i = 0; i < list.length; i++) {
					var price = list[i].price;
					price = parseFloat(price).toFixed(2);
					html += '<div class="weui-cell line-d1e bg-grey-f6 ft14">';
					html += '	<div class="weui-cell__hd" style="width:70%">'+list[i].foodName+'</div>';
					html += '	<div class="weui-cell__bd">x'+list[i].count+'</div>';
					html += '	<div class="weui-cell__ft ft-bold600 ft-black">¥ '+price+'</div>';
					html += '</div>';
				}
				$("#foodList").html(html);
				$("#money").html(parseFloat(order.price).toFixed(2));
				$("#price").html(parseFloat(order.price).toFixed(2));
				if (order.remarks!=null && order.remarks!='') {
					$("#remarks").html(order.remarks);
				}
			}else{
				$.alert(data.msg);
			}
		}
	});
}

/**
 * 获取订单店铺信息
 */
function getStore(){
	Utils.ajax({
		url : 'dietOrder/getStoreInfo.action',
		data : {id : storeId},
		success : function(data){
			if (data.success) {
				var store = data.store;
				$("#storeName").html(store.storeName);
				$("#dphPrice").html(parseFloat(store.dphPrice).toFixed(2));
			}else{
				$.alert(data.msg);
			}
		}
	});
}

/**
 * 确认支付
 */
function confirmPayment(){
	var address = $("#address").val();
	var remarks = $("#remarks").val();
	if (address == null || address=='') {
		$.alert("请选择收货地址");
		return false;
	}
	Utils.ajax({
		url : 'dietOrder/confirmPayment.action',
		data : {"orderNum":orderNum, "address":address, "remarks":remarks},
		success : function(data){
			if (data.success) {
				jumpPage("dietOrder/goSuccess.action?orderNum="+orderNum);
			}else{
				jumpPage("dietOrder/goFailure.action?orderNum="+orderNum+"&addressId="+address+"&storeId="+storeId);
			}
		}
	})
}