﻿<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title><spring:message code="sys.org.list.title"/></title>
	<link rel="stylesheet" href="plugins/ztree/css/zTreeStyle/zTreeStyle2.css"></link>
	<script src="plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script src="js/common/common.tree.js"></script>
	<script src="js/common/common.tableFunction.js"></script>
	<script src="js/system/org.js"></script>
</head>
<body>
	<div class="innter-left">
		<div class="table-header color01"><spring:message code="sys.org.orgInfo"/></div>
		<div id="orgTree" class="ztree"></div>
	</div>
	<!--右侧显示表格或者其它-->
	<div class="innter-content">
		<div class="ptool-bar">
			<button id="add_org_btn" class="btn darkblue" value="001001001" onclick="editOrg('add')"><i class="fa fa-plus"></i><spring:message code="sys.org.addSuborg"/></button>
			<button id="edit_org_btn" class="btn blue" value="001001002" onclick="editOrg('edit')"><i class="fa fa-edit"></i><spring:message code="sys.org.editOrg"/></button>
			<button id="btnEnable" class="btn green" value="001001003" onclick="doEnableOrg()"><i class="fa fa-gavel"></i><spring:message code="sys.org.activateOrg"/></button>
			<button id="btnDisable" class="btn yellow" value="001001004" onclick="doDisableOrg()"><i class="fa fa-ban"></i><spring:message code="sys.org.frozenOrg"/></button>
			<button id="add_role_btn" class="btn purple" value="001001005" onclick="editRole('add')"><i class="fa fa-bookmark-o"></i><spring:message code="sys.org.addRole"/></button>
		</div>
		<h5 class="innter-title"><span><spring:message code="sys.org.orgInfo"/></span></h5>
       	<form class="form-horizontal" id="descForm" onsubmit="return false">
         	<div class="form-body">
	        	<div class="form-group">
		          	<div class="col-sm-3">   
		            	<spring:message code="sys.org.orgName"/><spring:message code="public.txt.colon"/>
		            	<span data-field="orgName"></span> 
		            </div>
		            <div class="col-sm-3">   
		            	<spring:message code="sys.org.orgState"/><spring:message code="public.txt.colon"/>
		            	<span class="text-green" data-field="orgState"></span> 
		            </div>
		            <div class="col-sm-3">   
		            	<spring:message code="sys.org.orgType"/><spring:message code="public.txt.colon"/>
		            	<span data-field="typeName"></span> 
		            </div>
	          	</div>
	           	<div class="form-group">
		           	<div class="col-sm-12">  
		           		<spring:message code="sys.org.orgDesc"/><spring:message code="public.txt.colon"/>
		           		<span data-field="remark"></span>
		           	</div>
	          	</div>
           	</div>
       	</form>
		<h5 class="innter-title"><span><spring:message code="sys.org.roleList"/></span></h5>
		<!--start 切换表格及列表呈现方式-->
        <ul class="nav nav-pills pull-right" style="margin-top: -45px;">
			<li id="listIcon" data-toggle="tooltip" title="<spring:message code="public.txt.list"/>" class="start" onclick="switchView(1)">
		  		<a data-toggle="tab"><i class="fa fa-list-ul"></i></a>
		  	</li>
		  	<li id="mapIcon" data-toggle="tooltip" title="<spring:message code="public.txt.card"/>" class="end" onclick="switchView(0)">
		  		<a data-toggle="tab"><i class="fonticon icon-card"></i></a>
		  	</li>
        </ul>
        <!--end 切换表格及列表呈现方式-->
        <!-- 角色列表视图 -->
		<table id="roleTable" class="table table-striped table-hover" cellspacing="0" width="100%"></table>
		<!-- 角色卡片视图 -->
		<div id="orgRoleCardList" style="margin-top: 10px;margin-bottom: 10px;"></div>
		<input type="hidden" id="roleId_addPermission" />
		<input type="hidden" id="roleId_addUserByRole" />
		<!-- 机构新增、修改弹出框 -->
		<div id="editOrg"></div>
		<!-- 为角色添加人员弹出框 -->
		<div id="addUserByRole" style="display: none;"></div>
		<!-- 角色新增、修改弹出框 -->
		<div id="editRole" style="display: none;"></div>
		<!-- 新增角色权限弹出框 -->
		<div id="addRolePermission" style="display: none;"></div>
	</div>

</body>
</html>
