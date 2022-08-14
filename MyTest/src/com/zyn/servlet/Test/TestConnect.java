package com.zyn.servlet.Test;

import com.zyn.servlet.utils.DruidUtils;
import com.zyn.servlet.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zyn
 * @date 2022-05-15-17:24
 */
public class TestConnect {
    @Test
    public void TestConnect() throws Exception {
        Connection conn = DruidUtils.getConnection();
        System.out.println(conn);
    }
    @Test
    public void Testupdata(){
        QueryRunner queryRunner = new QueryRunner();
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            String sql = "insert into goods(username, Goodname, num, price) values (?,?,?,?);";
            int update = queryRunner.update(conn, sql, "张张", "鱼香肉丝", 1, 15);
            System.out.println(update);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtils.close(conn,null,null);
        }

    }
    @Test
    public void TestConnJDBC(){
        Connection connection = JdbcUtils.getConnection();
        System.out.println(connection);
    }
    @Test
    public void Testreduce(){
        try {
            Connection conn = JdbcUtils.getConnection();
            String sql = "delete from goods where id = 4;";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(conn,sql);
//            int a = 2/0;
            JdbcUtils.commitAndClose();
        } catch (SQLException e) {
            JdbcUtils.rollbackAndClose();
        }

    }
}
