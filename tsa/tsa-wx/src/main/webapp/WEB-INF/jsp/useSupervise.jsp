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
<title>服务监督-投诉</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
	
<script type="text/javascript">
	var basePath = "<%=basePath%>";
	var openid = "${openid}";
	var idNum = "${idNum}";
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
			<a class="weui-navbar__item weui-bar__item--on"
				href="javascript:void(0)" onclick="anchor(1)" id="anchor1"> 我的投诉
			</a> <a class="weui-navbar__item" href="javascript:void(0)"
				onclick="anchor(2)" id="anchor2"> 我要投诉 </a> <a
				class="weui-navbar__item" href="javascript:void(0)"
				onclick="anchor(3)" id="anchor3"> 投诉处理 </a>
		</div>
		<div class="weui-tab__bd">
			<!--tab 我的投诉-->
			<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active"
				style="width: 100%;"></div>
			<!--tab 我要投诉-->
			<div id="tab2" class="reset-weui-cells weui-tab__bd-item">
				<div class="weui-cells bg-white">
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd">
							<img src="images/icon-form06.png" width="20" height="20" alt="" />
						</div>
						<div class="weui-cell__bd">
							<p>
								<input class="weui-input" type="text" placeholder="主题"
									id="boardLocation">
							</p>
						</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd">
							<p>
								<img src="images/icon-form08.png" width="20" height="20" alt="" />
							</p>
							<p>&nbsp;</p>
							<p>&nbsp;</p>
							<p>&nbsp;</p>
						</div>
						<div class="weui-cell__bd">
							<textarea class="weui-textarea" placeholder="内容" rows="3"
								id="useCause" onchange="changeCauseNum(this)" maxlength="198"></textarea>
							<div class="weui-textarea-counter">
								<span id="causeNum">0</span>/200
							</div>
						</div>
					</div>
				</div>
				<div class="weui-cell line-d1e">
				<div style="width: 25px;">&nbsp;</div>
				<div class="ft-grey-999">上传照片</div>
			</div>
			<div class="weui-cell">
				<div style="width: 25px;">&nbsp;</div>
				<div class="uploader-pic" onclick="uploadImgs('supervise/wx_supervise')" id="firstImg" ></div>
				<div class="uploader-pic" onclick="uploadImgs('supervise/wx_supervise')" id="secondImg" style="display: none;"></div>
			</div>
				<!--btn-ok-->
				<input type="hidden" id="type" value="${type }">
				<div class="demos-content-padded">
					<a href="javascript:void(0)" class="weui-btn bg-deep-blue"
						onclick="useSupervise(this)">提 交</a>
				</div>
				<!--temp-->
				<div style="height: 68px;">&nbsp;</div>
			</div>
			<!--tab 工单处理-->
			<div id="tab3" class="weui-tab__bd-item" style="width: 100%;">

			</div>
		</div>
	</div>
	<%@ include file="wxFooter.jsp"%>
	<script type="text/javascript" src="js/useSupervise.js"></script>
	<script type="text/javascript" src="${ctx }/js/wxCommon.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
</body>
</html>
