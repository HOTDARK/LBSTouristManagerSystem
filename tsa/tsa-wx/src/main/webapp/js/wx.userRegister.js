var flag=false;
var valiFlag=false;
var reg = /^1[0-9]{10}$/;
function getValiCode(obj){
	if(flag){
		return;
	}
	if($("#userPhone").val()==""){
		$.toptip('请输入手机号', 'warning');
		return;
	}
	
	if(!reg.test($("#userPhone").val())){
		$.toptip('请输入正确的手机号', 'warning');
		return;
	}
	flag=true;
	$(obj).addClass("bg-grey-999");
	$(obj).attr("disabled",true);
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
		data:{"phone":$("#userPhone").val()},
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

function userRegister(obj){
	if($("#valiCode").val()==""){
		$.toptip("请输入手机验证码", 'warning');
		return;
	}
//	if(!valiFlag){
//		$.toptip("请先获取验证码", 'warning');
//		return;
//	}
	if($("#userPhone").val()==""){
		$.toptip('请输入手机号', 'warning');
		return;
	}
	if(!reg.test($("#userPhone").val())){
		$.toptip('请输入正确的手机号', 'warning');
		return;
	}
	if($("#userPwd").val()==""){
		$.toptip('请输入密码', 'warning');
		return;
	}
	if($("#userPwdConfirm").val()==""){
		$.toptip('请确认密码', 'warning');
		return;
	}
	if($("#xm").val()==""){
		$.toptip('请输入姓名', 'warning');
		return;
	}
	if($("#xbm").attr("data-values")==""){
		$.toptip('请选择性别', 'warning');
		return;
	}
	if($("#userPwd").val()!=$("#userPwdConfirm").val()){
		$.toptip('两次输入的密码不同', 'warning');
		return;
	}
	Utils.ajax({
		url : 'wxUserBase/userRegister.action',
		data:{"sjhm":$("#userPhone").val(),"openId":openid,"valiCode":$("#valiCode").val(),"dlmm":$("#userPwd").val(),"xm":$("#xm").val(),
				"xbm":$("#xbm").attr("data-values")},
		success : function(data) {
			if(data.success){
				$(obj).attr("disabled",true);
				$.toptip("注册成功，3秒钟后即将跳转", 3000,'success');
				setTimeout(function(){
					window.location="http://"+getRootPath()+"/?openid="+openid;
				},3000);
			} else {
				$.toptip(data.msg, 'error');
				if(data.msg=="微信终端已绑定，无法重复绑定!"){
					setTimeout(function(){
						jumpPage("wx/jumpIndex.action");
					},1000);
				}
			}
		}
	});
}
