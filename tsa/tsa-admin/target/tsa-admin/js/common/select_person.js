var deptTree;
var checkedUserAccount, checkedUserName;
var selectUserAccount, selectUserName;
var deptId, personAccount;

/**
 * 
 * @param checkAccount 保存用户账号容器ID
 * @param checkName	保存用户名容器ID
 * @param flag 是否控制权限 为空或者为0-不控制;1-控制
 * @returns
 */
function selectPerson(checkAccount, checkName, flag) {
	selectUserAccount = checkAccount;
	selectUserName = checkName;
	personAccount = $("#"+checkAccount).val();
	$('#checkPersonAccount').val(personAccount);
	Utils.showModel({
		id:"personSelectDiv",
		title: getProp("sys.user.list.title"), 
		btns:[["save_PersonSelectUser",getProp("public.btn.save")]], 
		url: "common/forwardSelectPerson.action",
		onload: function() {
			var orgPermissionStr = "";
			if(flag != null && flag == 1){
				// 获取用户数据权限
				Utils.ajax({
					url:'sysPermit/getOrgRoleByUser.action',
					async : false,
					success:function(data){
						if(data.status){
							orgPermissionStr = data.data;
						}
					},
					error:function(){
						Utils.showMsg("error","加载用户机构权限失败");
					}
				});
			}
			$("#personDeptTree").slimScroll({height:350});
			//加载机构树
			var treeData = {};
			treeData.url = "sysPermit/getOrgTreeAll.action?roleId=" + 1;
			treeData.text = "orgName";
			treeData.id = "orgId";
			treeData.pId = "parentId";
			treeData.rootPid = "0";
			treeData.treeId = "personDeptTree";
			treeData.click = function(event, treeId, treeNode) {
				deptTree = $.fn.zTree.getZTreeObj("personDeptTree");
				deptId = treeNode.orgId;
				initNotCheckUserInfo();
			};
			treeData.oParam = [];
			treeData.check = false;
			treeData.runClick = function() {};
			if(flag != null && flag == 1){
				treeData.nodesStr = orgPermissionStr;
			}else{
				treeData.nodesStr = "";
			}
			treeData.expand = false;
			if(flag != null && flag == 1){
				if(orgPermissionStr){
					commonTree.tree(treeData);
				}
			}else{
				commonTree.tree(treeData);
			}
			//为保存按钮绑定单击事件
			$('#save_PersonSelectUser').click(savePersonSelectUser);
			initCheckUserInfo();
		}
	});
}

function savePersonSelectUser() {
	checkedUserAccount = $("#checkPersonAccount").val();
	checkedUserAccount = checkedUserAccount.substring(0,checkedUserAccount.length-1);
	checkedUserName = $("#checkPersonName").text();
	$('#' + selectUserAccount).val(checkedUserAccount);
	$('#' + selectUserName).val(checkedUserName);
	$("#" + selectUserName).blur();
	Utils.closeModel('personSelectDiv');
}

/**
 * 加载未选用户
 * @param orgId 机构ID
 */
function initNotCheckUserInfo() {
	//根据机构ID把用户显示到未选用户下
	Utils.ajax({
        url: "sysUser/findUserListByOrgIdOrRoleId.action",  
        data: {orgId: deptId}, 
        success: function(data) {
        	var userInfoHtml = '';
        	var notCheckedPersonAccounts = '';
        	var checkedUserIds = $('#checkedUserIds').text();
        	personAccount = $('#checkPersonAccount').val();
        	for(var i=0; i < data.length; i++) {
        		if(personAccount.indexOf(data[i].userAccount) != -1) {
        			continue;
        		}
        		userInfoHtml += '<span id="user_'+ data[i].userAccount +'" onclick="addPersonToCheck(\''+ data[i].orgId +'\',\''+ data[i].userAccount +'\',\''+ data[i].userName +'\')" class="btn btn-xs blue">';
        		userInfoHtml += data[i].userName;
        		userInfoHtml += '</span>';
        		notCheckedPersonAccounts += data[i].orgId + ",";
        	}
        	$('#notCheckPersonName').html(userInfoHtml);
    		$('#notCheckPersonAccount').text(notCheckedPersonAccounts);
        }
	});
}

/**
 * 添加到选择人员
 * @param userOrgId
 * @param userAccount
 * @param userName
 * @returns
 */
function addPersonToCheck(userOrgId, userAccount, userName){
	//判断已选用户中是否存在当前选择的用户，如果存在则不显示到已选用户下
	var checkUserIds = $('#checkedUserIds').text();
	if(checkUserIds.indexOf(userAccount) != -1) {
		Utils.showMsg('error', getProp("sys.org.msg24")+getProp("public.txt.sigh"));
		return;	
	}
	$('#user_'+userAccount).remove();
	var userInfoHtml = '';
	userInfoHtml += '<span id="checkUser_'+ userAccount +'" class="btn btn-xs green">';
	userInfoHtml += userName;
	userInfoHtml += '<i class="fa fa-close" onclick="addPersonToNotCheck(\''+ userOrgId +'\',\''+ userAccount +'\',\''+ userName +'\')"></i>&nbsp;';
	userInfoHtml += '</span>';
	$('#checkPersonName').append(userInfoHtml);
	//拼装已选的用户ID
	var userAccounts = $('#checkPersonAccount').val();
	userAccounts +=  userAccount + ',';
	$('#checkPersonAccount').val(userAccounts);
}

/**
 * 初始化已选择人员
 */
function initCheckUserInfo(){
	if(personAccount != null && personAccount != ""){
		Utils.ajax({
	        url: "common/findPersonsByAccount.action?account="+personAccount,  
	        success: function(data) {
	        	if(data != null){
	        		var checkedPersonAccount = '';
	        		var userInfoHtml = '';
	        		for(var i=0; i < data.length; i++) {
	        			userInfoHtml += '<span id="checkUser_'+ data[i].userAccount +'" class="btn btn-xs green">';
	        			userInfoHtml += data[i].userName;
	        			userInfoHtml += '<i class="fa fa-close" onclick="addPersonToNotCheck(\''+ data[i].orgId +'\',\''+ data[i].userAccount +'\',\''+ data[i].userName +'\')"></i>';
	        			userInfoHtml += '</span>&nbsp;&nbsp;';
	        			//拼装已选的用户ID
	        			checkedPersonAccount += data[i].userAccount + ",";
	        		}
	        		$('#checkPersonName').html(userInfoHtml);
	        		$('#checkPersonAccount').val(checkedPersonAccount);
	        	}
	        }
		});
	}
}

/**
 * 删除已选人员
 * @param userOrgId
 * @param userAccount
 * @param userName
 * @returns
 */
function addPersonToNotCheck(userOrgId, userAccount, userName) {
	$('#checkUser_'+userAccount).remove();
	//如果当前选中的机构等于用户机构，则回显到未选用户下
	if(userOrgId == deptId) {
		var userInfoHtml = '';
		userInfoHtml += '<span id="user_'+ userAccount +'" title="单击添加用户" onclick="addPersonToCheck(\''+ userOrgId +'\',\''+ userAccount +'\',\''+ userName +'\')" class="btn btn-xs blue">';
		userInfoHtml += userName;
		userInfoHtml += '</span>';
		$('#notCheckPersonName').append(userInfoHtml);
	}
	//拼装已选的用户ID
	var userAccounts = $('#checkPersonAccount').val();
	if(userAccounts.indexOf(userAccount) != -1) {
		userAccounts = userAccounts.replace(userAccount+',', '');
		$('#checkPersonAccount').val(userAccounts);
	}
}