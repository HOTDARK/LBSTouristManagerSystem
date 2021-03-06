﻿<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title><spring:message code="platform.login.title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="header.jsp"%>
	<link rel="stylesheet" href="css/nplogin.css" />
	<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
	<div class="center">
<!-- 		<img class="logo" src="images/login/login-logo.png" /> -->
		<div id="loginbox">
			 <form class="form-vertical" role="form" id="loginForm" method="post" action="index/userLogin.action">
				<h4><spring:message code="platform.login.h4"/></h4>
				<div class="form-group">
					<input class="form-control user" id="userAccount"
						name="userAccount" placeholder="<spring:message code='platform.login.userAccount.placeholder'/>" check-type="required"
						required-message="<spring:message code='platform.login.userAccount.reqmessage'/>">
				</div>
				<div class="form-group">
					<input type="password" class="form-control password" id="userPwd"
						name="userPwd" placeholder="<spring:message code='platform.login.userPwd.placeholder'/>" check-type="required"
						required-message="<spring:message code='platform.login.userPwd.reqmessage'/>">
				</div>
				<div class="form-group" style="position: relative;">
					<input type="text" id="authorizeCode" name="authorizeCode" class="form-control vnum" 
						placeholder="<spring:message code='platform.login.authorizeCode.placeholder'/>" maxlength="4" check-type="required" required-message="<spring:message code='platform.login.authorizeCode.reqmessage'/>"/>
					<img src="captcha/imageCreate.action" class="vnumimg" id="kaptchaImage" title="<spring:message code='platform.login.kaptchaImage.title'/>" style="cursor: pointer;"/>
				</div>
				<div id="authorized"></div>
			</form>
			<div class="login-actions">
				<button class="btn-login" id="btnLogin">
				<spring:message code="platform.login.btnLogin.val"/>
				</button>
			</div>
		</div>
	</div>
<%-- 	<span class="info"><spring:message code="platform.login.copyright"/></span> --%>
</body>
</html>
