<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	BaseAction baseAction = new BaseAction();
	String localeName = baseAction.getLocaleName();
	request.setAttribute("localeName", localeName);	
	
	String businessSystemId = StringEscapeUtils.escapeHtml4(request.getParameter("businessSystemId"));
	request.setAttribute("businessSystemId",businessSystemId);
%>
<%
%>
<!--电信集团：  此页面未调整导航栏  ，整个页面没有居中调整-->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><s:text name="eaap.op2.sso.sysName"/></title>
		<link href="${contextPath}/resource/${contextStyleTheme}/css/${contextStyleSpecial}/main.css" rel="stylesheet"
			type="text/css" />
        <!-- cl	-->
		<script type="text/javascript" src="${contextPath}/struts/simple/resource/plugins/airDialog/artDialog.js?skin=${contextStyleTheme}" ></script> 
		<script type="text/javascript" src="${contextPath}/struts/simple/resource/plugins/airDialog/iframeTools.js'" ></script> 
		
		<script type="text/javascript" src="${contextPath}/resource/comm/js/jquery-1.7.min.js"></script>
		<script type="text/javascript">
			$(function(){
				document.getElementById('iframe1').style.width=document.body.offsetWidth- 178 + 'px';
				document.body.style.minWidth = (window.screen.width) * 0.88 + 'px';
//				var screenWidth = window.screen.width;
//				if(screenWidth < 1280){
//					document.body.style.width = 990 + 'px';
//				}else{
//					document.body.style.width = 1190 + 'px'
//				}
				
				/** Load the businessSystem 
				$.ajax({
					type:"post",
					url:"${contextPath}/login/login!querySysBusinessSystem.shtml",
					dataType: "json",
					success: function (data) {
						$.each(data, function(i, item) {
							if(item.businessSystemId == ${businessSystemId}){
								var str = "<option value='"+item.businessSystemId+"' selected='selected'> "+item.businessSystemName+"</option>";
							}else{
								var str = "<option value='"+item.businessSystemId+"'> "+item.businessSystemName+"</option>";
							}
							$("#businessSystem").append(str);
						});
					},
				});
				**/
				
				/**
				 * Load the top of the navigation data
			 	 * @param {Object} data
			 	*/
				$.ajax({
				   		type: "post",
				   		url: "index!showMenu.shtml",
				   		dataType: "json",
				   		data: {sysPersonId:${cardNumber.id}, parentMenuId:1,businessSystemId:${businessSystemId}},
				   		//${businessSystemId}},
				   		success: function (data) {
				        	$.each(data, function(i, item) {
				        		var str="<li id="+item.menuId+"><a target=\"_blank\"  href=\"#\" onclick =\" getLeftMain("+ item.menuId +"); firstMemuClick('"+item.menuHref+"'); return false;\" >"+ item.menuName +"</a></li><div id=\"img-menu\"></div>";
				    			//var str="<li id="+item.menuId+"><a target=\"_blank\"  href=\"#\" onclick =\" getLeftMain("+ item.menuId +"); return false;\" >"+ item.menuName +"</a></li><div id=\"img-menu\"></div>";
				        		$("#navtopmenu").append(str);
				        		if(i==0){	
				        			var iframeSrc = item.menuHref;
				        			if(iframeSrc != null && iframeSrc !== ""){
				        				$("#helo").css("display","none");
				        				document.getElementById('iframe1').src=item.menuHref;
				        			};
				        			getLeftMain(item.menuId);
				        		}
				        	});
				   		},
				   		error: function (XMLHttpRequest, textStatus, errorThrown) {
				          	//alert(errorThrown);
				          	location.href="${contextPath}/login/login.jsp";
				   		}
				});
			})
			/** the first level memu click **/
			function firstMemuClick(href){
				if(href != null && href !== ''){
					document.getElementById('iframe1').src= href;
				}else{
					$('#helo').css('display','block');
					$('#iframe1').css('display','none');
				}
			};	
			/**
			 * Load the left navigation bar
			 * @param {Object} data
			 */
			function getLeftMain(parentId){
				$("#menu ul li").removeClass("backgroundUrl");
				$("#"+parentId).addClass("backgroundUrl");
				$("#menu ul li a").removeClass("firstMenuColor");
				$("#"+parentId+" a").addClass("firstMenuColor");
				
				var div = document.getElementById("sidemenu"); 
			  	    while(div.hasChildNodes()) 
			  	    {
			  	        div.removeChild(div.firstChild);
			  	    }
			  	  	$.ajax({
				   		type: "post",
				   		url: "index!showMenu.shtml",
				   		data: {sysPersonId:${cardNumber.id}, parentMenuId:parentId,businessSystemId:${businessSystemId}},
				       	dataType: "json",
				       	success: function (data) {
				       			if(data.flag == 1){
				       				location.href="${contextPath}/login/login.jsp";
				       			}else {
						        	$.each(data, function(i, item) {
						        		var str="";
						        		str="<li><div id="+ item.menuId +" class=\"menuhover\"><div class=\"icon\"><div id=\""+item.menuId+"icon01\"  class=\"icon01\" style=\"background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/baseMessage.png)\"></div><div class=\"text01\">" +
						        		"	<a   id=\""+item.menuId+"a\"target=\"_blank\" href=\"#\"  onclick=\"return false;\">" + item.menuName + "</a></div></div><div id=\""+item.menuId+"bg\" class=\"bg\"></div></div></li>"; 
						        		$( "#sidemenu" ).append(str);
						        		var blockDiv="";
						        		blockDiv="<div id=\""+item.menuId+"Z_SubList\"class=\"Z_SubList\" style=\"display: none; top: 100px; left: 162px;\">" +
						        		"</div>";
						        		$( "#content" ).append(blockDiv);
						        		getChildNav(item.menuId);
						        		$(function() {
						        		    /** second navigation hover **/
											$("#"+item.menuId).hover(function() {
												$("#"+item.menuId).css("background","url(\"../resource/${contextStyleTheme}/images/${contextStyleSpecial}/menubg.png\")");
												$("#"+item.menuId+"bg").css("background","url(\"../resource/${contextStyleTheme}/images/${contextStyleSpecial}/menu2_09_03.png\")");
												$("#"+item.menuId+"icon01").css("background","url(\"../resource/${contextStyleTheme}/images/${contextStyleSpecial}/baseMessage1.png\")");
												if("${contextStyleSpecial}" == "orange" && "${contextStyleTheme}" == "orange"){
													$("#"+item.menuId+"a").css("color","#f89c00");
													$("#"+item.menuId).css("border-color","#f89c00");
												}else if("${contextStyleSpecial}" == "supermusic" && "${contextStyleTheme}" == "orange"){
													$("#"+item.menuId+"a").css("color","#f89c00");
													$("#"+item.menuId).css("border-color","#f89c00");
												}else if("${contextStyleSpecial}" == "tel" && "${contextStyleTheme}" == "blue"){
													$("#"+item.menuId+"a").css("color","#3da4ea");
													$("#"+item.menuId).css("border-color","#3da4ea");
												}else{
													$("#"+item.menuId+"a").css("color","#fff");
												}
												$("#"+item.menuId).css("border-width","1px 0 1px 1px");
												$("#"+item.menuId).css("border-style","solid none solid solid");
												
												$("#"+item.menuId+"Z_SubList").css("top",i*90+100);
												$("#"+item.menuId+"Z_SubList").css("display","block");
											}, function() {
													$("#"+item.menuId+"icon01").css("background","url(\"../resource/${contextStyleTheme}/images/${contextStyleSpecial}/baseMessage.png\")");
													$("#"+item.menuId+"a").css("color","#000");
													
													if("${contextStyleSpecial}" == "orange" && "${contextStyleTheme}" == "orange"){
														$("#"+item.menuId).css("background","#feffe2");
													    $("#"+item.menuId+"bg").css("background","#feffe2");
													}else if("${contextStyleSpecial}" == "supermusic" && "${contextStyleTheme}" == "orange"){
														$("#"+item.menuId).css("background","#feffe2");
													    $("#"+item.menuId+"bg").css("background","#feffe2");
													}else if("${contextStyleSpecial}" == "tel" && "${contextStyleTheme}" == "blue"){
														$("#"+item.menuId).css("background","#f4f8fd");
													    $("#"+item.menuId+"bg").css("background","#f4f8fd");
													}else{
														
													}
													
													$("#"+item.menuId).css("border-color","#ededed");
													$("#"+item.menuId).css("border-width","0 0 1px 0");
													$("#"+item.menuId).css("border-style","none none solid none");
													
													$("#"+item.menuId+"Z_SubList").css("display","none");
											});
											/** third navigation hover **/
											$("#"+item.menuId+"Z_SubList").hover(function() {
												$("#"+item.menuId+"Z_SubList").css("display","block");
												
												$("#"+item.menuId).css("background","url(\"../resource/${contextStyleTheme}/images/${contextStyleSpecial}/menubg.png\")");
												$("#"+item.menuId+"bg").css("background","url(\"../resource/${contextStyleTheme}/images/${contextStyleSpecial}/menu2_09_03.png\")");
												$("#"+item.menuId+"icon01").css("background","url(\"../resource/${contextStyleTheme}/images/${contextStyleSpecial}/baseMessage1.png\")");
												if("${contextStyleSpecial}" == "orange" && "${contextStyleTheme}" == "orange"){
													$("#"+item.menuId+"a").css("color","#f89c00");
													$("#"+item.menuId).css("border-color","#f89c00");
												}else if("${contextStyleSpecial}" == "supermusic" && "${contextStyleTheme}" == "orange"){
													$("#"+item.menuId+"a").css("color","#f89c00");
													$("#"+item.menuId).css("border-color","#f89c00");
												}else if("${contextStyleSpecial}" == "tel" && "${contextStyleTheme}" == "blue"){
													$("#"+item.menuId+"a").css("color","#3da4ea");
													$("#"+item.menuId).css("border-color","#3da4ea");
												}else{
													$("#"+item.menuId+"a").css("color","#fff");
												}
												
												$("#"+item.menuId).css("border-width","1px 0 1px 1px");
												$("#"+item.menuId).css("border-style","solid none solid solid");
											}, function() {
												$("#"+item.menuId+"Z_SubList").css("display","none");
												
												$("#"+item.menuId+"icon01").css("background","url(\"../resource/${contextStyleTheme}/images/${contextStyleSpecial}/baseMessage.png\")");
												$("#"+item.menuId+"a").css("color","#000");
												
												if("${contextStyleSpecial}" == "orange" && "${contextStyleTheme}" == "orange"){
													$("#"+item.menuId).css("background","#feffe2");
												    $("#"+item.menuId+"bg").css("background","#feffe2");
												}else if("${contextStyleSpecial}" == "supermusic" && "${contextStyleTheme}" == "orange"){
													$("#"+item.menuId).css("background","#feffe2");
												    $("#"+item.menuId+"bg").css("background","#feffe2");
												}else if("${contextStyleSpecial}" == "tel" && "${contextStyleTheme}" == "blue"){
													$("#"+item.menuId).css("background","#f4f8fd");
												    $("#"+item.menuId+"bg").css("background","#f4f8fd");
												}else{
													
												}
												
												$("#"+item.menuId).css("border-color","#ededed");
												$("#"+item.menuId).css("border-width","0 0 1px 0");
												$("#"+item.menuId).css("border-style","none none solid none");
											});
										});
						        		
						        	});
					        	};
				       	},
				       	error: function (XMLHttpRequest, textStatus, errorThrown) {
				              //alert(errorThrown);
				              location.href="${contextPath}/login/login.jsp";
				       	}
					});
			}
			
			/**
			 * Load the third level menu
			 * @param {Object} data
			 */
			function getChildNav(parentId){
				var div = document.getElementById(parentId+"Z_SubList");
				while(div.hasChildNodes()) 
			  	    {
			  	        div.removeChild(div.firstChild);
			  	    }
				$.ajax({
				   		type: "post",
				   		url: "index!showMenu.shtml",
						data: {sysPersonId:${cardNumber.id}, parentMenuId:parentId,businessSystemId:${businessSystemId}},
				       	dataType: "json",
				       	success: function (data) {
					        	$.each(data, function(i, item) {
					        		if(item.displayMode == 0) {
					        			var childDiv="<div class=\"subView\" style=\"display: block;\"><ul><li><a id="+parentId+"subView\" onclick=\" reClass()\"target=\"iframe1\" href="+ item.menuHref+"  >"+item.menuName+"</a></li></ul></div>";
					        		}else if(item.displayMode == 1) {
					        			//模式窗口:var childDiv="<div class=\"subView\" style=\"display: block;\"><ul><li><a id="+parentId+"subView\" href=\"#\" onclick=\"reClass(); window.showModalDialog('"+ item.menuHref+"','','dialogWidth=1000px;dialogHeight=500px;dialogLeft=130px;dialogTop=80px;center=yes')\" >"+item.menuName+"</a></li></ul></div>";
					        			//var childDiv="<div class=\"subView\" style=\"display: block;\"><ul><li><a id="+parentId+"subView\" href=\"#\" onclick=\"reClass(); window.open('"+ item.menuHref+"','newwindow','height=500,width=1000,top=60,left=60,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no')\" >"+item.menuName+"</a></li></ul></div>";
					        			var childDiv="<div class=\"subView\" style=\"display: block;\"><ul><li><a id="+parentId+"subView\" href=\"#\" onclick=\"reClass(); window.open('"+ item.menuHref+"','newwindow')\" >"+item.menuName+"</a></li></ul></div>";
					        		}
					        		$("#"+parentId+"Z_SubList").append(childDiv);
					        	});
				       	},
				       	error: function (XMLHttpRequest, textStatus, errorThrown) {
				              alert(errorThrown);
				              //location.href="${contextPath}/login/login.jsp";
				       	}
					});
			}
			
			
			function SetWinHeight(){
							var win=$("#iframe1");
							if (document.getElementById){
								if (win && !window.opera){
									if (win.contentDocument && win.contentDocument.body.offsetHeight){
										win.height = win.contentDocument.body.offsetHeight;
									}else if(win.Document && win.Document.body.scrollHeight){
										win.height = win.Document.body.scrollHeight;
									}
								}
							}
			} 
			
			function reClass(){
				$("#helo").css("display","none");
				$("#iframe1").css("display","block");
			}
			/** System Swithing **/
			function switchSystem(){
				var systemId = $("#businessSystem").val();
				location.href = "${contextPath}/main/main.jsp?businessSystemId="+systemId;
			}
		 	
		</script>

	</head>

	<body onresize="document.getElementById('iframe1').style.width=document.body.offsetWidth- 166 + 'px'">
		<div id="head" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/topmenubg_01.png)">
			<div id="logo" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/logo_03${localeName}.png);"></div>
			<div id="login">
				<s:text name="eaap.op2.sso.main.welcome"/>，${cardNumber.userId}！ 
				<s:text name="eaap.op2.sso.main.department"/>：${cardNumber.deptName}
			</div>
	   <!--  
	 		<div id="systemSwith" >
				<select id="businessSystem" onchange="switchSystem()">
				</select>
			</div>   
		-->	
			<div id="login2" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/login_07.png)">
				<ul>
					<li>hen
							<a target="_parent" href="${contextPath}/main/index!logOut.shtml?userName=${cardNumber.userId}"><s:text name="eaap.op2.sso.main.quit"/></a>
					</li>
				</ul>
			</div>
		</div>
		<div id="topmenu" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/menubg_03.png)">
<!--			<div id="xiaosanjiao" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/xiaosanjiao_03.png)"></div>-->
			<div id="menu">
				<ul id="navtopmenu">
				</ul>
			</div>
<!--			<div id="xiaosanjiao02" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/xiaosanjiao01_03.png)"></div>-->
		</div>
		<div id="content">
			<div id="sidemenu">
			</div>
			<div id="righthtml">
				<div id="helo">
					<div id="helo01" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/helo_03${localeName}.png);"></div>
					<div id="helo02" style="background-image: url(${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/logo.png)"></div>
				</div>
			</div>
		</div>
		<iframe id="iframe1" name="iframe1"  frameborder=0 scrolling="no" style="width: 757px;"></iframe>
	</body>
</html>
