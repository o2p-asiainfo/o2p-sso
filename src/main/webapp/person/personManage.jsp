<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title><s:text name="eaap.op2.sso.person.personManage"/></title>
	<script type="text/javascript" src="${contextPath}/resource/comm/js/jquery-1.7.min.js"></script>
	
		<script type="text/javascript" src="${contextPath}/struts/simple/resource/plugins/airDialog/artDialog.js?skin=orange" ></script> 
		<script type="text/javascript" src="${contextPath}/struts/simple/resource/plugins/airDialog/iframeTools.js" ></script> 
		
<!--	<s:property value="tagUtil.writeScript('/struts/simple/resource/plugins/airDialog/artDialog.js?skin=orange')" escape="false"/> -->
<!--    <s:property value="tagUtil.writeScript('/struts/simple/resource/plugins/airDialog/iframeTools.js')" escape="false"/>-->
<!--	<script type="text/javascript" src="${contextPath}/resource/plugins/artDialog/artDialog.js?skin=blue" ></script> -->
<!--	<script type="text/javascript" src="${contextPath}/resource/plugins/artDialog/iframeTools.js" ></script> -->
	
	<style type="text/css">
	</style>  
</head>
 <body>
	   <div class="contentWarp">
		  <!--<a class="button-base button-small" onclick="openWindows('http://www.baidu.com','Choose Org')" >111</a>		   -->
		  <div class="module-path">
	  		  <div class="module-path-content">
	  		  	  <img src="${contextPath}/resource/${contextStyleTheme}/images/search.png" /><s:text name="eaap.op2.sso.common.sysName"/>
	  			  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.common.sysBaseInfoManage"/>
		    	  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.person.personManage"/>
<!--		    	  <s:property value="%{getText('eaap.op2.conf.server.manager.choice')}" />-->
	          </div>
	      </div>
		  <div class="accordion-header" >
		      <div class="accordion-header-text">
		      	  <span><span class="accordion-header-icon" > &nbsp;&nbsp;&nbsp;&nbsp;</span><s:text name="eaap.op2.sso.common.queryCondition"/></span>  <!--<s:property value="%{getText('eaap.op2.conf.techimpl.queryArea')}" />-->
		      </div>
	      </div>
	      <div>
	      	   <form name="myForm" id="myForm" method="post">
	 		   	<table class="mgr-table">
					<tr>
	  					<td class="item"><s:text name="eaap.op2.sso.person.cardNumber"/>:</td>
	   					<td class="item-content">
	   						<div class="ui-widget">
	  							<ui:inputText skin="${contextStyleTheme}" name="cardNumber" id="cardNumber" ></ui:inputText>
	  						</div>  	
	   					</td>
	   					<td class="item"><s:text name="eaap.op2.sso.person.personName"/>:</td>
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
	   					<td class="item"><s:text name="eaap.op2.sso.person.department"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysDeptName" id="sysDeptName" list="deptList"  emptyOption="true" headerValue=" "	 listKey="deptId" listValue="sysDeptName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
			   			<td class="item"><s:text name="eaap.op2.sso.person.identity"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysIdTypeName" id="sysIdTypeName" list="sysIdTypeList"  emptyOption="true" headerValue=" "	 listKey="sysIdTypeId" listValue="sysIdTypeName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
			  			<td class="item"><s:text name="eaap.op2.sso.person.userType"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysUserTypeName" id="sysUserTypeName" list="sysUserTypeList"  emptyOption="true" headerValue=" "	 listKey="sysUserTypeId" listValue="sysUserTypeName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
	   				</tr>
	   				<tr>
	   					<td class="item"><s:text name="eaap.op2.sso.person.status"/>:</td>
			   			<td class="item-content" colspan=5>
			   				<div class="ui-widget"  style="float:left;">
			  				    <ui:select skin="${contextStyleTheme}" name="state" id="state" list="stateList"  emptyOption="true" headerValue=" "	 listKey="stateId" listValue="stateName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   				<div style="float:right;">
			  					<ui:button  skin="${contextStyleTheme}" text="%{getText('eaap.op2.sso.common.query')}" id="query" onclick="searchFunc();"/>
			  				</div>  	
			   			</td>
	   				</tr>
	    	    </table>
	           </form>
	    </div>
	    
	    <script type="text/javascript">
		   var title = [];
		   title[0]='<s:text name="eaap.op2.sso.person.cardNumber"/>';
		   title[1]='<s:text name="eaap.op2.sso.person.personName"/>';
		   title[2]='<s:text name="eaap.op2.sso.person.mobile"/>';
		   title[3]='<s:text name="eaap.op2.sso.person.email"/>';
		   title[4]='<s:text name="eaap.op2.sso.person.department"/>';
		   title[5]='<s:text name="eaap.op2.sso.person.identity"/>';
		   title[6]='<s:text name="eaap.op2.sso.person.userType"/>';
		   title[7]='<s:text name="eaap.op2.sso.person.status"/>';
		   title[8]='<s:text name="eaap.op2.sso.person.pwdLastUpTime"/>';
		   title[9]='<s:text name="eaap.op2.sso.person.pwdEffTime"/>';
		   title[10]='<s:text name="eaap.op2.sso.person.createTime"/>';
		   title[11]='员工ID';
		   title[12]='部门ID';
		   title[13]='身份ID';
		   title[14]='状态ID';
        </script>
		<ui:gridEasy  id="mygrid"  columns="cols" iconCls="icon-save" sortName="code" skin="${contextStyleTheme}" pageInfo="true" singleSelect="true"  sortOrder="desc"
			fit="true" collapsible="false"   title="" striped="true" pageList="10" pagination="true" frozenColumns="true" rownumbers="true" toolbar="true" 
			method="eaap-op2-sso-sysPersonAction.showGrid">
			<ui:gridEasyColumn width="120" index="0" title="0" field="CARD_NUMBER" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="1" title="1" field="NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="2" title="2" field="MOBILE" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="3" title="3" field="EMAIL" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="4" title="4" field="DEPT_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="5" title="5" field="SYS_ID_TYPE_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="110" index="6" title="6" field="SYS_USER_TYPE" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="80" index="7" title="7" field="SYS_STATUS_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="8" title="8" field="PWD_LAST_UP_TIME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="9" title="9" field="PWD_EFF_TIME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="10" title="10" field="CREATE_DATE" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="11" title="11" field="SYS_PERSON_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="12" title="12" field="DEPT_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="13" title="13" field="SYS_ID_TYPE_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="120" index="14" title="14" field="SYS_STATUS_ID" hidden="true" align="center"></ui:gridEasyColumn>
		</ui:gridEasy>
	   
	  </div>
	  
	  <%@include  file="../common.jsp" %>
</body>
</html>
	
	<script type="text/javascript">
		   
		//通过获得form表单里面所有的值到后台进行条件搜索
		function searchFunc() {
		    var form = $("#myForm").form();
	        $('#mygrid').datagrid("load", sy.serializeObject(form));
        }	
        function addMehtod(){
        	window.location.href="${contextPath}/sysPerson/showAddSysPerson.shtml"; 
     	}
      	function updateMethod(){
       		var rows = $('#mygrid').datagrid('getSelections');
         	if(rows.length==0){
	            alert("<s:text name='eaap.op2.sso.person.selectNotNull'/>");
		        return false; 
            } 
        	var sysPersonId = rows[0].SYS_PERSON_ID;
       		window.location.href="${contextPath}/sysPerson/showUpdateSysPerson.shtml?sysPersonId="+sysPersonId; 
      	}  
      	function deleteMethod(){
        	var rows = $('#mygrid').datagrid('getSelections');
            if(rows.length==0){
	             alert("<s:text name='eaap.op2.sso.person.selectNotNull'/>");
	             return false; 
            } 
       		var sysPersonId = rows[0].SYS_PERSON_ID;
       		var t = window.confirm("<s:text name='eaap.op2.sso.person.deleteRecord'/>");
       		if(t == true){
       			$.ajax({
		            type:"post",
		            async:false,
		            url:"${contextPath}/sysPerson/deleteSysPerson.shtml",
		            data:{sysPersonId:sysPersonId},
		            dataType: "json",
		            success:function(data){
		            	if(data.result == 1)
							alert("<s:text name='eaap.op2.sso.person.deleteFailure'/>");
						if(data.result == 0){
							$('#mygrid').datagrid('reload');
						}
			            /**if(msg=="failure"){
			            	alert("<s:property value="%{getText('eaap.op2.portal.pardProd.prodOfferunique')}" />");
			            }**/
		       		}
        		});
       		}
      	}
 
    </script>