<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>流程管理</title>
<link  rel="stylesheet"  type="text/css"   href="<%=basePath %>/wflibs/bootstrap/css/bootstrap.css"/>
<link  rel="stylesheet" type="text/css"  href="<%=basePath %>/wflibs/css/flow.css"/>
<link  rel="stylesheet" type="text/css"   href="<%=basePath %>/wflibs/plugins/font-awesome/css/font-awesome.css"/>
<script src="<%=basePath %>/wflibs/plugins/jquery.min.js"></script>
<script src="<%=basePath %>/wflibs/bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet"   href="<%=basePath %>/wflibs/plugins/datatables/css/jquery.dataTables.css"/>
<script type="text/javascript" src="<%=basePath %>/wflibs/plugins/datatables/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=basePath %>/wflibs/plugins/layer/layer.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/wflibs/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script src="<%=basePath %>wflibs/plugins/jquery.slimscroll.js"></script>
<script src="<%=basePath %>wflibs/plugins/jquery.lightbox_me.js"></script>
</head>
<body>
<div class="toolbar"> <a class="btn btn-info" href="<%=basePath%>modeler.jsp" target="_blank"><i class="fa fa-plus"></i>新增流程</a> <a class="btn btn-warning"><i class='fa  fa-trash-o'></i>删除</a> </div>
<table id="flowtable" class="table table-striped table-hover table-bordered">
  <thead>
    <tr>
     <th></th>
      <th>ID</th>
      <th>创建者</th>
      <th>流程名称</th>
      <th>流程ID</th>
      <th>版本号</th>
      <th>描述</th>
      <th>更新时间</th>
      <th>操作</th>
    </tr>
  </thead>
</table>
<!-- 查看历史版本  包括版本号，修改时间，操作（修改）-->

  <div id="vdg"  class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">历史版本</h4>
      </div>
      <div class="modal-body">
        <table id="vtable" class="table table-striped table-hover">
          <thead>
            <tr>
              <th>版本</th>
              <th>修改时间</th>
              <th>操作</th>
            </tr>
          </thead>
        </table>
      </div>
    </div>
    <!-- /.modal-content --> 
  </div>
  <!-- /.modal-dialog --> 

</body>
<script>
 $(function(){
	 
	 $('#flowtable').dataTable( {
		 "processing": true,
		 "serverSide": true,
		"aaSorting": [[ 1, "desc" ]],
	    "ajax": {
	    	url:"process_list.do",
	    	type:"post",
	    },
		"bSearchable": false,
		"sDom": '<"top">rt<"bottom"flpi><"clear">',
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
		 	{  "sTitle" : "<input type='checkbox' class='group-checkable' />",
               "bSortable":false,
			   "mRender": function(data, type, full) {
					return "<input type='checkbox' name='chkItem' class='checkboxes' />";
				}				  
			},
		    { "data": "id" },
		    { "data": "username" },
			{ "data": "name" },
			{ "data": "processId" },
			{ "data": "version" },	
			{ "data": "description","bSortable":false},	
			{ "data": "lastUpdateTime",
				mRender:function(data){
					return moment(data).format('YYYY-MM-DD HH:mm:ss');
				}
			},
			{ "bSortable":false,
			"mRender": function(data, type, full) {
				return "<a  class='btn btn-default btn-xs' target='_blank' href='run.do?id="+full.processId+"'><i class='fa fa-play'></i>执行</a><button  class='btn btn-default btn-xs' onclick='preview("+full.id+")'><i class='fa fa-search'></i>查看</button><button  class='btn btn-default btn-xs' onclick='edit("+full.id+")'><i class='fa fa-edit'></i>修改</button><button  class='btn btn-default  btn-xs' onclick='delitem(\"flowtable\","+full.id+")'><i class='fa  fa-trash-o'></i>删除</button><button  class='btn btn-default  btn-xs' onclick='showv("+full.id+")'><i class='fa fa-angle-double-right'></i>历史版本</button>";
			}},	
		]	
	} ); 
	 
		//弹出表格
	$('#vtable').dataTable( {
			"aaSorting": [[ 0, "desc" ]],
			"bSearchable": false,
			"sDom": '<"top">rt<"bottom"flpi><"clear">',
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
				{ "data": "version" },
				{ "data": "lastUpdateTime",
					mRender:function(data){
						return moment(data).format('YYYY-MM-DD HH:mm:ss');
					}
				},
				{ "bSortable":false,
				"mRender": function(data, type, full) {
					return "<button  class='btn btn-default btn-xs' onclick='preview("+full.id+")'><i class='fa fa-search'></i>查看</button><button  class='btn btn-default btn-xs' onclick='edit("+full.id+")'><i class='fa fa-edit'></i>修改</button><button  class='btn btn-default  btn-xs' onclick='delitem(\"vtable\","+full.id+")'><i class='fa  fa-trash-o'></i>删除</button>";
				}},	
			]	
		} );  

});
 
//弹出历史版本对话框
function showv(id){
	var table = $('#vtable').DataTable();
	table.settings()[0].ajax={dataSrc:""};
	
	table.ajax.url( 'history.do?id='+id ).load();
	
	$('#vdg').lightbox_me({
       centered: true, 
	});
}

function preview(id){
	$.layer({
        type : 2,
        title: '流程预览',
        shadeClose: true,
        maxmin: true,
        fix : false,
        area: ['1024px', 600],                     
        iframe: {
            src : 'preview.do?id='+id
        } 
    });
}

function edit(id){
	window.open('<%=basePath%>modeler.jsp?modelId='+id,'_blank');
}

function delitem(tableId,id){
	
	layer.confirm('确定删除吗？', function(){
		$.ajax({
    		url:"del.do",
    		data:{id:id},
    		dataType:"json",
    		success:function(data){
    			$('#'+tableId).DataTable().ajax.reload();
    			layer.closeAll();
    		}
    	});
		
	});

}

$(function(){
	$(".group-checkable").click(function(){
		$(".checkboxes").prop("checked",$(this).prop("checked"));
	});
	
	$("#delSelected").click(function(){
		layer.confirm('确定删除吗？', function(){
			
			var id = new Array();
			$(".checkboxes").each(function(){
				if($(this).prop("checked")){
					id.push($(this).val());
				}
			});
			
			$.ajax({
	    		url:"del.do",
	    		data:{id:id.join(",")},
	    		dataType:"json",
	    		success:function(data){
	    			$("#table").DataTable().ajax.reload();
	    			layer.closeAll();
	    		}
	    	});
			
		});
		
	});
});

</script>
</html>
