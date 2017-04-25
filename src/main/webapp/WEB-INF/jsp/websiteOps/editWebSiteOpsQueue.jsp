<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站操作队列</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editWebSiteOpsQueueForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站操作队列</h1>
					<button type="button" id="saveWebSiteOpsQueueBtn" class="btn btn-success">保存网站操作队列</button>
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
								<div class="row clearfix">
									<div class="col-md-6 column">
										<div class="form-group">
											 <label for="queueCnName" class="col-sm-3 control-label">队列中文名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="queueCnName" name="webSiteOpsQueue.queueCnName" value="<s:property value='webSiteOpsQueue.queueCnName'/>" placeholder="请输入队列中文名称"/>
											 </div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">网站操作类型<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<select class="form-control form-select2 mandatorySel" id="webSiteOpsSelector" name="webSiteOpsId" myVal="<s:property value='webSiteOpsId'/>"></select>
											</div>
										</div>
									</div>
									<div class="col-md-6 column">
										<div class="form-group">
											  <label for="queueEnName" class="col-sm-3 control-label">队列英文名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="queueEnName" name="webSiteOpsQueue.queueEnName" value="<s:property value='webSiteOpsQueue.queueEnName'/>" placeholder="请输入队列英文名称"/>
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
		<input type="hidden" name="queueId" value="<s:property value='webSiteOpsQueue.queueId'/>">
		</form>
	</div>
	<input type="hidden" id="type" value="<s:property value='type'/>">
	
	<script type="text/javascript">
		function refresh(){
		}
		
		$(function(){
			var type = $("#type").val();
			
			$.getJSON("findAllWebSiteOps", function(data){
				var webSiteOpsSelector = $("#webSiteOpsSelector");
				$.each(data.jsonResult, function(key, value){
					webSiteOpsSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				webSiteOpsSelector.prepend("<option></option>");
				$("#webSiteOpsSelector").select2({allowClear: true});
				$("#webSiteOpsSelector").select2("val", $("#webSiteOpsSelector").attr("myVal"));
			});
			
			$("#saveWebSiteOpsQueueBtn").click(function(){
				if(type==0){
					$("#editWebSiteOpsQueueForm").attr("action","newWebSiteOpsQueue");
				}else{
					$("#editWebSiteOpsQueueForm").attr("action","editWebSiteOpsQueue");
				}
				
				$("#editWebSiteOpsQueueForm").submit();
			});
			
			$("#editWebSiteOpsQueueForm").submit(function(){
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					$("#saveWebSiteOpsQueueBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>