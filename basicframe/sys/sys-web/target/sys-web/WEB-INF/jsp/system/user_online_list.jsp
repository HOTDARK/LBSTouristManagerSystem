<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>登录管理</title>
<script type="text/javascript" src="js/system/user_online.js"></script>
</head>
<body>
	<div class="row">
		<form id="searchFrom" class="form-inline search-bar col-md-12" onsubmit="return false">
			<input type="text" id="accountUserName" class="form-control" placeholder="<spring:message code="sys.signIn.userName.placeholder"/>" />
			<button type="button" id="searchBotton" class="btn blue" style="margin-left: 17px;"><i class="fa fa-search"><spring:message code="public.btn.query"/></i></button>
		</form>
		<div class="col-xm-12">
			<table id="onlineTable" class="table table-striped table-hover" cellspacing="0" width="100%"></table>
		</div>
	</div>
</body>
</html>