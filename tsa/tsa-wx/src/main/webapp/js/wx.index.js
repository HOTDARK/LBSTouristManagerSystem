function buytime() {
	$.showLoading();
	Utils.ajax({
		url : 'buytime/goBuyTime.action',
		data : {
			"openid" : openid
		},
		success : function(data) {
			$.hideLoading();
			if (!data.suc) {
				$.toast(data.msg, "forbidden");
				if (data.flag == 3) {
					setTimeout(function() {
						jumpPage('wx/jumpPage.action?viewName=userBind.jsp');
					}, 2000);
				}
			} else {
				jumpPage('wx/jumpPage.action?viewName=buytime.jsp&idNum=2');
			}
		}
	});
}

window.onload = function() {
	$.showLoading();
	Utils
			.ajax({
				url : 'index/getBaseData.action',
				data : {},
				success : function(data) {
					var topAdsHtml = "";
					if (data.topAds.length > 0) {
						$
								.each(
										data.topAds,
										function(j, i) {
											topAdsHtml += "<div class='swiper-slide' onclick='javascript:window.location.href=\""
													+ i.adUrl
													+ "\"'><img src='fileoper/downFile.action?filepath="
													+ i.adPicture
													+ "' width='100%' height='180' onerror='javascript:this.src=\"images/nopic.jpg\";' /></div>";
										});

					} else {
						for (var i = 1; i < 4; i++) {
							topAdsHtml += "<div class='swiper-slide'><img src='./images/banner-img0"
									+ i + ".jpg' /></div>";
						}
					}
					$("#topAd").html(topAdsHtml);
					$("#sci").swiper({
						loop : true,
						autoplay : 3000
					});
					var bottomAd = "";
					if (data.bottomAd != null) {
						$("#bottomAd").attr("onclick",
								"javascript:" + data.bottomAd.adUrl);
						bottomAd = "<img src='fileoper/downFile.action?filepath="
								+ data.bottomAd.adPicture
								+ "' width='100%' height='100' alt='' onerror='javascript:this.src=\"images/nopic.jpg\";'/>";
					} else {
						bottomAd = "<img src='images/ad04.jpg' width='100%' alt=''/>";
					}
					$("#bottomAd").html(bottomAd);

					var modulList = data.modulList;
					var swiperNum = Math.ceil(modulList.length / 8);
					var modulHtml = "";
					for (var i = 0; i < swiperNum; i++) {
						modulHtml += "<div class='swiper-slide'>"
								+ "<div class='weui-grids bg-white reset-index'>";
						for (var j = 0; j < 8; j++) {
							var index = i * 8 + j;

							if (index < modulList.length) {
								modulHtml += "<a href='javascript:void(0)' class='weui-grid js_grid' onclick=\""
										+ modulList[index].modulUrl
										+ "\">"
										+ "<div class='weui-grid__icon' style='background:#"
										+ modulList[index].modulCss
										+ ";'>"
										+ "<img src='fileoper/downFile.action?filepath="
										+ modulList[index].modulPicture
										+ "' alt='' onerror='javascript:this.src=\"images/nopic.jpg\";'>"
										+ "</div>"
										+ "<p class='weui-grid__label ft14'>"
										+ modulList[index].modulName
										+ "</p></a>";
							} else {
								break;
							}
						}
						modulHtml += "</div></div>"
					}
					$("#modul").html(modulHtml);
					$("#scr").swiper({});
					$.hideLoading();
				}
			});
	Utils
			.ajax({
				url : 'index/getActivity.action',
				data : {},
				success : function(data) {
					var activityHtml = "";
					var imageHeight=0;
					var imageWidth=0;
					var offsetWidth=document.body.offsetWidth;
					var offsetHeight=document.body.offsetHeight;
					for (var i = 0; i < data.list.length; i++) {
						activityHtml += "<div class='weui-grids bg-white index-ad col-xs-"
								+ (data.list)[i].length + "'>";
						if((data.list)[i].length==2){
							imageWidth=offsetWidth*0.33;
							imageHeight=offsetHeight*0.117;
						} else if((data.list)[i].length==3){
							imageWidth=offsetWidth*0.22;
							imageHeight=offsetHeight*0.1;
						}
						for (var j = 0; j < data.list[i].length; j++) {
							
							activityHtml += "<a href='"+data.list[i][j].detailUrl+"' class='weui-grid js_grid'>"
									+ "<p class='weui-grid__label ft-bold600 ft16'>"+data.list[i][j].detailName+"</p>"
									+ "<div class='weui-grid__icon'>"
									+ "<img src='fileoper/downFile.action?filepath="+data.list[i][j].detailPicture+"' alt='' style='height:"+imageHeight+"px;width:"+imageWidth+"px;'>"
									+ "</div></a>";
						}
						activityHtml += "</div>";
					}
					$("#activity").empty();
					$("#activity").html(activityHtml);
				}
			});
}