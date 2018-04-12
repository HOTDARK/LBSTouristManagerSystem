var myTree= new GridTree();
	
/**
 * 主要的测试方法
 */
function test()
{                  
	
    var GridColumnType = [
                                {
					header : '节点名称',
					headerIndex : 'disName',
					columntype : {
						inputtype : 'html',
						htmlStr : '<button >$</button>',
						nameId : 'textbox'
					}
				}, {
					header : '结论',
					headerIndex : 'disName',
					columntype : {
						inputtype : 'html',
						htmlStr : '<span >$</span>',
						nameId : 'textbox'
					}
				}, {
					header : '编码',
					headerIndex : 'numb',
					width : '150px',
					columntype : {
						inputtype : 'html',
						htmlStr : '<span >$</span>',
						nameId : 'textbox'
					}
				}, {
					header : '状态',
					headerIndex : 'state',
					width : '60px',
					columntype : {
						inputtype : 'html',
						htmlStr : '<span >$</span>',
						nameId : 'textbox'
					}				
					
				}, {
					header : '出参',
					headerIndex : 'para',
					width : '60px',
					columntype : {
						inputtype : 'html',
						htmlStr : '<a class="btn btn-default btn-xs" onclick="alert(\'$\');">查看</a>',
						nameId : 'textbox'						
					}
				}];
	var content = {columnModel:GridColumnType,        
                        hidddenProperties:['disId','disName'],
                        data:[
            				{"disId":"2200","disName":"节点名称","disParent":"",numb:"2",state:"1",para:"1,2"},
                            {"disId":"2201","disName":"节点名称","disParent":"2200",numb:"2",state:"1",para:"1,3"},
                            {"disId":"2202","disName":"节点名称","disParent":"2200",numb:"2",state:"1",para:"1"},
                            {"disId":"2203","disName":"节点名称","disParent":"2200",numb:"2",state:"1",para:"1"},
                            {"disId":"2204","disName":"节点名称","disParent":"2200",numb:"1",state:"0",para:"1"},
                            {"disId":"2205","disName":"节点名称","disParent":"2200",numb:"1",state:"0",para:"1"},
                            {"disId":"2206","disName":"节点名称","disParent":"2200",numb:"1",state:"0",para:"1"},
                            {"disId":"2207","disName":"节点名称","disParent":"2200",numb:"1",state:"0",para:"1"},
                            {"disId":"2208","disName":"节点名称","disParent":"2200",numb:"1",state:"1",para:"1"},
                            {"disId":"2300","disName":"节点名称","disParent":"",numb:"1",state:"1",para:"1"},
                            {"disId":"2301","disName":"节点名称","disParent":"2300",numb:"1",state:"1",para:"1"}
                        ],
                        idColumn:'disId',//id所在的列,一般是主键(不一定要显示出来)
                        parentColumn:'disParent', //父亲列id
                        handler:[{'onclick':function(obj){
                        	//alert('选中行的区域id是：'+obj.getAttribute('disId'));
                        }}],
                        rowCountOption:3,
                        height:'100px',      
                        styleOption:1,
                        rowCount:false,//是否自动计算行数                       
                        checkOption:0,//1:出现单选按钮,2:出现多选按钮,其他:不出现选择按钮
                        allCheck:true,//如果是多选,可以选择是否出现全部选择的按钮
                        debug:true,
                        pageBar:false,       
                        pageSize:3,
                        //disabeld:true,//为true就表示表格中的文本域,多选框等为不可编辑状态
                        disableOptionColumn:'rddisbled',//用来标识指定的选择框是否禁用的属性,默认没有
                        chooesdOptionColumn:'rddisbled',//用来标识默认的就选择多选框的属性,在有多选的选按钮的情况时候有用.
                        multiChooseMode:3,
                        pageAtServer:false,//表示后台分页
                        expandAll:false,//展开全部
                      	//handleCheck:function(){alert(1);},//定义点击选择按钮的事件.就是onclick的事件处理方法
                        tableId:'testTable',//表格树的id
                        el:'newtableTree'//要进行渲染的div id
           };
	myTree.loadData(content);
	myTree.makeTable();
	
	//展开全部节点
	_$('bt3').onclick=function(){myTree.expandAll();};
	//展开第一层节点
	_$('bt4').onclick=function(){myTree.closeAll();};
}

/**
 * 双击事件,双击一行调用该方法.
 * @param {行对象} obj
 */
function doubleClickOnRow(obj)
{
	debugObjectInfo(obj);
}

/**
 * 用来查看一个对象的属性
 */
function debugObjectInfo(obj){
	traceObject(obj);
	
	function traceObject(obj){ 
		var str = '';
		if(obj.tagName&&obj.name&&obj.id)
		str="<table border='1' width='100%'><tr><td colspan='2' bgcolor='#ffff99'>traceObject 　　tag: &lt;"+obj.tagName+"&gt;　　 name = \""+obj.name+"\" 　　id = \""+obj.id+"\" </td></tr>"; 
		else{
			str="<table border='1' width='100%'>"; 
		}
		var key=[]; 
		for(var i in obj){ 
			key.push(i); 
		} 
		key.sort(); 
		for(var i=0;i<key.length;i++){ 
			var v= new String(obj[key[i]]).replace(/</g,"&lt;").replace(/>/g,"&gt;"); 
			if(typeof obj[key[i]]=='string'&&v!=null&&v!='')
				str+="<tr><td valign='top'>"+key[i]+"</td><td>"+v+"</td></tr>"; 
		} 
		str=str+"</table>"; 
		writeMsg(str); 
	} 
	function trace(v){ 
		var str="<table border='1' width='100%'><tr><td bgcolor='#ffff99'>"; 
		str+=String(v).replace(/</g,"&lt;").replace(/>/g,"&gt;"); 
		str+="</td></tr></table>"; 
		writeMsg(str); 
	} 
	function writeMsg(s){ 
		traceWin=window.open("","traceWindow","height=600, width=800,scrollbars=yes"); 
		traceWin.document.write(s); 
	} 
}

function showHtml()
{
	jQuery('#ans').text(jQuery('#newtableTree').html());
}

function setGridTreeDisabled(v){
	myTree.setDisabled(v);
}

function showChoosed()
{
	var ans = getAllCheckValue();
	if(ans!='')
		alert(ans);
	else
		alert('没有选择');
}

function openAll()
{
	myTree.expandAll();
}

function closeAll()
{
	myTree.closeAll();
}