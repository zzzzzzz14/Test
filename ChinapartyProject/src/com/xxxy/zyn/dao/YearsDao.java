package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Years;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class YearsDao {
	private C3P0Utils c3p0 = null;

	public YearsDao() {

	}

	public List<Years> getAllYears() {

		Connection conn = null; //获取连接
		PreparedStatement pstm = null;  //编译预处理
		ResultSet rs = null; //数据集
		List<Years> list = new ArrayList<Years>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select * from years order by yearsname asc ");
		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString()); //编译预处理
			rs = pstm.executeQuery();
			while (rs.next()) //游标方式
			{
				Years model = new Years();
				model.setYears_id(rs.getString("years_id"));
				model.setYearsname(rs.getString("yearsname"));
				model.setYearsflag(rs.getString("yearsflag").equals("1")?"启用":"禁用");
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// c3p0.close(rs,pstm,conn);
		}
		return list;
	}

	// 添加年份信息
	public String addYears(Years model) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("insert into years(years_id,yearsname,yearsflag) values(?,?,?) ");
		conn = c3p0.getConnection();
		//
		boolean flag = isSameName(model);
		if (flag == false) {
			int num = 0;
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, model.getYears_id());
				pstm.setString(2, model.getYearsname());
				pstm.setString(3, model.getYearsflag());
				num = pstm.executeUpdate();// 执行
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
			return "Same";
		}

	}

	//
	public boolean isSameName(Years model) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		conn = c3p0.getConnection();// 建立连接
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select * from years where yearsname=?");
		boolean flag = false;
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, model.getYearsname());
			rs = pstm.executeQuery();// 读取
			if (rs.next()) {
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

	// 根据年份编号删除年份数据
	public String deleteYears(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("delete from years ");
		sqlStr.append("where years_id=? ");
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

	// 根据ID找年份信息
	public Years findYearsById(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select * from years ");
		sqlStr.append("where years_id=? ");
		conn = c3p0.getConnection();
		Years model = new Years();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cid);
			rs = pstm.executeQuery();
			if (rs != null && rs.next()) {
				model.setYears_id(rs.getString("years_id"));
				model.setYearsname(rs.getString("yearsname"));
				model.setYearsflag(rs.getString("yearsflag").equals("1")?"启用":"禁用");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return model;
	}

	// 更新（修改）年份信息
	public String updateYears(Years model) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("update years set yearsname=?,yearsflag=? where years_id=?");
		conn = c3p0.getConnection();
		int num = 0;
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, model.getYearsname());
			pstm.setString(2, model.getYearsflag());
			pstm.setString(3, model.getYears_id());
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

	// 带分页功能的Dao
	public List<Years> getAllYearsByPage(String str, Page page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Years> list = new ArrayList<Years>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select *from years  ");
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		sqlStr.append(" order by yearsname desc ");
		sqlStr.append(" limit ?,? ");
		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setInt(1, (page.getCurrentPage() - 1) * page.getCount());
			pstm.setInt(2, page.getCount());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Years model = new Years();
				model.setYears_id(rs.getString("years_id"));
				model.setYearsname(rs.getString("yearsname"));
				model.setYearsflag(rs.getString("yearsflag").equals("1")?"启用":"禁用");
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
		sqlStr.append("select count(1) from years ");
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		sqlStr.append(" order by yearsname desc ");
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
}
