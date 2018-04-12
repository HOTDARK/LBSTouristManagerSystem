<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>功能能列表</title>
    <script type="text/javascript">
	
      function show(div_id,title_name,print_date){
         var chart;
         var date1= Number(print_date[0]);
         var date2= Number(print_date[1]);
          $(document).ready(function () {
            
            // Build the chart
              $('#'+div_id).highcharts({
                  chart: {
                      plotBackgroundColor: null,
                      plotBorderWidth: null,
                      plotShadow: false
                  },
                  exporting:{
                     enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
                   },
                  title: {
                      text: title_name
                  },
                  colors:[
                      '#FF00FF',
                      '#0000FF',
                      '#D8DDE3',
                  ],
                  tooltip: {
                	  pointFormat: '{series.name}: <b>{point.y}G</b>',
                      percentageDecimals: 1
                  },
                  plotOptions: {
                      pie: {
                          allowPointSelect: true,
                          cursor: 'pointer',
                          dataLabels: {
                              enabled: false
                          },
                          showInLegend: true
                      }
                  },
                  series: [{
                      type: 'pie',
                      name: '值',
                      data: [['空闲',date2],
                             ['已使用',date1]
                            ]
                  }]
              });
          });
      }
    </script>
     
  </head>
  
  <body>
   <hr>盘符<hr>
	  	<c:forEach items="${monitoringInfo.sysDrive}" var="drive" varStatus="status">  
		  	<div class="div5" id="bar${ status.index + 1}"></div>
	  	</c:forEach>
  	
  	<script type="text/javascript">
  	$(function () {
  		var data = ${monitoringInfo.jsonDrive};
  		var num = 0;
  		 $.each(data,function(index,obj){ 
  			 num++;
  			 var soure =[obj.driveUsage,obj.driveFree];
  	         show('bar'+num,obj.driveName,soure);
  		 }) 
     });
  	</script>
  	<br><br><br><br><br><br><br><br><br><br>
  <hr>系统<hr>
  <table class="table4">
  	<tr>
  	<td>操作系统<spring:message code="public.txt.colon"/></td>
  	<td> ${monitoringInfo.systemKernel }</td>
  	</tr>
  	<tr>
  	<td>操作系统的描述<spring:message code="public.txt.colon"/></td>
  	<td>${monitoringInfo.systemDescribe }</td>
  	</tr>
  	<tr>
  	<td>操作系统的版本号<spring:message code="public.txt.colon"/></td>
  	<td>${monitoringInfo.systemVersion }</td>
  	</tr>
  	<tr>
  	<td>cpu数量<spring:message code="public.txt.colon"/></td>
  	<td>${monitoringInfo.cupNum }</td>
  	</tr>
  	<tr>
  	<td>cpuMHz<spring:message code="public.txt.colon"/></td>
  	<td>${monitoringInfo.cupMHz }</td>
  	</tr>
  	<tr>
  	<td>Cup类型<spring:message code="public.txt.colon"/></td>
  	<td>${monitoringInfo.cupType }</td>
  	</tr>
  	<tr>
  	<td>CPU生产商<spring:message code="public.txt.colon"/></td>
  	<td>${monitoringInfo.cupFactory }</td>
  	</tr>
  	<tr>
  	<td>内存总量<spring:message code="public.txt.colon"/></td>
  	<td>${monitoringInfo.memSize}</td>
  	</tr>
  </table>
  <hr>jvm<hr>
  <table class="table5">
  	<tr>
  	<td>JVM可以使用的总内存<spring:message code="public.txt.colon"/></td>
  	<td>${monitoringInfo.jvmMemSize }</td>
  	</tr>
  	<tr>
  	<td>Java的运行环境版本<spring:message code="public.txt.colon"/></td>
  	<td>${monitoringInfo.jvmVersion }</td>
  	</tr>
  	<td></td>
  	<td></td>
  	</tr>
  </table>
  <hr> 网络<hr>
  <table class="table6">
  	<tr>
  	<td>状态<spring:message code="public.txt.colon"/></td>
  	<td>${monitoringInfo.netState }</td>
  	</tr>
  	<tr>
  	<td>接收的总包裹数:</td>
  	<td>${monitoringInfo.netRxPackets}</td>
  	</tr>
  	<tr>
  	<td>发送的总包裹数:</td>
  	<td>${monitoringInfo.netTxPackets}</td>
  	</tr>
  	<tr>
  	<td>接收到的总字节数:</td>
  	<td>${monitoringInfo.netRxBytes}</td>
  	</tr>
  	<tr>
  	<td>发送的总字节数:</td>
  	<td>${monitoringInfo.netTxBytes}</td>
  	</tr>
  	</tr>
  	<td>接收到的错误包数:</td>
  	<td>${monitoringInfo.netRxErrors}</td>
  	</tr>
  	</tr>
  	<td>发送数据包时的错误数:</td>
  	<td>${monitoringInfo.netTxErrors}</td>
  	</tr>
  	</tr>
  	<td>接收时丢弃的包数:</td>
  	<td>${monitoringInfo.netRxDropped}</td>
  	</tr>
  	</tr>
  	<td>发送时丢弃的包数:</td>
  	<td>${monitoringInfo.netTxDropped}</td>
  	</tr>
  </table>
  </body>
</html>
