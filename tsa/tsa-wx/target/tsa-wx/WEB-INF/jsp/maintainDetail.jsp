<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/tags" prefix="date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>运驾服务-维修保养详情</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">
</head>
<body ontouchstart class="bg-grey-eee">

<div  class="customer-tabbar">
<div class="weui-tab">
	
	<div class="weui-tab__bd">
		<div class="weui-cells bg-white mar-t6 set-box-list">
			<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">维修保养详情</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>申报人</p>
				</div>
				<div class="weui-cell__ft">${maintain.applicantName }</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>维保车辆</p>
				</div>
				<div class="weui-cell__ft">${maintain.vehicleLicense }</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>维保日期</p>
				</div>
				<div class="weui-cell__ft"><fmt:formatDate value="${maintain.repairDate}" /></div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>送保厂家</p>
				</div>
				<div class="weui-cell__ft">${maintain.repairFactoryName}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>预算费用</p>
				</div>
				<div class="weui-cell__ft">￥ ${maintain.budgetCost}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>自带材料费用</p>
				</div>
				<div class="weui-cell__ft">￥ ${maintain.materialCost}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>维保原因</p>
				</div>
				<div class="weui-cell__ft">${maintain.repairCause}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>当前状态</p>
				</div>
				<div class="weui-cell__ft">
					<c:if test="${maintain.state==0}">
						待审核
					</c:if>
					<c:if test="${maintain.state==1}">
						部门审核通过
					</c:if>
					<c:if test="${maintain.state==2}">
						安全审核通过
					</c:if>
					<c:if test="${maintain.state==3}">
						领导审核通过
					</c:if>
					<c:if test="${maintain.state==4}">
						部门审核不通过
					</c:if>
					<c:if test="${maintain.state==5}">
						 安全审核不通过
					</c:if>
					<c:if test="${maintain.state==6}">
						领导审核不通过
					</c:if>
					<c:if test="${maintain.state==7}">
						维保完成
					</c:if>
				</div>
			</div>
			
			<c:if test="${maintain.state==1 || maintain.state==4}">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>部门意见</p>
					</div>
					<div class="weui-cell__ft">${maintain.deptOpinion}</div>
				</div>
			</c:if>
			<c:if test="${maintain.state==2 || maintain.state==5}">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>部门意见</p>
					</div>
					<div class="weui-cell__ft">${maintain.deptOpinion}</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>安全审核意见</p>
					</div>
					<div class="weui-cell__ft">${maintain.auditOpinion}</div>
				</div>
			</c:if>
			<c:if test="${maintain.state==3 || maintain.state==6 || maintain.state==7}">
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>部门意见</p>
					</div>
					<div class="weui-cell__ft">${maintain.deptOpinion}</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>安全审核意见</p>
					</div>
					<div class="weui-cell__ft">${maintain.auditOpinion}</div>
				</div>
				<div class="weui-cell line-d1e">
					<div class="weui-cell__bd">
					  <p>领导意见</p>
					</div>
					<div class="weui-cell__ft">${maintain.leaderOpinion}</div>
				</div>
				<c:if test="${maintain.state==7 }">
					<c:if test="${maintain.finished==1 }">
						<div class="weui-cell line-d1e">
							<div class="weui-cell__bd">
							  <p>发票号</p>
							</div>
							<div class="weui-cell__ft">${maintain.invoiceNum}</div>
						</div>
						<div class="weui-cell line-d1e">
							<div class="weui-cell__bd">
							  <p>开票时间</p>
							</div>
							<div class="weui-cell__ft">${maintain.invoiceDate}</div>
						</div>
					</c:if>
				</c:if>
			</c:if>
			
			<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">自带材料</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			
			<c:forEach var="cl" items="${maintain.byoList }">
				<div class="weui-cell line-d1e">
							<div class="weui-cell__bd">
							  <p>${cl.materialName }</p>
							</div>
							<div class="weui-cell__ft">${cl.count }</div>
						</div>
			</c:forEach>
			
			
			<p>&nbsp;</p>
				<!--return-->
				<div class="return-box" onclick="javascript:window.history.back();">
					<em class="l"></em>
					<i></i>返回
					<em class="r"></em>
				</div>
				<div style="height:68px;">&nbsp;</div>
		</div>
		
	</div>
	
</div>








<%@ include file="wxFooter.jsp"%>
</div>
</body>
</html>