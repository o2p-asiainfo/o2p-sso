package com.ailk.eaap.op2.sso.main.model;

public class UnifinedAuthBean {
	public static String PWDTYPE = "10";
	public static String ACCOUNTTYPE = "100";
	public static String SVCCONT = "<SvcCont>" + "<AuthReq>" + "<AuthInfo>"
			+ "<AccountType>accountType</AccountType>"
			+ "<AccountID>accountID</AccountID>" + "<PWDType>pWDType</PWDType>"
			+ " <Password>password</Password>" + "</AuthInfo>" + "</AuthReq>"
			+ "</SvcCont>";
	private String transactionId;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
