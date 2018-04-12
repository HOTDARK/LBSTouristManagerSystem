<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link rel="stylesheet" href="css/common/btn_inp.css">
<div class="row">
	<div class="col-sm-4">
		<div class="table-bordered T_magin" style="height:400px;">
			<spring:message code="sys.org.text" />
			<ul id="personDeptTree" class="ztree"></ul>
		</div>
	</div>
	<div class="col-sm-8">
		<div class="table-bordered T_magin" style="height:auto!important; min-height:200px; height:200px;">
			<div class="table-header color01"><spring:message code="sys.org.nsUser"/></div>
			<div id="notCheckPersonName"></div>
			<input type="hidden" id="notCheckPersonAccount"/>
		</div>
	</div>
	<div class="col-sm-8">
		<div class="table-bordered T_magin" style="height:auto!important; min-height:200px; height:200px;">
			<div class="table-header color01"><spring:message code="sys.org.sUser"/></div>
			<div id="checkPersonName"></div>
			<input type="hidden" id="checkPersonAccount"/>
		</div>
	</div>
</div>