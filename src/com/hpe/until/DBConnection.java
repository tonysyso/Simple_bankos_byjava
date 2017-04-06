package com.hpe.until;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String URL="jdbc:mysql://localhost:3306/Bank?useSSL=false&useUnicode=true&characterEncoding=GBK";
    public static final String USER="root";
    public static final String PASSWORD="123456";
    static{
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return conn;
	}
    public static void closeRs(ResultSet rs){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static void closeStmt(Statement stmt){
    	try {
			if(stmt!=null){
				stmt.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    public static void closeConn(Connection conn){
    	try {
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
