package com.eix.bookstore.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.eix.bookstore.exception.DBException;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
//
	private static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("bookStore");
	}
	
	public static Connection getConnection(){
		try {
			
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBException("数据库连接错误");
		}
	}
	
	public static void release(Connection connection){
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new DBException("数据库连接错误");
			}
		}
	}
	
	public static void release(ResultSet rs, Statement statement){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new DBException("数据库连接错误");
			}
		}
	}
}
