<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
.logForm th{
	text-align: right;
}
</style>
<div align="center">
	<table class="logForm" style="width:450px">
		<tr>
			<th>接口名称:</th>
			<td>${log.wid}</td>
			<th>用户:</th>
			<td>${log.username}</td>
		</tr>
		<tr>
			<th>调用开始时间:</th>
			<td><fmt:formatDate value="${log.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<th>调用结束时间:</th>
			<td><fmt:formatDate value="${log.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<th>接口状态:</th>
			<td>${log.state==1?"正常":"异常"}</td>
			<th></th>
	</table>
	<hr>
	<table>
		<tr>
			<th>输入参数:</th>
		</tr>
		<tr>
			<td><textarea rows="3" cols="85">${log.input}</textarea>
			</td>
		</tr>
		<tr>
			<th>输出参数:</th>
		</tr>
		<tr>
			<td><textarea rows="7" cols="85">${log.output}</textarea>
			</td>
		</tr>
	</table>
</div>
