<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
				<s:iterator value="websiteOperationReqList" status="st1">
					<tr>
						<s:if test="#st1.index == 0">
							<td class="ip-class"
								rowspan='<s:property value="websiteOperationReqList.size"/>'><s:property
									value="connection.data.get(#st.index).host" /></td>
							<td rowspan='<s:property value="websiteOperationReqList.size"/>'><s:property
									value="connection.data.get(#st.index).port" /></td>
							<td class="account-class"
								rowspan='<s:property value="websiteOperationReqList.size"/>'><s:property
									value="connection.data.get(#st.index).account" /></td>
						</s:if>

						<td><s:property value="websiteOperationName" /></td>
						<td><s:date name="ipReqStartTime" format=" HH:mm:ss " /> - <s:date
								name="ipReqEndTime" format="HH:mm:ss" /> <br />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property
								value="ipReqNumPerHour" />/<s:property
								value="maxIpRequestNumPerHour" /></td>
						<td><s:property value="ipReqNumPerDay" />/<s:property
								value="maxIpRequestNumPerDay" /></td>
						<td><s:date name="userReqStartTime" format=" HH:mm:ss " /> -
							<s:date name="userReqEndTime" format="HH:mm:ss" /> <br />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property
								value="userReqNumPerHour" />/<s:property
								value="maxUserRequestNumPerHour" /></td>
						<td><s:property value="userReqNumPerDay" />/<s:property
								value="maxUserRequestNumPerDay" /></td>
						<td><s:if
								test="exceptionRuleName == '' && ipReqNumPerHour <= maxIpRequestNumPerHour && ipReqNumPerDay <= maxIpRequestNumPerDay
															&& userReqNumPerHour<=maxUserRequestNumPerHour && userReqNumPerDay<= maxUserRequestNumPerDay ">
															正常
														</s:if> <s:else>
								<s:if test="exceptionRuleName!=''">
									<s:property value="exceptionRuleName" />
									<br />
								</s:if>
								<s:if test="ipReqNumPerHour <= maxIpRequestNumPerHour">
																IP超出限制<br />
								</s:if>
								<s:if test="ipReqNumPerDay <= maxIpRequestNumPerDay">
																当日IP超出限制<br />
								</s:if>
								<s:if test="userReqNumPerHour<=maxUserRequestNumPerHour">
																用户请求超出限制<br />
								</s:if>
								<s:if test="userReqNumPerDay<= maxUserRequestNumPerDay">
																当日用户请求超出限制<br />
								</s:if>
							</s:else></td>
					</tr>
				</s:iterator>
			</s:iterator>
		</tbody>
	</table>
</div>