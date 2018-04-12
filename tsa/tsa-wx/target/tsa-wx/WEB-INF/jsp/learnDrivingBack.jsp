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
		var backId="${learnOrder.id}";
		var orgCode="${orgCode}";
		var mode="${learnOrder.driveMode}";
		var coach="${learnOrder.coach}";
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
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="reject(this)" id="rejectBtn">驳回</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="cancel(this)" id="cancelBtn">取消</a>
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
				<div class="weui-cell__ft">${learnOrder.stuName }</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>联系电话</p>
				</div>
				<div class="weui-cell__ft">${learnOrder.stuPhone }</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>当前科目</p>
				</div>
				<div class="weui-cell__ft">${learnOrder.subjectCodeName}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>科目模式</p>
				</div>
				<div class="weui-cell__ft">${learnOrder.subjectModelName}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>学车方式</p>
				</div>
				<div class="weui-cell__ft">${learnOrder.driveModeName}</div>
			</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>教练</p>
					</div>
					<div class="weui-cell__ft">${learnOrder.coachName}</div>
				</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>预约课时</p>
				</div>
				<div class="weui-cell__ft">${learnOrder.orderTimes}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>预约时长</p>
				</div>
				<div class="weui-cell__ft">${learnOrder.stuTime/60} 小时</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>开始时间</p>
				</div>
				<div class="weui-cell__ft"><date:date value ="${learnOrder.startTime}"/></div>
			</div>
		</div>
		<div class="weui-cells bg-white mar-t6 ">
			<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">处理意见</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="vehicleDiv">
				<div class="weui-cell__bd">
				  <p>车辆</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择车辆" id="chooseVehicle" data-values=""></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="coachDiv">
				<div class="weui-cell__bd">
				  <p>教练</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择教练" id="chooseDriver" data-values=""></div>
			</div>
		</div>
		<div style="height:68px;">&nbsp;</div>
	</div>
	
</div>

<%@ include file="wxFooter.jsp"%>
</div>
<script type="text/javascript" src="js/wx.learnDrivingBack.js"></script>
</body>

</html>
