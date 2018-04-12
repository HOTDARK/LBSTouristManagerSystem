<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="form-horizontal" id="editActivityLayoutForm" onsubmit="return false">
	<input type="hidden" id="id" name="id" value="${rel.id}"/>
	<!-- 顺序号 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">顺序号<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" id="seqNum" name="seqNum" value="${rel.seqNum}" check-type="required number" maxlength="1"/>
		</div>
	</div>
	<!-- 素材 -->
	<div class="form-group" id="columnsNumDiv">
		<label class="col-sm-3 control-label no-padding-right">素材<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<select id="detailId" name="detailId" class="selectpicker" data-width="100%" check-type="required">
				<option value="">请选择</option>
				<c:forEach items="${list}" var="l">
					<option value="${l.id}" <c:if test="${rel.detailId == l.id}">selected="selected"</c:if>>${l.detailName}</option>
				</c:forEach>
			</select>
		</div>
	</div>
</form>
