<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script src="js/common/jquery.form.js" ></script> 
<style type="text/css"> 
._box 
{ 
position: relative; 
width: 106px; 
height: 101px; 
background-repeat: no-repeat; 
background-position: 0 0; 
background-attachment: scroll; 
line-height: 37px; 
text-align: center; 
color: white; 
cursor: pointer; 
overflow: hidden; 
z-index: 1; 
text-align: center; 
margin-left:200px; 
margin-right:auto; 
} 
._box input 
{ 
position: absolute; 
width: 106px; 
height: 131px;  
line-height: 40px; 
font-size: 23px; 
opacity: 0; 
filter: "alpha(opacity=0)"; 
filter: alpha(opacity=0); 
-moz-opacity: 0; 
left: -5px; 
top: -2px; 
cursor: pointer; 
z-index: 2; 
text-align: center; 
margin-left:0px; 
margin-right:auto;
} 
</style> 

<form class="form-horizontal" id="editUserForm" onsubmit="return false">
<input type="hidden" name="userId" id="userId" value="${user.userId}">
<input type="hidden" name="oldUserState" id="oldUserState" value="${user.state}">
<input type="hidden" name="orgId" id="orgId" value="${orgId}">
<input type="hidden" name="oldImageUri" id="userPhoto" value="${user.userPhoto}">
	<div class="form-group">
		<div class="_box"> 
			<input type="file" name="file" id="file" onchange="changePhoto(this)" alt="<spring:message code='sys.user.choose.photo' />"/>
			<c:choose>
			<c:when test="${not empty path}">
	        <img style="width: 106px;height: 101px;" alt="<spring:message code='sys.user.choose.photo' />" src="${path}" id="imgHeadPhoto" >
			</c:when>
			<c:otherwise>
		    <img style="width: 106px;height: 101px;" alt="<spring:message code='sys.user.choose.photo' />" src="images/up.gif" id="imgHeadPhoto"  onerror="this.src='images/up.gif'">
			</c:otherwise>
			</c:choose>
		</div> 
		<div style="padding-left: 170px;"><spring:message code="sys.user.please.photo" /><spring:message code="public.txt.bracket.left"/><spring:message code="public.txt.support" />&nbsp;jpg,gif,png<spring:message code="public.txt.bracket.right"/></div>
	</div>
	<div class="form-group">
		<label for="author" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.user.userAccount"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" maxLengthInput="30" class="form-control" id="userAccount" name="userAccount" check-type="required doublebyte specialchar" value="${user.userAccount}">
		</div>
	</div>
	<div class="form-group">
		<label for="author" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.user.userName"/><spring:message code="public.txt.colon"/>
			</label>
		<div class="column col-sm-6">
			<input type="text" maxLengthInput="20" class="form-control" id="userName" name="userName" check-type="required doublebyte specialchar" value="${user.userName}">
		</div>
	</div>
	<div class="form-group">
		<label for="author" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.user.userPwd"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="password" maxLengthInput="16" class="form-control" id="userPwd" name="userPwd" check-type="doublebyte specialchar"  placeholder="<spring:message code='sys.user.userPwd.placeholder' />" value="${user.userPwd}">
		</div>
	</div>
	<div class="form-group">
		<label for="state" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.user.state"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<select name="state" id="state"  class="form-control selectpicker">
				<option value="1" ${user.state=="1"?"selected='selected'":""}><spring:message code="public.select.effective" /></option>
				<option value="0" ${user.state=="0"?"selected='selected'":""}><spring:message code="public.select.einvalid" /></option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="sex" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.user.sex"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<select name="sex" id="sex"  class="form-control selectpicker">
				<option value="1" ${user.sex=="1"?"selected='selected'":""}><spring:message code="public.select.male" /></option>
				<option value="0" ${user.sex=="0"?"selected='selected'":""}><spring:message code="public.select.female" /></option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="author" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.user.telephone"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" maxLengthInput="20" class="form-control" id="telephone" name="telephone" value="${user.telephone}" check-type="tel_or_phone">
		</div>
	</div>
	<div class="form-group">
		<label for="author" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.user.email"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" maxLengthInput="60" class="form-control" id="email" name="email" value="${user.email}" check-type="required mail">
		</div>
	</div>
	<div class="form-group">
		<label for="author" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.user.post"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" maxLengthInput="25" class="form-control" id="post" name="post" value="${user.post}" check-type="zipcode">
		</div>
	</div>
	<div class="form-group">
		<label for="author" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.user.address"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" maxLengthInput="100" class="form-control" id="address" name="address" value="${user.address}">
		</div>
	</div>
	<div class="form-group">
		<label for="author" class="col-sm-3 control-label no-padding-right">
			<spring:message code="sys.user.seqNum"/><spring:message code="public.txt.colon"/>
		</label>
		<div class="column col-sm-6">
			<input type="text" maxLengthInput="10" class="form-control" id="seqNum" name="seqNum" check-type="required number" value="${user.seqNum}">
		</div>
	</div>
</form>
