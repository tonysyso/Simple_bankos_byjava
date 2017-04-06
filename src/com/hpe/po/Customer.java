package com.hpe.po;

import java.sql.Date;

public class Customer {
	private String custNumber; 
	private String custName; 
	private String custPwd; 
	private String custIdCard;
	private double custMoney;
	private Date custDtae;
	
	public String getCustNumber() {
		return custNumber;
	}
	@Override
	public String toString() {
		return "Customer [custNumber=" + custNumber + ", custName=" + custName + ", custPwd=" + custPwd
				+ ", custIdCard=" + custIdCard + ", custMoney=" + custMoney + ", custDtae=" + custDtae + "]";
	}
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPwd() {
		return custPwd;
	}
	public void setCustPwd(String custPwd) {
		this.custPwd = custPwd;
	}
	public String getCustIdCard() {
		return custIdCard;
	}
	public void setCustIdCard(String custIdCard) {
		this.custIdCard = custIdCard;
	}
	public double getCustMoney() {
		return custMoney;
	}
	public void setCustMoney(double custMoney) {
		this.custMoney = custMoney;
	}
	public Date getCustDtae() {
		return custDtae;
	}
	public void setCustDtae(Date custDtae) {
		this.custDtae = custDtae ;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
		super();
	}
	public Customer(String custName, String custIdCard, double custMoney){
		super();
		this.custName = custName;
		this.custIdCard = custIdCard;
		this.custMoney = custMoney;
	}
	public Customer(String custNumber,String custName,String custPwd, String custIdCard, double custMoney,Date custDtae){
		super();
		this.custNumber = custNumber;
		this.custName = custName;
		this.custPwd =custPwd;
		this.custIdCard = custIdCard;
		this.custMoney = custMoney;
		this.custDtae = custDtae;
	}
	public Customer(String custNumber,String custName,String custPwd, String custIdCard, double custMoneyt){
		super();
		this.custNumber = custNumber;
		this.custName = custName;
		this.custPwd =custPwd;
		this.custIdCard = custIdCard;
		this.custMoney = custMoney;
	}
}
