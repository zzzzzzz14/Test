package com.xxxy.zyn.action.classes;

import com.xxxy.zyn.bean.Classes;
import com.xxxy.zyn.dao.ClassesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class updateClassesServlet
 */
@WebServlet("/updateClassesServlet")
public class updateClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateClassesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html:charset=utf-8");
		//定义数据库classes中的属性(页面数据获取）
		String cname=request.getParameter("classname");
		String cflag=request.getParameter("cflag");
		String cdate=request.getParameter("classcdate");
		String cyears=request.getParameter("years");
		String cuuid=request.getParameter("classes_id");
		//构造班级对象
		Classes cls=new Classes();
		cls.setClasses_id(cuuid);
		cls.setClassesName(cname);
		cls.setClassesFlag(cflag);
		cls.setYears_id(cyears);
		//格式化cdate
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			cls.setClassesCDate(f.parse(cdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		//执行更新方法
		ClassesDao dao=new ClassesDao();
		String str=dao.updateClasses(cls);
		System.out.println(str);
		//判断数据是否修改成功
		PrintWriter out=response.getWriter();
		out.write(str);
		out.flush();
		out.close();
		
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
