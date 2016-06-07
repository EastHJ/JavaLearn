package com.imooc.jdbc.goddess.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.imooc.jdbc.goddess.db.DBT;

public class GoddessDAO {
	public void addGoddess(List<Goddess> goddess) throws SQLException {
		Connection con = null;
		boolean autocommit =false;
		try {
			con = DBT.getConnection();
			String sql = "insert into goddess(id,user_name,sex,age,birthday,email,mobile,create_user,create_date,update_user,update_date,isdel)"
					+ "  values (goddess_seq.nextval,?,?,?,?,?,?,?,sysdate,?,sysdate,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			autocommit = con.getAutoCommit();
			con.setAutoCommit(false);
			int count = 0;
			for (Goddess g : goddess) {
				count++;
				ps.setString(1, g.getUser_name());
				ps.setString(2, g.getSex());
				ps.setInt(3, g.getAge());
				ps.setDate(4, g.getBirthday());
				ps.setString(5, g.getEmail());
				ps.setString(6, g.getMobile());
				ps.setString(7, g.getCreate_user());
				ps.setString(8, g.getUpdate_user());
				ps.setInt(9, g.getIsdel());
				ps.addBatch();
				if (count % 20 == 0) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			ps.executeBatch();
			System.out.println("新增女神成功！");
			con.commit();
		} catch (SQLException e) {
			con.rollback();
			e.printStackTrace();
			throw new RuntimeException("新增女神失败！", e);
		}finally{
			con.setAutoCommit(autocommit);
			DBT.colse(con);
		}
	}

	public void updateGoddess(Goddess g) {
		Connection con = null;
		try {
			con = DBT.getConnection();
			String sql = "update goddess set user_name=?,sex =?,age =?,birthday=? ,email=?,"
					+ "mobile=?, update_user=?,update_date=sysdate ,isdel=? " + "where id =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, g.getUser_name());
			ps.setString(2, g.getSex());
			ps.setInt(3, g.getAge());
			ps.setDate(4, g.getBirthday());
			ps.setString(5, g.getEmail());
			ps.setString(6, g.getMobile());
			ps.setString(7, g.getUpdate_user());
			ps.setInt(8, g.getIsdel());
			ps.setInt(9, g.getId());
			ps.executeUpdate();
			System.out.println("更新女神成功！");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更新女神失败！", e);
		}finally{
			DBT.colse(con);
		}

	}
//根据Id删除
	public void deleteGoddess(Integer id) {
		Connection con = null;
		try {
			con = DBT.getConnection();
			String sql = "delete from goddess where id=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("删除女神成功!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除女神失败！", e);
		}finally{
			DBT.colse(con);
		}
	}
//删除特定组合条件删除信息
	public void deleteGoddess(List<Map<String,Object>> params) {
		Connection con = null;
		try {
			con = DBT.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from goddess where '1'='1' ");
			if(null!=params&&params.size()>0)
			{
				for(Map<String, Object> m: params)
				{
					sql.append(" "+m.get("key")+" "+m.get("name")+m.get("rela")+m.get("value"));
				}
			}
			PreparedStatement ps = con.prepareStatement(sql.toString());
			int flag =ps.executeUpdate();
			if(flag!=0){
			System.out.println("删除女神成功!");
			}else{
			System.out.println("数据不存在!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除女神失败！", e);
		}finally{
			DBT.colse(con);
		}
	}
//查看基本信息
	public List<Goddess> query() {
		Connection con = null;
		try {
			//获取数据库连接
			con = DBT.getConnection();
			String sql="select * from goddess ";
			//发送查询语句
			PreparedStatement ps = con.prepareStatement(sql);
			//查询获得结果集
			ResultSet rs = ps.executeQuery();
			//创建集合存放查询记录
			List<Goddess> goddess = new ArrayList<Goddess>();
			//遍历结果集存入集合
			while (rs.next()) {
				Goddess g = new Goddess();
				g.setId(Integer.parseInt(rs.getString("id").trim()));
				g.setUser_name(rs.getString("user_name").trim());
//				g.setAge(rs.getInt("age"));
				g.setSex(null);
				goddess.add(g);
			}
			return goddess;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询女神失败！", e);
		}finally{
			DBT.colse(con);
		}
		
	}
//根据ID查询
	public Goddess query(Integer id) {
		Connection con = null;
		try {
			con = DBT.getConnection();
			String sql = "select * from goddess where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				//System.out.println("查询女神为：姓名:" + rs.getString("user_name") + "\t年龄:" + rs.getString("age"));
				Goddess g = new Goddess();
				g.setUser_name(rs.getString("user_name").trim());
				g.setAge(rs.getInt("age"));
				g.setBirthday(rs.getDate("birthday"));
				g.setEmail(rs.getString("email").trim());
				g.setId(rs.getInt("id"));
				g.setMobile(rs.getString("mobile").trim());
				g.setSex(rs.getString("sex").trim());
				g.setCreate_user(rs.getString("create_user").trim());
				g.setCreate_date(rs.getDate("create_date"));
				g.setUpdate_user(rs.getString("update_user").trim());
				g.setUpdate_date(rs.getDate("update_date"));
				g.setIsdel(rs.getInt("isdel"));
				return g;
			}
			System.out.println("查询女神不存在！");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询女神失败！", e);
		}finally{
			DBT.colse(con);
		}
		return null;
	}
//其他组合条件查询
	public List<Goddess> query(List<Map<String,Object>> params) {
		Connection con = null;
		try {
			//获取数据库连接
			con = DBT.getConnection();
			//查询条件使用拼接字符串的形式，使查询条件更灵活
			StringBuilder sql = new StringBuilder();
			sql.append("select * from goddess where '1'='1' ");
			
			if(null!=params&&params.size()>0)
			{
				for(Map<String, Object> m: params)
				{
					sql.append(" "+m.get("key")+" "+m.get("name")+" "+m.get("rela")+m.get("value"));
				}
			}
			//System.out.println(sql.toString());
			//发送查询语句
			PreparedStatement ps = con.prepareStatement(sql.toString());
			//查询获得结果集
			ResultSet rs = ps.executeQuery();
			//创建集合存放查询记录
			List<Goddess> goddess = new ArrayList<Goddess>();
			//遍历结果集存入集合
			while (rs.next()) {
				Goddess g = new Goddess();
				g.setUser_name(rs.getString("user_name").trim());
				g.setAge(rs.getInt("age"));
				g.setBirthday(rs.getDate("birthday"));
				g.setEmail(rs.getString("email").trim());
				g.setId(rs.getInt("id"));
				g.setMobile(rs.getString("mobile").trim());
				g.setSex(rs.getString("sex").trim());
				g.setCreate_user(rs.getString("create_user").trim());
				g.setCreate_date(rs.getDate("create_date"));
				g.setUpdate_user(rs.getString("update_user").trim());
				g.setUpdate_date(rs.getDate("update_date"));
				g.setIsdel(rs.getInt("isdel"));
				goddess.add(g);
			}
			return goddess;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询女神失败！", e);
		}finally{
			DBT.colse(con);
		}
		
	}
}
