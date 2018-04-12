/**
 * 收货地址管理脚本
 */
$(function() {
    FastClick.attach(document.body);
    getAddressList();
});

function getAddressList(){
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
							html += '<div class="weui-cell line-d1e bg-white ft12">';
							html += '	<div class="weui-cell__hd" style="width: 36px">';
							if (list[i].addrDefault==1) {
								$("#address").val(list[i].id);
								html += '<img src="images/icon-ordering06.png" width="20" height="20"/>';
							}
							html += '	</div>';
							html += '	<div class="weui-cell__bd" onclick="selAddress('+list[i].id+')">';
							html += '		<p class="ft-bold600">'+list[i].addrArea+'&nbsp;&nbsp;'+list[i].addrDetail+'</p>';
							html += '		<p class="ft-grey-999">'+list[i].addrConsignee+' ： '+list[i].addrContact+'</p>';
							html += '	</div>';
							html += '	<div class="weui-cell__ft" onclick="editAddress('+list[i].id+')"><img src="images/icon-edit.png" width="16" /></div>';
							html += '</div>';
						}
						$("#addressList").html(html);
					}else{
						var html = '';
						html += '<div class="weui-cell line-d1e bg-white ft12">';
						html += '	<div class="weui-cell__hd" style="width: 36px"></div>';
						html += '	<div class="weui-cell__bd">';
						html += '		<p class="ft-bold600">暂无收货地址，请添加收货地址</p>';
						html += '	</div>';
						html += '</div>';
						$("#addressList").html(html);
					}
				}else{
					$.alert("获取地址失败");
				}
			}else{
				window.location=domain.drsp + data.msg;
			}
		}
	});
}
 
/**
 * 选择收货地址
 * @param id
 */
function selAddress(id){
	jumpPage('dietOrder/goPayment.action?orderNum='+orderNum+'&storeId='+storeId+'&addressId='+id);
}

/**
 * 弹出编辑地址popup
 * @param id
 */
function editAddress(id){
	$("#address").popup();
	if (id!=null) {
		Utils.ajax({
			url : 'receiveGoods/getUserAddr.action',
			data : {id : id},
			success : function(data){
				if (!$.isEmptyObject(data)) {
					$("input[name='addrConsignee']").val(data.addrConsignee);
					$("input[name='addrContact']").val(data.addrContact);
					$("input[name='addrArea']").val(data.addrArea);
					$("input[name='addrDetail']").val(data.addrDetail);
					$("input[name='addrLabel']").val(data.addrLabel);
					$("input[name='id']").val(data.id);
					if (data.addrDefault==1) {
						$("#switchCP").prop("checked","checked");
					}else{
						$("#switchCP").removeAttr("checked");
					}
				}
			}
		});
	}else{
		$("input[name='addrConsignee']").val(null);
		$("input[name='addrContact']").val(null);
		$("input[name='addrArea']").val(null);
		$("input[name='addrDetail']").val(null);
		$("input[name='addrLabel']").val(null);
		$("input[name='id']").val(null);
		$("#switchCP").removeAttr("checked");
	}
}

/**
 * 保存地址修改
 */
function saveAddress(){
	if ($("input[name='addrConsignee']").val()==null || $("input[name='addrConsignee']").val()=='') {
		$.alert("请填写收货人姓名 ");
	}
	if ($("input[name='addrContact']").val()==null || $("input[name='addrContact']").val()=='') {
		$.alert("请填写联系电话");
	}
	if ($("input[name='addrArea']").val()==null || $("input[name='addrArea']").val()=='') {
		$.alert("请填写所在地区");
	}
	if ($("input[name='addrDetail']").val()==null || $("input[name='addrDetail']").val()=='') {
		$.alert("请填写详细地址");
	}
	var id=$("input[name='id']").val();
	var url='',msg='';
	if (id!=null && id!='') {
		url='receiveGoods/updateReceiveGoods.action';
		msg='修改地址失败';
	}else{
		url='receiveGoods/saveReceiveGoods.action';
		msg='新增地址失败';
	}
	Utils.ajax({
		url : url,
		data : Utils.serializeObj($("#addressForm")),
		success : function(data){
			if (data) {
				$("input[name='addrConsignee']").val(null);
				$("input[name='addrContact']").val(null);
				$("input[name='addrArea']").val(null);
				$("input[name='addrDetail']").val(null);
				$("input[name='addrLabel']").val(null);
				$("input[name='id']").val(null);
				$("#switchCP").removeAttr("checked");
				$.closePopup();
				getAddressList();
			}else{
				$.alert(msg);
			}
		}
	});
}

/**
 * 验证默认地址
 */
function checkDefault(){
	var result = $("#switchCP").is(':checked');
	if (result) {
		$("#addrDefault").val(1);
	}else{
		$("#addrDefault").val(0);
	}
}