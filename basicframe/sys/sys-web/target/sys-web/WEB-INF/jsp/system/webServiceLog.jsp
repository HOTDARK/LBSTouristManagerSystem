<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'accessTest.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="editor-app/plugins/bootstrap-daterangepicker_1.3.7/daterangepicker-bs3.css"/>
<script type="text/javascript" src="plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="editor-app/plugins/bootstrap-daterangepicker_1.3.7/daterangepicker.js"></script>
 <script type="text/javascript" src="js/largearea/fault/initDateSelecter.js"></script> 
<script type="text/javascript" src="js/system/webServiceLog.js"></script>
</head>
<body style="padding: 20px !important;">
<br/>
<div style="padding-top: 10px;padding-left:6px;" >
	<form class="form-inline" id="log_searchForm" onsubmit="return false">
	  <div class="form-group">
	    <label for="wname">接口名称<spring:message code="public.txt.colon"/></label>
	    <input type="text" class="form-control" id="wname" name="wname" placeholder="请输入接口名称">
	  </div>
	  <div class="form-group">
	    <label for="username">用户名<spring:message code="public.txt.colon"/></label>
	    <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名称">
	  </div>
	  <div class="form-group">
				<div id="reportrange" class="daterange">
                  <i class="glyphicon glyphicon-calendar icon-calendar icon-large"></i>
                  <span></span> <b class="caret"></b>
                </div>
                <input type="hidden" id="fbeginTime">
                <input type="hidden" id="fendTime">
	  </div>
	  <button type="button" class="btn blue" onclick="doSel();"><i class="fa fa-search"></i>查询</button>
	</form>
</div>
<br/>
	<div class="row" >
		<div class="col-xm-12">
			<table id="userTable" class="display" cellspacing="0" width="100%"></table>
		</div>
		<div id="queryDetail"></div>
	</div>
</body>
</html>
