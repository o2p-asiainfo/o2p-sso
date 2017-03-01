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
<html lang="en" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="eaap.op2.sso.sysName"/></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<meta content="" name="description" />

<script type="text/javascript" src="${contextPath}/struts/simple/resource/plugins/airDialog/artDialog.js?skin=${contextStyleTheme}" ></script> 
<script type="text/javascript" src="${contextPath}/struts/simple/resource/plugins/airDialog/iframeTools.js" ></script> 
		
<link href="${contextPath}/resource/new/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/resource/new/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/resource/new/css/style.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/resource/new/css/style-responsive.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/resource/new/css/themes/orange.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="${contextPath}/resource/new/css/custom.css" rel="stylesheet" type="text/css" />
</head>
<style>
body{overflow-x:hidden;}
</style>
<body class="page-header-fixed s-indexBody page-sidebar-fixed">
<div class="header navbar navbar-fixed-top" id="header"> 
  <div class="header-inner"> 
    <div class="pull-left s-logo">
	    <img id="logo" src="${contextPath}/resource/${contextStyleTheme}/images/${contextStyleSpecial}/logo_03${localeName}.png"/>
	    <!-- <s:text name="eaap.op2.sso.sysName"/> -->
    </div> 
    <div class="pull-right s-user">
		<!-- img src="${contextPath}/resource/new/img/avatar1_small.jpg" alt="" width="29" -->
		<span class="s-userInfo">
			<s:text name="eaap.op2.sso.main.welcome"/> ${cardNumber.userId},  
			<s:text name="eaap.op2.sso.main.department"/>:${cardNumber.deptName}
		</span>
		<i class='fa fa-home s-exit'  style="margin-right:5;"><a target="mainFrame"  href="/conf/monitorNew/monitorViewCenter.jsp"><s:text name="eaap.op2.sso.main.home"/></a></i>
		<i class="glyphicon glyphicon-off  s-exit"  ><a target="_parent" href="${contextPath}/main/index!logOut.shtml?userName=${cardNumber.userId}"><s:text name="eaap.op2.sso.main.quit"/></a></i>
	</div>
  </div>
</div>


<div class="page-container"> 
  <div class="page-sidebar navbar-collapse collapse" id="sidebar"> 
    <ul id="sideMenu" data-slide-speed="200" data-auto-scroll="true" class="page-sidebar-menu">
      <li class="sidebar-toggler-wrapper s-mb-15"> 
        <div class="sidebar-toggler hidden-phone"> </div>
      </li>
    </ul>
  </div>
  <iframe src="/conf/monitorNew/monitorViewCenter.jsp" frameborder="0" class="s-mainFrame" id="mainFrame" name="mainFrame"></iframe>
</div>
<script src="${contextPath}/resource/new/plugins/jquery-1.10.2.min.js" type="text/javascript"></script> 
<script src="${contextPath}/resource/new/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script> 
<script src="${contextPath}/resource/new/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="${contextPath}/resource/new/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script> 
<script src="${contextPath}/resource/new/scripts/core/app-frame.js" type="text/javascript"></script> 
<script src="${contextPath}/resource/new/scripts/custom/frame.js" type="text/javascript"></script> 
<script>
jQuery(document).ready(function() {
    App.init(); 
    Frame.init();
});
</script> 
</body>
</html>
<script>
var businessSystemId=<%=businessSystemId%>;
var userId="${cardNumber.userId}";
var cardNumberId="${cardNumber.id}";

$(function(){
	if(businessSystemId==null || businessSystemId==""){
		businessSystemId=1;
	}
	if(userId==""){
		location.href="${contextPath}/login/login.jsp";
	}else{
		loadSideMenu();
	}
});

function loadSideMenu(){
  	$.ajax({
   		type: "post",
   		url: "index!showMenu.shtml",
   		data: {sysPersonId:cardNumberId,parentMenuId:1,businessSystemId:businessSystemId},
       	dataType: "json",
		async:false,
       	success: function (data) {
       			if(data.flag == 1){
       				location.href="${contextPath}/login/login.jsp";
       			}else {
		        	$.each(data, function(i, item){
		        		var str ="";
		        		if(i==0){
	       					str = "<li class='active' id='menu"+item.menuId+"' style='clear:both;'><a href='javascript:;'><i class='fa fa-home'></i><span class='title'>"+ item.menuName +"</span><span class='arrow open'></span></a>";
		        		}else{
	       					str = "<li class='open' id='menu"+item.menuId+"' style='clear:both;'><a href='javascript:;' style='background-color:#EEEEEE;'><i class='fa fa-home'></i><span class='title'>"+ item.menuName +"</span><span class='arrow'></span></a>";
		        		}
		        		str +=	getChildMenu(item.menuId,2);//2级菜单
		        		str +=	"</li>";
				  		$("#sideMenu").append(str);
		        	});
	        	};
				$("#sideMenu").append("<li class='last'> </li>");
       	},
       	error: function (XMLHttpRequest, textStatus, errorThrown) {
              location.href="${contextPath}/login/login.jsp";
       	}
	}); 	  	
}

function getChildMenu(parentId,level){
	var str ="";
  	$.ajax({
   		type: "post",
   		url: "index!showMenu.shtml",
   		data: {sysPersonId:cardNumberId,parentMenuId:parentId,businessSystemId:businessSystemId},
       	dataType: "json",
		async:false,
       	success: function (data) {
       			if(data.flag == 1){
       				location.href="${contextPath}/login/login.jsp";
       			}else {
       				str =		"<ul class='sub-menu'>";
		        	$.each(data, function(i, item) {
		        		if(level=="2"){
		        			//2级:
	       					str += "<li id='menu"+item.menuId+"' style='clear:both;'><a href='javascript:;' style='border-bottom:1px solid #E2E2E2;border-top:1px solid #E2E2E2;'><span class='title' style='padding-right:15px;'>"+ item.menuName +"</span><span class='arrow'></span></a>";
			        		str +=	getChildMenu(item.menuId,3);//3级菜单
		        		}else{
		        			//3级:
		        			if(item.displayMode == 1) {
		        				str += "<li id='menu"+item.menuId+"' style='clear:both;'><a  href='"+item.menuHref+"' target='_blank' ><span class='title'>"+ item.menuName +"</span></a>";
		        			}else{
		        		    	str += "<li id='menu"+item.menuId+"' style='clear:both;'><a  href='"+item.menuHref+"' target='mainFrame' ><span class='title'>"+ item.menuName +"</span></a>";
		        			}
		        		}
		        	});
	        		str +=	"</ul>";
	        	};
       	},
       	error: function (XMLHttpRequest, textStatus, errorThrown) {
              location.href="${contextPath}/login/login.jsp";
       	}
	}); 	  	
	return str;
}

 </script> 