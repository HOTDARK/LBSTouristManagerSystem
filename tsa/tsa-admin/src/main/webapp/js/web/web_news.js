/**
 * 网页新闻信息脚本
 */
$(function() {
	$('.selectpicker').selectpicker();
	c_tableFunction.tableFunction();
	bulidWebNewsData();
	$('#searchBotton').click(bulidWebNewsData);
});

/**
 * 初始化网页新闻列表
 */
function bulidWebNewsData() {
	dataTable({
		id : "webNewsTable",
		lengthChange : true,
		sort : false,
		info : true,
		paginate : true,
		autoWidth : true,
		processing : true,
		primaryKey : "id",
		sAjaxDataProp : "data",
		check : true,
		singleSelect : false,
		url : "webNews/findByPage.action",
		data : Utils.serializeObj($("#searchFrom")),
		columns : [ {
			title : "新闻标题",
			data : "newsTitle"
		}, {
			title : "关键词",
			data : "keyWord"
		}, {
			title : "创建人",
			data : "createUserName"
		}, {
			title : "创建时间",
			data : "createTime",
			formatter : formatDate
		}, {
			title : "发布时间",
			data : "publishTime",
			formatter : formatDate
		}, {
			title : "发布状态",
			data : "publishFlag",
			formatter : convertPublishFlag
		}, {
			title : "操作",
			formatter : initOperation
		} ]
	});
}

/**
 * 时间格式化
 * @param e
 * @returns
 */
function formatDate(value, type, data){
	if(value == null || value == ""){
		return "";
	}
	return moment(value).format("YYYY-MM-DD HH:mm:ss");
}

/**
 * 发布状态转换
 * @param value
 * @param type
 * @param data
 * @returns
 */
function convertPublishFlag(value, type, data){
	if(value==1){
		return "<span class='label label-success'>已发布</span>";
	} else {
		return "<span class='label label-danger'>未发布</span>";
	}
}

/**
 * 初始化操作
 * @param data
 * @param type
 * @param obj
 * @returns {String}
 */
function initOperation(data, type, obj) {
	var operateHtml = '<div class="side" id="side">';
	operateHtml +='<button class="side-control btn btn-default btn-xs"><i class="fonticon icon-bianji"></i>'+getProp("public.txt.operation")+'</button>';
	operateHtml += '<div class="sidebox">';
	if(obj.publishFlag == 1){
		operateHtml += '<a onclick="publishOperation(\''+obj.id+'\',0);" href="javascript:void(0);"><i class="fa fa-ban"></i>撤回</a>&nbsp;';
	}else{
		operateHtml += '<a onclick="publishOperation(\''+obj.id+'\',1);" href="javascript:void(0);"><i class="fa fa-gavel"></i>发布</a>&nbsp;';
		operateHtml += '<a onclick="editWebNews(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-edit"></i>'+getProp("public.txt.edit")+'</a>&nbsp;';
	}
	operateHtml += '<a onclick="lookWebNews(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-book"></i>'+getProp("public.txt.look")+'</a>&nbsp;';
	operateHtml += '<a onclick="deleteWebNews(\''+obj.id+'\');" href="javascript:void(0);"><i class="fa fa-ban"></i>'+getProp("public.txt.delete")+'</a>&nbsp;';
	operateHtml += '</div>';
	operateHtml += '</div>';
	return operateHtml;
}

/**
 * 跳转网页新闻编辑页面
 */
function editWebNews(id){
	var title = "";
	var url = "webNews/forwardEditWebNews.action";
	if(id == null || id == ""){ //新增
		title = "新增网页新闻";
	}else{ //修改
		title = "修改网页新闻";
		url += "?id="+id;
	}
	Utils.showModel({
		id : "editWebNewsDiv",
		title : title,
		url : url,
		width : "900px",
		btns : [ [ "saveWebNewsBtn", getProp("public.btn.save") ] ],
		onload : function(){
			$('.selectpicker').selectpicker();
			$("#editWebNewsForm").validation(function(obj, params) {}, {reqmark : true});
			$("#saveWebNewsBtn").click(saveWebNews);
			initFileUpload();
			if(id != null && id != ""){
				var pictureFlag = $("#pictureFlag").val();
				if(pictureFlag == 1){
					$("#pictureDiv").show();
				}else{
					$("#pictureDiv").hide();
				} 
			}
		}
	});
}

/**
 * 初始化图片上传方法
 * @returns
 */
function initFileUpload(){
	UplaodFile({
		id : "uploadfile",//上传input文件框的id
		fileExtension : [ 'png', 'jpg','bpm','gif','jpeg' ],//接收的文件后缀  
		data : {model : "webNews"},//模块名 即上传目录下新建的文件夹名称
		preview : false, //是否显示预览
		upload : false,//是否显示上传按钮
		remove : false,//显示移除按钮
		caption :false,//是否显示标题
		success: function(data){//回调函数
			$("#showPic").attr("src", "fileoper/downFile.action?filepath="+data.filePath);
			$("#picturePath").val(data.filePath);
		}
	});
}

/**
 * 是否显示上传图片
 * @returns
 */
function showPicture(){
	var pictureFlag = $("#pictureFlag").val();
	if(pictureFlag == 1){
		$("#pictureDiv").show();
	}else{
		$("#pictureDiv").hide();
	}
}

/**
 * 保存网页新闻
 * @returns
 */
function saveWebNews(){
	if ($("#editWebNewsForm").valid(this) == false) {
		return false;
	}
	var pictureFlag = $("#pictureFlag").val();
	if(pictureFlag == 1){
		var picturePath = $("#picturePath").val();
		if(picturePath == null || picturePath == ""){
			Utils.showMsg("warning", "请上传新闻图片");
			return false;
		}
	}
	var newsContent = $("#newsContent").val();
	if(newsContent == null || newsContent.replace(/<[^>]+>/g,"").trim() == ""){
		Utils.showMsg("warning", "请输入新闻内容");
		return false;
	}
	var id = $("#id").val();
	var url, sucMsg, errMsg;
	if(id == null || id == ""){
		url = "webNews/saveWebNews.action";
		sucMsg = "新增网页新闻成功";
		errMsg = "新增网页新闻失败";
	}else{
		url = "webNews/updateWebNews.action";
		sucMsg = "修改网页新闻成功";
		errMsg = "修改网页新闻失败";
	}
	Utils.ajax({
		url : url,
		data : $('#editWebNewsForm').serialize(),
		success : function(data){
			if(data){
				Utils.closeModel('editWebNewsDiv');
				dataTable.reload("webNewsTable");
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
 * 发布、撤回操作
 * @param id
 * @param publishFlag
 * @returns
 */
function publishOperation(id, publishFlag){
	var pubMsg, sucMsg, errMsg;
	if(publishFlag == 0){
		pubMsg = "确认撤回该新闻";
		sucMsg = "撤回新闻成功";
		errMsg = "撤回新闻失败";
	}else{
		pubMsg = "确认发布该新闻";
		sucMsg = "发布新闻成功";
		errMsg = "发布新闻失败";
	}
	Utils.confirm({
		msg : pubMsg + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "webNews/updateWebNews.action",
				data : {
					id : id,
					publishFlag : publishFlag
				},
				success : function(data){
					if(data){
						dataTable.reload("webNewsTable");
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
	});
}

/**
 * 删除操作
 * @param id
 * @returns
 */
function deleteWebNews(id){
	Utils.confirm({
		msg : "确认删除该新闻" + getProp("public.txt.question"),
		modalSure : function() {
			Utils.ajax({
				url : "webNews/updateWebNews.action",
				data : {
					id : id,
					publishFlag : 0,
					deleteFlag : 1
				},
				success : function(data){
					if(data){
						dataTable.reload("webNewsTable");
						Utils.showMsg("success", "删除新闻成功");
					}else{
						Utils.showMsg("error", "删除新闻失败");
					}
				},
				error : function(){
					Utils.showMsg("error", "删除新闻失败");
				}
			});
		}
	});
}

function lookWebNews(id){
	Utils.showModel({
		id : "lookWebNewsDiv",
		url : "webNews/forwardLookWebNews.action?id="+id,
		title : "查看网页新闻",
		width : "99%",
		onload : function(){
			
		}
	});
}