
function mark(id){
	var el = $("#svg-"+id);
	
	el.find("circle").attr("stroke","red");
	el.find("rect").attr("stroke","red");
	el.find("text").attr("stroke","red");
	
	var path = el.find("path");
	if(path.length>0){
		path.attr("stroke","red");
		path.each(function(){
			var url = $(this).attr("marker-end");
			if(url){
				markLineEnd(url.replace("url(","").replace(")","").replace(new RegExp("\"","gm"),""));
			}
			
		});
		
	}
}

function markLineEnd(id){
	$(id).find("path").attr("stroke","red").attr("fill","red");
	
}

for(var i=0;i<trace.childs.length;i++){
	var item = trace.childs[i];
	mark(item.elementId);
	
	if(item.subProcess){
		console.log();
		$("#svg-"+item.elementId).css("cursor","hand");
		$("#svg-"+item.elementId).click(item.subProcess,function(event){
			var data = event.data;
			console.log(data);;
			$("#trace").val(JSON.stringify(data));
			$("#processId").val(data.processId);
			$("#version").val(data.version);
			$("#form").submit();
		});
	}
}