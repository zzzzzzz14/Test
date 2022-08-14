package com.xxxy.zyn.action.years;

import com.xxxy.zyn.bean.Years;
import com.xxxy.zyn.dao.YearsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Servlet implementation class updateYearsServlet
 */
@WebServlet("/updateYearsServlet")
public class updateYearsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateYearsServlet() {
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
		String yname=request.getParameter("yearsname");
		String yflag=request.getParameter("yearsflag");
		String yuuid=request.getParameter("years_id");
		Years model=new Years();
		model.setYears_id(yuuid);
		model.setYearsname(yname);
		model.setYearsflag(yflag);
		YearsDao dao=new YearsDao();
		String str=dao.updateYears(model);
		System.out.println(str);
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
