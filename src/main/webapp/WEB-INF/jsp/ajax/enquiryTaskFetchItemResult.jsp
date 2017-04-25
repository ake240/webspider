<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="replace">
	<i> <script type="text/javascript">
		refresh();
	</script>
	</i>
	<table class="table table-hover">
		<thead>
			<tr>
				<th><label><input id="allCheckBox" type="checkbox"
						onclick="switchAllCheckboxes(this)">全选</label></th>
				<th>状态</th>
				<th>参数值</th>
				<th>抓取时间</th>
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
					<td><label><input type="checkbox" class="fi"
							name="checkFIs" value="<s:property value='id' />"></label> <s:property
							value='(#pageNow-1)*#pageSize + #st.index + 1' /></td>
					<td><s:property
							value='@com.datayes.webspider.util.DataConvertUtil@taskStatusConverter(status)' /></td>
					<td><s:property value='param' /></td>
					<td><s:date name="dFetchTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="taskFIInsertTime"><s:property value="insertTime" /></td>
					<td class="taskFIUpdateTime"><s:property value="updateTime" /></td>
					<td width="150px"><a class="edit-taskFI"
						taskFIid="<s:property value='id' />">编辑</a>&nbsp; <%-- <a class="remove-taskFI" taskFIid="<s:property value='id' />" >删除</a>&nbsp; --%>
						<s:if test="flag==0">
							<a class="change-taskFI" taskFIid="<s:property value='id'/>">启用</a>
						</s:if> <s:if test="flag==1">
							<a class="change-taskFI" taskFIid="<s:property value='id'/>">禁用</a>
						</s:if></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf"%>
</div>