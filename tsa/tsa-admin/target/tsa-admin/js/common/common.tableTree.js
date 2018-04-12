var c_tableTree = {};
/**
 * 加载树
 * @param tableId
 *			  表格ID
 * @param url
 * 			  请求地址
 * @param field
 * 			  对应thead的字段（数组）
 * @param idKey
 * 			  主键字段名称
 * @param pidKey
 * 			  父级字段名称
 * @param leafKey
 * 			  是否是叶子节点字段名称
 * @param isLoadPage
 * 			  是否加载分页
 * @param iDisplayStart 
 * 			  分页起始数据
 * @param iDisplayLength
 * 			  分页跨度
 * @param checked 
 * 			  是否加载复选框
 * @param checkClass
 * 			  复选框class属性
 * @param checkLeaf
 * 			  选中父级时，是否选中叶子节点
 * @param format
 * 			  给对应字段设置对应的方法，返回相应的数据结果
 */
c_tableTree.loadTree = function (e){
	$("#"+e.tableId).find("tbody").remove();
	var length;
	var start;
	var total;
	var size;
	e.pid = !e.pid?0:e.pid;
	var url = "";
	if(e.url.indexOf("?") != -1){
		url = e.url+"&"+e.pidKey+"="+e.pid;
	}
	else{
		url = e.url+"?"+e.pidKey+"="+e.pid;
	}
	/**
	 * 加载表格数据（第一级数据）
	 * url:地址
	 * count:页码
	 */
	var urlParam = url.split("?")[1];
	var urlData = urlParam.split("&");	
	var dataStr = "{";
	for(var i=0; i<urlData.length; i++){
		dataStr += "\""+urlData[i].split("=")[0] +"\":\""+ urlData[i].split("=")[1] +"\",";
	}
	dataStr = dataStr.substring(0, dataStr.length - 1);
	dataStr += "}";
	url = url.split("?")[0];
	var dateParam = eval('('+dataStr+')');
	c_tableTree.loadData = function(url,count){
		Utils.ajax({
			url: url,
			type: 'POST',
			dataType: 'json',
			data:dateParam,
			success:function(data){
				$("#"+e.tableId).find("label").remove();
				if((data.length == 0 && !e.isLoadPage) || (e.isLoadPage && data.data.length == 0)){
					$("#"+e.tableId).append("<tbody><tr class='odd'><td class='dataTables_empty' valign='top' colspan='"+e.field.length+"' style='background-color: #fff; text-align: center;'>没有检索到数据</td></tr></tbody>");
				} else {
					var html = "<tbody style='background-color: #fff;'>";
					if(e.isLoadPage){
						html += c_tableTree.getHtml(data.data,e,0,0);
					}
					else{
						html += c_tableTree.getHtml(data,e,0,0);
					}
					html += "</tbody>";
					$("#"+e.tableId).append(html);
				}
				if(e.isLoadPage){
					$("nav").remove();
					c_tableTree.loadPage(data,count);
				}
			}
		});
	};
	
	//是否加载分页
	if(e.isLoadPage){
		if(e.iDisplayLength == undefined){
			e.iDisplayLength = 10;
		}
		if(e.iDisplayStart == undefined){
			e.iDisplayStart = 0;
		}
		c_tableTree.loadData(url + "?iDisplayLength="+e.iDisplayLength+"&iDisplayStart="+e.iDisplayStart,1);
	}
	else{
		c_tableTree.loadData(url,1);
	}
	
	/**
	 * 切换页码
	 * count：页码
	 */
	c_tableTree.changePage = function(count){
		$("#"+e.tableId).find("tbody").remove();
		if(e.isLoadPage){
			c_tableTree.loadData(url + "?iDisplayLength="+e.iDisplayLength+"&iDisplayStart="+((e.iDisplayStart + 1)*count*10 - 10),count);
		}
		else{
			c_tableTree.loadData(url,1);
		}
		$("nav").find(".hover").removeClass("hover");
	};
	
	/**
	 * 加载分页数据
	 * data:Ajax返回数据对象
	 * count：页码
	 */
	c_tableTree.loadPage = function(data,count){
		var page = "";
		if(data.data.length != 0){
			$(".none").remove();
			var count = parseInt(count);
			page += "<nav class='pull-right'>";
			page += "<ul class='pagination'>";
			if((data.iTotalRecords <= data.iDisplayLength) || count == 1) {
				page += "<li><a id='up' href='javascript:void(0)' class='disabled' ";
			} else {
				page += "<li><a id='up' onclick='c_tableTree.changePage(this.name)' ";
			}
			if(count > 1){
				page += "name='"+(count-1)+"' ";
			}
			else{
				page += "name='1' ";
			}
			page += ">前一页</a></li>";
			length = data.iDisplayLength;
			start = data.iDisplayStart;
			total = data.iTotalRecords;
			size = Math.ceil(total / length);
			if(size > 5){
				if(count - 2 <= 0){
					for(var i = 1; i <= 5; i++){
						page += "<li><a onclick='c_tableTree.changePage("+i+")' ";
						if(i == count){
							page += "class='hover'";
						}
						page += "count='"+i+"'>"+i+"</a></li>";
					}
				}
				else if(count + 2 >= size){
					for(var i = size - 4; i <= size; i++){
						page += "<li><a onclick='c_tableTree.changePage("+i+")' ";
						if(i == count){
							page += "class='hover'";
						}
						page += "count='"+i+"'>"+i+"</a></li>";
					}
				}
				else{
					for(var i = count - 2; i <= count + 2; i++){
						page += "<li><a onclick='c_tableTree.changePage("+i+")' ";
						if(i == count){
							page += "class='hover'";
						}
						page += "count='"+i+"'>"+i+"</a></li>";
					}
				}
			} else {
				for(var i = 1; i <= size; i++){
					page += "<li><a onclick='c_tableTree.changePage("+i+")' ";
					if(i == count){
						page += "class='hover'";
					}
					page += "count='"+i+"'>"+i+"</a></li>";
				}
			}
			if((data.iTotalRecords <= data.iDisplayLength) || size == count) {
				page += "<li><a id='next' href='javascript:void(0)' class='disabled' ";
			} else {
				page += "<li><a id='next' onclick='c_tableTree.changePage(this.name)' ";
			}
			
			if(count < size){
				page += "name='"+(count+1)+"' ";
			}
			else{
				page += "name='"+size+"' ";
			}
			page += ">后一页</a></li>";
			page += "</ul>";
			page += "</nav>";
		}
		$("#"+e.tableId).after(page);
	};
	
	/**
	 * 加载子节点
	 * id:父节点id值
	 */
	c_tableTree.loadLeaf = function (id){
		var span = $("#leaf_"+id);
		var url_leaf = e.url.split("?")[0];
		if(span.attr("leaf_statue") == "unload"){
			Utils.ajax({
				url: url_leaf+"?"+e.pidKey+"="+id.replace(/_/g,"."),
				type: 'POST',
				dataType: 'json',
				success:function(data){
					var s_parent = span.parent().parent();
					var html = "";
					if(e.isLoadPage){
						html += c_tableTree.getHtml(data.data,e,s_parent.attr("class"),parseInt(s_parent.attr("index"))+1);
					}
					else{
						html += c_tableTree.getHtml(data,e,s_parent.attr("class"),parseInt(s_parent.attr("index"))+1);
					}
					s_parent.after(html);
					span.attr("class","leaf_open");
					span.attr("leaf_statue","load");
				}
			});
		}
		else{
			if(span.attr("class") == "leaf_open"){
				$(".tr_"+id).hide();
				span.attr("class","leaf_close");
			}
			else{
				var index = parseInt(span.parent().parent().attr("index"))+1;
				var ids = "."+span.parent().parent().attr("class")+".tr_"+id;
				$(".tr_"+id+"[index='"+index+"']").each(function(){
					var s = $(this).find("span").attr("class");
				    if(s!=undefined){
				    	var lid = s.split("_")[1];
				    	if(s == "leaf_open"){
				    		$(".tr_"+id).show();
				    	}
				    	else{
				    		$(this).show();
				    	}
				    }
				    else{
				    	$(this).show();
				    }
				});
				span.attr("class","leaf_open");
			}
		}
		
	};
	
	/**
	 * 拼接tr
	 * data:Ajax返回数据对象
	 * e:参数对象
	 * pid:父级ID
	 * index:层级
	 */
	c_tableTree.getHtml = function (data,e,pid,index){
		pid = (pid+"").replace(/\./g,"_");
		var html = "";
		for(var i = 0; i < data.length; i++){
			var pidKey = (data[i][e.pidKey]+"").replace(/\./g,"_");
			var idKey = (data[i][e.idKey]+"").replace(/\./g,"_");
			if(pid == 0){
				html += "<tr index='1' class='tr_"+pidKey+"'>";
			}
			else{
				html += "<tr index='"+index+"' class='"+pid+" tr_"+pidKey+"'>";
			}
			for(var j = 0; j < e.field.length; j++){
				//是否加入复选框
				if(e.checked){
					if(j == 0){
						html += "<td>";
						if(pid == 0){
							html += "<label><input type='checkbox' index='1' onclick='c_tableTree.checked(this,\""+"tr_"+idKey+"\")' class='ace "+e.checkClass+" tr_"+pidKey+"' value='"+data[i][e.field[j]]+"'";
						}
						else{
							html += "<label><input type='checkbox' index='"+index+"' onclick='c_tableTree.checked(this,\""+"tr_"+idKey+"\")' class='ace "+e.checkClass+" "+pid+" tr_"+pidKey+"' value='"+data[i][e.field[j]]+"'";;
						}
						if(e.hiddenField){
							for(var f = 0; f < e.hiddenField.length; f++){
								html += e.hiddenField[f]+"='"+data[i][e.hiddenField[f]]+"'";
							}
						}
						html += " /><span class='lbl'></span></label>";
						html += "</td>";
					}
					else if(j == 1){
						html += "<td>";
						if(data[i][e.leafKey] == 0){
							html += c_tableTree.getLeftHead(index) + "<span class='leaf_close' leaf_statue='unload' onclick='c_tableTree.loadLeaf(\""+idKey+"\")' id='leaf_"+idKey+"'></span>";
						}
						else{
							html += c_tableTree.getLeftHead(index+1);
						}
						html += data[i][e.field[j]]+"</td>";
					}
					else{
						var d = "";
						for(var key in e.format){
							if(key == e.field[j]){
								var el = data[i];
								d = window[e.format[key]](el);
								break;
							}
							else{
								d = data[i][e.field[j]];
							}
						}
						html += "<td>"+d+"</td>";
					}
				}
				else{
					if(j == 0){
						html += "<td>";
						if(data[i][e.leafKey] == 0){
							html += c_tableTree.getLeftHead(index) + "<span class='leaf_close' leaf_statue='unload' onclick='c_tableTree.loadLeaf(\""+idKey+"\")' id='leaf_"+idKey+"'></span>";
						}
						else{
							html += c_tableTree.getLeftHead(index);
						}
						html += data[i][e.field[j]]+"</td>";
					}
					else{
						var d = "";
						for(var key in e.format){
							if(key == e.field[j]){
								var el = data[i];
								d = window[e.format[key]](el);
								break;
							}
							else{
								d = data[i][e.field[j]];
							}
						}
						html += "<td>"+d+"</td>";
					}
				}
			}
			html += "</tr>";
		}
		return html;
	};
	
	/**
	 * 子节点缩进
	 * index:层级
	 */
	c_tableTree.getLeftHead = function (index){
		var html = "";
		for(var i = 0; i < index; i++){
			html = html + "<label class='leaf_count'></label>";
		}
		return html;
	};
	
	/**
	 * checkbox全选
	 * check:传人参数对象的check值
	 * cls:要选择中的节点的tr的class属性
	 */
	c_tableTree.checked = function(check,cls){
		if(e.checkLeaf){
			if($(check).prop("checked")){
				$("input."+cls).prop("checked", true);
			}
			else{
				$("input."+cls).prop("checked", false);
			}
		}
	};
}