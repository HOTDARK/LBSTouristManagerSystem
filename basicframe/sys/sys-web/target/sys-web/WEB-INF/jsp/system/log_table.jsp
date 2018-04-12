<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hd" uri="/hd-tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>LOG列表</title>
	<script src="plugins/datatables/js/jquery.dataTables.js"></script>
	<script src="js/common/common.tableFunction.js"></script>
	<style type="text/css">
		.side{position:fixed;width:39px;height:39px;right:0;z-index:100;display:none;}
		.side .sidebox{position:absolute;width:39px;height:39px;top:0;right:0;transition:all 0.3s;background:#3b98d6;opacity:0.8;filter:Alpha(opacity=80);color:#fff;font:14px/39px "微软雅黑";overflow:hidden;}
		.side .sidetop{width:39px;height:39px;line-height:30px;display:inline-block;background:#3b98d6;opacity:0.8;filter:Alpha(opacity=80);transition:all 0.3s;}
		.side .sidetop:hover{background:#ae1c1c;opacity:1;filter:Alpha(opacity=100);}
		.side a{margin-left: 10px;vertical-align: middle;color: #ffffff !important;}
	</style>
</head>
<body>
	<c:if test="${searchLog.type eq '1'}">
	     <hd:table cls="table table-striped table-bordered table-hover" bLengthChange="true" bSort="false" width="100%" height="auto" sAjaxDataProp="data" bFilter="false" bServerSide="true" bPaginate="true" url="sysLogFunc/getSysLogList.action?type=${searchLog.type}&classFunction=${searchLog.classFunction}" id="logList">    	
	     	<hd:column sClass="center" sWidth="15px" title="<label><input type='checkbox' class='ace' id='userListCheck'/><span class='lbl'></span></label>" field="logId"></hd:column>
	     	<hd:column title="操作用户" field="userName"></hd:column>
	     	<hd:column title="操作方法" field="classFunction"></hd:column>
	     	<hd:column title="类型" field="typeName"></hd:column>
	     	<hd:column title="创建时间" field="createTimeString"></hd:column>
	     	<hd:column title="描述" field="description"></hd:column>
	     </hd:table>
	</c:if>
	<c:if test="${searchLog.type eq '2'}">
	     <hd:table cls="table table-striped table-bordered table-hover" bLengthChange="true" width="100%" height="auto" sAjaxDataProp="data" bFilter="false" bServerSide="true" bPaginate="true" url="sysLogFunc/getSysLogList.action?type=${searchLog.type}" id="logList">    	
	     	<hd:column title="操作用户" field="userName"></hd:column>
	     	<hd:column title="登录次数" field="loginCount" bSortable="true"></hd:column>
	     	<hd:column title="操作组织机构次数" field="useSysOrgCount" bSortable="true"></hd:column>
	     	<hd:column title="操作人员管理次数" field="useSysUserCount" bSortable="true"></hd:column>
	     	<hd:column title="操作功能管理次数" field="useSysFunctionCount" bSortable="true"></hd:column>
	     	<hd:column title="操作类型管理次数" field="useTypeCount" bSortable="true"></hd:column>
	     </hd:table>
	</c:if>
</body>
</html>
