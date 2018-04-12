<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form class="form-horizontal" id="editTypeDict" onsubmit="return false">
	<input type="hidden" name="oldTypeDictCode" value="${typeDict.typeDictCode }">
    <input type="hidden" id="parentTypeDictCode" name="parentTypeDictCode" value="${typeDict.parentTypeDictCode}">
	<div class="row">
		<div class="form-group">
			<label for="typeDictName" class="col-sm-3 control-label"><spring:message code="sys.dict.typeName"/><spring:message code="public.txt.colon"/></label>
			<div class="column col-sm-6">
				<input type="text" class="form-control" id="typeDictName" value="${typeDict.typeDictName }" name="typeDictName" placeholder="<spring:message code="sys.dict.typeName.placeholder"/>" check-type="required" maxlength="100">
			</div>
		</div>
		<div class="form-group">
			<label for="typeDictCode" class="col-sm-3 control-label"><spring:message code="sys.dict.typeCode"/><spring:message code="public.txt.colon"/></label>
			<div class="column col-sm-6">
				<input type="text" class="form-control" id="typeDictCode" name="typeDictCode" value="${typeDict.typeDictCode }" placeholder="<spring:message code="sys.dict.typeCode.placeholder"/>" check-type="required number" maxlength="20">
			</div>
		</div>
		<div class="form-group">
			<label for="parentTypeDictName" class="col-sm-3 control-label"><spring:message code="sys.dict.parentName"/><spring:message code="public.txt.colon"/></label>
			<div class="column col-sm-6">
				<input type="text" class="form-control" id="parentTypeDictName" readonly="readonly" name="parentTypeDictName" value="<c:choose><c:when test="${typeDict.parentTypeDictName == null}">无</c:when><c:otherwise>${typeDict.parentTypeDictName}</c:otherwise></c:choose>">
			</div>
		</div>
		
		
		<%-- <div class="form-group">
		<label for="typeDictName" class="col-sm-3 control-label"><spring:message code="sys.dict.mappingName"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" id="mappingName" name="mappingName" value="${typeDict.mappingName}" placeholder="<spring:message code="sys.dict.mappingName.placeholder"/>"  maxlength="100">
		</div>
		</div>
		<div class="form-group">
			<label for="typeDictCode" class="col-sm-3 control-label"><spring:message code="sys.dict.mappingCode"/><spring:message code="public.txt.colon"/></label>
			<div class="column col-sm-6">
				<input type="text" class="form-control" id="mappingCode" name="mappingCode"  value="${typeDict.mappingCode}" placeholder="<spring:message code="sys.dict.mappingCode.placeholder"/>"  maxlength="20">
			</div>
		</div> --%>
		
		<div class="form-group">
			<label for="seqNum" class="col-sm-3 control-label"><spring:message code="sys.dict.orderNo"/><spring:message code="public.txt.colon"/></label>
			<div class="column col-sm-6">
				<input type="text" class="form-control" id="seqNum" name="seqNum" value="${typeDict.seqNum }" placeholder="<spring:message code="sys.dict.orderNo.placeholder"/>" check-type="required number" maxlength="20">
			</div>
		</div>
		<div class="form-group">
			<label for="applicationFlag" class="col-sm-3 control-label"><spring:message code="sys.dict.appFlag"/><spring:message code="public.txt.colon"/></label>
			<div class="column col-sm-6">
				<input type="text" class="form-control" id="applicationFlag" name="applicationFlag" value="${typeDict.applicationFlag}" placeholder="<spring:message code="sys.dict.appFlag.placeholder"/>" check-type="required" maxlength="100">
			</div>
		</div>
		<div class="form-group" style="display: none;">
			<label for="state" class="col-sm-3 control-label"><spring:message code="sys.dict.state"/><spring:message code="public.txt.colon"/></label>
			<div class="column col-sm-6">
				<select class="selectpicker" data-width="100%" id="state" name="state" data-container="body">
					<option value="0" <c:if test="${typeDict.state == 0}"> selected='selected' </c:if>><spring:message code="public.select.einvalid"/></option>
					<option value="1" <c:if test="${typeDict.state == 1}"> selected='selected' </c:if>><spring:message code="public.select.effective"/></option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="applicationFlag" class="col-sm-3 control-label"><spring:message code="sys.dict.remarks"/><spring:message code="public.txt.colon"/></label>
			<div class="column col-sm-6">
				<textarea rows="3" cols="5" class="form-control" placeholder="<spring:message code="sys.dict.remarks.placeholder"/>" name="remark">${typeDict.remark}</textarea>
			</div>
		</div>
	</div>
</form>
