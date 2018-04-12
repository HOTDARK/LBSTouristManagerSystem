<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
        <head>
        <meta charset="utf-8">
        <title></title>
        <link  rel="stylesheet"  type="text/css"   href="<%=basePath %>/wflibs/bootstrap/css/bootstrap.css"/>
        <link  rel="stylesheet" type="text/css"  href="<%=basePath %>/wflibs/css/flow.css"/>
        <link  rel="stylesheet" type="text/css"   href="<%=basePath %>/wflibs/plugins/font-awesome/css/font-awesome.css"/>
        <link rel="stylesheet" href="<%=basePath %>/wflibs/plugins/fonticons/fonticons.css" />
        <script    src="<%=basePath %>/wflibs/plugins/jquery.min.js"></script>
        <script src="<%=basePath %>/wflibs/bootstrap/js/bootstrap.js"></script>       
        <link rel="stylesheet"  href="<%=basePath %>/wflibs/plugins/treegridbt/jquery.treegrid.css">
        <script type="text/javascript" src="<%=basePath %>/wflibs/plugins/treegridbt/jquery.treegrid.js"></script>
        <script type="text/javascript" src="<%=basePath %>/wflibs/plugins/treegridbt/jquery.treegrid.bootstrap3.js"></script>
        <script src="<%=basePath %>wflibs/plugins/jquery.slimscroll.js"></script>
		<script src="<%=basePath %>wflibs/plugins/jquery.lightbox_me.js"></script>
        <script >
            $(document).ready(function() {
                $('.tree').treegrid(
                	
                	{
                		  'initialState': 'collapsed',
                	}
                );
                $('.tree-2').treegrid({
                    expanderExpandedClass: '"fa fa-angle-right',
                    expanderCollapsedClass: 'fa fa-angle-down'
                });

            });
        </script>
        </head>
        <body style="padding: 20px;">
        		<div class="row">
		<div  class="col-lg-12">
		<h5 class="flow-title"><i class="fi icon-flow"></i>${info.processName}</h5>
		  <ul class="detail-list"><li>日志ID:<span>23894</span></li>
			<li>流程ID:<span>${info.processId}</span></li>
			<li>流程开始时间:<span><fmt:formatDate value="${info.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
			<li>流程开始时间:<span><fmt:formatDate value="${info.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span></li>
		   </ul>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12"><div class="para"> 初始参数：<span></span></div></div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<h5 class="result-title"><i class="fa fa-angle-right"></i>结论:</h5>
			<c:if test="${info.exception!=null }">
				<textarea style="width:100%;height:120px">
				${info.exception }
				</textarea>
			</c:if>
			<c:if test="${info.exception==null }">
			<ul class="result-list">
				<li>编码：<span>${info.conclusionCode }</span></li>
				<li>状态：<span>${info.conclusionState }</span></li>
				<li style="width: 100%;">结论：<span>${info.conclusionResult }</span></li>		
				<li style="width: 100%;">建议：<span>${info.conclusionTips }</span></li>
			</ul>
			</c:if>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<h5 class="result-title"><i class="fa fa-angle-right"></i>执行轨迹:</h5>
			<div class="flow-step"><a class="btn btn-success" target="_blank" href="trace.do?id=${info.id}">点击查看</a></div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<h5 class="result-title"><i class="fa fa-angle-right"></i>测试过程:</h5>
	<table class="table tree table-bordered table-hover">
		<thead>
			<tr>
				<th>节点名称</th>
	            <th>结论</th>
	            <th>编码</th>
	            <th>状态</th>
	            <th>出参</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${logs }" var="item">
				<tr class="treegrid-${item.id } <c:if test="${item.pid!=0}">treegrid-parent-${item.pid}</c:if>">
					<td>${item.name }</td>
		            <td>${item.result }</td>
		            <td>${item.code }</td>
		            <td>
		            <c:choose>
		            	<c:when test="${item.state=='normal' }"><span class="label label-info">正常</span></c:when>
		            	<c:when test="${item.state=='warn' }"><span class="label label-warning">警告</span></c:when>
		            	<c:when test="${item.state=='error' }"><span class="label label-danger">错误</span></c:when>
		            </c:choose>
		            </td>
		            <c:choose>
		            <c:when test="${item.params!='' }">
		            <td><a class="btn btn-default btn-xs params" data='${item.params }'><i class='fa fa-angle-double-right'></i>查看</a></td>
		            </c:when>
		            <c:otherwise>
		            <td>--</td>
		            </c:otherwise>
		            </c:choose>
		          </tr>
			</c:forEach>
          </tbody>
        </table>     
		</div>
	</div>
	
  <div id="vdg"  class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">参数</h4>
      </div>
      <div class="modal-body">
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th>参数名</th>
              <th>值</th>
            </tr>
          </thead>
          <tbody id="tableContent">
          </tbody>
        </table>
      </div>
    </div>
    <!-- /.modal-content --> 
  </div>
<script>
$(function(){
	$(".params").click(function(){
		var data = $(this).attr("data");
		if(data){
			data = $.parseJSON(data);
			var html="";
			for(var i=0;i<data.length;i++){
				html+='<tr><td>'+data[i].key+'</td><td>'+data[i].value+'</td></tr>';
			}
			
			$("#tableContent").empty().append(html);
			
			$('#vdg').lightbox_me({
			   centered: true, 
			});
		}
		
	});
});
</script>            
</body>
</html>
