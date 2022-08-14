package com.xxxy.zyn.action.processtype;

import com.xxxy.zyn.dao.ProcesstypeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;






/**
 * Servlet implementation class deleteProcesstypeServlet
 */
@WebServlet("/deleteProcesstypeServlet")
public class deleteProcesstypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteProcesstypeServlet() {
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
			  response.setContentType("text/html;charset=utf-8");
			  String cid=request.getParameter("cid");
			  ProcesstypeDao dao=new ProcesstypeDao();
			  String flag=dao.deleteProcesstype(cid);
			  request.setAttribute("flag", flag);
			  //request.getRequestDispatcher("/getAllProcesstypeServlet").forward(request, response);
			  PrintWriter out = response.getWriter() ; 
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
