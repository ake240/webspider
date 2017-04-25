<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务器</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editMachineForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">服务器</h1>
					<button type="button" id="saveMachineBtn" class="btn btn-success">保存服务器</button>
				</div>
				
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
											 <label for="host" class="col-sm-3 control-label">Host<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="host" name="machine.host" value="<s:property value='machine.host'/>" placeholder="请输入host"/>
											 </div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">服务器类型<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<s:if test="type==1">
													<s:radio list="#{'1':'抓取master','2':'抓取机器'}" id="machineType" name="machine.machineType" value="machine.machineType"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'抓取master','2':'抓取机器'}" id="machineType" name="machine.machineType" value="1"/>
												</s:else>
											</div>
										</div>
									</div>
									<div class="col-md-6 column">
										<div class="form-group" id="endPageGrp">
											 <label for="port" class="col-sm-3 control-label">Port<span class="red">&nbsp;*</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="port" name="machine.port" value="<s:property value='machine.port'/>" placeholder="请输入port"/>
											 </div>
										</div>
										<div class="form-group" id="endPageGrp">
											 <label for="desc" class="col-sm-3 control-label">描述<span class="red">&nbsp;*</label>
											 <div class="col-sm-9">
											 	<textarea class="form-control mandatoryInp" id="desc" name="machine.machineDesc"><s:property value='machine.machineDesc'/></textarea>
											 </div>
										</div>
									</div>
								</div>
								
								<div class="row clearfix">
									<div class="col-md-12 column">
										<div class="form-group" id="machineRoleGrp">
											<label for="machineRoleSelector" class="col-sm-2 control-label">服务器角色<span class="red">&nbsp;*</span></label>
											<div class="col-sm-10">
												<select multiple class="form-control form-select2 mandatoryMulSel" id="machineRoleSelector" name="machineRoleIds"></select>
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
		<input type="hidden" name="machineId" value="<s:property value='machine.machineId'/>">
		</form>
	</div>
	<input type="hidden" id="type" value="<s:property value='type'/>">
	<input type="hidden" id="roleIds" value="<s:property value='roleIds'/>">
	
	<script type="text/javascript">
		function refresh(){
		}
		
		$(function(){
			var type = $("#type").val();
			var machineType = $("[name='machine.machineType']:checked").val();
			if(machineType==1){
				$("#machineRoleGrp").hide();
			}
			
			$.getJSON("findAllMachineRole", function(data){
				var machineRoleSelector = $("#machineRoleSelector");
				$.each(data.jsonResult, function(key, value){
					machineRoleSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				machineRoleSelector.prepend("<option></option>");
				$("#machineRoleSelector").select2({allowClear: true});
				
				if(type==1){
					var roleIds = $("#roleIds").val();
					var roleIdArray = roleIds.split(",");
					var select2value = new Array();
					for(var i=0;i<roleIdArray.length;i++){
						var roleId = roleIdArray[i];
						if(roleId){
							select2value.push(roleId);
						}
					}
					$("#machineRoleSelector").select2("val", select2value);
				}
			});
			
			$("#saveMachineBtn").click(function(){
				if(type==0){
					$("#editMachineForm").attr("action","newMachine");
				}else{
					$("#editMachineForm").attr("action","editMachine");
				}
				
				$("#editMachineForm").submit();
			});
			
			$("input[name='machine.machineType']").change(function(){
				var machineType = $(this).val();
				if(machineType==1){
					$("#machineRoleGrp").hide();
				}else{
					$("#machineRoleGrp").show();
				}
			});
			
			$("#editMachineForm").submit(function(){
				var machineType = $("[name='machine.machineType']:checked").val();
				if(machineType==1){
					$("#machineRoleGrp").remove();
				}
				
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					$("#saveMachineBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>