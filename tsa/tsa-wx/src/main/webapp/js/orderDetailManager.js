function acceptOrder(){
	$.confirm("确认接单？", function() {
		$.showLoading();
		Utils.ajax({
			url : 'dietOrderManager/acceptOrder.action',
			data : {
				"backAccount" : backAccount,
				"orderNum" : orderNum
			},
			success : function(data) {
				if(data){
					$.toast("接单成功");
					setTimeout(function(){
						location.reload();
					},2000);
				}
			}
		});
		
		
		  }, function() {
		  //点击取消后的回调函数
		  });
}