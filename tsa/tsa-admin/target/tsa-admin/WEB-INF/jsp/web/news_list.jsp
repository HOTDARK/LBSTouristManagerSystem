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
<script type="text/javascript" src="js/common/uploadFile.js"></script>
<script type="text/javascript" src="js/web/web_news.js"></script>
</head>
<body>
	<div class="row">
		<div class=" col-md-12">
	    	<div class="ptool-bar" >
	  		<a href="javascript:void(0);" class="btn darkblue" id="webAdAdd" onclick="editWebNews()"><i class="fa fa-plus"></i><spring:message code="public.txt.add"/></a>
	  	 </div>
	  	</div>
		<form id="searchFrom" class="form-inline search-bar col-md-12" onsubmit="return false">
			<input type="text" name="newsTitle" class="form-control" placeholder="<spring:message code="新闻标题"/>" />
			<input type="text" name="keyWord" class="form-control" placeholder="<spring:message code="关键词"/>" />
			<select class="selectpicker form-control query" data-width="120px" name="pictureFlag">
				<option value="">全部</option>
				<option value="1">图片新闻</option>
				<option value="0">普通新闻</option>
			</select>
			<select class="selectpicker form-control query" data-width="120px" name="publishFlag">
				<option value="">全部</option>
				<option value="1">已发布</option>
				<option value="0">未发布</option>
			</select>
			<button type="button" id="searchBotton" class="btn blue" style="margin-left: 17px;"><i class="fa fa-search"><spring:message code="public.btn.query"/></i></button>
		</form>
		<div class="col-xm-12">
			<table id="webNewsTable" class="table table-striped table-hover" cellspacing="0" width="100%"></table>
		</div>
	</div>
	<!-- 编辑网页新闻弹出层 -->
	<div id="editWebNewsDiv" style="display: none;"></div>
	<!-- 查看网页新闻弹出层 -->
	<div id="lookWebNewsDiv" style="display: none;"></div>
</body>
</html>