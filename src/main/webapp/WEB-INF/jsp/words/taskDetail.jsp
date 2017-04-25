<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加关键词任务</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="container spider-container">
    <form class="form-horizontal" id="taskForm" method="post" action="<%=request.getContextPath()%>/addWordsTask"
          role="form">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <div class="page-header page-title">
                    <s:if test="task==null">
                        <h1 style="display: inline;">添加关键词任务</h1>
                    </s:if>
                    <s:else>
                        <h1 style="display: inline;">修改关键词任务</h1>
                    </s:else>
                    <button type="button" id="saveKeywordBtn" class="btn btn-success">保存任务</button>
                </div>
                <s:if test="message!=null">
                    <div class="alert alert-danger alert-dismissable" style="padding: 8px 12px;">
                        <button type="button" class="close" style="right: 0;" data-dismiss="alert"
                                aria-hidden="true">&times;</button>
                        <s:property value='message'/>
                    </div>
                </s:if>
                <div class="panel-group" id="base-panel">
                    <div class="panel panel-default">
                        <div class="panel-heading" style="overflow: hidden;">
                            <a class="panel-title" data-toggle="collapse" data-parent="base-panel"
                               href="#base-div">基本设置</a>
                        </div>
                        <div id="base-div" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <div class="row clear-fix">
                                    <div class="col-md-6 column">
                                        <div class="form-group">
                                            <label for="keyword" class="col-sm-3 control-label">关键词<span class="red">&nbsp;*</span></label>

                                            <div class="col-sm-9">
                                                <s:if test="task==null">
                                                    <select class="form-control form-select2" id="keyword"
                                                            name="task.indexId"
                                                            placeholder="请输入抓取关键词"></select>
                                                    <input type="hidden" id="indexKeyword" name="task.indexKeyword">
                                                </s:if>
                                                <s:else>
                                                    <input type="hidden" value="<s:property value="task.taskId"/>"
                                                           name="task.taskId">
                                                    <input type="hidden" value="<s:property value="task.indexId"/>"
                                                           name="task.indexId">
                                                    <input type="hidden" value="<s:property value="task.insertTime"/>"
                                                           name="task.insertTime">
                                                    <input value="<s:property value="task.indexKeyword"/> "
                                                           name="task.indexKeyword" id="keyword" disabled>
                                                </s:else>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 column">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label" for="type">抓取类型
                                            </label>

                                            <div class="col-sm-9" style="padding-top: 7px">
                                                <span id="type"> <input type=hidden value="2" name="task.fetchType">历史抓取</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clear-fix">

                                    <div class="col-md-6 column">
                                        <div class="form-group">
                                            <label for="fetchStartTime" class="col-sm-3 control-label">开始时间</label>

                                            <div class="col-sm-9">
                                                <input type="text" id="fetchStartTime" name="task.fetchStartTime"
                                                       value="<s:property value='task.fetchStartTime'/>"
                                                       placeholder="开始日期"
                                                       onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                                       class="form-control Wdate"
                                                       autocomplete="off">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6 column">
                                        <div class="form-group">
                                            <label for="fetchEndTime" class="col-sm-3 control-label">结束时间</label>

                                            <div class="col-sm-9">
                                                <input type="text" id="fetchEndTime" name="task.fetchEndTime"
                                                       value="<s:property  value="task.fetchEndTime"/>"
                                                       placeholder="结束日期"
                                                       onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                                       class="form-control Wdate"
                                                       autocomplete="off">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
    $(function () {

        $("#saveKeywordBtn").click(function () {
            if ((Date.parse($('#fetchStartTime').val().replace(/-/g, "/")) -
                    Date.parse($('#fetchEndTime').val().replace(/-/g, "/"))) > 0) {
                alert("开始时间应该小于结束时间");
                return;
            }
            $("#taskForm").submit();
        });
        $("#keyword").change(function () {
            $("#indexKeyword").val(this.options[this.selectedIndex].text);

        });

        $(function () {
            $.getJSON("<%=request.getContextPath()%>/findFetchWords", function (data) {
                var wordSelector = $("#keyword");
                $.each(data.data, function (key, value) {
                    wordSelector.prepend("<option value='" + key + "'>" + value + "</option>");
                });
                wordSelector.prepend("<option></option>");
                $("#keyword").select2({allowClear: true});
            });

        });
    });
</script>
</body>
</html>