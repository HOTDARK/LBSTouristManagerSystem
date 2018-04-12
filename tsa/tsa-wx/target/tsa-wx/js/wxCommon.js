function jumpPage(actionUrl) {
	if (actionUrl.indexOf("?") != -1) {
		window.location = "http://" + getRootPath() + "/" + actionUrl
				+ '&openid=' + openid;
	} else {
		window.location = "http://" + getRootPath() + "/" + actionUrl
				+ '?openid=' + openid;
	}
}

var imgNum=0;
var imgData=new Map();
/**
 * 替换图片(单张)
 * @param obj
 */
function replaceImg(obj,model){
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
	            		url : 'fileoper/downloadImgsFromWx.action',
	        			data:{"openid":openid,"serverIds":serverIds,"ftpDir":model},
	        			success : function(data) {
        					var resultMap = data;
        					for (var key in resultMap) {
        						if (resultMap[key]==null) {
        							$.alert("图片["+key+"]上传失败");
        						}else{
        							var oldKey=$(obj).attr("data-value");
        							delete imgData[oldKey];
        							imgData[key]=resultMap[key];
        							var file = resultMap[key];
        							$(obj).html('<img src="fileoper/downFile.action?filepath='+file["filePath"]+'" style="width: 100%; height: 80px;"/>');
        							$(obj).attr("data-value",key);
        						}
        					}
	        				$.hideLoading();
	        			}
	        		});
	            }
	        },
	        fail:function(res){
	        	$.alert(res.errMsg);
	        }
		});
	}
}

/**
 * 上传图片(2张）
 * 返回整个附件对象
 */
function uploadImgs(param){
	var serverIds="";
	wx.chooseImage({
		count: 2-imgNum, // 默认9
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
	        			url : 'fileoper/downloadImgsFromWx.action',
	        			data:{"openid":openid,"serverIds":serverIds,"ftpDir":param},
	        			success : function(data) {
        					var resultMap = data;
        					for (var key in resultMap) {
        						if (resultMap[key]==null) {
        							$.alert("图片["+key+"]上传失败");
        						}else{
        							imgData[key]=resultMap[key];
        							imgNum=parseInt(imgNum)+parseInt(1);
        							var file = resultMap[key];
        							if (imgNum==1) {
        								$("#firstImg").html('<img src="fileoper/downFile.action?filepath='+file["filePath"]+'" style="width: 100%; height: 80px;"/>');
        								$("#firstImg").attr("onclick","replaceImg(this,'"+param+"')");
        								$("#firstImg").attr("data-value",key);
        								$("#secondImg").show();
									} else if (imgNum==2) {
										$("#secondImg").html('<img src="fileoper/downFile.action?filepath='+file["filePath"]+'" style="width: 100%; height: 80px;"/>');
										$("#secondImg").attr("onclick","replaceImg(this,'"+param+"')");
										$("#secondImg").attr("data-value",key);
									}
        						}
        					}
	        				$.hideLoading();
	        			}
	        		});
	            }
	        }
		});
	}
}

function resetImg(model){
	imgNum=0;
	imgData.clear();
	$("#firstImg").html('');
	$("#firstImg").attr('onclick','uploadImgs(\''+model+'\')');
	$("#secondImg").html('');
	$("#secondImg").hide();
}