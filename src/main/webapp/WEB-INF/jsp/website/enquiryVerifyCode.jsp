<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站验证码</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">网站验证码</h1>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryVerifyCode" id="enquiryVerifyCodeForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="webSiteSelector">所属网站</label>
										 <select class="form-control form-select2" id="webSiteSelector" name="webSiteId"></select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column">
									<input type="hidden" id="pageNow" name="pageNow" />
									<input type="hidden" id="isAjaxSearch" name="isAjaxSearch" value="1" />
									<button class="btn btn-success" id="searchBtn">搜索</button>
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
												<th>网站</th>
												<th>所在机器</th>
												<th>账号</th>
												<th>原因</th>
												<th>创建时间</th>
												<th>更新时间</th>
												<th>验证码图片</th>
												<th>验证码</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<s:set name="pageNow" value="pageDTO.pageNow"/>
											<s:set name="pageSize" value="pageDTO.pageSize"/>
											<s:iterator value="pageDTO.list" status="st">
												<tr>
													<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
													<td><s:property value='webSite.webSiteName'/></td>
													<td><s:property value='machineHost'/></td>
													<td><s:property value='account'/></td>
													<td><s:property value='verifyCodeCause'/></td>
													<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td><img alt="" src="<s:property value='verifyCodeUrl'/>"></td>
													<td>
														<s:if test="verifyCode==''">
															<input type="text" class="verifycode">
														</s:if>
														<s:else>
															<s:property value='verifyCode'/>
														</s:else>
													</td>
													<td>
														<s:if test="verifyCode==''">
															<a class="saveVerifyCode" verifycodeid="<s:property value='verifyCodeId'/>">保存</a>
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
	
	<%@ include file="/WEB-INF/jsp/modal/webSiteModal.jspf" %>
	
	<script type="text/javascript">
		function refresh(){
			$(".saveVerifyCode").click(function(){
				var obj = $(this);
				var verifyCodeId = obj.attr("verifycodeid");
				var tr = obj.parent().parent();
				var verifyCodeTD = tr.find(".verifycode").parent();
				var verifyCode = tr.find(".verifycode").val();
				if(verifyCode){
					$.getJSON("saveVerifyCode",{verifyCodeId:verifyCodeId,verifyCode:verifyCode}, function(data){
						var ret = $.parseJSON(data.jsonResult);
						if(ret.succeed){
							obj.remove();
							verifyCodeTD.empty();
							verifyCodeTD.text(verifyCode);
						}else{
							alert(ret.msg);
						}
					});
				}else{
					alert("请输入验证码！");
				}
			});
		}
		
		$(function(){
			
			refresh();
			
			$.getJSON("findAllWebSite", function(data){
				var webSiteSelector = $("#webSiteSelector");
				$.each(data.jsonResult, function(key, value){
					webSiteSelector.prepend("<option value='" + key + "'>" + value + "</option>");
				});
				webSiteSelector.prepend("<option></option>");
				$("#webSiteSelector").select2({allowClear: true});
				$("#webSiteSelector").select2("val","");
			});
			
			$("#enquiryVerifyCodeForm").submit(function(){
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