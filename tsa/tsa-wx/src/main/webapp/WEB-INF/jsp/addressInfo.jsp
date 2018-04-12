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
    <title>编辑收货地址</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var xgh="${user.xgh}";
		var type="${type}";
		var addrId="${entity.id}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">
  </head>
<body ontouchstart class="bg-grey-eee">
<p class="hei-6">&nbsp;</p>
<div class="weui-cell line-d1e bg-white ft14">
	<div class="weui-cell__hd ft-bold600" style="width: 70px">收货人</div>
	<div class="weui-cell__bd">
		<p><input class="weui-input" type="text" id="addrConsignee" placeholder="姓名" value="${entity.addrConsignee }"></p>
	</div>
</div>

<div class="weui-cell line-d1e bg-white ft14">
	<div class="weui-cell__hd ft-bold600" style="width: 70px">电话</div>
	<div class="weui-cell__bd">
		<p><input class="weui-input" type="tel" id="addrContact" placeholder="手机号码" value="${entity.addrContact }"></p>
	</div>
	
</div>
<div class="weui-cell line-d1e bg-white ft14">
	<div class="weui-cell__hd ft-bold600" style="width: 70px">所在地区</div>
	<div class="weui-cell__bd">
		<p><input class="weui-input" type="text" id="addrArea" placeholder="所在地区" value="${entity.addrArea }"></p>
	</div>
	
</div>
<div class="weui-cell line-d1e bg-white ft14">
	<div class="weui-cell__hd ft-bold600" style="width: 70px">详细地址</div>
	<div class="weui-cell__bd">
		<p><input class="weui-input" type="text" id="addrDetail" placeholder="详细地址" value="${entity.addrDetail }"></p>
	</div>
</div>
<div class="weui-cell line-d1e bg-white ft14">
	<div class="weui-cell__hd ft-bold600" style="width: 70px">是否默认</div>
	<div class="weui-cell__bd">
	</div>
	<div class="weui-cell__ft">
		<%-- <p><input class="weui-check" type="radio" name="isDefault" <c:if test="${entity.addrDefault==1 }">checked</c:if> >是
			<input class="weui-check" type="radio" name="isDefault" <c:if test="${entity.addrDefault==0 }">checked</c:if>>否
		</p> --%>
          <label for="switchCP" class="weui-switch-cp">
            <input id="switchCP" class="weui-switch-cp__input" type="checkbox"  <c:if test="${entity.addrDefault==1 }">checked="checked"</c:if>>
            <div class="weui-switch-cp__box"></div>
          </label>
	</div>
</div>
<p>&nbsp;</p>
<div class="weui-msg__opr-area">
	<p class="weui-btn-area" style="margin-top: 0;">
	  <a href="javascript:;" class="weui-btn bg-yellow ft-black" onclick="insertOrUpdate(this)">提   交</a>
	</p>
</div>
	
<script src="lib/jquery-2.1.4.js"></script>
<script src="lib/fastclick.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="js/jquery-weui.js"></script>
<script src="js/swiper.js"></script>
<script src="js/Utils.js"></script>
<script src="js/wxCommon.js"></script>
<script src="js/address.js"></script>
</body>
</html>
