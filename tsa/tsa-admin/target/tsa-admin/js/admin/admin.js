function deleteUser() {
    var checkedUser = [];
    // userId =  $("#selectedUserId").val();
    msg = "确认删除";

    var userChecked = $("[name='checkbox']:checkbox:checked");
    if (userChecked.length > 0) {
        userChecked.each(function (index, ele) {
            if ($(ele).attr("value") != undefined) {
                checkedUser.push($(ele).attr("value"));
            }
        });
    }
    if (checkedUser.length == 0) {
        Utils.showMsg("warning", "请选择数据");
        return;
    }

    userIds = checkedUser.join(",");
    //alert(userIds);
    Utils.confirm({
        msg: msg, modalSure: function () {
            Utils.ajax({
                url: "admin/deleteUsers.action",
                data: {
                    userIds: userIds
                },
                success: function (data) {
                    if (data.message == "success") {
                        Utils.showMsg("success", "删除成功");
                        if (viewModel == 1) {//如果是列表视图,则刷新列表
                            $('#checkbox').prop('checked', false);
                            dataTable.reload("userTable");
//						$('#userTable').dataTable().fnDraw();
                        }
                    } else {
                        Utils.showMsg("success", "删除失败");
                    }
                },
                error: function () {
                    Utils.showMsg("error", "请求异常");
                }
            });
        }
    });


}


function addNotice() {

    msg = "<p>内容:</p> " +
        "<div><textarea style='border: 0px ;overflow:hidden; resize:none;background:transparent;' rows='10' cols='40' id='noticeRemark'></textarea><br/>" +
        "<div>type:<select id='noticeType'><option  value='1'>普通</option><option value='2'>重要</option></select></div><br/>";
    Utils.confirm({
        msg: msg, modalSure: function () {
            /*private Integer noticeId;
	        private Integer createdBy;
	        private String remark;
	        private Integer type;
	        private java.util.Date createdTime;
	        */
            noticeRemark = $("#noticeRemark").val();
            var options=$("#noticeType option:selected");
            noticeType = options.val();
           // alert(noticeType);
            if (noticeRemark == undefined) {
                document.getElementById("noticeRemark").innerHTML = "描述不能为空";
            } else {
                Utils.ajax({
                    url: "admin/addNotice.action",
                    data: {
                        remark: noticeRemark,
                        type: noticeType
                    },
                    success: function (data) {
                        if (data.message == "success") {
                            Utils.showMsg("success", "添加成功");
                        } else {
                            Utils.showMsg("success", "添加失败");
                        }
                    },
                    error: function () {
                        Utils.showMsg("error", "请求异常");
                    }
                });
            }

        }
    });
}

function updateNotice(notice_id) {
    text = $("#showText").val();
    msg = "<p>内容:</p> " +
        "<div><textarea id='editText' style='border: 0px ;overflow:hidden; resize:none;background:transparent;' rows='10' cols='40' id='noticeRemark'>"+text+"</textarea><br/>" +
        "<div>type:<select id='noticeType'><option  value='1'>普通</option><option value='2'>重要</option></select></div><br/>";

    Utils.confirm({
        msg: msg, modalSure: function () {
            /*private Integer noticeId;
	        private Integer createdBy;
	        private String remark;
	        private Integer type;
	        private java.util.Date createdTime;
	        */
            noticeRemark = $("#editText").val();
            var options=$("#noticeType option:selected");
            noticeType = options.val();
            //alert(noticeType);
            if (noticeRemark == undefined) {
                document.getElementById("editText").innerHTML = "描述不能为空";
            } else {
                Utils.ajax({
                    url: "admin/updateNotice.action",
                    data: {
                        remark: noticeRemark,
                        type: noticeType,
                        noticeId:notice_id
                    },
                    success: function (data) {
                        if (data.message == "success") {
                            Utils.showMsg("success", "修改成功");
                        } else {
                            Utils.showMsg("success", "修改失败");
                        }
                    },
                    error: function () {
                        Utils.showMsg("error", "请求异常");
                    }
                });
            }

        }
    });
}

function deleteNotice(noticeId){
    msg = "确认删除";
    Utils.confirm({
        msg: msg, modalSure: function () {
            Utils.ajax({
                url: "admin/deleteNotice.action",
                data: {
                    noticeId: noticeId
                },
                success: function (data) {
                    if (data.message == "success") {
                        Utils.showMsg("success", "删除成功");
                    } else {
                        Utils.showMsg("success", "删除失败");
                    }
                },
                error: function () {
                    Utils.showMsg("error", "请求异常");
                }
            });
        }
    });
}
$(function () {
    $("#searchId").click();
});