<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>欢迎首页</title>
	<script type="text/javascript" src="plugins/bootstrap-daterangepicker/moment.min.js"></script>
	<script type="text/javascript" src="js/welcome.js"></script>
</head>
<body>
	<div class="row">
		<!--功能使用统计  -->
		<div class="col-sm-12">
			<div class="portlet portlet-default">
				<div class="portlet-title">
			    	<div class="caption"><i class="fa fa-group"></i>功能使用统计</div>
			    </div>
			    <div class="portlet-body">
			    	<div id="line" style="height:400px;"></div>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>
