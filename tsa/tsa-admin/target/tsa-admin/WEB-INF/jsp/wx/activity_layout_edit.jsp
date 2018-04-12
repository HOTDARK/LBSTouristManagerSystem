<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="form-horizontal" id="editActivityLayoutForm" onsubmit="return false">
	<input type="hidden" id="id" name="id" value="${entity.id}"/>
	<input type="hidden" id="infoId" name="infoId" value="${entity.infoId}"/>
	<!-- 顺序号 -->
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">顺序号<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" id="lineNum" name="lineNum" value="${entity.lineNum}" check-type="required number" maxlength="1"/>
		</div>
	</div>
	<!-- 总列数 -->
	<div class="form-group" id="columnsNumDiv">
		<label class="col-sm-3 control-label no-padding-right">总列数<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<select id="columnsNum" name="columnsNum" class="selectpicker" data-width="100%" check-type="required">
				<option value="">请选择</option>
				<option value="2" <c:if test="${entity.columnsNum == 2}">selected="selected"</c:if>>2</option>
				<option value="3" <c:if test="${entity.columnsNum == 3}">selected="selected"</c:if>>3</option>
			</select>
		</div>
	</div>
</form>
