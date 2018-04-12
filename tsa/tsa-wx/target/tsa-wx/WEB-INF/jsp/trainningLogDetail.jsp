<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/tags" prefix="date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>运驾服务-训练日志详情</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">
</head>
<body ontouchstart class="bg-grey-eee">

<div  class="customer-tabbar">
<div class="weui-tab">
	
	<div class="weui-tab__bd">
		<div class="weui-cells bg-white mar-t6 set-box-list">
			<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">训练日志详情</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>日志名称</p>
				</div>
				<div class="weui-cell__ft">${log.logName }</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>训练日期</p>
				</div>
				<div class="weui-cell__ft"><fmt:formatDate value="${log.createTime}" /></div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>出车前安检</p>
				</div>
				<div class="weui-cell__ft">${log.firstSecurityCheck}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>训练中及收车时安检</p>
				</div>
				<div class="weui-cell__ft">${log.lastSecurityCheck}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>维修保养及项目</p>
				</div>
				<div class="weui-cell__ft">${log.maintenance}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>加油数量</p>
				</div>
				<div class="weui-cell__ft">${log.oilNum}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>加气数量</p>
				</div>
				<div class="weui-cell__ft">${log.oilGasNum}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>上午训练地点或路线</p>
				</div>
				<div class="weui-cell__ft">${log.morningRoute}</div>
			</div>
			
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>上午学员名称</p>
				</div>
				<div class="weui-cell__ft">${log.morningStuName}</div>
			</div>
			
			
			
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>下午训练地点或路线</p>
				</div>
				<div class="weui-cell__ft">${log.afternoonRoute}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>下午学员名称</p>
				</div>
				<div class="weui-cell__ft">${log.afternoonStuName}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>科目二报考学员</p>
				</div>
				<div class="weui-cell__ft">${log.subjectTwoApplyName}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>科目二合格学员</p>
				</div>
				<div class="weui-cell__ft">${log.subjectTwoQualifiedName}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>科目三报考学员</p>
				</div>
				<div class="weui-cell__ft">${log.subjectThreeApplyName}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>科目三合格学员</p>
				</div>
				<div class="weui-cell__ft">${log.subjectThreeQualifiedName}</div>
			</div>
			
			
			<p>&nbsp;</p>
				<!--return-->
				<div class="return-box" onclick="javascript:window.history.back();">
					<em class="l"></em>
					<i></i>返回
					<em class="r"></em>
				</div>
				<div style="height:68px;">&nbsp;</div>
		</div>
		
	</div>
	
	<div style="height:68px;">&nbsp;</div>
</div>








<%@ include file="wxFooter.jsp"%>
</div>
</body>
</html>