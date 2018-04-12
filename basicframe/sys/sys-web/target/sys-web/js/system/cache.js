/**
 * 缓存管理脚本
 */
$(function() {
	//加载列表操作弹出层
	c_tableFunction.tableFunction();
	loadTable();
});

/**
 * 修改缓存管理器状态
 * @param state 被修改状态
 */
function changeState(state) {
	Utils.confirm({msg:getProp("sys.cache.msg6")+getProp("public.txt.question"), modalSure:function () {
		Utils.ajax({
			url : "sysCache/change.action",
			data : {state : state},
			success : function(res){
				if (res.success) {
					$("#cacheState").removeClass();
					$("#cacheState").addClass(res.state?"sign-green":"sign-red");
					$("#cacheState").html(res.state?getProp("sys.cache.state.open.txt"):getProp("sys.cache.state.close.txt"));
					$("#changeBtn").attr("onclick", "changeState("+!res.state+");");
					$("#btnVal").html(res.state?getProp("sys.cache.state.close"):getProp("sys.cache.state.open"));
					Utils.showMsg("success", res.state?getProp("sys.cache.msg4"):getProp("sys.cache.msg5"));
				} else {
					Utils.showMsg("error", res.msg, 5000)
				}
			}
		});
	}});
}

/**
 * 加载表格
 */
function loadTable() {
	dataTable({
		id: "cacheTable",
		lengthChange: true,
		sort: false,
		info: true,
		paginate: true,
		autoWidth: true,
		processing: true,
		check: false,
		singleSelect: false,
		url: "sysCache/list.action",
		data:{},
		columns:[
	 		{ title: getProp("sys.cache.name"), data: "name" },
			{ title: getProp("sys.cache.prefix"), data: "prefix"},
			{ title: getProp("sys.cache.method"), data: "method"},
			{ title: getProp("sys.cache.timeToLive"), data: "timeToLive"},
			{ title: getProp("sys.cache.timeToIdle"), data: "timeToIdle"},
			{ title: getProp("sys.cache.state"), data: "state", formatter:convertState},
			{ title: getProp("public.txt.operation"), formatter:initOption}
		]
	});
}

/**
 * 状态转换
 * @param data
 * @param type
 * @param obj
 * @returns
 */
function convertState(data, type, obj){
	return obj.state==1?getProp("sys.cache.state.open.txt"):getProp("sys.cache.state.close.txt");
}

/**
 * 初始化操作项
 * @param data
 * @param type
 * @param obj
 * @returns {String}
 */
function initOption(data, type, obj){
	var operateHtml = '<div class="side" id="side">';
	operateHtml += '<a href="javascript:cacheEdit(\''+obj.id+'\');" title="'+getProp("public.txt.edit")+'" ><i class="fa fa-edit"></i>'+getProp("public.txt.edit")+'</a>';
	if(obj.state==1){
		operateHtml += '<a href="javascript:updateCacheState(\'0\',\''+obj.id+'\');" title="'+getProp("sys.cache.state.close")+'" ><i class="fa fa-ban"></i>'+getProp("sys.cache.state.close")+'</a>';
	} else {
		operateHtml += '<a href="javascript:updateCacheState(\'1\',\''+obj.id+'\');" title="'+getProp("sys.cache.state.open")+'" ><i class="fa fa-gavel"></i>'+getProp("sys.cache.state.open")+'</a>';
	}
	operateHtml += '</div>';
	return operateHtml;
}

/**
 * 显示编辑页面
 * @param id
 */
function cacheEdit(id){
	Utils.showModel({
		id: "cacheEdit",
		title: getProp("sys.cache.edit"), 
		btns:[["save_cacheEdit", getProp("public.btn.save")]], 
		url: "sysCache/forwardEdit.action",
		onload: function() {
			$("#cacheEditForm").validation(function(obj,params){}, {reqmark:true});
			$("#save_cacheEdit").click(cacheUpdate);
			Utils.ajax({
				url : "sysCache/edit.action",
				data : {id : id},
				success : function(res){
					if (res.success) {
						Utils.fillForm(res.define, $("#cacheEditForm"));
						$('.selectpicker').selectpicker();
					} else {
						Utils.showMsg("error", res.msg, 5000);
					}
				}
			});
		}
	});
}

/**
 * 修改缓存
 * @returns {Boolean}
 */
function cacheUpdate(){
	if ($("#cacheEditForm").valid(this)==false){
       	return false;
    }
	Utils.ajax({
		url : "sysCache/update.action",
		data : $("#cacheEditForm").serialize(),
		success : function(res){
			if (res.success) {
				Utils.closeModel('cacheEdit');
				dataTable.reload('cacheTable');
				Utils.showMsg("success", getProp("sys.cache.msg1"));
			} else {
				Utils.showMsg("error", res.msg, 5000);
			}
		}
	});
}

/**
 * 修改缓存状态
 * @param state 被修改状态
 * @param id
 */
function updateCacheState(state, id){
	Utils.ajax({
		url : "sysCache/updateState.action",
		data : {id:id,state:state},
		success : function(res){
			if (res.success) {
				dataTable.reload('cacheTable');
				Utils.showMsg("success", state==1?getProp("sys.cache.msg2"):getProp("sys.cache.msg3"));
			} else {
				Utils.showMsg("error", res.msg, 5000);
			}
		}
	});
}