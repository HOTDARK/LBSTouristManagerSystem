<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/common/jscolor.js"></script>
<form class="form-horizontal" id="editWxModulInfoForm" onsubmit="return false">
	<input type="hidden" id="id" name="id" value="${entity.id}">
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">模块名称<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" check-type="required" id="modulName" name="modulName" value="${entity.modulName}" maxlength="10">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">模块链接<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<textarea class="form-control" id="modulUrl" name="modulUrl" check-type="required" rows="3" cols="" maxlength="256">${entity.modulUrl}</textarea>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">背景色<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input readonly="readonly" type="text" onchange="setImgBg()" class="jscolor form-control" check-type="required" id="modulCss" name="modulCss" value="${entity.modulCss}" maxlength="10">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">模块图片<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="hidden" id="modulPicture" name="modulPicture" value="${entity.modulPicture}"/>
			<img width="280px" id="showPic" style="cursor:pointer;" onclick="$('#uploadfile').click();" src="fileoper/downFile.action?filepath=${entity.modulPicture}" onerror="this.src='images/up.gif'">
			<span style="display: none;"><input id="uploadfile" class="file-loading" type="file"/><!-- multiple表示允许同时上传多个文件 --></span>
		</div>
	</div>
</form>
