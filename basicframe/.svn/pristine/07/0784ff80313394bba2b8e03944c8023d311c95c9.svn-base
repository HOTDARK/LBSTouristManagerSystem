$(document).ready(function() {
	$("#save_editApp").on('click', function(event) {
		var ajaxFormOpt = {
			url : 'appUpgradeAction/saveOrUpdateApp.action',
			type : 'post',
			dataType : 'json',
			async : false,
			success : function(returnData) {
				var state=returnData.success;
				if (state) {
					Utils.closeModel("editApp");
					Utils.showMsg("success", "添加/编辑成功");
					dataTable.reload('userTable');
				}else{
					Utils.showMsg("error", "保存失败,"+returnData.msg);
				}
			},
			error : function() {
				Utils.showMsg("error", "保存失败");
			}
		};
		// 将表单设为ajax表单
		$('#editAppForm').ajaxForm(ajaxFormOpt);
		$('#editAppForm').submit();// 提交表单
	});
});
