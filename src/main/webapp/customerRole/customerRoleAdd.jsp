<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title><s:text name="eaap.op2.sso.customerRole.addNewCustomerRole"/></title>
	<script type="text/javascript" src="${contextPath}/resource/comm/js/jquery-1.7.min.js"></script>
	
		<script type="text/javascript" src="${contextPath}/struts/simple/resource/plugins/airDialog/artDialog.js?skin=orange" ></script> 
		<script type="text/javascript" src="${contextPath}/struts/simple/resource/plugins/airDialog/iframeTools.js" ></script> 
		
<!--	<s:property value="tagUtil.writeScript('/struts/simple/resource/plugins/airDialog/artDialog.js?skin=orange')" escape="false"/> -->
<!--    <s:property value="tagUtil.writeScript('/struts/simple/resource/plugins/airDialog/iframeTools.js')" escape="false"/>-->
<!--	<script type="text/javascript" src="${contextPath}/resource/plugins/artDialog/artDialog.js?skin=blue" ></script> -->
<!--	<script type="text/javascript" src="${contextPath}/resource/plugins/artDialog/iframeTools.js" ></script> -->
	
	<style type="text/css">
	</style>
	
	<script type="text/javascript">
		/** form verify and submit **/
		function formSubmit(){
			var sysCustomerId = $("#sysCustomerName").val();
			var sysRoleIds = $("#roleName").multiselect("MyValues");
			if(sysCustomerId != "" && sysRoleIds !=""){
				$("#myForm").submit();
			}else{
				alert("<s:text name='eaap.op2.sso.customerRole.customerRoleNotNull'/>");
			}
		}
		
		function getChangeData(value){
			var paramsStr = "sysCustomerId:" + value;
			var url = "${contextPath}/rainbowui/getMulSelectBoxData.shtml?t=&method=eaap-op2-sso-sysCustomerRoleAction.queryNotSelectedRole&params="+paramsStr+"&listKey=SYS_ROLE_ID&listValue=SYS_ROLE_NAME";
			$("#roleName").multiselect({url: url}).multiselect("refresh");
			
			//$("#functionName").multiselect("refresh");
	 	}
    </script>
    
</head>
 <body>
	   <div class="contentWarp">
		  <!--<a class="button-base button-small" onclick="openWindows('http://www.baidu.com','Choose Org')" >111</a>		   -->
		  <div class="module-path">
	  		  <div class="module-path-content">
	  		  	  <img src="${contextPath}/resource/${contextStyleTheme}/images/search.png" /><s:text name="eaap.op2.sso.common.sysName"/>
	  			  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.common.sysAuthInfoManage"/>
		    	  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.customerRole.addNewCustomerRole"/>
<!--		    	  <s:property value="%{getText('eaap.op2.conf.server.manager.choice')}" />-->
	          </div>
	      </div>
		  <div class="accordion-header" >
		      <div class="accordion-header-text">
		      	  <span><span class="accordion-header-icon" > &nbsp;&nbsp;&nbsp;&nbsp;</span><s:text name="eaap.op2.sso.customerRole.customerRoleInfo"/></span>  <!--<s:property value="%{getText('eaap.op2.conf.techimpl.queryArea')}" />-->
		      </div>
	      </div>
	      <div>
	      	   <form name="myForm" id="myForm" method="post" action="${contextPath}/sysCustomerRole/addSysCustomerRole.shtml">
	 		   	<table class="mgr-table">
					<tr>
	  					<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.customerRole.customer"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysCustomerName" id="sysCustomerName" list="sysCustomerList"  emptyOption="true" headerValue=" "	 listKey="sysCustomerId" listValue="sysCustomerName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
	   					
	   					<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.customerRole.role"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			   			        <ui:multiSelectBox id="roleName" name="roleName" params="" width="225" layerWidth="217" loadMode="true" list=""  
			  				     method="eaap-op2-sso-sysCustomerRoleAction.queryNotSelectedRole" listKey="SYS_ROLE_ID" listValue="SYS_ROLE_NAME">
			  				    </ui:multiSelectBox>
			  				</div>  	
			   			</td>
	   				</tr>
	    	    </table>
	    	    <table class="mgr-table">
    		    	<tr>
    		       		<td colspan="6" class="item-content" align="center">
	    					<ui:button skin="${contextStyleTheme}"  text="%{getText('eaap.op2.sso.common.submit')}" onclick="formSubmit();"  id="submitId"></ui:button>
	    				    <ui:button skin="${contextStyleTheme}"  text="%{getText('eaap.op2.sso.common.cancel')}"  onclick="location='${contextPath}/sysCustomerRole/showSysCustomerRole.shtml'"  id="submitId"></ui:button>        
    					</td>
    			 	</tr>
    		 	</table>
	           </form>
	    </div>
	    
	   
	  </div>
	  
	  <%@include  file="../common.jsp" %>
</body>	  
         
</html>	