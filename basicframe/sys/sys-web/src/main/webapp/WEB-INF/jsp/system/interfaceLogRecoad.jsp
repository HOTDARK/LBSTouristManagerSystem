<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<title></title>
<style type="text/css">
.tab-pane .cell .title {
  border-bottom: 1px solid #e8e8e8;
  padding: 5px;
}
.rms-pane .cell .title .name {
  font-size: 18px;
  color: #83a8cc;
}
.tab-pane .cell .title .numb {
  font-size: 12px;
  padding-left: 20px;
}
.tab-pane .cell .title .type {
  font-size: 12px;
  padding-left: 20px;
}
.tab-pane .cell .put-in {
  background-color: #d5edfa;
  margin: 5px 0;
  padding: 10px;
}
.tab-pane .cell .put-out {
  background-color: #c0fea7;
  margin: 5px 0;
  padding: 10px;
}
._box {
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
	margin-left: 200px;
	margin-right: auto;
}

._box input {
	position: absolute;
	width: 106px;
	height: 131px;
	line-height: 40px;
	font-size: 23px;
	opacity: 0;
	filter: "alpha(opacity=0)";
	filter: alpha(opacity =   0);
	-moz-opacity: 0;
	left: -5px;
	top: -2px;
	cursor: pointer;
	z-index: 2;
	text-align: center;
	margin-left: 0px;
	margin-right: auto;
}
</style>
<div align="center" style="font-size: 14px;"><h5>${workFlowLog.process_name}</h5></div>
<c:if test="${not empty lists}">
  <div class="cell" id="rmscell">
	<c:forEach items="${lists}" var="item" varStatus="i">
			<div style="border-bottom: 1px solid #e8e8e8;padding: 5px;">
				<span style=" font-size: 18px;color: #83a8cc;">${i.index+1}</span><span style="font-size: 12px;padding-left: 20px;">账号:<b style="color: #005cd8;">${item.account}</b>
				</span><span style="font-size: 12px;padding-left: 20px;">接口功能:<b style="color: #005cd8;">${item.wname}</b> </span>
			</div>
			<div class="put-in" style="background-color: #d5edfa;margin: 5px 0;padding: 10px;">
				<span>输入<spring:message code="public.txt.colon"/></span>
				<p>${item.input}</p>
			</div>
			<div class="put-out" style="background-color: #c0fea7;margin: 5px 0;padding: 10px;">
				<span>输出<spring:message code="public.txt.colon"/></span>
				<p>
				${item.output}
				</p>
			</div>
	</c:forEach>
		</div>
</c:if>
