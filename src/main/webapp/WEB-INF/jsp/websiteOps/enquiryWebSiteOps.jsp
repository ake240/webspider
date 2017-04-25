<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站操作类型</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站操作类型</h1>
					<a class="btn btn-success" style="float: right;" href="webSiteOps" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;网站操作类型</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryWebSiteOps" id="enquiryWebSiteOpsForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="webSiteOpsName">操作类型名称</label>
										 <input type="text" class="form-control" id="webSiteOpsName" name="webSiteOpsName" placeholder="请输入操作类型名称"/>
									</div>
								</div>
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="webSiteSelector">所属网站</label>
										 <select class="form-control form-select2" id="webSiteSelector" name="webSiteId"></select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column">
									<input type="hidden" id="pageNow" name="pageNow" />
									<input type="hidden" id="isAjaxSearch" name="isAjaxSearch" value="1" />
									<button class="btn btn-success">搜索</button>
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
												<th>操作类型名称</th>
												<th>所属网站</th>
												<th>机器角色</th>
												<th>创建时间</th>
												<th>修改时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<s:set name="pageNow" value="pageDTO.pageNow"></s:set>
											<s:set name="pageSize" value="pageDTO.pageSize"></s:set>
											<s:iterator value="pageDTO.list" status="st">
												<tr>
													<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/>-<s:property value='webSiteOpsId'/></td>
													<td><s:property value='webSiteOpsName'/></td>
													<td><s:property value='webSite.webSiteName'/></td>
													<td><s:property value='machineRole.machineRoleName'/></td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<a class="edit-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">编辑</a>&nbsp;
														<a class="remove-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">删除</a>
														<s:if test="webSiteOpsParams.size()>0">
															<a class="edit-websiteopsparam" websiteopsid="<s:property value='webSiteOpsId'/>">编辑参数</a>
														</s:if>
														<s:else>
															<a class="new-websiteopsparam" websiteopsid="<s:property value='webSiteOpsId'/>">新建参数</a>
														</s:else>
														<a class="copy-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">复制</a>&nbsp;
														<a class="enquiry-taskFI" websiteopsid="<s:property value='webSiteOpsId'/>">抓取项管理</a>
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
			$(".edit-websiteops").click(function(){
				var form = $("<form id='editWebSiteOpsForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='1'>");
				var id_input = $("<input type='hidden' name='webSiteOpsId'>");
				id_input.attr("value", $(this).attr("websiteopsid"));
				form.attr("action", "webSiteOps");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editWebSiteOpsForm").remove();
			});
			
			$(".copy-websiteops").click(function(){
				var form = $("<form id='editWebSiteOpsForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='2'>");
				var id_input = $("<input type='hidden' name='webSiteOpsId'>");
				id_input.attr("value", $(this).attr("websiteopsid"));
				form.attr("action", "webSiteOps");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editWebSiteOpsForm").remove();
			});
			
			$(".remove-websiteops").click(function(){
				var webSiteOpsId = $(this).attr("websiteopsid");
				if(confirm("确认删除吗?")){
					$.getJSON("deleteWebSiteOps",{webSiteOpsId:webSiteOpsId}, function(data){
						var ret = $.parseJSON(data.jsonResult);
						if(ret.succeed){
							$("#enquiryWebSiteOpsForm").submit();
						}else{
							alert("错误:" + ret.msg);
						}
					});
				}
			});
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryWebSiteOpsForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
			
			$(".edit-websiteopsparam").click(function(){
				var form = $("<form id='editWebSiteOpsParamForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='1'>");
				var id_input = $("<input type='hidden' name='webSiteOpsId'>");
				id_input.attr("value", $(this).attr("websiteopsid"));
				form.attr("action", "webSiteOpsParam");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editWebSiteOpsParamForm").remove();
			});
			
			$(".new-websiteopsparam").click(function(){
				var form = $("<form id='newWebSiteOpsParamForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='0'>");
				var id_input = $("<input type='hidden' name='webSiteOpsId'>");
				id_input.attr("value", $(this).attr("websiteopsid"));
				form.attr("action", "webSiteOpsParam");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#newWebSiteOpsParamForm").remove();
			});
			
			$(".enquiry-taskFI").click(function(){
				var form = $("<form id='quiryTaskForm'></form>");
				var id_input = $("<input type='hidden' name='webSiteOpsId'>");
				id_input.attr("value", $(this).attr("websiteopsid"));
				form.attr("action", "enquiryTaskFI");
			    form.attr("method", "get");
			    form.attr("target", "_blank");
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			   $("#quiryTaskForm").remove();
			});
			
		}
		
		$(function(){
			refresh();
			
			$.getJSON("findAllWebSite", function(data){
				var webSiteSelector = $("#webSiteSelector");
				$.each(data.jsonResult, function(key, value){
					webSiteSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				webSiteSelector.prepend("<option></option>");
				$("#webSiteSelector").select2({allowClear: true});
				$("#webSiteSelector").select2("val","");
			});
			
			$("#enquiryWebSiteOpsForm").submit(function(){
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