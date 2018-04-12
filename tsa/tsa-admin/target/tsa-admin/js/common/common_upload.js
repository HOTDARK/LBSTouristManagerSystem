//文件上传
function sendFile(files, editor, id) {
    var data = new FormData();
    for (var i = 0; i < files.length; i++) {
    	data.append("files", files[i]);
	}
    data.append("model", "summernote");
    $.ajax({
        data: data,
        type: "POST",
        processData: false,
        contentType: false,
        cache: false,
        url: 'common/uploadImage.action',
        success: function (data) {
        	if(data.result){
        		var list = data.list;
        		if(list != null && list.length > 0){
        			var url = "";
        			for (var i = 0; i < list.length; i++) {
        				url = "fileoper/downFile.action?filepath=" +list[i].filePath;
        				$('#'+id).summernote('insertImage', url);         
					}
        		}
        	}
        }
    });
}