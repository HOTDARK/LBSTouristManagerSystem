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
<title>运驾中心-用车详情</title>
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
				<span class="ft-bold400">用车详情</span>
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
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>预约方式</p>
				</div>
				<div class="weui-cell__ft">${order.orderSourceStr}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>用车单位</p>
				</div>
				<div class="weui-cell__ft">${order.vehicleCompanyStr}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>用车事由</p>
				</div>
				<div class="weui-cell__ft">${order.useCause}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>出发时间</p>
				</div>
				<div class="weui-cell__ft"><date:date value ="${order.startOrderDate}"/></div>
			</div>
			
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>预估返回时间</p>
				</div>
				<div class="weui-cell__ft"><date:date value ="${order.estimateDate}"/></div>
			</div>
			
			
			
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>乘车人数</p>
				</div>
				<div class="weui-cell__ft">${order.rideNum}</div>
			</div>
			
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
			<c:if test="${order.state=='103' || order.state=='105' || order.state=='106' || order.state=='107'}">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>驾驶员</p>
					</div>
					<div class="weui-cell__ft">${order.driverName}</div>
				</div>
				
				<%-- <div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>车辆号码</p>
					</div>
					<div class="weui-cell__ft">${order.vehicleLicense}</div>
				</div> --%>
				
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
				<c:if test="${order.state=='106' }">
					<div class="weui-cell line-d1e">
						<div class="weui-cell__bd">
						  <p>过路费</p>
						</div>
						<div class="weui-cell__ft">￥ ${vehicleBack.roadToll}</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__bd">
						  <p>停车费</p>
						</div>
						<div class="weui-cell__ft">￥ ${vehicleBack.parkingRate}</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__bd">
						  <p>行驶里程</p>
						</div>
						<div class="weui-cell__ft">${vehicleBack.mileage} 公里</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__bd">
						  <p>里程单价</p>
						</div>
						<div class="weui-cell__ft">￥ ${vehicleBack.price}</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__bd">
						  <p>合计金额</p>
						</div>
						<div class="weui-cell__ft">￥ ${totalPay}</div>
					</div>
				</c:if>
				
			</c:if>
			<c:if test="${order.state=='102'}">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>驳回原因</p>
					</div>
					<div class="weui-cell__ft">${order.reason}</div>
				</div>
			</c:if>
			<p>&nbsp;</p>
				<!--return-->
				<div class="return-box" onclick="jumpPage('wx/jumpPage.action?viewName=useVehicle.jsp&idNum=1')">
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