<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title><s:text name="eaap.op2.sso.personRight.addNewPersonRight"/></title>
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
			var sysPersonId = $("#cardNumber").val();
			var roleRightTypeId = $("#roleRightType").val();
			var functionRightTypeId = $("#functionRightType").val();
			var roleIds = $("#roleName").val();
			var functionIds = $("#functionName").val();
			if(sysPersonId == ""){
				alert("<s:text name='eaap.op2.sso.personRight.cardnumberNotNull'/>");
			}else if((roleIds=="" || roleIds==null) && (functionIds==""||functionIds==null)){
				alert("<s:text name='eaap.op2.sso.personRight.roleAndFunctionNotNull'/>");
			}else if((roleIds!="" && roleIds!=null )&& roleRightTypeId==""){
				alert("<s:text name='eaap.op2.sso.personRight.roleTypeNotNull'/>");
			}else if((functionIds!="" && functionIds!=null) && functionRightTypeId ==""){
				alert("<s:text name='eaap.op2.sso.personRight.functionTypeNotNull'/>");
			}else{
				$("#myForm").submit();
			}
		}
		
		function getChangeData(value){
			var paramsStr = "sysPersonId:" + value;
			var roleUrl = "${contextPath}/rainbowui/getMulSelectBoxData.shtml?t=&method=eaap-op2-sso-sysPersonRightAction.queryNotSelectedRole&params="+paramsStr+"&listKey=SYS_ROLE_ID&listValue=SYS_ROLE_NAME";
			var functionUrl = "${contextPath}/rainbowui/getMulSelectBoxData.shtml?t=&method=eaap-op2-sso-sysPersonRightAction.queryNotSelectedFunction&params="+paramsStr+"&listKey=FUNCTION_ID&listValue=FUNCTION_NAME";
			$("#roleName").multiselect({url: roleUrl}).multiselect("refresh");
			$("#functionName").multiselect({url: functionUrl}).multiselect("refresh");
			
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
		    	  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.personRight.addNewPersonRight"/>
<!--		    	  <s:property value="%{getText('eaap.op2.conf.server.manager.choice')}" />-->
	          </div>
	      </div>
		  <div class="accordion-header" >
		      <div class="accordion-header-text">
		      	  <span><span class="accordion-header-icon" > &nbsp;&nbsp;&nbsp;&nbsp;</span><s:text name="eaap.op2.sso.personRight.personRightInfo"/></span>  <!--<s:property value="%{getText('eaap.op2.conf.techimpl.queryArea')}" />-->
		      </div>
	      </div>
	      <div>
	      	   <form name="myForm" id="myForm" method="post" action="${contextPath}/sysPersonRight/addSysPersonRight.shtml">
	 		   	<table class="mgr-table">
					<tr>
						<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.personRight.cardnumber"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="cardNumber" id="cardNumber" list="personList"  emptyOption="true" headerValue=" "	 listKey="sysPersonId" listValue="cardNumber" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
			   			<td class="item"><s:text name="eaap.op2.sso.personRight.role"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			   			        <ui:multiSelectBox id="roleName" name="roleName" params="" width="225" layerWidth="217" loadMode="true" list=""  
			  				     method="eaap-op2-sso-sysPersonRightAction.queryNotSelectedRole" listKey="SYS_ROLE_ID" listValue="SYS_ROLE_NAME">
			  				    </ui:multiSelectBox>
			  				</div>  	
			   			</td>
			   			<td class="item"><s:text name="eaap.op2.sso.personRight.roleType"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="roleRightType" id="roleRightType" list="roleRightTypeList"  emptyOption="true" headerValue=" "	 listKey="roleRightTypeId" listValue="roleRightTypeName" ></ui:select>
			  				</div>  	
			   			</td>
			   		</tr>
	   				<tr>	
	   					<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.personRight.status"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="state" id="state" list="stateList"  headerValue=" "	 listKey="stateId" listValue="stateName" ></ui:select>
			  				</div>  	
			   			</td>
	   					<td class="item"><s:text name="eaap.op2.sso.personRight.function"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			   			        <ui:multiSelectBox id="functionName" name="functionName" params="" width="225" layerWidth="217" loadMode="true" list=""  
			  				     method="eaap-op2-sso-sysPersonRightAction.queryNotSelectedFunction" listKey="FUNCTION_ID" listValue="FUNCTION_NAME">
			  				    </ui:multiSelectBox>
			  				</div>  	
			   			</td>
			   			<td class="item"><s:text name="eaap.op2.sso.personRight.functionType"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="functionRightType" id="functionRightType" list="functionRightTypeList"  emptyOption="true" headerValue=" "	 listKey="functionRightTypeId" listValue="functionRightTypeName" ></ui:select>
			  				</div>  	
			   			</td>
	   				</tr>
	    	    </table>
	    	    <table class="mgr-table">
    		    	<tr>
    		       		<td colspan="6" class="item-content" align="center">
	    					<ui:button skin="${contextStyleTheme}"  text="%{getText('eaap.op2.sso.common.submit')}" onclick="formSubmit();"  id="submitId"></ui:button>
	    				    <ui:button skin="${contextStyleTheme}"  text="%{getText('eaap.op2.sso.common.cancel')}"  onclick="location='${contextPath}/sysPersonRight/showSysPersonRight.shtml'"  id="submitId"></ui:button>        
    					</td>
    			 	</tr>
    		 	</table>
	           </form>
	    </div>
	    
	   
	  </div>
	  
	  <%@include  file="../common.jsp" %>
</body>	  
         
</html>	