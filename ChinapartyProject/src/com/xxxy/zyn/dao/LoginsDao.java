package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Logins;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Roles;
import com.xxxy.zyn.bean.Userinfo;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zyn
 * @date 2022-06-07-17:54
 */
public class LoginsDao {
    private C3P0Utils c3p0;

    public LoginsDao() {
        //实例化数据库
        c3p0 = new C3P0Utils();
    }

    //同名检测（用户的名字不可相同）
    public boolean isSameName(Logins model, char mFlag) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        conn = c3p0.getConnection();
        StringBuilder sqlStr = new StringBuilder();
        //测试classes中班级名是否相同
        sqlStr.append("select * from logins where loginsName = ? ");
//        修改时的同名检测
        if (mFlag == '1') {
            sqlStr.append("and logins_id!= '" + model.getLogins_id() + "' ");
        }
        boolean flag = false;
        try {
            //建立数据通道
            pstm = conn.prepareStatement(sqlStr.toString());
            //获取班级的名字（设置参数）
            pstm.setString(1, model.getLoginsName());
            //用rs 数据集读取数据
            rs = pstm.executeQuery();
            if (rs.next()) {
                //有同名
                flag = true;
            } else {
                //无同名
                flag = false;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return flag;
    }

    //添加用户信息
    public String addLogins(Logins model) {
        //数据库连接的必要语句
        Connection conn = null;
        PreparedStatement pstm = null;
        StringBuffer sqlStr = new StringBuffer();
        //建立数据库classes
        //写入Classes中的属性（Classes.java)
        sqlStr.append("insert into logins(logins_id, userinfo_id, roles_id, loginsName, loginsPwd, loginsFlag, loginsCDate) ");
        sqlStr.append("values (?,?,?,?,?,?,?);");
        //建立连接
        conn = c3p0.getConnection();
        //直接调用is
        boolean flag = isSameName(model, '0');
        //无重名
        if (flag == false) {
            int num = 0;
            try {
                pstm = conn.prepareStatement(sqlStr.toString());
                //相应导入表中的属性
                pstm.setString(1, model.getLogins_id());
                pstm.setString(2, model.getUserinfo_id());
                pstm.setString(3, model.getRoles_id());
                pstm.setString(4, model.getLoginsName());
                pstm.setString(5, model.getLoginsPwd());
                pstm.setString(6, model.getLoginsFlag());
                //对ClassescDate进行格式化（年-月-日 小时：分：秒）
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pstm.setString(7, f.format(model.getLoginsCDate()));
                //执行
                num = pstm.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                //关闭通道
                //c3p0.close(null, stat, conn);
            }
            //若数据写入成功，则输出"OK",反之为"Err"
            if (num > 0) {
                return "Ok";
            } else {
                return "Err";
            }
        }//若重名，则输出"Same"
        else {
            return "Same";
        }
    }

    //根据用户编号，删除用户数据
    public String deleteLogins(String lid) {
        Connection conn = null;
        PreparedStatement pstm = null;
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("delete from logins ");
        sqlStr.append("where logins_id = ? ;");
        //进行连接
        conn = c3p0.getConnection();
        int num = 0;
        //检测返回的条数
        try {
            pstm = conn.prepareStatement(sqlStr.toString());
            //导入146行的classes_id
            pstm.setString(1, lid);
            //若删除成功 num返回1,则num返回0
            num = pstm.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
        //删除成功返回Ok
        if (num > 0) {
            return "Ok";
        } else {
            return "Err";
        }
    }

    //根据id找用户信息
    public Logins findLoginsById(String lid) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StringBuffer sqlStr = new StringBuffer();
        //sql语句
        sqlStr.append("select * from logins ");
        sqlStr.append("where logins_id = ?;");
        //通道
        conn = c3p0.getConnection();
        Logins model = new Logins();
        try {
            pstm = conn.prepareStatement(sqlStr.toString());
            pstm.setString(1, lid);
            rs = pstm.executeQuery();
            //读取班级信息
            if (rs != null && rs.next()) {
                model.setLogins_id(rs.getString("logins_id"));
                model.setUserinfo_id(rs.getString("userinfo_id"));
                model.setRoles_id(rs.getString("roles_id"));
                model.setLoginsName(rs.getString("loginsName"));
                model.setLoginsPwd(rs.getString("loginsPwd"));
                model.setLoginsFlag(rs.getString("loginsFlag").equals("1") ? "启用" : "禁用");
//                model.setLoginsCDate(rs.getDate("loginsCDate"));//只显示日期
                model.setLoginsCDate(rs.getTimestamp("loginsCDate"));
                //model.setLoginsCDate(rs.getTime("loginsCDate"));//只显示时间
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
        return model;
    }

    //更新用户信息的方法
    public String updateLogins(Logins model){
        Connection conn=null;
        PreparedStatement pstm=null;
        boolean flag = isSameName(model,'1');
        if(flag!=true){
            StringBuilder sqlStr=new StringBuilder();
            //更新sql语句
            sqlStr.append("update logins set userinfo_id=?,roles_id=?,loginsName=?,loginsPwd=?,loginsFlag=?,loginsCDate=? ");
            sqlStr.append("where logins_id=? ;");
            //打开通道
            conn=c3p0.getConnection();
            int num=0;
            try {
                pstm=conn.prepareStatement(sqlStr.toString());
                pstm.setString(1, model.getUserinfo_id());
                pstm.setString(2, model.getRoles_id());
                pstm.setString(3, model.getLoginsName());
                pstm.setString(4, model.getLoginsPwd());
                pstm.setString(5, model.getLoginsFlag());
                //对ClassescDate进行格式化（年-月-日 小时：分：秒）
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pstm.setString(6, f.format(model.getLoginsCDate()));
                pstm.setString(7, model.getLogins_id());
                //执行
                num=pstm.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {

            }
            if(num>0){
                return "Ok";
            }else{
                return "Err";
            }
        }
        else {
            return "Same";
        }
    }

    //分页功能
    public List<Logins> getAllLoginsByPage(String str, Page page) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Logins> list = new ArrayList<Logins>();
        //写sql语句
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("select logins.*,roles.rolesName,userinfo.uiName from logins ,userinfo,roles ");
        //判断输入的数据
        sqlStr.append(" where logins.userinfo_id=userinfo.userinfo_id and roles.roles_id=logins.roles_id ");
        if (str != null && !str.equals("")) {
            sqlStr.append(" " + str + " ");
        }
        sqlStr.append(" limit ?,?;");
        //打开通道
        conn = c3p0.getConnection();
        try {
            pstm = conn.prepareStatement(sqlStr.toString());
            pstm.setInt(1, (page.getCurrentPage()-1)*page.getCount());
            pstm.setInt(2, page.getCount());
            //查询数据
            rs = pstm.executeQuery();
            //获取数据库classes中的内容
            while (rs.next()) {
                Logins model = new Logins();
                model.setLogins_id(rs.getString("logins_id"));
                model.setUserinfo_id(rs.getString("userinfo_id"));
                model.setRoles_id(rs.getString("roles_id"));
                model.setLoginsName(rs.getString("loginsName"));
                model.setLoginsPwd(rs.getString("loginsPwd"));
                model.setLoginsFlag(rs.getString("loginsFlag").equals("1") ? "启用" : "禁用");
//                model.setLoginsCDate(rs.getDate("loginsCDate"));//只显示日期
                model.setLoginsCDate(rs.getTimestamp("loginsCDate"));
                //model.setLoginsCDate(rs.getTime("loginsCDate"));//只显示时间
                model.setRolesName(rs.getString("rolesName"));
                model.setUiName(rs.getString("uiName"));
                list.add(model);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
        //返回对象
        return list;
    }
    public int getCount(String str){
        StringBuffer sqlStr=new StringBuffer();
        Connection conn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;
        int total=0;
        sqlStr.append("select count(1) from logins ");
        if(str!=null&&!str.equals("")){
            sqlStr.append(" where 1=1 "+str+" ");
        }
        sqlStr.append(" order by logins_id desc ");
        conn=c3p0.getConnection();
        try {
            pstm=conn.prepareStatement(sqlStr.toString());
            rs=pstm.executeQuery();
            if(rs.next()){
                total=rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{

        }
        return total;
    }
    //下拉列表获取元素
//    角色信息
    public List<Roles> getRoles(){
        List<Roles> list = new ArrayList<Roles>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select * from roles ");
        conn=c3p0.getConnection();
        try {
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()){
                Roles model = new Roles();
                model.setRoles_id(rs.getString("roles_id"));
                model.setRolesName(rs.getString("rolesName"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return list;
    }
//    下拉列表userinfo信息
    public List<Userinfo> getUserinfo(){
        List<Userinfo> list = new ArrayList<Userinfo>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select * from userinfo ");
        conn=c3p0.getConnection();
        try {
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()){
                Userinfo model = new Userinfo();
                model.setUserinfo_id(rs.getString("userinfo_id"));
                model.setUiName(rs.getString("uiName"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
        return list;
    }

    public String checkLogin(Logins logins){
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select * from logins where loginsName=? and loginsPwd=? and loginsFlag='1' ");
        conn=c3p0.getConnection();
        String res = null;
        try {
            pstm=conn.prepareStatement(sql.toString());
            pstm.setString(1,logins.getLoginsName());
            pstm.setString(2,logins.getLoginsPwd());
            rs = pstm.executeQuery();
            if(rs.next())
                res="Ok";
            else
                return "Err";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
