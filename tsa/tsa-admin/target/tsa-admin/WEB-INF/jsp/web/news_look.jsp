<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
    <div class="row">
    	<div class="col-md-1"></div>
    	<div class="col-md-10">
           	<p style="height: 40px;"></p>
   			<div style="font-size:24px; text-align: center;">${entity.newsTitle }</div>
           	<p style="height: 10px;"></p>
   			<p style="padding-left: 30px;">发布时间:<fmt:formatDate value="${entity.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
           	<p style="height: 10px;"></p>
   			<p>${entity.newsContent }</p>
   		</div>
    </div>
</div>
