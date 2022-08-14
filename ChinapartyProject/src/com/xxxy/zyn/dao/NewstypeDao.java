package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Newstype;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NewstypeDao {
	private C3P0Utils c3p0;
	
	public NewstypeDao() {
		// TODO Auto-generated constructor stub
		//实例化数据库
		c3p0=new C3P0Utils();
	}
	//添加部门信息
		public String addNewstype(Newstype model){
			//数据库连接的必要语句
			Connection conn=null;
			PreparedStatement pstm=null;
			StringBuffer sqlStr=new StringBuffer();
			//建立数据库
			//写入newstype中的属性（newstype.java)
			sqlStr.append("insert into newstype(newstype_id,newstypeName,newstypeFlag,newsstypeCDate) values(?,?,?,?) ");
			//建立连接
			conn=c3p0.getConnection();
			//直接调用is（同名检测）
			boolean flag=isSameName(model);
			//无重名
			if(flag==false){
				int num=0;
				try {
					pstm=conn.prepareStatement(sqlStr.toString());
					//相应导入表中的属性
					pstm.setString(1, model.getNewstype_id());
					pstm.setString(2, model.getNewstypeName());
					pstm.setString(3, model.getNewstypeFlag());
					//对ClassescDate进行格式化（年-月-日 小时：分：秒）
					SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					pstm.setString(4, f.format(model.getNewsstypeCDate()));
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
		//同名检测（部门的名字不可相同）
		public boolean isSameName(Newstype model){
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			conn=c3p0.getConnection();
			StringBuffer sqlStr=new StringBuffer();
			//测试classes中班级名是否相同
			sqlStr.append("select * from newstype where newstypeName=? ");
			boolean flag=false;
			try {
				//建立数据通道
				pstm=conn.prepareStatement(sqlStr.toString());
				//获取部门的名字（设置参数）
				pstm.setString(1, model.getNewstypeName());
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
		//列出部门信息
		public List<Newstype> getAllNewstype(String str){
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			List<Newstype> list=new ArrayList<Newstype>();
			//写sql语句
			StringBuilder sqlStr =new StringBuilder();
			sqlStr.append("select * from newstype");
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
					Newstype model=new Newstype();
					//获取数据库department中信息
					model.setNewstype_id(rs.getString("newstype_id"));
					model.setNewstypeName(rs.getString("newstypeName"));
					model.setNewstypeFlag(rs.getString("newstypeFlag").equals("1")?"启用":"禁用");
					//model.setDepartmentCDate(rs.getDate("departmentCDate"));//只显示日期
					model.setNewsstypeCDate(rs.getTimestamp("newsstypeCDate"));
					//model.setNewsstypeCDate(rs.getTime("newsstypeCDate"));//只显示时间
				
					//将以上信息放在列表里
					list.add(model);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
			//返回对象
			return list;
		}
		//根据部门编号，删除部门数据
		public String deleteNewstype(String cid){
			Connection conn=null;
			PreparedStatement pstm=null;
			StringBuffer sqlStr=new StringBuffer();
			sqlStr.append("delete  from newstype ");
			
			sqlStr.append("where newstype_id=? ");
			//进行连接
			conn=c3p0.getConnection();
			int num=0;
			//检测返回的条数
			try {
				pstm=conn.prepareStatement(sqlStr.toString());
				//导入146行的department_id
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
		//根据id找部门信息
		public Newstype findNewstypeById(String cid){
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			StringBuffer sqlStr=new StringBuffer();
			//sql语句
			sqlStr.append("select * from newstype ");
			sqlStr.append("where newstype_id=? ");
			//通道
			conn=c3p0.getConnection();
			Newstype model=new Newstype();
			try {
				pstm=conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, cid);
				rs=pstm.executeQuery();
				//读取班级信息
				if(rs!=null&&rs.next()){
					//获取数据库department中信息
					model.setNewstype_id(rs.getString("newstype_id"));
					model.setNewstypeName(rs.getString("newstypeName"));
					model.setNewstypeFlag(rs.getString("newstypeFlag").equals("1")?"启用":"禁用");
					//model.setDepartmentCDate(rs.getDate("departmentCDate"));//只显示日期
					model.setNewsstypeCDate(rs.getTimestamp("newsstypeCDate"));
					//model.setNewsstypeCDate(rs.getTime("newsstypeCDate"));//只显示时间
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
			return model;
		}
		//更新班级信息的方法
				public String updateNewstype(Newstype model){
					Connection conn=null;
					PreparedStatement pstm=null;
					StringBuilder sqlStr=new StringBuilder();
					boolean flag=isSameName(model);
					//StringBuilder sqlStr=new StringBuilder();
					//更新sql语句
					//sqlStr.append("update classes set classesName=?,classesFlag=?,classesCDate=?,years_id=? where classes_id=? ");
					sqlStr.append("update newstype set newstypeName=?,newstypeFlag=?,newsstypeCDate=? where newstype_id=? ");
						//打开通道
						conn=c3p0.getConnection();
						int num=0;
						try {
							pstm=conn.prepareStatement(sqlStr.toString());
							pstm.setString(1, model.getNewstypeName());
							pstm.setString(2, model.getNewstypeFlag());
							//对ClassescDate进行格式化（年-月-日 小时：分：秒）
							SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							pstm.setString(3, f.format(model.getNewsstypeCDate()));
							pstm.setString(4, model.getNewstype_id());
							//执行命令
							num=pstm.executeUpdate();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							
						}
						if(num>0){
							//更新成功
							return "Ok";
						}else{
							//更新失败
							return "Err";
						}
					}
		//带分页的部门列表
		public List<Newstype> getAllNewstypeByPage(String str, Page page){
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			List<Newstype> list=new ArrayList<Newstype>();
			//写sql语句
			StringBuilder sqlStr =new StringBuilder();
			sqlStr.append("select * from newstype ");
			
			//判断输入的数据
			if(str!=null&&!str.equals("")){
				sqlStr.append(" where 1=1 "+str+" ");
			}
			//排序部门名降序排列
			sqlStr.append(" order by newstypeName desc ");
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
					Newstype model=new Newstype();
					//获取数据库department中信息
					model.setNewstype_id(rs.getString("newstype_id"));
					model.setNewstypeName(rs.getString("newstypeName"));
					model.setNewstypeFlag(rs.getString("newstypeFlag").equals("1")?"启用":"禁用");
					//model.setDepartmentCDate(rs.getDate("departmentCDate"));//只显示日期
					model.setNewsstypeCDate(rs.getTimestamp("newsstypeCDate"));
					//model.setNewsstypeCDate(rs.getTime("newsstypeCDate"));//只显示时间
					//将以上信息放在列表里
					list.add(model);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
			//返回对象
			return list;
		}
		//记录分页数
		public int getCount(String str){
			StringBuffer sqlStr=new StringBuffer();
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			//定义返回条数
			int total=0;
			sqlStr.append("select count(1) from newstype  ");
			if(str!=null&&!str.equals("")){
				sqlStr.append(" where 1=1 "+str+" ");
			}
			sqlStr.append(" order by newstypeName desc ");
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
		//获取所有部门编号和名称，用于添加和修改时的下拉选择
		public List<Newstype> getNews(){
			List<Newstype> list=new ArrayList<Newstype>();
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			StringBuffer sqlStr=new StringBuffer();
			sqlStr.append(" select * from newstype where newstypeFlag='1' ");
			sqlStr.append(" order by newstypeName asc ");
			conn=c3p0.getConnection();
			try {
				pstm=conn.prepareStatement(sqlStr.toString());
				rs=pstm.executeQuery();
				while(rs.next()){
					Newstype model=new Newstype();
					model.setNewstype_id(rs.getString("newstype_id"));
					model.setNewstypeName(rs.getString("newstypeName"));
					list.add(model);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
			return list;
		}
}

