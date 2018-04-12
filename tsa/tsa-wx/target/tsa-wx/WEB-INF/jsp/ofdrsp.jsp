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
    <title>关于后勤</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <script type="text/javascript">
		var basePath = "<%=basePath%>";
		var openid="${openid}";
	</script>
	<base href="<%=basePath%>">
	<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/lib/weui.css">
    <link rel="stylesheet" href="${ctx }/css/jquery-weui.css">
    <link rel="stylesheet" href="${ctx }/css/demos.css">
    <script src="${ctx }/lib/jquery-2.1.4.js"></script>
	<script src="${ctx }/lib/fastclick.js"></script>
	<script src="${ctx }/js/jquery-weui.js"></script>
	<script src="${ctx }/js/swiper.js"></script>
  </head>
<body ontouchstart class="bg-grey-eee">
<div class="weui-tab__bd-item">
	<div class="weui-cells bg-white mar-t6 ft14">
		<div class="center-text hei-36 line-hei36 ft-grey-999 line-d1e ft18 bg-grey-f6">
			<span class="line-mid">&nbsp;&nbsp;</span> 
			<span class="ft-bold400 ft-black">集团简介</span>
			<span class="line-mid" >&nbsp;&nbsp;</span>
		</div>
		<div class="weui-cell ft-grey-666">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;西南大学后勤集团是为学校做好后勤服务保障的直属单位。它的前身是西南师范大学后勤集团与西南农业大学后勤集团。2005年，两校合并组建成西南大学后勤集团。</div>
		<div class="weui-cell ft-grey-666">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在教育规划纲要的指引下，在学校党政领导的领导下，后勤集团董事会监事会的指导下,西南大学后勤集团在深化后勤社会化改革的春风吹拂下，坚持“姓”教的属性，坚持“三服务，两育人”宗旨，以“自主经营、独立核算、自负盈亏”的按现代企业模式促进改革深化，走出了一条可持续发展之路。</div>
		<div class="weui-cell ft-grey-666">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有为才有位的用人制度和多劳多得、奖勤罚懒的分配制度，激发了职工的积极性和创造性。年年有定位、有创新、有重点、有突破，一年一大步，步步上台阶。实施了煤改气清洁能源工程、草坪工程、大道改造、电缆下地、灯饰工程、节能工程、集中加工配送工程。在重庆市高校标准化食堂评比中排名第一，市教委授牌表彰。后勤集团连续十年被表彰为重庆市高校后勤社会化改革工作先进集体。2005年和2011年都被评为“全国高等院校后勤工作先进集体”。</div>
		<div class="weui-cell ft-grey-666">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;“师生至上、服务第一”的理念，“团结、务实、敬业、创新”的团训，“我们是光荣后勤人”的团歌，彰显独具西南大学特色的后勤集团企业文化。</div>
		<div class="weui-cell ft-grey-666">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;后勤集团设有行政办公室、计划财务部、人力资源部和质量安全检查部，下辖膳食服务中心、学生宿舍管理服务中心、商贸服务中心、运输及驾驶培训中心、物业管理中心、工程维修中心、动力服务中心、接待服务中心（含桂园宾馆、桂园酒楼和接待中心）和卓立建筑工程有限公司，现有正式职工190人，聘用员工900人，为学校的中心任务和学校发展做好服务保障，努力搞好经营，努力实现社会和经济双效益和后勤集团自身的做大做强做好而不懈努力！</div>
				
	</div>
</div>
<script>
  $(function() {
    FastClick.attach(document.body);
  });
</script>
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
