<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百度指数关键数据</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clear-fix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display:inline">百度指数关键数据</h1>
					<!-- <a class="btn btn-success" style="float:right" href="addKeywordPage" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;百度指数关键词</a> -->
				</div>
				
				
				
				<!-- 搜索条件 -->				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">搜索条件</h3>
					</div>
					
					<div class="panel-body">
						<form action="enquiryIndexInfo" id="enquiryIndexInfoForm" method="POST">
							<div class="row clearfix">
								<div class="col-md-3 column">
									<div class="form-group">
										<label for="keyword">百度指数关键词</label>
										<select class="form-control form-select2" id="keywordSelector" name="keyword" myVal="<s:property value='keyword'/>"></select>
										<!-- <input type="text" class="form-control" id="keyword" name="keyword" placeholder="请输入百度关键词"/> -->
									</div>
								</div>
								<div class="col-md-3 column">
									<div class="form-group">
										<label for=status>指数类型</label>
										<select class="form-control form-select2" id="keywordStatusSelector" name="indexType">
											<option value="">全部</option>
											<option value="pc">pc</option>
											<option value="all">all</option>
											<option value="wise">wise</option>
										</select>
									</div>
								</div>
							</div>
							
							<div class="row clear-fix">
								<div class="col-md-3 column">
									<div class="form-group">
		                                <label for="clawstart">开始时间</label>
		                                <input id="clawstart" type="text" name="beginDate"
		                                       value="<s:date format='yyyy-MM-dd' name="beginDate"/>" placeholder="开始日期"
		                                       onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control Wdate"
		                                       autocomplete="off">
									</div>
	                            </div>
	                            <div class="col-md-3 column">
	                            	<div class="form-group">
		                                <label for="clawend">结束时间</label>
		                                <input type="text" id="clawend" name="endDate"
		                                       value="<s:date format='yyyy-MM-dd' name="beginDate"/>" placeholder="结束日期"
		                                       onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control Wdate"
		                                       autocomplete="off">
	                            	</div>
	
	                            </div>
							</div>
							
							<div class="row clear-fix">
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
												<th>指数类型</th>
												<th>日期</th>
												<th>指数值</th>
												<th>创建时间</th>
												<th>更新时间</th>
											</tr>
										</thead>
										<tbody>
											<s:set name="pageNow" value="pageDTO.pageNow"></s:set>
											<s:set name="pageSize" value="pageDTO.pageSize"></s:set>
											<s:iterator value="pageDTO.list" status="st">
												<tr>
													<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
													<td><s:property value='indexWord'/></td>
													<td><s:property value='indexType'/></td>
													<td><s:date name="indexDate" format="yyyy-MM-dd"/></td>
													<td><s:property value='indexNum'/></td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
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
		
		
		
		
		$(".navPage").click(function(){
			var pageNow = $(this).attr("id");
			$("#pageNow").val(pageNow);
			$("#enquiryIndexInfoForm").ajaxSubmit(function(data){
				$("#replace").replaceWith(data);
			});
		});
		
		$("#keywordStatusSelector").select2({allowClear: true});
		
	}
	
	$(function(){
		refresh();
		
		
		$.getJSON("findAllKeyword", function(data){
			var keywordSelector = $("#keywordSelector");
			$.each(data.jsonResult, function(key, value){
				keywordSelector.prepend("<option value='" + key + "'>" + value + "</option>");
			});
			keywordSelector.prepend("<option></option>");
			$("#keywordSelector").select2({allowClear: true});
			
			$("#keywordSelector").select2("val",$("#keywordSelector").attr("myVal"));
		});
		
		
		$("#enquiryIndexInfoForm").submit(function(){
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