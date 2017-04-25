<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>抓取项管理</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf"%>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">抓取项管理</h1>
					<a class="newTaskFI-Btn" href="taskFI?websiteOpsId=<s:property value='webSiteOpsId' />" target="_blank" style="float: right;"><i class="glyphicon glyphicon-plus"></i>&nbsp;抓取项</a>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							搜索条件
						</h3>
					</div>
					<div class="panel-body">
						<form action="enquiryTaskFI" id="enquiryTaskFIForm" method="post">
							<div class="row clearfix">
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="taskName">操作类型名称</label>
										 <input type="text" class="form-control" id="webSiteOpsName" name="webSiteOpsName" readonly="readonly" value="<s:property value='webSiteOpsName' />" />
									</div>
									<div class="form-group">
										 <label for="procHost">Mongo查询命令</label>
										 <textarea type="text" class="form-control" id="param" name="enquiryParam" placeholder="请输入mongo查询语句">{}</textarea>
									</div>
								</div>
								<div class="col-md-6 column">
									<div class="form-group">
										 <label for="websiteOperationSelector">抓取项所在数据库后缀</label>
										 <input class="form-control" id="websiteOperationSelector" name="webSiteOpsId"  readonly="readonly" value="<s:property value='webSiteOpsId' />" />
									</div>
									
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column">
									<input type="hidden" id="pageNow" name="pageNow" />
									<input type="hidden" id="isAjaxSearch" name="isAjaxSearch" value="1" />
									<button  type="submit" class="btn btn-success" id="searchBtn">搜索</button>
									<button id="batchActionEntrance" disabled="disabled" class="btn btn-success" data-toggle="modal" data-target="#batchActionModal">编辑批量操作</button>
									<div class="modal fade" id="batchActionModal" tabindex="-1" role="dialog" aria-labelledby="batchActionModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
													&times;</button>
													<h4 class="modal-title" id="batchActionModalLabel">编辑抓取项字段</h4>
												</div>
												<div class="modal-body">
													<div class="form-group">
														 <label for="taskFIStatusSelector">抓取项状态</label>
														 <select class="form-control form-select2" id="taskFIStatusSelector" name="batchStatus">
														 	<option></option>
														 	<option value="0">开始执行</option>
														 	<option value="1">执行中</option>
														 	<option value="2">执行完成</option>
														 	<option value="3">定时任务执行中</option>
														 	<option value="4">定时任务执行完成</option>
														 	<option value="9">执行出错</option>
														 </select>
													</div>
													<div class="form-group">
														 <label for="taskFIFlagSelector">抓取项标识</label>
														 <select class="form-control form-select2" id="taskFIFlagSelector" name="batchFlag">
														 	<option></option>
														 	<option value="0">禁用</option>
														 	<option value="1">启用</option>
														 </select>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
													<button type="button" class="btn btn-primary" id="batchActionBtn">提交</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
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
												<th><label><input id="allCheckBox" type="checkbox" onclick="switchAllCheckboxes(this)">全选</label></th>
												<th>状态</th>
												<th>参数值</th>
												<th>抓取时间</th>
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
													<td><label><input type="checkbox" class="fi" name="checkFIs" value="<s:property value='id' />" ></label> 
													<s:property value='(#pageNow-1)*#pageSize + #st.index + 1'/></td>
													<td><s:property value='@com.datayes.webspider.util.DataConvertUtil@taskStatusConverter(status)'/></td>
													<td><s:property value='param'/></td>
													<td><s:date name="dFetchTime" format="yyyy-MM-dd HH:mm:ss"/></td>
													<td class="taskFIInsertTime"><s:property value="insertTime"/></td>
													<td class="taskFIUpdateTime"><s:property value="updateTime"/></td>
													<td width="150px">
														<a class="edit-taskFI" taskFIid="<s:property value='id' />" >编辑</a>&nbsp;
														<%-- <a class="remove-taskFI" taskFIid="<s:property value='id' />" >删除</a>&nbsp; --%>
														<s:if test="flag==0">
															<a class="change-taskFI" taskFIid="<s:property value='id'/>">启用</a>
														</s:if>
														<s:if test="flag==1">
															<a class="change-taskFI" taskFIid="<s:property value='id'/>">禁用</a>
														</s:if>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									<%@include file="/WEB-INF/jsp/common/pagination.jspf"  %>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function refresh(){
			
			$("input[type='checkbox']").click(function(){
				$this=this;
				if($this.checked==true){
					$("#batchActionEntrance").attr("disabled",false)
				}
			});
			 
			 $("#batchActionBtn").click(function(){
				 var status=$("#taskFIStatusSelector").attr("value");
				 var flag=$("#taskFIFlagSelector").attr("value");
				 var websiteOpsId=$("#websiteOperationSelector").attr("value")
				 var FIs=new Array();
				 $("input.fi").each(function(){
					 $this=this;
					 if($this.checked==true){
					 FIs.push(this.value); 
					 }
				 });
				 var FIStr=FIs.join();
					 $.getJSON("batchEdit",{webSiteOpsId:websiteOpsId,batchStatus:status,batchFlag:flag,checkFIs:FIStr},function(data){
						 var ret=$.parseJSON(data.jsonResult)
						 if(ret.succeed){
							 $("#batchActionModal").modal("hide");
							 $("#enquiryTaskFIForm").submit();
						 }else{
							 alert(ret.msg);
						 }
					 });
			 });
			
			 $(".edit-taskFI").click(function(){
				var form=$("<form id='editTaskFIForm'</form>");
				var type_input=$("<input type='hidden' name='type' value='3' >")
				var task_input=$("<input type='hidden' name='websiteOpsId' >")
				var id_input=$("<input type='hidden' name='itemId' >")
				task_input.attr("value",$("#websiteOperationSelector").attr("value"))
				id_input.attr("value",$(this).attr("taskFIid"))
				form.append(id_input)
				form.append(type_input)
				form.append(task_input)
				form.attr("action","taskFI")
				form.attr("method","post")
				form.attr("target","_blank")
				$("body").append(form)
				form.submit();
				$("#editTaskFIForm").remove();
			});
			 
			$(".remove-taskFI").click(function(){
				var itemId = $(this).attr("taskFIid");
				var websiteOpsId=$("#websiteOperationSelector").attr("value")
				if(confirm("确认删除?")){
					$.getJSON("deleteTaskFI",{itemId:itemId,websiteOpsId:websiteOpsId},function(data){
						var ret=$.parseJSON(data.jsonResult)
						if(ret.succeed){
							$("#enquiryTaskFIForm").submit();
						}else{
							alert(ret.msg);
						}
					});
					}
				});
			
			$(".change-taskFI").click(function(){
				var tr = $(this).parent().parent();
				var itemId = $(this).attr("taskFIid");
				var now = getFormatDate();
				var obj = $(this);
				var websiteOpsId=$("#websiteOperationSelector").attr("value")
				$.getJSON("changeTaskFIStatus",{itemId:itemId,websiteOpsId:websiteOpsId},function(data){
					var ret = $.parseJSON(data.jsonResult);
					if(ret.succeed){
						var taskUpdateTime = tr.find(".taskFIUpdateTime");
						taskUpdateTime.text(now);
						var objText=obj.text();
						if(objText=="启用"){
							obj.text("禁用");
						}else{
							obj.text("启用");
						}
					}else{
						alert("错误: " + ret.msg);
					}
				});
			});
			
			$(".navPage").click(function(){
				var pageNow = $(this).attr("id");
				$("#pageNow").val(pageNow);
				$("#enquiryTaskFIForm").ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
			});
		}
		function switchAllCheckboxes(obj) {
		    var $this = $(obj);
		    var flag = obj.checked;
		    $("input[type='checkbox']").each(function () {
		        this.checked = flag;
		    });
		}
		
		$(function(){
			refresh();
			$("#taskFIStatusSelector").select2({allowClear: true});
			$("#taskFIStatusSelector").select2("val",$("#taskFIStatusSelector").attr("value"));
			
			$("#taskFIFlagSelector").select2({allowClear: true});
			$("#taskFIFlagSelector").select2("val",$("#taskFIFlagSelector").attr("value"));
			
			
			 $("#enquiryTaskFIForm").submit(function(){
				$("#pageNow").val(1);
				$(this).ajaxSubmit(function(data){
					$("#replace").replaceWith(data);
				});
				return false;
			}); 
		});
		
	</script>

</body>
</html>