<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
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
    <title>解除绑定</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var url="${url}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
    <link rel="stylesheet" href="${ctx }/css/demos2.css">

  </head>

<body ontouchstart class="bg-grey-eee">
	<div class="user-box">
      	<div class="bg">
      		<div class="title">解除绑定</div>
      		<!--user-portrait-->
      		<div class="user-portrait">
				<!--未登录默认头像-->
				<img src="images/user-portrait.jpg" width="90" height="90" alt=""/>
				<!--登录后微信头像-->
				<!--<img src="images/temp-portrait.jpg" width="90" height="90" alt=""/>-->
			</div>
    		<!--user-topbg-img-->
	    	<img src="images/user-bg.png" width="100%" class="arc-img" alt=""/>
		</div>
	</div>
	<div class="weui-cells bg-white">
		<!--<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
			<span class="line-mid">&nbsp;&nbsp;</span> 
			<span class="ft-bold400">绑定</span><span class="ft14">（微报修工单处理）</span> 
			<span class="line-mid" >&nbsp;&nbsp;</span>
		</div>-->
		<div class="weui-cell line-d1e">
			<div class="weui-cell__hd"><img src="images/icon-order02.png" width="24" height="26" alt=""/></div>
			<div class="weui-cell__bd">
			  <p><input class="weui-input" type="tel" placeholder="手机号" id="bindUserPhone" value="${userInfo.sjhm}" readonly></p>
			</div>
			<div class="weui-cell__ft"><a type="button" class="btn-code bg-deep-blue ft14" onclick="getValiCode(this)" value="获取验证码">获取验证码</a></div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__hd"><img src="images/icon-order03.png" width="24" height="26" alt=""/></div>
			<div class="weui-cell__bd">
			  <p><input class="weui-input" type="tel" id="phoneCode" placeholder="手机验证码"></p>
			</div>
		</div>
	</div>
	<div class="weui-msg__opr-area">
    <p class="weui-btn-area">
      <a href="javascript:void(0)" class="weui-btn bg-deep-blue" onclick="bindPhone(this)">解   除   绑   定</a>
    </p>
  </div>
	<!--temp-->
	<div style="height:100px;">&nbsp;</div>

<%@ include file="wxFooter.jsp"%>

<script type="text/javascript" src="js/wx.unBind.js"></script>
</body>
</html>
