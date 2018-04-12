/**
 * 初始化用户复选框
 * @param data
 * @param type
 * @param obj
 * @returns {String}
 */
function userListCheckInit(data, type, obj){
	 return "<label><input type='checkbox' class='ace' name='userCheck' userId='"+obj.userId+"'/><span class='lbl'></span></label>";
}

/**
 * 初始化用户性别
 * @param data
 * @param type
 * @param obj
 * @returns {String}
 */
function userListStateInit(data, type, obj){
	if(obj.state==0){
		return "<font color='red'>"+obj.stateName+"</font>";
	}
	return obj.stateName;
}

/**
 * 用户列表全选/全不选
 * @param e
 */
function userListCheckedAll(e){
	$("[name='userCheck']:checkbox").prop("checked",e.checked);
}

/**
 * 初始化用户复选框
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