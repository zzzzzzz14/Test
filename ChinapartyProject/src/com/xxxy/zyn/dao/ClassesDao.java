package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Classes;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClassesDao {
	private C3P0Utils c3p0;
	public ClassesDao(){
		//实例化数据库
		c3p0=new C3P0Utils();
	}
	//添加班级信息
	public String addClasses(Classes cls){
		//数据库连接的必要语句
		Connection conn=null;
		PreparedStatement pstm=null;
		StringBuilder sqlStr=new StringBuilder();
		//建立数据库classes
		//写入Classes中的属性（Classes.java)
		sqlStr.append("insert into classes(classes_id,classesName,classesFlag,classesCDate,years_id) value(?,?,?,?,?) ");
		//建立连接
		conn=c3p0.getConnection();
		//直接调用is
		boolean flag=isSameName(cls);
		//无重名
		if(flag==false){
			int num=0;
			try {
				pstm=conn.prepareStatement(sqlStr.toString());
				//相应导入表中的属性
				pstm.setString(1, cls.getClasses_id());
				pstm.setString(2, cls.getClassesName());
				pstm.setString(3, cls.getClassesFlag());
				//对ClassescDate进行格式化（年-月-日 小时：分：秒）
				SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pstm.setString(4, f.format(cls.getClassesCDate()));
				pstm.setString(5, cls.getYears_id());
				//执行
				num=pstm.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//关闭通道
				//c3p0.close(null, stat, conn);
			}
			//若数据写入成功，则输出"OK",反之为"Err"
			if(num>0){
				return "Ok";
			}else{
				return "Err";
			}
		}//若重名，则输出"Same"
		else{
			return "Same";
		}
	}
	//同名检测（班级的名字不可相同）
	public boolean isSameName(Classes cls){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		conn=c3p0.getConnection();
		StringBuilder sqlStr=new StringBuilder();
		//测试classes中班级名是否相同
		sqlStr.append("select * from classes where classesName=?");
		boolean flag=false;
		try {
			//建立数据通道
			pstm=conn.prepareStatement(sqlStr.toString());
			//获取班级的名字（设置参数）
			pstm.setString(1, cls.getClassesName());
			//用rs 数据集读取数据
			rs=pstm.executeQuery();
			if(rs.next()){
				//有同名
				flag=true;
			}else{
				//无同名
				flag=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	//列出班级信息
	public List<Classes> getAllClasses(String str){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<Classes> list=new ArrayList<Classes>();
		//写sql语句
		StringBuilder sqlStr =new StringBuilder();
		sqlStr.append("SELECT C.*,y.yearsname FROM classes C ");
		sqlStr.append("LEFT JOIN years y on C.years_id=y.years_id ");
		//判断输入的数据
		if(str!=null&&!str.equals("")){
			sqlStr.append(" where 1=1 "+str+" ");
		}
		//打开通道
		conn=c3p0.getConnection();
		try {
			pstm=conn.prepareStatement(sqlStr.toString());
			//查询数据
			rs=pstm.executeQuery();
			//获取数据库classes中的内容
			while(rs.next()){
				Classes cls=new Classes();
				//获取数据库classes中的classes_id信息
				cls.setClasses_id(rs.getString("classes_id"));
				cls.setClassesName(rs.getString("classesName"));
				cls.setClassesFlag(rs.getString("classesFlag").equals("1")?"启用":"禁用");
				cls.setClassesCDate(rs.getDate("classesCDate"));//只显示日期
				//cls.setClassesCDate(rs.getTimestamp("classesCDate"));
				//cls.setClassesCDate(rs.getTime("classesCDate"));//只显示时间
				cls.setYears_id(rs.getString("years_id"));
				cls.setYearsname(rs.getString("yearsname"));
				//将以上信息放在列表里
				list.add(cls);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		//返回对象
		return list;
	}
	//根据班级编号，删除班级数据
	public String deleteClasses(String cid){
		Connection conn=null;
		PreparedStatement pstm=null;
		StringBuilder sqlStr=new StringBuilder();
		sqlStr.append("delete from classes ");
		sqlStr.append("where classes_id=?");
		//进行连接
		conn=c3p0.getConnection();
		int num=0;
		//检测返回的条数
		try {
			pstm=conn.prepareStatement(sqlStr.toString());
			//导入146行的classes_id
			pstm.setString(1, cid);
			//若删除成功 num返回1,则num返回0
			num=pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		//删除成功返回Ok
		if(num>0){
			return "Ok";
		}else{
			return "Err";
		}
	}
	//根据id找班级信息
	public Classes findClassesById(String cid){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		StringBuilder sqlStr=new StringBuilder();
		//sql语句
		sqlStr.append("select * from classes ");
		sqlStr.append("where classes_id=? ");
		//通道
		conn=c3p0.getConnection();
		Classes cls=new Classes();
		try {
			pstm=conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cid);
			rs=pstm.executeQuery();
			//读取班级信息
			if(rs!=null&&rs.next()){
				cls.setClasses_id(rs.getString("classes_id"));
				cls.setClassesName(rs.getString("classesName"));
				cls.setClassesFlag(rs.getString("classesFlag").equals("1")?"启用":"禁用");
				//cls.setClassesCDate(rs.getDate("classesCDate"));//只显示日期
				cls.setClassesCDate(rs.getTimestamp("classesCDate"));
				//cls.setClassesCDate(rs.getTime("classesCDate"));//只显示时间
				cls.setYears_id(rs.getString("years_id"));
				//cls.setYearsname(rs.getString("yearsname"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return cls;
	}
	//更新班级信息的方法
	public String updateClasses(Classes cls){
		Connection conn=null;
		PreparedStatement pstm=null;
		StringBuilder sqlStr=new StringBuilder();
		//更新sql语句
		sqlStr.append("update classes set classesName=?,classesFlag=?,classesCDate=?,years_id=? where classes_id=? ");
		//打开通道
		conn=c3p0.getConnection();
		int num=0;
		try {
			pstm=conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cls.getClassesName());
			pstm.setString(2, cls.getClassesFlag());
			//对ClassescDate进行格式化（年-月-日 小时：分：秒）
			SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstm.setString(3, f.format(cls.getClassesCDate()));
			pstm.setString(4, cls.getYears_id());
			pstm.setString(5, cls.getClasses_id());
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
	//分页功能
	public List<Classes> getAllClassesByPage(String str, Page page){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		List<Classes> list=new ArrayList<Classes>();
		//写sql语句
		StringBuilder sqlStr =new StringBuilder();
		sqlStr.append("SELECT C.*,y.yearsname FROM classes C ");
		sqlStr.append("LEFT JOIN years y on C.years_id=y.years_id ");
		//判断输入的数据
		if(str!=null&&!str.equals("")){
			sqlStr.append(" where 1=1 "+str+" ");
		}
		sqlStr.append(" order by classesName desc ");
		sqlStr.append(" limit ?,? ");
		//打开通道
		conn=c3p0.getConnection();
		try {
			pstm=conn.prepareStatement(sqlStr.toString());
			pstm.setInt(1, (page.getCurrentPage()-1)*page.getCount());
			pstm.setInt(2, page.getCount());
			//查询数据
			rs=pstm.executeQuery();
			//获取数据库classes中的内容
			while(rs.next()){
				Classes cls=new Classes();
				//获取数据库classes中的classes_id信息
				cls.setClasses_id(rs.getString("classes_id"));
				cls.setClassesName(rs.getString("classesName"));
				cls.setClassesFlag(rs.getString("classesFlag").equals("1")?"启用":"禁用");
				//cls.setClassesCDate(rs.getDate("classesCDate"));//只显示日期
				cls.setClassesCDate(rs.getTimestamp("classesCDate"));
				//cls.setClassesCDate(rs.getTime("classesCDate"));//只显示时间
				cls.setYears_id(rs.getString("years_id"));
				cls.setYearsname(rs.getString("yearsname"));
				//将以上信息放在列表里
				list.add(cls);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
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
		sqlStr.append("select count(1) from classes ");
		if(str!=null&&!str.equals("")){
			sqlStr.append(" where 1=1 "+str+" ");
		}
		sqlStr.append(" order by classesName desc ");
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
}
