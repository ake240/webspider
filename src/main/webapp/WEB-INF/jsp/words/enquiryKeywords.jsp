<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百度指数关键词列表</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clear-fix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display:inline">百度指数关键词</h1>
					<a class="btn btn-success" style="float:right" href="addKeywordPage" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;百度指数关键词</a>
				</div>
				
				
				
				<!-- 搜索条件 -->				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">搜索条件</h3>
					</div>
					
					<div class="panel-body">
						<form action="enquiryKeywords" id="enquiryKeywordsForm" method="POST">
							<div class="row clearfix">
								<div class="col-md-3 column">
									<div class="form-group">
										<label for="keyword">百度指数关键词</label>
										<input type="text" class="form-control" id="keyword" name="keyword" placeholder="请输入百度关键词"/>
									</div>
								</div>
								<div class="col-md-3 column">
									<div class="form-group">
										<label for=status>关键词状态</label>
										<select class="form-control form-select2" id="keywordStatusSelector" name="status">
											<option value="2">全部</option>
											<option value="1">启用</option>
											<option value="0">停止</option>
										</select>
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
				
				
				<!-- 搜索结果 -->
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
												<th>关键词</th>
												<th>使用状态</th>
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
													<td><s:property value='word'/></td>
													<td>
														<s:if test="active ==0">停用</s:if>
														<s:else>启用</s:else>
														<%-- <s:property value='active'/> --%>
													</td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<s:if test="active ==0">
															<a class="activate-keyword" keywordId="<s:property value='indexId'/>">启用</a>&nbsp;
														</s:if>
														<s:else>
															<a class="deactivate-keyword" keywordId="<s:property value='indexId'/>">停用</a>&nbsp;
														</s:else>
														<a class="viewIndexInfo" keywordId="<s:property value='indexId'/>" keyword="<s:property value='word'/>">查看指数</a>&nbsp;
<%-- 														<a class="edit-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">编辑</a>&nbsp; --%>
<%-- 														<a class="remove-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">删除</a> --%>
<%-- 														<s:if test="webSiteOpsParams.size()>0"> --%>
<%-- 															<a class="edit-websiteopsparam" websiteopsid="<s:property value='webSiteOpsId'/>">编辑参数</a> --%>
<%-- 														</s:if> --%>
<%-- 														<s:else> --%>
<%-- 															<a class="new-websiteopsparam" websiteopsid="<s:property value='webSiteOpsId'/>">新建参数</a> --%>
<%-- 														</s:else> --%>
<%-- 														<a class="copy-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">复制</a> --%>
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
		
		//viewIndexInfo
		$(".viewIndexInfo").click(function(){
				var form = $("<form id='viewIndexInfoForm'></form>");
				var id_input = $("<input type='hidden' name='keyword'>");
				id_input.attr("value", $(this).attr("keyword"));
				form.attr("action", "enquiryIndexInfo");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    //form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#viewIndexInfoForm").remove();
			});
		
		$(".activate-keyword").click(function(){
			var keywordId = $(this).attr("keywordId");
			if(confirm("确认启用")){
				$.getJSON("keywordStatusChange",{keywordId: keywordId, status:1} , function(data){
					var ret = $.parseJSON(data.jsonResult);
					if(ret.succeed){
						$("#enquiryKeywordsForm").submit();
					}else{
						alert(ret.msg);
					}
				});
			}
		});
		
		$(".deactivate-keyword").click(function(){
			var keywordId = $(this).attr("keywordId");
			if(confirm("确认停用")){
				$.getJSON("keywordStatusChange",{keywordId: keywordId, status:0} ,function(data){
					var ret = $.parseJSON(data.jsonResult);
					if(ret.succeed){
						$("#enquiryKeywordsForm").submit();
					}else{
						alert(ret.msg);
					}
				});
			}
		});
		
		
		$(".navPage").click(function(){
			var pageNow = $(this).attr("id");
			$("#pageNow").val(pageNow);
			$("#enquiryKeywordsForm").ajaxSubmit(function(data){
				$("#replace").replaceWith(data);
			});
		});
		
		$("#keywordStatusSelector").select2({allowClear: true});
		
	}
	
	$(function(){
		refresh();
		
		$("#enquiryKeywordsForm").submit(function(){
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