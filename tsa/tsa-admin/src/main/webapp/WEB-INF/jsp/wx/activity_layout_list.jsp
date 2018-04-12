<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.img-div-2 {
	  width: 80%;
	  height: 200px;
	  margin: 0 auto;
	  text-align: center;
	  line-height: 200px;
	  background: #eee;
	}
	
	.img-div-3 {
	  width: 80%;
	  height: 127px;
	  margin: 0 auto;
	  text-align: center;
	  line-height: 127px;
	  background: #eee;
	}
	
	.img-div-2 img {
	  width : 100%;
	  height: 100%;
	}
	
	.img-div-3 img {
	  width : 100%;
	  height: 100%;
	}
	
	.activity-name{
		color: #333;
		font-weight: 600;
		font-size: 16px;
		padding-top: 5px;
		text-align: center;
	}
	
	.rel-div{
		color: red;
		font-size: 18px;
		padding-top: 20px;
		text-align: center;
	}
	
	.slimScrollDiv{height: auto !important;}
</style>
<form class="form-horizontal">
<div class="col-md-12">
   	<div class="ptool-bar" >
 		<a href="javascript:void(0);" class="btn darkblue" id="activityLayoutAdd" onclick="editActivityLayout()"><i class="fa fa-plus"></i><spring:message code="public.txt.add"/></a>
	</div>
</div>
<div class="col-xm-12" id="activityLayoutDiv">
	<table id="activityLayoutTable" class="table table-striped table-hover" cellspacing="0" width="100%"></table>
</div>
<div id="activityRelDiv"></div>
</form>
<div id="editLayoutDiv" style="display: none;"></div>
<div id="editActivityRelDiv" style="display: none;"></div>