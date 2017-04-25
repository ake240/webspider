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
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">服务器角色</h1>
					<a class="btn btn-success" style="float: right;" href="machineRole" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;服务器角色</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryMachineRole" method="post" id="enquiryMachineRoleForm">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="roleName">角色名称</label>
										 <input type="text" class="form-control" id="roleName" name="roleName"/>
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
												<th>角色名称</th>
												<th>网站配置</th>
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
													<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
													<td><s:property value='machineRoleName'/></td>
													<td><a class="show-websiteconfig" websiteconfigid="<s:property value='webSiteConfig.webSiteConfigId'/>"><s:property value='webSiteConfig.webSiteConfigName'/><a></td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<a class="edit-machinerole" roleid="<s:property value='machineRoleId'/>">编辑</a>&nbsp;
														<%-- <a class="remove-machinerole" roleid="<s:property value='machineRoleId'/>">删除</a>&nbsp; --%>
														<a class="show-machine" roleid="<s:property value='machineRoleId'/>">查看机器</a>
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
	
	<%@ include file="/WEB-INF/jsp/modal/categoryModal.jspf" %>
	
	<script type="text/javascript">
		function refresh(){
			$(".edit-machinerole").click(function(){
				var form = $("<form id='editMachineRoleForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='1'>");
				var id_input = $("<input type='hidden' name='roleId'>");
				id_input.attr("value", $(this).attr("roleid"));
				form.attr("action", "machineRole");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editMachineRoleForm").remove();
			});
			
			$(".show-websiteconfig").click(function(){
				var form = $("<form id='editWebSiteConfigForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='1'>");
				var id_input = $("<input type='hidden' name='webSiteConfigId'>");
				id_input.attr("value", $(this).attr("websiteconfigid"));
				form.attr("action", "webSiteConfig");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editWebSiteConfigForm").remove();
			});

			$(".remove-machinerole").click(function(){
				
			});
			
			$(".show-machine").click(function(){
				var form = $("<form id='showMachineForm'></form>");
				var id_input = $("<input type='hidden' name='machineRoleId'>");
				id_input.attr("value", $(this).attr("roleid"));
				form.attr("action", "enquiryMachine");
			    form.attr("method", "get");
			    form.attr("target", "_blank");
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#showMachineForm").remove();
			});
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryMachineRoleForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
			
		}
		
		$(function(){
			refresh();
			
			$("#enquiryMachineRoleForm").submit(function(){
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