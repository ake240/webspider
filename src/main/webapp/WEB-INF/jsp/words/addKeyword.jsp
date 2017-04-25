<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加百度指数关键词</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="addKeywordForm" method="post" action="addKeyword" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">百度指数关键词</h1>
					<button type="button" id="saveKeywordBtn" class="btn btn-success">保存关键词</button>
				</div>
				<s:if test="message!=null">
					<div class="alert alert-danger alert-dismissable" style="padding: 8px 12px;">
						<button type="button" class="close" style="right: 0;" data-dismiss="alert" aria-hidden="true">&times;</button>
						<s:property value='message'/>
					</div>
				</s:if>
				<div class="panel-group" id="base-panel">
					<div class="panel panel-default">
						<div class="panel-heading" style="overflow: hidden;">
							 <a class="panel-title" data-toggle="collapse" data-parent="base-panel" href="#base-div">基本设置</a>
						</div>
						<div id="base-div" class="panel-collapse collapse in">
							<div class="panel-body">
								<div class="row clear-fix">
									<div class="col-md-6 column">
										<div class="form-group">
											 <label for="host" class="col-sm-3 control-label">关键词名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="keyword" name="keyword.word" value="<s:property value='machine.host'/>" placeholder="请输入关键词"/>
											 </div>
										</div>
									</div>
									<div class="col-md-6 column">
										<div class="form-group">
											<label class="col-sm-3 control-label">使用状态<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<select class="form-control form-select2" id="keywordStatusSelector" name="keyword.active">
													<option value="1">启用</option>
													<option value="0">停止</option>
												</select>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
	
	<script type="text/javascript">
		$(function(){
			
			$("#saveKeywordBtn").click(function(){
				$("#addKeywordForm").submit();
			});
		});
	</script>
</body>
</html>