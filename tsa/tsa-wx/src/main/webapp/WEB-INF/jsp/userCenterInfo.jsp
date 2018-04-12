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
    <title>西南大学数字后勤服务大厅</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		//alert(location.href.split('#')[0]);
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">
  </head>
<body ontouchstart class="bg-grey-eee">
<div class="customer-tabbar">
	<div class="user-info">
		<img src="${wxUser.headimgurl}" width="80" height="80" class="pull-left img-circle mar-l20 mar-t10" alt=""/>
		<div class="pull-left ft-white mar-l20" >
			<p class="ft18 mar-t10">${userInfo.xm }</p>
			<p class="text-number"><i class="icon-call"></i> ${userInfo.sjhm }</p>
			<p><i class="icon-state"></i> 正常状态</p>
		</div>
	</div>
	<div class="weui-cells mar-t6">
		<div class="bg-white">
			<!-- <div class="weui-cell line-d1e">
				<div class="weui-cell__hd"><img src="images/icon-form09.png" width="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p>我的消息</p>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div> -->
			<div class="weui-cell line-d1e" onclick="jumpPage('userCenter/goAddressList.action')">
				<div class="weui-cell__hd"><img src="images/icon-form07.png" width="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p>我的收货地址</p>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
			<div class="weui-cell line-d1e" onclick="jumpPage('userCenter/unBind.action')">
				<div class="weui-cell__hd"><img src="images/icon-home08.png" width="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p>解除绑定</p>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
		</div>
		
		<!-- <div class="bg-white mar-t6">
		<div class="weui-cell line-d1e">
			<div class="weui-cell__hd"><img src="images/icon-form02.png" width="20" alt=""/></div>
			<div class="weui-cell__bd ft14 ft-orange">
			  <p>服务电话：023-68326888（9:00-23:00）</p>
			</div>
		</div>
		</div> -->
	</div>
	<!--temp-->
	<div style="height:100px;">&nbsp;</div>
</div>
<%@ include file="wxFooter.jsp"%>
</body>
</html>
