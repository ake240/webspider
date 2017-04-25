<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站数据源配置</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editWebSiteConfigForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站数据源配置</h1>
					<button type="button" id="saveWebSiteConfigBtn" class="btn btn-success">保存数据源配置</button>
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
											 <label for="webSiteConfigName" class="col-sm-3 control-label">名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="webSiteConfigName" name="webSiteConfig.webSiteConfigName" value="<s:property value='webSiteConfig.webSiteConfigName'/>" placeholder="请输入名称"/>
											 </div>
										</div>
										<div class="form-group">
											 <label for="" class="col-sm-3 control-label">是否需要代理IP<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<s:if test="type==1">
											 		<s:radio list="#{'1':'是','0':'否'}" name="webSiteConfig.needProxyIP" value="webSiteConfig.needProxyIP"/>
											 	</s:if>
											 	<s:else>
											 		<s:radio list="#{'1':'是','0':'否'}" name="webSiteConfig.needProxyIP" value="0"/>
											 	</s:else>
											 </div>
										</div>
										<div class="form-group" id="fixedProxyIP" style="display: none;">
											 <label for="" class="col-sm-3 control-label">是否需要固定IP</label>
											 <div class="col-sm-9">
											 	<s:if test="type==1">
											 		<s:radio list="#{'1':'是','0':'否'}" name="webSiteConfig.needFixedProxyIP" value="webSiteConfig.needFixedProxyIP"/>
											 	</s:if>
											 	<s:else>
											 		<s:radio list="#{'1':'是','0':'否'}" name="webSiteConfig.needFixedProxyIP" value="0"/>
											 	</s:else>
											 </div>
										</div>										
										<div class="form-group">
											 <label for="description" class="col-sm-3 control-label">描述</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control" id="description" name="webSiteConfig.description" value="<s:property value='webSiteConfig.description'/>" placeholder="请输入配置描述"/>
											 </div>
										</div>
									</div>
									<div class="col-md-6 column">
										<div class="form-group">
											 <label for="webSiteSelector" class="col-sm-3 control-label">网站<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<select class="form-control form-select2 mandatorySel" id="webSiteSelector" name="webSiteId" myVal="<s:property value='webSiteId'/>"></select>
											 </div>
										</div>
										<div class="form-group">
											 <label for="" class="col-sm-3 control-label">是否需要登录<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<s:if test="type==1">
											 		<s:radio list="#{'1':'是','0':'否'}" name="webSiteConfig.needLogin" value="webSiteConfig.needLogin"/>
											 	</s:if>
											 	<s:else>
											 		<s:radio list="#{'1':'是','0':'否'}" name="webSiteConfig.needLogin" value="0"/>
											 	</s:else>
											 </div>
										</div>
										<div class="form-group" id="loginClassGrp">
											 <label for="loginClass" class="col-sm-3 control-label">登录类<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control option mandatoryInp" id="loginClass" name="webSiteConfig.loginClass" value="<s:property value='webSiteConfig.loginClass'/>" placeholder="请输入登录类"/>
											 </div>
										</div>
									</div>
								</div>
								
								<div id="loginSetting">
									<div class="panel panel-default" id="accountSetting">
										<div class="panel-heading">
											<div class="panel-title inline">
												账号设置
											</div>
										</div>
										<div class="panel-body">
											<div class="row clearfix">
												<div class="col-md-12 column">
													<div class="form-group">
														 <label style="margin-left: 20px; margin-right: 40px;">登录间隔(秒)<span class="red">&nbsp;*</span></label>
													 	<input type="text" style="display: inline; width: 150px;" class="form-control option mandatoryInp" name="webSiteConfig.userLoginIntervalStart" value="<s:property value='webSiteConfig.userLoginIntervalStart'/>" placeholder="min"/>
													 	&nbsp;-&nbsp;
													 	<input type="text" style="display: inline; width: 150px;" class="form-control option mandatoryInp" name="webSiteConfig.userLoginIntervalEnd" value="<s:property value='webSiteConfig.userLoginIntervalEnd'/>" placeholder="max"/>
													</div>
												</div>
											</div>
											<div class="row clearfix">
												<div class="col-md-6 column">
													<div class="form-group">
														 <label for="userLoginMinutes" class="col-sm-3 control-label">指定分钟内登录<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="userLoginMinutes" name="webSiteConfig.userLoginMinutes" value="<s:property value='webSiteConfig.userLoginMinutes'/>" placeholder="user login minutes"/>
														 </div>
													</div>
													<div class="form-group">
														 <label for="" class="col-sm-3 control-label">登录方式<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<s:if test="type==1">
														 		<s:radio list="#{'0':'连续1小时','1':'每小时'}" name="webSiteConfig.userLoginHour" value="webSiteConfig.userLoginHour"/>
														 	</s:if>
														 	<s:else>
														 		<s:radio list="#{'0':'连续1小时','1':'每小时'}" name="webSiteConfig.userLoginHour" value="0"/>
														 	</s:else>
														 </div>
													</div>
													<div class="form-group">
														 <label for="userLoginCountPerDay" class="col-sm-3 control-label">每天登录次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="userLoginCountPerDay" name="webSiteConfig.userLoginCountPerDay" value="<s:property value='webSiteConfig.userLoginCountPerDay'/>" placeholder="请输入登录次数"/>
														 </div>
													</div>
												</div>
												<div class="col-md-6 column">
													<div class="form-group">
														 <label for="userLoginCountInMinutes" class="col-sm-3 control-label">登录次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="userLoginCountInMinutes" name="webSiteConfig.userLoginCountInMinutes" value="<s:property value='webSiteConfig.userLoginCountInMinutes'/>" placeholder="user login count in minutes"/>
														 </div>
													</div>
													<div class="form-group">
														 <label for="userLoginCountPerHour" class="col-sm-3 control-label">登录次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="userLoginCountPerHour" name="webSiteConfig.userLoginCountPerHour" value="<s:property value='webSiteConfig.userLoginCountPerHour'/>" placeholder="请输入登录次数"/>
														 </div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="panel panel-default" id="ipSetting">
										<div class="panel-heading">
											<div class="panel-title inline">
												IP设置
											</div>
										</div>
										<div class="panel-body">
											<div class="row clearfix">
												<div class="col-md-12 column">
													<div class="form-group">
														 <label style="margin-left: 20px; margin-right: 40px;">登录间隔(秒)<span class="red">&nbsp;*</span></label>
													 	<input type="text" style="display: inline; width: 150px;" class="form-control option mandatoryInp" name="webSiteConfig.ipLoginIntervalStart" value="<s:property value='webSiteConfig.ipLoginIntervalStart'/>" placeholder="min"/>
													 	&nbsp;-&nbsp;
													 	<input type="text" style="display: inline; width: 150px;" class="form-control option mandatoryInp" name="webSiteConfig.ipLoginIntervalEnd" value="<s:property value='webSiteConfig.ipLoginIntervalEnd'/>" placeholder="max"/>
													</div>
												</div>
											</div>
											<div class="row clearfix">
												<div class="col-md-6 column">
													<div class="form-group">
														 <label for="ipLoginMinutes" class="col-sm-3 control-label">指定分钟内登录<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="ipLoginMinutes" name="webSiteConfig.ipLoginMinutes" value="<s:property value='webSiteConfig.ipLoginMinutes'/>" placeholder="ip login minutes"/>
														 </div>
													</div>
													<div class="form-group">
														 <label for="" class="col-sm-3 control-label">登录方式<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<s:if test="type==1">
														 		<s:radio list="#{'0':'连续1小时','1':'每小时'}" name="webSiteConfig.ipLoginHour" value="webSiteConfig.ipLoginHour"/>
														 	</s:if>
														 	<s:else>
														 		<s:radio list="#{'0':'连续1小时','1':'每小时'}" name="webSiteConfig.ipLoginHour" value="0"/>
														 	</s:else>
														 </div>
													</div>
													<div class="form-group">
														 <label for="ipLoginCountPerDay" class="col-sm-3 control-label">每天登录次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="ipLoginCountPerDay" name="webSiteConfig.ipLoginUserCountPerDay" value="<s:property value='webSiteConfig.ipLoginUserCountPerDay'/>" placeholder="请输入登录次数"/>
														 </div>
													</div>
												</div>
											
												<div class="col-md-6 column">
													<div class="form-group">
														 <label for="ipLoginCountInMinutes" class="col-sm-3 control-label">登录次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="ipLoginCountInMinutes" name="webSiteConfig.ipLoginUserCountInMinutes" value="<s:property value='webSiteConfig.ipLoginUserCountInMinutes'/>" placeholder="ip login count in minutes"/>
														 </div>
													</div>
													<div class="form-group">
														 <label for="ipLoginCountPerHour" class="col-sm-3 control-label">登录次数<span class="red">&nbsp;*</span></label>
														 <div class="col-sm-9">
														 	<input type="text" class="form-control option mandatoryInp" id="ipLoginCountPerHour" name="webSiteConfig.ipLoginUserCountPerHour" value="<s:property value='webSiteConfig.ipLoginUserCountPerHour'/>" placeholder="请输入登录次数"/>
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
			<input type="hidden" name="webSiteConfigId" value="<s:property value='webSiteConfig.webSiteConfigId'/>">
		</form>
	</div>
	<input type="hidden" id="type" value="<s:property value='type'/>">
	
	<script type="text/javascript">
		function refresh(){
		}
		
		$(function(){
			var type = $("#type").val();
			
			var needLogin = $("[name='webSiteConfig.needLogin'][checked='checked']").val();
			if(needLogin==0){
				$("#loginClassGrp").hide();
				$("#loginSetting").hide();
				$(".option").each(function(){
					$(this).removeClass("mandatoryInp");
				});
			}
			
			var needProxyIP = $("[name='webSiteConfig.needProxyIP'][checked='checked']").val();
			if(needProxyIP==1){
				$("#fixedProxyIP").show();
			}
			
			$("[name='webSiteConfig.needProxyIP']").change(function(){
				var needProxyIP = $(this).val();
				if(needProxyIP==0){
					$("#fixedProxyIP").hide();
				}else{
					$("#fixedProxyIP").show();
				}
			});
			
			$("[name='webSiteConfig.needLogin']").change(function(){
				needLogin = $(this).val();
				if(needLogin==0){
					$("#loginClassGrp").hide();
					$("#loginSetting").hide();
					$(".option").each(function(){
						$(this).removeClass("mandatoryInp");
					});
				}else{
					$("#loginClassGrp").show();
					$("#loginSetting").show();
					$(".option").each(function(){
						$(this).addClass("mandatoryInp");
					});
				}
			});
			
			$.getJSON("findAllWebSite", function(data){
				var webSiteSelector = $("#webSiteSelector");
				$.each(data.jsonResult, function(key, value){
					webSiteSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				webSiteSelector.prepend("<option></option>");
				$("#webSiteSelector").select2({allowClear: true});
				
				$("#webSiteSelector").select2("val",$("#webSiteSelector").attr("myVal"));
			});
			
			$("#saveWebSiteConfigBtn").click(function(){
				if(type==0){
					$("#editWebSiteConfigForm").attr("action","newWebSiteConfig");
				}else{
					$("#editWebSiteConfigForm").attr("action","editWebSiteConfig");
				}
				
				$("#editWebSiteConfigForm").submit();
			});
			
			$("#editWebSiteConfigForm").submit(function(){
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					if(needLogin==0){
						$("#loginClassGrp").detach();
						$("#loginSetting").detach();
					}
					$("#saveWebSiteConfigBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>