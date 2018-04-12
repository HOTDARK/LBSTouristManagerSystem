/**
 * 商家信息管理脚本
 */
var imgNum=0;
$(function() {
    FastClick.attach(document.body);
    getStoreInfo();
    getCuisineList();
});

/**
 * 获取餐厅信息
 */
function getStoreInfo(){
	Utils.ajax({
		url: 'storeManage/getStoreInfo.action',
		data: {account : account},
		success: function(data){
			if (data.success) {
				var store = data.store;
				$("input[name='id']").val(store.id);
				$("input[name='logo']").val(store.logo);
				$("#logo").html("<img src='fileoper/downFile.action?filepath="+store.logo+"' height='100%' width='100%'/>");
				$("input[name='storeName']").val(store.storeName);
				$("textarea[name='storeIntro']").text(store.storeIntro);
				$("input[name='linkman']").val(store.linkman);
				$("input[name='phone']").val(store.phone);
				$("textarea[name='address']").text(store.address);
				$("input[name='license']").val(store.license);
				$("#license").html("<img src='fileoper/downFile.action?filepath="+store.license+"' height='100%' width='100%'/>");
				$("input[name='permit']").val(store.permit);
				$("#permit").html("<img src='fileoper/downFile.action?filepath="+store.permit+"' height='100%' width='100%'/>");
				$("input[name='status']").val(store.status);
				if (store.storeStatus==1) {
					$("#switchCP").prop("checked","checked");
				}else{
					$("#switchCP").removeAttr("checked");
				}
				$("input[name='orderPhone']").val(store.orderPhone);
				$("input[name='initiatePrice']").val(store.initiatePrice);
				$("input[name='dphPrice']").val(store.dphPrice);
				$("input[name='dispatching']").val(store.dispatching);
				$("textarea[name='affiche']").text(store.affiche);
				$("input[name='notice']").val(store.notice);
				var list = data.relList;
				var nameStr='',idStr='';
				for (var i = 0; i < list.length; i++) {
					idStr += list[i].cuisineId+',';
					nameStr += list[i].cuisineName+',';
				}
				idStr = idStr.substring(0,idStr.lastIndexOf(','));
				nameStr = nameStr.substring(0,nameStr.lastIndexOf(','));
				$("#cuiRel").attr("data-values",idStr);
				$("#cuiRel").val(nameStr);
				$("input[name='cuiIds']").val(idStr);
				var sacList = data.sacList;
				var imgHtml='';
				imgNum = sacList.length;
				if (imgNum<4) {
					$("#btn-up").show();
				}
				for (var j = 0; j < sacList.length; j++) {
					imgHtml += '<img src="fileoper/downFile.action?filepath='+sacList[j].filePath+'" width="90" height="60" id="img_'+sacList[j].id+'" onclick="replaceImg(\'img_'+sacList[j].id+'\')" data-values="'+sacList[j].id+'" class="pull-left" style="margin-right: 2%; margin-top: 2%;" />';
				}
				$("#imgList").html(imgHtml);
			}else{
				$.alert(data.errMsg);
			}
		}
	});
}

/**
 * 获取所有菜系
 */
function getCuisineList(){
	Utils.ajax({
		url: 'storeManage/getCuisineList.action',
		data:{},
		success: function(data){
			if (data.success) {
				var items=[];
				var cuiList = data.cuiList;
				for (var i = 0; i < cuiList.length; i++) {
					items.push({title:cuiList[i].cuisineName,value:cuiList[i].id});
				}
				$("#cuiRel").select({
					title: "主营菜系",
					multi: true,
					items: items,
					onClose: function(d){
						$("input[name='cuiIds']").val($("#cuiRel").attr("data-values"));
					}
				});
			}
		}
	});
}

/**
 * 验证餐厅营业状态
 */
function checkStoreState(){
	var result = $("#switchCP").is(':checked');
	if (result) {
		$("#storeStatus").val(1);
	}else{
		$.confirm({
			title: '确定歇业？',
			text: '餐厅歇业后将停止售卖该餐厅所有菜品',
			onOK: function () {
				$("#storeStatus").val(0);
				$("#switchCP").removeAttr("checked");
			},
			onCancel: function () {
				$("#switchCP").prop("checked","checked");
			}
		});
	}
}

/**
 * 上传图片(单张)
 * @param obj
 */
function uploadImg(obj){
	var serverIds='';
	wx.chooseImage({
		count: 1, // 默认9
		sizeType: ['compressed'],
	    success: function (res) {
	    	$.showLoading("图片上传中");
	    	var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	    	syncUpload(localIds);
	    }
	});
	var syncUpload = function(localIds){
		var localId = localIds.pop();
		wx.uploadImage({
	        localId: localId,
	        isShowProgressTips: 0,
	        success: function (res) {
	            var serverId = res.serverId; // 返回图片的服务器端ID
	            serverIds += serverId+',';
	            if(localIds.length > 0){
	                syncUpload(localIds);
	            } else {
	            	serverIds=serverIds.substring(0,serverIds.length-1);
	            	Utils.ajax({
	        			url : 'fileoper/downloadImgFromWx.action',
	        			data:{"serverIds":serverIds,"ftpDir":"diet/store"},
	        			success : function(data) {
	        				if (data.success) {
	        					var filepaths = data.paths;
	        					for (var key in filepaths) {
	        						$("#"+obj).empty();
		        					$("#"+obj).html("<img src='fileoper/downFile.action?filepath="+filepaths[key]+"' id='img_"+key+"' data-values='' data-filepath='"+filepaths[key]+"' height='100%' width='100%' alt=''/>");
		        					$("input[name='"+obj+"']").val(filepaths[key]);
	        					}
	        				}else{
	        					$.alert("上传图片失败");
	        				}
	        				$.hideLoading();
	        			}
	        		});
	            }
	        }
		});
	}
}

/**
 * 替换图片(单张)
 * @param obj
 */
function replaceImg(obj){
	var serverIds='';
	wx.chooseImage({
		count: 1, // 默认9
		sizeType: ['compressed'],
		success: function (res) {
			$.showLoading("图片上传中");
			var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
			syncUpload(localIds);
		}
	});
	var syncUpload = function(localIds){
		var localId = localIds.pop();
		wx.uploadImage({
	        localId: localId,
	        isShowProgressTips: 0,
	        success: function (res) {
	            var serverId = res.serverId; // 返回图片的服务器端ID
	            serverIds += serverId+',';
	            if(localIds.length > 0){
	                syncUpload(localIds);
	            } else {
	            	serverIds=serverIds.substring(0,serverIds.length-1);
	            	Utils.ajax({
	        			url : 'fileoper/downloadImgFromWx.action',
	        			data:{"serverIds":serverIds,"ftpDir":"diet/store"},
	        			success : function(data) {
	        				if (data.success) {
	        					var filepaths = data.paths;
								var paths = $("#paths").val();
	        					for (var key in filepaths) {
	        						if (filepaths[key]==null || filepaths[key]=='') {
	        							$.alert("图片["+key+"]上传失败");
	        						}else{
	        							if ($("#"+obj).attr("data-values")!=null && $("#"+obj).attr("data-values")!='') {
	        								var imgIds = $("#imgIds").val();
	        								imgIds = imgIds+$("#"+obj).attr("data-values")+",";
	        								$("#imgIds").val(imgIds);
	        							}else{
	        								var oldPath = $("#"+obj).attr("data-filepath");
	        								if (paths.indexOf(oldPath)>0) {
	        									paths=paths.replace(oldPath,filepaths[key]);
	        								}
	        							}
	        							$("#"+obj).remove(); 
	        							imgNum=parseInt(imgNum)+parseInt(1);
	        							paths = paths+filepaths[key]+",";
	        							$("#imgList").append('<img src="fileoper/downFile.action?filepath='+filepaths[key]+'" width="90" height="60" id="img_'+key+'" onclick="replaceImg(\'img_'+key+'\')" data-values="" data-filepath="'+filepaths[key]+'" class="pull-left" style="margin-right: 2%; margin-top: 2%;"/>');
	        						}
	        					}
	        					$("#paths").val(paths);
	        					if (imgNum==4) {
	        						$("#btn-up").hide();
	        					}
	        				}else{
	        					$.alert("上传图片失败");
	        				}
	        				$.hideLoading();
	        			}
	        		});
	            }
	        }
		});
	}
}

/**
 * 删除图片
 * @param obj
 */
function delImg(obj){
	if ($("#"+obj).attr("data-values")!=null && $("#"+obj).attr("data-values")!='') {
		var imgIds = $("#imgIds").val();
		imgIds = imgIds+$("#"+obj).attr("data-values")+",";
		$("#imgIds").val(imgIds);
	}else{
		var oldPath = $("#"+obj).attr("data-filepath");
		var paths = $("#paths").val();
		paths=paths.replace(oldPath+",",'');
		$("#paths").val(paths);
	}
	imgNum=imgNum-1;
	if (imgNum<4) {
		$("#btn-up").show();
	}
	$("#"+obj).remove();
}

/**
 * 上传图片(多张）
 */
function uploadImgs(){
	var serverIds="";
	wx.chooseImage({
		count: 4-imgNum, // 默认9
		sizeType: ['compressed'],
	    success: function (res) {
	    	$.showLoading("图片上传中");
	    	var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	    	syncUpload(localIds);
	    }
	});
	var syncUpload = function(localIds){
		var localId = localIds.pop();
		wx.uploadImage({
	        localId: localId,
	        isShowProgressTips: 0,
	        success: function (res) {
	            var serverId = res.serverId; // 返回图片的服务器端ID
	            serverIds += serverId+',';
	            if(localIds.length > 0){
	                syncUpload(localIds);
	            } else {
	            	serverIds=serverIds.substring(0,serverIds.length-1);
	            	Utils.ajax({
	        			url : 'fileoper/downloadImgFromWx.action',
	        			data:{"serverIds":serverIds,"ftpDir":"diet/store"},
	        			success : function(data) {
	        				if (data.success) {
	        					var filepaths = data.paths;
	        					var paths = $("#paths").val();
	        					for (var key in filepaths) {
	        						if (filepaths[key]==null) {
	        							$.alert("图片["+key+"]上传失败");
	        						}else{
	        							imgNum=parseInt(imgNum)+parseInt(1);
	        							paths = paths+filepaths[key]+",";
	        							$("#imgList").append('<img src="fileoper/downFile.action?filepath='+filepaths[key]+'" width="90" height="60" id="img_'+key+'" onclick="replaceImg(\'img_'+key+'\')" data-values="" data-filepath="'+filepaths[key]+'" class="pull-left" style="margin-right: 2%; margin-top: 2%;"/>');
	        						}
	        					}
	        					$("#paths").val(paths);
	        					if (imgNum==4) {
	        						$("#btn-up").hide();
	        					}
	        				}else{
	        					$.alert("上传图片失败");
	        				}
	        				$.hideLoading();
	        			}
	        		});
	            }
	        }
		});
	}
}

/**
 * 保存餐厅信息
 */
function saveStore(){
	if ($("input[name='logo']").val()==null||$("input[name='logo']").val()=='') {
		$.alert("请上传餐厅logo");
		return false;
	}
	if ($("input[name='storeName']").val()==null||$("input[name='storeName']").val()=='') {
		$.alert("请输入餐厅名称");
		return false;
	}
	if ($("input[name='linkman']").val()==null||$("input[name='linkman']").val()=='') {
		$.alert("请输入餐厅联系人");
		return false;
	}
	if ($("input[name='phone']").val()==null||$("input[name='phone']").val()=='') {
		$.alert("请输入餐厅联系电话");
		return false;
	}
	if ($("textarea[name='address']").val()==null||$("textarea[name='address']").val()=='') {
		$.alert("请输入餐厅地址");
		return false;
	}
	if ($("input[name='license']").val()==null||$("input[name='license']").val()=='') {
		$.alert("请上传餐厅营业执照");
		return false;
	}
	if ($("input[name='permit']").val()==null||$("input[name='permit']").val()=='') {
		$.alert("请上传餐厅许可证");
		return false;
	}
	if ($("input[name='cuiIds']").val()==null||$("input[name='cuiIds']").val()=='') {
		$.alert("请选择餐厅经营菜系");
		return false;
	}
	if ($("input[name='orderPhone']").val()==null||$("input[name='orderPhone']").val()=='') {
		$.alert("请输入餐厅订餐电话");
		return false;
	}
	if ($("input[name='initiatePrice']").val()==null||$("input[name='initiatePrice']").val()=='') {
		$.alert("请输入餐厅起送价");
		return false;
	}
	if ($("input[name='dispatching']").val()==null||$("input[name='dispatching']").val()=='') {
		$.alert("请输入餐厅配送方式");
		return false;
	}
	Utils.ajax({
		url: 'storeManage/saveStore.action',
		data: Utils.serializeObj($("#storeForm")),
		success: function(data){
			if (data.success) {
				$("#paths").val(null);
				$("#imgIds").val(null);
				getStoreInfo();
				$.alert("编辑餐厅信息成功");
			}else{
				$.alert("编辑餐厅信息失败");
			}
		}
	});
}
