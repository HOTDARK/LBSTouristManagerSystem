<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html> 
<html>
  <head>
    <title>解绑成功</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">

  </head>

<body ontouchstart class="bg-user">
<div class="weui-tab">
<div  class="text-center" style="margin-top: 15%;">
	<img src="images/tip-img.png" width="70%" alt=""/>
	<div class="demos-content-padded">
		<a href="javascript:void(0);" id="op" onclick="jumpPage('wx/jumpPage.action?viewName=userBind.jsp')"  class="weui-btn bg-blue-purple">
			(3 sec) 立即绑定
		</a>
	</div>
	<p class="ft22 ft-white">您的手机号已经解绑</p>
	<p class="ft-white">请重新捆绑手机号</p>
	<p>&nbsp;</p>
	<div class="center-block">
		<a href="javascript:void(0);" class="weui-btn bg-rose-bengal ft14" style="display: inline-table; padding: 0px 15px;"
		onclick="jumpPage('wx/jumpPage.action?viewName=userRegister.jsp')">新用户注册</a>
	</div>
</div>
</div>
<%@ include file="wxFooter.jsp"%>
</body>
<script type="text/javascript">
var i=2;
var si;
window.onload=function(){
	si=setInterval(function(){
		if(i==0){
			jumpPage('wx/jumpPage.action?viewName=userBind.jsp');
		}
		$("#op").html("("+i+" sec) 立即绑定");
		i--;
	},1000);
}

</script>
</html>
