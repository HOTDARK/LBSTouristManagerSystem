<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
	.img-div-2 {
	  width: 80%;
	  height: 124px;
	  margin: 0 auto;
	}
	
	.img-div-3 {
	  width: 80%;
	  height: 94px;
	  margin: 0 auto;
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
	
	.border{
		border: 1px #ddd solid;
		padding: 10px;
	}
	
	.slimScrollDiv{height: auto !important;}
</style>
<form class="form-horizontal">
	<c:forEach items="${map}" var="m" varStatus="">
		<div class="row">
			<c:forEach items="${m.value}" var="v" varStatus="s">
				<c:if test="${fn:length(m.value) == 2}">
					<div class="col-sm-6 border">
						<p class='activity-name'>${v.modulName}</p>
						<div class="img-div-2">
							<img src="fileoper/downFile.action?filepath=${v.filePath}">
						</div>	
					</div>
				</c:if>
				<c:if test="${fn:length(m.value) == 3}">
					<div class="col-sm-4 border">
						<p class='activity-name'>${v.modulName}</p>
						<div class="img-div-3">
							<img src="fileoper/downFile.action?filepath=${v.filePath}">
						</div>	
					</div>
				</c:if>
			</c:forEach>
		</div>
	</c:forEach>
</form>