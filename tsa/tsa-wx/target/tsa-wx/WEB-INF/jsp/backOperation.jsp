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
    <title>西南大学数字后勤服务大厅</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var operType = "${operType}";
		var backAccount = "${backAccount}";
		var backId="${repairInfo.id}";
		var backRepairNum="${repairInfo.repairNum}";
		var backRepairState="${repairInfo.repairState}";
		var repairProjectOneStr="${repairInfo.repairProjectOneStr}";
		var serviceCompany="${repairInfo.serviceCompany}";
		var userPhone="${repairInfo.userPhone}";
		var orgCode="${orgCode}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
  </head>
<body ontouchstart class="bg-grey-eee">
<div class="weui-navbar ft18 ft-bold400 set-box">
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="firstAudit()" id="firstAuditBtn" style="display:none;">一级审核</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="secondAudit()" id="secondAuditBtn" style="display:none;">维修单确认</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="zhuangong()" id="zgBtn" style="display:none;">转工</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="closeOrReject(1)" id="rejectBtn" style="display:none;">驳回</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="closeOrReject(2)" id="closeBtn" style="display:none;">关闭</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="userConfirm()" id="confirmBtn" style="display:none;">用户确认</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="userConfirm()" id="fashBtn" style="display:none;">方案审核</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="paigong()" id="pgBtn" style="display:none;">派工</a>
		<a href="javascript:void(0)" type="button" class="bg-deep-blue ft14" onclick="wangong()" id="wgBtn" style="display:none;">完工</a>
	</div>
<div  class="customer-tabbar">
<div class="weui-tab">
	
	<div class="weui-tab__bd">
		<div class="weui-cells bg-white mar-t6 set-box-list" style="margin-top:60px;">
			<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">工单详情</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>报修编号</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.repairNum }</div>
			</div>
			<%-- <div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>报修渠道</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.channelStr }</div>
			</div> --%>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>报修时间</p>
				</div>
				<div class="weui-cell__ft"><date:date value ="${repairInfo.repairDate }"/></div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>报修人</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.repairUserName}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>报修人电话</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.userPhone}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>维修区域</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.repairAreaStr}</div>
			</div>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>报修单位</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.repairCompanyStr}</div>
			</div>
			<%-- <div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>付费类型</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.paymentTypeStr}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>维修类别</p>
				</div>
				<div class="weui-cell__ft">
					<c:if test="${repairInfo.repairType==01}">直接维修</c:if>
					<c:if test="${repairInfo.repairType==02}">要方案维修</c:if>
				</div>
			</div> --%>
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>维修项目</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.repairProjectOneStr}</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>维修子项目</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.repairProjectTwoStr}</div>
			</div>
			
			
			<%-- <div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>承接单位</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.serviceCompanyStr}</div>
			</div> --%>
			<%-- <div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>维修状态</p>
				</div>
				<div class="weui-cell__ft">${repairInfo.repairStateStr}</div>
			</div> --%>
			<%-- <div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>预约维修时间</p>
				</div>
				<div class="weui-cell__ft">
					<c:if test="${repairInfo.orderRepairDate!=null && repairInfo.orderRepairDate!=''}">
						<date:date value ="${repairInfo.orderRepairDate }"/>
					</c:if>
				</div>
			</div> --%>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>详细地点</p>
				</div>
				<div class="weui-cell__ft"></div>
			</div>
			<div class="weui-cell line-d1e">
				<span class="weui-form-preview__value">
				  
				</span>
				<textarea class="weui-textarea" placeholder="详细地点" rows="3" readonly>${repairInfo.detailLocation}</textarea>
				  
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__bd">
				  <p>维修内容</p>
				</div>
				<div class="weui-cell__ft"></div>
			</div>
			<div class="weui-cell line-d1e">
				<textarea class="weui-textarea" placeholder="维修内容" rows="3" readonly>${repairInfo.repairContent}</textarea>
			</div>
			<div class="weui-cell mar-t6">
						<i></i>
						<div><p class="icon-s-img"></p><p class="icon-s-topmimg">&nbsp;</p></div>
						<div class="weui-cell__bd">
							<p class="ft-grey-999">报修照片</p>
							<p>
								<c:if test="${fn:length(attas)==0}">
									<img src="images/nopic.jpg" width="90" alt=""/>
								</c:if>
								<c:if test="${fn:length(attas)>0}">
									<input type="hidden" id="filepath1" value="${attas[0].filePath }">
									<img src="fileoper/downFile.action?filepath=${attas[0].filePath }" width="90" alt="" onclick="showPb(0)"/>
								</c:if>
								<c:if test="${fn:length(attas)>1}">
									<input type="hidden" id="filepath2" value="${attas[1].filePath }">
									&nbsp;<img src="fileoper/downFile.action?filepath=${attas[1].filePath }" width="90" alt="" onclick="showPb(1)"/>
								</c:if>
							</p>
						</div>
					</div>
		</div>
		<div class="weui-cells bg-white mar-t6 ">
			<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e">
				<span class="line-mid">&nbsp;&nbsp;</span> 
				<span class="ft-bold400">处理意见</span>
				<span class="line-mid" >&nbsp;&nbsp;</span>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="cjdwDiv">
				<div class="weui-cell__bd">
				  <p>承接单位</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择承接单位" id="cjdw" data-values=""></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="fflxDiv">
				<div class="weui-cell__bd">
				  <p>付费类型</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择付费类型" id="fflx" data-values=""></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="wxlbDiv">
				<div class="weui-cell__bd">
				  <p>维修类别</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择维修类别" id="wxlb" data-values=""></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="wxxmDiv">
				<div class="weui-cell__bd">
				  <p>维修项目</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择维修项目" id="wxxm" data-values="${repairInfo.repairProjectOne }"></div>
			</div>
			<div class="weui-cell line-d1e" style="display:none;" id="wxzxmDiv">
				<div class="weui-cell__bd">
				  <p>维修子项目</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择维修子项目" id="wxzxm" data-values=""></div>
			</div>
			
			<div class="weui-cell line-d1e" style="display:none;" id="wxryDiv">
				<div class="weui-cell__bd">
				  <p>维修人员</p>
				</div>
				<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择维修人员" id="wxry" data-values=""></div>
			</div>
			<div style="display:none;" id="wgDiv">
				<div class="weui-cell line-d1e">
					<div style="width: 25px;">&nbsp;</div>
					<div class="ft-grey-999">上传完工照片</div>
				</div>
				<div class="weui-cell">
					<div style="width: 25px;">&nbsp;</div>
					<div class="uploader-pic" onclick="uploadImgs('repair/repair_declare')" id="firstImg" ></div>
					<div class="uploader-pic" onclick="uploadImgs('repair/repair_declare')" id="secondImg" style="display: none;"></div>
				</div>
				<div class="weui-cell line-d1e" id="qzhz" style="display:none;">
					<div class="weui-cell__bd">
					  <p>是否有签字回执</p>
					</div>
					<div class="weui-cell__bd"><input class="weui-input" type="text" placeholder="选择是否有签字回执" id="huizhi" data-values=""></div>
				</div>
			</div>
			
		</div>
		<div style="height:68px;">&nbsp;</div>
	</div>
	
</div>
</div>
<%@ include file="wxFooter.jsp"%>

<script type="text/javascript" src="js/wx.backOperation.js"></script>
</body>
<script type="text/javascript">
	var timeStamp=${config.timestamp};
	wx.config({
		debug: false,
	    appId: '${config.appid}',
	    timestamp:timeStamp,
	    nonceStr: '${config.noncestr}',
	    signature: '${config.signature}',
	    jsApiList: ['chooseImage', 'uploadImage']
	});
</script>
</html>
