<%@ taglib prefix="hd" uri="/hd-tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!--左侧显示树，引用ztree-->
<div class="row">
	<div class="col-sm-4 col-mini-4">
		<div class="panel panel-default">
	        <div id="oTree" class="panel-body ztree"></div>
       		<input type="hidden" id="newOrgId">
       		<input type="hidden" id="oldOrgId">
      	</div> 
    </div>
    <!--右侧显示表格或者其它-->
	<div class="col-sm-8 col-mini-8">
      	<div class="T_magin">
      		<div class="table-header color01"><spring:message code="sys.user.userInfo"/></div>
	    	<table class="table table-bordered" cellspacing="0" width="100%" >           
	        	<tbody>
		        	<tr>
		            	<td><spring:message code="sys.user.userAccount"/><spring:message code="public.txt.colon"/><span id="showUserAccount"></span></td> 
		            	<td><spring:message code="sys.user.userName"/><spring:message code="public.txt.colon"/><span id="showUserName"></span></td>
		            </tr>
		       	</tbody>
	       	</table> 
      	</div>
      	<div class="T_magin">
      		<div class="table-header color01"><spring:message code="sys.user.haveRoles"/></div>
          	<hd:table cls="table table-bordered" bSort="false" bFilter="false" bInfo="false" width="100%" bServerSide="true" height="auto" fnServerData="true" bPaginate="false" id="userCurrentRoleList">    	
	     		<hd:column sClass="center" sWidth="15px" title="<label><input type='checkbox' class='ace' onclick='currentRoleCheckedAll(this);' id='currentRoleCheck'/><span class='lbl'></span></label>" bSortable="false" field="roleId" mRender="currentRoleCheckInit" />
	     		<hd:column title="${orgTitle }" bSortable="false" field="orgName"></hd:column>
	     		<hd:column title="${roleTitle }" bSortable="false" field="roleName"></hd:column>
	     	</hd:table>
      	</div>
     	<div class="T_magin"> 
     		<div class="table-header color01"><spring:message code="sys.user.userdept.change"/></div>
      		<table class="table table-bordered" cellspacing="0" width="100%" >           
	            <tbody>
	            	<tr>
	            		<td>
	            			<spring:message code="sys.user.userdept.now"/><spring:message code="public.txt.colon"/><span id="oldOrg" style="color:red;"></span><br>
	            			<spring:message code="sys.user.userdept.change.to"/><spring:message code="public.txt.colon"/><span id="newOrg"></span>
	            		</td>
	            	</tr>
	            </tbody>
        	</table>
      	</div>
   	</div>
</div>
