<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!--左侧显示树，引用ztree-->
<div class="row">
	<input type="hidden" id="roleUid" value="${user.userId }">
	<div class="col-sm-4">
		<div class="panel panel-default">
	    	<div class="panel-body">
	        	<ul id="orgDemo" class="ztree"></ul>
	        </div> 
	    </div>
    </div>
    <!--右侧显示表格或者其它-->
    <div class="col-sm-8">
    	<div style="display: none;" id="orgIdHtml"></div>
      	<div class="T_magin">
        	<div class="table-header color01">
            	<spring:message code="sys.user.userInfo"/>
			</div>
      		<table class="table  table-bordered table-hover" cellspacing="0" width="100%" >
	           	<tbody>
	            	<tr>
	            		<td><spring:message code="sys.user.userAccount"/><spring:message code="public.txt.colon"/>${user.userAccount}</td>
	            		<td><spring:message code="sys.user.userName"/><spring:message code="public.txt.colon"/>${user.userName}</td>
	            	</tr>
	            </tbody>
            </table> 
      	</div>
       	<div class="T_magin">
       		<div class="table-header color01"><spring:message code="sys.user.haveRoles"/></div>
      		<table class="table table-striped table-bordered table-hover" cellspacing="0" width="100%" >
	           	<thead>
	            	<tr><td><spring:message code="sys.user.role.org"/></td><td><spring:message code="sys.user.role.name"/></td></tr>
	            </thead>
            	<tbody id="haveRoles"></tbody>
            </table> 
      	</div>
       	<div class="T_magin">
       		<div class="table-header color01"><spring:message code="sys.user.choose.role"/></div>
      		<table class="table table-striped table-bordered table-hover" cellspacing="0" width="100%" >
            	<tbody id="orgRoles"></tbody>
            </table> 
      	</div>
   	</div>
</div>