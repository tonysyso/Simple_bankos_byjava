package com.hpe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.StringHolder;

import com.hpe.po.Customer;
import com.hpe.until.DBConnection;

public class BankDaoImpl implements BankDao {
	String type1 = "存款";
	String type2 = "取款";
	String type3 = "转账";
	int resultInt = 0;
	Connection conn=null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	int result=0;
	@Override
	public boolean adlogin(String account,String password) {
		// TODO Auto-generated method stub
		String sql="select * from administrator where adminNumber = ? and adminPwd= ?";
		try {
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if (rs.next()) return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeRs(rs);
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		return false;
	}
	
	@Override
	public int addCustomer(Customer ct) {
		// TODO Auto-generated method stub
		String sql="insert into customer(custNumber,custName,custPwd,custIdCard,custMoney,custDate) values(?,?,?,?,?,now())";
		try {
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ct.getCustNumber());
			pstmt.setString(2, ct.getCustName());
			pstmt.setString(3, ct.getCustPwd());
			pstmt.setString(4, ct.getCustIdCard());
			pstmt.setDouble(5, ct.getCustMoney());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		return result;
	}
	
	@Override
	public double countMoney() {
		// TODO Auto-generated method stub
		String sql ="select sum(custMoney) as allmoney from customer";
		double allMoney=0;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) allMoney =rs.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeRs(rs);
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		
		return allMoney;
	}
	
	@Override
	public List<Customer> rankList() {
		// TODO Auto-generated method stub
		List<Customer> rankList = new ArrayList<>();
		String sql = "select custName,custIdCard,custMoney from customer order by custMoney desc";
		try {
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Customer ct =new Customer();
				ct.setCustName(rs.getString(1));
				ct.setCustIdCard(rs.getString(2));
				ct.setCustMoney(rs.getDouble(3));
				rankList.add(ct);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeRs(rs);
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		return rankList;
	}
	
	@Override
	public boolean ctlogin(String account,String password) {
		// TODO Auto-generated method stub
		String sql="select * from customer where custNumber = ? and custPwd= ?";
		try {
			conn=DBConnection.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if (rs.next()) return true;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			DBConnection.closeRs(rs);
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		return false;
	}
	
	@Override
	public double deposit(double depsoit,String account) {
		double money=0;
		try {
			conn = DBConnection.getConnection();

			conn.setAutoCommit(false);

			String sql = "update customer set custMoney=custMoney+? where custNumber='"
					+ account+ "'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, depsoit);
			pstmt.executeUpdate();

			String sql2 = "insert into business (custNumber,businessType,businessMoney) "
					+ "values('" + account + "','"+type1+"',?)";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setDouble(1, depsoit);
			pstmt.executeUpdate();


			String sql3 = "select custMoney from customer where custNumber ="+account;
			pstmt=conn.prepareStatement(sql3);
			rs = pstmt.executeQuery();
			while(rs.next()) money = rs.getDouble(1);


			conn.commit();
			conn.setAutoCommit(true);

		} catch (Exception e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		return money;
	}
	
	@Override
	public int drawMoney(double depsoit,String account) {
		// TODO Auto-generated method stub
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "update customer set custMoney=custMoney-? where custNumber='"
					+ account + "'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, depsoit);
			resultInt = pstmt.executeUpdate();
			String sql2 = "insert into business (custNumber,businessType,businessMoney) "
					+ "values('" + account + "','"+type2+"',?)";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setDouble(1, depsoit);
			resultInt = pstmt.executeUpdate();

		} catch (Exception e) {
			try {
				conn.rollback();
				conn.setAutoCommit(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		return resultInt;
	}
	
	@Override
	public double checkMoney(String account) {
		// TODO Auto-generated method stub
		String sql ="select custMoney from customer where custNumber ="+account;
		double Money=0;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) Money =rs.getDouble(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeRs(rs);
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		return Money;
		
	}
	
	@Override
	public int transferMoney(double depsoit, String ctaccount,String custNumber2) {
		try {
			conn = DBConnection.getConnection();
		    conn.setAutoCommit(false);
		    String sql2 = "update customer set custMoney=custMoney-? where custNumber='"
		        + ctaccount + "'";
		    pstmt = conn.prepareStatement(sql2);
		    pstmt.setDouble(1, depsoit);
		    resultInt = pstmt.executeUpdate();

		    String sql3 = "update customer set custMoney=custMoney+? where custNumber='"
		        + custNumber2 + "'";;
		    pstmt = conn.prepareStatement(sql3);
		    pstmt.setDouble(1, depsoit);
		    resultInt = pstmt.executeUpdate();

		    String sql4 = "insert into business (custNumber,businessType,businessMoney) "
		        + "values('" + ctaccount + "','"+type3+"',?)";
		    pstmt = conn.prepareStatement(sql4);
		    pstmt.setDouble(1, depsoit);
		    resultInt =pstmt.executeUpdate();

		    conn.commit();
		    conn.setAutoCommit(true);

		  } catch (Exception e) {
		    try {
		      conn.rollback();
		      conn.setAutoCommit(true);
		    } catch (Exception e1) {
		      e1.printStackTrace();
		    }
		    e.printStackTrace();
		  } finally {
		    DBConnection.closeStmt(pstmt);
		    DBConnection.closeConn(conn);
		  }
		  return resultInt;
	}
	
	@Override
	public int pwd(String oldPwd,String ctaccount) {
		// TODO Auto-generated method stub
		String sql = "select * from customer where  custNumber='" + ctaccount
				+ "' and custPwd = ?";
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(true);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oldPwd);
			rs = pstmt.executeQuery();
			while (!rs.next()) {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeRs(rs);
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}

		return 1;
	}
	
	
	
	@Override
	public int alterPw(String newPwd, String ctaccount) {
		// TODO Auto-generated method stub

		String sql = "update customer set custPwd=? where custNumber='"+ ctaccount + "'";
			try {
				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, newPwd);
				resultInt = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBConnection.closeStmt(pstmt);
				DBConnection.closeConn(conn);
			}
		return resultInt;
	}

	public double allMoney(String ctaccount) {
		double allmoney = 0;
		String sql = "select custMoney from customer where custNumber='"
				+ ctaccount + "'";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				allmoney = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeRs(rs);
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		return allmoney;
	}
	
	public int balance(double depsoit,String ctaccount) {
		try {

			String sql = "select * from customer where  custNumber='"
					+ ctaccount + "' and custMoney >= ?";
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, depsoit);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			DBConnection.closeRs(rs);
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		return 1;
	}
	
	
	public boolean searchName(String custNumber) {
		String sql = "select * from customer where custNumber=?";
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custNumber);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeRs(rs);
			DBConnection.closeStmt(pstmt);
			DBConnection.closeConn(conn);
		}
		return false;
		
	}
}
