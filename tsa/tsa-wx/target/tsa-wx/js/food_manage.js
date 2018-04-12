/**
 * 菜品管理
 */
var totalPages;
var pageNum=1
$(function() {
	FastClick.attach(document.body);
	getFoodList();
	$(document.body).pullToRefresh(function() {
    	pageNum=1;
		totalPages=0;
		$(document.body).destroyInfinite();
		getFoodList();
        setTimeout(function() {
        	$(document.body).pullToRefreshDone();
        }, 2000);
	});
});
/**
 * 上传图片
 * @param obj
 */
function uploadImg(obj){
	var serverIds='';
	wx.chooseImage({
		count: 1, // 默认9
		sizeType: ['compressed'],
	    success: function (res) {
	    	$.showLoading("图片上传中");
	    	var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	    	syncUpload(localIds);
	    }
	});
	var syncUpload = function(localIds){
		var localId = localIds.pop();
		
		wx.uploadImage({
	        localId: localId,
	        isShowProgressTips: 0,
	        success: function (res) {
	            var serverId = res.serverId; // 返回图片的服务器端ID
	            serverIds += serverId+',';
	            if(localIds.length > 0){
	                syncUpload(localIds);
	            } else {
	            	serverIds=serverIds.substring(0,serverIds.length-1);
	            	Utils.ajax({
	        			url : 'fileoper/downloadImgFromWx.action',
	        			data:{"serverIds":serverIds,"ftpDir":"diet/foodVariety"},
	        			success : function(data) {
	        				if (data.success) {
	        					var filepaths = data.paths;
	        					for (var key in filepaths) {
	        						$("#onloadImg").empty();
		        					$("#onloadImg").html("<img src='fileoper/downFile.action?filepath="+filepaths[key]+"' height='100%' width='100%' alt=''/>");
		        					$("#coverPhoto").val(filepaths[key]);
	        					}
	        				}else{
	        					$.alert("上传图片失败");
	        				}
	        				$.hideLoading();
	        			}
	        		});
	            }
	        }
		});
	}
}

/**
 * 获取菜品数据
 */
function getFoodList(){
	Utils.ajax({
		url: 'foodManage/getFoodList.action',
		data:{"storeId":storeId, "pageNum":pageNum},
		success: function(data){
			if (data.success) {
				totalPages=data.totalPages;
				var html = getFoodHtml(data.foodList);
				if (pageNum==1) {
					$("#foodList").empty();
					$("#foodList").html(html);
					$(document.body).pullToRefresh();
					if (totalPages > 1) {
						var backLoading = false;
						$(document.body).infinite().on("infinite", function() {
							if (backLoading) return;
							backLoading = true;
							getFoodList();
							setTimeout(function() {
								backLoading = false;
							}, 1000);
						});
					}
				}else{
					$("#foodList").append(html);
					if (totalPages <= pageNum) {
						$("#wxBackLoadmore").hide();
						$(document.body).destroyInfinite();
					}
				}
				$('.weui-cell_swiped').swipeout();
				pageNum = parseInt(pageNum) + parseInt(1);
				$.hideLoading();
			}else{
				$.alert(data.msg);
			}
		}
	});
}

getFoodHtml=function(data){
	var html = '';
	if (totalPages==0) {
		html += '<div class="text-center" style="margin-top: 15%;">';
		html += '	<img src="images/nodata.png" width="70%"/>';
		html +=	'	<p class="ft18 ft-grey-999">暂无菜品数据</p>';
		html += '	<div style="height:100px;">&nbsp;</div>';
		html += '</div>';
	}else{
		for (var i = 0; i <= data.length; i++) {
			var price = data[i].price;
			price = parseFloat(price).toFixed(2);
			html += '<div class="weui-cell weui-cell_swiped line-d1e bg-white ft14">';
			html += '	<div class="weui-cell__bd">';
			html += '		<div class="weui-cell">';
			if (data[i].state==0) {
				html += '		<div class="weui-cell__hd" style="width: 36px"><img src="images/icon-ordering07.png" width="20" height="20"/></div>';
			}else{
				html += '		<div class="weui-cell__hd" style="width: 36px"><img src="images/icon-ordering06.png" width="20" height="20"/></div>';
			}
			html += '			<div class="weui-cell__bd">';
			html += '				<span style="display: inline-table; width: 25%;">'+data[i].foodName+'</span>';
			html += '				<span class="ft-grey-999" style="display: inline-table; width: 50%;">'+data[i].synopsis+'</span>';
			html += '				<span class="weui-cell__ft ft-red">￥'+price+'</span>';
			html += '			</div>';
			html += '		</div>';
			html += '	</div>';
			html += '	<div class="weui-cell__ft">';
			html += '		<a class="weui-swiped-btn weui-swiped-btn_default  close-swipeout" href="javascript:void(0);" onclick="editFood('+data[i].id+')">修改</a>';
			if (data[i].state==0) {
				html += '		<a class="weui-swiped-btn bg-green-merald close-swipeout" href="javascript:void(0);" onclick="activeFood('+data[i].id+')">上架</a>';
				html += '		<a class="weui-swiped-btn weui-swiped-btn_warn close-swipeout" href="javascript:void(0);" onclick="deleteFood('+data[i].id+')">删除</a>';
			}else{
				html += '	<a class="weui-swiped-btn bg-orange close-swipeout" href="javascript:void(0);" onclick="frozenFood('+data[i].id+')">下架</a>';
			}
			html += '	</div>';
			html += '</div>';
		}
		if (totalPages == pageNum) {
			html += '<div class="weui-loadmore weui-loadmore_line">';
			html +=	'	<span class="weui-loadmore__tips">没有更多数据了</span>';
			html +=	'</div>';
		}
		if (totalPages > pageNum && pageNum == 1) {
			var htmls = '';
			htmls += '<div class="weui-loadmore" id="wxBackLoadmore">';
			htmls += '	<i class="weui-loading"></i>';
			htmls += '	<span class="weui-loadmore__tips">正在加载</span>';
			htmls += '</div>';
			$("#loadMore").html(htmls);
		}
	}
	return html;
}

/**
 * 编辑菜品
 * @param id
 */
function editFood(id){
	if (id!=null) {
		Utils.ajax({
			url: 'foodManage/getFood.action',
			data:{id : id},
			success: function(data){
				if (data.success) {
					var food = data.entity;
					var list = data.relList;
					var nameStr='',idStr='';
					for (var i = 0; i < list.length; i++) {
						idStr += list[i].id+',';
						nameStr += list[i].productsName+',';
					}
					idStr = idStr.substring(0,idStr.lastIndexOf(','));
					nameStr = nameStr.substring(0,nameStr.lastIndexOf(','));
					$("input[name='id']").val(food.id);
					$("input[name='coverPhoto']").val(food.coverPhoto);
					$("input[name='foodName']").val(food.foodName);
					$("input[name='synopsis']").val(food.synopsis);
					$("input[name='price']").val(food.price);
					$("input[name='productsIds']").val(idStr);
					$("input[name='detailInfo']").val(food.detailInfo);
					$("#proRel").attr("data-values",idStr);
					$("#proRel").val(nameStr);
					$("#onloadImg").html("<img src='fileoper/downFile.action?filepath="+food.coverPhoto+"' height='100%' width='100%' alt=''/>");
					$("#editFood").popup();
				}else{
					$.alert(data.msg);
				}
			}
		})
	}else{
		$("input[name='id']").val(null);
		$("input[name='coverPhoto']").val(null);
		$("input[name='foodName']").val(null);
		$("input[name='synopsis']").val(null);
		$("input[name='price']").val(null);
		$("input[name='productsIds']").val(null);
		$("input[name='detailInfo']").val(null);
		$("#proRel").attr("data-values","");
		$("#proRel").val(null);
		$("#editFood").popup();
	}
	getProList();
}

/**
 * 获取餐厅所有餐品
 */
function getProList(){
	var items=[];
	Utils.ajax({
		url: 'productsManage/getAllPro.action',
		data:{"storeId":storeId},
		success: function(data){
			if (data.success) {
				var list = data.proList;
				for (var i = 0; i < list.length; i++) {
					items.push({title:list[i].productsName,value:list[i].id});
				}
				$("#proRel").select({
					title: "所属餐品",
					multi: true,
					items: items,
					onClose: function(d){
						$("input[name='productsIds']").val($("#proRel").attr("data-values"));
					}
				});
			}
		}
	});
}

/**
 * 保存菜品
 */
function saveFood(){
	if ($("input[name='foodName']").val()==null || $("input[name='foodName']").val()=='') {
		$.alert("请填写菜品名称");
	}
	if ($("input[name='price']").val()==null || $("input[name='price']").val()=='') {
		$.alert("请填写菜品价格");
	}
	if ($("#proRel").val()==null || $("#proRel").val()=='') {
		$.alert("请选择所属餐品");
	}
	var id = $("#id").val();
	var url='',msg='';
	if (id!=null && id!='') {
		$("#updateUser").val(account);
		url='foodManage/editFood.action';
		msg='修改菜品失败';
	}else{
		$("#createUser").val(account);
		url='foodManage/insertFood.action';
		msg='新增菜品失败';
	}
	Utils.ajax({
		url: url,
		data: Utils.serializeObj($("#foodForm")),
		success: function(data){
			if (data) {
				$("input[name='id']").val(null);
				$("input[name='coverPhoto']").val(null);
				$("input[name='foodName']").val(null);
				$("input[name='synopsis']").val(null);
				$("input[name='price']").val(null);
				$("input[name='productsIds']").val(null);
				$("input[name='detailInfo']").val(null);
				$("#proRel").attr("data-values","");
				$("#proRel").val(null);
				$("#createUser").val(null);
				$("#updateUser").val(null);
				$.closePopup();
				pageNum=1;
				totalPages=0;
				getFoodList();
			}else{
				$.alert(msg);
			}
		}
	});
}

/**
 * 删除菜品及其所属关系
 * @param id
 */
function deleteFood(id){
	$.confirm({
		title: '确认删除菜品',
		text: '您确定要删除该菜品以及该菜品的所属关系吗？',
		onOK: function () {
			Utils.ajax({
				url: 'foodManage/deleteFood.action',
				data: {id : id},
				success: function(data){
					if (data.success) {
						pageNum=1;
						totalPages=0;
						getFoodList();
					}else{
						$.alert("删除菜品失败");
					}
				}
			});
		},
		onCancel: function () {
			$('.weui-cell_swiped').swipeout('close');
		}
	});
}

/**
 * 菜品上架
 * @param id
 */
function activeFood(id){
	Utils.ajax({
		url: 'foodManage/editFoodState.action',
		data: {id : id, state : 1},
		success: function(data){
			if (data.success) {
				pageNum=1;
				totalPages=0;
				getFoodList();
			}else{
				$.alert("菜品上架失败");
			}
		}
	});
}

/**
 * 菜品下架
 * @param id
 */
function frozenFood(id){
	$.confirm({
		title: '确认下架菜品',
		text: '您确定要将该菜品下架？',
		onOK: function () {
			Utils.ajax({
				url: 'foodManage/editFoodState.action',
				data: {id : id, state : 0},
				success: function(data){
					if (data.success) {
						pageNum=1;
						totalPages=0;
						getFoodList();
					}else{
						$.alert("菜品下架失败");
					}
				}
			});
		},
		onCancel: function () {
			$('.weui-cell_swiped').swipeout('close');
		}
	});
}