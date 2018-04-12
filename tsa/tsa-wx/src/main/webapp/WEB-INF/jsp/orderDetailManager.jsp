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
    <title>订单详情</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var backAccount="${backAccount}";
		var orderNum="${entity.orderNum}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
	<link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
    <link rel="stylesheet" href="${ctx }/css/demos2.css">
  </head>
<body ontouchstart class="bg-grey-eee">
<div class="bg-white ft14">
	<div class="bg-white weui-cell line-d1e" >
		<div class=""><span class="">订单编号：</span>${entity.orderNum}</div>
	</div>
	<!-- <div class="bg-grey-f6 weui-cell line-d1e" >
		<div class="ft-red">系统于15:46取消订单, 取消理由: 超时未支付</div>
	</div> -->
	<div class="weui-cell line-d1e bg-grey-f6 ft14">
		<div class="weui-cell__hd">商家</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right">${store.storeName}</div>
	</div>
	<c:forEach items="${list }" var="food">
		<div class="weui-cell line-d1e bg-grey-f6">
			<div class="weui-cell__hd" style="width:70%">${food.foodName}</div>
			<div class="weui-cell__bd">X${food.count}</div>
			<div class="weui-cell__ft ft-bold600 ft-black">¥ ${food.price}</div>
		</div>
	</c:forEach>
	<div class="weui-cell line-d1e ft-grey-666">
		<div class="weui-cell__bd">配送费</div>
		<div class="weui-cell__ft ft-bold600 ft-black">¥ ${store.dphPrice }</div>
	</div>
	<c:if test="${entity.payType==1 || entity.payType==2 || entity.payType==3 || entity.payType==4}">
		<div class="weui-cell line-d1e ft-grey-666 ft14">
			<div class="weui-cell__bd">&nbsp;</div>
			<div class="weui-cell__ft">买家已付款共计 <span class="ft-red ft-bold600 ft18">¥ ${entity.price}</span></div>
		</div>
		<div class="weui-cell line-d1e ft-grey-666 ft14">
			<div class="weui-cell__hd">支付方式</div>
			<div class="weui-cell__bd ft-grey-999" style="text-align: right">
				<c:choose>
					<c:when test="${entity.payType==1 }">
						支付宝
					</c:when>
					<c:when test="${entity.payType==2 }">
						微信
					</c:when>
					<c:when test="${entity.payType==3 }">
						校园卡
					</c:when>
					<c:when test="${entity.payType==4 }">
						到付
					</c:when>
				</c:choose>
			</div>
		</div>
	</c:if>
	<div class="weui-cell line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">当前状态</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right">
			<c:choose>
				<c:when test="${entity.state==0 }">
					待付款 
				</c:when>
				<c:when test="${entity.state==1 }">
					已付款
				</c:when>
				<c:when test="${entity.state==2 }">
					已接单
				</c:when>
				<c:when test="${entity.state==3 }">
					派送中
				</c:when>
				<c:when test="${entity.state==4 }">
					已完成
				</c:when>
				<c:when test="${entity.state==5 }">
					已取消
				</c:when>
			</c:choose>
		</div>
	</div>
</div>
<div class="weui-cells mar-t6 ">
	<div class="weui-cell line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd ft-bold600">配送信息</div>
		<div class="weui-cell__bd"></div>
	</div>
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">配送方式</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right"> ${entity.dispatching}</div>
	</div>
	<c:if test="${entity.state==4 && entity.reachDate!=null }">
		<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
			<div class="weui-cell__hd">送达时间</div>
			<div class="weui-cell__bd ft-grey-999" style="text-align: right"> <date:date value="${entity.reachDate}" /></div>
		</div>
	</c:if>
	
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">联系人</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right"> ${entity.receiveName }</div>
	</div>
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">联系电话</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right"> ${entity.orderPhone }</div>
	</div>
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">收货地址</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right"> ${entity.address}</div>
	</div>
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__hd">备注</div>
		<div class="weui-cell__bd ft-grey-999" style="text-align: right">${entity.remarks }</div>
	</div>
</div>
<c:if test="${entity.state==1 }">
	<div class="weui-cell bg-white line-d1e ft-grey-666 ft14">
		<div class="weui-cell__bd ft-grey-999"><a href="javascript:;" onclick="acceptOrder()" class="weui-btn weui-btn_primary">接单</a></div>
	</div>
</c:if>
<div class="weui-msg__opr-area">
	<p class="weui-btn-area">
	  <a href="javascript:;" onclick="javascript:history.back();" class="weui-btn weui-btn_default ft14"><span class="ft-orange">返回</span></a>
	</p>
</div>
<p>&nbsp;</p>
<script src="lib/jquery-2.1.4.js"></script>
<script src="lib/fastclick.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="js/jquery-weui.js"></script>
<script src="js/swiper.js"></script>
<script type="text/javascript" src="js/Utils.js"></script>
<script src="js/wxCommon.js"></script>
<script src="js/orderDetailManager.js"></script>
</body>
</html>
