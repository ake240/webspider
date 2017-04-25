<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站操作类型参数</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editWebSiteOpsParamForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站操作类型参数</h1>
					<button type="button" id="WebSiteOpsParamBtn1" class="btn btn-success saveWebSiteOpsParam">保存操作类型参数</button>
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
							 <div style="float: right;">
								<a class="glyphicon glyphicon-plus-sign icon-size addParamBtn"></a>
							 </div>
						</div>
						<div id="base-div" class="panel-collapse collapse in">
							<div class="panel-body" id="param-body">
								<div class="row clearfix">
									<div style="padding: 0 20px 20px 20px;">
										 <label for="operationSelector" >操作类型<span class="red">&nbsp;*</span></label>
										 <select class="form-select2 mandatorySel" id="operationSelector" name="webSiteOpsId" myVal="<s:property value='webSiteOpsId'/>"></select>
									</div>
									<div style="padding: 0 20px 20px 20px;">
										<label>参数组合</label>
										<textarea style="display: inline;" readonly="readonly" class="form-control"><s:property value='allParams'/></textarea>
									</div>
								</div>
								
								<%@ include file="/WEB-INF/jsp/websiteOps/webSiteOpsParamAdd.jspf" %>
								<s:if test="type==1">
									<s:iterator value="webSiteOpsParams">
										<%@ include file="/WEB-INF/jsp/websiteOps/webSiteOpsParam.jspf" %>		
									</s:iterator>
								</s:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div>
			<a class="glyphicon glyphicon-plus-sign icon-size addParamBtn" style="float: left;"></a>
			<button type="button" id="WebSiteOpsParamBtn2" style="float: right;" class="btn btn-success saveWebSiteOpsParam">保存操作类型参数</button>
		</div>
		</form>
	</div>
	<input type="hidden" id="type" value="<s:property value='type'/>">
	
	<script type="text/javascript">
		function refreshName(){
			var a=0;
			$(".new-field-cnname").each(function(){
				$(this).attr("name","webSiteOpsParams[" + a + "].fieldCnName");
				a=a+1;
			});
			
			var b=0;
			$(".new-field-enname").each(function(){
				$(this).attr("name","webSiteOpsParams[" + b + "].fieldEnName");
				b=b+1;
			});
			
			var c=0;
			$(".new-field-value").each(function(){
				$(this).attr("name","webSiteOpsParams[" + c + "].fieldValue");
				c=c+1;
			});
			
			var d=0;
			$(".new-sortno").each(function(){
				$(this).attr("name","webSiteOpsParams[" + d + "].sortNo");
				d=d+1;
			});
			
			var e=0;
			$(".new-field-desc").each(function(){
				$(this).attr("name","webSiteOpsParams[" + e + "].fieldDesc");
				e=e+1;
			});
			
			var x=0,y=0;
			$("input.new-field-type").each(function(){
				if(y<5){
					$(this).attr("name","webSiteOpsParams[" + x + "].fieldType");
					$(this).attr("id","fieldtype" + x + "" +y);
					$(this).next().attr("for","fieldtype" + x + "" +y);
					y=y+1;
				}
				if(y==5){
					y=0;
					x=x+1;
				}
			});
			
			var m=0,n=0;
			$("input.new-field-flag").each(function(){
				if(n<2){
					$(this).attr("name","webSiteOpsParams[" + m + "].fieldFlag");
					$(this).attr("id","fieldflag" + m + "" +n);
					$(this).next().attr("for","fieldflag" + m + "" +n);
					n=n+1;
				}
				if(n==2){
					n=0;
					m=m+1;
				}
			});
			
			var p=0,q=0;
			$("input.new-placeholder-flag").each(function(){
				if(q<3){
					$(this).attr("name","webSiteOpsParams[" + p + "].placeholderFlag");
					$(this).attr("id","placeholderflag" + p + "" +q);
					$(this).next().attr("for","placeholderflag" + p + "" +q);
					q=q+1;
				}
				if(q==3){
					q=0;
					p=p+1;
				}
			});
			
			var z=0;
			$(".new-placeholder").each(function(){
				$(this).attr("name","webSiteOpsParams[" + z + "].placeholder");
				z=z+1;
			});
			
			var u=0;
			$(".new-fieldid").each(function(){
				$(this).attr("name","webSiteOpsParams[" + u + "].fieldId");
				u=u+1;
			});
			
			var i=0,j=0;
			$("input.new-needencode").each(function(){
				if(j<5){
					$(this).attr("name","webSiteOpsParams[" + i + "].needEncode");
					$(this).attr("id","needencode" + i + "" + j);
					$(this).next().attr("for","needencode" + i + "" + j);
					j=j+1;
				}
				if(j==5){
					j=0;
					i=i+1;
				}
			});
			
			var f=0,g=0;
			$("input.new-need-store").each(function(){
				if(g<2){
					$(this).attr("name","webSiteOpsParams[" + f + "].needStore");
					$(this).attr("id","needStore" + f + "" + g);
					$(this).next().attr("for","needStore" + f + "" + g);
					g=g+1;
				}
				if(g==2){
					g=0;
					f=f+1;
				}
			});
		}
		
		$(function(){
			var type = $("#type").val();
			var detachedParamPanel = $("#detached-param");
			var newParamPanel = $("#new-param");
			
			$.getJSON("findAllWebSiteOps", function(data){
				var operationSelector = $("#operationSelector");
				$.each(data.jsonResult, function(key, value){
					operationSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				operationSelector.prepend("<option></option>");
				$("#operationSelector").select2({allowClear: true});
				$("#operationSelector").select2("val",$("#operationSelector").attr("myVal"));
				$("#operationSelector").select2("readonly",true);
			});
			
			if(type==0){
				detachedParamPanel.detach();
			}else{
				detachedParamPanel.remove();
				newParamPanel.detach();
			}
			
			refreshName();
			
			$(".addParamBtn").click(function(){
				var newParam = detachedParamPanel.clone();
				$("#param-body").append(newParam);
				
				$(".removeParamBtn").click(function(){
					var count = $(".param-tag").size();
					if(count>1){
						$(this).parent().parent().parent().remove();
						refreshName();
					}
				});
				refreshName();
			});
			
			$(".removeParamBtn").click(function(){
				var count = $(".param-tag").size();
				if(count>1){
					$(this).parent().parent().parent().remove();
					refreshName();
				}
			});
			
			$(".deleteParamInDBBtn").click(function(){
				var count = $(".deleteParamInDBBtn").size();
				if(count>1){
					if(confirm("确认删除?")){
						var fieldId = $(this).attr("fieldid");
						var paramPanel = $(this).parent().parent().parent();
						$.getJSON("deleteWebSiteOpsParam",{fieldId:fieldId},function(data){
							var ret = $.parseJSON(data.jsonResult);
							if(ret.succeed){
								paramPanel.remove();
								refreshName();
							}else{
								alert("错误：" + ret.msg);
							}
						});
					}
				}else{
					alert("操作类型参数仅有一个，不允许删除");
				}
			});
			
			$(".saveWebSiteOpsParam").click(function(){
				if(type==0){
					$("#editWebSiteOpsParamForm").attr("action","newWebSiteOpsParam");
				}else{
					$("#editWebSiteOpsParamForm").attr("action","editWebSiteOpsParam");
				}
				$("#editWebSiteOpsParamForm").submit();
			});
			
			$("#editWebSiteOpsParamForm").submit(function(){
				var ret = checkMandatory();
				if(ret){
					$(".saveWebSiteOpsParam").attr("disabled", "disabled");
				}else{
					alert("必填字段不允许为空");
					return false;
				}
			});
		});
	</script>
</body>
</html>