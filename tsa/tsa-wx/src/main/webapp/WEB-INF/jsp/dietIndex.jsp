<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/tags" prefix="date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html> 
<html>
  <head>
    <title>膳食服务</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var clength="${clength}";
		console.log("菜系长度："+clength);
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">
  </head>
<body ontouchstart class="bg-grey-eee">
<div class="weui-tab">
	<div class="weui-navbar ft18 ft-bold400 reset-tab swiper-container">
    <div class="swiper-wrapper" id="swiper-wrapper">
      <div class="swiper-slide swipper-item-on" id="swiper-slide1" onclick="getStore(this)" style="padding:12px 0;">全部</div>
      <c:forEach items="${cuisineInfo }" var="c" varStatus="i">
      	<div class="swiper-slide" onclick="getStore(this,${c.id})" id="swiper-slide${i.index+2 }" style="padding:12px 0;">${c.cuisineName }</div>
      </c:forEach>
    </div>
    <!-- Add Pagination -->
    <div class="swiper-pagination"></div>
  </div>
	<div class="weui-tab__bd">
		<!--购物车btn-->
		<div class="tag-no position-b" style="bottom:5%;">
			<span class="count ft-no-bold" id="totalCount"></span>
			<button type="button" class="btn-shopping bg-ordering" onclick="jumpPage('diet/goTrolley.action')"><img src="images/icon-shopping.png" /></button>
  		</div>
	  	<!--tab con-01-->
		<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active" style="width:100%;"></div>
	</div>
</div>
<script src="${ctx }/lib/jquery-2.1.4.js"></script>
<script src="${ctx }/lib/fastclick.js"></script>
<script src="${ctx }/js/jquery-weui.js"></script>
<script src="${ctx }/js/swiper.js"></script>
<script type="text/javascript" src="${ctx}/js/Utils.js"></script>
<script>
function htmlFontSize() {
	var h = Math.max(document.documentElement.clientHeight,
			window.innerHeight || 0);
	var w = Math.max(document.documentElement.clientWidth,
			window.innerWidth || 0);
	var width = w > h ? h : w;
	width = width > 720 ? 720 : width
	var fz = ~~(width * 100000 / 36) / 10000
	document.getElementsByTagName("html")[0].style.cssText = 'font-size: '
			+ fz + "px";
	var realfz = ~~(+window.getComputedStyle(document
			.getElementsByTagName("html")[0]).fontSize.replace('px', '') * 10000) / 10000
	if (fz !== realfz) {
		document.getElementsByTagName("html")[0].style.cssText = 'font-size: '
				+ fz * (fz / realfz) + "px";
	}
}
  $(function() {
    FastClick.attach(document.body);
    var swiper = new Swiper('.swiper-container', {
        slidesPerView: 4,
        spaceBetween: 10,
        pagination: {
          el: '.swiper-pagination',
          clickable: true,
        },
      });
  });
</script>
<script src="${ctx }/js/wxCommon.js"></script>
<script src="${ctx }/js/dietIndex.js"></script>
</body>
</html>
