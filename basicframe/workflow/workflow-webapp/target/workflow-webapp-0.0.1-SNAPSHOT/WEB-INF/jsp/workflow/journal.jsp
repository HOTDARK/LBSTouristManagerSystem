<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>日志管理</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>wflibs/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>wflibs/css/flow.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>wflibs/plugins/font-awesome/css/font-awesome.css"/>
<script src="<%=basePath %>wflibs/plugins/jquery.min.js"></script>
<script src="<%=basePath %>wflibs/bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" href="<%=basePath %>wflibs/plugins/datatables/css/jquery.dataTables.css"/>
<script type="text/javascript"  src="<%=basePath %>wflibs/plugins/datatables/js/jquery.dataTables.js"></script> 
<link href="<%=basePath %>wflibs/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"s rel="stylesheet">
<script src="<%=basePath %>wflibs/plugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="<%=basePath %>wflibs/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script> 
<script type="text/javascript" src="<%=basePath %>/wflibs/plugins/layer/layer.min.js"></script>
</head>
<body>
<!-- begin search -->
  <div class="row search-bar">
    <div class="col-md-12">
      <form class="form-inline pull-right search-bar" role="form" onsubmit="return false;">    
        <div class="form-group">
            <label class="sr-only" for="time">时间</label>    
               <div id="reportrange"   class="daterange">
                  <i class="glyphicon glyphicon-calendar icon-calendar icon-large"></i>
                  <span></span> <b class="caret"></b>
               </div>
          </div>
        <select class="form-control"  id="process_id">
        	<option value="">--请选择--</option>
        	<c:forEach items="${process }" var="item">
        		<option value="${item.processId }">${item.name }</option>
        	</c:forEach>
        </select>
        <input class="form-control" placeholder="结论" id="result">  
        <a id="search" class="btn btn-primary"><i class="fa fa-search"></i>搜索</a>
      </form>
    </div>
  </div>
  <!-- end search --> 
  <!--表格-->
   <table id="table" class="table table-striped table-hover table-bordered">
          <thead>
            <tr>
              <th>日志ID</th>
              <th>用户</th>
              <th>流程名</th>
              <th>流程ID</th>
              <th>版本</th>
              <th>开始时间</th>
              <th>结束时间</th>
              <th>总耗时(ms)</th>
              <th>结论</th>
              <th>异常</th>
              <th>操作</th>
            </tr>
          </thead>
  </table>
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
                    	 console.log(startDate);
						$('#reportrange span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
                     }
                  );
				 
	//Set the initial state of the picker label
	$('#reportrange span').html(moment().subtract('days', 6).format('YYYY-MM-DD') + ' - ' + moment().format('YYYY-MM-DD'));
	 	//表格
	 $('#table').dataTable( {
		"ordering": false,
		"processing": true,
	    "serverSide": true,
		"sDom": '<"top">rt<"bottom"flpi><"clear">',
		"bSearchable": false,	
		"ajax": {
			"url": "list.do",
			"type": "POST",
			data:function(d){
				d.startDate = startDate;
				d.endDate = endDate;
				d.result = $("#result").val();
				d.processId = $("#process_id").val();
			}
		},
		"oLanguage" : {
              "sLengthMenu": "每页显示 _MENU_ 条记录",
                "sZeroRecords": "抱歉， 没有找到",
                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty": "没有数据",
                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                "sZeroRecords": "没有检索到数据",
                "sSearch": "搜索:",
                "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
				}
              
		},
		"columns": [	
			{ "data": "id" },
			{ "data": "username" },
		    { "data": "processName" },
			{ "data": "processId" },
			{ "data": "version" },
			{ "data": "startTime",
				mRender:function(data){
					return moment(data).format('YYYY-MM-DD HH:mm:ss');
				}	
			},
			{ "data": "endTime",
				mRender:function(data){
					return moment(data).format('YYYY-MM-DD HH:mm:ss');
				}	
			},
			{ "data": "totalTime" },
			{ "data": "conclusionResult" },
			{ "data": "exception",
				mRender:function(data){
					return data?"<span class='label label-warning'>异常</span>":"<span class='label label-success'>正常</span>";
				}
			},
			{ "bSortable":false,
			"mRender": function(data, type, full) {
				return "<button  class='btn btn-default btn-xs' onclick='trace("+full.id+")'><i class='fa fa-angle-double-right'></i>执行轨迹</button><button  class='btn btn-default btn-xs' onclick='detail("+full.id+")'><i class='fa fa-angle-double-right'></i>查看详情</button>";
			}},	
		]
	} ); 
	 	
	$("#search").click(function(){
		$('#table').DataTable().ajax.reload();
	});
});
	 
function detail(id){
	$.layer({
        type : 2,
        title: '日志详情',
        shadeClose: true,
        maxmin: true,
        fix : false,
        area: ['1024px', 600],                     
        iframe: {
            src : 'detail.do?id='+id
        } 
    });
}

function trace(id){
	$.layer({
        type : 2,
        title: '执行轨迹',
        shadeClose: true,
        maxmin: true,
        fix : false,
        area: ['1024px', 600],                     
        iframe: {
            src : 'trace.do?id='+id
        } 
    });
}

</script>
</html>