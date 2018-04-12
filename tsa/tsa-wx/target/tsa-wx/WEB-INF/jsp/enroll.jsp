<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
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
    <title>运驾中心-报名</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var idNum="${idNum}";
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
	<div class="weui-navbar ft18 ft-bold400 reset-tab">
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(1)" id="anchor1">
		  我的报名
		</a>
		<a class="weui-navbar__item weui-bar__item--on" href="javascript:void(0)" onclick="anchor(2)" id="anchor2">
		  我要报名
		</a>
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(3)" id="anchor3">
		  工单处理
		</a>
	</div>
	<div class="weui-tab__bd">
		<!--tab 我的报名-->
		<div id="tab1" class="weui-tab__bd-item" style="width:100%;">
				<div class="weui-pull-to-refresh__layer">
					<div class='weui-pull-to-refresh__arrow'></div>
					<div class='weui-pull-to-refresh__preloader'></div>
					<div class="down">下拉刷新</div>
					<div class="up">释放刷新</div>
					<div class="refresh">正在刷新</div>	
				</div>
		</div>
		<!--tab 我要报名-->
		<div id="tab2" class="reset-weui-cells weui-tab__bd-item weui-tab__bd-item--active">
			<div class="weui-cells bg-white">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form01.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="姓名" name="sname" id="sname">
					  	<input type="hidden" id="ssex"><input type="hidden" id="enrollUser">
					  </p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form02.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="tel" placeholder="联系电话" name="telephone" id="telephone"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="报考车型" id="applyModel" data-values=""></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="学车方式" id="driveModel" data-values=""></p>
					</div>
				</div>
			</div>
			<!--btn-ok-->
			<div class="demos-content-padded">
				<a href="javascript:void(0)" class="weui-btn bg-deep-blue" onclick="enroll(this)">提 交</a>
			</div>
		   <!--temp-->
			<div style="height:68px;">&nbsp;</div>
		</div>
		<!--tab 工单处理-->
		<div id="tab3" class="weui-tab__bd-item" style="width:100%;">
			
		</div>
	</div>
</div>
<%@ include file="wxFooter.jsp"%>
<script type="text/javascript" src="js/wx.enroll.js"></script>
</body>
</html>
