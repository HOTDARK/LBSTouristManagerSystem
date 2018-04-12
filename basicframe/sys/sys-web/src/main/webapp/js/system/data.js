$(function() {
	var uploader;
	var typeid="1";
	//OLT上下行端口流量
	$(".updata-type-list li").click(function(){
		var id=$(this).find("span").attr("id");
		$(this).parent().children().find("span").removeClass("select");
		$(this).find("span").addClass("select");
		typeid=id;
		fileDataTable(typeid);
		uploader.settings.url=base+'/fileUpload/dataUpload.action?id='+id;
	});
	//实例化一个plupload上传对象
    uploader = new plupload.Uploader({
	runtimes:'gears,browserplus,html5,html4',
	multi_selection:false,
    browse_button : 'browse', //触发文件选择对话框的按钮，为那个元素id
    url : base+'/fileUpload/dataUpload.action?id='+"1", //服务器端的上传页面地址
    filters : 
    	      [
               {title : "Excel  files", extensions : "xls,xlsx"}
              ],
              max_file_size : '153600kb', //最大只能上传150M的文件
              prevent_duplicates : false //不允许选取重复文件
             
    });  
    //在实例对象上调用init()方法进行初始化
    uploader.init();
    //绑定各种事件，并在事件监听函数中做你想做的事
    uploader.bind('FilesAdded',function(uploader,files){
    var ssss="";
       $.each(files,function(n,value) {   
             ssss+=value.name;
           });  
    	$("#fileInfo").append(ssss);
        //每个事件监听函数都会传入一些很有用的参数，
        //我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
    });
    uploader.bind('UploadProgress',function(uploader,file){
       // $("#uploadprocess").html(file.percent+"%");
    });
    
    uploader.bind('QueueChanged',function(uploader){
    	//alert("QueueChanged");
    	//console.info(uploader);
    });
    
    uploader.bind('FilesRemoved',function(uploader,files){
    	//alert("FilesRemoved");
    	//console.info(uploader);
    });
    uploader.bind('UploadFile',function(uploader,file){
    	//console.info(uploader);
    	//alert("UploadFile");
    	//console.info(uploader);
    });
    
    uploader.bind('StateChanged',function(uploader){
    	//alert("StateChanged");
    	var state=uploader.state;
    	//状态==2时弹出遮罩层
    	if (state==2) {
    		loadingShow("正在上传中。。。。");
//    		if (typeid==3 ||typeid==4) {
//    			alert("文件正在后台上传并解析入库...");
//    			//location.reload() ;
//    			console.info("typeid:"+typeid);
//    			$('#uploadDiv').datagrid('load', {name:typeid});
//    		}else{
//    			loadingShow("正在上传中。。。。");
//    		}
		}
    	//alert(state);
    	//console.info(uploader);
    });
    
    uploader.bind('Error',function(uploader,errObject){
    	var meg=errObject.message;
    	if (meg!="HTTP Error.") {
    		if (meg=="File size error.") {
    			alert("上传文件最大为150M");
			}else{
				alert("上传错误，原因是："+errObject.message);
			}
		}
    	 fileDataTable(typeid);
    });
    
    uploader.bind('UploadComplete',function(uploader,files){ 
    	$("#fileInfo").html("");
    	$("#uploadprocess").html("");
    	 $.each(files,function(n,value) {   
    		 uploader.removeFile(value);
           });  
    	//取消掉遮罩层
    	loadingHide();
    	 fileDataTable(typeid);
    	//location.reload() ; 刷新整个页面
    	});
    
    uploader.bind('FileUploaded',function(uploader,file,responseObject){
    	uploader.removeFile(file);
    	var result=responseObject.response;
    	if (result=="sucess") {
			//成功
    		var aa="您选择的文件已成功上传并解析入库完成"; 
    		alert(aa);
		}else{
			//失败
            if (result=="no") {
            	alert("您选择的文件上传出错");
			}
            if (result=="error") {
            	alert("您选择的文件已成功上传,没有解析入库 ,原因是解析文件错误");
			}
            if (result=="empty") {
            	alert("您选择的文件已成功上传,没有解析入库 ,原因是excel工作薄为空");
			}
            if (result=="nomath") {
            	alert("您选择的文件已成功上传,没有解析入库 ,原因是文件格式不对");
			}
            //no error empty   nomath  
		}
    	 fileDataTable(typeid);
    });
	//最后给"开始上传"按钮注册事件
    document.getElementById('start_upload').onclick = function(){
    	var ss=$("#fileInfo").text();
    	if(ss!=""){
    		uploader.start(); //调用实例对象的start()方法开始上传文件，当然你也可以在其他地方调用该方法
    	}else{
    		alert("请先选择上传文件");
    	}
    };
    fileDataTable(typeid);
});


function fileDataTable(type) {
	dataTable({
		id: "uploadDiv",
		lengthChange: false,
		sort: false,
		info: false,
		paginate: true,
		autoWidth: true,
		processing: true,
		primaryKey: "id",
		sAjaxDataProp : "data",
		check: false,
		singleSelect: false,
		url : base+'/data/fileData.action',
		data:{
			"file_type":type
		},
		columns:[
	 		{ title: "ID", data: "id" },
			{ title: "文件名称 ", data: "file_title"},
			{ title: "文件大小(M)", data: "file_size"},
			{ title: "是否入库 ", data: "is_parse",formatter : isParseInit},
			{ title: "入库数量(条)", data: "countNum"},
			{ title: "上传日期 ", data: "file_date",formatter : appDateInit},
			{ title: "上传人 ", data: "file_operater"}
		]
	});
}

function appDateInit(data, type, obj) {
	var operateHtml = moment(obj.file_date).format('YYYY-MM-DD HH:mm:ss');
	return operateHtml;
}
function isParseInit(data, type, obj) {
	var value = obj.is_parse;
	if (value=="Y") {
		return "全入";
	}else if (value=="P") {
		return "部分入库";
	}else if (value=="G") {
		return "正在入库中";
	}else if(value=="N"){
		return "未入";
	}
}

function loadingShow(msg){
 	$("body").append('<div class="divshow" style="display: none" id="divshow"></div>');
 	document.getElementById("divshow").style.display="block"; 
     var loadingContainer = $("div.loading");
     if (loadingContainer.length <= 0) {
         loadingContainer = $("<div>", { Class: "loadingWhenSave" , id:"loadingWhenSave" });
         var img = $("<img>", { src: base+"/images/loading.gif" });
         loadingContainer.html(msg);
         //loadingContainer.append("<br/>");
         loadingContainer.append(img).css({
             position: "absolute", //"absolute",
             zIndex: "9999",
             textAlign: "center",
             //backgroundColor: "#000",
             border: "solid 1px back",
             paddingTop: "18px",
             fontSize: "14px",
             top: "30%",
             left: "40%"
            // height: "20px",
            // width: "20px"
         });
         //document.body.appendChild(loadingContainer);
         loadingContainer.appendTo('body');
     }
     //$(loadingContainer).show();
 }


function loadingHide(){
 	document.getElementById("divshow").style.display="none";  
     $("#loadingWhenSave").remove();
 }

	