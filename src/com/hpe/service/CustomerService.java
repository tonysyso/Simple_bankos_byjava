package com.hpe.service;

public interface CustomerService {
	public boolean ctlogin(String account,String password);
	public double deposit(double depsoit,String account);
	public int drawMoney(double depsoit,String account);
	public double checkMoney(String account);
	public boolean transferMoney(double depsoit, String ctaccount,String custNumber2);
	public int alterPw(String newPwd,String ctaccount);
	public boolean pwd (String oldPwd,String ctaccount);
	
	public double allMoney(String ctaccount);
	public int balance(double depsoit,String ctaccount);
	public boolean searchName(String custName);
	
	
}
