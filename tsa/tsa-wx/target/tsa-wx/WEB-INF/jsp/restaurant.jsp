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
    <title>${storeInfo.storeName}</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var productsId;
		var storeId="${storeInfo.id}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">
	<script src="${ctx }/lib/jquery-2.1.4.js"></script>
  </head>
<body ontouchstart class="bg-grey-eee">
<!--餐厅title-->
	<div class="bg-black restaurant pad-10 weui-media-box weui-media-box_appmsg">
	  <div class="weui-media-box__hd"><img class="weui-media-box__thumb" src="fileoper/downFile.action?filepath=${storeInfo.logo}" onerror="javascript:this.src='images/nopic.jpg';"></div>
	  <div class="weui-media-box__bd ft14 ft-white mar-r10">
	  	<input type="hidden" id="storeId" value="${storeInfo.id}">
		<p>起送 ¥<span id="initiatePrice">${storeInfo.initiatePrice}</span> | 配送 ¥<span id="dphPrice">${storeInfo.dphPrice}</span></p>
		<p class="weui-media-box__desc mar-t10">公告：${storeInfo.notice}</p>
	  </div>
	  <div class="pull-right tag-no">
	  	  <span class="count ft-no-bold" id="totalCount"></span>
		  <button type="button" class="btn-shopping bg-ordering" onclick="jumpPage('diet/goTrolley.action')"><img src="images/icon-shopping.png" /></button>
	  </div>
	</div>
<!--/餐厅title-->
<div class="weui-tab" style="height:auto;">
	
	<div class="weui-navbar ft18 ft-bold400 ordering-tab">
		<a class="weui-navbar__item weui-bar__item--on" id="anchor1" href="javascript:void(0)" onclick="anchor(1)">
		  点菜
		</a>
		<a class="weui-navbar__item" href="javascript:void(0)" id="anchor2" onclick="anchor(2)">
		  评价
		</a>
		<a class="weui-navbar__item" href="javascript:void(0)" id="anchor3" onclick="anchor(3)">
		  介绍
		</a>
	</div>
	<div class="weui-tab__bd">		
	  	<!--tab con-01-->
		<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active">
		  	<p class="hei-6">&nbsp;</p>
		  	<div class="pull-left order-menu ft14" id="left-box">
	  			<c:forEach items="${productsInfos}" var="p" varStatus="i">
		  			<c:if test="${i.index==0 }">
		  				<li class="on" onclick="getFoods(${p.id},this)">${p.productsName}</li>
		  				<script>
		  					productsId=${p.id};
		  				</script>
		  			</c:if>
		  			<c:if test="${i.index>0 }">
		  				<li onclick="getFoods(${p.id},this)">${p.productsName}</li>
		  			</c:if>
	  			</c:forEach>
	  		</div>
	  		<!--list-box-01-->
		  	<div class="pull-right order-list bg-white" id="right-box" style="min-height:100%;">
		  		<div class="pad-0-15" id="dataList"></div>
			</div>
			<!--temp-->
			<p class="clearfix"></p>
			<div style="height:68px;">&nbsp;</div>
			<!--tabbar shopping-cart-->
			<div class="weui-tabbar shopping-cart">
				<div class="order-list ft12" style="display: none;" id="cart">
					<div class="title ft-grey-999">
						<span class="pull-left buy-title ft-bold400 ft-grey-999">已选菜品</span>
						<span class="pull-right dishes"><a href="javascript:void(0);" class="clear-cart" onclick="emptyTrolley()"><i></i>清空菜品</a></span>
					</div>
					<div id="trolleyList"></div>
					<%-- <div class="other-charge ft-grey-666">
						<div class="clearfix delivery-cost">
						  <span class="pull-left">配送费(不计入起送价)</span>
						  <span class="pull-right shippingfee text-number ft18">¥<span id="dphPrice">${storeInfo.dphPrice}</span></span>
						</div>
					</div> --%>
					<div class="privilege hidden"></div>
					<div class="total">共<span class="totalNumber" id="totalNumber"></span>份，共计&nbsp;<span class="bill pull-right">¥<span id="totalMoney"></span></span></div>
					<!--temp-->
					<div style="height:68px;">&nbsp;</div>
				</div>
				<div class="footer">
					<div class="pull-left">
						<div class="btn-shopping-up" onclick="changeCart()"></div>
						<div class="logo"><i></i></div>
						<div class="brief-order text-number" style="display: inline-block">
							<span class="count" id="totalnumber"></span>
							<div class="price">
								<p><i>¥</i><span id="totalmoney"></span></p>
								<p class="ft12 ft-grey-b5">另需配送费 ¥<span>${storeInfo.dphPrice}</span>.00</p>
							</div>
						</div>
					</div>
					<script>
						$(function(){
							$(".pull-left").click(function(e){
								/*切换折叠后摘要*/
								$(this).find(".icon-down").toggle();
							});
						});
					</script>
					<div class="pull-right bg-grey-f29 ft18" >
						<a class="ready-pay"  href="javascript:void(0);" style="display: none;" id="balance">差¥<span></span>元起送</a>
						<button class="go-pay" type="button" id="submit" onclick="createOrder()" style="display: inline-block;color: #333;background-color: #ffaa00;">立即下单</button>
					</div>
				</div>

			</div>
			<!--/tabbar-->
		</div>
		<!--tab con-02-->
		<div id="tab2" class="weui-tab__bd-item">
			<div class="weui-cells mar-t6">
				<div class="bg-white">
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd text-center">
							<p class="ft30 ft-yellow">${storeInfo.storeScore }</p>
							<p class="ft12 ft-grey-999">综合评分</p>
						</div>
						<div class="weui-cell__bd ft12 ft-grey-999 mar-l20">
							<div class="icon-score mar-t6">
								<span class="pull-left mar-t6 mar-r10">菜品</span>
								<div>
									<i style="width:${storeInfo.dishesScore*16 }px;"></i>
								</div>
								<span class="txt">${storeInfo.dishesScore }</span>
							</div>
							<p class="clearfix"></p>
							<div class="icon-score mar-t6">
								<span class="pull-left mar-t6 mar-r10">配送</span>
								<div>
									<i style="width:${storeInfo.dphScore*16 }px;"></i>
								</div>
								<span class="txt">${storeInfo.dphScore }</span>
							</div>
						</div>
						<!-- <div class="weui-cell__ft ft-grey-999 text-center">
							<p class="ft24">4.7</p>
							<p class="ft12">商家评分</p>
						</div> -->
					</div>
				</div>
				<div class="bg-white mar-t6">
					<!--tag-->
					<div class="weui-cell line-d1e pad-0 bg-white zindex20">
						<div class="weui-cell__bd ft12 btn-tag" id="conditionDiv">
						  <a href="javascript:void(0)" class="on" onclick="changeCondition(this,0)" id="quanbu"></a>
						  <a href="javascript:void(0)" onclick="changeCondition(this,1)" id="manyi"></a>
						  <a href="javascript:void(0)" onclick="changeCondition(this,2)" id="bumanyi"></a>
						  <a href="javascript:void(0)" onclick="changeCondition(this,3)" id="youtu"></a>
						</div>
					</div>
					<div id="commentsList">
						
					</div>
				</div>
			</div>
		</div>
		<!--tab con-03-->
		<div id="tab3" class="weui-tab__bd-item">
			<div class="weui-cells mar-t6 ft12">
				<div class="bg-white">
					<div class="weui-cell">
						<div>
							<p class="ft-bold600">商家介绍</p>
							<div class="icon-score mar-t6">
								<div>
									<i style="width:${storeInfo.storeScore*16}px;"></i>
								</div>
								 <span class="txt ft-grey-666 ft14">${storeInfo.storeScore}&nbsp;月售${storeInfo.sales}单</span>
							</div>
						</div>
					</div>
					<div class="weui-cell" style="padding-top: 0">
						${storeInfo.storeIntro}
					</div>
					<div class="weui-cell line-d1e" style="padding-top: 0" id="introPicContainer">
					<c:forEach items="${storeInfo.list }" var="pic" varStatus="i">
						<div class="weui-cell__hd" id="introPicDiv${i.index+1 }" onclick="showIntroPic(${i.index+1 })">
							<img src="fileoper/downFile.action?filepath=${pic.filePath}" width="78" height="78" alt=""/> 
							<input type="hidden" id="introPic${i.index+1}" value="${pic.filePath}" name="introPic">
						</div>
					</c:forEach>
						<!-- <div class="weui-cell__hd">
							<img src="images/temp-04.jpg" width="78" height="78" alt=""/> 
						</div>
						<div class="weui-cell__hd">
							<img src="images/temp-04.jpg" width="78" height="78" alt=""/>
						</div> -->
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd"><img src="images/icon-ordering02.png" width="20" height="20" alt=""/></div>
						<div class="weui-cell__bd">地址：${storeInfo.address}</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd"><img src="images/icon-ordering03.png" width="20" height="20" alt=""/></div>
						<div class="weui-cell__bd">订餐电话：${storeInfo.orderPhone}</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd"><img src="images/icon-ordering01.png" width="20" height="20" alt=""/></div>
						<div class="weui-cell__bd">配送服务：${storeInfo.dispatching}</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd"><img src="images/icon-ordering04.png" width="20" height="20" alt=""/></div>
						<div class="weui-cell__bd">配送时间：${storeInfo.dphTime}</div>
					</div>
				</div>
				<div class="bg-white mar-t6" onclick="showPermitPhoto()">
					<div class="weui-cell">
						<div class="weui-cell__hd"><img src="images/icon-ordering05.png" width="20" height="20" alt=""/></div>
						<div class="weui-cell__bd">
						  <a href="" class="ft-grey-666 ft-bold600">餐厅资质</a>
						  <input type="hidden" id="permitPic" value="${storeInfo.permit}">
						  <input type="hidden" id="licensePic" value="${storeInfo.license}">
						</div>
						<div class="weui-cell__ft"><img src="images/icon05.png" width="10" height="18" alt=""/></div>
					</div>
				</div>
				
			</div>
		</div>
	</div>
</div>

<script src="${ctx }/lib/fastclick.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="${ctx }/js/jquery-weui.js"></script>
<script src="${ctx }/js/swiper.js"></script>
<script type="text/javascript" src="${ctx}/js/Utils.js"></script>
<script type="text/javascript">
	//Div高度一致left-right
	/* $(function(){
		if
			($("#left-box").height() > $("#right-box").height())
		{
			$("#right-box").css("height",$("#left-box").height()) 
	  	}
		else
		{
			$("#left-box").css("height",$("#right-box").height()) 
		}
	}) */
</script>
<script src="${ctx }/js/wxCommon.js"></script>
<script src="${ctx }/js/restaurant.js"></script>
<script src="${ctx }/js/trolley.js"></script>
</body>
</html>
