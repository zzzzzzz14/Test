package com.xxxy.zyn.action.classes;

import com.xxxy.zyn.dao.ClassesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class deleteClassesServlet
 */
@WebServlet("/deleteClassesServlet")
public class deleteClassesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteClassesServlet() {
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
		//获取转发过来的id
		String cid=request.getParameter("cid");
		ClassesDao dao=new ClassesDao();
		//将cid 放入flag域
		String flag=dao.deleteClasses(cid);
		//结果域放进去
		request.setAttribute("flag", flag);
		//请求重定向
		//request.getRequestDispatcher("/getAllClassesServlet").forward(request, response);
		PrintWriter out=response.getWriter();
		out.write(flag);
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
