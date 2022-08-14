package com.xxxy.zyn.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Utils {
	private static ComboPooledDataSource ds = null;
	/**
	 * 定义一个ThreadLocal,绑定Connection，每个线程对应一个Connection,执行事务使用
	 */
	private static ThreadLocal<Connection> tLocal = new ThreadLocal<>();
	static {
		ds = new ComboPooledDataSource();
	}

	public static DataSource getDataSource() {
		return ds;
	}

	public static Connection getConnection() {
		Connection con = tLocal.get();
		if (con == null) {
			try {
				con = ds.getConnection();
				tLocal.set(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	/**
	 * 
	 * @return 获取当前线程绑定的Connection
	 * @throws SQLException
	 */
	public static Connection getTranConnection() throws SQLException {
		// 得到ThreadLocal中的connection
		Connection conn = tLocal.get();
		// 判断conn是否为空，如果不为空，则说明事务已经开启
		if (conn == null) {
			conn = getConnection();
			// 把当前开启的事务放入ThreadLocal中
			tLocal.set(conn);
		}
		return conn;
	}

	/**
	 * 开启事务，如果当前线程中没有Connection，则创建该线程对应的一个Connection
	 * 
	 * @throws SQLException
	 */
	public static void beginTran() throws SQLException {
		// 设置事务提交为手动
		getTranConnection().setAutoCommit(false);
	}

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public static void commit() throws SQLException {
		// 得到ThreadLocal中的connection
		Connection conn = getTranConnection();
		// 判断conn是否为空，如果为空，则说明没有开启事务
		if (conn != null) {
			// 如果conn不为空,提交事务
			conn.commit();
			// 事务提交后，关闭连接
			conn.close();
			// 将连接移出ThreadLocal
			tLocal.remove();
		}
	}

	/**
	 * 回滚事务
	 * 
	 * @throws SQLException
	 */
	public static void rollback() throws SQLException {
		// 得到ThreadLocal中的connection
		Connection conn = getTranConnection();
		// 判断conn是否为空，如果为空，则说明没有开启事务，也就不能回滚事务
		if (conn != null) {
			// 事务回滚
			conn.rollback();
			// 事务回滚后，关闭连接
			conn.close();
			// 将连接移出ThreadLocal
			tLocal.remove();
		}
	}

	// 定义一个方法用来释放资源
	public static void close(ResultSet rs, Statement stat, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
