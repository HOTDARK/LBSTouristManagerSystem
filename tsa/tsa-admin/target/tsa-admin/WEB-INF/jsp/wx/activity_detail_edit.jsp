<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="form-horizontal" id="editActivityDetailForm" onsubmit="return false">
	<input type="hidden" id="id" name="id" value="${entity.id}"/>
	<!-- 活动主题 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">活动主题<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" id="detailName" name="detailName" value="${entity.detailName}" check-type="required" maxlength="16"/>
		</div>
	</div>
	<!-- 活动链接 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">活动链接<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<textarea class="form-control" id="detailUrl" name="detailUrl" check-type="required" rows="3" cols="" maxlength="256">${entity.detailUrl}</textarea>
			<span style="vertical-align: top; color: red;">链接请加http://</span>
		</div>
	</div>
	<!-- 活动图片 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">活动图片<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="hidden" id="detailPicture" name="detailPicture" value="${entity.detailPicture}"/>
			<img width="280px" id="showPic" style="cursor:pointer;" onclick="$('#uploadfile').click();" src="fileoper/downFile.action?filepath=${entity.detailPicture}" onerror="this.src='images/up.gif'">
			<span style="display: none;"><input id="uploadfile" class="file-loading" type="file"/><!-- multiple表示允许同时上传多个文件 --></span>
		</div>
	</div>
	<!-- 活动描述 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">活动描述<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<textarea class="form-control" id="detailDesc" name="detailDesc" rows="5" cols="" maxlength="256">${entity.detailDesc}</textarea>
		</div>
	</div>
</form>
