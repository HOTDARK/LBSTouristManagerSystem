/**
 * 欢迎界面脚本
 */
// echarts3.0以上加载 开始******
$(function(){
	// 加载功能使用统计
	loadLine({});
});
// echarts3.0以上加载 结束******

/**
 * 加载功能使用统计
 * @param param
 */
function loadLine(param){
	var chart = echarts.init(document.getElementById('line'), "macarons");
	// 加载固定项目
	var option = {
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
				option.xAxis = [
        	        {
        	            type : 'category',
        	            boundaryGap : false,
        	            data : data.xAxis
        	        }
        	    ];
				option.series = [
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
				chart.setOption(option);
			} else {
				Utils.showMsg("error", data.msg);
			}
		}
	});
}
