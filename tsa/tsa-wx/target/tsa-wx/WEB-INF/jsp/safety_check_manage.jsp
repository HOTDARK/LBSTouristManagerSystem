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
    <title>运驾服务-安全检查</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var account="${account}";
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
	<script type="text/javascript" src="${ctx }/js/safety_check_manage.js"></script>
  </head>
<body ontouchstart class="bg-grey-eee">
	<div class="weui-pull-to-refresh__layer">
    	<div class='weui-pull-to-refresh__arrow'></div>
	    <div class='weui-pull-to-refresh__preloader'></div>
	    <div class="down">下拉刷新</div>
	    <div class="up">释放刷新</div>
	    <div class="refresh">正在刷新</div>
	</div>
	<div class="ft14" id="safetyList"></div>
	<div id="loadMore"></div>
	<p>&nbsp;</p>
</body>
</html>
