<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/common/header.jspf" %>
	<div class="container spider-container">
		<form class="form" id="editUserForm" method="post" role="form">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="page-header page-title">
					<h1 style="display: inline;">用户</h1>
					<button type="button" id="saveUserBtn" class="btn btn-success">保存用户</button>
				</div>
				<s:if test="message!=null">
					<div class="alert alert-danger alert-dismissable" style="padding: 8px 12px;">
						<button type="button" class="close" style="right: 0;" data-dismiss="alert" aria-hidden="true">&times;</button>
						<s:property value='message'/>
					</div>
				</s:if>
				<div class="panel-group" id="base-panel">
					<div class="panel panel-default">
						<div class="panel-heading" style="overflow: hidden;">
							 <a class="panel-title" data-toggle="collapse" data-parent="base-panel" href="#base-div">基本设置</a>
						</div>
						<div id="base-div" class="panel-collapse collapse in">
							<div class="panel-body">
								<div class="row clearfix">
									<div class="col-md-6 column">
										<div class="form-group">
											<label for="username">用户名<span class="red">&nbsp;*</span>(5~16位字符或数字以及圆点符号)</label>
										 	<s:if test="type==0">
										 		<input type="text" class="form-control mandatoryInp" id="username" name="user.username" value="<s:property value='user.username'/>"/>
										 	</s:if>
										 	<s:else>
										 		<span class="form-control"><s:property value='user.username'/></span>
										 	</s:else>
										</div>
									</div>
									<s:if test="type==1">
										<div class="col-md-6 column">
											<div class="form-group">
												<label for="password">密码<span class="red">&nbsp;*</span>(6~20位字符或数字)</label>
												<input type="password" id="password" class="form-control mandatoryInp" name="user.password">
											</div>
										</div>
									</s:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" name="userId" value="<s:property value='user.userId'/>">
		</form>
	</div>
	<input type="hidden" id="type" value="<s:property value='type'/>">
	
	<script type="text/javascript">
		function checkUsername(){
			var username = $("#username").val();
			if(!username.match(/^[a-zA-Z0-9.]{5,16}$/)){
				$("#username").addClass("input-error");
				return false;
			}else{
				$("#username").removeClass("input-error");
				return true;
			}
		}
		
		function checkPassword(){
			var password = $("#password").val();
			console.log(password);
			if(!password.match(/^[a-zA-Z0-9]{6,20}$/)){
				$("#password").addClass("input-error");
				return false;
			}else{
				$("#password").removeClass("input-error");
				return true;
			}
		}
		
		$(function(){
			var type = $("#type").val();
			
			$("#saveUserBtn").click(function(){
				if(type==0){
					$("#editUserForm").attr("action","newUser");
				}else{
					$("#editUserForm").attr("action","updatePassword");
				}
				
				$("#editUserForm").submit();
			});
			
			$("#editUserForm").submit(function(){
				var ret = checkMandatory();
				if(!ret){
					alert("请输入必填字段");
					return false;
				}else{
					if(type == 0 && !checkUsername()){
						alert("用户格式不正确");
						return false;
					}
					
					if(type == 1 && !checkPassword()){
						alert("密码格式不正确");
						return false;
					}
					
					$("#saveUserBtn").attr("disabled", "disabled");
				}
			});
			
		});
	</script>
</body>
</html>