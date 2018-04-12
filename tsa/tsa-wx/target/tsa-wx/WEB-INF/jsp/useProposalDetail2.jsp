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
<title>服务监督-建议处理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var jurisdictionCode= "${entity.jurisdictionCode}";
		var track = "${entity.track}";
		var state = "${entity.state}";
		var menuCode = "${entity.menuCode}";
		var type = "${entity.type}";
		var adopt1 = "${entity.adopt}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
</head>
<body ontouchstart class="bg-grey-eee">
<div class="weui-navbar ft18 ft-bold400 set-box">
	<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="assignCp(this)" id="assign" style="display:none;">指派</a>
	<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="releaseCp(this)" id="release" style="display:none;">发布</a>
	<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="withdrawCp(this)" id="withdraw" style="display:none;">撤回</a>
	<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="rejectCp(this)" id="reject" style="display:none;">驳回</a>
	<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="suerCp(this)" id="suer" style="display:none;">确认</a>
	<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="adopt(this)" id="adopt" style="display:none;">提交</a>
</div>
<div  class="customer-tabbar">
<div class="weui-tab">
	<div class="weui-tab__bd">
		<div class="weui-cells bg-white mar-t6 set-box-list" style="margin-top:60px;">
			<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">建议详情</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>建议编号</p>
				</div>
				<div class="weui-cell__ft">${entity.id }</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>建议主题</p>
				</div>
				<div class="weui-cell__ft">${entity.title}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd" >
				  <p style="width: 80px">建议内容</p>
				</div>
				<div class="weui-cell__hd">${entity.content}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>当前状态</p>
				</div>
				<div class="weui-cell__ft">${entity.track==1?"已处理":"未处理"}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>建议时间</p>
				</div>
				<div class="weui-cell__ft"><date:date value ="${entity.createTime} "/></div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>备注信息</p>
				</div>
				<div class="weui-cell__ft">${entity.reason}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>附件信息</p>
				</div>
			</div>
			<div class="weui-cell line-d1e" style="padding-top: 0" id="introPicContainer">
				<c:forEach items="${entity.list}" var="l" varStatus="index">
					<div class="weui-cell__hd">
						<input type="hidden" id="filepath${index.index}" value="${l.filePath}">
						<img src="fileoper/downFile.action?filepath=${l.filePath}" width="78" height="78" alt="" onclick="showPb(${index.index})"/>
					</div>
				</c:forEach>
			</div>
			<div class="weui-cell weui-cell_switch bg-white">
				<input type="hidden" id="addrDefault" name="addrDefault" value="0">
			    <div class="weui-cell__bd">是否采纳</div>
			    <div class="weui-cell__ft">
			    	<label for="switchCP" class="weui-switch-cp">
			        	<input id="switchCP" onchange="checkDefault()" class="weui-switch-cp__input" type="checkbox">
			      		<div class="weui-switch-cp__box"></div>
			    	</label>
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
					<textarea class="weui-textarea" placeholder="处理意见" rows="3"
						id="feedback" onchange="changeCauseNum(this)" maxlength="198">${entity.adoptEvaluate }</textarea>
					<div class="weui-textarea-counter">
						<span id="causeNum">0</span>/200
					</div>
				</div>
			</div>
			<input type="hidden" id="id" value="${entity.id }">
<!-- 			<div class="demos-content-padded">
				<a href="javascript:void(0)" class="weui-btn bg-deep-blue"
					onclick="adopt(this)">提 交</a>
			</div> -->
			<div id="org" style="display: none;" class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">指派部门</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="orgDiv">
				<div class="weui-cell__bd">
				  <p>部门</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择部门" id="chooseOrg" data-values=""></div>
			</div>
			<div id="uploadImg" style="display: none;">
				<div class="weui-cell line-d1e">
					<div class="ft-grey-999">上传照片</div>
				</div>
				<div class="weui-cell">
					<div style="width: 25px;">&nbsp;</div>
					<div class="uploader-pic" onclick="uploadImgs('supervise/supervise')" id="firstImg" ></div>
					<div class="uploader-pic" onclick="uploadImgs('supervise/supervise')" id="secondImg" style="display: none;"></div>
				</div>
			</div>
				<!--return-->
				<div class="return-box" onclick="javascript:history.back();">
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
<script type="text/javascript" src="js/useSupervise2.js"></script>
</div>
</body>
</html>