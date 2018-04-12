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