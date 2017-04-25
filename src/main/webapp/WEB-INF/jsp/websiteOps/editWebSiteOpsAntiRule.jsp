<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作类型反监控</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editWebSiteOpsAntiRuleForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">操作类型反监控</h1>
					<button type="button" id="saveWebSiteOpsAntiRuleBtn" class="btn btn-success">保存操作类型反监控</button>
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
											 <label for="antiRuleName" class="col-sm-3 control-label">名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="antiRuleName" name="webSiteOpsAntiRule.antiRuleName" value="<s:property value='webSiteOpsAntiRule.antiRuleName'/>" placeholder="请输入名称"/>
											 </div>
										</div>
									</div>
									<div class="col-md-6 column">
										<div class="form-group">
											 <label for="webSiteOpsSelector" class="col-sm-3 control-label">网站操作类型<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<select class="form-control form-select2 mandatorySel" id="webSiteOpsSelector" name="webSiteOpsId" myVal="<s:property value='webSiteOpsId'/>"></select>
											 </div>
										</div>
									</div>
								</div>
								
								<div>
									<div class="panel panel-default">
										<div class="panel-heading">
											<div class="panel-title inline">
												账号设置
											</div>
										</div>
										<div class="panel-body">
											<div class="row clearfix">
												<div class="col-md-12 column">
													<div class="form-group">
														<label style="margin-left: 20px; margin-right: 40px;">请求间隔(秒)<span class="red">&nbsp;*</span></label>
													 	<input type="text" style="display: inline; width: 150px;" class="form-control option mandatoryInp" name="webSiteOpsAntiRule.userRequestIntervalStart" value="<s:property value='webSiteOpsAntiRule.userRequestIntervalStart'/>" placeholder="min"/>
													 	&nbsp;-&nbsp;
													 	<input type="text" style="display: inline; width: 150px;" class="form-control option mandatoryInp" name="webSiteOpsAntiRule.userRequestIntervalEnd" value="<s:property value='webSiteOpsAntiRule.userRequestIntervalEnd'/>" placeholder="max"/>
													</div>
												</div>
											</div>
											<div class="row clearfix">
												<div class="col-md-6 column">
													<div class="form-group">
														 <label for="userRequestSeconds" class="col-sm-3 control-label">指定秒内请求<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="userRequestSeconds" name="webSiteOpsAntiRule.userRequestSeconds" value="<s:property value='webSiteOpsAntiRule.userRequestSeconds'/>" placeholder="user request seconds"/>
														 </div>
													</div>
													<div class="form-group">
														 <label for="" class="col-sm-3 control-label">请求方式<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<s:if test="type==1">
														 		<s:radio list="#{'0':'连续1小时','1':'每小时'}" name="webSiteOpsAntiRule.userRequestHour" value="webSiteOpsAntiRule.userRequestHour"/>
														 	</s:if>
														 	<s:else>
														 		<s:radio list="#{'0':'连续1小时','1':'每小时'}" name="webSiteOpsAntiRule.userRequestHour" value="0"/>
														 	</s:else>
														 </div>
													</div>
													<div class="form-group">
														 <label for="userRequestCountPerDay" class="col-sm-3 control-label">每天请求次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="userRequestCountPerDay" name="webSiteOpsAntiRule.userRequestCountPerDay" value="<s:property value='webSiteOpsAntiRule.userRequestCountPerDay'/>" placeholder="请输入登录次数"/>
														 </div>
													</div>
												</div>
												<div class="col-md-6 column">
													<div class="form-group">
														 <label for="userRequestCountInSeconds" class="col-sm-3 control-label">请求次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="userRequestCountInSeconds" name="webSiteOpsAntiRule.userRequestCountInSeconds" value="<s:property value='webSiteOpsAntiRule.userRequestCountInSeconds'/>" placeholder="user request count in seconds"/>
														 </div>
													</div>
													<div class="form-group">
														 <label for="userRequestCountPerHour" class="col-sm-3 control-label">请求次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="userRequestCountPerHour" name="webSiteOpsAntiRule.userRequestCountPerHour" value="<s:property value='webSiteOpsAntiRule.userRequestCountPerHour'/>" placeholder="请输入登录次数"/>
														 </div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="panel panel-default">
										<div class="panel-heading">
											<div class="panel-title inline">
												IP设置
											</div>
										</div>
										<div class="panel-body">
											<div class="row clearfix">
												<div class="col-md-12 column">
													<div class="form-group">
														 <label style="margin-left: 20px; margin-right: 40px;">请求间隔(秒)<span class="red">&nbsp;*</span></label>
													 	<input type="text" style="display: inline; width: 150px;" class="form-control option mandatoryInp" name="webSiteOpsAntiRule.ipRequestIntervalStart" value="<s:property value='webSiteOpsAntiRule.ipRequestIntervalStart'/>" placeholder="min"/>
													 	&nbsp;-&nbsp;
													 	<input type="text" style="display: inline; width: 150px;" class="form-control option mandatoryInp" name="webSiteOpsAntiRule.ipRequestIntervalEnd" value="<s:property value='webSiteOpsAntiRule.ipRequestIntervalEnd'/>" placeholder="max"/>
													</div>
												</div>
											</div>
											<div class="row clearfix">
												<div class="col-md-6 column">
													<div class="form-group">
														 <label for="ipRequestSeconds" class="col-sm-3 control-label">指定秒内请求<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="ipRequestSeconds" name="webSiteOpsAntiRule.ipRequestSeconds" value="<s:property value='webSiteOpsAntiRule.ipRequestSeconds'/>" placeholder="ip request seconds"/>
														 </div>
													</div>
													<div class="form-group">
														 <label for="" class="col-sm-3 control-label">请求方式<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<s:if test="type==1">
														 		<s:radio list="#{'0':'连续1小时','1':'每小时'}" name="webSiteOpsAntiRule.ipRequestHour" value="webSiteOpsAntiRule.ipRequestHour"/>
														 	</s:if>
														 	<s:else>
														 		<s:radio list="#{'0':'连续1小时','1':'每小时'}" name="webSiteOpsAntiRule.ipRequestHour" value="0"/>
														 	</s:else>
														 </div>
													</div>
													<div class="form-group">
														 <label for="ipRequestCountPerDay" class="col-sm-3 control-label">每天请求次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="ipRequestCountPerDay" name="webSiteOpsAntiRule.ipRequestCountPerDay" value="<s:property value='webSiteOpsAntiRule.ipRequestCountPerDay'/>" placeholder="请输入登录次数"/>
														 </div>
													</div>
												</div>
											
												<div class="col-md-6 column">
													<div class="form-group">
														 <label for="ipRequestCountInSeconds" class="col-sm-3 control-label">请求次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="ipRequestCountInSeconds" name="webSiteOpsAntiRule.ipRequestCountInSeconds" value="<s:property value='webSiteOpsAntiRule.ipRequestCountInSeconds'/>" placeholder="ip request count in seconds"/>
														 </div>
													</div>
													<div class="form-group">
														 <label for="ipRequestCountPerHour" class="col-sm-3 control-label">请求次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="ipRequestCountPerHour" name="webSiteOpsAntiRule.ipRequestCountPerHour" value="<s:property value='webSiteOpsAntiRule.ipRequestCountPerHour'/>" placeholder="请输入登录次数"/>
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
				</div>
			</div>
		</div>
			<input type="hidden" name="antiRuleId" value="<s:property value='webSiteOpsAntiRule.antiRuleId'/>">
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
			
			$("#saveWebSiteOpsAntiRuleBtn").click(function(){
				if(type==0){
					$("#editWebSiteOpsAntiRuleForm").attr("action","newWebSiteOpsAntiRule");
				}else{
					$("#editWebSiteOpsAntiRuleForm").attr("action","editWebSiteOpsAntiRule");
				}
				
				$("#editWebSiteOpsAntiRuleForm").submit();
			});
			
			$("#editWebSiteOpsAntiRuleForm").submit(function(){
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					$("#saveWebSiteOpsAntiRuleBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>