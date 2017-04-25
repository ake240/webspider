<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>存储字段</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">存储字段</h1>
					<a class="btn btn-success" style="float: right;" href="dataStoreField" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;存储字段</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryDataStoreField" id="enquiryDataStoreFieldForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="dataStoreTypeSelector">存储类型名称</label>
										 <select class="form-control form-select2" id="dataStoreTypeSelector" name="dataStoreTypeId"></select>
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
												<th>存储类型名称</th>
												<th>中文名称</th>
												<th>英文名称</th>
												<th>唯一值</th>
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
													<td><s:property value='dataStoreType.dataStoreTypeCnName'/></td>
													<td><s:property value='fieldCnName'/></td>
													<td><s:property value='fieldEnName'/></td>
													<td>
														<s:if test="isUnique==1">
															是
														</s:if>
														<s:else>
															否
														</s:else>
													</td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<a class="edit-datastorefield" datastorefieldid="<s:property value='dataStoreFieldId'/>">编辑</a>&nbsp;
														<a class="remove-datastorefield" datastorefieldid="<s:property value='dataStoreFieldId'/>">删除</a>&nbsp;
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
	<input type="hidden" id="dataStoreTypeId" value="<s:property value='dataStoreTypeId'/>">
	
	<script type="text/javascript">
		function refresh(){
			$(".edit-datastorefield").click(function(){
				var form = $("<form id='editDataFieldForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='1'>");
				var id_input = $("<input type='hidden' name='dataStoreFieldId'>");
				id_input.attr("value", $(this).attr("datastorefieldid"));
				form.attr("action", "dataStoreField");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editDataFieldForm").remove();
			});
			
			$(".remove-datastorefield").click(function(){
				var dataStoreFieldId = $(this).attr("datastorefieldid");
				if(confirm("确认删除吗?")){
					$.getJSON("deleteDataStoreField",{dataStoreFieldId:dataStoreFieldId}, function(data){
						var ret = $.parseJSON(data.jsonResult);
						if(ret.succeed){
							$("#enquiryDataStoreFieldForm").submit();
						}else{
							alert("错误:" + ret.msg);
						}
					});
				}
			});
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryDataStoreFieldForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
			
		}
		
		$(function(){
			refresh();
			
			$.getJSON("findAllDataStoreType", function(data){
				var dataStoreTypeSelector = $("#dataStoreTypeSelector");
				$.each(data.jsonResult, function(key, value){
					dataStoreTypeSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				dataStoreTypeSelector.prepend("<option></option>");
				$("#dataStoreTypeSelector").select2({allowClear: true});
				
				$("#dataStoreTypeSelector").select2("val",$("#dataStoreTypeId").val());
			});
			
			$("#enquiryDataStoreFieldForm").submit(function(){
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