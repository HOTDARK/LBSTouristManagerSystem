<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/tags" prefix="date"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html> 
<html>
  <head>
    <title>膳食服务-订餐评价</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
		var storeId="${storeId}";
		var orderNum="${id}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
    <script type="text/javascript" src="${ctx }/lib/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="${ctx }/lib/fastclick.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-weui.js"></script>
	<script type="text/javascript" src="${ctx }/js/swiper.js"></script>
	<script type="text/javascript" src="${ctx }/js/Utils.js"></script>
    <script type="text/javascript" src="${ctx }/js/wxCommon.js"></script> 
    <script type="text/javascript" src="js/orderEvaluate.js"></script>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
  </head>
<body ontouchstart class="bg-grey-eee">
<div class="bg-white  weui-cell line-d1e" >
	<div class="ft-red"><span class="ft-grey-999">订单编号：</span>${id}</div>
</div>
<p class="hei-6">&nbsp;</p>
<div class="bg-white ft14">
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">菜品评分</div>
		<div class="weui-cell__bd">
			<div class="icon-score">
				<div>
					<p style="z-index:2;"><a href="javascript:;" onclick="cppf(1)"></a><a href="javascript:;" onclick="cppf(2)"></a><a href="javascript:;" onclick="cppf(3)"></a><a href="javascript:;" onclick="cppf(4)"></a><a href="javascript:;" onclick="cppf(5)"></a></p>
					<i id="cpScore" style="width:0px;z-index:1;"></i>
				</div>
				<span class="txt ft-grey-666 ft14" id="cpScoreText">0分</span>
			</div>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">商家评分</div>
		<div class="weui-cell__bd">
			<div class="icon-score">
				<div>
					<p style="z-index:2;"><a href="javascript:;" onclick="sjpf(1)"></a><a href="javascript:;" onclick="sjpf(2)"></a><a href="javascript:;" onclick="sjpf(3)"></a><a href="javascript:;" onclick="sjpf(4)"></a><a href="javascript:;" onclick="sjpf(5)"></a></p>
					<i id="sjScore" style="width:0px;z-index:1;"></i>
				</div>
				<span class="txt ft-grey-666 ft14" id="sjScoreText">0分</span>
			</div>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">配送评分</div>
		<div class="weui-cell__bd">
			<div class="icon-score">
				<div>
					<p style="z-index:2;"><a href="javascript:;" onclick="pspf(1)"></a><a href="javascript:;" onclick="pspf(2)"></a><a href="javascript:;" onclick="pspf(3)"></a><a href="javascript:;" onclick="pspf(4)"></a><a href="javascript:;" onclick="pspf(5)"></a></p>
					<i id="psScore" style="width:0px;z-index:1;"></i>
				</div>
				<span class="txt ft-grey-666 ft14"  id="psScoreText">0分</span>
			</div>
		</div>
	</div>
	
	<div class="weui-cell line-d1e">
		<div class="weui-cell__bd">
		  <textarea class="weui-textarea" placeholder="评价内容" rows="3" id="content" maxlength="198" onchange="limitcontent(this)"></textarea>
		  <div class="weui-textarea-counter"><span id="lc">0</span>/200</div>
		</div>
	</div>
	<div class="weui-cell line-d1e">
		<div class="weui-cell__hd" style="width: 80px">评价图片</div>
		<div class="weui-cell__bd">
			<div class="btn-uploader pull-left" onclick="uploadImg(this)" id="dietevaluateImg1"></div>
			<div class="btn-uploader pull-left" onclick="uploadImg(this)" id="dietevaluateImg2"></div>
		</div>
	</div>
</div>
<p>&nbsp;</p>
<div class="weui-msg__opr-area">
	<p class="weui-btn-area" style="margin-top: 0;">
	  <a href="javascript:;" class="weui-btn bg-yellow ft-black" onclick="submitComment(this)">提   交</a>
	  <a href="javascript:;" class="weui-btn weui-btn_default ft14" onclick="backToList()"><span class="ft-grey-999">暂不评价</span> <span class="ft-orange">返回</span></a>
	</p>
</div>
<script>
  $(function() {
    FastClick.attach(document.body);
	var timeStamp=${config.timestamp};
	
	
	
	
  });
  var timeStamp="${config.timestamp}";
  wx.config({
		debug: false,
	    appId: '${config.appid}',
	    timestamp:timeStamp,
	    nonceStr: '${config.noncestr}',
	    signature: '${config.signature}',
	    jsApiList: ['chooseImage', 'uploadImage']
	});
  	var imgData;
	var serverIds="";
	function uploadImg(obj){
		wx.chooseImage({
			count: 2, // 默认9
			sizeType: ['compressed'],
		    success: function (res) {
		    	$.showLoading("图片上传中");
		        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
		        
		        if(localIds.length>0){
		        	serverIds="";
		        	$("#dietevaluateImg1").empty();
		        	$("#dietevaluateImg2").empty();
		        	$("#dietevaluateImg1").html("<img src='"+localIds[0]+"' height='100%' width='100%' alt=''/>");
		        }
		        if(localIds.length>1){
		        	$("#dietevaluateImg2").html("<img src='"+localIds[1]+"' height='100%' width='100%' alt=''/>");
		        }
		        if(localIds.length>0){
		        	syncUpload(localIds);
		        }
		    }
		});
		var syncUpload = function(localIds){
			    var localId = localIds.pop();
			    wx.uploadImage({
			        localId: localId,
			        isShowProgressTips: 0,
			        success: function (res) {
			            var serverId = res.serverId; // 返回图片的服务器端ID
			            serverIds+=serverId+",";
			            
			            if(localIds.length > 0){
			                syncUpload(localIds);
			            } else {
			            	serverIds=serverIds.substring(0,serverIds.length-1);
			            	Utils.ajax({
			        			url : 'wxRepair/downloadImgFromWx.action',
			        			data:{"openid":openid,"serverIds":serverIds,"ftpDir":"diet/orderEvaluate"},
			        			success : function(data) {
			        				imgData=data;
			        				$.hideLoading();
			        			}
			        		});
			            }
			        }
			    });
			};
	}
  
</script>
</body>
</html>
