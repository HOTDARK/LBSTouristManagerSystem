<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>节点模板</title>
<link rel="stylesheet" type="text/css" href="wflibs/css/flow.css"/>
</head>
<body>
<div class="toolbar" style="float: left;">
<a class="btn btn-info"   onClick="addNode();" ><i class="fa fa-plus"></i>新增节点</a> 
<a class="btn btn-warning" href="javascript:void(0)" id="delSelected"><i class='fa  fa-trash-o'></i>删除</a> 
</div>

<div align="left" style="float: right; margin-right: 10px; margin-top: 10px;">
    <div class="col-md-12">
      <form class="form-inline" onsubmit="return false;">    
       <select class="selectpicker" id="steGroup">
        	<option value="">--请选择--</option>
        </select>
          <input class="form-control" placeholder="节点ID" id="steId">  
          <input class="form-control" placeholder="节点名称" id="steName">  
        <a id="search" class="btn btn-primary"><i class="fa fa-search"></i>搜索</a>
      </form>
    </div>
  </div>
 <table id="table" class="table table-striped table-hover dataTable no-footer">
     <thead>
       <tr>
         <th></th>
         <th>ID</th>
         <th>节点名称</th>
         <th>分组名</th>
         <th width="150px;">操作</th>
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
				<form class="form-horizontal" onsubmit="return false">
					<input type="hidden" name="id" id="operId" value="0">
					<div class="form-group">
						<label for="stencilId" class="col-sm-2 control-label">ID</label>
						<div class="col-sm-9">
							<input id="stencilId" name="stencilId" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="nodename" class="col-sm-2 control-label">名称</label>
						<div class="col-sm-9">
							<input id="nodename" name="title" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="clazz" class="col-sm-2 control-label">类名</label>
						<div class="col-sm-9">
							<input id="clazz" name="clazz" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="groups" class="col-sm-2 control-label">分组</label>
						<div class="col-sm-9">
							<input id="groups" name="groups" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="icon" class="col-sm-2 control-label">图标</label>
						<div class="col-sm-9">
							<input id="icon" name="icon" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label for="svg" class="col-sm-2 control-label">SVG</label>
						<div class="col-sm-9">
							<textarea id="svg" name="view" style="height:150px;magrin-top:5px;" class="form-control" ></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="desc" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-9">
							<textarea class="form-control" id="desc" name="description"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" onclick="save();">保存</button>
				<button type="button" class="btn btn-default" onclick="$('#addnodedg').trigger('close');">取消</button>
			</div>
		</div>
	</div>
</body>
<script>
 $(function(){ 
	 $('#table').dataTable( {
	    "ordering": false,
		"processing": true,
	    "serverSide": true,
		"sDom": '<"top">rt<"bottom"flpi><"clear">',
		"bSearchable": false,	
		"ajax": {
			"url": "workflow/stencil/list.do",
			"type": "POST",
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
				return "<input type='checkbox' name='chkItem' style='margin-left: 8px;' class='checkboxes' value='"+full.id+"'/>";
				}				  
			},
		    { "data": "stencilId" },
			{ "data": "title" },
			{ "data": "groups" },	
			{ "bSortable":false,
			"mRender": function(data, type, full) {
				return "<button  class='btn btn-default btn-xs' onclick='editNode("+full.id+")'><i class='fa fa-edit'></i>修改</button><button  class='btn btn-default  btn-xs' onclick='delitem("+full.id+")'><i class='fa  fa-trash-o'></i>删除</button>";
			}},	
		]	
	} );   
	
	$("#search").click(function(){
		$('#table').DataTable().ajax.reload();
	});
	
		Utils.ajax({
		url : "workflow/stencil/stegrouplist.do",
		success : function(data) {
			var datas = data;
			var html="";
			if(datas.length>0){
				for(var i = 0 ; i < datas.length ; i++){
					var info = datas[i];
	                html+="<option value="+info['groups']+">"+info['groups']+"</option>";
				}
                $("#steGroup").append(html);
                $('.selectpicker').selectpicker();
			}
		},
		error : function() {
			Utils.showMsg("error", "请求异常");
		}
	});
	
});

function addNode(){
	$("#operTitle").html("新增节点");
	clearForm();
	$("#addnodedg").lightbox_me({
		centered: true, 
	});
}

function editNode(id){
	$("#operTitle").html("编辑节点");
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
			o[this['name']] = this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
}

function save(){
	var data = serializeObject($("#addnodedg form"));
	$.ajax({
		url:"workflow/stencil/saveOrUpdate.do",
		type:"post",
		data:data,
		success:function(rs){
			if(rs.success){
				$("#addnodedg").trigger("close");
				dataTable.reload('table');
			}else{
				alert(rs.msg);
			}
		}
	});
}

function delitem(id) {
	if (id != null) {
		Utils.confirm({
			title : "温馨提示",
			msg : "确认删除此数据吗？",
			modalSure : function() {
				Utils.ajax({
					url : "workflow/stencil/del.do",
					data : {id : id},
					success : function(data) {
						var state = data.success;
						if (state) {
							Utils.showMsg("success", "删除成功！");
							$("#table").DataTable().ajax.reload();
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
					url : "workflow/stencil/del.do",
					data : {id:id.join(",")},
					success : function(data) {
						var state = data.success;
						if (state) {
							Utils.showMsg("success", "删除成功！");
							$("#table").DataTable().ajax.reload();
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