<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="header.jsp"%>
	<script type="text/javascript">
		$(function(){
			$("#loginSsoForm").submit();
		});
	</script>
</head>
<body>
	<div style="display: none;">
		<form id="loginSsoForm" method="post" action="index/userLogin.action">
			<input type="text" name="userAccount" value="${userAccount }">
			<input type="text" name="source" value="${source }">
		</form>
	</div>
</body>
</html>
