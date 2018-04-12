<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>功能使用统计</title>
 	<link rel="stylesheet" type="text/css" href="plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
 	<script type="text/javascript" src="plugins/bootstrap-daterangepicker/moment.min.js"></script>
 	<script type="text/javascript" src="plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript" src="js/statis/func_use_chart.js"></script>
	<script type="text/javascript">
		//加载列表数据
		function queryCount(){
			var st = $("#startTime").val();
			var et = $("#endTime").val();
			var orgid = $("#orgId").find("option:selected").text();
			getBarData({stime:st,etime:et});
		}
		function goBackToList(){
			toMenu("","sysLogFunc/toFunctionCountListPage.action","详细功能使用统计");
		}
		function chatExportCount(){
			toMenu("","sysLogFunc/toFunctionCountListPage.action","详细功能使用统计");
		}
	</script>
</head>
<body>
	<div class="row">
 		<form class="form-inline" id="conditionForm" action="sysLogFunc/chatExportCount.action" method="post">
	    	<div class="col-md-12">
		      	 <div class="form-group">
		            <label class="sr-only" for="time">时间</label>    
	               	<div id="reportrange" class="daterange">
	                   <i class="glyphicon glyphicon-calendar icon-calendar icon-large"></i>
	                   <span></span> <b class="caret"></b>
	           		</div>
	             </div>
		          <input type="text" name="stime" id="startTime" hidden="hidden">    
		          <input type="text" name="etime" id="endTime" hidden="hidden">    
		          <a id="search" class="btn btn-primary" onclick="queryCount();"><i class="fa fa-search"></i>统计</a>
		          <a class="btn btn-primary" onclick="exportCount();"><i class="fa fa-sign-out"></i>导出</a>
	     	</div>
		    <div class="col-md-12">
			      <div class="form-group">
				      <ul  class="nav nav-pills">
					     <li id="thicon" data-toggle="tooltip" title="统计图" class="start active" onclick="javascript:void(0);"><a href="#map" data-toggle="tab"><i class="fa fa-line-chart"></i></a></li>
					     <li id="listicon" data-toggle="tooltip" title="列表"  class="end" onclick="goBackToList(1)"><a href="#list" data-toggle="tab"><i class="fa fa-list-ul"></i></a></li>
				 	 </ul>
			 	 </div>
		    </div>
      	</form>
		<div class="col-md-12">
			<div id="numBar" style="height: 400px;"></div>
		</div>
  	</div>
</body>
<script>
var startDate=moment().subtract('days', 6).format('YYYY-MM-DD');
var endDate=moment().format('YYYY-MM-DD');
$(function(){ 
	 	//
	 	$('#reportrange').daterangepicker(
                     {
                        startDate: moment().subtract('days', 6),
                        endDate: moment(),
                        showDropdowns: false,
                        showWeekNumbers: true,
                        timePicker: false,
                        timePickerIncrement: 1,
                        timePicker12Hour: true,
                        ranges: {
                           '今天': [moment(), moment()],
                           '昨天': [moment().subtract('days', 1), moment().subtract('days', 1)],
                           '最近7天': [moment().subtract('days', 6), moment()],
                           '最近30天': [moment().subtract('days', 29), moment()],
                           '当月': [moment().startOf('month'), moment().endOf('month')],
                           '上月': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                        },
                        opens: 'left',
                        buttonClasses: ['btn '],
                        applyClass: 'btn-small btn-primary',
                        cancelClass: 'btn-small',
                        format: 'YYYY-MM-DD',
                        separator: ' to ',
                        locale: {
                            applyLabel: '确定',
							cancelLabel: '取消',
                            fromLabel: '从',
                            toLabel: '至',
                            customRangeLabel: '自定义时间段',
                            daysOfWeek: ['日', '一', '二', '三', '四', '五','六'],
                            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                            firstDay: 1
                        }
                     },
                     function(start, end) {
                    	startDate = start.format('YYYY-MM-DD');
                    	endDate = end.format('YYYY-MM-DD');
                    	$("#startTime").val(startDate);
                    	$("#endTime").val(endDate);
						$('#reportrange span').html(start.format('YYYY-MM-DD') + ' 至 ' + end.format('YYYY-MM-DD'));
                     }
                  );
				 
	//Set the initial state of the picker label
	$('#reportrange span').html(moment().startOf('month').format('YYYY-MM-DD') + ' 至 ' + moment().endOf('month').format('YYYY-MM-DD'));
	$("#startTime").val(moment().startOf('month').format('YYYY-MM-DD'));
	$("#endTime").val(moment().endOf('month').format('YYYY-MM-DD'));
});
</script>
</html>
