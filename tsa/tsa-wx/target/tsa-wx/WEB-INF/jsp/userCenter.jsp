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
    <title>用户中心</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		//alert(location.href.split('#')[0]);
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
	<div class="user-box bg-white">
      	<div class="bg">
      		<a href="javascript:void(0)" onclick="jumpPage('userCenter/userCenterInfo.action')" class="user-set"><img src="images/icon-set.png" width="18" alt=""/></a>
      		<div class="title">用户中心</div>
      		<!--user-portrait-->
      		<div class="user-portrait">
				<!--未登录默认头像-->
				<!--<img src="images/user-portrait.jpg" width="90" height="90" alt=""/>-->
				<!--登录后微信头像-->
				<img src="${wxUser.headimgurl}" width="90" height="90" alt=""/>
			</div>
    		<!--user-topbg-img-->
	    	<img src="images/user-bg.png" width="100%" class="arc-img" alt=""/>
		</div>
	</div>
	<div class="text-center bg-white" style="padding-bottom: 10px;padding-top:10px;">
	  <p class="ft22">${userInfo.xm }</p>
		<div class="button_sp_area">
			<p class="weui-btn weui-btn_mini bg-orange ft16" style="line-height: 1.8">
				<i class="icon-call"></i>
				<span class="text-number">${userInfo.sjhm }</span>&nbsp;&nbsp;
				<i class="icon-state"></i>
				<span class="text-number">正常状态</span>
			</p>
		</div>
	</div>
	<div class="weui-cells mar-t6">
		<div class="bg-white">
			<div class="weui-cell line-d1e" onclick="jumpPage('wxRepair/getMyRepairList.action?idNum=1')">
				<div class="weui-cell__hd"><img src="images/icon-home01.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">我的报修</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
			<div class="weui-cell line-d1e" onclick="jumpPage('dietOrder/goOrderList.action')">
				<div class="weui-cell__hd"><img src="images/icon-home02.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">我的订餐</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
			
			<div class="weui-cell line-d1e" onclick="jumpPage('wx/jumpPage.action?viewName=learnDriving.jsp&idNum=1')">
				<div class="weui-cell__hd"><img src="images/icon-home03.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">我的学车</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
			<div class="weui-cell line-d1e" onclick="jumpPage('wx/jumpPage.action?viewName=useVehicle.jsp&idNum=1')">
				<div class="weui-cell__hd"><img src="images/icon-home04.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">我的用车</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
			<div class="weui-cell line-d1e" onclick="jumpPage('wx/jumpPage.action?viewName=enroll.jsp&idNum=1')">
				<div class="weui-cell__hd"><img src="images/enroll.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">我的报名</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
			
			<div class="weui-cell line-d1e" onclick="jumpPage('wx/jumpPage.action?viewName=useSupervise.jsp&idNum=1&type=0')">
				<div class="weui-cell__hd"><img src="images/icon-home05.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">我的投诉</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
			<div class="weui-cell line-d1e" onclick="jumpPage('wx/jumpPage.action?viewName=useProposal.jsp&idNum=1&type=1')">
				<div class="weui-cell__hd"><img src="images/tag.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">我的建议</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
			<div class="weui-cell line-d1e" onclick="jumpPage('wx/jumpPage.action?viewName=usePraise.jsp&idNum=1')">
				<div class="weui-cell__hd"><img src="images/proposal.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">我的表扬</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
			<div class="weui-cell line-d1e" onclick="checkStoreStatus()">
				<div class="weui-cell__hd"><img src="images/icon-home09.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">商家服务</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
			<div class="weui-cell line-d1e" onclick="jumpPage('wx/jumpPage.action?viewName=vehicleManager.jsp')">
				<div class="weui-cell__hd"><img src="images/car-set.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">运驾服务</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
			</div>
		</div>
		
		<div class="bg-white mar-t6">
		<!-- <div class="weui-cell line-d1e">
			<div class="weui-cell__hd"><img src="images/icon-home06.png" width="28" alt=""/></div>
			<div class="weui-cell__bd">
			  <a href="javascript:void(0)" class="ft-grey-666">客服中心</a>
			</div>
		</div> -->
		<div class="weui-cell line-d1e" onclick="jumpPage('index/goOfdrsp.action')">
			<div class="weui-cell__hd"><img src="images/icon-home07.png" width="28" alt=""/></div>
			<div class="weui-cell__bd">
		  		<a href="javascript:void(0)" class="ft-grey-666">关于后勤</a>
			</div>
			<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18"/></div>
		</div>
		</div>
		
		<div class="bg-white mar-t6">
			<div class="weui-cell line-d1e"  onclick="jumpPage('userCenter/userCenterInfo.action')">
				<div class="weui-cell__hd"><img src="images/icon-home08.png" width="28" alt=""/></div>
				<div class="weui-cell__bd">
				  <a href="javascript:void(0)" class="ft-grey-666">个人信息</a>
				</div>
				<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18"/></div>
			</div>
		</div>
	</div>
	<!--temp-->
	<div style="height:100px;">&nbsp;</div>
</div>
<%@ include file="wxFooter.jsp"%>
	<script type="text/javascript" src="${ctx }/js/usercenter.js"></script>
</body>
</html>
