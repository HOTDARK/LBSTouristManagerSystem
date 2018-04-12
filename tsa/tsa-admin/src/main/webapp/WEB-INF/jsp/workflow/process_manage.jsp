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
<link rel="stylesheet" type="text/css" href="wflibs/css/flow.css"/>
<script type="text/javascript" src="plugins/bootstrap-daterangepicker/moment.min.js"></script>
</head>
<body>
<div class="toolbar"> <a class="btn btn-info" href="<%=basePath%>modeler.jsp" target="_blank"><i class="fa fa-plus"></i>新增流程</a> <a class="btn btn-warning" id="delSelected"><i class='fa  fa-trash-o'></i>删除</a> </div>
<table id="flowtable" class="table table-striped table-hover dataTable no-footer">
  <thead>
    <tr>
     <th></th>
      <th>ID</th>
      <th>创建者</th>
      <th>流程名称</th>
      <th>流程ID</th>
      <th>版本号</th>
     <!--  <th>描述</th> -->
      <th>更新时间</th>
      <th nowrap="nowrap">操作</th>
    </tr>
  </thead>
</table>
<div id="flowShow"></div>
<div id="vdg"  class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">历史版本</h4>
      </div>
      <div class="modal-body">
        <table id="vtable" class="table table-striped table-hover dataTable no-footer">
          <thead>
            <tr>
              <th>版本</th>
              <th>修改时间</th>
              <th nowrap="nowrap">操作</th>
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
		"sDom": '<"top">rt<"bottom"flpi><"clear">',
		"bSearchable": false,
	    "ajax": {
	    	url:"workflow/process/process_list.do",
	    	type:"POST",
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
		 	{  "sTitle" : "<input type='checkbox' class='group-checkable' />",
               "bSortable":false,
			   "mRender": function(data, type, full) {
					return "<input type='checkbox' name='chkItem' style='margin-left: 8px;' class='checkboxes' value='"+full.id+"'/>";
				}				  
			},
		    { "data": "id" },
		    { "data": "username" },
			{ "data": "name" },
			{ "data": "processId" },
			{ "data": "version" },	
			/* { "data": "description","bSortable":false},	 */
			{ "data": "lastUpdateTime",
				mRender:function(data){
					return moment(data).format('YYYY-MM-DD HH:mm:ss');
				}
			},
			{ "bSortable":false,
			"mRender": function(data, type, full) {
				return "<button  class='btn btn-default btn-xs' onclick='preview("+full.id+")'><i class='fa fa-search'></i>查看</button><button  class='btn btn-default btn-xs' onclick='edit("+full.id+")'><i class='fa fa-edit'></i>修改</button><button  class='btn btn-default  btn-xs' onclick='delitem(\"flowtable\","+full.id+")'><i class='fa  fa-trash-o'></i>删除</button><button  class='btn btn-default  btn-xs' onclick='showv("+full.id+")'><i class='fa fa-angle-double-right'></i>历史版本</button>";
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
	}); 
});

 
function preview(id){
	Utils.showModel({
		id : "flowShow",
		title : "查看流程",
		width : "850px",
		url : "workflow/process/preview.do?id=" + id
	});
}

//弹出历史版本对话框
function showv(id){
	var table = $('#vtable').DataTable();
	table.settings()[0].ajax={dataSrc:""};
	
	table.ajax.url( 'workflow/process/history.do?id='+id ).load();
	
	$('#vdg').lightbox_me({
       centered: true, 
	});
}



function edit(id){
	window.open('<%=basePath%>modeler.jsp?modelId='+id,'_blank');
}

function delitem(tableId, id) {
	if (id != null) {
		Utils.confirm({
			title : "温馨提示",
			msg : "确认删除此数据吗？",
			modalSure : function() {
				Utils.ajax({
					url : "workflow/process/del.do",
					data : {id : id},
					success : function(data) {
						var state = data.success;
						if (state) {
							Utils.showMsg("success", "删除成功！");
							if(tableId == 'vtable'){
								$("#"+tableId).DataTable().ajax.reload();
							}else{
								$("#"+tableId).DataTable().ajax.reload();
							}
						} else {
							Utils.showMsg("error", "删除失败," + data.msg);
						}
					},
					error : function() {
						Utils.showMsg("error", "请求异常");
					}
				});
			}
		});
	}
}

$(function(){
	$(".group-checkable").click(function(){
		$(".checkboxes").prop("checked",$(this).prop("checked"));
	});
	
	$("#delSelected").click(function(){
		Utils.confirm({
			title : "温馨提示",
			msg : "确认删除选中的数据吗？",
			modalSure : function() {
				var id = new Array();
				$(".checkboxes").each(function(){
					if($(this).prop("checked")){
						id.push($(this).val());
					}
				});
				Utils.ajax({
					url : "workflow/process/del.do",
					data : {id:id.join(",")},
					success : function(data) {
						var state = data.success;
						if (state) {
							Utils.showMsg("success", "删除成功！");
							$("#flowtable").DataTable().ajax.reload();
						} else {
							Utils.showMsg("error", "删除失败," + data.msg);
						}
					},
					error : function() {
						Utils.showMsg("error", "请求异常");
					}
				});
			}
		});
	});
});

</script>
</html>
