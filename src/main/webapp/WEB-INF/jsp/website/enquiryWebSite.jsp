<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站</h1>
					<button class="btn btn-success" id="newWebSiteBtn">新建网站</button>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryWebSite" id="enquiryWebSiteForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="webSiteName">网站名称</label>
										 <input type="text" class="form-control" id="webSiteName" name="webSiteName" placeholder="请输入网站名称"/>
									</div>
								</div>
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="categorySelector">网站分类</label>
										 <select class="form-control form-select2" id="categorySelector" name="categoryId"></select>
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
												<th>网站名称</th>
												<th>网站分类</th>
												<th>创建时间</th>
												<th>更新时间</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<s:set name="pageNow" value="pageDTO.pageNow"/>
											<s:set name="pageSize" value="pageDTO.pageSize"/>
											<s:iterator value="pageDTO.list" status="st">
												<s:set name="categoryNames" value="''"/>
												<s:set name="categoryIds" value="''"/>
												<tr>
													<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
													<td><s:property value='webSiteName'/></td>
													<td>
														<s:iterator value="categories" status="">
															<s:set name="categoryNames" value="#categoryNames + categoryName + ' | '"/>
															<s:set name="categoryIds" value="#categoryIds + categoryId + ','"/>
														</s:iterator>
														<span class="convertCategoryNames"><s:property value="#categoryNames"/></span>
													</td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<a><i websiteid="<s:property value='webSiteId'/>" websitename="<s:property value='webSiteName'/>" categoryids="<s:property value='#categoryIds'/>" class="glyphicon glyphicon-edit edit-website icon-size"></i></a>
														<a><i websiteid="<s:property value='webSiteId'/>" class="glyphicon glyphicon-remove remove-website icon-size"></i></a>
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
			$(".edit-website").click(function(){
				$("#webSiteLabel").text("编辑网站");
				$("#editWebSiteForm").attr("action","editWebSite");
				$("#webSiteNameEdit").val($(this).attr("websitename"));
				var categoryids = $(this).attr("categoryids");
				var categoryidArray = categoryids.split(",");
				var select2value = new Array();
				for(var i=0;i<categoryidArray.length;i++){
					var categoryId = categoryidArray[i];
					if(categoryId){
						select2value.push(categoryId);
					}
				}
				$("#webSiteId").val($(this).attr("websiteid"));
				$("#editCategorySelector").select2("val", select2value);
				$("#webSiteModal").modal();
			});
			
			$(".remove-website").click(function(){
				if(confirm("确认删除吗?")){
					
				}
			});
			
			$(".convertCategoryNames").each(function(){
				var categoryNames = $(this).text();
				categoryNames = categoryNames.substring(0,categoryNames.length-2);
				$(this).text(categoryNames);
			});
		}
		
		$(function(){
			
			/* $(".navbar-default .navbar-nav > li > a").hover(
				function () {
					$(".open").removeClass("open");
					$(this).parent().addClass("open");
			  	}
			); */
			
			refresh();
			
			$.getJSON("findAllCategory", function(data){
				var categorySelector = $("#categorySelector");
				var editCategorySelector = $("#editCategorySelector");
				$.each(data.jsonResult, function(key, value){
					categorySelector.prepend("<option value='" + key + "'>" + value + "</option>");
					editCategorySelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				categorySelector.prepend("<option></option>");
				$("#categorySelector").select2({allowClear: true});
				$("#categorySelector").select2("val","");
				$("#editCategorySelector").select2({allowClear: true});
			});
			
			$("#newWebSiteBtn").click(function(){
				$("#webSiteLabel").text("新建网站");
				$("#editWebSiteForm").attr("action","newWebSite");
				$("#webSiteNameEdit").val("");
				$("#editCategorySelector").select2("val","");
				$("#webSiteModal").modal();
			});
			
			$("#enquiryWebSiteForm").submit(function(){
				$("#pageNow").val(1);
				$(this).ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
				return false;
			});
			
			$("#editWebSiteForm").submit(function(){
				var ret = checkMandatory();
				if(ret){
					$(this).ajaxSubmit(function(json){
						var result = $.parseJSON(json.jsonResult);
						if(result.succeed){
							$("#editWebSiteForm").resetForm();
							$("#webSiteModal").modal('hide');
							$("#enquiryWebSiteForm").ajaxSubmit(function(data){
								$("#replace").replaceWith(data);
							});
						}else{
							alert(result.msg);
						}
					});
				}else{
					alert("必填字段不允许为空");
				}
				return false;
			});
			
		});
	</script>
</body>
</html>