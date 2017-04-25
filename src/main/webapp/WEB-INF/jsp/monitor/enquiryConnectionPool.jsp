<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>连接池监控</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">连接池</h1>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryConnectionPool" method="post" id="enquiryMachineForm">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										<label for="host">机器</label>
										<select class="form-control form-select2" id="machineSelector" name="host"></select>
									</div>
								</div>
								<div class="col-md-6 column">
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column">
									<input type="hidden" id="isAjaxSearch" name="ajaxSearch" value="true" />
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
									<!--  TODO -->
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
			$(".pool-detail").click(function(){
				var form = $("<form id='editWebSiteConfigForm'></form>");
				var type_input = $("<input type='hidden' name='host'>");
				type_input.attr("value", $("#host").val());
				var id_input = $("<input type='hidden' name='id'>");
				id_input.attr("value", $(this).attr("data-id"));
 				var role_input = $("<input type='hidden' name='role'>");
 				role_input.attr("value", $(this).attr("data-name"));
// enquiryConnectionPool enquiryConnectionDirect

				//form.attr("action", "enquiryConnection");
				form.attr("action", "enquiryConnectionDirect");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
 			    form.append(role_input);
			    $("body").append(form);
			    form.submit();
			    $("#editWebSiteConfigForm").remove();
			});
		}
		
		$(function(){
			refresh();
			
			$.getJSON("findAllSlave", function(data){
				var machineSelector = $("#machineSelector");
				$.each(data.jsonResult, function(key, value){
					machineSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				machineSelector.prepend("<option></option>");
				$("#machineSelector").select2({allowClear: true});
				$("#machineSelector").select2("val","");
			});
			
			$("#enquiryMachineForm").submit(function(){
				var machineHost = $("#machineSelector").find("option:selected").text();
				if(machineHost.length == 0){
					alert("没有选中任何抓取机器");
					return false;
				}
				$("#replace").text("正在加载数据");
				$(this).ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
				return false;
			});
			
		});
	</script>
</body>
</html>