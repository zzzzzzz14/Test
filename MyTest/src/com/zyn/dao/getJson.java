package com.zyn.dao;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zyn.bean.Goods;
import com.zyn.bean.Page;
import com.zyn.bean.customers;
import com.zyn.servlet.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.*;
import java.util.HashMap;
import java.util.List;

/**
 * @author zyn
 * @date 2022-05-18-10:43
 */
public class  getJson {

    public getJson() {
        super();
    }
    public JsonObject select() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DruidUtils.getConnection();
            String sql = "select * from customers";
            ps = conn.prepareStatement(sql);
            rs=ps.executeQuery();
            if(rs == null){
                return  null;
            }
            Gson gson = new Gson();
            HashMap<String, String> hm = new HashMap<String, String>();
            JsonArray jsonarray = new JsonArray();
            JsonObject json = new JsonObject();
            String Shm = null;
            json.addProperty("code", 0);
            json.addProperty("msg", "");
            json.addProperty("count", 1000);
            while (rs.next()){
                ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
                int icolnum = rsmd.getColumnCount();
                for (int i = 1; i <= icolnum; i++) {
                    hm.put(rsmd.getColumnName(i).toString(), rs.getString(i));
                }
                jsonarray.add(gson.toJsonTree(hm));
//                System.out.println(jsonarray);
            }
            json.add("data",jsonarray);
//            System.out.println(json);
            return json;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtils.close(conn,rs,ps);
        }
        return null;
    }
    public JSONObject select_(){
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            String sql = "select * from customers";
            QueryRunner queryRunner = new QueryRunner();
            BeanListHandler<customers> hander = new BeanListHandler<>(customers.class);
            List<customers> list = queryRunner.query(conn, sql, hander);
            JSONObject json = new JSONObject();
            json.put("code",0);
            json.put("msg","");
            json.put("count",28);
            json.put("data",list);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            DruidUtils.close(conn,null,null);
        }
        return null;
    }
    public Object getCount() {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            String sql = "select count(*) from Goods";
            QueryRunner queryRunner = new QueryRunner();
            ScalarHandler scalarHandler = new ScalarHandler();
//            ScalarHandler接口返回的值是Object类型;
            Object  Count= queryRunner.query(conn, sql, scalarHandler);
            return Count;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DruidUtils.close(conn,null,null);
        }
        return 0;
    }
    public JSONObject Page(Page page){
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            BeanListHandler<Goods> hander = new BeanListHandler<>(Goods.class);
            String sql = "select * from Goods limit ?,?;";
            List<Goods> list = queryRunner.query(conn, sql, hander, (page.getCurrentPage() - 1) * page.getCount(), page.getCount());
            JSONObject json = new JSONObject();
            json.put("code",0);
            json.put("msg","");
            getJson getJson = new getJson();
            json.put("count",getJson.getCount());
            json.put("data",list);
            return json;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
