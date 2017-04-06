package com.hpe.service;

import java.util.List;

import com.hpe.po.Customer;

public interface AdminService {
	public boolean adlogin(String account, String password);
	public boolean addCustomer(Customer ct);
	
	public double countMoney();
	public List<Customer>rankList();
}
