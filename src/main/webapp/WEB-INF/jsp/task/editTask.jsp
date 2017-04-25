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
		<form class="form-horizontal" id="editTaskForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">任务管理</h1>
					<button type="button" id="saveTaskBtn" class="btn btn-success">保存任务</button>
				</div>
				<s:if test="message!=null">
					<div class="alert alert-danger alert-dismissable" style="padding: 8px 12px;">
						<button type="button" class="close" style="right: 0;" data-dismiss="alert" aria-hidden="true">&times;</button>
						<s:property value='message'/>
					</div>
				</s:if>
				<div class="panel-group" id="base-panel">
					<div class="panel panel-default">
						<div class="panel-heading" style="overflow: hidden;">
							 <a class="panel-title" data-toggle="collapse" data-parent="base-panel" href="#base-div">基本设置</a>
						</div>
						<div id="base-div" class="panel-collapse collapse in">
							<div class="panel-body">
								<div class="row clearfix">
									<div class="col-md-6 column">
										<div class="form-group">
											 <label for="taskName" class="col-sm-3 control-label">任务中文名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="taskName" name="task.taskName" value="<s:property value='task.taskName'/>" placeholder="请输入任务中文名称"/>
											 </div>
										</div>
										<div class="form-group">
											<label for="webSiteOpsSelector" class="col-sm-3 control-label">网站操作类型<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<select class="form-control form-select2 mandatorySel" id="webSiteOpsSelector" name="webSiteOpsId" myVal="<s:property value='webSiteOpsId'/>"></select>
											</div>
										</div>
										<div class="form-group">
											 <label for="procClass" class="col-sm-3 control-label">任务执行类<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<s:if test="type==1">
											 		<input type="text" class="form-control mandatoryInp" id="procClass" name="task.procClass" value="<s:property value='task.procClass'/>" placeholder="请输入任务执行类"/>
											 	</s:if>
											 	<s:else>
											 		<input type="text" class="form-control mandatoryInp" id="procClass" name="task.procClass" value="com.kepler.spider.util.scheduling.impl.DefaultProcessor" placeholder="请输入任务执行类"/>
											 	</s:else>
											 	
											 </div>
										</div>
										<div class="form-group">
											 <label for="procExpr" class="col-sm-3 control-label">调度表达式</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control" id="procExpr" name="task.procExpr" value="<s:property value='task.procExpr'/>" placeholder="请输入调度表达式"/>
											 </div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">是否重复执行<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<s:if test="type==1">
													<s:radio list="#{'1':'是','0':'否'}" id="" name="task.needRecycle" value="task.needRecycle"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'是','0':'否'}" id="" name="task.needRecycle" value="0"/>
												</s:else>
											</div>
										</div>
										
										<s:if test="type==1">
											<div class="form-group">
												<label class="col-sm-3 control-label">任务状态<span class="red">&nbsp;*</span></label>
												<div class="col-sm-9">
													<select class="form-control form-select2 mandatorySel" id="taskStatusSelector" name="task.taskStatus" myVal="<s:property value='task.taskStatus'/>">
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
										 </s:if>
									</div>
									
									<div class="col-md-6 column">
										<div class="form-group">
											 <label for="taskEnName" class="col-sm-3 control-label">任务英文名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="taskEnName" name="task.taskEnName" value="<s:property value='task.taskEnName'/>" placeholder="请输入任务英文名称"/>
											 </div>
										</div>
										<div class="form-group">
											 <label for="procHost" class="col-sm-3 control-label">执行机器IP</label>
											 <div class="col-sm-9">
											 	<select class="form-control form-select2" id="machineSelector" name="task.procHost" myVal="<s:property value='task.procHost'/>"></select>
											 </div>
										</div>
										<div class="form-group">
											 <label for="extroParam" class="col-sm-3 control-label">处理参数</label>
											 <div class="col-sm-9">
											 	<textarea type="text" class="form-control" id="extroParam" name="task.extroParam" placeholder="请输入处理参数"><s:property value='task.extroParam'/></textarea>
											 </div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">任务类型<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<s:if test="type==1">
													<s:radio list="#{'1':'固定间隔','2':'quartz运行'}" id="taskType" name="task.taskType" value="task.taskType"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'固定间隔','2':'quartz运行'}" id="taskType" name="task.taskType" value="1"/>
												</s:else>
											</div>
										</div>
										
										<div class="form-group" id="endPageGrp">
											 <label for="procInterval" class="col-sm-3 control-label">时间间隔</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control" id="procInterval" name="task.procInterval" value="<s:property value='task.procInterval'/>" placeholder="请输入任务执行时间间隔"/>
											 </div>
										</div>
										
										<s:if test="type==1">
											<div class="form-group">
												 <label for="procMessage" class="col-sm-3 control-label">任务执行消息</label>
												 <div class="col-sm-9">
												 	<textarea type="text" class="form-control" id="procMessage" name="task.procMessage"><s:property value='task.procMessage'/></textarea>
												 </div>
											</div>
										</s:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="taskId" value="<s:property value='task.taskId'/>">
		</form>
	</div>
	<input type="hidden" id="type" value="<s:property value='type'/>">
	
	<script type="text/javascript">
		function refresh(){
		}
		
		$(function(){
			var type = $("#type").val();
			
			$.getJSON("findAllWebSiteOps", function(data){
				var webSiteOpsSelector = $("#webSiteOpsSelector");
				$.each(data.jsonResult, function(key, value){
					webSiteOpsSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				webSiteOpsSelector.prepend("<option></option>");
				$("#webSiteOpsSelector").select2({allowClear: true});
				$("#webSiteOpsSelector").select2("val",$("#webSiteOpsSelector").attr("myVal"));
			});
			
			$.getJSON("findAllMachine", function(data){
				var machineSelector = $("#machineSelector");
				$.each(data.jsonResult, function(key, value){
					machineSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				machineSelector.prepend("<option></option>");
				$("#machineSelector").select2({allowClear: true});
				$("#machineSelector").select2("val",$("#machineSelector").attr("myVal"));
			});
			
			$("#taskStatusSelector").select2({allowClear: true});
			$("#taskStatusSelector").select2("val",$("#taskStatusSelector").attr("myVal"));
			
			$("#saveTaskBtn").click(function(){
				if(type!=1){
					$("#editTaskForm").attr("action","newTask");
				}else{
					$("#editTaskForm").attr("action","editTask");
				}
				
				$("#editTaskForm").submit();
			});
			
			$("#editTaskForm").submit(function(){
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					$("#saveTaskBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>