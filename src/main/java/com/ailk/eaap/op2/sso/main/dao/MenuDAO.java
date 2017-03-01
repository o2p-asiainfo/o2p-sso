package com.ailk.eaap.op2.sso.main.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Map;

import com.asiainfo.foundation.exception.BusinessException;
import com.asiainfo.foundation.log.LogModel;
import com.asiainfo.foundation.log.Logger;
import com.asiainfo.foundation.common.ExceptionCommon;

import com.ailk.eaap.op2.sso.main.bo.MenuInfo;
import com.ailk.eaap.op2.sso.main.bo.MenuInfoBO;
import com.linkage.rainbow.dao.SqlMapDAO;

/**
 * 
 * @author zhaobl
 *
 */
public class MenuDAO implements IMenuDAO {
    private SqlMapDAO sqlMapDao;
	private Logger log = Logger.getLog(this.getClass());

//    /**
//     * ��ѯ�˵���Ϣ
//     */
//    public List<MenuInfo> queryMenuInfo(MenuInfo menuBean) {
//	return (List<MenuInfo>) sqlMapDao.queryForList(
//		"eaap-op2-sso-main.queryMenuInfo", menuBean);
//    }
    
    public List<MenuInfoBO> queryMenuInfoBO(String sysPersonId,
			String parentMenuId, String businessSystemId,String type,String language,Integer tenantId) {
    	Map map = new HashMap();
    	map.put("sysPersonId", sysPersonId);
    	map.put("parentMenuId", parentMenuId);
    	map.put("businessSystemId", businessSystemId);
    	map.put("tenantId", tenantId);
    	List menuInfoBOList = new ArrayList();
    	try {
    		if(null != type && "1".equals(type)){
    			if("zh".equals(language)){
        			menuInfoBOList = sqlMapDao.queryForList("eaap-op2-sso-menu.queryMenuInfoBO2", map);
    			}else{
        			menuInfoBOList = sqlMapDao.queryForList("eaap-op2-sso-menu.queryMenuInfoBOEn2", map);
    			}
    		}else{
    			if("zh".equals(language)){
    				menuInfoBOList = sqlMapDao.queryForList("eaap-op2-sso-menu.queryMenuInfoBO", map);
    			}else{
    				menuInfoBOList = sqlMapDao.queryForList("eaap-op2-sso-menu.queryMenuInfoBOEn", map);
    			}
    		}
    		
    	}catch(Exception e){
    		log.error(LogModel.EVENT_APP_EXCPT, new BusinessException(ExceptionCommon.WebExceptionCode,e.getMessage(),null));
    	}
		if(menuInfoBOList == null)
			return null;
    	return (List<MenuInfoBO>) menuInfoBOList;
	}
    
    public SqlMapDAO getSqlMapDao() {
    	return sqlMapDao;
    }

    public void setSqlMapDao(SqlMapDAO sqlMapDao) {
    	this.sqlMapDao = sqlMapDao;
    }

}
