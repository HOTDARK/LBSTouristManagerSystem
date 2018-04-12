<!--tabbar-->
<div class="weui-tabbar">
	<a href="javascript:void(0)" onclick="jumpPage('wx/jumpPage.action?viewName=index.jsp')"
		class="weui-tabbar__item">
		<div class="weui-tabbar__icon">
			<i class="icon-home"></i>
		</div>
		<p class="weui-tabbar__label">首页</p>
	</a> <a href="javascript:void(0)" class="weui-tabbar__item">
		<div class="weui-tabbar__icon">
			<i class="icon-links"></i>
		</div>
		<p class="weui-tabbar__label">微链接</p>
	</a> <a href="javascript:void(0)" onclick="jumpPage('userCenter/userCenterHome.action')" class="weui-tabbar__item">
		<div class="weui-tabbar__icon">
			<i class="icon-user"></i>
		</div>
		<p class="weui-tabbar__label">我的</p>
	</a> <input type="hidden" id="openId" value="${openid}">
</div>
<!--/tabbar-->
<script src="${ctx }/lib/jquery-2.1.4.js"></script>
<script src="${ctx }/lib/fastclick.js"></script>
<script>
	$(function() {
		FastClick.attach(document.body);
	});
</script>
<script src="${ctx }/js/jquery-weui.js"></script>
<script src="${ctx }/js/swiper.js"></script>
<script>
	/* $("#sci").swiper({
		loop : true,
		autoplay : 3000
	});
	$("#scr").swiper({
	}); */
</script>
<script>
	function htmlFontSize() {
		var h = Math.max(document.documentElement.clientHeight,
				window.innerHeight || 0);
		var w = Math.max(document.documentElement.clientWidth,
				window.innerWidth || 0);
		var width = w > h ? h : w;
		width = width > 720 ? 720 : width
		var fz = ~~(width * 100000 / 36) / 10000
		document.getElementsByTagName("html")[0].style.cssText = 'font-size: '
				+ fz + "px";
		var realfz = ~~(+window.getComputedStyle(document
				.getElementsByTagName("html")[0]).fontSize.replace('px', '') * 10000) / 10000
		if (fz !== realfz) {
			document.getElementsByTagName("html")[0].style.cssText = 'font-size: '
					+ fz * (fz / realfz) + "px";
		}
	}
</script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/Utils.js"></script>
<script src="${ctx }/js/wxCommon.js"></script>