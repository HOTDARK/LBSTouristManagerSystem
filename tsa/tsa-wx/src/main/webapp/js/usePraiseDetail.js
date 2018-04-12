window.onload = function() {
	//发布
	if(state==0){
		$("#withdraw").show();
	}
	//撤回
	if(state==1){
		$("#release").show();
		$("#assign").show();
		$("#org").show();
		$("#orgDiv").show();
	}
	$.showLoading();
	Utils.ajax({
		url : 'praise/findOrg.action',
		data : {
			openid:openid
		},
		success : function(data) {
			var org = [];
			$.each(data, function(j, i) {
				var row = {
					'title' : i.orgName,
					'value' : i.orgCode
				};
				org.push(row);
			});
			$("#chooseOrg").select({
				title : "请选择部门",
				items : org
			});
			$.hideLoaing();
		}
	});
}
/**
 * 发布
 * @param obj
 */
function releaseCp(obj){
	$(obj).attr("disabled", true);
	$.confirm("确认发布吗？", function() {
		$.showLoading();
		Utils.ajax({
			url : 'praise/updatePraise.action',
			data : {
				"id" : $("#id").val(),
				"state" : 0,
				"openid" : openid,
				is : 3
			},
			success : function(data) {
				$.hideLoading();
				$(obj).attr("disabled", false);
				if (data.success) {
					$.toast("操作成功,3秒钟后即将跳转");
					setTimeout(function() {
						jumpPage("praise/findPraiseDetail.action?viewName=usePraise.jsp&idNum=3");
					}, 3000);
				} else {
					$.toast("操作失败");
				}
			}
		});
	}, function() {
		// 点击取消后的回调函数
	});
}
/**
 * 撤回
 * @param obj
 */
function withdrawCp(obj){
	$(obj).attr("disabled", true);
	$.confirm("确认撤回吗？", function() {
		$.showLoading();
		Utils.ajax({
			url : 'praise/updatePraise.action',
			data : {
				"id" : $("#id").val(),
				"state" : 1,
				"openid" : openid,
				is : 3
			},
			success : function(data) {
				$.hideLoading();
				$(obj).attr("disabled", false);
				if (data.success) {
					$.toast("操作成功,3秒钟后即将跳转");
					setTimeout(function() {
						jumpPage("praise/findPraiseDetail.action?viewName=usePraise.jsp&idNum=3");
					}, 3000);
				} else {
					$.toast("操作失败");
				}
			}
		});
	}, function() {
		// 点击取消后的回调函数
	});
}
//标注
function assignCp(obj){
	$(obj).attr("disabled", true);
	var org = $("#chooseOrg").attr("data-values");
	var id=$("#id").val();
	if (org == "") {
		$.toast("请选择部门", "forbidden");
		$(obj).attr("disabled", false);
		return;
	}
	$.showLoading();
	Utils.ajax({
		url : 'praise/updatePraise.action',
		data : {
			"id" : id,
			"orgCode" : org,
			"openid" : openid,
			is : 4
		},
		success : function(data) {
			$.hideLoading();
			$(obj).attr("disabled", false);
			if (data.success) {
				$.toast("操作成功,3秒钟后即将跳转");
				setTimeout(function() {
					jumpPage("praise/findPraiseDetail.action?viewName=usePraise.jsp&idNum=3");
				}, 3000);
			} else {
				$.toast("操作失败");
			}
		}
	});
}
function showPb(i){
	var item=[];
	var filepath1=$("#filepath"+0).val();
	var filepath2=$("#filepath"+1).val();
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