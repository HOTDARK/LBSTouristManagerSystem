<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/tags" prefix="date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html> 
<html>
  <head>
    <title>膳食服务-评价管理</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var storeId="${storeId}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
    <script type="text/javascript" src="${ctx }/lib/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="${ctx }/lib/fastclick.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-weui.js"></script>
	<script type="text/javascript" src="${ctx }/js/swiper.js"></script>
	<script type="text/javascript" src="${ctx }/js/Utils.js"></script>
	<script type="text/javascript" src="${ctx }/js/wxCommon.js"></script>
	<script type="text/javascript" src="${ctx }/js/comment_manage.js"></script>
</head>
<body ontouchstart class="bg-grey-eee">
	<div class="weui-pull-to-refresh__layer">
    	<div class='weui-pull-to-refresh__arrow'></div>
	    <div class='weui-pull-to-refresh__preloader'></div>
	    <div class="down">下拉刷新</div>
	    <div class="up">释放刷新</div>
	    <div class="refresh">正在刷新</div>
	</div>
	<p class="hei-6">&nbsp;</p>
	<div class="bg-white ft14" id="commentList"></div>
	<div id="loadMore"></div>
	<div id="recomment" class="weui-popup__container" style="z-index: 999;">
		<div class="weui-popup__overlay"></div>
		<div class="weui-popup__modal">
			<p class="hei-6">&nbsp;</p>
			<form id="commentForm" onsubmit="return false">
				<input type="hidden" id="id" name="id" value=""/>
				<div class="bg-white ft14">
					<div class="weui-cell line-d1e bg-white ft14">
						<div class="weui-cell__hd">我来回复</div>
					</div>
					<div class="weui-cell line-d1e bg-white ft14">
						<div class="weui-cell__bd">
							<!-- <input type="text"> -->
							<textarea type="text" class="weui-textarea" id="replay" name="replay" placeholder="对评论内容进行回复" rows="5" maxlength="200"></textarea>
						</div>
					</div>
				</div>
				<p>&nbsp;</p>
				<div class="weui-msg__opr-area">
					<p class="weui-btn-area">
					  <a href="javascript:void(0);" class="weui-btn bg-orange ft-black" onclick="saveCommentReplay()">确   定</a>
					  <a href="javascript:void(0);" class="weui-btn weui-btn_default ft14 close-popup"><span class="ft-grey-999">暂不回复</span><span class="ft-orange">取消</span></a>
					</p>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
