/**
 * 欢迎界面脚本
 */
// echarts3.0以上加载 开始******
$(function(){
	loadPieNest();
	loadPie();
	loadLine();
	loadLineArea();
	loadBar(); 
	loadBarMore();
});
// echarts3.0以上加载 结束******

function loadPieNest(){
	var chart = echarts.init(document.getElementById('pieNest'));
	var option = {
		title : {
	        text: '某站点用户访问来源',
	        subtext: '纯属虚构',
	        x:'center'
	    },
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        x: 'left',
	        data:['直达','营销广告','搜索引擎','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他']
	    },
	    series: [
	        {
	            name:'访问来源',
	            type:'pie',
	            selectedMode: 'single',
	            radius: [0, '30%'],

	            label: {
	                normal: {
	                    position: 'inner'
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:[
	                {value:335, name:'直达', selected:true},
	                {value:679, name:'营销广告'},
	                {value:1548, name:'搜索引擎'}
	            ]
	        },
	        {
	            name:'访问来源',
	            type:'pie',
	            radius: ['40%', '55%'],

	            data:[
	                {value:335, name:'直达'},
	                {value:310, name:'邮件营销'},
	                {value:234, name:'联盟广告'},
	                {value:135, name:'视频广告'},
	                {value:1048, name:'百度'},
	                {value:251, name:'谷歌'},
	                {value:147, name:'必应'},
	                {value:102, name:'其他'}
	            ]
	        }
	    ]
	};
	chart.setOption(option);
}

function loadPie(){
	var chart = echarts.init(document.getElementById('pie'));
	var option = {
	    title : {
	        text: '某站点用户访问来源',
	        subtext: '纯属虚构',
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
	    },
	    series : [
	        {
	            name: '访问来源',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:[
	                {value:335, name:'直接访问'},
	                {value:310, name:'邮件营销'},
	                {value:234, name:'联盟广告'},
	                {value:135, name:'视频广告'},
	                {value:1548, name:'搜索引擎'}
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
	chart.setOption(option);
}

function loadLine(){
	var chart = echarts.init(document.getElementById('line'));
	var option = {
	    title : {
	        text: '未来一周气温变化',
	        subtext: '纯属虚构'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['最高气温','最低气温']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['周一','周二','周三','周四','周五','周六','周日']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            axisLabel : {
	                formatter: '{value} °C'
	            }
	        }
	    ],
	    series : [
	        {
	            name:'最高气温',
	            type:'line',
	            data:[11, 11, 15, 13, 12, 13, 10],
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
	        },
	        {
	            name:'最低气温',
	            type:'line',
	            data:[1, -2, 2, 5, 3, 2, 0],
	            markPoint : {
	                data : [
	                    {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        }
	    ]
	};
	chart.setOption(option);
}

function loadLineArea(){
	var chart = echarts.init(document.getElementById('lineArea'));
	var option = {
	    title : {
	        text: '某楼盘销售情况',
	        subtext: '纯属虚构'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['意向','预购','成交']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['周一','周二','周三','周四','周五','周六','周日']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'成交',
	            type:'line',
	            smooth:true,
	            itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[10, 12, 21, 54, 260, 830, 710]
	        },
	        {
	            name:'预购',
	            type:'line',
	            smooth:true,
	            itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[30, 182, 434, 791, 390, 30, 10]
	        },
	        {
	            name:'意向',
	            type:'line',
	            smooth:true,
	            itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[1320, 1132, 601, 234, 120, 90, 20]
	        }
	    ]
	};
	chart.setOption(option);
}

function loadBar(){
	var chart = echarts.init(document.getElementById('bar'), 'macarons');
	var option = {
	    title : {
	        text: '某地区蒸发量和降水量',
	        subtext: '纯属虚构'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['蒸发量','降水量']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'蒸发量',
	            type:'bar',
	            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3],
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
	        },
	        {
	            name:'降水量',
	            type:'bar',
	            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3],
	            markPoint : {
	                data : [
	                    {name : '年最高', value : 182.2, xAxis: 7, yAxis: 183, symbolSize:18},
	                    {name : '年最低', value : 2.3, xAxis: 11, yAxis: 3}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name : '平均值'}
	                ]
	            }
	        }
	    ]
	};
	chart.setOption(option);
}

function loadBarMore(){
	var chart = echarts.init(document.getElementById('barMore'), 'macarons');
	var placeHoledStyle = {
	    normal:{
	        barBorderColor:'rgba(0,0,0,0)',
	        color:'rgba(0,0,0,0)'
	    },
	    emphasis:{
	        barBorderColor:'rgba(0,0,0,0)',
	        color:'rgba(0,0,0,0)'
	    }
	};
	var dataStyle = { 
	    normal: {
	        label : {
	            show: true,
	            position: 'insideLeft',
	            formatter: '{c}%'
	        }
	    }
	};
	var option = {
	    title: {
	        text: '多维条形图',
	        subtext: 'From ExcelHome',
	        sublink: 'http://e.weibo.com/1341556070/AiEscco0H'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        },
	        formatter : '{b}<br/>{a0}:{c0}%<br/>{a2}:{c2}%<br/>{a4}:{c4}%<br/>{a6}:{c6}%'
	    },
	    legend: {
	        y: 55,
	        itemGap : document.getElementById('barMore').offsetWidth / 8,
	        data:['GML', 'PYP','WTC', 'ZTW']
	    },
	    toolbox: {
	        show : true,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    grid: {
	        y: 80,
	        y2: 30
	    },
	    xAxis : [
	        {
	            type : 'value',
	            position: 'top',
	            splitLine: {show: false},
	            axisLabel: {show: false}
	        }
	    ],
	    yAxis : [
	        {
	            type : 'category',
	            splitLine: {show: false},
	            data : ['重庆', '天津', '上海', '北京']
	        }
	    ],
	    series : [
	        {
	            name:'GML',
	            type:'bar',
	            stack: '总量',
	            itemStyle : dataStyle,
	            data:[38, 50, 33, 72]
	        },
	        {
	            name:'GML',
	            type:'bar',
	            stack: '总量',
	            itemStyle: placeHoledStyle,
	            data:[62, 50, 67, 28]
	        },
	        {
	            name:'PYP',
	            type:'bar',
	            stack: '总量',
	            itemStyle : dataStyle,
	            data:[61, 41, 42, 30]
	        },
	        {
	            name:'PYP',
	            type:'bar',
	            stack: '总量',
	            itemStyle: placeHoledStyle,
	            data:[39, 59, 58, 70]
	        },
	        {
	            name:'WTC',
	            type:'bar',
	            stack: '总量',
	            itemStyle : dataStyle,
	            data:[37, 35, 44, 60]
	        },
	        {
	            name:'WTC',
	            type:'bar',
	            stack: '总量',
	            itemStyle: placeHoledStyle,
	            data:[63, 65, 56, 40]
	        },
	        {
	            name:'ZTW',
	            type:'bar',
	            stack: '总量',
	            itemStyle : dataStyle,
	            data:[71, 50, 31, 39]
	        },
	        {
	            name:'ZTW',
	            type:'bar',
	            stack: '总量',
	            itemStyle: placeHoledStyle,
	            data:[29, 50, 69, 61]
	        }
	    ]
	};
	chart.setOption(option);
}
