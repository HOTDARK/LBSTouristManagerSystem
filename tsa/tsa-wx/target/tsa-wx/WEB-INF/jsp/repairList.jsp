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
    <title>在线报修</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var totalPages=${resultData.totalPages};
		var openid="${openid}";
		var userPhone="${userInfo.sjhm}";
		var idNum="${idNum}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
	<link rel="stylesheet" href="${ctx }/css/demos2.css">
  </head>
<body ontouchstart class="bg-grey-eee">
<div class="weui-tab">
	<div class="weui-navbar ft18 ft-bold400 reset-tab">
	<a class="weui-navbar__item weui-bar__item--on" href="javascript:void(0)" onclick="anchor(1)" id="anchor1">
	  我的报修
	</a>
	<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(2)" id="anchor2">
	  我要报修
	</a>
	<a class="weui-navbar__item" href="javascript:void(0)" onclick="anchor(3)" id="anchor3">
	  工单处理
	</a>
	</div>
	<div class="weui-tab__bd">
	<!--tab 我的报修-->
	<div id="tab1" class="weui-tab__bd-item weui-tab__bd-item--active" style="width:100%;">
		<div class="weui-pull-to-refresh__layer">
			<div class='weui-pull-to-refresh__arrow'></div>
			<div class='weui-pull-to-refresh__preloader'></div>
			<div class="down">下拉刷新</div>
			<div class="up">释放刷新</div>
			<div class="refresh">正在刷新</div>	
		</div>
		<c:if test="${resultData.totalPages!=0 }">
			<div id="sourceAdd">
			
				<!--list-->
				<c:forEach items="${resultData.repairInfoList}" var="repairInfo">
					
					<div class=" bg-white mar-t6">
						<div class="ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30">
							<i class="icon-number pull-left mar-t6"></i>
							<span class="text-number pull-left mar-t6 ">${repairInfo.repairNum}</span>
						</div>
						<div class="pull-right mar-r10">
							
							<c:if test="${repairInfo.repairState == 003011}">
								<i class="bg-deep-blue tip-state" onclick="confirmRepair(${repairInfo.id})" >确认维修</i>
							</c:if>
							<c:if test="${repairInfo.repairState == 003001}">
								<i class="bg-deep-blue tip-state" onclick="closeRepair(${repairInfo.id})" >关闭维修</i>
							</c:if>
						</div>
						<p class="clearfix line-1eee"></p>
						<a href="wxRepair/getRepairDetail.action?openid=${openid}&id=${repairInfo.id}" class="weui-media-box weui-media-box_appmsg" style="padding-bottom: 8px;">
						  <div class="weui-media-box__hd" style="position: relative">
						  	<i class="bg-green-merald tip-state" style="position: absolute;">${repairInfo.repairStateStr }</i>
						  	<c:if test="${repairInfo.picPath==null || repairInfo.picPath==''}">
						  		<img class="weui-media-box__thumb" src="images/nopic.jpg">
						  	</c:if>
						  	<c:if test="${repairInfo.picPath != null && repairInfo.picPath !=''}">
						  		<img class="weui-media-box__thumb" src="fileoper/downFile.action?filepath=${repairInfo.picPath}" onerror="javascript:this.src='images/nopic.jpg';">
						  	</c:if>
						  </div>
						  <div class="weui-media-box__bd">
							<h4 class="weui-media-box__title ft-bold400"><i class="icon-position"></i>${repairInfo.repairAreaStr}</h4>
							<p class="line-x1eee"></p>
							<p class="weui-media-box__desc mar-t6 ft-grey-666 ft14">${repairInfo.repairContent}</p>
							<p class="weui-media-box__desc ft12 ft-grey-999 text-number mar-t3"><date:date value ="${repairInfo.repairDate} "/></p>
						  </div>
						</a>
					</div>
				</c:forEach>
				<!--/list-->
			
			
			
			
			<c:if test="${resultData.totalPages == 1 }">
				<div class="center-text" style="color: #b0b0b0; line-height: 50px;">------没有更多数据了------</div>
			</c:if>
		</div>
			<c:if test="${resultData.totalPages != 1 }">
				<div class="weui-loadmore" id="wxLoadmore">
					<i class="weui-loading"></i>
					<span class="weui-loadmore__tips">正在加载</span>
				</div>
			</c:if>
			
		</c:if>
		<c:if test="${resultData.totalPages==0 }">
			<div id="sourceList" class="text-center" style="margin-top: 15%;">
				<img src="images/nodata.png" width="70%" alt=""/>
				<p class="ft18 ft-grey-999">亲，您还没有报修数据</p>
				<div style="height:100px;">&nbsp;</div>
			</div>
		</c:if>
		<div style="height:68px;">&nbsp;</div>
	</div>
	<!--tab 我要报修-->
	<div id="tab2" class="reset-weui-cells weui-tab__bd-item">
		<div class="weui-cells bg-white">
			<div class="weui-cell line-d1e">
				<div class="weui-cell__hd"><img src="images/icon-form01.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p><input class="weui-input" type="tel" placeholder="报修人" value="${userInfo.xm}" id="repairUserName"></p>
				</div>
				<div class="weui-cell__ft ft12">报修人</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__hd"><img src="images/icon-form02.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p><input class="weui-input" type="tel" placeholder="联系电话" value="${userInfo.sjhm}" id="userPhone"></p>
				</div>
				<div class="weui-cell__ft ft12">联系电话</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p><input class="weui-input" type="text" placeholder="维修区域" id="repairAreaPicker" data-values=""></p>
				</div>
			</div>
			<div class="weui-cell line-d1e" id="xsssDiv" style="display:none">
				<div class="weui-cell__hd"><img src="images/icon-form06.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p>
				  	<input class="weui-input" type="text" placeholder="所在园区" id="xsss" data-values="" >
				  </p>
				</div>
			</div>
			<div class="weui-cell line-d1e" id="xsssTwoDiv" style="display:none">
				<div class="weui-cell__hd"><img src="images/icon-form06.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p>
				  	<input class="weui-input" type="text" placeholder="所在楼栋" id="xsssTwo" data-values="" >
				  </p>
				</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__hd"><img src="images/icon-form07.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p id="areaLocation"></p>
				</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__hd"><img src="images/icon-form07.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p><input class="weui-input" type="text" placeholder="详细地点" maxlength="90" id="detailLocation"></p>
				</div>
			</div>
			<div class="weui-cell line-d1e" id="sbdwDiv">
				<div class="weui-cell__hd"><img src="images/icon-form06.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p><input class="weui-input" type="text" placeholder="申报单位" id="serviceCompanyPicker" data-values="">
				  	
				  </p>
				</div>
			</div>
			<div class="weui-cell line-d1e" id="sbdwTwoDiv">
				<div class="weui-cell__hd"><img src="images/icon-form06.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p><input class="weui-input" type="text" placeholder="二级单位" id="secondeCompanyPicker" data-values="">
				  	
				  </p>
				</div>
			</div>
			
			
			<div class="weui-cell line-d1e">
				<div class="weui-cell__hd"><img src="images/icon-form04.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p><input class="weui-input" type="text" placeholder="维修类别" id="repairProjectsPicker" data-values=""></p>
				</div>
			</div>
			
			<!-- <div class="weui-cell line-d1e">
				<div class="weui-cell__hd"><img src="images/icon-form05.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p><input class="weui-input" type="tel" placeholder="预约维修时间（选填）" id="orderRepairTime"></p>
				  <input type="hidden" id="ort">
				</div>
			</div> -->
			
			<div class="weui-cell line-d1e" style="display:none" id="sfxmDiv">
				<div class="weui-cell__hd"><img src="images/icon-form03.png" width="20" height="20" alt=""/></div>
				<div class="weui-cell__bd">
				  <p><input class="weui-input" type="text" placeholder="维修子项" id="sfxmPicker" data-values=""></p>
				</div>
			</div>
			<div class="weui-cell line-d1e">
				<div class="weui-cell__hd">
					<p><img src="images/icon-form08.png" width="20" height="20" alt=""/></p>
					<p>&nbsp;</p>
					<p>&nbsp;</p>
					<p>&nbsp;</p>
				</div>
				<div class="weui-cell__bd">
				  <textarea class="weui-textarea" placeholder="维修内容" rows="3" maxlength="200" onchange="limitLength(this)" id="repairContent"></textarea>
				  <div class="weui-textarea-counter"><span id="currentLength">0</span>/200</div>
				</div>
			</div>
			<div class="weui-cell line-d1e">
				<div style="width: 25px;">&nbsp;</div>
				<div class="ft-grey-999">上传照片</div>
			</div>
			<div class="weui-cell">
				<div style="width: 25px;">&nbsp;</div>
				<div class="uploader-pic" onclick="uploadImgs('repair/repair_declare')" id="firstImg" ></div>
				<div class="uploader-pic" onclick="uploadImgs('repair/repair_declare')" id="secondImg" style="display: none;"></div>
			</div>
		</div>
		<!--btn-ok-->
		<div class="demos-content-padded">
			<a href="javascript:void(0);" class="weui-btn bg-deep-blue" onclick="wxRepair(this)">提 交</a>
		</div>
	   <!--temp-->
		<div style="height:68px;">&nbsp;</div>
	</div>
	<!--tab 工单处理-->
	<div id="tab3" class="weui-tab__bd-item" style="width:100%;">
		<div class="weui-pull-to-refresh__layer">
			<div class='weui-pull-to-refresh__arrow'></div>
			<div class='weui-pull-to-refresh__preloader'></div>
			<div class="down">下拉刷新</div>
			<div class="up">释放刷新</div>
			<div class="refresh">正在刷新</div>	
		</div>
	</div>
	</div>
</div>
<%@ include file="wxFooter.jsp"%>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript" src="js/wx.repairList.js"></script>
<script type="text/javascript" src="${ctx }/js/wxCommon.js"></script>
<script>
	var timeStamp=${config.timestamp};
	wx.config({
		debug: false,
	    appId: '${config.appid}',
	    timestamp:timeStamp,
	    nonceStr: '${config.noncestr}',
	    signature: '${config.signature}',
	    jsApiList: ['chooseImage', 'uploadImage']
	});
	var repairProjects=[];
	var repairProjectsJ=${repairProjects};
	$.each(repairProjectsJ,function(j,i){
		var row={'title':i.typeDictName,'value':i.typeDictCode};
		repairProjects.push(row);
	});
	$("#sfxmPicker").select({
		title: "请选择维修子项"
	});
	$("#repairProjectsPicker").select({
		title: "请选择维修类别",
		items: repairProjects,
		onClose:function(d){
			if($("#repairAreaPicker").attr("data-values")=="006002"){
				var xsssParent=$("#repairProjectsPicker").attr("data-values");
				$("#sfxmPicker").val("");
				$("#sfxmPicker").attr("data-values","");
				
				Utils.ajax({
	    			url : 'wxRepair/findSysDictByParent.action',
	    			data:{"parentCode":xsssParent},
	    			success : function(data) {
		    			var sfxm=[];
		    			var sfxmj=${sfxm};
		    			$.each(data,function(j,i){
		    				var row={'title':i.typeDictName,'value':i.typeDictCode};
		    				sfxm.push(row);
		    			});
	    				$("#sfxmPicker").select("update", { items: sfxm});
	    			}
	    		});
			}
		}
	});
	var xsss=[];
	var xsssj=${xsss};
	$.each(xsssj,function(j,i){
		var row={'title':i.typeDictName,'value':i.typeDictCode};
		xsss.push(row);
	});
	$("#xsssTwo").select({title:"请选择所在楼栋"});
	$("#xsss").select({
		title: "请选择所在园区",
		items: xsss,
		onClose: function (d) {
			var xsssParent=$("#xsss").attr("data-values");
			$("#xsssTwo").val("");
			$("#xsssTwo").attr("data-values","");
			Utils.ajax({
    			url : 'wxRepair/findSysDictByParent.action',
    			data:{"parentCode":xsssParent},
    			success : function(data) {
    				var proje=[];
	    			$.each(data,function(j,i){
	    				var row={'title':i.typeDictName,'value':i.typeDictCode};
	    				proje.push(row);
	    			});
	    			
    				$("#xsssTwo").select("update", { items: proje,onClose:function(d){
						changeLocation();
					} }
    					
    				);
    			}
    		});
			changeLocation();
		}
	});
	
	var repairAreas=[];
	var repairAreasJ=${repairAreas};
	$.each(repairAreasJ,function(j,i){
		var row={'title':i.typeDictName,'value':i.typeDictCode};
		repairAreas.push(row);
	});
	$("#repairAreaPicker").select({
		title: "请选择维修区域",
		items: repairAreas,
		onClose: function (d) {
			var parentCode="";
	    	if($("#repairAreaPicker").attr("data-values")=="006002"){
	    		parentCode="008";
	    		$("#sbdwDiv").hide();
	    		$("#xsssDiv").show();
	    		$("#sbdwTwoDiv").hide();
	    		$("#sfxmDiv").show();
	    		$("#xsssTwoDiv").show();
	    	} else {
	    		parentCode="007";
	    		$("#sbdwDiv").show();
	    		$("#xsssDiv").hide();
	    		$("#sbdwTwoDiv").show();
	    		$("#sfxmDiv").hide();
	    		$("#xsssTwoDiv").hide();
	    	}
	    	$("#repairProjectsPicker").val("");
			$("#repairProjectsPicker").attr("data-values","");
	    	Utils.ajax({
    			url : 'wxRepair/findSysDictByParent.action',
    			data:{"parentCode":parentCode},
    			success : function(data) {
    				var proje=[];
	    			$.each(data,function(j,i){
	    				var row={'title':i.typeDictName,'value':i.typeDictCode};
	    				proje.push(row);
	    			});
	    			
    				$("#repairProjectsPicker").select("update", { items: proje });
    			}
    		});
	    	changeLocation();
	    }
	});
	
	
	
	/* $("#orderRepairTime").datetimePicker({
		title:'请选择预约维修时间',
        times: function () {
          return [
            {
              values: (function () {
                var hours = ['08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18'];
                return hours;
              })()
            },
            {
              divider: true,  // 这是一个分隔符
              content: '时'
            }
          ];
        },
        yearSplit:'年',
        monthSplit:'月',
        dateSplit:'日',
        onChange: function (picker, values, displayValues) {
            var orderRepairTime=values[0]+"-"+values[1]+"-"+values[2]+" "+values[3]+":00:00";
            console.log(orderRepairTime);
            $("#ort").val(orderRepairTime);
          },
          min:new Date().format("yyyy-MM-dd")
      }); */
	
	$("#secondeCompanyPicker").select({
		title: "请选择二级单位"
	});
	//serviceCompanyPicker
	var serviceCompany=[];
	var serviceCompanyJ=${serviceCompany};
	$.each(serviceCompanyJ,function(j,i){
		var row={'title':i.orgName,'value':i.orgId};
		serviceCompany.push(row);
	});
	$("#serviceCompanyPicker").select({
		title: "请选择申报单位",
		items: serviceCompany,
		onChange: function(d) {
	    	Utils.ajax({
	    		url : 'wxRepair/secondeOrg.action',
	    		data:{"parentOrgId":d.values},
	    		success : function(data) {
	    			$("#secondeCompanyPicker").val("");
	    			var secondeCompany=[];
	    			$.each(data,function(j,i){
	    				var row={'title':i.orgName,'value':i.orgCode};
	    				secondeCompany.push(row);
	    			});
	    			$("#secondeCompanyPicker").select("update", { items: secondeCompany })
	    		}
	    	});
		},
	});

</script>
</body>
</html>
