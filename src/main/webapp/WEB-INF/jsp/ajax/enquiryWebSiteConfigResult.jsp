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
				<th>名称</th>
				<th>所属网站</th>
				<th>需要代理IP</th>
				<th>需要固定IP</th>
				<th>需要登录</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:set name="pageNow" value="pageDTO.pageNow"></s:set>
			<s:set name="pageSize" value="pageDTO.pageSize"></s:set>
			<s:iterator value="pageDTO.list" status="st">
				<tr>
					<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
					<td><s:property value='webSiteConfigName'/></td>
					<td><s:property value='webSite.webSiteName'/></td>
					<td>
						<s:if test="needProxyIP==1">
							是
						</s:if>
						<s:else>
							否
						</s:else>
					</td>
					<td>
						<s:if test="needFixedProxyIP==1">
							是
						</s:if>
						<s:else>
							否
						</s:else>
					</td>
					<td>
						<s:if test="needLogin==1">
							是
						</s:if>
						<s:else>
							否
						</s:else>
					</td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a class="edit-websiteconfig" websiteconfigid="<s:property value='webSiteConfigId'/>">编辑</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
</div>

