<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务管理</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">任务管理</h1>
					<a class="btn btn-success" style="float: right;" href="task" target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;任务</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryTask" id="enquiryTaskForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="procHost">执行机器</label>
										 <select class="form-control form-select2" id="machineSelector" name="procHost"></select>
									</div>
									<div class="form-group">
										 <label for="taskName">任务名称</label>
										 <input type="text" class="form-control" id="taskName" name="taskName" placeholder="请输入任务名称"/>
									</div>
									
								</div>
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="websiteOperationSelector">操作类型</label>
										 <select class="form-control form-select2" id="websiteOperationSelector" name="webSiteOpsId"></select>
									</div>
									<div class="form-group">
										 <label for="taskStatusSelector">任务状态</label>
										 <select class="form-control form-select2" id="taskStatusSelector" name="taskStatus">
										 	<option></option>
										 	<option value="-2">等待手动开始执行</option>
										 	<option value="-1">等待程序开始执行</option>
										 	<option value="0">开始执行</option>
										 	<option value="1">执行中</option>
										 	<option value="2">执行完成</option>
										 	<option value="3">定时任务执行中</option>
										 	<option value="4">定时任务执行完成</option>
										 	<option value="9">执行出错</option>
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
												<th>任务中文名称</th>
												<th>任务英文名称</th>
												<th>操作类型</th>
												<th>执行机器</th>
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
													<td><s:property value='taskName'/></td>
													<td><s:property value='taskEnName'/></td>
													<td><s:property value='webSiteOps.webSiteOpsName'/></td>
													<td><s:property value='procHost'/></td>
													<td class="taskStatus"><s:property value='@com.datayes.webspider.util.DataConvertUtil@taskStatusConverter(taskStatus)'/></td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td class="taskUpdateTime"><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td width="150px">
														<a class="edit-task" taskid="<s:property value='taskId'/>">编辑</a>&nbsp;
														<a class="copy-task" taskid="<s:property value='taskId'/>">复制</a>
														<a class="enquiry-taskFI" websiteopsid="<s:property value='webSiteOps.webSiteOpsId'/>">抓取项管理</a>
														<s:if test="taskStatus==-1">
															<a class="start-task" taskid="<s:property value='taskId'/>">启动</a>
														</s:if>
														<s:if test="taskStatus==2 || taskStatus==9">
															<a class="restart-task" taskid="<s:property value='taskId'/>">重新启动</a>
														</s:if>
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
			$(".edit-task").click(function(){
				var form = $("<form id='editTaskForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='1'>");
				var id_input = $("<input type='hidden' name='taskId'>");
				id_input.attr("value", $(this).attr("taskid"));
				form.attr("action", "task");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editTaskForm").remove();
			});
			
			$(".copy-task").click(function(){
				var form = $("<form id='editTaskForm'></form>");
				var type_input = $("<input type='hidden' name='type' value='2'>");
				var id_input = $("<input type='hidden' name='taskId'>");
				id_input.attr("value", $(this).attr("taskid"));
				form.attr("action", "task");
			    form.attr("method", "post");
			    form.attr("target", "_blank");
			    form.append(type_input);
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			    $("#editTaskForm").remove();
			});
			
			$(".start-task,.restart-task").click(function(){
				var tr = $(this).parent().parent();
				var taskId = $(this).attr("taskid");
				var now = getFormatDate();
				var obj = $(this);
				
				$.getJSON("changeTaskStatus",{taskId:taskId,status:0},function(data){
					var ret = $.parseJSON(data.jsonResult);
					if(ret.succeed){
						var taskStatusTD = tr.find(".taskStatus");
						var taskUpdateTime = tr.find(".taskUpdateTime");
						taskStatusTD.text("开始执行");
						taskUpdateTime.text(now);
						obj.remove();
					}else{
						alert("错误: " + ret.msg);
					}
				});
			});
			
			$(".enquiry-taskFI").click(function(){
				var form = $("<form id='quiryTaskForm'></form>");
				var id_input = $("<input type='hidden' name='webSiteOpsId'>");
				id_input.attr("value", $(this).attr("websiteopsid"));
				form.attr("action", "enquiryTaskFI");
			    form.attr("method", "get");
			    form.attr("target", "_blank");
			    form.append(id_input);
			    $("body").append(form);
			    form.submit();
			   $("#quiryTaskForm").remove();
			});
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryTaskForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
		}
		
		$(function(){
			refresh();
			
			$.getJSON("findAllWebSiteOps", function(data){
				var websiteOperationSelector = $("#websiteOperationSelector");
				$.each(data.jsonResult, function(key, value){
					websiteOperationSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				websiteOperationSelector.prepend("<option></option>");
				$("#websiteOperationSelector").select2({allowClear: true});
				$("#websiteOperationSelector").select2("val","");
			});
			
			$.getJSON("findAllMachine", function(data){
				var machineSelector = $("#machineSelector");
				$.each(data.jsonResult, function(key, value){
					machineSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				machineSelector.prepend("<option></option>");
				$("#machineSelector").select2({allowClear: true});
				$("#machineSelector").select2("val","");
			});
			
			$("#taskStatusSelector").select2({allowClear: true});
			
			$("#enquiryTaskForm").submit(function(){
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