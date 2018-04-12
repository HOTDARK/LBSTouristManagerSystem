$(function() {
	   var userName=$.trim($("#username").val()); 
	   var wname=$.trim($("#wname").val()); 
	   var start=$("#fbeginTime").val(); 
	   var end=$("#fendTime").val(); 
	   bulidData(userName,wname,start,end);
	});

    function doSel() {
	   var userName=$.trim($("#username").val()); 
	   var wname=$.trim($("#wname").val()); 
	   var start=$("#fbeginTime").val(); 
	   var end=$("#fendTime").val();
	   bulidData(userName,wname,start,end);
    }

	function bulidData(userName,wname,start,end) {
		dataTable({
			id : "userTable",
			lengthChange : false,
			sort : false,
			info : false,
			paginate : true,
			autoWidth : true,
			processing : true,
			primaryKey : "id",
			sAjaxDataProp : "data",
			check : false,
			singleSelect : false,
			url : "websvc/websvc_list.action",
			data : {
				"wname":wname,
				"username":userName,
				"startDate":start,
				"endDate":end
				},
			columns : [ {
				title : "接口名称",
				data : "wname"
			}, {
				title : "输入参数",
				data : "input",
				formatter : initInput
			}, {
				title : "返回参数",
				data : "output",
				formatter : initOutput
			}, {
				title : "调用开始时间",
				data : "startTime",
				formatter : initStart
			}, {
				title : "调用结束时间",
				data : "endTime",
				formatter : initEnd
			}, {
				title : "用户名",
				data : "username"
			},{
				title : "接口状态",
				data : "state",
				formatter : initState
			}, {
				title : "操作",
				data : "id",
				formatter : appCoperzionInit
			} ]
		});
	}
	
  function queryDetail(id) {
		Utils.showModel({
			id : "queryDetail",
			title : "查看详情",
			btns : [["save_queryDetail",'取消']],
			url : "websvc/websvc_detail.action?id=" + id
		});
	}
	
  function appCoperzionInit(data, type, obj) {
	var operateHtml="";
    operateHtml += "<button  class='btn btn-default btn-xs' onclick='queryDetail("+obj.id+")'><i class='fa fa-search'></i>查看详情</button>";
	return operateHtml;
  }
	
  function initState(data, type, obj) {
		var state=obj.state;
		if (state=="1") {
			return "正常";
		}else{
			return "异常";
		}
   }
	
  function initStart(data, type, obj) {
		var start=obj.startTime;
		return moment(start).format('YYYY-MM-DD HH:mm:ss');
   }
  
  function initEnd(data, type, obj) {
		var end=obj.endTime;
		return moment(end).format('YYYY-MM-DD HH:mm:ss');
 }
	
  function initInput(data, type, obj) {
	var input=obj.input;
	if (input.length<=13) {
		return input;
	}else{
		input=input.substring(0,12);
		return input+"...";
	 }
   }
  
  function initOutput(data, type, obj) {
	  var output=obj.output;
	  if (output.length<=13) {
		  return output;
	  }else{
		  output=output.substring(0,12);
		  return output+"...";
	  }
  }
	
