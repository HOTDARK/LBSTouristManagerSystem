

function showPb(i){
	var item=[];
	var filepath1=$("#filepath1").val();
	var filepath2=$("#filepath2").val();
	if(filepath1!=undefined){
		item.push("fileoper/downFile.action?filepath="+filepath1);
	}
	if(filepath2!=undefined){
		item.push("fileoper/downFile.action?filepath="+filepath2);
	}
	var pb = $.photoBrowser({
		  items: item,
		  initIndex:i
		});
	pb.open();
}