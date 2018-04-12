<%@ page import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: xiezh
  Date: 2018/3/19
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公告</title>
    <script type="text/javascript" src="js/admin/admin.js"></script>
</head>
<body>
<div class="ptool-bar">
    <span class="btn darkblue" onclick="addNotice();"><i class="fa fa-plus"></i>新增公告</span>
</div>
<c:forEach items="${notices}" var="notice">
    <div>
        <div class="card card-green" style="float: left">
            <div class="card-form"><span class="title"><label id="userCardName1" title="全局管理员">
        <div>公告</div>
    </label></span> <span
                    class="pull-right"><label id="checkbox_label" class="cd_hide"><div class="icheckbox_square-green"
                                                                                       style="position: relative;"><input
                    type="checkbox" id="checkbox" name="roleCheck" roleid="1"
                    style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"><ins
                    class="iCheck-helper"
                    style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div><span
                    class="lbl"></span></label></span>
                <div><textarea id="showText" readonly="readonly" style="border: 0px ;overflow:hidden; resize:none;background:transparent;" rows="10" cols="30">${notice.remark}</textarea></div>
                <br/>
                <div>发布人：${notice.userName}</div>
                <div>发布时间：${notice.strTime}</div>
                <div class="ptool-bar">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span class="btn yellow" onclick="updateNotice(${notice.noticeId});"><i class="fa fa-ban"></i>修改</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span class="btn gray" onclick="deleteNotice(${notice.noticeId});"><i class="fa fa-ban"></i>删除</span>
                </div>
            </div>
        </div>

    </div>
</c:forEach>


</body>
</html>
