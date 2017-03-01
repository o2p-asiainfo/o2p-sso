package com.ailk.eaap.op2.sso.main.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ailk.eaap.op2.sso.main.model.RequestContractBean;

public class ListenerDaoImpl implements ListenerDao {
	private JdbcTemplate uapJdbcTemplate;
	private String queryPersonNum;
	private String serialNumber;
	private RequestContractBean contractHeader;
	private String obtainBizCode;
	private String obtainContract;
	private String obtainContractVer;
	private String obtainActionCode;
	private String obtainServiceLevel;
	private String obtainSrcOrgID;
	private String obtainSrcSysID;
	private String obtainDstOrgID;
	private String obtainDstSysID;
	private String obtainSrcSysSign;
	private String obtainUrl;

	public String getObtainUrl() {
		return obtainUrl;
	}

	public void setObtainUrl(String obtainUrl) {
		this.obtainUrl = obtainUrl;
	}

	public String getObtainBizCode() {
		return obtainBizCode;
	}

	public void setObtainBizCode(String obtainBizCode) {
		this.obtainBizCode = obtainBizCode;
	}

	public String getObtainContract() {
		return obtainContract;
	}

	public void setObtainContract(String obtainContract) {
		this.obtainContract = obtainContract;
	}

	public String getObtainContractVer() {
		return obtainContractVer;
	}

	public void setObtainContractVer(String obtainContractVer) {
		this.obtainContractVer = obtainContractVer;
	}

	public String getObtainActionCode() {
		return obtainActionCode;
	}

	public void setObtainActionCode(String obtainActionCode) {
		this.obtainActionCode = obtainActionCode;
	}

	public String getObtainServiceLevel() {
		return obtainServiceLevel;
	}

	public void setObtainServiceLevel(String obtainServiceLevel) {
		this.obtainServiceLevel = obtainServiceLevel;
	}

	public String getObtainSrcOrgID() {
		return obtainSrcOrgID;
	}

	public void setObtainSrcOrgID(String obtainSrcOrgID) {
		this.obtainSrcOrgID = obtainSrcOrgID;
	}

	public String getObtainSrcSysID() {
		return obtainSrcSysID;
	}

	public void setObtainSrcSysID(String obtainSrcSysID) {
		this.obtainSrcSysID = obtainSrcSysID;
	}

	public String getObtainDstOrgID() {
		return obtainDstOrgID;
	}

	public void setObtainDstOrgID(String obtainDstOrgID) {
		this.obtainDstOrgID = obtainDstOrgID;
	}

	public String getObtainDstSysID() {
		return obtainDstSysID;
	}

	public void setObtainDstSysID(String obtainDstSysID) {
		this.obtainDstSysID = obtainDstSysID;
	}

	public String getObtainSrcSysSign() {
		return obtainSrcSysSign;
	}

	public void setObtainSrcSysSign(String obtainSrcSysSign) {
		this.obtainSrcSysSign = obtainSrcSysSign;
	}

	public RequestContractBean getContractHeader() {
		return contractHeader;
	}

	public void setContractHeader(RequestContractBean contractHeader) {
		this.contractHeader = contractHeader;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public JdbcTemplate getUapJdbcTemplate() {
		return uapJdbcTemplate;
	}

	public void setUapJdbcTemplate(JdbcTemplate uapJdbcTemplate) {
		this.uapJdbcTemplate = uapJdbcTemplate;
	}

	public int queryPersonNum(String personId) {
		int result = uapJdbcTemplate.queryForInt(queryPersonNum,
				new Object[] { personId });
		return result;
	}

	public String getQueryPersonNum() {
		return queryPersonNum;
	}

	public void setQueryPersonNum(String queryPersonNum) {
		this.queryPersonNum = queryPersonNum;
	}

	public String obtainSerialNumber() {
		String getSerialNumber = uapJdbcTemplate.queryForObject(serialNumber,
				String.class)
				+ "";
		return getSerialNumber;
	}

	public RequestContractBean obtainRequestheader() {
		String busCode = uapJdbcTemplate.queryForObject(obtainBizCode,
				String.class)
				+ "";
		String contract = uapJdbcTemplate.queryForObject(obtainContract,
				String.class)
				+ "";
		String contractVer = uapJdbcTemplate.queryForObject(obtainContractVer,
				String.class)
				+ "";
		String actionCode = uapJdbcTemplate.queryForObject(obtainActionCode,
				new Object[] { busCode, contract, contractVer }, String.class)
				+ "";
		String serviceLevel = uapJdbcTemplate.queryForObject(
				obtainServiceLevel, new Object[] { busCode, contract,
						contractVer }, String.class)
				+ "";
		String srcOrgID = uapJdbcTemplate.queryForObject(obtainSrcOrgID,
				new Object[] { busCode, contract, contractVer }, String.class)
				+ "";
		String srcSysID = uapJdbcTemplate.queryForObject(obtainSrcSysID,
				new Object[] { busCode, contract, contractVer }, String.class)
				+ "";
		String srcSysSign = uapJdbcTemplate.queryForObject(obtainSrcSysSign,
				new Object[] { busCode, contract, contractVer }, String.class)
				+ "";
		String dstOrgID = uapJdbcTemplate.queryForObject(obtainDstOrgID,
				new Object[] { busCode, contract, contractVer }, String.class)
				+ "";
		String dstSysID = uapJdbcTemplate.queryForObject(obtainDstSysID,
				new Object[] { busCode, contract, contractVer }, String.class)
				+ "";

		contractHeader.setBusCode(busCode);
		contractHeader.setActionCode(actionCode);
		contractHeader.setDstOrgId(dstOrgID);
		contractHeader.setDstSysId(dstSysID);
		contractHeader.setServiceCode(contract);
		contractHeader.setServiceContractVer(contractVer);
		contractHeader.setServiceLevel(serviceLevel);
		contractHeader.setSrcOrgId(srcOrgID);
		contractHeader.setSrcSysId(srcSysID);
		contractHeader.setSrcSysSign(srcSysSign);
		return contractHeader;
	}

	public String obtainUrl() {
		String busCode = uapJdbcTemplate.queryForObject(obtainBizCode,
				String.class)
				+ "";
		String contract = uapJdbcTemplate.queryForObject(obtainContract,
				String.class)
				+ "";
		String contractVer = uapJdbcTemplate.queryForObject(obtainContractVer,
				String.class)
				+ "";
		String actionCode = uapJdbcTemplate.queryForObject(obtainActionCode,
				new Object[] { busCode, contract, contractVer }, String.class)
				+ "";
		String url = uapJdbcTemplate.queryForObject(obtainUrl,
				new Object[] { busCode, contract, contractVer }, String.class)
				+ "";
		return url;
	}
}
