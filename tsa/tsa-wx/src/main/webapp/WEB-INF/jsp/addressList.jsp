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
    <title>我的收货地址</title>
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
<%-- <c:forEach items="${addList }" var="add">
		<div class="weui-cell line-d1e bg-white ft12 weui-cell_swiped">
			<div class="weui-cell__hd" style="width: 36px">
				<c:if test="${add.addrDefault==1 }">
					<img src="images/icon-ordering06.png" width="20" height="20" alt=""/>
				</c:if>
			</div>
			<div class="weui-cell__bd">
			
				<p class="ft-bold600">${add.addrArea }${add.addrDetail }</p>
				<p class="ft-grey-999">${add.addrConsignee}&nbsp;${add.addrContact}</p>
			</div>
			<div class="weui-cell__ft">
				<img src="images/icon-edit.png" width="16" onclick="editAddr(${add.id})"/>
			</div>
			
		</div>
		
</c:forEach> --%>
<%-- <c:if test="${addList==null || fn:length(addList)==0}  ">
	<div class="weui-cell line-d1e bg-white ft12">
	
	</div>
</c:if> --%>
<c:forEach items="${addList }" var="add">
<div class="weui-cell weui-cell_swiped bg-white ft12 line-d1e" >
            <div class="weui-cell__bd">
              <div class="weui-cell">
                <div  style="width: 36px;">
                  	<c:if test="${add.addrDefault==1 }">
						<img src="images/icon-ordering06.png" width="20" height="20" alt=""/>
					</c:if>
                </div>
                <div class="weui-cell__bd">
					<p class="ft-bold600">${add.addrArea }${add.addrDetail }</p>
					<p class="ft-grey-999">${add.addrConsignee}&nbsp;${add.addrContact}</p>
				</div>
              </div>
            </div>
            <div class="weui-cell__ft">
            	<%-- <img src="images/icon-edit.png" width="16" onclick="editAddr(${add.id})"/> --%>
            	<c:if test="${add.addrDefault==0 }">
            		<a class="weui-swiped-btn bg-blue-purple delete-swipeout" href="javascript:" onclick="setDefault(${add.id})">设为默认</a>
            	</c:if>
            	<a class="weui-swiped-btn weui-swiped-btn_default close-swipeout" href="javascript:" onclick="editAddr(${add.id})">修改</a>
              	<a class="weui-swiped-btn weui-swiped-btn_warn delete-swipeout" href="javascript:" onclick="deleteAddr(${add.id})">删除</a>
            </div>
          </div>
</c:forEach>

<!--temp-->
<p class="clearfix"></p>
<div style="height:68px;">&nbsp;</div>
<!--tabbar shopping-cart-->
<div class="weui-tabbar payment-box bg-white ft-yellow">
	<button type="button" class="btn-add ft14 ft-yellow" onclick="forwardAddAddr()"><img src="images/icon-add.png" width="20" height="20" /> 新增收货地址</button>	 
</div>
<!--/tabbar-->
<script src="lib/jquery-2.1.4.js"></script>
<script src="lib/fastclick.js"></script>

<script src="js/jquery-weui.js"></script>
<script src="js/swiper.js"></script>
<script src="js/Utils.js"></script>
<script src="js/wxCommon.js"></script>
<script src="js/address.js"></script>

<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
</body>
</html>
