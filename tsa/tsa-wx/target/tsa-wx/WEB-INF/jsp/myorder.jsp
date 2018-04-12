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
    <title>我的订单</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var flag="${flag}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
    <link rel="stylesheet" href="${ctx }/css/demos2.css">
    <script type="text/javascript" src="${ctx }/lib/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="${ctx }/lib/fastclick.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-weui.js"></script>
	<script type="text/javascript" src="${ctx }/js/swiper.js"></script>
	<script type="text/javascript" src="${ctx }/js/Utils.js"></script>
    <script type="text/javascript" src="${ctx }/js/wxCommon.js"></script> 
    <script type="text/javascript" src="${ctx }/js/myorder.js"></script> 
  </head>
<body ontouchstart class="bg-grey-eee">
 	<div class="weui-pull-to-refresh__layer">
	    <div class='weui-pull-to-refresh__arrow'></div>
	    <div class='weui-pull-to-refresh__preloader'></div>
	    <div class="down">下拉刷新</div>
	    <div class="up">释放刷新</div>
	    <div class="refresh">正在刷新</div>
	</div>
	<div class="weui-cell line-d1e bg-white ft14" id="times">
		<div class="weui-cell__bd" id="name"></div><input type="hidden" id="value" value="">
		<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18"/></div>
	</div>
	<div id="orderList"></div>
	<div id="loadMore"></div>
</body>
</html>
