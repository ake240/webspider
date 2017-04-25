<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<div id="replace">
	<table class="table table-hover">
    <thead>
    <tr>
        <th>序号</th>
        <th>关键词</th>
        <th>抓取类型</th>
        <th>开始时间</th>
        <th>结束时间</th>
        <th>抓取状态</th>
        <th>消息</th>
        <th>创建时间</th>
        <th>更新时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
	    <s:set name="pageNow" value="pageDTO.pageNow"></s:set>
	    <s:set name="pageSize" value="pageDTO.pageSize"></s:set>
        <s:set name="fetchTypeMap" value='#{"1":"实时抓取","2":"历史抓取","3":"补充抓取"}'></s:set>
        <s:set name="fetchStatueMap" value='#{"0":"未完成","1":"已完成"}'></s:set>
	    <s:iterator value="pageDTO.list" status="st">
	        <tr>
	            <td><s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
	            <td><s:property value='indexKeyword'/></td>
                <td><s:property value="#fetchTypeMap[fetchType+'']"></s:property></td>
                <td><s:date name="fetchStartTime" format="yyyy-MM-dd"/></td>
	            <td><s:date name="fetchEndTime" format="yyyy-MM-dd"/></td>
                <td><s:property value="#fetchStatueMap[fetchStatus+'']"/></td>
                <td><s:property value='msg'/></td>
	            <td><s:date name="insertTime" format="yyyy-MM-dd HH:mm:ss"/></td>
	            <td><s:date name="updateTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                    <s:if test="fetchType==2">
                        <a target="_blank"
                           href="<%=request.getContextPath()%>/createTask?task.taskId=<s:property value="taskId"/>">编辑</a>&nbsp;
                    </s:if>
                    <s:else>
                        <span>无</span>
                    </s:else>
                </td>
	        </tr>
	    </s:iterator>
	    </tbody>
	</table>
<%@include file="/WEB-INF/jsp/common/pagination.jspf" %>
</div>
<script type="text/javascript">
		refresh();
</script>


</body>
</html>
