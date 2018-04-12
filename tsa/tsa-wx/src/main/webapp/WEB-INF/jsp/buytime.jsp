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
    <title>运驾中心-购买学时</title>
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
	<div class="weui-popup__container popup-bottom" id="half">
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
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(1)" id="anchor1">
		  我的购买记录
		</a>
		<a class="weui-navbar__item weui-bar__item--on" href="javascript:void(0)" onclick="anchor(2)" id="anchor2">
		  我要买学时
		</a>
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(3)" id="anchor3">
		  工单处理
		</a>
	</div>
	<div class="weui-tab__bd">
		<!--tab 我的购买记录-->
		<div id="tab1" class="weui-tab__bd-item" style="width:100%;">
				
		</div>
		<!--tab 我要买学时-->
		<div id="tab2" class="reset-weui-cells weui-tab__bd-item weui-tab__bd-item--active">
			<div class="weui-cells bg-white">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p id="totalTime"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p id="useTime"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p id="leftTime"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="tel" placeholder="学时" id="timeNum"></p>
					</div>
				</div>
			</div>
			<!--btn-ok-->
			<div class="demos-content-padded">
				<a href="javascript:void(0)" class="weui-btn bg-deep-blue" onclick="buytime(this)">提 交</a>
			</div>
		   <!--temp-->
			<div style="height:68px;">&nbsp;</div>
		</div>
		<!--tab 工单处理-->
		<div id="tab3" class="weui-tab__bd-item" style="width:100%;"></div>
	</div>
</div>
<%@ include file="wxFooter.jsp"%>
<script type="text/javascript" src="js/wx.buytime.js"></script>
</body>
</html>
