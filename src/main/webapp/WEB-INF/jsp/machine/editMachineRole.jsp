<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>服务器角色</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editMachineRoleForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">服务器角色</h1>
					<button type="button" id="saveMachineRoleBtn" class="btn btn-success">保存服务器角色</button>
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
											 <label for="roleName" class="col-sm-3 control-label">角色名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="roleName" name="machineRole.machineRoleName" value="<s:property value='machineRole.machineRoleName'/>" placeholder="请输入角色名称"/>
											 </div>
										</div>
									</div>
									<div class="col-md-6 column">
										<div class="form-group" id="endPageGrp">
											 <label for="" class="col-sm-3 control-label">网站配置<span class="red">&nbsp;*</label>
											 <div class="col-sm-9">
											 	<select class="form-control form-select2 mandatorySel" id="webSiteConfigSelector" name="webSiteConfigId" websiteconfigid="<s:property value='webSiteConfigId'/>"></select>
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
		<input type="hidden" name="roleId" value="<s:property value='machineRole.machineRoleId'/>">
		</form>
	</div>
	<input type="hidden" id="type" value="<s:property value='type'/>">
	
	<script type="text/javascript">
		function refresh(){
		}
		
		$(function(){
			var type = $("#type").val();
			
			$.getJSON("findAllWebSiteConfig", function(data){
				var webSiteConfigSelector = $("#webSiteConfigSelector");
				$.each(data.jsonResult, function(key, value){
					webSiteConfigSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				webSiteConfigSelector.prepend("<option></option>");
				$("#webSiteConfigSelector").select2({allowClear: true});
				
				var webSiteConfigId = $("#webSiteConfigSelector").attr("websiteconfigid");
				$("#webSiteConfigSelector").select2("val", webSiteConfigId);
			});
			
			$("#saveMachineRoleBtn").click(function(){
				if(type==0){
					$("#editMachineRoleForm").attr("action","newMachineRole");
				}else{
					$("#editMachineRoleForm").attr("action","editMachineRole");
				}
				
				$("#editMachineRoleForm").submit();
			});
			
			$("#editMachineRoleForm").submit(function(){
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					$("#saveMachineRoleBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>