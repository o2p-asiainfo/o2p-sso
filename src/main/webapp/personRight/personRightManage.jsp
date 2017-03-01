<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title><s:text name="eaap.op2.sso.personRight.personRightManage"/></title>
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
		   
		//通过获得form表单里面所有的值到后台进行条件搜索
		function searchFunc() {
		    var form = $("#myForm").form();
	        $('#mygrid').datagrid("load", sy.serializeObject(form));
        }	
        function addMehtod(){
        	var sysIdType = ${sysIdType};
        	if(sysIdType == 3){
        		alert("<s:text name='eaap.op2.sso.personRight.personNoPrivilege'/>");
        	}else{
        		window.location.href="${contextPath}/sysPersonRight/showAddSysPersonRight.shtml";
        	}
     	}
      	function updateMethod(){
       		var rows = $('#mygrid').datagrid('getSelections');
         	if(rows.length==0){
	            alert("<s:text name='eaap.op2.sso.personRight.selectNotNull'/>");
		        return false; 
            } 
        	var sysRightId = rows[0].SYS_RIGHT_ID;
       		window.location.href="${contextPath}/sysPersonRight/showUpdateSysPersonRight.shtml?sysRightId="+sysRightId; 
      	}  
      	function deleteMethod(){
        	var rows = $('#mygrid').datagrid('getSelections');
            if(rows.length==0){
	             alert("<s:text name='eaap.op2.sso.personRight.selectNotNull'/>");
	             return false; 
            } 
       		var sysRightId = rows[0].SYS_RIGHT_ID;
       		var t = window.confirm("<s:text name='eaap.op2.sso.personRight.deleteRecord'/>");
       		if(t == true){
       			$.ajax({
		            type:"post",
		            async:false,
		            url:"${contextPath}/sysPersonRight/deleteSysPersonRight.shtml",
		            data:{sysRightId:sysRightId},
		            success:function(msg){
			            /**if(msg=="failure"){
			            	alert("<s:property value="%{getText('eaap.op2.portal.pardProd.prodOfferunique')}" />");
			            }**/
			            $('#mygrid').datagrid('reload');
		       		}
        		});
       		}
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
		    	  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.personRight.personRightManage"/>
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
			  			<td class="item"><s:text name="eaap.op2.sso.personRight.cardnumber"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="cardNumber" id="cardNumber" list="personList"  emptyOption="true" headerValue=" "	 listKey="sysPersonId" listValue="cardNumber" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
			   			<td style="text-align: right;padding-right:30px; ">
			   				<ui:button  skin="${contextStyleTheme}" text="%{getText('eaap.op2.sso.common.query')}" id="query" onclick="searchFunc();"/>
			    		</td>
	   				</tr>
	    	    </table>
	           </form>
	    </div>
	    
	    <script type="text/javascript">
		   var title = [];
		   title[0]='<s:text name="eaap.op2.sso.personRight.cardnumber"/>';
		   title[1]='<s:text name="eaap.op2.sso.personRight.name"/>';
		   title[2]='<s:text name="eaap.op2.sso.personRight.role"/>';
		   title[3]='<s:text name="eaap.op2.sso.personRight.function"/>';
		   title[4]='<s:text name="eaap.op2.sso.personRight.authCardnumber"/>';
		   title[5]='<s:text name="eaap.op2.sso.personRight.roleType"/>';
		   title[6]='<s:text name="eaap.op2.sso.personRight.authTime"/>';
		   title[7]='<s:text name="eaap.op2.sso.personRight.status"/>';
		   title[8]='权限明细编号';
		   title[9]='员工编号';
		   title[10]='角色编号';
		   title[11]='功能点编号';
		   title[12]='授权员工编号';
		   title[13]='角色类型编号';
		   title[14]='状态编号';
        </script>
		<ui:gridEasy  id="mygrid"  columns="cols" iconCls="icon-save" sortName="code" skin="${contextStyleTheme}" pageInfo="true" singleSelect="true"  sortOrder="desc"
			fit="true" collapsible="false"   title="" striped="true" pageList="10" pagination="true" frozenColumns="true" rownumbers="true" toolbar="true" method="eaap-op2-sso-sysPersonRightAction.showGrid">
			<ui:gridEasyColumn width="250" index="0" title="0" field="CARD_NUMBER" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="1" title="1" field="NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="2" title="2" field="SYS_ROLE_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="3" title="3" field="FUNCTION_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="4" title="4" field="AUTH_CARD_NUMBER" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="5" title="5" field="SYS_ROLE_TYPE_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="6" title="6" field="AUTH_TIME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="7" title="7" field="SYS_STATUS_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="8" title="8" field="SYS_RIGHT_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="9" title="9" field="SYS_PERSON_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="10" title="10" field="SYS_ROLE_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="11" title="11" field="FUNCTION_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="12" title="12" field="AUTH_PERSON_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="13" title="13" field="SYS_ROLE_TYPE_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="14" title="14" field="SYS_STATUS_ID" hidden="true" align="center"></ui:gridEasyColumn>
		</ui:gridEasy>
	   
	  </div>
	  
	  <%@include  file="../common.jsp" %>
</body>	  
         
</html>	