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
				<th>机器</th>
				<th>端口</th>
				<th>类型</th>
				<th>角色</th>
				<th>描述</th>
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
					<s:set name="roleNames" value="''"/>
					<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
					<td><s:property value='host'/></td>
					<td><s:property value='port'/></td>
					<td><s:property value='@com.datayes.webspider.util.DataConvertUtil@machineTypeConverter(machineType)'/></td>
					<td>
						<s:iterator value="machineRoles" status="">
							<s:set name="roleNames" value="#roleNames + machineRoleName + ' | '"/>
						</s:iterator>
						<span class="convertRoleNames"><s:property value="#roleNames"/></span>
					</td>
					<td><s:property value='machineDesc'/></td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a><i class="glyphicon glyphicon-edit edit-machine icon-size" machineid="<s:property value='machineId'/>"></i></a>
						<%-- <a><i class="glyphicon glyphicon-remove remove-machine icon-size" machineid="<s:property value='machineId'/>"></i></a> --%>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
</div>

