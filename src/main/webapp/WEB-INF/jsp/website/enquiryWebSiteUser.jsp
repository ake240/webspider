<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站用户</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站用户</h1>
					<button class="btn btn-success" id="newWebSiteUserBtn">新建网站用户</button>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryWebSiteUser" id="enquiryWebSiteUserForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="account">用户名称</label>
										 <input type="text" class="form-control" id="account" name="account" placeholder="请输入用户名称"/>
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
												<th>用户名称</th>
												<th>用户密码</th>
												<th>所属网站</th>
												<th>信息</th>
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
													<td><s:property value='account'/></td>
													<td><s:property value='password'/></td>
													<td><s:property value='webSite.webSiteName'/></td>
													<td class="messageTD"><s:property value='message'/></td>
													<td class="statusTD">
														<s:if test="status==1">
															可用
														</s:if>
														<s:else>
															不可用
														</s:else>
													</td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td class="updateTimeTD"><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td>
														<a class="edit-websiteuser" websiteuserid="<s:property value='id'/>" account="<s:property value='account'/>" password="<s:property value='password'/>" websiteid="<s:property value='webSite.webSiteId'/>">编辑</a>&nbsp;
														<%-- <a class="remove-websiteuser" websiteuserid="<s:property value='webSiteUserId'/>">删除</a>&nbsp; --%>
														<s:if test="status==1">
															<a class="stop-websiteuser" websiteuserid="<s:property value='id'/>" status="0">停用</a>
														</s:if>
														<s:else>
															<a class="start-websiteuser" websiteuserid="<s:property value='id'/>" status="1">启用</a>
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
	
	<%@ include file="/WEB-INF/jsp/modal/webSiteUserModal.jspf" %>
	
	<script type="text/javascript">
		function refresh(){
			$(".edit-websiteuser").click(function(){
				$("#webSiteUserLabel").text("编辑网站用户");
				$("#editWebSiteUserForm").attr("action","editWebSiteUser");
				$("#accountEdit").val($(this).attr("account"));
				$("#passwordEdit").val($(this).attr("password"));
				$("#newWebSiteSelector").select2("val", $(this).attr("websiteid"));
				$("#webSiteUserId").val($(this).attr("websiteuserid"));
				$("#webSiteUserModal").modal();
			});
			
			$(".remove-websiteuser").click(function(){
				if(confirm("确认删除吗?")){
					
				}
			});
			
			$(".start-websiteuser, .stop-websiteuser").click(function(){
				var tr = $(this).parent().parent();
				var messageTD = tr.find(".messageTD");
				var statusTD = tr.find(".statusTD");
				var updateTimeTD = tr.find(".updateTimeTD");
				var obj = $(this);
				var webSiteUserId = $(this).attr("websiteuserid");
				var status = $(this).attr("status");
				
				$.getJSON("changeWebSiteUserStatus",{webSiteUserId:webSiteUserId,status:status},function(data){
					var ret = $.parseJSON(data.jsonResult);
					if(ret.succeed){
						if(status==0){
							statusTD.text("不可用");
							obj.text("启用");
							obj.attr("status",1);
						}else{
							statusTD.text("可用");
							obj.text("停用");
							obj.attr("status",0);
							messageTD.text("");
						}
						updateTimeTD.text(getFormatDate());
						
					}else{
						alert("错误: " + ret.msg);
					}
				});
			});
		}
		
		$(function(){
			refresh();
			
			$.getJSON("findAllWebSite", function(data){
				var webSiteSelector = $("#webSiteSelector");
				var newWebSiteSelector = $("#newWebSiteSelector");
				$.each(data.jsonResult, function(key, value){
					webSiteSelector.prepend("<option value='" + key + "'>" + value + "</option>");
					newWebSiteSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				webSiteSelector.prepend("<option></option>");
				newWebSiteSelector.prepend("<option></option>");
				$("#webSiteSelector").select2({allowClear: true});
				$("#webSiteSelector").select2("val","");
				$("#newWebSiteSelector").select2({allowClear: true});
				$("#newWebSiteSelector").select2("val","");
			});
			
			$("#newWebSiteUserBtn").click(function(){
				$("#webSiteUserLabel").text("新建网站用户");
				$("#editWebSiteUserForm").attr("action","newWebSiteUser");
				$("#accountEdit").val("");
				$("#passwordEdit").val("");
				$("#newWebSiteSelector").select2("val","");
				$("#webSiteUserModal").modal();
			});
			
			$("#enquiryWebSiteUserForm").submit(function(){
				$("#pageNow").val(1);
				$(this).ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
				return false;
			});
			
			$("#editWebSiteUserForm").submit(function(){
				var ret = checkMandatory();
				if(ret){
					$(this).ajaxSubmit(function(json){
						var result = $.parseJSON(json.jsonResult);
						if(result.succeed){
							$("#editWebSiteUserForm").resetForm();
							$("#webSiteUserModal").modal('hide');
							$("#enquiryWebSiteUserForm").submit();
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