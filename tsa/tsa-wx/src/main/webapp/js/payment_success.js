/**
 * 支付成功页面脚本
 */
$(function(){
	FastClick.attach(document.body);
})

/**
 * 跳转订单详情页面
 */
function goMyOrder(){
	jumpPage("dietOrder/goOrderList.action");
}