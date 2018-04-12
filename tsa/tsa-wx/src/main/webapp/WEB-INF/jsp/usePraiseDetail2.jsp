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
<title>服务监督-表扬处理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var state = "${entity.state}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">
</head>
<body ontouchstart class="bg-grey-eee">
<div class="weui-navbar ft18 ft-bold400 set-box">
	<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="assignCp(this)" id="assign" style="display:none;">标注</a>
	<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="releaseCp(this)" id="release" style="display:none;">发布</a>
	<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="withdrawCp(this)" id="withdraw" style="display:none;">撤回</a>
</div>
<div  class="customer-tabbar">
<div class="weui-tab">
	<div class="weui-tab__bd">
		<div class="weui-cells bg-white mar-t6 set-box-list" style="margin-top:60px;">
			<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">表扬详情</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<%-- <div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>建议编号</p>
				</div>
				<div class="weui-cell__ft">${entity.id }</div>
			</div> --%>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd" style="width: 80px">
				  <p>表扬主题</p>
				</div>
				<div class="weui-cell__ft">${entity.title}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p style="width: 80px">表扬内容</p>
				</div>
				<div class="weui-cell__hd">${entity.content}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd" style="width: 80px">
				  <p>当前状态</p>
				</div>
				<div class="weui-cell__ft">${entity.state==1?"撤回":"发布"}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd" style="width: 80px">
				  <p>提交时间</p>
				</div>
				<div class="weui-cell__ft"><date:date value ="${entity.createTime} "/></div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd" style="width: 80px">
				  <p>表扬对象</p>
				</div>
				<div class="weui-cell__ft">${entity.orgName}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd" style="width: 80px">
				  <p>图片信息</p>
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
			<div id="org" style="display: none;" class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">标注部门</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="orgDiv">
				<div class="weui-cell__bd" style="width: 80px">
				  <p>部门</p>
				</div>
				<div class="weui-cell__hd">
					<input class="weui-input" type="text" placeholder="选择部门" id="chooseOrg" data-values="${entity.orgCode }" value="${entity.orgName }">
				</div>
			</div>
			<input type="hidden" id="id" value="${entity.id }">
			<p>&nbsp;</p>
				<!--return-->
				<div class="return-box" onclick="jumpPage('wx/jumpPage.action?viewName=usePraise.jsp&idNum=3')">
					<em class="l"></em>
					<i></i>返回
					<em class="r"></em>
				</div>
				<div style="height:68px;">&nbsp;</div>
		</div>
		<div style="height:68px;">&nbsp;</div>
	</div>
	
</div>
<%@ include file="wxFooter.jsp"%>
<script type="text/javascript" src="js/usePraiseDetail.js"></script>
</div>
</body>
</html>