/**
 * 餐品管理
 */
var totalPages;
var pageNum=1
$(function() {
    FastClick.attach(document.body);
    getProducts();
    $(document.body).pullToRefresh(function() {
    	pageNum=1;
		totalPages=0;
		$(document.body).destroyInfinite();
		getProducts();
        setTimeout(function() {
        	$(document.body).pullToRefreshDone();
        }, 2000);
	});
});

/**
 * 获取餐品数据
 */
function getProducts(){
	Utils.ajax({
		url: 'productsManage/getProductList.action',
		data:{"storeId":storeId, "pageNum":pageNum},
		success: function(data){
			if (data.success) {
				totalPages=data.totalPages;
				var html = getHtml(data.proList);
				if (pageNum==1) {
					$("#proList").empty();
					$("#proList").html(html);
					$(document.body).pullToRefresh();
					if (totalPages > 1) {
						var backLoading = false;
						$(document.body).infinite().on("infinite", function() {
							if (backLoading) return;
							backLoading = true;
							getProducts();
							setTimeout(function() {
								backLoading = false;
							}, 1000);
						});
					}
				}else{
					$("#proList").append(html);
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

getHtml = function(data){
	var html = '';
	if (totalPages==0) {
		html += '<div class="text-center" style="margin-top: 15%;">';
		html += '	<img src="images/nodata.png" width="70%"/>';
		html +=	'	<p class="ft18 ft-grey-999">暂无餐品类型</p>';
		html += '	<div style="height:100px;">&nbsp;</div>';
		html += '</div>';
	}else{
		for (var i = 0; i < data.length; i++) {
			html += '<div class="weui-cell weui-cell_swiped line-d1e bg-white ft14">';
			html += '	<div class="weui-cell__bd">';
			html += '		<div class="weui-cell">';
			if (data[i].state==0) {
				html += '		<div class="weui-cell__hd" style="width: 36px"><img src="images/icon-ordering07.png" width="20" height="20"/></div>';
			}else{
				html += '		<div class="weui-cell__hd" style="width: 36px"><img src="images/icon-ordering06.png" width="20" height="20"/></div>';
			}
			html += '			<div class="weui-cell__bd">';
			html += '				<span style="display: inline-table; width: 25%;">'+data[i].productsName+'</span>';
			html += '				<span class="ft-grey-999">'+data[i].productsIntr+'</span>';
			html += '			</div>';
			html += '		</div>';
			html += '	</div>';
			html += '	<div class="weui-cell__ft">';
			html += '		<a class="weui-swiped-btn weui-swiped-btn_default  close-swipeout" href="javascript:void(0);" onclick="editPro('+data[i].id+')">修改</a>';
			if (data[i].state==0) {
				html += '		<a class="weui-swiped-btn bg-green-merald close-swipeout" href="javascript:void(0);" onclick="activePro('+data[i].id+')">激活</a>';
				html += '		<a class="weui-swiped-btn weui-swiped-btn_warn close-swipeout" href="javascript:void(0);" onclick="deletePro('+data[i].id+')">删除</a>';
			}else{
				html += '	<a class="weui-swiped-btn bg-orange close-swipeout" href="javascript:void(0);" onclick="frozenPro('+data[i].id+')">冻结</a>';
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
 * 激活餐品
 * @param id
 */
function activePro(id){
	Utils.ajax({
		url: 'productsManage/activePro.action',
		data: {id : id},
		success: function(data){
			if (data.success) {
				pageNum=1;
				totalPages=0;
				getProducts();
			}else{
				$.alert("激活餐品失败");
			}
		}
	});
}

/**
 * 冻结餐品
 * @param id
 */
function frozenPro(id){
	$.confirm({
		title: '确认冻结餐品',
		text: '您确定要冻结该餐品以及该餐品的所有关系吗？',
		onOK: function () {
			Utils.ajax({
				url: 'productsManage/frozenPro.action',
				data: {id : id},
				success: function(data){
					if (data.success) {
						pageNum=1;
						totalPages=0;
						getProducts();
					}else{
						$.alert("冻结餐品失败");
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
 * 编辑餐品
 * @param id
 */
function editPro(id){
	if (id!=null) {
		Utils.ajax({
			url: 'productsManage/getProduct.action',
			data:{id : id},
			success: function(data){
				if (data.success) {
					var pro = data.pro;
					$("input[name='productsName']").val(pro.productsName);
					$("input[name='productsIntr']").val(pro.productsIntr);
					$("input[name='id']").val(pro.id);
					$("input[name='seqNum']").val(pro.seqNum);
//					$("input[name='state']").val(pro.state);
					$("textarea[name='remark']").val(pro.remark);
//					if (pro.state==1) {
//						$("#switchCP").prop("checked","checked");
//					}else{
//						$("#switchCP").removeAttr("checked");
//					}
					$("#editProduct").popup();
				}else{
					$.alert(data.msg);
				}
			}
		})
	}else{
		$("#editProduct").popup();
		$("input[name='productsName']").val(null);
		$("input[name='productsIntr']").val(null);
		$("input[name='id']").val(null);
		$("input[name='seqNum']").val(null);
//		$("input[name='state']").val(null);
		$("textarea[name='remark']").val(null);
//		$("#switchCP").removeAttr("checked");
	}
}

/**
 * 保存餐品
 */
function savePro(){
	if ($("input[name='productsName']").val()==null || $("input[name='productsName']").val()=='') {
		$.alert("请填写餐品名称");
		return false;
	}
	if ($("input[name='seqNum']").val()==null || $("input[name='seqNum']").val()=='') {
		$.alert("请填写餐品排序号");
		return false;
	}
	var id=$("input[name='id']").val();
	var url='',msg='';
	if (id!=null && id!='') {
		url='productsManage/editProduct.action';
		msg='修改餐品失败';
	}else{
		url='productsManage/insertProduct.action';
		msg='新增餐品失败';
	}
	Utils.ajax({
		url: url,
		data: Utils.serializeObj($("#proForm")),
		success: function(data){
			if (data) {
				$("input[name='productsName']").val(null);
				$("input[name='productsIntr']").val(null);
				$("input[name='id']").val(null);
				$("input[name='seqNum']").val(null);
//				$("input[name='state']").val(null);
				$("textarea[name='remark']").val(null);
//				$("#switchCP").removeAttr("checked");
				$.closePopup();
				pageNum=1;
				totalPages=0;
				getProducts();
			}else{
				$.alert(msg);
			}
		}
	})
}

/**
 * 验证餐品状态
 */
function checkState(){
	var result = $("#switchCP").is(':checked');
	if (result) {
		$("#state").val(1);
	}else{
		$("#state").val(0);
	}
}

/**
 * 删除餐品及其关系
 */
function deletePro(){
	$.confirm({
		title: '确认删除餐品',
		text: '您确定要删除该餐品以及该餐品的所有关系吗？',
		onOK: function () {
			Utils.ajax({
				url: 'productsManage/deletePro.action',
				data: {id : id},
				success: function(data){
					if (data.success) {
						pageNum=1;
						totalPages=0;
						getProducts();
					}else{
						$.alert("删除餐品失败");
					}
				}
			});
		},
		onCancel: function () {
			$('.weui-cell_swiped').swipeout('close');
		}
	});
}