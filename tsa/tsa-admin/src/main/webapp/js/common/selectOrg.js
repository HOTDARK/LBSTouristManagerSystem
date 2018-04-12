/**
 * 选择单位
 */
var thisOrgNodes;
var thisDivId ; //div的ID
var thisCodeId;  //code的ID
var thisValueName; //value的ID
var isPermit //是否控制权限
function selectOrg(divId,codeId,valueName,isPermit){
	thisDivId = divId;
	thisCodeId = codeId;
	thisValueName = valueName
	Utils.showModel({
 		id: divId,
 		width:"400px",
 		title: getProp("sys.org.text"), 
 		btns:[["save_Org",getProp("public.btn.save")]], 
 		url: "common/forwardSelectOrg.action",
 		onload: function() {
 			var orgPermissionStr = "";
 			if(isPermit==1){
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
			$("#addOrgTree").slimScroll({height:310});
			//加载机构树
			var treeData = {};
			treeData.url = "sysOrg/getOrgTree.action";
			treeData.text = "orgName";
			treeData.id = "orgId";
			treeData.pId = "parentOrgId";
			treeData.rootPid = "0";
			treeData.treeId = "addOrgTree";
			treeData.click = function(event, treeId, treeNode) {
				addOrgTree = $.fn.zTree.getZTreeObj("addOrgTree");
				orgNodes = addOrgTree.getSelectedNodes();
				thisOrgNodes = treeNode;
			};
			treeData.oParam = [];
			treeData.check = false;
			treeData.runClick = function() {};
			if(isPermit==1){
				treeData.nodesStr = orgPermissionStr;
			}else{
				treeData.nodesStr = "";	
			}			
			treeData.expand = false;
			if(isPermit==1){				
				commonTree.tree(treeData);
			}else{
				commonTree.tree(treeData);
			}
			//为保存按钮绑定单击事件
			$('#save_Org').click(saveSelectOrg);
			$(".modal-body").css("overflow-y","hidden");
		}
 	});
}
function saveSelectOrg(){
	$("#"+thisValueName).val(thisOrgNodes.orgName);
	$("#"+thisCodeId).val(thisOrgNodes.orgCode);
	Utils.closeModel(thisDivId);
}