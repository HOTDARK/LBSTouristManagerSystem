<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
${svg}
<form id="form" method="post" style="display:none" target="_blank" action="trace.do">
	<input type="hidden" name="processId" id="processId"/>
	<input type="hidden" name="version" id="version"/>
	<input type="hidden" name="trace" id="trace"/>
</form>
<script src="<%=basePath %>wflibs/plugins/jquery.min.js"></script>
<script src="<%=basePath %>wflibs/plugins/json2.js"></script>
<script>
var trace = eval('(${trace})');
</script>
<script src="<%=basePath %>wflibs/trace.js"></script>
