/**
 * 用户管理脚本
 */
var viewModel = 1;//1表示当前为table视图,0表示为卡片视图
var orgPermissionStr = "";
$(function(){
	$('.selectpicker').selectpicker();
	$("#orgTree").slimScroll({height:$("#orgTree").parent().parent().height()});
	Utils.ajax({
		url:'sysPermit/getOrgRoleByUser.action',
		success:function(data){
			if(data.status){
				orgPermissionStr = data.data;
				initUserOrgTree();
			}
		},
		error:function(){
			Utils.showMsg("error","加载用户机构权限失败");
		}
	});
	//容器滚动设置
	/*$('#dataView').slimScroll({
	    position: 'right',
	    height: '600px',
	    railVisible: true,
	    alwaysVisible: true
	});*/
});

function initUserOrgTree(){
	/** 初始化组织机构树start **/
	var treeData={};
	treeData.url = "sysOrg/getOrgTree.action";
	treeData.text = "orgName";
	treeData.id = "orgId";
	treeData.pId = "parentOrgId";
	treeData.rootPid = "0";
	treeData.treeId = "orgTree";
	treeData.click = function(event ,treeId,treeNode){
		$("#orgId").val(treeNode.orgId);
		/**清除页面参数 start**/
		$("#searchUserName").val("");
		$("#searchTelephone").val("");
		/**清除页面参数 end**/
		//加载列表操作弹出层
		c_tableFunction.tableFunction();
		//点击树节点后,直接进行页面跳转加载数据
		if(viewModel==1){
			$("#listIcon").addClass("active");
			userTable();
		} else {
			$("#mapIcon").addClass("active");
			initCard();
			loadData();
		}
	};
	treeData.oParam = {state: 1};
	treeData.check = false;
	treeData.runClick = function(){
	
	};
	treeData.nodesStr = orgPermissionStr;
	treeData.expand = false;
	if(orgPermissionStr){
		commonTree.tree(treeData);
	}
	/** 初始化组织机构树end **/	
}

function userTable() {
	dataTable({
		id: "userTable",
		lengthChange: true,
		sort: false,
		info: true,
		paginate: true,
		autoWidth: true,
		processing: true,
		primaryKey: "userId",
		sAjaxDataProp : "data",
		check: true,
		singleSelect: false,
		url: "sysUser/getUserListByOrgId.action",
		data: Utils.serializeObj($("#searchForm")),
		columns:[
	 		{ title: getProp("sys.user.userName"), data: "userName" },
			{ title: getProp("sys.user.userAccount"), data: "userAccount"},
			{ title: getProp("sys.user.sex"), data: "sexName"},
			{ title: getProp("sys.user.email"), data: "email"},
			{ title: getProp("sys.user.telephone"), data: "telephone"},
			{ title: getProp("sys.user.state"), data: "stateName", formatter:stateinit},
			{ title: getProp("public.txt.operation"), data: "stateName", formatter:userListStateInit}
		]
	});
}

/**
 * 视图变更
 * @param flag 1-表示到table视图,0表示到卡片视图
 */
function switchView(flag){
	if(flag==0){//到卡片视图
		if(viewModel==0){
			return;//如果当前就是卡片视图,则不处理
		}
		viewModel = 0;//当前视图标记设为卡片视图
		$("#userTable").hide();
		$("#userCardList").show();
		initCard();
		loadData();
	} else {//到列表视图
		if(viewModel==1){
			return;//如果当前就是列表视图,则不处理
		}
		viewModel = 1;//当前视图标记设为列表视图
		$("#userCardList").hide();
		$("#userTable").show();
		userTable();
	}
}

/**
 * 列表搜索
 */
function userListSearch(){
	//进行查询
	if(viewModel == 1){
		userTable();
	} else {
		loadData();
	}
}

/**
 * 激活/冻结用户
 * @param state (1-激活,0-冻结)
 * @param userId 要操作的用户Id,如果批量,则传入空字符串
 * @param userName 用户名字
 */
function updateUserState(state,userId,userName){
	var msg = "";
	var userIds = "";
	var checkedUser = [];
	if(userId){//表示是单个用户操作
		msg = getProp("sys.user.confirm.msg1")+"["+userName+"]"+getProp("public.txt.question");
		if(state==0){
			msg = getProp("sys.user.confirm.msg3")+"["+userName+"]"+getProp("public.txt.question");
		}
		checkedUser.push(userId);
		userIds = userId;
	}
	else{//全局批量操作
		var userChecked = $("[name='checkbox']:checkbox:checked");
		if(userChecked.length > 0){
			userChecked.each(function(index,ele){
				if($(ele).attr("value") != undefined) {
					checkedUser.push($(ele).attr("value"));
				}
			});
		}
		if(checkedUser.length == 0) {
			Utils.showMsg("warning","请选择数据");
			return;
		}
		
		
		msg = getProp("sys.user.confirm.msg2")+getProp("public.txt.question");
		if(state==0){
			msg = getProp("sys.user.confirm.msg4")+getProp("public.txt.question");
		}
		userIds = checkedUser.join(",");
	}
	Utils.confirm({msg:msg,modalSure:function(){		
		Utils.ajax({
			url:"sysUser/activateAndFrozen.action",
			data:{
				userState:state,
				userIds:userIds
			},
			success:function(data){
				if(data){
					if(state==1){				
						Utils.showMsg("success",getProp("sys.user.confirm.res1"));
					}
					else{
						Utils.showMsg("success",getProp("sys.user.confirm.res2"));
					}
					if(viewModel==1){//如果是列表视图,则刷新列表
						$('#checkbox').prop('checked', false);
						dataTable.reload("userTable");
//						$('#userTable').dataTable().fnDraw();
					}
					else{//卡片视图,刷新卡片
						for(var i = 0; i < checkedUser.length; i++){
							if(state==1){
								$("#card_"+checkedUser[i]).removeClass("card-yellow");
								$("#card_"+checkedUser[i]).addClass("card-green");
								$("#userCardState"+checkedUser[i]).html("有效");
								$("#userCardState"+checkedUser[i]).removeClass("label-warning");
								$("#userCardState"+checkedUser[i]).addClass("label-success");
								$("#btnUpdateUserState"+checkedUser[i]).html('<i class="fa fa-ban"></i>'+getProp("public.txt.frozen"));
								$("#btnUpdateUserState"+checkedUser[i]).attr("title",getProp("public.txt.frozen"));
								$("#btnUpdateUserState"+checkedUser[i]).attr("href","javascript:updateUserState('0','"+checkedUser[i]+"','"+$("#btnUpdateUserState"+checkedUser[i]).attr("user_name")+"');");
								$("#btnUpdateUserState"+checkedUser[i]).find("i:eq(0)").removeClass("glyphicon-asterisk").removeClass("color02");
								$("#btnUpdateUserState"+checkedUser[i]).find("i:eq(0)").addClass("glyphicon-ban-circle").addClass("color03");
							}else{
								$("#card_"+checkedUser[i]).removeClass("card-green");
								$("#card_"+checkedUser[i]).addClass("card-yellow");
								$("#userCardState"+checkedUser[i]).html("无效");
								$("#userCardState"+checkedUser[i]).removeClass("label-success");
								$("#userCardState"+checkedUser[i]).addClass("label-warning");
								$("#btnUpdateUserState"+checkedUser[i]).html('<i class="fa fa-gavel"></i>'+getProp("public.txt.activate"));
								$("#btnUpdateUserState"+checkedUser[i]).attr("title",getProp("public.txt.activate"));
								$("#btnUpdateUserState"+checkedUser[i]).attr("href","javascript:updateUserState('1','"+checkedUser[i]+"','"+$("#btnUpdateUserState"+checkedUser[i]).attr("user_name")+"');");
								$("#btnUpdateUserState"+checkedUser[i]).find("i:eq(0)").removeClass("glyphicon-ban-circle").removeClass("color03");
								$("#btnUpdateUserState"+checkedUser[i]).find("i:eq(0)").addClass("glyphicon-asterisk").addClass("color02");
							}
						}
					}
				}
				else{
					if(state==1){				
						Utils.showMsg("error","激活用户失败");
					}
					else{
						Utils.showMsg("error","冻结用户失败");
					}
				}
			},
			error:function(){
				Utils.showMsg("error","请求异常");
			}
		});
	}});
}

/**
 * 弹出重置密码弹出框
 * @param userId 用户id
 */
function initPwd(userId){
	$("#selectedUserId").val(userId);
	Utils.showModel({
		id:"showPwdEdit",
		title: getProp("sys.user.pwd.reset"), 
		btns:[["save_showPwdEdit",getProp("public.btn.save")]], 
		url: "sysUser/toPwdInit.action",
		onload: function(){
			$("#pwdForm").validation();
			$("#save_showPwdEdit").click(resetPwd);
		}
	});
}

/**
 * 机构变更
 * @param userId 用户id
 */
function userOrgEdit(userId){
	$('#selectedUserId').val(userId);
	Utils.showModel({
		 id:"userOrgEdit",
		 title: getProp("sys.user.org.change"), 
		 btns:[["save_userOrgEdit",getProp("public.btn.save")]], 
		 url: "sysUser/toOrgEdit.action",
		 data: {"orgTitle":getProp("sys.user.role.org"),"roleTitle":getProp("sys.user.role.name")},
		 onload: function() {
			 changeUrl("userCurrentRoleList","sysUser/getRolesByUserId.action?userId="+userId);
			 /** 初始化组织机构树start **/
			var treeData={};
			treeData.url = "sysOrg/getOrgTree.action";
			treeData.text = "orgName";
			treeData.id = "orgId";
			treeData.pId = "parentOrgId";
			treeData.rootPid = "0";
			treeData.treeId = "oTree";
			treeData.runClick = false;
			treeData.click = function(event ,treeId,treeNode){
				if(treeNode&&treeNode.orgId!=0){
					$("#newOrg").html(treeNode.orgName);
					$("#newOrgId").val(treeNode.orgId);
				}
				else{//如果节点为空或者为根节点,则清除相关数据
					$("#newOrg").html("");
					$("#newOrgId").val("");
				}
			};
			treeData.oParam = [];
			treeData.check = false;
			treeData.runClick = function(){
			
			};
			treeData.nodesStr = orgPermissionStr;
			treeData.expand = false;
			if (orgPermissionStr) {
				commonTree.tree(treeData);
			}
			/** 初始化组织机构树end **/	
			initUserInfo();//初始化用户信息
			$("#oTree").slimScroll({height:350});
			$("#save_userOrgEdit").click(saveUserOrg);
		 }
	});
}

/**
 * 添加用户
 */
function addUser() {
	if(!$("#orgId").val()||$("#orgId").val()==0){
		Utils.showMsg("warning","请选择有效的组织机构");
		return;
	}
	Utils.showModel({
		id:"editUser",
		title: getProp("sys.user.addUser"), 
		btns:[["save_editUser",getProp("public.btn.save")]], 
		data:{"orgId":$("#orgId").val()},
		url: "sysUser/toUserEdit.action",
		onload: function(){
			initUser();
		}
	});
}

/**
 * 修改用户
 * @param userId 用户id
 */
function editUser(userId) {
	Utils.showModel({
		id:"editUser",
		title: getProp("sys.user.editUser"), 
		btns:[["save_editUser",getProp("public.btn.save")]], 
		url: "sysUser/toUserEdit.action?userId="+userId,
		onload: function(){
			initUser();
		}
	});
}

/**
 * 字符串截取,英文按照一个长度,中文按章两个长度
 * @param str
 * @param length
 * @returns {String}
 */
function subStr(str,length){
	var len = 0;
	var resultStr = "";
    for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1 
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
        	len++;
        }
        else {
        	len += 2;
        }
        if(len<=length){
        	resultStr += str.substring(i,i+1);
        }
        else{
        	resultStr += "...";
        	break;
        }
    }
    return resultStr;
}

function initUser(){
	$('.selectpicker').selectpicker();
	var userId = $("#userId").val();
	var userAccount = "";
	var email = "";
	var telephone = "";
	var stateName = "";
	var state = -1;
	var userName = "";
	var sexName = "";
		
	$("#editUserForm").validation(function(obj,params){
		if (obj.id=='userAccount'){
			$.post("sysUser/getCountByEntity.action",{userAccount :$(obj).val(),userId:userId},function(data){
				if(data) {
					params.err = true;
					params.msg = "帐号重复";
				} else {
					params.err = false;
					params.msg = "";
				}
			});
		}},
	{reqmark:true});
	$("#save_editUser").click(saveUser);
}

function saveUser(){
	userAccount = $("#userAccount").val();
	email = $("#email").val();
	telephone = $("#telephone").val();
	state = $("#state").val();
	stateName = ($("#state").val()=="0"?getProp("public.select.einvalid"):getProp("public.select.effective"));
	sexName = ($("#sex").val()=="0"?getProp("public.select.female"):getProp("public.select.male"));
	userName = $("#userName").val();
	if ($("#editUserForm").valid(this)==false){
       	return false;
     }
	var ajaxFormOpt = {
			url : 'sysUser/editUser.action',
			type : 'post',
			dataType : 'json',
			async : false,
			success : function(returnData) {
				if(returnData){
					if($("#userId").val()){
						if(viewModel==1){
							$('#checkbox').prop('checked', false);
							dataTable.reload('userTable');
						}
						else{
							$('#editUserForm').trigger('close');
							$("#userCardAccount"+userId).html(subStr(userAccount,20));
							$("#userCardAccount"+userId).attr("title",userAccount);
							$("#userCardEmail"+userId).html(subStr(email,20));
							$("#userCardEmail"+userId).attr("title",email);
							$("#userCardTelephone"+userId).html(telephone);
							$("#userCardTelephone"+userId).attr("title",telephone);
							$("#userCardState"+userId).html(stateName);
							$("#userCardName"+userId).html(subStr(userName,22));
							$("#userCardName"+userId).attr("title",userName);
							$("#userCardSex"+userId).html(sexName);
							if (returnData.userPhoto) {
								$("#userCardImg"+userId).attr("src", "uploads/"+returnData.userPhoto);
							}
							//处理卡片上按钮
							if(state!=$("#oldUserState")){
								$("#btnUpdateUserState"+userId).attr("user_name",userName);
								if(state==1){
									$("#userCardState"+userId).removeAttr("color");
									$("#btnUpdateUserState"+userId).attr("title","冻结");
									$("#btnUpdateUserState"+userId).attr("href","javascript:updateUserState('0','"+userId+"','"+$("#btnUpdateUserState"+userId).attr("user_name")+"');");
									$("#btnUpdateUserState"+userId).find("i:eq(0)").removeClass("glyphicon-asterisk").removeClass("color02");
									$("#btnUpdateUserState"+userId).find("i:eq(0)").addClass("glyphicon-ban-circle").addClass("color03");
								}
								else{
									$("#userCardState"+userId).attr("color","red");
									$("#btnUpdateUserState"+userId).attr("title","激活");
									$("#btnUpdateUserState"+userId).attr("href","javascript:updateUserState('1','"+userId+"','"+$("#btnUpdateUserState"+userId).attr("user_name")+"');");
									$("#btnUpdateUserState"+userId).find("i:eq(0)").removeClass("glyphicon-ban-circle").removeClass("color03");
									$("#btnUpdateUserState"+userId).find("i:eq(0)").addClass("glyphicon-asterisk").addClass("color02");
								}
							}
						}
					}
					else{
						if(viewModel==1){
							$('#checkbox').prop('checked', false);
							dataTable.reload('userTable');
						} else {
							var searchData = {};
							searchData.orgId = $("#orgId").val();
							if($.trim($("#searchUserName").val())){
								searchData.userName = $.trim($("#searchUserName").val());
							}
							if($.trim($("#searchTelephone").val())){
								searchData.telephone = $.trim($("#searchTelephone").val());
							}
							if($("#searchState").val()!="-1"){
								searchData.state = $("#searchState").val();
							}
							initCard();
							loadData();
						}
					}
					$('#editUserForm').trigger('close');
					Utils.showMsg("success","保存成功");
				} else {
					Utils.showMsg("error","保存失败");
				}
			},
			error : function() {
				Utils.showMsg("error","保存失败");
			}
	};
	// 将表单设为ajax表单
	$('#editUserForm').ajaxForm(ajaxFormOpt);
	$('#editUserForm').submit();// 提交表单
}

/**
 * 角色变更
 * @param userId 用户id
 */
function editUserRoles(userId) {
	Utils.showModel({
		id:"editUser",
		title: getProp("sys.user.role.change"), 
		btns:[["save_userRole",getProp("public.btn.save")]], 
		data:{"userId":userId},
		url: "sysUser/toUserRoleEdit.action",
		onload: function(){
			$("#orgDemo").slimScroll({height:350});
			var roleIds = new Array();
			$.ajax({
				type: "POST",
				url: "sysUser/getRolesByUserId.action",
				data: {'userId':userId},
				dataType: "json",
				async:false,
				success: function(data){
					$("#haveRoles").html();
					var resultHtml="";
					$.each(data,function(i,result){
						roleIds[i] = result.roleId;
						resultHtml += "<tr><td>"+result.orgName+"</td><td>"+result.roleName+" </td>  </tr>"
					});
					$("#haveRoles").html(resultHtml);
				}
	         });
			/** 初始化组织机构树start **/
			var treeData={};
	  		treeData.url = "sysOrg/getOrgTree.action";
	  		treeData.text = "orgName";
	  		treeData.id = "orgId";
	  		treeData.pId = "parentOrgId";
	  		treeData.rootPid = "0";
	  		treeData.treeId = "orgDemo";
	  		treeData.click = function(event ,treeId,treeNode){
		  		$("#orgRoles").html("");
		  		$("#orgIdHtml").html(treeNode.orgId);
	  			$.ajax({
					type: "POST",
					url: "sysUser/getRolesByOrgId.action",
					data: {'orgId':treeNode.orgId},
					dataType: "json",
					success: function(data){
						var orgRolesHtml="";
						$.each(data,function(i,result){
							if ($.inArray(result.roleId, roleIds) != -1) {
								orgRolesHtml += '<tr><td class="center"><label><input type="checkbox" checked="checked" value="'+result.roleId+'" class="ace" name="roleId"/><span class="lbl"></span></label></td><td>'+result.orgName+'</td><td>'+result.roleName+'</td></tr>';
							} else {
								orgRolesHtml += '<tr><td class="center"><label><input type="checkbox" value="'+result.roleId+'" class="ace" name="roleId"/><span class="lbl"></span></label></td><td>'+result.orgName+'</td><td>'+result.roleName+'</td></tr>';
							}
							});
						$("#orgRoles").html(orgRolesHtml);
					}
	         });
	  		};
	  		treeData.oParam = [];
	  		treeData.check = false;
	  		treeData.runClick = function(){
	  		
	  		};
	  		treeData.nodesStr = orgPermissionStr;
	  		treeData.expand = false;
	  		if (orgPermissionStr) {
	  			commonTree.tree(treeData);
			}
	  		/** 初始化组织机构树end **/
	  		$("#save_userRole").click(saveUserRole);
		}
	});
}

/**
 * 初始化用户复选框
 * @param data
 * @param type
 * @param obj
 * @returns {String}
 */

function userListCheckInit(data, type, obj){
	
	 return "<label><input type='checkbox'  name='userCheck' userId='"+obj.userId+"'/><span class='lbl'></span></label>";
}

/**
 * 初始化用户性别
 * @param data
 * @param type
 * @param obj
 * @returns {String}
 */
function stateinit(data, type, obj){
	if(obj.state==0){
		return "<span class='label label-warning'>"+obj.stateName+"</span>";
	} if(obj.state==1) {
		return "<span class='label label-success'>"+obj.stateName+"</span>";
	} else {
		return "<span class='label label-danger'>未知</span>";
	}
}
function userListStateInit(data, type, obj){
	var operateHtml = '<div class="side" id="side">';
	operateHtml +='<button   class="side-control btn btn-default btn-xs"><i class="fonticon icon-bianji"></i>'+getProp("public.txt.operation")+'</button>';
	operateHtml += '<div class="sidebox">';
	operateHtml += '<a href="javascript:editUser(\''+obj.userId+'\');" title="'+getProp("public.txt.edit")+'" ><i class="fa fa-edit"></i>'+getProp("public.txt.edit")+'</a>';
	if(obj.state==0){
		operateHtml += '<a href="javascript:updateUserState(\'1\',\''+obj.userId+'\',\''+obj.userName+'\');" title="'+getProp("public.txt.activate")+'" ><i class="fa fa-gavel"></i>'+getProp("public.txt.activate")+'</a>';
	}
	else{
		operateHtml += '<a href="javascript:updateUserState(\'0\',\''+obj.userId+'\',\''+obj.userName+'\');" title="'+getProp("public.txt.frozen")+'" ><i class="fa fa-ban"></i>'+getProp("public.txt.frozen")+'</a>';
	}
	operateHtml += '<a href="javascript:initPwd(\''+obj.userId+'\');" title="'+getProp("sys.user.pwd.reset")+'" ><i class="fa fa-key"></i>'+getProp("sys.user.pwd.reset")+'</a>';
	operateHtml += '<a href="javascript:editUserRoles(\''+obj.userId+'\');" title="'+getProp("sys.user.role.change")+'" ><i class="fa fa-user"></i>'+getProp("sys.user.role.change")+'</a>';
	operateHtml += '<a href="javascript:userOrgEdit(\''+obj.userId+'\');" title="'+getProp("sys.user.org.change")+'" ><i class="fa fa-sitemap"></i>'+getProp("sys.user.org.change")+'</a>';
	operateHtml += '</div>';
	operateHtml += '</div>';
	
//	$('input').iCheck({
//        checkboxClass: 'icheckbox_minimal-blue',
//        radioClass: 'iradio_minimal-blue',
//        increaseArea: '20%' // optional
			
//});
	return operateHtml;
}

/**
 * 用户列表全选/全不选
 * @param e
 */
function userListCheckedAll(e){
	$("[name='userCheck']:checkbox").prop("checked",e.checked);
}

/**
 * 初始化用户操作按钮--已用其他方式实现
 * @param data
 * @param type
 * @param obj
 * @returns {String}
 */
function operateInit(data, type, obj){
	var operateHtml = "<a href='javascript:editUser(\""+obj.userId+"\");'>修改用户</a>";
		if(obj.state==0){
			operateHtml += "<a href='javascript:updateUserState(\"1\",\""+obj.userId+"\",\""+obj.userName+"\");'>激活</a>";
		}
		else{
			operateHtml += "<a href='javascript:updateUserState(\"0\",\""+obj.userId+"\",\""+obj.userName+"\");'>冻结</a>";
		}
		operateHtml += "<a href='javascript:initPwd(\""+obj.userId+"\");'>密码重置</a><a href='javascript:editUserRoles(\""+obj.userId+"\");'>用户角色变更</a><a href='javascript:userOrgEdit(\""+obj.userId+"\");'>用户机构变更</a>";
	
	 return operateHtml;
}

/****************卡片视图**********************/
function initCard(){
	$(".dark").hide();
	$(".card").hover(function(){
		 $(".dark",this).slideToggle(1);
		 if(!$(this).find("input[type='checkbox']").is(":checked")){
			 $(this).find("input[type='checkbox']").removeClass("cd_hide");
		 }
	});	
	$(".card").mouseleave(function(){
		  if(!$(this).find("input[type='checkbox']").is(":checked")){
			 $(this).find("input[type='checkbox']").addClass("cd_hide");
		  }
	});
}
var currentPage = 1;//当前页码
var pageSize = 2000;//每页显示数量
var isLoading = false;//是否正在加载
var hasData = true;//是否有数据
/**
 * 字符串截取,英文按照一个长度,中文按章两个长度
 * @param str
 * @param length
 * @returns {String}
 */
function subStr(str,length){
	var len = 0;
	var resultStr = "";
    for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1 
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
        	len++;
        }
        else {
        	len += 2;
        }
        if(len<=length){
        	resultStr += str.substring(i,i+1);
        }
        else{
        	resultStr += "...";
        	break;
        }
    }
    return resultStr;
}

/**
 * 加载数据
 * @return
 */
function loadData(){
	isLoading = true;
	var formData = $("#searchForm").serialize();
	formData.iDisplayStart = (currentPage-1)*pageSize;
	formData.iDisplayLength = pageSize;
	Utils.ajax({
		url:'sysUser/getUserListByOrgId.action',
		data:formData,
		success:function(data){
			$("#userCardList").html('');
			var userList =data.data;
			if(userList.length<=0){
				hasData = false;//无数据
				Utils.showMsg("info","没有更多数据");
				return;
			}
			var html = '';
			var userName;
			var email;
			var userAccount;
			for (var i = 0; i < userList.length; i++) {
				if (!userList[i].userPhoto) {
					userList[i].userPhoto = "images/profile-pic.jpg";
				} else {
					userList[i].userPhoto = "uploads/"+userList[i].userPhoto;
				}
				if(userList[i].state==1){
					html += '<div id="card_'+userList[i].userId+'" class="card per-card card-green">';
				}
				else{
					html += '<div id="card_'+userList[i].userId+'" class="card per-card card-yellow">';
				}
				
				userName = subStr(userList[i].userName,22);
				userAccount = subStr(userList[i].userAccount,20);
				email = subStr(userList[i].email,20);
				html += '<div class="card-form">'
				+'<span class="title">'+getProp("sys.user.userName")+getProp("public.txt.colon")+'<i id="userCardName'+userList[i].userId+'" title="'+userList[i].userName+'">'+userName+'</i></span>'
				+'<span  class="pull-right"><label id="checkbox_label" class="cd_hide"><input type="checkbox" id="checkbox" name="userCheck"  userId="'+userList[i].userId+'"><span class="lbl"></span></label></span></div>'	            
				+'<div class="space2"></div><div class="card-content"><div class="card-left"><img class="perimg"  onerror="this.src=\'images/profile-pic.jpg\'" id="userCardImg'+userList[i].userId+'" src="'+userList[i].userPhoto+'"></div>'
				+'<div class="card-right">'
				+'<span>'+getProp("sys.user.userAccount")+getProp("public.txt.colon")+'</span><font id="userCardAccount'+userList[i].userId+'" title="'+userList[i].userAccount+'">'+userAccount+'</font><div class="space2"></div>'
				+'<span>'+getProp("sys.user.sex")+getProp("public.txt.colon")+'</span><font id="userCardSex'+userList[i].userId+'" title="'+userList[i].sexName+'">'+userList[i].sexName+'</font><div class="space2"></div>'
				+'<span>'+getProp("sys.user.email")+getProp("public.txt.colon")+'</span><font id="userCardEmail'+userList[i].userId+'" title="'+userList[i].email+'">'+email+'</font><div class="space2"></div>'
				+'<span>'+getProp("sys.user.telephone")+getProp("public.txt.colon")+'</span><font id="userCardTelephone'+userList[i].userId+'" title="'+userList[i].telephone+'">'+userList[i].telephone+'</font><div class="space2"></div>';
				if(userList[i].state==1){						
					html+='<span>'+getProp("sys.user.state")+getProp("public.txt.colon")+'</span><span class="label  label-success" id="userCardState'+userList[i].userId+'">'+userList[i].stateName+'</span>';
				}
				else{
					html+='<span>'+getProp("sys.user.state")+getProp("public.txt.colon")+'</span><span class="label  label-warning" id="userCardState'+userList[i].userId+'" color="red">'+userList[i].stateName+'</span>';
				}
				html+='</div></div>'
				+'<div class="clear">'
				+'<div class="dark">'
                +'<p><a href="javascript:editUser(\''+userList[i].userId+'\');" title="'+getProp("public.txt.edit")+'" ><i class="fa fa-edit"></i>'+getProp("public.txt.edit")+'</a>';
                if(userList[i].state==0){	                	
                	html+='<a id="btnUpdateUserState'+userList[i].userId+'" href="javascript:updateUserState(\'1\',\''+userList[i].userId+'\',\''+userList[i].userName+'\');" user_name="'+userList[i].userName+'" title="'+getProp("public.txt.activate")+'"><i class="fa fa-gavel"></i>'+getProp("public.txt.activate")+'</a>';
                }
                else{
                	html+='<a id="btnUpdateUserState'+userList[i].userId+'" href="javascript:updateUserState(\'0\',\''+userList[i].userId+'\',\''+userList[i].userName+'\');" user_name="'+userList[i].userName+'" title="'+getProp("public.txt.frozen")+'"><i class="fa fa-ban"></i>'+getProp("public.txt.frozen")+'</a>';
                }
                html+='<a href="javascript:initPwd(\''+userList[i].userId+'\');" title="'+getProp("sys.user.pwd.reset")+'" ><i class="fa fa-key"></i>'+getProp("sys.user.pwd.reset")+'</a>'
                +'<a href="javascript:editUserRoles(\''+userList[i].userId+'\');" title="'+getProp("sys.user.role.change")+'" ><i class="fa fa-user"></i>'+getProp("sys.user.role.change")+'</a>'
                +'<a href="javascript:userOrgEdit(\''+userList[i].userId+'\');" title="'+getProp("sys.user.org.change")+'" ><i class="fa fa-sitemap"></i>'+getProp("sys.user.org.change")+'</a></p>'
                +'</div></div></div>';
			}
			$("#userCardList").append(html);
			
			/** 重新绑定鼠标悬浮事件start **/
			$(".dark").hide();
			$(".card").hover(function(){
				 $(".dark").hide();
				 $(".dark",this).show();
				 if(!$(this).find("input[type='checkbox']").is(":checked")){
					 $(this).find("#checkbox_label").removeClass("cd_hide");
				 }
			});	
			$(".card").mouseleave(function(){
				  $(".dark").hide();
				  if(!$(this).find("input[type='checkbox']").is(":checked")){
					 $(this).find("#checkbox_label").addClass("cd_hide");
				  }
			});
			$('input').iCheck({
	            checkboxClass: 'icheckbox_square-green',
	            radioClass: 'iradio_square-green',
	            increaseArea: '20%' // optional
				
	  });
			/** 重新绑定鼠标悬浮事件end **/
		},
		error:function(){
			Utils.showMsg("error","请求异常");
		}
	});
	isLoading = false;
}

/****************密码重置**********************/
function resetPwd(){
	if (!$("#pwdForm").valid(this)){
       	return false;
    }
	Utils.ajax({
		type: "POST",
		url: "sysUser/editUserPwd.action",
		data: {
			userId:$("#selectedUserId").val(),
			userPwd:$("#userPwdInput").val()
		},
		async: false,
		success: function(data){
			if(data){
				Utils.showMsg("success","密码重置成功");
				Utils.closeModel("showPwdEdit");
			}
			else{
				Utils.showMsg("error","密码修改失败");
			}
		},
		error: function(){
			Utils.showMsg("error","请求异常");
		}
     });
}

/****************机构变更**********************/
function saveUserOrg(){
	var roleIds = [];
	$("[name='currentRole']:checkbox").each(function(index,ele){
		if(!ele.checked){
			roleIds.push($(ele).attr("roleId"));//将所有未选中的角色添加(用于移除角色)
		}
	});
	var roleIdStr = roleIds.join(",");
	var data = {};
	data.roleIds = roleIdStr;
	data.userId = $("#selectedUserId").val();
	if($("#newOrgId").val()!=$("#oldOrgId").val()){			
		data.orgId = $("#newOrgId").val();//如果组织机构没有修改,则不提交该参数
	}
	Utils.ajax({
		url:'sysUser/updateUserOrg.action',
		data:data,
		success:function(data){
			if(data){
				Utils.showMsg("success","用户机构变更成功");
				Utils.closeModel("userOrgEdit");
				if(viewModel==1){
					dataTable.reload('userTable');
				} else {
					var searchData = {};
					searchData.orgId = $("#orgId").val();
					if($.trim($("#searchUserName").val())){
						searchData.userName = $.trim($("#searchUserName").val());
					}
					if($.trim($("#searchTelephone").val())){
						searchData.telephone = $.trim($("#searchTelephone").val());
					}
					if($("#searchState").val()!="-1"){
						searchData.state = $("#searchState").val();
					}
					initCard();
					loadData();
				}
			}
			else{
				Utils.showMsg("error","用户机构变更失败");
			}
		},
		error:function(){
			Utils.showMsg("error","请求异常");
		}
	});
}

/**
 * 初始化用户相关信息
 */
function initUserInfo(){
	Utils.ajax({
		url: "sysUser/getSysUserInfo.action",
		data: {
			userId:$("#selectedUserId").val()
		},
		async: false,
		success: function(data){
			if(data){
				$("#showUserName").html(data.userName);
				$("#showUserAccount").html(data.userAccount);
				$("#oldOrg").html(data.orgNames);
				$("#oldOrgId").val(data.orgId);
			}
			else{
				Utils.showMsg("error","加载用户数据出错");
			}
		},
		error: function(){
			Utils.showMsg("error","请求异常");
		}
     });
}

/**
 * 初始化当前角色复选框
 * @param data
 * @param type
 * @param obj
 * @returns {String}
 */
function currentRoleCheckInit(data, type, obj){
	 return "<label><input type='checkbox' class='ace' name='currentRole' roleId='"+obj.roleId+"' checked='checked'/><span class='lbl'></span></label>";
}

/**
 * 当前角色全选/全不选
 * @param e
 */
function currentRoleCheckedAll(e){		
	$("[name='currentRole']:checkbox").prop("checked",e.checked);
}

/****************角色变更**********************/
function saveUserRole(){
	var roleIdStr = "";
	var check_array=document.getElementsByName("roleId");
	if (check_array.length > 0) {
        for(var i=0;i<check_array.length;i++) {
            if(check_array[i].checked==true) {
               roleIdStr += check_array[i].value+",";
            }
        }
	} else {
		roleIdStr = null;
	}
    $.ajax({
		type: "POST",
		url: "sysUser/updateUserRoles.action",
		data: {'roleIdStr':roleIdStr,'orgId':$("#orgIdHtml").html(),'userId':$("#roleUid").val()},
		dataType: "json",
		success: function(data){
			if(data){
				$('.modal-content').trigger('close');
				Utils.showMsg("success","保存成功");
			} else {
				Utils.showMsg("error","保存失败");
			}
		}
     });
}