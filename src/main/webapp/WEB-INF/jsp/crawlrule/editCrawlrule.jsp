<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网页抽取规则</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form-horizontal" id="editCrawlruleForm" method="post" role="form">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="page-header page-title">
						<h1 style="display: inline;">网页抽取规则</h1>
						<s:if test="type!=1 || crawlrule.status==0">
							<button type="button" id="saveCrawlruleBtn" class="btn btn-success saveCrawlrule" style="margin-left: 20px;">保存规则</button>
						</s:if>
						<button type="button" id="validateCrawlruleBtn" class="btn btn-success validateCrawlrule">测试规则</button>
					</div>
					
					<!-- Begin 基本设置 -->
					<div class="panel-group" id="base-panel">
						<div class="panel panel-default">
							<div class="panel-heading" style="overflow: hidden;">
								 <a class="panel-title" data-toggle="collapse" data-parent="base-panel" href="#base-div">基本设置</a>
							</div>
							<div id="base-div" class="panel-collapse collapse in">
								<div class="panel-body">
									<div class="row clearfix">
										<input type="hidden" name="crawlrule.id" value="<s:property value='crawlrule.id'/>">
										<div class="col-md-6 column">
											<div class="form-group">
												 <label for="crawlruleName" class="col-sm-3 control-label">规则名称<span class="red">&nbsp;*</span></label>
												 <div class="col-sm-9">
												 	<input type="text" class="form-control mandatoryInp" id="crawlruleName" name="crawlrule.crawlRuleName" value="<s:property value='crawlrule.crawlRuleName'/>" placeholder="请输入规则名称"/>
												 </div>	
											</div>
											<div class="form-group">
												 <label for="pageUrl" class="col-sm-3 control-label">测试网址<span class="red">&nbsp;*</span></label>
												 <div class="col-sm-9">
												 	<textarea rows="2" cols="5" class="form-control mandatoryInp" id="pageUrl" name="crawlrule.pageUrl" placeholder="请输入测试网址"><s:property value='crawlrule.pageUrl'/></textarea>
												 </div>
											</div>
											
											<div class="form-group">
												 <label for="dataStoreTypeIdSelector" class="col-sm-3 control-label">存储类型<span class="red">&nbsp;*</span></label>
												 <div class="col-sm-9">
												 	<select class="form-control form-select2 mandatorySel" id="dataStoreTypeIdSelector" name="crawlrule.dataStoreTypeId" myVal="<s:property value='crawlrule.dataStoreTypeId'/>"></select>
												 </div>
											</div>
											
<!-- 											<div class="form-group"> -->
<%-- 												 <label for="operationSelector" class="col-sm-3 control-label">操作类型<span class="red">&nbsp;*</span></label> --%>
<!-- 												 <div class="col-sm-9"> -->
<%-- 												 	<select class="form-control form-select2 mandatorySel" id="operationSelector" name="crawlrule.websiteOperationId" myVal="<s:property value='crawlrule.websiteOperationId'/>"></select> --%>
<!-- 												 </div> -->
<!-- 											</div> -->

										</div>
										<div class="col-md-6 column">
											<div class="form-group">
												<label class="col-sm-3 control-label">页面编码<span class="red">&nbsp;*</span></label>
												<div class="col-sm-9">
													<s:if test="type==0">
														<s:radio list="#{'1':'utf-8','2':'gb2312', '3':'gbk', '4':'iso-8859-1'}" name="crawlrule.pageEncode" value="1"/>
													</s:if>
													<s:else>
														<s:radio list="#{'1':'utf-8','2':'gb2312', '3':'gbk', '4':'iso-8859-1'}" name="crawlrule.pageEncode" value="crawlrule.pageEncode"/>
													</s:else>
												</div>
											</div>
											<div class="form-group">
												 <label for="referer" class="col-sm-3 control-label">网站Referer</label>
												 <div class="col-sm-9">
												 	<input type="text" class="form-control" id="referer" name="crawlrule.referer" value="<s:property value='crawlrule.referer'/>" placeholder="请输入网站Referer"/>
												 </div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label">节点类别<span class="red">&nbsp;*</span></label>
												<div class="col-sm-9">
													<s:if test="type==0">
														<s:radio list="#{'1':'单节点','2':'列表'}" cssClass="nodeTypeRadio" name="crawlrule.nodeType" value="1"/>
													</s:if>
													<s:else>
														<input type="radio" name="crawlrule.nodeType" value="<s:property value='crawlrule.nodeType'/>" checked>
														<s:if test="crawlrule.nodeType==1">
															<label>单节点</label>
														</s:if>
														<s:else>
															<label>列表</label>
														</s:else>
													</s:else>
												</div>
											</div>
										</div>
									</div>
									<div class="row clearfix">
										<div class="col-md-12 column">
											<label for="websiteOperationIdList" class="col-sm-2 control-label">操作类型列表</label>
											<div class="col-sm-10">
												<input type="hidden" id="websiteOperationIds" value="<s:property value='crawlrule.websiteOperationIdList'/>"> 
												<select multiple class="form-control form-select2" id="websiteOperationIdList" name="crawlrule.websiteOperationIdList"></select>
<%-- 												<input type="text" class="form-control" id="websiteOperationIdList" name="crawlrule.websiteOperationIdList" value="<s:property value='crawlrule.websiteOperationIdList'/>" placeholder="请输入规则名称"/> --%>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- End 基本设置 -->
					
					<s:if test="type!=0">
						<input type="hidden" name="crawlrule.status" value="<s:property value='crawlrule.status'/>">
						<input type="hidden" name="crawlrule.insertTime" value="<s:date name='crawlrule.insertTime' format='yyyy-MM-dd HH:mm:ss'/>">
					</s:if>
					
					<!-- Begin 列表设置 -->
					<s:if test="type==0||type!=0&&crawlrule.nodeType==2">
						<div class="panel-group" id="list-node-panel">
							<div class="panel panel-default">
								<div class="panel-heading" style="overflow: hidden;">
									 <a class="panel-title" data-toggle="collapse" data-parent="list-node-panel" href="#list-node-div">列表规则设置</a>
								</div>
								<div id="list-node-div" class="panel-collapse collapse in">
									<div class="panel-body">
										<div class="row clearfix">
											<div class="col-md-6 column">
												<div class="form-group">
													 <label for="list-crawlNodeCnName" class="col-sm-3 control-label">节点中文名称<span class="red">&nbsp;*</span></label>
													 <div class="col-sm-9">
													 	<input type="text" class="form-control mandatoryInp" id="list-crawlNodeCnName" name="crawlrule.crawlRuleNodeList[0].crawlNodeCnName" value="<s:property value='crawlrule.crawlRuleNodeList[0].crawlNodeCnName'/>" placeholder="请输入节点中文名称"/>
													 </div>
												</div>
												<div class="form-group">
													<label for="" class="col-sm-3 control-label">抽取方式<span class="red">&nbsp;*</span></label>
													<div class="col-sm-9">
														<s:if test="type==0">
															<s:radio list="#{'1':'xpath','2':'正则', '3':'文本值'}" name="crawlrule.crawlRuleNodeList[0].crawlExprType" value="1"/>
														</s:if>
														<s:else>
															<s:radio list="#{'1':'xpath','2':'正则', '3':'文本值'}" name="crawlrule.crawlRuleNodeList[0].crawlExprType" value="crawlrule.crawlRuleNodeList[0].crawlExprType"/>
														</s:else>
														
													</div>
												</div>
<!-- 												<div class="form-group"> -->
<!-- 													 <label for="extOperationSelector" class="col-sm-3 control-label">操作类型</label> -->
<!-- 													 <div class="col-sm-9"> -->
<%-- 													 	<select class="form-control form-select2" id="extOperationSelector" name="crawlrule.crawlRuleNodeList[0].extWebsiteOperationId" myVal="<s:property value='crawlrule.crawlRuleNodeList[0].extWebsiteOperationId'/>"></select> --%>
<!-- 													 </div> -->
<!-- 												</div> -->
											</div>
											<div class="col-md-6 column">
												<div class="form-group">
													 <label for="list-crawlNodeEnName" class="col-sm-3 control-label">节点英文名称<span class="red">&nbsp;*</span></label>
													 <div class="col-sm-9">
													 	<input type="text" class="form-control mandatoryInp" id="list-crawlNodeEnName" name="crawlrule.crawlRuleNodeList[0].crawlNodeEnName" value="<s:property value='crawlrule.crawlRuleNodeList[0].crawlNodeEnName'/>" placeholder="请输入节点英文名称"/>
													 </div>
												</div>
												<div class="form-group">
													 <label for="list-crawlExprVal" class="col-sm-3 control-label">抓取表达式<span class="red">&nbsp;*</span></label>
													 <div class="col-sm-9">
													 	<input type="text" class="form-control mandatoryInp" id="list-crawlExprVal" name="crawlrule.crawlRuleNodeList[0].crawlExprVal" value="<s:property value='crawlrule.crawlRuleNodeList[0].crawlExprVal'/>" placeholder="请输入抓取表达式"/>
													 </div>
												</div>
												<div class="form-group">
													 <label for="extCrawlNodeNameSelector" class="col-sm-3 control-label">抓取节点名称</label>
													 <div class="col-sm-9">
													 	<%-- <input type="text" class="form-control" id="list-extCrawlNodeName" name="crawlrule.crawlRuleNodeList[0].extCrawlNodeName" value="<s:property value='crawlrule.crawlRuleNodeList[0].extCrawlNodeName'/>" placeholder="请输入抓取节点名称"/> --%>
													 	<select class="form-control" id="extCrawlNodeNameSelector" name="crawlrule.crawlRuleNodeList[0].extCrawlNodeName" myVal="<s:property value='crawlrule.crawlRuleNodeList[0].extCrawlNodeName'/>"></select>
													 </div>
												</div>
											</div>
										</div>
										
										<input type="hidden" name="crawlrule.crawlRuleNodeList[0].nodeType" value="2" />
										
										<div class="panel-group" id="removeexpr-panel">
											<div class="panel panel-default">
												<div class="panel-heading" style="overflow: hidden;">
													<a class="panel-title" data-toggle="collapse" data-parent="removeexpr-panel" href="#reomveexpr-div">去除规则设置</a>
													<div style="float: right;">
														<a class="glyphicon glyphicon-plus-sign icon-size" id="addRemoveExprBtn"></a>
													 </div>
												</div>
												<div id="reomveexpr-div" class="panel-collapse collapse in">
													<div class="panel-body">
														<div class="row clearfix" id="removeexpr-content">
															<div class="col-md-12 column removeexpr-node" style="overflow: hidden; margin-bottom: 10px;" id="removeexpr-add">
																<div class="inline">
																	<label style="margin-right: 20px;">抽取方式<span class="red">&nbsp;*</span></label>
																	<s:radio list="#{'1':'xpath','2':'正则', '3':'文本值'}" id="" name="" cssClass="removeexpr-type" value="1"/>
																</div>
																<div class="inline" style="margin-left: 50px;">
																	<label style=" margin-right: 20px;">抓取表达式<span class="red">&nbsp;*</span></label>
																	<input type="text" class="form-control inline removeexpr-value mandatoryInp" style="width: 500px;">
																</div>
																<a style="margin-left: 50px;"><i class="glyphicon glyphicon-minus-sign icon-size remove-exprevalue"></i></a>
															</div>
															
															<s:if test="type!=0">
																<s:iterator value="crawlrule.crawlRuleNodeList[0].removeCrawlExprValues" status="st">
																	<div class="col-md-12 column removeexpr-node" style="overflow: hidden; margin-bottom: 10px;">
																		<s:set name="exprType" value="value"></s:set>
																		<s:set name="exprValue" value="key"></s:set>
																		<div class="inline">
																			<label style="margin-right: 20px;">抽取方式</label>
																			<s:radio list="#{'1':'xpath','2':'正则', '3':'文本值'}" id="exprType%{#st.index}" name="exprType%{#st.index}" cssClass="removeexpr-type" value="#exprType"/>
																		</div>
																		<div class="inline" style="margin-left: 50px;">
																			<label style=" margin-right: 20px;">抓取表达式</label>
																			<input type="text" class="form-control inline removeexpr-value mandatoryInp" style="width: 500px;" value="<s:property value='key'/>">
																		</div>
																		<a style="margin-left: 50px;"><i class="glyphicon glyphicon-minus-sign icon-size remove-exprevalue"></i></a>
																	</div>
																</s:iterator>
															</s:if>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</s:if>
					<!-- End 列表设置 -->
					
					
					<!-- Begin 抽取内容 -->
					<div class="panel-group" id="content-panel">
						<div class="panel panel-default">
							<div class="panel-heading" style="overflow: hidden;">
								 <a class="panel-title" data-toggle="collapse" data-parent="content-panel" href="#content-div">抽取内容设置</a>
								 <div style="float: right;">
									<a class="glyphicon glyphicon-plus-sign icon-size addContentBtn"></a>
								 </div>
							</div>
							<div id="content-div" class="panel-collapse collapse in">
								<div class="panel-body" id="add-content">
									<%@ include file="/WEB-INF/jsp/crawlrule/crawlruleNodeAdd.jspf" %>
									<s:if test="type==0">
										
									</s:if>
									<s:else>
										<s:if test="crawlrule.nodeType==1">
											<s:iterator value="crawlrule.crawlRuleNodeList" status="st">
												<s:set name="namePrefix" value="'crawlrule.crawlRuleNodeList'"></s:set>
												<%@ include file="/WEB-INF/jsp/crawlrule/crawlruleNode.jspf" %>
											</s:iterator>
										</s:if>
										<s:else>
											<s:iterator value="crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList" status="st">
												<s:set name="namePrefix" value="'crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList'"></s:set>
												<%@ include file="/WEB-INF/jsp/crawlrule/crawlruleNode.jspf" %>
											</s:iterator>
										</s:else>
									</s:else>
								</div>
								
								<div id="stringLengthGroup">
									<label for="" class="col-sm-3 control-label">字符串长度</label>
									<div class="col-sm-9">
										<input type="text" class="form-control new-content-minlength inline" name="" placeholder="min" style="width: 170px; float: left;">
										<input type="text" class="form-control new-content-maxlength inline" name="" placeholder="max" style="width: 170px; float: right;">
									</div>
								</div>
								<div id="referGroup">
									<label for="" class="col-sm-3 control-label">Refer</label>
									<div class="col-sm-9">
										<s:radio list="#{'true':'是','false':'否'}" id="" cssClass="new-content-refer" name="" value="false"/>
									</div>
								</div>
								<div class="form-group" id="replace-rule-add">
									<label for="" class="col-sm-3 control-label">请选择<span class="red">&nbsp;*</span></label>
									<div class="col-sm-8">
									 	<select class="form-control replace-rule-selector mandatoryDefSel"></select>
									</div>
									<div class="col-sm-1" style="float: right;">
										<a class="glyphicon glyphicon-minus-sign icon-size removeReplaceRuleBtn"></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- End 抽取内容 -->
			</div>
			</div>
			<div>
				<a class="glyphicon glyphicon-plus-sign icon-size addContentBtn" style="float: left;"></a>
				<s:if test="type!=1 || crawlrule.status==0">
					<button type="button" id="saveCrawlruleBtn2" style="float: right; margin-left: 20px;" class="btn btn-success saveCrawlrule">保存规则</button>
				</s:if>
				<button type="button" id="validateCrawlruleBtn2" style="float: right;" class="btn btn-success validateCrawlrule">测试规则</button>
			</div>
		</form>
	</div>
	
	<input type="hidden" id="type" value="<s:property value='type'/>">
	<input type="hidden" id="nodeType" value="<s:property value='crawlrule.nodeType'/>">
	
	<script type="text/javascript">
		
		var listNodePanel = $("#list-node-panel");
		var detachedContentPanel = $("#detached-content");
		var newContentPanel = $("#new-content");
		var removeexprAdd = $("#removeexpr-add")
		var type = $("#type").val();
		var nodeType = $("#nodeType").val();
		
		var stringLengthGroup = $("#stringLengthGroup");
		var referGroup = $("#referGroup");
		var replaceRuleAdd = $("#replace-rule-add");
		
		//var fieldNameMap = new Map();
		var fieldNameMap = {};
	
		function refreshName(type,radio){
			
			var i=0;
			$(".new-content-nodeid").each(function(){
				if(radio=='1'){
					$(this).attr("name","crawlrule.crawlRuleNodeList[" + i + "].crawlNodeId");
				}else{
					$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + i + "].crawlNodeId");
				}
				i=i+1;
			});
			
			var j=0;
			$(".new-content-enname").each(function(){
				if(radio=='1'){
					$(this).attr("name","crawlrule.crawlRuleNodeList[" + j + "].crawlNodeEnName");
				}else{
					$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + j + "].crawlNodeEnName");
				}
				j=j+1;
			});
			
			var m=0,n=0;
			$("input.new-content-crawltype").each(function(){
				if(n<4){
					if(radio=='1'){
						$(this).attr("name","crawlrule.crawlRuleNodeList[" + m + "].crawlExprType");
						$(this).attr("id","contentcrawltype" + m + "" + n);
						$(this).next().attr("for","contentcrawltype" + m + "" + n);
					}else{
						$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + m + "].crawlExprType");
						$(this).attr("id","contentcrawltype" + m + "" + n);
						$(this).next().attr("for","contentcrawltype" + m + "" + n);
					}
					n=n+1;
				}
				if(n==4){
					n=0;
					m=m+1;
				}
			});
			
			var k=0;
			$(".new-content-expr").each(function(){
				if(radio=='1'){
					$(this).attr("name","crawlrule.crawlRuleNodeList[" + k + "].crawlExprVal");
				}else{
					$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + k + "].crawlExprVal");
				}
				k=k+1;
			});
			
			var p=0,q=0;
			$("input.new-content-trimblank").each(function(){
				if(q<2){
					if(radio=='1'){
						$(this).attr("name","crawlrule.crawlRuleNodeList[" + p + "].trimBlank");
						$(this).attr("id","trimblank" + p + "" + q);
						$(this).next().attr("for","trimblank" + p + "" + q);
					} else {
						$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + p + "].trimBlank");
						$(this).attr("id","trimblank" + p + "" + q);
						$(this).next().attr("for","trimblank" + p + "" + q);
					}
					q=q+1;
				}
				if(q==2){
					q=0;
					p=p+1;
				}
			});
			
			var p=0,q=0;
			$("input.new-content-selectable").each(function(){
				if(q<2){
					if(radio=='1'){
						$(this).attr("name","crawlrule.crawlRuleNodeList[" + p + "].selectable");
						$(this).attr("id","contentselectable" + p + "" + q);
						$(this).next().attr("for","contentselectable" + p + "" + q);
					}else{
						$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + p + "].selectable");
						$(this).attr("id","contentselectable" + p + "" + q);
						$(this).next().attr("for","contentselectable" + p + "" + q);
					}
					q=q+1;
				}
				if(q==2){
					q=0;
					p=p+1;
				}
			});
			
			var r=0,s=0;
			$("input.new-content-sourcehtml").each(function(){
				if(s<2){
					if(radio=='1'){
						$(this).attr("name","crawlrule.crawlRuleNodeList[" + r + "].nodeHtmlSourceOptional");
						$(this).attr("id","contentnodeHtmlSourceOptional" + r + "" +s);
						$(this).next().attr("for","contentnodeHtmlSourceOptional" + r + "" +s);
					}else{
						$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + r + "].nodeHtmlSourceOptional");
						$(this).attr("id","contentnodeHtmlSourceOptional" + r + "" +s);
						$(this).next().attr("for","contentnodeHtmlSourceOptional" + r + "" +s);
					}
					s=s+1;
				}
				if(s==2){
					s=0;
					r=r+1;
				}
			});
			
			var a=0,b=0;
			$("input.new-content-nodeproperty").each(function(){
				if(b<4){
					if(radio=='1'){
						$(this).attr("name","crawlrule.crawlRuleNodeList[" + a + "].nodeProperty");
						$(this).attr("id","contentnodeProperty" + a + "" +b);
						$(this).next().attr("for","contentnodeProperty" + a + "" +b);
					}else{
						$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + a + "].nodeProperty");
						$(this).attr("id","contentnodeProperty" + a + "" +b);
						$(this).next().attr("for","contentnodeProperty" + a + "" +b);
					}
					b=b+1;
				}
				if(b==4){
					b=0;
					a=a+1;
				}
			});
			
			var e=0;
			$(".new-content-nodetype").each(function(){
				if(radio=='1'){
					$(this).attr("name","crawlrule.crawlRuleNodeList[" + e + "].nodeType");
				}else{
					$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + e + "].nodeType");
				}
				e=e+1;
			});
			
			var x=0;
			$(".multigroup").each(function(){
				$(this).attr("id","multigroup"+x);
				x=x+1;
			});
			
			x = 0; 
			$(".replace-rule-holder").each(function(){
				$(this).attr("id", "replace-rule-holder" + x);
				x=x+1;
			});
			
			x=0;
			$(".replace-rule-panel").each(function(){
				var replaceRulePanel = $(this);
				replaceRulePanel.attr("id","replace-rule-panel" + x);
				var replaceRulePanelIndex = 0;
				replaceRulePanel.find(".replace-rule-selector").each(function(){
					var holderId = $(this).parent().parent().parent().attr("id");
	 				var c = holderId.substring(19);
	 				if(radio=='1'){
	 					$(this).attr("name","crawlrule.crawlRuleNodeList["+c+"].placeholderList["+replaceRulePanelIndex+"]");
	 				} else {
	 					$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList["+c+"].placeholderList["+replaceRulePanelIndex+"]");
	 				}
	 				replaceRulePanelIndex = replaceRulePanelIndex + 1;
				});
				x=x+1;
			});
			
			$(".new-content-minlength").each(function(){
				var multigroupId = $(this).parent().parent().parent().attr("id");
				var c = multigroupId.substring(10);
				if(radio=='1'){
					$(this).attr("name","crawlrule.crawlRuleNodeList[" + c + "].minLength");
				}else{
					$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + c + "].minLength");
				}
			});
			
			$(".new-content-maxlength").each(function(){
				var multigroupId = $(this).parent().parent().parent().attr("id");
				var d = multigroupId.substring(10);
				if(radio=='1'){
					$(this).attr("name","crawlrule.crawlRuleNodeList[" + d + "].maxLength");
				}else{
					$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + d + "].maxLength");
				}
			});
			
			var v=0;
			$("input.new-content-refer").each(function(){
				var multigroupId = $(this).parent().parent().parent().attr("id");
				var u = multigroupId.substring(10);
				if(v<2){
					if(radio=='1'){
						$(this).attr("name","crawlrule.crawlRuleNodeList[" + u + "].refererFlag");
						$(this).attr("id","contentrefer" + u + "" +v);
						$(this).next().attr("for","contentrefer" + u + "" +v);
					}else{
						$(this).attr("name","crawlrule.crawlRuleNodeList[0].childCrawlRuleNodeList[" + u + "].refererFlag");
						$(this).attr("id","contentrefer" + u + "" +v);
						$(this).next().attr("for","contentrefer" + u + "" +v);
					}
					v=v+1;
				}
				
				if(v==2){
					v=0;
				}
			});
		}
		
		function refreshListNodeSetting(){
			$("#list-crawlNodeCnName").val("");
			$("#list-crawlNodeEnName").val("");
			$("#list-crawlExprVal").val("");
			/* $("#list-extCrawlNodeName").val(""); */
			$("#extCrawlNodeNameSelector").val("");
			$("input[name='crawlrule.crawlRuleNodeList[0].crawlExprType'][value='1']").attr("checked","checked");
			
// 			$.getJSON("findAllWebSiteOps", function(data){
// 				var extOperationSelector = $("#extOperationSelector");
// 				extOperationSelector.empty();
// 				$.each(data.jsonResult, function(key, value){
// 					extOperationSelector.prepend("<option value='" + key + "'>" + value + "</option>");
// 				});
// 				extOperationSelector.prepend("<option></option>");
// 				$("#extOperationSelector").select2({allowClear: true});
// 				$("#extOperationSelector").select2("val","");
// 			});
			// $("#s2id_extOperationSelector .select2-search-choice-close").css("color","red");
		}
		
		function refreshRemoveName(){
			var i=0,j=0;
			$("input.removeexpr-type").each(function(){
				if(j<3){
					$(this).attr("id","exprType"+i+""+j);
					$(this).attr("name","exprType"+i);
					$(this).next().attr("for","exprType"+i+""+j);
					j=j+1;
				}
				
				if(j==3){
					j=0;
					i=i+1;
				}
			});
		}
		
		function refreshFieldNameAfterLoad(){
			// var webSiteOpsId = $("#operationSelector").val();
			var dataStoreTypeId = $("#dataStoreTypeIdSelector").val();
			$.getJSON("findAllDataStoreField", {dataStoreTypeId:dataStoreTypeId}, function(data){
				//fieldNameMap.clear();
				fieldNameMap = data.jsonResult;
				
				$("#extCrawlNodeNameSelector").empty();
				$.each(data.jsonResult, function(key, value){
					//$("#extCrawlNodeNameSelector").prepend("<option value='" + value + "'>" + key + "</option>");
					$("#extCrawlNodeNameSelector").prepend("<option value='" + value.fieldEnName + "'>" + value.fieldCnName + "</option>");
				});
				$("#extCrawlNodeNameSelector").prepend("<option></option>");
								
				var extCrawlNodeName = $("#extCrawlNodeNameSelector").attr("myVal");
				$("#extCrawlNodeNameSelector").val(extCrawlNodeName);
				
				$("select.new-content-nodeid").each(function(){
					var myVal = $(this).attr("myVal");
					var sel = $(this);
					sel.empty();
					$.each(data.jsonResult, function(key, value){
						//sel.prepend("<option value='" + key + "'>" + key + "</option>");
						//fieldNameMap.put(key,value);
						sel.prepend("<option value='" + key + "'>" + value.fieldCnName + "</option>");
					});
					$(this).prepend("<option></option>");
					$(this).val(myVal);
				});
				
				$("select.replace-rule-selector").each(function(){
					var myVal = $(this).attr("myVal");
					var sel = $(this);
					sel.empty();
					$.each(data.jsonResult, function(key,value){
						//sel.prepend("<option value='"+value+"'>" + key + "</option>" );
						sel.prepend("<option value='" + value.fieldEnName + "'>" + value.fieldCnName + "</option>");
					})
					$(this).prepend("<option></option>");
					$(this).val(myVal);
				})
			});
		}
		
		function refreshFieldName(){
			var dataStoreTypeId = $("#dataStoreTypeIdSelector").val();
			$.getJSON("findAllDataStoreField", {dataStoreTypeId:dataStoreTypeId}, function(data){
				//fieldNameMap.clear();
				fieldNameMap = data.jsonResult;
				
				var extCrawlNodeName = $("#extCrawlNodeNameSelector").val();
				console.log("#extCrawlNodeNameSelector"+extCrawlNodeName)
				$("#extCrawlNodeNameSelector").empty();
				$.each(data.jsonResult, function(key, value){
					//$("#extCrawlNodeNameSelector").prepend("<option value='" + value + "'>" + key + "</option>");
					$("#extCrawlNodeNameSelector").prepend("<option value='" + value.fieldEnName + "'>" + value.fieldCnName + "</option>");
				});
				$("#extCrawlNodeNameSelector").prepend("<option></option>");
								
				$("#extCrawlNodeNameSelector").val(extCrawlNodeName);
				
				$("select.new-content-nodeid").each(function(){
					//var myVal = $(this).attr("myVal");
					var myVal = $(this).val();
					var sel = $(this);
					sel.empty();
					$.each(data.jsonResult, function(key, value){
						//sel.prepend("<option value='" + key + "'>" + key + "</option>");
						//fieldNameMap.put(key,value);
						sel.prepend("<option value='" + key + "'>" + value.fieldCnName + "</option>");
					});
					$(this).prepend("<option></option>");
					$(this).val(myVal);
				});
				
				$("select.replace-rule-selector").each(function(){
					var myVal = $(this).val();
					var sel = $(this);
					sel.empty();
					$.each(data.jsonResult, function(key,value){
						//sel.prepend("<option value='"+value+"'>" + key + "</option>" );
						sel.prepend("<option value='" + value.fieldEnName + "'>" + value.fieldCnName + "</option>");
					})
					$(this).prepend("<option></option>");
					$(this).val(myVal);
				})
			});
		}
		
		$(function(){
			
			// $("#s2id_extOperationSelector .select2-search-choice-close").css("color","red");
			
			$.getJSON("findAllDataStoreType", function(data){
				var dataStoreTypeIdSelector = $("#dataStoreTypeIdSelector");
				$.each(data.jsonResult, function(key, value){
					dataStoreTypeIdSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				dataStoreTypeIdSelector.prepend("<option></option>")
				dataStoreTypeIdSelector.select2({allowClear:true});
				if(type!=0){
					//dataStoreTypeIdSelector
					$("#dataStoreTypeIdSelector").select2("val",$("#dataStoreTypeIdSelector").attr("myVal"));
				}
			});
			
			$.getJSON("findAllWebSiteOps", function(data){
				
// 				<label for="websiteOperationIdList" class="col-sm-2 control-label">操作类型列表</label>
// 					<input type="hidden" id="websiteOperationIds" value="<s:property value='crawlrule.websiteOperationIdList'/>"> 
// 					<select multiple class="form-control form-select2" id="websiteOperationIdList" name="crawlrule.websiteOperationIdList"></select>
				
				var operationListSelector = $("#websiteOperationIdList");
				
				//var operationSelector = $("#operationSelector");
				//var extOperationSelector = $("#extOperationSelector");
				$.each(data.jsonResult, function(key, value){
					//operationSelector.prepend("<option value='" + key + "'>" + value + "</option>");
					//extOperationSelector.prepend("<option value='" + key + "'>" + value + "</option>");
					operationListSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				//operationSelector.prepend("<option></option>");
				//extOperationSelector.prepend("<option></option>");
				//$("#operationSelector").select2({allowClear: true});
				//$("#extOperationSelector").select2({allowClear: true});
				//$("#operationSelector").select2("val","");
				//$("#extOperationSelector").select2("val","");
				
				operationListSelector.prepend("<option></option>")
				$("#websiteOperationIdList").select2({allowClear:true});
				
				if(type!=0){
					//$("#operationSelector").select2("val",$("#operationSelector").attr("myVal"));
					//$("#extOperationSelector").select2("val",$("#extOperationSelector").attr("myVal"));
					
					var websiteOperationIds = $("#websiteOperationIds").val();
					if(websiteOperationIds == null || websiteOperationIds.length < 2){
					} else {
						websiteOperationIds = websiteOperationIds.substring(1,websiteOperationIds.length-1);
						var websiteOperationIdArray = websiteOperationIds.split(",");
						var select2value = new Array();
						for(var i=0;i<websiteOperationIdArray.length;i++){
							var roleId = websiteOperationIdArray[i];
							if(roleId){
								select2value.push(roleId.trim());
							}
						}
						$("#websiteOperationIdList").select2("val", select2value);
					}
					
					refreshFieldNameAfterLoad();
					
				}
			});
			
			removeexprAdd.detach();
			stringLengthGroup.detach();
			referGroup.detach();
			replaceRuleAdd.detach();
			
			if(type==0 || type!=0&&nodeType==1){
				listNodePanel.detach();
			}
			
			if(type==0){
				radio=1;
				detachedContentPanel.detach();
			}else{
				radio=nodeType;
				detachedContentPanel.remove();
 				newContentPanel.detach();
			}
			
			refreshName(type,radio);
			
			$(".nodeTypeRadio").change(function(){
				radio = $(this).val();
				refreshName(type,radio);
				refreshFieldName();
				if(radio==2){
					$("#base-panel").after(listNodePanel);
					refreshListNodeSetting();
					$("#removeexpr-content").empty();
					
					$("#addRemoveExprBtn").unbind("click");
					$("#addRemoveExprBtn").click(function(){
						var newRemoveExpr = removeexprAdd.clone();
						$("#removeexpr-content").append(newRemoveExpr);
						refreshRemoveName();
						
						$(".remove-exprevalue").click(function(){
							$(this).parent().parent().remove();
						});
					});
				}else{
					$("#list-node-panel").detach();
				}
			});
			
			/* this field is deprecated 
			$("#operationSelector").change(function(){
				refreshFieldName();
			}); 
			*/
			$("#dataStoreTypeIdSelector").change(function(){
				refreshFieldName();
			});
			
			$("select.new-content-nodeid").change(function(){
				//var cnName = $(this).val();
				//var enName = fieldNameMap.get(cnName);
				//$(this).next().val(enName);
				var nodeId = $(this).val();
				var fieldMap = fieldNameMap[nodeId];
				$(this).next().val(fieldMap.fieldEnName);
				$(this).next().next().val(fieldMap.fieldCnName);
			});
			
			/// added by zhicheng
			$("input.new-content-crawltype").change(function(){
				var nodeProperty = $(this).val();
				var replaceRulePanel =$(this).parent().parent().parent().parent().next(); 
				
				if(nodeProperty==4){
					// display:true
					replaceRulePanel.show();//.css("style","overflow:hidden")
				} else {
					var id = replaceRulePanel.attr("id");
					var key = id.substring(18);//replace-rule-panel0
					var replaceRuleHolder = $("#replace-rule-holder"+key);
					replaceRuleHolder.empty();
					replaceRulePanel.hide();//css("style","overflow:hidden;display:none");
					
				}
				refreshName(type, radio);
				refreshFieldName();
			})
			/// end add by zhicheng
			
			$("input.new-content-nodeproperty").change(function(){
				var nodeProperty = $(this).val();
				var id = $(this).attr("id");
				var key = id.substring(19, id.length-1);
				var multigroup = $("#multigroup"+key);
				multigroup.empty();
				
				if(nodeProperty==1){
					multigroup.append(stringLengthGroup.clone());
				}else if(nodeProperty==4){
					multigroup.append(referGroup.clone());
				}
				refreshName(type,radio);
			});
			
			$(".saveCrawlrule").click(function(){
				if(type==1){
					$("#editCrawlruleForm").attr("action","editCrawlrule");
				}else{
					$("#editCrawlruleForm").attr("action","newCrawlrule");				
				}
				$("#editCrawlruleForm").attr("target","_self");
				$("#editCrawlruleForm").submit();
			});
			
			$(".validateCrawlrule").click(function(){
				$("#editCrawlruleForm").attr("action","validateCrawlrule");
				$("#editCrawlruleForm").attr("target","_blank");
				$("#editCrawlruleForm").submit();
			});
			
			$("#editCrawlruleForm").submit(function(){
				var m=0;
				$(".removeexpr-node").each(function(){
					$(this).find("input.removeexpr-type").each(function(){
						$(this).attr("name","removeCrawlExprValues[" + m + "].crawlExprType");	
					});
					$(this).find("input.removeexpr-value").attr("name","removeCrawlExprValues[" + m +"].crawlExprValue");
					m=m+1;
				});
				
				var ret = checkMandatory();
				
// 				if(nodeType==2){
// 					// var extOperationId = $("#extOperationSelector").select2("val");
// 					/* var listExtCrawlNodeName = $("#list-extCrawlNodeName").val(); */
// 					var extCrawlNodeName = $("#extCrawlNodeNameSelector").val();
// 					if( !extCrawlNodeName){ //extOperationId &&
// 						$("#extCrawlNodeNameSelector").addClass("input-error");
// 						ret = false;
// 					}else{
// 						$("#extCrawlNodeNameSelector").removeClass("input-error");
// 					}
// 				}
				
				var typeRet = true;
				var replaceAlertInfo = "";
				$("input.new-content-crawltype:checked").each(function(){
					var crawlType = $(this).val();
					if(crawlType == 4){
						replaceAlertInfo = $(this).parent().parent().parent().find("select.new-content-nodeid").eq(0).find("option:checked").text()
						var newContentExprPanel= $(this).parent().parent().parent().next();
						var exprVal = newContentExprPanel.find(".new-content-expr").eq(0).val();
						var replaceRulePanel = $(this).parent().parent().parent().parent().next();
						var replaceRuleCount = replaceRulePanel.find("select.replace-rule-selector").length;
						if(!(replaceRuleCount == (exprVal.split("@#@").length-1))){
							typeRet = false;							
						}
					}
				});
				
				if(!typeRet){
					alert(replaceAlertInfo + " 替换条数不对");
					return false;
				}
				
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					var act = $("#editCrawlruleForm").attr("action");
					if(act != "validateCrawlrule"){
						$(".saveCrawlrule").attr("disabled", "disabled");
					}
				}
			});
			
			addReplaceRuleButtonClickEvent();
			removeReplaceRuleBtnRefresh();
			
			$(".addContentBtn").click(function(){
				var newContent = detachedContentPanel.clone();
				$("#add-content").append(newContent);
				
				$("input.new-content-nodeproperty").each(function(){
					$(this).unbind("change");
				})
				$("input.new-content-crawltype").each(function(){
					$(this).unbind("change");
				})
				$(".add-replace-rule-button").each(function(){
					$(this).unbind("click");
				})
				/// added by zhicheng
				addNewContentCrawltypeChangeEvent();
				addReplaceRuleButtonClickEvent();				
				addNewContentNodePropertyChangeEvent();
				/// end add by zhicheng
				
				refreshName(type,radio);
				refreshFieldName();
				
				removeReplaceRuleBtnRefresh();
				
				$("select.new-content-nodeid").change(function(){
					//var cnName = $(this).val();
					//var enName = fieldNameMap.get(cnName);
					//$(this).next().val(enName);
					var nodeId = $(this).val();
					var fieldMap = fieldNameMap[nodeId];
					$(this).next().val(fieldMap.fieldEnName);
					$(this).next().next().val(fieldMap.fieldCnName);
				});
				
				$(".removeContentBtn").click(function(){
					var count = $(".content-tag").size();
					if(count>1){
						$(this).parent().parent().parent().remove();
						refreshName(type,radio);
					}
				});
			});
			
			$("#addRemoveExprBtn").click(function(){
				var newRemoveExpr = removeexprAdd.clone();
				$("#removeexpr-content").append(newRemoveExpr);
				refreshRemoveName();
				
				$(".remove-exprevalue").click(function(){
					$(this).parent().parent().remove();
				});
			});
			
			$(".remove-exprevalue").click(function(){
				$(this).parent().parent().remove();
			});
			
			$(".removeContentBtn").click(function(){
				var count = $(".content-tag").size();
				if(count>1){
					$(this).parent().parent().parent().remove();
					refreshName(type,radio);
				}
			});
		});
		
		function addNewContentCrawltypeChangeEvent(){
			$("input.new-content-crawltype").change(function(){
				var nodeProperty = $(this).val();
				var replaceRulePanel =$(this).parent().parent().parent().parent().next(); 
				
				if(nodeProperty==4){
					// display:true
					replaceRulePanel.show();//.css("style","overflow:hidden")
				} else {
					var id = replaceRulePanel.attr("id");
					var key = id.substring(18);//replace-rule-panel0
					var replaceRuleHolder = $("#replace-rule-holder"+key);
					replaceRuleHolder.empty();
					replaceRulePanel.hide();//css("style","overflow:hidden;display:none");
					
				}
				refreshName(type, radio);
				removeReplaceRuleBtnRefresh()
				refreshFieldName();
			});
		}
		
		function addReplaceRuleButtonClickEvent(){
			$(".add-replace-rule-button").click(function(){
				var newContent = replaceRuleAdd.clone();
				$(this).parent().parent().next().children().eq(0).children().eq(0).children().eq(0).append(newContent);
				refreshName(type, radio);
				removeReplaceRuleBtnRefresh()
				refreshFieldName();
			});
		}
		
		
		function addNewContentNodePropertyChangeEvent(){
			$("input.new-content-nodeproperty").change(function(){
				var nodeProperty = $(this).val();
				var id = $(this).attr("id");
				var key = id.substring(19, id.length-1);
				var multigroup = $("#multigroup"+key);
				multigroup.empty();
				
				if(nodeProperty==1){
					multigroup.append(stringLengthGroup.clone());
				}else if(nodeProperty==4){
					multigroup.append(referGroup.clone());
				}
				refreshName(type,radio);
				refreshFieldName();
			});
		}
		
		function removeReplaceRuleBtnRefresh(){
			$(".removeReplaceRuleBtn").each(function(){
				$(this).unbind("click");
			});
			$(".removeReplaceRuleBtn").click(function(){
				$(this).parent().parent().remove();
				refreshName(type, radio);
				refreshFieldName();
			});
		};
		
		
	</script>
</body>
</html>