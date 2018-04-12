<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="sys.user.list.title"/></title>
    <link rel="stylesheet" href="plugins/ztree/css/zTreeStyle/zTreeStyle2.css"/>

    <script src="plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>
    <script src="js/common/common.tree.js"></script>
    <script src="js/common/common.tableFunction.js"></script>
    <script type="text/javascript" src="js/system/user.js"></script>
    <script type="text/javascript"
            src="/tsa/js/admin/admin.js"></script>
</head>

<body>
<!--左侧显示树，引用ztree-->
<div class="innter-left">
    <div class="table-header color01"><spring:message code="sys.org.orgInfo"/></div>
    <div id="orgTree" class="panel-body ztree"></div>
</div>
<!--右侧显示表格或者其它-->
<div class="row innter-content">
    <div class="ptool-bar">
        <span class="btn darkblue" onclick="addUser();"><i class="fa fa-plus"></i><spring:message
                code="sys.user.addUser"/></span>
        <span class="btn green" onclick="updateUserState('1');"><i class="fa fa-gavel"></i><spring:message
                code="sys.user.activateUser"/></span>
        <%--<span class="btn yellow" onclick="updateUserState('0');"><i class="fa fa-ban"></i><spring:message
                code="sys.user.frozenUser"/></span>--%>
        <span class="btn gray" onclick="deleteUser();"><i class="fa fa-ban"></i><spring:message
                code="public.txt.delete"/></span>
    </div>
    <div class="col-md-12">
        <form id="searchForm" class="form-inline search-bar" onsubmit="return false">
            <input type="hidden" id="selectedUserId"/>
            <input type="hidden" id="orgId" name="orgId"/>
            <div class="col-md-12">
                <input type="text" class="form-control" style="width:120px; float:left; margin-left:2px;"
                       id="searchUserName" name="userName" placeholder="用户姓名">
                <input type="text" class="form-control"
                       style="width:120px; float:left;  margin-left:2px;margin-right:2px;" id="searchTelephone"
                       name="telephone" placeholder="联系电话">
                <select class="selectpicker form-control" data-width="120px" id="searchState" name="state">
<%--                    <option value=""><spring:message code="public.select.please"/></option>
                    <option value="1"><spring:message code="public.select.effective"/></option>--%>
                    <option value="0" selected="selected"><spring:message code="public.select.einvalid"/></option>
                </select>
                <div class="btn blue" onclick="userListSearch();" id="searchId">
                    <i class="fa fa-search"></i><spring:message code="public.btn.query"/>
                </div>
            </div>
        </form>
    </div>
    <h5 class="innter-title"><span><spring:message code="sys.user.list.person"/></span></h5>
    <!--start 切换表格及列表呈现方式-->
    <%-- 	<ul class="nav nav-pills pull-right" style="margin-top: -45px;">
         <li id="listIcon" data-toggle="tooltip" title="<spring:message code='public.txt.list'/>" class="start" onclick="switchView(1)">
             <a href="#list" data-toggle="tab"><i class="fa fa-list-ul"></i></a>
         </li>
         <li id="mapIcon" data-toggle="tooltip" title="<spring:message code='public.txt.card'/>" class="end" onclick="switchView(0)">
             <a href="#map" data-toggle="tab"><i class="fonticon icon-card"></i></a>
         </li>
     </ul> --%>
    <!--end 切换表格及列表呈现方式-->
    <!-- 列表视图 -->
    <table id="userTable" class="table table-striped table-hover" cellspacing="0" width="100%"></table>
    <!-- 卡片视图 -->
    <div id="userCardList" style="margin-top: 10px;margin-bottom: 10px;"></div>

    <div id="userOrgEdit"></div>
    <div id="showPwdEdit"></div>
    <div id="editUser"></div>
</div>
</body>
</html>
