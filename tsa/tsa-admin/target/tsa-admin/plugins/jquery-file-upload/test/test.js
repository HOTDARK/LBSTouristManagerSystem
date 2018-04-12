var myec;
    var mychart;
    $(document).ready(function(){
	    require.config({
	    	packages: [
	   	            {
	   	                name: 'echarts',
	   	                location: base+"/plugins/echarts",
	   	                main: 'echarts'
	   	            }
	   	        ]
	    });
	    require(
	            [
	                'echarts',
	                'echarts/chart/bar',
	                'echarts/chart/line'
	            ],
	            function (ec) {
	            	myec = ec;
	            	myChart = myec.init(document.getElementById('main'));
	            	
	            	$.ajax({
	            		url: base+"/reports/schemaTitles.do",
	            		type: 'get',
	            		dataType: 'JSON',
	            		success: function(rsObj){
	            			if(rsObj.success){
	            				initChart({
	        	            		dataX : ["请查询"] ,
	        	            		a: rsObj.result,
	        	            		seriesData : rsObj.initlist
	        	            	});
	            			}
	            		}
	            	});
	            }
	    );

	    $("#main").height($(this).height()/10*9);
	    
	    $(window).resize(function(){
	    	setTimeout(function(){
			    $("#main").height($(this).height()/10*9);
	    		myChart.resize();
	    	}, 300);
	    });
    });
    
    function search(){
    	if(""==$("#startTime").val() || ""==$("#endTime").val()){
    		layer.msg("请正确选择查询时间！",2,5);
			return false;
    	}
    	$.ajax({
    		url: "${BASE_PATH}/reports/diagnosisReport.do",
    		type: 'get',
    		dataType: 'JSON',
    		data:{startTime:$("#startTime").val() , endTime:$("#endTime").val()},
    		success: function(rsObj){
    			if(rsObj.success){
    				initChart(rsObj);
    			}else{
    				alert("统计错误");
    			}
    		}
    	});
    }
    
    function initChart(rsObj){
           var option = {
       		    title : {
                      text: "宽带故障统计报表",
                 },
                 tooltip : {
                     trigger: 'item' ,
                     axisPointer : {            // 坐标轴指示器，坐标轴触发有效
             	         type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
             	     }
                 },
                 grid : {
                	 x :70,
                	 y :100,
                	 x2:70,
                	 y2:50
                 },
                 legend: {
                     data: rsObj.a,
                     y: 50
                 },
                 toolbox: {
                     show : true,
                     feature : {
                         mark : {show: true},
                         magicType : {show: true, type: ['line', 'bar']},
                         restore : {show: true},
                         saveAsImage : {show: true}
                     }
                 },
                 xAxis : [
                     {
                         type : 'category',
                         data : rsObj.dataX
                     }
                 ],
                 yAxis : [
                     {
                         type : 'value'
                     }
                 ],
                 series : rsObj.seriesData
           }
           myChart.setOption(option);
    }