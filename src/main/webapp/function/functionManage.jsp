<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ailk.eaap.op2.sso.framework.action.BaseAction"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title><s:text name="eaap.op2.sso.function.functionManage"/></title>
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
        	window.location.href="${contextPath}/sysFunction/showAddSysFunction.shtml"; 
     	}
      	function updateMethod(){
       		var rows = $('#mygrid').datagrid('getSelections');
         	if(rows.length==0){
	            alert("<s:text name='eaap.op2.sso.customer.selectNotNull'/>");
		        return false; 
            } 
        	var functionId = rows[0].functionId;
       		window.location.href="${contextPath}/sysFunction/showUpdateSysFunction.shtml?functionId="+functionId; 
      	}  
      	function deleteMethod(){
        	var rows = $('#mygrid').datagrid('getSelections');
            if(rows.length==0){
	             alert("<s:text name='eaap.op2.sso.customer.selectNotNull'/>");
	             return false; 
            } 
       		var functionId = rows[0].functionId;
       		var t = window.confirm("<s:text name='eaap.op2.sso.customer.deleteRecord'/>");
       		if(t == true){
       			$.ajax({
		            type:"post",
		            async:false,
		            url:"${contextPath}/sysFunction/deleteSysFunction.shtml",
		            data:{functionId:functionId},
		            dataType: "json",
		            success:function(data){
			            /**if(msg=="failure"){
			            	alert("<s:property value="%{getText('eaap.op2.portal.pardProd.prodOfferunique')}" />");
			            }**/
			            if(data.result == 1)
							alert("<s:text name='eaap.op2.sso.function.deleteFailure'/>");
						if(data.result == 0){
							$('#mygrid').datagrid('reload');
						}
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
	  			  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.common.sysBaseInfoManage"/>
		    	  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.function.functionManage"/>
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
	  					<td class="item"><s:text name="eaap.op2.sso.function.functionName"/>:</td>
	   					<td class="item-content">
	   						<div class="ui-widget">
	  							<ui:inputText skin="${contextStyleTheme}" name="sysFunctionName" id="sysFunctionName" ></ui:inputText>
	  						</div>  	
	   					</td>
	   					<td class="item"><s:text name="eaap.op2.sso.function.parentFunction"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="parentFunctionName" id="parentFunctionName" list="parentFunctionList"  emptyOption="true" headerValue=" "	 listKey="parentFunctionId" listValue="parentFunctionName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
			   	    </tr>
			   	    <tr>
	   					<td class="item"><s:text name="eaap.op2.sso.function.sysBusiness"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="sysBusinessName" id="sysBusinessName" list="sysBusinessList"  emptyOption="true" headerValue=" "	 listKey="sysBusinessId" listValue="sysBusinessName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
			  			<td class="item"><s:text name="eaap.op2.sso.function.status"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="state" id="state" list="stateList"  emptyOption="true" headerValue=" "	 listKey="stateId" listValue="stateName" onchange="getChangeData(this.value);"></ui:select>
			  				</div>  	
			   			</td>
	   				</tr>
	   				<tr>
			   			<td colspan=4 style="text-align: right;padding-right:30px; ">
			   				<ui:button  skin="${contextStyleTheme}" text="%{getText('eaap.op2.sso.common.query')}" id="query" onclick="searchFunc();"/>
			    		</td>
	   				</tr>
	    	    </table>
	           </form>
	    </div>
	    
	    <script type="text/javascript">
		   var title = [];
		   title[0]='<s:text name="eaap.op2.sso.function.functionName"/>';
		   title[1]='<s:text name="eaap.op2.sso.function.parentFunction"/>';
		   title[2]='<s:text name="eaap.op2.sso.function.url"/>';
		   title[3]='<s:text name="eaap.op2.sso.function.sysBusiness"/>';
		   title[4]='<s:text name="eaap.op2.sso.function.shouNum"/>';
		   title[5]='<s:text name="eaap.op2.sso.function.displayMode"/>';
		   title[6]='<s:text name="eaap.op2.sso.function.status"/>';
		   title[7]='功能点编号';
		   title[8]='父功能点编号';
		   title[9]='所属业务系统ID';
		   title[10]='状态ID';
        </script>
		<ui:gridEasy  id="mygrid"  columns="cols" iconCls="icon-save" sortName="code" skin="${contextStyleTheme}" pageInfo="true" singleSelect="true"  sortOrder="desc"
			fit="true" collapsible="false"   title="" striped="true" pageList="10" pagination="true" frozenColumns="true" rownumbers="true" toolbar="true" method="eaap-op2-sso-sysFunctionAction.showGrid">
			<ui:gridEasyColumn width="200" index="0" title="0" field="functionName" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="200" index="1" title="1" field="parentFunctionName" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="400" index="2" title="2" field="url" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="150" index="3" title="3" field="businessSystemName" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="100" index="4" title="4" field="shownum" hidden="false" align="center" ></ui:gridEasyColumn>
			<ui:gridEasyColumn width="150" index="5" title="5" field="displayMode" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="100" index="6" title="6" field="sysStatusName" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="200" index="7" title="7" field="functionId" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="200" index="8" title="8" field="parentFunctionId" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="200" index="9" title="9" field="businessSystemId" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="200" index="10" title="10" field="sysStatusId" hidden="true" align="center" ></ui:gridEasyColumn>
		</ui:gridEasy>
	   
	  </div>
	  
	  <%@include  file="../common.jsp" %>
</body>	  
         
</html>	