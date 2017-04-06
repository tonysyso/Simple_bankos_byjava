package com.hpe.service;

import java.util.List;

import com.hpe.dao.BankDao;
import com.hpe.dao.BankDaoImpl;
import com.hpe.po.Customer;

public class AdminServiceImpl implements AdminService {
	int result = 0;
	BankDao bankDao =new BankDaoImpl();
	@Override
	public boolean adlogin(String account, String password) {
		// TODO Auto-generated method stub
		return bankDao.adlogin(account, password);
	}
	
	@Override
	public boolean addCustomer(Customer ct) {
		// TODO Auto-generated method stub
		result = bankDao.addCustomer(ct);
		if(result>0) return true;
		else return false;
	}
	
	@Override
	public double countMoney() {
		// TODO Auto-generated method stub
		return bankDao.countMoney();
	}
	
	@Override
	public List<Customer> rankList() {
		// TODO Auto-generated method stub
		return bankDao.rankList();
	}
	
}
