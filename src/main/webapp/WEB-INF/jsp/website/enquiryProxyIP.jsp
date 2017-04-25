<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站代理IP</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站代理IP</h1>
					<!-- <button class="btn btn-success" id="newProxyIPBtn">新建网站代理IP</button> -->
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryProxyIP" id="enquiryProxyIPForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="host">IP</label>
										 <input type="text" class="form-control" name="host">
									</div>
								</div>
								<div class="col-md-6 column">
									<div class="form-group">
										 <label>状态</label>
										 <select class="form-control form-select2" id="statusSelector" name="status">
										 	<option></option>
										 	<option value="1">可用</option>
										 	<option value="0">不可用</option>
										 </select>
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
												<th>IP</th>
												<th>端口</th>
												<!-- <th>所属网站</th> -->
												<th>状态</th>
												<th>创建时间</th>
												<th>更新时间</th>
												<!-- <th>操作</th> -->
											</tr>
										</thead>
										<tbody>
											<s:set name="pageNow" value="pageDTO.pageNow"/>
											<s:set name="pageSize" value="pageDTO.pageSize"/>
											<s:iterator value="pageDTO.list" status="st">
												<tr>
													<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
													<td><s:property value='host'/></td>
													<td><s:property value='port'/></td>
													<%-- <td><s:property value='webSite.webSiteName'/></td> --%>
													<td>
														<s:if test="status==1">
															可用
														</s:if>
														<s:else>
															不可用
														</s:else>
													</td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<%-- <td>
														<a><i proxyipid="<s:property value='id'/>" class="glyphicon glyphicon-edit edit-proxyip icon-size"></i></a>
														<a><i proxyipid="<s:property value='id'/>" class="glyphicon glyphicon-remove remove-proxyip icon-size"></i></a>
													</td> --%>
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
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryProxyIPForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
		}
		
		$(function(){
			
			refresh();
			
			$("#statusSelector").select2("val","");
			$("#statusSelector").select2({allowClear: true});
			
			$("#enquiryProxyIPForm").submit(function(){
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