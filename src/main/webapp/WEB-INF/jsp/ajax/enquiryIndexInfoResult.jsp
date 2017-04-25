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
				<th>序号</th>
				<th>关键词</th>
				<th>指数类型</th>
				<th>日期</th>
				<th>指数值</th>
				<th>创建时间</th>
				<th>更新时间</th>
			</tr>
		</thead>
		<tbody>
			<s:set name="pageNow" value="pageDTO.pageNow"></s:set>
			<s:set name="pageSize" value="pageDTO.pageSize"></s:set>
			<s:iterator value="pageDTO.list" status="st">
				<tr>
					<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1' /></td>
					<td><s:property value='indexWord' /></td>
					<td><s:property value='indexType' /></td>
					<td><s:date name="indexDate" format="yyyy-MM-dd" /></td>
					<td><s:property value='indexNum' /></td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf"%>
</div>