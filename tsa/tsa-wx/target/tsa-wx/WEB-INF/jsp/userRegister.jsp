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
    <title>用户注册</title>
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
	<div class="user-box bg-white">
      	<div class="bg">
      		<div class="title">用户注册</div>
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
	<div class="weui-cells bg-white mar-t0">
		<p>&nbsp;</p>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__hd"><img src="images/icon-order02.png" width="24" height="26" alt=""/></div>
			<div class="weui-cell__bd">
			  <p><input class="weui-input" type="tel" placeholder="手机号" id="userPhone"></p>
			</div>
			<div class="weui-cell__ft">
			<a type="button" href="javascript:void(0)" onclick="getValiCode(this)" 
				class="btn-code bg-deep-blue ft14" value="获取验证码">获取验证码</a>
			</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__hd"><img src="images/icon-order03.png" width="24" height="26" alt=""/></div>
			<div class="weui-cell__bd">
			  <p><input class="weui-input" type="text" placeholder="手机验证码" id="valiCode"></p>
			</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__hd"><img src="images/icon-password.png" width="24" height="26" alt=""/></div>
			<div class="weui-cell__bd">
			  <p><input class="weui-input" type="password" placeholder="密码" id="userPwd"></p>
			</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__hd"><img src="images/icon-password01.png" width="24" height="26" alt=""/></div>
			<div class="weui-cell__bd">
			  <p><input class="weui-input" type="password" placeholder="确认密码" id="userPwdConfirm"></p>
			</div>
		</div>
		
		<div class="weui-cell line-d1e">
			<div class="weui-cell__hd"><img src="images/icon-user01.png" width="24" height="26" alt=""/></div>
			<div class="weui-cell__bd">
			  <p><input class="weui-input" type="text" placeholder="姓名" id="xm"></p>
			</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__hd"><img src="images/icon-gender.png" width="24" height="26" alt=""/></div>
			<div class="weui-cell__bd">
			  <p><input class="weui-input" type="tel" placeholder="性别" id="xbm" data-values=""></p>
			</div>
		</div>
	</div>
	<!-- <div class="weui-cells weui-cells_checkbox repair-detailed mar-t3">
		<label class="weui-cell weui-check__label">
			<div class="weui-cell__hd">
			  <input type="checkbox" class="weui-check" name="checkbox1"  checked="checked">
			  <i class="weui-icon-checked"></i>
			</div>
			<div class="weui-cell__bd ft16 ft-grey-999">
			  <p>我已阅读并接受 <a href="#" class="ft-orange">用户协议</a></p>
			</div>
		</label>
	</div> -->  
	
	<div class="weui-msg__opr-area">
		<p class="weui-btn-area" style="margin-top: 0;">
		  <a href="javascript:;" class="weui-btn bg-deep-blue" onclick="userRegister(this)">提   交</a>
		  <a href="javascript:void(0)" onclick="jumpPage('wx/jumpPage.action?viewName=userBind.jsp')" class="weui-btn weui-btn_default ft14"><span class="ft-grey-999">已有账号</span> <span class="ft-blue">返回捆绑</span></a>
		</p>
	</div>
	<!--temp-->
	<div style="height:68px;">&nbsp;</div>
</div>
<%@ include file="wxFooter.jsp"%>
<script type="text/javascript" src="js/wx.userRegister.js"></script>
<script type="text/javascript">
$("#xbm").select({
	  title: "选择性别",
	  items: [
	    {
	      title: "男",
	      value: "1",
	    },
	    {
	      title: "女",
	      value: "2",
	    }
	  ]
	});
</script>
</body>
</html>
