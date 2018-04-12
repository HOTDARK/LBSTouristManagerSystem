<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <title>字典管理</title>
	<style type="text/css">
    	.hover{
    		background-color: #2283c5 !important;
    		color: #fafafa !important;
    	}
    </style>
    <script type="text/javascript" src="js/common/common.tableTree.js"></script>
    <script type="text/javascript" src="js/common/common.tableFunction.js"></script>
    <script type="text/javascript" src="js/system/dict.js"></script>
  </head>  
<body>
  <div class="row">
   <div class=" col-md-12">
    	<div class="ptool-bar" >
  		<a href="javascript:void(0);"class="btn darkblue" onclick="addTypeDictView();">
	   <i class="fa fa-plus "></i><spring:message code="public.txt.add"/></a>
	    <a href="javascript:void(0);"class="btn blue"onclick="editTypeDictView();">
	   <i class="fa fa-edit"></i><spring:message code="public.txt.edit"/></a>
	    <a href="javascript:void(0);"class="btn yellow"onclick="freezeTypeDict();">
	   <i class="fa fa-ban"></i><spring:message code="public.txt.frozen"/></a>
	    <a href="javascript:void(0);"class="btn green"onclick="activateTypeDict();">
	   <i class="fa fa-gavel"></i><spring:message code="public.txt.activate"/></a>
  	 </div>
  	</div>
  <div class=" col-md-12">
  	<div class="search-bar" id="querys" >
  	<table>
  	<tr>
  	<td>
  		<input type="text" class="form-control" style="width: 155px;float: left;" id="typeName" name="typeDictName" placeholder="<spring:message code="sys.dict.typeName.placeholder"/>" />
  	</td>
  	<td>
	    <input type="text" class="form-control" style="width: 155px;float: left;" id="typeCode" name="typeDictCode" placeholder="<spring:message code="sys.dict.typeCode.placeholder"/>" />
  	</td>
  	<td>
		<select class="selectpicker form-control query" data-width="120px" id="typeState" name="typeDictCode">
			<option value="-1"><spring:message code="public.select.please"/></option>
			<option value="1"><spring:message code="public.select.effective"/></option>
			<option value="0"><spring:message code="public.select.einvalid"/></option>
		</select>
  	</td>
  	<td>
		<button type="button" id="queryTypeDict" class="btn blue" style="margin-left: 5px;">
			<i class="fa fa-search"><spring:message code="public.btn.query"/></i>
		</button>
  	</td>
  	</tr>
  	</table>
  	</div>	
  </div>
  <div class="col-sm-12">
  	<table id="tableTree" class="table table-tree table-hover operation">
      <thead>
        <tr>
          <th width="20"><label><input type='checkbox' class='ace' id="tableCheck" onclick="checkAll(this);"/><span class='lbl'></span></label></th>
          <th width="200"><spring:message code="sys.dict.typeName"/></th>
          <th><spring:message code="sys.dict.typeCode"/></th>
         <%--  <th><spring:message code="sys.dict.mappingName"/></th>
          <th><spring:message code="sys.dict.mappingCode"/></th> --%>
          <th><spring:message code="sys.dict.orderNo"/></th>
          <th><spring:message code="sys.dict.appFlag"/></th>
          <th><spring:message code="sys.dict.state"/></th>
          <th style="width:100px;"><spring:message code="public.txt.operation"/></th>
        </tr>
      </thead>
    </table>
  </div>
  <div id="typeDictModal" style="display: none"></div>
  </div>
</body>
</html>
