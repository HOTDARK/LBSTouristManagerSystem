function editAddr(id){
	jumpPage("receiveGoods/forwardEditAddr.action?id="+id);
}

function forwardAddAddr(){
	jumpPage("receiveGoods/forwardEditAddr.action");
}

function setDefault(id){
	$.showLoading();
	Utils.ajax({
		url:'receiveGoods/setDefault.action',
		data:{id : id},
		success: function(data){
			$.hideLoading();
			if(data){
				$.toast("修改成功");
				setTimeout(function(){window.location.reload(true);},1500);
				
			}
		}
	});
}

function insertOrUpdate(obj){
	$(obj).attr("disabled",true);
	var addrConsignee=$("#addrConsignee").val();
	var addrContact=$("#addrContact").val();
	var addrArea=$("#addrArea").val();
	var addrDetail=$("#addrDetail").val();
	var isDefault=($('#switchCP').is(':checked'))?1:0;
	if(addrConsignee==""){
		$.toast('请输入收货人姓名', 'forbidden');
		$(obj).attr("disabled",false);
		return;
	}
	if(addrContact==""){
		$.toast('请输入收货人手机号', 'forbidden');
		$(obj).attr("disabled",false);
		return;
	}
	
	var reg = /^1[0-9]{10}$/;
	if(!reg.test(addrContact)){
		$.toast('请输入正确的手机号', 'forbidden');
		return;
	}
	
	if(addrArea==""){
		$.toast('请输入所在区域', 'forbidden');
		$(obj).attr("disabled",false);
		return;
	}
	if(addrDetail==""){
		$.toast('请输入详细地址', 'forbidden');
		$(obj).attr("disabled",false);
		return;
	}
	$.showLoading();
	if(type=="1"){//新增receiveGoods
		Utils.ajax({
			url:'receiveGoods/saveReceiveGoods.action',
			data:{
				xgh:xgh,
				addrConsignee:addrConsignee,
				addrContact:addrContact,
				addrArea:addrArea,
				addrDetail:addrDetail,
				addrDefault:isDefault
				},
			success: function(data){
				$.hideLoading();
				if(data){
					$.toast("保存成功");
					setTimeout(function(){jumpPage("userCenter/goAddressList.action");},1500);
				}
			}
		});
	} else {
		Utils.ajax({
			url:'receiveGoods/updateReceiveGoods.action',
			data:{
				id : addrId,
				xgh:xgh,
				addrConsignee:addrConsignee,
				addrContact:addrContact,
				addrArea:addrArea,
				addrDetail:addrDetail,
				addrDefault:isDefault
				},
			success: function(data){
				$.hideLoading();
				if(data){
					$.toast("修改成功");
					setTimeout(function(){jumpPage("userCenter/goAddressList.action");},1500);
				}
			}
		});
	}
}

function deleteAddr(id){
	$.confirm("确定删除吗？", function() {
		$.showLoading();
		Utils.ajax({
			url:'receiveGoods/deleteReceiveGoods.action',
			data:{
				id : id,
			},
			success: function(data){
				$.hideLoading();
				if(data){
					$.toast("删除成功");
					setTimeout(function(){window.location.reload(true);},1500);
				}
			}
		});
		  }, function() {
		  //点击取消后的回调函数
		  });
}