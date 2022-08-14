package com.xxxy.zyn.action.post;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Post;
import com.xxxy.zyn.dao.PostDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class getAllPostByPageServlet
 */
@WebServlet("/getAllPostByPageServlet")
public class getAllPostByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllPostByPageServlet() {
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
		PostDao dao = new PostDao();
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String cname = request.getParameter("cname");
		String whereStr="";
		StringBuffer str = new StringBuffer();
		if(sdate!=null && !sdate.equals("")){
			str.append(" and postCDate>='"+sdate+"'");
		}
		if(edate!=null && !edate.equals("")){
			str.append(" and postCDate<='"+edate+" 23:59:59'");
		}
		if(cname!=null && !cname.equals("")){
			//cname = new String(cname.getBytes("ISO-8859-1"),"utf-8"); //用地址栏提交
			str.append(" and postName like '%"+cname+"%' ");
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
			List<Post> list = dao.getAllPostByPage(str.toString(), page);
			request.setAttribute("mds", list);
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
			request.getRequestDispatcher("/view/post/PostListByPage.jsp").forward(request, response);

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
