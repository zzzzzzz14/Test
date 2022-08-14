package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Post;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
	private C3P0Utils c3p0;

	public PostDao() {
		c3p0 = new C3P0Utils();
	}

	// 添加职务信息
	public String addpost(Post cls) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("insert into post(post_id,postName, postCDate) values (?,?,?) ");
		conn = c3p0.getConnection();
		// 同名检测
		boolean flag = isSameName(cls, '0');
		if (flag == false) {
			int num = 0;
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, cls.getPost_id());
				pstm.setString(2, cls.getPostName());
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pstm.setString(3, f.format(cls.getPostCDate()));
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
	public boolean isSameName(Post model, char mFlag) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		conn = c3p0.getConnection();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("select * from post where postName=?");
		boolean flag = false;
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, model.getPostName());
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

	// 列出部门信息
	public List<Post> getAllPost(String str) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Post> list = new ArrayList<Post>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("SELECT * FROM post ");
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}

		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Post model = new Post();
				model.setPost_id(rs.getString("post_id"));
				model.setPostName(rs.getString("postName"));
				// model.setDepartmentCDate(rs.getDate("departmentCDate"));//
				// 只有日期
				model.setPostCDate(rs.getTimestamp("postCDate"));
				// cls.setDepartmentCDate(rs.getTime("departmentCDate"));
				list.add(model);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

		return list;
	}

	// 根据职务编号删除职务信息
	public String deletePost(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("delete from post ");
		sqlStr.append("where post_id=? ");
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

	// 根据ID找部门信息
	public Post findPostById(String cid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append("select  *  ");
		sqlStr.append(" from post ");
		sqlStr.append(" where post_id=? ");
		conn = c3p0.getConnection();
		Post model = new Post();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cid);
			rs = pstm.executeQuery();
			if (rs != null && rs.next()) {
				model.setPost_id(rs.getString("post_id"));
				model.setPostName(rs.getString("postName"));
				// model.setPostCDate(rs.getDate("postCDate"));// 只有日期
				model.setPostCDate(rs.getTimestamp("postCDate"));
				// model.setPostCDate(rs.getTime("postCDate"));//只有时间
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
		return model;
	}

	// 更新信息
	public String updatePost(Post model) {
		Connection conn = null;
		PreparedStatement pstm = null;
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append(" update post set postName=?,postCDate=? ");
		sqlStr.append(" where post_id=? ");
		conn = c3p0.getConnection();
		int num = 0;
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, model.getPostName());
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstm.setString(2, f.format(model.getPostCDate()));
			pstm.setString(3, model.getPost_id());

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

	// 带分页的部门列表
	public List<Post> getAllPostByPage(String str, Page page) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<Post> list = new ArrayList<Post>();
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("SELECT * from post ");
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		sqlStr.append(" order by post_id desc ");
		sqlStr.append(" limit ?,? ");
		conn = c3p0.getConnection();
		try {
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm = conn.prepareStatement(sqlStr.toString());
			pstm.setInt(1, (page.getCurrentPage() - 1) * page.getCount());
			pstm.setInt(2, page.getCount());
			rs = pstm.executeQuery();
			while (rs.next()) {
				Post model = new Post();
				model.setPost_id(rs.getString("post_id"));
				model.setPostName(rs.getString("postName"));
				model.setPostCDate(rs.getTimestamp("postCDate"));
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
		sqlStr.append(" select count(1) from post  ");
		if (str != null && !str.equals("")) {
			sqlStr.append(" where 1=1 " + str + " ");
		}
		sqlStr.append(" order by postName desc ");
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
