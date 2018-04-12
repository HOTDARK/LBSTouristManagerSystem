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
    <title>运驾服务-维修保养</title>
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
<div class="weui-tab">
	<div class="weui-navbar ft18 ft-bold400 reset-tab">
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(1)" id="anchor1">
		  我的维保
		</a>
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(2)" id="anchor2">
		  我要维保
		</a>
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(3)" id="anchor3">
		 维保审核
		</a>
	</div>
	<div class="weui-tab__bd">
		<!--tab 我的维保-->
		<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active" style="width:100%;">
				
		</div>
		<!--tab 我要维保-->
		<div id="tab2" class="reset-weui-cells weui-tab__bd-item">
			<div class="weui-cells bg-white">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form01.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="申报人" name="applicant" id="applicant" value="${userInfo.xm}" readonly></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form05.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="tel" placeholder="维保时间" name="repairDate" id="repairDate">
					  	<input type="hidden" id="rTime">
					  </p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="维保车辆" id="vehicleLicense"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="送保厂家" id="repairFactory"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form05.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="tel" placeholder="预算费用" id="budgetCost"></p>
					  <input type="hidden" id="stTime">
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form05.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="tel" placeholder="自带材料费用" id="materialCost"></p>
					  <input type="hidden" id="emTime">
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
					  <textarea class="weui-textarea" placeholder="维保原因" rows="3" id="useCause" onchange="changeCauseNum(this)" maxlength="198"></textarea>
					  <div class="weui-textarea-counter"><span id="causeNum">0</span>/200</div>
					</div>
				</div>
				<div >
					<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
						<div class='pull-right mar-r10'>
							<i class='bg-deep-blue tip-state' onclick="addCl()">添加</i>
						</div>
						<span class="line-mid">&nbsp;&nbsp;</span>
						<span class="ft-bold400">自带材料</span>
						<span class="line-mid" >&nbsp;&nbsp;</span>
					</div>
					<div id="cailiao">
					</div>
		    	</div>
			</div>
			<!--btn-ok-->
			<div class="demos-content-padded">
				<a href="javascript:void(0)" class="weui-btn bg-deep-blue" onclick="saveMaintain(this)">提 交</a>
			</div>
		   <!--temp-->
			<div style="height:68px;">&nbsp;</div>
		</div>
		<!--tab 维保审核-->
		<div id="tab3" class="weui-tab__bd-item" style="width:100%;">
			
		</div>
		
		
	</div>
</div>
<%@ include file="wxFooter.jsp"%>
<script type="text/javascript" src="js/maintain.js"></script>
</body>
</html>
