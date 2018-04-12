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
				
				valiFlag=true;
				
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
		url : 'wxUserBase/bindPhone.action',
		data:{"phone":$("#bindUserPhone").val(),"openId":openid,"valiCode":$("#phoneCode").val()},
		success : function(data) {
			if(data.success){
				$(obj).attr("disabled",true);
				$.toptip("绑定成功，3秒钟后即将跳转", 3000,'success');
				setTimeout(function(){
					window.location="http://"+getRootPath()+"/"+url+'?openid='+openid;
				},3000);
			} else {
				$.toptip(data.msg, 'error');
				if(data.msg=="微信终端已绑定，无法重复绑定!"){
					setTimeout(function(){
						jumpPage(url);
					},1000);
				} else if (data.msg=="找不到该手机号的用户信息，请核对!"){
					$.toptip("您还没有注册，即将跳转注册页面", 'error');
					setTimeout(function(){
						jumpPage("wx/jumpPage.action?viewName=userRegister.jsp");
					},1000);
				}
			}
		}
	});
}
