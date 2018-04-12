<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="dist/summernote.css">
<script type="text/javascript" src="dist/summernote.js"></script>
<script type="text/javascript" src="dist/lang/summernote-zh-CN.js"></script>
<script type="text/javascript" src="js/common/common_upload.js"></script>
<form class="form-horizontal" id="editWebNewsForm" onsubmit="return false">
	<input type="hidden" id="id" name="id" value="${entity.id}"/>
	<!-- 新闻标题-->
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right">新闻标题<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-8">
			<input type="text" class="form-control" id="newsTitle" name="newsTitle" value="${entity.newsTitle}" check-type="required" maxlength="25"/>
		</div>
	</div>
	<!-- 关键词 -->
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right">关键词<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-8">
			<input type="text" class="form-control" id="keyWord" name="keyWord" value="${entity.keyWord}" check-type="required" maxlength="50"/>
		</div>
	</div>
	<!-- 是否图片新闻 -->
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right">是否图片新闻<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-8">
			<select onchange="showPicture()" class="selectpicker form-control query" data-width="100%" name="pictureFlag" id="pictureFlag" check-type="required">
				<option value="">请选择</option>
				<option value="0" <c:if test="${0 == entity.pictureFlag}">selected="selected"</c:if>>否</option>
				<option value="1" <c:if test="${1 == entity.pictureFlag}">selected="selected"</c:if>>是</option>
			</select>
		</div>
	</div>
	<!-- 新闻图片 -->
	<div class="form-group" id="pictureDiv" style="display: none;">
		<label class="col-sm-2 control-label no-padding-right">新闻图片<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-8">
			<input type="hidden" id="picturePath" name="picturePath" value="${entity.picturePath}"/>
			<img width="100%" id="showPic" style="cursor:pointer;" onclick="$('#uploadfile').click();" src="fileoper/downFile.action?filepath=${entity.picturePath}" onerror="this.src='images/up.gif'">
			<span style="display: none;"><input id="uploadfile" class="file-loading" type="file"/><!-- multiple表示允许同时上传多个文件 --></span>
		</div>
	</div>
	<!-- 新闻内容 -->
	<div class="form-group">
		<label class="col-sm-2 control-label no-padding-right">新闻内容<spring:message code="public.txt.colon"/></label>
		<div class="column col-sm-8">
			<textarea class="form-control" id="newsContent" name="newsContent" rows="5" cols="">${entity.newsContent}</textarea>
			<script>
				$(document).ready(function() {
					$('#newsContent').summernote({
						lang : 'zh-CN', //设置语言
						height : 300, // set editor height
						minHeight : null, // set minimum height of editor
						maxHeight : null, // set maximum height of editor
						focus : true, // set focus to editable area after initializing summernote
						dialogsInBody : true,
						callbacks : {
							onImageUpload: function(files, editor) { //the onImageUpload API  
								sendFile(files, editor, "newsContent");  
							}
						}
					});
				});
			</script>
		</div>
	</div>
</form>
