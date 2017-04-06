package com.hpe.view;

import java.sql.Savepoint;
import java.util.List;
import java.util.Scanner;

import com.hpe.po.Customer;
import com.hpe.service.AdminService;
import com.hpe.service.AdminServiceImpl;
import com.hpe.service.CustomerService;
import com.hpe.service.CustomerServiceImpl;

public class BankManager {
	//全局输入流
	Scanner scanner = new Scanner(System.in);
	AdminService adminService = new AdminServiceImpl();
	CustomerService customerService = new CustomerServiceImpl();
	//全局顾客账号 用为查找条件
	String ctaccount;
	public static void main(String[] args) {
		BankManager bankManager = new BankManager();
		bankManager.init();
		
	}
	//初始化界面
	public void init(){
		while(true){
			System.out.println("-------------------------------银行系统-------------------------------");
			System.out.println("                              1.管理员");
			System.out.println("                              2.顾客");
			System.out.println("--------------------------------------------------------------------");
			System.out.print("请选择：");
			int select =scanner.nextInt();
			switch (select) {
				case 1:
					System.out.print("请输入用户名：");
					String account = scanner.next();
					System.out.print("请输入密码：");
					String password = scanner.next();
					if(adminService.adlogin(account, password)) adminshow();
					else System.out.println("账号 or 密码输入错误！");
					break;
				case 2:
					System.out.print("请输入用户名：");
					ctaccount = scanner.next();
					System.out.print("请输入密码：");
					String ctpassword = scanner.next();
					if (customerService.ctlogin(ctaccount, ctpassword)) customshow();
					else System.out.println("账号 or 密码输入错误！");
					break;
				default:
					break;
			}
		}
	}
	//系统进入管理员 界面
	public void adminshow(){
		while(true){
			 System.out.println("\t\t银行系统[管理员]");
			 System.out.println("**********************************************************************");
			 System.out.println("1.添加顾客	2.计算储蓄总额		3.富豪排行榜		4.退出");
			 System.out.println("**********************************************************************");
			 System.out.print("请选择：");
			 int adselect = scanner.nextInt();
			 
			 switch (adselect) {
				case 1:
					//添加顾客
					addCustomer();
					System.out.print("1.返回管理员主菜单 0.退出系统: ");
					int i = scanner.nextInt();
					switch (i) {
						case 1:
							adminshow();
							break;
						case 0:
							System.out.println("谢谢使用");
							System.exit(0);
						default:
							break;
					}
					break;
				case 2:
					//计算总额
					countMoney();
					
					System.out.print("1.返回登录主菜单 2.返回管理员主菜单 3.退出系统: ");
					int j = scanner.nextInt();
					switch (j) {
						case 1:
							init();
							break;
						case 2:
							adminshow();
							break;
						case 3:
							System.out.println("谢谢使用");
							System.exit(0);
						default:
							break;
					}
					break;
				case 3:
					//排行榜
					rankList();
					System.out.print("1.返回登录主菜单 2.返回管理员主菜单 3.退出系统: ");
					int k = scanner.nextInt();
					switch (k) {
						case 1:
							init();
							break;
						case 2:
							adminshow();
							break;
						case 3:
							System.out.println("谢谢使用");
							System.exit(0);
						default:
							break;
					}
					break;
				case 4:
					init();
					break;
				
				default:
					break;
			 }
		 }
	}
	


	//管理员操作 1.添加顾客
	private void addCustomer() {
		System.out.print("请输入顾客银行卡号：");
		String custNumber = scanner.next();
		System.out.print("请输入顾客姓名：");
		String custName =scanner.next();
		System.out.print("请输入顾客身份证：");
		String custIdCard = scanner.next();
		System.out.print("请输入顾客开户金额：");
		double custMoney = scanner.nextDouble();
		System.out.print("请输入初始密码：");
		String custPwd = scanner.next();
		Customer customer = new Customer();
		customer.setCustNumber(custNumber);
		customer.setCustName(custName);
		customer.setCustIdCard(custIdCard);
		customer.setCustMoney(custMoney);
		customer.setCustPwd(custPwd);
		if (adminService.addCustomer(customer))
			System.out.println("添加成功");
		
	}
	//管理员操作 2.计算总额
	private void countMoney() {
		System.out.println("当前存款总额为"+adminService.countMoney());
	}
	//管理员操作 3.排行榜
	private void rankList() {
		List<Customer> list = adminService.rankList();
		int rank =0;
		System.out.println("名次\t姓名\t身份证号码\t\t金额");
			for(Customer customer : list){
				rank++;
				System.out.println(rank+"\t"+customer.getCustName()+"\t"+customer.getCustIdCard()+"\t"+customer.getCustMoney());
			}
	}
	
	//系统进入顾客 界面
	 public void customshow(){
		 while(true){
			 System.out.println("\t\t银行系统[顾客]");
			 System.out.println("************************************************************");
			 System.out.println("1.存款	2.取款	3.查询余额	4.转账	5.修改密码	6.退出");
			 System.out.println("************************************************************");
			 System.out.print("请选择：");
			 int ctselect = scanner.nextInt();
			 switch (ctselect) {
				case 1:
					//存款
					deposit(ctaccount);
					finishShow();
					break;
				case 2:
					//取款
					drawMoney();
					finishShow();
					break;
				case 3:
					//查询余额
					checkMoney();
					finishShow();
					break;
				case 4:
					//转账
					transferMoney();
					finishShow();
					break;
				case 5:
					//修改密码
					alterPw();
					break;
				case 6:
					init();
					break;
				default:
					break;
			}
		 }
	 }
	 public void finishShow(){
		 System.out.println("1.返回顾客主菜单 2.退出系统");
		 int i = scanner.nextInt();
			switch (i) {
				case 1:
					customshow();
					break;
				case 0:
					System.out.println("谢谢使用");
					System.exit(0);
				default:
					break;
			}
	 }
	
	
	 
	
	
	//顾客操作 1.存款
	private void deposit(String account) {
		System.out.print("请输入存款金额：");
		double savemoney = scanner.nextDouble();
		System.out.println("存款成功，当前余额"+customerService.deposit(savemoney, ctaccount));
		
	}
	 //顾客操作 2.取款
	private void drawMoney() {
		System.out.print("请输入取款金额：");
		double depsoit = scanner.nextDouble();
		double firstMoney = customerService.allMoney(ctaccount);
		int result = customerService.balance(depsoit, ctaccount);
		if (result == 0) {System.out.println("余额不足,当前余额"+firstMoney);}
		 else {
			 if (customerService.drawMoney(depsoit, ctaccount)>0) {
				 double endMoney = customerService.allMoney(ctaccount);
				 System.out.println("取款成功，当前余额:" + endMoney);
			}else {System.out.println("取款失败");}
			 
		 }
			
	}
	 //顾客操作 3.查询余额
	private void checkMoney() {
		System.out.println("当前余额"+customerService.checkMoney(ctaccount));
	}
	//顾客操作 4.转账
	private void transferMoney() {
		   System.out.print("请输入转账账号：");
			String custNumber = scanner.next();
			boolean result = customerService.searchName(custNumber);
			while (!result) {
				System.out.println("账号错误请重新输入");
				transferMoney();//重复调用
			}
			System.out.print("请输入转账金额：");
			double transfermoney = scanner.nextDouble();
			int resultInt = customerService.balance(transfermoney, ctaccount);;
			if (resultInt == 0) {
				double mon = customerService.allMoney(ctaccount);
				System.out.println("账户余额不足，当前余额：" + mon);
				
			} else {
				result = customerService.transferMoney(transfermoney, ctaccount, custNumber);
				if (result) {
					System.out.println("转账成功！");
					
				}
			}
	}
	//顾客操作 5.修改密码
	private void alterPw() {
		int resultInt = 0;
		System.out.print("请输入旧密码：");
		String oldPwd = scanner.next();
		boolean result = customerService.pwd(oldPwd,ctaccount);
		if (!result) {
			System.out.println("旧密码错误，请重写输入！");
			alterPw();
		}
		System.out.print("请输入新密码：");
		String newPwd = scanner.next();
		resultInt = customerService.alterPw(newPwd, ctaccount);
		if (resultInt != 0) {
			System.out.println("密码修改成功");
		}
	}
}
