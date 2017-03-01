<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
  <head>
    <title>密码重置</title>
    <script type="text/javascript" src="${contextPath}/resource/comm/js/jquery-1.7.min.js"></script>
    
	<style type="text/css">
		body{
			color: #666666;
		}
		ul, li{
			list-style-type: none;
			margin: 0;
			padding: 0;
			font: 12px/150% Arial,Verdana,"宋体";
		}
		strong{
			margin: 0;
			padding: 0;
		}
		.step li{
			float: left;
			font-weight: bold;
			height: 25px;
			line-height: 25px;
			padding: 0 86px 0 85px;
			color: #999999;
		}
		label {
			float: left;
			font-size:12px;
			color: #999999;
		}
		a {
			color: #005EA7;
			cursor: pointer;
		}
		#entry{
			width: 800px;
			border: 1px solid #D1D1D1;
			border-radius: 3px;
			margin: 0 auto;
		}
		.mtop{
			border-bottom: 1px solid #D1D1D1;
			height: 33px;
			background-color: #FAFAFA;
			/**background-color:#3B90B7;**/
		}
		.mtop h2{
			font-size: 14px;
			padding-left: 15px;
			line-height: 33px;
			margin: 0;
		}
		.mbody{
			padding: 20px 40px;
		}
		.step{
			height: 25px;
			margin-bottom: 30px;
			overflow: hidden;
		}
		#step1{
			margin-left: 28px;
			background:  no-repeat scroll right top #dbecf5;
			color: #359ad8;
		}
		#step2{
			background:  no-repeat scroll right top #EDEDED;
		}
		#step3{
			background:  #EDEDED;
		}
		#entry .form1{
			display: block;
			overflow: hidden;
			margin: 0 auto;
			width: 520px;
			padding-left: 120px;
		}
		#entry .form2{
			display: none;
			overflow: hidden;
			margin: 0 auto;
			width: 520px;
			padding-left: 120px;
		}
		#entry .form3{
			display: none;
			overflow: hidden;
			margin: 0 auto;
			width: 520px;
		}
		#entry .item{
			height: 55px;
			line-height: 26px;
			padding-top: 5px;
		}
		#entry .label{
		    font-size: 14px;
			float: left;
			text-align: right;
			width: 90px;
		}
		.f1{
			float: left;
		}
		.f2{
			text-align:center;
		}
		#username{
			float: left;
			height: 16px;
			padding: 4px 3px;
			width: 240px;
			border: 1px solid #BBBBBB;
			font-family: arial,"宋体";
		}
		#username_error, #authcode_error, #code_error{
			color: #CC0000;
		}
		#authcode{
			float: left;
			height: 16px;
			padding: 4px 3px;
			width: 100px;
			border: 1px solid #BBBBBB;
			font-family: arial,"宋体";
		}
		#code{
			float: left;
			height: 16px;
			padding: 4px 3px;
			width: 100px;
			border: 1px solid #BBBBBB;
			font-family: arial,"宋体";
		}
		.clr{
			display: block;
		}
		#authcode_img{
			cursor: pointer;
			height: 26px;
			width: 100px;
			margin: 0 5px;
		}
		#nextstep{
			cursor: pointer;
			float: left;
			font-size: 14px;
			font-weight: bold;
			height: 25px;
			width: 68px;
			line-height: 25px;
			text-align: center;
		}
		#submit{
			cursor: pointer;
			float: left;
			font-size: 14px;
			font-weight: bold;
			height: 25px;
			width: 68px;
			line-height: 25px;
			text-align: center;
		}
		#ok{
			text-align: center;
			cursor: pointer;
			float: left;
			font-size: 14px;
			font-weight: bold;
			height: 25px;
			width: 68px;
			line-height: 25px;
			margin-left: 230px;
		}
		.text-info{
			font-family: Verdana,Geneva,sans-serif;
			font-weight : bold;
			width:112px;
			color: #359AD8;
		}
		#sendMobileCode{
			color: #666666;
			float: left;
			height: 25px;
			line-height: 25px;
			text-align: center;
			width: 121px;
			font-size: 12px;
			text-decoration: none;
			margin-right: 5px;
		}
		#sendMobileCode1{
			float: left;
			height: 25px;
			line-height: 25px;
			text-align: center;
			width: 121px;
			font-size: 12px;
			text-decoration: none;
			margin-right: 5px;
			color: #CCCCCC;
			font-weight: bold;
		}
		#sendMobileCodeDiv{
			display: block;
		}
		#timeDiv{
			display: none;
		}

	</style>
	
	<script type="text/javascript">
	
		$(function() {
			$("#username").focus(function(){
				$("#username_error").css("display","none");
			});
			$("#authcode").focus(function(){
				$("#authcode_error").css("display","none");
			});
			$("#code").focus(function(){
				$("#code_error").css("display","none");
			});

			/** nextstep click **/
			$("#nextstep").click(function(){
				var username = $("#username").attr("value");
				var authcode = $("#authcode").attr("value");
				if(username!=null && username!="" && authcode!=null && authcode!=""){
					$.ajax({
						type : "post",
						url : "${contextPath}/password/password!getMobileByCardnumber.shtml",
						data : {
							cardNumber : username,
							authCode : authcode
						},
						dataType : "json",
						success: function(data){
							if(data.result != 0){
								$("#authcode_error").text(data.message);
								$("#authcode_error").css("display","block");
							}else{
								$(".form1").css("display","none");
								$(".form2").css("display","block");
								
								$("#step1").css("background-image","url(${contextPath}/resource/${contextStyleTheme}/images/p5.png)");
								$("#step1").css("background-color","#BBBBBB");
								$("#step1").css("color","#FFFFFF");
								$("#step2").css("background-image","url(${contextPath}/resource/${contextStyleTheme}/images/p2.png)");
								$("#step2").css("background-color","#dbecf5");
								$("#step2").css("color","#359ad8");
								
								$("#user").text(username);
								$("#mobile").text(data.mobile);
								$("#mobile1").text(data.mobile);
							};
						},
						error: function (XMLHttpRequest, textStatus, errorThrown) {
				              alert(errorThrown);
				       	}
					})
				}else if(username == ""){
					$("#username_error").text("请输入您的用户名");
					$("#username_error").css("display","block");
				}else if(authcode == ""){
					$("#authcode_error").text("请输入验证码");
					$("#authcode_error").css("display","block");
				}
			});
			/** send message authcode **/
			$("#sendMobileCode").click(function(){
				var username = $("#username").attr("value");
			    $.ajax({
		 			 	url: "${contextPath}/password/password!sendMsgAuthcode.shtml",
		 				data:"cardNumber="+username 
 				});
				
				$("#time").text(119);
				$("#sendMobileCodeDiv").css("display","none");
				$("#timeDiv").css("display","block");
				setTimeout(countDown, 1000);
				$("#code").removeAttr("disabled");
			});
			
			/** verify message authcode **/
			$("#submit").click(function(){
				var username = $("#username").val();
				var authCode = $("#code").val();
				$.ajax({
					type : "post",
					url : "${contextPath}/password/password!msgAuthcodeValidate.shtml",
					data : {
						cardNumber : username,
						authCode : authCode
					},
					dataType : "json",
					success: function(data){
						if(data.result != 0){
							$("#code_error").text(data.message);
							$("#code_error").css("display","block");
						}else{
							$(".form2").css("display","none");
							$(".form3").css("display","block");
							
							$("#step1").css("background-image","url(${contextPath}/resource/${contextStyleTheme}/images/p9.png)");
							$("#step2").css("background-image","url(${contextPath}/resource/${contextStyleTheme}/images/p5.png)");
							$("#step2").css("background-color","#BBBBBB");
							$("#step2").css("color","#FFFFFF");
							$("#step3").css("background-color","#dbecf5");
							$("#step3").css("color","#359ad8");
							
							$("#password").text(data.defaultPassword);
						};
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
				              alert(errorThrown);
				    }
				});
			});
			
			$("#ok").click(function(){
				window.location.href="${contextPath}/login/login.jsp";
			});
			
		});
		/** seconds counter **/
		function countDown(){
			var time = $("#time").text();
			$("#time").text(time - 1);
			if (time == 1) {
				$("#sendMobileCodeDiv").css("display","block");
				$("#timeDiv").css("display","none");
				$("#code").removeAttr("disabled");
			} else {
				setTimeout(countDown, 1000);
			}
		}
		/** authcode click **/
		function imgClick(){
			$("#authcode_img").attr({src:"${contextPath}/login/login!generateCheckCode.shtml?"+ Math.random()});
		}
		
	</script>

  </head>
  
  <body>
    <br />
    <br />
    <br />
    <div id="entry">
    	<div class="mtop">
    		<h2>密码重置</h2>
    	</div>
    	<div class="mbody">
    		<div class="step">
    			<ul>
    				<li id="step1" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/p2.png);">填写用户名</li>
    				<li id="step2" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/p1.png);">验证身份</li>
    				<li id="step3" >完成</li>
    			</ul>
    		</div>
    		<div class="form1">
    			<div class="item">
    				<span class="label">用户名：</span>
    				<div class="f1">
    					<input id="username" type="text"/>
    					<span class="clr"></span>
    					<label id="username_error"></label>
    				</div>
    			</div>
    			<div class="item">
    				<span class="label">验证码：</span>
    				<div class="f1">
    					<input id="authcode" type="text"/>
    					<label>
    						<img id="authcode_img" src="${contextPath}/login/login!generateCheckCode.shtml" alt="" align="middle" onclick="imgClick()"/>
    					</label>
    					<label>
    						看不清？
    						<a onclick="imgClick()">换一张</a>
    					</label>
    					<span class="clr"> </span>
    					<label id="authcode_error"></label>
    				</div>
    			</div>
    			<div class="item">
    				<span class="label">&nbsp</span>
    				<div id="nextstep" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/p3.png);">下一步</div>
    			</div>
    		</div>
    		
    		<div class="form2">
    			<div class="item">
    				<span class="label">用户：</span>
    				<div class="f1">
    					<label id="user" class="text-info"></label>
    				</div>
    			</div>
    			<div class="item">
    				<span class="label">已验证手机：</span>
    				<div class="f1">
    					<div id="sendMobileCodeDiv">
    						<label id="mobile" class="text-info"></label>
    						<a id="sendMobileCode" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/p6.png);">获取短信验证码</a>
    					</div>
    					<div id="timeDiv">
    						<label id="mobile1" class="text-info"></label>
    						<a id="sendMobileCode1" href="" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/p7.png);">
    							<strong id="time"></strong>
    							秒后发重新发送
    						</a>
    						<label>验证码已发送，请查收短信!</label>
    					</div>
    				</div>
    			</div>
    			<div class="item">
    				<span class="label">短信验证码：</span>
    				<div class="f1">
    					<input id="code" disabled="disabled"/>
    					<span class="clr"> </span>
    					<label id="code_error" style="display: none"></label>
    				</div>
    			</div>
    			<div class="item">
    				<span class="label">&nbsp</span>
    				<div id="submit" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/p3.png);">提交</div>
    			</div>
    		</div>
    		
    		<div class="form3">
    			<div class="item">
    				<div class="f2">
    					密码重置成功，初始密码：
    					<span id="password" class="text-info"></span>
    				</div>
    			</div>
    			<div class="item">
    				<div class="f2">
    					<div id="ok" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/p3.png);" >确定</div>
    				</div>
    			</div>
    		</div>
    	</div>    	
    	
    </div>
  </body>
</html>
