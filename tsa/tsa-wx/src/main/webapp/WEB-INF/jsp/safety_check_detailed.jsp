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
    <title>运驾服务-安全检查</title>
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
    <script type="text/javascript" src="${ctx }/lib/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="${ctx }/lib/fastclick.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-weui.js"></script>
	<script type="text/javascript" src="${ctx }/js/swiper.js"></script>
	<script type="text/javascript" src="${ctx }/js/Utils.js"></script>
	<script type="text/javascript" src="${ctx }/js/wxCommon.js"></script>
	<script type="text/javascript" src="${ctx }/js/safety_check_detailed.js"></script>
  </head>
<body ontouchstart class="bg-grey-eee">
	<c:if test="${entity.checkResult == 0}">
		<div class="ft14 set-box" style="margin-bottom: 5px; display: flex; background-color: #FAFAFA;">
			<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="affirm(${entity.id})" id="auditBtn">检查确认</a>
			<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="reject(${entity.id})" id="rejectBtn">检查驳回</a>
		</div>
	</c:if>
	<div class="weui-tab">
		<div class="weui-tab__bd">
			<div class="weui-cells bg-white mar-t6 set-box-list ft14">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd" style="width: 80px">车牌号码</div>
					<div class="weui-cell__bd">${entity.vehicleLicense}</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd" style="width: 80px">驾驶员</div>
					<div class="weui-cell__bd">${entity.driverName}</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd" style="width: 80px">检查人员</div>
					<div class="weui-cell__bd">${entity.inspectorName}</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd" style="width: 80px">检查日期</div>
					<div class="weui-cell__bd"><fmt:formatDate value="${entity.checkDate}" pattern="yyyy-MM-dd"/></div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd" style="width: 80px">检查记录</div>
					<div class="weui-cell__bd">${entity.serviceRecord}</div>
				</div>
				<c:if test="${entity.checkResult == 2}">
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd" style="width: 80px">驳回原因</div>
						<div class="weui-cell__bd">${entity.inconformtyReason}</div>
					</div>
				</c:if>
			</div>
			<!-- 检查项目 -->
			<%int i=0; %>
			<c:forEach items="${entity.assistantList}" var="list">
				<div class="weui-cells bg-white mar-t6 ft14">
					<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
						<span class="line-mid">&nbsp;&nbsp;</span> 
						<span class="ft-bold400">${list.parentName}</span>
						<span class="line-mid" >&nbsp;&nbsp;</span>
					</div>
					<c:forEach items="${list.list}" var="l">
						<div class="weui-cell line-d1e" id="vehicleDiv">
							<div class="weui-cell__hd" style="width: 80%;">${l.projectName}</div>
							<div class="weui-cell__ft">
								<c:if test="${l.checkResult == 0}"><div style="color: green; margin-top: 7px;">正常</div></c:if>
								<c:if test="${l.checkResult == 1}"><div style="color: red; padding-top: 7px;">异常</div></c:if>
							</div>
						</div>
						<%i++; %>
					</c:forEach>
				</div>
			</c:forEach>
		</div>
	</div>
	<div id="rejectCheck" class="weui-popup__container" style="z-index: 999;">
		<div class="weui-popup__overlay"></div>
		<div class="weui-popup__modal">
			<p class="hei-6">&nbsp;</p>
			<form id="rejectForm" onsubmit="return false">
				<input type="hidden" id="id" name="id" value=""/>
				<div class="bg-white ft14">
					<div class="weui-cell line-d1e bg-white ft14">
						<div class="weui-cell__hd">检查驳回</div>
					</div>
					<div class="weui-cell line-d1e bg-white ft14">
						<div class="weui-cell__bd">
							<textarea type="text" class="weui-textarea" id="inconformtyReason" placeholder="请填写驳回原因" rows="5" maxlength="400"></textarea>
						</div>
					</div>
				</div>
				<p>&nbsp;</p>
				<div class="weui-msg__opr-area">
					<p class="weui-btn-area">
					  <a href="javascript:void(0);" class="weui-btn bg-orange ft-black" onclick="saveReject()">提交</a>
					  <a href="javascript:void(0);" class="weui-btn weui-btn_default ft14 close-popup"><span class="ft-orange">取消</span></a>
					</p>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
