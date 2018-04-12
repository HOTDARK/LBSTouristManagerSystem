<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>网页广告列表</title>
<link rel="stylesheet" href="plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<script type="text/javascript" src="plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="js/common/common.tableFunction.js"></script>
<script type="text/javascript" src="js/common/uploadFile.js"></script>
<script type="text/javascript" src="js/wx/activity_detail.js"></script>
</head>
<body>
	<div class="row">
		<div class=" col-md-12">
	    	<div class="ptool-bar" >
	  		<a href="javascript:void(0);" class="btn darkblue" id="detailAdd" onclick="editActivityDetail()"><i class="fa fa-plus"></i><spring:message code="public.txt.add"/></a>
	  	 </div>
	  	</div>
		<form id="searchFrom" class="form-inline search-bar col-md-12" onsubmit="return false">
			<input type="text" name="detailName" class="form-control" placeholder="<spring:message code="素材名称"/>" />
			<button type="button" id="searchBotton" class="btn blue" style="margin-left: 17px;"><i class="fa fa-search"><spring:message code="public.btn.query"/></i></button>
		</form>
		<div class="col-xm-12" id="permitApplyDiv">
			<table id="detailTable" class="table table-striped table-hover" cellspacing="0" width="100%"></table>
		</div>
	</div>
	<div id="editActivityDetailDiv" style="display: none;"></div>
</body>
</html>