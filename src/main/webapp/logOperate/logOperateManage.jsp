<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title><s:text name="eaap.op2.sso.logOperate.logOperateManage"/></title>
	<script type="text/javascript" src="${contextPath}/resource/comm/js/jquery-1.7.min.js"></script>
	
	<style type="text/css">
		#itemm{
			width: 10%;
		}
		#itemm-content{
			width: 10%;
		}
	</style>
	
	<script type="text/javascript">
		   
		//通过获得form表单里面所有的值到后台进行条件搜索
		function searchFunc() {
			var minDate = $("#minDate").val();
			var maxDate = $("#maxDate").val();
			if(minDate!=null && minDate!="" && maxDate!=null && maxDate!="" ){
				if(minDate > maxDate){
					alert("<s:text name='eaap.op2.sso.logOperate.dateError'/>");
				}else{
					var form = $("#myForm").form();
	        		$('#mygrid').datagrid("load", sy.serializeObject(form));
				}
			}else{
				var form = $("#myForm").form();
	        	$('#mygrid').datagrid("load", sy.serializeObject(form));
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
	  			  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.common.sysLogManage"/>
		    	  <img src="${contextPath}/resource/${contextStyleTheme}/images/module-path.png" /><s:text name="eaap.op2.sso.logOperate.logOperateManage"/>
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
	  					<td class="item" id="itemm"><s:text name="eaap.op2.sso.logOperate.cardnumber"/>:</td>
			   			<td class="item-content" id="itemm-content">
			   				<div class="ui-widget">
			  				    <ui:select skin="${contextStyleTheme}" name="cardNumber" id="cardNumber" list="personList"  emptyOption="true" headerValue=" "	 listKey="sysPersonId" listValue="cardNumber" onchange=""></ui:select>
			  				</div>  	
			   			</td>
			   			
			  			<td class="item" id="itemm"><s:text name="eaap.op2.sso.logOperate.date"/>:</td>
			   			<td class="item-content">
			   				<div class="ui-widget">
			  				    <ui:date id="minDate" name="minDate" skin="${contextStyleTheme}" dateFmt="yyyy-MM-dd HH:mm:ss"></ui:date>
			  				    <s:text name="eaap.op2.sso.logOperate.to"/>  <ui:date id="maxDate" name="maxDate" skin="${contextStyleTheme}" dateFmt="yyyy-MM-dd HH:mm:ss"></ui:date>
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
		   title[0]='<s:text name="eaap.op2.sso.logOperate.cardnumber"/>';
		   title[1]='<s:text name="eaap.op2.sso.logOperate.name"/>';
		   title[2]='<s:text name="eaap.op2.sso.logOperate.functionModel"/>';
		   title[3]='<s:text name="eaap.op2.sso.logOperate.operate"/>';
		   title[4]='<s:text name="eaap.op2.sso.logOperate.class"/>';
		   title[5]='<s:text name="eaap.op2.sso.logOperate.method"/>';
		   title[6]='<s:text name="eaap.op2.sso.logOperate.operateTime"/>';
		   title[7]='<s:text name="eaap.op2.sso.logOperate.params"/>';
		   title[8]='操作编号';
		   title[9]='员工编号';
		   title[10]='功能点ID';
        </script>
		<ui:gridEasy  id="mygrid"  columns="cols" iconCls="icon-save" sortName="code" skin="${contextStyleTheme}" pageInfo="true" singleSelect="true"  sortOrder="desc"
			fit="true" collapsible="false"   title="" striped="true" pageList="10" pagination="true" frozenColumns="true" rownumbers="true" toolbar="true" method="eaap-op2-sso-sysLogOperateAction.showGrid">
			<ui:gridEasyColumn width="100" index="0" title="0" field="CARD_NUMBER" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="100" index="1" title="1" field="NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="100" index="2" title="2" field="MODEL_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="80" index="3" title="3" field="OP_SUM" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="180" index="4" title="4" field="CLASS_NAME" hidden="false" align="center" ></ui:gridEasyColumn>
			<ui:gridEasyColumn width="80" index="5" title="5" field="METHOD_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="100" index="6" title="6" field="OPERATE_TIME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="200" index="7" title="7" field="PARAM_NAME" hidden="false" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="250" index="8" title="8" field="SYS_LOG_OPERATE_ID" hidden="true" align="center" ></ui:gridEasyColumn>
			<ui:gridEasyColumn width="200" index="9" title="9" field="SYS_PERSON_ID" hidden="true" align="center"></ui:gridEasyColumn>
			<ui:gridEasyColumn width="200" index="10" title="10" field="FUNCTION_ID" hidden="true" align="center"></ui:gridEasyColumn>
		</ui:gridEasy>
	   
	  </div>
	  
	  <%@include  file="../common.jsp" %>
</body>	  
         
</html>	