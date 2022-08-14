package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.User;
import com.xxxy.zyn.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author zyn
 * @date 2022-06-03-16:00
 */
public class Userdao {
    public Userdao() {
    }
    /*****
     * 查找相同username
     */
    public String findusername(User user){
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            ScalarHandler scalarHandler = new ScalarHandler();
            String sql = "select count(1) from user where username=?";
            Object query = queryRunner.query(conn, sql, scalarHandler, user.getUsername());
            if((long)query==1)
                return "用户名不可用";
            else {
                return "用户名可用";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return "";
    }

    /***
     * 添加用户
     * @param user
     */
    public String addUser(User user){
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "insert into user( username, userpass, phone,money)values(?,?,?,0);";
            queryRunner.update(conn,sql,user.getUsername(),user.getUserpass(),user.getPhone());
            return "添加成功";
        } catch (SQLException e) {
            e.printStackTrace();
            return "添加失败";
        }finally {
        }

    }

    /**
     * 登录login页面
     * @param user
     * @return
     */
    public String login(User user){
        Connection conn = null;
        try {
            conn=DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select count(*) from user where username=? and userpass=?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            Object query = queryRunner.query(conn, sql, scalarHandler, user.getUsername(), user.getUserpass());
            if((long)query==1){
                return "登录成功";
            }
            else if((long)query==0){
                String findusername = findusername(user);
                if(findusername=="用户名不可用"){
                    return "密码错误";
                }else {
                    return "用户不存在";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
        }
        return "";
    }

    /***
     * 根据用户名查找User对象
     * @return
     */
    public User findUser(String usrname){
        Connection conn = null;
        User user = new User();
        try {
            conn=DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select * from user where username=?";
            BeanHandler<User> userBeanListHandler = new BeanHandler<>(User.class);
            user = queryRunner.query(conn, sql, userBeanListHandler, usrname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /***
     * 根据bean对象修改用户属性
     * @param user
     * @return
     */
    public String updateuser (User user){
        Connection conn = null;
        int update=0;
        try {
            conn=DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "update user set userpass=?,phone=? where username=?;";
            ScalarHandler scalarHandler = new ScalarHandler();
            update = queryRunner.update(conn, sql, user.getUserpass(), user.getPhone(), user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(update>0) return "succ";
        else return "err";
    }

    /**
     * 根据用户名修改用户金额
     * @return
     */
    public String updatemoney(int money,String username){
        Connection conn = null;
        int update=0;
        try {
            conn=DruidUtils.getConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "update user set money=money+? where username=?";
            ScalarHandler scalarHandler = new ScalarHandler();
            update = queryRunner.update(conn, sql, money, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(update>0) return "succ";
        else return "err";
    }
}
