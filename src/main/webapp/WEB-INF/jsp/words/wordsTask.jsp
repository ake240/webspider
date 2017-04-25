<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>关键词抓取任务列表</title>

</head>
<body>
<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
<div class="container spider-container">
    <div class="row clear-fix">
        <div class="col-md-12 column">
            <div class="page-header page-title">
                <h1 style="display:inline">关键词抓取任务列表</h1>
                <a class="btn btn-success" style="float:right" href="<%=request.getContextPath()%>/createTask"
                   target="_blank"><i class="glyphicon glyphicon-plus"></i>&nbsp;
                    添加关键词抓取任务</a>
            </div>


            <!-- 搜索条件 -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">搜索条件</h3>
                </div>

                <div class="panel-body">
                    <form action="wordsTask" id="wordsTask" method="POST">
                        <div class="row clearfix">
                            <div class="col-md-3 column">
                                <div class="form-group">
                                    <label for="word">关键词</label>
                                    <input type="hidden" name="pageNum" id="pageNum">
                                    <input type="hidden" name="isAjax" id="isAjax">
                                    <select class="form-control form-select2" id="word" name="params.word"
                                            placeholder="请输入抓取关键词"></select>
                                </div>
                            </div>
                            <div class="col-md-3 column">
                                <div class="form-group">
                                    <label for="clawertype">抓取类型</label>
                                    <select class="form-control form-select2" id="clawertype" name="params.type">
                                        <option></option>
                                        <option value="1">实时抓取</option>
                                        <option value="2">历史抓取</option>
                                        <option value="3">补充抓取</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3 column">
                                <div class="form-group">
                                    <label for="clawerstatus">抓取状态</label>
                                    <select class="form-control form-select2" id="clawerStatus" name="params.status">
                                        <option></option>
                                        <option value="1">未完成</option>
                                        <option value="2">已完成</option>
                                    </select>
                                </div>
                            </div>
                            <%-- --%>
                        </div>
                        <div class="row clearfix">
                            <div class="col-md-3 column">
                                <div class="form-group">
                                    <label for="clawstart">开始时间</label>
                                    <input id="clawstart" type="text" name="params.starttime"
                                           value="<s:date format='yyyy-MM-dd' name="params.starttime"/>"
                                           placeholder="开始日期"
                                           onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control Wdate"
                                           autocomplete="off">
                                </div>
                            </div>
                            <div class="col-md-3 column">
                                <div class="form-group">
                                    <label for="clawend">结束时间</label>
                                    <input type="text" id="clawend" name="params.endtime"
                                           value="<s:date format='yyyy-MM-dd' name="params.endtime"/>"
                                           placeholder="结束日期"
                                           onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="form-control Wdate"
                                           autocomplete="off">
                                </div>

                            </div>

                        </div>

                        <div class="row clearfix">
                            <div class="col-md-12 column">
                                <button class="btn btn-success">搜索</button>
                            </div>
                        </div>

                    </form>


                </div>
            </div>


            <!-- 搜索结果 -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        搜索结果
                    </h3>
                </div>
                <div class="panel-body">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
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
                                                <%--<td><s:property value="fetchType"></s:property></td>--%>
                                            <td><s:property value="#fetchTypeMap[fetchType+'']"></s:property></td>
                                                <%--<td>#fetchTypeMap["1"]</td>--%>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

    function refresh() {
        $(".navPage").click(function () {
            var pageNow = $(this).attr("id");
            $("#pageNum").val(pageNow);
            $("#isAjax").val(true);
            $("#wordsTask").ajaxSubmit(function (data) {
                $("#replace").replaceWith(data);
            });
        });
    }

    $(function () {

        refresh();

        $.getJSON("<%=request.getContextPath()%>/findFetchWords", function (data) {
            var wordSelector = $("#word");
            $.each(data.data, function (key, value) {
                wordSelector.prepend("<option value='" + key + "'>" + value + "</option>");
            });
            wordSelector.prepend("<option></option>");
            $("#word").select2({allowClear: true});
            $("#word").select2("val", "");

        });
        $("#clawertype").select2({allowClear: true});
        $("#clawerStatus").select2({allowClear: true});
        $("#clawertype").select2("val","");
        $("#clawerStatus").select2("val","");
        $("#enquiryKeywordsForm").submit(function () {
            $("#pageNow").val(1);
            $(this).ajaxSubmit(function (data) {
                $("#replace").replaceWith(data);
            });
            return false;
        });

        /*
         $(".navPage").live(function () {
         var pageNow = $(this).attr("id");
         $("#pageNum").val(pageNow);
         $("#wordsTask").ajaxSubmit(function (data) {
         $("#replace").replaceWith(data);
         });
         });
         */

        $("#wordsTask").submit(function () {
            $("#pageNum").val(1);
            $("#isAjax").val(true);
            $(this).ajaxSubmit(function (data) {
                $("#replace").replaceWith(data);
            });
            return false;
        });
    });


</script>
</body>
</html>