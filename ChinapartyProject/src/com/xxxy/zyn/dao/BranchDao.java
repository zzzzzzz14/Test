package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Branch;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class BranchDao {
	private C3P0Utils c3p0;

	public BranchDao() {
		c3p0 = new C3P0Utils();
	}

	// 添加部门信息
	public String addBranch(Branch model) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("insert into branch(branch_id,branchName,branchPid,branchFlag,branchCDate) values(?,?,?,?,?) ");
		conn = c3p0.getConnection();
		// 同名检测
		boolean flag = isSameName(model, '0');
		if (flag == false) {
			int num = 0;
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, model.getBranch_id());
				pstm.setString(2, model.getBranchName());
				pstm.setString(3, model.getBranchPid());
				pstm.setString(4, model.getBranchFlag());
				// 日期格式化
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pstm.setString(5, f.format(model.getBranchCDate()));
				num = pstm.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// c3p0.close(null, pstm, conn);
			}

			if (num > 0) {
				return "Ok";
			} else {
				return "Err";
			}
		} else {
			return "sname";
		}

	}

	// 同名检测方法
	public boolean isSameName(Branch model, char mflag) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		conn = c3p0.getConnection();// 建立连接

		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select * from branch where branchName=? ");
		if (mflag == '1') {// 修改时得同名检测
			sqlStr.append(" and branch_id!='" + model.getBranch_id() + "' ");
		}
		boolean flag = false;
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, model.getBranchName());
			rs = pstm.executeQuery();// 读取,查询
			if (rs.next()) {// 用游标方式，指向数据的下一条
				flag = true;
			} else {
				flag = false;// 没有同名
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	// 列出组织信息
	public List<Branch> getAllBranch(String str) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Branch> list = new ArrayList<Branch>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("SELECT d1.*,d2.branchName pbname ");
		sqlStr.append("FROM branch d1 ");
		sqlStr.append("LEFT JOIN branch d2 ON d1.branchPid=d2.branch_id ");
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Branch model = new Branch();
				model.setBranch_id(rs.getString("branch_id"));
				model.setBranchName(rs.getString("branchName"));
				model.setBranchFlag(rs.getString("branchFlag").equals("1") ? "启用" : "禁用");
				model.setBranchCDate(rs.getDate("branchCDate"));// 只有日期
				// model.setDepartmentCDate(rs.getTimestamp("departmentCDate"));
				// model.setDepartmentCDate(rs.getTime("classesCDate"));//只显示时间
				model.setBranchPid(rs.getString("branchPid"));
				model.setPbname(rs.getString("pbname"));
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return list;
	}

	// 根据班级编号删除班级数据
	public String deleteBranch(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("delete from branch ");
		sqlStr.append("where branch_id=? ");
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

	// 根据ID找组织机构信息
	public Branch findBranchById(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select d1.*,d2.branchName pbname from branch d1 ");
		sqlStr.append("left JOIN branch d2 on d1.branchPid=d2.branch_id ");
		sqlStr.append("where d1.branch_id=? ");
		conn = c3p0.getConnection();
		Branch model = new Branch();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cid);
			rs = pstm.executeQuery();
			if (rs != null && rs.next()) {
				model.setBranch_id(rs.getString("branch_id"));
				model.setBranchName(rs.getString("branchName"));
				model.setBranchFlag(rs.getString(("branchFlag")).equals("1") ? "启用" : "禁用");
				// model.setDepartmentCDate(rs.getDate("departmentCDate"));//
				// 只有日期
				model.setBranchCDate(rs.getTimestamp("branchCDate"));
				// model.setDepartmentCDate(rs.getTime("departmentCDate"));//只显示时间
				model.setBranchPid(rs.getString("branchPid"));
				model.setPbname(rs.getString("pbname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return model;
	}

	public String updateBranch(Branch model){
		Connection conn=null;
		PreparedStatement pstm=null;
		boolean flag=isSameName(model, '1');//同盟检测
		if(flag!=true){
			StringBuffer sqlStr=new StringBuffer();
			sqlStr.append("update branch set branchName=?,branchFlag=?,");
			sqlStr.append("branchCDate=?,branchPid=? ");
			sqlStr.append("where branch_id=? ");
			conn=c3p0.getConnection();
			int num=0;
			try {
				pstm=conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, model.getBranchName());
				pstm.setString(2, model.getBranchFlag());
				SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pstm.setString(3, f.format(model.getBranchCDate()));
				pstm.setString(4, model.getBranchPid());
				pstm.setString(5, model.getBranch_id());
				num=pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(num>0){
				return "Ok";
			}else{
				return "Err";
			}
		}else{
			return "Same";
		}
	}
	// 带分页的部门列表
	public List<Branch> getAllBranchByPage(String str, Page page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Branch> list = new ArrayList<Branch>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("SELECT d1.*,d2.branchName pbname FROM branch d1 ");
		sqlStr.append("LEFT JOIN branch d2 on d1.branchPid=d2.branch_id ");
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		sqlStr.append(" order by d1.branchName desc ");// 级联查询，降序排列
		sqlStr.append(" limit ?,? ");
		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setInt(1, (page.getCurrentPage() - 1) * page.getCount());
			pstm.setInt(2, page.getCount());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Branch model = new Branch();
				model.setBranch_id(rs.getString("branch_id"));
				model.setBranchName(rs.getString("branchName"));
				model.setBranchFlag(rs.getString("branchFlag").equals("1") ? "启用" : "禁用");
				// model.setBranchCDate(rs.getDate("branchCDate"));// 只有日期
				model.setBranchCDate(rs.getTimestamp("branchCDate"));
				// model.setBranchCDate(rs.getTime("branchCDate"));//只显示时间
				model.setBranchPid(rs.getString("branchPid"));
				model.setPbname(rs.getString("pbname"));
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return list;
	}

	public int getCount(String str) {// 查询语句
		StringBuffer sqlStr = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int total = 0;
		sqlStr.append("select count(1) from branch d1 ");// 给branch表起一个别名d1，用d1代替branch
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		sqlStr.append(" order by d1.branchName desc ");
		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			rs = pstm.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return total;
	}
	public List<Branch> getBranches(){
		List<Branch> list = new ArrayList<Branch>();
		Connection conn = null ; 
		PreparedStatement pstm = null ; 
		ResultSet rs = null ; 
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select * from Branch where branchFlag='1' ") ;
		sqlStr.append(" order by branchName asc ") ;
		conn = c3p0.getConnection() ; 
		try{
			pstm = conn.prepareStatement(sqlStr.toString());
			rs = pstm.executeQuery();
			while(rs.next()){
				Branch model = new Branch();
				model.setBranch_id(rs.getString("branch_id"));
				model.setBranchName(rs.getString("branchName"));
				list.add(model);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return list;
	}
}
