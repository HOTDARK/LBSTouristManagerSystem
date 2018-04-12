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
    <title>运驾服务</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	<script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
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
	<!-- <div class="weui-navbar ft18 ft-bold400 ordering-tab">
		<a class="weui-navbar__item weui-bar__item--on" href="javascript:;">
		  运驾服务
		</a>
	</div> -->
	<div class="weui-tab__bd">
		<div id="tab1" class="weui-tab__bd-item  weui-tab__bd-item--active">
			<!--默认 捆绑工单-->
			<div id="sourceL" style="display: none">
				<div class="weui-cells bg-white mar-t6 ">
					<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
						<span class="line-mid">&nbsp;&nbsp;</span> 
						<span class="ft-bold400">工单绑定</span>
						<span class="line-mid" >&nbsp;&nbsp;</span>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd"><img src="images/icon-order01a.png" width="24" height="26" alt=""/></div>
						<div class="weui-cell__bd">
						  <p><input class="weui-input" type="text" placeholder="请输入工号" id='backAccount'></p>
						</div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd"><img src="images/icon-order02a.png" width="24" height="26" alt=""/></div>
						<div class="weui-cell__bd">
						  <p><input class="weui-input" type="tel" placeholder="手机号" id='bindUserPhone' readonly></p>
						</div>
						<div class="weui-cell__ft"><a type="button" href="javascript:void(0)" class="btn-code bg-orange ft14" value="获取验证码" onclick='getValiCode(this)'>获取验证码</a></div>
					</div>
					<div class="weui-cell line-d1e">
						<div class="weui-cell__hd"><img src="images/icon-order03a.png" width="24" height="26" alt=""/></div>
						<div class="weui-cell__bd">
						  <p><input class="weui-input" type="tel" placeholder="手机验证码" id='phoneCode'></p>
						</div>
					</div>
				</div>
				<!--btn-ok-->
				<div class="demos-content-padded">
					<a href="javascript:;" class="weui-btn bg-orange ft-black" onclick='bindPhone(this)'> 立 即 绑 定</a>
				</div>
			</div>
			<!--捆绑成功-->
			<div class="weui-cells mar-t6" id="sourceK"  style="display: none">
				
			</div>
		</div>
	</div>
</div>


<script src="lib/jquery-2.1.4.js"></script>
<script src="lib/fastclick.js"></script>
<script src="js/jquery-weui.js"></script>
<script src="js/swiper.js"></script>
<script src="js/Utils.js"></script>
<script src="js/wxCommon.js"></script>
<script src="js/vehicleManager.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
  function htmlFontSize(){
	    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
	    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
	    var width = w > h ? h : w;
	    width = width > 720 ? 720 : width
	    var fz = ~~(width*100000/36)/10000
	    document.getElementsByTagName("html")[0].style.cssText = 'font-size: ' + fz +"px";
	    var realfz = ~~(+window.getComputedStyle(document.getElementsByTagName("html")[0]).fontSize.replace('px','')*10000)/10000
	    if (fz !== realfz) {
	        document.getElementsByTagName("html")[0].style.cssText = 'font-size: ' + fz * (fz / realfz) +"px";
	    }
	}
</script>

</body>
</html>
