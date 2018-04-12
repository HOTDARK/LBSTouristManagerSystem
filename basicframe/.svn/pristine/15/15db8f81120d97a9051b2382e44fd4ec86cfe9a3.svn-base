<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>节点模板</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>wflibs/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>wflibs/css/flow.css"/>
<link  rel="stylesheet" type="text/css" href="<%=basePath %>wflibs/plugins/font-awesome/css/font-awesome.css"/>
<script src="<%=basePath %>wflibs/plugins/jquery.min.js"></script>
<script src="<%=basePath %>wflibs/bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" href="<%=basePath %>wflibs/plugins/datatables/css/jquery.dataTables.css"/>
<script type="text/javascript" src="<%=basePath %>wflibs/plugins/datatables/js/jquery.dataTables.js"></script> 
<script src="<%=basePath %>wflibs/plugins/jquery.slimscroll.js"></script>
<script src="<%=basePath %>wflibs/plugins/jquery.lightbox_me.js"></script>
<script type="text/javascript" src="<%=basePath %>/wflibs/plugins/layer/layer.min.js"></script>
</head>
<body>
<div class="toolbar">
<a class="btn btn-info" data-toggle="modal" onClick="addNode();" data-target="#addnode"><i class="fa fa-plus"></i>
新增节点</a> <a class="btn btn-warning" href="javascript:void(0)" id="delSelected"><i class='fa  fa-trash-o'></i>删除</a> 
</div>
 <table id="table" class="table table-striped table-hover table-bordered">
     <thead>
       <tr>
         <th></th>
         <th>ID</th>
         <th>节点名称</th>              
         <th>分组名</th>
         <th>描述</th>
         <th>操作</th>
       </tr>
     </thead>
   </table>
        
 <!--新增节点 Modal -->

	<div id="addnodedg" class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="operTitle">新增节点</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<input type="hidden" name="id" id="operId" value="0">
					<div class="form-group">
						<label for="stencilId" class="col-sm-2 control-label">ID</label>
						<div class="col-sm-10">
							<input id="stencilId" name="stencilId" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="nodename" class="col-sm-2 control-label">名称</label>
						<div class="col-sm-10">
							<input id="nodename" name="title" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="clazz" class="col-sm-2 control-label">类名</label>
						<div class="col-sm-10">
							<input id="clazz" name="clazz" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="groups" class="col-sm-2 control-label">分组</label>
						<div class="col-sm-10">
							<input id="groups" name="groups" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="icon" class="col-sm-2 control-label">图标</label>
						<div class="col-sm-10">
							<input id="icon" name="icon" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="svg" class="col-sm-2 control-label">SVG</label>
						<div class="col-sm-10">
							<textarea id="svg" name="view" style="height:150px;magrin-top:5px;" class="form-control" ></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="desc" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-10">
							<textarea class="form-control" id="desc" name="description"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default "
					onclick="$('#addnodedg').trigger('close');">取消</button>
				<button type="button" class="btn btn-primary" onclick="save()">保存</button>
			</div>
		</div>
	</div>
	
</body>
<script>

 $(function(){ 
	 $('#table').dataTable( {
		"aaSorting": [[ 1, "desc" ]],
		"sDom": '<"top">rt<"bottom"flpi><"clear">',
		"bSearchable": false,	
		"ajax": {
			url:"list.do",
			type:"POST",
			data:function(d){
				d.group = $("#steGroup").val();
				d.stenId = $("#steId").val();
				d.stenName = $("#steName").val();
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
		 {   "sTitle" : "<input type='checkbox' class='group-checkable' />",
               "bSortable":false,
			   "mRender": function(data, type, full) {
				return "<input type='checkbox' name='chkItem' class='checkboxes' value='"+full.id+"'/>";
				}				  
			},
		    { "data": "stencilId" },
			{ "data": "title" },
			{ "data": "groups" },	
			{ "data": "description","bSortable":false },	
			{ "bSortable":false,
			"mRender": function(data, type, full) {
				return "<button  class='btn btn-default btn-xs' onclick='editNode("+full.id+")'><i class='fa fa-edit'></i>修改</button><button  class='btn btn-default  btn-xs' onclick='delitem("+full.id+")'><i class='fa  fa-trash-o'></i>删除</button>";
			}},	
		]	
	} );   
});

function addNode(){
	$("#operTitle").val("添加");
	clearForm();
	$('#addnodedg').lightbox_me({
		centered: true, 
	});
}

function editNode(id){
	$("#operTitle").val("编辑");
	
	var list = $("#table").DataTable().data();
	var data = null;
	for(var i=0;i<list.length;i++){
		if(list[i].id==id){
			data = list[i];
			break;
		}
	}
	
	var form = $("#addnodedg form");
	for(var k in data){
		form.find("[name='"+k+"']").val(data[k]);
	}
	
	$('#addnodedg').lightbox_me({
		centered: true, 
	});
}

function serializeObject(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
}

function save(){
	var data = serializeObject($("#addnodedg form"));
	$.ajax({
		url:"saveOrUpdate.do",
		type:"post",
		dataType:"json",
		data:data,
		success:function(rs){
			if(rs.success){
				$("#addnodedg").trigger("close");
				$("#table").DataTable().ajax.reload();
			}else{
				alert(rs.msg);
			}
		}
	});
	
}

function delitem(id){
	
	layer.confirm('确定删除吗？', function(){
		$.ajax({
    		url:"del.do",
    		data:{id:id},
    		dataType:"json",
    		success:function(data){
    			$("#table").DataTable().ajax.reload();
    			layer.closeAll();
    		}
    	});
		
	});

}

var defaultFormValue = {id:0};
function clearForm(){
	$("#addnodedg form [name]").each(function(){
		var name = $(this).attr("name");
		if(typeof(defaultFormValue[name])!= "undefined"){
			$(this).val(defaultFormValue[name]);
		}else{
			$(this).val("");
		}
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
