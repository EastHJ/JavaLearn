package com.imooc.jdbc.goddess.conntral;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imooc.jdbc.goddess.model.Goddess;

public class Test {
	public static void main(String[] args) throws SQLException {
		GoddessAction gd = new GoddessAction();
		List<Goddess> goddess = new ArrayList<Goddess>();
	    Goddess g = new Goddess();
	    g.setAge(27);
	    g.setUser_name("小影");
	    g.setBirthday(new Date(System.currentTimeMillis()));
	    g.setCreate_user("yinmo");
	    g.setEmail("xiaoying@126.com");
	    g.setIsdel(1);
	    g.setMobile("11112020300");
	    g.setSex("女");
	    g.setUpdate_user("yinmo");
	   g.setId(6);
	    goddess.add(g);
					gd.add(goddess);
		gd.edit(g);
		List<Map<String, Object>> params =new ArrayList<Map<String, Object>>();
		Map<String, Object> m = new HashMap<>();
		m.put("key", "and");
		m.put("name", "user_name");
		m.put("rela", "=");
		m.put("value", "'小美'");
		params.add(m);
//		Map<String, Object> m1 = new HashMap<>();
//		m1.put("name", "mobile");
//		m1.put("real", "=");
//		m1.put("value", "'11112020300'");
//		params.add(m1);
//		gd.delete(params);
//	    System.out.println(gd.query(5));
		for(Goddess g1 :(gd.query(params)))
		{
			System.out.println(g1.toString());
		}
		System.out.println(gd.query(3).toString());
	}

}
