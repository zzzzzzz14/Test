package com.xxxy.zyn.action.processtype;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Processtype;
import com.xxxy.zyn.dao.ProcesstypeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Servlet implementation class getAllClassesServlet
 */
@WebServlet("/getAllProcesstypeServlet")
public class getAllProcesstypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllProcesstypeServlet() {
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
		 ProcesstypeDao dao=new ProcesstypeDao();
		 List<Processtype> list=new ArrayList<Processtype>();
			JSONObject jo=new JSONObject();
			jo.put("data", list);
			System.out.println(jo.toJSONString());
			PrintWriter out=response.getWriter();
			out.print(jo.toString());
			out.flush();
			out.close();
			request.setAttribute("pbnames", list);
			
//		 list=dao.getAllClasses("");
//		 request.setAttribute("classes", list);
//		 System.out.println(list);
//		 request.getRequestDispatcher("/view/classes/ClassesList.jsp").forward(request, response);
//		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
