var UplaodFile =  function(options){
    var control = $("#"+options.id);
    control.fileinput({         
    	showCaption: options.caption==undefined?true:options.caption,
    	language : 'zh', //设置语言   
    	enctype : 'multipart/form-data',
    	uploadUrl : options.url==undefined?"fileoper/uploadFile.action":options.url, 
    	showPreview :options.preview==undefined?false:options.preview,//是否显示预览
   	    showUpload: options.upload==undefined?false:options.upload,//是否显示上传按钮
	    showRemove :options.remove==undefined?false:options.remove,//显示移除按钮
	    allowedFileExtensions : options.fileExtension,//接收的文件后缀 
    	browseClass : "btn btn-primary", //按钮样式  
    	maxFileSize : options.size==undefined?0:options.size,
        uploadExtraData:function(previewId, index) {
		    return options.data;
	  },
      }).on('fileuploaderror', function(event, data, msg) {//文件上载时面临验证错误时触发事件
    	  if(options.pathId != undefined && options.pathId != ""){
    		  $("#"+options.pathId).val("");
    	  }
    	  $(".kv-upload-progress").hide();
    	   Utils.showMsg("error",msg);
      }).on("filebatchselected", function(event, files) {//选择文件后触发事件
    	  $(this).fileinput("upload");
	  }).on("fileuploaded", function(event, data) { //上传成功后触发事件
	      if(data.response)  { 
	    		//隐藏进度条
	    	   $(".kv-upload-progress").hide();
	    	   if(data.response.result){
	    		   options.success(data.response);
	    	   }else{
	    		   $(".file-caption-name").css("color","red");
	    		   Utils.showMsg("error","上传失败"); 
	    	   }
	      }else{
	    	  $(".file-caption-name").css("color","red");
	    	  Utils.showMsg("error","上传失败");
	      } 
	  }); 
}