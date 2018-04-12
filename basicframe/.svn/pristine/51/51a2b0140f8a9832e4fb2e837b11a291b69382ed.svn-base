/**
 * 加载树
 * 
 * @param id 					
 * 			tableID
 * @param lengthChange 			
 * 			开关，是否显示一个每页长度的选择条（需要分页器支持）  true：允许，false：不允许
 * @param paginate				
 * 			开关，是否显示（使用）分页器 true：分页，false：不分页
 * @param sort 
			开关，是否启用各列具有按表头排序的功能 true：排序，false：不排序
 * @param sorting（组合参数，sort：true时生效）
			表头排序字段，不传默认第一列表头倒序[[ 0, "desc" ]]
 * @param columnDefs（组合参数，sort：true时生效） 
			表头排序排除字段，不传默认全部可以表头排序[{"bSortable":false, "aTargets":[]}]
 * @param info 
			是否显示从 xx到 xx /共 xx 条数据 true：显示，false：不显示
 * @param lengthMenu 
			这个为选择每页的条目数，当使用一个二维数组时，二维层面只能有两个元素，第一个为显示每页条目数的选项，第二个是关于这些选项的解释 比如：[[10, 25, 50, -1], [10, 25, 50, "All"]]
 * @param processing 
			开关，以指定当正在处理数据的时候，是否显示“正在加载。。。。”这个提示信息  true：显示，false：不显示
 * @param autoWidth 
			是否自适应列宽  true：自适应，false：不自适应
 * @param bScrollInfinite 
			开关，以指定是否无限滚动（与sScrollY配合使用），在大数据量的时候很有用。当这个标志为true的时候，分页器就默认关闭
 * @param url 
			指定要从哪个URL获取数据
 */
var dataTable = {};
var clickCheckbox = false;

/**
 * table初始化完成后需要调用的函数,用于绑定行的复选框事件
 * @param param 从用户初始化table时传入的json对象中获取,param包含id,check,singleSelect三个属性
 */
function defaultInitComplete(param){
	if(!param.check) {
		return;
	}
	$('input[name="checkbox"]').each(function(index,ele){
		$(ele).click(function(){
			//标识是否是由checkbox本身的事件，还是行事件
			clickCheckbox = true;
			if(!param.singleSelect) { // 多选
				if(ele.id == 'checkbox') { // 全选/全不选
					$('input[name="checkbox"]').prop('checked', this.checked);
					$('input[name="checkbox"]').each(function(index,ele1){
						//添加标识class，获取选中行数据是用
						$(this).closest("tr").toggleClass('dataTable_row_checked');
					});
					clickCheckbox = false;//表示不是点击的数据行复选框
				} else { //单个选中，可以多选
					//添加标识class，获取选中行数据是用
					$(this).closest("tr").toggleClass('dataTable_row_checked');
				}
			} else { //单选
				//在单选状态下阻止全选
				$('#checkbox').prop('checked', false);
				if (this.checked) {
					$('input[name="checkbox"]').prop('checked', false);
					$(this).prop('checked', true);
					//添加标识class，获取选中行数据是用
					myDateTable.$('.dataTable_row_checked').removeClass('dataTable_row_checked');
					$(this).closest("tr").addClass('dataTable_row_checked');
				} else {
					//添加标识class，获取选中行数据是用
					$(this).closest("tr").removeClass('dataTable_row_checked');
				}
			}
		});
	});
}

/**
 * table绘制完成后的回调,用于绑定行的点击事件
 * @param param 从用户初始化table时传入的json对象中获取,param包含id,check,singleSelect三个属性
 */
function defaultDrawCallback(param){
	if(!param.check) {
		return;
	}
	//为行绑定单击事件，单击时让行选中时，赋予选中行样式
	$('#'+param.id+' tbody tr').click(function () {
	
		if(clickCheckbox){ // 如果单击复选框就不触发行事件
			clickCheckbox = false;
			return;
		}
		if(param.singleSelect) { //单选
			if ($(this).find('input[name="checkbox"]')[0].checked) {
				$(this).find('input[name="checkbox"]').prop('checked', false);
				//添加标识class，获取选中行数据是用
				$(this).removeClass('dataTable_row_checked');
			} else {
				$('input[name="checkbox"]').prop('checked', false);
				$(this).find('input[name="checkbox"]').prop('checked', true);
				//添加标识class，获取选中行数据是用
				myDateTable.$('.dataTable_row_checked').removeClass('dataTable_row_checked');
				$(this).addClass('dataTable_row_checked');
			}
		} else { //多选
			if ( $(this).find('input[name="checkbox"]')[0].checked ) {
				$(this).find('input[name="checkbox"]').prop('checked', true);
			} else {
				$(this).find('input[name="checkbox"]').prop('checked', false);
			}
			//添加标识class，获取选中行数据是用
			$(this).toggleClass('dataTable_row_checked');
		}
	});
}
dataTable = function (param) {
	
	//============================================
	//============初始全局变量默认值===============
	//============================================
	
	/**
	 * 描述：开关，是否单选
	 * 结果：true：单选，false：多选
	 * 默认值：false
	 */
	param.singleSelect = param.singleSelect ? true : false;
	
	/**
	 * 描述：开关，是否显示一个每页长度的选择条（需要分页器支持）
	 * 结果：true：允许，false：不允许
	 * 默认值：false
	 */
	param.lengthChange = param.lengthChange ? true : false;
	
	/**
	 * 描述：开关，是否显示（使用）分页器 
	 * 结果：true：分页，false：不分页
	 * 默认值：false
	 */
	param.paginate = param.paginate ? true : false;
	
	/**
	 * 描述：开关，是否启用各列具有按列排序的功能 
	 * 结果：true：排序，false：不排序
	 * 默认值：false
	 */
	param.sort = param.sort ? true : false;
	
	/**
	 * 描述：排序字段
	 * 结果：按第几列排序，从0开始
	 * 默认值：[[ 0, "desc" ]]
	 */
	param.sorting = param.sorting ? param.sorting : [[ 0, "desc" ]];
	
	/**
	 * 描述：排除排序字段
	 * 结果：全部可以表头排序
	 * 默认值：[{"bSortable":false, "aTargets":[]}]
	 */
	param.columnDefs = param.columnDefs ? param.columnDefs : [{"bSortable":false, "aTargets":[]}];
	
	/**
	 * 描述：开关，是否显示复选框
	 * 结果：true：允许，false：不允许
	 * 默认值：false
	 */
	param.check = param.check ? true : false;
	
	/**
	 * 描述：设置行样式
	 * 结果：left：左对齐，center：中对齐，right：右对齐，还可以传入class样式
	 * 默认值：center，居中
	 */
	param.sClass = param.sClass ? param.sClass : 'center';
	
	/**
	 * 描述：是否显示从 xx到 xx /共 xx 条数据 
	 * 结果：true：显示，false：不显示
	 * 默认值：false：不显示
	 */
	param.info = param.info ? true : false;
	
	var tempParam = {};
	tempParam.id = param.id;
	tempParam.singleSelect = param.singleSelect;
	tempParam.check = param.check;
	/** 重新组合表格初始化完成的函数start **/
	if(param.fnInitComplete){//判断用户是否定义了初始化完成的函数
		var completeFunc = param.fnInitComplete;//获取用户自定义的函数
		param.fnInitComplete = function(){
			//重写函数,执行工具类自带的方法之后再执行用户传入的函数
			defaultInitComplete(tempParam);
			completeFunc.call();
		}
	}
	else{
		param.fnInitComplete = function(){
			defaultInitComplete(tempParam);
		}
	}
	/** 重新组合表格初始化完成的函数end **/
	
	/** 重新组合表格绘制后的回调函数start **/
	if(param.fnDrawCallback){//判断用户是否定义了表格绘制完成的回调函数
		var callBackFunc = param.fnDrawCallback;//获取用户自定义的回调函数
		param.fnDrawCallback = function(){
			//重写函数,执行工具类自带的方法之后再执行用户传入的函数
			defaultDrawCallback(tempParam);
			callBackFunc.call();
		}
	}
	else{
		param.fnDrawCallback = function(){
			defaultDrawCallback(tempParam);
		}
	}
	/** 重新组合表格绘制后的回调函数end **/
	// 拼装行
	var columns = [];
	if(param.check) {
		var checkHtml = '';
		var checkColumn = {sTitle : '<label><input type="checkbox" id="checkbox" name="checkbox" ><span class="lbl"></span></label>', bSortable:false, sWidth: "10px", sClass: "center",
						  		mRender: function(data, type, full) {
									return '<label><input type="checkbox" name="checkbox" value="' + full[param.primaryKey] + '"><span class="lbl"></span></label>';
								}				  
						   };
		columns.push(checkColumn);
		
	}
	for(var i=0; i<param.columns.length; i++) {
		var column = {sTitle: param.columns[i].title, sName: (param.columns[i].sname ? param.columns[i].sname : param.columns[i].data), 
						mData: param.columns[i].data, sClass: param.sClass,sDefaultContent: '',
						mRender: param.columns[i].formatter, width:param.columns[i].width};
		columns.push(column);
	}
	var myDateTable = $('#' + param.id).DataTable({
	    bLengthChange: param.lengthChange,	
        bPaginate: param.paginate,
        bSort: param.sort,
        aaSorting: param.sorting,
        aoColumnDefs: param.columnDefs,
        bInfo: param.info,
        /*sScrollX:param.sScrollX,
        sScrollXInner:param.sScrollXInner,
        bScrollCollapse:param.bScrollCollapse,
        fixedColumns:param.fixedColumns,*/
        sAjaxDataProp : param.sAjaxDataProp!=undefined?param.sAjaxDataProp:"data",
        bProcessing: param.processing?param.processing:false,
        bAutoWidth: param.autoWidth?param.autoWidth:true,
        bScrollInfinite: param.scrollInfinite?param.scrollInfinite:false,
        iDisplayLength: param.displayLength!=undefined?param.displayLength:10,  //默认显示页码
        bSearchable: false,		//禁用自带的搜索
        bDestroy: true,			//刷新列表时，摧毁当前列表，重新生成列表（在操作列表后刷新列表时需用到，否则报错）
        bStateSave: param.stateSave?param.stateSave:false,      	//开关，是否打开客户端状态记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或重新打开浏览器，之前的状态都是保存下来的
        bFilter: false,        	//是否启用搜索
        bScrollCollapse: param.scrollCollapse?param.scrollCollapse:false,
        bServerSide: param.url == '' && param.url == undefined?false:true,
        sAjaxSource: param.url?param.url:'',
	    sDom: 'rtlipf',
	    oLanguage : {
	    	sProcessing: "正在加载中...",
		    sLengthMenu: "每页显示 _MENU_ 条记录",
		    sZeroRecords: "抱歉， 没有找到",
		    sInfo: "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
		    sInfoEmpty: "没有数据",
		    sInfoFiltered: "(从 _MAX_ 条数据中检索)",
		    sZeroRecords: "没有检索到数据",
		    sSearch: "搜索:",
		    oPaginate: {
			    sFirst: "首页",
			    sPrevious: "前一页",
			    sNext: "后一页",
			    sLast: "尾页"
	    	}
		},
		aoColumns: columns,
		fnInitComplete: param.fnInitComplete,
		fnDrawCallback: param.fnDrawCallback,
		fnServerData: function(url, dParam, func, settings) {
			//替换默认访问方式,主要处理访问时无法用json添加页面参数的问题		
			if(!param.data) {
				param.data = {};//如果页面上没有参数,则将参数设为空
			}	
			if(dParam) {//追加默认参数
				for(var i=0; i<dParam.length; i++) {
					var name = dParam[i].name;
					var value = dParam[i].value;
					param.data[name] = value;
				}
			}
			if(param.sort){
				if (settings.aaSorting) {
					var sortColumns = "";
					for (var i = 0; i < settings.aaSorting.length; i++) {
						var as = settings.aaSorting[i];
						var ao = settings.aoColumns[as[0]];
						if(ao){
							if(i == settings.aaSorting.length - 1){
								sortColumns += ao.sName + " " + as[1];
							} else {
								sortColumns += ao.sName + " " + as[1] + ",";
							}
						}
					}
					if(sortColumns){
						param.data.sortColumns = sortColumns;
					}
				}
			}
			/** 重新发起请求 **/
			Utils.ajax({
		        url: param.url,  
		        data: param.data, 
		        success: func
			});
		}
	});
	
};

/**
 * 获取当前选中行
 * @param tableId
 * @returns 如果有选中行，返回选中行对象；如果没有返回undefined
 */
dataTable.getSelectRow = function(tableId) {
	return $('#'+tableId).DataTable().rows('.dataTable_row_checked').data()[0];
};

/**
 * 获取所有选中行
 * @param tableId
 * @returns 如果有选中行，返回选中的所有行，格式：数组；如果没有返回undefined
 */
dataTable.getSelectRows = function(tableId) {
	return $('#'+tableId).DataTable().rows('.dataTable_row_checked').data();
};

/**
 * 移除所有选中行
 * @param tableId
 */
dataTable.removeSelectRow = function(tableId) {
	for(var i=0; i<dataTable.getSelectRow().length; i++) {
		$('#'+tableId+' tbody tr').removeClass('dataTable_row_checked');
	}
};

/**
 * 添加行
 */
dataTable.fnAddData = function (tableId, data) {
	$('#'+tableId).dataTable().fnAddData(data,true);
}

/**
 * 刷新表格
 * @param tableId
 */
dataTable.reload = function(tableId) {
	$('#'+tableId).DataTable().ajax.reload();
};