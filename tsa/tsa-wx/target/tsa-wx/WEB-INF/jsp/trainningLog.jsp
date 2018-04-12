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
    <title>运驾服务-训练日志</title>
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
		  我的日志
		</a>
		<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(2)" id="anchor2">
		  我要写日志
		</a>
		
	</div>
	<div class="weui-tab__bd">
		<!--tab 我的日志-->
		<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active" style="width:100%;">
				
		</div>
		<!--tab 我要写日志-->
		<div id="tab2" class="reset-weui-cells weui-tab__bd-item">
			<div class="weui-cells bg-white">
				
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">训练日期：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="tel" placeholder="请选择训练日期" name="createTime" id="createTime">
					  </p>
					</div>
				</div>
				<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
					<span class="line-mid">&nbsp;&nbsp;</span>
					<span class="ft-bold400">教练车辆基本情况</span>
					<span class="line-mid" >&nbsp;&nbsp;</span>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">出车前安检：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="firstSecurityCheck"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">训练中及收车时安检：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="lastSecurityCheck"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">维修保养及项目：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="maintenance"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">加油数量：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="oilNum"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">加气数量：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="oilGasNum"></p>
					</div>
				</div>
				<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
					<span class="line-mid">&nbsp;&nbsp;</span>
					<span class="ft-bold400">学员训练情况</span>
					<span class="line-mid" >&nbsp;&nbsp;</span>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">上午训练地点或路线：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="morningRoute"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">上午学员名称：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="morningStuName"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">下午训练地点或路线：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="afternoonRoute"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">下午学员名称：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="afternoonStuName"></p>
					</div>
				</div>
				<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
					<span class="line-mid">&nbsp;&nbsp;</span>
					<span class="ft-bold400">学员考试情况</span>
					<span class="line-mid" >&nbsp;&nbsp;</span>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">科目二报考学员：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="subjectTwoApplyName"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">科目二合格学员：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="subjectTwoQualifiedName"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">科目三报考学员：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="subjectThreeApplyName"></p>
					</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__hd">科目三合格学员：</div>
					<div class="weui-cell__bd">
					  <p><input class="weui-input" type="text" placeholder="" id="subjectThreeQualifiedName"></p>
					</div>
				</div>
			</div>
			<!--btn-ok-->
			<div class="demos-content-padded">
				<a href="javascript:void(0)" class="weui-btn bg-deep-blue" onclick="submitLog(this)">提 交</a>
			</div>
		   <!--temp-->
			<div style="height:68px;">&nbsp;</div>
		</div>
		
		
		
	</div>
</div>
<%@ include file="wxFooter.jsp"%>
<script type="text/javascript" src="js/trainningLog.js"></script>
</body>
</html>
