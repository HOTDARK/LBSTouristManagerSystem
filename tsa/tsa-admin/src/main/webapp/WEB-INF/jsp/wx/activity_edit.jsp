<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="form-horizontal" id="editActivityInfoForm" onsubmit="return false">
	<!-- 活动名称 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">活动名称<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" check-type="required" name="activityName" id="activityName" value="${entity.activityName}" maxlength="50">
		</div>
	</div>
	<!-- 活动位编码 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">活动位编码<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<select id="activityPosition" name="activityPosition" class="selectpicker" data-width="100%" check-type="required">
				<option value="">请选择</option>
				<c:forEach items="${positions }" var="dict">
					<option value="${dict.typeDictCode }" <c:if test="${dict.typeDictCode == entity.activityPosition}">selected="selected"</c:if>><spring:message code="${dict.typeDictName }"/></option>
				</c:forEach>
			</select>
		</div>
	</div>
	<!-- 排版简介 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">排版简介<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" check-type="required" name="activityLayout" id="activityLayout" value="${entity.activityLayout}" max="50">
		</div>
	</div>
	<!-- 活动描述 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">活动描述<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<textarea class="form-control" rows="5" cols="" id="activityDesc" name="activityDesc" maxlength="128">${entity.activityDesc}</textarea>
		</div>
	</div>
</form>
