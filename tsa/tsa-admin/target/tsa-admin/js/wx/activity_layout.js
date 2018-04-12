/**
 * 初始化活动行(列表)
 * @param infoId
 * @returns
 */
var activityId;
function bulidActivityLayout(infoId){
	activityId = infoId;
	dataTable({
		id : "activityLayoutTable",
		lengthChange : true,
		sort : false,
		info : false,
		paginate : true,
		autoWidth : true,
		processing : true,
		primaryKey : "id",
		sAjaxDataProp : "data",
		check : false,
		singleSelect : false,
		url : "wxActivityLayout/findByPage.action",
		data : {
			infoId : infoId
		},
		columns : [ {
			title : "行数",
			data : "lineNum"
		}, {
			title : "总列数",
			data : "columnsNum"
		}, {
			title : "操作",
			formatter : initLayoutOperation
		} ]
	});
}

/**
 * 初始化操作
 * @param data
 * @param type
 * @param obj
 * @returns
 */
function initLayoutOperation(data, type, obj){
//	var operateHtml = '<div class="side" id="side">';
//	operateHtml +='<button class="side-control btn btn-default btn-xs"><i class="fonticon icon-bianji"></i>'+getProp("public.txt.operation")+'</button>';
//	operateHtml += '<div class="sidebox">';
	var operateHtml = "";
	operateHtml += '<a onclick="editActivityLayout(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-edit"></i>'+getProp("public.txt.edit")+'</a>&nbsp;';
	operateHtml += '<a onclick="lookActivityRel(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-book"></i>查看列</a>&nbsp;';
	operateHtml += '<a onclick="deleteLayout(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-ban"></i>'+getProp("public.txt.delete")+'</a>&nbsp;';
//	operateHtml += '</div>';
//	operateHtml += '</div>';
	return operateHtml;
}

/**
 * 初始化新增、修改方法
 * @param id
 * @returns
 */
function editActivityLayout(id){
	var title = "";
	var url = "wxActivityLayout/forwardEditLayout.action";
	if(id == null || id == ""){ //新增
		title = "新增行信息";
	}else{ //修改
		title = "修改行信息";
		url += "?id="+id;
	}
	Utils.showModel({
		id : "editLayoutDiv",
		title : title,
		url : url,
		btns : [ [ "saveActivityLayoutBtn", getProp("public.btn.save") ] ],
		onload : function(){
			$("#infoId").val(activityId);
			$('.selectpicker').selectpicker();
			$("#editActivityLayoutForm").validation(function(obj, params) {}, {reqmark : true});
			$("#saveActivityLayoutBtn").click(saveActivityLayout);
			if(id != null && id != ""){
				$("#columnsNumDiv").hide();
			}
		}
	});
}

/**
 * 初始化保存方法
 * @returns
 */
function saveActivityLayout(){
	if ($("#editActivityLayoutForm").valid(this) == false) {
		return false;
	}
	var id = $("#id").val();
	var url, sucMsg, errMsg;
	if(id == null || id == ""){
		url = "wxActivityLayout/saveLayout.action";
		sucMsg = "新增行信息成功";
		errMsg = "新增行信息失败";
	}else{
		url = "wxActivityLayout/updateLayout.action";
		sucMsg = "修改行信息成功";
		errMsg = "修改行信息失败";
	}
	Utils.ajax({
		url : url,
		data : $('#editActivityLayoutForm').serialize(),
		success : function(data){
			if(data){
				Utils.closeModel('editLayoutDiv');
				dataTable.reload("activityLayoutTable");
				Utils.showMsg("success", sucMsg);
			}else{
				Utils.showMsg("error", errMsg);
			}
		},
		error : function(){
			Utils.showMsg("error", errMsg);
		}
	});
}

/**
 * 初始化删除方法
 * @param id
 * @returns
 */
function deleteLayout(id){
	Utils.confirm({
		msg : "确认删除该活动行信息" + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "wxActivityLayout/deleteLayout.action",
				data : {
					id : id
				},
				success : function(data){
					if(data){
						dataTable.reload("activityLayoutTable");
						Utils.showMsg("success", "删除活动行信息成功");
					}else{
						Utils.showMsg("error", "删除活动行信息失败");
					}
				},
				error : function(){
					Utils.showMsg("error", "删除活动行信息失败");
				}
			});
		}
	});
}

/**
 * 查看活动列
 * @param id
 * @returns
 */
var layoutId;
function lookActivityRel(id){
	layoutId = id;
	var html = "";
	var activityRelDiv = $("#activityRelDiv");
	Utils.ajax({
		url : "wxActivityRel/findByCondition.action",
		data : {
			layoutId : id
		},
		success : function(data){
			if(data){
				if(data.length > 0){
					var num = 12/data.length;
					for (var i = 0; i < data.length; i++) {
						html += "<div class='col-sm-"+num+"'>";
						if(data[i].modulName != null && data[i].modulName != ""){
							html += "<p class='activity-name'>"+data[i].modulName+"</p>";
						}else{
							html += "<p class='activity-name'>请添加素材</p>";
						}
						html += "<a onclick='editActivityRel("+data[i].id+")'>";
						if(data[i].filePath != null && data[i].filePath != ""){
							html += "<div class='img-div-"+data.length+"'>";
							html += "<img style='width:100%' title='"+data[i].seqNum+"' src='fileoper/downFile.action?filepath="+data[i].filePath+"'>";
							html += "</div>"
						}else{
							html += "<div class='img-div-"+data.length+"'>请添加素材</div>";
						}
						html += "</a></div>";
					}
				}else{
					html = "<div class='rel-div'>暂无活动列1</div>";
				}
			}else{
				html = "<div class='rel-div'>暂无活动列2</div>";
			}
			activityRelDiv.empty();
			activityRelDiv.append(html);
		},
		error : function(){
			html = "<div class='rel-div'>暂无活动列3</div>";
			activityRelDiv.empty();
			activityRelDiv.append(html);
		}
	});
}

function editActivityRel(id){
	Utils.showModel({
		id : "editActivityRelDiv",
		url : "wxActivityRel/forwardEditRel.action?id="+id,
		title : "修改活动列",
		btns : [ [ "saveActivityRelBtn", getProp("public.btn.save") ] ],
		onload : function(){
			$("#saveActivityRelBtn").click(saveActivityRel);
			$(".selectpicker").selectpicker();
		}
	});
}

function saveActivityRel(){
	Utils.ajax({
		url : "wxActivityRel/updateActivityRel.action",
		data : $('#editActivityLayoutForm').serialize(),
		success : function(data){
			if(data){
				Utils.showMsg("success", "修改活动列成功");
				Utils.closeModel('editActivityRelDiv');
				lookActivityRel(layoutId);
			}else{
				Utils.showMsg("error", "修改活动列失败");
			}
		},
		error : function(){
			Utils.showMsg("error", "修改活动列失败");
		}
	});
}