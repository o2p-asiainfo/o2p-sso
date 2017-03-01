<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<title>密码修改</title>
		<script type="text/javascript" src="${contextPath}/resource/comm/js/jquery-1.7.min.js"></script>
		<link href="${contextPath}/resource/${contextStyleTheme}/css/changePassword.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">
			$(function() {
				document.body.minWidth = $("#myform").width();
				document.body.minHeight = $("#myform").height();
				document.getElementById("myform").style.top = (document.body.clientHeight - $("#myform").height())/2 + "px";
				document.getElementById("myform").style.marginLeft = (document.body.clientWidth - $("#myform").width())/2 + "px";
				
				$("#forgetpass").click(function(){
					top.location.href="forgetPassword.jsp";
				});
			})
			
			function resize(){
				document.getElementById("myform").style.top = (document.body.clientHeight - $("#myform").height())/2 + "px";
				document.getElementById("myform").style.marginLeft = (document.body.clientWidth - $("#myform").width())/2 + "px";
			}
			/** verify old password, not null **/
			function oldblur(){
				var oldpassword = $("#oldpass").val();
				$("#oldpassflow").css("display", "none");
				if(oldpassword==null || oldpassword=="") {
					$("#oldpassflow").html("请输入旧密码");
					$("#oldpassflow").css("display", "block");
				}
			}
			/** verify new password **/
			function newblur(){
				var newpassword = $("#newpass").val();
				var reg = /^[A-Za-z0-9]{6,14}$/g;
				$("#newpassflow").css("color","#a5a6aa");
				if(!reg.test(newpassword)){
					$("#newpassflow").css("color","#FF0000");
				}
			}
			/** verify confirmpassword **/
			function confirmblur(){
				var newpass = $("#newpass").val();
				var confirmpass = $("#confirmpass").val();
				$("#confirmpassflow").css("display", "none");
				if(newpass!="" && confirmpass != newpass){
					$("#confirmpassflow").html("与新密码不一致，请重新输入");
					$("#confirmpassflow").css("display", "block");
				}
			}
			/** reset **/
			function reset(){
				$("#oldpass").val("");
				$("#newpass").val("");
				$("#confirmpass").val("");
				$("#oldpassflow").css("display", "none");
				$("#confirmpassflow").css("display", "none");
				$("#newpassflow").css("color","#a5a6aa");
			}
			/** save password **/
			function save(){
				var oldpassword = $("#oldpass").attr("value"); 
				var newpassword = $("#newpass").attr("value");
				var confirmpass = $("#confirmpass").attr("value");
				if(oldpassword==null || oldpassword==""){
					oldblur();
				}else {
					var reg = /^[A-Za-z0-9]{6,14}$/g;
					$("#newpassflow").css("color","#a5a6aa");
					if(!reg.test(newpassword)){
						$("#newpassflow").css("color","#FF0000");
					}else{
						if(confirmpass != newpassword){
							confirmblur();
						}else {
							$.ajax( {
								type : "post",
								url : "${contextPath}/password/password!changePassword.shtml",
								data : { cardNumber : '${cardNumber.userId}', newPassword : newpassword, oldPassword: oldpassword },
								dataType : "json",
								success : function(data) {
									if(data.result == 1){
										window.alert(data.message);
									};
									if(data.result == 0){
										window.alert(data.message);
										top.location.href="${contextPath}/main/index!logOut.shtml?userName=${cardNumber.userId}";
									};
									if(data.result == 2){
										top.location.href="${contextPath}/main/index!logOut.shtml?userName=${cardNumber.userId}";
									}
								},
								error: function (XMLHttpRequest, textStatus, errorThrown) {
						              alert(errorThrown);
						       	}
							})
						}
					}
				}
			}
		</script>
	</head>
	<body onresize="resize()">
		<br />
		<br />
		<br />
		<div id="myform" >
			<div id="myform-body">
				<fieldset id="fieldset">
					<legend>密码修改</legend>
					<div id="fieldset-body">
						<table cellspacing="8px">
							<tr>
								<td align="right"><label for="oldpass">旧密码：</label></td>
								<td><input type="password" id="oldpass" onblur="oldblur()"/></td>
								<td>
									<div id="oldpassflow">11</div>
								</td>
							</tr>
							<tr>
								<td align="right"><label for="newpass">新密码：</label></td>
								<td><input type="password" id="newpass" onblur="newblur()"/></td>
								<td>
									<div id="newpassflow" >6-14位字母、数字组合，字母区分大小写</div>
								</td>
							</tr>
							<tr>
								<td align="right"><label for="confirmpass">确认密码：</label></td>
								<td><input type="password" id="confirmpass" onblur="confirmblur()"/></td>
								<td>
									<div id="confirmpassflow"></div>
								</td>
							</tr>
							<tr><td></td></tr>
							<tr>
								<td></td>
								<td></td>
								<td align="right">忘记密码？<a id="forgetpass"  >密码重置</a></td>
							</tr>
						</table>
					</div>
				</fieldset>
			</div>
			<div id="tool-bar">
				<div id="save" onclick="save()" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/p3.png);">保存修改</div>
<!--				<button id="reset" onclick="reset()">重置</button>-->
			</div>
		</div>
		
		<%@include  file="../common.jsp" %>
	</body>
</html>
