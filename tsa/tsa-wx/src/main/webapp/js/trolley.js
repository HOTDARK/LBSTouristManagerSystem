/**
 * 购物车管理脚本
 */
$(function(){
	loadCookieByStore();
})

/**
 * 加载cookie
 */
function loadCookieByStore(){
	var storeId =$("#storeId").val();
	var store = "store_"+storeId;
	var totalMoney=0;
	var count = 0;
	var html = '';
	if(getCookie(store)){
		var list = JSON.parse(getCookie(store));
		if (list!=null && list.length>0) {
			$("#trolley").show();
			$("#emptyTrolley").hide();
			for (var i = 0; i < list.length; i++) {
				var money = list[i].count*list[i].price;
				money=money.toFixed(2);
				totalMoney=parseFloat(totalMoney)+parseFloat(money);
				totalMoney=totalMoney.toFixed(2);
				count=parseInt(count)+parseInt(list[i].count);
				html += '<div class="weui-cell line-d1e pad-0-15">';
				html += '	<div class="weui-cell__hd" style="width: 100px;">'+list[i].foodName+'</div>';
				html += '	<div class="weui-cell__bd ft18 mar-t3 text-number ft-red text-center">¥'+money+'</div>';
				html += '	<div class="weui-cell__bd ft14 ft-grey-999 mar-t10 text-number">';
				html += '		<p class="pull-right">&nbsp;&nbsp;<a href="javascript:void(0);" onclick="addFood('+storeId+','+list[i].foodId+')"><img src="images/icon-add.png" width="24" height="24" /></a></p>';
				html += '		<p class="pull-right">'+list[i].count+'</p>';
				html += '		<p class="pull-right"><a href="javascript:void(0);" onclick="reduceFood('+storeId+','+list[i].foodId+')"><img src="images/icon-reduce.png" width="24" height="24"/></a>&nbsp;&nbsp;</p>';
				html += '	</div>';
				html += '</div>';
			}
			$("#trolleyList").html(html);
			$("#totalNumber").html(count);
			$("#totalMoney").html(totalMoney);
			$("#totalnumber").html(count);
			$("#totalmoney").text(totalMoney);
			var initiatePrice = parseFloat($("#initiatePrice").text());
			var dphPrice=parseFloat($("#dphPrice").text());
			if (initiatePrice!=0 && (totalMoney<initiatePrice)) {
				$("#balance span").html((initiatePrice-totalMoney).toFixed(2));
				$("#balance").show();
				$("#submit").hide();
			}else{
				$("#balance span").html(0);
				$("#balance").hide();
				$("#submit").show();
			}
		}else{
			html += '<div class="weui-cell line-d1e pad-0-15 ft16 ft-yellow">还未选择菜品</div>';
			$("#trolleyList").html(html);
			$("#totalNumber").html(count);
			$("#totalMoney").html(totalMoney);
			$("#totalnumber").html(count);
			$("#totalmoney").text(totalMoney);
			$("#balance").hide();
			$("#submit").hide();
		}
	}else{
		$("#balance").hide();
		$("#submit").hide();
		html += '<div class="weui-cell line-d1e pad-0-15 ft16 ft-yellow">还未选择菜品</div>';
		$("#trolleyList").html(html);
		$("#totalNumber").html(count);
		$("#totalMoney").html(totalMoney);
		$("#totalnumber").html(count);
		$("#totalmoney").text(totalMoney);
	}
	getLocalTrolley();
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
 * 新增菜品
 * @param id
 * @param name
 * @param price
 * @returns
 */
function insertFood(id,name,price,photo){
	var store = "store_"+$("#storeId").val();
	var food={};
	food.foodId=id;
	food.foodName=name;
	food.price=price;
	food.coverPhoto=photo;
	food.count=1;
	var list = [];
	if (getCookie(store)) {
		list = JSON.parse(getCookie(store));
		var sflag=false;
		for (var i = 0; i < list.length; i++) {
			if (list[i].foodId==id) {
				list[i].count=parseInt(list[i].count)+parseInt(1);
				sflag=true;
				break;
			}
		}
		if (!sflag) {
			list.push(food);
		}
	}else{
		list.push(food);
	}
	var date = new Date();
	date.setDate(date.getDate() + 3);
	document.cookie = store + '=' + escape(JSON.stringify(list)) + ';expires='+ date + ';path=/drsp-wx';
	var map = new Map();
	if (getCookie('local')) {
		map=JSON.parse(getCookie('local'));
	}
	map[store]=list;
	document.cookie = 'local=' + escape(JSON.stringify(map)) + ';expires='+ date + ';path=/drsp-wx';
	loadCookieByStore();
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

/**
 * 增加菜品数量
 * @param storeId
 * @param id
 */
function addFood(storeId,id){
	var store = "store_"+storeId;
	var list = JSON.parse(getCookie(store));
	for (var i = 0; i < list.length; i++) {
		if (list[i].foodId==id) {
			list[i].count=parseInt(list[i].count)+parseInt(1);
			break;
		}
	}
	var date = new Date();
	date.setDate(date.getDate() + 3);
	document.cookie = store + '=' + escape(JSON.stringify(list)) + ';expires='+ date + ';path=/drsp-wx';
	var map=new Map();
	if (getCookie('local')) {
		map=JSON.parse(getCookie('local'));
	}
	map[store]=list;
	document.cookie = 'local=' + escape(JSON.stringify(map)) + ';expires='+ date + ';path=/drsp-wx';
	loadCookieByStore();
}

/**
 * 减少菜品
 * @param storeId
 * @param id
 * @returns
 */
function reduceFood(storeId,id){
	var store = "store_"+storeId;
	var list = JSON.parse(getCookie(store));
	for (var i = 0; i < list.length; i++) {
		if (list[i].foodId==id) {
			if (list[i].count>1) {
				list[i].count=list[i].count-1;
			}else{
				list.splice(i,1)
			}
			break;
		}
	}
	var date = new Date();
	date.setDate(date.getDate() + 3);
	document.cookie = store + '=' + escape(JSON.stringify(list)) + ';expires='+ date + ';path=/drsp-wx';
	var map=new Map();
	if (getCookie('local')) {
		map=JSON.parse(getCookie('local'));
	}
	map[store]=list;
	document.cookie = 'local=' + escape(JSON.stringify(map)) + ';expires='+ date + ';path=/drsp-wx';
	loadCookieByStore();
}

/**
 * 清空购物车
 */
function emptyTrolley(){
	var store = "store_"+$("#storeId").val();
	var date = new Date();
	date.setTime(date.getTime() - 1);
	document.cookie = store + '='+null+';expires=' + date.toGMTString() + ';path=/drsp-wx';
	var map=new Map();
	if (getCookie('local')) {
		map=JSON.parse(getCookie('local'));
	}
	delete map[store];  
	date.setDate(date.getDate() + 3);
	document.cookie = 'local=' + escape(JSON.stringify(map)) + ';expires='+ date + ';path=/drsp-wx';
	loadCookieByStore();
}

/**
 * 生成订单
 */
function createOrder(){
	var storeId = $("#storeId").val();
	var dphPrice = $("#dphPrice").text();
	var list = JSON.parse(getCookie('store_'+storeId));
	var objectData={
		"storeId":storeId,
		"foodList": list,
		"price":dphPrice,
		"openid":openid
	}
	Utils.ajax({
		url : 'dietOrder/insertOrder.action',
		data : JSON.stringify(objectData),
		contentType: 'application/json',
		success : function(data){
			if (data.success) {
				var orderNum = data.orderNum;
				if (orderNum!=null && orderNum!='') {
					var store = "store_"+storeId;
					var date = new Date();
					date.setTime(date.getTime() - 1);
					document.cookie = store + '='+null+';expires=' + date.toGMTString() + ';path=/drsp-wx';
					var map=new Map();
					if (getCookie('local')) {
						map=JSON.parse(getCookie('local'));
					}
					delete map[store];  
					date.setDate(date.getDate() + 3);
					document.cookie = 'local=' + escape(JSON.stringify(map)) + ';expires='+ date + ';path=/drsp-wx';
					loadCookieByStore();
					jumpPage("dietOrder/goPayment.action?storeId="+storeId+"&orderNum="+orderNum);
				}else{
					$.alert(data.msg);
				}
			}else{
				$.alert(data.msg);
			}
		}
	});
}