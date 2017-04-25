<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>存储字段</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editDataStoreFieldForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">存储字段</h1>
					<button type="button" id="saveDataStoreFieldBtn" class="btn btn-success">保存存储字段</button>
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
											<label for="dataStoreTypeSelector" class="col-sm-3 control-label">存储类型<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<select class="form-control form-select2 mandatorySel" id="dataStoreTypeSelector" name="dataStoreTypeId" myVal="<s:property value='dataStoreTypeId'/>"></select>
											</div>
										</div>
										<div class="form-group">
											<label for="fieldEnName" class="col-sm-3 control-label">英文名称<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="fieldEnName" name="dataStoreField.fieldEnName" value="<s:property value='dataStoreField.fieldEnName'/>" placeholder="请输入英文名称"/>
											 </div>
										</div>
										<div class="form-group">
											 <label class="col-sm-3 control-label">是否唯一<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<s:if test="type==0">
													<s:radio list="#{'1':'是','0':'否'}" name="dataStoreField.isUnique" value="0"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'是','0':'否'}" name="dataStoreField.isUnique" value="dataStoreField.isUnique"/>
												</s:else>
											 </div>
										</div>
									</div>
									
									<div class="col-md-6 column">
										<div class="form-group">
											 <label for="fieldCnName" class="col-sm-3 control-label">中文名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="fieldCnName" name="dataStoreField.fieldCnName" value="<s:property value='dataStoreField.fieldCnName'/>" placeholder="请输入中文名称"/>
											 </div>
										</div>
										<div class="form-group">
											 <label class="col-sm-3 control-label">字段类型<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<s:if test="type==0">
													<s:radio list="#{'1':'字符串','2':'整数', '3':'时间', '4':'URL'}" name="dataStoreField.fieldType" value="1"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'字符串','2':'整数', '3':'时间', '4':'URL'}" name="dataStoreField.fieldType" value="dataStoreField.fieldType"/>
												</s:else>
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
			<input type="hidden" name="dataStoreField.dataStoreFieldId" value="<s:property value='dataStoreField.dataStoreFieldId'/>">
		</form>
	</div>
	<input type="hidden" id="type" value="<s:property value='type'/>">
	
	<script type="text/javascript">
		$(function(){
			var type = $("#type").val();
			
			$.getJSON("findAllDataStoreType", function(data){
				var dataStoreTypeSelector = $("#dataStoreTypeSelector");
				$.each(data.jsonResult, function(key, value){
					dataStoreTypeSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				dataStoreTypeSelector.prepend("<option></option>");
				$("#dataStoreTypeSelector").select2({allowClear: true});
				
				$("#dataStoreTypeSelector").select2("val",$("#dataStoreTypeSelector").attr("myVal"));
			});
			
			$("#saveDataStoreFieldBtn").click(function(){
				if(type==0){
					$("#editDataStoreFieldForm").attr("action","newDataStoreField");
				}else{
					$("#editDataStoreFieldForm").attr("action","editDataStoreField");
				}
				
				$("#editDataStoreFieldForm").submit();
			});
			
			$("#editDataStoreFieldForm").submit(function(){
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					$("#saveDataStoreFieldBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>