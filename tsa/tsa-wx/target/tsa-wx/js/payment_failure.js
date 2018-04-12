/**
 * 支付失败页面脚本
 */
$(function() {
	FastClick.attach(document.body);
});

/**
 * 重新支付
 */
function afreshPay(){
	jumpPage("dietOrder/goPayment.action?orderNum="+orderNum+"&storeId="+storeId+"&addressId="+addressId);
}

function goMyOrder(){
	jumpPage("dietOrder/goOrderList.action");
}