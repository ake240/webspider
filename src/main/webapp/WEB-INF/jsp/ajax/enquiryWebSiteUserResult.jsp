<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="replace">
	<i>
		<script type="text/javascript">
			refresh();
		</script>
	</i>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名称</th>
				<th>用户密码</th>
				<th>所属网站</th>
				<th>信息</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:set name="pageNow" value="pageDTO.pageNow"></s:set>
			<s:set name="pageSize" value="pageDTO.pageSize"></s:set>
			<s:iterator value="pageDTO.list" status="st">
				<tr>
					<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
					<td><s:property value='account'/></td>
					<td><s:property value='password'/></td>
					<td><s:property value='webSite.webSiteName'/></td>
					<td class="messageTD"><s:property value='message'/></td>
					<td class="statusTD">
						<s:if test="status==1">
							可用
						</s:if>
						<s:else>
							不可用
						</s:else>
					</td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="updateTimeTD"><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a class="edit-websiteuser" websiteuserid="<s:property value='id'/>" account="<s:property value='account'/>" password="<s:property value='password'/>" websiteid="<s:property value='webSite.webSiteId'/>">编辑</a>&nbsp;
						<%-- <a class="remove-websiteuser" websiteuserid="<s:property value='webSiteUserId'/>">删除</a>&nbsp; --%>
						<s:if test="status==1">
							<a class="stop-websiteuser" websiteuserid="<s:property value='id'/>" status="0">停用</a>
						</s:if>
						<s:else>
							<a class="start-websiteuser" websiteuserid="<s:property value='id'/>" status="1">启用</a>
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
</div>

