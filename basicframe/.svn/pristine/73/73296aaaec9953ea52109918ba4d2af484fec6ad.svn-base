/**
 * 功能管理脚本
 */
var param = {
		tableId:"tableTree",
		url:"sysFunc/querySysFunctionList.action",
		field:["functionId","functionName","functionCode","functionType","state","seqNum","functionUrl","cz"],
		idKey:"functionId",
		pidKey:"parentFunctionId",
		leafKey:"functionLeafNode",
		hiddenField:["functionName"],
		isLoadPage:false,
		checked:true,
		checkClass:"checks",
		format:{state:"formatState",functionType:"formatType",cz:"formatControl"}
};

$(function(){
	$('.selectpicker').selectpicker();
	// 加载列表数据
	c_tableFunction.tableFunction();
	c_tableTree.loadTree(param);
	$(".btn-slide").click(function(){
		$("#panel").slideToggle("slow");
		$(this).toggleClass("slide-hover"); return false;
	}); 
});

function formatControl(e){
	var html = '<div class="side" id="side">';
	html +='<button class="side-control btn btn-default btn-xs"><i class="fonticon icon-bianji"></i>'+getProp("public.txt.operation")+'</button>';
	html += '<div class="sidebox">';
	html += '<a onclick="addSysFunction('+e.functionId+',\''+e.functionName+'\')" href="javascript:void(0);"><i class="fa fa-plus"/>'+getProp("sys.fun.addSubfun")+'</a>';
	html += '<a onclick="editorSysFunction('+e.functionId+')" href="javascript:void(0);"><i class="fa fa-edit" />'+getProp("public.txt.edit")+'</a>';
	html += '<a onclick="freezeOperation('+e.functionId+')" href="javascript:void(0);"><i class="fa fa-ban"/>'+getProp("public.txt.frozen")+'</a>';
	html += '<a onclick="activateOperation('+e.functionId+')" href="javascript:void(0);"><i class="fa fa-gavel"/>'+getProp("public.txt.activate")+'</a>';
	html += '</div>';
	html += '</div>';
		return html ;
}

function formatState(e){
	if(e.state == 1){
		return  "<span class='label  label-success'>"+getProp("public.select.effective")+"</span>";
	}else{
		return "<span class='label  label-warning'>"+getProp("public.select.einvalid")+"</span>";
	}
}
function formatType(e){
	if(e.functionType == 1){
		return getProp("sys.fun.type.function");
	}else{
		return getProp("sys.fun.type.menu");
	}
}

//进入新增页面
function addSysFunction(parentFunctionId, parentFunctionName){
	if(parentFunctionId == undefined){
	  var temp = 0;
		$("#tableTree input.checks").each(function(){
	 	 	if($(this).prop("checked")){
	 	 	temp ++;
	 	 		parentFunctionId =  $(this).val();
	 	 		parentFunctionName = $(this).attr("functionName");
	 	 	}
 		});
 		if(temp > 1){
		Utils.showMsg("warning","请选择一条数据");
		return false;
	  }
	}
	if(parentFunctionId == undefined){
 		parentFunctionId = 0;
 		parentFunctionName = "无";
	}
	Utils.showModel({
 		id:"sysFunction",
 		title: getProp("sys.fun.addFun"), 
 		btns:[["save_sysFunction",getProp("public.btn.save")]], 
 		url: "sysFunc/saveSysFunction.action?parentFunctionId="+parentFunctionId,
 	    data:{"parentFunctionName":parentFunctionName},
 	    onload: funcEditInit
 	});
}
//激活
function activateOperation(functionId){
	editSysFunctionState(1,functionId);
} 
//冻结
function freezeOperation(functionId){
	editSysFunctionState(0,functionId);
} 
//修改状态 冻结和激活
function editSysFunctionState(state,functionId) {
	var strTemp = "";
	if(functionId == undefined){
		strTemp = getClickValue();
		if(strTemp == ""){
			Utils.showMsg("error","请选择数据");
			return;
		}
	}else{
		strTemp = functionId;
	}
	var msg = "冻结选中及其下级功能";
	if(state==1){
		msg = "激活选中及其上级功能";
	}
	Utils.confirm({msg:"确定要"+msg+"？",modalSure:function(){	
		Utils.ajax({
	 		url:"sysFunc/editSysFunctionState.action?functionIds="+strTemp+"&state="+state,
	 		data:$('#addSysfunction').serialize(),
	 		success:function(data){
				if(data){
					$('#tableCheck').prop('checked', false);
					c_tableTree.loadTree(param);
					Utils.showMsg("success",msg+"成功");
				} else {
					Utils.showMsg("error",msg+"失败");
				}
	 		}
	 	});
	}});
}
//进入修改页面
function editorSysFunction(functionId) {
	if(functionId == undefined){
		var index = 0;
		var temp=0;
		$("#tableTree input.checks").each(function(){
	 	 	if($(this).prop("checked")){
	 	 	temp ++;
	 	 	     ++index;
	 	 		if(index == 2){
	 	 			return;
	 	 		}
	 	 		functionId =  $(this).val();
	 	 	}
 		});
 		
	}
	if(functionId == undefined){
		Utils.showMsg("error","请选择数据");
		return;
	}if(temp >1){
		Utils.showMsg("error","最多选择一条数据");
		return;
	}
	Utils.showModel({
 		id:"sysFunction",
 		title: getProp("sys.fun.editFun"), 
 		btns:[["save_sysFunction",getProp("public.btn.save")]], 
 		url: "sysFunc/querySysFunction.action?functionId="+functionId,
 		onload: funcEditInit
 	});
}

//选中全部
function getClickAll(e){
	if($(e).prop("checked")){
		$("input.checks").prop("checked", true);
	}
	else{
		$("input.checks").prop("checked", false);
	}
}
//获取选中的lickbo的值
function getClickValue(){
	var strTemp = "";
 	$("#tableTree input.checks ").each(function(){
 	 	if($(this).prop("checked")){
 	 		strTemp +=  $(this).val() + ",";
 	 	}
	});
	 strTemp = strTemp.substring(0,strTemp.length -1);
	 return strTemp;
}

//查询
function selectSysFunction(){
  	$("#tableTree tbody").remove();
	var functionName = $("#functionName").val();
	var state = $("#state_type").val();
	var functionType = $("#function_type").val();
	var url = "sysFunc/querySysFunctionList.action?r=1";
	if($.trim(functionName) != ""){
		url += "&functionName="+$.trim(functionName);
	}
	if(state != "-1"){
		url += "&state="+state;
	}
	if(functionType != "-1"){
		url += "&functionType="+functionType;
	}
	param.url = url;
	$('#tableCheck').prop('checked', false);
	c_tableTree.loadTree(param);
}

/*****************************编辑*********************************/
function funcEditInit(){
	$('.selectpicker').selectpicker();
	$("#editSysfunction").validation(function(obj,params){
		if (obj.id=='functionCode'){
			$.post("sysFunc/validateHasFuncCode.action",{functionCode :$("#functionCode").val(),functionId:$("#functionId").val()},function(data){
				if(data) {
					params.err = true;
					params.msg = "功能编码已经存在";
				} else {
					params.err = false;
					params.msg = "";
				}
			});
		 }
	}, {reqmark:true});
	$('#save_sysFunction').click(submitAjax);
	DY_scroll('.img-scroll','.prev','.next','.img-list',2,false);// true为自动播放，不加此参数或false就默认不自动
}

function submitAjax(){
 	if($("#editSysfunction").valid(this)==false){
	       return false;
	}
	Utils.ajax({
	 	url:"sysFunc/editSysFunction.action",
	 	data:$('#editSysfunction').serialize(),
	 	success:function(data){
	 		if(data){
	 			Utils.closeModel('sysFunction');
				reefresh();
				$('#tableCheck').prop('checked', false);
				Utils.showMsg("success","保存功能成功");
			} else {
				Utils.showMsg("error","保存功能失败");
			}
	 	}
	});
}
var LastCount =0;

function CountStrByte(Message,Remain) { //字节统计
	var ByteCount = 0;
	var StrValue =$("#"+Message).val();
	var StrLength = $("#"+Message).val().length;
	var MaxValue = 2000;
	if (LastCount != StrLength) { // 在此判断，减少循环次数
		for (i = 0; i < StrLength; i++) {
			ByteCount = (StrValue.charCodeAt(i) <= 256) ? ByteCount + 1 : ByteCount + 2;
			if (ByteCount > MaxValue) {
			 	$("#"+Message).val(StrValue.substring(0, i));
				ByteCount = MaxValue;
				break;
			}
		}
		$("#"+Remain).html( MaxValue - ByteCount);
		LastCount = StrLength;
	}
}
function reefresh(){
	c_tableTree.loadTree(param);
}
function DY_scroll(wraper,prev,next,img,speed,or) {
	var wraper = $(wraper);
	var prev = $(prev);
	var next = $(next);
	var img = $(img).find('ul');
	var w = img.find('li').outerWidth(true);
	var s = speed;
	next.click(function(){
		img.find('li').eq(0).appendTo(img);
		img.css({'margin-left':0});
		img.animate({'margin-left':-w});
	});
	prev.click(function(){
        img.find('li:last').prependTo(img);
        img.css({'margin-left':-w});
        img.animate({'margin-left':0});
	});
	if (or == true){
		ad = setInterval(function() { next.click();},s*1000);
		wraper.hover(function(){clearInterval(ad);},function(){ad = setInterval(function() { next.click();},s*1000);});
	}
}

function isDisplay(){
	 var functionType = $("#functionType").val();
	 var parentFunctionId = $("#parentFunctionId").val();
	 if(functionType == 2){
		 $("#icos").toggle();
	 }else{
		 $("#icos").hide();
	 }
}
function showIco(obj){
	 $("#icoList").find("li").each(function(){
		 if($(this).attr("data") == $(obj).attr("data")){
			 if($(this).hasClass("select-li")){
			 	$(this).removeClass("select-li");
	    		$("#icoName").val("");
			 }
			 else{
			 	$("#icoName").val($(this).attr("data"));
				$(this).addClass("select-li");
			 }
		 }else{
			  $(this).removeClass("select-li");
		 }
	 });
}