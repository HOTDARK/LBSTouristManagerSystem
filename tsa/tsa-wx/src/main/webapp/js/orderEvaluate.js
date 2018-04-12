function backToList(){
	window.history.back();
}

var cp=0,sj=0,ps=0;
function cppf(cppf){
	cp=cppf;
	$("#cpScore").width(cp*16);
	$("#cpScoreText").text(cp+"分");
}

function sjpf(sjpf){
	sj=sjpf;
	$("#sjScore").width(sj*16);
	$("#sjScoreText").text(sj+"分");
}

function pspf(pspf){
	ps=pspf;
	$("#psScore").width(ps*16);
	$("#psScoreText").text(ps+"分");
}

function limitcontent(obj){
	$("#lc").text($(obj).val().length);
}

function submitComment(obj){
	$(obj).attr("disabled",true);
	var content=$("#content").val();
	if(cp==0){
		$.toast("请完成对菜品的星评","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(sj==0){
		$.toast("请完成对商家的星评","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	if(ps==0){
		$.toast("请完成对配送的星评","forbidden");
		$(obj).attr("disabled",false);
		return;
	}
	
	var filePaths = "", oldFileNames = "", fileNames = "", fileSizes = "", fileExtensions = "";
	if (imgData != undefined && imgData != null && imgData.length != 0) {
		$.each(imgData, function(j, i) {
			filePaths += i.filePath + ",";
			oldFileNames += i.oldFileName + ",";
			fileNames += i.fileName + ",";
			fileSizes += i.fileSize + ",";
			fileExtensions += i.fileExtension + ",";
		});
		filePaths = filePaths.substring(0, filePaths.length - 1);
		oldFileNames = oldFileNames.substring(0, oldFileNames.length - 1);
		fileNames = fileNames.substring(0, fileNames.length - 1);
		fileSizes = fileSizes.substring(0, fileSizes.length - 1);
		fileExtensions = fileExtensions.substring(0, fileExtensions.length - 1);
	}
	$.showLoading();
	Utils.ajax({
		url: 'dietOrder/insertComment.action',
		data: {
			"imgData":imgData,
			"storeScore":sj,
			"dishesScore":cp,
			"dphScore":ps,
			"content":content,
			"filePaths":filePaths,
			"oldFileNames":oldFileNames,
			"fileNames":fileNames,
			"fileSizes":fileSizes,
			"fileExtensions":fileExtensions,
			"openid":openid,
			"storeId":storeId,
			"orderNum":orderNum
			},
		success: function(data){
			$.hideLoading();
			if(data.success){
				$.toast("评价成功");
				setTimeout(function(){jumpPage("dietOrder/goOrderList.action");},1500);
			}
		}
	});
}