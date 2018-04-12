/**
 * 安全检查详情
 */

/**
 * 检查确认
 * @param id
 */
function affirm(id){
	$.confirm({
		title: '检查确认',
		text: '检查结果完全属实，且无异议？',
		onOK: function () {
			Utils.ajax({
				url: 'safetyCheck/confirmCheckResult.action',
				data: {"id":id, "checkResult":1},
				success: function(data){
					if (data.success) {
						document.location.reload();
					}else{
						$.alert("确认失败，请稍后尝试");
					}
				}
			});
	 	},
	 	onCancel: function () {
	 		
	 	}
	});
}

/**
 * 检查驳回
 * @param id
 */
function reject(id){
	$("#id").val(id);
	$("#inconformtyReason").val(null);
	$("#rejectCheck").popup();
}

function saveReject(){
	var id = $("#id").val();
	var inconformtyReason = $("#inconformtyReason").val();
	if (inconformtyReason==null || inconformtyReason=='') {
		$.alert("请填写驳回原因");
		return false;
	}
	Utils.ajax({
		url: 'safetyCheck/confirmCheckResult.action',
		data: {"id":id, "checkResult":2, "inconformtyReason":inconformtyReason},
		success: function(data){
			if (data.success) {
				document.location.reload();
			}else{
				$.alert("驳回失败，请稍后尝试");
			}
		}
	});
}