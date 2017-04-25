<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>连接池</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">连接池：<s:property value="role"/></h1>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							筛选条件
						</h3>
					</div>
					<div class="panel-body">
					
						<form action="enquiryConnection" method="post" id="enquiryConnectionPool">
							<div class="row clearfix">
								<div class="col-md-4 column">
									<div class="form-group">
										<label for="host">IP</label>
										<select class="form-control form-select2" id="ipSelector" name="ip"></select>
									</div>
								</div>
								<div class="col-md-4 column">
									<div class="form-group">
										<label for="host">账号</label>
										<select class="form-control form-select2" id="accountSelector" name="account"></select>
									</div>
								</div>
								<div class="col-md-4 column">
									<div class="form-group">
										<label for="host">状态</label>
										<select class="form-control form-select2" id="statusSelector" name="status">
											<option value=""></option>
											<option value="正常">正常</option>
											<option value="异常">异常</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column">
									<input type="hidden" name="host" value='<s:property value="host"/>' />
									<input type="hidden" name="id" value='<s:property value="id"/>' />
									<input type="hidden" name="ajaxSearch" value="true"/>
									<button type="submit" class="btn btn-success" id="searchBtn">筛选</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							筛选结果
						</h3>
					</div>
					<div class="panel-body">
						<div class="row clearfix">
							<div class="col-md-12 column">
								<div id="replace">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>IP</th>
											<th>端口</th>
											<th>账号</th>
											<th>操作类型</th>
											<th>IP 1h</th>
											<th>IP今天</th>
											<th>账号 1h</th>
											<th>账号今天</th>
											<th>连接状态</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="connection.data" status="st">
											<s:if test="websiteOperationReqList.size == 0">
												<tr>
													<td class="ip-class"><s:property value="connection.data.get(#st.index).host"/></td>
													<td><s:property value="connection.data.get(#st.index).port"/></td>
													<td class="account-class"><s:property value="connection.data.get(#st.index).accout"/></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
											</s:if>
											<s:else></s:else>
											<s:iterator value="websiteOperationReqList" status="st1">
												<tr>
													<s:if test="#st1.index == 0">
														<td class="ip-class" rowspan='<s:property value="websiteOperationReqList.size"/>'><s:property value="connection.data.get(#st.index).host"/></td>
														<td rowspan='<s:property value="websiteOperationReqList.size"/>'><s:property value="connection.data.get(#st.index).port"/></td>
														<td class="account-class" rowspan='<s:property value="websiteOperationReqList.size"/>'><s:property value="connection.data.get(#st.index).account"/></td>
													</s:if>
													
													<td><s:property value="websiteOperationName"/></td>
													<td>
														<s:date name="ipReqStartTime" format=" HH:mm:ss " /> - <s:date name="ipReqEndTime" format="HH:mm:ss" />  
														<br/>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="ipReqNumPerHour"/>/<s:property value="maxIpRequestNumPerHour"/>
													</td>
													<td><s:property value="ipReqNumPerDay"/>/<s:property value="maxIpRequestNumPerDay"/></td>
													<td>
														<s:date name="userReqStartTime" format=" HH:mm:ss " /> - <s:date name="userReqEndTime" format="HH:mm:ss" />  
														<br/>
														&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="userReqNumPerHour"/>/<s:property value="maxUserRequestNumPerHour"/>
													</td>
													<td><s:property value="userReqNumPerDay"/>/<s:property value="maxUserRequestNumPerDay"/></td>
													<td>
														<s:if test="exceptionRuleName == ''
														 &&	( maxIpRequestNumPerHour <= 0 || ipReqNumPerHour < maxIpRequestNumPerHour ) 
														 && ( maxIpRequestNumPerDay <= 0 || ipReqNumPerDay < maxIpRequestNumPerDay)
													     && ( maxUserRequestNumPerHour <= 0 || userReqNumPerHour < maxUserRequestNumPerHour) 
													     && ( maxUserRequestNumPerDay <= 0 || userReqNumPerDay < maxUserRequestNumPerDay)">
															正常
														</s:if>
														<s:else>
															<s:if test="exceptionRuleName!=''">
																<s:property value="exceptionRuleName"/><br/>
															</s:if>
															<s:if test="maxIpRequestNumPerHour > 0 && ipReqNumPerHour >= maxIpRequestNumPerHour">
																IP超出限制<br/>
															</s:if>
															<s:if test="maxIpRequestNumPerDay > 0 && ipReqNumPerDay >= maxIpRequestNumPerDay">
																当日IP超出限制<br/>
															</s:if>
															<s:if test="maxUserRequestNumPerHour > 0 && userReqNumPerHour >= maxUserRequestNumPerHour">
																用户请求超出限制<br/>
															</s:if>
															<s:if test="maxUserRequestNumPerDay > 0 && userReqNumPerDay >= maxUserRequestNumPerDay">
																当日用户请求超出限制<br/>
															</s:if>
														</s:else>
													</td>
												</tr>
											</s:iterator>
										</s:iterator>
									</tbody>
								</table>
							</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		
		$(function(){
			$("#enquiryConnectionPool").submit(function(){
				$(this).ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
				return false;
			});
			
			$(".ip-class").each(function(){
				if($(this).text !=null && $(this).text != ""){
					$("#ipSelector").append("<option value="+$(this).text()+">" + $(this).text() + "</option>")
				}
			})
			$(".account-class").each(function(){
				if($(this).text != ""){
					$("#accountSelector").append("<option value="+$(this).text()+">" + $(this).text() + "</option>")
				}
			})
			$("#ipSelector").prepend("<option value=></option>")
			$("#accountSelector").prepend("<option></option>")
			$("#ipSelector").select2({allowClear: true});
			$("#accountSelector").select2({allowClear: true});
			$("#statusSelector").select2({allowClear: true});
			$("#ipSelector").select2("val","<s:property value='ip'/>");
			$("#accountSelector").select2("val","<s:property value='account'/>");
		});
	</script>
</body>
</html>