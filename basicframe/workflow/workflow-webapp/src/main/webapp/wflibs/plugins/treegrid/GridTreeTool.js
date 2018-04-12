/**
 * ���ڱ������һЩ���÷�������������������js��
 */
 
 
/**
 * ��Ĭ�ϵ��Ҽ�����
 * @return {Boolean}
 */
document.oncontextmenu = function(event) {
    return false;
}

/**
 * ����ҳ��ĵ����¼������ز˵���
 */
document.onclick = function() {
	var mndiv = _$('_menuDiv');
	if(mndiv)
        mndiv.style.visibility = "hidden";
}

/**
 * ʵ���������
 * @param {} ary
 * @return {}
 */
function toArray(ary) {
	var result = new Array(ary.length);
	for (var i = 0; i < ary.length; i++) {
		result[i] = ary[i]
	}
	return result;
}

/**
 * ����bind����Ϊfunction�Զ����ٶ���Զ���Ĳ����������µĺ������������¼��İ󶨷����д��ݲ�����������
 * @return {}
 */
Function.prototype.bind = function() {
	var args = toArray(arguments);
	var owner = args.shift();
	var _this = this;
	return function(owner) {
		return _this.apply(owner, args.concat(toArray(arguments)));
	}
}

/**
 * �����ʽ�ļ�
 * csspath:��ʽ�ļ��ĵ�ַ
 */
function importcss(csspath) {
	var scripts = document.getElementsByTagName("link");
	for (var i = 0; i < scripts.length; i++) {
		if (csspath == scripts[i].getAttribute("href")) {
			return;
		}
	}
	// ��ie��.
	if (isIE)
		document.createStyleSheet(csspath);
	// �ڻ����
	else {
		var headElement = document.getElementsByTagName("head")[0];
		var tempStyleElement = document.createElement('link');// w3c
		tempStyleElement.setAttribute("rel", "stylesheet");
		tempStyleElement.setAttribute("type", "text/css");
		tempStyleElement.setAttribute("href", csspath);
		headElement.appendChild(tempStyleElement);
	}
}


/**
 * ��json��map����ת��Ϊ�����е�hashMap����
 * @param {json��map����} jsonMap
 */
function jsonMapToJsHashMap(jsonMap){
	var mapObj = new HashMap();
	for(var obj in jsonMap){
		mapObj.put(obj,jsonMap[obj]);
	}
	return mapObj;
}


/**
 * ��һ�������м�ȥ����һ������ arr1:����1 arr2:����2 ����:arr1 - arr2��������
 */
function removeArrayFromOtherArray(arr1, arr2) {
	var tempArr = [];
	var bingo = [];
	var len1 = arr2.length;
	for (var ii = 0; ii < len1; ii++) {
		bingo.push(findInArray(arr1, arr2[ii]));
	}
	var len2 = arr1.length;
	for (var ii = 0; ii < len2; ii++) {
		if (findInArray(bingo, ii) == -1) {
			tempArr.push(arr1[ii]);
		}
	}
	return tempArr;
}

/**
 * ��һ����������ָ����Ԫ�� 
 * arr:������� 
 * obj:Ҫ���ҵĶ���
 * ����ֵ:����ҵ��ͷ��������е�λ��(��0��ʼ),����ͷ���-1
 */
function findInArray(arr, obj) {
	var ans = -1;
	var len = arr.length;
	for (var i = 0; i < len; i++) {		
		if(arr[i]==obj){
			ans = i;
			return ans;
		}
	}
	return -1;
}


/**
 * ���ƶ����ѡ��ť��ѡ��״̬
 * checkName:��ѡ��ť��name
 * v:���õ�ֵ
 */
function _checkedAll(checkName,v)
{
	var objs = document.getElementsByName(checkName);	
	var len = objs.length;
	if(isIE){
		for(var i=0;i<len;i++)
		{
			//�ų���Щ�ж������Ƿ��̨ѡ��Ľڵ�.
			if(objs[i].userSetDisabled==null||objs[i].userSetDisabled!='disabled')
				objs[i].checked = v;
			
		}
	} else {
		for (var i = 0; i < len; i++) {
			// �ų���Щ�ж������Ƿ��̨ѡ��Ľڵ�.
			if (objs[i].getAttribute('userSetDisabled') == null
					|| objs[i].getAttribute('userSetDisabled') != 'disabled')
				objs[i].checked = v;

		}
	}
}

/**
 * ����ڻ�����治����ʹ��innerText������
 */
function appendText(node, txt) {
	var nd = document.createTextNode(txt);
	node.appendChild(nd);
}

/**
 * ɾ��ָ�������ָ����ʽ��
 * @param {����} obj
 * @param {��ʽ��} cssName
 */
function rmCss(obj,cssName)
{
	if(obj){
		if(includeClassInObj(obj,cssName)){
			//��������ɾ����ʽ�����Ѿ����ڵ������ʽ��Ȼ��ϲ��������ϵĿո�Ϊһ���ո�
			obj.className = obj.className.replace(cssName, '').replace(/\s{2,}/g,' ');
		}
	}
}

/**
 * �ж�ָ�������Ƿ���ָ������ʽ
 * @param {����} obj
 * @param {��ʽ��} cssName
 * @return {Boolean}
 */
function includeClassInObj(obj, cssName) {
	if (!obj.className) { return false; }
	return obj.className.indexOf(cssName)!= -1;	
}

/**
 * Ϊָ���������ָ����ʽ��
 * @param {����} obj
 * @param {��ʽ��} cssName
 */
function adCss(obj,cssName)
{
	if(obj){
		if (includeClassInObj(obj, cssName)) {
		 	return ; 
		}
		if (typeof obj.className!='undefined') {
			obj.className = obj.className +' '+cssName;
		} else {
			obj.className = cssName;
		}
	}
}

/**
 * �ж�һ��Ԫ���Ƿ��Զ�����Ϊ������.
 * ���û��userSetDisabled���Ի����������Ϊdisabled.�ͷ���true,
 * ���򷵻�false.
 * @param {Ԫ�ض���} o
 */
function _notBindDisabled(o){
	if(o.userSetDisabled==null||o.userSetDisabled!='disabled'){
		return 1;
	}
	return 0;
}

/**
 * ����ָ��Ԫ�ز����û��߿���
 * obj:Ԫ��
 * val:������(1)���߿���(0)
 */
function disableDom(obj,val)
{
	if(obj)
		obj.disabled = val;
}

/**
 * ����ָ��name��Ԫ���Ƿ����
 * @param {name����} domName
 * @param {������(1)���߿���(0)} val
 */
function disableDomByName(domName,val)
{
	var obj = document.getElementsByName(domName);
	var len = obj.length;
	if(len>0){		
		if(isIE){
				for(var i=0;i<len;i++){
					if(_notBindDisabled(obj[i]))
						obj[i].disabled = val;
				}
		}else{
			for(var i=0;i<len;i++){
				if(obj[i].getAttribute('userSetDisabled')==null||obj[i].getAttribute('userSetDisabled')!='disabled')
					obj[i].disabled = val;
			}
		}
	}
}


/**
 * �Ƴ�ָ���ڵ��������ʾ
 */
function removeHightLight(obj)
{
	obj.className = obj.className.replace('highlight','').replace(/\s{2,}/g,' ');
}

/**
 * ����ָ���ڵ��������ʾ
 * @param {�ж���} obj
 */
function addHightLight(obj)
{
	obj.className = obj.className+' highlight';
}


/**
 * ��ֹjs�¼���ð��
 */
function stopBubble(e){ 
	 //һ��������������¼��� 
     if(!isIE){ 
     	e.preventDefault();
     	e.stopPropagation();
     }else{ 
         //IEȡ��ð���¼� 
         window.event.cancelBubble = true; 
     } 
 }
 
/**
 * �õ�ѡ���id�е�id
 */
function getAllCheckValue(){
	var chks = document.getElementsByName('_chks');
	var ans = '';
	var len = chks.length;
	for(i=0;i<len;i++)
	{
		if(chks[i].checked)
		{
			ans += chks[i].value+',';
		}
	}
	ans = ans.substring(0,ans.length-1);  
	return ans;
}

/**
 * ��̬����һ��radio�ķ���
 * @param {�������ϼ�domԪ��} el
 * @param {�Ƿ����} dis
 * @param {��ʽ�ı�} sty
 * @param {��������} name
 * @param {ֵ����} val
 * @param {�����¼�} click
 * @param {innerTextֵ} innertext
 * @param {��ʽ��} cssname
 * @param {�Ƿ�Ĭ��ѡ��} chk
 */
function createRadio(el,dis,sty,name,val,click,innertext,cssname,chk){
	var rd ; 
	//����ķ�ʽ��ie������ã����Ҵ���������radio���Ա�ѡ�񡣶���hh���С�
	if(isIE)
	{
		rd = document.createElement(["<input type='radio' name='",name,"' style='",sty,"' value='",val
			,"' ",dis," class='",cssname,"' userSetDisabled='",dis,"' ",chk,">"].join(''));
	}else{
		rd = document.createElement("input");
		rd.setAttribute('type','radio');
		rd.disabled = dis;
		rd.checked = chk;
		rd.setAttribute('style',sty);
		rd.setAttribute('name',name);
		rd.setAttribute('userSetDisabled',dis);
		rd.setAttribute('value',val);
	}
	if(click)
		rd.onclick = click;
	el.appendChild(rd);
	if(innertext!='')
		el.appendChild(document.createTextNode(innertext));
}

/**
 * ��̬����һ����ѡ��ť
 * @param {�������ϼ�domԪ��} el
 * @param {�Ƿ����} dis
 * @param {��ʽ�ı�} sty
 * @param {��������} name
 * @param {ֵ����} val
 * @param {�����¼�} click
 * @param {innerTextֵ} innertext
 * @param {��ʽ��} cssname
 * @param {�Ƿ�Ĭ��ѡ��} chk
 */
function createCheckbox(el,dis,sty,name,val,click,innertext,cssname,chk){
	var rd ; 
	//����ķ�ʽ��ie������ã����Ҵ���������checkbox���Ա�ѡ�񡣶���hh���С�
	if(isIE)
	{
		rd = document.createElement(["<input type='checkbox' name='",name,"' style='",sty,"' value='",val
			,"' ",dis," class='",cssname,"' userSetDisabled='",dis,"' ",chk,">"].join(''));
	}else{
		rd = document.createElement("input");
		rd.setAttribute('type','checkbox');
		rd.disabled = dis;
		rd.checked = chk;
		rd.setAttribute('style',sty);
		rd.setAttribute('name',name);
		rd.setAttribute('userSetDisabled',dis);
		rd.setAttribute('value',val);
	}
	if(click)
		rd.onclick = click;
	el.appendChild(rd);
	if(innertext!='')
		el.appendChild(document.createTextNode(innertext));
}

/**
 * ��̬����hidden��
 * @param {idֵ} id
 * @param {����ֵ} name
 * @param {val����} val
 * @return {}
 */
function createHidden(id,name,val){
	var hid;
	if(isIE){
		hid = document.createElement(["<input type='hidden' id='",id,"' name='",name,"' value='",val,"'>"].join(''));
	}else{
		hid = document.createElement("hidden");
		hid.setAttribute('id',id);
		hid.setAttribute('name',name);
		hid.setAttribute('value',val);
	}
	return hid;
}
/**
 * ����һ��ImgԪ��
 * @param {ͼƬ��Դ} imgsrc
 */
function createImg(imgsrc)
{
	var node = document.createElement('img');
	node.setAttribute('src',imgsrc);
	return node;
}

/**
 * �õ�ָ����Ԫ��id��Ӧ�Ľڵ�.
 * @param {} id
 */
function _$(id){
	return document.getElementById(id);
}

Function.prototype.attachAfter = function(closure,functionOwner) {
	var _this = this;
	return function() {
		this.apply(functionOwner,arguments);
		closure();
	}
}
function attachEvent (obj , eventName,handler) {
	obj[eventName]=(obj[eventName]||function(){}).attachAfter(handler,obj);
}