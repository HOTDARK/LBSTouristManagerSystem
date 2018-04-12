var flag=false;
var valiFlag=false;
var reg = /^1[0-9]{10}$/;
function getValiCode(obj){
	if(flag){
		return;
	}
	if($("#bindUserPhone").val()==""){
		$.toptip('请输入手机号', 'warning');
		return;
	}
	
	if(!reg.test($("#bindUserPhone").val())){
		$.toptip('请输入正确的手机号', 'warning');
		return;
	}
	flag=true;
	valiFlag=true;
	$(obj).attr("disabled",true);
	$(obj).addClass("bg-grey-999");
	var bindTime=60;
	var bindInterval = setInterval(function(){
		if(bindTime==0){
			flag=false;
			$(obj).attr("disabled",false);
			$(obj).removeClass("bg-grey-999");
			$(obj).html("点击重新发送");
			clearInterval(bindInterval);
		} else {
			$(obj).html("重新发送（"+bindTime+"）");
			bindTime--;
		}
	},1000);
	Utils.ajax({
		url : 'wxUserBase/getValiCode.action',
		data:{"phone":$("#bindUserPhone").val()},
		success : function(data) {
			$.toptip(data.message, 'warning');
			if(data.success){
				
			} else {
				$.toptip(data.msg, 'warning');
			}
		}
	});
}


function bindPhone(obj){
	if($("#phoneCode").val()==""){
		$.toptip("请输入手机验证码", 'warning');
		return;
	}
//	if(!valiFlag){
//		$.toptip("请先获取验证码", 'warning');
//		return;
//	}
	if($("#bindUserPhone").val()==""){
		$.toptip('请输入手机号', 'warning');
		return;
	}
	if(!reg.test($("#bindUserPhone").val())){
		$.toptip('请输入正确的手机号', 'warning');
		return;
	}
	Utils.ajax({
		url : 'wxUserBase/unBindPhone.action',
		data:{"phone":$("#bindUserPhone").val(),"openId":openid,"valiCode":$("#phoneCode").val()},
		success : function(data) {
			if(data.success){
				$(obj).attr("disabled",true);
				$.toptip("解除绑定成功", 1000,'success');
				setTimeout(function(){
					jumpPage('wx/jumpPage.action?viewName=bindTip.jsp');
				},1000);
			} else {
				$.toptip(data.msg, 'error');
			}
		}
	});
}
