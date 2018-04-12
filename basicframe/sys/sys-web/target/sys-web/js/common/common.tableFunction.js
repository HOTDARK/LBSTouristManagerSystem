var c_tableFunction = {};

c_tableFunction.tableFunction = function(){
	$(".table").on("mouseenter","tbody tr",function(){
		var t = $(this).offset().top;
		
		//$(".side").css("display","none");
		$(this).find(".side").css({"display":"block","top":(t-1)+"px"});
	//	$(this).find(".side").css({"display":"block","top":(t-1)+"px"});
//		$(".side").hover(function(){
//			var width = parseInt($(this).find("a").length)*34;
//			$(this).find(".sidebox").stop().animate({"width":width+"px"},300).css({"opacity":"1","filter":"Alpha(opacity=100)","background":"#197ec1"});	
//		},function(){
//			$(this).find(".sidebox").stop().animate({"width":"39px"},300).css({"opacity":"0.8","filter":"Alpha(opacity=80)","background":"#3b98d6"});	
//		});
		$(".side").hover(function(){
			
		$(this).find(".sidebox").show();
		},function(){
			$(this).find(".sidebox").hide();});
		
		
	});
	$(".table").on("mouseleave",function(){
		$(".sidebox").css("display","none");
	});
	
};