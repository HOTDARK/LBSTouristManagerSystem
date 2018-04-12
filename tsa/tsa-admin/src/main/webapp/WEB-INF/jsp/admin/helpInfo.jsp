<%--
  Created by IntelliJ IDEA.
  User: xiezh
  Date: 2018/3/20
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>求助信息</title>
    <script type="text/javascript" src="js/admin/admin.js"></script>
</head>
<body>
<c:forEach items="${helpInfos}" var="item">
    <div>
        <div class="card card-green" style="float: left">
            <div class="card-form"><span class="title"><label id="userCardName1" title="全局管理员">
        <div>求助信息</div>
    </label></span> <span
                    class="pull-right"><label id="checkbox_label" class="cd_hide"><div class="icheckbox_square-green"
                                                                                       style="position: relative;"><input
                    type="checkbox" id="checkbox" name="roleCheck" roleid="1"
                    style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"><ins
                    class="iCheck-helper"
                    style="position: absolute; top: -20%; left: -20%; display: block; width: 140%; height: 140%; margin: 0px; padding: 0px; background: rgb(255, 255, 255); border: 0px; opacity: 0;"></ins></div><span
                    class="lbl"></span></label></span>
                <div><textarea id="showText" readonly="readonly"
                               style="border: 0px ;overflow:hidden; resize:none;background:transparent;" rows="10"
                               cols="30">${item.remark}</textarea></div>
                <br/>
                <div>发布人：${item.userName}</div>
                <div>发布时间：${item.releaseTime}</div>
                <div>求助状态：
                    <c:if test="${item.state == 0}">
                        未完成
                    </c:if></div>
                <c:if test="${item.state != 0}">
                    已完成
                </c:if></div>
                <div class="ptool-bar">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <span class="btn yellow" onclick="changeHelpInfoState(${item.helpInfoId});"><i
                            class="fa fa-ban"></i>完成</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>
