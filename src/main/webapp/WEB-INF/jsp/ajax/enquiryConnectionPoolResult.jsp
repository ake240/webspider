<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="replace">
	<i>
		<script type="text/javascript">
			refresh();
		</script>
	</i>
	<input type="hidden" id="host" value='<s:property value="host"/>' />
	<s:if test="pool != null">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>名称</th>
					<th>连接总数</th>
					<th>可用连接数</th>
					<th>异常连接数</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="pool.data" status="st">
					<tr>
						<td><s:property value='machineRoleId'/></td>
						<td><a class="pool-detail" data-id="<s:property value='machineRoleId'/>" data-name="<s:property value='machineRoleName'/>"><s:property value='machineRoleName'/></a></td>
						<td><s:property value='totalConnections'/></td>
						<td><s:property value='inUseConnections'/></td>
						<td><s:property value='exceptionConnections'/></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:if>
	<s:else>
		目标机器相关服务未启动。
	</s:else>
</div>

