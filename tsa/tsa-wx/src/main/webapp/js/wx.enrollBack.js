window.onload=function(){
	$("#invoiceDate").calendar(
			{
				dateFormat: 'yyyy年mm月dd日',
				onClose:function (p){
			}
	});
}
/**
 * 审核通过
 */
function audit(obj) {
	$(obj).attr("disabled", true);
	$.confirm("确定通过？", function() {
		var invoiceNum=$("#invoiceNum").val();
		var invoiceDate=$("#invoiceDate").val();
		var reg=/^[0-9a-zA-Z]*$/g;
		if(invoiceNum==""){
			$.toast("请输入发票号","forbidden");
			$(obj).attr("disabled", false);
			return;
		}
		if (!reg.test(invoiceNum)) {
			$.toast("发票号只能输入字母和数字，请重新输入","forbidden");
			$(obj).attr("disabled", false);
			return;
		}
		if(invoiceDate==""){
			$.toast("请选择开票日期","forbidden");
			$(obj).attr("disabled", false);
			return;
		}
		Utils.ajax({
			url : 'enrollBack/audit.action',
			data : {
				"id" : id,
				"userAccount" : backAccount,
				"invoiceNum":invoiceNum,
				"invoiceDate":invoiceDate
			},
			success : function(data) {
				$(obj).attr("disabled", false);
				if (data.errCode == "SUC") {
					$.toast("操作成功");
					setTimeout(function() {
						jumpPage('wx/jumpPage.action?viewName=enroll.jsp&idNum=3');
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
				url : 'enrollBack/reject.action',
				data : {
					"id" : id,
					"reason" : input,
					"userAccount" : backAccount
				},
				success : function(data) {
					$(obj).attr("disabled", false);
					if (data.errCode == "SUC") {
						$.toast("操作成功！");
						setTimeout(function() {
							jumpPage('wx/jumpPage.action?viewName=enroll.jsp&idNum=3');
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
