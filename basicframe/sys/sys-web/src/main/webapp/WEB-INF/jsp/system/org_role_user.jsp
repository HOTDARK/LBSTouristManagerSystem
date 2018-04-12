<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="css/common/btn_inp.css">
<div class="row">
	<div class="col-sm-4">
		<div class="table-bordered T_magin" style="height:400px;">
			<div class="table-header color01"><spring:message code="sys.org.orgInfo"/></div>
			<ul id="addUserTree" class="ztree"></ul>
		</div>
	</div>
	<div class="col-sm-8">
		<div class="table-bordered T_magin" style="height:auto!important; min-height:200px; height:200px;">
			<div class="table-header color01"><spring:message code="sys.org.nsUser"/></div>
			<span id="notCheckedUserIds" style="display: none;"></span>
			<div id="notCheckContentUser">
			</div>
		</div>
	
		<div class="table-bordered T_magin" style="height:auto!important; min-height:200px; height:200px;">
			<div class="table-header color01"><spring:message code="sys.org.sUser"/></div>
			<span id="checkedUserIds" style="display: none;"></span>
			<div id="checkContentUser">
			</div>
		</div>
	</div>
</div>