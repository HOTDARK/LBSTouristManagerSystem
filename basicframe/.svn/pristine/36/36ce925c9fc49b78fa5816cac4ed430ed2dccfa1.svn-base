<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="hd" uri="/hd-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="plugins/datatables/js/jquery.js"></script>
    <script type="text/javascript" src="plugins/datatables/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
    function check(data, type, obj){
     	 return "<input type='checkbox' dutyName='"+obj.typeDictName+"' id='custId' class='checkboxes' value='"+obj.typeDictCode+"'/>";
    }
    </script>
    </head>
    
  <body>
  	<button id="sss">SSS</button>
		<hd:table cls="table table-bordered table-hover" bPaginate="true" bProcessing="true" bServerSide="true" url="sysDict/getTypeDictTableTree.action" id="tipeDict">
	  		<hd:column title="操作" bSortable="false" field="typeDictCode" mRender="check" />
	  		<hd:column title="名称" bSortable="true" field="typeDictName" />
	  		<hd:column title="父级编号" bSortable="true" field="parentTypeDictCode" />
	  	</hd:table>
  	<%--<table id="tableTree" class="table table-bordered table-hover">
      <thead>
        <tr>
          <th>名称</th>
          <th>编号</th>
          <th>父级编号</th>
        </tr>
      </thead>
    </table>
  --%></body>
  
</html>
