<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
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
    <title>膳食服务-订单详情</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var orderNum="${orderNum}";
		var storeId="${storeId}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
    <script type="text/javascript" src="${ctx }/lib/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="${ctx }/lib/fastclick.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-weui.js"></script>
	<script type="text/javascript" src="${ctx }/js/swiper.js"></script>
	<script type="text/javascript" src="${ctx }/js/Utils.js"></script>
    <script type="text/javascript" src="${ctx }/js/wxCommon.js"></script> 
    <script type="text/javascript" src="${ctx }/js/order_details.js"></script> 
  </head>
<body ontouchstart class="bg-grey-eee">
<div class="bg-white ft14">
	
	<div class="bg-white weui-cell line-d1e" >
		<div>订单编号：<span id="orderNum"></span></div>
	</div>
	<div class="bg-grey-f6 weui-cell line-d1e" id="state" style="display: none;">
		<div class="ft-red"></div>
	</div>
	<div class="weui-cell line-d1e bg-grey-f6 ft14">
		<div class="weui-cell__hd">商家</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right" id="sotreName"></div>
	</div>
	<div id="foodList"></div>
	<div class="weui-cell line-d1e ft-grey-666">
		<div class="weui-cell__bd">配送费</div>
		<div class="weui-cell__ft ft-bold600 ft-black">¥ <span id="dphPrice"></span></div>
	</div>
	<!-- <div class="weui-cell line-d1e ft-grey-666">
		<div class="weui-cell__bd">红包</div>
		<div class="weui-cell__ft ft-grey-999">未用</div>
	</div>
	<div class="weui-cell line-d1e ft-grey-666 ft14">
		<div class="weui-cell__bd">商家代金券</div>
		<div class="weui-cell__ft ft-grey-999">未用</div>
	</div> -->
	<div class="weui-cell line-d1e ft-grey-666 ft14">
		<div class="weui-cell__bd">&nbsp;</div>
		<div class="weui-cell__ft">共计 <span class="ft-red ft-bold600 ft18">¥<span id="price"></span></span></div>
	</div>
	<div class="weui-cell line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">支付方式</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right" id="payType"></div>
	</div>
</div>

<div class="weui-cells mar-t6 ">
	<div class="weui-cell line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd ft-bold600">配送信息</div>
		<div class="weui-cell__bd"></div>
	</div>
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">配送方式</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right" id="dispatching"></div>
	</div>
	<!-- <div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">送达时间</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right"> 尽快送出</div>
	</div> -->
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">联系人</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right" id="receiveName"></div>
	</div>
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">联系电话</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right" id="orderPhone"></div>
	</div>
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">收货地址</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right" id="address"></div>
	</div>
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">备注</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right" id="remarks"></div>
	</div>
</div>
<!-- <div class="weui-msg__opr-area">
	<p class="weui-btn-area"> <span class="ft-grey-999">查看完毕</span> 
	  <a href="javascript:void(0);" class="weui-btn weui-btn_default ft14" onclick="jumpPage('dietOrder/goOrderList.action')"><span class="ft-orange">返回</span></a>
	</p>
</div> -->
</body>
</html>
