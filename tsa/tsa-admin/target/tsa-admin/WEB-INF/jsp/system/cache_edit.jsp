<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form class="form-horizontal" id="cacheEditForm" onsubmit="return false">
	<input type="hidden" name="id">
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.cache.name"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" name="name" disabled="disabled" check-type="required">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.cache.prefix"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" name="prefix" disabled="disabled" check-type="required">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.cache.method"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<textarea class="form-control" name="method" rows="3" disabled="disabled" check-type="required"></textarea>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.cache.state"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<select name="state" class="form-control selectpicker">
				<option value="1"><spring:message code="sys.cache.state.open"/></option>
				<option value="0"><spring:message code="sys.cache.state.close"/></option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.cache.timeToLive"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" name="timeToLive" check-type="required number">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.cache.timeToIdle"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" name="timeToIdle" check-type="required number">
		</div>
	</div>
</form>
