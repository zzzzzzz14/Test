package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Roles;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RolesDao {
	private C3P0Utils c3p0;

	public RolesDao() {
		// TODO Auto-generated constructor stub
		c3p0 = new C3P0Utils();
	}

	// 添加角色信息
	public String addRoles(Roles model) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("insert into roles(roles_id,rolesName,");
		sqlStr.append("rolesCDate) values(?,?,?) ");
		conn = c3p0.getConnection();
		// 同名检测
		int num = 0;
		try {
				pstm = conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, model.getRoles_id());
				pstm.setString(2, model.getRolesName());
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pstm.setString(3, f.format(model.getRolesCDate()));
				num=pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (num > 0) {
				return "Ok";
			} else {
				return "Err";
			}
		} 

	// 列出角色信息
	public List<Roles> getAllRoles(String str) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Roles> list = new ArrayList<Roles>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select * from roles order by rolesName asc ");
		//sqlStr.append("LEFT JOIN roles r2 on r1.roles_id=r2.roles_id ");
		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Roles model = new Roles();
				model.setRoles_id(rs.getString("roles_id"));
				model.setRolesName(rs.getString("rolesName"));
				model.setRolesCDate(rs.getDate("rolesCDate"));// 只有日期
				// model.setDepartmentCDate(rs.getTimestamp("departmentCDate"));
				// model.setDepartmentCDate(rs.getTime("departmentCDate"));//只显示时间
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return list;
	}

	// 根据角色编号删除角色数据
	public String deleteRoles(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("delete from Roles ");
		sqlStr.append("where Roles_id=? ");
		conn = c3p0.getConnection();
		int num = 0;
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cid);
			num = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		if (num > 0) {
			return "Ok";
		} else {
			return "Err";
		}
	}

	// 根据ID找角色信息
	public Roles findRolesById(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select * from roles ");
		sqlStr.append("where roles_id=? ");
		conn = c3p0.getConnection();
		Roles model = new Roles();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cid);
			rs = pstm.executeQuery();
			if (rs != null && rs.next()) {
				model.setRoles_id(rs.getString("roles_id"));
				model.setRolesName(rs.getString("rolesName"));
				//model.setDepartmentCDate(rs.getDate("departmentCDate"));// 只有日期
				model.setRolesCDate(rs.getTimestamp("rolesCDate"));
				// model.setDepartmentCDate(rs.getTime("departmentCDate"));//只显示时间
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return model;
	}
	// 更新角色信息
	public String updateRoles(Roles model) {
		 Connection conn=null;
	   	 PreparedStatement pstm=null;
	   	 StringBuilder sqlStr=new StringBuilder();
	   	 sqlStr.append("update roles set rolesName=?,rolesCDate=? where roles_id=? ");
	   	 conn=c3p0.getConnection();
	   	 int num=0;
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, model.getRolesName());
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pstm.setString(2, f.format(model.getRolesCDate()));
				pstm.setString(3, model.getRoles_id());
				num=pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (num > 0) {
				return "Ok";
			} else {
				return "Err";
			}
		} 
	//带分页角色列表
	public List<Roles> getAllRolesByPage(String str, Page page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Roles> list = new ArrayList<Roles>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("SELECT * FROM roles ");
		if(str!=null && !str.equals("")){
			sqlStr.append(" where 1=1 "+ str + " ");
		}
		sqlStr.append(" order by rolesName desc ");
		sqlStr.append(" limit ?,? ");
		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setInt(1, (page.getCurrentPage()-1)*page.getCount());
			pstm.setInt(2, page.getCount());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Roles model = new Roles();
				model.setRoles_id(rs.getString("roles_id"));
				model.setRolesName(rs.getString("rolesName"));
				//model.setDepartmentCDate(rs.getDate("departmentCDate"));// 只有日期
			    model.setRolesCDate(rs.getTimestamp("rolesCDate"));
				// model.setDepartmentCDate(rs.getTime("departmentCDate"));//只显示时间
				
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return list;
	}
	public int getCount(String str){
		StringBuffer sqlStr=new StringBuffer();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		int total=0;
		sqlStr.append("select count(1) from roles ");
		sqlStr.append(" order by rolesName desc ");
		conn=c3p0.getConnection();
		try {
			pstm=conn.prepareStatement(sqlStr.toString());
			rs=pstm.executeQuery();
			if(rs.next()){
				total=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
		return total;
	}
	//获取所有部门编号和名称，用于添加和修改时的下拉选择
	public List<Roles> getRoles(){
		List<Roles> list=new ArrayList<Roles>();
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		StringBuffer sqlStr=new StringBuffer();
		sqlStr.append("select * from roles where rolesFlag='1' ");
		sqlStr.append(" order by rolesName asc ");
		conn=c3p0.getConnection();
		try {
			pstm=conn.prepareStatement(sqlStr.toString());
			rs=pstm.executeQuery();
			while(rs.next()){
				Roles model=new Roles();
				model.setRoles_id(rs.getString("roles_id"));
				model.setRolesName(rs.getString("rolesName"));
				list.add(model);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return list;
	}
}
