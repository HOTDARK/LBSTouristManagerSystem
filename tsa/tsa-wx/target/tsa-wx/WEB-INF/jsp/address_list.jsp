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
    <title>收货地址</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var storeId="${storeId}";
		var orderNum="${orderNum}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
    <script type="text/javascript" src="${ctx}/js/Utils.js"></script>
    <script src="${ctx }/lib/jquery-2.1.4.js"></script>
	<script src="${ctx }/lib/fastclick.js"></script>
	<script src="${ctx }/js/jquery-weui.js"></script>
	<script src="${ctx }/js/swiper.js"></script>
	<script src="${ctx }/js/wxCommon.js"></script>
	<script src="${ctx }/js/address_list.js"></script>
	<style type="text/css">
		.weui-popup__container{
			z-index: 999;
		}
	</style>
  </head>
<body ontouchstart class="bg-grey-eee">
<!--/*已有地址选择*/-->
<div id="addressList"></div>
<!--temp-->
<p class="clearfix"></p>
<div style="height:68px;">&nbsp;</div>
<!--tabbar shopping-cart-->
<div class="weui-tabbar payment-box bg-white ft-yellow">
	<button type="button" class="btn-add ft14 ft-yellow" onclick="editAddress()"><img src="images/icon-add.png" width="20" height="20" /> 新增收货地址</button>	 
</div>
<div id="address" class="weui-popup__container">
	<div class="weui-popup__overlay"></div>
	<div class="weui-popup__modal">
		<p class="hei-6">&nbsp;</p>
		<form id="addressForm" onsubmit="return false">
			<input type="hidden" name="xgh" value="${xgh }">
			<input type="hidden" name="id" value="">
			<div class="weui-cell line-d1e bg-white ft14">
				<div class="weui-cell__hd ft-bold600" style="width: 60px">收货人</div>
				<div class="weui-cell__bd">
					<p><input class="weui-input" name="addrConsignee" type="text" placeholder="姓名"></p>
				</div>
			</div>
			<div class="weui-cell line-d1e bg-white ft14">
				<div class="weui-cell__hd ft-bold600" style="width: 60px">电话</div>
				<div class="weui-cell__bd">
					<p><input class="weui-input" name="addrContact" type="tel" placeholder="手机号码"></p>
				</div>
			</div>
			<div class="weui-cell line-d1e bg-white ft14">
				<div class="weui-cell__hd ft-bold600" style="width: 60px">所在地区</div>
				<div class="weui-cell__bd">
					<p><input class="weui-input" name="addrArea" type="text" placeholder="所在地区"></p>
				</div>
			</div>
			<div class="weui-cell line-d1e bg-white ft14">
				<div class="weui-cell__hd ft-bold600" style="width: 60px">详细地址</div>
				<div class="weui-cell__bd">
					<p><input class="weui-input" name="addrDetail" type="text" placeholder="详细地址"></p>
				</div>
			</div>
			<div class="weui-cell line-d1e bg-white ft14">
				<div class="weui-cell__hd ft-bold600" style="width: 60px">地址标签</div>
				<div class="weui-cell__bd">
					<p><input class="weui-input" name="addrLabel" type="text" placeholder="地址标签"></p>
				</div>
			</div>
			<div class="weui-cell weui-cell_switch bg-white">
				<input type="hidden" id="addrDefault" name="addrDefault" value="0">
			    <div class="weui-cell__bd">设置默认地址</div>
			    <div class="weui-cell__ft">
			    	<label for="switchCP" class="weui-switch-cp">
			        	<input id="switchCP" onchange="checkDefault()" class="weui-switch-cp__input" type="checkbox">
			      		<div class="weui-switch-cp__box"></div>
			    	</label>
		    	</div>
			</div>
		</form>
		<p>&nbsp;</p>
		<div class="weui-msg__opr-area">
			<p class="weui-btn-area" style="margin-top: 0;">
				<a href="javascript:void(0);" class="weui-btn bg-yellow ft-black" onclick="saveAddress()">提&nbsp;&nbsp;交</a>
				<a href="javascript:void(0);" class="weui-btn weui-btn_primary close-popup" style="background-color: #999;" >关&nbsp;&nbsp;闭</a>
			</p>
		</div>
	</div>
</div>
<!--/tabbar-->
</body>
</html>
