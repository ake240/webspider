<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆 - web spider</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="banner">
		<div class="container header-container">
			<div class="navbar-header">
				<a href="http://www.datayes.com/" target="_blank">
					<img src="images/logo.png" class="logo">
				</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row clearfix">
			<div class="login-container">
				<div class="col-md-12 column">
					<div class="page-header" style="border-bottom-color: #ccc">
						<h2>登录</h2>
					</div>
				</div>
				<div class="col-md-12 column" style="text-align: center;">
					<form role="form" action="login" id="loginForm" method="post">
						<s:if test="message!=null">
							<div class="form-group login-input">
								<div class="alert alert-danger alert-dismissable" style="padding: 8px 12px;">
									<button type="button" class="close" style="right: 0;" data-dismiss="alert" aria-hidden="true">&times;</button>
									<s:property value='message'/>
								</div>
							</div>
						</s:if>
						<div class="form-group login-input">
							<input type="text" class="form-control" id="username" name="username" value="<s:property value='username'/>" placeholder="用户名" />
						</div>
						<div class="form-group login-input">
							 <input type="password" class="form-control" id="password" name="password" placeholder="密码" />
						</div>
						<!-- <div class="checkbox">
							 <label><input type="checkbox" />两周内自动登录</label>
						</div> -->
						<button type="submit" id="loginBtn" class="btn btn-success login-input">登&nbsp;录</button>
					</form>
				</div>
			</div>
		</div>
		
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="login-foot">
					Copyright © 2014 DataYes. All rights reserved.
				</div>
				<div style="text-align: center; color: #ffffff">
					version : 2014-09-26
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$("#loginForm").submit(function(){
				var username = $("#username").val();
				var password = $("#password").val();
				var ret = true;
				
				if(!username){
					$("#username").addClass("input-error");
					ret = false;
				}else{
					$("#username").removeClass("input-error");
				}
				
				if(!password){
					$("#password").addClass("input-error");
					ret = false;
				}else{
					$("#password").removeClass("input-error");					
				}
				
				if(ret){
					$("#loginBtn").attr("disabled","disabled");
				}
				
				return ret;
			});
			
		});
	
	</script>
</body>
</html>