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
		var operState = "${order.state}";
		var backAccount = "${backAccount}";
		var backId="${order.id}";
		var backOrderNum="${order.orderNum}";
		var orgCode="${orgCode}";
		var vehicleLicense="${order.vehicleLicense}";
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
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="audit(this)" id="auditBtn" style="display:none;">审核通过</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="rejectOrder(this)" id="rejectBtn" style="display:none;">驳回</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="depart(this)" id="depart" style="display:none;">确认出车</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="goBack(this)" id="back" style="display:none;">确认回场</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="saveBack(this)" id="backInfo" style="display:none;">保存回场信息</a>
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
				  <p>预约单编号</p>
				</div>
				<div class="weui-cell__ft">${order.orderNum }</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>预约人</p>
				</div>
				<div class="weui-cell__ft">${order.orderUserName}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>预约人电话</p>
				</div>
				<div class="weui-cell__ft">${order.orderTelephone}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>当前状态</p>
				</div>
				<div class="weui-cell__ft">${order.stateName}</div>
			</div>
			<c:if test="${order.state!=105 && order.state!=103}">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>预约方式</p>
					</div>
					<div class="weui-cell__ft">${order.orderSourceStr}</div>
				</div>
			</c:if>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>用车单位</p>
				</div>
				<div class="weui-cell__ft">${order.vehicleCompanyStr}</div>
			</div>
			<c:if test="${order.state!=105 && order.state!=103}">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>用车事由</p>
					</div>
					<div class="weui-cell__ft">${order.useCause}</div>
				</div>
			</c:if>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>出发时间</p>
				</div>
				<div class="weui-cell__ft"><date:date value ="${order.startOrderDate}"/></div>
			</div>
			
			<c:if test="${order.state!=105 && order.state!=103}">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>预估返回时间</p>
					</div>
					<div class="weui-cell__ft"><date:date value ="${order.estimateDate}"/></div>
				</div>
			</c:if>
			
			
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>乘车人数</p>
				</div>
				<div class="weui-cell__ft">${order.rideNum}</div>
			</div>
			<c:if test="${order.state!=105 && order.state!=103}">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>车型要求</p>
					</div>
					<div class="weui-cell__ft">${order.gearboxStr}</div>
				</div>
			
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>行车出发地</p>
					</div>
					<div class="weui-cell__ft">${order.departPlace}</div>
				</div>
			</c:if>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>行车目的地</p>
				</div>
				<div class="weui-cell__ft">${order.destination}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>上车地点</p>
				</div>
				<div class="weui-cell__ft">${order.boardLocation}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>乘车人姓名</p>
				</div>
				<div class="weui-cell__ft">${order.busRider}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>乘车人手机</p>
				</div>
				<div class="weui-cell__ft">${order.riderPhone}</div>
			</div>
			
			<c:if test="${order.state=='106' || order.state=='107'}">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>驾驶员</p>
					</div>
					<div class="weui-cell__ft">${order.driverName}</div>
				</div>
				
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>车辆号码</p>
					</div>
					<div class="weui-cell__ft">${order.vehicleLicense}</div>
				</div>
				
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>车辆品牌</p>
					</div>
					<div class="weui-cell__ft">${order.brandName}</div>
				</div>
				
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>车辆颜色</p>
					</div>
					<div class="weui-cell__ft">${order.colorStr}</div>
				</div>
			</c:if>
		</div>
		<div class="weui-cells bg-white mar-t6 ">
			<c:if test="${order.state!=103 }">
				<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
					<span class="line-mid">&nbsp;&nbsp;</span>
					<span class="ft-bold400">处理意见</span>
					<span class="line-mid" >&nbsp;&nbsp;</span>
				</div>
			</c:if>
			<div class="weui-cell line-d1e" style="display:none;" id="vehicleDiv">
				<div class="weui-cell__bd">
				  <p>车辆</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择车辆" id="chooseVehicle" data-values=""></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="driverDiv">
				<div class="weui-cell__bd">
				  <p>司机</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择司机" id="chooseDriver" data-values=""></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="messageDiv">
				<div class="weui-cell__bd">
					<p>短信发送对象</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择短信发送对象" id="sendMessage" data-values=""></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="passengerDiv">
				<div class="weui-cell__bd">
				  <p>乘坐人</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="请输入乘坐人" id="passenger" data-values=""></div>
			</div>
			<!-- <div class="weui-cell line-d1e" style="display:none;" id="dispatherDiv">
				<div class="weui-cell__bd">
				  <p>派车人</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="请输入派车人" id="dispather" data-values=""></div>
			</div> -->
			
			<div class="weui-cell line-d1e" style="display:none;" id="startDiv">
				<div class="weui-cell__bd">
				  <p>发车时间</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="请选择发车时间" id="startTime" value="">
					<input type="hidden" id="sTime">
				</div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="endDiv">
				<div class="weui-cell__bd">
				  <p>收车时间</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="请选择收车时间" id="endTime" value="">
					<input type="hidden" id="eTime">
				</div>
			</div>
			
			
			<div class="weui-cell line-d1e" style="display:none;" id="parkDiv">
				<div class="weui-cell__bd">
				  <p>停车费</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="tel" placeholder="请输入停车费" id="park" data-values=""></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="tollDiv">
				<div class="weui-cell__bd">
				  <p>过路费</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="tel" placeholder="请输入过路费" id="toll" data-values=""></div>
			</div>
			
			<div class="weui-cell line-d1e" style="display:none;" id="milestoneDiv">
				<div class="weui-cell__bd">
				  <p>行驶里程</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="tel" placeholder="行驶里程" id="milestone" data-values=""></div>
			</div>
			
			<div class="weui-cell line-d1e" style="display:none;" id="sectionDiv">
				<div class="weui-cell__bd">
				  <p>行车区间</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="行车区间" id="section" data-values=""></div>
			</div>
			
			<div class="weui-cell line-d1e" style="display:none;" id="beforeDiv">
				<div class="weui-cell__bd">
				  <p>出车前自查情况</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="出车前自查情况" id="before" data-values=""></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="drivingDiv">
				<div class="weui-cell__bd">
				  <p>行驶中自查情况</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="行驶中自查情况" id="driving" data-values=""></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="afterDiv">
				<div class="weui-cell__bd">
				  <p>收车后自查情况</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="收车后自查情况" id="after" data-values=""></div>
			</div>
		</div>
		<div style="height:68px;">&nbsp;</div>
	</div>
	
</div>

<%@ include file="wxFooter.jsp"%>
</div>
<script type="text/javascript" src="js/wx.vehicleBack.js"></script>
</body>

</html>
