package com.imooc.jdbc.goddess.conntral;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.imooc.jdbc.goddess.model.Goddess;
import com.imooc.jdbc.goddess.model.GoddessDAO;

/**
 * 此类为控制层，实现试图层与模型层的信息交换
 * 此类须调用模型层的增、删、改、查功能
 * 同时将结果输出到视图层
 * @author h
 *
 */
public class GoddessAction {
	//增加
	public void add(List<Goddess> goddess) throws SQLException{
		GoddessDAO dao = new GoddessDAO();
		dao.addGoddess(goddess);
	}
	//删除根据
	public void delete(Integer id)
	{
		GoddessDAO dao = new GoddessDAO();
		dao.deleteGoddess(id);
	}
	//根据其他制定条件删除
	public void delete(List<Map<String,Object>> params){
		GoddessDAO dao = new GoddessDAO();
		dao.deleteGoddess(params);
		
	}
	//修改
	public void edit(Goddess g){
		GoddessDAO dao = new GoddessDAO();
		dao.updateGoddess(g);
	}
	//查询基本信息
	public  List<Goddess> query()
	{
		GoddessDAO dao = new GoddessDAO();
		return dao.query();

	}
	//指定id查询
	public Goddess query(Integer id)
	{
		GoddessDAO dao = new GoddessDAO();
		return dao.query(id);

	}
	//组合条件查询
	public List<Goddess> query(List<Map<String,Object>> params)
	{
		GoddessDAO dao = new GoddessDAO();
		
		 return dao.query(params);

	}
}
