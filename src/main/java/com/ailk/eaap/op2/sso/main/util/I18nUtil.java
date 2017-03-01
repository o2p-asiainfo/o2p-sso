package com.ailk.eaap.op2.sso.main.util;

import java.util.Locale;

public class I18nUtil {
	public static Locale getLocaleByCode(String code){
		Locale[] localList = Locale.getAvailableLocales();
		for(Locale l:localList){
			String codeS = l.getLanguage()+"_"+l.getCountry();
			if(codeS.equals(code)){
				return l;
			}
		}
		return Locale.getDefault();
	}
	public static void main(String[] args) {
		Locale l = getLocaleByCode("en_US");
		System.out.println(l.getLanguage()+"_"+l.getCountry());
	}
}
