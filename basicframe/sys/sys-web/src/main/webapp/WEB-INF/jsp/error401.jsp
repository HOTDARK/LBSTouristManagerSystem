<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0.2
Version: 1.5.4
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<base href="<%=basePath%>">
<script src="<%=basePath%>/plugins/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/common/jquery.i18n.properties-min-1.0.9.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/common/common.js"></script>
<meta charset="utf-8" />
<title>401</title>
<style type="text/css">
h1 {
	font-family:"微软雅黑";
	font-size:40px;
	margin:20px 0;
	border-bottom:solid 1px #ccc;
	padding-bottom:20px;
	letter-spacing:2px;
}
.time-item strong {
	color:#C71C60;	
	line-height:49px;
	font-size:36px;
	font-family:Arial;
	padding:0 10px;
	margin-right:10px;

}
#day_show {
	float:left;
	line-height:49px;
	color:#c71c60;
	font-size:32px;
	margin:0 10px;
	font-family:Arial,Helvetica,sans-serif;
}
.item-title .unit {
	background:none;
	line-height:49px;
	font-size:24px;
	padding:0 10px;
	float:left;
}

</style>
<script type="text/javascript">

var intDiff = parseInt(3);//倒计时总秒数量

function timer(intDiff){
	window.setInterval(function(){
	var day=0,
		hour=0,
		minute=0,
		second=0;//时间默认值		
	if(intDiff > 0){
		day = Math.floor(intDiff / (60 * 60 * 24));
		hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
		minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
		second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
	}
	if (minute <= 9) minute = '0' + minute;
	if (second <= 9) second = '0' + second;
	/*$('#day_show').html(day+"天");
	$('#hour_show').html('<s id="h"></s>'+hour+'时');
	$('#minute_show').html('<s></s>'+minute+'分');*/
	$('#secondShow').html('<s></s>'+second);
	intDiff--;
	if(intDiff==0){
		//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp    
		var curWwwPath=window.document.location.href; 
		//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp 
		var pathName=window.document.location.pathname;  
		var pos=curWwwPath.indexOf(pathName);  
		//获取主机地址，如： http://localhost:8083 
		var localhostPaht=curWwwPath.substring(0,pos);  
		//获取带"/"的项目名，如：/uimcardprj 
		var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
		window.location.href = localhostPaht+projectName;
		}
	}, 1000);
	
} 

$(function(){
	$("#prompth1").html(getProp("platform.error401.h1"));
	$("#spanPage").html(getProp("platform.error401.page"));
	$("#second").html(getProp("platform.error401.second"));
	$("#prompt").html(getProp("platform.error401.prompt"));
	timer(intDiff);
});	
</script>
</head>
<body>
<div style="width:760px;vertical-align: middle; margin:10px auto;">
<img style="float:left;" src="images/cs_img.png">
<div style="float:left; width:400px; margin-top:100px; color:#003;">
<h1 style="font-size:26px;" id="prompth1"></h1>
<div class="time-item">
	<span id="spanPage"></span>
	<strong><span id="secondShow">04</span><span id="second"></span></strong>
	<span id="prompt"></span>
</div>
</div>
</div>

<!--倒计时模块-->

</body>
</html>