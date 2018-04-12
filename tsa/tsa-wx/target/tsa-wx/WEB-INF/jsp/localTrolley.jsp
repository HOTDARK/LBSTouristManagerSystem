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
    <title>我的购物车</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
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
	<script type="text/javascript" src="${ctx }/js/local_trolley.js"></script>
  </head>
<body ontouchstart class="bg-grey-eee">
	<div id="none" style="display: none;">
		<p style="margin-top: 20%;"></p>
    	<div class="ft18 ft-grey8 text-center"><img src="images/shopping-cart.png" width="120" height="106"/>
    	<p style="margin-top: 15px;">亲！您的购车是空的，去  <span class="ft-orange"><a onclick="jumpPage('diet/goDiet.action')">点菜</a></span>吧~</p></div>
    	<p style="margin-bottom: 10%;"></p>
	</div>
	<div id="show" style="display: none;"></div>
</body>
</html>