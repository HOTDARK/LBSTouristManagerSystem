<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>西南大学数字后勤服务大厅</title>
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
</head>
<body ontouchstart class="bg-grey-eee">
<div class="weui-tab">
	<div class="weui-tab__bd">
		<!--tab home-->
		<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
			<!--banner-->
			<div class="swiper-container"  id="sci" data-autoplay="1000">
				<!-- Additional required wrapper -->
				<div class="swiper-wrapper" id="topAd"></div>
				<!-- If we need pagination -->
				<div class="swiper-pagination"></div>
			</div>
			<!--/banner-->
			<!--sys-nav-->
			<div class="swiper-container bg-white" id="scr" data-autoplay="0">
				<div class="swiper-wrapper" id="modul"></div>
					<p style="height: 6px">&nbsp;</p>
					<div class="swiper-pagination"></div>
				</div>
				<!--/sys-nav-->
				<!--content-->
				<p style="height: 6px">&nbsp;</p>
				<div id="activity">
					
					
				</div>
				
				<!--ad-img-1-->
				<div class="mar-t6" id="bottomAd"></div>
			  <!--/content-->
				<!--temp-->
				<div style="height:68px;">&nbsp;</div>
			</div>
			<!--tab home-->
			<div id="tab2" class="weui-tab__bd-item">
			  <h1>页面二</h1>
			</div>
			<div id="tab3" class="weui-tab__bd-item">
			  <h1>页面3</h1>
			</div>
		</div>
		<%@ include file="wxFooter.jsp"%>
	</div>
	<script type="text/javascript" src="js/wx.index.js"></script>
</body>
</html>
