var commonTree = {};
/**
 * 加载树
 * 
 * @param url
 * 			  请求地址
 * @param oParam
 * 			  请求参数（数组）
 * @param text
 * 			  显示node文本对应的字段
 * @param id
 * 			  设置node的idKey对应的字段名
 * @param pId
 * 			  设置node的pIdKey对应的字段名
 * @param rootPid
 * 			  根节点父级ID
 * @param treeId
 *            树的元素ID（div的ID）
 * @param click
 *            点击事件回调函数 回调函数提供三个参数： 1、event 事件源 2、treeId 树对象Id 3、treeNode
 *            点击当前节点对象
 * @param nodesStr 
 *  	      禁用非当前nodesStr节点
 * @param runClick 
 *  	      加载完成后是否执行onclick方法 
 * @param check 
 *  	      是否加入复选框
 * @param expand 
 * 			  是否展开全部子节点
 * @param checkNodes 
 *  	      要选中的节点
 * @param expandRoot
 *  	      是否默认展开根节点
 */
commonTree.tree = function (data) {	
	var disabledTreeNodeStr = data.nodesStr;
	$.fn.zTree.init($("#" + data.treeId), {
		async : {
			enable : true,
			url : data.url,
			type: "post",
			dataType: "json",
			autoParam: ["id"],
			otherParam: data.oParam
		},
		check:{ enable: data.check},
		data : {
			key : {
				name:data.text
			},
			simpleData : {
				enable : true,
				idKey : data.id,
				pIdKey : data.pId,
				rootPId : data.rootPid
			}
		},
		callback : {
			onClick : data.click,
			onAsyncSuccess : function(event, treeId, treeNode, msg) {
				/**选中节点*/
				var nodes;
				if (disabledTreeNodeStr) {
					var sid;
					if(disabledTreeNodeStr.indexOf(',') > 0){
						sid = parseInt(disabledTreeNodeStr.split(',')[0]);
					}else{
						sid = parseInt(disabledTreeNodeStr);
					}
					nodes = $.fn.zTree.getZTreeObj(treeId).getNodeByParam(data.id, sid);// 根据ID取得需要选中的节点
				} else {
					nodes = $.fn.zTree.getZTreeObj(treeId).getNodeByParam("level", 0);// 根节点
				}
				disabledTreeNodes(treeId, disabledTreeNodeStr);
				$.fn.zTree.getZTreeObj(treeId).selectNode(nodes);
				if(data.expandRoot == undefined) {
					data.expandRoot = true;
				}
				if(data.expandRoot){
					/**展开第一级目录*/
					$.fn.zTree.getZTreeObj(treeId).expandNode(nodes,true,data.expand,true,true);
				}
				if(data.checkNodes){
					for (var i=0, l = data.checkNodes.length; i < l; i++) {
						$("#" + data.treeId).checkNode(data.checkNodes[i], true, true);
					}
				}
				if(data.runClick){
					data.click(event,treeId,nodes);
				}
				disabledTreeNodes(treeId, disabledTreeNodeStr);
				/*if(data.nodesStr != undefined && data.nodesStr != ''){
					data.nodesStr = disabledTreeNodeStr;
					disabledTreeNodes(treeId, data.nodesStr);
				}*/
			},
			onAsyncError : function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
				common.alert('加载数据失败', 'error');
			},
			onExpand : function(event, treeId, treeNode){
				disabledTreeNodes(treeId, disabledTreeNodeStr);
			}
			/*,
			beforeExpand: function(treeId, treeNode) {
				treeNodeId = treeNode[idKey];
			}*/
		}
	
	});
	// 禁用非指定节点
	function disabledTreeNodes(treeId, nodesStr){
		if (!nodesStr) {
			return;
		}
		var nodes = new Array();
		if(nodesStr.indexOf(',') > 0){
			nodes = nodesStr.split(',');
		}else{
			nodes[0] = parseInt(nodesStr);
		}
		var rootObj = $.fn.zTree.getZTreeObj(treeId);
		var treeNodes = rootObj.transformToArray(rootObj.getNodes());
		var liObj = $('#' + treeId + ' > li').eq(0).attr('id');
		var liIds = liObj.split('_');
		var liId = parseInt(liIds[liIds.length - 1]);
		var isExist = false;
		var index = 0, treeNodeOrgId = 0;
		for(var i=0; i < treeNodes.length; i++){
			isExist = false;
			var node = rootObj.getNodeByTId(treeId + '_' + (i + liId));
			treeNodeOrgId = (node == null) ? 0 : parseInt(node['orgId']);
			for(var j=0; j < nodes.length; j++){
				if(parseInt(nodes[j]) == treeNodeOrgId){
					isExist = true;
					break;
				}
			}
			if(!isExist){
				index = i + liId;
				var html  = $('#' + treeId).find('#' + treeId + '_' + index + '_a').html();
				if (html) {
					$('#' + treeId).find('#' + treeId + '_' + index + '_a').remove();
					$('#' + treeId).find('#' + treeId + '_' + index).append(html);
					$('#' + treeId).find('#' + treeId + '_' + index + '_span').attr('disabled','disabled');
					$('#' + treeId).find('#' + treeId + '_' + index).addClass('disabledTreeNode');
					// 有复选框的，直接干掉复选框
					$('#' + treeId).find('#' + treeId + '_' + index + '_check').remove();
				}
			}
		}
	}
	return $.fn.zTree.getZTreeObj(data.treeId);
};