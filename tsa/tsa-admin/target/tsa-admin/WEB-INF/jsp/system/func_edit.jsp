<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="css/common/font.css" type="text/css"></link>
<style type="text/css">
	body,ul,li { padding:0; margin:0}
	ul,li { list-style:none}
	.img-scroll { position:relative;width:250px;margin-left: 25px;}
	.img-scroll .prev,.img-scroll .next { position:absolute; display:block; width:15px; height:30px;top:0; color:#FFF; text-align:center; line-height:30px;}
	.img-scroll .prev { left:0;cursor:pointer;background-image:url("images/prev.png") center center no-repeat;}
	.img-scroll .next { right:0;cursor:pointer;background-image:url("images/next.png") center center no-repeat;}
	.img-list { position:relative; width:150px; height:30px; margin-left:45px; overflow:hidden}
	.img-list ul { width:9999px;}
	.img-list li { float:left; display:inline; width:30px; margin-right:10px; height:30px; background-color:#f5f5f5; text-align:center; line-height:30px;}
	.select-li{ border: 2px solid #62a8d9;}
</style>
  
<form class="form-horizontal" id="editSysfunction" onsubmit="return false">
	<input type="hidden" id="functionId" name="functionId"  value="${sysFunction.functionId }">
	<input type="hidden" id="parentFunctionId" name="parentFunctionId" value="${sysFunction.parentFunctionId}">
   	<div class="form-group">
		<label for="functionName" class="col-sm-3 control-label no-padding-right"><spring:message code="sys.fun.name"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" id="functionName" name="functionName" value="${sysFunction.functionName}" placeholder="<spring:message code="sys.fun.name.placeholder"/>" check-type="required" maxlength="100">
		</div>
	</div>
    <div class="form-group">
		<label for="functionCode" class="col-sm-3 control-label no-padding-right"><spring:message code="sys.fun.code"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" id="functionCode" name="functionCode" value="${sysFunction.functionCode}" placeholder="<spring:message code="sys.fun.code.placeholder"/>" check-type="required number" maxlength="20">
		</div>
	</div>
    <div class="form-group">
		<label for="parentFunctionName" class="col-sm-3 control-label no-padding-right"><spring:message code="sys.fun.parentName"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control"  id="parentFunctionName" name="parentFunctionName" readonly="readonly" value="<c:choose><c:when test="${sysFunction.parentFunctionName == null}">无</c:when><c:otherwise>${sysFunction.parentFunctionName}</c:otherwise></c:choose>">
		</div>
	</div>
	<div class="form-group" style="display: none;">
		<label for="state" class="col-sm-3 control-label no-padding-right"><spring:message code="sys.fun.state"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<select class="form-control selectpicker" id="state" name="state">
				<option value="0" <c:if test="${sysFunction.state == 0}"> selected='selected' </c:if>><spring:message code="public.select.einvalid"/></option>
				<option value="1" <c:if test="${sysFunction.state == 1}"> selected='selected' </c:if>><spring:message code="public.select.effective"/></option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="functionType" class="col-sm-3 control-label no-padding-right"><spring:message code="sys.fun.type"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<select  class="form-control selectpicker" id="functionType" name="functionType" onchange="isDisplay();">
				<option value="2" <c:if test="${sysFunction.functionType == 2}"> selected="selected" </c:if>><spring:message code="sys.fun.type.menu"/></option>
				<option value="1" <c:if test="${sysFunction.functionType == 1}"> selected="selected" </c:if>><spring:message code="sys.fun.type.function"/></option>
			</select>
		</div>
	</div>
	<div id="icos" class="form-group" <c:if test='${sysFunction.functionType != 2}'>style="display: none"</c:if> >
    	<label for="icoName" class="col-sm-3 control-label no-padding-right"><spring:message code="sys.fun.icon"/><spring:message code="public.txt.colon"/></label>
	    <div class="column col-sm-6">
	    	<input type="hidden" id="icoName" name="icoName" value="${sysFunction.icoName}"/>
		    <div class="img-scroll column col-sm-6">
			    <span class="prev"></span>
			    <span class="next"></span>
			    <div class="img-list">
			        <ul id="icoList">
			            <c:forEach items="${icoClass}" var="ico">
			            	<c:if test="${ico.icoclass == sysFunction.icoName}">
			            		<li class="fa ${ico.icoclass} select-li" style="cursor: pointer;" data="${ico.icoclass}" onclick="showIco(this);"></li>
			            	</c:if>
			            	<c:if test="${ico.icoclass != sysFunction.icoName}">
			            		<li class="fa ${ico.icoclass}" style="cursor: pointer;" data="${ico.icoclass}" onclick="showIco(this);"></li>
			            	</c:if>
			            </c:forEach>
			        </ul>
			    </div>
			</div>
		</div>
    </div>		
	<div class="form-group">
		<label for="functionUrl" class="col-sm-3 control-label no-padding-right"><spring:message code="sys.fun.url"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control"  id="functionUrl" name="functionUrl" value="${sysFunction.functionUrl }" placeholder="<spring:message code="sys.fun.url.placeholder"/>" maxlength="200">
		</div>
	</div>
	<div class="form-group">
		<label for="seqNum" class="col-sm-3 control-label no-padding-right"><spring:message code="sys.fun.orderNo"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="text" class="form-control"  id="seqNum" name="seqNum" value="${sysFunction.seqNum }" placeholder="<spring:message code="sys.fun.orderNo.placeholder"/>" maxlength="10" check-type="required number">
		</div>
	</div>
	<div class="form-group">
		<label for="seqNum" class="col-sm-3 control-label no-padding-right"><spring:message code="sys.fun.note"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<textarea id="functionDesc" name="functionDesc" class="form-control" rows="" cols="">${sysFunction.functionDesc }</textarea>
			<%-- <br><spring:message code="sys.fun.surplus.char"/><spring:message code="public.txt.colon"/><lable id="remain" value="100" size="3">2000</lable>  --%>
		</div>
	</div>
</form>