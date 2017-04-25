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
				<th>存储类型名称</th>
				<th>中文名称</th>
				<th>英文名称</th>
				<th>唯一值</th>
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
					<td><s:property value='dataStoreType.dataStoreTypeCnName'/></td>
					<td><s:property value='fieldCnName'/></td>
					<td><s:property value='fieldEnName'/></td>
					<td>
						<s:if test="isUnique==1">
							是
						</s:if>
						<s:else>
							否
						</s:else>
					</td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a class="edit-datastorefield" datastorefieldid="<s:property value='dataStoreFieldId'/>">编辑</a>&nbsp;
						<a class="remove-datastorefield" datastorefieldid="<s:property value='dataStoreFieldId'/>">删除</a>&nbsp;
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
</div>

