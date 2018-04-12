/**
 * 安全检查管理脚本
 */
var totalPages=0;
var pageNum=1
$(function(){
	FastClick.attach(document.body);
	getSafetyCheckList();
	$(document.body).pullToRefresh(function() {
    	pageNum=1;
		totalPages=0;
		$(document.body).destroyInfinite();
		getSafetyCheckList();
        setTimeout(function() {
        	$(document.body).pullToRefreshDone();
        }, 2000);
	});
});

function getSafetyCheckList(){
	Utils.ajax({
		url: 'safetyCheck/getSafetyCheckList.action',
		data:{"driver":account, "pageNum":pageNum},
		success: function(data){
			if (data.success) {
				totalPages=data.pageNums;
				var html = getSafetyHtml(data.list);
				if (pageNum==1) {
					$("#safetyList").empty();
					$("#safetyList").html(html);
					$(document.body).pullToRefresh();
					if (totalPages > 1) {
						var backLoading = false;
						$(document.body).infinite().on("infinite", function() {
							if (backLoading) return;
							backLoading = true;
							getSafetyCheckList();
							setTimeout(function() {
								backLoading = false;
							}, 1000);
						});
					}
				}else{
					$("#safetyList").append(html);
					if (totalPages <= pageNum) {
						$("#wxBackLoadmore").hide();
						$(document.body).destroyInfinite();
					}
				}
				$('.weui-cell_swiped').swipeout();
				pageNum = parseInt(pageNum) + parseInt(1);
				$.hideLoading();
			}else{
//				$.alert(data.msg);
			}
		}
	});
}

function getSafetyHtml(data){
	var html = '';
	if (totalPages==0) {
		html += '<div class="text-center" style="margin-top: 15%;">';
		html += '	<img src="images/nodata.png" width="70%"/>';
		html +=	'	<p class="ft18 ft-grey-999">暂无安全检查数据</p>';
		html += '	<div style="height:100px;">&nbsp;</div>';
		html += '</div>';
	}else{
		for (var i = 0; i < data.length; i++) {
			var serviceRecord=data[i].serviceRecord;
			if (serviceRecord.length>20) {
				serviceRecord=serviceRecord.substring(0,20)+'...';
			}
			if (i==0) {
				html += "<div class='bg-white'>";
			}else{
				html += "<div class='bg-white mar-t6'>";
			}
			html += "	<div class='ft12 pull-left ft14 ft-deep-blue mar-l10 hei-30'>";
			html += "		<i class='icon-number pull-left mar-t6'></i>";
			html += "		<span class='text-number pull-left mar-t6 '>"+data[i].vehicleLicense+"</span>";
			html += "	</div>";
			html += "	<div class='pull-right mar-r10'>";
			if (data[i].checkResult==0) {
				html += "	<i class='bg-blue tip-state'>未确认</i>";
			}else if (data[i].checkResult==1){
				html += "	<i class='bg-green-merald tip-state'>已确认</i>";
			}else if (data[i].checkResult==2){
				html += "	<i class='bg-red tip-state'>已驳回</i>";
			}
			html += "	</div>";
			html += "	<p class='clearfix line-1eee'></p>";
			html += "	<a href='javascript:void(0)' onclick='jumpPage(\"safetyCheck/goSafetyCheckDetailed.action?id="+data[i].id+"\")'  class='weui-media-box weui-media-box_appmsg' style='padding-bottom: 8px;'>"
			html += "		<div class='weui-media-box__bd'>";
			html += "			<h4 class='weui-media-box__title ft-bold400'>检查人员："+data[i].inspectorName+"</h4>";
			html += "			<p class='line-x1eee'></p>"
			html += "			<p class='weui-media-box__desc mar-t6 ft-grey-666 ft14'>"+serviceRecord+"</p>";
			html += "			<p class='weui-media-box__desc ft12 ft-grey-999 text-number mar-t3'>检查日期："+new Date(data[i].createTime).format("yyyy-MM-dd hh:mm")+"</p>";
			html += "		</div>";
			html += "	</a>";
			html += "</div>";
			html += "<div style='height: 1px;'>&nbsp;</div>";
		}
		if (totalPages == pageNum) {
			html += '<div class="weui-loadmore weui-loadmore_line">';
			html +=	'	<span class="weui-loadmore__tips" style="background-color: transparent;">没有更多数据了</span>';
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