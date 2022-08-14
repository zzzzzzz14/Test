package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Department;
import com.xxxy.zyn.bean.Page;
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
 * @date 2022-05-26-8:17
 */
public class DepartmentDao {
    private C3P0Utils c3p0;

    public DepartmentDao() {
        //实例化数据库
        c3p0 = new C3P0Utils();
    }

    //添加部门信息
    public String addDepartment(Department dep) {
        //数据库连接的必要语句
        Connection conn = null;
        PreparedStatement pstm = null;
        StringBuffer sqlStr = new StringBuffer();
        //建立数据库classes
        //写入Classes中的属性（Classes.java)
        sqlStr.append("insert into department(department_id, departmentName, departmentPid, departmentFlag, departmentCDate) ");
        sqlStr.append("values (?,?,?,?,?);");
        //建立连接
        conn = c3p0.getConnection();
        //直接调用is
        boolean flag = isSameName(dep, '0');
        //无重名
        if (flag == false) {
            int num = 0;
            try {
                pstm = conn.prepareStatement(sqlStr.toString());
                //相应导入表中的属性
                pstm.setString(1, dep.getDepartment_id());
                pstm.setString(2, dep.getDepartmentName());
                pstm.setString(3, dep.getDepartmentPid());
                pstm.setString(4, dep.getDepartmentFlag());
                //对ClassescDate进行格式化（年-月-日 小时：分：秒）
                SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pstm.setString(5, f.format(dep.getDepartmentCDate()));
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

    //同名检测（班级的名字不可相同）
    public boolean isSameName(Department dep, char mFlag) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        conn = c3p0.getConnection();
        StringBuilder sqlStr = new StringBuilder();
        //测试classes中班级名是否相同
        sqlStr.append("select * from department where departmentName=? ");
//        修改时的同名检测
        if (mFlag == '1') {
            sqlStr.append("and department_id!= '" + dep.getDepartment_id() + "' ");
        }
        boolean flag = false;
        try {
            //建立数据通道
            pstm = conn.prepareStatement(sqlStr.toString());
            //获取班级的名字（设置参数）
            pstm.setString(1, dep.getDepartmentName());
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

    //    列出部门信息
    public List<Department> getAllDepartment(String str) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList<Department>();
        //写sql语句
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("select d1.*,d2.departmentName pdname ");
        sqlStr.append("from department d1 ");
        sqlStr.append("left join department d2 on d1.departmentPid=d2.department_id ");
        //判断输入的数据
        if (str != null && !str.equals("")) {
            sqlStr.append(" where 1=1 " + str + " ");
        }
        //打开通道
        conn = c3p0.getConnection();
        try {
            pstm = conn.prepareStatement(sqlStr.toString());
            //查询数据
            rs = pstm.executeQuery();
            //获取数据库classes中的内容
            while (rs.next()) {
                Department dep = new Department();
                //获取数据库classes中的classes_id信息
                dep.setDepartment_id(rs.getString("department_id"));
                dep.setDepartmentName(rs.getString("departmentName"));
                dep.setDepartmentFlag(rs.getString("departmentFlag").equals("1") ? "启用" : "禁用");
                dep.setDepartmentCDate(rs.getDate("departmentCDate"));//只显示日期
                //cls.setClassesCDate(rs.getTimestamp("classesCDate"));
                //cls.setClassesCDate(rs.getTime("classesCDate"));//只显示时间
                dep.setDepartmentPid(rs.getString("departmentPid"));
                dep.setPdname(rs.getString("pdname"));
                //将以上信息放在列表里
                list.add(dep);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
        //返回对象
        return list;
    }

    //根据部门编号，删除部门数据
    public String deleteDepartment(String did) {
        Connection conn = null;
        PreparedStatement pstm = null;
        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append("delete from department ");
        sqlStr.append("where department_id=?");
        //进行连接
        conn = c3p0.getConnection();
        int num = 0;
        //检测返回的条数
        try {
            pstm = conn.prepareStatement(sqlStr.toString());
            //导入146行的classes_id
            pstm.setString(1, did);
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

    //根据id找部门信息
    public Department findDepartmentById(String did) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StringBuffer sqlStr = new StringBuffer();
        //sql语句
        sqlStr.append("select d1.*,d2.departmentName pdname ");
        sqlStr.append("from department d1 ");
        sqlStr.append("left join department d2 on d1.departmentPid=d2.department_id ");
        sqlStr.append("where d1.department_id=? ");
        //通道
        conn = c3p0.getConnection();
        Department dep = new Department();
        try {
            pstm = conn.prepareStatement(sqlStr.toString());
            pstm.setString(1, did);
            rs = pstm.executeQuery();
            //读取班级信息
            if (rs != null && rs.next()) {
                dep.setDepartment_id(rs.getString("department_id"));
                dep.setDepartmentName(rs.getString("departmentName"));
                dep.setDepartmentFlag(rs.getString("departmentFlag").equals("1") ? "启用" : "禁用");
//                dep.setDepartmentCDate(rs.getDate("departmentCDate"));//只显示日期
                dep.setDepartmentCDate(rs.getTimestamp("departmentCDate"));
                //cls.setClassesCDate(rs.getTime("classesCDate"));//只显示时间
                //dep.setDepartmentPid(rs.getString("departmentPid"));
                dep.setPdname(rs.getString("pdname"));
                //cls.setYearsname(rs.getString("yearsname"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }
        return dep;
    }
    //更新部门信息的方法
    public String updateDepartment(Department dep){
        Connection conn=null;
        PreparedStatement pstm=null;
        boolean flag = isSameName(dep,'1');
        if(flag!=true){
            StringBuilder sqlStr=new StringBuilder();
            //更新sql语句
            sqlStr.append("update department set departmentName = ?,departmentFlag=?,departmentCDate=?,departmentPid=? ");
            sqlStr.append("where department_id = ? ;");
            //打开通道
            conn=c3p0.getConnection();
            int num=0;
            try {
                pstm=conn.prepareStatement(sqlStr.toString());
                pstm.setString(1,dep.getDepartmentName());
                pstm.setString(2, dep.getDepartmentFlag());
                //对ClassescDate进行格式化（年-月-日 小时：分：秒）
                SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pstm.setString(3, f.format(dep.getDepartmentCDate()));
                pstm.setString(4, dep.getDepartmentPid());
                pstm.setString(5,dep.getDepartment_id());
                //执行命令
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
    public List<Department> getAllDepartmentByPage(String str, Page page) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList<Department>();
        //写sql语句
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("select d1.*,d2.departmentName pdname ");
        sqlStr.append("from department d1 ");
        sqlStr.append("left join department d2 on d1.departmentPid=d2.department_id ");
        //判断输入的数据
        if (str != null && !str.equals("")) {
            sqlStr.append(" where 1=1 " + str + " ");
        }
        sqlStr.append(" order by d1.departmentName desc ");
        sqlStr.append(" limit ?,?;");
        //打开通道
        conn = c3p0.getConnection();
        System.out.println(str);
        try {
            pstm = conn.prepareStatement(sqlStr.toString());
            pstm.setInt(1, (page.getCurrentPage()-1)*page.getCount());
            pstm.setInt(2, page.getCount());
            //查询数据
            rs = pstm.executeQuery();
            //获取数据库classes中的内容
            while (rs.next()) {
                Department dep = new Department();
                //获取数据库classes中的classes_id信息
                dep.setDepartment_id(rs.getString("department_id"));
                dep.setDepartmentName(rs.getString("departmentName"));
                dep.setDepartmentFlag(rs.getString("departmentFlag").equals("1") ? "启用" : "禁用");
               // dep.setDepartmentCDate(rs.getDate("departmentCDate"));//只显示日期
                dep.setDepartmentCDate(rs.getTimestamp("departmentCDate"));
                //cls.setClassesCDate(rs.getTime("classesCDate"));//只显示时间
                dep.setDepartmentPid(rs.getString("departmentPid"));
                dep.setPdname(rs.getString("pdname"));
                //将以上信息放在列表里
                list.add(dep);
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
        sqlStr.append("select count(1) from department ");
        if(str!=null&&!str.equals("")){
            sqlStr.append(" where 1=1 "+str+" ");
        }
        sqlStr.append(" order by departmentName desc ");
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
    public List<Department> getDepts(){
        List<Department> list = new ArrayList<Department>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        sql.append("select * from department where departmentFlag ='1'  ");
        sql.append("order by departmentName ASC ");
        conn=c3p0.getConnection();
        try {
            pstm = conn.prepareStatement(sql.toString());
            rs = pstm.executeQuery();
            while (rs.next()){
                Department model = new Department();
                model.setDepartment_id(rs.getString("department_id"));
                model.setDepartmentName(rs.getString("departmentName"));
                list.add(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

        }
        return list;
    }
}
