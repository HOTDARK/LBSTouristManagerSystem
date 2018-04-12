/**
 * 服务器监控脚本
 */
//内存
var ram="";
//CPU
var cpu="";
//JVM
var jvm="";	
$(document).ready(function() {
	 setting();
	 loadTabData() ;
	});
	function setting(){
		 $("#cpuh-slider").slider({
			    orientation: "horizontal",
			    range: "min",
			    min: 0,
			    max: 100,
			    value: cpu,
			    slide: function (event, ui) {
			        var cpu=ui.value;
			        $("#cpuSpanId").html(cpu+"%");
			    	$("#cpuhandleId").attr("style","width:"+cpu+"%;");
					$("#cpucornerId").attr("style","left:"+cpu+"%;");
			    }
			});

			 $("#ramh-slider").slider({
			    orientation: "horizontal",
			    range: "min",
			    min: 0,
			    max: 100,
			    value: cpu,
			    slide: function (event, ui) {
			        var ram=ui.value;
			        $("#ramSpanId").html(ram+"%");
			    	$("#ramhandleId").attr("style","width:"+ram+"%;");
					$("#ramcornerId").attr("style","left:"+ram+"%;");
			    }
			});
			 $("#jvmh-slider").slider({
			    orientation: "horizontal",
			    range: "min",
			    min: 0,
			    max: 100,
			    value: cpu,
			    slide: function (event, ui) {
			        var jvm=ui.value;
			        $("#jvmSpanId").html(jvm+"%");
			    	$("#jvmhandleId").attr("style","width:"+jvm+"%;");
					$("#jvmcornerId").attr("style","left:"+jvm+"%;");
			    }
			});
	}


	function loadTabData() {
		$.fn.jqLoading({ height: 100, width: 240, text: "正在加载中，请耐心等待...." });
		$.ajax({
					type : 'POST',
					url : "serverMonitor/info.action",
					dataType : 'json',
					success : function(result) {
						$.fn.jqLoading("destroy");
						var data = result.data;
						var _target;
						var html;
						for ( var index in data) {
							if (index == 'diskInfos') {
								//html = createDiskInfosHtml(index, data[index]);
								html = createDiskInfosHtml(data[index]);
							} else if (index == 'cpuInfos') {
								html = createCpuInfosHtml(data[index]);
							} else {
								html = data[index];
							}
							_target = $("#" + index);
							if (_target)
								_target.html(html);
						}
						var sun=$("#jvmTotalMem").html()-$("#jvmFreeMem").html();
						fchart("JVM","M", "剩余容量", $("#jvmFreeMem").html(), "已经使用", sun, "jvm", "jvmchart2","2");
						var memPercent = (100 * data['usedMem'] / data['totalMem']) .toFixed(1) + "%";
						var swapPercent = (100 * data['usedSwap'] / data['totalSwap']) .toFixed(1) + "%";
						$("#mem_percent").css("width", memPercent);
						$("#swap_percent").css("width", swapPercent);
						$("#memPercent").html(memPercent);
						$("#swapPercent").html(swapPercent);
						cpu=result.cpu;
						ram=result.ram;
						jvm=result.jvm;
						$("#cpuSpanId").html(cpu+"%");
						$("#cpuhandleId").attr("style","width:"+cpu+"%;");
						$("#cpucornerId").attr("style","left:"+cpu+"%;");
						
						$("#ramSpanId").html(ram+"%");
						$("#ramhandleId").attr("style","width:"+ram+"%;");
						$("#ramcornerId").attr("style","left:"+ram+"%;");
						
						
						$("#jvmSpanId").html(jvm+"%");
						$("#jvmhandleId").attr("style","width:"+jvm+"%;");
						$("#jvmcornerId").attr("style","left:"+jvm+"%;");
						
						fchart("CPU使用情况","%", "剩余",(100-result.cpuUsage), "已使用",result.cpuUsage, "cpu", "cpuchart","1");
						fchart("内存使用情况","%", "剩余",(100-result.serverUsage), "已使用",result.serverUsage, "ram", "ramchart","1");
						fchart("JVM使用情况","%", "剩余",(100-result.JvmUsage), "已使用",result.JvmUsage, "jvm", "jvmchart","1");
					},
					 error: function (XMLHttpRequest, textStatus, errorThrown) {
						 $.fn.jqLoading("destroy");
				         alert(errorThrown); 
				 	} 
				});
	}
	
	function fchart(name,pen,label1,value1,label2,value2,charId,divId,type){
	  	var text_chart="('<chart baseFontSize=\"12\" pieRadius=\"70\" caption=\""+name+"\" numberSuffix=\""+pen+"\">";
	  	text_chart+="<set value=\""+value1+"\" label=\""+label1+"\" color=\"AFD8F8\" />";
	  	if (charId=="cpu") {
			if(value2>=parseInt(cpu)){
			text_chart+="<set value=\""+value2+"\" label=\""+label2+"\" color=\"ff0000\" />";
			}else{
			text_chart+="<set value=\""+value2+"\" label=\""+label2+"\" color=\"46AF6D\" />";
			}
		}else if(charId=="ram"){
			if(value2>=parseInt(ram)){
			text_chart+="<set value=\""+value2+"\" label=\""+label2+"\" color=\"ff0000\" />";
			}else{
			text_chart+="<set value=\""+value2+"\" label=\""+label2+"\" color=\"46AF6D\" />";
			}
		}else if(charId=="jvm"&&type=="1"){
			if(value2>=parseInt(jvm)){
			text_chart+="<set value=\""+value2+"\" label=\""+label2+"\" color=\"ff0000\" />";
			}else{
			text_chart+="<set value=\""+value2+"\" label=\""+label2+"\" color=\"46AF6D\" />";
			}
		}else if (charId=="jvm"&&type=="2") {
			text_chart+="<set value=\""+value2+"\" label=\""+label2+"\" color=\"46AF6D\" />";
		}
	  	text_chart+="</chart>')";
	  	if (type=="1") {
	  		var chart = new FusionCharts("plugins/FusionCharts/Pie3D.swf", charId, "100%", "200");
		}else if(type=="2"){
			var chart = new FusionCharts("plugins/FusionCharts/Pie3D.swf", charId, "100%", "300");
		}
		  chart.setDataXML(text_chart);		   
		  chart.render(divId);  
	 }

	function createCpuInfosHtml(datas) {
		var html = "";
		for ( var index in datas) {
			var info = datas[index];
			var temp=parseInt(index);
			if(!isNaN(temp)){
			var title = info['vendor'] + " " + info['model'];
			var usedPercent = info['used'];
			html = html + "<div class=\"form_tltle\">第 "
					+ (parseInt(index) + 1) + " 块CPU</div>";
			html = html
					+ "<table border=\"0\" cellpadding=\"6\" cellspacing=\"6\" class=\"form2column\">";
			html = html + "<tr><td class=\"label\">CPU信息:</td>";
			html = html + "<td>" + title + "</td></tr>";
			html = html + "<tr><td class=\"label\">使用情况:</td>";
			html = html
					+ "<td><div class=\"block_stat\"><div style=\"background:#46AF6D;height:15px; width:"+usedPercent+";\">&nbsp;</div></div> &nbsp;使用率"+getProp("public.txt.colon")
					+ usedPercent + "</td>";
			html = html + "</table>";
			}
		}
		return html;
	}

//	function createDiskInfosHtml(type, datas) {
//		var html = "";
//		var name, title, availSize, freeSize, percentSize;
//		for ( var index in datas) {
//			var info = datas[index];
//			var temp=info['devName'];
//			if (typeof(temp) != "undefined"){ 
//			name = "磁盘 " + info['devName'];
//			totalSize = info['totalSize'];
//			availSize = info['availSize'];
//			type = info['type'];
//			percentSize = 150 * (info['usedSize'] / info['totalSize']);
//			html = html + "<table class=\"block_table\">";
//			html = html + "<tr><th width=\"20%\">&nbsp;</th><th width=\"80%\">"
//					+ name + "</th></tr>";
//			html = html + "<tr>";
//			html = html + "<td rowspan=\"2\" class=\"block_icon_disk\"></td>";
//			html = html
//					+ "<td><div class=\"block_stat\"><div style=\"background:#46AF6D;height:15px; width:"+percentSize+"px;\">&nbsp;</div></div></td>";
//			html = html + "</tr>";
//			html = html + "<tr><td>" + availSize +type+" 可用 共" + totalSize
//					+type+" </td></tr>";
//			html = html + "</table>";
//			}
//		}
//		return html;
//	}
	
	function createDiskInfosHtml(datas){
		var html = "";
		var name;
		var availSize;
		var totalSize;
		var percentSize;
		for(var index in datas){
			var info = datas[index];
			var temp=info['devName'];
			if (typeof(temp) != "undefined"){ 
			name = "磁盘 "+info['devName'];
			totalSize = info['totalSize'];
			availSize = info['availSize'];
			type = info['type'];
			usedSize=(totalSize-availSize);
			percentSize = 150 * (usedSize/totalSize);
			html = html + "<table class=\"block_table\">";
			html = html + "<tr><th width=\"20%\">&nbsp;</th><th width=\"80%\">"+name+"</th></tr>";
			html = html + "<tr>";
			html = html + "<td rowspan=\"2\" class=\"block_icon_disk\"></td>";
			if(percentSize>=80){
			var title= percentSize.toFixed(2);
			html = html + "<td><div title=\"已使用"+getProp("public.txt.colon")+title+"%\""+" class=\"block_stat\"><div style=\"background:#ff0000;height:15px; width:"+percentSize+"px;\">&nbsp;</div></div></td>";
			}else{
			var title= percentSize.toFixed(2);
			html = html + "<td><div title=\"已使用"+getProp("public.txt.colon")+title+"%\""+" class=\"block_stat\"><div style=\"background:#46AF6D;height:15px; width:"+percentSize+"px;\">&nbsp;</div></div></td>";
			}
			html = html + "</tr>";
			html = html + "<tr><td>"+availSize+type+" 可用    &nbsp;&nbsp; 共"+totalSize+type+" </td></tr>";
			html = html + "</table>";
			}
		}
		return html;
	}
 
	//保存按钮
	 function modifySer(){
	 var arr = new Array();
	 var cpu=$("#cpuSpanId").html().substring(0,$("#cpuSpanId").html().length-1);
	 var ram=$("#ramSpanId").html().substring(0,$("#ramSpanId").html().length-1);
	 var jvm=$("#jvmSpanId").html().substring(0,$("#jvmSpanId").html().length-1);
	 arr.push(cpu,ram,jvm);
	  	Utils.confirm({
	 		title : "温馨提示",
	 		msg : "确认更新已设置的数据?",
	 		modalSure : function() {
	 			Utils.ajax({
	 				url: "serverMonitor/modifySer.action",
	                 data:{"arr":arr.join(",")},
	 				dataType: "json",
	 				success : function(data) {
	 					 if(data.flag){
	 					 Utils.showMsg("success", "更新成功！");
	 					 loadTabData();
	 					 }else{
	 					 Utils.showMsg("error", "更新失败！");
	 					 }
	 				},
	 				error : function() {
	 					Utils.showMsg("error", "请求异常");
	 				}
	 			});
	 		}
	 	});
	 }
	 