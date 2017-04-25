<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站操作类型</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editWebSiteOpsForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站操作类型</h1>
					<button type="button" id="saveWebSiteOpsBtn" class="btn btn-success">保存操作类型</button>
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
											 <label for="webSiteOpsName" class="col-sm-3 control-label">操作类型名称<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control mandatoryInp" id="webSiteOpsName" name="webSiteOps.webSiteOpsName" value="<s:property value='webSiteOps.webSiteOpsName'/>" placeholder="请输入操作类型名称"/>
											 </div>
										</div>
										<div class="form-group">
											<label for="webSiteSelector" class="col-sm-3 control-label">所属网站<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<select class="form-control form-select2 mandatorySel" id="webSiteSelector" name="webSiteId" myVal="<s:property value='webSiteId'/>"></select>
											</div>
										</div>
										<div class="form-group">
											<label for="machineRoleSelector" class="col-sm-3 control-label">网站角色<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<select class="form-control form-select2 mandatorySel" id="machineRoleSelector" name="machineRoleId" myVal="<s:property value='machineRoleId'/>"></select>
											</div>
										</div>
										
										<div class="form-group">
											 <label for="consumers" class="col-sm-3 control-label">并发消费者数量<span class="red">&nbsp;*</span></label>
											 <div class="col-sm-9">
											 	<s:if test="type!=0">
											 		<input type="text" class="form-control mandatoryInp" id="consumers" name="webSiteOps.consumers" value="<s:property value='webSiteOps.consumers'/>"/>
											 	</s:if>
											 	<s:else>
											 		<input type="text" class="form-control mandatoryInp" id="consumers" name="webSiteOps.consumers" value="1"/>
											 	</s:else>
											 </div>
										</div>
										
									</div>
									
									<div class="col-md-6 column">
										
										<div class="form-group" style="display:none">
											<label for="pWebSiteOpsSelector" class="col-sm-3 control-label">父操作类型</label>
											<div class="col-sm-9">
												<select class="form-control form-select2" id="pWebSiteOpsSelector" name="webSiteOps.pWebSiteOpsId" myVal="<s:property value='webSiteOps.pWebSiteOpsId'/>"></select>
											</div>
										</div>
										
										<div class="form-group" style="display:none">
											<label for="webSiteOpsSelector" class="col-sm-3 control-label">依赖操作类型</label>
											<div class="col-sm-9">
												<select class="form-control form-select2" id="webSiteOpsSelector" name="webSiteOps.extWebSiteOpsId" myVal="<s:property value='webSiteOps.extWebSiteOpsId'/>"></select>
											</div>
										</div>
										
										<div class="form-group">
											 <label for="operationClass" class="col-sm-3 control-label">操作类</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control" id="operationClass" name="webSiteOps.operationClass" value="<s:property value='webSiteOps.operationClass'/>" placeholder="请输入操作类"/>
											 </div>
										</div>
										
										<div class="form-group">
											 <label for="extroParam" class="col-sm-3 control-label">操作类参数</label>
											 <div class="col-sm-9">
											 	<textarea rows="2" cols="5" class="form-control" id="extraParam" name="webSiteOps.extraParam" placeholder="请输入参数"><s:property value='webSiteOps.extraParam'/></textarea>
											 </div>
										</div>
										
										<div class="form-group" style="display:none">
											<label class="col-sm-3 control-label">动态请求</label>
											<div class="col-sm-9">
												<s:if test="type!=0">
													<s:radio list="#{'1':'是','0':'否'}" id="" name="webSiteOps.dynamicReq" value="webSiteOps.dynamicReq"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'是','0':'否'}" id="" name="webSiteOps.dynamicReq" value="1"/>
												</s:else>
											</div>
										</div>
										
									</div>
								</div>
								
								<div class="row clearfix">
									<div class="col-md-12 column">
										<div class="form-group">
											<label for="parentWebSiteOpsSelector" class="col-sm-2 control-label">父操作类型</label>
											<div class="col-sm-10">
												<input type="hidden" id="websiteOpsIds" value="<s:property value='websiteOpsIds'/>">
												<select multiple class="form-control form-select2" id="parentWebSiteOpsSelector" name="parentWebsiteOpsIds"></select>
<%-- 												<select multiple class="form-control form-select2" id="websiteOperationIdList" name="crawlrule.websiteOperationIdList"></select> --%>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="panel panel-default">
						<div class="panel-heading" style="overflow: hidden;">
							 <a class="panel-title" data-toggle="collapse" data-parent="base-panel" href="#base-div">输入设置</a>
						</div>
						<div id="base-div" class="panel-collapse collapse in">
							<div class="panel-body">
								<div class="row clearfix">
									<div class="col-md-6 column">
										<div class="form-group">
											 <label for="reqAction" class="col-sm-3 control-label">表单请求URL</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control" id="reqAction" name="webSiteOps.reqAction" value="<s:property value='webSiteOps.reqAction'/>" placeholder="表单请求URL"/>
											 </div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">请求方式<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<s:if test="type!=0">
													<s:radio list="#{'1':'GET','2':'POST'}" id="reqMethod" name="webSiteOps.requestMethod" value="webSiteOps.requestMethod"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'GET','2':'POST'}" id="reqMethod" name="webSiteOps.requestMethod" value="1"/>
												</s:else>
												
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">抓取Url匹配</label>
											<div class="col-sm-9">
												<textarea rows="2" cols="5" class="form-control" name="webSiteOps.urlFilterRegex" placeholder="抓取Url匹配,以@@@分隔"><s:property value='webSiteOps.urlFilterRegex'/></textarea>
											 </div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">是否分页<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<s:if test="type!=0">
													<s:radio list="#{'1':'是','0':'否'}" id="pagination" cssClass="paginationCls" name="webSiteOps.pagination" value="webSiteOps.pagination"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'是','0':'否'}" id="pagination" cssClass="paginationCls" name="webSiteOps.pagination" value="0"/>
												</s:else>
											</div>
										</div>
										<div class="form-group" id="stepGrp">
											 <label for="step" class="col-sm-3 control-label">分页步长</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control" id="step" name="webSiteOps.step" value="<s:property value='webSiteOps.step'/>" placeholder="请输入分页步长"/>
											 </div>
										</div>
									</div>
									<div class="col-md-6 column">
										<div class="form-group">
											<label class="col-sm-3 control-label">页面编码<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<s:if test="type!=0">
													<s:radio list="#{'1':'utf-8','2':'gb2312', '3':'gbk', '4':'iso-8859-1'}" id="pageEncode" name="webSiteOps.pageEncode" value="webSiteOps.pageEncode"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'utf-8','2':'gb2312', '3':'gbk', '4':'iso-8859-1'}" id="pageEncode" name="webSiteOps.pageEncode" value="1"/>
												</s:else>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-3 control-label">是否循环<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<s:if test="type!=0">
													<s:radio list="#{'1':'是','0':'否'}" id="needRecycle" name="webSiteOps.needRecycle" value="webSiteOps.needRecycle"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'是','0':'否'}" id="needRecycle" name="webSiteOps.needRecycle" value="1"/>
												</s:else>
											</div>
										</div>
										<div class="form-group" id="startPageGrp">
											 <label for="startPage" class="col-sm-3 control-label">分页起始值</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control" id="startPage" name="webSiteOps.startPage" value="<s:property value='webSiteOps.startPage'/>" placeholder="请输入分页起始值"/>
											 </div>
										</div>
										<div class="form-group" id="endPageGrp">
											 <label for="endPage" class="col-sm-3 control-label">分页结束值</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control" id="endPage" name="webSiteOps.endPage" value="<s:property value='webSiteOps.endPage'/>" placeholder="请输入分页结束值"/>
											 </div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="panel panel-default">
						<div class="panel-heading" style="overflow: hidden;">
							 <a class="panel-title" data-toggle="collapse" data-parent="base-panel" href="#base-div">输出设置</a>
						</div>
						<div id="base-div" class="panel-collapse collapse in">
							<div class="panel-body">
								<div class="row clearfix">
									<div class="col-md-6 column">
										<div class="form-group">
											<label class="col-sm-3 control-label">存储方式</label>
											<div class="col-sm-9">
												<s:if test="type!=0">
													<s:checkbox name="saveToDB" id="saveToDB" value="webSiteOps.saveToDB"/><label for="saveToDB">数据库</label>&nbsp;
													<s:checkbox name="saveToMQ" id="saveToMQ" value="webSiteOps.saveToMQ"/><label for="saveToMQ">MQ</label>&nbsp;
													<s:checkbox name="saveToFile" id="saveToFile" value="webSiteOps.saveToFile"/><label for="saveToFile">文件</label>&nbsp;
												</s:if>
												<s:else>
													<s:checkbox name="saveToDB" id="saveToDB" value="1"/><label for="saveToDB">数据库</label>&nbsp;
													<s:checkbox name="saveToMQ" id="saveToMQ" value="1"/><label for="saveToMQ">MQ</label>&nbsp;
													<s:checkbox name="saveToFile" id="saveToFile" value="0"/><label for="saveToFile">文件</label>&nbsp;
												</s:else>
												
											</div>
										</div>	
										<div class="form-group">
											<label class="col-sm-3 control-label">层次类型</label>
											<div class="col-sm-9">
												<s:if test="type!=0">
													<s:radio list="#{'1':'中间层','0':'最终页面'}" id="" name="webSiteOps.intermediateResult" value="webSiteOps.intermediateResult"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'中间层','0':'最终页面'}" id="" name="webSiteOps.intermediateResult" value="1"/>
												</s:else>
											</div>
										</div>
										<div class="form-group">
											<label for="dataStoreTypeSelector" class="col-sm-3 control-label">存储类型<span class="red">&nbsp;*</span></label>
											<div class="col-sm-9">
												<select class="form-control form-select2 mandatorySel" id="dataStoreTypeSelector" name="dataStoreTypeId" myVal="<s:property value='dataStoreTypeId'/>"></select>
											</div>
										</div>
									</div>
									<div class="col-md-6 column" >
										<div class="form-group">
											 <label for="fileQueueMaxSize" class="col-sm-3 control-label">最大文件缓存量</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control" id="fileQueueMaxSize" name="webSiteOps.fileQueueMaxSize" value="<s:property value='webSiteOps.fileQueueMaxSize'/>" placeholder="最大文件缓存量"/>
											 </div>
										</div>
										
										<div class="form-group">
											 <label for="fileQueueFlushInterval" class="col-sm-3 control-label">文件flush间隔</label>
											 <div class="col-sm-9">
											 	<input type="text" class="form-control" id="fileQueueFlushInterval" name="webSiteOps.fileQueueFlushInterval" value="<s:property value='webSiteOps.fileQueueFlushInterval'/>" placeholder="文件flush间隔"/>
											 </div>
										</div>
										
										<div class="form-group">
											 <label for="fileQueueFlushInterval" class="col-sm-3 control-label">数据存在是否更新</label>
											 <div class="col-sm-9">
											 	<s:if test="type!=0">
													<s:radio list="#{'1':'是','0':'否'}" id="" name="webSiteOps.needUpdate" value="webSiteOps.needUpdate"/>
												</s:if>
												<s:else>
													<s:radio list="#{'1':'是','0':'否'}" id="" name="webSiteOps.needUpdate" value="0"/>
												</s:else>
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
			<input type="hidden" name="webSiteOps.webSiteOpsId" value="<s:property value='webSiteOps.webSiteOpsId'/>">
		</form>
	</div>
	<input type="hidden" id="type" value="<s:property value='type'/>">
	<input type="hidden" id="paginationHden" value="<s:property value='webSiteOps.pagination'/>">
	
	<script type="text/javascript">
		function refresh(){
		}
		
		$(function(){
			var type = $("#type").val();
			var paginationHden = $("#paginationHden").val();
			
			$.getJSON("findAllWebSite", function(data){
				var webSiteSelector = $("#webSiteSelector");
				$.each(data.jsonResult, function(key, value){
					webSiteSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				webSiteSelector.prepend("<option></option>");
				$("#webSiteSelector").select2({allowClear: true});
				
				$("#webSiteSelector").select2("val",$("#webSiteSelector").attr("myVal"));
			});
			
// 			<input type="hidden" id="websiteOpsIds" value="<s:property value='websiteOpsIds'/>">
// 			<select multiple class="form-control form-select2" id="parentWebSiteOpsSelector" name="parentWebsiteOpsIds"></select>
			
			$.getJSON("findAllWebSiteOps", function(data){
				var parentWebSiteOpsSelector = $("#parentWebSiteOpsSelector")
				var pWebSiteOpsSelector = $("#pWebSiteOpsSelector");
				var webSiteOpsSelector = $("#webSiteOpsSelector");
				$.each(data.jsonResult, function(key, value){
					webSiteOpsSelector.prepend("<option value='" + key + "'>" + value + "</option>");
					pWebSiteOpsSelector.prepend("<option value='" + key + "'>" + value + "</option>");
					parentWebSiteOpsSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				webSiteOpsSelector.prepend("<option></option>");
				pWebSiteOpsSelector.prepend("<option></option>");
				parentWebSiteOpsSelector.prepend("<option></option>");
				$("#parentWebSiteOpsSelector").select2({allowClear: true});
				$("#webSiteOpsSelector").select2({allowClear: true});
				$("#pWebSiteOpsSelector").select2({allowClear: true});
				$("#webSiteOpsSelector").select2("val", $("#webSiteOpsSelector").attr("myVal"));
				$("#pWebSiteOpsSelector").select2("val", $("#pWebSiteOpsSelector").attr("myVal"));
				console.log("$(#pWebSiteOpsSelector).attr(myVal)" + $("#pWebSiteOpsSelector").attr("myVal"));
				
				if(type==1){
					var roleIds = $("#websiteOpsIds").val();
					var roleIdArray = roleIds.split(",");
					var select2value = new Array();
					for(var i=0;i<roleIdArray.length;i++){
						var roleId = roleIdArray[i];
						if(roleId){
							select2value.push(roleId);
						}
					}
					$("#parentWebSiteOpsSelector").select2("val", select2value);
				}
				
			});
			
			$.getJSON("findAllDataStoreType", function(data){
				var dataStoreTypeSelector = $("#dataStoreTypeSelector");
				$.each(data.jsonResult, function(key, value){
					dataStoreTypeSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				dataStoreTypeSelector.prepend("<option></option>");
				$("#dataStoreTypeSelector").select2({allowClear: true});
				
				$("#dataStoreTypeSelector").select2("val",$("#dataStoreTypeSelector").attr("myVal"));
			});
			
			$.getJSON("findAllMachineRole", function(data){
				var machineRoleSelector = $("#machineRoleSelector");
				$.each(data.jsonResult, function(key, value){
					machineRoleSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				machineRoleSelector.prepend("<option></option>");
				$("#machineRoleSelector").select2({allowClear: true});
				
				$("#machineRoleSelector").select2("val",$("#machineRoleSelector").attr("myVal"));
			});
			
			if(paginationHden==0){
				$("#startPageGrp").hide();
				$("#endPageGrp").hide();
				$("#stepGrp").hide();
			}
			
			$(".paginationCls").change(function(){
				var pagination = $(this).val();
				if(pagination==1){
					$("#startPage").val("");
					$("#endPage").val("");
					$("#step").val("");
					$("#startPageGrp").show();
					$("#endPageGrp").show();
					$("#stepGrp").show();
				}else{
					$("#startPageGrp").hide();
					$("#endPageGrp").hide();
					$("#stepGrp").hide();
				}
			});
			
			$("#saveWebSiteOpsBtn").click(function(){
				if(type==1){
					$("#editWebSiteOpsForm").attr("action","editWebSiteOps");
				}else{
					$("#editWebSiteOpsForm").attr("action","newWebSiteOps");
				}
				
				$("#editWebSiteOpsForm").submit();
			});
			
			$("#editWebSiteOpsForm").submit(function(){
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					$("#saveWebSiteOpsBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>