package com.zyn.dao;

import com.zyn.servlet.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zyn
 * @date 2022-05-26-18:18
 */
public class Goodsdao {
    public Goodsdao() {
    }

    /***
     * 增加菜品数量，一个一个加
     * @param id
     * @return
     */
    public int increase(String id){
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "update goods set num = num+1,total_prices=num*goods.price where id = ?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            int flag = queryRunner.update(conn,sql,id);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();return -1;
        }finally {
            DruidUtils.close(conn,null,null);
        }

    }

    /**
     * 减少菜品数量，减到1，数据库check抛出1064，再调用delete删除
     * @param id
     * @return
     */
    public int reduce(String id){
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "update goods set num = num-1,total_prices=num*goods.price where id = ?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            int flag = queryRunner.update(conn, sql, id);
            return  flag;

        } catch (Exception e) {
//            e.printStackTrace();
//            如果减到1时，再往下减就是删除
            delete(id);
            return 0;
        }finally {
            DruidUtils.close(conn,null,null);
        }
    }

    /***
     * 删除单行菜品
     * @param id
     * @return
     */
    public int delete(String id){
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "delete from goods where id = ?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            int flag = queryRunner.update(conn, sql, id);
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
        }

    }

}
