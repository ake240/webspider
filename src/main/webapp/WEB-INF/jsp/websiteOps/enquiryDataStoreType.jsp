<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>存储类型</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">存储类型</h1>
					<a class="btn btn-success" style="float: right;" href="dataStoreType" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;存储类型</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryDataStoreType" id="enquiryDataStoreTypeForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="dataStoreTypeCnName">存储类型名称</label>
										 <input type="text" class="form-control" id="dataStoreTypeCnName" name="dataStoreTypeCnName" placeholder="请输入存储类型名称"/>
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
												<th>表名</th>
												<!-- <th>类名</th> -->
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
													<td><s:property value='dataStoreTypeCnName'/></td>
													<td><s:property value='collectionName'/></td>
												<!-- <td><s:property value='dataStoreTypeClass'/></td>  -->
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<a class="edit-datastoretype" datastoretypeid="<s:property value='dataStoreTypeId'/>">编辑</a>&nbsp;
														<a class="remove-datastoretype" datastoretypeid="<s:property value='dataStoreTypeId'/>">删除</a>&nbsp;
														<a class="show-datastorefield" datastoretypeid="<s:property value='dataStoreTypeId'/>">查看存储字段</a>
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
			$(".edit-datastoretype").click(function(){
				var form = $("<form id='editDataStoreTypeForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='1'>");
				var id_input = $("<input type='hidden' name='dataStoreTypeId'>");
				id_input.attr("value", $(this).attr("datastoretypeid"));
				form.attr("action", "dataStoreType");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editDataStoreTypeForm").remove();
			});
			
			$(".remove-datastoretype").click(function(){
				var dataStoreTypeId = $(this).attr("datastoretypeid");
				if(confirm("确认删除吗?")){
					$.getJSON("deleteDataStoreType",{dataStoreTypeId:dataStoreTypeId}, function(data){
						var ret = $.parseJSON(data.jsonResult);
						if(ret.succeed){
							$("#enquiryDataStoreTypeForm").submit();
						}else{
							alert("错误:" + ret.msg);
						}
					});
				}
			});
			
			$(".show-datastorefield").click(function(){
				var form = $("<form id='enquiryDataStoreFieldForm'></form>");
				var id_input = $("<input type='hidden' name='dataStoreTypeId'>");
				id_input.attr("value", $(this).attr("datastoretypeid"));
				form.attr("action", "enquiryDataStoreField");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#enquiryDataStoreFieldForm").remove();
			});
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryDataStoreTypeForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
			
		}
		
		$(function(){
			refresh();
			
			$("#enquiryDataStoreTypeForm").submit(function(){
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