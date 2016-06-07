package com.imooc.jdbc.goddess.test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imooc.jdbc.goddess.model.Goddess;
import com.imooc.jdbc.goddess.model.GoddessDAO;

/**
 * 本类原用于测试，goddessdao已更新，现已不适用，
 * 
 * 本类用于测试goddessdao
 * @author h
 *
 */
public class GoddessTest {
	public static void main(String[] args) throws SQLException {
		GoddessDAO gd = new GoddessDAO();
		List<Goddess> goddess = new ArrayList<Goddess>();
	    Goddess g = new Goddess();
	    g.setAge(20);
	    g.setUser_name("小影");
	    g.setBirthday(new Date(System.currentTimeMillis()));
	    g.setCreate_user("yinmo");
	    g.setEmail("xiaoxia@126.com");
	    g.setIsdel(1);
	    g.setMobile("11112020300");
	    g.setSex("女");
	    g.setUpdate_user("yinmo");
	   g.setId(2);
	    goddess.add(g);
					gd.addGoddess(goddess);
//		gd.updateGoddess(g);
		List<Map<String, Object>> params =new ArrayList<Map<String, Object>>();
		Map<String, Object> m = new HashMap<>();
		m.put("name", "user_name");
		m.put("real", "=");
		m.put("value", "'小夏'");
		params.add(m);
		Map<String, Object> m1 = new HashMap<>();
		m1.put("name", "mobile");
		m1.put("real", "=");
		m1.put("value", "'11112020300'");
		params.add(m1);
		//gd.deleteGoddess(params );
		gd.query(params);
		for(Goddess g1 :(gd.query(params)))
		{
			System.out.println(g1.toString());
		}
		System.out.println(gd.query(3).toString());
	}

}
