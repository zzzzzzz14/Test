package com.xxxy.zyn.action.classes;

import com.xxxy.zyn.bean.Classes;
import com.xxxy.zyn.dao.ClassesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class getAllClassesServlet
 */
@WebServlet("/getAllClassesServlet")
public class getAllClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllClassesServlet() {
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
		//实例化
		ClassesDao dao=new ClassesDao();
		List<Classes> list=new ArrayList<Classes>();
		String cname=request.getParameter("classname");
		String cflag=request.getParameter("cflag");
		String sdate=request.getParameter("classescdate_s");
		String edate=request.getParameter("classescdate_e");
		StringBuffer str=new StringBuffer();
		if(cflag!=null&&!cflag.equals("")){
			if(cflag.equals("1")||cflag.equals("0")){
				str.append(" and classesFlag="+cflag);
			}
		}
		if(sdate!=null&&!sdate.equals("")){
			str.append(" and classesCDate>='"+sdate+"'");
		}
		if(edate!=null&&!edate.equals("")){
			str.append(" and classesCDate<='"+edate+" 23:59:59'");
		}
		
		if(cname!=null&&!cname.equals("")){
			cname=new String(cname.getBytes("ISO-8859-1"),"UTF-8");
			str.append(" and classesName like '%"+cname+"%' ");
		}
		list=dao.getAllClasses(str.toString());
		//将查询到的内容放到域中
		request.setAttribute("classes",list);
		System.out.println(list);
		//重定向（请求转发）
		request.getRequestDispatcher("/view/classes/ClassesList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
