package com.xxxy.zyn.dao;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Food;
import com.xxxy.zyn.bean.Orderhistory;
import com.xxxy.zyn.bean.Orders;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.utils.DruidUtils;
import com.xxxy.zyn.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author zyn
 * @date 2022-06-28-17:48
 */
public class Ordersdao {
    public Ordersdao() {
    }

    /**
     * 添加个人订单
     *
     * @param fname
     * @return
     */

    public String addorders(Food food, String username) {
        Connection conn = null;
        int flag = 0;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
//            先查询有没有之前添加过
            String sql = "select count(*) from orders where username=? and fname=?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            Object ans = queryRunner.query(conn, sql, scalarHandler, username, food.getFname());
            long q = (long) ans;
            if (q == 0) {
                String sql1 = "insert into orders(username, fname, num, price) values (?,?,?,?)";
                flag = queryRunner.update(conn, sql1, username, food.getFname(), 1, (int) food.getPrice());
            } else {
                String sql2 = "update orders set num = num+1,total_prices=num*orders.price where username=? and fname=?;";
                flag = queryRunner.update(conn, sql2, username, food.getFname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
        if (flag > 0) return "succ";
        else return "eorr";
    }

    /**
     * 查询我的订单
     *
     * @return select count(*) from orders where username=?;
     */
    public Object getCount(String username) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            String sql = "select count(*) from orders where username=?;";
            QueryRunner queryRunner = new QueryRunner();
            ScalarHandler scalarHandler = new ScalarHandler();
//            ScalarHandler接口返回的值是Object类型;
            Object Count = queryRunner.query(conn, sql, scalarHandler,username);
            return Count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return 0;
    }
    public JSONObject Page(Page page ,String username) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            BeanListHandler<Orders> hander = new BeanListHandler<>(Orders.class);
            String sql = "select * from orders where username=? limit ?,?;";
            List<Orders> list = queryRunner.query(conn, sql, hander,username, (page.getCurrentPage() - 1) * page.getCount(), page.getCount());
            JSONObject json = new JSONObject();
            json.put("code", 0);
            json.put("msg", "");
            Ordersdao dao = new Ordersdao();
            json.put("count", dao.getCount(username));
            json.put("data", list);
            return json;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }

        return null;
    }

    /***
     * 增加菜品数量，一个一个加
     * @param id
     * @return
     *
     */
    public int increase(String id) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "update orders set num = num+1,total_prices=num*orders.price where id = ?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            int flag = queryRunner.update(conn, sql, id);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
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
            String sql = "update orders set num = num-1,total_prices=num*orders.price where id = ?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            int flag = queryRunner.update(conn, sql, id);
            return  flag;

        } catch (Exception e) {
//            e.printStackTrace();
//            如果减到1时，再往下减就是删除
            delete(id);
            return 0;
        }finally {
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
            String sql = "delete from orders where id = ?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            int flag = queryRunner.update(conn, sql, id);
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
        }
    }

    /**
     * 结算订单
     * @return
     */
    public String balance(String username){
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql1 = "select count(*) from orders where username=?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            Object query = queryRunner.query(conn, sql1, scalarHandler, username);
            if((long)query==0){
                return "null";
            }else {
                String sql = "update user set money=money-(select sum(total_prices) from orders where orders.username=?) where username=?";
                int update = queryRunner.update(conn, sql, username, username);
                return "succ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JdbcUtils.rollbackAndClose();
            return "erro";
        }finally {
        }

    }

    /**
     * 把结算过的订单删除并加到历史订单中
     * @param username
     * @return
     */
    public String orderhistory(String username){
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select * from orders where username=?;";
            BeanListHandler<Orders> hander = new BeanListHandler<>(Orders.class);
            List<Orders> list = queryRunner.query(conn, sql, hander, username);
            String fnames="";
            for (Orders orders : list) {
                fnames+=orders.getFname()+"*"+orders.getNum();
            }
            ScalarHandler scalarHandler = new ScalarHandler();
            String sql1 = "select sum(total_prices) from orders where orders.username=?";
            Object money = queryRunner.query(conn, sql1, scalarHandler, username);
            String sql2 = "insert into orderhistory(username, info, ordertime,money)values (?,?,?,?);";
            Date date = new Date();
            queryRunner.update(conn, sql2, username, fnames, date, money);
            String sql3 = "delete from orders where username=?;";
            queryRunner.update(conn, sql3, username);
            return "succ";
        } catch (SQLException e) {
            e.printStackTrace();
            return "erro";
        }finally {
        }
    }

    /**
     * 查询我的历史订单
     *
     * @return select count(*) from orders where username=?;
     */
    public Object getCounthistory(String username) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            String sql = "select count(*) from orderhistory where username=?;";
            QueryRunner queryRunner = new QueryRunner();
            ScalarHandler scalarHandler = new ScalarHandler();
//            ScalarHandler接口返回的值是Object类型;
            Object Count = queryRunner.query(conn, sql, scalarHandler,username);
            return Count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return 0;
    }
    public JSONObject Pagehistory(Page page ,String username) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            BeanListHandler<Orderhistory> hander = new BeanListHandler<>(Orderhistory.class);
            String sql = "select * from orderhistory where username=? limit ?,?;";
            List<Orderhistory> list = queryRunner.query(conn, sql, hander,username, (page.getCurrentPage() - 1) * page.getCount(), page.getCount());
            JSONObject json = new JSONObject();
            json.put("code", 0);
            json.put("msg", "");
            Ordersdao dao = new Ordersdao();
            json.put("count", dao.getCounthistory(username));
            json.put("data", list);
            return json;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }

        return null;
    }
}
