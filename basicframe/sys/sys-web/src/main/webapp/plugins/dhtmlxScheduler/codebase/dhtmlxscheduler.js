/*
@license
dhtmlxScheduler v.4.3.1 

This software is covered by GPL license. You also can obtain Commercial or Enterprise license to use it in non-GPL project - please contact sales@dhtmlx.com. Usage without proper license is prohibited.

(c) Dinamenta, UAB.
*/
function dtmlXMLLoaderObject(e, t, i, s) {
    return this.xmlDoc = "", "undefined" != typeof i ? this.async = i : this.async = !0, this.onloadAction = e || null, this.mainObject = t || null, this.waitCall = null, this.rSeed = s || !1, this
}

function callerFunction(e, t) {
    return this.handler = function (i) {
        return i || (i = window.event), e(i, t), !0
    }, this.handler
}

function getAbsoluteLeft(e) {
    return getOffset(e).left
}

function getAbsoluteTop(e) {
    return getOffset(e).top
}

function getOffsetSum(e) {
    for (var t = 0, i = 0; e;) t += parseInt(e.offsetTop), i += parseInt(e.offsetLeft),
        e = e.offsetParent;
    return {
        top: t,
        left: i
    }
}

function getOffsetRect(e) {
    var t = e.getBoundingClientRect(),
        i = document.body,
        s = document.documentElement,
        a = window.pageYOffset || s.scrollTop || i.scrollTop,
        n = window.pageXOffset || s.scrollLeft || i.scrollLeft,
        r = s.clientTop || i.clientTop || 0,
        d = s.clientLeft || i.clientLeft || 0,
        o = t.top + a - r,
        l = t.left + n - d;
    return {
        top: Math.round(o),
        left: Math.round(l)
    }
}

function getOffset(e) {
    return e.getBoundingClientRect ? getOffsetRect(e) : getOffsetSum(e)
}

function convertStringToBoolean(e) {
    switch ("string" == typeof e && (e = e.toLowerCase()),
        e) {
    case "1":
    case "true":
    case "yes":
    case "y":
    case 1:
    case !0:
        return !0;
    default:
        return !1
    }
}

function getUrlSymbol(e) {
    return -1 != e.indexOf("?") ? "&" : "?"
}

function dhtmlDragAndDropObject() {
    return window.dhtmlDragAndDrop ? window.dhtmlDragAndDrop : (this.lastLanding = 0, this.dragNode = 0, this.dragStartNode = 0, this.dragStartObject = 0, this.tempDOMU = null, this.tempDOMM = null, this.waitDrag = 0, window.dhtmlDragAndDrop = this, this)
}

function _dhtmlxError(e, t, i) {
    return this.catches || (this.catches = []), this
}

function dhtmlXHeir(e, t) {
    for (var i in t) "function" == typeof t[i] && (e[i] = t[i]);

    return e
}

function dataProcessor(e) {
    return this.serverProcessor = e, this.action_param = "!nativeeditor_status", this.object = null, this.updatedRows = [], this.autoUpdate = !0, this.updateMode = "cell", this._tMode = "GET", this.post_delim = "_", this._waitMode = 0, this._in_progress = {}, this._invalid = {}, this.mandatoryFields = [], this.messages = [], this.styles = {
        updated: "font-weight:bold;",
        inserted: "font-weight:bold;",
        deleted: "text-decoration : line-through;",
        invalid: "background-color:FFE0E0;",
        invalid_cell: "border-bottom:2px solid red;",
        error: "color:red;",
        clear: "font-weight:normal;text-decoration:none;"
    }, this.enableUTFencoding(!0), dhtmlxEventable(this), this
}
window.dhtmlXScheduler = window.scheduler = {
        version: "4.3.1"
    }, window.dhtmlx || (dhtmlx = function (e) {
        for (var t in e) dhtmlx[t] = e[t];
        return dhtmlx
    }), dhtmlx.extend_api = function (e, t, i) {
        var s = window[e];
        s && (window[e] = function (e) {
            var i;
            if (e && "object" == typeof e && !e.tagName) {
                i = s.apply(this, t._init ? t._init(e) : arguments);
                for (var a in dhtmlx) t[a] && this[t[a]](dhtmlx[a]);
                for (var a in e) t[a] ? this[t[a]](e[a]) : 0 === a.indexOf("on") && this.attachEvent(a, e[a]);

            } else i = s.apply(this, arguments);
            return t._patch && t._patch(this), i || this
        }, window[e].prototype = s.prototype, i && dhtmlXHeir(window[e].prototype, i))
    }, dhtmlxAjax = {
        get: function (e, t) {
            var i = new dtmlXMLLoaderObject(!0);
            return i.async = arguments.length < 3, i.waitCall = t, i.loadXML(e), i
        }, post: function (e, t, i) {
            var s = new dtmlXMLLoaderObject(!0);
            return s.async = arguments.length < 4, s.waitCall = i, s.loadXML(e, !0, t), s
        }, getSync: function (e) {
            return this.get(e, null, !0)
        }, postSync: function (e, t) {
            return this.post(e, t, null, !0)
        }
    }, dtmlXMLLoaderObject.count = 0,
    dtmlXMLLoaderObject.prototype.waitLoadFunction = function (e) {
        var t = !0;
        return this.check = function () {
            if (e && e.onloadAction && (!e.xmlDoc.readyState || 4 == e.xmlDoc.readyState)) {
                if (!t) return;
                t = !1, dtmlXMLLoaderObject.count++, "function" == typeof e.onloadAction && e.onloadAction(e.mainObject, null, null, null, e), e.waitCall && (e.waitCall.call(this, e), e.waitCall = null)
            }
        }, this.check
    }, dtmlXMLLoaderObject.prototype.getXMLTopNode = function (e, t) {
        var i;
        if (this.xmlDoc.responseXML) {
            var s = this.xmlDoc.responseXML.getElementsByTagName(e);

            if (0 === s.length && -1 != e.indexOf(":")) var s = this.xmlDoc.responseXML.getElementsByTagName(e.split(":")[1]);
            i = s[0]
        } else i = this.xmlDoc.documentElement; if (i) return this._retry = !1, i;
        if (!this._retry && _isIE) {
            this._retry = !0;
            var t = this.xmlDoc;
            return this.loadXMLString(this.xmlDoc.responseText.replace(/^[\s]+/, ""), !0), this.getXMLTopNode(e, t)
        }
        return dhtmlxError.throwError("LoadXML", "Incorrect XML", [t || this.xmlDoc, this.mainObject]), document.createElement("DIV")
    }, dtmlXMLLoaderObject.prototype.loadXMLString = function (e, t) {
        if (_isIE) this.xmlDoc = new ActiveXObject("Microsoft.XMLDOM"), this.xmlDoc.async = this.async, this.xmlDoc.onreadystatechange = function () {}, this.xmlDoc.loadXML(e);
        else {
            var i = new DOMParser;
            this.xmlDoc = i.parseFromString(e, "text/xml")
        }
        t || (this.onloadAction && this.onloadAction(this.mainObject, null, null, null, this), this.waitCall && (this.waitCall(), this.waitCall = null))
    }, dtmlXMLLoaderObject.prototype.loadXML = function (e, t, i, s) {
        this.rSeed && (e += (-1 != e.indexOf("?") ? "&" : "?") + "a_dhx_rSeed=" + (new Date).valueOf()), this.filePath = e, !_isIE && window.XMLHttpRequest ? this.xmlDoc = new XMLHttpRequest : this.xmlDoc = new ActiveXObject("Microsoft.XMLHTTP"), this.async && (this.xmlDoc.onreadystatechange = new this.waitLoadFunction(this)), "string" == typeof t ? this.xmlDoc.open(t, e, this.async) : this.xmlDoc.open(t ? "POST" : "GET", e, this.async), s ? (this.xmlDoc.setRequestHeader("User-Agent", "dhtmlxRPC v0.1 (" + navigator.userAgent + ")"), this.xmlDoc.setRequestHeader("Content-type", "text/xml")) : t && this.xmlDoc.setRequestHeader("Content-type", "application/x-www-form-urlencoded"),
            this.xmlDoc.setRequestHeader("X-Requested-With", "XMLHttpRequest"), this.xmlDoc.send(null || i), this.async || new this.waitLoadFunction(this)()
    }, dtmlXMLLoaderObject.prototype.destructor = function () {
        return this._filterXPath = null, this._getAllNamedChilds = null, this._retry = null, this.async = null, this.rSeed = null, this.filePath = null, this.onloadAction = null, this.mainObject = null, this.xmlDoc = null, this.doXPath = null, this.doXPathOpera = null, this.doXSLTransToObject = null, this.doXSLTransToString = null, this.loadXML = null, this.loadXMLString = null,
            this.doSerialization = null, this.xmlNodeToJSON = null, this.getXMLTopNode = null, this.setXSLParamValue = null, null
    }, dtmlXMLLoaderObject.prototype.xmlNodeToJSON = function (e) {
        for (var t = {}, i = 0; i < e.attributes.length; i++) t[e.attributes[i].name] = e.attributes[i].value;
        t._tagvalue = e.firstChild ? e.firstChild.nodeValue : "";
        for (var i = 0; i < e.childNodes.length; i++) {
            var s = e.childNodes[i].tagName;
            s && (t[s] || (t[s] = []), t[s].push(this.xmlNodeToJSON(e.childNodes[i])))
        }
        return t
    }, dhtmlDragAndDropObject.prototype.removeDraggableItem = function (e) {
        e.onmousedown = null, e.dragStarter = null, e.dragLanding = null
    }, dhtmlDragAndDropObject.prototype.addDraggableItem = function (e, t) {
        e.onmousedown = this.preCreateDragCopy, e.dragStarter = t, this.addDragLanding(e, t)
    }, dhtmlDragAndDropObject.prototype.addDragLanding = function (e, t) {
        e.dragLanding = t
    }, dhtmlDragAndDropObject.prototype.preCreateDragCopy = function (e) {
        return !e && !window.event || 2 != (e || event).button ? window.dhtmlDragAndDrop.waitDrag ? (window.dhtmlDragAndDrop.waitDrag = 0, document.body.onmouseup = window.dhtmlDragAndDrop.tempDOMU,
            document.body.onmousemove = window.dhtmlDragAndDrop.tempDOMM, !1) : (window.dhtmlDragAndDrop.dragNode && window.dhtmlDragAndDrop.stopDrag(e), window.dhtmlDragAndDrop.waitDrag = 1, window.dhtmlDragAndDrop.tempDOMU = document.body.onmouseup, window.dhtmlDragAndDrop.tempDOMM = document.body.onmousemove, window.dhtmlDragAndDrop.dragStartNode = this, window.dhtmlDragAndDrop.dragStartObject = this.dragStarter, document.body.onmouseup = window.dhtmlDragAndDrop.preCreateDragCopy, document.body.onmousemove = window.dhtmlDragAndDrop.callDrag,
            window.dhtmlDragAndDrop.downtime = (new Date).valueOf(), e && e.preventDefault ? (e.preventDefault(), !1) : !1) : void 0
    }, dhtmlDragAndDropObject.prototype.callDrag = function (e) {
        e || (e = window.event);
        var t = window.dhtmlDragAndDrop;
        if (!((new Date).valueOf() - t.downtime < 100)) {
            if (!t.dragNode) {
                if (!t.waitDrag) return t.stopDrag(e, !0);
                if (t.dragNode = t.dragStartObject._createDragNode(t.dragStartNode, e), !t.dragNode) return t.stopDrag();
                t.dragNode.onselectstart = function () {
                        return !1
                    }, t.gldragNode = t.dragNode, document.body.appendChild(t.dragNode),
                    document.body.onmouseup = t.stopDrag, t.waitDrag = 0, t.dragNode.pWindow = window, t.initFrameRoute()
            }
            if (t.dragNode.parentNode != window.document.body && t.gldragNode) {
                var i = t.gldragNode;
                t.gldragNode.old && (i = t.gldragNode.old), i.parentNode.removeChild(i);
                var s = t.dragNode.pWindow;
                if (i.pWindow && i.pWindow.dhtmlDragAndDrop.lastLanding && i.pWindow.dhtmlDragAndDrop.lastLanding.dragLanding._dragOut(i.pWindow.dhtmlDragAndDrop.lastLanding), _isIE) {
                    var a = document.createElement("Div");
                    a.innerHTML = t.dragNode.outerHTML, t.dragNode = a.childNodes[0];

                } else t.dragNode = t.dragNode.cloneNode(!0);
                t.dragNode.pWindow = window, t.gldragNode.old = t.dragNode, document.body.appendChild(t.dragNode), s.dhtmlDragAndDrop.dragNode = t.dragNode
            }
            t.dragNode.style.left = e.clientX + 15 + (t.fx ? -1 * t.fx : 0) + (document.body.scrollLeft || document.documentElement.scrollLeft) + "px", t.dragNode.style.top = e.clientY + 3 + (t.fy ? -1 * t.fy : 0) + (document.body.scrollTop || document.documentElement.scrollTop) + "px";
            var n;
            n = e.srcElement ? e.srcElement : e.target, t.checkLanding(n, e)
        }
    }, dhtmlDragAndDropObject.prototype.calculateFramePosition = function (e) {
        if (window.name) {
            for (var t = parent.frames[window.name].frameElement.offsetParent, i = 0, s = 0; t;) i += t.offsetLeft, s += t.offsetTop, t = t.offsetParent;
            if (parent.dhtmlDragAndDrop) {
                var a = parent.dhtmlDragAndDrop.calculateFramePosition(1);
                i += 1 * a.split("_")[0], s += 1 * a.split("_")[1]
            }
            if (e) return i + "_" + s;
            this.fx = i, this.fy = s
        }
        return "0_0"
    }, dhtmlDragAndDropObject.prototype.checkLanding = function (e, t) {
        e && e.dragLanding ? (this.lastLanding && this.lastLanding.dragLanding._dragOut(this.lastLanding), this.lastLanding = e, this.lastLanding = this.lastLanding.dragLanding._dragIn(this.lastLanding, this.dragStartNode, t.clientX, t.clientY, t),
            this.lastLanding_scr = _isIE ? t.srcElement : t.target) : e && "BODY" != e.tagName ? this.checkLanding(e.parentNode, t) : (this.lastLanding && this.lastLanding.dragLanding._dragOut(this.lastLanding, t.clientX, t.clientY, t), this.lastLanding = 0, this._onNotFound && this._onNotFound())
    }, dhtmlDragAndDropObject.prototype.stopDrag = function (e, t) {
        var i = window.dhtmlDragAndDrop;
        if (!t) {
            i.stopFrameRoute();
            var s = i.lastLanding;
            i.lastLanding = null, s && s.dragLanding._drag(i.dragStartNode, i.dragStartObject, s, _isIE ? event.srcElement : e.target);

        }
        i.lastLanding = null, i.dragNode && i.dragNode.parentNode == document.body && i.dragNode.parentNode.removeChild(i.dragNode), i.dragNode = 0, i.gldragNode = 0, i.fx = 0, i.fy = 0, i.dragStartNode = 0, i.dragStartObject = 0, document.body.onmouseup = i.tempDOMU, document.body.onmousemove = i.tempDOMM, i.tempDOMU = null, i.tempDOMM = null, i.waitDrag = 0
    }, dhtmlDragAndDropObject.prototype.stopFrameRoute = function (e) {
        e && window.dhtmlDragAndDrop.stopDrag(1, 1);
        for (var t = 0; t < window.frames.length; t++) try {
            window.frames[t] != e && window.frames[t].dhtmlDragAndDrop && window.frames[t].dhtmlDragAndDrop.stopFrameRoute(window);

        } catch (i) {}
        try {
            parent.dhtmlDragAndDrop && parent != window && parent != e && parent.dhtmlDragAndDrop.stopFrameRoute(window)
        } catch (i) {}
    }, dhtmlDragAndDropObject.prototype.initFrameRoute = function (e, t) {
        e && (window.dhtmlDragAndDrop.preCreateDragCopy(), window.dhtmlDragAndDrop.dragStartNode = e.dhtmlDragAndDrop.dragStartNode, window.dhtmlDragAndDrop.dragStartObject = e.dhtmlDragAndDrop.dragStartObject, window.dhtmlDragAndDrop.dragNode = e.dhtmlDragAndDrop.dragNode, window.dhtmlDragAndDrop.gldragNode = e.dhtmlDragAndDrop.dragNode,
            window.document.body.onmouseup = window.dhtmlDragAndDrop.stopDrag, window.waitDrag = 0, !_isIE && t && (!_isFF || _FFrv < 1.8) && window.dhtmlDragAndDrop.calculateFramePosition());
        try {
            parent.dhtmlDragAndDrop && parent != window && parent != e && parent.dhtmlDragAndDrop.initFrameRoute(window)
        } catch (i) {}
        for (var s = 0; s < window.frames.length; s++) try {
            window.frames[s] != e && window.frames[s].dhtmlDragAndDrop && window.frames[s].dhtmlDragAndDrop.initFrameRoute(window, !e || t ? 1 : 0)
        } catch (i) {}
    }, _isFF = !1, _isIE = !1, _isOpera = !1, _isKHTML = !1, _isMacOS = !1,
    _isChrome = !1, _FFrv = !1, _KHTMLrv = !1, _OperaRv = !1, -1 != navigator.userAgent.indexOf("Macintosh") && (_isMacOS = !0), navigator.userAgent.toLowerCase().indexOf("chrome") > -1 && (_isChrome = !0), -1 != navigator.userAgent.indexOf("Safari") || -1 != navigator.userAgent.indexOf("Konqueror") ? (_KHTMLrv = parseFloat(navigator.userAgent.substr(navigator.userAgent.indexOf("Safari") + 7, 5)), _KHTMLrv > 525 ? (_isFF = !0, _FFrv = 1.9) : _isKHTML = !0) : -1 != navigator.userAgent.indexOf("Opera") ? (_isOpera = !0, _OperaRv = parseFloat(navigator.userAgent.substr(navigator.userAgent.indexOf("Opera") + 6, 3))) : -1 != navigator.appName.indexOf("Microsoft") ? (_isIE = !0, -1 == navigator.appVersion.indexOf("MSIE 8.0") && -1 == navigator.appVersion.indexOf("MSIE 9.0") && -1 == navigator.appVersion.indexOf("MSIE 10.0") || "BackCompat" == document.compatMode || (_isIE = 8)) : "Netscape" == navigator.appName && -1 != navigator.userAgent.indexOf("Trident") ? _isIE = 8 : (_isFF = !0, _FFrv = parseFloat(navigator.userAgent.split("rv:")[1])), dtmlXMLLoaderObject.prototype.doXPath = function (e, t, i, s) {
        if (_isKHTML || !_isIE && !window.XPathResult) return this.doXPathOpera(e, t);
        if (_isIE) return t || (t = this.xmlDoc.nodeName ? this.xmlDoc : this.xmlDoc.responseXML),
            t || dhtmlxError.throwError("LoadXML", "Incorrect XML", [t || this.xmlDoc, this.mainObject]), i && t.setProperty("SelectionNamespaces", "xmlns:xsl='" + i + "'"), "single" == s ? t.selectSingleNode(e) : t.selectNodes(e) || new Array(0);
        var a = t;
        t || (t = this.xmlDoc.nodeName ? this.xmlDoc : this.xmlDoc.responseXML), t || dhtmlxError.throwError("LoadXML", "Incorrect XML", [t || this.xmlDoc, this.mainObject]), -1 != t.nodeName.indexOf("document") ? a = t : (a = t, t = t.ownerDocument);
        var n = XPathResult.ANY_TYPE;
        "single" == s && (n = XPathResult.FIRST_ORDERED_NODE_TYPE);

        var r = [],
            d = t.evaluate(e, a, function (e) {
                return i
            }, n, null);
        if (n == XPathResult.FIRST_ORDERED_NODE_TYPE) return d.singleNodeValue;
        for (var o = d.iterateNext(); o;) r[r.length] = o, o = d.iterateNext();
        return r
    }, _dhtmlxError.prototype.catchError = function (e, t) {
        this.catches[e] = t
    }, _dhtmlxError.prototype.throwError = function (e, t, i) {
        return this.catches[e] ? this.catches[e](e, t, i) : this.catches.ALL ? this.catches.ALL(e, t, i) : (window.alert("Error type: " + arguments[0] + "\nDescription: " + arguments[1]), null)
    }, window.dhtmlxError = new _dhtmlxError,
    dtmlXMLLoaderObject.prototype.doXPathOpera = function (e, t) {
        var i = e.replace(/[\/]+/gi, "/").split("/"),
            s = null,
            a = 1;
        if (!i.length) return [];
        if ("." == i[0]) s = [t];
        else {
            if ("" !== i[0]) return [];
            s = (this.xmlDoc.responseXML || this.xmlDoc).getElementsByTagName(i[a].replace(/\[[^\]]*\]/g, "")), a++
        }
        for (a; a < i.length; a++) s = this._getAllNamedChilds(s, i[a]);
        return -1 != i[a - 1].indexOf("[") && (s = this._filterXPath(s, i[a - 1])), s
    }, dtmlXMLLoaderObject.prototype._filterXPath = function (e, t) {
        for (var i = [], t = t.replace(/[^\[]*\[\@/g, "").replace(/[\[\]\@]*/g, ""), s = 0; s < e.length; s++) e[s].getAttribute(t) && (i[i.length] = e[s]);

        return i
    }, dtmlXMLLoaderObject.prototype._getAllNamedChilds = function (e, t) {
        var i = [];
        _isKHTML && (t = t.toUpperCase());
        for (var s = 0; s < e.length; s++)
            for (var a = 0; a < e[s].childNodes.length; a++) _isKHTML ? e[s].childNodes[a].tagName && e[s].childNodes[a].tagName.toUpperCase() == t && (i[i.length] = e[s].childNodes[a]) : e[s].childNodes[a].tagName == t && (i[i.length] = e[s].childNodes[a]);
        return i
    }, "undefined" == typeof window.dhtmlxEvent && (window.dhtmlxEvent = function (e, t, i) {
        e.addEventListener ? e.addEventListener(t, i, !1) : e.attachEvent && e.attachEvent("on" + t, i);

    }), dtmlXMLLoaderObject.prototype.xslDoc = null, dtmlXMLLoaderObject.prototype.setXSLParamValue = function (e, t, i) {
        i || (i = this.xslDoc), i.responseXML && (i = i.responseXML);
        var s = this.doXPath("/xsl:stylesheet/xsl:variable[@name='" + e + "']", i, "http://www.w3.org/1999/XSL/Transform", "single");
        s && (s.firstChild.nodeValue = t)
    }, dtmlXMLLoaderObject.prototype.doXSLTransToObject = function (e, t) {
        e || (e = this.xslDoc), e.responseXML && (e = e.responseXML), t || (t = this.xmlDoc), t.responseXML && (t = t.responseXML);
        var i;
        if (_isIE) {
            i = new ActiveXObject("Msxml2.DOMDocument.3.0");

            try {
                t.transformNodeToObject(e, i)
            } catch (s) {
                i = t.transformNode(e)
            }
        } else this.XSLProcessor || (this.XSLProcessor = new XSLTProcessor, this.XSLProcessor.importStylesheet(e)), i = this.XSLProcessor.transformToDocument(t);
        return i
    }, dtmlXMLLoaderObject.prototype.doXSLTransToString = function (e, t) {
        var i = this.doXSLTransToObject(e, t);
        return "string" == typeof i ? i : this.doSerialization(i)
    }, dtmlXMLLoaderObject.prototype.doSerialization = function (e) {
        if (e || (e = this.xmlDoc), e.responseXML && (e = e.responseXML), _isIE) return e.xml;
        var t = new XMLSerializer;

        return t.serializeToString(e)
    }, dhtmlxEventable = function (obj) {
        obj.attachEvent = function (e, t, i) {
            return e = "ev_" + e.toLowerCase(), this[e] || (this[e] = new this.eventCatcher(i || this)), e + ":" + this[e].addEvent(t)
        }, obj.callEvent = function (e, t) {
            return e = "ev_" + e.toLowerCase(), this[e] ? this[e].apply(this, t) : !0
        }, obj.checkEvent = function (e) {
            return !!this["ev_" + e.toLowerCase()]
        }, obj.eventCatcher = function (obj) {
            var dhx_catch = [],
                z = function () {
                    for (var e = !0, t = 0; t < dhx_catch.length; t++)
                        if (dhx_catch[t]) {
                            var i = dhx_catch[t].apply(obj, arguments);

                            e = e && i
                        }
                    return e
                };
            return z.addEvent = function (ev) {
                return "function" != typeof ev && (ev = eval(ev)), ev ? dhx_catch.push(ev) - 1 : !1
            }, z.removeEvent = function (e) {
                dhx_catch[e] = null
            }, z
        }, obj.detachEvent = function (e) {
            if (e) {
                var t = e.split(":");
                this[t[0]].removeEvent(t[1])
            }
        }, obj.detachAllEvents = function () {
            for (var e in this) 0 === e.indexOf("ev_") && (this.detachEvent(e), this[e] = null)
        }, obj = null
    }, window.dhtmlx || (window.dhtmlx = {}),
    function () {
        function e(e, t) {
            var s = e.callback;
            i(!1), e.box.parentNode.removeChild(e.box), c = e.box = null,
                s && s(t)
        }

        function t(t) {
            if (c) {
                t = t || event;
                var i = t.which || event.keyCode;
                return dhtmlx.message.keyboard && ((13 == i || 32 == i) && e(c, !0), 27 == i && e(c, !1)), t.preventDefault && t.preventDefault(), !(t.cancelBubble = !0)
            }
        }

        function i(e) {
            i.cover || (i.cover = document.createElement("DIV"), i.cover.onkeydown = t, i.cover.className = "dhx_modal_cover", document.body.appendChild(i.cover));
            document.body.scrollHeight;
            i.cover.style.display = e ? "inline-block" : "none"
        }

        function s(e, t, i) {
            var s = i ? i : e || "",
                a = "dhtmlx_" + s.toLowerCase().replace(/ /g, "_") + "_button";

            return "<div class='dhtmlx_popup_button " + a + "' result='" + t + "' ><div>" + e + "</div></div>"
        }

        function a(e) {
            u.area || (u.area = document.createElement("DIV"), u.area.className = "dhtmlx_message_area", u.area.style[u.position] = "5px", document.body.appendChild(u.area)), u.hide(e.id);
            var t = document.createElement("DIV");
            return t.innerHTML = "<div>" + e.text + "</div>", t.className = "dhtmlx-info dhtmlx-" + e.type, t.onclick = function () {
                    u.hide(e.id), e = null
                }, "bottom" == u.position && u.area.firstChild ? u.area.insertBefore(t, u.area.firstChild) : u.area.appendChild(t),
                e.expire > 0 && (u.timers[e.id] = window.setTimeout(function () {
                    u.hide(e.id)
                }, e.expire)), u.pull[e.id] = t, t = null, e.id
        }

        function n(t, i, a) {
            var n = document.createElement("DIV");
            n.className = " dhtmlx_modal_box dhtmlx-" + t.type, n.setAttribute("dhxbox", 1);
            var r = "";
            if (t.width && (n.style.width = t.width), t.height && (n.style.height = t.height), t.title && (r += '<div class="dhtmlx_popup_title">' + t.title + "</div>"), r += '<div class="dhtmlx_popup_text"><span>' + (t.content ? "" : t.text) + '</span></div><div  class="dhtmlx_popup_controls">',
                i) {
                var d = t.ok || scheduler.locale.labels.message_ok;
                void 0 === d && (d = "OK"), r += s(d, !0, "ok")
            }
            if (a) {
                var o = t.cancel || scheduler.locale.labels.message_cancel;
                void 0 === o && (o = "Cancel"), r += s(o, !1, "cancel")
            }
            if (t.buttons)
                for (var l = 0; l < t.buttons.length; l++) r += s(t.buttons[l], l);
            if (r += "</div>", n.innerHTML = r, t.content) {
                var h = t.content;
                "string" == typeof h && (h = document.getElementById(h)), "none" == h.style.display && (h.style.display = ""), n.childNodes[t.title ? 1 : 0].appendChild(h)
            }
            return n.onclick = function (i) {
                i = i || event;
                var s = i.target || i.srcElement;

                if (s.className || (s = s.parentNode), "dhtmlx_popup_button" == s.className.split(" ")[0]) {
                    var a = s.getAttribute("result");
                    a = "true" == a || ("false" == a ? !1 : a), e(t, a)
                }
            }, t.box = n, (i || a) && (c = t), n
        }

        function r(e, s, a) {
            var r = e.tagName ? e : n(e, s, a);
            e.hidden || i(!0), document.body.appendChild(r);
            var d = Math.abs(Math.floor(((window.innerWidth || document.documentElement.offsetWidth) - r.offsetWidth) / 2)),
                o = Math.abs(Math.floor(((window.innerHeight || document.documentElement.offsetHeight) - r.offsetHeight) / 2));
            return "top" == e.position ? r.style.top = "-3px" : r.style.top = o + "px",
                r.style.left = d + "px", r.onkeydown = t, r.focus(), e.hidden && dhtmlx.modalbox.hide(r), r
        }

        function d(e) {
            return r(e, !0, !1)
        }

        function o(e) {
            return r(e, !0, !0)
        }

        function l(e) {
            return r(e)
        }

        function h(e, t, i) {
            return "object" != typeof e && ("function" == typeof t && (i = t, t = ""), e = {
                text: e,
                type: t,
                callback: i
            }), e
        }

        function _(e, t, i, s) {
            return "object" != typeof e && (e = {
                text: e,
                type: t,
                expire: i,
                id: s
            }), e.id = e.id || u.uid(), e.expire = e.expire || u.expire, e
        }
        var c = null;
        document.attachEvent ? document.attachEvent("onkeydown", t) : document.addEventListener("keydown", t, !0),
            dhtmlx.alert = function () {
                var e = h.apply(this, arguments);
                return e.type = e.type || "confirm", d(e)
            }, dhtmlx.confirm = function () {
                var e = h.apply(this, arguments);
                return e.type = e.type || "alert", o(e)
            }, dhtmlx.modalbox = function () {
                var e = h.apply(this, arguments);
                return e.type = e.type || "alert", l(e)
            }, dhtmlx.modalbox.hide = function (e) {
                for (; e && e.getAttribute && !e.getAttribute("dhxbox");) e = e.parentNode;
                e && (e.parentNode.removeChild(e), i(!1))
            };
        var u = dhtmlx.message = function (e, t, i, s) {
            e = _.apply(this, arguments), e.type = e.type || "info";

            var n = e.type.split("-")[0];
            switch (n) {
            case "alert":
                return d(e);
            case "confirm":
                return o(e);
            case "modalbox":
                return l(e);
            default:
                return a(e)
            }
        };
        u.seed = (new Date).valueOf(), u.uid = function () {
            return u.seed++
        }, u.expire = 4e3, u.keyboard = !0, u.position = "top", u.pull = {}, u.timers = {}, u.hideAll = function () {
            for (var e in u.pull) u.hide(e)
        }, u.hide = function (e) {
            var t = u.pull[e];
            t && t.parentNode && (window.setTimeout(function () {
                    t.parentNode.removeChild(t), t = null
                }, 2e3), t.className += " hidden", u.timers[e] && window.clearTimeout(u.timers[e]),
                delete u.pull[e])
        }
    }(), dataProcessor.prototype = {
        setTransactionMode: function (e, t) {
            this._tMode = e, this._tSend = t, "REST" == e && (this._tSend = !1, this._endnm = !0)
        }, escape: function (e) {
            return this._utf ? encodeURIComponent(e) : escape(e)
        }, enableUTFencoding: function (e) {
            this._utf = convertStringToBoolean(e)
        }, setDataColumns: function (e) {
            this._columns = "string" == typeof e ? e.split(",") : e
        }, getSyncState: function () {
            return !this.updatedRows.length
        }, enableDataNames: function (e) {
            this._endnm = convertStringToBoolean(e)
        }, enablePartialDataSend: function (e) {
            this._changed = convertStringToBoolean(e)
        }, setUpdateMode: function (e, t) {
            this.autoUpdate = "cell" == e, this.updateMode = e, this.dnd = t
        }, ignore: function (e, t) {
            this._silent_mode = !0, e.call(t || window), this._silent_mode = !1
        }, setUpdated: function (e, t, i) {
            if (!this._silent_mode) {
                var s = this.findRow(e);
                i = i || "updated";
                var a = this.obj.getUserData(e, this.action_param);
                a && "updated" == i && (i = a), t ? (this.set_invalid(e, !1), this.updatedRows[s] = e, this.obj.setUserData(e, this.action_param, i), this._in_progress[e] && (this._in_progress[e] = "wait")) : this.is_invalid(e) || (this.updatedRows.splice(s, 1),
                    this.obj.setUserData(e, this.action_param, "")), t || this._clearUpdateFlag(e), this.markRow(e, t, i), t && this.autoUpdate && this.sendData(e)
            }
        }, _clearUpdateFlag: function (e) {}, markRow: function (e, t, i) {
            var s = "",
                a = this.is_invalid(e);
            if (a && (s = this.styles[a], t = !0), this.callEvent("onRowMark", [e, t, i, a]) && (s = this.styles[t ? i : "clear"] + s, this.obj[this._methods[0]](e, s), a && a.details)) {
                s += this.styles[a + "_cell"];
                for (var n = 0; n < a.details.length; n++) a.details[n] && this.obj[this._methods[1]](e, n, s)
            }
        }, getState: function (e) {
            return this.obj.getUserData(e, this.action_param);

        }, is_invalid: function (e) {
            return this._invalid[e]
        }, set_invalid: function (e, t, i) {
            i && (t = {
                value: t,
                details: i,
                toString: function () {
                    return this.value.toString()
                }
            }), this._invalid[e] = t
        }, checkBeforeUpdate: function (e) {
            return !0
        }, sendData: function (e) {
            return !this._waitMode || "tree" != this.obj.mytype && !this.obj._h2 ? (this.obj.editStop && this.obj.editStop(), "undefined" == typeof e || this._tSend ? this.sendAllData() : this._in_progress[e] ? !1 : (this.messages = [], !this.checkBeforeUpdate(e) && this.callEvent("onValidationError", [e, this.messages]) ? !1 : void this._beforeSendData(this._getRowData(e), e))) : void 0;

        }, _beforeSendData: function (e, t) {
            return this.callEvent("onBeforeUpdate", [t, this.getState(t), e]) ? void this._sendData(e, t) : !1
        }, serialize: function (e, t) {
            if ("string" == typeof e) return e;
            if ("undefined" != typeof t) return this.serialize_one(e, "");
            var i = [],
                s = [];
            for (var a in e) e.hasOwnProperty(a) && (i.push(this.serialize_one(e[a], a + this.post_delim)), s.push(a));
            return i.push("ids=" + this.escape(s.join(","))), dhtmlx.security_key && i.push("dhx_security=" + dhtmlx.security_key), i.join("&")
        }, serialize_one: function (e, t) {
            if ("string" == typeof e) return e;
            var i = [];
            for (var s in e)
                if (e.hasOwnProperty(s)) {
                    if (("id" == s || s == this.action_param) && "REST" == this._tMode) continue;
                    i.push(this.escape((t || "") + s) + "=" + this.escape(e[s]))
                }
            return i.join("&")
        }, _sendData: function (e, t) {
            if (e) {
                if (!this.callEvent("onBeforeDataSending", t ? [t, this.getState(t), e] : [null, null, e])) return !1;
                t && (this._in_progress[t] = (new Date).valueOf());
                var i = new dtmlXMLLoaderObject(function (i, s, a, n, r) {
                        var d = [];
                        if (t) d.push(t);
                        else if (e)
                            for (var o in e) d.push(o);
                        return i.afterUpdate(i, r, d);

                    }, this, !0),
                    s = this.serverProcessor + (this._user ? getUrlSymbol(this.serverProcessor) + ["dhx_user=" + this._user, "dhx_version=" + this.obj.getUserData(0, "version")].join("&") : "");
                if ("GET" == this._tMode) i.loadXML(s + (-1 != s.indexOf("?") ? "&" : "?") + this.serialize(e, t));
                else if ("POST" == this._tMode) i.loadXML(s, !0, this.serialize(e, t));
                else if ("REST" == this._tMode) {
                    var a = this.getState(t),
                        n = s.replace(/(\&|\?)editing\=true/, "");
                    "inserted" == a ? i.loadXML(n + t, "POST", this.serialize(e, t)) : "deleted" == a ? i.loadXML(n + t, "DELETE", "") : i.loadXML(n + t, "PUT", this.serialize(e, t));

                }
                this._waitMode++
            }
        }, sendAllData: function () {
            if (this.updatedRows.length) {
                this.messages = [];
                for (var e = !0, t = 0; t < this.updatedRows.length; t++) e &= this.checkBeforeUpdate(this.updatedRows[t]);
                if (!e && !this.callEvent("onValidationError", ["", this.messages])) return !1;
                if (this._tSend) this._sendData(this._getAllData());
                else
                    for (var t = 0; t < this.updatedRows.length; t++)
                        if (!this._in_progress[this.updatedRows[t]]) {
                            if (this.is_invalid(this.updatedRows[t])) continue;
                            if (this._beforeSendData(this._getRowData(this.updatedRows[t]), this.updatedRows[t]),
                                this._waitMode && ("tree" == this.obj.mytype || this.obj._h2)) return
                        }
            }
        }, _getAllData: function (e) {
            for (var t = {}, i = !1, s = 0; s < this.updatedRows.length; s++) {
                var a = this.updatedRows[s];
                this._in_progress[a] || this.is_invalid(a) || this.callEvent("onBeforeUpdate", [a, this.getState(a), this._getRowData(a)]) && (t[a] = this._getRowData(a, a + this.post_delim), i = !0, this._in_progress[a] = (new Date).valueOf())
            }
            return i ? t : null
        }, setVerificator: function (e, t) {
            this.mandatoryFields[e] = t || function (e) {
                return "" !== e
            }
        }, clearVerificator: function (e) {
            this.mandatoryFields[e] = !1
        }, findRow: function (e) {
            var t = 0;
            for (t = 0; t < this.updatedRows.length && e != this.updatedRows[t]; t++);
            return t
        }, defineAction: function (e, t) {
            this._uActions || (this._uActions = []), this._uActions[e] = t
        }, afterUpdateCallback: function (e, t, i, s) {
            var a = e,
                n = "error" != i && "invalid" != i;
            if (n || this.set_invalid(e, i), this._uActions && this._uActions[i] && !this._uActions[i](s)) return delete this._in_progress[a];
            "wait" != this._in_progress[a] && this.setUpdated(e, !1);
            var r = e;
            switch (i) {
            case "inserted":
            case "insert":
                t != e && (this.obj[this._methods[2]](e, t), e = t);
                break;
            case "delete":
            case "deleted":
                return this.obj.setUserData(e, this.action_param, "true_deleted"), this.obj[this._methods[3]](e), delete this._in_progress[a], this.callEvent("onAfterUpdate", [e, i, t, s])
            }
            "wait" != this._in_progress[a] ? (n && this.obj.setUserData(e, this.action_param, ""), delete this._in_progress[a]) : (delete this._in_progress[a], this.setUpdated(t, !0, this.obj.getUserData(e, this.action_param))), this.callEvent("onAfterUpdate", [r, i, t, s])
        }, afterUpdate: function (e, t, i) {
            if (window.JSON) try {
                var s = JSON.parse(t.xmlDoc.responseText),
                    a = s.action || this.getState(i) || "updated",
                    n = s.sid || i[0],
                    r = s.tid || i[0];
                return e.afterUpdateCallback(n, r, a, s), void e.finalizeUpdate()
            } catch (d) {}
            if (t.getXMLTopNode("data"), !t.xmlDoc.responseXML) return this.obj && this.obj.callEvent && this.obj.callEvent("onSaveError", [i, t.xmlDoc]), this.cleanUpdate(i);
            var o = t.doXPath("//data/action");
            if (!o.length) return this.cleanUpdate(i);
            for (var l = 0; l < o.length; l++) {
                var h = o[l],
                    a = h.getAttribute("type"),
                    n = h.getAttribute("sid"),
                    r = h.getAttribute("tid");

                e.afterUpdateCallback(n, r, a, h)
            }
            e.finalizeUpdate()
        }, cleanUpdate: function (e) {
            if (e)
                for (var t = 0; t < e.length; t++) delete this._in_progress[e[t]]
        }, finalizeUpdate: function () {
            this._waitMode && this._waitMode--, ("tree" == this.obj.mytype || this.obj._h2) && this.updatedRows.length && this.sendData(), this.callEvent("onAfterUpdateFinish", []), this.updatedRows.length || this.callEvent("onFullSync", [])
        }, init: function (e) {
            this.obj = e, this.obj._dp_init && this.obj._dp_init(this)
        }, setOnAfterUpdate: function (e) {
            this.attachEvent("onAfterUpdate", e);

        }, enableDebug: function (e) {}, setOnBeforeUpdateHandler: function (e) {
            this.attachEvent("onBeforeDataSending", e)
        }, setAutoUpdate: function (e, t) {
            e = e || 2e3, this._user = t || (new Date).valueOf(), this._need_update = !1, this._loader = null, this._update_busy = !1, this.attachEvent("onAfterUpdate", function (e, t, i, s) {
                this.afterAutoUpdate(e, t, i, s)
            }), this.attachEvent("onFullSync", function () {
                this.fullSync()
            });
            var i = this;
            window.setInterval(function () {
                i.loadUpdate()
            }, e)
        }, afterAutoUpdate: function (e, t, i, s) {
            return "collision" == t ? (this._need_update = !0, !1) : !0
        }, fullSync: function () {
            return this._need_update && (this._need_update = !1, this.loadUpdate()), !0
        }, getUpdates: function (e, t) {
            return this._update_busy ? !1 : (this._update_busy = !0, this._loader = this._loader || new dtmlXMLLoaderObject(!0), this._loader.async = !0, this._loader.waitCall = t, void this._loader.loadXML(e))
        }, _v: function (e) {
            return e.firstChild ? e.firstChild.nodeValue : ""
        }, _a: function (e) {
            for (var t = [], i = 0; i < e.length; i++) t[i] = this._v(e[i]);
            return t
        }, loadUpdate: function () {
            var e = this,
                t = this.obj.getUserData(0, "version"),
                i = this.serverProcessor + getUrlSymbol(this.serverProcessor) + ["dhx_user=" + this._user, "dhx_version=" + t].join("&");

            i = i.replace("editing=true&", ""), this.getUpdates(i, function () {
                var t = e._loader.doXPath("//userdata");
                e.obj.setUserData(0, "version", e._v(t[0]));
                var i = e._loader.doXPath("//update");
                if (i.length) {
                    e._silent_mode = !0;
                    for (var s = 0; s < i.length; s++) {
                        var a = i[s].getAttribute("status"),
                            n = i[s].getAttribute("id"),
                            r = i[s].getAttribute("parent");
                        switch (a) {
                        case "inserted":
                            e.callEvent("insertCallback", [i[s], n, r]);
                            break;
                        case "updated":
                            e.callEvent("updateCallback", [i[s], n, r]);
                            break;
                        case "deleted":
                            e.callEvent("deleteCallback", [i[s], n, r]);

                        }
                    }
                    e._silent_mode = !1
                }
                e._update_busy = !1, e = null
            })
        }
    }, window.dataProcessor && !dataProcessor.prototype.init_original && (dataProcessor.prototype.init_original = dataProcessor.prototype.init, dataProcessor.prototype.init = function (e) {
        this.init_original(e), e._dataprocessor = this, this.setTransactionMode("POST", !0), this.serverProcessor += (-1 != this.serverProcessor.indexOf("?") ? "&" : "?") + "editing=true"
    }), dhtmlxError.catchError("LoadXML", function (e, t, i) {
        var s = i[0].responseText;
        switch (scheduler.config.ajax_error) {
        case "alert":
            window.alert(s);
            break;
        case "console":
            window.console.log(s)
        }
    }), dhtmlxEventable(scheduler), scheduler._detachDomEvent = function (e, t, i) {
        e.removeEventListener ? e.removeEventListener(t, i, !1) : e.detachEvent && e.detachEvent("on" + t, i)
    }, scheduler._init_once = function () {
        function e() {
            return {
                w: window.innerWidth || document.documentElement.clientWidth,
                h: window.innerHeight || document.documentElement.clientHeight
            }
        }

        function t(e, t) {
            return e.w == t.w && e.h == t.h
        }
        var i = e();
        dhtmlxEvent(window, "resize", function () {
            var s = e();
            t(i, s) || (window.clearTimeout(scheduler._resize_timer),
                scheduler._resize_timer = window.setTimeout(function () {
                    scheduler.callEvent("onSchedulerResize", []) && (scheduler.update_view(), scheduler.callEvent("onAfterSchedulerResize", []))
                }, 100)), i = s
        }), scheduler._init_once = function () {}
    }, scheduler.init = function (e, t, i) {
        t = t || scheduler._currentDate(), i = i || "week", this._obj && this.unset_actions(), this._obj = "string" == typeof e ? document.getElementById(e) : e, this._skin_init && scheduler._skin_init(), scheduler.date.init(), this._els = [], this._scroll = !0, this._quirks = _isIE && "BackCompat" == document.compatMode,
            this._quirks7 = _isIE && -1 == navigator.appVersion.indexOf("MSIE 8"), this.get_elements(), this.init_templates(), this.set_actions(), this._init_once(), this._init_touch_events(), this.set_sizes(), scheduler.callEvent("onSchedulerReady", []), this.setCurrentView(t, i)
    }, scheduler.xy = {
        min_event_height: 40,
        scale_width: 50,
        scroll_width: 18,
        scale_height: 20,
        month_scale_height: 20,
        menu_width: 25,
        margin_top: 0,
        margin_left: 0,
        editor_width: 140,
        month_head_height: 22
    }, scheduler.keys = {
        edit_save: 13,
        edit_cancel: 27
    }, scheduler.set_sizes = function () {
        var e = this._x = this._obj.clientWidth - this.xy.margin_left,
            t = this._y = this._obj.clientHeight - this.xy.margin_top,
            i = this._table_view ? 0 : this.xy.scale_width + this.xy.scroll_width,
            s = this._table_view ? -1 : this.xy.scale_width;
        this.set_xy(this._els.dhx_cal_navline[0], e, this.xy.nav_height, 0, 0), this.set_xy(this._els.dhx_cal_header[0], e - i, this.xy.scale_height, s, this.xy.nav_height + (this._quirks ? -1 : 1));
        var a = this._els.dhx_cal_navline[0].offsetHeight;
        a > 0 && (this.xy.nav_height = a);
        var n = this.xy.scale_height + this.xy.nav_height + (this._quirks ? -2 : 0);

        this.set_xy(this._els.dhx_cal_data[0], e, t - (n + 2), 0, n + 2)
    }, scheduler.set_xy = function (e, t, i, s, a) {
        e.style.width = Math.max(0, t) + "px", e.style.height = Math.max(0, i) + "px", arguments.length > 3 && (e.style.left = s + "px", e.style.top = a + "px")
    }, scheduler.get_elements = function () {
        for (var e = this._obj.getElementsByTagName("DIV"), t = 0; t < e.length; t++) {
            var i = e[t].className || "",
                s = e[t].getAttribute("name") || "";
            i && (i = i.split(" ")[0]), this._els[i] || (this._els[i] = []), this._els[i].push(e[t]);
            var a = scheduler.locale.labels[s || i];
            "string" != typeof a && s && !e[t].innerHTML && (a = s.split("_")[0]),
                a && (e[t].innerHTML = a)
        }
    }, scheduler.unset_actions = function () {
        for (var e in this._els)
            if (this._click[e])
                for (var t = 0; t < this._els[e].length; t++) this._els[e][t].onclick = null;
        this._obj.onselectstart = null, this._obj.onmousemove = null, this._obj.onmousedown = null, this._obj.onmouseup = null, /*this._obj.ondblclick = null,*/ this._obj.oncontextmenu = null
    }, scheduler.set_actions = function () {
        for (var e in this._els)
            if (this._click[e])
                for (var t = 0; t < this._els[e].length; t++) this._els[e][t].onclick = scheduler._click[e];
        this._obj.onselectstart = function (e) {
            return !1
        }, this._obj.onmousemove = function (e) {
            scheduler._temp_touch_block || scheduler._on_mouse_move(e || event)
        }, this._obj.onmousedown = function (e) {
            scheduler._ignore_next_click || scheduler._on_mouse_down(e || event)
        }, this._obj.onmouseup = function (e) {
            scheduler._ignore_next_click || scheduler._on_mouse_up(e || event)
        }, /*this._obj.ondblclick = function (e) {
            scheduler._on_dbl_click(e || event)
        },*/ this._obj.oncontextmenu = function (e) {
            var t = e || event,
                i = t.target || t.srcElement,
                s = scheduler.callEvent("onContextMenu", [scheduler._locate_event(i), t]);

            return s
        }
    }, scheduler.select = function (e) {
        this._select_id != e && (scheduler._close_not_saved(), this.editStop(!1), this.unselect(), this._select_id = e, this.updateEvent(e))
    }, scheduler.unselect = function (e) {
        if (!e || e == this._select_id) {
            var t = this._select_id;
            this._select_id = null, t && this.getEvent(t) && this.updateEvent(t)
        }
    }, scheduler.getState = function () {
        return {
            mode: this._mode,
            date: new Date(this._date),
            min_date: new Date(this._min_date),
            max_date: new Date(this._max_date),
            editor_id: this._edit_id,
            lightbox_id: this._lightbox_id,
            new_event: this._new_event,
            select_id: this._select_id,
            expanded: this.expanded,
            drag_id: this._drag_id,
            drag_mode: this._drag_mode
        }
    }, scheduler._click = {
        dhx_cal_data: function (e) {
                if (scheduler._ignore_next_click) return e.preventDefault && e.preventDefault(), e.cancelBubble = !0, scheduler._ignore_next_click = !1, !1;
                var t = e ? e.target : event.srcElement,
                    i = scheduler._locate_event(t);
                if (e = e || event, i) {
                    if (!scheduler.callEvent("onClick", [i, e]) || scheduler.config.readonly) return
                } else scheduler.callEvent("onEmptyClick", [scheduler.getActionData(e).date, e]);

                if (i && scheduler.config.select) {
                    scheduler.select(i);
                    var s = t.className; - 1 != s.indexOf("_icon") && scheduler._click.buttons[s.split(" ")[1].replace("icon_", "")](i)
                } else scheduler._close_not_saved(), (new Date).valueOf() - (scheduler._new_event || 0) > 500 && scheduler.unselect()
            }, dhx_cal_prev_button: function () {
                scheduler._click.dhx_cal_next_button(0, -1)
            }, dhx_cal_next_button: function (e, t) {
                scheduler.setCurrentView(scheduler.date.add(scheduler.date[scheduler._mode + "_start"](scheduler._date), t || 1, scheduler._mode))
            },
            dhx_cal_today_button: function () {
                scheduler.callEvent("onBeforeTodayDisplayed", []) && scheduler.setCurrentView(scheduler._currentDate())
            }, dhx_cal_tab: function () {
                var e = this.getAttribute("name"),
                    t = e.substring(0, e.search("_tab"));
                scheduler.setCurrentView(scheduler._date, t)
            }, buttons: {
                "delete": function (e) {
                    var t = scheduler.locale.labels.confirm_deleting;
                    scheduler._dhtmlx_confirm(t, scheduler.locale.labels.title_confirm_deleting, function () {
                        scheduler.deleteEvent(e)
                    })
                }, edit: function (e) {
                    scheduler.edit(e)
                }, save: function (e) {
                    scheduler.editStop(!0)
                }, details: function (e) {
                    scheduler.showLightbox(e)
                }, cancel: function (e) {
                    scheduler.editStop(!1)
                }
            }
    }, scheduler._dhtmlx_confirm = function (e, t, i) {
        if (!e) return i();
        var s = {
            text: e
        };
        t && (s.title = t), i && (s.callback = function (e) {
            e && i()
        }), dhtmlx.confirm(s)
    }, scheduler.addEventNow = function (e, t, i) {
        var s = {};
        e && null !== e.constructor.toString().match(/object/i) && (s = e, e = null);
        var a = 6e4 * (this.config.event_duration || this.config.time_step);
        e || (e = s.start_date || Math.round(scheduler._currentDate().valueOf() / a) * a);

        var n = new Date(e);
        if (!t) {
            var r = this.config.first_hour;
            r > n.getHours() && (n.setHours(r), e = n.valueOf()), t = e.valueOf() + a
        }
        var d = new Date(t);
        n.valueOf() == d.valueOf() && d.setTime(d.valueOf() + a), s.start_date = s.start_date || n, s.end_date = s.end_date || d, s.text = s.text || this.locale.labels.new_event, s.id = this._drag_id = this.uid(), this._drag_mode = "new-size", this._loading = !0, this.addEvent(s), this.callEvent("onEventCreated", [this._drag_id, i]), this._loading = !1, this._drag_event = {}, this._on_mouse_up(i)
    }, scheduler._on_dbl_click = function (e, t) {
        if (t = t || e.target || e.srcElement, !this.config.readonly) {
            var i = (t.className || "").split(" ")[0];
            switch (i) {
            case "dhx_scale_holder":
            case "dhx_scale_holder_now":
            case "dhx_month_body":
            case "dhx_wa_day_data":
                if (!scheduler.config.dblclick_create) break;
                this.addEventNow(this.getActionData(e).date, null, e);
                break;
            case "dhx_cal_event":
            case "dhx_wa_ev_body":
            case "dhx_agenda_line":
            case "dhx_grid_event":
            case "dhx_cal_event_line":
            case "dhx_cal_event_clear":
                var s = this._locate_event(t);
               /* if (!this.callEvent("onDblClick", [s, e])) return;*/

                this.config.details_on_dblclick || this._table_view || !this.getEvent(s)._timed || !this.config.select ? this.showLightbox(s) : this.edit(s);
                break;
            case "dhx_time_block":
            case "dhx_cal_container":
                return;
            default:
                var a = this["dblclick_" + i];
                if (a) a.call(this, e);
                else if (t.parentNode && t != this) return scheduler._on_dbl_click(e, t.parentNode)
            }
        }
    }, scheduler._get_column_index = function (e) {
        var t = 0;
        if (this._cols) {
            for (var i = 0, s = 0; s < this._cols.length && !i; s++) i = this._cols[s];
            if (t = i ? e / i : 0, this._ignores)
                for (var s = 0; t >= s; s++) this._ignores[s] && t++;

        }
        return t
    }, scheduler._week_indexes_from_pos = function (e) {
        if (this._cols) {
            var t = this._get_column_index(e.x);
            return e.x = Math.min(this._cols.length - 1, Math.max(0, Math.ceil(t) - 1)), e.y = Math.max(0, Math.ceil(60 * e.y / (this.config.time_step * this.config.hour_size_px)) - 1) + this.config.first_hour * (60 / this.config.time_step), e
        }
        return e
    }, scheduler._mouse_coords = function (e) {
        var t, i = document.body,
            s = document.documentElement;
        t = _isIE || !e.pageX && !e.pageY ? {
            x: e.clientX + (i.scrollLeft || s.scrollLeft || 0) - i.clientLeft,
            y: e.clientY + (i.scrollTop || s.scrollTop || 0) - i.clientTop
        } : {
            x: e.pageX,
            y: e.pageY
        }, t.x -= getAbsoluteLeft(this._obj) + (this._table_view ? 0 : this.xy.scale_width), t.y -= getAbsoluteTop(this._obj) + this.xy.nav_height + (this._dy_shift || 0) + this.xy.scale_height - this._els.dhx_cal_data[0].scrollTop, t.ev = e;
        var a = this["mouse_" + this._mode];
        if (a) return a.call(this, t);
        if (this._table_view) {
            var n = this._get_column_index(t.x);
            if (!this._cols || !this._colsS) return t;
            var r = 0;
            for (r = 1; r < this._colsS.heights.length && !(this._colsS.heights[r] > t.y); r++);
            t.y = Math.ceil(24 * (Math.max(0, n) + 7 * Math.max(0, r - 1)) * 60 / this.config.time_step), (scheduler._drag_mode || "month" == this._mode) && (t.y = 24 * (Math.max(0, Math.ceil(n) - 1) + 7 * Math.max(0, r - 1)) * 60 / this.config.time_step), "move" == this._drag_mode && scheduler._ignores_detected && scheduler.config.preserve_length && (t._ignores = !0, this._drag_event._event_length || (this._drag_event._event_length = this._get_real_event_length(this._drag_event.start_date, this._drag_event.end_date, {
                x_step: 1,
                x_unit: "day"
            }))), t.x = 0
        } else t = this._week_indexes_from_pos(t);
        return t
    }, scheduler._close_not_saved = function () {
        if ((new Date).valueOf() - (scheduler._new_event || 0) > 500 && scheduler._edit_id) {
            var e = scheduler.locale.labels.confirm_closing;
            scheduler._dhtmlx_confirm(e, scheduler.locale.labels.title_confirm_closing, function () {
                scheduler.editStop(scheduler.config.positive_closing)
            }), e && (this._drag_id = this._drag_pos = this._drag_mode = null)
        }
    }, scheduler._correct_shift = function (e, t) {
        return e -= 6e4 * (new Date(scheduler._min_date).getTimezoneOffset() - new Date(e).getTimezoneOffset()) * (t ? -1 : 1)
    }, scheduler._is_pos_changed = function (e, t) {
        function i(e, t, i) {
            return !!(Math.abs(e - t) > i)
        }
        if (!e || !this._drag_pos) return !0;

        var s = 100,
            a = 5;
        return !!(!this._drag_pos.start || +new Date - this._drag_pos.start > s || i(e.x, t.x, a) || i(e.y, t.y, a))
    }, scheduler._on_mouse_move = function (e) {
        if (this._drag_mode) {
            var t = this._mouse_coords(e);
            if (t.force_redraw || this._is_pos_changed(this._drag_pos, t)) {
                var i, s;
                if (this._edit_id != this._drag_id && this._close_not_saved(), !this._drag_mode) return;
                if (this._drag_pos = t, this._drag_pos.has_moved = !0, "create" == this._drag_mode) {
                    if (this._close_not_saved(), this.unselect(this._select_id), this._loading = !0, i = this._get_date_from_pos(t).valueOf(), !this._drag_start) {
                        var a = this.callEvent("onBeforeEventCreated", [e, this._drag_id]);
                        if (!a) return;
                        return void(this._drag_start = i)
                    }
                    s = i, s == this._drag_start;
                    var n = new Date(this._drag_start),
                        r = new Date(s);
                    "day" != this._mode && "week" != this._mode || n.getHours() != r.getHours() || n.getMinutes() != r.getMinutes() || (r = new Date(this._drag_start + 1e3)), this._drag_id = this.uid(), this.addEvent(n, r, this.locale.labels.new_event, this._drag_id, t.fields), this.callEvent("onEventCreated", [this._drag_id, e]), this._loading = !1, this._drag_mode = "new-size";

                }
                var d, o = this.getEvent(this._drag_id);
                if ("move" == this._drag_mode) i = this._min_date.valueOf() + 6e4 * (t.y * this.config.time_step + 24 * t.x * 60 - (scheduler._move_pos_shift || 0)), !t.custom && this._table_view && (i += 1e3 * this.date.time_part(o.start_date)), i = this._correct_shift(i), t._ignores && this.config.preserve_length && this._table_view ? (this.matrix && (d = this.matrix[this._mode]), d = d || {
                    x_step: 1,
                    x_unit: "day"
                }, s = 1 * i + this._get_fictional_event_length(i, this._drag_event._event_length, d)) : s = o.end_date.valueOf() - (o.start_date.valueOf() - i);
                else {
                    if (i = o.start_date.valueOf(), s = o.end_date.valueOf(), this._table_view) {
                        var l = this._min_date.valueOf() + t.y * this.config.time_step * 6e4 + (t.custom ? 0 : 864e5);
                        if ("month" == this._mode)
                            if (l = this._correct_shift(l, !1), this._drag_from_start) {
                                var h = 864e5;
                                l <= scheduler.date.date_part(new Date(s + h - 1)).valueOf() && (i = l - h)
                            } else s = l;
                        else t.resize_from_start ? i = l : s = l
                    } else s = this.date.date_part(new Date(o.end_date.valueOf() - 1)).valueOf() + t.y * this.config.time_step * 6e4, this._els.dhx_cal_data[0].style.cursor = "s-resize", ("week" == this._mode || "day" == this._mode) && (s = this._correct_shift(s));

                    if ("new-size" == this._drag_mode)
                        if (s <= this._drag_start) {
                            var _ = t.shift || (this._table_view && !t.custom ? 864e5 : 0);
                            i = s - (t.shift ? 0 : _), s = this._drag_start + (_ || 6e4 * this.config.time_step)
                        } else i = this._drag_start;
                    else i >= s && (s = i + 6e4 * this.config.time_step)
                }
                var c = new Date(s - 1),
                    u = new Date(i);
                if (scheduler.config.limit_drag_out && (+u < +scheduler._min_date || +s > +scheduler._max_date)) {
                    var g = s - u; + u < +scheduler._min_date ? (u = new Date(scheduler._min_date), s = new Date(+u + g)) : (s = new Date(scheduler._max_date), u = new Date(+s - g));
                    var c = new Date(s - 1)
                }
                if (!this._table_view && !scheduler.config.all_timed && (!scheduler._get_section_view() && t.x != this._get_event_sday({
                    start_date: new Date(s),
                    end_date: new Date(s)
                }) || new Date(s).getHours() >= this.config.last_hour)) {
                    var g = s - u,
                        h = this._min_date.valueOf() + 24 * t.x * 60 * 6e4;
                    s = scheduler.date.date_part(new Date(h)), s.setHours(this.config.last_hour), c = new Date(s - 1), "move" == this._drag_mode && (u = new Date(+s - g))
                }
                if (this._table_view || c.getDate() == u.getDate() && c.getHours() < this.config.last_hour || scheduler._allow_dnd)
                    if (o.start_date = u,
                        o.end_date = new Date(s), this.config.update_render) {
                        var f = scheduler._els.dhx_cal_data[0].scrollTop;
                        this.update_view(), scheduler._els.dhx_cal_data[0].scrollTop = f
                    } else this.updateEvent(this._drag_id);
                this._table_view && this.for_rendered(this._drag_id, function (e) {
                    e.className += " dhx_in_move"
                }), this.callEvent("onEventDrag", [this._drag_id, this._drag_mode, e])
            }
        } else if (scheduler.checkEvent("onMouseMove")) {
            var v = this._locate_event(e.target || e.srcElement);
            this.callEvent("onMouseMove", [v, e])
        }
    }, scheduler._on_mouse_down = function (e, t) {
        if (2 != e.button && !this.config.readonly && !this._drag_mode) {
            t = t || e.target || e.srcElement;
            var i = t.className && t.className.split(" ")[0];
            switch (i) {
            case "dhx_cal_event_line":
            case "dhx_cal_event_clear":
                this._table_view && (this._drag_mode = "move");
                break;
            case "dhx_event_move":
            case "dhx_wa_ev_body":
                this._drag_mode = "move";
                break;
            case "dhx_event_resize":
                this._drag_mode = "resize", (t.className || "").indexOf("dhx_event_resize_end") < 0 ? scheduler._drag_from_start = !0 : scheduler._drag_from_start = !1;
                break;
            case "dhx_scale_holder":
            case "dhx_scale_holder_now":
            case "dhx_month_body":
            case "dhx_matrix_cell":
            case "dhx_marked_timespan":
                this._drag_mode = "create";
                break;
            case "":
                if (t.parentNode) return scheduler._on_mouse_down(e, t.parentNode);
                break;
            default:
                if ((!scheduler.checkEvent("onMouseDown") || scheduler.callEvent("onMouseDown", [i])) && t.parentNode && t != this && "dhx_body" != i) return scheduler._on_mouse_down(e, t.parentNode);
                this._drag_mode = null, this._drag_id = null
            }
            if (this._drag_mode) {
                var s = this._locate_event(t);
                if (this.config["drag_" + this._drag_mode] && this.callEvent("onBeforeDrag", [s, this._drag_mode, e])) {
                    if (this._drag_id = s, (this._edit_id != this._drag_id || this._edit_id && "create" == this._drag_mode) && this._close_not_saved(), !this._drag_mode) return;
                    this._drag_event = scheduler._lame_clone(this.getEvent(this._drag_id) || {}), this._drag_pos = this._mouse_coords(e), this._drag_pos.start = +new Date
                } else this._drag_mode = this._drag_id = 0
            }
            this._drag_start = null
        }
    }, scheduler._get_private_properties = function (e) {
        var t = {};
        for (var i in e) 0 === i.indexOf("_") && (t[i] = !0);
        return t
    }, scheduler._clear_temporary_properties = function (e, t) {
        var i = this._get_private_properties(e),
            s = this._get_private_properties(t);
        for (var a in s) i[a] || delete t[a]
    }, scheduler._on_mouse_up = function (e) {
        if (!e || 2 != e.button || !scheduler.config.touch) {
            if (this._drag_mode && this._drag_id) {
                this._els.dhx_cal_data[0].style.cursor = "default";
                var t = this._drag_id,
                    i = this._drag_mode,
                    s = !this._drag_pos || this._drag_pos.has_moved,
                    a = this.getEvent(this._drag_id);
                if (s && (this._drag_event._dhx_changed || !this._drag_event.start_date || a.start_date.valueOf() != this._drag_event.start_date.valueOf() || a.end_date.valueOf() != this._drag_event.end_date.valueOf())) {
                    var n = "new-size" == this._drag_mode;
                    if (this.callEvent("onBeforeEventChanged", [a, e, n, this._drag_event]))
                        if (this._drag_id = this._drag_mode = null, n && this.config.edit_on_create) {
                            if (this.unselect(), this._new_event = new Date, this._table_view || this.config.details_on_create || !this.config.select) return scheduler.callEvent("onDragEnd", [t, i, e]), this.showLightbox(t);
                            this._drag_pos = !0, this._select_id = this._edit_id = t
                        } else this._new_event || this.callEvent(n ? "onEventAdded" : "onEventChanged", [t, this.getEvent(t)]);
                    else n ? this.deleteEvent(a.id, !0) : (this._drag_event._dhx_changed = !1,
                        this._clear_temporary_properties(a, this._drag_event), scheduler._lame_copy(a, this._drag_event), this.updateEvent(a.id))
                }
                this._drag_pos && (this._drag_pos.has_moved || this._drag_pos === !0) && this.render_view_data(), scheduler.callEvent("onDragEnd", [t, i, e])
            }
            this._drag_id = null, this._drag_mode = null, this._drag_pos = null
        }
    }, scheduler._trigger_dyn_loading = function () {
        return this._load_mode && this._load() ? (this._render_wait = !0, !0) : !1
    }, scheduler.update_view = function () {
        var e = this[this._mode + "_view"];
        return e ? e(!0) : this._reset_scale(),
            this._trigger_dyn_loading() ? !0 : void this.render_view_data()
    }, scheduler.isViewExists = function (e) {
        return !!(scheduler[e + "_view"] || scheduler.date[e + "_start"] && scheduler.templates[e + "_date"] && scheduler.templates[e + "_scale_date"])
    }, scheduler.updateView = function (e, t) {
        e = e || this._date, t = t || this._mode;
        var i = "dhx_cal_data";
        this._mode ? this._obj.className = this._obj.className.replace("dhx_scheduler_" + this._mode, "dhx_scheduler_" + t) : this._obj.className += " dhx_scheduler_" + t;
        var s = this._mode == t && this.config.preserve_scroll ? this._els[i][0].scrollTop : !1;

        this[this._mode + "_view"] && t && this._mode != t && this[this._mode + "_view"](!1), this._close_not_saved();
        var a = "dhx_multi_day";
        this._els[a] && (this._els[a][0].parentNode.removeChild(this._els[a][0]), this._els[a] = null), this._mode = t, this._date = e, this._table_view = "month" == this._mode, this._dy_shift = 0;
        var n = this._els.dhx_cal_tab;
        if (n)
            for (var r = 0; r < n.length; r++) {
                var d = n[r].className;
                d = d.replace(/ active/g, ""), n[r].getAttribute("name") == this._mode + "_tab" && (d += " active"), n[r].className = d
            }
        this.update_view(), "number" == typeof s && (this._els[i][0].scrollTop = s);

    }, scheduler.setCurrentView = function (e, t) {
        this.callEvent("onBeforeViewChange", [this._mode, this._date, t || this._mode, e || this._date]) && (this.updateView(e, t), this.callEvent("onViewChange", [this._mode, this._date]))
    }, scheduler._render_x_header = function (e, t, i, s, a) {
        a = a || 0;
        var n = document.createElement("DIV");
        n.className = "dhx_scale_bar", this.templates[this._mode + "_scalex_class"] && (n.className += " " + this.templates[this._mode + "_scalex_class"](i));
        var r = this._cols[e] - 1;
        "month" == this._mode && 0 === e && this.config.left_border && (n.className += " dhx_scale_bar_border",
            t += 1), this.set_xy(n, r, this.xy.scale_height - 2, t, a), n.innerHTML = this.templates[this._mode + "_scale_date"](i, this._mode), s.appendChild(n)
    }, scheduler._get_columns_num = function (e, t) {
        var i = 7;
        if (!scheduler._table_view) {
            var s = scheduler.date["get_" + scheduler._mode + "_end"];
            s && (t = s(e)), i = Math.round((t.valueOf() - e.valueOf()) / 864e5)
        }
        return i
    }, scheduler._get_timeunit_start = function () {
        return this.date[this._mode + "_start"](new Date(this._date.valueOf()))
    }, scheduler._get_view_end = function () {
        var e = this._get_timeunit_start(),
            t = scheduler.date.add(e, 1, this._mode);

        if (!scheduler._table_view) {
            var i = scheduler.date["get_" + scheduler._mode + "_end"];
            i && (t = i(e))
        }
        return t
    }, scheduler._calc_scale_sizes = function (e, t, i) {
        var s = e,
            a = this._get_columns_num(t, i);
        this._process_ignores(t, a, "day", 1);
        for (var n = a - this._ignores_detected, r = 0; a > r; r++) this._ignores[r] ? (this._cols[r] = 0, n++) : this._cols[r] = Math.floor(s / (n - r)), s -= this._cols[r], this._colsS[r] = (this._cols[r - 1] || 0) + (this._colsS[r - 1] || (this._table_view ? 0 : this.xy.scale_width + 2));
        this._colsS.col_length = a, this._colsS[a] = this._cols[a - 1] + this._colsS[a - 1] || 0;

    }, scheduler._set_scale_col_size = function (e, t, i) {
        var s = this.config;
        this.set_xy(e, t - 1, s.hour_size_px * (s.last_hour - s.first_hour), i + this.xy.scale_width + 1, 0)
    }, scheduler._render_scales = function (e, t) {
        var i = new Date(scheduler._min_date),
            s = new Date(scheduler._max_date),
            a = this.date.date_part(scheduler._currentDate()),
            n = parseInt(e.style.width, 10),
            r = new Date(this._min_date),
            d = this._get_columns_num(i, s);
        this._calc_scale_sizes(n, i, s);
        var o = 0;
        e.innerHTML = "";
        for (var l = 0; d > l; l++) {
            if (this._ignores[l] || this._render_x_header(l, o, r, e), !this._table_view) {
                var h = document.createElement("DIV"),
                    _ = "dhx_scale_holder";
                r.valueOf() == a.valueOf() && (_ = "dhx_scale_holder_now"), this._ignores_detected && this._ignores[l] && (_ += " dhx_scale_ignore"), h.className = _ + " " + this.templates.week_date_class(r, a), this._set_scale_col_size(h, this._cols[l], o), t.appendChild(h), this.callEvent("onScaleAdd", [h, r])
            }
            o += this._cols[l], r = this.date.add(r, 1, "day"), r = this.date.day_start(r)
        }
    }, scheduler._reset_scale = function () {
        if (this.templates[this._mode + "_date"]) {
            var e = this._els.dhx_cal_header[0],
                t = this._els.dhx_cal_data[0],
                i = this.config;

            e.innerHTML = "", t.innerHTML = "";
            var s = (i.readonly || !i.drag_resize ? " dhx_resize_denied" : "") + (i.readonly || !i.drag_move ? " dhx_move_denied" : "");
            t.className = "dhx_cal_data" + s, this._scales = {}, this._cols = [], this._colsS = {
                height: 0
            }, this._dy_shift = 0, this.set_sizes();
            var a, n, r = this._get_timeunit_start(),
                d = scheduler._get_view_end();
            if (a = n = this._table_view ? scheduler.date.week_start(r) : r, this._min_date = a, this._els.dhx_cal_date[0].innerHTML = this.templates[this._mode + "_date"](r, d, this._mode), this._max_date = d, scheduler._render_scales(e, t),
                this._table_view) this._reset_month_scale(t, r, n);
            else if (this._reset_hours_scale(t, r, n), i.multi_day) {
                var o = "dhx_multi_day";
                this._els[o] && (this._els[o][0].parentNode.removeChild(this._els[o][0]), this._els[o] = null);
                var l = this._els.dhx_cal_navline[0],
                    h = l.offsetHeight + this._els.dhx_cal_header[0].offsetHeight + 1,
                    _ = document.createElement("DIV");
                _.className = o, _.style.visibility = "hidden", this.set_xy(_, Math.max(this._colsS[this._colsS.col_length] + this.xy.scroll_width - 2, 0), 0, 0, h), t.parentNode.insertBefore(_, t);

                var c = _.cloneNode(!0);
                c.className = o + "_icon", c.style.visibility = "hidden", this.set_xy(c, this.xy.scale_width, 0, 0, h), _.appendChild(c), this._els[o] = [_, c], this._els[o][0].onclick = this._click.dhx_cal_data
            }
        }
    }, scheduler._reset_hours_scale = function (e, t, i) {
        var s = document.createElement("DIV");
        s.className = "dhx_scale_holder";
        for (var a = new Date(1980, 1, 1, this.config.first_hour, 0, 0), n = 1 * this.config.first_hour; n < this.config.last_hour; n++) {
            var r = document.createElement("DIV");
            r.className = "dhx_scale_hour", r.style.height = this.config.hour_size_px - (this._quirks ? 0 : 1) + "px";

            var d = this.xy.scale_width;
            this.config.left_border && (d -= 1, r.className += " dhx_scale_hour_border"), r.style.width = d + "px", r.innerHTML = scheduler.templates.hour_scale(a), s.appendChild(r), a = this.date.add(a, 1, "hour")
        }
        e.appendChild(s), this.config.scroll_hour && (e.scrollTop = this.config.hour_size_px * (this.config.scroll_hour - this.config.first_hour))
    }, scheduler._currentDate = function () {
        return scheduler.config.now_date ? new Date(scheduler.config.now_date) : new Date
    }, scheduler._process_ignores = function (e, t, i, s, a) {
        this._ignores = {},
            this._ignores_detected = 0;
        var n = scheduler["ignore_" + this._mode];
        if (n)
            for (var r = new Date(e), d = 0; t > d; d++) n(r) && (this._ignores_detected += 1, this._ignores[d] = !0, a && t++), r = scheduler.date.add(r, s, i), scheduler.date[i + "_start"] && (r = scheduler.date[i + "_start"](r))
    }, scheduler._render_month_scale = function (e, t, i) {
        function s(e) {
            var t = scheduler._colsS.height;
            return void 0 !== scheduler._colsS.heights[e + 1] && (t = scheduler._colsS.heights[e + 1] - (scheduler._colsS.heights[e] || 0)), t
        }
        var a = scheduler.date.add(t, 1, "month"),
            n = new Date(i),
            r = scheduler._currentDate();

        this.date.date_part(r), this.date.date_part(i);
        for (var d = Math.ceil(Math.round((a.valueOf() - i.valueOf()) / 864e5) / 7), o = [], l = 0; 7 >= l; l++) {
            var h = (this._cols[l] || 0) - 1;
            0 === l && this.config.left_border && (h -= 1), o[l] = " style='width:" + h + "px;"
        }
        for (var _ = 0, c = "<table cellpadding='0' cellspacing='0'>", u = [], l = 0; d > l; l++) {
            c += "<tr>";
            for (var g = Math.max(s(l) - scheduler.xy.month_head_height, 0), f = 0; 7 > f; f++) {
                c += "<td";
                var v = "";
                t > i ? v = "dhx_before" : i >= a ? v = "dhx_after" : i.valueOf() == r.valueOf() && (v = "dhx_now"), this._ignores_detected && this._ignores[f] && (v += " dhx_scale_ignore"),
                    c += " class='" + v + " " + this.templates.month_date_class(i, r) + "' >";
                var m = "dhx_month_body",
                    p = "dhx_month_head";
                0 === f && this.config.left_border && (m += " dhx_month_body_border", p += " dhx_month_head_border"), this._ignores_detected && this._ignores[f] ? c += "<div></div><div></div>" : (c += "<div class='" + p + "'>" + this.templates.month_day(i) + "</div>", c += "<div class='" + m + "' " + o[f] + ";height:" + g + "px;'></div></td>"), u.push(i);
                var x = i.getDate();
                i = this.date.add(i, 1, "day"), i.getDate() - x > 1 && (i = new Date(i.getFullYear(), i.getMonth(), x + 1, 12, 0));

            }
            c += "</tr>", scheduler._colsS.heights[l] = _, _ += s(l)
        }
        c += "</table>", this._min_date = n, this._max_date = i, e.innerHTML = c, this._scales = {};
        for (var b = e.getElementsByTagName("div"), l = 0; l < u.length; l++) {
            var e = b[2 * l + 1],
                y = u[l];
            this._scales[+y] = e
        }
        for (var l = 0; l < u.length; l++) {
            var y = u[l];
            this.callEvent("onScaleAdd", [this._scales[+y], y])
        }
        return this._max_date
    }, scheduler._reset_month_scale = function (e, t, i) {
        var s = scheduler.date.add(t, 1, "month"),
            a = scheduler._currentDate();
        this.date.date_part(a), this.date.date_part(i);
        var n = Math.ceil(Math.round((s.valueOf() - i.valueOf()) / 864e5) / 7),
            r = Math.floor(e.clientHeight / n) - this.xy.month_head_height;

        return this._colsS.height = r + this.xy.month_head_height, this._colsS.heights = [], scheduler._render_month_scale(e, t, i)
    }, scheduler.getLabel = function (e, t) {
        for (var i = this.config.lightbox.sections, s = 0; s < i.length; s++)
            if (i[s].map_to == e)
                for (var a = i[s].options, n = 0; n < a.length; n++)
                    if (a[n].key == t) return a[n].label;
        return ""
    }, scheduler.updateCollection = function (e, t) {
        var i = scheduler.serverList(e);
        return i ? (i.splice(0, i.length), i.push.apply(i, t || []), scheduler.callEvent("onOptionsLoad", []), scheduler.resetLightbox(), !0) : !1;

    }, scheduler._lame_clone = function (e, t) {
        var i, s, a;
        for (t = t || [], i = 0; i < t.length; i += 2)
            if (e === t[i]) return t[i + 1];
        if (e && "object" == typeof e) {
            for (a = {}, s = [Array, Date, Number, String, Boolean], i = 0; i < s.length; i++) e instanceof s[i] && (a = i ? new s[i](e) : new s[i]);
            t.push(e, a);
            for (i in e) Object.prototype.hasOwnProperty.apply(e, [i]) && (a[i] = scheduler._lame_clone(e[i], t))
        }
        return a || e
    }, scheduler._lame_copy = function (e, t) {
        for (var i in t) t.hasOwnProperty(i) && (e[i] = t[i]);
        return e
    }, scheduler._get_date_from_pos = function (e) {
        var t = this._min_date.valueOf() + 6e4 * (e.y * this.config.time_step + 24 * (this._table_view ? 0 : e.x) * 60);

        return new Date(this._correct_shift(t))
    }, scheduler.getActionData = function (e) {
        var t = this._mouse_coords(e);
        return {
            date: this._get_date_from_pos(t),
            section: t.section
        }
    }, scheduler._focus = function (e, t) {
        e && e.focus && (this.config.touch ? window.setTimeout(function () {
            e.focus()
        }, 100) : (t && e.select && e.select(), e.focus()))
    }, scheduler._get_real_event_length = function (e, t, i) {
        var s, a = t - e,
            n = i._start_correction + i._end_correction || 0,
            r = this["ignore_" + this._mode],
            d = 0;
        for (i.render ? (d = this._get_date_index(i, e), s = this._get_date_index(i, t)) : s = Math.round(a / 60 / 60 / 1e3 / 24); s > d;) {
            var o = scheduler.date.add(t, -i.x_step, i.x_unit);
            a -= r && r(t) ? t - o : n, t = o, s--
        }
        return a
    }, scheduler._get_fictional_event_length = function (e, t, i, s) {
        var a = new Date(e),
            n = s ? -1 : 1;
        if (i._start_correction || i._end_correction) {
            var r;
            r = s ? 60 * a.getHours() + a.getMinutes() - 60 * (i.first_hour || 0) : 60 * (i.last_hour || 0) - (60 * a.getHours() + a.getMinutes());
            var d = 60 * (i.last_hour - i.first_hour),
                o = Math.ceil((t / 6e4 - r) / d);
            t += o * (1440 - d) * 60 * 1e3
        }
        var l, h = new Date(1 * e + t * n),
            _ = this["ignore_" + this._mode],
            c = 0;
        for (i.render ? (c = this._get_date_index(i, a),
            l = this._get_date_index(i, h)) : l = Math.round(t / 60 / 60 / 1e3 / 24); l * n >= c * n;) {
            var u = scheduler.date.add(a, i.x_step * n, i.x_unit);
            _ && _(a) && (t += (u - a) * n, l += n), a = u, c += n
        }
        return t
    }, scheduler._get_section_view = function () {
        return this.matrix && this.matrix[this._mode] ? this.matrix[this._mode] : this._props && this._props[this._mode] ? this._props[this._mode] : null
    }, scheduler._get_section_property = function () {
        return this.matrix && this.matrix[this._mode] ? this.matrix[this._mode].y_property : this._props && this._props[this._mode] ? this._props[this._mode].map_to : null;

    }, scheduler._is_initialized = function () {
        var e = this.getState();
        return this._obj && e.date && e.mode
    }, scheduler._is_lightbox_open = function () {
        var e = this.getState();
        return null !== e.lightbox_id && void 0 !== e.lightbox_id
    }, scheduler.date = {
        init: function () {
                for (var e = scheduler.locale.date.month_short, t = scheduler.locale.date.month_short_hash = {}, i = 0; i < e.length; i++) t[e[i]] = i;
                for (var e = scheduler.locale.date.month_full, t = scheduler.locale.date.month_full_hash = {}, i = 0; i < e.length; i++) t[e[i]] = i
            }, date_part: function (e) {
                var t = new Date(e);

                return e.setHours(0), e.setMinutes(0), e.setSeconds(0), e.setMilliseconds(0), e.getHours() && (e.getDate() < t.getDate() || e.getMonth() < t.getMonth() || e.getFullYear() < t.getFullYear()) && e.setTime(e.getTime() + 36e5 * (24 - e.getHours())), e
            }, time_part: function (e) {
                return (e.valueOf() / 1e3 - 60 * e.getTimezoneOffset()) % 86400
            }, week_start: function (e) {
                var t = e.getDay();
                return scheduler.config.start_on_monday && (0 === t ? t = 6 : t--), this.date_part(this.add(e, -1 * t, "day"))
            }, month_start: function (e) {
                return e.setDate(1), this.date_part(e)
            },
            year_start: function (e) {
                return e.setMonth(0), this.month_start(e)
            }, day_start: function (e) {
                return this.date_part(e)
            }, _add_days: function (e, t) {
                var i = new Date(e.valueOf());
                return i.setDate(i.getDate() + t), t >= 0 && !e.getHours() && i.getHours() && (i.getDate() < e.getDate() || i.getMonth() < e.getMonth() || i.getFullYear() < e.getFullYear()) && i.setTime(i.getTime() + 36e5 * (24 - i.getHours())), i
            }, add: function (e, t, i) {
                var s = new Date(e.valueOf());
                switch (i) {
                case "day":
                    s = scheduler.date._add_days(s, t);
                    break;
                case "week":
                    s = scheduler.date._add_days(s, 7 * t);

                    break;
                case "month":
                    s.setMonth(s.getMonth() + t);
                    break;
                case "year":
                    s.setYear(s.getFullYear() + t);
                    break;
                case "hour":
                    s.setTime(s.getTime() + 60 * t * 60 * 1e3);
                    break;
                case "minute":
                    s.setTime(s.getTime() + 60 * t * 1e3);
                    break;
                default:
                    return scheduler.date["add_" + i](e, t, i)
                }
                return s
            }, to_fixed: function (e) {
                return 10 > e ? "0" + e : e
            }, copy: function (e) {
                return new Date(e.valueOf())
            }, date_to_str: function (e, t) {
                return e = e.replace(/%[a-zA-Z]/g, function (e) {
                        switch (e) {
                        case "%d":
                            return '"+scheduler.date.to_fixed(date.getDate())+"';
                        case "%m":
                            return '"+scheduler.date.to_fixed((date.getMonth()+1))+"';

                        case "%j":
                            return '"+date.getDate()+"';
                        case "%n":
                            return '"+(date.getMonth()+1)+"';
                        case "%y":
                            return '"+scheduler.date.to_fixed(date.getFullYear()%100)+"';
                        case "%Y":
                            return '"+date.getFullYear()+"';
                        case "%D":
                            return '"+scheduler.locale.date.day_short[date.getDay()]+"';
                        case "%l":
                            return '"+scheduler.locale.date.day_full[date.getDay()]+"';
                        case "%M":
                            return '"+scheduler.locale.date.month_short[date.getMonth()]+"';
                        case "%F":
                            return '"+scheduler.locale.date.month_full[date.getMonth()]+"';
                        case "%h":
                            return '"+scheduler.date.to_fixed((date.getHours()+11)%12+1)+"';

                        case "%g":
                            return '"+((date.getHours()+11)%12+1)+"';
                        case "%G":
                            return '"+date.getHours()+"';
                        case "%H":
                            return '"+scheduler.date.to_fixed(date.getHours())+"';
                        case "%i":
                            return '"+scheduler.date.to_fixed(date.getMinutes())+"';
                        case "%a":
                            return '"+(date.getHours()>11?"pm":"am")+"';
                        case "%A":
                            return '"+(date.getHours()>11?"PM":"AM")+"';
                        case "%s":
                            return '"+scheduler.date.to_fixed(date.getSeconds())+"';
                        case "%W":
                            return '"+scheduler.date.to_fixed(scheduler.date.getISOWeek(date))+"';
                        default:
                            return e
                        }
                    }), t && (e = e.replace(/date\.get/g, "date.getUTC")),
                    new Function("date", 'return "' + e + '";')
            }, str_to_date: function (e, t) {
                for (var i = "var temp=date.match(/[a-zA-Z]+|[0-9]+/g);", s = e.match(/%[a-zA-Z]/g), a = 0; a < s.length; a++) switch (s[a]) {
                case "%j":
                case "%d":
                    i += "set[2]=temp[" + a + "]||1;";
                    break;
                case "%n":
                case "%m":
                    i += "set[1]=(temp[" + a + "]||1)-1;";
                    break;
                case "%y":
                    i += "set[0]=temp[" + a + "]*1+(temp[" + a + "]>50?1900:2000);";
                    break;
                case "%g":
                case "%G":
                case "%h":
                case "%H":
                    i += "set[3]=temp[" + a + "]||0;";
                    break;
                case "%i":
                    i += "set[4]=temp[" + a + "]||0;";
                    break;
                case "%Y":
                    i += "set[0]=temp[" + a + "]||0;";

                    break;
                case "%a":
                case "%A":
                    i += "set[3]=set[3]%12+((temp[" + a + "]||'').toLowerCase()=='am'?0:12);";
                    break;
                case "%s":
                    i += "set[5]=temp[" + a + "]||0;";
                    break;
                case "%M":
                    i += "set[1]=scheduler.locale.date.month_short_hash[temp[" + a + "]]||0;";
                    break;
                case "%F":
                    i += "set[1]=scheduler.locale.date.month_full_hash[temp[" + a + "]]||0;"
                }
                var n = "set[0],set[1],set[2],set[3],set[4],set[5]";
                return t && (n = " Date.UTC(" + n + ")"), new Function("date", "var set=[0,0,1,0,0,0]; " + i + " return new Date(" + n + ");")
            }, getISOWeek: function (e) {
                if (!e) return !1;
                var t = e.getDay();

                0 === t && (t = 7);
                var i = new Date(e.valueOf());
                i.setDate(e.getDate() + (4 - t));
                var s = i.getFullYear(),
                    a = Math.round((i.getTime() - new Date(s, 0, 1).getTime()) / 864e5),
                    n = 1 + Math.floor(a / 7);
                return n
            }, getUTCISOWeek: function (e) {
                return this.getISOWeek(this.convert_to_utc(e))
            }, convert_to_utc: function (e) {
                return new Date(e.getUTCFullYear(), e.getUTCMonth(), e.getUTCDate(), e.getUTCHours(), e.getUTCMinutes(), e.getUTCSeconds())
            }
    }, scheduler.locale = {
        date: {
            month_full: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            month_short: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
            day_full: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
            day_short: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"]
        },
        labels: {
            dhx_cal_today_button: "Today",
            day_tab: "Day",
            week_tab: "Week",
            month_tab: "Month",
            new_event: "New event",
            icon_save: "Save",
            icon_cancel: "Cancel",
            icon_details: "Details",
            icon_edit: "Edit",
            icon_delete: "Delete",
            confirm_closing: "",
            confirm_deleting: "Event will be deleted permanently, are you sure?",
            section_description: "Description",
            section_time: "Time period",
            full_day: "Full day",
            confirm_recurring: "Do you want to edit the whole set of repeated events?",
            section_recurring: "Repeat event",
            button_recurring: "Disabled",
            button_recurring_open: "Enabled",
            button_edit_series: "Edit series",
            button_edit_occurrence: "Edit occurrence",
            agenda_tab: "Agenda",
            date: "Date",
            description: "Description",
            year_tab: "Year",
            week_agenda_tab: "Agenda",
            grid_tab: "Grid",
            drag_to_create: "Drag to create",
            drag_to_move: "Drag to move",
            message_ok: "OK",
            message_cancel: "Cancel"
        }
    }, scheduler.config = {
        default_date: "%j %M %Y",
        month_date: "%F %Y",
        load_date: "%Y-%m-%d",
        week_date: "%l",
        day_date: "%D, %F %j",
        hour_date: "%H:%i",
        month_day: "%d",
        xml_date: "%m/%d/%Y %H:%i",
        api_date: "%d-%m-%Y %H:%i",
        preserve_length: !0,
        time_step: 5,
        start_on_monday: 1,
        first_hour: 0,
        last_hour: 24,
        readonly: !1,
        drag_resize: 1,
        drag_move: 1,
        drag_create: 1,
        dblclick_create: 1,
        edit_on_create: 1,
        details_on_create: 0,
        resize_month_events: !1,
        resize_month_timed: !1,
        cascade_event_display: !1,
        cascade_event_count: 4,
        cascade_event_margin: 30,
        multi_day: !0,
        multi_day_height_limit: 0,
        drag_lightbox: !0,
        preserve_scroll: !0,
        select: !0,
        server_utc: !1,
        touch: !0,
        touch_tip: !0,
        touch_drag: 500,
        quick_info_detached: !0,
        positive_closing: !1,
        drag_highlight: !0,
        limit_drag_out: !1,
        icons_edit: ["icon_save", "icon_cancel"],
        icons_select: ["icon_details", "icon_edit", "icon_delete"],
        buttons_left: ["dhx_save_btn", "dhx_cancel_btn"],
        buttons_right: ["dhx_delete_btn"],
        lightbox: {
            sections: [{
                name: "description",
                height: 200,
                map_to: "text",
                type: "textarea",
                focus: !0
            }, {
                name: "time",
                height: 72,
                type: "time",
                map_to: "auto"
            }]
        },
        highlight_displayed_event: !0,
        left_border: !1,
        ajax_error: "alert",
        delay_render: 0
    }, scheduler.templates = {}, scheduler.init_templates = function () {
        var e = scheduler.locale.labels;
        e.dhx_save_btn = e.icon_save, e.dhx_cancel_btn = e.icon_cancel, e.dhx_delete_btn = e.icon_delete;
        var t = scheduler.date.date_to_str,
            i = scheduler.config,
            s = function (e, t) {
                for (var i in t) e[i] || (e[i] = t[i])
            };
        s(scheduler.templates, {
            day_date: t(i.default_date),
            month_date: t(i.month_date),
            week_date: function (e, t) {
                return scheduler.templates.day_date(e) + " &ndash; " + scheduler.templates.day_date(scheduler.date.add(t, -1, "day"))
            }, day_scale_date: t(i.default_date),
            month_scale_date: t(i.week_date),
            week_scale_date: t(i.day_date),
            hour_scale: t(i.hour_date),
            time_picker: t(i.hour_date),
            event_date: t(i.hour_date),
            month_day: t(i.month_day),
            xml_date: scheduler.date.str_to_date(i.xml_date, i.server_utc),
            load_format: t(i.load_date, i.server_utc),
            xml_format: t(i.xml_date, i.server_utc),
            api_date: scheduler.date.str_to_date(i.api_date),
            event_header: function (e, t, i) {
                return scheduler.templates.event_date(e) + " - " + scheduler.templates.event_date(t)
            }, event_text: function (e, t, i) {
                return i.text
            }, event_class: function (e, t, i) {
                return ""
            }, month_date_class: function (e) {
                return ""
            }, week_date_class: function (e) {
                return ""
            }, event_bar_date: function (e, t, i) {
                return scheduler.templates.event_date(e) + " "
            }, event_bar_text: function (e, t, i) {
                return i.text
            }, month_events_link: function (e, t) {
                return "<a>View more(" + t + " events)</a>"
            }, drag_marker_class: function (e, t, i) {
                return ""
            }, drag_marker_content: function (e, t, i) {
                return ""
            }
        }), this.callEvent("onTemplatesReady", [])
    }, scheduler.uid = function () {
        return this._seed || (this._seed = (new Date).valueOf()), this._seed++
    }, scheduler._events = {}, scheduler.clearAll = function () {
        this._events = {}, this._loaded = {}, this._edit_id = null, this._select_id = null, this._drag_id = null, this._drag_mode = null, this._drag_pos = null, this.clear_view(), this.callEvent("onClearAll", [])
    }, scheduler.addEvent = function (e, t, i, s, a) {
        if (!arguments.length) return this.addEventNow();
        var n = e;
        1 != arguments.length && (n = a || {},
            n.start_date = e, n.end_date = t, n.text = i, n.id = s), n.id = n.id || scheduler.uid(), n.text = n.text || "", "string" == typeof n.start_date && (n.start_date = this.templates.api_date(n.start_date)), "string" == typeof n.end_date && (n.end_date = this.templates.api_date(n.end_date));
        var r = 6e4 * (this.config.event_duration || this.config.time_step);
        n.start_date.valueOf() == n.end_date.valueOf() && n.end_date.setTime(n.end_date.valueOf() + r), n._timed = this.isOneDayEvent(n);
        var d = !this._events[n.id];
        return this._events[n.id] = n, this.event_updated(n),
            this._loading || this.callEvent(d ? "onEventAdded" : "onEventChanged", [n.id, n]), n.id
    }, scheduler.deleteEvent = function (e, t) {
        var i = this._events[e];
        (t || this.callEvent("onBeforeEventDelete", [e, i]) && this.callEvent("onConfirmedBeforeEventDelete", [e, i])) && (i && (this._select_id = null, delete this._events[e], this.event_updated(i)), this.callEvent("onEventDeleted", [e, i]))
    }, scheduler.getEvent = function (e) {
        return this._events[e]
    }, scheduler.setEvent = function (e, t) {
        t.id || (t.id = e), this._events[e] = t
    }, scheduler.for_rendered = function (e, t) {
        for (var i = this._rendered.length - 1; i >= 0; i--) this._rendered[i].getAttribute("event_id") == e && t(this._rendered[i], i)
    }, scheduler.changeEventId = function (e, t) {
        if (e != t) {
            var i = this._events[e];
            i && (i.id = t, this._events[t] = i, delete this._events[e]), this.for_rendered(e, function (e) {
                e.setAttribute("event_id", t)
            }), this._select_id == e && (this._select_id = t), this._edit_id == e && (this._edit_id = t), this.callEvent("onEventIdChange", [e, t])
        }
    },
    function () {
        for (var e = ["text", "Text", "start_date", "StartDate", "end_date", "EndDate"], t = function (e) {
            return function (t) {
                return scheduler.getEvent(t)[e]
            }
        }, i = function (e) {
            return function (t, i) {
                var s = scheduler.getEvent(t);
                s[e] = i, s._changed = !0, s._timed = this.isOneDayEvent(s), scheduler.event_updated(s, !0)
            }
        }, s = 0; s < e.length; s += 2) scheduler["getEvent" + e[s + 1]] = t(e[s]), scheduler["setEvent" + e[s + 1]] = i(e[s])
    }(), scheduler.event_updated = function (e, t) {
        this.is_visible_events(e) ? this.render_view_data() : this.clear_event(e.id)
    }, scheduler.is_visible_events = function (e) {
        var t = e.start_date < this._max_date && this._min_date < e.end_date;

        if (t) {
            var i = this._table_view || e.end_date.getHours() >= this.config.first_hour && e.end_date.getHours() < this.config.last_hour || e.start_date.getHours() >= this.config.first_hour && e.start_date.getHours() < this.config.last_hour;
            if (i) return !0;
            var s = (e.end_date.valueOf() - e.start_date.valueOf()) / 36e5,
                a = 24 - (this.config.last_hour - this.config.first_hour);
            return s > a
        }
        return !1
    }, scheduler.isOneDayEvent = function (e) {
        var t = e.end_date.getDate() - e.start_date.getDate();
        return t ? (0 > t && (t = Math.ceil((e.end_date.valueOf() - e.start_date.valueOf()) / 864e5)),
            1 == t && !e.end_date.getHours() && !e.end_date.getMinutes() && (e.start_date.getHours() || e.start_date.getMinutes())) : e.start_date.getMonth() == e.end_date.getMonth() && e.start_date.getFullYear() == e.end_date.getFullYear()
    }, scheduler.get_visible_events = function (e) {
        var t = [];
        for (var i in this._events) this.is_visible_events(this._events[i]) && (!e || this._events[i]._timed) && this.filter_event(i, this._events[i]) && t.push(this._events[i]);
        return t
    }, scheduler.filter_event = function (e, t) {
        var i = this["filter_" + this._mode];
        return i ? i(e, t) : !0
    }, scheduler._is_main_area_event = function (e) {
        return !!e._timed
    }, scheduler.render_view_data = function (e, t) {
        if (!e) {
            if (this._not_render) return void(this._render_wait = !0);
            this._render_wait = !1, this.clear_view(), e = this.get_visible_events(!(this._table_view || this.config.multi_day))
        }
        for (var i = 0, s = e.length; s > i; i++) this._recalculate_timed(e[i]);
        if (this.config.multi_day && !this._table_view) {
            for (var a = [], n = [], i = 0; i < e.length; i++) this._is_main_area_event(e[i]) ? a.push(e[i]) : n.push(e[i]);
            this._rendered_location = this._els.dhx_multi_day[0],
                this._table_view = !0, this.render_data(n, t), this._table_view = !1, this._rendered_location = this._els.dhx_cal_data[0], this._table_view = !1, this.render_data(a, t)
        } else this._rendered_location = this._els.dhx_cal_data[0], this.render_data(e, t)
    }, scheduler._view_month_day = function (e) {
        var t = scheduler.getActionData(e).date;
        scheduler.callEvent("onViewMoreClick", [t]) && scheduler.setCurrentView(t, "day")
    }, scheduler._render_month_link = function (e) {
        for (var t = this._rendered_location, i = this._lame_clone(e), s = e._sday; s < e._eday; s++) {
            i._sday = s, i._eday = s + 1;
            var a = scheduler.date,
                n = scheduler._min_date;
            n = a.add(n, i._sweek, "week"), n = a.add(n, i._sday, "day");
            var r = scheduler.getEvents(n, a.add(n, 1, "day")).length,
                d = this._get_event_bar_pos(i),
                o = d.x2 - d.x,
                l = document.createElement("div");
            l.onclick = function (e) {
                scheduler._view_month_day(e || event)
            }, l.className = "dhx_month_link", l.style.top = d.y + "px", l.style.left = d.x + "px", l.style.width = o + "px", l.innerHTML = scheduler.templates.month_events_link(n, r), this._rendered.push(l), t.appendChild(l)
        }
    }, scheduler._recalculate_timed = function (e) {
        if (e) {
            var t;
            t = "object" != typeof e ? this._events[e] : e, t && (t._timed = scheduler.isOneDayEvent(t))
        }
    }, scheduler.attachEvent("onEventChanged", scheduler._recalculate_timed), scheduler.attachEvent("onEventAdded", scheduler._recalculate_timed), scheduler.render_data = function (e, t) {
        e = this._pre_render_events(e, t);
        for (var i = 0; i < e.length; i++)
            if (this._table_view)
                if ("month" != scheduler._mode) this.render_event_bar(e[i]);
                else {
                    var s = scheduler.config.max_month_events;
                    s !== 1 * s || e[i]._sorder < s ? this.render_event_bar(e[i]) : void 0 !== s && e[i]._sorder == s && scheduler._render_month_link(e[i]);

                } else this.render_event(e[i])
    }, scheduler._get_first_visible_cell = function (e) {
        for (var t = 0; t < e.length; t++)
            if (-1 == (e[t].className || "").indexOf("dhx_scale_ignore")) return e[t];
        return e[0]
    }, scheduler._pre_render_events = function (e, t) {
        var i = this.xy.bar_height,
            s = this._colsS.heights,
            a = this._colsS.heights = [0, 0, 0, 0, 0, 0, 0],
            n = this._els.dhx_cal_data[0];
        if (e = this._table_view ? this._pre_render_events_table(e, t) : this._pre_render_events_line(e, t), this._table_view)
            if (t) this._colsS.heights = s;
            else {
                var r = n.firstChild;
                if (r.rows) {
                    for (var d = 0; d < r.rows.length; d++) {
                        a[d]++;
                        var o = r.rows[d].cells,
                            l = this._colsS.height - this.xy.month_head_height;
                        if (a[d] * i > l) {
                            var h = l;
                            1 * this.config.max_month_events !== this.config.max_month_events || a[d] <= this.config.max_month_events ? h = a[d] * i : (this.config.max_month_events + 1) * i > l && (h = (this.config.max_month_events + 1) * i);
                            for (var _ = 0; _ < o.length; _++) o[_].childNodes[1].style.height = h + "px"
                        }
                        a[d] = (a[d - 1] || 0) + scheduler._get_first_visible_cell(o).offsetHeight
                    }
                    if (a.unshift(0), r.parentNode.offsetHeight < r.parentNode.scrollHeight && !scheduler._colsS.scroll_fix && scheduler.xy.scroll_width) {
                        var c = scheduler._colsS,
                            u = c[c.col_length],
                            g = c.heights.slice();
                        u -= scheduler.xy.scroll_width || 0, this._calc_scale_sizes(u, this._min_date, this._max_date), scheduler._colsS.heights = g, this.set_xy(this._els.dhx_cal_header[0], u, this.xy.scale_height), scheduler._render_scales(this._els.dhx_cal_header[0]), scheduler._render_month_scale(this._els.dhx_cal_data[0], this._get_timeunit_start(), this._min_date), c.scroll_fix = !0
                    }
                } else if (e.length || "visible" != this._els.dhx_multi_day[0].style.visibility || (a[0] = -1), e.length || -1 == a[0]) {
                    var f = (r.parentNode.childNodes, (a[0] + 1) * i + 1),
                        v = f,
                        m = f + "px";
                    this.config.multi_day_height_limit && (v = Math.min(f, this.config.multi_day_height_limit), m = v + "px"), n.style.top = this._els.dhx_cal_navline[0].offsetHeight + this._els.dhx_cal_header[0].offsetHeight + v + "px", n.style.height = this._obj.offsetHeight - parseInt(n.style.top, 10) - (this.xy.margin_top || 0) + "px";
                    var p = this._els.dhx_multi_day[0];
                    p.style.height = m, p.style.visibility = -1 == a[0] ? "hidden" : "visible";
                    var x = this._els.dhx_multi_day[1];
                    x.style.height = m, x.style.visibility = -1 == a[0] ? "hidden" : "visible",
                        x.className = a[0] ? "dhx_multi_day_icon" : "dhx_multi_day_icon_small", this._dy_shift = (a[0] + 1) * i, this.config.multi_day_height_limit && (this._dy_shift = Math.min(this.config.multi_day_height_limit, this._dy_shift)), a[0] = 0, v != f && (n.style.top = parseInt(n.style.top) + 2 + "px", p.style.overflowY = "auto", x.style.position = "fixed", x.style.top = "", x.style.left = "")
                }
            }
        return e
    }, scheduler._get_event_sday = function (e) {
        return Math.floor((e.start_date.valueOf() - this._min_date.valueOf()) / 864e5)
    }, scheduler._get_event_mapped_end_date = function (e) {
        var t = e.end_date;
        if (this.config.separate_short_events) {
            var i = (e.end_date - e.start_date) / 6e4;
            i < this._min_mapped_duration && (t = this.date.add(t, this._min_mapped_duration - i, "minute"))
        }
        return t
    }, scheduler._pre_render_events_line = function (e, t) {
        e.sort(function (e, t) {
            return e.start_date.valueOf() == t.start_date.valueOf() ? e.id > t.id ? 1 : -1 : e.start_date > t.start_date ? 1 : -1
        });
        var i = [],
            s = [];
        this._min_mapped_duration = Math.ceil(60 * this.xy.min_event_height / this.config.hour_size_px);
        for (var a = 0; a < e.length; a++) {
            var n = e[a],
                r = n.start_date,
                d = n.end_date,
                o = r.getHours(),
                l = d.getHours();

            if (n._sday = this._get_event_sday(n), this._ignores[n._sday]) e.splice(a, 1), a--;
            else {
                if (i[n._sday] || (i[n._sday] = []), !t) {
                    n._inner = !1;
                    for (var h = i[n._sday]; h.length;) {
                        var _ = h[h.length - 1],
                            c = this._get_event_mapped_end_date(_);
                        if (!(c.valueOf() <= n.start_date.valueOf())) break;
                        h.splice(h.length - 1, 1)
                    }
                    for (var u = h.length, g = !1, f = 0; f < h.length; f++) {
                        var _ = h[f],
                            c = this._get_event_mapped_end_date(_);
                        if (c.valueOf() <= n.start_date.valueOf()) {
                            g = !0, n._sorder = _._sorder, u = f, n._inner = !0;
                            break
                        }
                    }
                    if (h.length && (h[h.length - 1]._inner = !0), !g)
                        if (h.length)
                            if (h.length <= h[h.length - 1]._sorder) {
                                if (h[h.length - 1]._sorder)
                                    for (f = 0; f < h.length; f++) {
                                        for (var v = !1, m = 0; m < h.length; m++)
                                            if (h[m]._sorder == f) {
                                                v = !0;
                                                break
                                            }
                                        if (!v) {
                                            n._sorder = f;
                                            break
                                        }
                                    } else n._sorder = 0;
                                n._inner = !0
                            } else {
                                var p = h[0]._sorder;
                                for (f = 1; f < h.length; f++) h[f]._sorder > p && (p = h[f]._sorder);
                                n._sorder = p + 1, n._inner = !1
                            } else n._sorder = 0;
                    h.splice(u, u == h.length ? 0 : 1, n), h.length > (h.max_count || 0) ? (h.max_count = h.length, n._count = h.length) : n._count = n._count ? n._count : 1
                }(o < this.config.first_hour || l >= this.config.last_hour) && (s.push(n),
                    e[a] = n = this._copy_event(n), o < this.config.first_hour && (n.start_date.setHours(this.config.first_hour), n.start_date.setMinutes(0)), l >= this.config.last_hour && (n.end_date.setMinutes(0), n.end_date.setHours(this.config.last_hour)), n.start_date > n.end_date || o == this.config.last_hour) && (e.splice(a, 1), a--)
            }
        }
        if (!t) {
            for (var a = 0; a < e.length; a++) e[a]._count = i[e[a]._sday].max_count;
            for (var a = 0; a < s.length; a++) s[a]._count = i[s[a]._sday].max_count
        }
        return e
    }, scheduler._time_order = function (e) {
        e.sort(function (e, t) {
            return e.start_date.valueOf() == t.start_date.valueOf() ? e._timed && !t._timed ? 1 : !e._timed && t._timed ? -1 : e.id > t.id ? 1 : -1 : e.start_date > t.start_date ? 1 : -1;

        })
    }, scheduler._pre_render_events_table = function (e, t) {
        this._time_order(e);
        for (var i, s = [], a = [
            [],
            [],
            [],
            [],
            [],
            [],
            []
        ], n = this._colsS.heights, r = this._cols.length, d = {}, o = 0; o < e.length; o++) {
            var l = e[o],
                h = l.id;
            d[h] || (d[h] = {
                first_chunk: !0,
                last_chunk: !0
            });
            var _ = d[h],
                c = i || l.start_date,
                u = l.end_date;
            c < this._min_date && (_.first_chunk = !1, c = this._min_date), u > this._max_date && (_.last_chunk = !1, u = this._max_date);
            var g = this.locate_holder_day(c, !1, l);
            if (l._sday = g % r, !this._ignores[l._sday] || !l._timed) {
                var f = this.locate_holder_day(u, !0, l) || r;

                l._eday = f % r || r, l._length = f - g, l._sweek = Math.floor((this._correct_shift(c.valueOf(), 1) - this._min_date.valueOf()) / (864e5 * r));
                var v, m = a[l._sweek];
                for (v = 0; v < m.length && !(m[v]._eday <= l._sday); v++);
                if (l._sorder && t || (l._sorder = v), l._sday + l._length <= r) i = null, s.push(l), m[v] = l, n[l._sweek] = m.length - 1, l._first_chunk = _.first_chunk, l._last_chunk = _.last_chunk;
                else {
                    var p = this._copy_event(l);
                    p.id = l.id, p._length = r - l._sday, p._eday = r, p._sday = l._sday, p._sweek = l._sweek, p._sorder = l._sorder, p.end_date = this.date.add(c, p._length, "day"),
                        p._first_chunk = _.first_chunk, _.first_chunk && (_.first_chunk = !1), s.push(p), m[v] = p, i = p.end_date, n[l._sweek] = m.length - 1, o--
                }
            }
        }
        return s
    }, scheduler._copy_dummy = function () {
        var e = new Date(this.start_date),
            t = new Date(this.end_date);
        this.start_date = e, this.end_date = t
    }, scheduler._copy_event = function (e) {
        return this._copy_dummy.prototype = e, new this._copy_dummy
    }, scheduler._rendered = [], scheduler.clear_view = function () {
        for (var e = 0; e < this._rendered.length; e++) {
            var t = this._rendered[e];
            t.parentNode && t.parentNode.removeChild(t);

        }
        this._rendered = []
    }, scheduler.updateEvent = function (e) {
        var t = this.getEvent(e);
        this.clear_event(e), t && this.is_visible_events(t) && this.filter_event(e, t) && (this._table_view || this.config.multi_day || t._timed) && (this.config.update_render ? this.render_view_data() : "month" != this.getState().mode || this.getState().drag_id || this.isOneDayEvent(t) ? this.render_view_data([t], !0) : this.render_view_data())
    }, scheduler.clear_event = function (e) {
        this.for_rendered(e, function (e, t) {
            e.parentNode && e.parentNode.removeChild(e), scheduler._rendered.splice(t, 1);

        })
    }, scheduler._y_from_date = function (e) {
        var t = 60 * e.getHours() + e.getMinutes();
        return Math.round((60 * t * 1e3 - 60 * this.config.first_hour * 60 * 1e3) * this.config.hour_size_px / 36e5) % (24 * this.config.hour_size_px)
    }, scheduler._calc_event_y = function (e, t) {
        t = t || 0;
        var i = 60 * e.start_date.getHours() + e.start_date.getMinutes(),
            s = 60 * e.end_date.getHours() + e.end_date.getMinutes() || 60 * scheduler.config.last_hour,
            a = this._y_from_date(e.start_date),
            n = Math.max(t, (s - i) * this.config.hour_size_px / 60);
        return {
            top: a,
            height: n
        }
    }, scheduler.render_event = function (e) {
        var t = scheduler.xy.menu_width,
            i = this.config.use_select_menu_space ? 0 : t;
        if (!(e._sday < 0)) {
            var s = scheduler.locate_holder(e._sday);
            if (s) {
                var a = this._calc_event_y(e, scheduler.xy.min_event_height),
                    n = a.top,
                    r = a.height,
                    d = e._count || 1,
                    o = e._sorder || 0,
                    l = Math.floor((s.clientWidth - i) / d),
                    h = o * l + 1;
                if (e._inner || (l *= d - o), this.config.cascade_event_display) {
                    var _ = this.config.cascade_event_count,
                        c = this.config.cascade_event_margin;
                    h = o % _ * c;
                    var u = e._inner ? (d - o - 1) % _ * c / 2 : 0;
                    l = Math.floor(s.clientWidth - i - h - u)
                }
                var g = this._render_v_bar(e, i + h, n, l, r, e._text_style, scheduler.templates.event_header(e.start_date, e.end_date, e), scheduler.templates.event_text(e.start_date, e.end_date, e));

                if (this._rendered.push(g), s.appendChild(g), h = h + parseInt(s.style.left, 10) + i, this._edit_id == e.id) {
                    g.style.zIndex = 1, l = Math.max(l - 4, scheduler.xy.editor_width), g = document.createElement("DIV"), g.setAttribute("event_id", e.id), this.set_xy(g, l, r - 20, h, n + 14), g.className = "dhx_cal_event dhx_cal_editor";
                    var f = scheduler.templates.event_class(e.start_date, e.end_date, e);
                    f && (g.className += " " + f);
                    var v = document.createElement("DIV");
                    this.set_xy(v, l - 6, r - 26), v.style.cssText += ";margin:2px 2px 2px 2px;overflow:hidden;", g.appendChild(v),
                        this._els.dhx_cal_data[0].appendChild(g), this._rendered.push(g), v.innerHTML = "<textarea class='dhx_cal_editor'>" + e.text + "</textarea>", this._quirks7 && (v.firstChild.style.height = r - 12 + "px"), this._editor = v.firstChild, this._editor.onkeydown = function (e) {
                            if ((e || event).shiftKey) return !0;
                            var t = (e || event).keyCode;
                            t == scheduler.keys.edit_save && scheduler.editStop(!0), t == scheduler.keys.edit_cancel && scheduler.editStop(!1)
                        }, this._editor.onselectstart = function (e) {
                            return (e || event).cancelBubble = !0, !0
                        }, scheduler._focus(v.firstChild, !0),
                        this._els.dhx_cal_data[0].scrollLeft = 0
                }
                if (0 !== this.xy.menu_width && this._select_id == e.id) {
                    this.config.cascade_event_display && this._drag_mode && (g.style.zIndex = 1);
                    for (var m = this.config["icons_" + (this._edit_id == e.id ? "edit" : "select")], p = "", x = e.color ? "background-color: " + e.color + ";" : "", b = e.textColor ? "color: " + e.textColor + ";" : "", y = 0; y < m.length; y++) p += "<div class='dhx_menu_icon " + m[y] + "' style='" + x + b + "' title='" + this.locale.labels[m[y]] + "'></div>";
                    var w = this._render_v_bar(e, h - t + 1, n, t, 20 * m.length + 26 - 2, "", "<div style='" + x + b + "' class='dhx_menu_head'></div>", p, !0);

                    w.style.left = h - t + 1, this._els.dhx_cal_data[0].appendChild(w), this._rendered.push(w)
                }
                this.config.drag_highlight && this._drag_id == e.id && this.highlightEventPosition(e)
            }
        }
    }, scheduler._render_v_bar = function (e, t, i, s, a, n, r, d, o) {
        var l = document.createElement("DIV"),
            h = e.id,
            _ = o ? "dhx_cal_event dhx_cal_select_menu" : "dhx_cal_event",
            c = scheduler.templates.event_class(e.start_date, e.end_date, e);
        c && (_ = _ + " " + c);
        var u = e.color ? "background:" + e.color + ";" : "",
            g = e.textColor ? "color:" + e.textColor + ";" : "",
            f = '<div event_id="' + h + '" class="' + _ + '" style="position:absolute; top:' + i + "px; left:" + t + "px; width:" + (s - 4) + "px; height:" + a + "px;" + (n || "") + '"></div>';

        l.innerHTML = f;
        var v = l.cloneNode(!0).firstChild;
        if (!o && scheduler.renderEvent(v, e, s, a, r, d)) return v;
        v = l.firstChild;
        var m = '<div class="dhx_event_move dhx_header" style=" width:' + (s - 6) + "px;" + u + '" >&nbsp;</div>';
        m += '<div class="dhx_event_move dhx_title" style="' + u + g + '">' + r + "</div>", m += '<div class="dhx_body" style=" width:' + (s - (this._quirks ? 4 : 14)) + "px; height:" + (a - (this._quirks ? 20 : 30) + 1) + "px;" + u + g + '">' + d + "</div>";
        var p = "dhx_event_resize dhx_footer";
        return o && (p = "dhx_resize_denied " + p), m += '<div class="' + p + '" style=" width:' + (s - 8) + "px;" + (o ? " margin-top:-1px;" : "") + u + g + '" ></div>',
            v.innerHTML = m, v
    }, scheduler.renderEvent = function () {
        return !1
    }, scheduler.locate_holder = function (e) {
        return "day" == this._mode ? this._els.dhx_cal_data[0].firstChild : this._els.dhx_cal_data[0].childNodes[e]
    }, scheduler.locate_holder_day = function (e, t) {
        var i = Math.floor((this._correct_shift(e, 1) - this._min_date) / 864e5);
        return t && this.date.time_part(e) && i++, i
    }, scheduler._get_dnd_order = function (e, t, i) {
        if (!this._drag_event) return e;
        this._drag_event._orig_sorder ? e = this._drag_event._orig_sorder : this._drag_event._orig_sorder = e;

        for (var s = t * e; s + t > i;) e--, s -= t;
        return e = Math.max(e, 0)
    }, scheduler._get_event_bar_pos = function (e) {
        var t = this._colsS[e._sday],
            i = this._colsS[e._eday];
        i == t && (i = this._colsS[e._eday + 1]);
        var s = this.xy.bar_height,
            a = e._sorder;
        if (e.id == this._drag_id) {
            var n = this._colsS.heights[e._sweek + 1] - this._colsS.heights[e._sweek] - this.xy.month_head_height;
            a = scheduler._get_dnd_order(a, s, n)
        }
        var r = a * s,
            d = this._colsS.heights[e._sweek] + (this._colsS.height ? this.xy.month_scale_height + 2 : 2) + r;
        return {
            x: t,
            x2: i,
            y: d
        }
    }, scheduler.render_event_bar = function (e) {
        var t = this._rendered_location,
            i = this._get_event_bar_pos(e),
            s = i.y,
            a = i.x,
            n = i.x2,
            r = "";
        if (n) {
            var d = scheduler.config.resize_month_events && "month" == this._mode && (!e._timed || scheduler.config.resize_month_timed),
                o = document.createElement("DIV"),
                l = e.hasOwnProperty("_first_chunk") && e._first_chunk,
                h = e.hasOwnProperty("_last_chunk") && e._last_chunk,
                _ = d && (e._timed || l),
                c = d && (e._timed || h),
                u = "dhx_cal_event_clear";
            (!e._timed || d) && (u = "dhx_cal_event_line"), l && (u += " dhx_cal_event_line_start"), h && (u += " dhx_cal_event_line_end"),
                _ && (r += "<div class='dhx_event_resize dhx_event_resize_start'></div>"), c && (r += "<div class='dhx_event_resize dhx_event_resize_end'></div>");
            var g = scheduler.templates.event_class(e.start_date, e.end_date, e);
            g && (u += " " + g);
            var f = e.color ? "background:" + e.color + ";" : "",
                v = e.textColor ? "color:" + e.textColor + ";" : "",
                m = ["position:absolute", "top:" + s + "px", "left:" + a + "px", "width:" + (n - a - 15) + "px", v, f, e._text_style || ""].join(";"),
                p = '<div event_id="' + e.id + '" class="' + u + '" style="' + m + '">';
            d && (p += r), "month" == scheduler.getState().mode && (e = scheduler.getEvent(e.id)),
                e._timed && (p += scheduler.templates.event_bar_date(e.start_date, e.end_date, e)), p += scheduler.templates.event_bar_text(e.start_date, e.end_date, e) + "</div>", p += "</div>", o.innerHTML = p, this._rendered.push(o.firstChild), t.appendChild(o.firstChild)
        }
    }, scheduler._locate_event = function (e) {
        for (var t = null; e && !t && e.getAttribute;) t = e.getAttribute("event_id"), e = e.parentNode;
        return t
    }, scheduler.edit = function (e) {
        this._edit_id != e && (this.editStop(!1, e), this._edit_id = e, this.updateEvent(e))
    }, scheduler.editStop = function (e, t) {
        if (!t || this._edit_id != t) {
            var i = this.getEvent(this._edit_id);
            i && (e && (i.text = this._editor.value), this._edit_id = null, this._editor = null, this.updateEvent(i.id), this._edit_stop_event(i, e))
        }
    }, scheduler._edit_stop_event = function (e, t) {
        this._new_event ? (t ? this.callEvent("onEventAdded", [e.id, e]) : e && this.deleteEvent(e.id, !0), this._new_event = null) : t && this.callEvent("onEventChanged", [e.id, e])
    }, scheduler.getEvents = function (e, t) {
        var i = [];
        for (var s in this._events) {
            var a = this._events[s];
            a && (!e && !t || a.start_date < t && a.end_date > e) && i.push(a);

        }
        return i
    }, scheduler.getRenderedEvent = function (e) {
        if (e) {
            for (var t = scheduler._rendered, i = 0; i < t.length; i++) {
                var s = t[i];
                if (s.getAttribute("event_id") == e) return s
            }
            return null
        }
    }, scheduler.showEvent = function (e, t) {
        var i = "number" == typeof e || "string" == typeof e ? scheduler.getEvent(e) : e;
        if (t = t || scheduler._mode, i && (!this.checkEvent("onBeforeEventDisplay") || this.callEvent("onBeforeEventDisplay", [i, t]))) {
            var s = scheduler.config.scroll_hour;
            scheduler.config.scroll_hour = i.start_date.getHours();
            var a = scheduler.config.preserve_scroll;

            scheduler.config.preserve_scroll = !1;
            var n = i.color,
                r = i.textColor;
            scheduler.config.highlight_displayed_event && (i.color = scheduler.config.displayed_event_color, i.textColor = scheduler.config.displayed_event_text_color), scheduler.setCurrentView(new Date(i.start_date), t), i.color = n, i.textColor = r, scheduler.config.scroll_hour = s, scheduler.config.preserve_scroll = a, scheduler.matrix && scheduler.matrix[t] && (scheduler._els.dhx_cal_data[0].scrollTop = getAbsoluteTop(scheduler.getRenderedEvent(i.id)) - getAbsoluteTop(scheduler._els.dhx_cal_data[0]) - 20),
                scheduler.callEvent("onAfterEventDisplay", [i, t])
        }
    }, scheduler._append_drag_marker = function (e) {
        if (!e.parentNode) {
            var t = scheduler._els.dhx_cal_data[0],
                i = t.lastChild;
            i.className && i.className.indexOf("dhx_scale_holder") < 0 && i.previousSibling && (i = i.previousSibling), i && 0 === i.className.indexOf("dhx_scale_holder") && i.appendChild(e)
        }
    }, scheduler._update_marker_position = function (e, t) {
        var i = scheduler._calc_event_y(t, 0);
        e.style.top = i.top + "px", e.style.height = i.height + "px"
    }, scheduler.highlightEventPosition = function (e) {
        var t = document.createElement("div");
        t.setAttribute("event_id", e.id), this._rendered.push(t), this._update_marker_position(t, e);
        var i = this.templates.drag_marker_class(e.start_date, e.end_date, e),
            s = this.templates.drag_marker_content(e.start_date, e.end_date, e);
        t.className = "dhx_drag_marker", i && (t.className += " " + i), s && (t.innerHTML = s), this._append_drag_marker(t)
    }, scheduler._loaded = {}, scheduler._load = function (e, t) {
        if (e = e || this._load_url) {
            e += (-1 == e.indexOf("?") ? "?" : "&") + "timeshift=" + (new Date).getTimezoneOffset(),
                this.config.prevent_cache && (e += "&uid=" + this.uid());
            var i;
            if (t = t || this._date, this._load_mode) {
                var s = this.templates.load_format;
                for (t = this.date[this._load_mode + "_start"](new Date(t.valueOf())); t > this._min_date;) t = this.date.add(t, -1, this._load_mode);
                i = t;
                for (var a = !0; i < this._max_date;) i = this.date.add(i, 1, this._load_mode), this._loaded[s(t)] && a ? t = this.date.add(t, 1, this._load_mode) : a = !1;
                var n = i;
                do i = n, n = this.date.add(i, -1, this._load_mode); while (n > t && this._loaded[s(n)]);
                if (t >= i) return !1;
                for (dhtmlxAjax.get(e + "&from=" + s(t) + "&to=" + s(i), function (e) {
                    scheduler.on_load(e)
                }); i > t;) this._loaded[s(t)] = !0, t = this.date.add(t, 1, this._load_mode)
            } else dhtmlxAjax.get(e, function (e) {
                scheduler.on_load(e)
            });
            return this.callEvent("onXLS", []), !0
        }
    }, scheduler.on_load = function (e) {
        var t, i = !1;
        if (this._process && "xml" != this._process) try {
            t = this[this._process].parse(e.xmlDoc.responseText)
        } catch (s) {
            i = !0
        } else t = this._magic_parser(e), t || (i = !0);
        i && (this.callEvent("onLoadError", [e.xmlDoc]), t = []), scheduler._process_loading(t), this.callEvent("onXLE", [])
    }, scheduler._process_loading = function (e) {
        this._loading = !0, this._not_render = !0;
        for (var t = 0; t < e.length; t++) this.callEvent("onEventLoading", [e[t]]) && this.addEvent(e[t]);
        this._not_render = !1, this._render_wait && this.render_view_data(), this._loading = !1, this._after_call && this._after_call(), this._after_call = null
    }, scheduler._init_event = function (e) {
        e.text = e.text || e._tagvalue || "", e.start_date = scheduler._init_date(e.start_date), e.end_date = scheduler._init_date(e.end_date)
    }, scheduler._init_date = function (e) {
        return e ? "string" == typeof e ? scheduler.templates.xml_date(e) : new Date(e) : null;

    }, scheduler.json = {}, scheduler.json.parse = function (data) {
        "string" == typeof data && (scheduler._temp = eval("(" + data + ")"), data = scheduler._temp ? scheduler._temp.data || scheduler._temp.d || scheduler._temp : []), data.dhx_security && (dhtmlx.security_key = data.dhx_security);
        var collections = scheduler._temp && scheduler._temp.collections ? scheduler._temp.collections : {},
            collections_loaded = !1;
        for (var key in collections)
            if (collections.hasOwnProperty(key)) {
                collections_loaded = !0;
                var collection = collections[key],
                    arr = scheduler.serverList[key];

                if (!arr) continue;
                arr.splice(0, arr.length);
                for (var j = 0; j < collection.length; j++) {
                    var option = collection[j],
                        obj = {
                            key: option.value,
                            label: option.label
                        };
                    for (var option_key in option)
                        if (option.hasOwnProperty(option_key)) {
                            if ("value" == option_key || "label" == option_key) continue;
                            obj[option_key] = option[option_key]
                        }
                    arr.push(obj)
                }
            }
        collections_loaded && scheduler.callEvent("onOptionsLoad", []);
        for (var evs = [], i = 0; i < data.length; i++) {
            var event = data[i];
            scheduler._init_event(event), evs.push(event)
        }
        return evs
    }, scheduler.parse = function (e, t) {
        this._process = t, this.on_load({
            xmlDoc: {
                responseText: e
            }
        })
    }, scheduler.load = function (e, t) {
        "string" == typeof t && (this._process = t, t = arguments[2]), this._load_url = e, this._after_call = t, this._load(e, this._date)
    }, scheduler.setLoadMode = function (e) {
        "all" == e && (e = ""), this._load_mode = e
    }, scheduler.serverList = function (e, t) {
        return t ? (this.serverList[e] = t.slice(0), this.serverList[e]) : (this.serverList[e] = this.serverList[e] || [], this.serverList[e])
    }, scheduler._userdata = {}, scheduler._magic_parser = function (e) {
        var t;
        if (!e.getXMLTopNode) {
            var i = e.xmlDoc.responseText;
            e = new dtmlXMLLoaderObject(function () {}), e.loadXMLString(i)
        }
        if (t = e.getXMLTopNode("data"), "data" != t.tagName) return null;
        var s = t.getAttribute("dhx_security");
        s && (dhtmlx.security_key = s);
        for (var a = e.doXPath("//coll_options"), n = 0; n < a.length; n++) {
            var r = a[n].getAttribute("for"),
                d = this.serverList[r];
            if (d) {
                d.splice(0, d.length);
                for (var o = e.doXPath(".//item", a[n]), l = 0; l < o.length; l++) {
                    for (var h = o[l], _ = h.attributes, c = {
                        key: o[l].getAttribute("value"),
                        label: o[l].getAttribute("label")
                    }, u = 0; u < _.length; u++) {
                        var g = _[u];
                        "value" != g.nodeName && "label" != g.nodeName && (c[g.nodeName] = g.nodeValue)
                    }
                    d.push(c)
                }
            }
        }
        a.length && scheduler.callEvent("onOptionsLoad", []);
        for (var f = e.doXPath("//userdata"), n = 0; n < f.length; n++) {
            var v = this._xmlNodeToJSON(f[n]);
            this._userdata[v.name] = v.text
        }
        var m = [];
        t = e.doXPath("//event");
        for (var n = 0; n < t.length; n++) {
            var p = m[n] = this._xmlNodeToJSON(t[n]);
            scheduler._init_event(p)
        }
        return m
    }, scheduler._xmlNodeToJSON = function (e) {
        for (var t = {}, i = 0; i < e.attributes.length; i++) t[e.attributes[i].name] = e.attributes[i].value;

        for (var i = 0; i < e.childNodes.length; i++) {
            var s = e.childNodes[i];
            1 == s.nodeType && (t[s.tagName] = s.firstChild ? s.firstChild.nodeValue : "")
        }
        return t.text || (t.text = e.firstChild ? e.firstChild.nodeValue : ""), t
    }, scheduler.attachEvent("onXLS", function () {
        if (this.config.show_loading === !0) {
            var e;
            e = this.config.show_loading = document.createElement("DIV"), e.className = "dhx_loading", e.style.left = Math.round((this._x - 128) / 2) + "px", e.style.top = Math.round((this._y - 15) / 2) + "px", this._obj.appendChild(e)
        }
    }), scheduler.attachEvent("onXLE", function () {
        var e = this.config.show_loading;
        e && "object" == typeof e && (this._obj.removeChild(e), this.config.show_loading = !0)
    }), scheduler.ical = {
        parse: function (e) {
            var t = e.match(RegExp(this.c_start + "[^\f]*" + this.c_end, ""));
            if (t.length) {
                t[0] = t[0].replace(/[\r\n]+(?=[a-z \t])/g, " "), t[0] = t[0].replace(/\;[^:\r\n]*:/g, ":");
                for (var i, s = [], a = RegExp("(?:" + this.e_start + ")([^\f]*?)(?:" + this.e_end + ")", "g"); null !== (i = a.exec(t));) {
                    for (var n, r = {}, d = /[^\r\n]+[\r\n]+/g; null !== (n = d.exec(i[1]));) this.parse_param(n.toString(), r);
                    r.uid && !r.id && (r.id = r.uid),
                        s.push(r)
                }
                return s
            }
        }, parse_param: function (e, t) {
            var i = e.indexOf(":");
            if (-1 != i) {
                var s = e.substr(0, i).toLowerCase(),
                    a = e.substr(i + 1).replace(/\\\,/g, ",").replace(/[\r\n]+$/, "");
                "summary" == s ? s = "text" : "dtstart" == s ? (s = "start_date", a = this.parse_date(a, 0, 0)) : "dtend" == s && (s = "end_date", a = this.parse_date(a, 0, 0)), t[s] = a
            }
        }, parse_date: function (e, t, i) {
            var s = e.split("T");
            s[1] && (t = s[1].substr(0, 2), i = s[1].substr(2, 2));
            var a = s[0].substr(0, 4),
                n = parseInt(s[0].substr(4, 2), 10) - 1,
                r = s[0].substr(6, 2);
            return scheduler.config.server_utc && !s[1] ? new Date(Date.UTC(a, n, r, t, i)) : new Date(a, n, r, t, i);

        }, c_start: "BEGIN:VCALENDAR",
        e_start: "BEGIN:VEVENT",
        e_end: "END:VEVENT",
        c_end: "END:VCALENDAR"
    }, scheduler._lightbox_controls = {}, scheduler.formSection = function (e) {
        var t = this.config.lightbox.sections,
            i = 0;
        for (i; i < t.length && t[i].name != e; i++);
        var s = t[i];
        scheduler._lightbox || scheduler.getLightbox();
        var a = document.getElementById(s.id),
            n = a.nextSibling,
            r = {
                section: s,
                header: a,
                node: n,
                getValue: function (e) {
                    return scheduler.form_blocks[s.type].get_value(n, e || {}, s)
                }, setValue: function (e, t) {
                    return scheduler.form_blocks[s.type].set_value(n, e, t || {}, s);

                }
            },
            d = scheduler._lightbox_controls["get_" + s.type + "_control"];
        return d ? d(r) : r
    }, scheduler._lightbox_controls.get_template_control = function (e) {
        return e.control = e.node, e
    }, scheduler._lightbox_controls.get_select_control = function (e) {
        return e.control = e.node.getElementsByTagName("select")[0], e
    }, scheduler._lightbox_controls.get_textarea_control = function (e) {
        return e.control = e.node.getElementsByTagName("textarea")[0], e
    }, scheduler._lightbox_controls.get_time_control = function (e) {
        return e.control = e.node.getElementsByTagName("select"),
            e
    }, scheduler.form_blocks = {
        template: {
            render: function (e) {
                var t = (e.height || "30") + "px";
                return "<div class='dhx_cal_ltext dhx_cal_template' style='height:" + t + ";'></div>"
            }, set_value: function (e, t, i, s) {
                e.innerHTML = t || ""
            }, get_value: function (e, t, i) {
                return e.innerHTML || ""
            }, focus: function (e) {}
        },
        textarea: {
            render: function (e) {
                var t = (e.height || "130") + "px";
                return "<div class='dhx_cal_ltext' style='height:" + t + ";'><textarea></textarea></div>"
            }, set_value: function (e, t, i) {
                e.firstChild.value = t || ""
            }, get_value: function (e, t) {
                return e.firstChild.value
            }, focus: function (e) {
                var t = e.firstChild;
                scheduler._focus(t, !0)
            }
        },
        select: {
            render: function (e) {
                for (var t = (e.height || "23") + "px", i = "<div class='dhx_cal_ltext' style='height:" + t + ";'><select style='width:100%;'>", s = 0; s < e.options.length; s++) i += "<option value='" + e.options[s].key + "'>" + e.options[s].label + "</option>";
                return i += "</select></div>"
            }, set_value: function (e, t, i, s) {
                var a = e.firstChild;
                !a._dhx_onchange && s.onchange && (a.onchange = s.onchange, a._dhx_onchange = !0), "undefined" == typeof t && (t = (a.options[0] || {}).value),
                    a.value = t || ""
            }, get_value: function (e, t) {
                return e.firstChild.value
            }, focus: function (e) {
                var t = e.firstChild;
                scheduler._focus(t, !0)
            }
        },
        time: {
            render: function (e) {
                e.time_format || (e.time_format = ["%H:%i", "%d", "%m", "%Y"]), e._time_format_order = {};
                var t = e.time_format,
                    i = scheduler.config,
                    s = this.date.date_part(scheduler._currentDate()),
                    a = 1440,
                    n = 0;
                scheduler.config.limit_time_select && (a = 60 * i.last_hour + 1, n = 60 * i.first_hour, s.setHours(i.first_hour));
                for (var r = "", d = 0; d < t.length; d++) {
                    var o = t[d];
                    switch (d > 0 && (r += " "), o) {
                    case "%Y":
                        e._time_format_order[3] = d, r += "<select>";
                        for (var l = s.getFullYear() - 5, h = 0; 10 > h; h++) r += "<option value='" + (l + h) + "'>" + (l + h) + "</option>";
                        r += "</select> ";
                        break;
                    case "%m":
                        e._time_format_order[2] = d, r += "<select>";
                        for (var h = 0; 12 > h; h++) r += "<option value='" + h + "'>" + this.locale.date.month_full[h] + "</option>";
                        r += "</select>";
                        break;
                    case "%d":
                        e._time_format_order[1] = d, r += "<select>";
                        for (var h = 1; 32 > h; h++) r += "<option value='" + h + "'>" + h + "</option>";
                        r += "</select>";
                        break;
                    case "%H:%i":
                        e._time_format_order[0] = d, r += "<select>";
                        var h = n,
                            _ = s.getDate();

                        for (e._time_values = []; a > h;) {
                            var c = this.templates.time_picker(s);
                            r += "<option value='" + h + "'>" + c + "</option>", e._time_values.push(h), s.setTime(s.valueOf() + 60 * this.config.time_step * 1e3);
                            var u = s.getDate() != _ ? 1 : 0;
                            h = 24 * u * 60 + 60 * s.getHours() + s.getMinutes()
                        }
                        r += "</select>"
                    }
                }
                return "<div style='height:30px;padding-top:0px;font-size:inherit;' class='dhx_section_time'>" + r + "<span style='font-weight:normal; font-size:10pt;'> &nbsp;&ndash;&nbsp; </span>" + r + "</div>"
            }, set_value: function (e, t, i, s) {
                function a(e, t, i) {
                    for (var a = s._time_values, n = 60 * i.getHours() + i.getMinutes(), r = n, d = !1, o = 0; o < a.length; o++) {
                        var h = a[o];
                        if (h === n) {
                            d = !0;
                            break
                        }
                        n > h && (r = h)
                    }
                    e[t + l[0]].value = d ? n : r, d || r || (e[t + l[0]].selectedIndex = -1), e[t + l[1]].value = i.getDate(), e[t + l[2]].value = i.getMonth(), e[t + l[3]].value = i.getFullYear()
                }
                var n, r, d = scheduler.config,
                    o = e.getElementsByTagName("select"),
                    l = s._time_format_order;
                if (d.full_day) {
                    if (!e._full_day) {
                        var h = "<label class='dhx_fullday'><input type='checkbox' name='full_day' value='true'> " + scheduler.locale.labels.full_day + "&nbsp;</label></input>";
                        scheduler.config.wide_form || (h = e.previousSibling.innerHTML + h),
                            e.previousSibling.innerHTML = h, e._full_day = !0
                    }
                    var _ = e.previousSibling.getElementsByTagName("input")[0];
                    _.checked = 0 === scheduler.date.time_part(i.start_date) && 0 === scheduler.date.time_part(i.end_date), o[l[0]].disabled = _.checked, o[l[0] + o.length / 2].disabled = _.checked, _.onclick = function () {
                        if (_.checked) {
                            var t = {};
                            scheduler.form_blocks.time.get_value(e, t, s), n = scheduler.date.date_part(t.start_date), r = scheduler.date.date_part(t.end_date), (+r == +n || +r >= +n && (0 !== i.end_date.getHours() || 0 !== i.end_date.getMinutes())) && (r = scheduler.date.add(r, 1, "day"));

                        } else n = null, r = null;
                        o[l[0]].disabled = _.checked, o[l[0] + o.length / 2].disabled = _.checked, a(o, 0, n || i.start_date), a(o, 4, r || i.end_date)
                    }
                }
                if (d.auto_end_date && d.event_duration)
                    for (var c = function () {
                        n = new Date(o[l[3]].value, o[l[2]].value, o[l[1]].value, 0, o[l[0]].value), r = new Date(n.getTime() + 60 * scheduler.config.event_duration * 1e3), a(o, 4, r)
                    }, u = 0; 4 > u; u++) o[u].onchange = c;
                a(o, 0, i.start_date), a(o, 4, i.end_date)
            }, get_value: function (e, t, i) {
                var s = e.getElementsByTagName("select"),
                    a = i._time_format_order;
                if (t.start_date = new Date(s[a[3]].value, s[a[2]].value, s[a[1]].value, 0, s[a[0]].value),
                    t.end_date = new Date(s[a[3] + 4].value, s[a[2] + 4].value, s[a[1] + 4].value, 0, s[a[0] + 4].value), !s[a[3]].value || !s[a[3] + 4].value) {
                    var n = this.getEvent(this._lightbox_id);
                    n && (t.start_date = n.start_date, t.end_date = n.end_date)
                }
                return t.end_date <= t.start_date && (t.end_date = scheduler.date.add(t.start_date, scheduler.config.time_step, "minute")), {
                    start_date: new Date(t.start_date),
                    end_date: new Date(t.end_date)
                }
            }, focus: function (e) {
                scheduler._focus(e.getElementsByTagName("select")[0])
            }
        }
    }, scheduler.showCover = function (e) {
        if (e) {
            e.style.display = "block";
            var t = window.pageYOffset || document.body.scrollTop || document.documentElement.scrollTop,
                i = window.pageXOffset || document.body.scrollLeft || document.documentElement.scrollLeft,
                s = window.innerHeight || document.documentElement.clientHeight;
            t ? e.style.top = Math.round(t + Math.max((s - e.offsetHeight) / 2, 0)) + "px" : e.style.top = Math.round(Math.max((s - e.offsetHeight) / 2, 0) + 9) + "px", document.documentElement.scrollWidth > document.body.offsetWidth ? e.style.left = Math.round(i + (document.body.offsetWidth - e.offsetWidth) / 2) + "px" : e.style.left = Math.round((document.body.offsetWidth - e.offsetWidth) / 2) + "px";

        }
        this.show_cover()
    }, scheduler.showLightbox = function (e) {
        if (e) {
            if (!this.callEvent("onBeforeLightbox", [e])) return void(this._new_event && (this._new_event = null));
            var t = this.getLightbox();
            this.showCover(t), this._fill_lightbox(e, t), this.callEvent("onLightbox", [e])
        }
    }, scheduler._fill_lightbox = function (e, t) {
        var i = this.getEvent(e),
            s = t.getElementsByTagName("span");
        scheduler.templates.lightbox_header ? (s[1].innerHTML = "", s[2].innerHTML = scheduler.templates.lightbox_header(i.start_date, i.end_date, i)) : (s[1].innerHTML = this.templates.event_header(i.start_date, i.end_date, i),
            s[2].innerHTML = (this.templates.event_bar_text(i.start_date, i.end_date, i) || "").substr(0, 70));
        for (var a = this.config.lightbox.sections, n = 0; n < a.length; n++) {
            var r = a[n],
                d = document.getElementById(r.id).nextSibling,
                o = this.form_blocks[r.type],
                l = void 0 !== i[r.map_to] ? i[r.map_to] : r.default_value;
            o.set_value.call(this, d, l, i, r), a[n].focus && o.focus.call(this, d)
        }
        scheduler._lightbox_id = e
    }, scheduler._lightbox_out = function (e) {
        for (var t = this.config.lightbox.sections, i = 0; i < t.length; i++) {
            var s = document.getElementById(t[i].id);

            s = s ? s.nextSibling : s;
            var a = this.form_blocks[t[i].type],
                n = a.get_value.call(this, s, e, t[i]);
            "auto" != t[i].map_to && (e[t[i].map_to] = n)
        }
        return e
    }, scheduler._empty_lightbox = function (e) {
        {
            var t = scheduler._lightbox_id,
                i = this.getEvent(t);
            this.getLightbox()
        }
        this._lame_copy(i, e), this.setEvent(i.id, i), this._edit_stop_event(i, !0), this.render_view_data()
    }, scheduler.hide_lightbox = function (e) {
        this.hideCover(this.getLightbox()), this._lightbox_id = null, this.callEvent("onAfterLightbox", [])
    }, scheduler.hideCover = function (e) {
        e && (e.style.display = "none"), this.hide_cover()
    }, scheduler.hide_cover = function () {
        this._cover && this._cover.parentNode.removeChild(this._cover), this._cover = null
    }, scheduler.show_cover = function () {
        if (!this._cover) {
            this._cover = document.createElement("DIV"), this._cover.className = "dhx_cal_cover";
            var e = void 0 !== document.height ? document.height : document.body.offsetHeight,
                t = document.documentElement ? document.documentElement.scrollHeight : 0;
            this._cover.style.height = Math.max(e, t) + "px", document.body.appendChild(this._cover);

        }
    }, scheduler.save_lightbox = function () {
        var e = this._lightbox_out({}, this._lame_copy(this.getEvent(this._lightbox_id)));
        (!this.checkEvent("onEventSave") || this.callEvent("onEventSave", [this._lightbox_id, e, this._new_event])) && (this._empty_lightbox(e), this.hide_lightbox())
    }, scheduler.startLightbox = function (e, t) {
        this._lightbox_id = e, this._custom_lightbox = !0, this._temp_lightbox = this._lightbox, this._lightbox = t, this.showCover(t)
    }, scheduler.endLightbox = function (e, t) {
        this._edit_stop_event(scheduler.getEvent(this._lightbox_id), e),
            e && scheduler.render_view_data(), this.hideCover(t), this._custom_lightbox && (this._lightbox = this._temp_lightbox, this._custom_lightbox = !1), this._temp_lightbox = this._lightbox_id = null
    }, scheduler.resetLightbox = function () {
        scheduler._lightbox && !scheduler._custom_lightbox && scheduler._lightbox.parentNode.removeChild(scheduler._lightbox), scheduler._lightbox = null
    }, scheduler.cancel_lightbox = function () {
        this.callEvent("onEventCancel", [this._lightbox_id, this._new_event]), this.endLightbox(!1), this.hide_lightbox()
    },
    scheduler._init_lightbox_events = function () {
        this.getLightbox().onclick = function (e) {
            var t = e ? e.target : event.srcElement;
            if (t.className || (t = t.previousSibling), t && t.className) switch (t.className) {
            case "dhx_save_btn":
                scheduler.save_lightbox();
                break;
            case "dhx_delete_btn":
                var i = scheduler.locale.labels.confirm_deleting;
                scheduler._dhtmlx_confirm(i, scheduler.locale.labels.title_confirm_deleting, function () {
                    scheduler.deleteEvent(scheduler._lightbox_id), scheduler._new_event = null, scheduler.hide_lightbox()
                });
                break;
            case "dhx_cancel_btn":
                scheduler.cancel_lightbox();
                break;
            default:
                if (t.getAttribute("dhx_button")) scheduler.callEvent("onLightboxButton", [t.className, t, e]);
                else {
                    var s, a, n; - 1 != t.className.indexOf("dhx_custom_button") && (-1 != t.className.indexOf("dhx_custom_button_") ? (s = t.parentNode.getAttribute("index"), n = t.parentNode.parentNode) : (s = t.getAttribute("index"), n = t.parentNode, t = t.firstChild)), s && (a = scheduler.form_blocks[scheduler.config.lightbox.sections[s].type], a.button_click(s, t, n, n.nextSibling))
                }
            }
        }, this.getLightbox().onkeydown = function (e) {
            switch ((e || event).keyCode) {
            case scheduler.keys.edit_save:
                if ((e || event).shiftKey) return;
                scheduler.save_lightbox();
                break;
            case scheduler.keys.edit_cancel:
                scheduler.cancel_lightbox()
            }
        }
    }, scheduler.setLightboxSize = function () {
        var e = this._lightbox;
        if (e) {
            var t = e.childNodes[1];
            t.style.height = "0px", t.style.height = t.scrollHeight + "px", e.style.height = t.scrollHeight + scheduler.xy.lightbox_additional_height + "px", t.style.height = t.scrollHeight + "px"
        }
    }, scheduler._init_dnd_events = function () {
        dhtmlxEvent(document.body, "mousemove", scheduler._move_while_dnd),
            dhtmlxEvent(document.body, "mouseup", scheduler._finish_dnd), scheduler._init_dnd_events = function () {}
    }, scheduler._move_while_dnd = function (e) {
        if (scheduler._dnd_start_lb) {
            document.dhx_unselectable || (document.body.className += " dhx_unselectable", document.dhx_unselectable = !0);
            var t = scheduler.getLightbox(),
                i = e && e.target ? [e.pageX, e.pageY] : [event.clientX, event.clientY];
            t.style.top = scheduler._lb_start[1] + i[1] - scheduler._dnd_start_lb[1] + "px", t.style.left = scheduler._lb_start[0] + i[0] - scheduler._dnd_start_lb[0] + "px";

        }
    }, scheduler._ready_to_dnd = function (e) {
        var t = scheduler.getLightbox();
        scheduler._lb_start = [parseInt(t.style.left, 10), parseInt(t.style.top, 10)], scheduler._dnd_start_lb = e && e.target ? [e.pageX, e.pageY] : [event.clientX, event.clientY]
    }, scheduler._finish_dnd = function () {
        scheduler._lb_start && (scheduler._lb_start = scheduler._dnd_start_lb = !1, document.body.className = document.body.className.replace(" dhx_unselectable", ""), document.dhx_unselectable = !1)
    }, scheduler.getLightbox = function () {
        if (!this._lightbox) {
            var e = document.createElement("DIV");

            e.className = "dhx_cal_light", scheduler.config.wide_form && (e.className += " dhx_cal_light_wide"), scheduler.form_blocks.recurring && (e.className += " dhx_cal_light_rec"), /msie|MSIE 6/.test(navigator.userAgent) && (e.className += " dhx_ie6"), e.style.visibility = "hidden";
            for (var t = this._lightbox_template, i = this.config.buttons_left, s = 0; s < i.length; s++) t += "<div class='dhx_btn_set dhx_left_btn_set " + i[s] + "_set'><div dhx_button='1' class='" + i[s] + "'></div><div>" + scheduler.locale.labels[i[s]] + "</div></div>";
            i = this.config.buttons_right;

            for (var s = 0; s < i.length; s++) t += "<div class='dhx_btn_set dhx_right_btn_set " + i[s] + "_set' style='float:right;'><div dhx_button='1' class='" + i[s] + "'></div><div>" + scheduler.locale.labels[i[s]] + "</div></div>";
            t += "</div>", e.innerHTML = t, scheduler.config.drag_lightbox && (e.firstChild.onmousedown = scheduler._ready_to_dnd, e.firstChild.onselectstart = function () {
                return !1
            }, e.firstChild.style.cursor = "pointer", scheduler._init_dnd_events()), document.body.insertBefore(e, document.body.firstChild), this._lightbox = e;
            var a = this.config.lightbox.sections;

            t = "";
            for (var s = 0; s < a.length; s++) {
                var n = this.form_blocks[a[s].type];
                if (n) {
                    a[s].id = "area_" + this.uid();
                    var r = "";
                    a[s].button && (r = "<div class='dhx_custom_button' index='" + s + "'><div class='dhx_custom_button_" + a[s].button + "'></div><div>" + this.locale.labels["button_" + a[s].button] + "</div></div>"), this.config.wide_form && (t += "<div class='dhx_wrap_section'>");
                    var d = this.locale.labels["section_" + a[s].name];
                    "string" != typeof d && (d = a[s].name), t += "<div id='" + a[s].id + "' class='dhx_cal_lsection'>" + r + d + "</div>" + n.render.call(this, a[s]),
                        t += "</div>"
                }
            }
            for (var o = e.getElementsByTagName("div"), s = 0; s < o.length; s++) {
                var l = o[s];
                if ("dhx_cal_larea" == l.className) {
                    l.innerHTML = t;
                    break
                }
            }
            this.setLightboxSize(), this._init_lightbox_events(this), e.style.display = "none", e.style.visibility = "visible"
        }
        return this._lightbox
    }, scheduler.attachEvent("onEventIdChange", function (e, t) {
        this._lightbox_id == e && (this._lightbox_id = t)
    }), scheduler._lightbox_template = "<div class='dhx_cal_ltitle'><span class='dhx_mark'>&nbsp;</span><span class='dhx_time'></span><span class='dhx_title'></span></div><div class='dhx_cal_larea'></div>",
    scheduler._init_touch_events = function () {
        "force" != this.config.touch && (this.config.touch = this.config.touch && (-1 != navigator.userAgent.indexOf("Mobile") || -1 != navigator.userAgent.indexOf("iPad") || -1 != navigator.userAgent.indexOf("Android") || -1 != navigator.userAgent.indexOf("Touch"))), this.config.touch && (this.xy.scroll_width = 0, window.navigator.msPointerEnabled ? (this._touch_events(["MSPointerMove", "MSPointerDown", "MSPointerUp"], function (e) {
            return e.pointerType == e.MSPOINTER_TYPE_MOUSE ? null : e
        }, function (e) {
            return !e || e.pointerType == e.MSPOINTER_TYPE_MOUSE;

        })/*, this._obj.ondblclick = function () {}*/) : this._touch_events(["touchmove", "touchstart", "touchend"], function (e) {
            return e.touches && e.touches.length > 1 ? null : e.touches[0] ? {
                target: e.target,
                pageX: e.touches[0].pageX,
                pageY: e.touches[0].pageY
            } : e
        }, function () {
            return !1
        }))
    }, scheduler._touch_events = function (e, t, i) {
        function s(e, t, i) {
            dhtmlxEvent(e, t, function (e) {
                return scheduler._is_lightbox_open() ? !0 : i(e)
            })
        }

        function a(e, t, i, s) {
            if (e && t) {
                for (var a = e.target; a && a != scheduler._obj;) a = a.parentNode;
                if (a == scheduler._obj) {
                    var n = Math.abs(e.pageY - t.pageY),
                        r = Math.abs(e.pageX - t.pageX);

                    s > n && r > i && (!n || r / n > 3) && (e.pageX > t.pageX ? scheduler._click.dhx_cal_next_button() : scheduler._click.dhx_cal_prev_button())
                }
            }
        }

        function n(e) {
            var t = scheduler.getState().drag_mode,
                i = scheduler.matrix ? scheduler.matrix[scheduler._mode] : !1,
                s = scheduler.render_view_data;
            "create" == t && i && (scheduler.render_view_data = function () {
                for (var e = scheduler.getState().drag_id, t = scheduler.getEvent(e), s = i.y_property, a = scheduler.getEvents(t.start_date, t.end_date), n = 0; n < a.length; n++) a[n][s] != t[s] && (a.splice(n, 1), n--);
                t._sorder = a.length - 1,
                    t._count = a.length, this.render_data([t], scheduler.getState().mode)
            }), scheduler._on_mouse_move(e), "create" == t && i && (scheduler.render_view_data = s)
        }

        function r(e) {
            scheduler._hide_global_tip(), h && (scheduler._on_mouse_up(t(e || event)), scheduler._temp_touch_block = !1), scheduler._drag_id = null, scheduler._drag_mode = null, scheduler._drag_pos = null, clearTimeout(l), h = c = !1, _ = !0
        }
        var d, o, l, h, _, c, u = (-1 != navigator.userAgent.indexOf("Android") && -1 != navigator.userAgent.indexOf("WebKit"), 0);
        s(document.body, e[0], function (e) {
            if (!i(e)) {
                if (h) return n(t(e)), scheduler._update_global_tip(), e.preventDefault && e.preventDefault(), e.cancelBubble = !0, !1;
                if (o = t(e), c) return o ? void((d.target != o.target || Math.abs(d.pageX - o.pageX) > 5 || Math.abs(d.pageY - o.pageY) > 5) && (_ = !0, clearTimeout(l))) : void(_ = !0)
            }
        }), s(this._els.dhx_cal_data[0], "scroll", r), s(this._els.dhx_cal_data[0], "touchcancel", r), s(this._els.dhx_cal_data[0], "contextmenu", function (e) {
            return c ? (e && e.preventDefault && e.preventDefault(), (e || event).cancelBubble = !0, !1) : void 0
        }), s(this._els.dhx_cal_data[0], e[1], function (e) {
            if (!i(e)) {
                var s;
                if (h = _ = !1, c = !0, scheduler._temp_touch_block = !0, s = o = t(e), !s) return void(_ = !0);
                var a = new Date;
                if (!_ && !h && 250 > a - u) return scheduler._click.dhx_cal_data(s), window.setTimeout(function () {
                    scheduler._on_dbl_click(s)
                }, 50), e.preventDefault && e.preventDefault(), e.cancelBubble = !0, scheduler._block_next_stop = !0, !1;
                if (u = a, !_ && !h && scheduler.config.touch_drag) {
                    var n = scheduler._locate_event(document.activeElement),
                        r = scheduler._locate_event(s.target),
                        g = d ? scheduler._locate_event(d.target) : null;
                    if (n && r && n == r && n != g) return e.preventDefault && e.preventDefault(),
                        e.cancelBubble = !0, scheduler._ignore_next_click = !1, scheduler._click.dhx_cal_data(s), d = s, !1;
                    l = setTimeout(function () {
                            h = !0;
                            var e = d.target;
                            e && e.className && -1 != e.className.indexOf("dhx_body") && (e = e.previousSibling), scheduler._on_mouse_down(d, e), scheduler._drag_mode && "create" != scheduler._drag_mode && scheduler.for_rendered(scheduler._drag_id, function (e, t) {
                                e.style.display = "none", scheduler._rendered.splice(t, 1)
                            }), scheduler.config.touch_tip && scheduler._show_global_tip(), scheduler._on_mouse_move(d)
                        }, scheduler.config.touch_drag),
                        d = s
                }
            }
        }), s(this._els.dhx_cal_data[0], e[2], function (e) {
            return i(e) ? void 0 : (h || a(d, o, 200, 100), h && (scheduler._ignore_next_click = !0), r(e), scheduler._block_next_stop ? (scheduler._block_next_stop = !1, e.preventDefault && e.preventDefault(), e.cancelBubble = !0, !1) : void 0)
        }), dhtmlxEvent(document.body, e[2], r)
    }, scheduler._show_global_tip = function () {
        scheduler._hide_global_tip();
        var e = scheduler._global_tip = document.createElement("DIV");
        e.className = "dhx_global_tip", scheduler._update_global_tip(1), document.body.appendChild(e);

    }, scheduler._update_global_tip = function (e) {
        var t = scheduler._global_tip;
        if (t) {
            var i = "";
            if (scheduler._drag_id && !e) {
                var s = scheduler.getEvent(scheduler._drag_id);
                s && (i = "<div>" + (s._timed ? scheduler.templates.event_header(s.start_date, s.end_date, s) : scheduler.templates.day_date(s.start_date, s.end_date, s)) + "</div>")
            }
            "create" == scheduler._drag_mode || "new-size" == scheduler._drag_mode ? t.innerHTML = (scheduler.locale.drag_to_create || "Drag to create") + i : t.innerHTML = (scheduler.locale.drag_to_move || "Drag to move") + i;

        }
    }, scheduler._hide_global_tip = function () {
        var e = scheduler._global_tip;
        e && e.parentNode && (e.parentNode.removeChild(e), scheduler._global_tip = 0)
    }, scheduler._dp_init = function (e) {
        e._methods = ["_set_event_text_style", "", "changeEventId", "_dp_hook_delete"], this._dp_hook_delete = function (e) {
                return this.deleteEvent(e, !0)
            }, this.attachEvent("onEventAdded", function (t) {
                !this._loading && this._validId(t) && e.setUpdated(t, !0, "inserted")
            }), this.attachEvent("onConfirmedBeforeEventDelete", function (t) {
                if (this._validId(t)) {
                    var i = e.getState(t);
                    return "inserted" == i || this._new_event ? (e.setUpdated(t, !1), !0) : "deleted" == i ? !1 : "true_deleted" == i ? !0 : (e.setUpdated(t, !0, "deleted"), !1)
                }
            }), this.attachEvent("onEventChanged", function (t) {
                !this._loading && this._validId(t) && e.setUpdated(t, !0, "updated")
            }), e._getRowData = function (e, t) {
                var i = this.obj.getEvent(e),
                    s = {};
                for (var a in i) 0 !== a.indexOf("_") && (i[a] && i[a].getUTCFullYear ? s[a] = this.obj.templates.xml_format(i[a]) : s[a] = i[a]);
                return s
            }, e._clearUpdateFlag = function () {}, e.attachEvent("insertCallback", scheduler._update_callback),
            e.attachEvent("updateCallback", scheduler._update_callback), e.attachEvent("deleteCallback", function (e, t) {
                this.obj.setUserData(t, this.action_param, "true_deleted"), this.obj.deleteEvent(t)
            })
    }, scheduler._validId = function (e) {
        return !0
    }, scheduler.setUserData = function (e, t, i) {
        e ? this.getEvent(e)[t] = i : this._userdata[t] = i
    }, scheduler.getUserData = function (e, t) {
        return e ? this.getEvent(e)[t] : this._userdata[t]
    }, scheduler._set_event_text_style = function (e, t) {
        this.for_rendered(e, function (e) {
            e.style.cssText += ";" + t
        });
        var i = this.getEvent(e);

        i._text_style = t, this.event_updated(i)
    }, scheduler._update_callback = function (e, t) {
        var i = scheduler._xmlNodeToJSON(e.firstChild);
        i.text = i.text || i._tagvalue, i.start_date = scheduler.templates.xml_date(i.start_date), i.end_date = scheduler.templates.xml_date(i.end_date), scheduler.addEvent(i)
    }, scheduler._skin_settings = {
        fix_tab_position: [1, 0],
        use_select_menu_space: [1, 0],
        wide_form: [1, 0],
        hour_size_px: [44, 42],
        displayed_event_color: ["#ff4a4a", "ffc5ab"],
        displayed_event_text_color: ["#ffef80", "7e2727"]
    }, scheduler._skin_xy = {
        lightbox_additional_height: [90, 50],
        nav_height: [59, 22],
        bar_height: [24, 20]
    }, scheduler._configure = function (e, t, i) {
        for (var s in t) "undefined" == typeof e[s] && (e[s] = t[s][i])
    }, scheduler._skin_init = function () {
        if (!scheduler.skin)
            for (var e = document.getElementsByTagName("link"), t = 0; t < e.length; t++) {
                var i = e[t].href.match("dhtmlxscheduler_([a-z]+).css");
                if (i) {
                    scheduler.skin = i[1];
                    break
                }
            }
        var s = 0;
        if (!scheduler.skin || "classic" !== scheduler.skin && "glossy" !== scheduler.skin || (s = 1), this._configure(scheduler.config, scheduler._skin_settings, s),
            this._configure(scheduler.xy, scheduler._skin_xy, s), "flat" === scheduler.skin && (scheduler.xy.scale_height = 35, scheduler.templates.hour_scale = function (e) {
                var t = e.getMinutes();
                t = 10 > t ? "0" + t : t;
                var i = "<span class='dhx_scale_h'>" + e.getHours() + "</span><span class='dhx_scale_m'>&nbsp;" + t + "</span>";
                return i
            }), !s) {
            var a = scheduler.config.minicalendar;
            a && (a.padding = 14), scheduler.templates.event_bar_date = function (e, t, i) {
                return ""
            }, scheduler.attachEvent("onTemplatesReady", function () {
                var e = scheduler.date.date_to_str("%d");
                scheduler.templates._old_month_day || (scheduler.templates._old_month_day = scheduler.templates.month_day);
                var t = scheduler.templates._old_month_day;
                if (scheduler.templates.month_day = function (i) {
                    if ("month" == this._mode) {
                        var s = e(i);
                        return 1 == i.getDate() && (s = scheduler.locale.date.month_full[i.getMonth()] + " " + s), +i == +scheduler.date.date_part(new Date) && (s = scheduler.locale.labels.dhx_cal_today_button + " " + s), s
                    }
                    return t.call(this, i)
                }, scheduler.config.fix_tab_position) {
                    for (var i = scheduler._els.dhx_cal_navline[0].getElementsByTagName("div"), s = null, a = 211, n = 0; n < i.length; n++) {
                        var r = i[n],
                            d = r.getAttribute("name");
                        if (d) switch (r.style.right = "auto", d) {
                        case "day_tab":
                            r.style.left = "14px", r.className += " dhx_cal_tab_first";
                            break;
                        case "week_tab":
                            r.style.left = "75px";
                            break;
                        case "month_tab":
                            r.style.left = "136px", r.className += " dhx_cal_tab_last";
                            break;
                        default:
                            r.style.left = a + "px", r.className += " dhx_cal_tab_standalone", a = a + 14 + r.offsetWidth
                        } else 0 === (r.className || "").indexOf("dhx_minical_icon") && r.parentNode == scheduler._els.dhx_cal_navline[0] && (s = r)
                    }
                    s && (s.style.left = a + "px")
                }
            }), scheduler._skin_init = function () {};

        }
    }, window.jQuery && ! function (e) {
        var t = [];
        e.fn.dhx_scheduler = function (i) {
            if ("string" != typeof i) {
                var s = [];
                return this.each(function () {
                    if (this && this.getAttribute && !this.getAttribute("dhxscheduler")) {
                        for (var e in i) "data" != e && (scheduler.config[e] = i[e]);
                        this.getElementsByTagName("div").length || (this.innerHTML = '<div class="dhx_cal_navline"><div class="dhx_cal_prev_button">&nbsp;</div><div class="dhx_cal_next_button">&nbsp;</div><div class="dhx_cal_today_button"></div><div class="dhx_cal_date"></div><div class="dhx_cal_tab" name="day_tab" style="right:204px;"></div><div class="dhx_cal_tab" name="week_tab" style="right:140px;"></div><div class="dhx_cal_tab" name="month_tab" style="right:76px;"></div></div><div class="dhx_cal_header"></div><div class="dhx_cal_data"></div>',
                            this.className += " dhx_cal_container"), scheduler.init(this, scheduler.config.date, scheduler.config.mode), i.data && scheduler.parse(i.data), s.push(scheduler)
                    }
                }), 1 === s.length ? s[0] : s
            }
            return t[i] ? t[i].apply(this, []) : void e.error("Method " + i + " does not exist on jQuery.dhx_scheduler")
        }
    }(jQuery),
    function () {
        function e(e, t, i) {
            t && (e._date = t), i && (e._mode = i)
        }
        var t = scheduler.setCurrentView,
            i = scheduler.updateView,
            s = null,
            a = null,
            n = function (t, n) {
                var r = this;
                window.clearTimeout(a), window.clearTimeout(s), e(this, t, n), a = setTimeout(function () {
                    r.callEvent("onBeforeViewChange", [r._mode, r._date, n || r._mode, t || r._date]) && (i.call(r, t, n), r.callEvent("onViewChange", [r._mode, r._date]), window.clearTimeout(s), a = 0)
                }, scheduler.config.delay_render)
            },
            r = function (t, n) {
                var r = this,
                    d = arguments;
                e(this, t, n), window.clearTimeout(s), s = setTimeout(function () {
                    a || i.apply(r, d)
                }, scheduler.config.delay_render)
            };
        scheduler.attachEvent("onSchedulerReady", function () {
            scheduler.config.delay_render ? (scheduler.setCurrentView = n, scheduler.updateView = r) : (scheduler.setCurrentView = t,
                scheduler.updateView = i)
        })
    }();
//# sourceMappingURL=sources/dhtmlxscheduler.js.map