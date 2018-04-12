$(function() {
	if (productsId != undefined && productsId != null) {
		$.showLoading();
		ajaxRefresh(1);
	}
});
var iDisplayLength = 10;
var cp=1;
function ajaxRefresh(currentPage) {
	cp=currentPage;
	Utils.ajax({
		url : 'restaurant/getFoods.action',
		data : {
			"pageNum" : currentPage,
			"iDisplayLength" : iDisplayLength,
			"productsId" : productsId
		},
		success : function(data) {
			var dataListHtml = getHtml(data, currentPage);
			if (currentPage == 1) {
				$("#dataList").empty();
				$("#dataList").html(dataListHtml);
				$("#dataList").pullToRefresh();
				$("#dataList").on("pull-to-refresh", function() {
					ajaxRefresh(1);
				});
				$("#dataList").pullToRefreshDone();
				
				if (data.pageNums > 1) {
					var backLoading = false;
					$("#dataList").infinite().on("infinite", function() {
						console.log("加载：");
						if (backLoading)
							return;
						backLoading = true;
						cp += 1;
						ajaxRefresh(cp);
						setTimeout(function() {
							backLoading = false;
						}, 1000);
					});
				}
			} else {
				$("#dataList").append(dataListHtml);
				if (data.pageNums <= currentPage) {
					$("#wxBackLoadmore").hide();
					$("#dataList").destroyInfinite();
				}
			}
			$.hideLoading();
			$("#right-box").css("min-height",(document.body.offsetHeight-220));
		},
		error : function() {
			$.hideLoading();
		}
	});
}

getHtml = function(data, currentPage) {
	var dataListHtml = "";

	if (currentPage == 1) {
		dataListHtml += "<div class='weui-pull-to-refresh__layer'>"
				+ "<div class='weui-pull-to-refresh__arrow'></div>"
				+ "<div class='weui-pull-to-refresh__preloader'></div>"
				+ "<div class='down'>下拉刷新</div><div class='up'>释放刷新</div>"
				+ "<div class='refresh'>正在刷新</div></div>";
	}
	if (data.pageNums == 0) {
		dataListHtml += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>没有检索到数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div></div>";

	} else {
		$
				.each(
						data.list,
						function(i, j) {
							dataListHtml += "<div class='weui-media-box pad-15-0 line-b weui-media-box_appmsg bg-white'>"
									+ "<div class='weui-media-box__hd'>"
									+ "<img class='weui-media-box__thumb' style='height:70px;width:70px;' onerror='javascript:this.src=\"images/nopic.jpg\";' src='fileoper/downFile.action?filepath="
									+ j.coverPhoto
									+ "'>"
									+ "</div>"
									+ "<div class='weui-media-box__bd'>"
									+ "<h4 class='weui-media-box__title ft-bold600'>"
									+ j.foodName
									+ "</h4>"
									+ "<p class='weui-media-box__desc ft-grey-666 ft12'><span>月售"+j.sales+"单</span></p>" /* <span>&nbsp;好评100%</span>*/
									+ "<div class=''>"
									+ "<p class='ft24 mar-t3 pull-left text-number ft-red'>¥ "
									+ j.price
									+ "</p>"
									+ "<div class='ft14 ft-grey-999 mar-t10 pull-right text-number'>"
									+ "<p class='pull-right'>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='insertFood("+j.id+",\""+j.foodName+"\","+j.price+",\""+j.coverPhoto+"\")'><img src='images/icon-add.png' width='24' height='24'/></a></p>"
									+ "</div>"
									+ "</div>"
									+ "<p class='clearfix'></p>"
									+ "</div>"
									+ "</div>";
						});
		if (data.pageNums == currentPage) {
			dataListHtml += "<div class='weui-loadmore'>"
					+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
					+ "</div>";
		}
		if (data.pageNums > currentPage && currentPage == 1) {
			dataListHtml += "<div class='weui-loadmore' id='wxBackLoadmore'>"
					+ "<i class='weui-loading'></i>"
					+ "<span class='weui-loadmore__tips'>正在加载</span>"
					+ "</div>";
		}
		
		 
	}

	return dataListHtml;
}

/**
 * 查看餐厅资质
 */
function showPermitPhoto() {
	var permitPic = "fileoper/downFile.action?filepath="
			+ $("#permitPic").val();
	var licensePic = "fileoper/downFile.action?filepath="
		+ $("#licensePic").val();
	var pb = $.photoBrowser({
		items : [  {
			      image: permitPic,
			      caption: "餐饮服务许可证"
			    } ,
			    {
				  image: licensePic,
				  caption: "营业执照"
				} ]
	});
	pb.open();
}

/**
 * 查看餐厅简介图片
 * 
 * @param divNum
 */
function showIntroPic(divNum) {
	var pics = [];
	var nums = $("#introPicContainer").find("input[name='introPic']").length;
	for (var i = 1; i <= nums; i++) {
		pics.push("fileoper/downFile.action?filepath="
				+ $("#introPic" + i).val());
	}
	var pb = $.photoBrowser({
		items : pics,
		initIndex : divNum - 1
	});
	pb.open();
}

/**
 * tab切换
 * 
 * @param divId
 */
function anchor(divId) {
	for (var i = 0; i < 3; i++) {
		$("#anchor" + (i + 1)).removeClass("weui-bar__item--on");
		$("#tab" + (i + 1)).removeClass("weui-tab__bd-item--active");
	}
	$("#anchor" + divId).addClass("weui-bar__item--on");
	$("#tab" + divId).addClass("weui-tab__bd-item--active");
	
	if (divId == 2) {
		$.showLoading();
		getcomments(1);
	}
}

/**
 * 点击餐品切换菜品
 * 
 * @param cid
 * @param obj
 */
function getFoods(cid, obj) {
	$.showLoading();
	$("#left-box").find("li").removeClass("on");
	$(obj).addClass("on");
	productsId = cid;
	$("#wxBackLoadmore").hide();
	$("#dataList").destroyInfinite();
	ajaxRefresh(1);
}

var storeScore=0;
var ccp=1;
var commentLoading = false;
/**
 * 获取评论
 */
function getcomments(currentPage) {
	ccp=currentPage;
	Utils.ajax({
		url : 'restaurant/getCommets.action',
		data : {
			"pageNum" : currentPage,
			"iDisplayLength" : 10,
			"storeScore":storeScore,
			"storeId":storeId
		},
		success : function(data) {
			$.hideLoading();
			$("#quanbu").text("全部("+data.all+")");
			$("#manyi").text("满意("+data.manyi+")");
			$("#bumanyi").text("不满意("+data.bumanyi+")");
			$("#youtu").text("有图("+data.pic+")");
			var dataListHtml = getCommentsHtml(data, currentPage);
			if (currentPage == 1) {
				$("#commentsList").empty();
				$("#commentsList").html(dataListHtml);
				$("#commentsList").pullToRefresh();
				$("#commentsList").on("pull-to-refresh", function() {
					getcomments(1);
				});
				$("#commentsList").pullToRefreshDone();
				if (data.pageNums > 1) {
					
					$("body").infinite(20).on("infinite", function() {
						if (commentLoading)
							return;
						commentLoading = true;
						ccp += 1;
						getcomments(ccp);
						setTimeout(function() {
							commentLoading = false;
						}, 1000);
					});
				}
			} else {
				$("#clist").append(dataListHtml);
				if (data.pageNums <= currentPage) {
					$("#commentBackLoadmore").hide();
					$("body").destroyInfinite();
				}
			}
		}
	});
}



getCommentsHtml = function(data, currentPage) {
	var dataListHtml = "";

	if (currentPage == 1) {
		dataListHtml += "<div class='weui-pull-to-refresh__layer'>"
				+ "<div class='weui-pull-to-refresh__arrow'></div>"
				+ "<div class='weui-pull-to-refresh__preloader'></div>"
				+ "<div class='down'>下拉刷新</div><div class='up'>释放刷新</div>"
				+ "<div class='refresh'>正在刷新</div></div>"
				+ "<div id='clist'>";
	}
	if (data.pageNums == 0) {
		dataListHtml += "<div class='text-center' style='margin-top: 15%;'>"
				+ "<img src='images/nodata.png' width='70%' alt=''/>"
				+ "<p class='ft18 ft-grey-999'>没有检索到数据</p>"
				+ "<div style='height:100px;'>&nbsp;</div></div>";

	} else {
		
		$
				.each(
						data.list,
						function(i, j) {
							var userPhoto="";
							if(j.userPhoto==null || j.userPhoto==""){
								userPhoto="images/bg-portrait.png";
							} else {
								userPhoto="fileoper/downFile.action?filepath="+j.userPhoto;
							}
							var content="";
							if(j.content==null || j.content ==""){
								content="此用户没有填写评论！";
							} else {
								content=j.content;
							}
							dataListHtml += "<div class='weui-cell user-pic line-d1e pad-0'>"
									+ "<div class='pad-10'><img src='"+userPhoto+"' width='40' height='40' class='img-circle' alt='' onerror='javascript:this.src=\"images/nopic.jpg\";'/></div>"
									+ "<div class='ft12'>"
									+ "<div class='weui-cell'>"
									+ "<div>"
									+ "<p class='ft-bold400 ft14'>"+j.userName+"</p>"
									+ "<div class='icon-score mar-t6'>"
									+ "<div>"
									+ "<i style='width:"+(j.storeScore*16)+"px;'></i>"
									+ "</div>"
									+ "</div>"
									+ "</div>"
									+ "</div>"
									+ "<div class='weui-cell' style='padding-top: 0'>"
									+ content
									+ "</div>";
							if(j.list.length>0){
								dataListHtml+="<div class='weui-cell' style='padding-top: 0'>";
								var attaList="";
								var attaPhoto;
								for(var k=0;k<j.list.length;k++){
									attaPhoto="fileoper/downFile.action?filepath="+j.list[k].filePath;
									attaList+=attaPhoto+",";
								}
								attaList=attaList.substring(0,attaList.length-1);
								for(var k=0;k<j.list.length;k++){
									attaPhoto="fileoper/downFile.action?filepath="+j.list[k].filePath;
									dataListHtml+= "<div class='weui-cell__hd'>"
												+ "<img src='"+attaPhoto+"' width='50' height='50' alt='' onerror='javascript:this.src=\"images/nopic.jpg\";' onclick='getCommentPhoto(\""+attaList+"\","+k+")'/> "
												+ "</div>";
								}
								dataListHtml+="</div>";
								
							}
							if(j.replay!=null && j.replay!=""){
								dataListHtml+="<div class='weui-cell ft-grey-999' style='padding-top: 0'>"
											+ "<span class='ft-yellow'>商家回复：</span>"+j.replay
											+ "</div>";
							}
									
							dataListHtml+="</div></div>";
						});
		if (data.pageNums == currentPage) {
			dataListHtml += "<div class='weui-loadmore'>"
					+ "<span class='weui-loadmore__tips'>没有更多数据了</span>"
					+ "</div>";
		}
		if(currentPage==1){
			dataListHtml+="</div>";
		}
		if (data.pageNums > currentPage && currentPage == 1) {
			dataListHtml += "<div class='weui-loadmore' id='commentBackLoadmore'>"
					+ "<i class='weui-loading'></i>"
					+ "<span class='weui-loadmore__tips'>正在加载</span>"
					+ "</div>";
		}
		
		if(currentPage==1){
			dataListHtml+="<div style='height:20px;'>&nbsp;</div>";
		}
	}

	return dataListHtml;
}

function changeCondition(obj, cnum) {
	$.showLoading();
	$("#conditionDiv").find("a").removeClass("on");
	$(obj).addClass("on");
	storeScore=cnum;
	getcomments(1);
}

/**
 * 查看评论大图
 * @param attaList
 * @param pindex
 */
function getCommentPhoto(attaList,pindex){
	var al=attaList.split(",");
	var alj=[];
	for(var i=0;i<al.length;i++){
		alj.push(al[i]);
	}
	var pb = $.photoBrowser({
		items : alj,
		initIndex : pindex
	});
	pb.open();
}

/**
 * 购物车折叠切换
 */
var state = false;
function changeCart(){
	if (!state) {
		$("#cart").show();
		state=true;
	}else{
		$("#cart").hide();
		state=false;
	}
}