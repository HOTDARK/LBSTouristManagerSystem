<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title><spring:message code="sys.cache.txt" /> </title>
	<script type="text/javascript" src="js/common/common.tableFunction.js"></script>
	<script type="text/javascript" src="js/system/cache.js"></script>
</head>

<body>
	<div class="row">
		<form id="searchFrom" class="form-inline search-bar col-md-12" onsubmit="return false">
	   		<spring:message code="sys.cache.manager.state" /><spring:message code="public.txt.colon" />
			<span id="cacheState" class="${state?'sign-green':'sign-red'}"><spring:message code="${state?'sys.cache.state.open.txt':'sys.cache.state.close.txt'}" /></span>&nbsp;&nbsp;
			<div class="btn blue" id="changeBtn" onclick="changeState(${!state });">
				<i class="fa fa-cogs"></i><span id="btnVal"><spring:message code="${state?'sys.cache.state.close':'sys.cache.state.open'}"/></span>
	   		</div>
		</form>
		<table id="cacheTable" class="table table-striped table-hover" cellspacing="0" width="100%"></table>
		<div id="cacheEdit"></div>
	</div>
</body>
</html>