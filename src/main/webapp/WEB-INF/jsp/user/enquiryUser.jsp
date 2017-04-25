<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">用户</h1>
					<a class="btn btn-success" style="float: right;" href="user" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;用户</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryUser" id="enquiryUserForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="username">用户名</label>
										 <input type="text" class="form-control" id="username" name="username"/>
									</div>
								</div>
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="statusSelector">用户状态</label>
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
												<th>用户名</th>
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
													<td><s:property value='username'/></td>
													<td class="status">
														<s:if test="status==0">
															不可用
														</s:if>
														<s:else>
															可用
														</s:else>
													</td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td class="updateTime"><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<s:if test="status==0">
															<a class="start-user" status="1" userid="<s:property value='userId'/>">启用</a>
														</s:if>
														<s:else>
															<a class="stop-user" status="0" userid="<s:property value='userId'/>">停用</a>
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
			$(".start-user,.stop-user").click(function(){
				var tr = $(this).parent().parent();
				var userId = $(this).attr("userid");
				var status = $(this).attr("status");
				var obj = $(this);
				
				$.getJSON("changeUserStatus",{userId:userId,status:status},function(data){
					var ret = $.parseJSON(data.jsonResult);
					if(ret.succeed){
						var statusTD = tr.find(".status");
						var updateTimeTD = tr.find(".updateTime");
						if(status==0){
							statusTD.text("不可用");
							obj.text("启用");
							obj.attr("status",1);
						}else{
							statusTD.text("可用");
							obj.text("停用");
							obj.attr("status",0);
						}
						updateTimeTD.text(getFormatDate());
						
					}else{
						alert("错误: " + ret.msg);
					}
				});
			});
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryUserForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
		}
		
		$(function(){
			refresh();
			
			$("#statusSelector").select2({allowClear: true});
			$("#statusSelector").select2("val","");
			
			$("#enquiryUserForm").submit(function(){
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