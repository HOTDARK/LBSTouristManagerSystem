$(function(){
	getBarData({});
});

function getBarData(param){
	var barChart = echarts.init(document.getElementById('numBar'), "macarons");
	// 加载固定项目
	var optionBar = {
	    title : {
	        text: '功能使用统计'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['使用总数']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	        	mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']}
	        }
	    },
	    calculable : true,
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} 次'
	            }
	        }
	    ]
	};
	// 加载动态项目
	Utils.ajax({
		url:"sysLogFunc/syslogCountByArea.action",
		data:{orgId:param.orgId,stime:param.stime,etime:param.etime},
		success:function(data){
			if(data.success){
				optionBar.xAxis = [
        	        {
        	            type : 'category',
        	            boundaryGap : false,
        	            data : data.xAxis
        	        }
        	    ];
				optionBar.series = [
			        {
			            name:'使用总数',
			            type:'line',
			            data:data.nums,
			            markPoint : {
			                data : [
			                    {type : 'max', name: '最大值'},
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
			        }
			    ];
				// 为echarts对象加载数据
				barChart.setOption(optionBar);
			} else {
				Utils.showMsg("error", data.msg);
			}
		}
	});
}

function linkToDetail(area){
	var url = 'sysLogFunc/toFunctionCountListPage.do?1=1';
	url+='&startTime='+$("#startTime").val();
	url+='&endTime='+$("#endTime").val();
	url+='&area='+encodeURI(area);
	toMenu("event",url, '详细功能使用统计');
}


function exportCount(){
	$("#conditionForm").submit();
}