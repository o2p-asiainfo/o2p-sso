package com.ailk.eaap.op2.sso.main.util;



import com.ailk.eaap.op2.sso.main.model.CountFlowBean;
import com.ailk.eaap.op2.sso.main.model.JsonTransformBean;
import com.ailk.eaap.op2.sso.main.model.PageBean;
import com.ailk.eaap.op2.sso.main.model.SearchFlowBean;

public class SqlUtil {
private static final String SEARCH_FLOW_SQL = "select a.*,b.XRB_EVENT_SEQ_ID,c.NAME provinceNm,d.NAME flowNm,d.INOROUT " +
		" from npsoa.NPSOA_BUSI_FLOW_INSTANCE a,npsoa.NPSOA_BFI_2_EVENT_SEQ b, " +
		" ORG c,npsoa.NPSOA_FLOW_DEF d " +
		" where a.np_flow_inst_id = b.np_flow_inst_id " +
		" and a.org_id = c.org_id " +
		" and a.busi_flowid = d.busi_flowid ";

private static final String COUNT_FLOW_SQL = "select &searchKey& code,a.busi_flowid flowCd,a.end_desc state,b.inorout,count(*) num " +
		" from npsoa.NPSOA_BUSI_FLOW_INSTANCE a," +
		" npsoa.NPSOA_FLOW_DEF b" +
		" where a.busi_flowid = b.busi_flowid" +
		" &condition&" +
		" group by &searchKey&,a.busi_flowid,a.end_desc,b.inorout";

private static final String SEARCH_NPLIST_SQL = "select a.npcodelist,a.beg_time,a.end_time " +
		" from npsoa.NPSOA_BUSI_FLOW_INSTANCE a" +
		" where 1=1";

public static final String buildCountFlowSql(CountFlowBean bean){
	String sql = "";
	StringBuffer buffer = new StringBuffer("");
	if("byProvince".equals(bean.getSearchType())){
		if(!ConvertUtil.isEmpty(bean.getProvince())){
			buffer.append(" and a.ORG_ID = ").append(bean.getProvince());
		}
		if(!ConvertUtil.isEmpty(bean.getInOrOut1())){
			buffer.append(" and b.inorout = ").append(bean.getInOrOut1());
		}
		if(!ConvertUtil.isEmpty(bean.getState())){
			buffer.append(" and a.end_desc = '").append(bean.getState()).append("'");
		}
		if(!ConvertUtil.isEmpty(bean.getSonFlow())){
			if("1".equals(bean.getSonFlow())){
				buffer.append(" and a.busi_flowid in ('3','4')");
			}else if("2".equals(bean.getSonFlow())){
				buffer.append(" and a.busi_flowid in ('8','9','10','11')");
			}else if("3".equals(bean.getSonFlow())){
				buffer.append(" and a.busi_flowid in ('14','15')");
			}else{
				
			}				
		}else{
			if(!ConvertUtil.isEmpty(bean.getPreFlow())){
				if("1".equals(bean.getPreFlow())){
					buffer.append(" and a.busi_flowid in ('1','2')");
				}else if("2".equals(bean.getPreFlow())){
					buffer.append(" and a.busi_flowid in ('5','6','7')");
				}else if("3".equals(bean.getPreFlow())){
					buffer.append(" and a.busi_flowid in ('12','13')");
				}else if("4".equals(bean.getPreFlow())){
					buffer.append(" and a.busi_flowid = '16'");
				}else{
					
				}	
			}
		}
		if(!ConvertUtil.isEmpty(bean.getStartTime1())){
			buffer.append(" and a.BEG_TIME >= to_date('")
			.append(bean.getStartTime1() + " 00:00:00")
			.append("', 'yyyy-mm-dd hh24:mi:ss') ");
		}
		if(!ConvertUtil.isEmpty(bean.getEndTime1())){
			buffer.append(" and a.END_TIME <= to_date('")
			.append(bean.getEndTime1() + " 23:59:59")
			.append("', 'yyyy-mm-dd hh24:mi:ss') ");
		}
		sql = COUNT_FLOW_SQL.replaceAll("&searchKey&", "a.org_id");
	}else{
		if(!ConvertUtil.isEmpty(bean.getNpCode())){
			buffer.append(" and a.NPCODELIST like '%").append(bean.getNpCode()).append("%'");
		}		
		if(!ConvertUtil.isEmpty(bean.getStartTime2())){
			buffer.append(" and a.BEG_TIME >= to_date('")
			.append(bean.getStartTime2() + " 00:00:00")
			.append("', 'yyyy-mm-dd hh24:mi:ss') ");
		}
		if(!ConvertUtil.isEmpty(bean.getEndTime2())){
			buffer.append(" and a.END_TIME <= to_date('")
			.append(bean.getEndTime2() + " 23:59:59")
			.append("', 'yyyy-mm-dd hh24:mi:ss') ");
		}
		if(!ConvertUtil.isEmpty(bean.getInOrOut2())){
			buffer.append(" and b.inorout = ").append(bean.getInOrOut2());
		}
		sql = COUNT_FLOW_SQL.replaceAll("&searchKey&", "a.NPCODELIST");
	}
	sql = sql.replaceAll("&condition&", buffer.toString());
	return sql;
}

public static final String buildSearchFlowSql(SearchFlowBean bean){
	StringBuffer buffer = new StringBuffer(SEARCH_FLOW_SQL);
	if(!ConvertUtil.isEmpty(bean.getSonFlow())){
		if("1".equals(bean.getSonFlow())){
			buffer.append(" and a.busi_flowid in ('3','4')");
		}else if("2".equals(bean.getSonFlow())){
			buffer.append(" and a.busi_flowid in ('8','9','10','11')");
		}else if("3".equals(bean.getSonFlow())){
			buffer.append(" and a.busi_flowid in ('14','15')");
		}else{
			
		}				
	}else{
		if(!ConvertUtil.isEmpty(bean.getPreFlow())){
			if("1".equals(bean.getPreFlow())){
				buffer.append(" and a.busi_flowid in ('1','2')");
			}else if("2".equals(bean.getPreFlow())){
				buffer.append(" and a.busi_flowid in ('5','6','7')");
			}else if("3".equals(bean.getPreFlow())){
				buffer.append(" and a.busi_flowid in ('12','13')");
			}else if("4".equals(bean.getPreFlow())){
				buffer.append(" and a.busi_flowid = '16'");
			}else{
				
			}	
		}
	}
	if(!ConvertUtil.isEmpty(bean.getStartTime())){
		buffer.append(" and a.BEG_TIME >= to_date('")
		.append(bean.getStartTime() + " 00:00:00")
		.append("', 'yyyy-mm-dd hh24:mi:ss') ");
	}
	if(!ConvertUtil.isEmpty(bean.getEndTime())){
		buffer.append(" and a.END_TIME <= to_date('")
		.append(bean.getEndTime() + " 23:59:59")
		.append("', 'yyyy-mm-dd hh24:mi:ss') ");			
	}
	if(!ConvertUtil.isEmpty(bean.getNpCode())){
		buffer.append(" and a.NPCodeList like '%").append(bean.getNpCode()).append("%'");
	}
	return buffer.toString();
}

public static final String buildSearchNpListSql(JsonTransformBean bean){
	StringBuffer buffer = new StringBuffer(SEARCH_NPLIST_SQL);
	if("byProvince".equals(bean.getSearchType())){
		if(!ConvertUtil.isEmpty(bean.getSubProvince())){
			buffer.append(" and a.ORG_ID = ").append(bean.getSubProvince());
		}
	}else{
		if(!ConvertUtil.isEmpty(bean.getNpCodeList())){
			buffer.append(" and a.NPCODELIST = '").append(bean.getNpCodeList()).append("'");
		}	
	}
	if(!ConvertUtil.isEmpty(bean.getSubFlowCd())){
		buffer.append(" and a.busi_flowid = '").append(bean.getSubFlowCd()).append("'");
	}
	if(!ConvertUtil.isEmpty(bean.getSubState())){
		buffer.append(" and a.end_desc = '").append(bean.getSubState()).append("'");
	}
	if(!ConvertUtil.isEmpty(bean.getSubStartTime())){
		buffer.append(" and a.BEG_TIME >= to_date('")
		.append(bean.getSubStartTime() + " 00:00:00")
		.append("', 'yyyy-mm-dd hh24:mi:ss') ");
	}
	if(!ConvertUtil.isEmpty(bean.getSubEndTime())){
		buffer.append(" and a.END_TIME <= to_date('")
		.append(bean.getSubEndTime() + " 23:59:59")
		.append("', 'yyyy-mm-dd hh24:mi:ss') ");
	}
	return buffer.toString();
}

public static final String buildPageSql(String sql,PageBean bean){
	StringBuffer buffer = new StringBuffer("");
	buffer.append("select * from ");
	buffer.append("(select a.*,rownum row_num from (");
	buffer.append(sql);
	buffer.append(") a where rownum <=");
	buffer.append(getEndNum(bean.getCurrentPage(),bean.getPageCount()));
	buffer.append(") b  where b.row_num >= ");
	buffer.append(getStartNum(bean.getCurrentPage(),bean.getPageCount()));
	return buffer.toString();
}
public static final int getStartNum(String currPage,String PageCount){
	int a = Integer.parseInt(currPage);
	int b = Integer.parseInt(PageCount);
	return b*(a - 1) + 1;
}
public static final int getEndNum(String currPage,String PageCount){
	int a = Integer.parseInt(currPage);
	int b = Integer.parseInt(PageCount);
	return a*b;
}
public static final Integer getPageSize(int recordNum,int pageNum){
	return recordNum % pageNum ==0? recordNum/pageNum:recordNum/pageNum+1;
}
}
