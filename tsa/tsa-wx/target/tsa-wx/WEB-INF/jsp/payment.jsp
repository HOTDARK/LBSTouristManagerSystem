<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/tags" prefix="date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html> 
<html>
  <head>
    <title>膳食服务-订单支付</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var storeId="${storeId}";
		var addressId="${addressId}";
		var orderNum="${orderNum}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
    <script type="text/javascript" src="${ctx}/js/Utils.js"></script>
    <script src="${ctx }/lib/jquery-2.1.4.js"></script>
	<script src="${ctx }/lib/fastclick.js"></script>
	<script src="${ctx }/js/jquery-weui.js"></script>
	<script src="${ctx }/js/swiper.js"></script>
	<script src="${ctx }/js/wxCommon.js"></script>
	<script src="${ctx }/js/payment.js"></script>
</head>
<body ontouchstart class="bg-grey-eee">
<!--/*已有地址选择*/-->
<div class="weui-cell line-d1e bg-white ft14" onclick="goAddressList()">
	<div class="weui-cell__hd" style="width: 36px"><img src="images/icon-ordering02-1.png" width="20" height="20"/></div>
	<input type="hidden" id="address" value="">
	<div class="weui-cell__bd" id="Address"></div>
	<div class="weui-cell__ft"><img src="images/icon05.png" width="10" /></div>
</div>
<div class="weui-cells bg-white mar-t6 ">
	<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
		<span class="line-mid">&nbsp;&nbsp;</span> 
		<span class="ft-bold600" id="storeName"></span>
		<span class="line-mid" >&nbsp;&nbsp;</span>
	</div>
	<div id="foodList"></div>
	<div class="weui-cell line-d1e ft-grey-666 ft14">
		<div class="weui-cell__bd">配送费</div>
		<div class="weui-cell__ft ft-bold600 ft-black">¥<span id="dphPrice"></span></div>
	</div>
	<div class="weui-cell line-d1e ft-grey-666 ft14">
		<div class="weui-cell__bd">&nbsp;</div>
		<div class="weui-cell__ft ft-bold600">共计 <span class="ft-red">¥<span id="money"></span></span></div>
	</div>
</div>
<div class="weui-cells bg-white mar-t6 ">
	<div class="weui-cell line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">支付方式</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right">微信在线支付</div>
	</div>
	<div class="weui-cell line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd" style="width: 60px;">备注</div>
		<div class="weui-cell__bd ft-grey-999 ft12"><input class="weui-input" id="remarks" type="text" placeholder="口味偏好等" maxlength="50"></div>
	</div><!--  onfocus="placeholder=''" onblur="if(value==''){placeholder='口味偏好等'}"  -->
</div>
<!--temp-->
<p class="clearfix"></p>
<div style="height:68px;">&nbsp;</div>
<!--tabbar shopping-cart-->
<div class="weui-tabbar payment-box bg-white">
	<div class="txt">待支付 <span class="ft-red ft-bold600">¥<span id="price"></span></span></div>
	<button type="button" class="btn ft16 ft-bold600" onclick="confirmPayment()">确认付款</button>	  
</div>
<!--/tabbar-->
</html>
