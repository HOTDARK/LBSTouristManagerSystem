<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
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
    <title>便民电话</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <!-- <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/> -->
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
  </head>
<body ontouchstart class="bg-grey-eee">
<div class="weui-tab__bd-item">
	<div class="weui-cells bg-white mar-t6 ft14">
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft18 bg-grey-f6">
			<span class="line-mid">&nbsp;&nbsp;</span> 
			<span class="ft-bold400 ft-grey-666">就餐服务</span>
			<span class="line-mid" >&nbsp;&nbsp;</span>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">膳食中心</div>
			<div class="weui-cell__ft">68254701</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">和丰楼二楼</div>
			<div class="weui-cell__ft">13908367714</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">桂园酒楼</div>
			<div class="weui-cell__ft">68252250</div>
		</div>
		
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft18 bg-grey-f6">
			<span class="line-mid">&nbsp;&nbsp;</span> 
			<span class="ft-bold400 ft-grey-666">住宿服务</span>
			<span class="line-mid" >&nbsp;&nbsp;</span>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">宿管中心</div>
			<div class="weui-cell__ft">68253909</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">桂园宾馆</div>
			<div class="weui-cell__ft">68293000</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">兰苑宾馆</div>
			<div class="weui-cell__ft">68251752</div>
		</div>
		
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft18 bg-grey-f6">
			<span class="line-mid">&nbsp;&nbsp;</span> 
			<span class="ft-bold400 ft-grey-666">维修服务</span>
			<span class="line-mid" >&nbsp;&nbsp;</span>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">维修报修</div>
			<div class="weui-cell__ft">68252024/68251769</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">水管报修</div>
			<div class="weui-cell__ft">68253343</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">电路报修</div>
			<div class="weui-cell__ft">68253342</div>
		</div>
		
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft18 bg-grey-f6">
			<span class="line-mid">&nbsp;&nbsp;</span> 
			<span class="ft-bold400 ft-grey-666">运驾培训</span>
			<span class="line-mid" >&nbsp;&nbsp;</span>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">驾校培训</div>
			<div class="weui-cell__ft">68251760</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">车辆服务</div>
			<div class="weui-cell__ft">68251288</div>
		</div>
		
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft18 bg-grey-f6">
			<span class="line-mid">&nbsp;&nbsp;</span> 
			<span class="ft-bold400 ft-grey-666">送水服务</span>
			<span class="line-mid" >&nbsp;&nbsp;</span>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">送水电话</div>
			<div class="weui-cell__ft">68254240</div>
		</div>
		
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft18 bg-grey-f6">
			<span class="line-mid">&nbsp;&nbsp;</span> 
			<span class="ft-bold400 ft-grey-666">三热维修</span>
			<span class="line-mid" >&nbsp;&nbsp;</span>
		</div>
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft14 bg-grey-f6">
			<span class="ft-bold400 ft-grey-666">洗澡水报修</span>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">竹园/李园/桃园/橘园1-2舍</div>
			<div class="weui-cell__ft">13996492686</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">橘园3-13舍/梅园</div>
			<div class="weui-cell__ft">18166314587</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">杏园A-F栋</div>
			<div class="weui-cell__ft">18716649124</div>
		</div>
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft14 bg-grey-f6">
			<span class="ft-bold400 ft-grey-666">开水器维修</span>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">电话</div>
			<div class="weui-cell__ft">18166314587</div>
		</div>
		
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft18 bg-grey-f6">
			<span class="line-mid">&nbsp;&nbsp;</span> 
			<span class="ft-bold400 ft-grey-666">物业服务</span>
			<span class="line-mid" >&nbsp;&nbsp;</span>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">物业中心</div>
			<div class="weui-cell__ft">68367944</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">社区服务</div>
			<div class="weui-cell__ft">68367568</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">校园环境</div>
			<div class="weui-cell__ft">68252974</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">教学楼服务</div>
			<div class="weui-cell__ft">68367947</div>
		</div>
		
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft18 bg-grey-f6">
			<span class="line-mid">&nbsp;&nbsp;</span> 
			<span class="ft14 ft-grey-666">后勤集团</span> <span class="ft-bold400 ft-grey-666">服务监督</span> 
			<span class="line-mid" >&nbsp;&nbsp;</span>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">集团办</div>
			<div class="weui-cell__ft">68252210</div>
		</div>
		<div class="weui-cell line-d1e">
			<div class="weui-cell__bd">后勤集团邮箱</div>
			<div class="weui-cell__ft">hqjt2000@swu.edu.cn</div>
		</div>
		
	</div>
</div>


<script src="${ctx }/lib/jquery-2.1.4.js"></script>
<script src="${ctx }/lib/fastclick.js"></script>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
<script src="${ctx }/js/jquery-weui.js"></script>
<script src="${ctx }/js/swiper.js"></script>
<script>
  $(".swiper-container").swiper({
	loop: true,
	autoplay: 3000
  });
</script>
<script>
  function htmlFontSize(){
    var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);
    var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
    var width = w > h ? h : w;
    width = width > 720 ? 720 : width
    var fz = ~~(width*100000/36)/10000
    document.getElementsByTagName("html")[0].style.cssText = 'font-size: ' + fz +"px";
    var realfz = ~~(+window.getComputedStyle(document.getElementsByTagName("html")[0]).fontSize.replace('px','')*10000)/10000
    if (fz !== realfz) {
        document.getElementsByTagName("html")[0].style.cssText = 'font-size: ' + fz * (fz / realfz) +"px";
    }
}
</script>
</body>
</html>
