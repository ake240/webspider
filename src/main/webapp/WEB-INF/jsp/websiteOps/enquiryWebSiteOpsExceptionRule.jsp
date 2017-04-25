<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作类型异常监控</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">操作类型异常监控</h1>
					<a class="btn btn-success" style="float: right;" href="webSiteOpsExceptionRule" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;操作类型异常监控</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryWebSiteOpsExceptionRule" id="enquiryWebSiteOpsExceptionRuleForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="ruleName">名称</label>
										 <input type="text" class="form-control" id="ruleName" name="ruleName" placeholder="请输入名称"/>
									</div>
								</div>
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="webSiteOpsSelector">网站操作类型</label>
										 <select class="form-control form-select2" id="webSiteOpsSelector" name="webSiteOpsId"></select>
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
												<th>异常监控名称</th>
												<th>网站操作类型</th>
												<th>状态</th>
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
													<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
													<td><s:property value='exceptionRuleName'/></td>
													<td><s:property value='webSiteOps.webSiteOpsName'/></td>
													<td class="ruleStatus">
														<s:if test="status==0">
															停用
														</s:if>
														<s:else>
															启用
														</s:else>
													</td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td class="ruleUpdateTime"><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<a class="edit-websiteopsexceptionrule" exceptionruleid="<s:property value='exceptionRuleId'/>">编辑</a>&nbsp;
														<s:if test="status==0">
															<a class="start-websiteopsexceptionrule" status="1" exceptionruleid="<s:property value='exceptionRuleId'/>">启用</a>
														</s:if>
														<s:else>
															<a class="stop-websiteopsexceptionrule" status="0" exceptionruleid="<s:property value='exceptionRuleId'/>">停用</a>
														</s:else>
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
			$(".edit-websiteopsexceptionrule").click(function(){
				var form = $("<form id='editWebSiteOpsExceptionRuleForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='1'>");
				var id_input = $("<input type='hidden' name='exceptionRuleId'>");
				id_input.attr("value", $(this).attr("exceptionruleid"));
				form.attr("action", "webSiteOpsExceptionRule");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editWebSiteOpsExceptionRuleForm").remove();
			});
			
			$(".start-websiteopsexceptionrule,.stop-websiteopsexceptionrule").click(function(){
				var tr = $(this).parent().parent();
				var exceptionRuleId = $(this).attr("exceptionruleid");
				var status = $(this).attr("status");
				var obj = $(this);
				
				$.getJSON("changeExceptionRuleStatus",{exceptionRuleId:exceptionRuleId,status:status},function(data){
					var ret = $.parseJSON(data.jsonResult);
					if(ret.succeed){
						var ruleStatusTD = tr.find(".ruleStatus");
						var ruleUpdateTime = tr.find(".ruleUpdateTime");
						if(status==0){
							ruleStatusTD.text("停用");
							obj.text("启用");
							obj.attr("status",1);
						}else{
							ruleStatusTD.text("启用");
							obj.text("停用");
							obj.attr("status",0);
						}
						ruleUpdateTime.text(getFormatDate());
						
					}else{
						alert("错误: " + ret.msg);
					}
				});
			});
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryWebSiteOpsForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
		}
		
		$(function(){
			refresh();
			
			$.getJSON("findAllWebSiteOps", function(data){
				var webSiteOpsSelector = $("#webSiteOpsSelector");
				$.each(data.jsonResult, function(key, value){
					webSiteOpsSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				webSiteOpsSelector.prepend("<option></option>");
				$("#webSiteOpsSelector").select2({allowClear: true});
				$("#webSiteOpsSelector").select2("val","");
			});
			
			$("#enquiryWebSiteOpsExceptionRuleForm").submit(function(){
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