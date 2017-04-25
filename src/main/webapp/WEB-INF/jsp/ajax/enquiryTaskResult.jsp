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
				<th>任务中文名称</th>
				<th>任务英文名称</th>
				<th>操作类型</th>
				<th>执行机器</th>
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
					<td><s:property value='taskName'/></td>
					<td><s:property value='taskEnName'/></td>
					<td><s:property value='webSiteOps.webSiteOpsName'/></td>
					<td><s:property value='procHost'/></td>
					<td class="taskStatus"><s:property value='@com.datayes.webspider.util.DataConvertUtil@taskStatusConverter(taskStatus)'/></td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td class="taskUpdateTime"><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td width="150px">
						<%-- <a class="enquiry-taskFI" taskid="<s:property value='taskId'/>">抓取项管理</a>&nbsp; --%>
						<a class="edit-task" taskid="<s:property value='taskId'/>">编辑</a>&nbsp;
						<%-- <a class="remove-task" taskid="<s:property value='taskId'/>">删除</a>&nbsp; --%>
						<a class="copy-task" taskid="<s:property value='taskId'/>">复制</a>
						<a class="enquiry-taskFI" websiteopsid="<s:property value='webSiteOps.webSiteOpsId'/>">抓取项管理</a>
						<s:if test="taskStatus==-1">
							<a class="start-task" taskid="<s:property value='taskId'/>">启动</a>
						</s:if>
						<s:if test="taskStatus==2 || taskStatus==9">
							<a class="restart-task" taskid="<s:property value='taskId'/>">重新启动</a>
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
</div>

