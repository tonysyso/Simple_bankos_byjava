package com.hpe.service;

import com.hpe.dao.BankDao;
import com.hpe.dao.BankDaoImpl;

public class CustomerServiceImpl implements CustomerService {
	BankDao bankDao =new BankDaoImpl();
	int resultInt = 0;
	@Override
	public boolean ctlogin(String account,String password) {
		// TODO Auto-generated method stub
		return bankDao.ctlogin(account, password);
	}
	
	@Override
	public double deposit(double depsoit,String account) {
		// TODO Auto-generated method stub
		return bankDao.deposit(depsoit, account);
	}
	
	@Override
	public int drawMoney(double depsoit,String account) {
		// TODO Auto-generated method stub
		return bankDao.drawMoney(depsoit, account);
	}
	
	@Override
	public double checkMoney(String account) {
		// TODO Auto-generated method stub
		return bankDao.checkMoney(account);
	}
	
	@Override
	public boolean transferMoney(double depsoit, String ctaccount,String custNumber2){
		// TODO Auto-generated method stub
		resultInt = bankDao.transferMoney(depsoit, ctaccount, custNumber2);
		if (resultInt > 0) return true;
	    else return false;
	}
	public boolean pwd (String oldPwd,String ctaccount){
		resultInt = bankDao.pwd(oldPwd, ctaccount);
		if (resultInt == 0) {
			return false;
		} else if (resultInt == 1) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public int alterPw(String newPwd,String ctaccount) {
		// TODO Auto-generated method stub
		resultInt = bankDao.alterPw(newPwd, ctaccount);
		if (resultInt > 0) {
			return resultInt;
		} else {
			return 0;
		}
	}

	@Override
	public double allMoney(String ctaccount) {
		// TODO Auto-generated method stub
		return bankDao.allMoney(ctaccount);
	}

	@Override
	public int balance(double depsoit, String ctaccount) {
		// TODO Auto-generated method stub
		return bankDao.balance(depsoit, ctaccount);
	}
	public boolean searchName(String custName){
		return bankDao.searchName(custName);
	}
	
}
