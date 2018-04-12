<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>详细功能日志统计</title>
	<script type="text/javascript" src="js/common/common.tableTree.js"></script>
	<script type="text/javascript" src="js/common/common.tableFunction.js"></script>
	<link rel="stylesheet" type="text/css" href="plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
	<script type="text/javascript" src="plugins/bootstrap-daterangepicker/moment.min.js"></script>
	<script type="text/javascript" src="plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript" src="js/statis/func_use_list.js"></script>
</head>
<body>
 	<div class="row">
    	<form class="form-inline" id="conditionForm" action="sysLogFunc/exportCount.action" method="post">
         	<div class="col-md-12">
	      	 	<div class="form-group">
		            <label class="sr-only" for="time">时间</label>    
		            <div id="reportrange" class="daterange">
		             	<i class="glyphicon glyphicon-calendar icon-calendar icon-large"></i>
		                <span></span><b class="caret"></b>
		           	</div>
	          	</div>
          		<input type="text" name="stime" id="startTime" hidden="hidden">    
          		<input type="text" name="etime" id="endTime" hidden="hidden">    
          		<input type="text" name="orgId" id="orgIdHidden" hidden="hidden">    
       			<select class="form-control selectpicker" data-width="120px" id="orgId" name="orgName">
	       			<option value="">--全部--</option>
					<c:forEach items="${lists}" var="item">
					<option value="${item.orgCode}">${item.orgName}</option>
					</c:forEach>
				</select>
				<div class="form-group">
					<input type="text" class="form-control" id="functionName" name="functionName" style="width:120px;margin-left: 5px;float:left;" placeholder="请输入功能名称" check-type="required" maxlength="100">
				</div>
	        	<a id="search" class="btn btn-primary" onclick="queryCount();"><i class="fa fa-search"></i>搜索</a>
	        	<a id="exportCount" class="btn btn-primary" onclick="exportCount();"><i class="fa fa-sign-out"></i>导出</a>
        	</div>
    		<div class="col-md-12">
			    <input type="hidden" id="btime" value="${startTime}">
			    <input type="hidden" id="etime" value="${endTime}">
			    <input type="hidden" id="orgname" value="${area}">
			    <div class="form-group">
		        	<ul class="nav nav-pills pull-right">
						<li id="thicon" data-toggle="tooltip" title="统计图" class="end" onclick="goBackToMain();"><a href="#map" data-toggle="tab"><i class="fa fa-line-chart"></i></a></li>
						<li id="listicon" data-toggle="tooltip" title="列表"  class="start active" onclick="javascript:void(0);"><a href="#list" data-toggle="tab"><i class="fa fa-list-ul"></i></a></li>
				 	</ul>
		        </div>
    		</div>
		</form>
 		<div class="col-sm-12">
  			<table id="tableTree" class="table table-tree table-hover operation ">
	      		<thead>
	        		<tr>
	          			<th>地市</th>
	          			<th>功能名称</th>
	          			<th>次数 </th>
	        		</tr>
	      		</thead>
    		</table>
  		</div>
	</div>
</body>
</html>
