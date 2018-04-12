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
    <title>膳食服务-菜品信息</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var storeId="${storeId}";
		var account="${account}";
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
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/food_manage.js"></script>
	<script type="text/javascript">
	var timeStamp=${config.timestamp};
	wx.config({
		debug: false,
	    appId: '${config.appid}',
	    timestamp:timeStamp,
	    nonceStr: '${config.noncestr}',
	    signature: '${config.signature}',
	    jsApiList: ['chooseImage', 'uploadImage']
	});
	</script>
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
	<div class="bg-white ft14" id="foodList"></div>
	<div id="loadMore"></div>
	<p>&nbsp;</p>
	<div class="weui-msg__opr-area">
		<p class="weui-btn-area" style="margin-top: 0;">
		  <a href="javascript:void(0);" onclick="editFood()" class="weui-btn bg-yellow ft-black">新   增</a>
		</p>
	</div>
	<div id="editFood" class="weui-popup__container">
		<div class="weui-popup__overlay"></div>
		<div class="weui-popup__modal">
			<p class="hei-6">&nbsp;</p>
			<form id="foodForm" onsubmit="return false">
				<input type="hidden" id="id" name="id" value=""/>
				<input type="hidden" id="storeId" name="storeId" value="${storeId }"/>
				<input type="hidden" id="coverPhoto" name="coverPhoto" value=""/>
				<input type="hidden" id="createUser" name="createUser" value=""/>
				<input type="hidden" id="updateUser" name="updateUser" value=""/>
				<div class="weui-cell line-d1e bg-white">
					<div class="weui-cell__hd" style="width: 80px">菜品图片</div>
					<div class="weui-cell__bd">
						<div class="btn-uploader" onclick="uploadImg(this)" id="onloadImg" style="height: 90px"></div>
					</div>
				</div>
				<div class="weui-cell line-d1e bg-white ft14">
					<div class="weui-cell__hd" style="width: 80px">菜品名称</div>
					<div class="weui-cell__bd"><input type="text" name="foodName" class="weui-input" placeholder="请输入菜品名称"></div>
				</div>
				<div class="weui-cell line-d1e bg-white ft14">
					<div class="weui-cell__hd" style="width: 80px">菜品介绍</div>
					<div class="weui-cell__bd"><input type="text" name="synopsis" class="weui-input" placeholder="一句话介绍菜品"></div>
				</div>
				<div class="weui-cell line-d1e bg-white ft14">
					<div class="weui-cell__hd" style="width: 80px">售价(元)</div>
					<div class="weui-cell__bd"><input type="tel" name="price" class="weui-input" placeholder=""></div>
				</div>
				<div class="weui-cell line-d1e bg-white ft14">
					<div class="weui-cell__hd" style="width: 80px">所属餐品</div>
					<div class="weui-cell__bd"><input type="text" class="weui-input" id="proRel" placeholder="还没选择餐品"></div>
					<input type="hidden" name="productsIds" value="">
				</div>
				<div class="weui-cell line-d1e bg-white ft14">
					<div class="weui-cell__bd">
						<textarea type="text" name="detailInfo" class="weui-textarea" placeholder="详细信息" rows="3"></textarea>
					</div>
				</div>
				<p>&nbsp;</p>
				<div class="weui-msg__opr-area">
					<p class="weui-btn-area">
					  <a href="javascript:void(0);" class="weui-btn bg-orange ft-black" onclick="saveFood()">确   定</a>
					  <a href="javascript:void(0);" class="weui-btn weui-btn_default ft14 close-popup"><span class="ft-grey-999">暂不新增</span><span class="ft-orange">取消</span></a>
					</p>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
