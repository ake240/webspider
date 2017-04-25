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
				<th>网站名称</th>
				<th>网站分类</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:set name="pageNow" value="pageDTO.pageNow"/>
			<s:set name="pageSize" value="pageDTO.pageSize"/>
			<s:iterator value="pageDTO.list" status="st">
				<s:set name="categoryNames" value="''"/>
				<s:set name="categoryIds" value="''"/>
				<tr>
					<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
					<td><s:property value='webSiteName'/></td>
					<td>
						<s:iterator value="categories" status="">
							<s:set name="categoryNames" value="#categoryNames + categoryName + ' | '"/>
							<s:set name="categoryIds" value="#categoryIds + categoryId + ','"/>
						</s:iterator>
						<span class="convertCategoryNames"><s:property value="#categoryNames"/></span>
					</td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a><i websiteid="<s:property value='webSiteId'/>" websitename="<s:property value='webSiteName'/>" categoryids="<s:property value='#categoryIds'/>" class="glyphicon glyphicon-edit edit-website icon-size"></i></a>
						<a><i websiteid="<s:property value='webSiteId'/>" class="glyphicon glyphicon-remove remove-website icon-size"></i></a>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
</div>

