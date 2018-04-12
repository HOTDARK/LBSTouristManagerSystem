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
    <title>运驾中心-用车</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var idNum="${idNum}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">

  </head>

<body ontouchstart class="bg-grey-eee">
		<div class="weui-popup__container" id="half" style="z-index: 9999;">
		<div class="weui-popup__overlay"></div>
		<div class="weui-popup__modal">
			<div class="comment-ti"><i></i> 从 <span class="ft-white" id="spanFrom"></span> 到 <span class="ft-white" id="spanTo"></span></div>
			<div class="weui-tab__bd">
				<div class="weui-cells bg-white mar-t6 ">
					<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
						<span class="line-mid">&nbsp;&nbsp;</span> 
						<span class="ft-bold600">用车评价</span>
						<span class="line-mid" >&nbsp;&nbsp;</span>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__bd">
						  <p class="ft-grey-999">驾驶员</p>
						</div>
						<div class="weui-cell__ft">
							<div class="icon-stars">
								<p><a onclick="jsy(1)"></a><a onclick="jsy(2)"></a><a onclick="jsy(3)"></a><a onclick="jsy(4)"></a><a onclick="jsy(5)"></a></p>
								<i style="width:26px;" id="jsypf"></i>
		               		</div>
						</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__bd">
						  <p class="ft-grey-999">车辆</p>
						</div>
						<div class="weui-cell__ft">
							<div class="icon-stars">
								<p><a onclick="cl(1)"></a><a onclick="cl(2)"></a><a onclick="cl(3)"></a><a onclick="cl(4)"></a><a onclick="cl(5)"></a></p>
								<i style="width:26px;" id="clpf"></i>
		               		</div>
						</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__bd">
						  <p class="ft-grey-999">综合</p>
						</div>
						<div class="weui-cell__ft">
							<div class="icon-stars">
								<p><a onclick="zh(1)"></a><a onclick="zh(2)"></a><a onclick="zh(3)"></a><a onclick="zh(4)"></a><a onclick="zh(5)"></a></p>
								<i style="width:26px;" id="zhpf"></i>
		               		</div>
						</div>
					</div>
				</div>
				<div class="comment-txt">
					<textarea rows="5" placeholder="还有什么补充的？我们一定改正" id="commentStr"></textarea>
				</div>
				<div class="demos-content-padded">
				<a href="javascript:void(0)" class="weui-btn bg-deep-blue" onclick="appraiseVehicleOrder()">提 交</a>
			</div>
			<div style="height:68px;">&nbsp;</div>
			</div>
			</div>
		</div>

<div class="weui-tab">
	<div class="weui-navbar ft18 ft-bold400 reset-tab">
		<a class="weui-navbar__item weui-bar__item--on" href="javascript:void(0)" onclick="anchor(1)" id="anchor1">
		  我的用车
		</a>
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(2)" id="anchor2">
		  我要用车
		</a>
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(3)" id="anchor3">
		  工单处理
		</a>
	</div>
	<div class="weui-tab__bd">
		<!--tab 我的用车-->
		<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active" style="width:100%;">
				
		</div>
		<!--tab 我要用车-->
		<div id="tab2" class="reset-weui-cells weui-tab__bd-item">
			<div class="weui-cells bg-white">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form01.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="用车人" name="orderUserName" id="orderUserName"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form02.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="tel" placeholder="联系电话" name="orderTelephone" id="orderTelephone"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="用车单位" id="useCompanyOne"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="二级单位" id="useCompany"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form05.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="出发时间" id="startTime"></p>
					  <input type="hidden" id="stTime">
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form05.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="预估返回时间" id="estimateTime"></p>
					  <input type="hidden" id="emTime">
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form07.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="出发地" id="departPlace"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form07.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="目的地" id="destination"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form07.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="上车地点" id="boardLocation"></p>
					</div>
				</div>
				
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form01.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="乘车人姓名" id="busRider"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form02.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="tel" placeholder="乘车人手机" id="riderPhone"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form01.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="tel" placeholder="乘车人数" id="rideNum"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form06.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="车型要求" id="vehicleGearbox"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">
						<p><img src="images/icon-form08.png" width="20" height="20" alt=""/></p>
						<p>&nbsp;</p>
						<p>&nbsp;</p>
						<p>&nbsp;</p>
					</div>
					<div class="weui-cell__bd">
					  <textarea class="weui-textarea" placeholder="用车事由" rows="3" id="useCause" onchange="changeCauseNum(this)" maxlength="198"></textarea>
					  <div class="weui-textarea-counter"><span id="causeNum">0</span>/200</div>
					</div>
				</div>
			</div>
			<!--btn-ok-->
			<div class="demos-content-padded">
				<a href="javascript:void(0)" class="weui-btn bg-deep-blue" onclick="useVehicle(this)">提 交</a>
				<a href="javascript:void(0);" class="weui-btn weui-btn_default ft14 close-popup" onclick="backToList()"><span class="ft-grey-999">暂不评价</span> <span class="ft-orange">返回</span></a>
			</div>
		   <!--temp-->
			<div style="height:68px;">&nbsp;</div>
		</div>
		<!--tab 工单处理-->
		<div id="tab3" class="weui-tab__bd-item" style="width:100%;">
			
		</div>
		
		
	</div>
</div>
<%@ include file="wxFooter.jsp"%>
<script type="text/javascript" src="js/wx.useVehicle.js"></script>
</body>
</html>
