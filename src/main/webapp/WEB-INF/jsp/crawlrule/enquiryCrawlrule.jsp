<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网页抽取规则</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网页抽取规则</h1>
					<a class="btn btn-success" style="float: right;" href="crawlrule" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;网页抽取规则</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="" method="post" id="enquiryCrawlruleForm">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="crawlruleName">抽取规则名称</label>
										 <input type="text" class="form-control" id="crawlruleName" name="crawlruleName" placeholder="请输入抽取规则名称"/>
									</div>
								</div>
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="statusSelector">状态</label>
										 <select class="form-control form-select2" id="statusSelector" name="status">
										 	<option></option>
										 	<option value="0">未启用</option>
										 	<option value="1">已启用</option>
										 	<option value="2">已停用</option>
										 </select>
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
												<th>规则名称</th>
												<th>操作者</th>
												<th>创建时间</th>
												<th>更新时间</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<s:set name="pageNow" value="pageDTO.pageNow"></s:set>
											<s:set name="pageSize" value="pageDTO.pageSize"></s:set>
											<s:iterator value="pageDTO.list" status="st">
												<tr>
													<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
													<td><s:property value='crawlRuleName'/></td>
													<td><s:property value='operator'/></td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:property value='@com.datayes.webspider.util.DataConvertUtil@statusConverter(status)'/></td>
													<td>
														<a class="edit-crawlrule" crawlruleid="<s:property value='id'/>">编辑</a>&nbsp;
														<s:if test="status==0">
															<a class="publish-crawlrule" crawlruleid="<s:property value='id'/>">启用</a>&nbsp;
														</s:if>
														<s:elseif test="status==1">
															<a class="stop-crawlrule" crawlruleid="<s:property value='id'/>">停用</a>&nbsp;
														</s:elseif>
														<s:if test="status!=2">
															<a class="remove-crawlrule" crawlruleid="<s:property value='id'/>">删除</a>&nbsp;
														</s:if>
														<a class="copy-crawlrule" crawlruleid="<s:property value='id'/>">复制</a>
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
			$(".edit-crawlrule").click(function(){
				var form = $("<form id='editCrawlruleForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='1'>");
				var id_input = $("<input type='hidden' name='crawlruleId'>");
				id_input.attr("value", $(this).attr("crawlruleid"))
				form.attr("action", "crawlrule");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editCrawlruleForm").remove();
			});
			
			$(".copy-crawlrule").click(function(){
				var form = $("<form id='editCrawlruleForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='2'>");
				var id_input = $("<input type='hidden' name='crawlruleId'>");
				id_input.attr("value", $(this).attr("crawlruleid"))
				form.attr("action", "crawlrule");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editCrawlruleForm").remove();
			});
			
			$(".remove-crawlrule").click(function(){
				var crawlruleId = $(this).attr("crawlruleid");
				if(confirm("确认删除?")){
					$.getJSON("changeStatus", {crawlruleId : crawlruleId, status : 2}, function(data){
						var ret = $.parseJSON(data.jsonResult);
						if(ret.succeed){
							$("#enquiryCrawlruleForm").submit();
						}else{
							alert(ret.msg);
						}
					});
				}
			});
			
			$(".publish-crawlrule").click(function(){
				var crawlruleId = $(this).attr("crawlruleid");
				if(confirm("确认启用?")){
					$.getJSON("changeStatus", {crawlruleId : crawlruleId, status : 1}, function(data){
						var ret = $.parseJSON(data.jsonResult);
						if(ret.succeed){
							$("#enquiryCrawlruleForm").submit();
						}else{
							alert(ret.msg);
						}
					});
				}
			});
			
			$(".stop-crawlrule").click(function(){
				var crawlruleId = $(this).attr("crawlruleid");
				if(confirm("确认停用?")){
					$.getJSON("changeStatus", {crawlruleId : crawlruleId, status : 0}, function(data){
						var ret = $.parseJSON(data.jsonResult);
						if(ret.succeed){
							$("#enquiryCrawlruleForm").submit();
						}else{
							alert(ret.msg);
						}
					});
				}
			});
			
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryCrawlruleForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
		}
		
		$(function(){
			refresh();
			
			/* $.getJSON("findAllWebSiteOps", function(data){
				var websiteOperationSelector = $("#websiteOperationSelector");
				$.each(data.jsonResult, function(key, value){
					websiteOperationSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				websiteOperationSelector.prepend("<option></option>");
				$("#websiteOperationSelector").select2({allowClear: true});
			}); */
			
			$("#statusSelector").select2({allowClear: true});
			
			$("#enquiryCrawlruleForm").submit(function(){
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