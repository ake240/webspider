<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>存储类型</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editDataStoreTypeForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">存储类型</h1>
					<button type="button" id="saveDataStoreTypeBtn" class="btn btn-success">保存存储类型</button>
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
											 <label for="dataStoreTypeCnName" class="col-sm-3 control-label">存储类型名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="dataStoreTypeCnName" name="dataStoreType.dataStoreTypeCnName" value="<s:property value='dataStoreType.dataStoreTypeCnName'/>" placeholder="请输入存储类型名称"/>
											 </div>
										</div>
										<!-- 
										<div class="form-group">
											<label for="dataStoreTypeClass" class="col-sm-3 control-label">存储类型类名<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="dataStoreTypeClass" name="dataStoreType.dataStoreTypeClass" value="<s:property value='dataStoreType.dataStoreTypeClass'/>" placeholder="请输入存储类型类名"/>
											 </div>
										</div>
										 -->
									</div>
									
									<div class="col-md-6 column">
										<div class="form-group">
											 <label for="collectionName" class="col-sm-3 control-label">表名<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="collectionName" name="dataStoreType.collectionName" value="<s:property value='dataStoreType.collectionName'/>" placeholder="请输入表名"/>
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
			<input type="hidden" name="dataStoreType.dataStoreTypeId" value="<s:property value='dataStoreType.dataStoreTypeId'/>">
		</form>
	</div>
	<input type="hidden" id="type" value="<s:property value='type'/>">
	
	<script type="text/javascript">
		$(function(){
			var type = $("#type").val();
			$("#saveDataStoreTypeBtn").click(function(){
				if(type==0){
					$("#editDataStoreTypeForm").attr("action","newDataStoreType");
				}else{
					$("#editDataStoreTypeForm").attr("action","editDataStoreType");
				}
				
				$("#editDataStoreTypeForm").submit();
			});
			
			$("#editDataStoreTypeForm").submit(function(){
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					$("#saveDataStoreTypeBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>