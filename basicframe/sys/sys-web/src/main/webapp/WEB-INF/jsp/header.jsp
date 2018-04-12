<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
var basePath = "<%=basePath%>";
</script>
<!-- 全局变量 -->
<base href="<%=basePath%>">
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- BOOTSTRAP BEGIN-->
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/uniform/css/uniform.default.min.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/themes/light.css" id="style_color" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/font-awesome/css/font-awesome.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/style-responsive.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/select/bootstrap-select.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/common/custom.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/fonticons/iconvault-preview.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/datatables/css/jquery.dataTables.css" />
<!-- BOOTSTRAP END-->
<!--单选复选插件-->
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/iCheck-master/skins/square/yellow.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/perfect-scrollbar/perfect-scrollbar.css" /> 
<script type="text/javascript" src="${ctx}/plugins/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/common/jquery.i18n.properties-min-1.0.9.js"></script>
<script type="text/javascript" src="${ctx}/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- 百度地图API -->
<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
 -->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 --> 
<script type="text/javascript" src="${ctx}/plugins/select/bootstrap-select.js"></script>
<script type="text/javascript" src="${ctx}/plugins/jquery.ba-resize.js"></script>
<script type="text/javascript" src="${ctx}/plugins/slimScroll/jquery.slimscroll.js"></script>
<script type="text/javascript" src="${ctx}/plugins/jquery.lightbox_me.js"></script>
<script type="text/javascript" src="${ctx}/plugins/bootstrap-validation/src/bootstrap3-validation.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/common/Utils.js"></script>
<script type="text/javascript" src="${ctx}/plugins/datatables/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.table.js"></script>
<script type="text/javascript" src="${ctx}/plugins/datatables/js/dataTables.fixedColumns.js"></script>
<script type="text/javascript" src="${ctx}/plugins/datatables/js/dataTables.scroller.js"></script>
<script type="text/javascript" src="${ctx}/plugins/jquery.blockui.min.js"></script> 
<script type="text/javascript" src="${ctx}/plugins/jquery.cookie.min.js"></script> 
<script type="text/javascript" src="${ctx}/plugins/uniform/jquery.uniform.js"></script>
<script type="text/javascript" src="${ctx}/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.js"></script> 
<script type="text/javascript" src="${ctx}/js/common/app.js"></script>
<script type="text/javascript" src="${ctx}/plugins/iCheck-master/icheck.js" ></script>
<script type="text/javascript" src="${ctx}/plugins/perfect-scrollbar/jquery.mousewheel.js"></script>
<script type="text/javascript" src="${ctx}/plugins/perfect-scrollbar/perfect-scrollbar.js"></script>
<script type="text/javascript" src="${ctx}/plugins/labelbetter/jquery.label_better.js"></script>
<!-- echarts图表 -->
<script type="text/javascript" src="${ctx}/plugins/echarts/echarts-3.2.3/dist/echarts.js"></script>
<script type="text/javascript" src="${ctx}/plugins/echarts/echarts-3.2.3/theme/macarons.js"></script>
<script type="text/javascript" src="${ctx}/plugins/echarts/echarts-3.2.3/theme/shine.js"></script>
<script type="text/javascript" src="${ctx}/plugins/echarts/echarts-3.2.3/theme/roma.js"></script>
<script type="text/javascript" src="${ctx}/plugins/echarts/echarts-3.2.3/theme/infographic.js"></script>
<script type="text/javascript" src="${ctx}/plugins/echarts/echarts-3.2.3/map/js/province/chongqing.js"></script>
<script type="text/javascript" src="${ctx}/plugins/echarts/echarts-3.2.3/map/js/china.js"></script> 
<script type="text/javascript" src="${ctx}/plugins/echarts/echarts-3.2.3/map/js/world.js"></script>
<!-- 引入百度地图API -->
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<!-- 添加echarts bmap.js对百度地图支持 -->
<script type="text/javascript" src="${ctx}/plugins/echarts/echarts-3.2.3/dist/extension/bmap.js"></script>
<!-- 添加上传文件支持 -->
<link href="plugins/bootstrap-fileinput-4.3.2/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<script src="plugins/bootstrap-fileinput-4.3.2/js/fileinput.js" type="text/javascript"></script>
<script src="plugins/bootstrap-fileinput-4.3.2/js/locales/zh.js" type="text/javascript"></script>

<!--  消息提示  -->
<div id="alertsuccess" style="display: none;" class="alert alert-success  do-tips" role="alert">
	<span class="icons close"></span>
</div>
<div id="alertinfo" style="display: none;" class="alert alert-info  do-tips" role="alert">
	<span class=" icons close"></span> 
</div>
<div id="alertwarning" style="display: none;" class="alert alert-warning do-tips" role="alert">
	<span class=" icons close"></span> 
</div>
<div id="alerterror" style="display: none;" class="alert alert-danger  do-tips" role="alert">
	<span class=" icons close"></span> 
</div>
<!-- 对话框   -->
<div id="showModal_Div"></div>