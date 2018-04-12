<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>仪表盘统计</title>
	<!-- 如果需要混用2.0、3.0，2.0的引用必须放到3.0之后 -->
	<script type="text/javascript" src="plugins/echarts/echarts-2.0/build/dist/echarts.js"></script>
	<script type="text/javascript" src="js/statis/gauge_case.js"></script>
</head>
<body>
	<div class="row">
		<div id="dashs" class="col-sm-12">
			<div id="dash" class="column col-sm-6" style="height:333px;"></div>
			<div id="dashOther" class="column col-sm-6" style="height:333px;"></div>
		</div>
		<div id="dashMore" class="col-sm-12" style="height:333px;"></div>
	</div>
</body>
</html>