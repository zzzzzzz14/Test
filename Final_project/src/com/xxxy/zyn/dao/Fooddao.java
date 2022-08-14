package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Food;
import com.xxxy.zyn.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;

/**
 * @author zyn
 * @date 2022-06-28-10:46
 */
public class Fooddao {
    /**
     * 根据fname查询food对象
     * @param fname
     */
    public Food FindFood(String fname){
        Connection conn = null;
        Food food = new Food();
        try {
            conn= DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select * from food where fname=? ; ";
            BeanHandler<Food> userBeanListHandler = new BeanHandler<>(Food.class);
            food = queryRunner.query(conn, sql, userBeanListHandler, fname);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
        return food;
    }
}
