<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>功能能列表</title>
  	<script type="text/javascript" src="js/common/common.tableTree.js"></script>
   	<script type="text/javascript" src="js/common/common.tableFunction.js"></script>
   	<script type="text/javascript" src="js/system/func.js"></script>
</head>
<body>
	<div class="row">
  		<div class="col-md-12">
		  	<div class="ptool-bar" >
		 		<span class="btn darkblue" onclick="addSysFunction();"><i class="fa fa-plus"></i><spring:message code="public.txt.add"/></span>
      			<span class="btn blue" onclick="editorSysFunction();"><i class="fa fa-edit"></i><spring:message code="public.txt.edit"/></span>
      			<span class="btn yellow" onclick="editSysFunctionState(0);"><i class="fa fa-ban"></i><spring:message code="public.txt.frozen"/></span>
      			<span class="btn green" onclick="editSysFunctionState(1);"><i class="fa fa-gavel"></i><spring:message code="public.txt.activate"/></span>
		  	</div>
		</div>
  		<form class="form-horizontal" id="selectSysFunction" onsubmit="return false">
  			<div class="col-md-12">
        		<div class="search-bar">
					<input type="text" class="form-control" id="functionName" name="functionName" style="width:185px;margin-left: 5px;float:left;" placeholder="<spring:message code="sys.fun.name.placeholder"/>">
					<select class="selectpicker form-control query" data-width="120px" id="state_type" name="state">
						<option value="-1"><spring:message code="public.select.please"/></option>
						<option value="1"><spring:message code="public.select.effective"/></option>
						<option value="0"><spring:message code="public.select.einvalid"/></option>
					</select>
		  			<select class="selectpicker form-control query" data-width="120px" id="function_type" name="functionType">
						<option value="-1"><spring:message code="public.select.please"/></option>
						<option value="1"><spring:message code="sys.fun.type.function"/></option>
						<option value="2"><spring:message code="sys.fun.type.menu"/></option>
					</select>
		  			<button type="button" class="btn blue" onclick="selectSysFunction();">
						<i class="fa fa-search"><spring:message code="public.btn.query"/></i>
					</button>
        		</div>	
        	</div>
			<%-- <div style="text-align:left;float:left;width:100%;padding:15px;" id="panel" class="search-more">
				<div class="form-group">
					<label style="width: 100px; height: 40px;" for="functionName" class="control-label col-sm-3">功能名称<spring:message code="public.txt.colon"/></label>
					<input type="text" class="form-control" style="width: 260px;" placeholder="请输入功能名称" check-type="required" maxlength="100">
				</div>
				<div class="form-group">
					<label style="width: 100px;"  for="state" class="control-label col-sm-3">状态<spring:message code="public.txt.colon"/></label>
					<select  class="selectpicker form-control"  data-width="260px" >
						<option value="1">有效</option>
						<option value="0">无效</option>
					</select>
				</div>
      		</div> --%>
		</form> 
  		<div id="sysFunction" style="display: none"></div>
  		<div class="col-sm-12">
		  	<table id="tableTree" class="table table-tree table-hover operation ">
		    	<thead>
			        <tr>
			          	<th width="1"><label><input type="checkbox" id="tableCheck" onclick="getClickAll(this);" class="ace"><span class="lbl"></span></label></th>
			          	<th width="20%"><spring:message code="sys.fun.name"/></th>
			          	<th width="10%"><spring:message code="sys.fun.code"/></th>
			          	<th width="10%"><spring:message code="sys.fun.type"/></th>
			          	<th width="10%"><spring:message code="sys.fun.state"/></th>
			          	<th width="20%"><spring:message code="sys.fun.orderNo"/></th>
			          	<th width="20%"><spring:message code="sys.fun.url"/></th>
			          	<th width="10%"><spring:message code="public.txt.operation"/></th>
			        </tr>
		      	</thead>
		    </table>
  		</div>
	</div>
</body>
</html>
