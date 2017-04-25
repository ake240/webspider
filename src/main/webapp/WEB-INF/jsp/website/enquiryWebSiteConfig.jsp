<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站数据源配置</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站数据源配置</h1>
					<a class="btn btn-success" style="float: right;" href="webSiteConfig" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;网站数据源配置</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryWebSiteConfig" id="enquiryWebSiteConfigForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="webSiteConfigName">名称</label>
										 <input type="text" class="form-control" id="webSiteConfigName" name="name">
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
									<button class="btn btn-success" id="searchBtn">搜索</button>
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
												<th>名称</th>
												<th>所属网站</th>
												<th>需要代理IP</th>
												<th>需要固定IP</th>
												<th>需要登录</th>
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
													<td><s:property value='webSiteConfigName'/></td>
													<td><s:property value='webSite.webSiteName'/></td>
													<td>
														<s:if test="needProxyIP==1">
															是
														</s:if>
														<s:else>
															否
														</s:else>
													</td>
													<td>
														<s:if test="needFixedProxyIP==1">
															是
														</s:if>
														<s:else>
															否
														</s:else>
													</td>
													<td>
														<s:if test="needLogin==1">
															是
														</s:if>
														<s:else>
															否
														</s:else>
													</td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<a class="edit-websiteconfig" websiteconfigid="<s:property value='webSiteConfigId'/>">编辑</a>
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
	
	<%@ include file="/WEB-INF/jsp/modal/webSiteModal.jspf" %>
	
	<script type="text/javascript">
		function refresh(){
			$(".edit-websiteconfig").click(function(){
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
			
			$("#enquiryWebSiteConfigForm").submit(function(){
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