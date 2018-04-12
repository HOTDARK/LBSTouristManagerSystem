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
<title>服务监督-建议详情</title>
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
				<span class="ft-bold400">建议详情</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>建议编号</p>
				</div>
				<div class="weui-cell__ft">${entity.id }</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>建议主题</p>
				</div>
				<div class="weui-cell__ft">${entity.title}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd" >
				  <p style="width: 80px">建议内容</p>
				</div>
				<div class="weui-cell__hd">${entity.content}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>当前状态</p>
				</div>
				<div class="weui-cell__ft">${entity.track==1?"已处理":"未处理"}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>建议时间</p>
				</div>
				<div class="weui-cell__ft"><date:date value ="${entity.createTime} "/></div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>是否采纳</p>
				</div>
				<c:if test="${entity.track==0 }">
					<div class="weui-cell__ft"></div>
				</c:if>
				<c:if test="${entity.track==1 }">
					<div class="weui-cell__ft">${entity.adopt==1?"采纳":"不采纳"}</div>
				</c:if>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>采纳考评</p>
				</div>
				<div class="weui-cell__ft">${entity.adoptEvaluate}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>附件信息</p>
				</div>
			</div>
			<div class="weui-cell line-d1e" style="padding-top: 0" id="introPicContainer">
				<c:forEach items="${entity.list}" var="l" varStatus="index">
					<div class="weui-cell__hd">
						<input type="hidden" id="filepath${index.index}" value="${l.filePath}">
						<img src="fileoper/downFile.action?filepath=${l.filePath}" width="78" height="78" alt="" onclick="showPb(${index.index})"/>
					</div>
				</c:forEach>
			</div>
			<p>&nbsp;</p>
				<!--return-->
				<div class="return-box" onclick="jumpPage('wx/jumpPage.action?viewName=useProposal.jsp&idNum=1&type=${entity.type}')">
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
<script type="text/javascript" src="js/useSupervise2.js"></script>
</div>
</body>
</html>