package com.huawei.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sh_acount")
public class Acount {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "USER_NAME")
	private String userName;
	private String balance;
	
	public Acount() {
		super();
	}
	public Acount(String userName, String balance) {
		super();
		this.userName = userName;
		this.balance = balance;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Acount [id=" + id + ", userName=" + userName + ", balance=" + balance + "]";
	}
	
}
