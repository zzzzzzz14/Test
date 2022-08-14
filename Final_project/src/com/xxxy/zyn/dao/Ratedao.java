package com.xxxy.zyn.dao;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Rate;
import com.xxxy.zyn.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zyn
 * @date 2022-06-29-10:13
 */
public class Ratedao {
    public Ratedao() {
    }
    public String addrate(Rate rate) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "insert into rate(username, evaluate, evaluateValue, ratedate) values (?,?,?,?);";
            int update = queryRunner.update(conn, sql, rate.getUsername(), rate.getEvaluate(), rate.getEvaluateValue(), rate.getRatedate());
            return "succ";
        } catch (Exception e) {
            e.printStackTrace();
            return "erro";
        } finally {
        }
    }

    /**
     * 查询我的评论
     *
     * @return select count(*) from orders where username=?;
     */
    public Object getCount(String username) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            String sql = "select count(*) from rate where username=?;";
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
    public JSONObject Page(Page page , String username) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            BeanListHandler<Rate> hander = new BeanListHandler<>(Rate.class);
            String sql = "select * from rate where username=? limit ?,?;";
            List<Rate> list = queryRunner.query(conn, sql, hander,username, (page.getCurrentPage() - 1) * page.getCount(), page.getCount());
            JSONObject json = new JSONObject();
            json.put("code", 0);
            json.put("msg", "");
            Ratedao dao = new Ratedao();
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
     * 删除评价
     * @param id
     * @return
     */
    public String delete(String id){
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "delete from rate where id = ?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            int flag = queryRunner.update(conn, sql, id);
            return "succ";
        } catch (SQLException e) {
            e.printStackTrace();
            return "erro";
        } finally {
        }
    }

    /**
     * 查询热门评论
     *
     * @return select count(*) from orders where username=?;
     */
    public Object getCounthot() {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            String sql = "select count(*) from rate where evaluateValue>4;";
            QueryRunner queryRunner = new QueryRunner();
            ScalarHandler scalarHandler = new ScalarHandler();
//            ScalarHandler接口返回的值是Object类型;
            Object Count = queryRunner.query(conn, sql, scalarHandler);
            return Count;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return 0;
    }
    public JSONObject Pagehot(Page page ) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            BeanListHandler<Rate> hander = new BeanListHandler<>(Rate.class);
            String sql = "select * from rate where  evaluateValue>4 limit ?,?;";
            List<Rate> list = queryRunner.query(conn, sql, hander, (page.getCurrentPage() - 1) * page.getCount(), page.getCount());
            JSONObject json = new JSONObject();
            json.put("code", 0);
            json.put("msg", "");
            Ratedao dao = new Ratedao();
            json.put("count", dao.getCounthot());
            json.put("data", list);
            return json;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return null;
    }
}
