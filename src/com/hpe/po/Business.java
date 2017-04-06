package com.hpe.po;

public class Business {
	private int businessNum;
	private String custNumber;
	private String businessType;
	private double businessMoney;
	public int getBusinessNum() {
		return businessNum;
	}
	public void setBusinessNum(int businessNum) {
		this.businessNum = businessNum;
	}
	public String getCustNumber() {
		return custNumber;
	}
	public void setCustNumber(String custNumber) {
		this.custNumber = custNumber;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public double getBusinessMoney() {
		return businessMoney;
	}
	public void setBusinessMoney(double businessMoney) {
		this.businessMoney = businessMoney;
	}
	@Override
	public String toString() {
		return "Business [businessNum=" + businessNum + ", custNumber=" + custNumber + ", businessType=" + businessType
				+ ", businessMoney=" + businessMoney + "]";
	}
	
	
}
