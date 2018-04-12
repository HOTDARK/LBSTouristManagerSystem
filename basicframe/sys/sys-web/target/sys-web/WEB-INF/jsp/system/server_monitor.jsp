<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="plugins/jquery-ui-bootstrap/css/custom-theme/jquery-ui-1.10.0.custom.css">
<script type="text/javascript" src="plugins/jquery-ui-bootstrap/js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="plugins/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="js/common/jquery-ui-jqLoding.js"></script>
<script type="text/javascript" src="js/system/monitor.js"></script>
<style type="text/css">
.label {
	background-color: #e8f3fd;
	text-align: right;
	padding-right: 10px;
}

td span {
	margin-left: 10px;
}

.block_panel {
	float: left;
	width: 99%;
}

.block_table {
	float: left;
	width: 250px;
	text-align: left;
	border: 0;
}

.block_icon_disk {
	text-align: center;
	background:
		url(${pageContext.request.contextPath}/images/icons/other/disk.jpg)
		no-repeat;
}

.block_icon_cpuInfos {
	text-align: center;
	background:
		url(${pageContext.request.contextPath}/images/icons/other/disk.jpg)
		no-repeat;
}

.block_stat {
	background: #FFFFFF;
	border: #666666 solid 1px;
	height: 15px;
	width: 150px;
}

.label {
	color: black;
}

.ttab {
	font-size: 17px;
	line-height: 38px;
	text-align: center;
}

.form_tltle {
	background: #CAE8EA;
	border-bottom: 1px solid #C1DAD7 1px solid;
	border-top: 1px solid #C1DAD7 1px solid;
	height: 37px;
	border-left: #C1DAD7 1px solid;
	border-right: #C1DAD7 1px solid;
	line-height: 37px;
	text-align: left;
	text-indent: 1em;
	font-weight: bold;
}

.tdclass {
	background-color: #e8f3fd;
}
</style>
</head>

<body>
	<div id="navtab" style="overflow:hidden;height:99%;background: #FAFAFA">
		<ul id="myTab" class="nav nav-tabs" role="tablist">
			<li role="presentation" class="active"><a href="#useDetails"
				role="tab" data-toggle="tab" class="detail-lab addColor">使用情况</a>
			</li>
			<li role="presentation"><a href="#home" role="tab"
				data-toggle="tab" class="detail-lab addColor">运行环境</a>
			</li>
			<li role="presentation"><a href="#cpuInfo" role="tab"
				data-toggle="tab" class="detail-lab addColor">CPU信息</a>
			</li>
			<li role="presentation"><a href="#leicunInfo" role="tab"
				data-toggle="tab" class="detail-lab addColor">内存信息</a>
			</li>
			<li role="presentation"><a href="#hardesk" role="tab"
				data-toggle="tab" class="detail-lab addColor">磁盘信息</a>
			</li>
		</ul>

		<div class="tab-content">
			<div align="left" role="tabpanel" class="tab-pane fade in active"
				id="useDetails" aria-labelledBy="home-tab">
				<table style="width: 100%;">
					<tr>
						<td><div id="cpuchart"></div> <br />
							<div align="center">
								<table>
									<tr>
										<td style="width:22%;">告警阈值:</td>
										<td style="width:78%;"><span id="cpuSpanId"
											style="margin-left: 38%;"></span></td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="cpuh-slider"
												class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"
												style="width: 100%;" aria-disabled="false">
												<div id="cpuhandleId"
													class="ui-slider-range ui-widget-header"></div>
												<a id="cpucornerId"
													class="ui-slider-handle ui-state-default ui-corner-all"
													href="#"></a>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td><div id="ramchart"></div> <br />
							<div align="center">
								<table>
									<tr>
										<td style="width:22%;">告警阈值:</td>
										<td style="width:78%;"><span id="ramSpanId"
											style="margin-left: 38%;"></span></td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="ramh-slider"
												class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"
												style="width: 100%;" aria-disabled="false">
												<div id="ramhandleId"
													class="ui-slider-range ui-widget-header"></div>
												<a id="ramcornerId"
													class="ui-slider-handle ui-state-default ui-corner-all"
													href="#"></a>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</td>
						<td><div id="jvmchart"></div> <br />
							<div align="center">
								<table>
									<tr>
										<td style="width:22%;">告警阈值:</td>
										<td style="width:78%;"><span id="jvmSpanId"
											style="margin-left: 38%;"></span></td>
									</tr>
									<tr>
										<td colspan="2">
											<div id="jvmh-slider"
												class="ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all"
												style="width: 100%;" aria-disabled="false">
												<div id="jvmhandleId"
													class="ui-slider-range ui-widget-header"></div>
												<a id="jvmcornerId"
													class="ui-slider-handle ui-state-default ui-corner-all"
													href="#"></a>
											</div></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<br />
				<div align="center">
					<button type="button" onclick="modifySer();" style="width:50%;"
						class="btn btn-danger btn-lg">更新设置</button>
				</div>
			</div>

			<div role="tabpanel" class="tab-pane fade " id="home"
				aria-labelledBy="home-tab">
				<div class="block_panel">
					<table border="1" style="height: 90%;width: 100%;">
						<tr>
							<td>
								<table border="1" cellpadding="6" cellspacing="6" class="ttab"
									style="height: 100%;">

									<tr>
										<th width="160px" class="tdclass">当前服务器时间:</th>
										<td><span id="serverTime"></span>
										</td>
									</tr>
									<tr>
										<th class="tdclass">服务器名:</th>
										<td><span id="serverName"></span>
										</td>
									</tr>
									<tr>
										<th class="tdclass">操作IP:</th>
										<td><span id="ip"></span>
										</td>
									</tr>
									<tr>
										<th class="tdclass">操作系统:</th>
										<td><span id="serverOs"></span>
										</td>
									</tr>
									<tr>
										<th class="tdclass">Java目录:</th>
										<td><span id="javaHome"></span>
										</td>
									</tr>
									<tr>
										<th class="tdclass">Java版本:</th>
										<td><span id="javaVersion"></span>
										</td>
									</tr>
									<tr>
										<th class="tdclass">Java临时目录:</th>
										<td><span id="javaTmpPath"></span>
										</td>
									</tr>
									<tr>
										<th class="tdclass">JVM总内存:</th>
										<td><span id="jvmTotalMem"></span> M</td>
									</tr>
									<tr>
										<th class="tdclass">JVM最大内存容量:</th>
										<td><span id="jvmMaxMem"></span> M</td>
									</tr>
									<tr>
										<th class="tdclass">JVM空闲内存:</th>
										<td><span id="jvmFreeMem"></span> M</td>
									</tr>
								</table>
							</td>
							<td><div id="jvmchart2"></div></td>
						</tr>
					</table>
				</div>
			</div>


			<div role="tabpanel" class="tab-pane fade " id="cpuInfo"
				aria-labelledBy="home-tab">
				<div class="block_panel" id="cpuInfos"></div>
			</div>

			<div role="tabpanel" class="tab-pane fade " id="leicunInfo"
				aria-labelledBy="home-tab">
				<table border="0" cellpadding="10" cellspacing="10" class="ttab"
					style="height: 90%;">
					<tr>
						<td class="label">总内存容量:</td>
						<td><span id="totalMem"></span> M</td>
					</tr>
					<tr>
						<td class="label">已用内存:</td>
						<td><span id="usedMem"></span> M</td>
					</tr>
					<tr>
						<td class="label">可用内存:</td>
						<td><span id="freeMem"></span> M</td>
					</tr>
					<tr>
						<td class="label">使用率:</td>
						<td><div class="block_stat">
								<div id="mem_percent"
									style="background:#46AF6D;height:15px; width:30px;">&nbsp;</div>
							</div> <span id="memPercent"></span>
						</td>
					</tr>
					<tr>
						<td class="label" width="160px">总交换区容量:</td>
						<td><span id="totalSwap"></span> M</td>
					</tr>
					<tr>
						<td class="label">已用交换区:</td>
						<td><span id="usedSwap"></span> M</td>
					</tr>
					<tr>
						<td class="label">可用交换区:</td>
						<td><span id="freeSwap"></span> M</td>
					</tr>
					<tr>
						<td class="label">使用率:</td>
						<td><div class="block_stat">
								<div id="swap_percent"
									style="background:#46AF6D;height:15px; width:30px;">&nbsp;</div>
							</div> <span id="swapPercent"></span>
						</td>
					</tr>
				</table>
			</div>

			<div role="tabpanel" class="tab-pane fade" id="hardesk"
				aria-labelledBy="home-tab">
				<div class="block_panel" id="diskInfos"></div>
			</div>
		</div>



	</div>
</body>
</html>
