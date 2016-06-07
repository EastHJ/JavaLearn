package com.imooc.jdbc.goddess.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;



/**
 * 此类是用于创建链接连数据库,使用properties文件配置数据库相关信息
 * 
 * @author h
 *
 */
public class DBDAO {

	// 链接池数据源对象
	private static BasicDataSource datesource = new BasicDataSource();

	// 连接池初始化函数
	public static void init() {

		try {
			// 通过Properties读取配置文件
			Properties ps = new Properties();
			// 通过本类的输入流载入配置文件
			ps.load(DBDAO.class.getClassLoader().getResourceAsStream("source/db.properties"));
			// 驱动类名
			String driver = ps.getProperty("driver");
			// 数据库连接方式和地址
			String url = ps.getProperty("url");
			// 数据库用户名
			String user = ps.getProperty("user");
			// 数据库用户密码
			String password = ps.getProperty("password");
			// 加载驱动使用连接池会自动加载驱动不用在手动加载
//			 Class.forName(driver);
			// 为防止创建链接过多导致影响数据库创建连接池管理连接
			// 设置连接池属性初始连接数
			String initialsize = ps.getProperty("initialsize");
			// 设置最大连接数
			String maxactive = ps.getProperty("maxactive");
			// 最小连接数
			String minidle =ps.getProperty("minidle");
			// 最大空闲数
			String maxidle = ps.getProperty("maxidle");
			// 超市等待时间
			String maxwait = ps.getProperty("maxwait");
			// 设置连接池属性
			datesource.setDriverClassName(driver);
			datesource.setUrl(url);
			datesource.setUsername(user);
			datesource.setPassword(password);
			if(null!=initialsize){
			datesource.setInitialSize(Integer.parseInt(initialsize));
			}
			if(null!=maxactive){
				//最大连接数不能为零
				if(!"0".equals(maxactive.trim())){
			datesource.setMaxActive(Integer.parseInt(maxactive));
				}
			}
			if(null!=minidle){
			datesource.setMinIdle(Integer.parseInt(minidle));
			}
			if(null!=maxwait){//连接使用超时回收时间单位ms
			datesource.setMaxWait(Integer.parseInt(maxwait));
			}
			if(null!=maxidle){
			datesource.setMaxIdle(Integer.parseInt(maxidle));
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("连接池初始化失败，请检查配置文件！", e);
		}

	}
@Test
	public void test() throws SQLException{
	    Connection con =datesource.getConnection();
		String sql = "insert into emp (empno,ename,job) values empno=?,ename =? job = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, 998);
		ps.setString(2, "悟空");
		ps.setString(3, "保镖");
		ps.executeUpdate();
	}
	
	// 获取连接    加 synchronized防止并发问题，保证线程安全
	public static synchronized Connection getConnectoin() throws SQLException {
		//连接池为空则初始化连接池
		if(null==datesource)
		{
			init();
		}
		//通过连接池获取连接
		Connection con = null;
		if (null != datesource) {
			con = datesource.getConnection();
		}
		return con;
	}

	// 连接在使用完之后要关掉
	public static void close(Connection con) {
		if (null != con) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("关闭链接失败！", e);
			}
		}
	}

}
