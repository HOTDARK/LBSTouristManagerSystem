/**
 * 审核通过
 */
window.onload=function(){
//	var curDate=new Date();
//	var minDate=new Date(curDate.getTime()+24*60*60*1000*ad).format("yyyy-MM-dd");
//	var maxDate=new Date(curDate.getTime()+24*60*60*1000*mad).format("yyyy-MM-dd");
//	$("#invoicedate").datetimePicker({
//		title: '开票日期',
////		min:minDate,
//		max:new Date(),
//	    yearSplit:'年',
//	    monthSplit:'月',
//	    dateSplit:'日',
//		onChange: function (picker, values, displayValues) {
//			var orderRepairTime=values[0]+"-"+values[1]+"-"+values[2];
//            $("#invoiceDate").val(orderRepairTime);
//		}
//	});
	$("#invoicedate").calendar({
		dateFormat:"yyyy-mm-dd",
		maxDate: new Date(),
		onChange: function (p, values, displayValues){
//			alert($("#invoicedate").val())
			$("#invoiceDate").val(values)
		}
	});
}
function audit(obj) {
	$(obj).attr("disabled", true);
	var amount=$("#amount").val();
	var invoiceDate=$("#invoicedate").val();
	var invoiceNum=$("#invoiceNum").val();
	if (amount == "") {
		$.toast("请输入金额", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	if (invoiceNum == null || invoiceNum == "") {
		$.toast("请输入发票号", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	if (invoiceDate==null || invoiceDate == "") {
		$.toast("请选择开票日期", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	$.confirm("确定通过？", function() {
		Utils.ajax({
			url : 'buytimeBack/audit.action',
			data : {
				"id" : backId,
				"userAccount" : backAccount,
				"amount":amount,
				"invoiceDate":invoiceDate,
				"invoiceNum":invoiceNum
			},
			success : function(data) {
				$(obj).attr("disabled", false);
				if (data.errCode == "SUC") {
					$.toast("操作成功");
					setTimeout(function() {
						jumpPage('wx/jumpPage.action?viewName=buytime.jsp&idNum=3');
					}, 2000);
				} else {
					$.toast(data.errMsg, "forbidden");
				}
			}
		});
		  }, function() {
		  //点击取消后的回调函数
		  });
	
}

/**
 * 驳回
 */
function reject(obj) {
	$(obj).attr("disabled", true);

	$.prompt({
		title : "驳回报名",
		text : '请输入理由',
		input : '',
		empty : false, // 是否允许为空
		onOK : function(input) {
			Utils.ajax({
				url : 'buytimeBack/reject.action',
				data : {
					"id" : backId,
					"reason" : input,
					"userAccount" : backAccount
				},
				success : function(data) {
					$(obj).attr("disabled", false);
					if (data.errCode == "SUC") {
						$.toast("操作成功！");
						setTimeout(function() {
							jumpPage('wx/jumpPage.action?viewName=buytime.jsp&idNum=3');
						}, 2000);
					} else {
						$.toast(data.errMsg, "forbidden");
					}
				}
			});
		},
		onCancel : function() {

		}
	});
}
