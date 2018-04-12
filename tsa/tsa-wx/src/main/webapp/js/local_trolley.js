/**
 * 全局购物车
 */
$(function(){
	FastClick.attach(document.body);
	getLocalTrolley();
})

/**
 * 获取全局Cookie/全局购物车
 */
function getLocalTrolley(){
	if(getCookie('local')){
		var map = JSON.parse(getCookie('local'));
		var ids='';
		var html ='';
		for ( var key in map) { 
			var list = map[key];
			if (list.length>0) {
				var storeId=key.substring(6);
				ids=ids+','+storeId;
				html += '<div id="store_'+storeId+'">';
				html += '	<div class="weui-cell bg-white line-d1e">';
				html += '		<div class="weui-cell__hd ft-bold600 ft-grey-666" id="storeName_'+storeId+'"></div>';
				html += '		<div class="weui-cell__bd mar-t6 mar-l15" onclick="jumpPage(\'restaurant/goRestaurant.action?id='+storeId+'\')"><img src="images/icon05.png" width="10" height="18"/></div>';
				html += '		<div class="weui-cell__ft mar-t6" onclick="emptyTrolley('+storeId+')"><img src="images/icon-del.png" width="16" height="16"/></div>';
				html += '	</div>';
				html += '	<div id="ul_'+storeId+'">';
				var totalMoney=0;
				for (var i = 0; i < list.length; i++) {
					var money = list[i].count*list[i].price;
					money=money.toFixed(2);
					totalMoney=parseFloat(totalMoney)+parseFloat(money);
					totalMoney=totalMoney.toFixed(2);
					html += '<div class="weui-media-box bg-white pad-15-0 line-b weui-media-box_appmsg" id="'+storeId+'_li_'+list[i].foodId+'">';
					html += '	<div class="weui-media-box__hd">';
					html += '		<img class="weui-media-box__thumb" src="fileoper/downFile.action?filepath='+list[i].coverPhoto+'" style="width: 60px;height: 60px;">';
					html += '	</div>';
					html += '	<div class="weui-media-box__bd">';
					html += '		<div class="weui-media-box__title ft-bold400 ft14">'+list[i].foodName+'</div>';
					html += '		<div>';
					html += '			<div class="ft14 ft-grey-999 mar-t10 pull-left text-number">';
					html += '				<p class="pull-right">&nbsp;&nbsp;<a onclick="addCardFood('+storeId+','+list[i].foodId+','+list[i].price+')"><img src="images/icon-add.png" width="24" height="24"/></a></p>';
					html += '				<p class="pull-right" id="'+storeId+'_num_'+list[i].foodId+'">'+list[i].count+'</p>';
					html += '				<p class="pull-right"><a onclick="reduceCardFood('+storeId+','+list[i].foodId+','+list[i].price+')"><img src="images/icon-reduce.png" width="24" height="24"/></a>&nbsp;&nbsp;</p>';
					html += '			</div>';
					html += '			<p class="ft22 mar-t3 pull-right mar-r15 text-number ft-red">¥<span id="'+storeId+'_money_'+list[i].foodId+'">'+money+'</span></p>';
					html += '		</div>';
					html += '		<p class="clearfix"></p>';
					html += '	</div>';
					html += '</div>';
				}
				html += '	</div>';
				html += '	<div class="weui-media-box__bd bg-white">';
				html += '		<div class="weui-media-box__title ft-bold400" style="margin-left: 15px;line-height: 38px;">';
				html += '			<span class="ft12 pull-left" style="color: #999;">另需配送费 ¥<span id="dph_'+storeId+'">0</span></span>';
//				html += '			<span class="ft16 mar-t3 pull-right mar-r15 text-number ft-red"></span>';
				html += '		</div>';
				html += '	</div>';
				html += '	<div class="weui-cell bg-white line-d1e mar-b5">';
				html += '		<div class="weui-cell__bd ft12 ft-grey-666"><div style="display: none;" id="balance_'+storeId+'">还差 <span class="ft-red"></span> 元起送</div></div>';
				html += '		<div class="weui-cell__ft">';
				html += '			合计 <span class="ft22 ft-red text-number">¥<span id="price_'+storeId+'">'+totalMoney+'</span></span>';
				html += '			<button type="button" class="btn-ok ft14 ft-bold600" id="order_'+storeId+'" onclick="jumpPage(\'restaurant/goRestaurant.action?id='+storeId+'\')" style="display: none;">去凑单</button>';
				html += '			<button type="button" class="btn-ok01 ft14 ft-bold600" id="btn_'+storeId+'" onclick="createOrder('+storeId+')" style="display: none;">结算</button>';
				html += '		</div>';
				html += '	</div>';
				html += '<input type="hidden" id="initiatePrice_'+storeId+'" value="">';
				html += '</div>';
			}
		}
		$("#show").html(html);
	}
	if (html!=null && html!='') {
		$("#show").show();
		$("#none").hide();
		getStores(ids);
	}else{
		$("#show").hide();
		$("#none").show();
	}
}

/**
 * 根据ids批量获取店铺信息
 * @param ids
 */
function getStores(ids){
	ids=ids.substring(1);
	Utils.ajax({
		url:'restaurant/getStores.action',
		data:{ids:ids},
		success:function(data){
			if (data!=null && data.length>0) {
				for (var i = 0; i < data.length; i++) {
					$("#storeName_"+data[i].id).html(data[i].storeName);
					$("#dph_"+data[i].id).html(parseFloat(data[i].dphPrice).toFixed(2));
					var totalMoney = $("#price_"+data[i].id).text();
//					var price=parseFloat(parseFloat(totalMoney)+parseFloat(data[i].dphPrice));
//					$("#price_"+data[i].id).html(price.toFixed(2));
					$("#initiatePrice_"+data[i].id).val(data[i].initiatePrice);
					if (data[i].initiatePrice>totalMoney) {
						$("#balance_"+data[i].id+" span").html((data[i].initiatePrice-totalMoney).toFixed(2));
						$("#balance_"+data[i].id).show();
						$("#order_"+data[i].id).show();
						$("#btn_"+data[i].id).hide();
					}else{
						$("#balance_"+data[i].id).hide();
						$("#order_"+data[i].id).hide();
						$("#btn_"+data[i].id).show();
					}
				}
			}
		}
	})
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
 * 减少菜品
 * @param storeId
 * @param foodId
 * @param price
 */
function reduceCardFood(storeId,foodId,price){
	var num = $("#"+storeId+"_num_"+foodId).text();
	var money = $("#"+storeId+"_money_"+foodId).text();
	var totalMoney = $("#price_"+storeId).text();
	if (num>1) {
		$("#"+storeId+"_num_"+foodId).html(num-1);
		$("#"+storeId+"_money_"+foodId).html((parseFloat(money)-parseFloat(price)).toFixed(2));
		$("#price_"+storeId).html((parseFloat(totalMoney)-parseFloat(price)).toFixed(2));
	}else{
		$("#"+storeId+"_money_"+foodId).html((parseFloat(money)-parseFloat(price)).toFixed(2));
		$("#price_"+storeId).html((parseFloat(totalMoney)-parseFloat(price)).toFixed(2));
		$("#"+storeId+"_li_"+foodId).remove();
		var html = $("#ul_"+storeId).html();
		html=html.trim();
		if (html==null || html.length==0) {
			$("#store_"+storeId).remove();
			var text=$("#show").html();
			if (text==null || text=='') {
				$("#show").hide();
				$("#none").show();
			}
		}
	}
	var store = "store_"+storeId;
	var list = JSON.parse(getCookie(store));
	for (var i = 0; i < list.length; i++) {
		if (list[i].foodId==foodId) {
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
}

/**
 * 增加菜品
 * @param storeId
 * @param foodId
 * @param price
 */
function addCardFood(storeId,foodId,price){
	var num = $("#"+storeId+"_num_"+foodId).text();
	var money = $("#"+storeId+"_money_"+foodId).text();
	var totalMoney = $("#price_"+storeId).text();
	$("#"+storeId+"_num_"+foodId).html(parseInt(num)+parseInt(1));
	$("#"+storeId+"_money_"+foodId).html((parseFloat(money)+parseFloat(price)).toFixed(2));
	$("#price_"+storeId).html((parseFloat(totalMoney)+parseFloat(price)).toFixed(2));
	var store = "store_"+storeId;
	var list = JSON.parse(getCookie(store));
	for (var i = 0; i < list.length; i++) {
		if (list[i].foodId==foodId) {
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
}

/**
 * 清空购物车
 * @param id
 */
function emptyTrolley(id){
	$("#store_"+id).remove();
	var text=$("#show").html();
	if (text==null || text=='') {
		$("#show").hide();
		$("#none").show();
	}
	var store = "store_"+id;
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
}

/**
 * 生成订单
 */
function createOrder(storeId){
	var dphPrice = $("#dph_"+storeId).text();
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