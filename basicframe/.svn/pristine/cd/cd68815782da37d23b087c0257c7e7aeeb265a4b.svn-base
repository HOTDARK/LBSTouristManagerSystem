<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>功能能列表</title>
    <script type="text/javascript" src="plugins/highcharts/js/highcharts.js"></script>
    <script type="text/javascript" src="plugins/highcharts/js/modules/exporting.js"></script>
    <style type="text/css">
    
    .div1 {
	  height: 300px;
	  width: 300px;
	  margin:2px;
	}
	
	.div2 {
	  height:300px;
	  width: 500px;
	  margin:2px;
	  position: absolute;
	  top: 3px;
	  left: 314px;
	}
	
	.div5 {
	  float:left;
	  height: 200px;
	  width: 200px;
	}
    
	.table5 {
	  height: 40px;
	  width: 400px;
	}
	
	.table6 {
	  height: 200px;
	  width: 400px;
	}
    
	.table7 {
	  height: 200px;
	  width: 400px;
	}
	    
    
    </style>
    <script type="text/javascript">
    $(function(){    
    	
    	$.ajax({
            type: "POST",
            cache: false,
            url: "monitorin/querMonitorinInfo.action",
            dataType: "html",
            success: function (sourse) {
            	$("#monitoringId").html(sourse);
            }
    	});
    	
    	  var cpu = 0;
    	  var mem = 0;
          var jvm = 0;
    	$(document).ready(function() {                                                  
	    	Highcharts.setOptions({                                                     
		    	global: {                                                               
		    		useUTC: false                                                       
		    	}                                                                       
    		});                                                                         
    																			
	    	 	var chart1; 
	    	$(function() { 
	    	    chart1 = new Highcharts.Chart({ 
	    	        chart: { 
	    	            renderTo: 'container1', //图表放置的容器，DIV 
	    	            defaultSeriesType: 'spline', //图表类型为曲线图 
	    	            events: { 
	    	                load: function() {  
	    	                	 var series_jvm = this.series[0];  
	    	                	 var series_mem = this.series[1];  

	    	                    //每隔5秒钟，图表更新一次，y数据值在0-100之间的随机数 
	    	                    setInterval(function() { 
	    	                        var x = (new Date()).getTime(); // 当前时间 
	    	                        var y = Number(jvm);
	    	                        var y1 = Number(mem);  
	    	                        series_jvm.addPoint([x, y], true, true); 
	    	                        series_mem.addPoint([x, y1], true, true); 
	    	                    }, 
	    	                    1000); 
	    	                } 
	    	            } 
	    	        }, 
	    	        title: { 
	    	            text: 'jvm - mem内存'  //图表标题 
	    	        }, 
	    	        xAxis: { //设置X轴 
	    	            type: 'datetime',  //X轴为日期时间类型 
	    	            tickPixelInterval: 100 //X轴标签间隔 
	    	        }, 
	    	        yAxis: { //设置Y轴 
	    	            title: {                                                            
	    	        		text: 'jvm - mem内存'                                                   
	    	        	},
	    	            //tickPositions: [0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100], // 指定竖轴坐标点的值 
		    	        plotLines: [{                                                       
		    	    		value: 0,                                                       
		    	    		width: 1,                                                       
		    	    		color: '#808080'                                                
		    	    	}]    
	    	        }, 
	    	        tooltip: {//当鼠标悬置数据点时的提示框 
	    	        	pointFormat: '{point.y}M</b>',
	                    percentageDecimals: 1
	    	        }, 
	    	        legend: { 
	    	            enabled: true  //设置图例不可见 
	    	        }, 
	    	        exporting: { 
	    	            enabled: true  //设置导出按钮不可用 
	    	        }, 
	    	        series: [{
	    	        	name:'jvm',
	    	            data: (function() { //设置默认数据， 
	    	                var data = [], 
	    	                time = (new Date()).getTime(), 
	    	                i; 
	    	 
	    	                for (i = -19; i <= 0; i++) { 
	    	                    data.push({ 
	    	                        x: time + i * 5000,  
	    	                        y: Math.random()*100 
	    	                    }); 
	    	                } 
	    	                return data; 
	    	            })() 
	    	        }, { 
	    	        	name:'mem',
	    	            data: (function() { //设置默认数据， 
	    	                var data = [], 
	    	                time = (new Date()).getTime(), 
	    	                i; 
	    	 
	    	                for (i = -19; i <= 0; i++) { 
	    	                    data.push({ 
	    	                        x: time + i * 5000,  
	    	                        y: Math.random()*1000 
	    	                    }); 
	    	                } 
	    	                return data; 
	    	            })() 
	    	        }] 
	    	    }); 
    	    });
	    
    	}); 
    });                
	</script>
  </head>
  
  
  	  
  
  <body>
	 <div id="container" class="div1" style="width:300px;height:300px;margin:5px;"></div>
	 <div id="container1" class="div2"style="width:500px;height:300px;left:350px;"></div>
	 <div id="monitoringId"></div>
  </body>
</html>

