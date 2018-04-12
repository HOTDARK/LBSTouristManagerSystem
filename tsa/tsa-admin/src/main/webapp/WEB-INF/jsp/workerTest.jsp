<%--
  Created by IntelliJ IDEA.
  User: xiezh
  Date: 2018/3/23
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
登录测试：<<br>
<form action="api/worker/login.action" method="post">
    workerAccount<input type="text" name="workerAccount"/><br>
    workerPassword<input type="password" name="workerPassword"/><br>
    <input type="submit"/><br>
</form>

更新位置测试：<<br>
<form action="api/worker/updateLocation.action" method="post">
    token<input type="text" name="token"/><br>
    经度<input type="text" name="userLongitude"/><br>
    纬度<input type="text" name="userLatitude"/><br>
    <input type="submit"/>
</form>

工作人员结束求助信息测试：<br>
<form action="api/worker/finishHelpInfo.action" method="post">
    token<input type="text" name="token"/><br>
    帮助信息的id<input type="text" name="helpInfoId"/><br>
    <input type="submit"/>
</form>

工作人员获取发布的求助信息测试：<br>
<form action="api/worker//getHelpInfo.action" method="post">
    token<input type="text" name="token"/><br>
    <input type="submit"/>
</form>

工作人员获取获被帮助人的位置信息测试：<br>
<form action="api/worker//getTouristLocation.action" method="post">
    token<input type="text" name="token"/><br>
    <input type="submit"/>
</form>
</body>
</html>
