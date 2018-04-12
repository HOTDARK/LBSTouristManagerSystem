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
<form action="api/tourist/login.action" method="post">
    <input type="text" name="telphone"/><br>
    <input type="submit"/><br>
</form>

更新位置测试：<<br>
<form action="api/tourist/updateLocation.action" method="post">
    token<input type="text" name="token"/><br>
    经度<input type="text" name="userLongitude"/><br>
    纬度<input type="text" name="userLatitude"/><br>
    <input type="submit"/>
</form>

添加求助信息测试：<<br>
<form action="api/tourist/addHelpInfo.action" method="post">
    token<input type="text" name="token"/><br>
    发布地点<input type="text" name="releasePlace"/><br>
    描述<input type="text" name="remark"/><br>
    <input type="submit"/>
</form>
游客结束求助信息测试：<br>
<form action="api/tourist/finishHelpInfo.action" method="post">
    token<input type="text" name="token"/><br>
    帮助信息的id<input type="text" name="helpInfoId"/><br>
    <input type="submit"/>
</form>

游客获取自己发布的求助信息测试：<br>
<form action="api/tourist//getSelfHelpInfo.action" method="post">
    token<input type="text" name="token"/><br>
    <input type="submit"/>
</form>

游客获取获取工作人员位置信息测试：<br>
<form action="api/tourist//getWorkerLocation.action" method="post">
    token<input type="text" name="token"/><br>
    <input type="submit"/>
</form>

游客获取景区地理位置信息测试：<br>
<form action="api/tourist//getPlace.action" method="post">
    token<input type="text" name="token"/><br>
    <input type="submit"/>
</form>
</body>
</html>
