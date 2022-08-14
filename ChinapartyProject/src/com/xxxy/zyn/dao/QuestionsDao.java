package com.xxxy.zyn.dao;

import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Questions;
import com.xxxy.zyn.dbutils.C3P0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class QuestionsDao {
	private C3P0Utils c3p0;
	
	public QuestionsDao() {
		c3p0 = new C3P0Utils();
	}
	
	// 添加题目信息
		public String addQuestions(Questions que){
			Connection conn=null;
			PreparedStatement pstm=null;
			StringBuffer sqlStr = new StringBuffer();
			sqlStr.append("insert into questions(questions_id,theme_id,quesTitle,quesA,quesB,quesC,quesD,quesScore,quesStand,quesCDate,quesFlag) values(?,?,?,?,?,?,?,?,?,?,?) " );
			conn = c3p0.getConnection();
			//同名检测
			boolean flag=isSameName(que,'0');
			if(flag==false){
				int num=0;
				try {
					pstm=conn.prepareStatement(sqlStr.toString());
					pstm.setString(1,que.getQuestions_id());
					pstm.setString(2,que.getTheme_id());
					pstm.setString(3,que.getQuesTitle());
					pstm.setString(4,que.getQuesA());
					pstm.setString(5,que.getQuesB());
					pstm.setString(6,que.getQuesC());
					pstm.setString(7,que.getQuesD());
					pstm.setString(8,que.getQuesScore());
					pstm.setString(9,que.getQuesStand());
					//日期格式化
					SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					pstm.setString(10,f.format(que.getQuesCDate()));
					
					pstm.setString(11,que.getQuesFlag());
					
					
					num = pstm.executeUpdate();
					
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
				return "sname";
			}
			
		}
		
		//同名检测方法
		public boolean isSameName(Questions que,char mFlag){
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			conn = c3p0.getConnection();//建立连接
			
			StringBuffer sqlStr = new StringBuffer();
			sqlStr.append("select *from questions where quesTitle=? ");
			if(mFlag=='1'){//修改时的同名检测
				sqlStr.append(" and questions_id!='"+que.getQuestions_id()+"' ");
			}
			boolean flag = false;
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				pstm.setString(1,que.getQuesTitle());
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
		
		// 列出题目信息
		public List<Questions> getAllQuestions(String str) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<Questions> list = new ArrayList<Questions>();
			StringBuilder sqlStr = new StringBuilder();
			sqlStr.append("select * from questions ");
			sqlStr.append("where questions_id ");
			if (str != null && !str.equals("")) {
				sqlStr.append(" where 1=1 " + str + " ");
			}
			conn = c3p0.getConnection();
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				rs = pstm.executeQuery();
				while (rs.next()) {
					Questions que = new Questions();
					que.setQuestions_id(rs.getString("questions_id"));
					que.setTheme_id(rs.getString("theme_id"));
					que.setQuesTitle(rs.getString("quesTitle"));
					que.setQuesA(rs.getString("quesA"));
					que.setQuesB(rs.getString("quesB"));
					que.setQuesC(rs.getString("quesC"));
					que.setQuesD(rs.getString("quesD"));
					que.setQuesScore(rs.getString("quesScore"));
					que.setQuesStand(rs.getString("quesStand"));
					
					que.setQuesCDate(rs.getDate("quesCDate"));// 只显示日期
					// model.setDepartmentCDate(rs.getTimestamp("departmentCDate"));//都显示
					// model.setDepartmentCDate(rs.getTime("departmentCDate"));//只显示时间
					
					que.setQuesFlag(rs.getString("quesFlag").equals("1") ? "启用" : "禁用");
					
//					que.setDepartmentPid(rs.getString("departmentPid"));
//					que.setPdname(rs.getString("pdname"));
					list.add(que);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}
			return list;
		}
		
		//根据题目编号删除班级数据
		public String deleteQuestions(String cid){
			Connection conn = null;
			PreparedStatement pstm = null;
			StringBuilder sqlStr =  new StringBuilder();
			sqlStr.append("delete from questions ");
			sqlStr.append("where questions_id=? ");
			conn=c3p0.getConnection();
			int num=0;
			try {
				pstm=conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, cid);
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
		
		
		//根据ID找题目信息
		public Questions findQuestionsById(String cid){
			Connection conn=null;
			PreparedStatement pstm=null;
			ResultSet rs=null;
			StringBuffer sqlStr = new StringBuffer();
			sqlStr.append("select *from questions ");
			sqlStr.append("where questions_id=? ");
			conn = c3p0.getConnection();
			Questions que = new Questions();
			
			try {
				pstm=conn.prepareStatement(sqlStr.toString());
				pstm.setString(1, cid);
				rs=pstm.executeQuery();
				if(rs!=null && rs.next()){
					que.setQuestions_id(rs.getString("questions_id"));
					que.setTheme_id(rs.getString("theme_id"));
					que.setQuesTitle(rs.getString("quesTitle"));
					que.setQuesA(rs.getString("quesA"));
					que.setQuesB(rs.getString("quesB"));
					que.setQuesC(rs.getString("quesC"));
					que.setQuesD(rs.getString("quesD"));
					que.setQuesScore(rs.getString("quesScore"));
					que.setQuesStand(rs.getString("quesStand"));
					
					//que.setQuesCDate(rs.getDate("quesCDate"));// 只显示日期
					que.setQuesCDate(rs.getTimestamp("quesCDate"));//都显示
					// model.setDepartmentCDate(rs.getTime("departmentCDate"));//只显示时间
					
					que.setQuesFlag(rs.getString("quesFlag").equals("1") ? "启用" : "禁用");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
			}
			return que;
		}
		
		public String updateQuestions(Questions que){
			Connection conn=null;
			PreparedStatement pstm=null;
			boolean flag=isSameName(que, '1');
			if(flag!=true){
				StringBuffer sqlStr=new StringBuffer();
				sqlStr.append("update questions set theme_id=?,quesTitle=?,quesA=?,quesB=?,quesC=?,quesD=?,quesScore=?,quesStand=?,");
				sqlStr.append("quesCDate=?,quesFlag=? ");
				sqlStr.append("where questions_id=? ");
				conn=c3p0.getConnection();
				int num=0;
				try {
					pstm=conn.prepareStatement(sqlStr.toString());
					pstm.setString(1, que.getTheme_id());
					pstm.setString(2, que.getQuesTitle());
					pstm.setString(3, que.getQuesA());
					pstm.setString(4, que.getQuesB());
					pstm.setString(5, que.getQuesC());
					pstm.setString(6, que.getQuesD());
					pstm.setString(7, que.getQuesScore());
					pstm.setString(8, que.getQuesStand());
					
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					pstm.setString(9, f.format(que.getQuesCDate()));
					
					pstm.setString(10, que.getQuesFlag());
					pstm.setString(11, que.getQuestions_id());
					num = pstm.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (num > 0) {
					return "Ok";
				} else {
					return "Err";
				}
			}else{
				return "Same";
			}
		}
		
		// 带分页的部门列表
		public List<Questions> getAllQuestionsByPage(String str, Page page) {
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<Questions> list = new ArrayList<Questions>();
			StringBuilder sqlStr = new StringBuilder();
			sqlStr.append("select q.*,th.themeTitle FROM questions q ");
			sqlStr.append("LEFT JOIN theme th on th.theme_id=q.theme_id ");
			if (str != null && !str.equals("")) {
				sqlStr.append(" where 1=1 " + str + " ");
			}
			sqlStr.append(" ORDER BY quesCDate DESC ");
			sqlStr.append(" limit ?,? ");
			conn = c3p0.getConnection();
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				pstm.setInt(1, (page.getCurrentPage()-1)*page.getCount());
				pstm.setInt(2, page.getCount());
				rs = pstm.executeQuery();
				while (rs.next()) {
					Questions que = new Questions();
					que.setQuestions_id(rs.getString("questions_id"));
					que.setTheme_id(rs.getString("theme_id"));
					que.setQuesTitle(rs.getString("quesTitle"));
					que.setQuesA(rs.getString("quesA"));
					que.setQuesB(rs.getString("quesB"));
					que.setQuesC(rs.getString("quesC"));
					que.setQuesD(rs.getString("quesD"));
					que.setQuesScore(rs.getString("quesScore"));
					que.setQuesStand(rs.getString("quesStand"));
					
					//que.setQuesCDate(rs.getDate("quesCDate"));// 只显示日期
					que.setQuesCDate(rs.getTimestamp("quesCDate"));//都显示
					// model.setDepartmentCDate(rs.getTime("departmentCDate"));//只显示时间
					
					que.setQuesFlag(rs.getString("quesFlag").equals("1") ? "启用" : "禁用");
					que.setThemeTitle(rs.getString("themeTitle"));
					list.add(que);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}
			return list;
		}
		
		public int getCount(String str) {
			StringBuffer sqlStr = new StringBuffer();
			Connection conn = null;
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int total = 0;
			sqlStr.append("select count(1) from questions ");
			if (str != null && !str.equals("")) {
				sqlStr.append(" where 1=1 " + str + " ");
			}
			sqlStr.append(" order by theme_id  ");
			conn = c3p0.getConnection();
			try {
				pstm = conn.prepareStatement(sqlStr.toString());
				rs = pstm.executeQuery();
				if (rs.next()) {
					total = rs.getInt(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

			}
			return total;
		}
}
