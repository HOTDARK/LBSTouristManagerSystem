var startDate=moment().subtract('days', 6).format('YYYY-MM-DD');
var endDate=moment().format('YYYY-MM-DD');
$(function(){
	$('#reportrange').daterangepicker(
                     {
                        startDate: moment().subtract('days', 6),
                        endDate: moment(),
                        showDropdowns: false,
                        showWeekNumbers: true,
                        timePicker: false,
                        timePickerIncrement: 1,
                        timePicker12Hour: true,
                        ranges: {
                           '今天': [moment(), moment()],
                           '昨天': [moment().subtract('days', 1), moment().subtract('days', 1)],
                           '最近7天': [moment().subtract('days', 6), moment()],
                           '最近30天': [moment().subtract('days', 29), moment()],
                           '当月': [moment().startOf('month'), moment().endOf('month')],
                           '上月': [moment().subtract('month', 1).startOf('month'), moment().subtract('month', 1).endOf('month')]
                        },
                        opens: 'left',
                        buttonClasses: ['btn '],
                        applyClass: 'btn-small btn-primary',
                        cancelClass: 'btn-small',
                        format: 'YYYY-MM-DD',
                        separator: ' to ',
                        locale: {
                            applyLabel: '确定',
							cancelLabel: '取消',
                            fromLabel: '从',
                            toLabel: '至',
                            customRangeLabel: '自定义时间段',
                            daysOfWeek: ['日', '一', '二', '三', '四', '五','六'],
                            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                            firstDay: 1
                        }
                     },
                     function(start, end) {
                    	startDate = start.format('YYYY-MM-DD');
                    	endDate = end.format('YYYY-MM-DD');
                    	$("#startTime").val(startDate);
                    	$("#endTime").val(endDate);
						$('#reportrange span').html(start.format('YYYY-MM-DD') + ' 至 ' + end.format('YYYY-MM-DD'));
                     }
                  );
				 
	//Set the initial state of the picker label
	$('#reportrange span').html(moment().startOf('month').format('YYYY-MM-DD') + ' 至 ' + moment().endOf('month').format('YYYY-MM-DD'));
	$("#startTime").val(moment().startOf('month').format('YYYY-MM-DD'));
	$("#endTime").val(moment().endOf('month').format('YYYY-MM-DD'));
});


var param = {
		tableId:"tableTree",
		url:"sysLogFunc/syslogCount.action",
		field:["orgName","functionName","count"],
		isLoadPage:false,
		checked:false,
		checkClass:"checks",
		format:{'':''}
};
//加载列表数据
$(function(){
	setCompVal();
	$('.selectpicker').selectpicker();
	var url="sysLogFunc/syslogCount.action?1=1"+getParam();
	param.url = url;
	c_tableTree.loadTree(param);
	c_tableFunction.tableFunction();
});

function queryCount(){
	var url="sysLogFunc/syslogCount.action?1=1"+getParam();
	param.url = url;
	c_tableTree.loadTree(param);
}

function exportCount(){
	$("#orgIdHidden").val("");
	var vv=$("#orgId").val();
	if(null != vv && "" !=vv){
		var orgid = $("#orgId").find("option:selected").text();
		$("#orgIdHidden").val(orgid);
	}
	$("#conditionForm").submit();
}

function getParam(){
	var url = '';
	var st = $("#startTime").val();
	var et = $("#endTime").val();
	var orgid = $("#orgId").find("option:selected").text();
	var functionName = $("#functionName").val();
	if(null != st && "" != st){
		url+="&stime="+st;
	}
	if(null != et && "" != et){
		url+="&etime="+et;
	}
	if(null != orgid && "" != orgid && ""!=$("#orgId").val()){
		url+="&orgId="+orgid;
	}
	if(null != functionName && "" != functionName){
		url+="&functionName="+functionName;
	}
	return url;
}

function setCompVal(){
	var btime = $("#btime").val();
	var etime = $("#etime").val();
	var orgname = $("#orgname").val();
	if(btime != "" && etime != ""){
		$("#startTime").val(btime);
		$("#endTime").val(etime);
		$("#reportrange span").html(btime + ' 至 ' + etime);
	}
	$("#orgId option").each(function() {
		if($(this).text() == orgname) {
			$("#orgId").val($(this).val());
		}
	});
}
function goBackToMain(){
	toMenu("","sysLogFunc/forwardLogFunc.action", '功能使用统计');
}