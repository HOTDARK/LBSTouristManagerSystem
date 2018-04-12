/**
 * 商家评论管理脚本
 */
var totalPages;
var pageNum=1
$(function() {
	FastClick.attach(document.body);
	getCommentList();
	$(document.body).pullToRefresh(function() {
    	pageNum=1;
		totalPages=0;
		$(document.body).destroyInfinite();
		getCommentList();
        setTimeout(function() {
        	$(document.body).pullToRefreshDone();
        }, 2000);
	});
});


function getCommentList(){
	Utils.ajax({
		url: 'commentManage/getCommentList.action',
		data:{"storeId":storeId, "pageNum":pageNum},
		success: function(data){
			if (data.success) {
				totalPages=data.totalPages;
				var html = getCommentHtml(data.commentList);
				if (pageNum==1) {
					$("#commentList").empty();
					$("#commentList").html(html);
					$(document.body).pullToRefresh();
					if (totalPages > 1) {
						var backLoading = false;
						$(document.body).infinite().on("infinite", function() {
							if (backLoading) return;
							backLoading = true;
							getCommentList();
							setTimeout(function() {
								backLoading = false;
							}, 1000);
						});
					}
				}else{
					$("#commentList").append(html);
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

getCommentHtml = function(data){
	var html = '';
	if (totalPages==0) {
		html += '<div class="text-center" style="margin-top: 15%;">';
		html += '	<img src="images/nodata.png" width="70%"/>';
		html +=	'	<p class="ft18 ft-grey-999">暂无订单评价数据</p>';
		html += '	<div style="height:100px;">&nbsp;</div>';
		html += '</div>';
	}else{
		for (var i = 0; i < data.length; i++) {
			html += '<div class="bg-white ft12">';
			html += '	<p class="hei-6 bg-grey-eee"></p>';
			html += '	<div class="line-d1e">';
			html += '		<div class="pull-right ft-grey-999 mar-r15 mar-t10">';
			html += '			<a type="button" class="btn-code bg-blue-grass" onclick="queryDetaile('+data[i].orderNum+')">详情</a>';	
			if (data[i].replayFlag==0) {
				html += '		<a type="button" class="btn-code bg-orange" onclick="replay('+data[i].id+')">回复</a>';
			}
			html += '		</div>';
			html += '		<div class="ft-grey-999 weui-cell" >';
			html += '			<div class=""><span class="">订单编号：</span>'+data[i].orderNum+'</div>';
			html += '		</div>';
			html += '	</div>';
			html += '	<div class="pull-right ft-grey-999 mar-r15 mar-t10">'+new Date(data[i].date).format("yyyy-MM-dd hh:mm")+'</div>';
			html += '	<div class="weui-cell" style="padding-top: 5px;">';
			html += '		<div class="icon-score mar-t6">';
			html += '			<div><i style="width:'+data[i].storeScore*16+'px;"></i></div>';
			html += '			<span class="txt ft-grey-666 ft14">'+data[i].userName+'</span>';
			html += '		</div>';
			html += '	</div>';
			html += '	<div class="weui-cell ft14" style="padding-top: 0">'+data[i].content+'</div>';
			if (data[i].pictureFlag==1) {
				var picList = data[i].list;
				html += '<div class="weui-cell" style="padding-top: 0">';
				for (var j = 0; j < picList.length; j++) {
					html += '<div class="weui-cell__hd">';
					html += '	<img src="fileoper/downFile.action?filepath='+picList[j].filePath+'" width="50" height="50"/> ';
					html += '</div>';
				}
				html += '</div>';
			}
			if (data[i].replayFlag==1) {
				html += '<div class="weui-cell ft-grey-999" style="padding-top: 0">';
				html += '	<span class="ft-yellow">商家回复：</span>'+data[i].replay;
				html += '</div>';
			}
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
 * 查询订单详情
 * @param orderNum
 */
function queryDetaile(orderNum){
	jumpPage("dietOrderManager/goOrderDetail.action?orderNum="+orderNum);
}

/**
 * 商家回复
 * @param id
 */
function replay(id){
	$("#id").val(id);
	$("textarea[name='replay']").val(null);
	$("#recomment").popup();
}

/**
 * 保存商家回复
 */
function saveCommentReplay(){
	var id = $("#id").val();
	var replay = $("#replay").val();
	if (replay == null || replay == '') {
		$.alert("请填写回复内容");
		return false;
	}
	Utils.ajax({
		url: 'commentManage/saveReplay.action',
		data:{"id" : id, "replay" : replay},
		success: function(data){
			if (data.success) {
				$("#id").val(null);
				$("textarea[name='replay']").val(null);
				pageNum=1;
				totalPages=0;
				getCommentList();
				$.closePopup();
			}else{
				$.alert("回复失败");
			}
		}
	});
}