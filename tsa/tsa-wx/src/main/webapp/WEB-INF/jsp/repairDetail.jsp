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
    <title>在线报修-工单详情</title>
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
		<!--tab 报修详情-->
		<div  class="customer-tabbar">
		<div class="bg-deep-blue">
				<div class="pull-left ft-white mar-l10 hei-30">
					<i class="icon-number on pull-left mar-t6"></i>
					<span class="text-number pull-left mar-t3 ">${resultData.repairInfo.repairNum }</span>
				</div>
				<!-- <div class="pull-right mar-r10 bg-blue tip-state mar-t6" style="line-height:25px;"><i class="icon06"></i>我要报修</div> -->
				<p class="clearfix"></p>
				<div href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg" style="padding-bottom: 15px;">
				  <div class="weui-media-box__hd nopic-border">
					<img class="img-circle" src="${headImg}" width="70">
				  </div>
				  <div class="weui-media-box__bd ft-white">
					<h4 class="ft-bold400"><i class="icon-position01"></i>${resultData.repairInfo.repairAreaStr}</h4>
					<p class="line-x1b"></p>
					<p class="mar-t6 ft14  mar-l20">${resultData.repairInfo.repairContent}</p>
					<p class="text-number ft14 mar-l20"><date:date value ="${resultData.repairInfo.repairDate}"/></p>
				  </div>
				</div>
			</div>
			<div style="margin-left: 49px; line-height: 0"><img src="images/repair-img01.png" width="56" alt=""/></div>
			<div style="padding: 0 10px 15px 10px;">
				<div  class="weui-cells repair-detailed" style="margin-top: 0;">
					<c:forEach items="${resultData.repairLogs}" var="logs" varStatus="i">
						<c:if test="${i.count == 1 }">
							<div class="weui-cell on">
						</c:if>
						<c:if test="${i.count != 1 }">
							<div class="weui-cell mar-t6">
						</c:if>
							<i></i>
							<div><p class="icon-s-img"></p><p class="icon-s-topmimg">&nbsp;</p></div>
							<div class="weui-cell__bd">
								<p class="weui-media-box__desc text-number mar-t3 ft16">
									<i class="bg-white tip-state"><span class="ft-grey-666 ft14">${logs.repairStateStr}</span></i>
									<date:date value ="${logs.operDate }"/>
								</p>
								<p class="weui-media-box__desc mar-t6 ft-bold400 ft14">${logs.operContent}</p>
							</div> 
						</div>
					</c:forEach>
					<!--repair-pic-->
					<div class="weui-cell mar-t6">
						<i></i>
						<div><p class="icon-s-img"></p><p class="icon-s-topmimg">&nbsp;</p></div>
						<div class="weui-cell__bd">
							<p class="ft-grey-999">报修照片</p>
							<p>
								<c:if test="${fn:length(resultData.attas)==0}">
									<img src="images/nopic.jpg" width="90" alt=""/>
								</c:if>
								<c:if test="${fn:length(resultData.attas)>0}">
									<input type="hidden" id="filepath1" value="${resultData.attas[0].filePath }">
									<img src="fileoper/downFile.action?filepath=${resultData.attas[0].filePath }" width="90" alt="" onclick="showPb(0)"/>
								</c:if>
								<c:if test="${fn:length(resultData.attas)>1}">
									<input type="hidden" id="filepath2" value="${resultData.attas[1].filePath }">
									&nbsp;<img src="fileoper/downFile.action?filepath=${resultData.attas[1].filePath }" width="90" alt="" onclick="showPb(1)"/>
								</c:if>
							</p>
						</div>
					</div>
				</div>
				<p>&nbsp;</p>
				<!--return-->
				<div class="return-box" onclick="jumpPage('wxRepair/getMyRepairList.action?idNum=1')">
					<em class="l"></em>
					<i></i> 返回
					<em class="r"></em>
				</div>
			</div>
			<!--temp-->
			<div style="height:68px;">&nbsp;</div>
<%@ include file="wxFooter.jsp"%>
<script type="text/javascript" src="js/wx.repairDetail.js"></script>
</body>
</html>
