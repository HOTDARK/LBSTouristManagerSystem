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
    <title>西南大学数字后勤服务大厅</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var backAccount = "${backAccount}";
		var backId="${buytime.id}";
		var orgCode="${orgCode}";
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
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="audit(this)" id="auditBtn">审核通过</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="reject(this)" id="rejectBtn" style="display:none;">驳回</a>
	</div>
<div  class="customer-tabbar">
<div class="weui-tab">
	
	<div class="weui-tab__bd">
		<div class="weui-cells bg-white mar-t6 set-box-list" style="margin-top:60px;">
			<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">工单详情</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>姓名</p>
				</div>
				<div class="weui-cell__ft">${buytime.userName }</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>联系电话</p>
				</div>
				<div class="weui-cell__ft">${buytime.buyTelephone}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>购买学时</p>
				</div>
				<div class="weui-cell__ft">${buytime.buyTime}</div>
			</div>
		</div>
		<div class="weui-cells bg-white mar-t6 ">
			<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">处理意见</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e" id="vehicleDiv">
				<div class="weui-cell__hd" style="width: 80px;">
				  <p>金额</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="tel" placeholder="请输入金额" id="amount"></div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__hd" style="width: 80px;">
				  <p>发票号</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="请输入发票号" id="invoiceNum"></div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__hd" style="width: 80px;">
				  <p>开票日期</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" id="invoicedate" readonly="readonly"></div>
				<input type="hidden" id="invoiceDate">
			</div>
		</div>
		<div style="height:68px;">&nbsp;</div>
	</div>
</div>
<%@ include file="wxFooter.jsp"%>
</div>
<script type="text/javascript" src="${ctx }/js/wx.buytimeBack.js"></script>
</body>

</html>
