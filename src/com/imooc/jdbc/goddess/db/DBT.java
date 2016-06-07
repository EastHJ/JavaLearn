package com.imooc.jdbc.goddess.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 本类是测试数据库的链接
 * 
 * @author h
 *
 */
public class DBT {
	private static  String url;
	private static  String user;
	private static  String password;
	static {

		try {
			// 通过Properties读取配置文件
			Properties ps = new Properties();
			// 通过本类的输入流载入配置文件
			ps.load(DBT.class.getClassLoader().getResourceAsStream("db.properties"));
			// 驱动类名
			String driver = ps.getProperty("driver");
			// 数据库连接方式和地址
			url = ps.getProperty("url");
			// 数据库用户名
			user = ps.getProperty("user");
			// 数据库用户密码
			password = ps.getProperty("password");
			// 1.加载驱动
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("加载驱动失败！", e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 2.获得数据库链接
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
//3.关闭连接
	public static void colse(Connection con) {
		if (null != con) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭连接失败！");
			}
		}
	}
	
	public static void main(String[] args) {
		
	}

}
