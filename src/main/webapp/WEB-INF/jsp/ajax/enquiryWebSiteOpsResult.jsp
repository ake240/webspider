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
				<th>操作类型名称</th>
				<th>所属网站</th>
				<th>机器角色</th>
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
					<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/>-<s:property value='webSiteOpsId'/></td>
					<td><s:property value='webSiteOpsName'/></td>
					<td><s:property value='webSite.webSiteName'/></td>
					<td><s:property value='machineRole.machineRoleName'/></td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a class="edit-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">编辑</a>&nbsp;
						<a class="remove-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">删除</a>
						<s:if test="webSiteOpsParams.size()>0">
							<a class="edit-websiteopsparam" websiteopsid="<s:property value='webSiteOpsId'/>">编辑参数</a>
						</s:if>
						<s:else>
							<a class="new-websiteopsparam" websiteopsid="<s:property value='webSiteOpsId'/>">新建参数</a>
						</s:else>
						<a class="copy-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">复制</a>&nbsp;
						<a class="enquiry-taskFI" websiteopsid="<s:property value='webSiteOpsId'/>">抓取项管理</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
</div>

