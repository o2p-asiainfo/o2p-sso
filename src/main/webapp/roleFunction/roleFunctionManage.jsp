<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title><s:text name="eaap.op2.sso.roleFunction.roleFunctionManage"/></title>
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
        	window.location.href="${contextPath}/sysRoleFunction/showAddSysRoleFunction.shtml"; 
     	}
      	/**function updateMethod(){
       		var rows = $('#mygrid').datagrid('getSelections');
         	if(rows.length==0){
	            alert("请选择一条记录");
		        return false; 
            } 
        	var sysPersonId = rows[0].SYS_PERSON_ID;
       		window.location.href="${contextPath}/sysPerson/showUpdateSysPerson.shtml?sysPersonId="+sysPersonId; 
      	}  **/
      	function deleteMethod(){
        	var rows = $('#mygrid').datagrid('getSelections');
            if(rows.length==0){
	             alert("<s:text name='eaap.op2.sso.roleFunction.selectNotNull'/>");
	             return false; 
            } 
       		var sysRoleFuncId = rows[0].SYS_ROLE_FUNC_ID;
       		var t = window.confirm("<s:text name='eaap.op2.sso.roleFunction.deleteRecord'/>");
       		if(t == true){
       			$.ajax({
		            type:"post",
		            async:false,
		            url:"${contextPath}/sysRoleFunction/deleteSysRoleFunction.shtml",
		            data:{sysRoleFuncId:sysRoleFuncId},
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
		    	  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.roleFunction.roleFunctionManage"/>
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
			  			<td class="item"><s:text name="eaap.op2.sso.roleFunction.role"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysRoleName" id="sysRoleName" list="sysRoleList"  emptyOption="true" headerValue=" "	 listKey="sysRoleId" listValue="sysRoleName" onchange="getChangeData(this.value);"></ui:select>
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
		   title[0]='<s:text name="eaap.op2.sso.roleFunction.role"/>';
		   title[1]='<s:text name="eaap.op2.sso.roleFunction.function"/>';
		   title[2]='角色编号';
		   title[3]='功能点编号';
		   title[4]='角色权限编号';
        </script>
		<ui:gridEasy  id="mygrid"  columns="cols" iconCls="icon-save" sortName="code" skin="${contextStyleTheme}" pageInfo="true" singleSelect="true"  sortOrder="desc"
			fit="true" collapsible="false"   title="" striped="true" pageList="10" pagination="true" frozenColumns="true" rownumbers="true" toolbar="true" method="eaap-op2-sso-sysRoleFunctionAction.showGrid">
			<ui:gridEasyColumn width="250" index="0" title="0" field="SYS_ROLE_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="1" title="1" field="FUNCTION_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="2" title="2" field="ROLE_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="3" title="3" field="FUNCTION_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="4" title="4" field="SYS_ROLE_FUNC_ID" hidden="true" align="center"></ui:gridEasyColumn>
		</ui:gridEasy>
	   
	  </div>
	  
	  <%@include  file="../common.jsp" %>
</body>	  
         
</html>	