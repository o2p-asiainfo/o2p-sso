<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils"%>
<%@ page import="com.ailk.eaap.op2.bo.Tenant"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	BaseAction baseAction = new BaseAction();
	String localeName = baseAction.getLocaleName();
	request.setAttribute("localeName", localeName);	
	
	String tenantLogo ="";
	String tenantCode ="";
	String tenantName ="";
	if(request.getSession().getAttribute("cardNumber") !=null){
		Tenant tenant = (Tenant) request.getSession().getAttribute("Tenant");
		tenantLogo = tenant.getLogo();
		tenantCode = tenant.getCode();
		tenantName = tenant.getName();
	}else{
		//out.println("<script>javascript:window.location.href=\"${contextPath}/"+tenant.getCode()+"/login.jsp\"</script>");
		out.println("<script>javascript:history.go(-1);</script>");
	}
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<head>
<meta charset="utf-8" />
<title>${APP_WEB_TITLE}</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="${contextPath}/resource/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/resource/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->

<!-- BEGIN THEME STYLES -->
<!-- <link href="${contextPath}/resource/css/style-template.css" rel="stylesheet" type="text/css" /> -->
<link href="${contextPath}/resource/css/style.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/resource/css/themes/orange.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="${contextPath}/resource/css/style-responsive.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/resource/css/frame.css" rel="stylesheet" type="text/css" />
<!-- END THEME STYLES -->

<link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->

<body class="page-header-fixed">
<div class="header navbar navbar-default navbar-static-top">
<div class="container">
<div class="navbar-header"> 
  <button class="navbar-toggle btn navbar-btn" data-toggle="collapse" data-target=".navbar-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
  <a class="navbar-brand logo-v1" href="index.jsp">
  		<img id="logo" src="${contextPath}/resource/tenant/<%=tenantLogo%>"  title="<%=tenantName%>"  height="50">
  </a> 
</div>

<!-- BEGIN TOP NAVIGATION MENU -->
<div class="navbar-collapse collapse">
<ul class="nav navbar-nav">
  <li class="s-login-link dropdown logined"> <span class="sep"></span> <a class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-delay="0" data-close-others="false" href="#"> ${cardNumber.userId} <i class="fa fa-sort-down"></i></a>
    <ul class="dropdown-menu s-user-box">
      <li><a  target="_parent" href="${contextPath}/main/logOut.shtml"><i class="fa fa-sign-out"></i>Sign out</a> </li>
    </ul>
  </li>
</ul>

<ul class="nav navbar-nav"  id="sideMenu">
	<!-- li class="dropdown">
		<a href="#"> Service <i class="fa fa-angle-down"></i></a>
		<ul class="dropdown-menu" aria-labelledby="mega-menu" style="width:1030px; margin-left:-430px;">
			<li>
			  <div class="nav-content">
					  <div class="nav-content-col nav-content-col2">
					    <h3>General Info</h3>
					    <ul style="height:260px;">
					      <li><a href="http://localhost:8080/conf/mgr/showOrgList.shtml" target="mainFrame"> Organization </a> </li>
					      <li><a href="http://localhost:8080/conf/component/showCompList.shtml" target="mainFrame"> Component </a> </li>
					      <li><a href="#"> Service Catalog </a> </li>
					      <li><a href="#"> Protocol Management </a> </li>
					      <li><a href="#"> Metedata Management </a> </li>
					      <li><a href="#"> Service Management </a> </li>
					      <li><a href="#"> WSDL Management </a> </li>
					      <li><a href="#"> Effective Management </a> </li>
					      <li><a href="#"> Message Flow </a> </li>
					      <li><a href="#"> Remote Call URL Code </a> </li>
					      <li><a href="#"> Data Source Configuration </a> </li>
					      <li><a href="#"> Message Flow Exception </a> </li>
					      <li><a href="#"> Refresh </a> </li>
					    </ul>
					  </div>
			    </div>
			</li>
		</ul>
	</li -->
</ul>
  
</div>
<!-- BEGIN TOP NAVIGATION MENU -->
</div>
</div>
<!-- END HEADER --> 

<!-- BEGIN PAGE CONTAINER --   /conf/monitorNew/monitorViewCenter.jsp    -->
<iframe src=""   frameborder="0" class="page-container" id="mainFrame" name="mainFrame" width="100%"></iframe>
<!-- END PAGE CONTAINER --> 

<!-- Load javascripts at bottom, this will reduce page load time --> 
<!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) --> 
<!--[if lt IE 9]>
    <script src="${contextPath}/resource/plugins/respond.min.js"></script>  
    <![endif]--> 
<script src="${contextPath}/resource/plugins/jquery-1.10.2.min.js" type="text/javascript"></script> 
<script src="${contextPath}/resource/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script> 
<script src="${contextPath}/resource/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<!-- END CORE PLUGINS --> 
<!-- BEGIN PAGE LEVEL JAVASCRIPTS(REQUIRED ONLY FOR CURRENT PAGE) --> 
<script src="${contextPath}/resource/scripts/frame.js"></script> 
<script type="text/javascript">
jQuery(document).ready(function() {
	Frame.init();
});
</script> 
<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
</html>

<script>
var businessSystemId=1;
var userId="${cardNumber.userId}";
var cardNumberId="${cardNumber.id}";


$(function(){
	if(businessSystemId==null || businessSystemId==""){
		businessSystemId=1;
	}
	if(userId==""){
		history.go(-1);
	}else{
		getHmePage();
		loadSideMenu();
	} 
});

function verifyUserTimer(){
	$.ajax({
   		type: "post",
   		url: "../main/verifyUser.shtml",
       	dataType: "json",
		async:false,
       	success: function (data) {
   			if(data.result == 1){
       			window.alert('<s:text name="eaap.op2.sso.main.ssoErrorOne"/>');
       			location.href="${contextPath}/login/login.jsp";
       		}else if(data.result == 2){
       			window.alert('<s:text name="eaap.op2.sso.main.ssoErrorTwo"/>');
       			location.href="${contextPath}/login/login.jsp";
       		}
       	}
	}); 
}

//加载起始页面
function getHmePage(){
  	$.ajax({
   		type: "post",
   		url: "../main/showMenu.shtml",
   		data: {sysPersonId:cardNumberId,parentMenuId:0,businessSystemId:businessSystemId},
       	dataType: "json",
		async:false,
       	success: function (data) {
       			if(data.flag == 1){
       				//location.href="${contextPath}/login/login.jsp";
       			}else {
		        	$.each(data, function(i, item){
		        		var homeURL = item.menuHref;
				  		$("#mainFrame").attr("src",homeURL);
		        	});
	        	};
       	},
       	error: function (XMLHttpRequest, textStatus, errorThrown) {
              //location.href="${contextPath}/login/login.jsp";
       	}
	}); 	  	
}


function loadSideMenu(){
  	$.ajax({
   		type: "post",
   		url: "../main/showMenu.shtml",
   		data: {sysPersonId:cardNumberId,parentMenuId:1,businessSystemId:businessSystemId},
       	dataType: "json",
		async:false,
       	success: function (data) {
       			if(data.flag == 1){
       				//location.href="${contextPath}/login/login.jsp";
       			}else {
		        	$.each(data, function(i, item){
		        		var ulWidth = 216;
		        		var subNum = parseInt(item.subNum);
		        		if(subNum >1 && subNum <=5){
		        			ulWidth = (subNum)*204 +12;
		        		}else if(subNum >5){
		        			ulWidth = 1032;
		        		}
		        		var marginLeft=0;
		        		if(i==0){
		        			marginLeft = -550;
		        		}else if(i==4){
		        			marginLeft = -450;
		        		}else{
		        			marginLeft = -120;
		        		}		        		
		        		var othCss= "";
		        		if((data.length - i)==1){
		        			othCss = "pull-right";		//最后一个主菜单右对齐
		        		}
		        		var str ="<li class=\"dropdown\">";
	       				str += "<a href=\"#\"> "+ item.menuName +" <i class=\"fa fa-angle-down\"></i></a>";
	       				str += "<ul class=\"dropdown-menu "+othCss+"\" style=\"width:"+ulWidth+"px; margin-left:"+marginLeft+"px;\">";
	       				str += "<li>";
	       				str += "<div class=\"nav-content\">";
		        		str +=	getChildMenu(item.menuId,2);//2级菜单
		        		str +=	"</div>";
					    str +=	"</li>";
						str +=	"</ul>";
		        		str +=	"</li>";
				  		$("#sideMenu").append(str);
		        	});
	        	};
       	},
       	error: function (XMLHttpRequest, textStatus, errorThrown) {
              //location.href="${contextPath}/login/login.jsp";
       	}
	}); 	  	
}

function getChildMenu(parentId,level){
	var str ="";
  	$.ajax({
   		type: "post",
   		url: "../main/showMenu.shtml",
   		data: {sysPersonId:cardNumberId,parentMenuId:parentId,businessSystemId:businessSystemId},
       	dataType: "json",
		async:false,
       	success: function (data) {
       			if(data.flag == 1){
       				//location.href="${contextPath}/login/login.jsp";
       			}else {
		        	$.each(data, function(i, item) {
		        		if(level=="2"){
		        			//2级:
		        			if(parseInt(item.subNum)>8){
			        			str += "<div class=\"nav-content-col nav-content-col2\">";
		        			}else{
			        			str += "<div class=\"nav-content-col\">";
		        			}
		        			str += "<h3 title=\""+ item.menuName +"\">"+ item.menuName +"</h3>";
		        			str += "<ul>";
			        		str +=	getChildMenu(item.menuId,3);//3级菜单
			        		str += "</ul>";
			        		str += "</div>";
		        		}else{
		        			//3级:
		        			if(item.displayMode == 1) {
		        				str += "<li title=\""+ item.menuName +"\"><a onclick=\"verifyUser('"+item.menuHref+"','"+item.displayMode+"')\" style= \"cursor:pointer;\" target=\"_blank\"> "+ item.menuName +" </a></li>";
		        			}else{
		        				str += "<li title=\""+ item.menuName +"\"><a onclick=\"verifyUser('"+item.menuHref+"','"+item.displayMode+"')\" style= \"cursor:pointer;\" target=\"mainFrame\"> "+ item.menuName +" </a></li>";
		        			}
		        		}
		        	});
	        	}
       	},
       	error: function (XMLHttpRequest, textStatus, errorThrown) {
              //location.href="${contextPath}/login/login.jsp";
       	}
	}); 	  	
	return str;
}

function verifyUser(url,displayMode){
	$.ajax({
   		type: "post",
   		url: "../main/verifyUser.shtml",
       	dataType: "json",
		async:false,
       	success: function (data) {
       		if(data.result == 0){
       			if(displayMode == 1){
       				window.open(url,'_blank');
       			}else{
       				$("#mainFrame").attr("src",url); 
       			}
       		}else if(data.result == 1){
       			window.alert('<s:text name="eaap.op2.sso.main.ssoErrorOne"/>');
       			location.href="${contextPath}/login/login.jsp";
       		}else if(data.result == 2){
       			window.alert('<s:text name="eaap.op2.sso.main.ssoErrorTwo"/>');
       			location.href="${contextPath}/login/login.jsp";
       		}
       	}
	}); 
}

 </script> 