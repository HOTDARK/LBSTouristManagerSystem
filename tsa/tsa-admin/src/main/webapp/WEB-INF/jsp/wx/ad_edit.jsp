<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="form-horizontal" id="editWxAdInfoForm" onsubmit="return false">
	<input type="hidden" id="id" name="id" value="${entity.id}"/>
	<!-- 广告标题 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">广告名称<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" id="adName" name="adName" value="${entity.adName}" check-type="required" maxlength="50"/>
		</div>
	</div>
	<!-- 广告位置 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">广告名称<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<select class="selectpicker form-control query" data-width="100%" name="adPosition" id="adPosition" check-type="required">
				<option value="">请选择</option>
				<c:forEach items="${positions }" var="dict">
					<option value="${dict.typeDictCode }" <c:if test="${dict.typeDictCode == entity.adPosition}">selected="selected"</c:if>>${dict.typeDictName }</option>
				</c:forEach>
			</select>
		</div>
	</div>
	<!-- 开始时间 -->
	<div class="form-group" id="beginTimeDiv" style="display: none">
		<label class="col-sm-3 control-label">开始时间<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input readonly="readonly" type="text" class="form-control" id="bdate" value="<fmt:formatDate value="${entity.bdate}" pattern="yyyy-MM-dd HH:mm:ss"/>" name="bdate"/>
		</div>
	</div>
	<!-- 结束时间 -->
	<div class="form-group" id="endTimeDiv" style="display: none">
		<label class="col-sm-3 control-label">结束时间<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input readonly="readonly" type="text" class="form-control" id="edate" value="<fmt:formatDate value="${entity.edate}" pattern="yyyy-MM-dd HH:mm:ss"/>" name="edate"/>
		</div>
	</div>
	<!-- 广告图片宽度 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">图片宽度<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" id="adWidth" name="adWidth" value="${entity.adWidth}" check-type="required number" maxlength="5"/>
		</div>
	</div>
	<!-- 广告图片高度 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">图片高度<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" id="adHeight" name="adHeight" value="${entity.adHeight}" check-type="required number" maxlength="5"/>
		</div>
	</div>
	<!-- 广告图片 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">广告图片<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="hidden" id="adPicture" name="adPicture" value="${entity.adPicture}"/>
			<img width="280px" id="showPic" style="cursor:pointer;" onclick="$('#uploadfile').click();" src="fileoper/downFile.action?filepath=${entity.adPicture}" onerror="this.src='images/up.gif'">
			<span style="display: none;"><input id="uploadfile" class="file-loading" type="file"/><!-- multiple表示允许同时上传多个文件 --></span>
		</div>
	</div>
	<!-- 广告链接 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">广告链接<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<textarea class="form-control" id="adUrl" name="adUrl" check-type="required" rows="3" cols="" maxlength="256">${entity.adUrl}</textarea>
			<span style="vertical-align: top; color: red;">链接请加http://</span>
		</div>
	</div>
	<!-- 广告备注 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">广告备注<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<textarea class="form-control" id="adDesc" name="adDesc" rows="5" cols="" maxlength="256">${entity.adDesc}</textarea>
		</div>
	</div>
</form>
