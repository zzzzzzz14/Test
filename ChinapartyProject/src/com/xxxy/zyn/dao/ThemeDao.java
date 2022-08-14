package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.theme;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ThemeDao {
	private C3P0Utils c3p0;

	public ThemeDao() {
		// TODO Auto-generated constructor stub
		c3p0 = new C3P0Utils();
	}

	
	public String addThemeTitle(theme model) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("insert into theme(theme_id,themeTitle,themeFlag,themeCDate) values(?,?,?,?) ");
		conn = c3p0.getConnection();
		boolean flag = isSameName(model, '0');
		if (flag == false) {
			int num = 0;
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, model.getTheme_id());
				pstm.setString(2, model.getThemeTitle());
				pstm.setString(3, model.getThemeFlag());
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pstm.setString(4, f.format(model.getThemeCDate()));

				num = pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				// c3p0.close(null, pstm, conn);
			}
			if (num > 0) {
				return "OK";
			} else {
				return "Err";
			}
		} else {
			return "Same";
		}

	}

	public boolean isSameName(theme model, char mFlag) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		conn = c3p0.getConnection();
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select * from theme where themeTitle=? ");
		if (mFlag == '1') {
			sqlStr.append(" and theme_id!='" + model.getTheme_id() + "' ");
		}
		boolean flag = false;
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, model.getThemeTitle());
			rs = pstm.executeQuery();
			if (rs.next()) {
				flag = true;
			} else {
				flag = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public theme findThemeById(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select * from theme ");
		sqlStr.append("where theme_id=? ");
		conn = c3p0.getConnection();
		theme model = new theme();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cid);
			rs = pstm.executeQuery();
			if (rs != null && rs.next()) {

				model.setTheme_id(rs.getString("theme_id"));
				model.setThemeTitle(rs.getString("themeTitle"));
				model.setThemeFlag(rs.getString("themeFlag"));
				// cls.setClassesCDate(rs.getDate("classesCDate"));
				model.setThemeCDate(rs.getTimestamp("themeCDate"));
				// cls.setClassesCDate(rs.getTime("classesCDate"));

				// cls.setYearsname(rs.getString("yearsname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return model;
	}

	
	public String deleteTheme(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("delete from theme ");
		sqlStr.append("where theme_id=? ");
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
			return "OK";
		} else {
			return "Err";
		}
	}


	public String updateTheme(theme model) {
		Connection conn = null;
		PreparedStatement pstm = null;
		boolean flag = isSameName(model, '1');
		if (flag != true) {
			StringBuffer sqlStr = new StringBuffer();
			sqlStr.append("update theme set themeTitle=?,themeFlag=?,");
			sqlStr.append("themeCDate=? ");
			sqlStr.append("where theme_id=? ");
			conn = c3p0.getConnection();
			int num = 0;
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, model.getThemeTitle());
				pstm.setString(2, model.getThemeFlag());
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				pstm.setString(3, f.format(model.getThemeCDate()));

				pstm.setString(4, model.getTheme_id());
				num = pstm.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (num > 0) {
				return "OK";
			} else {
				return "Err";
			}

		} else {
			return "Same";
		}
	}


	public List<theme> getAllThemeByPage(String str, Page page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<theme> list = new ArrayList<theme>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select theme_id,themeTitle,case themeFlag when '0' then '未测试' ");
		sqlStr.append("when '1' then '开启测试' when '2' then '已测试' end themeFlag,");
		sqlStr.append("themeCDate from theme ");

		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		sqlStr.append(" order by themeTitle desc ");
		sqlStr.append(" limit ?,? ");
		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setInt(1, (page.getCurrentPage() - 1) * page.getCount());
			pstm.setInt(2, page.getCount());
			rs = pstm.executeQuery();
			while (rs.next()) {
				theme model = new theme();
				model.setTheme_id(rs.getString("theme_id"));
				model.setThemeTitle(rs.getString("themeTitle"));

				model.setThemeFlag(rs.getString("themeFlag"));

				// model.setThemeCDate(rs.getDate("themeCDate"));
				model.setThemeCDate(rs.getTimestamp("themeCDate"));
				// model.setDepartmentCDate(rs.getTime("classesCDate"));
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return list;
	}

	public int getCount(String str) {
		StringBuffer sqlStr = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int total = 0;
		sqlStr.append("select count(1) from theme ");
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		sqlStr.append(" order by themeTitle desc ");
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
	public List<theme> getThemes(){
		List<theme> list = new ArrayList<theme>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from theme  ");
		conn=c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while (rs.next()){
				theme model = new theme();
				model.setTheme_id(rs.getString("theme_id"));
				model.setThemeTitle(rs.getString("themeTitle"));
				list.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {

		}
		return list;
	}
}