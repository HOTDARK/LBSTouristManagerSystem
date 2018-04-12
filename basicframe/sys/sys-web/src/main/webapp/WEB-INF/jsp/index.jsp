<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title><spring:message code="platform.index.title"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file="header.jsp"%>
	<script type="text/javascript" src="js/index.js"></script>
</head>
<body class="page-header-fixed" onbeforeunload="onbeforeunload();"> 
	<!--导航区域-->
	<div class="header navbar navbar-inverse navbar-fixed-top" id="navbar">
		<div class="header-inner" id="navbar-container">
        	<a class="navbar-brand"><img src="images/logo.png" alt="logo"></a> 
            <a href="javascript:;" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <img src="plugins/assets/img/menu-toggler.png" alt="" /> </a> 
			<ul class="nav navbar-nav pull-right" role="navigation">
		    	<li class="dropdown user">
		    		<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
		    			<img class="userimg img-circle" alt="" src="${sessionMap.userInfo.userPhoto}" />
		    			<span class="username">${sessionMap.userInfo.userName}</span><i class="fa fa-angle-down"></i>
		    		</a>
			        <ul class="dropdown-menu dropdown-menu-right">
			          	<li><a href="javascript:;" onclick="editPwd();" title="<spring:message code="platform.index.modify.pwd"/>"><i class="fa fa-key"></i>&nbsp;<spring:message code="platform.index.modify.pwd"/></a></li><li class="divider"></li>
			          	<li><a onclick="loginOut()"><i class="fa fa-external-link"></i>&nbsp;<spring:message code="platform.index.logout"/></a></li>
			        </ul>
		      	</li>
		    </ul>
			<input type="hidden" id="isNeedLogout" value="need">
   			<input type="hidden" id="loginUserId" value="${sessionMap.userInfo.userId}">
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="page-container" id="main-container">
		<!--左侧菜单-->
		<div class="page-sidebar collapse navbar-collapse" id="sidebar">
		 	<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
	        <div class="sidebar-toggler hidden-phone"></div>
	        <!-- BEGIN SIDEBAR TOGGLER BUTTON --> 
			<ul class="page-sidebar-menu" id="my_menu"></ul>
		</div>
	    <div class="theme-panel hidden-xs hidden-sm">
	    	<div class="toggler" title="<spring:message code="platform.index.skin.change"/>"></div>
	      	<div class="toggler-close"></div>
	      	<div class="theme-options">
		        <div class="theme-option theme-colors clearfix"><span><spring:message code="platform.index.skin.color"/></span>
		        	<ul>
			            <li class="color-black current color-default" data-style="default"></li>
			            <li class="color-blue" data-style="blue"></li>
			            <li class="color-brown" data-style="brown"></li>
			            <li class="color-purple" data-style="purple"></li>
			            <li class="color-grey" data-style="grey"></li>
			            <li class="color-white color-light" data-style="light"></li>
		          	</ul>
		        </div>
		        <div class="theme-option"><span><spring:message code="platform.index.skin.layout"/></span>
		          <select class="layout-option form-control input-small">
		            <option value="fluid" selected="selected"><spring:message code="platform.index.skin.auto"/></option>
		            <option value="boxed"><spring:message code="platform.index.skin.fixed.width"/></option>
		          </select>
		        </div>
		        <div class="theme-option"><span><spring:message code="platform.index.skin.head"/></span>
		        	<select class="header-option form-control input-small">
		            	<option value="fixed" selected="selected"><spring:message code="platform.index.skin.fixed"/></option>
		            	<option value="default"><spring:message code="platform.index.skin.default"/></option>
		          	</select>
		       	</div>
		        <div class="theme-option"><span><spring:message code="platform.index.skin.menu"/></span>
		        	<select class="sidebar-option form-control input-small">
		            	<option value="fixed"><spring:message code="platform.index.skin.fixed"/></option>
		            	<option value="default" selected="selected"><spring:message code="platform.index.skin.default"/></option>
		       		</select>
		        </div>
		        <div class="theme-option"><span><spring:message code="platform.index.skin.bottom"/></span>
		          	<select class="footer-option form-control input-small">
		            	<option value="fixed"><spring:message code="platform.index.skin.fixed"/></option>
		            	<option value="default" selected="selected"><spring:message code="platform.index.skin.default"/></option>
		          	</select>
		        </div>
	      	</div>
	    </div>
	    <!-- END BEGIN STYLE CUSTOMIZER --> 
	    <div class="page-breadcrumb breadcrumbs" id="breadcrumbs">
			<ul class="breadcrumb" id="my_nav"></ul>
	  	</div>
	  	<!-- BEGIN PAGE DESC-->
		<div class="page-content" id="contentHTML"></div>
		<input type="hidden" id="checkedFuncId">
	</div>
	<div id="modifyPwd"></div>
</body>
</html>