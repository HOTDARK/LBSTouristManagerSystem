<%--
  Created by IntelliJ IDEA.
  User: xiezh
  Date: 2018/3/17
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=rzPiSAaTNGgNNoH7MbSzGS11Z3PtNtKc"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=rzPiSAaTNGgNNoH7MbSzGS11Z3PtNtKc"></script>

    <script type="text/javascript"
            src="/tsa/js/map/mapOption.js"></script>
    <style type="text/css">
        html {
            height: 100%;
            width: 100%
        }

        body {
            height: 100%;
            widht: 100%;
            margin: 0px;
            padding: 0px;
            margin: 0px;
        }

        #mapMain {
            height: 100%;
            width: 100%;
        }

        #container {
            float: left;
            height: 100%;
            width: 80%;
        }

        #mapRight {
            float: left;
            height: 50px;
            width: 20%;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
    <br>
    <input type="text" id="lonlat" value="0.0" readonly="readonly" style="border:0px"/>
    <input type="text" id="lonlat2" value="0.0" readonly="readonly" style="border:0px"/><br>
    <div>
        <div class="col-md-12">
            <div class="ptool-bar">
                <c:forEach items="${places}" var="item">
                    <span class="btn darkblue" onclick="addPlace(${item.id})">
                        <i class="fa fa-plus"></i>添加${item.mapName}</span>
                </c:forEach>
            </div>
        </div>
    </div>
    <div style="height: 800px; width: 100%" id="mapMain">
        <div id="container" style="height: 800px; width: 1300px" style="float: left"></div>
        <div id="mapRight">
            描述:<textarea id="remark" rows="5" cols="20"></textarea>><br>
            <div class="col-md-12">

                <div class="ptool-bar">
                    &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;
                    <span class="btn darkblue" onclick="clickEdit(1)">
                        <i class="fa fa-edit" ></i>修改</span>
                    &nbsp;&nbsp;&nbsp;
                    <span class="btn darkblue" onclick="clickEdit(2)">
                         <i class="fa fa-edit"></i>删除</span>
                </div>
            </div>
        </div>
    </div>
    <br>
    <input type="hidden" value="${currentUser.userId}" id="currentUserID"/>
    <input type="hidden" value="" id="editId"/>
</body>
</html>
