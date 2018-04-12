<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="row">
<div class="col-sm-4">
	<div class="table-bordered T_magin" style="height:auto!important; min-height:400px; height:400px;">
		<div class="table-header color01"><spring:message code="sys.org.orgPermission"/></div>
		<ul id="permissionOrgTree" class="ztree"></ul>
	</div>
</div>
<div class="col-sm-8">
	<div class="table-bordered T_magin" style="height:auto!important; min-height:400px; height:400px;">
		<div class="table-header color01"><spring:message code="sys.org.function.list"/></div>
		<ul id="permissionFuncTree" class="ztree"></ul>
	</div>
</div>
</div>
