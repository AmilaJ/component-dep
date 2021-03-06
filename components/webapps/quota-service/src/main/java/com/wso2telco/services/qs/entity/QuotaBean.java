package com.wso2telco.services.qs.entity;

import java.util.Date;

public class QuotaBean {
	private String operator;
	private String serviceProvider;
	private String applicationName;
	private String apiName;
	private String quotaLimit;

	private Date fromDate;
	private Date toDate;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getQuotaLimit() {
		return quotaLimit;
	}

	public void setQuotaLimit(String quotaLimit) {
		this.quotaLimit = quotaLimit;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
