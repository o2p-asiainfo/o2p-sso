package com.ailk.eaap.op2.sso.main.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: CookiesUtil
 * @Description: 
 * @author zhengpeng
 * @date 2015-2-8 上午10:13:34
 *
 */
public class CookiesUtil {
	
	/**
     * 清除所有cookie
     * @param req
     * @param res
     */
    public static void clearCookie(HttpServletRequest req,HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        for(Cookie _cookie : cookies) {
            Cookie cookie = new Cookie(_cookie.getName(),null);
            cookie.setMaxAge(0);
            cookie.setPath("/"); 
            res.addCookie(cookie);
        }
    }
    
    /**
     * 添加一个cookie
     * @param res
     * @param name
     * @param value
     * @param maxAge
     */
    public static void addCookie(HttpServletResponse res,String name,String value,int maxAge) {
        Cookie cookie = new Cookie(name,value);
        if(maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        cookie.setPath("/");
        res.addCookie(cookie);
    }
    
    /**
     * 清除cookie
     * @param req
     * @param res
     * @param name
     */
    public static void removeCookie(HttpServletRequest req,HttpServletResponse res,String name) {
        if(null != name) {
            Cookie cookie = new Cookie(name,null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            res.addCookie(cookie);
        }
    }
    
    /**
     * 获取cookie的值
     * @param req
     * @param name
     * @return
     */
    public static String getCookieValue(HttpServletRequest req,String name) {
        Cookie cookie = get(req,name);
        String cookieVal = (null == cookie) ? null : cookie.getValue();
        return cookieVal;
    }
    
    public static Cookie get(HttpServletRequest req,String name) {
        Map<String,Cookie> cookieMap = _readCookieMap(req);
        if(cookieMap.containsKey(name)) {
            return (Cookie)cookieMap.get(name);
        } else {
            return null;
        }
    }
    
    private static Map<String,Cookie> _readCookieMap(HttpServletRequest req) {
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = req.getCookies();
        if(null != cookies) {
            for(Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
    
    

}
