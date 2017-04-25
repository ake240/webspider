<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证网页抽取规则</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">验证网页抽取规则</h1>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							抽取结果
						</h3>
					</div>
					<div class="panel-body">
						<div>
							<label for="抽取规则名称">抽取规则名称:</label>
							<span><s:property value='crawlrule.crawlRuleName'/></span>
						</div>
						<div>
							<label for="pageUrl">网页URL:</label>
							<a href="<s:property value='pageData.crawlUrl'/>" target="_blank"><s:property value='pageData.crawlUrl'/></a>
						</div>
						<div style="border-bottom: 1px solid #ccc; padding-top: 10px; padding-bottom: 10px;">
							 <label for="parseSuccess">验证结果:</label>
							 <span><s:property value='pageData.parseSuccess'/></span>
						</div>
						<div style="border-bottom: 1px solid #ccc; padding-top: 10px; padding-bottom: 10px;">
							 <label>网页内容:</label>
							 <a href="javascript:void" id="htmlContentShow" onclick="viewHtmtSource(1);">展开</a>
							 <a href="javascript:void" id="htmlContentHide" onclick="viewHtmtSource(2);" style="display:none">隐藏</a>
							 <br/>
							 <div id="htmlContent" style="display:none"><s:property value='htmlContent'/></div>
						</div>
						<s:if test="pageData.crawlRuleBO.nodeType==1">
							<s:iterator value="pageData.htmlNodeDataList">
								<div style="border-bottom: 1px solid #ccc; padding-top: 10px; padding-bottom: 10px;">
									<label><s:property value='crawlRuleNodeBO.crawlNodeCnName'/>:</label>
									<s:iterator value="valueList">
										<span><s:property /></span>&nbsp;&nbsp;
									</s:iterator>
								</div>
							</s:iterator>
						</s:if>
						<s:else>
							<s:iterator value="pageData.htmlNodeDataList"  status="st">
								<div style="border-bottom: 1px solid #ccc; padding-top: 10px; padding-bottom: 10px;">
									<label><s:property value='#st.index+1'/></label>
									<s:iterator value="htmlNodeDataList">
										<div>
											<label><s:property value='crawlRuleNodeBO.crawlNodeCnName'/>:</label>
											<s:iterator value="valueList">
												<span><s:property /></span>&nbsp;&nbsp;
											</s:iterator>
										</div>
									</s:iterator>
								</div>
							</s:iterator>
						</s:else>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function viewHtmtSource(n){
			if(n==1){
				$("#htmlContentShow").hide();
				$("#htmlContentHide").show();
				$("#htmlContent").show();
			}else{
				$("#htmlContentShow").show();
				$("#htmlContentHide").hide();
				$("#htmlContent").hide();
			}
		}
	</script>
</body>
</html>