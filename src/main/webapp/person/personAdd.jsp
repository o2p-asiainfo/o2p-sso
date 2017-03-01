<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title><s:text name="eaap.op2.sso.person.addNewPerson"/></title>
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
			var cardNumber1 = $("#cardNumber").val();
			var name1 = $("#name").val();
			var mobile1 = $("#mobile").val();
			var email1 = $("#email").val();
			var cardNumber = $.trim(cardNumber1);
			var name = $.trim(name1);
			var mobile = $.trim(mobile1);
			var email = $.trim(email1);
			
			var cardNumberReg = /^[A-Za-z0-9]{1,30}$/;
			var nameReg = /^.{0,20}$/;
			var mobileReg = /^[0-9]{11}$/;
			var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			
			if(!cardNumberReg.test(cardNumber)){
				alert("<s:text name='eaap.op2.sso.person.cardNumberError'/>");
			}else if(!nameReg.test(name)){
				alert("<s:text name='eaap.op2.sso.person.nameError'/>");
			}else if(mobile != "" && !mobileReg.test(mobile)){
				alert("<s:text name='eaap.op2.sso.person.mobileError'/>");
			}else if(email!="" && !emailReg.test(email) && email.length>30){
				alert("<s:text name='eaap.op2.sso.person.emailError'/>");
			}else{
				$.ajax({
					type: "post",
					url: "${contextPath}/sysPerson/isPersonExist.shtml",
					data: {cardNumber : cardNumber},
					dataType: "json",
					success: function(data){
						if(data.result == 1)
							alert("<s:text name='eaap.op2.sso.person.personAlreadyExist'/>");
						if(data.result == 0){
							$("#myForm").submit();
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert(errorThrown);
					}
				});
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
		    	  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.person.addNewPerson"/>
<!--		    	  <s:property value="%{getText('eaap.op2.conf.server.manager.choice')}" />-->
	          </div>
	      </div>
		  <div class="accordion-header" >
		      <div class="accordion-header-text">
		      	  <span><span class="accordion-header-icon" > &nbsp;&nbsp;&nbsp;&nbsp;</span><s:text name="eaap.op2.sso.person.personInfo"/></span>  <!--<s:property value="%{getText('eaap.op2.conf.techimpl.queryArea')}" />-->
		      </div>
	      </div>
	      <div>
	      	   <form name="myForm" id="myForm" method="post" action="${contextPath}/sysPerson/addSysPerson.shtml">
	 		   	<table class="mgr-table">
					<tr>
	  					<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.person.cardNumber"/>:</td>
	   					<td class="item-content">
	   						<div class="ui-widget">
	  							<ui:inputText skin="${contextStyleTheme}" name="cardNumber" id="cardNumber" ></ui:inputText>
	  						</div>  	
	   					</td>
	   					<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.person.personName"/>:</td>
	   					<td class="item-content">
	   						<div class="ui-widget">
	  							<ui:inputText skin="${contextStyleTheme}" name="name" id="name" ></ui:inputText>
	  						</div>  	
	   					</td>
	   					<td class="item"><s:text name="eaap.op2.sso.person.mobile"/>:</td>
	   					<td class="item-content">
	   						<div class="ui-widget">
	  							<ui:inputText skin="${contextStyleTheme}" name="mobile" id="mobile" ></ui:inputText>
	  						</div>  	
	   					</td>
	   				</tr>
	   				<tr>
	   					<td class="item"><s:text name="eaap.op2.sso.person.email"/>:</td>
	   					<td class="item-content">
	   						<div class="ui-widget">
	  							<ui:inputText skin="${contextStyleTheme}" name="email" id="email" ></ui:inputText>
	  						</div>  	
	   					</td>
			   			<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.person.identity"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysIdTypeName" id="sysIdTypeName" list="sysIdTypeList"   headerValue=" "	 listKey="sysIdTypeId" listValue="sysIdTypeName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
			  			<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.person.userType"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysUserTypeName" id="sysUserTypeName" list="sysUserTypeList"   headerValue=" "	 listKey="sysUserTypeId" listValue="sysUserTypeName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
	   				</tr>
	   				<tr>
	   					<td class="item"><s:text name="eaap.op2.sso.person.cardType"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysCardTypeName" id="sysCardTypeName" list="sysCardTypeList"  emptyOption="true" headerValue=" "	 listKey="sysCardTypeId" listValue="sysCardTypeName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
	   					<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.person.department"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysDeptName" id="sysDeptName" list="deptList"   headerValue="--请选择--"	 listKey="deptId" listValue="sysDeptName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
			  			<td class="item"><s:text name="eaap.op2.sso.person.area"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysAreaName" id="sysAreaName" list="sysAreaList"  emptyOption="true" headerValue=" "	 listKey="sysAreaId" listValue="sysAreaName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
	   				</tr>
	   				<tr>
	   					<td class="item"><font color="red">*</font><s:text name="eaap.op2.sso.person.status"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="state" id="state" list="stateList"  headerValue=" "	 listKey="stateId" listValue="stateName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
			   			<td></td>
			   			<td></td>
			   			<td></td>
			   			<td></td>
	   				</tr>
	    	    </table>
	    	    <table class="mgr-table">
    		    	<tr>
    		       		<td colspan="6" class="item-content" align="center">
	    					<ui:button skin="${contextStyleTheme}"  text="%{getText('eaap.op2.sso.common.submit')}" onclick="formSubmit()"  id="submitId"></ui:button>
	    				    <ui:button skin="${contextStyleTheme}"  text="%{getText('eaap.op2.sso.common.cancel')}"  onclick="location='${contextPath}/sysPerson/showSysPerson.shtml'"  id="submitId"></ui:button>        
    					</td>
    			 	</tr>
    		 	</table>
	           </form>
	    </div>
	    
	   
	  </div>
	  
	  <%@include  file="../common.jsp" %>
</body>	  
         
</html>	