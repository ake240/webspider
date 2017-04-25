<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>连接池</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h3 style="display: inline;">机器：<s:property value="host"/>，&nbsp;连接池名称：<s:property value="role"/></h3>
				</div>
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							筛选条件
						</h3>
					</div>
					<div class="panel-body">
					
						<form action="enquiryConnection" method="post" id="enquiryConnectionPool">
							<div class="row clearfix">
								<div class="col-md-4 column">
									<div class="form-group">
										<label for="host">IP</label>
										<select class="form-control form-select2" id="ipSelector" name="ip"></select>
									</div>
								</div>
								<div class="col-md-4 column">
									<div class="form-group">
										<label for="host">账号</label>
										<select class="form-control form-select2" id="accountSelector" name="account"></select>
									</div>
								</div>
								<div class="col-md-4 column">
									<div class="form-group">
										<label for="host">状态</label>
										<select class="form-control form-select2" id="statusSelector" name="status">
											<option value=""></option>
											<option value="true">正常</option>
											<option value="false">异常</option>
										</select>
									</div>
								</div>
							</div>
							<div class="row clearfix">
								<div class="col-md-12 column">
									<input type="hidden" id="host" name="host" value='<s:property value="host"/>' />
									<input type="hidden" id="id" name="id" value='<s:property value="id"/>' />
									<input type="hidden" name="ajaxSearch" value="true"/>
									<button type="submit" class="btn btn-success" id="searchBtn">筛选</button>
									<input type="button" class="btn btn-success" id="beginRefreshButton" value="开启自动刷新" />
									<input type="button" class="btn btn-success" id="endRefreshButton" value="关闭自动刷新" />
								</div>
							</div>
						</form>
					</div>
				</div>
				
				
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							筛选结果
						</h3>
					</div>
					<div class="panel-body">
						<div class="row clearfix">
							<div class="col-md-8 column"></div>
							<div class="col-md-4 column" style="text-align:right;" id="resultCount">
								
							</div>
						</div>
						<div class="row clearfix">
							<div class="col-md-12 column">
								<div id="replace">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>IP</th>
											<th>端口</th>
											<th>账号</th>
											<th>操作类型</th>
											<th>IP 1h</th>
											<th>IP今天</th>
											<th>账号 1h</th>
											<th>账号今天</th>
											<th>连接状态</th>
										</tr>
									</thead>
									<tbody id="tbody">
									</tbody>
								</table>
								<div id="paging" class="page-nav" style="overflow: hidden;"></div>
							</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var connectionString ;
		var connectionData;
		var itemCount;
		var pageSize = 25;
		var currentPage = 1;
		var pageCount;
// 		function initializePage(){
// 			connectionData = $.parseJSON(connectionString).data;
// 			filter2();
// 			itemCount = connectionData.length;
// 			pageCount = Math.ceil(itemCount / pageSize);
// 			showPage(1);
// 		}
		
		function filter(){
			connectionData = $.parseJSON(connectionString).data;
			var tempTotal = connectionData.length;
			filter2();
			itemCount = connectionData.length;
			$("#resultCount").text("统计：筛选后有"+itemCount+"条记录，共有"+tempTotal+"条记录")
			pageCount = Math.ceil(itemCount / pageSize);
			showPage(1);
		}
		
		function filter2(){
// 			if(ipValue !=null){
// 				ip = ipValue;//$("#ipSelector").find("option:selected").text();
// 			} else {
// 			}
			ip = $("#ipSelector").find("option:selected").text();
			connectionData =$.grep(connectionData, function(item){
				if( ip == null || ip == ""){
					return true;
				} else {
					if(item.host == ip){
						return true;
					} else {
						return false;
					}
				}
			});
			account = $("#accountSelector").find("option:selected").text();
			connectionData = $.grep(connectionData, function(item){
				if( account == null || account == ""){
					return true;
				} else {
					if(item.account == account){
						return true;
					} else {
						return false;
					}
				}
			});
			
			
			status = $("#statusSelector").find("option:selected").text();
// 			console.log("Status:" + status);
			connectionData = $.grep(connectionData, function(item){
				flag = true;
				if(status == null || status == ""){
					return true;
				} else{
					item.websiteOperationReqList = $.grep(item.websiteOperationReqList, function(innerItem){
						if(innerItem.exceptionRuleName == null || innerItem.exceptionRuleName == ""){
							
						} else {
							flag = false;
						}
						if(innerItem.exceptionRuleName == null || innerItem.exceptionRuleName == "" ){
							
						} else {
							flag = false;
						
						if(innerItem.maxIpRequestNumPerHour > 0 && innerItem.IpReqNumPerHour >= innerItem.maxIpRequestNumPerHour){
							flag = false;
						}
						if(innerItem.maxIpRequestNumPerDay > 0 && innerItem.IpReqNumPerDay >= innerItem.maxIpRequestNumPerDay){
							flag = false;
						}
						if(innerItem.maxUserRequestNumPerHour >0 && innerItem.userReqNumPerHour >= innerItem.maxUserRequestNumPerHour){
							flag = false;
						}
						if(innerItem.maxUserRequestNumPerday >0 && innerItem.userReqNumPerDay >= innerItem.maxUserRequestNumPerDay){
							flag = false;
						}
						
						if(status == "正常"){
							return flag;
						} else {
							return !flag;
						}
					});
					
					if(item.websiteOperationReqList == null || item.websiteOperationReqList.length == 0){
						return false;
					} else {
						return true;
					}
				}
				
				
			});
		}
		
		function processItem(item){
			var size = item.websiteOperationReqList.length;
			//console.log(size);
			if(size == 0){
				var tr = "<tr><td>"+
					item.host + "</td><td>" +
					item.port + "</td><td>" +
					item.account + "</td><td></td><td></td><td></td><td></td><td></td><td></td></tr>";
				$("#tbody").append(tr);
			} else {
				var accountTd = "</td><td></td><td>"
				if ((item.account==null || item.account=="")){
					
				} else {
					accountTd = 	new Date(item.websiteOperationReqList[0].userReqStartTime).Format("hh:mm:ss") + "-" +
									new Date(item.websiteOperationReqList[0].userReqEndTime).Format("hh:mm:ss") + "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
									+ item.websiteOperationReqList[0].userReqNumPerHour +"/" + item.websiteOperationReqList[0].maxUserRequestNumPerHour + "</td><td>" 
									+ item.websiteOperationReqList[0].userReqNumPerDay +"/" + item.websiteOperationReqList[0].maxUserRequestNumPerDay + "</td><td>";
				}
				var tr = "<tr><td rowspan='"+size+"'>"+
					item.host + "</td><td rowspan='"+size+"'>" +
					item.port + "</td><td rowspan='"+size+"'>" +
					item.account + "</td><td>"+
					
					item.websiteOperationReqList[0].websiteOperationName + "</td><td>" +
					new Date(item.websiteOperationReqList[0].ipReqStartTime).Format("hh:mm:ss") + "-" +
					new Date(item.websiteOperationReqList[0].ipReqEndTime).Format("hh:mm:ss") + "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+ item.websiteOperationReqList[0].ipReqNumPerHour +"/" + item.websiteOperationReqList[0].maxIpRequestNumPerHour + "</td><td>"+
					item.websiteOperationReqList[0].ipReqNumPerDay +"/" + item.websiteOperationReqList[0].maxIpRequestNumPerDay + "</td><td>"+
						accountTd
					+
					processExceptionNotice(item.websiteOperationReqList[0]) + "</td></tr>";
				$("#tbody").append(tr);	
				for(var i = 1 ;i<size;i++){
					$("#tbody").append("<tr><td>"+
							item.websiteOperationReqList[i].websiteOperationName + "</td><td>" +
							new Date(item.websiteOperationReqList[i].ipReqStartTime).Format("hh:mm:ss") + "-" +
							new Date(item.websiteOperationReqList[i].ipReqEndTime).Format("hh:mm:ss") + "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
							+ item.websiteOperationReqList[i].ipReqNumPerHour +"/" + item.websiteOperationReqList[i].maxIpRequestNumPerHour + "</td><td>"+
							item.websiteOperationReqList[i].ipReqNumPerDay +"/" + item.websiteOperationReqList[i].maxIpRequestNumPerDay + "</td><td>"+
							accountTd +
							processExceptionNotice(item.websiteOperationReqList[i]) + "</td></tr>")
				}
			}
			//console.log(item);
			
		}
		
		function processExceptionNotice(item){
			if(item.exceptionRuleName == '' &&	( item.maxIpRequestNumPerHour <= 0 || item.ipReqNumPerHour < item.maxIpRequestNumPerHour ) 
					 && ( item.maxIpRequestNumPerDay <= 0 || item.ipReqNumPerDay < item.maxIpRequestNumPerDay)
				     && ( item.maxUserRequestNumPerHour <= 0 || item.userReqNumPerHour < item.maxUserRequestNumPerHour) 
				     && ( item.maxUserRequestNumPerDay <= 0 || item.userReqNumPerDay < item.maxUserRequestNumPerDay)){
				return "正常";
			} else {
				result = item.exceptionRuleName + "<br/>";
				if(item.maxIpRequestNumPerHour > 0 && item.ipReqNumPerHour >= item.maxIpRequestNumPerHour){
					result += "IP超出限制<br/>";
				}
				if(item.maxIpRequestNumPerDay > 0 && item.ipReqNumPerDay >= item.maxIpRequestNumPerDay){
					result += "当日IP超出限制<br/>";
				}
				if(item.maxUserRequestNumPerDay > 0 && item.userReqNumPerDay >= item.maxUserRequestNumPerDay){
					result += "用户请求超出限制<br/>";
				}
				if(item.maxUserRequestNumPerDay > 0 && item.userReqNumPerDay >= item.maxUserRequestNumPerDay){
					result += "当日用户请求超出限制<br/>";
				}
				return result;
			}
		}
		
		function showPage(pageNo){
			$('#tbody').html('');
	        for(var i = (pageNo-1) * pageSize; i < pageSize*pageNo && i <connectionData.length ; i++){
	            processItem(connectionData[i]);
	        }
	        $("#paging").html("");
	        
	        for(var i=1;i<=pageCount;i++){
	        	if(i!=pageNo){
		        	$("#paging").append('<button type="button" class="navPage btn btn-default page-active" pageNum="'+i+'">第'+i+'页</button>&nbsp;');
	        	}
	        }
	        $('.navPage').click(function(){
	            a = $(this).attr('pagenum');
	            showPage(a);
	        })
		}
		
		var timeoutId;
		
// 		function autoRefresh(){		
// 			documentReload();
// 			timeoutId = setInterval("hello()",5000);
// // 			timeoutId = setTimeout(autoRefresh(),5000);
// 		}
// 		function hello(){
// 			console.log("Hello");
// 		}
		
		$("#beginRefreshButton").click(function(){
			if(timeoutId == null){
				timeoutId = 1;
				documentReload();
			}
		});
		$("#endRefreshButton").click(function(){
			clearTimeout(timeoutId);
			timeoutId = null;
		});
		
		
		var ipValue;// = $("#ipSelector").find("option:selected").text();
		var accountValue;// = $("#accountSelector").find("option:selected").text();
		function documentReload(){
			ipValue = $("#ipSelector").find("option:selected").text();
			accountValue = $("#accountSelector").find("option:selected").text();

			$("#ipSelector").empty();
			$("#accountSelector").empty();

// 			$("#ipSelector").select2("val","");
// 			$("#accountSelector").select2("val","");
			
			input = {};
			input.host = $("#host").val();
			input.id = $("#id").val();
			$('#tbody').html('');
			$('#tbody').append("<tr><td colspan='4'>正在加载数据</td></tr>");
			$.getJSON("enquiryConnectionJSON",input, function(result){
				$('#tbody').html('');
				//console.log(result);
				connectionString = result.jsonResult.connectionString;
				//initializePage();
				var ipArr = [];
				var accountArr = [];
				for(var i=0;i<result.connection.data.length;i++){
					ipArr.push(result.connection.data[i].host);
					accountArr.push(result.connection.data[i].account);
				}
				ipArr.sort();
				//console.log()
				accountArr.sort();
				
// 				$.unique(ipArr);
// 				$.unique(accountArr);
				var temp; 
				for(var i in ipArr){
					if(ipArr[i] != temp){
						$("#ipSelector").append("<option value="+ ipArr[i] +">" + ipArr[i] + "</option>");
						temp = ipArr[i];
					}
				}
				temp = '';
				for(var i in accountArr){
					if(accountArr[i] != temp){
						$("#accountSelector").append("<option value="+ accountArr[i] +">" + accountArr[i] + "</option>");
						temp = accountArr[i];
					}
				}
				
				$("#ipSelector").prepend("<option></option>")
				$("#accountSelector").prepend("<option></option>")
				$("#ipSelector").select2({allowClear: true});
				$("#accountSelector").select2({allowClear: true});
				$("#statusSelector").select2({allowClear: true});
				console.log("Ip/Account:"+ ipValue + "/" + accountValue);
				$("#ipSelector").select2("val",ipValue);
				$("#accountSelector").select2("val",accountValue);
// 				
				if(timeoutId != null){
					timeoutId = setTimeout("documentReload()",5000);
				}
				filter();
			});
			
			
		}
		
		$(function(){
			//enquiryConnectionJSON?host=10.21.233.64&id=3
			documentReload();
			
			
			$("#enquiryConnectionPool").submit(function(){
				filter();
				return false;
			});
			
		});
		
		
		
		// 对Date的扩展，将 Date 转化为指定格式的String 
		// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
		// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
		// 例子： 
		// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
		// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
		Date.prototype.Format = function(fmt) 
		{ //author: meizz 
		  var o = { 
		    "M+" : this.getMonth()+1,                 //月份 
		    "d+" : this.getDate(),                    //日 
		    "h+" : this.getHours(),                   //小时 
		    "m+" : this.getMinutes(),                 //分 
		    "s+" : this.getSeconds(),                 //秒 
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度 
		    "S"  : this.getMilliseconds()             //毫秒 
		  }; 
		  if(/(y+)/.test(fmt)) 
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		  for(var k in o) 
		    if(new RegExp("("+ k +")").test(fmt)) 
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
		  return fmt; 
		}
	</script>
</body>
</html>