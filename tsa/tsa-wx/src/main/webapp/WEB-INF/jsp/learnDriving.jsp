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
    <title>运驾中心-学车</title>
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
	<div class="weui-popup__container" id="half" style="z-index: 999;">
		<div class="weui-popup__overlay"></div>
		<div class="weui-popup__modal">
			<div class="weui-tab__bd">
				<div class="weui-cells bg-white mar-t6 ">
					<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
						<span class="line-mid">&nbsp;&nbsp;</span>
						<span class="ft-bold600">学车评价</span>
						<span class="line-mid" >&nbsp;&nbsp;</span>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__bd">
						  <p class="ft-grey-999">教练员</p>
						</div>
						<div class="weui-cell__ft">
							<div class="icon-stars">
								<p><a onclick="jl(1)"></a><a onclick="jl(2)"></a><a onclick="jl(3)"></a><a onclick="jl(4)"></a><a onclick="jl(5)"></a></p>
								<i style="width:26px;" id="jlpf"></i>
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
					<textarea rows="5" placeholder="还有什么补充的？我们一定改正" id="appraiseInfo"></textarea>
				</div>
				<div class="demos-content-padded">
					<a href="javascript:void(0)" class="weui-btn bg-deep-blue" onclick="saveLearnEvaluate()">提 交</a>
					<a href="javascript:void(0);" class="weui-btn weui-btn_default ft14 close-popup"><span class="ft-grey-999">暂不评价</span> <span class="ft-orange">返回</span></a>
				</div>
				<div style="height:68px;">&nbsp;</div>
			</div>
		</div>
	</div>

<div class="weui-tab">
	<div class="weui-navbar ft18 ft-bold400 reset-tab">
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(1)" id="anchor1">
		  我的学车
		</a>
		<a class="weui-navbar__item weui-bar__item--on" href="javascript:void(0)" onclick="anchor(2)" id="anchor2">
		  我要学车
		</a>
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(3)" id="anchor3">
		  工单处理
		</a>
	</div>
	<div class="weui-tab__bd">
		<!--tab 我的用车-->
		<div id="tab1" class="weui-tab__bd-item" style="width:100%;">
				
		</div>
		<!--tab 我要用车-->
		<div id="tab2" class="reset-weui-cells weui-tab__bd-item weui-tab__bd-item--active">
			<div class="weui-cells bg-white">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form01.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p id="sname"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form02.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p id="driveMode"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e" id="stuSub">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="选择科目" id="currentSub" data-values=""></p>
					</div>
				</div>
				<div class="weui-cell line-d1e" id="coachStr" style="display: none;">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p id="cstr"></p>
					</div>
				</div>
				
				<div class="weui-cell line-d1e" id="subDiv" style="display:none;">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="科目" id="sub" data-values=""></p>
					</div>
				</div>
				<div class="weui-cell line-d1e" id="practiceModeDiv">
					<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="练车模式" id="practiceMode" data-values=""></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd"><img src="images/icon-form05.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="学车时段" id="practiceTime" data-values=""></p>
					</div>
				</div>
				<div class="weui-cell line-d1e" id="sTimeDiv">
					<div class="weui-cell__hd"><img src="images/icon-form05.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="开始时间" id="startTime"></p>
					  <input type="hidden" id="sTime">
					</div>
				</div>
				<div class="weui-cell line-d1e" id="ksDiv">
					<div class="weui-cell__hd"><img src="images/icon-form07.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="预约课时" id="practiceKs" data-values=""></p>
					</div>
				</div>
				
				<div class="weui-cell line-d1e" id="coachDiv">
					<div class="weui-cell__hd"><img src="images/icon-form01.png" width="20" height="20" alt=""/></div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="tel" placeholder="教练" id="coach" data-values=""></p>
					</div>
				</div>
			</div>
			<!--btn-ok-->
			<div class="demos-content-padded">
				<a href="javascript:void(0)" class="weui-btn bg-deep-blue" onclick="learnDriving(this)">提 交</a>
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
<script type="text/javascript" src="js/wx.learnDriving.js"></script>
</body>
</html>
