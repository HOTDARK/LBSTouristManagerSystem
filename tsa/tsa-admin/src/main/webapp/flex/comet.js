/*{	
	uri:"uri",

	destination:"destination",
	handler:function(data){},
	subscribed:function(subId){},
	unsubscribed:function(){},
	fault:function(err){}
}
*/

var comet = {
	handler:function(data){},
	subscribed:function(clientId){},
	unsubscribed:function(){},
	fault:function(err){},
	init:function(swfId,cfg){
		this.el = document.getElementById(swfId);
		this.el.config({
			uri:"messagebroker/streamingamf",
			destination:"comet",
			handler:"comet.handler",
			subscribed:"comet.subscribed",
			unsubscribed:"comet.unsubscribed",
			fault:"comet.fault"
		});
		
		if(cfg.subscribed){
			this.subscribed = cfg.subscribed;		
		}
		
		if(cfg.unsubscribed){
			this.unsubscribed = cfg.unsubscribed;
		}
		
		if(cfg.handler){
			this.handler = cfg.handler;	
		}
		
		if(cfg.fault){
			this.fault = cfg.fault;
		}
		
//		layer.close(alertLayer);
	},
	start:function(){
		this.el.start();
	},
	stop:function(){
		this.el.stop();
	}
};
//界面加载提示框
var alertLayer;

$(function(){
	//让界面回到顶部，由于加载SWF的原因，将页面拉到顶部在IE中能提高加载效率
	$('body,html').animate({scrollTop:0},1000);
	
	//初始界面加载提醒
//	alertLayer=layer.load('界面加载中...请稍后...', 3);
	//验证是否安装flash
	if(!flashChecker().f){
		Utils.confirm({title:"温馨提示",msg:"您当前没有安装Flash Player , 是否前往下载？",modalSure:function(){
			window.location="installFile/install_flash_player_ax_1.1413358574.exe";
		}});
		return false;
	}
	
	$.ajaxSetup({
		beforeSend:function(){
//			this.layer_load_index = layer.load();
		},
		complete:function(){
//			layer.close(this.layer_load_index);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
//			layer.alert(XMLHttpRequest.responseText?XMLHttpRequest.responseText:"您的网络可能与服务器不通，请重试。", 0, !1);
		}
	});
	
	$(".swfcontent").append('<script type="text/javascript" src="flex/swfobject.js"></script>');
	$(".swfcontent").prepend('<div id="flashContent"></div>');
	
	var swfVersionStr = "11.1.0";
	var xiSwfUrlStr = "flex/playerProductInstall.swf";
	var flashvars = {};
	var params = {};
	params.quality = "low";
	params.bgcolor = "#ffffff";
	params.allowscriptaccess = "sameDomain";
	params.allowfullscreen = "true";
	var attributes = {};
	attributes.id = "Comet";
	attributes.name = "Comet";
	attributes.align = "middle";
	swfobject.embedSWF(
		"flex/Comet.swf", "flashContent", 
	    "1", "1", 
	    swfVersionStr, xiSwfUrlStr, 
	    flashvars, params, attributes);
	swfobject.createCSS("#flashContent", "display:block;text-align:left;");
	
});

//验证是否安装flash
function flashChecker(){
	var hasFlash=0;         	 //是否安装了flash
	var flashVersion=0; 		 //flash版本
	var isIE=/*@cc_on!@*/0;      //是否IE浏览器
	
	if(isIE){
		var swf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
		if(swf){
			hasFlash=1;
			VSwf=swf.GetVariable("$version");
			flashVersion=parseInt(VSwf.split(" ")[1].split(",")[0]); 
		}
	}else{
		if (navigator.plugins && navigator.plugins.length > 0) {
			var swf = navigator.plugins["Shockwave Flash"];
			if (swf){
				hasFlash = 1;
				var words = swf.description.split(" ");
				for ( var i = 0; i < words.length; ++i) {
					if (isNaN(parseInt(words[i])))
						continue;
					flashVersion = parseInt(words[i]);
				}
			}
		}
	}
	return {f:hasFlash,v:flashVersion};
}