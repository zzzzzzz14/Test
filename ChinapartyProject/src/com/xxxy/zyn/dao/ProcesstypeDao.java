package com.xxxy.zyn.dao;


import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Processtype;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcesstypeDao {
	private C3P0Utils c3p0;
	public ProcesstypeDao(){
		//写构造方法
		c3p0=new C3P0Utils();
		
	}
//	添加信息的方法
	public String addProcesstype(Processtype cls){
		Connection conn=null;
		PreparedStatement pstm=null;
		StringBuilder sqlStr=new StringBuilder();
		sqlStr.append("insert into processtype(processtype_id,ptypeName,ptypeFlag) values(?,?,?)");
        conn=c3p0.getConnection();
		//同名检测
		boolean flag=isSameName(cls,'0');
        if(flag==false){
         int num=0;
         try {
      pstm=conn.prepareStatement(sqlStr.toString());
      pstm.setString(1, cls.getProcesstype_id());
      pstm.setString(2, cls.getPtypeName());
      pstm.setString(3, cls.getPtypeFlag());
     
	

            num=pstm.executeUpdate();
            //执行
         } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }finally {
   //c3p0.close(null, pstm, conn);
  }
         if(num>0){
          return "Ok";
         }else{
          return "Err";
         }
        }else{
         return "Same";
        }
        
    }
//同名检测方法
	public boolean isSameName(Processtype cls, char mflag){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		conn=c3p0.getConnection();
		StringBuilder sqlStr=new StringBuilder();
		sqlStr.append("select * from processtype where ptypeName=?");
		if (mflag == '1') {// 修改时得同名检测
			sqlStr.append(" and processtype_id!='" + cls.getProcesstype_id() + "' ");
		}
		boolean flag=false;
		try {
			pstm=conn.prepareStatement(sqlStr.toString());
			pstm.setString(1, cls.getPtypeName());
			rs=pstm.executeQuery();
			if(rs.next()){
				flag=true;
				
			}else{
				flag=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
		
	}
	//根据id
	public Processtype findProcesstypeById(String cid){
		Connection conn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;	
		StringBuilder sqlStr=new StringBuilder();
		sqlStr.append("select * from processtype ");
		sqlStr.append("where processtype_id=? ");
		conn=c3p0.getConnection();
		Processtype cls=new Processtype();
		try {
		pstm=conn.prepareStatement(sqlStr.toString());
		pstm.setString(1, cid);
		rs=pstm.executeQuery();
		if(rs!=null&&rs.next()){
			cls.setProcesstype_id(rs.getString("processtype_id"));
			cls.setPtypeName(rs.getString("ptypeName"));
			cls.setPtypeFlag(rs.getString("ptypeFlag").equals("1")?"启用":"禁用");
			//model.setProcesstypeCDate(rs.getDate("processtypeCDate"));//只有日期
			//model.setProcesstypeCDate(rs.getTimestamp("processtypeCDate"));
			//model.setProcesstypeCDate(rs.getTime("processtypeCDate"));//只显示时间
			//model.set(rs.getString("yearsname"));
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		
	}
	return cls;
	}
		//更新（修改）信息
 		public String updateProcesstype(Processtype cls){
 			Connection conn=null;
 			PreparedStatement pstm=null;
 			boolean flag=isSameName(cls, '1');
 			if(flag!=true){
 			StringBuffer sqlStr=new StringBuffer();
 			sqlStr.append("update processtype set ptypeName=?,ptypeFlag=? ");

 			sqlStr.append("where processtype_id=? ");
 			conn=c3p0.getConnection();
 			int num=0;
 			try{
 				pstm=conn.prepareStatement(sqlStr.toString());
 			
 			    pstm.setString(1, cls.getPtypeName());
 			    pstm.setString(2,cls.getPtypeFlag());
 				pstm.setString(3, cls.getProcesstype_id());
 			    
 			    num=pstm.executeUpdate();
 			}catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 			if(num>0){
 				return "Ok";
 			}else{
 	 			return "Err";
 			}
 	 		}else{
 				return "Same";
 		}
 			}
		//带分页功能的Dao
 		public List<Processtype> getAllProcesstypeByPage(String str, Page page){
 			Connection conn=null;
 	 		PreparedStatement pstm=null;
 	 		ResultSet rs=null;	
 	 		List<Processtype> list=new ArrayList<Processtype>();
 	 		StringBuilder sqlStr=new StringBuilder();
 	 		//查询语句
 	 		sqlStr.append("select * from processtype");
 	 		
 	 		if(str!=null&&!str.equals("")){
 	 			sqlStr.append(" where 1=1 "+str+" ");
 	 		}
 	 		sqlStr.append(" order by ptypeName desc ");
 	 		sqlStr.append(" limit ?,? ");//记录开始到结束
 	 		conn=c3p0.getConnection();
 	 		try {
 				pstm=conn.prepareStatement(sqlStr.toString());
 				pstm.setInt(1, (page.getCurrentPage()-1)*page.getCount());
 				pstm.setInt(2, page.getCount());
 				rs=pstm.executeQuery();
 				while(rs.next()){
 					Processtype cls=new Processtype();
 					cls.setProcesstype_id(rs.getString("processtype_id"));
 					cls.setPtypeName(rs.getString("ptypeName"));
 					cls.setPtypeFlag(rs.getString("ptypeFlag").equals("1")?"启用":"禁用");
 					list.add(cls);
 				}
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}finally{
 				
 			}
 	 		
 	 		return list;
 		}
 		
 		//删除
 		public String deleteProcesstype(String cid){
 			  Connection conn=null;
 			  PreparedStatement Pstm=null;
 			  StringBuilder sqlstr=new StringBuilder();
 			  sqlstr.append("delete from processtype ");
 			  sqlstr.append("where processtype_id=? ");
 			 conn=c3p0.getConnection();
 			  int num=0;
 			  try {
 			   Pstm=conn.prepareStatement(sqlstr.toString());
 			   Pstm.setString(1,cid);
 			   num=Pstm.executeUpdate();
 			  }catch (SQLException e ) {
 			   //TODO AUTO-generated catch block
 			   e.printStackTrace();
 			  }finally{
 			   
 			  }if(num>0){
 			   return "Ok";
 			  }else{
 			   return "Err";
 			  }
 		
 			  
 			  }
 		//分页
 		public int getCount(String str){
 			StringBuffer sqlStr=new StringBuffer();
 			Connection conn=null;
 			PreparedStatement pstm=null;
 			ResultSet rs=null;
 			int total=0;
 			sqlStr.append("select count(1) from processtype ");
 			if(str!=null&&str.equals("")){
 				sqlStr.append(" where 1=1 "+str+" ");
 			}
 			sqlStr.append(" order by ptypeName desc ");//名字排序
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
 	// 获取所有履历编号和名称，用于添加和修改时的下拉选择
 		public List<Processtype> getProts() {
 			List<Processtype> list = new ArrayList<Processtype>();
 			Connection conn = null;
 			PreparedStatement pstm = null;
 			ResultSet rs = null;
 			StringBuffer sqlStr = new StringBuffer();
 			sqlStr.append(" select * from processtype where ptypeFlag='1' ");
 			sqlStr.append(" order by ptypeName asc ");
 			conn = c3p0.getConnection();
 			try {
 				pstm = conn.prepareStatement(sqlStr.toString());
 				rs = pstm.executeQuery();
 				while (rs.next()) {
 					Processtype model = new Processtype();
 					model.setProcesstype_id(rs.getString("processtype_id"));
 					model.setPtypeName(rs.getString("ptypeName"));
 					list.add(model);
 				}
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} finally {

 			}
 			return list;
 		}
		
		
	
	}
	
	
