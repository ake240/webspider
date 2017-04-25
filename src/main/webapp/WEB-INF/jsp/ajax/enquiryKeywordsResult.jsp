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
				<th>使用状态</th>
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
					<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1' /></td>
					<td><s:property value='word' /></td>
					<td>
						<%-- <s:property value='active' /> --%> <s:if test="active ==0">停用</s:if>
						<s:else>启用</s:else>
					</td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<s:if test="active ==0">
							<a class="activate-keyword"	keywordId="<s:property value='indexId'/>">启用</a>
						</s:if> 
						<s:else>
							<a class="deactivate-keyword" keywordId="<s:property value='indexId'/>">停用</a>
						</s:else> 
						<a class="viewIndexInfo" keywordId="<s:property value='indexId'/>" keyword="<s:property value='word'/>">查看指数</a>&nbsp;
						<%-- 														<a class="edit-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">编辑</a>&nbsp; --%>
						<%-- 														<a class="remove-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">删除</a> --%>
						<%-- 														<s:if test="webSiteOpsParams.size()>0"> --%> <%-- 															<a class="edit-websiteopsparam" websiteopsid="<s:property value='webSiteOpsId'/>">编辑参数</a> --%>
						<%-- 														</s:if> --%> <%-- 														<s:else> --%> <%-- 															<a class="new-websiteopsparam" websiteopsid="<s:property value='webSiteOpsId'/>">新建参数</a> --%>
						<%-- 														</s:else> --%> <%-- 														<a class="copy-websiteops" websiteopsid="<s:property value='webSiteOpsId'/>">复制</a> --%>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf"%>
</div>