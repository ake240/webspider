<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站分类</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站分类</h1>
					<button class="btn btn-success" id="newCategoryBtn">新建网站分类</button>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryCategory" method="post" id="enquiryCategoryForm">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="categoryName">分类名称</label>
										 <input type="text" class="form-control" id="categoryName" name="categoryName" placeholder="请输入分类名称"/>
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
												<th>分类名称</th>
												<th>父分类</th>
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
													<td><s:property value='categoryName'/></td>
													<td><s:property value='parentCategory.categoryName'/></td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<a><i class="glyphicon glyphicon-edit edit-category icon-size" categoryid="<s:property value='categoryId'/>" pcategoryid="<s:property value='parentCategory.categoryId'/>" categoryname="<s:property value='categoryName'/>"></i></a>
														<a><i class="glyphicon glyphicon-remove remove-category icon-size" categoryid="<s:property value='categoryId'/>" categoryname="<s:property value='categoryName'/>"></i></a>
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
			$(".edit-category").click(function(){
				$("#categoryLabel").text("编辑网站分类");
				$("#categoryForm").attr("action", "editCategory");
				$("#categoryNameEdit").val($(this).attr("categoryname"));
				var pcategoryId = $(this).attr("pcategoryid");
				if(pcategoryId){
					$("#parentCategorySelector").select2("val", pcategoryId);
				}else{
					$("#parentCategorySelector").select2("val", "");
				}
				$("#categoryId").val($(this).attr("categoryid"));
				$("#categoryModal").modal();
			});
			
			$(".remove-category").click(function(){
				if(confirm("确认删除 '" + $(this).attr("categoryname") +"' 吗?")){
					var categoryId = $(this).attr("categoryid");
					$.getJSON("removeCategory", {categoryId : categoryId}, function(data){
						var result = $.parseJSON(data.jsonResult);
						if(result.succeed){
							$("#enquiryCategoryForm").ajaxSubmit(function(data){
								$("#replace").replaceWith(data);
							});
						}else{
							alert("错误：" + result.msg);
						}
					});
				}
			});
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryCategoryForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
		}
		
		$(function(){
			refresh();
			
			$("#newCategoryBtn").click(function(){
				$("#categoryLabel").text("新建网站分类");
				$("#categoryForm").attr("action", "newCategory");
				$("#categoryNameEdit").val("");
				$("#parentCategorySelector").select2("val", "");
				$("#categoryModal").modal();
			});
			
			$.getJSON("findAllCategory", function(data){
				var parentCategorySelector = $("#parentCategorySelector");
				$.each(data.jsonResult, function(key, value){
					parentCategorySelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				parentCategorySelector.prepend("<option></option>");
				$("#parentCategorySelector").select2({allowClear: true});
			});
			
			$("#enquiryCategoryForm").submit(function(){
				$("#pageNow").val(1);
				$(this).ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
				return false;
			});
			
			$("#categoryForm").submit(function(){
				var categoryName = $("#categoryNameEdit").val();
				
				if(categoryName){
					$(this).ajaxSubmit(function(json){
						var result = $.parseJSON(json.jsonResult);
						if(result.succeed){
							$("#categoryForm").resetForm();
							$("#categoryModal").modal('hide');
							$("#enquiryCategoryForm").ajaxSubmit(function(data){
								$("#replace").replaceWith(data);
							});
						}else{
							alert(result.msg);
						}
					});
				}else{
					//TODO has-error
				}
				return false;
			});
			
		});
	</script>
</body>
</html>