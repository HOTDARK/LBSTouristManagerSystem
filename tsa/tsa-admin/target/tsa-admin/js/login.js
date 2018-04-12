/**
 * 登录界面管理脚本
 */
var Sys = {};
var ua = navigator.userAgent.toLowerCase();
var s;
(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
/* if (Sys.ie) alert('IE: ' + Sys.ie);
if (Sys.firefox) alert('Firefox: ' + Sys.firefox);
if (Sys.chrome) alert('Chrome: ' + Sys.chrome);
if (Sys.opera) alert('Opera: ' + Sys.opera);
if (Sys.safari) alert('Safari: ' + Sys.safari); */
var isAllowIe = false;//标记是否是允许的IE版本
if(!Sys.firefox&&!Sys.chrome){
	if(Sys.ie){
		var version = new Number(Sys.ie);
		var ver = version.toFixed(1);
		if(ver>=10){
			isAllowIe = true;//表示是IE浏览器且版本为10.0以上
		}
	}
	var isIE11 = (ua.indexOf("trident") > -1 && ua.indexOf("rv") > -1);
	if (isIE11) {
		isAllowIe = true;//表示为IE11
	}
	if(!isAllowIe){
		location.href="browser_check.html";
	}
}

$(function(){
	$("#loginForm").validation(function(obj,params){},{reqmark:true});
	$('#kaptchaImage').click(function() {//生成验证码
		$(this).hide().attr('src','captcha/imageCreate.action?' + Math.floor(Math.random()*100)).fadeIn();
	});
	$("#authorizeCode").focus(function() {
		$("#authorized").text("");
	});
	$('body').bind('keyup', function(e) {
		var key = e.which;
		if (key == 13) {
			$("#loginForm").submit();
		}
	});
	$('#userAccount').bind('keyup', function(e) {
		var key = e.which;
		if (key == 13) {
			$("#loginForm").submit();
		}
	});
	$("#btnLogin").click(function(){
		$("#loginForm").submit();
	});
	$("#loginForm").submit(loginSubmit);
});

function loginSubmit(){
	if ($("#loginForm").valid(this)==false){
		return false;
    }
	return true;
}