<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<form class="form-horizontal" id="pwdForm" onsubmit="return false">
	<div class="form-group">
		<label for="password" class="col-sm-3 control-label"><spring:message code="sys.user.new.password"/><spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-6">
			<input type="password" class="form-control" maxLengthInput="16" id="userPwdInput" name="userPwd" check-type="required specialchar">
		</div>
	</div>
</form>