<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>功能能列表</title>
    <link href="plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet" media="screen">
    <style type="text/css">
    .icon-arrow-right{
    display: inline-block;
    width: 14px;
    height: 14px;
    margin-top: 1px;
    line-height: 14px;
    vertical-align: text-top;
    background-image: url("${pageContext.request.contextPath}/images/glyphicons-halflings.png");
    background-position: -264px -96px;
    background-repeat: no-repeat;
}
.icon-arrow-left {
    display: inline-block;
    width: 14px;
    height: 14px;
    margin-top: 1px;
    line-height: 14px;
    vertical-align: text-top;
    background-image: url("${pageContext.request.contextPath}/images/glyphicons-halflings.png");
    background-position: -240px -96px;
    background-repeat: no-repeat;
}
    </style>
  </head>
  <body> 
       <div class=" date form_month" data-date="" data-date-format="yyyy" data-link-field="attendance_month" data-link-format="yyyy">
							    <div class="column col-sm-6">
				                    <input id="attendance_month_con" size="16" type="text" class="form-control" value="" style="width:120px;" placeholder="请选择日期" readonly>
				                    <span class="add-on"><i class="icon-remove"></i></span>
									<span class="add-on"><i class="icon-th"></i></span>
				                </div>
								</div>
								  <input type="hidden" id="attendance_month" name="paramTime" value=""/>
								  
  </body>
  
<script type="text/javascript" src="plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
	$(function(){
	 	var dataStr = new Date().format("yyyy");
		$("#attendance_month").val(dataStr);
		$("#attendance_month_con").val(dataStr);
	});
	
	$('.form_month').datetimepicker({
        language:  'zh-CN',
       //weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 4,
		minView: 4,
		forceParse: 0
    });
</script>
  
</html>

