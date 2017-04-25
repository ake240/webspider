<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>抓取项管理</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
	<div class="container spider-container">
		<form class="form-horizontal" id="editTaskFIForm" method="post" role="form" enctype="multipart/form-data" >
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="page-header page-title">
						<h1 style="display: inline;">抓取项管理</h1>
						<button type="button" id="saveTaskFIBtn" class="btn btn-success">保存抓取项</button>
					</div>
					<s:if test="message!=null">
						<div class="alert alert-danger alert-dismissable"
							style="padding: 8px 12px;">
							<button type="button" class="close" style="right: 0;" data-dismiss="alert" aria-hidden="true">&times;</button>
							<s:property value='message' />
						</div>
					</s:if>
					<div class="panel-group" id="base-panel">
						<div class="panel-heading" style="overflow: hidden;">
							<a class="panel-title" data-toggle="collapse" data-parent="base-panel" href="#base-div">基本设置</a>
						</div>
						<div id="base-div" class="panel-collapse collapse in">
							<div class="panel-body">
								<div class="row clearfix">
									<div class="col-md-6 column">
										<div class="form-group">
											<label for="taskName" class="col-sm-3 control-label">操作类型名称</label>
											<div class="col-sm-9">
												<input class="form-control" id="websiteOpsName" name="websiteOpsName" readonly="readonly" value="<s:property value='websiteOps.webSiteOpsName'/>" />
											</div>
										</div>
										<s:if test="type!=3">
											<div class="form-group">
												<label for="taskFIFile">抓取项目所在路径</label> <input type="file" class="form-control" id="taskFIFile" name="uploadFile" value="<s:property value='uploadFile'/>" />
											</div>
										</s:if>
									</div>
									<div class="col-md-6 column">
										<div class="form-group">
											<label for="webSiteOpsSelector" class="col-sm-3 control-label">数据库后缀名</label>
											<div class="col-sm-9">
												<input class="form-control" id="webSiteOpsSelector" name="websiteOpsId" readonly="readonly" value="<s:property value='websiteOpsId' />" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-group" id="save-taskFIs">
						<div class="panel panel-default">
								<div class="panel-heading" style="overflow: hidden;">
									<a class="panel-title" data-toggle="collapse" data-parent="save-taskFIs" href="#save-taskFI-div">参数处理</a>
								</div>
							<div id="save-taskFI-div" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="row clearfix">
										<div class="col-md-6 column">
											<div class="form-group">
												<label for="itemUrl" class="col-sm-3 control-label">抓取项URL</label>
												<div class="col-sm-9">
													<textarea class="form-control" id="itemUrl" name="item.url"><s:property value='item.url' /></textarea>
												</div>
											</div>
											<div class="form-group">
												<label for="taskFIStatusSelector" class="col-sm-3 control-label">运行状态标识</label>
												<div class="col-sm-9">
													<select class="form-control form-select2 mandatorySel" id="taskFIStatusSelector" name="item.status" myVal="<s:property value='item.status' />" >
														<option value="0">开始执行</option>
													 	<option value="1">执行中</option>
													 	<option value="2">执行完成</option>
													 	<option value="3">定时任务执行中</option>
													 	<option value="4">定时任务执行完成</option>
													 	<option value="9">执行出错</option>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label for="taskFIFlagSelector" class="col-sm-3 control-label">抓取项标识</label>
												<div class="col-sm-9">
													 <select class="form-control form-select2" id="taskFIFlagSelector" name="item.flag" myVal="<s:property value='item.flag' />" >
													 	<option value="0">禁用</option>
													 	<option value="1">启用</option>
													 </select>
												</div>
											</div>
											<div class="form-group">
												<label for="taskFIRecycleSelector" class="col-sm-3 control-label">重复抓取标识</label>
												<div class="col-sm-9">
													 <select class="form-control form-select2" id="taskFIRecycleSelector" name="item.needRecycle" myVal="<s:property value='item.needRecycle' />" >
													 	<option value="0">不重复</option>
													 	<option value="1">重复</option>
													 </select>
												</div>
											</div>
											<div class="form-group">
												<label for="ItemErrorMsg" class="col-sm-3 control-label">抓取错误消息</label>
												<div class="col-sm-9">
													<textarea class="form-control" id="ItemErrorMsg" name="item.errorMsg"><s:property value='item.errorMsg' /></textarea>
												</div>
											</div>
											<div class="form-group">
												<label for="itemHeader" class="col-sm-3 control-label">抓取项头部</label>
												<div class="col-sm-9">
													<textarea class="form-control" rows="2"  name="header" ><s:property value='header' /></textarea>
													<%-- <input type="text" class="form-control" id="itemHeader" name="header" value="<s:property value='header' />" > --%>
												</div>
											</div>
											<div class="form-group">
												<label for="Param" class="col-sm-3 control-label">抓取参数</label>
												<div class="col-sm-9">
													<textarea class="form-control" rows="5"  name="params" ><s:property value='params' /></textarea>
												</div>
											</div>
											
										</div>
										
										<div class="col-md-6 column">
											<div class="form-group">
												<label for="fetchInterval" class="col-sm-3 control-label">抓取间隔(毫秒)</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" id="fetchInterval" name="item.fetchInterval" value="<s:property value='item.fetchInterval' default='3600000'  />" >
												</div>
											</div>
											<div class="form-group">
												<label for="nextFetchInterval" class="col-sm-3 control-label">下次抓取时间</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" id="nextFetchInterval" name="nextFetchTime" value="<s:property value='nextFetchTime' />" >
												</div>
											</div>
											<s:if test="type==3">
											<div class="form-group">
												<label for="fetchTime" class="col-sm-3 control-label">抓取时间</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" id="fetchTime"  name="fetchTime" value="<s:property value='fetchTime' />" >
												</div>
											</div>
											</s:if>
											<div class="form-group">
												<label for="startPage" class="col-sm-3 control-label">起始页码</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" id="startPage"  name="item.startPage" value="<s:property value='item.startPage' />" >
												</div>
											</div>
											<div class="form-group">
												<label for="endPage" class="col-sm-3 control-label">结束页码</label>
												<div class="col-sm-9">
													<input type="text" class="form-control" id="endPage"  name="item.endPage" value="<s:property value='item.endPage' />" >
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

						</div>
					</div>

				</div>
			</div>
			<input type="hidden" id="insertTime" name="item.insertTime" value="<s:property value='item.insertTime'/>">
			<input type="hidden" id="type" name="type" value="<s:property value='type'/>">
			<input type="hidden" name="itemId" value="<s:property value='itemId'/>">
		</form>
	</div>


<script type="text/javascript">
function refresh(){
	$("#taskFIStatusSelector").select2({allowClear: true});
	$("#taskFIStatusSelector").select2("val",$("#taskFIStatusSelector").attr("myVal"));
	
	$("#taskFIFlagSelector").select2({allowClear: true});
	$("#taskFIFlagSelector").select2("val",$("#taskFIFlagSelector").attr("myVal"));
	
	$("#taskFIRecycleSelector").select2({allowClear: true});
	$("#taskFIRecycleSelector").select2("val",$("#taskFIRecycleSelector").attr("myVal"));

}

$(function(){
	refresh();
	var type = $("#type").val();
	
	$("#saveTaskFIBtn").click(function(){
		if (type == 3) {
			$("#editTaskFIForm").attr("action", "editTaskFI");
		}else{
			
			$("#editTaskFIForm").attr("action", "saveTaskFI");
		}
		$("#editTaskFIForm").submit();
	});
	
	
	$("#editTaskFIForm").submit(function(){
 		var ret = checkMandatory();
		if(!ret){
 			alert("请输入必填字段");
 			return false;
 		}else{
 			$("#saveTaskFIBtn").attr("disabled", "disabled");
 		}
	});
	
});



</script>



</body>
</html>