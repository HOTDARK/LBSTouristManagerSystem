/**
 * 用户中心
 */
function checkStoreStatus(){
	$.showLoading();
	Utils.ajax({
		url: 'wxUserBase/isBindBackground.action',
		data: {"openid" : openid, "backType" : "diet"},
		success: function(data){
			$.hideLoading();
			if (data.backOrgCode == null || data.backOrgCode == "") {
				jumpPage('wx/jumpPage.action?viewName=restaurantManager.jsp&idNum=3');
			}else{
				$.showLoading();
				Utils.ajax({
					url: 'storeManage/getStoreInfo.action',
					data: {account:data.backAccount},
					success: function(data){
						$.hideLoading();
						if (data.success) {
							jumpPage('wx/jumpPage.action?viewName=restaurantManager.jsp&idNum=3');
						}else{
							$.alert("您绑定的餐厅已被冻结，请联系膳食中心");
						}
					}
				});
			}
		}
	});
}