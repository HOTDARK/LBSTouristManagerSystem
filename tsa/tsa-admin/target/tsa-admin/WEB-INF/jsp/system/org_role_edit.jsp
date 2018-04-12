<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<form class="form-horizontal" id="editRoleForm" onsubmit="return false">
	<input type="hidden" name="orgId" />
	<input type="hidden" name="roleId" />
	<input type="hidden" name="roleSource" />
	<div class="form-group">
		<label for="roleName" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.org.roleName"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" class="form-control" name="roleName" placeholder="<spring:message code="sys.org.roleName.placeholder"/>" check-type="required">
		</div>
	</div>
	<div class="form-group">
		<label for="roleType" class="col-sm-3 control-label no-padding-right"><spring:message code="sys.org.roleType"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<select name="roleType" class="form-control selectpicker">
				<option value="2"><spring:message code="sys.org.roleType.normal"/></option>
				<option value="1"><spring:message code="sys.org.roleType.admin"/></option>
			</select>
		</div>
	</div>
	
</form>
