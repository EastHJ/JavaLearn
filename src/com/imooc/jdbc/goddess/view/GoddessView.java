package com.imooc.jdbc.goddess.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.imooc.jdbc.goddess.conntral.GoddessAction;
import com.imooc.jdbc.goddess.model.Goddess;

public class GoddessView {

	private final static String CONTEXT = "欢迎来到女神禁区：\n" + "下面是女神禁区的功能列表：\n" + "[MAIN/M]:主菜单\n" + "[QUERY/Q]:查看全部女神的信息\n"
			+ "[GET/G]:查看某位女神的详细信息\n" + "[ADD/A]:添加女神信息\n" + "[UPDATE/U]:更新女神信息\n" + "[DELETE/D]:删除女神信息\n"
			+ "[SEARCH/S]:查询女神信息(根据姓名、手机号来查询)\n" + "[EXIT/E]:退出女神禁区\n" + "[BREAK/B]:退出当前功能，返回主菜单";

	private static final String OPERATION_MAIN = "MAIN";
	private static final String OPERATION_QUERY = "QUERY";
	private static final String OPERATION_GET = "GET";
	private static final String OPERATION_ADD = "ADD";
	private static final String OPERATION_UPDATE = "UPDATE";
	private static final String OPERATION_DELETE = "DELETE";
	private static final String OPERATION_SEARCH = "SEARCH";
	private static final String OPERATION_EXIT = "EXIT";
	private static final String OPERATION_BREAK = "BREAK";
	private static final String UPDATE_ITEMS = "请选择修改内容，按相应字母选择：" + "\n[N]姓名\t[A]年龄\t[B]生日\t[M]手机号"
			+ "\n[E]邮箱\t[F]完成\t[C]取消";

	public static void main(String[] args) {
		// 输出菜单信息
		System.out.println(CONTEXT);
		GoddessAction action = new GoddessAction();
		Goddess gods = new Goddess();
		Scanner scan = new Scanner(System.in);
		String operation = null;
		List<Goddess> list = new ArrayList<Goddess>();
		Integer step = 1;
		System.out.println("请选择:");
		String in = null;
		while (true) {
			list.clear();
			in = scan.next().toString();
			if (OPERATION_EXIT.equals(in.toUpperCase()) || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("您已退出女神禁区！");
				break;
			} else if (OPERATION_MAIN.equals(in.toUpperCase())
					|| OPERATION_MAIN.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println(CONTEXT);
				System.out.println("请选择:");
			} else if (OPERATION_QUERY.equals(in.toUpperCase())
					|| OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase())) {
				list = action.query();
				System.out.println("所有女神基本信息：");
				for (Goddess g : list) {
					System.out.println(g);
				}
			} else if (OPERATION_GET.equals(in.toUpperCase())
					|| OPERATION_GET.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("请输入女神ID:");
				// System.out.println(id);
				gods = action.query(scan.nextInt());
				if (null != gods) {
					System.out.println("所查询女神信息如下：\n" + gods);
				} else {
					System.out.println("查询失败！");
				}
			} else if (OPERATION_ADD.equals(in.toUpperCase())
					|| null == operation && OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_ADD.equals(operation)) {
				operation = OPERATION_ADD;
				// 新增女神
				if (1 == step) {
					System.out.println("请输入女神姓名：");
				} else if (2 == step) {
					gods.setUser_name(in.trim());
					System.out.println("请输入女神年龄：");
				} else if (3 == step) {
					int age = Integer.parseInt(in);
					if (0 < age && age < 120) {
						gods.setAge(age);
						System.out.println("请输入女神生日,格式：yyyy-mm-dd");
					} else {
						System.out.println("输入年龄异常，请重新输入！");
						step = 1;
					}
				} else if (4 == step) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date birthday = null;
					try {
						birthday = (Date) sdf.parse(in);
						gods.setBirthday(birthday);
						System.out.println("请输入女神邮箱：");
					} catch (ParseException e) {
						e.printStackTrace();
						System.out.println("格式输入有误，请重新输入！\n请输入女神生日,格式：yyyy-mm-dd");
						step = 3;
					}
				} else if (5 == step) {
					String regex = "[a-zA-Z0-9_]+@（[a-zA-Z0-9]+\\.）+[a-zA-Z]";
					if (in.trim().matches(regex)) {
						gods.setEmail(in.trim());
						System.out.println("请输入女神手机号：");
					} else {
						System.out.println("输入邮箱格式有误，请重新输入！\n请输入女神邮箱：");
						step = 4;
					}
				} else if (6 == step) {
					String reg = "\\d{11}";
					if (in.trim().matches(reg)) {
						gods.setMobile(in.trim());
						System.out.println("请输入更新者姓名：");
					} else {
						System.out.println("输入如格式有误,请重新输入！\n请输入女神手机号：");
						step = 5;
					}
				} else if (7 == step) {
					gods.setUpdate_user(in);
					try {
						list.add(gods);
						action.add(list);
						System.out.println("新增女神成功");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("新增女神失败");
					} finally {
						step = 1;
					}
				}
				if (OPERATION_ADD.equals(operation)) {
					step++;
				} else if (OPERATION_BREAK.equals(in.toUpperCase())
						|| OPERATION_BREAK.substring(0, 1).equals(in.toUpperCase())) {
					System.out.println("您已取消新增女神！\n请选择要进行的操作！");
					operation = null;
					step = 1;
				}
			} else if (OPERATION_UPDATE.equals(in.toUpperCase())
					|| OPERATION_UPDATE.substring(0, 1).equals(in.toUpperCase())
					|| OPERATION_UPDATE.equals(operation)) {
				if (null == operation) {
					operation = OPERATION_UPDATE;
					System.out.println("请输入要更新女神ID");
					gods = action.query(scan.nextInt());
					System.out.println(UPDATE_ITEMS);
				} else if ("N".equals(in.toUpperCase())) {
					System.out.println("请输入更改后的姓名：");
					gods.setUser_name(scan.next());
				} else if ("A".equals(in.toUpperCase())) {
					System.out.println("请输入更改后的年龄：");
					gods.setAge(scan.nextInt());
				} else if ("B".equals(in.toUpperCase())) {
					System.out.println("请输入更改后的生日：");
					gods.setUser_name(scan.nextLine());
				} else if ("M".equals(in.toUpperCase())) {
					System.out.println("请输入更改后的手机号：");
					gods.setUser_name(scan.nextLine());
				} else if ("E".equals(in.toUpperCase())) {
					System.out.println("请输入更改后的邮箱：");
					gods.setUser_name(scan.nextLine());
				} else if ("F".equals(in.toUpperCase())) {
					action.edit(gods);
					operation = null;
				} else if ("C".equals(in.toUpperCase())) {
					System.out.println("您已取消更新女神！");
					operation = null;
					continue;
				} else {
					System.out.println("请选择正确操作！\n" + UPDATE_ITEMS);
				}
				System.out.println("请继续：");
			} else if (OPERATION_DELETE.equals(in.toUpperCase())
					|| OPERATION_DELETE.substring(0, 1).equals(in.toUpperCase())) {
				System.out.println("请输入要删除女神的ID：");
				action.delete(scan.nextInt());
			} else if (OPERATION_SEARCH.equals(in.toUpperCase())
					|| OPERATION_SEARCH.substring(0, 1).equals(in.toUpperCase())) {
				Map<String,Object> m = new HashMap<String,Object>();
				List<Map<String,Object>> params =new ArrayList<>();
				m.put("key", "and");
				m.put("rela", "=");
				System.out.println("请输入查询字段([name]姓名、[mobile]手机号)");
				String s = scan.next();
				if("姓名".equals(s)||"name".equals(s.toLowerCase()))
				{  
					m.put("name", "user_name");
					System.out.println("请输入要查询女神姓名：");
					m.put("value", "'"+scan.next()+"'");
				}else if("手机号".equals(s)||"mobile".equals(s.toLowerCase()))
				{
					m.put("name", s);
					System.out.println("请输入手机号");
					m.put("value", scan.next());
				}
				params.add(m);
			    list=action.query(params);
			    for(Goddess g:list)
			    {
			    	System.out.println(g);
			    }
				
			}
		}
		scan.close();
	}
}
