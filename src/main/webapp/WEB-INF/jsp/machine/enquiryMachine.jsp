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
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">服务器</h1>
					<a class="btn btn-success" style="float: right;" href="machine" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;服务器</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryMachine" method="post" id="enquiryMachineForm">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										<label for="host">机器</label>
										<select class="form-control form-select2" id="machineSelector" name="host"></select>
									</div>
									<div class="form-group">
										<label for="machineTypeSelector">机器类型</label>
										<select class="form-control form-select2" id="machineTypeSelector" name="machineType">
											<option></option>
											<option value="1">抓取master</option>
											<option value="2">抓取机器</option>
										</select>
									</div>
								</div>
								<div class="col-md-6 column">
									<div class="form-group">
										<label for="machineRoleSelector">机器角色</label>
										<select class="form-control form-select2" id="machineRoleSelector" name="machineRoleId" myVal="<s:property value='machineRoleId'/>"></select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column">
									<input type="hidden" id="pageNow" name="pageNow" />
									<input type="hidden" id="isAjaxSearch" name="isAjaxSearch" value="1" />
									<button type="submit" class="btn btn-success" id="searchBtn">搜索</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索结果
						</h3>
					</div>
					<div class="panel-body">
						<div class="row clearfix">
							<div class="col-md-12 column">
								<div id="replace">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>机器</th>
												<th>端口</th>
												<th>类型</th>
												<th>角色</th>
												<th>描述</th>
												<th>创建时间</th>
												<th>更新时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<s:set name="pageNow" value="pageDTO.pageNow"></s:set>
											<s:set name="pageSize" value="pageDTO.pageSize"></s:set>
											<s:iterator value="pageDTO.list" status="st">
												<tr>
													<s:set name="roleNames" value="''"/>
													<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
													<td><s:property value='host'/></td>
													<td><s:property value='port'/></td>
													<td><s:property value='@com.datayes.webspider.util.DataConvertUtil@machineTypeConverter(machineType)'/></td>
													<td>
														<s:iterator value="machineRoles" status="">
															<s:set name="roleNames" value="#roleNames + machineRoleName + ' | '"/>
														</s:iterator>
														<span class="convertRoleNames"><s:property value="#roleNames"/></span>
													</td>
													<td><s:property value='machineDesc'/></td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<a><i class="glyphicon glyphicon-edit edit-machine icon-size" machineid="<s:property value='machineId'/>"></i></a>
														<%-- <a><i class="glyphicon glyphicon-remove remove-machine icon-size" machineid="<s:property value='machineId'/>"></i></a> --%>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function refresh(){
			$(".edit-machine").click(function(){
				var form = $("<form id='editMachineForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='1'>");
				var id_input = $("<input type='hidden' name='machineId'>");
				id_input.attr("value", $(this).attr("machineid"));
				form.attr("action", "machine");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editMachineForm").remove();
			});
			
			$(".remove-machine").click(function(){
				
			});
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryMachineForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
			
			$(".convertRoleNames").each(function(){
				var roleNames = $(this).text();
				roleNames = roleNames.substring(0,roleNames.length-2);
				$(this).text(roleNames);
			});
		}
		
		$(function(){
			refresh();
			
			$("#machineTypeSelector").select2({allowClear: true});
			
			$.getJSON("findAllMachineRole", function(data){
				var machineRoleSelector = $("#machineRoleSelector");
				$.each(data.jsonResult, function(key, value){
					machineRoleSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				machineRoleSelector.prepend("<option></option>");
				$("#machineRoleSelector").select2({allowClear: true});
				var machineRoleId = $("#machineRoleSelector").attr("myVal");
				$("#machineRoleSelector").select2("val", machineRoleId);
			});
			
			$.getJSON("findAllMachine", function(data){
				var machineSelector = $("#machineSelector");
				$.each(data.jsonResult, function(key, value){
					machineSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				machineSelector.prepend("<option></option>");
				$("#machineSelector").select2({allowClear: true});
				$("#machineSelector").select2("val","");
			});
			
			$("#enquiryMachineForm").submit(function(){
				$("#pageNow").val(1);
				$(this).ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
				return false;
			});
			
		});
	</script>
</body>
</html>