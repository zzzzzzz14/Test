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
import java.util.UUID;

/**
 * Servlet implementation class addClassesServlet
 */
@WebServlet("/addClassesServlet")
public class addClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addClassesServlet() {
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
		//定义数据库classes中的属性
		String cname=request.getParameter("classname");
		String cflag=request.getParameter("cflag");
		String cdate=request.getParameter("classcdate");
		String cyears=request.getParameter("years");
		String cuuid=UUID.randomUUID().toString().replace("-", "");
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
		ClassesDao dao=new ClassesDao();
		String str=dao.addClasses(cls);
		//判断数据是否添加成功
		//if(str.equals("Ok")){
			//str="添加成功";
		//}else if(str.equals("Err")){
			//str="添加失败";
		//}else{
			//str="存在同名";
		//}
		//request.setAttribute("flag", str);
		//转发到Classes.jsp
		//request.getRequestDispatcher("/Classes.jsp").forward(request, response);;
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
