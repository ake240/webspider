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
				<th>网站</th>
				<th>所在机器</th>
				<th>账号</th>
				<th>原因</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>验证码图片</th>
				<th>验证码</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:set name="pageNow" value="pageDTO.pageNow"/>
			<s:set name="pageSize" value="pageDTO.pageSize"/>
			<s:iterator value="pageDTO.list" status="st">
				<tr>
					<td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
					<td><s:property value='webSite.webSiteName'/></td>
					<td><s:property value='machineHost'/></td>
					<td><s:property value='account'/></td>
					<td><s:property value='verifyCodeCause'/></td>
					<td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td><img alt="" src="<s:property value='verifyCodeUrl'/>"></td>
					<td>
						<s:if test="verifyCode==''">
							<input type="text" class="verifycode">
						</s:if>
						<s:else>
							<s:property value='verifyCode'/>
						</s:else>
					</td>
					<td>
						<s:if test="verifyCode==''">
							<a class="saveVerifyCode" verifycodeid="<s:property value='verifyCodeId'/>">保存</a>
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
</div>

