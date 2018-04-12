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
    <title>膳食服务-餐厅信息</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var account="${account}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
    <script src="${ctx }/lib/jquery-2.1.4.js"></script>
	<script src="${ctx }/lib/fastclick.js"></script>
	<script src="${ctx }/js/jquery-weui.js"></script>
	<script src="${ctx }/js/swiper.js"></script>
	<script type="text/javascript" src="${ctx }/js/Utils.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/storeInfo.js"></script>
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
<p class="hei-6">&nbsp;</p>
<form id="storeForm" onsubmit="return false">
<div class="bg-white ft14">
	<input type="hidden" name="id" value="">
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">餐厅Logo</div>
		<div class="weui-cell__bd">
			<input type="hidden" name="logo" value="">
			<div class="btn-uploader" id="logo" onclick="uploadImg('logo')"></div>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">餐厅名称</div>
		<div class="weui-cell__bd">
			<p><input class="weui-input" type="text" name="storeName" placeholder="请输入餐厅名" value="" readonly="readonly"></p>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">餐厅介绍</div>
		<div class="weui-cell__bd">
		  <textarea class="weui-textarea" name="storeIntro" placeholder="餐厅介绍" maxlength="400"></textarea>
		  <!-- <div class="weui-textarea-counter"><span id="length">0</span>/200</div> -->
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">餐厅图片</div>
		<div class="weui-cell__bd">
			<input type="hidden" id="imgIds" name="imgIds">
			<input type="hidden" id="paths" name="paths">
			<div id="imgList"></div>
			<div class="btn-uploader pull-left" id="btn-up" onclick="uploadImgs()" style="display: none; width: 88px; height: 58px; margin-top: 2%;"></div>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">联系人</div>
		<div class="weui-cell__bd">
			<p><input class="weui-input" type="text" name="linkman" placeholder="请输入姓名"></p>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">联系电话</div>
		<div class="weui-cell__bd">
			<p><input class="weui-input" type="tel" name="phone" placeholder="请输电话号码"></p>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">地址</div>
		<div class="weui-cell__bd">
			<textarea class="weui-textarea" name="address" placeholder="请输入餐厅地址" maxlength="180"></textarea>
			<!-- <p><input class="weui-input" type="text" name="address" placeholder="请输入餐厅地址"></p> -->
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">营业执照</div>
		<div class="weui-cell__bd">
			<input type="hidden" name="license" value="">
			<div class="btn-uploader" id="license" onclick="uploadImg('license')"></div>
		</div>
	</div>
	<div class="weui-cell line-d1e">	
		<div class="weui-cell__hd" style="width: 80px">餐饮许可证</div>
		<div class="weui-cell__bd">
			<input type="hidden" name="permit" value="">
			<div class="btn-uploader" id="permit"  onclick="uploadImg('permit')"></div>
		</div>
	</div>
</div>
<div class="bg-white ft14 mar-t6">
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">菜系</div>
		<div class="weui-cell__bd">
			<input type="text" class="weui-input" id="cuiRel" placeholder="请选择菜系">
			<input type="hidden" name="cuiIds" value="">
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">订餐电话</div>
		<div class="weui-cell__bd">
			<p><input class="weui-input" name="orderPhone" type="tel" placeholder="请输入订餐电话"></p>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">起送价</div>
		<div class="weui-cell__bd">
			<p><input class="weui-input" name="initiatePrice" type="tel" placeholder="请输入起送价格"></p>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">配送费</div>
		<div class="weui-cell__bd">
			<p><input class="weui-input" name="dphPrice" type="tel" placeholder="请输入配送费"></p>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">配送方式</div>
		<div class="weui-cell__bd">
			<p><input class="weui-input" name="dispatching" type="text" placeholder="请输入配送方式"></p>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">餐厅公告</div>
		<div class="weui-cell__bd">
			<textarea class="weui-textarea" name="affiche" placeholder="请输入商家公告" maxlength="400"></textarea>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">友情提示</div>
		<div class="weui-cell__bd">
			<p><input class="weui-input" name="notice" type="text" placeholder="请输入友情提示"></p>
		</div>
	</div>
	<div class="weui-cell weui-cell_switch bg-white">
		<input type="hidden" id="storeStatus" name="storeStatus" value="0">
	    <div class="weui-cell__bd">营业状态</div>
	    <div class="weui-cell__ft">
	    	<label for="switchCP" class="weui-switch-cp">
	        	<input id="switchCP" onchange="checkStoreState()" class="weui-switch-cp__input" type="checkbox">
	      		<div class="weui-switch-cp__box"></div>
	    	</label>
    	</div>
	</div>
</div>
</form>
<p>&nbsp;</p>
<div class="weui-msg__opr-area">
	<p class="weui-btn-area" style="margin-top: 0;">
		<a href="javascript:void(0);" class="weui-btn bg-yellow ft-black" onclick="saveStore()">提   交</a>
	</p>
</div>
<p>&nbsp;</p>
</body>
</html>
