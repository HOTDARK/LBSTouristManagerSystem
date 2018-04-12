<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="/hd-tags" prefix="hd" %>
<form class="form-horizontal" id="editOrgForm" onsubmit="return false">
	<input type="hidden" name="orgId" />
	<input type="hidden" name="parentOrgId" />
	<div class="form-group">
		<label for="orgName" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.org.orgName"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" name="orgName" placeholder="<spring:message code="sys.org.orgName.placeholder"/>" check-type="required" maxlength="100">
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.org.fullName"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" name="orgFullName" placeholder="<spring:message code="sys.org.fullName.placeholder"/>" check-type="required" maxlength="255">
		</div>
	</div>
	<div id="addMethod_div" class="form-group">
		<label class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.org.addType"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<select name="addMethod" class="form-control selectpicker" check-type="required">
				<option value="sub"><spring:message code="sys.org.subOrg"/></option>
				<option value="brother"><spring:message code="sys.org.brotherOrg"/></option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.org.orgType"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<hd:select url="baseAction/obtainCache.action?code=002" id="orgType" cls="form-control" name="orgType" checkType="required" errMsg="<spring:message code='sys.org.orgType.placeholder'/>" />
		</div>
	</div>
	<div class="form-group" style="display: none;">
		<label for="orgType" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.org.orgCode"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" name="orgCode" placeholder="<spring:message code="sys.org.orgCode.placeholder"/>">
		</div>
	</div>
	<div class="form-group" style="display: none;">
		<label for="remark" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.org.subArea"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<textarea name="areaName" rows="3" class="form-control"></textarea>
		</div>
		<span style="color: red; font-size: 9;top: 20px;"><spring:message code="sys.org.subArea.desc"/></span>
	</div>
	<div class="form-group">
		<label for="remark" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.org.orgDesc"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<textarea name="remark" rows="3" class="form-control" maxlength="2000"></textarea>
		</div>
	</div>
</form>
