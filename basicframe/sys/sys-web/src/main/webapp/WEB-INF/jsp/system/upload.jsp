<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
	<title>上传数据</title>
	<link rel="stylesheet" href="css/updata.css" />
<style type="text/css">
.inputCss{
  padding: 10px 5px;
  width: 500px;
  font-family: Arial, "微软雅黑", "宋体", Tahoma, Helvetica, sans-serif;
  background-color: #FFFFFF;
  border: 1px solid #d6d6d6;
  line-height: 22px;
  height: 40px;
}
</style>
<script src="wfplugins/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://bp.yahooapis.com/2.4.21/browserplus-min.js"></script>
<script type="text/javascript" src="libs/plupload/js/plupload.js"></script>
<script type="text/javascript" src="libs/plupload/js/plupload.gears.js"></script>
<script type="text/javascript" src="libs/plupload/js/plupload.silverlight.js"></script>
<script type="text/javascript" src="libs/plupload/js/plupload.flash.js"></script>
<script type="text/javascript" src="libs/plupload/js/plupload.browserplus.js"></script>
<script type="text/javascript" src="libs/plupload/js/plupload.html4.js"></script>
<script type="text/javascript" src="libs/plupload/js/plupload.html5.js"></script>
<script type="text/javascript" src="js/system/data.js"></script>
</head>
<body>
<ul class="updata-type-list">
		<li style="margin-left: 15px;" class="olt-up"><span id="1" class="select">OLT上下行端口流量</span></li>
		<li style="margin-left: 60px;"  class="dalam-up"><span id="2">DSLAM上行端口流量</span></li> 
		<li style="margin-left: 60px;" class="net-quality"><span id="3">DSL线路质量(接入网)</span></li> 
		<li style="margin-left: 60px;"  class="dsl-speed"><span id="4">DSL线路质量(DSL优化提速)</span></li> 
		<li style="margin-left: 60px;" class="center-flow"><span id="5">IP城域网汇聚层以上中继流量</span></li>
	</ul>	
	<hr />	
	<br/>
	
	<div class="up-box">
	  <table>
	  <tr>
	  <td><div id="fileInfo" class="inputCss"></div></td>
	  <td>
	    <button style="margin-left: 5px;" class="updata-btn" id="browse">添加文件</button>
		<button class="update-ok" id="start_upload">确定上传</button>
	  </td>
	  </tr>
	  </table>
		<!-- <input type="text" id="fileInfo" disabled="disabled"/> -->
	</div>
    <span id="uploadprocess"></span>
    <br/>
    <table  class="display" cellspacing="0" width="100%" id="uploadDiv"></table>
</body>
</html>
