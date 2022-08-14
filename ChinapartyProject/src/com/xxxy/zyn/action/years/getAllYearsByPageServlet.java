package com.xxxy.zyn.action.years;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Years;
import com.xxxy.zyn.dao.YearsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class getAllYearsByPageServlet
 */
@WebServlet("/getAllYearsByPageServlet")
public class getAllYearsByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllYearsByPageServlet() {
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
				
				YearsDao dao = new YearsDao();
				String yearsflag = request.getParameter("yearsflag");
				String yearsname = request.getParameter("yearsname");
				String whereStr="";
				StringBuffer str = new StringBuffer();
				if(yearsflag!=null && !yearsflag.equals("")){
					if(yearsflag.equals("1") || yearsflag.equals("0")){
						str.append(" and yearsflag="+yearsflag);
					}
				}
				if(yearsname!=null && !yearsname.equals("")){
					//yearsname = new String(cname.getBytes("ISO-8859-1"),"utf-8"); //用地址栏提交
					str.append(" and yearsname like '%"+yearsname+"%' ");
				}
				
				
				String cpage = request.getParameter("page");
				String limit = request.getParameter("limit");
				
				
				/*
				String cpage = "1";
				String limit = "3";
				*/
				if(cpage!=null && !cpage.equals("")){
					Page page = new Page();
					page.setCurrentPage(Integer.parseInt(cpage));
					page.setCount(Integer.parseInt(limit));
					List<Years> list = dao.getAllYearsByPage(str.toString(), page);
					request.setAttribute("model", list);
					int total = dao.getCount(str.toString());
					System.out.println(list);
					JSONObject jo = new JSONObject();
					jo.put("code", 0);
					jo.put("msg", "你好");
					jo.put("count", total);
					jo.put("data", list);
					PrintWriter out = response.getWriter();
					out.print(jo.toString());
					out.flush();
					out.close();
				}else{
					request.getRequestDispatcher("/view/years/YearsListByPage.jsp").forward(request, response);

				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
