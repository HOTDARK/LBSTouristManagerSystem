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
    <title>膳食服务-支付成功</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var orderNum="${orderNum}";
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
	<script type="text/javascript" src="${ctx }/js/payment_success.js"></script>
  </head>
<body ontouchstart class="bg-grey-eee">
	<div class="uccess-box center-block" >
		<div class="box">
			<p class="img center-block"></p>
			<p class="txt ft-bold600">支付成功</p>
		</div>
	</div>
	<p>&nbsp;</p>
	<div class="weui-msg__opr-area">
		<p class="weui-btn-area" style="margin-top: 0;">
		  <a href="javascript:void(0);" class="weui-btn bg-yellow ft-black" onclick="goMyOrder()">查看订单</a>
		  <a href="javascript:void(0);" class="weui-btn bg-yellow ft-black" onclick="jumpPage('diet/goDiet.action')">继续点餐</a>
		</p>
	</div>
</body>
</html>