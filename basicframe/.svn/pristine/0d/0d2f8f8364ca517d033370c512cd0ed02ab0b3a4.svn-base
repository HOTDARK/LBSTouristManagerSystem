/**
 * 机构管理脚本
 */
//视图切换；1：table视图，0：卡片视图
var viewFlag = 1;
var orgPermissionStr = "";
var editFlag = false;
var activeFlag = false;
var frozenFlag = false;
var userChange = false;
var permitChange = false;
$(function() {
	$("#orgTree").slimScroll({height:$("#orgTree").parent().parent().height()});
	$("#add_org_btn").hide();
	Utils.ajax({
		url:'sysOrg/getBtnPermission.action',
		async:false,
		data:{btnVal:$('#add_org_btn').val()},
		success:function(data){
			if(data.flag){
				$('#add_org_btn').show();
			}
		}
	});
	$("#edit_org_btn").hide();
	Utils.ajax({
		url:'sysOrg/getBtnPermission.action',
		async:false,
		data:{btnVal:$('#edit_org_btn').val()},
		success:function(data){
			if(data.flag){
				$('#edit_org_btn').show();
				editFlag = true;
			}
		}
	});
	$("#btnEnable").hide();
	Utils.ajax({
		url:'sysOrg/getBtnPermission.action',
		async:false,
		data:{btnVal:$('#btnEnable').val()},
		success:function(data){
			if(data.flag){
				$('#btnEnable').show();
				activeFlag = true;
			}
		}
	});
	$("#btnDisable").hide();
	Utils.ajax({
		url:'sysOrg/getBtnPermission.action',
		async:false,
		data:{btnVal:$('#btnDisable').val()},
		success:function(data){
			if(data.flag){
				$('#btnDisable').show();
				frozenFlag = true; 
			}
		}
	});
	$("#add_role_btn").hide();
	Utils.ajax({
		url:'sysOrg/getBtnPermission.action',
		async:false,
		data:{btnVal:$('#add_role_btn').val()},
		success:function(data){
			if(data.flag){
				$('#add_role_btn').show();
			}
		}
	});
	Utils.ajax({
		url:'sysOrg/getBtnPermission.action',
		async:false,
		data:{btnVal:"001001006"},
		success:function(data){
			if(data.flag){
				userChange = true;
			}
		}
	});
	Utils.ajax({
		url:'sysOrg/getBtnPermission.action',
		async:false,
		data:{btnVal:"001001007"},
		success:function(data){
			if(data.flag){
				permitChange = true;
			}
		}
	});
	// 获取用户数据权限
	Utils.ajax({
		url:'sysPermit/getOrgRoleByUser.action',
		success:function(data){
			if(data.status){
				orgPermissionStr = data.data;
				initOrgListTree();
			}
		},
		error:function(){
			Utils.showMsg("error","加载用户机构权限失败");
		}
	});
});

/**
 * 加载机构左侧树
 */
var orgId = 0;
var orgTree, orgNodes, selectTreeNode;
var roleId;
function initOrgListTree() {
	//加载机构树
	var treeData = {};
	treeData.url = "sysOrg/getAllOrgTree.action";
	treeData.text = "orgName";
	treeData.id = "orgId";
	treeData.pId = "parentOrgId";
	treeData.rootPid = "0";
	treeData.treeId = "orgTree";
	treeData.click = function(event, treeId, treeNode) {
		orgTree = $.fn.zTree.getZTreeObj("orgTree");
		if (treeNode!=null) {
			selectTreeNode = treeNode;
			orgId = treeNode.orgId;
			orgNodes = orgTree.getSelectedNodes();
			initOrgInfo(treeNode);
			if(treeNode.state == 0) {
				roleTable("");
				Utils.showMsg('error', '该组织机构已被冻结!');
				return;
			}
		}
		initRoleInfo();
	};
	treeData.oParam = {};
	treeData.check = false;
	treeData.runClick = function() {};
	treeData.nodesStr = orgPermissionStr;
	treeData.expand = false;
	if(orgPermissionStr){
		commonTree.tree(treeData);
	}
}

/**
 * 初始化角色信息
 */
function initRoleInfo(){
	var p = {
			key:"input",
			data:"value",
			functions:{"#add":"addTypeDictView"}
	};
	c_tableFunction.tableFunction(p);
	$('input').iCheck({
		checkboxClass: 'icheckbox_square-yellow',
		radioClass: 'iradio_square-yellow',
		increaseArea: '20%' // optional
	});
	//视图切换
	if(viewFlag == 1) {
		$("#listIcon").addClass("active");
		roleTable(orgId);
	} else {
		$("#mapIcon").addClass("active");
		loadData();
	}
}

/**
 * 根据机构ID加载角色列表
 * @param orgId 机构ID
 */
function roleTable(orgId) {
	dataTable({
		id: "roleTable",
		lengthChange: false,
		sort: false,
		info: false,
		paginate: false,
		autoWidth: true,
		processing: true,
		check: false,
		singleSelect: false,
		url: "sysRole/findRoleListByOrgId.action",
		data: {orgId: orgId, roleSource: "jggl"},
		columns:[
	 		{ title: getProp("sys.org.roleName"), data: "roleName" },
			{ title: getProp("sys.org.roleType"), data: "roleType", formatter: convertRoleType },
			{ title: getProp("sys.org.roleState"), data: "state", hClass: 'center',formatter: convertState },
			{ title: getProp("sys.org.userNum"), data: "userNum" },
			{ title: getProp("public.txt.operation"), formatter: initOperation }
		]
	});
}

/**
 * 转换角色类型
 * @param value
 * @param type
 * @param data
 * @returns
 */
function convertRoleType(value, type, data) {
	if(value == 2) {
		return getProp("sys.org.roleType.normal");
	} else if(value == 1) {
		return getProp("sys.org.roleType.admin");
	}
}

/**
 * 转换状态
 * @param value
 * @param type
 * @param data
 * @returns
 */
function convertState(value, type, data) {
	if(value == 1) {
		return "<span class='label label-success'>"+getProp("public.select.effective")+"</span>";
	} else {
		return "<span class='label label-warning'>"+getProp("public.select.einvalid")+"</span>";
	}
}

/**
 * 初始化操作
 * @param value
 * @param type
 * @param data
 * @returns {String}
 */
function initOperation(value, type, data) {
	var btnHtml = '<div class="side" id="side">';
	btnHtml +='<button   class="side-control btn btn-default btn-xs"><i class="fonticon icon-bianji"></i>'+getProp("public.txt.operation")+'</button>';
	btnHtml += '<div class="sidebox">';
	if(editFlag){
		btnHtml += '<a href="javascript:editRole(\'edit\', \''+data.roleId+'\');" title="'+getProp("public.txt.edit")+'"><i class="fonticon icon-edit"></i>'+getProp("public.txt.edit")+'</a>';
	}
    if(data.state==0) {
    	if(activeFlag){
    		btnHtml += '<a href="javascript:doEnableRole(\''+data.roleId+'\');" title="'+getProp("public.txt.activate")+'"><i class="glyphicon bigger-160 glyphicon-asterisk"></i>'+getProp("public.txt.activate")+'</a>';
    	}
    } else {
    	if(frozenFlag){
    		btnHtml += '<a href="javascript:doDisableRole(\''+data.roleId+'\');" title="'+getProp("public.txt.frozen")+'"><i class="glyphicon bigger-160 glyphicon-ban-circle"></i>'+getProp("public.txt.frozen")+'</a>';
    	}
    }
    if(userChange){
    	btnHtml += '<a href="javascript:addUserByRole(\''+data.roleId+'\');" title="'+getProp("sys.org.addPerson")+'"><i class="fonticon icon-my"></i>'+getProp("sys.org.addPerson")+'</a>';
    }
    if(permitChange){
    	btnHtml += '<a href="javascript:addRolePermission(\''+data.roleId+'\');" title="'+getProp("sys.org.auth")+'"><i class="fa fa-wrench"></i>'+getProp("sys.org.auth")+'</a>';
    }
	btnHtml += '</div>';
	btnHtml += '</div>';
	return btnHtml;
}

/**
 * 初始化组织机构数据
 * @param node 节点
 */
function initOrgInfo(treeNode){
	var form = $("#descForm");
	Utils.fillForm(treeNode, form);
	if(treeNode.state == 0){
		form.find("[data-field=orgState]").html(getProp("public.select.einvalid"));			
		form.find("[data-field=orgState]").attr("class","text-red");			
	} else {
		form.find("[data-field=orgState]").html(getProp("public.select.effective"));			
		form.find("[data-field=orgState]").attr("class","text-green");
	}
}

/**
 * 新增、修改机构弹出框
 * @param method add：新增，edit：修改
 */
function editOrg(method) {
	if (method=="edit") {
		if(orgId == "0"){
			Utils.showMsg('error', getProp("sys.org.msg1")+getProp("public.txt.sigh"));
			return ;
		}
	}
	var title = (method == 'add') ? getProp("sys.org.addSuborg") : getProp("sys.org.editOrg");
	// 判断是否选择了树上的组织机构
	var node;
	if(node = validSelectedOrg()) {
		if(node.state == 0) {
			Utils.showMsg('error', getProp("sys.org.msg2")+getProp("public.txt.sigh"));
			return;
		}
		if(orgId < 0 && method != 'edit'){
			Utils.showMsg('error', getProp("sys.org.msg3")+getProp("public.txt.sigh"));
			return;
		}
		Utils.showModel({
			id:"editOrg",
			title: title, 
			btns:[["save_editOrg",getProp("public.btn.save")]], 
			url: "sysOrg/forwardOrgEdit.action",
			onload: function() {
				var form = $("#editOrgForm");
				form.validation(function(obj,params){
					if (obj.name=='orgCode'){
						var data = {};
						if(form.find("[name=orgId]").val()){
							data.orgId = form.find("[name=orgId]").val();
						}
						data.orgCode = form.find("[name=orgCode]").val();
						$.post("sysOrg/validateHasOrgCode.action", data, function(data){
							if(data) {
								params.err = true;
								params.msg = "机构标识已经存在";
							} else {
								params.err = false;
								params.msg = "";
							}
						});
					}},
				{reqmark:true});
				$('#save_editOrg').click(saveOrg);
				form.find("[name=orgName]").blur(function(){
					form.find("[name=orgFullName]").val(form.find("[name=orgName]").val());
				});
				if(method == "add") {
					// 新增
					form.find("#addMethod_div").show();
					// 设置新机构的父级为树上选择的节点
					form.find("[name=parentOrgId]").val(orgId);
					form.find("[name=addMethod]").val('sub');
					$('.selectpicker').selectpicker();
				} else {
					Utils.ajax({
						url : 'sysOrg/findById.action',
						data : {'orgId': orgId},
						success : function(data) {
							if(data != '') {
								//初始表单
								form.find('#addMethod_div').hide();
								Utils.fillForm(data, form)
								$('#editOrgForm').find('[name=addMethod]').val('');
								$('.selectpicker').selectpicker('render');
							}
						}
					});
				}
			}
		});
	}
}

/**
 * 保存机构
 */
function saveOrg() {
	var form = $("#editOrgForm");
	if(form.valid(this)==false){
	       return false;
	}
	//验证机构名称是否存在
	var orgName = $.trim(form.find('[name=orgName]').val());
	var orgType = form.find('[name=orgType] option:checked').text();
	var remark = form.find('[name=remark]').val();
	var areaName = form.find("[name=areaName]").val();
	var addMethod = $.trim(form.find('[name=addMethod]').val());
	var parentOrgId = form.find('[name=parentOrgId]').val(); 
	var orgId = form.find('[name=orgId]').val();
	if (!orgId) {
		if (selectTreeNode.parentOrgId == 0 && addMethod == "brother") {
			Utils.showMsg('error', '顶级机构下不能添加平级机构!');
			return false;
		}
	}
	if(orgName){
		Utils.ajax({
			url : 'sysOrg/findSameOrganizationName.action',
			data : {
				'orgName': orgName,
				'addMethod': addMethod,
				'parentOrgId': parentOrgId,
				'orgId': orgId
			},
			success : function(flag) {
				if(flag) { //不存在
					if (form.valid(this)==false){
						return false;
				    } else {
						Utils.ajax({
							url : 'sysOrg/doSave.action',
							data : form.serialize(),
							success : function(data) {
								// 重新获取用户数据权限
								Utils.ajax({
									url:'sysPermit/getOrgRoleByUser.action',
									success:function(data){
										if(data.status){
											orgPermissionStr = data.data;
										}
									},
									error:function(){
										Utils.showMsg("error","加载用户机构权限失败");
									}
								});
								if(data.status) {
									//修改机构后刷新结点
									var selectedNode = orgTree.getSelectedNodes()[0];
									if(orgId > 0 ){
										selectedNode.orgName = orgName;
										selectedNode.typeName = orgType;
										selectedNode.remark = remark;
										selectedNode.areaName = areaName;
										orgTree.updateNode(selectedNode);
									}else{
										// 新增刷新树结构
										refreshAddTree(data.sysOrg, addMethod);
									}
									initOrgInfo(selectedNode);
									Utils.closeModel('editOrg');
									Utils.showMsg('success', '机构保存成功!');
								} else {
									Utils.showMsg('error', '机构保存失败!');
								}
							}
						});
				    }
				} else {
					Utils.showMsg('warning', '机构名称：['+ orgName +']已存在!');
				}
			}
		});
	}
}

/**
 * 刷新树结构
 * @param sysOrg
 * @param addMethod
 */
function refreshAddTree(sysOrg, addMethod){
	// 刷新父节点
	var node;
	var selectedNode = orgTree.getSelectedNodes()[0];
	if(!selectedNode['isParent']){						
		selectedNode['isParent'] = true;
	}
	if(addMethod != 'sub'){
		selectedNode = selectedNode.getParentNode();
	}
	var newNode = {
			orgName: sysOrg.orgName, 
			orgId: sysOrg.orgId, 
			parentOrgId: sysOrg.parentOrgId, 
			remark: sysOrg.remark
	};
	orgTree.addNodes(selectedNode, newNode);
	orgTree.expandNode(selectedNode, true, false, false, true);
	// 设置选中新增节点
	orgTree.selectNode(node, false);
}

/**
 * 激活机构
 */
function doEnableOrg(){
	if(orgId == "0"){
		Utils.showMsg('error',  getProp("sys.org.msg8")+getProp("public.txt.sigh"));
		return ;
	}
	checkIsSelected(function() {
		var node;
		if(node = validSelectedOrg()) {
			if(node.state == 1) {
				Utils.showMsg('error', getProp("sys.org.msg10")+getProp("public.txt.sigh"));
				return;
			}
			// 判断是否父节点为激活状态，如果不是，不能激活
			Utils.ajax({
				url:'sysOrg/getParentOrgState.action',
				data:{'orgId': orgId},
				success:function(data){
					if(data) {
						Utils.confirm({msg:getProp("sys.org.confirm.msg2")+getProp("public.txt.question"),modalSure:function () {
							$("#btnEnable").attr("disabled", "disabled");
							// 激活节点
							Utils.ajax({
								url:'sysOrg/doEnabled.action',
								data:{'orgId': orgId, 'state': 1},
								success:function(dataC){
									$("#btnEnable").removeAttr("disabled");
									if(dataC) {
										Utils.showMsg('success', getProp("sys.org.msg13")+getProp("public.txt.sigh"));
										//更新该节点状态
										node.state = 1;
										orgTree.updateNode(node);
										//更新该节点的子节点状态
										updateAllChildNode(node, 1);
										initOrgInfo(node);//重新加载组织机构信息
										roleTable(orgId);
									} else {
										Utils.showMsg('error', getProp("sys.org.msg14")+getProp("public.txt.sigh"));
									}
								},
								error:function(){
									$("#btnEnable").removeAttr("disabled");
								}
							});
						}});
					} else {
						Utils.showMsg('error', getProp("sys.org.msg15")+getProp("public.txt.sigh"));
					}
				},
				error: function(er, xhr){
					Utils.showMsg('error', getProp("sys.org.msg14")+getProp("public.txt.sigh"));
				}
			});
		}
	});
}

/**
 * 冻结机构
 */
function doDisableOrg(){
	if(orgId == "0"){
		Utils.showMsg('error', getProp("sys.org.msg7")+getProp("public.txt.sigh"));
		return ;
	}
	checkIsSelected(function() {
		var node;
		if(node = validSelectedOrg()) {
			if(node.state == 0) {
				Utils.showMsg('error', getProp("sys.org.msg9")+getProp("public.txt.sigh"));
				return;
			}
			Utils.confirm({msg:getProp("sys.org.confirm.msg1")+getProp("public.txt.question"),modalSure:function () {
				$("#btnDisable").attr("disabled", "disabled");
				Utils.ajax({
					url : 'sysOrg/doUnabled.action',
					data : {'orgId': orgId, 'state': 0},
					success : function(data) {
						$("#btnDisable").removeAttr("disabled");
						if(data) {
							Utils.showMsg('success', getProp("sys.org.msg11")+getProp("public.txt.sigh"));
							//更新该节点状态
							node.state = 0;
							orgTree.updateNode(node);
							//更新该节点的子节点状态
							updateAllChildNode(node, 0);
							initOrgInfo(node);//重新加载组织机构信息
							roleTable(orgId);
						} else {
							Utils.showMsg('error', getProp("sys.org.msg12")+getProp("public.txt.sigh"));
						}
					},
					error:function(){
						$("#btnDisable").removeAttr("disabled");
					}
				});
			}});
		}
	});
}

/**
 * 递归更新当前节点的所有子节点
 * @param node 当前节点
 */
function updateAllChildNode(node, state){ 
	//判断是否有子节点
	if (node.children == undefined)
	return;
	//更新父节点状态
	node.state = state;
	orgTree.updateNode(node);
	//更新父节点下的子节点状态
	for (var i = 0; i < node.children.length; i++) {
		node.children[i].state = state;
		orgTree.updateNode(node.children[i]);
		updateAllChildNode(node.children[i], state);
	}
}

/**
 * 新增、修改角色弹出框
 * @param method add：新增，edit：修改
 * @param roleId 如果不为空，代表卡片修改
 */
function editRole(method, roleId) {
	var title = (method == 'add') ? getProp("sys.org.addRole") : getProp("sys.org.editRole");
	if(orgId == "0"){
		Utils.showMsg('error', getProp("sys.org.msg4")+getProp("public.txt.sigh"));
		return ;
	}
	var node = '';
	// 判断是否选择了树上的组织机构
	if((node = validSelectedOrg()) == false) { 
		return ;
	}
	if(node.state == 0) {
		Utils.showMsg('error', '['+node.orgName+']'+getProp("sys.org.msg5")+getProp("public.txt.sigh"));
		return ;
	}
	if(roleId == undefined && method == 'edit') {
		roleId = dataTable.getSelectRow('roleTable').roleId;
	} else {
		roleId = roleId;
	}
	if(roleId == undefined  && method == 'edit') {
		Utils.showMsg('error', getProp("sys.org.msg6")+getProp("public.txt.sigh"));
		return ;
	}
	Utils.showModel({
		id:"editRole",
		title: title, 
		btns:[["save_editRole",getProp("public.btn.save")]], 
		url: "sysRole/forwardRoleEdit.action",
		onload: function() {
			var form = $('#editRoleForm');
			form.find('[name=orgId]').val(orgId);
			form.find('[name=roleSource]').val("jggl");
			form.validation(function(obj,params){},{reqmark:true});
			$('.selectpicker').selectpicker();
			$('#save_editRole').click(saveRole);
			if(method == 'edit') {
				Utils.ajax({
					url : 'sysRole/findById.action',
					data : {'roleId': roleId},
					success : function(data) {
						if(data != '') {
							Utils.fillForm(data, form);
							$('.selectpicker').selectpicker('render');
						}
					}
				});
			}
		}
	});
}

/**
 * 保存角色
 */
function saveRole() {
	var form = $("#editRoleForm");
	if(form.valid(this)==false){
	       return false;
	}
	//验证同级机构下角色名称是否存在
	var roleId = form.find('[name=roleId]').val();
	var roleName = $.trim(form.find('[name=roleName]').val());
	if(roleName) {
		Utils.ajax({
			url : 'sysRole/findExistsRoleNameByOrgId.action',
			data : {
				'orgId': orgId, 
				'roleName': roleName, 
				'roleId': roleId
			},
			success : function(data) {
				if(data) {
					if (form.valid(this)==false){
						return;
				    }
					Utils.ajax({
						url : 'sysRole/doSave.action',
						data : form.serialize(),
						success : function(data) {
							if(data) {
								Utils.showMsg('success', '保存角色成功!');
								if(viewFlag == 1) {
									dataTable.reload('roleTable');
								} else {
									loadData();
								}
							} else {
								Utils.showMsg('error', '保存角色失败!');
							}
							Utils.closeModel('editRole');
						}
					});
				} else {
					Utils.showMsg('error', '角色名称：['+ roleName +']已存在!');
				}
			}
		});
	}
}

/**
 * 视图变更
 * @param flag 1-表示到table视图,0表示到卡片视图
 */
function switchView(flag){
	if(flag == 0) {//到卡片视图
		if(viewFlag == 0){//如果当前是卡片视图,则不处理
			return;
		}
		viewFlag = 0;
		$("#roleTable").hide();
		$("#orgRoleCardList").show();
		loadData();
	} else {//到列表视图
		if(viewFlag == 1){
			return;
		}
		viewFlag = 1;
		$("#orgRoleCardList").hide();
		$("#roleTable").show();
		roleTable(orgId);
	}
}

/*****************************角色人员管理*********************************/
var addUserOrgId = 0;
var addUserTree, userNodes;
/**
 * 为角色添加用户弹出框
 * @param roleId 如果不为空，代表卡片修改
 */
function addUserByRole(roleId) {
	if(roleId == undefined) {
		roleId = dataTable.getSelectRow('roleTable').roleId;
	} else {
		roleId = roleId;
	}
	if(roleId == undefined) {
		Utils.showMsg('error', getProp("sys.org.msg6")+getProp("public.txt.sigh"));
		return ;
	}
	$('#roleId_addUserByRole').val(roleId);
	Utils.showModel({
		id:"addUserByRole",
		title: getProp("sys.org.addPerson"), 
		btns:[["save_addUserByRole",getProp("public.btn.save")]], 
		url: "sysRole/forwardRoleUser.action",
		onload: function() {
			$("#addUserTree").slimScroll({height:350});
			//加载机构树
			var treeData = {};
			treeData.url = "sysOrg/getOrgTree.action";
			treeData.text = "orgName";
			treeData.id = "orgId";
			treeData.pId = "parentOrgId";
			treeData.rootPid = "0";
			treeData.treeId = "addUserTree";
			treeData.click = function(event, treeId, treeNode) {
				addUserTree = $.fn.zTree.getZTreeObj("addUserTree");
				userNodes = addUserTree.getSelectedNodes();
				//加载未选用户
				showOrgNotCheckUser(treeNode.orgId);
				addUserOrgId = treeNode.orgId;
			};
			treeData.oParam = [];
			treeData.check = false;
			treeData.runClick = function() {};
			treeData.nodesStr = orgPermissionStr;
			treeData.expand = false;
			if (orgPermissionStr) {
				commonTree.tree(treeData);
			}
			//加载已选用户
			showOrgCheckUser();
			//为保存按钮绑定单击事件
			$('#save_addUserByRole').click(saveUserByRoleId);
		}
	});
}

/**
 * 加载未选用户
 * @param orgId 机构ID
 */
function showOrgNotCheckUser(orgId) {
	//根据机构ID把用户显示到未选用户下
	Utils.ajax({
        url: "sysUser/findUserListByOrgIdOrRoleId.action",  
        data: {orgId: orgId}, 
        success: function(data) {
        	var userInfoHtml = '';
        	var notCheckedOrgIds = '';
        	var checkedUserIds = $('#checkedUserIds').text();
        	for(var i=0; i < data.length; i++) {
        		if(checkedUserIds.indexOf(data[i].userId) != -1) {
        			continue;
        		}
        		userInfoHtml += '<span id="user_'+ data[i].userId +'" onclick="addUserToCheck(\''+ data[i].orgId +'\',\''+ data[i].userId +'\',\''+ data[i].userName +'\')" class="btn btn-xs blue">';
        		userInfoHtml += data[i].userName;
        		userInfoHtml += '</span>';
        		notCheckedOrgIds += data[i].orgId + ",";
        	}
        	$('#notCheckContentUser').html(userInfoHtml);
    		$('#notCheckedOrgIds').text(notCheckedOrgIds);
        }
	});
}

/**
 * 加载已选用户
 * @param orgId 机构ID
 */
function showOrgCheckUser() {
	//根据角色ID把用户显示到已选用户下
	var roleId = $('#roleId_addUserByRole').val();
	Utils.ajax({
        url: "sysUser/findUserListByOrgIdOrRoleId.action",  
        data: {roleId: roleId}, 
        success: function(data) {
        	if(data != null && data != '') {
        		var checkedUserIds = '';
        		var userInfoHtml = '';
        		for(var i=0; i < data.length; i++) {
        			userInfoHtml += '<span id="checkUser_'+ data[i].userId +'" class="btn btn-xs green">';
        			userInfoHtml += data[i].userName;
        			userInfoHtml += '<i class="fa fa-close" onclick="addUserToNotCheck(\''+ data[i].orgId +'\',\''+ data[i].userId +'\',\''+ data[i].userName +'\')"></i>';
        			userInfoHtml += '</span>';
        			//拼装已选的用户ID
        			checkedUserIds += data[i].userId + ",";
        		}
        		$('#checkContentUser').html(userInfoHtml);
        		$('#checkedUserIds').text(checkedUserIds);
        	}
        }
	});
}

/**
 * 选择用户，把用户显示到已选用户下
 * @param userOrgId 用户机构ID
 * @param userId 用户ID
 * @param userName 用户名称
 */
function addUserToCheck(userOrgId, userId, userName) {
	//判断已选用户中是否存在当前选择的用户，如果存在则不显示到已选用户下
	var checkUserIds = $('#checkedUserIds').text();
	if(checkUserIds.indexOf(userId) != -1) {
		Utils.showMsg('error', getProp("sys.org.msg24")+getProp("public.txt.sigh"));
		return;	
	}
	$('#user_'+userId).remove();
	var userInfoHtml = '';
	userInfoHtml += '<span id="checkUser_'+ userId +'" class="btn btn-xs green">';
	userInfoHtml += userName;
	userInfoHtml += '<i class="fa fa-close" onclick="addUserToNotCheck(\''+ userOrgId +'\',\''+ userId +'\',\''+ userName +'\')"></i>';
	userInfoHtml += '</span>';
	$('#checkContentUser').append(userInfoHtml);
	//拼装已选的用户ID
	var userIds = $('#checkedUserIds').text();
	userIds =  userId + ',';
	$('#checkedUserIds').append(userIds);
}

/**
 * 取消选择用户，把取消选择的用户重新显示到未选用户下
 * @param userOrgId 用户机构ID
 * @param userId 用户ID
 * @param userName 用户名称
 */
function addUserToNotCheck(userOrgId, userId, userName) {
	$('#checkUser_'+userId).remove();
	//如果当前选中的机构等于用户机构，则回显到未选用户下
	if(userOrgId == addUserOrgId) {
		var userInfoHtml = '';
		userInfoHtml += '<span id="user_'+ userId +'" title="单击添加用户" onclick="addUserToCheck(\''+ userOrgId +'\',\''+ userId +'\',\''+ userName +'\')" class="btn btn-xs blue">';
		userInfoHtml += userName;
		userInfoHtml += '</span>';
		$('#notCheckContentUser').append(userInfoHtml);
	}
	//拼装已选的用户ID
	var userIds = $('#checkedUserIds').text();
	if(userIds.indexOf(userId) != -1) {
		userIds = userIds.replace(userId+',', '');
		$('#checkedUserIds').text(userIds);
	}
}

/**
 * 保存用户
 */
function saveUserByRoleId() {
	var roleId = $('#roleId_addUserByRole').val();
	var userIds = $('#checkedUserIds').text();
	userIds = userIds.substring(0, userIds.lastIndexOf(","));
	Utils.ajax({
        url: "sysRole/saveUserByRoleId.action",  
        data: {roleId: roleId, userIds: userIds}, 
        success: function(data) {
        	if(data) {
        		Utils.showMsg('success', '保存用户成功!');
        		Utils.closeModel('addUserByRole');
				if(viewFlag == 1) {
					dataTable.reload('roleTable');
				} else {
					loadData();
				}
        	} else {
        		Utils.showMsg('error', '保存用户失败!');
        	}
        }
	});
}

/*****************************角色权限管理*********************************/
/**
 * 新增角色权限弹出框
 * @param roleId 如果不为空，代表卡片修改
 */
function addRolePermission(roleId) {
	if(roleId == undefined) {
		roleId = dataTable.getSelectRow('roleTable').roleId;
	} else {
		roleId = roleId;
	}
	if(roleId == undefined) {
		Utils.showMsg('error', getProp("sys.org.msg6")+getProp("public.txt.sigh"));
		return ;
	}
	$('#roleId_addPermission').val(roleId);
	Utils.showModel({
		id:"addRolePermission",
		title: getProp("sys.org.auth"), 
		btns:[["save_addRolePermission",getProp("public.btn.save")]], 
		url: "sysRole/forwardRolepermission.action",
		onload: function() {
			//加载机构树
			initPermissionOrgTree();
			//加载功能树
			initPermissionFuncTree();
			//为保存按钮绑定单击事件
			$("#permissionOrgTree").slimScroll({height:350});
			$("#permissionFuncTree").slimScroll({height:350});
			$('#save_addRolePermission').click(savePermiss);
		}
	});
}

/**
 * 加载机构树
 */
var permissionOrgTree, permOrgNodes;
function initPermissionOrgTree() {
	//加载机构树
	var treeData = {};
	treeData.url = "sysPermit/getOrgTreeAll.action";
	treeData.text = "orgName";
	treeData.id = "orgId";
	treeData.pId = "parentId";
	treeData.rootPid = "0";
	treeData.treeId = "permissionOrgTree";
	treeData.click = function(event, treeId, treeNode) {
		permissionOrgTree = $.fn.zTree.getZTreeObj("permissionOrgTree");
	};
	treeData.oParam = {roleId: $('#roleId_addPermission').val()};
	treeData.check = true;
	treeData.runClick = function() {};
	treeData.nodesStr = orgPermissionStr;
	treeData.expand = false;
	if (orgPermissionStr) {
		commonTree.tree(treeData);
	}
}

/**
 * 加载功能树
 */
var permissionFuncTree, funcNodes;
function initPermissionFuncTree() {
	//加载机构树
	var treeData = {};
	treeData.url = "sysPermit/getFunctionTreeAll.action";
	treeData.text = "functionName";
	treeData.id = "functionId";
	treeData.pId = "parentId";
	treeData.rootPid = "0";
	treeData.treeId = "permissionFuncTree";
	treeData.click = function(event, treeId, treeNode) {
		permissionFuncTree = $.fn.zTree.getZTreeObj("permissionFuncTree");
	};
	treeData.oParam = {roleId: $('#roleId_addPermission').val()};
	treeData.check = true;
	treeData.runClick = function() {};
	treeData.nodesStr = "";
	treeData.expand = false;
	commonTree.tree(treeData);
}

/**
 * 验证是否已选择机构树节点
 * @returns false：未选中
 */
function validSelectedPerOrg(){
	permOrgNodes = permissionOrgTree.getCheckedNodes(true);
	if(permOrgNodes != '') {
		return true;
	} else {
		return false;
	}
};

/**
 * 验证是否已选择功能树节点
 * @returns false：未选中
 */
function validSelectedPerFunc(){
	funcNodes = permissionFuncTree.getCheckedNodes(true);
	if(funcNodes != '') {
		return true;
	} else {
		return false;
	}
};

/**
 * 保存权限
 */
function savePermiss() {
	if(validSelectedPerOrg() && validSelectedPerFunc()) {
		var roleId = $('#roleId_addPermission').val();
		var orgIds = '';
		var funcIds = '';
		//获取机构树勾选的机构ID
		for (var i = 0; i<permOrgNodes.length; i++) {
			if(permOrgNodes[i].orgId!=0){				
				orgIds += permOrgNodes[i].orgId + ",";
			}
		}
		//获取机构树勾选的机构ID
		for (var i = 0; i<funcNodes.length; i++) {
			funcIds += funcNodes[i].functionId + ",";
		}
		if(funcIds != '' && orgIds != '') {
			orgIds = orgIds.substring(0, orgIds.lastIndexOf(","));
			funcIds = funcIds.substring(0, funcIds.lastIndexOf(","));
			Utils.ajax({
		        url: "sysPermit/savePermission.action",  
		        data: {roleId: roleId, orgIds: orgIds, funcIds: funcIds}, 
		        success: function(data) {
		        	if(data) {
		        		Utils.closeModel('addRolePermission');
		        		Utils.showMsg('success', '保存权限配置成功!');
		        	} else {
		        		Utils.showMsg('error', '保存权限配置失败!');
		        	}
		        }
			});
		}
	} else{
		Utils.showMsg('error', '请勾选要配置的机构或功能节点!');
	}
}

/**
 * 激活角色
 */
function doEnableRole(roleId){
	if(roleId == undefined) {
		roleId = dataTable.getSelectRow('roleTable').roleId;
	} else {
		roleId = roleId;
	}
	if(roleId == undefined) {
		Utils.showMsg('error', getProp("sys.org.msg6")+getProp("public.txt.sigh"));
		return ;
	}
	Utils.confirm({msg:getProp("sys.org.confirm.msg5")+getProp("public.txt.question"),modalSure:function () {
		Utils.ajax({
	        url: "sysRole/enableRole.action",  
	        data: {roleIds: roleId, orgId: orgId, state: 1}, 
	        success: function(data) {
	        	if(data) {
	        		Utils.confirm({msg:getProp("sys.org.confirm.msg6")+getProp("public.txt.question"),modalSure:function () {
	        			Utils.ajax({
					        url: "sysRole/enableRoleUser.action",  
					        data: {roleIds: roleId, state: 1}, 
					        success: function(dataUser) {
					        	if(dataUser) {
					        		Utils.showMsg('success', getProp("sys.org.msg20")+getProp("public.txt.sigh"));
					        	} else {
					        		Utils.showMsg('error', getProp("sys.org.msg21")+getProp("public.txt.sigh"));
					        	}
					        }
						});
	        		}});
	        		Utils.showMsg('success', getProp("sys.org.msg22")+getProp("public.txt.sigh"));
					if(viewFlag == 1) {
						dataTable.reload('roleTable');
					} else {
						loadData();
					}
	        	} else {
	        		Utils.showMsg('error', getProp("sys.org.msg23")+getProp("public.txt.sigh"));
	        	}
	        }
		});
	}});
}

/**
 * 冻结角色
 */
function doDisableRole(roleId){
	if(roleId == undefined) {
		roleId = dataTable.getSelectRow('roleTable').roleId;
	} else {
		roleId = roleId;
	}
	if(roleId == undefined) {
		Utils.showMsg('error', getProp("sys.org.msg6")+getProp("public.txt.sigh"));
		return ;
	}
	Utils.confirm({msg:getProp("sys.org.confirm.msg3")+getProp("public.txt.question"),modalSure:function () {
		Utils.ajax({
			url: "sysRole/unableRole.action",  
	        data: {roleIds: roleId, orgId:orgId, state: 0}, 
	        success: function(data) {
	        	if(data) {
	        		Utils.confirm({msg:getProp("sys.org.confirm.msg4")+getProp("public.txt.question"),modalSure:function () {
	        			Utils.ajax({
							url: "sysRole/unableRoleUser.action",  
					        data: {roleIds: roleId, state: 0}, 
					        success: function(dt) {
					        	if(dt){
					             	Utils.showMsg('success', getProp("sys.org.msg16")+getProp("public.txt.sigh"));
					         	}else{
					         		Utils.showMsg('error', getProp("sys.org.msg17")+getProp("public.txt.sigh"));
					         	}
					          }		
				        });
	        		}});
	        		Utils.showMsg('success', getProp("sys.org.msg18")+getProp("public.txt.sigh"));
					if(viewFlag == 1) {
						dataTable.reload('roleTable');
					} else {
						loadData();
					}
	        	} else {
	        		Utils.showMsg('error', getProp("sys.org.msg19")+getProp("public.txt.sigh"));
	        	}
	        }
	    });
	}});
}

/**
 * 验证是否已选择机构树节点
 * @returns false：未选中
 */
function validSelectedOrg(){
	if(orgNodes.length==1) {
		return orgNodes[0];
	} else {
		Utils.showMsg('error', '请选择相应的组织机构!');
		return false;
	}
};

/**
 * 激活或冻结前判断是否已经选中节点（排除最顶层的节点）
 * @param func 
 */
function checkIsSelected(func) {
	if(orgNodes.length < 1){
		Utils.showMsg('error', '请选择相应的组织机构!');
		return;
	} else {
		if(typeof (func) == 'function'){			
			func();
		}
	}
}

/****************卡片视图**********************/
var hasData = true;//是否有数据
/**
 * 字符串截取,英文按照一个长度,中文按照两个长度
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
	Utils.ajax({
		url:'sysRole/findRoleListByOrgId.action',
		data: {orgId: orgId},
		success:function(data){
			$("#orgRoleCardList").html('');
			var orgRoleList =data.data;
			if(orgRoleList.length<=0){
				hasData = false;//无数据
				Utils.showMsg("info","没有更多数据");
				return;
			} 
			var html = '';
			var roleName;
			for (var i = 0; i < orgRoleList.length; i++) {
				if(orgRoleList[i].state==1){
				html += '<div class="card card-green">';
				}
				else{
				html += '<div class="card card-yellow">';
				}
				roleName = subStr(orgRoleList[i].roleName,30);
				html += '	<div class="card-form">';
				html += '		<span class="title"><label id="userCardName'+orgRoleList[i].roleId+'" title="'+orgRoleList[i].roleName+'">'+roleName+'</label></span>';
				html += '		<span class="pull-right"><label id="checkbox_label" ><input type="checkbox" id="checkbox" name="roleCheck"  roleId="'+orgRoleList[i].roleId+'"><span class="lbl"></span></label></span>';
				html +=	'	</div>';	            
				html += '	<div class="space2"></div>';
				html += '	<div class="card-content">';
				if(orgRoleList[i].roleType == 1){						
					html += '	<div>'+getProp("sys.org.roleType")+getProp("public.txt.colon")+'<font id="userCardState'+orgRoleList[i].roleId+'">'+getProp("sys.org.roleType.admin")+'</font></div>';
				} else{
					html += '	<div>'+getProp("sys.org.roleType")+getProp("public.txt.colon")+'<font id="userCardState'+orgRoleList[i].roleId+'">'+getProp("sys.org.roleType.normal")+'</font></div>';
				}
				html += '		<div>'+getProp("sys.org.userNum")+getProp("public.txt.colon")+'<font id="userCardState'+orgRoleList[i].roleId+'">'+ orgRoleList[i].userNum +'</font></div>';
				if(orgRoleList[i].state==1){						
					html += '	<div>'+getProp("sys.org.roleState")+getProp("public.txt.colon")+'<span class="label  label-success" id="userCardState'+orgRoleList[i].roleId+'">'+getProp("public.select.effective")+'</span></div>';
				} else{
					html += '	<div>'+getProp("sys.org.roleState")+getProp("public.txt.colon")+'<span class="label  label-warning" id="userCardState'+orgRoleList[i].roleId+'" color="red">'+getProp("public.select.einvalid")+'</span></div>';
				}
				html += '	</div>';
				html += '	<div class="clear"></div>';
				html += '	<div class="dark card-toolbar">';
				html += '		<p><a href="javascript:void(0);" title="'+getProp("public.txt.edit")+'" class="btn2 btn-xs" onclick="editRole(\'edit\', '+orgRoleList[i].roleId+')"><i class="fa fa-edit"></i>'+getProp("public.txt.edit")+'</a>';
                if(orgRoleList[i].state==0){	                	
                	html += '	<a href="javascript:void(0);" title="'+getProp("public.txt.activate")+'" class="btn2 btn-xs" onclick="doEnableRole('+orgRoleList[i].roleId+')"><i class="fa fa-gavel"></i>'+getProp("public.txt.activate")+'</a>';
                } else{
                	html += '	<a href="javascript:void(0);" title="'+getProp("public.txt.frozen")+'" class="btn2 btn-xs" onclick="doDisableRole('+orgRoleList[i].roleId+')"><i class="fa fa-ban"></i>'+getProp("public.txt.frozen")+'</a>';
                }
                html += '		<a href="javascript:void(0);" title="'+getProp("sys.org.addPerson")+'" class="btn2 btn-xs" onclick="addUserByRole('+orgRoleList[i].roleId+')"><i class="fonticon icon-my"></i>'+getProp("sys.org.addPerson")+'</a>';
                html += '		<a href="javascript:void(0);" title="'+getProp("sys.org.auth")+'" class="btn2 btn-xs" onclick="addRolePermission('+orgRoleList[i].roleId+')"><i class="fa fa-wrench"></i>'+getProp("sys.org.auth")+'</a>';
                html += '	</div>';
                html += '</div>';
			}
			$("#orgRoleCardList").append(html);
			
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