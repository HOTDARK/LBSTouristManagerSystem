<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.0.2
Version: 1.5.4
Author: KeenThemes
Website: http://www.keenthemes.com/
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title>404错误</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta name="MobileOptimized" content="320">
<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="css/common/error.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/common/main.css">
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-404-full-page">
	<div class="row">
		<div class="col-md-12 page-404">
			<div class="number">404</div>
			<div class="details">
				<h1>出错啦！无法找到该页!</h1>
				<p>您正在访问的页面可能已经删除、更名或暂时不可用。 请确认网址拼写是否正确或稍后尝试再次刷新。</p>
				<p>如果您无法载入任何页面，请检查您计算机的网络连接。</p>
				<p>如有问题,请联系网管，谢谢！</p>
			<!-- 	<p>
					<a class="back-btn" href="javascript:history.go(-1)">返回</a>
				</p> -->
			</div>
		</div>
	</div>
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
	<script src="assets/plugins/respond.min.js"></script>
	<script src="assets/plugins/excanvas.min.js"></script> 
	<![endif]-->
	<!-- END CORE PLUGINS -->
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>