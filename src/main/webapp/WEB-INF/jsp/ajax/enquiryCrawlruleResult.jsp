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
				<th>规则名称</th>
				<th>操作者</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:set name="pageNow" value="pageDTO.pageNow"></s:set>
			<s:set name="pageSize" value="pageDTO.pageSize"></s:set>
			<s:iterator value="pageDTO.list" status="st">
				<tr>
					<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
					<td><s:property value='crawlRuleName'/></td>
					<td><s:property value='operator'/></td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:property value='@com.datayes.webspider.util.DataConvertUtil@statusConverter(status)'/></td>
					<td>
						<a class="edit-crawlrule" crawlruleid="<s:property value='id'/>">编辑</a>&nbsp;
						<s:if test="status==0">
							<a class="publish-crawlrule" crawlruleid="<s:property value='id'/>">启用</a>&nbsp;
						</s:if>
						<s:elseif test="status==1">
							<a class="stop-crawlrule" crawlruleid="<s:property value='id'/>">停用</a>&nbsp;
						</s:elseif>
						<s:if test="status!=2">
							<a class="remove-crawlrule" crawlruleid="<s:property value='id'/>">删除</a>&nbsp;
						</s:if>
						<a class="copy-crawlrule" crawlruleid="<s:property value='id'/>">复制</a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
</div>

