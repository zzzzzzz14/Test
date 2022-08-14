package com.xxxy.zyn.dao;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Process;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProcessDao {
	private C3P0Utils c3p0;

	public ProcessDao() {
		c3p0 = new C3P0Utils();
	}

	
	public String addProcess(Process pcs) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(
				"insert into Process(process_id,processtype_id,processDate,processPlace,processHandler,processContent,");
		sqlStr.append("processTime,logins_id) values(?,?,?,?,?,?,?,?) ");
		conn = c3p0.getConnection();
		
		boolean flag = isSameName(pcs);
		if (flag == false) {
			int num = 0;
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, pcs.getProcess_id());
				pstm.setString(2, pcs.getProcesstype_id());
				pstm.setString(3, pcs.getProcessDate());
				pstm.setString(4, pcs.getProcessPlace());
				pstm.setString(5, pcs.getProcessHandler());
				pstm.setString(6, pcs.getProcessContent());
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pstm.setString(7, f.format(pcs.getProcessTime()));
				pstm.setString(8, pcs.getLogins_id());
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
			return "Same";
		}
	}

	
	public boolean isSameName(Process pcs) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		conn = c3p0.getConnection();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select * from process where process_id=?");
		boolean flag = false;
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, pcs.getProcess_id());
			rs = pstm.executeQuery();
			if (rs.next()) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated actch block
			e.printStackTrace();
		}
		return flag;
	}


	public List<Process> getAllProcess(String str) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
	
		List<Process> list = new ArrayList<Process>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("SELECT * FROM process c ");
		/* sqlStr.append("LEFT JOIN years y on c.years_id=y.years_id "); */
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		conn = c3p0.getConnection();
		try {
		
			pstm = conn.prepareStatement(sqlStr.toString());

			rs = pstm.executeQuery();
			while (rs.next()) {
				Process cls = new Process();
				
				cls.setProcess_id(rs.getString("process_id"));
				cls.setProcesstype_id(rs.getString("processtype_id"));
				cls.setProcessDate(rs.getString("processDate"));
				cls.setProcessPlace(rs.getString("processPlace"));
				cls.setProcessHandler(rs.getString("processHandler"));
				cls.setProcessContent(rs.getString("processContent"));
				cls.setProcessTime(rs.getTimestamp("processTime"));
				cls.setLogins_id(rs.getString("logins_id"));
				
				list.add(cls);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return list;
	}

	public String deleteProcess(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("delete from process ");
		sqlStr.append("where process_id=? ");
	
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


	public Process findProcessById(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select * from process ");
		sqlStr.append("where process_id=?");

		conn = c3p0.getConnection();
	
		Process cls = new Process();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cid);
			
			rs = pstm.executeQuery();
		
			if (rs != null && rs.next()) {
				
				cls.setProcess_id(rs.getString("process_id"));
				cls.setProcesstype_id(rs.getString("processtype_id"));
				cls.setProcessDate(rs.getString("processDate"));
				cls.setProcessPlace(rs.getString("processPlace"));
				cls.setProcessHandler(rs.getString("processHandler"));
				cls.setProcessContent(rs.getString("processContent"));
				cls.setProcessTime(rs.getTimestamp("processTime"));
				cls.setLogins_id(rs.getString("logins_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return cls;
	}

	
	public String updateProcess(Process cls) {
		Connection conn = null;
		PreparedStatement pstm = null;
		

		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("update process set processtype_id=?,processDate=?,processPlace=?,"
				+ "processHandler=?,processContent=?,processTime=?,logins_id=? where process_id=? ");

	
		conn = c3p0.getConnection();
		int num = 0;
		try {
			
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cls.getProcesstype_id());
			pstm.setString(2, cls.getProcessDate());
			pstm.setString(3, cls.getProcessPlace());
			pstm.setString(4, cls.getProcessHandler());
			pstm.setString(5, cls.getProcessContent());
			
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstm.setString(6, f.format(cls.getProcessTime()));
			pstm.setString(7, cls.getLogins_id());
			pstm.setString(8, cls.getProcess_id());
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


	public List<Process> getAllProcessPage(String str, Page page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Process> list = new ArrayList<Process>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("SELECT p.*,pt.ptypeName FROM process p ");
		sqlStr.append("LEFT JOIN processtype pt on pt.processtype_id=p.processtype_id ");
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		sqlStr.append(" order by p.processTime desc ");
		sqlStr.append(" limit ?,? ");
		conn = c3p0.getConnection();
		System.out.println(sqlStr);
		System.out.println(str);
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setInt(1, (page.getCurrentPage() - 1) * page.getCount());
			pstm.setInt(2, page.getCount());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Process pcs = new Process();
				pcs.setProcess_id(rs.getString("process_id"));
				pcs.setProcesstype_id(rs.getString("processtype_id"));
				pcs.setProcessDate(rs.getString("processDate"));
				pcs.setProcessPlace(rs.getString("processPlace"));
				pcs.setProcessHandler(rs.getString("processHandler"));
				pcs.setProcessContent(rs.getString("processContent"));
				pcs.setProcessTime(rs.getTimestamp("processTime"));
				pcs.setLogins_id(rs.getString("logins_id"));
				pcs.setPtypeName(rs.getString("ptypeName"));
				list.add(pcs);
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
		sqlStr.append("select count(1) from process p ");
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		sqlStr.append(" order by processtype_id desc ");
		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			rs = pstm.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return total;
	}
}
