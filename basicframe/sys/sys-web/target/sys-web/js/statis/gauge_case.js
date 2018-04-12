/**
 * 仪表盘统计脚本
 */
// echarts3.0以上加载 开始******
$(function(){
	loadDash();
	loadDashMore();
});
//echarts3.0以上加载 结束******

//echarts2.0加载 开始******
//路径配置
require.config({
	paths: {
	echarts: 'plugins/echarts/echarts-2.0/build/dist'
	}
});   
//使用(按需加载)
require([
	'echarts',
	'echarts/chart/gauge'
	],
function (ecs) {
	loadDashOther(ecs);
});
//echarts2.0加载 结束******

function loadDash(){
	var chart = echarts.init(document.getElementById('dash'));
	var option = {
	    tooltip : {
	        formatter: "{a} <br/>{b} : {c}%"
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    series : [
	        {
	            name:'业务指标',
	            type:'gauge',
	            splitNumber: 10,       // 分割段数，默认为5
	            axisLine: {            // 坐标轴线
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: [[0.2, '#228b22'],[0.8, '#48b'],[1, '#ff4500']], 
	                    width: 8
	                }
	            },
	            axisTick: {            // 坐标轴小标记
	                splitNumber: 10,   // 每份split细分多少段
	                length :12,        // 属性length控制线长
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: 'auto'
	                }
	            },
	            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    color: 'auto'
	                }
	            },
	            splitLine: {           // 分隔线
	                show: true,        // 默认显示，属性show控制显示与否
	                length :30,         // 属性length控制线长
	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
	                    color: 'auto'
	                }
	            },
	            pointer : {
	                width : 5
	            },
	            title : {
	                show : true,
	                offsetCenter: [0, '-40%'],       // x, y，单位px
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    fontWeight: 'bolder'
	                }
	            },
	            detail : {
	                formatter:'{value}%',
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    color: 'auto',
	                    fontWeight: 'bolder'
	                }
	            },
	            data:[{value: 50, name: '完成率'}]
	        }
	    ]
	};
	
	clearInterval(timeTicket);
	var timeTicket = setInterval(function (){
	    option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
	    chart.setOption(option,true);
	},2000)
}

function loadDashOther(ecs){
	var chart = ecs.init(document.getElementById('dashOther'));
	var option = {
	    tooltip : {
	        formatter: "{a} <br/>{b} : {c}%"
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    series : [
	        {
	            name:'个性化仪表盘',
	            type:'gauge',
	            center : ['50%', '50%'],    // 默认全局居中
	            radius : [0, '75%'],
	            startAngle: 140,
	            endAngle : -140,
	            min: 0,                     // 最小值
	            max: 100,                   // 最大值
	            precision: 0,               // 小数精度，默认为0，无小数点
	            splitNumber: 10,             // 分割段数，默认为5
	            axisLine: {            // 坐标轴线
	                show: true,        // 默认显示，属性show控制显示与否
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: [[0.2, 'lightgreen'],[0.4, 'orange'],[0.8, 'skyblue'],[1, '#ff4500']], 
	                    width: 30
	                }
	            },
	            axisTick: {            // 坐标轴小标记
	                show: true,        // 属性show控制显示与否，默认不显示
	                splitNumber: 5,    // 每份split细分多少段
	                length :8,         // 属性length控制线长
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: '#eee',
	                    width: 1,
	                    type: 'solid'
	                }
	            },
	            axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
	                show: true,
	                formatter: function(v){
	                    switch (v+''){
	                        case '10': return '弱';
	                        case '30': return '低';
	                        case '60': return '中';
	                        case '90': return '高';
	                        default: return '';
	                    }
	                },
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    color: '#333'
	                }
	            },
	            splitLine: {           // 分隔线
	                show: true,        // 默认显示，属性show控制显示与否
	                length :30,         // 属性length控制线长
	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
	                    color: '#eee',
	                    width: 2,
	                    type: 'solid'
	                }
	            },
	            pointer : {
	                length : '80%',
	                width : 8,
	                color : 'auto'
	            },
	            title : {
	                show : true,
	                offsetCenter: ['-65%', -10],       // x, y，单位px
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    color: '#333',
	                    fontSize : 15
	                }
	            },
	            detail : {
	                show : true,
	                backgroundColor: 'rgba(0,0,0,0)',
	                borderWidth: 0,
	                borderColor: '#ccc',
	                width: 100,
	                height: 40,
	                offsetCenter: ['-60%', 10],       // x, y，单位px
	                formatter:'{value}%',
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    color: 'auto',
	                    fontSize : 30
	                }
	            },
	            data:[{value: 50, name: '仪表盘'}]
	        }
	    ]
	};

	clearInterval(timeTicket);
	var timeTicket = setInterval(function (){
	    option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
	    chart.setOption(option, true);
	},2000)
}

function loadDashMore(){
	var chart = echarts.init(document.getElementById('dashMore'), 'macarons');
	var option = {
	    backgroundColor: '#1b1b1b',
	    tooltip : {
	        formatter: "{a} <br/>{c} {b}"
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    series : [
	        {
	            name:'速度',
	            type:'gauge',
	            min:0,
	            max:220,
	            splitNumber:11,
	            axisLine: {            // 坐标轴线
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: [[0.09, 'lime'],[0.82, '#1e90ff'],[1, '#ff4500']],
	                    width: 3,
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            axisLabel: {            // 坐标轴小标记
	                textStyle: {       // 属性lineStyle控制线条样式
	                    fontWeight: 'bolder',
	                    color: '#fff',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            axisTick: {            // 坐标轴小标记
	                length :15,        // 属性length控制线长
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: 'auto',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            splitLine: {           // 分隔线
	                length :25,         // 属性length控制线长
	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
	                    width:3,
	                    color: '#fff',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            pointer: {           // 分隔线
	                shadowColor : '#fff', //默认透明
	                shadowBlur: 5
	            },
	            title : {
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    fontWeight: 'bolder',
	                    fontSize: 20,
	                    fontStyle: 'italic',
	                    color: '#fff',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            detail : {
	                backgroundColor: 'rgba(30,144,255,0.8)',
	                borderWidth: 1,
	                borderColor: '#fff',
	                shadowColor : '#fff', //默认透明
	                shadowBlur: 5,
	                offsetCenter: [0, '50%'],       // x, y，单位px
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    fontWeight: 'bolder',
	                    color: '#fff'
	                }
	            },
	            data:[{value: 40, name: 'km/h'}]
	        },
	        {
	            name:'转速',
	            type:'gauge',
	            center : ['25%', '55%'],    // 默认全局居中
	            radius : '50%',
	            min:0,
	            max:7,
	            endAngle:45,
	            splitNumber:7,
	            axisLine: {            // 坐标轴线
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: [[0.29, 'lime'],[0.86, '#1e90ff'],[1, '#ff4500']],
	                    width: 2,
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            axisLabel: {            // 坐标轴小标记
	                textStyle: {       // 属性lineStyle控制线条样式
	                    fontWeight: 'bolder',
	                    color: '#fff',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            axisTick: {            // 坐标轴小标记
	                length :12,        // 属性length控制线长
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: 'auto',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            splitLine: {           // 分隔线
	                length :20,         // 属性length控制线长
	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
	                    width:3,
	                    color: '#fff',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            pointer: {
	                width:5,
	                shadowColor : '#fff', //默认透明
	                shadowBlur: 5
	            },
	            title : {
	                offsetCenter: [0, '-30%'],       // x, y，单位px
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    fontWeight: 'bolder',
	                    fontStyle: 'italic',
	                    color: '#fff',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            detail : {
	                //backgroundColor: 'rgba(30,144,255,0.8)',
	               // borderWidth: 1,
	                borderColor: '#fff',
	                shadowColor : '#fff', //默认透明
	                shadowBlur: 5,
	                width: 80,
	                height:30,
	                offsetCenter: [25, '20%'],       // x, y，单位px
	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
	                    fontWeight: 'bolder',
	                    color: '#fff'
	                }
	            },
	            data:[{value: 1.5, name: 'x1000 r/min'}]
	        },
	        {
	            name:'油表',
	            type:'gauge',
	            center : ['75%', '50%'],    // 默认全局居中
	            radius : '50%',
	            min:0,
	            max:2,
	            startAngle:135,
	            endAngle:45,
	            splitNumber:2,
	            axisLine: {            // 坐标轴线
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
	                    width: 2,
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            axisTick: {            // 坐标轴小标记
	                length :12,        // 属性length控制线长
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: 'auto',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            axisLabel: {
	                textStyle: {       // 属性lineStyle控制线条样式
	                    fontWeight: 'bolder',
	                    color: '#fff',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                },
	                formatter:function(v){
	                    switch (v + '') {
	                        case '0' : return 'E';
	                        case '1' : return 'Gas';
	                        case '2' : return 'F';
	                    }
	                }
	            },
	            splitLine: {           // 分隔线
	                length :15,         // 属性length控制线长
	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
	                    width:3,
	                    color: '#fff',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            pointer: {
	                width:2,
	                shadowColor : '#fff', //默认透明
	                shadowBlur: 5
	            },
	            title : {
	                show: false
	            },
	            detail : {
	                show: false
	            },
	            data:[{value: 0.5, name: 'gas'}]
	        },
	        {
	            name:'水表',
	            type:'gauge',
	            center : ['75%', '50%'],    // 默认全局居中
	            radius : '50%',
	            min:0,
	            max:2,
	            startAngle:315,
	            endAngle:225,
	            splitNumber:2,
	            axisLine: {            // 坐标轴线
	                lineStyle: {       // 属性lineStyle控制线条样式
	                    color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
	                    width: 2,
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            axisTick: {            // 坐标轴小标记
	                show: false
	            },
	            axisLabel: {
	                textStyle: {       // 属性lineStyle控制线条样式
	                    fontWeight: 'bolder',
	                    color: '#fff',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                },
	                formatter:function(v){
	                    switch (v + '') {
	                        case '0' : return 'H';
	                        case '1' : return 'Water';
	                        case '2' : return 'C';
	                    }
	                }
	            },
	            splitLine: {           // 分隔线
	                length :15,         // 属性length控制线长
	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
	                    width:3,
	                    color: '#fff',
	                    shadowColor : '#fff', //默认透明
	                    shadowBlur: 10
	                }
	            },
	            pointer: {
	                width:2,
	                shadowColor : '#fff', //默认透明
	                shadowBlur: 5
	            },
	            title : {
	                show: false
	            },
	            detail : {
	                show: false
	            },
	            data:[{value: 0.5, name: 'gas'}]
	        }
	    ]
	};

	clearInterval(timeTicket);
	var timeTicket = setInterval(function (){
	    option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
	    option.series[1].data[0].value = (Math.random()*7).toFixed(2) - 0;
	    option.series[2].data[0].value = (Math.random()*2).toFixed(2) - 0;
	    option.series[3].data[0].value = (Math.random()*2).toFixed(2) - 0;
	    chart.setOption(option,true);
	},2000)
}