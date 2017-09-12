package com.huawei.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 第三方账户余额信息model
 * 
 * @author wangbo
 * 
 */
public class AccountBalanceModel {

	// id
	private int id;

	// 机构标识
	private String identifier;

	// 机构名称
	private String branchName;

	// 账户名
	private String accountName;

	// 查询时间
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")  
	private Date queryDate;

	// 当前余额
	private double accountBalance;

	// 可用余额
	private double availableBalance;

	// 页面条数
	private int pageSize;

	// 当前页面
	private int currentPage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(double availableBalance) {
		this.availableBalance = availableBalance;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	@Override
	public String toString() {
		return "AccountBalanceModel [id=" + id + ", identifier=" + identifier
				+ ", branchName=" + branchName + ", accountName=" + accountName
				+ ", queryDate=" + queryDate + ", accountBalance="
				+ accountBalance + ", availableBalance=" + availableBalance
				+ "]";
	}

}
