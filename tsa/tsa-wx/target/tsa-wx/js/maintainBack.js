var clList;
window.onload=function(){
	Utils.ajax({
		url : 'maintain/getDetail.action',
		data:{"id":id},
		success : function(data) {
			$.hideLoading();
			
			clList=data.materialList;
			var mcl=data.byoList;
			if(mcl!=null){
			for(var i=0;i<mcl.length;i++){
				clid++;
				var clhtml="<div class='weui-cell weui-cell_swiped'>"
						            +"<div class='weui-cell__bd'>"
						            +"<div class='weui-cell'>"
						            +"<div class='weui-cell__bd'>"
						            +"<p><input class='weui-input' type='text' placeholder='请选择材料' id='cl"+clid+"' value='"+mcl[i].materialName+"' data-values='"+mcl[i].materialCode+"' style='width:50%'></p>"
						            +"</div>"
						            +"<div class='weui-cell__ft'><span class='weui-input' style='font-size:6px;color:#ddd;'>左滑删除</span>&nbsp;"
						            +"<div class='weui-count'>"
						            +"<a class='weui-count__btn weui-count__decrease' onclick='decrease(this)'></a>"
						            +"<input class='weui-count__number' type='number' id='clnum"+clid+"' value='"+mcl[i].count+"' />"
						            +"<a class='weui-count__btn weui-count__increase' onclick='increase(this)'></a>"
						            +"</div>"
						            +"</div>"
						            +"</div>"
						            +"</div>"
						            +"<div class='weui-cell__ft'>"
						            +"<a class='weui-swiped-btn weui-swiped-btn_warn delete-swipeout' href='javascript:' onclick='deleteSwipe(this)'>删除</a>"
						            +"<a class='weui-swiped-btn weui-swiped-btn_default close-swipeout' href='javascript:' onclick='closeSwipe(this)'>关闭</a>"
						            +"</div>"
						            +"</div>";
				$("#cailiao").append(clhtml);
				$('.weui-cell_swiped').swipeout();
				var cl=[];
				$.each(clList,function(j,i){
					var row={'title':i.dictName,'value':i.dictCode};
					cl.push(row);
				});
				$("#cl"+clid).select({
					title: "请选择材料",
					items: cl
				});
			}
			}
		}
	});
}

var MAX = 99, MIN = 1;
function decrease(obj){
	var $input=$(obj).next();
	var number = parseInt($input.val() || "0") - 1;
	if (number < MIN) number = MIN;
	$input.val(number);
}
function increase(obj){
	var $input = $(obj).prev();
	  var number = parseInt($input.val() || "0") + 1;
	  if (number > MAX) number = MAX;
	  $input.val(number);
}
function deleteSwipe(obj){
	$(obj).parents('.weui-cell').remove();
}
function closeSwipe(obj){
	$(obj).parents('.weui-cell').swipeout('close');
}

var clid=0;
function addCl(){
	clid++;
	var clhtml="<div class='weui-cell weui-cell_swiped'>"
			            +"<div class='weui-cell__bd'>"
			            +"<div class='weui-cell'>"
			            +"<div class='weui-cell__bd'>"
			            +"<p><input class='weui-input' type='text' placeholder='请选择材料' id='cl"+clid+"' style='width:50%'></p>"
			            +"</div>"
			            +"<div class='weui-cell__ft'><span class='weui-input' style='font-size:6px;color:#ddd;'>左滑删除</span>&nbsp;"
			            +"<div class='weui-count'>"
			            +"<a class='weui-count__btn weui-count__decrease' onclick='decrease(this)'></a>"
			            +"<input class='weui-count__number' type='number' id='clnum"+clid+"' value='1' />"
			            +"<a class='weui-count__btn weui-count__increase' onclick='increase(this)'></a>"
			            +"</div>"
			            +"</div>"
			            +"</div>"
			            +"</div>"
			            +"<div class='weui-cell__ft'>"
			            +"<a class='weui-swiped-btn weui-swiped-btn_warn delete-swipeout' href='javascript:' onclick='deleteSwipe(this)'>删除</a>"
			            +"<a class='weui-swiped-btn weui-swiped-btn_default close-swipeout' href='javascript:' onclick='closeSwipe(this)'>关闭</a>"
			            +"</div>"
			            +"</div>";
	$("#cailiao").append(clhtml);
	$('.weui-cell_swiped').swipeout();
	var cl=[];
	$.each(clList,function(j,i){
		var row={'title':i.dictName,'value':i.dictCode};
		cl.push(row);
	});
	$("#cl"+clid).select({
		title: "请选择材料",
		items: cl
	});
}

function audit(obj,exmainStat){
	$(obj).attr("disabled",true);
	var title;
	if(exmainStat==0){
		title="确认审核通过？";
	} else if (exmainStat==1){
		title="确认驳回？";
	}
	$.confirm(title, function() {
		  var yijian=$("#yijian").val();
		  if(yijian==""){
			  $.toast("请输入处理意见", "forbidden");
				$(obj).attr("disabled",false);
				return;
		  }
		  var clin=$("#cailiao").find("input[class='weui-input']");
		  var clnm=$("#cailiao").find("input[class='weui-count__number']");
		  var mcode="",mcount="";
			for(var i=0;i<clin.length;i++){
				if($(clin[i]).attr("data-values") != undefined && $(clin[i]).attr("data-values") !=""){
					mcode+=$(clin[i]).attr("data-values")+",";
					mcount+=$(clnm[i]).val()+",";
				} else {
					$.toast("添加的材料项必须选择", "forbidden");
					$(obj).attr("disabled",false);
					return;
				}
			}
			if(mcode!="" && mcount != ""){
				mcode=mcode.substring(0,mcode.length-1);
				mcount=mcount.substring(0,mcount.length-1);
			}
			$.showLoading();
			Utils.ajax({
				url : 'maintain/audit.action',
				data:{
					"id":id,
					"state":state,
					"exmainStat":exmainStat,
					"opinion":yijian,
					"userAccount":userAccount,
					"mcode":mcode,
					"mcount":mcount},
				success : function(data) {
					$.hideLoading();
					if(!data.result){
						$.toast(data.str,"forbidden");
					} else {
						$.toast("操作成功");
						setTimeout(function(){window.location="maintain/goMaintain.action?backAccount="+userAccount+"&idNum=3&openid="+openid;},2000);
					}
				}
			});
		}, function() {
		  //点击取消后的回调函数
		});
}