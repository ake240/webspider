<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作类型异常监控</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editWebSiteOpsExceptionRuleForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">操作类型异常监控</h1>
					<button type="button" id="saveWebSiteOpsExceptionRuleBtn" class="btn btn-success">保存异常监控</button>
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
											 <label for="exceptionRuleName" class="col-sm-3 control-label">名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="exceptionRuleName" name="webSiteOpsExceptionRule.exceptionRuleName" value="<s:property value='webSiteOpsExceptionRule.exceptionRuleName'/>" placeholder="请输入名称"/>
											 </div>
										</div>
										<div class="form-group">
											  <label class="col-sm-3 control-label">抓取方式<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<s:if test="type==1">
													<s:radio list="#{'1':'xpath','2':'正则'}" id="" name="webSiteOpsExceptionRule.exprType" value="webSiteOpsExceptionRule.exprType"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'xpath','2':'正则'}" id="" name="webSiteOpsExceptionRule.exprType" value="1"/>
												</s:else>
											 </div>
										</div>
<%-- 										<s:if test="type==1"> --%>
<!-- 											<div class="form-group"> -->
<!-- 												<label class="col-sm-3 control-label">状态</label> -->
<!-- 												 <div class="col-sm-9"> -->
<%-- 												 	<s:if test="webSiteOpsExceptionRule.status==1"> --%>
<%-- 												 		<span class="form-control">启用</span>	 --%>
<%-- 												 	</s:if> --%>
<%-- 												 	<s:else> --%>
<%-- 												 		<span class="form-control">停用</span> --%>
<%-- 												 	</s:else> --%>
<!-- 												 </div> -->
<!-- 											</div> -->
<%-- 										</s:if> --%>
									</div>
									<div class="col-md-6 column">
										<div class="form-group">
											<label class="col-sm-3 control-label">网站操作类型<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<select class="form-control form-select2 mandatorySel" id="webSiteOpsSelector" name="webSiteOpsId" myVal="<s:property value='webSiteOpsId'/>"></select>
											</div>
										</div>
										
										<div class="form-group">
											<label for="exceptionType" class="col-sm-3 control-label">异常类型<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<s:if test="type==1">
													<s:radio list="#{'1':'普通异常','2':'验证码', '3':'登陆过期'}" id="exceptionType" name="webSiteOpsExceptionRule.exceptionType" value="webSiteOpsExceptionRule.exceptionType"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'普通异常','2':'验证码', '3':'登陆过期'}" id="exceptionType" name="webSiteOpsExceptionRule.exceptionType" value="1"/>
												</s:else>
											 </div>
										</div>
										
										
									</div>
								</div>
								<div class="row clearfix">
									<div class="col-md-12 column">
										<!-- Added by zhicheng -->
										
										<div class="form-group">
											 <label for="exprValue" class="col-sm-2 control-label">抓取表达式<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<textarea class="form-control mandatoryInp" id="exprValue" name="webSiteOpsExceptionRule.exprVal"><s:property value="webSiteOpsExceptionRule.exprVal"/></textarea>
											 </div>
										</div>
										
										<div class="form-group">
											<label for="matchValue" class="col-sm-2 control-label">匹配默认值</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="matchValue" name="webSiteOpsExceptionRule.matchValue" value="<s:property value='webSiteOpsExceptionRule.matchValue'/>" placeholder="默认匹配值"/>
											 </div>
										</div>
										
										<div class="form-group">
											<label for="exceptionProcessClass" class="col-sm-2 control-label">异常处理类名</label>
											<div class="col-sm-9">
												<input type="text" class="form-control" id="exceptionProcessClass" name="webSiteOpsExceptionRule.exceptionProcessClass" value="<s:property value='webSiteOpsExceptionRule.exceptionProcessClass'/>" placeholder="请输入类名"/>
											 </div>
										</div>
										
										<div class="form-group">
											<label for="exceptionProcessClassContent" class="col-sm-2 control-label">异常处理类代码</label>
											<div class="col-sm-9">
												<textarea class="form-control " id="exceptionProcessClassContent" name="webSiteOpsExceptionRule.exceptionProcessClassContent"><s:property value="webSiteOpsExceptionRule.exceptionProcessClassContent"/></textarea>
											 </div>
										</div>
										
										<div class="form-group">
											<label for="extroParam" class="col-sm-2 control-label">额外参数</label>
											<div class="col-sm-9">
												<textarea class="form-control" id="extroParam" name="webSiteOpsExceptionRule.extroParam"><s:property value='webSiteOpsExceptionRule.extroParam'/></textarea>
											 </div>
										</div>
										<!-- end Adding -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="exceptionRuleId" value="<s:property value='webSiteOpsExceptionRule.exceptionRuleId'/>">
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
				$("#webSiteOpsSelector").select2("val", $("#webSiteOpsSelector").attr("myVal"));
			});
			
			$("#saveWebSiteOpsExceptionRuleBtn").click(function(){
				if(type==0){
					$("#editWebSiteOpsExceptionRuleForm").attr("action","newWebSiteOpsExceptionRule");
				}else{
					$("#editWebSiteOpsExceptionRuleForm").attr("action","editWebSiteOpsExceptionRule");
				}
				
				$("#editWebSiteOpsExceptionRuleForm").submit();
			});
			
			$("#editWebSiteOpsExceptionRuleForm").submit(function(){
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					$("#saveWebSiteOpsExceptionRuleBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>