<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="replace">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名</th>
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
					<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1' /></td>
					<td><s:property value='username' /></td>
					<td class="status"><s:if test="status==0">
															不可用
														</s:if> <s:else>
															可用
														</s:else></td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="updateTime"><s:date name="updateTime"
							format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:if test="status==0">
							<a class="start-user" status="1"
								userid="<s:property value='userId'/>">启用</a>
						</s:if> <s:else>
							<a class="stop-user" status="0"
								userid="<s:property value='userId'/>">停用</a>
						</s:else></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf"%>
</div>

