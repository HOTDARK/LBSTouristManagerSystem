var backAccount;
var permitStr;
var orgCode;
var uphone;
$(function() {
	gdcl();
});
/**
 * 获取手机验证码
 */
var backFlag = false;
var valibackFlag = false;
var reg = /^1[0-9]{10}$/;
function getValiCode(obj) {
	if (backFlag) {
		return;
	}
	if ($("#backAccount").val() == "") {
		$.toast("请输入工号", "forbidden");
		return;
	}
	if ($("#bindUserPhone").val() == "") {
		$.toast("请输入手机号", "forbidden");
		return;
	}

	if (!reg.test($("#bindUserPhone").val())) {
		$.toast("请输入正确的手机号", "forbidden");
		return;
	}
	backFlag = true;
	$(obj).attr("disabled", true);
	$(obj).addClass("bg-grey-999");
	$(obj).removeClass("bg-orange");
	var bindTime = 60;
	var bindInterval = setInterval(function() {
		if (bindTime == 0) {
			backFlag = false;
			$(obj).attr("disabled", false);
			$(obj).removeClass("bg-grey-999");
			$(obj).addClass("bg-orange");
			$(obj).html("点击重新发送");
			clearInterval(bindInterval);
		} else {
			$(obj).html("重新发送（" + bindTime + "）");
			bindTime--;
		}
	}, 1000);
	Utils.ajax({
		url : 'wxUserBase/getValiCode.action',
		data : {
			"phone" : $("#bindUserPhone").val()
		},
		success : function(data) {
			$.toptip(data.message, 'warning');
			if (data.success) {

				valibackFlag = true;

			} else {
				$.toptip(data.msg, 'warning');
			}
		}
	});
}

/**
 * 绑定后台帐号
 * 
 * @param obj
 */
function bindPhone(obj) {
	if ($("#backAccount").val() == "") {
		$.toast("请输入工号", "forbidden");
		return;
	}
	if ($("#phoneCode").val() == "") {
		$.toast("请输入手机验证码", "forbidden");
		return;
	}
	// if(!valibackFlag){
	// $.toptip("请先获取验证码", 'warning');
	// return;
	// }
	if ($("#bindUserPhone").val() == "") {
		$.toast("请输入手机号", "forbidden");
		return;
	}
	if (!reg.test($("#bindUserPhone").val())) {
		$.toast("请输入正确的手机号", "forbidden");
		return;
	}
	$(obj).attr("disabled", true);
	Utils.ajax({
		url : 'wxUserBase/bindRepairBackground.action',
		data : {
			"phone" : $("#bindUserPhone").val(),
			"openId" : openid,
			"valiCode" : $("#phoneCode").val(),
			"backAccount" : $("#backAccount").val(),
			"backType" : "vehicle"
		},
		success : function(data) {
			if (data.errCode == "SUC") {
				$.toast("绑定成功，3秒钟后即将跳转");
				setTimeout(function() {
					gdcl();
				}, 3000);
			} else {
				$.toast(data.errMsg, "forbidden");
			}
		}
	});
}

/**
 * 查询是否绑定后台帐号，如果已绑定，显示待处理列表
 */
function gdcl() {
	$.showLoading();
	Utils.ajax({
		url : 'wxUserBase/isBindBackground.action',
		data : {
			"openid" : openid,
			"backType" : "vehicle"
		},
		success : function(data) {
			$.hideLoading();
			uphone = data.uphone;
			var tab3Html = "";
			if (data.backOrgCode == null || data.backOrgCode == "") {
				isStore = false;
				$("#sourceL").show();
				$("#sourceK").hide();
				$("#bindUserPhone").val(uphone);
			} else {
				$("#sourceL").hide();
				$("#sourceK").show();
				backAccount = data.backAccount;
				permitStr = data.permitString;
				orgCode = data.backOrgCode;
				var operHtml = getOperHtml(permitStr);
				$("#sourceK").empty();
				$("#sourceK").html(operHtml);
			}
		}
	});
}

getOperHtml = function(permitStr) {
	var operHtml = "<div class='bg-white'>";
	if (permitStr.indexOf("008007") >= 0) {
		operHtml += "<div class='weui-cell line-d1e' onclick='jumpPage(\"safetyCheck/goSafetyCheck.action?account="
				+ backAccount
				+ "\")'>"
				+ "<div class='weui-cell__bd'>"
				+ "<a href='javascript:;' class='ft-grey-666'>安全检查</a>"
				+ "</div>"
				+ "<div class='weui-cell__ft'><img src='images/icon05.png' width='10' height='18' alt=''/></div>"
				+ "</div>";
	}
	if (permitStr.indexOf("008001") >= 0) {
		operHtml += "<div class='weui-cell line-d1e' onclick='jumpPage(\"maintain/goMaintain.action?backAccount="
				+ backAccount
				+ "&idNum=2\")'>"
				+ "<div class='weui-cell__bd'>"
				+ "<a href='javascript:;' class='ft-grey-666'>维修保养</a>"
				+ "</div>"
				+ "<div class='weui-cell__ft'><img src='images/icon05.png' width='10' height='18' alt=''/></div>"
				+ "</div>";
	}
	if (permitStr.indexOf("006014") >= 0) {
		operHtml += "<div class='weui-cell line-d1e' onclick='jumpPage(\"wx/jumpPage.action?viewName=trainningLog.jsp&idNum=2\")'>"
				+ "<div class='weui-cell__bd'>"
				+ "<a href='javascript:;' class='ft-grey-666'>训练日志</a>"
				+ "</div>"
				+ "<div class='weui-cell__ft'><img src='images/icon05.png' width='10' height='18' alt=''/></div>"
				+ "</div>";
	}
	operHtml += "</div>";
	return operHtml;
}