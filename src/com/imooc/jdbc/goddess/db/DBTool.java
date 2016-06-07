package com.imooc.jdbc.goddess.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 本类是测试数据库的链接
 * 
 * @author h
 *
 */
public class DBTool {
//	 private static final String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
//	 private static final String user = "system";
//	 private static final String password ="cambrian210";
	
	private static String driver ;//="oracle.jdbc.driver.OracleDriver";

	public static void main(String[] args) {
		
		Connection con = null;
		try {
			// 通过Properties读取配置文件
			Properties ps = new Properties();
			// 通过本类的输入流载入配置文件
			ps.load(DBTool.class.getClassLoader().getResourceAsStream("db.properties"));
			// 驱动类名
			driver = ps.getProperty("driver");
			System.out.println(driver);
			// 数据库连接方式和地址
			String url = ps.getProperty("url");
			System.out.println(url);
			// 数据库用户名
			String user = ps.getProperty("user");
			System.out.println(user);
			// 数据库用户密码
			String password = ps.getProperty("password");
			System.out.println(password);
			// 1.加载驱动
			Class.forName(driver);
			// 2.获得数据库链接
			con = DriverManager.getConnection(url, user, password);
			// 3.创建statement 发送sql语句;
			Statement smt = con.createStatement();
			String sql = "select * from emp";
			// 4.发送sql 语句，查询成功获得结果集
			ResultSet rs = smt.executeQuery(sql);
			// 5.循环输出结果集部分内容
			while (rs.next()) {
				System.out.println(rs.getInt("empno") + " " + rs.getString("ename"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("加载驱动失败！", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("获得连接失败！", e);
			 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭连接失败！");
			}
		}

	}
}
