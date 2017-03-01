<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	BaseAction baseAction = new BaseAction();
	String localeName = baseAction.getLocaleName();
	request.setAttribute("localeName", localeName);
	String tid = request.getParameter("tid")==null?"":request.getParameter("tid"); 
%>
<html>
	<head>
		<title>${APP_WEB_TITLE}</title>
		<script type="text/javascript"src="${contextPath}/resource/comm/js/jquery-1.7.min.js"></script>
		<script type="text/javascript"src="${contextPath}/resource/comm/js/sha256.js"></script>
		<link id="css" href="${contextPath}/resource/${contextStyleTheme}/css/${contextStyleSpecial}/login.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			#forgetPass{
				font-size: 12px;
				margin-left:480px;
				margin-top: 300px;
				position: absolute;
				width: 200px;
				text-align: right;
			}
			#resetPass{
				color: #005EA7;
				cursor: pointer;
			}
		</style>
		<script>
			var tid = '<%=tid%>';
			$(function() {
				getTenantInfo();
				var screenWidth = window.screen.width;
				document.body.style.minWidth = 990 + 'px';
					
				/** Load businessSystem 
				$.ajax({
					type:"post",
					url:"login!querySysBusinessSystem.shtml",
					dataType: "json",
					success: function (data) {
						$.each(data, function(i, item) {
							var str = "<option value='"+item.businessSystemId+"'> "+item.businessSystemName+"</option>";
							$("#systemSwith").append(str);
						});
					},
				});  **/
			
				var checkcode = $("#check").attr("value");
				var passId = $("#passId").attr("value");
				var password = $("#passWord").attr("value");
				if (checkcode != "") {
					$("#passCheckflow").css("display", "none");
				}
				if (passId != "") {
					$("#passIdflow").css("display", "none");
				}
				$("#passId").focus(function() {
					$("#passIdflow").css("display", "none");
				});
				$("#passId").click(function() {
					$("#passIdflow").css("display", "none");
				});
				$("#passIdflow").click(function() {
					$("#passIdflow").css("display", "none");
					$("#passId").focus();
				});
				$("#passWord").focus(function() {
					$("#passWordflow").css("display", "none");
				});
				$("#passWord").click(function() {
					$("#passWordflow").css("display", "none");
				});
				$("#passWordflow").click(function() {
					$("#passWordflow").css("display", "none");
					$("#passWord").focus();
				});
				$("#check").focus(function() {
					$("#passCheckflow").css("display", "none");
				});
				$("#check").click(function() {
					$("#passCheckflow").css("display", "none");
				});
				$("#passCheckflow").click(function() {
					$("#passCheckflow").css("display", "none");
					$("#check").focus();
				});
				$("#checkImg").click(function(){
					$("#checkImg").attr({src:"../login/generateCheckCode.shtml?"+ Math.random()});
				});
				$("#ok").hover(
					function() {
						$("#menu01").css("background-image",
								"url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/menu-hover_11.png)");
					},
					function() {
						$("#menu01").css("background-image",
								"url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/menu_11.png)");
				});
				$("#re").hover(
					function() {
						$("#menu02").css("background-image",
								"url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/menu-hover_11.png)");
					},
					function() {
						$("#menu02").css("background-image",
								"url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/menu_11.png)");
				});
				
				$(document).keydown(function(event){
					if(event.keyCode==13) {
						//$("#ok").click();
						login();
						return false;
					}
				});
				
				$("#resetPass").click(function(){
					window.location.href = "${contextPath}/password/forgetPassword.jsp";
				});
				
			});
			/**
			 * check code and user
			 */
			function login() {
				///var businessSystemId = $("#systemSwith").val();
				var checkcode = $("#check").attr("value");
				var username = $("#passId").attr("value");
				var password = $("#passWord").attr("value");
				var tenantId = $("#tenantId").val();
				var date = new Date();
				var timeOffset = date.getTimezoneOffset();
				if(checkcode != "" && username != "" && password != ""){
					$.ajax( {
						type : "post",
						url : "../login/login.shtml",
						data : {
							checkcode: checkcode,
							username : username, 
							password : hex_sha256(password),
							timeOffset : timeOffset,
							tenantId : tenantId
						},
						dataType : "json",
						success : function(data) {
							$.each(data, function(i, item) {
								if (item.result == 3) {
									window.alert('<s:text name="eaap.op2.sso.login.passwordExpire" />');
									location.href = "${contextPath}/password/changePassword.jsp";
								}
								if (item.result == 0) {
									///location.href = "${contextPath}/main/main.jsp?businessSystemId=1";
									location.href = "${contextPath}/main/index.jsp";
									//?businessSystemId="+businessSystemId;
								}
								if (item.result == 1) {
									window.alert(item.errorMessage);
									//alertMsg('warning',item.errorMessage,'warning');
									$("#checkImg").attr({src:"../login/generateCheckCode.shtml?"+ Math.random()});
								}
								/** Existing user login in other locations **/
								if (item.result == 2) {
									//confirmMsg('tips','<s:text name="eaap.op2.sso.login.hasLoggedOn" />',function a(t) {
									var t = window.confirm('<s:text name="eaap.op2.sso.login.hasLoggedOn" />');
										if(t == true){
											$.ajax( {
												type : "post",
												url : "../login/forcedLogin.shtml",
												data : {
													username : username
												},
												success: function(){
													///location.href = "${contextPath}/main/main.jsp?businessSystemId="+businessSystemId;
													location.href = "${contextPath}/main/index.jsp";
												//?businessSystemId="+businessSystemId;
												}
											})
										}
								 	//})
								}
							});
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert(errorThrown);
						}
					});
				} else if(username == ""){
					window.alert('<s:text name="eaap.op2.sso.login.username.notNull"/>');
//				    alertMsg('warning','<s:text name="eaap.op2.sso.login.username.notNull"/>','warning');
				} else if(password == ""){
					window.alert('<s:text name="eaap.op2.sso.login.password.notNull"/>');
//					alertMsg('warning','<s:text name="eaap.op2.sso.login.password.notNull"/>','warning');
				} else if(checkcode == ""){
					window.alert('<s:text name="eaap.op2.sso.login.checkcode.notNull"/>');
//					alertMsg('warning','<s:text name="eaap.op2.sso.login.checkcode.notNull"/>','warning');
				} 
			}
			/**
			 * reset
			 */
			function reset() {
				var checkcode = $("#check").attr("value", "");
				var passId = $("#passId").attr("value", "");
				var password = $("#passWord").attr("value", "");
			}
			
			function getTenantInfo(){
			  	$.ajax({
			   		type: "post",
			   		url: "../login/getTenantInfo.shtml?tid=" + tid,
			       	dataType: "json",
					async:false,
			       	success: function (data) {
			       		if(data.RespCode == "000"){
			       			var myDate = new Date();
			       			var year = myDate.getFullYear();
		       				var tenant = data.tenant;
		       				$("#tenantId").val(tenant.tenantId);
		       				$("#logo01").css("background-image","url(${contextPath}/resource/tenant/"+tenant.logo+")");
		       				$("#footer").html("Copyright &copy; "+year+" "+tenant.name+" All Rights Reserved");
		       			}else if(data.RespCode == "111"){
		       				//URL地址错误，根据租户Code找不到租户信息
		       				var rHtml = "<div style='color:#FF0000;font-size:16px; line-height:35px;padding:10px;'>"
	       					rHtml += "Access Failed, Please confirm the URL is available."
		       				rHtml += "</div>"
	       					$("html").html(rHtml);
		       			}else {
		       				//异常
		       				var rHtml = "<div style='color:#FF0000;font-size:16px; line-height:35px;padding:10px;'>"
	       					rHtml += "Access failure, service exception."
		       				rHtml += "</div>"
	       					$("html").html(rHtml);
		       			}
			       	},
			       	error: function (XMLHttpRequest, textStatus, errorThrown) {
	       				//异常
	       				var rHtml = "<div style='color:#FF0000;font-size:16px; line-height:35px;padding:10px;'>"
       					rHtml += "Access failure, service exception."
	       				rHtml += "</div>"
       					$("body").html(rHtml);
			       	}
				}); 	  	
			}
			
</script>
</head>
<body>
		<s:form action="login!login.shtml" method="post" name="loginform"  id="loginform" theme="simple">
			<input type="hidden"   id="tenantId"  name="tenantId"/>
			<div id="backgroud" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/background.png);">
				<div id="logo">
					<div id="logo01" style="background-image: url(${contextPath}/resource/tenant/logo_en_US_super.png)"></div>
				</div>

				<div id="login">
					<div id="login01" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/login-bg_08${localeName}.png)">
						<div id="user">
							<div id="user01">
								<div id="user02"></div>
								<div class="usertext">
									<input id="passId" value=""></input>
									<div id="passIdflow">
										<s:text name="eaap.op2.sso.login.username"/>
									</div>
								</div>
							</div>
						</div>
						<div id="password">
							<div id="user01">
								<div id="password02"></div>
								<div class="usertext">
									<input type="password" id="passWord"  value=""/>
									<div id="passWordflow">
										<s:text name="eaap.op2.sso.login.password"/>
									</div>
								</div>
							</div>
						</div>
   <!--				  		
  			 	  		<div id="system">
							<div id="user01">
								<div id="password02"></div>
								<div class="usertext">
									<select id="systemSwith"></select>
								</div>
							</div>
						</div>	
	-->			   	 
						<div id="yanzhengma">
							<div id="yangzhengma01">
								<input id="check"  value=""/>
								<div id="passCheckflow">
									<s:text name="eaap.op2.sso.login.checkcode"/>
								</div>
							</div>
							<div id="yangzhengma02">
								<img id="checkImg"
									src="../login/generateCheckCode.shtml" alt=""
									align="middle" />
							</div>
						</div>

						<div id="menu">
							<div id="menu01" >
								<ul>
									<li> 
										<button  id="ok"  target="_parent"  style="cursor:pointer; width:80px; height:32px; background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/menu_11.png); border:none; height:28px;" 
											onclick="login();return false;"  tabindex="4"><s:text name="eaap.op2.sso.login.confirm"/></button>
									</li>
								</ul>
							</div>
							<div id="menu02" >
								<ul>
									<li>
										<button  id="re"  target="_parent"  style="cursor:pointer; width:80px; height:32px; background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/menu_11.png); border:none; height:28px;" 
											onclick="reset(); return false;"><s:text name="eaap.op2.sso.login.reset"/></button>
									</li>
								</ul>
							</div>
						</div>
               <!--				  	 	  
					 	<div id="forgetPass">
								忘记密码？<a id="resetPass">密码重置</a>
						</div> 
				-->	 	
				    </div>
					 
				</div>
				<div id="footer">
					Copyright &copy; 2012-2016 ${tenant.name} All Rights Reserved
				</div>
			</div>
		</s:form>
		
	</body>
</html>
