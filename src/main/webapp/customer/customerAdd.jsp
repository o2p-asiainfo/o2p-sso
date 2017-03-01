<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title><s:text name="eaap.op2.sso.customer.addNewCustomer"/></title>
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
			var sysCustomerName1 = $("#sysCustomerName").val();
			var sysCustomerName = $.trim(sysCustomerName1);
			var sysCustomerNameReg = /^.{1,50}$/;
			if(sysCustomerNameReg.test(sysCustomerName)){
				$.ajax({
					type: "post",
					url: "${contextPath}/sysCustomer/isCustomerExist.shtml",
					data: {sysCustomerName : sysCustomerName},
					dataType: "json",
					success: function(data){
						if(data.result == 1)
							alert("<s:text name='eaap.op2.sso.customer.customerAlreadyExist'/>");
						if(data.result == 0){
							$("#myForm").submit();
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown);
					}
				});
			}else{
				alert("<s:text name='eaap.op2.sso.customer.customerNameOverlong'/>");
			}
		}
		
		function getChangeData(value){
			
	 	}
    </script>
    
</head>
 <body>
	   <div class="contentWarp">
		  <!--<a class="button-base button-small" onclick="openWindows('http://www.baidu.com','Choose Org')" >111</a>		   -->
		  <div class="module-path">
	  		  <div class="module-path-content">
	  		  	  <img src="${contextPath}/resource/${contextStyleTheme}/images/search.png" /><s:text name="eaap.op2.sso.common.sysName"/>
	  			  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.common.sysBaseInfoManage"/>
		    	  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.customer.addNewCustomer"/>
<!--		    	  <s:property value="%{getText('eaap.op2.conf.server.manager.choice')}" />-->
	          </div>
	      </div>
		  <div class="accordion-header" >
		      <div class="accordion-header-text">
		      	  <span><span class="accordion-header-icon" > &nbsp;&nbsp;&nbsp;&nbsp;</span><s:text name="eaap.op2.sso.customer.customerInfo"/></span>  <!--<s:property value="%{getText('eaap.op2.conf.techimpl.queryArea')}" />-->
		      </div>
	      </div>
	      <div>
	      	   <form name="myForm" id="myForm" method="post" action="${contextPath}/sysCustomer/addSysCustomer.shtml">
	 		   	<table class="mgr-table">
					<tr>
	  					<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.customer.customerName"/>:</td>
	   					<td class="item-content">
	   						<div class="ui-widget">
	  							<ui:inputText skin="${contextStyleTheme}" name="sysCustomerName" id="sysCustomerName" ></ui:inputText>
	  						</div>  	
	   					</td>
	   					<td class="item"><s:text name="eaap.op2.sso.customer.area"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysAreaName" id="sysAreaName" list="areaList"  emptyOption="true" headerValue=" "	 listKey="sysAreaId" listValue="sysAreaName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
			  			<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.customer.status"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="state" id="state" list="stateList"   headerValue=" "	 listKey="stateId" listValue="stateName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
	   				</tr>
	    	    </table>
	    	    <table class="mgr-table">
    		    	<tr>
    		       		<td colspan="6" class="item-content" align="center">
	    					<ui:button skin="${contextStyleTheme}"  text="%{getText('eaap.op2.sso.common.submit')}" onclick="formSubmit()"  id="submitId"></ui:button>
	    				    <ui:button skin="${contextStyleTheme}"  text="%{getText('eaap.op2.sso.common.cancel')}"  onclick="location='${contextPath}/sysCustomer/showSysCustomer.shtml'"  id="submitId"></ui:button>        
    					</td>
    			 	</tr>
    		 	</table>
	           </form>
	    </div>
	    
	   
	  </div>
	  
	  <%@include  file="../common.jsp" %>
</body>	  
         
</html>	