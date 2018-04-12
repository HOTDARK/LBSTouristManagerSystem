/**
 * 字典管理脚本
 */
var param = {
		tableId:"tableTree",
		url: "sysDict/getTypeDictTableTree.action",
		field:["typeDictCode","typeDictName","typeDictCode"/*,"mappingName","mappingCode"*/,"seqNum","applicationFlag","state","id"],
		idKey:"typeDictCode",
		pidKey:"parentTypeDictCode",
		leafKey:"typeLeafNode",
		hiddenField:["typeDictName"],
		isLoadPage:true,
	/* 	iDisplayStart:0,
		iDisplayLength:10, */
		checked:true,
		checkClass:"checks",
		checkLeaf:false,
		format:{state:"formatState",id:"formatControl"}
};
$(function(){
	$('.selectpicker').selectpicker();
	c_tableFunction.tableFunction();
	c_tableTree.loadTree(param);
	$('#queryTypeDict').on("click",function(){
		var typeCode = $("#typeCode").val();
		var typeName = $("#typeName").val();
		var typeState = $("#typeState").val();
		var url = "sysDict/getTypeDictTableTree.action?r="+Math.random();
		if($.trim(typeCode) != ""){
			url += "&typeDictCode="+$.trim(typeCode);
		}
		if($.trim(typeName) != ""){
			url += "&typeDictName="+$.trim(typeName);
		}
		if(typeState != "-1"){
			url += "&state="+typeState;
		}
		param.url=url;
		$('#tableCheck').prop('checked', false);
		c_tableTree.loadTree(param);
	});
});
function formatControl(e){
	var html = '<div class="side" id="side">';
	html +='<button   class="side-control btn btn-default btn-xs"><i class="fonticon icon-bianji"></i>'+getProp("public.txt.operation")+'</button>';
	html += '<div class="sidebox">';
	html += '<a href="javascript:addTypeDictView(\''+e.typeDictCode+'\',\''+e.typeDictName+'\');"><i class="fa fa-plus"/>'+getProp("sys.dict.addSubDict")+'</a>';
	html += '<a href="javascript:editTypeDictView(\''+e.typeDictCode+'\');"><i class="fa fa-edit" />'+getProp("public.txt.edit")+'</a>';
	html += '<a href="javascript:freezeTypeDict(\''+e.typeDictCode+'\');"><i class="fa fa-ban"/>'+getProp("public.txt.frozen")+'</a>';
	html += '<a href="javascript:activateTypeDict(\''+e.typeDictCode+'\');"><i class="fa fa-gavel"/>'+getProp("public.txt.activate")+'</a>';
	html += '</div>';
	html += '</div>';
	return html ;
}
function formatState(e){
	if(e.state == 1){
		return "<span class='label  label-success'>"+getProp("public.select.effective")+"</span>";
	}else{
		return  "<span class='label  label-warning'>"+getProp("public.select.einvalid")+"</span>";
	}
}
//修改类型页面
function editTypeDictView(typeDictCode){
	if(typeDictCode == undefined){
		var temp = 0;
		$(".checks").each(function(){
    		if($(this).prop("checked")){
    			temp ++;
    			typeDictCode = $(this).val();
    		}
    	});
		if(temp != 1){
    		Utils.showMsg("warning",getProp("sys.dict.msg8"));
    	} else {
    		if(typeDictCode == "" || typeDictCode == undefined){
    			typeDictCode = 0;
    		}
    		Utils.showModel({
    	 		id:"typeDictModal",
    	 		title: getProp("sys.dict.editType"), 
    	 		btns:[["save_typeDictModal",getProp("public.btn.save")]], 
    	 		url: "sysDict/editTypeDictView.action?code="+typeDictCode,
    	 		onload: function(){
    	 			$('.selectpicker').selectpicker();
    	 		 	$("#editTypeDict").validation();
    	 		 	$('#save_typeDictModal').click(saveDict);
    	 		 	$("#typeDictCode").attr("readonly", "readonly");
    	 		}
    	 	});
    	}
	} else {
		Utils.showModel({
	 		id:"typeDictModal",
	 		title: getProp("sys.dict.editType"), 
	 		btns:[["save_typeDictModal",getProp("public.btn.save")]], 
	 		url: "sysDict/editTypeDictView.action?code="+typeDictCode,
	 		onload: function(){
	 			$('.selectpicker').selectpicker();
	 		 	$("#editTypeDict").validation();
	 		 	$('#save_typeDictModal').click(saveDict);
	 		 	$("#typeDictCode").attr("readonly", "readonly");
	 		}
	 	});
	}
}
//激活
function activateTypeDict(typeDictCode){
	var code = "";
	if(typeDictCode == undefined){
		code = "";
		var temp = 0;
    	$(".checks").each(function(){
    		if($(this).prop("checked")){
    			temp ++;
    			code += $(this).val()+",";
    		}
    	});
    	if(temp == 0){
    		Utils.showMsg("warning",getProp("sys.dict.msg1"));
    		return;
    	}
    	code = code.substring(0,code.length-1);
	}
	else{
		code = typeDictCode;
	}
	Utils.confirm({msg:getProp("sys.dict.msg2")+getProp("public.txt.question"),modalSure:function(){	
			Utils.ajax({
		 		url:"sysDict/activateTypeDict.action",
		 		data:{typeDictCode:code},
		 		success:function(data){
					if(data){
						Utils.showMsg("success",getProp("sys.dict.msg3"));
						$('#tableCheck').prop('checked', false);
						c_tableTree.loadTree(param);
					} else {
						Utils.showMsg("error",getProp("sys.dict.msg4"));
					}
		 		}
		 	});
		}
	});
}
//冻结
function freezeTypeDict(typeDictCode){
	var code = "";
	if(typeDictCode == undefined){
		code = "";
		var temp = 0;
    	$(".checks").each(function(){
    		if($(this).prop("checked")){
    			temp ++;
    			code += $(this).val()+",";
    		}
    	});
    	if(temp == 0){
    		Utils.showMsg("warning",getProp("sys.dict.msg1"));
    		return;
    	}
    	code = code.substring(0,code.length-1);
	}
	else{
		code = typeDictCode;
	}
	Utils.confirm({msg:getProp("sys.dict.msg5")+getProp("public.txt.question"),modalSure:function(){	
			Utils.ajax({
		 		url:"sysDict/freezeTypeDict.action",
		 		data:{typeDictCode:code},
		 		success:function(data){
					if(data){
						Utils.showMsg("success",getProp("sys.dict.msg6"));
						$('#tableCheck').prop('checked', false);
						c_tableTree.loadTree(param);
					} else {
						Utils.showMsg("error",getProp("sys.dict.msg7"));
					}
		 		}
		 	});
		}
	});
}

//新增类型页面
function addTypeDictView(typeDictCode,typeDictName){
	var temp = 0;
	if(typeDictCode == undefined){
		$(".checks").each(function(){
    		if($(this).prop("checked")){
    			temp ++;
    			typeDictCode = $(this).val();
    			typeDictName = $(this).attr("typeDictName");
    		}
    	});
	}
	if(temp > 1){
		Utils.showMsg("warning","最多只能选择一条数据");
	}
	else{
		if(typeDictCode == "" || typeDictCode == undefined){
			typeDictCode = 0;
			typeDictName = "无";
		}
		Utils.showModel({
	 		id:"typeDictModal",
	 		title: getProp("sys.dict.addType"), 
	 		btns:[["save_typeDictModal",getProp("public.btn.save")]], 
	 		url: "sysDict/addTypeDictView.action?pid="+typeDictCode,
	 		data:{"pName":typeDictName},
	 		onload: function(){
	 			$('.selectpicker').selectpicker();
	 		 	$("#editTypeDict").validation(function(obj,params){
	 				if (obj.id=='typeDictCode'){
	 					$.post("sysDict/validateTypeDictCode.action",{typeDictCode :$("#typeDictCode").val()},function(data){
	 						if(data) {
	 							params.err = true;
	 							params.msg = "类型编码已经存在";
	 						} else {
	 							params.err = false;
	 							params.msg = "";
	 						}
	 					});
	 				}},
	 				{reqmark:true});
	 		 	$('#save_typeDictModal').click(saveDict);
	 		}
	 	});
	}
}
function checkAll(e){
	if($(e).prop("checked")){
		$(".checks").prop("checked",true);
	}
	else{
		$(".checks").prop("checked",false);
	}
}

function saveDict(){
	if($("#editTypeDict").valid(this)==false){
	       return false;
	}
 	Utils.ajax({
 		url:"sysDict/editTypeDict.action",
 		data:$('#editTypeDict').serialize(),
 		success:function(data){
			if(data){
				Utils.closeModel('typeDictModal');
				Utils.showMsg("success","保存类型成功");
				$('#tableCheck').prop('checked', false);
				c_tableTree.loadTree(param);
			} else {
				Utils.showMsg("error","保存类型失败");
			}
 		}
 	});
}