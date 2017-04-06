package com.hpe.dao;

import java.util.List;

import com.hpe.po.Customer;

public interface BankDao {
	public boolean adlogin(String account, String password);
	public int addCustomer(Customer ct);
	public double countMoney();
	public List<Customer>rankList();
	
	
	
	public boolean ctlogin(String account,String password);
	public double deposit(double deposit,String account);
	public int drawMoney(double depsoit,String account);
	public double checkMoney(String account);
	public int transferMoney(double depsoit, String ctaccount,String custNumber2);
	public int alterPw(String newPwd,String ctaccount);
	public int pwd (String oldPwd,String ctaccount);
	public double allMoney(String ctaccount);
	public int balance(double depsoit,String ctaccount);
	public boolean searchName(String custName);
}
