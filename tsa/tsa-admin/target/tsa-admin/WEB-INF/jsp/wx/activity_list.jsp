<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>网页广告列表</title>
<link rel="stylesheet" href="plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<script type="text/javascript" src="plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="js/common/common.tableFunction.js"></script>
<script type="text/javascript" src="js/wx/activity.js"></script>
<script type="text/javascript" src="js/wx/activity_layout.js"></script>
</head>
<body>
	<div class="row">
		<div class=" col-md-12">
	    	<div class="ptool-bar" >
	  		<a href="javascript:void(0);" class="btn darkblue" id="activityAdd" onclick="editActivityInfo()"><i class="fa fa-plus"></i><spring:message code="public.txt.add"/></a>
	  	 </div>
	  	</div>
		<form id="searchFrom" class="form-inline search-bar col-md-12" onsubmit="return false">
			<input type="text" name="activityName" class="form-control" placeholder="<spring:message code="活动名称"/>" />
			<select class="selectpicker form-control query" data-width="120px" name="activityPosition">
				<option value="" selected="selected"><spring:message code="全部"/></option>
				<c:forEach items="${positions }" var="dict">
					<option value="${dict.typeDictCode }"><spring:message code="${dict.typeDictName }"/></option>
				</c:forEach>
			</select>
			<button type="button" id="searchBotton" class="btn blue" style="margin-left: 17px;"><i class="fa fa-search"><spring:message code="public.btn.query"/></i></button>
		</form>
		<div class="col-xm-12" id="permitApplyDiv">
			<table id="activityTable" class="table table-striped table-hover" cellspacing="0" width="100%"></table>
		</div>
	</div>
	<!-- 编辑活动信息弹出层 -->
	<div id="editActivityInfoDiv" style="display: none;"></div>
	<!-- 查看活动弹出层 -->
	<div id="lookActivityDiv" style="display: none;"></div>
	<!-- 查看活动行列弹出层 -->
	<div id="lookActivityLayoutRelDiv" style="display: none;"></div>
</body>
</html>