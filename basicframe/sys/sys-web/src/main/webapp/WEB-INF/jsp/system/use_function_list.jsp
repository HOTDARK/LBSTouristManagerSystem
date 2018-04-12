<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title></title>
	<!-- Custom styles for this template -->	
	<link rel="stylesheet" href="plugins/ztree/css/zTreeStyle/zTreeStyle2.css" />
	<script src="plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script src="js/common/common.tree.js"></script>
</head>
<body>
      <div>
           <div class="t_top" style="background:#edf3f7;">
           	<div class="row-fluid">
		</div>            
            </div>
          <div class="table table-striped  table-hover"  style=" float:left;padding-left:1px;" id="dataView">
	            
 		</div>	
    </div>
   <div class="ace-settings-container" id="ace-settings-container">
	
	</div></div>
    <script type="text/javascript">
    $(function(){
    	loadPage("sysLogFunc/browse.action?type=2","system/log_table.jsp",null,"dataView");
    });
	function userListSearch () {
	/**获取页面参数 start**/
	var searchData = {};
		searchData.classFunction = $("#classFunction").val();
		if($.trim($("#classFunction").val())){
			searchData.classFunction = $.trim($("#classFunction").val());
		}
		loadPage("sysLogFunc/browse.action?type=2","system/log_table.jsp",searchData,"dataView");
	}
    </script>
</body>
</html>
