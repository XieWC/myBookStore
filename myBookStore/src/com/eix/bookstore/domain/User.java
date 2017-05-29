package com.eix.bookstore.domain;

import java.util.LinkedHashSet;
import java.util.Set;



public class User {
	private Integer userId;
	private String userName;
	private int accountId;
    private Set<Trade> trades = new LinkedHashSet<>();
	public Set<Trade> getTrades() {
		return trades;
	}

	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
    
}
