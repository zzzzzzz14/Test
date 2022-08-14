package com.xxxy.zyn.action.classes;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Classes;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.dao.ClassesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class getAllClassesBYPageServlet
 */
@WebServlet("/getAllClassesBYPageServlet")
public class getAllClassesBYPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllClassesBYPageServlet() {
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
		ClassesDao dao=new ClassesDao();
		String cflag=request.getParameter("cflag");
		String sdate=request.getParameter("classcdate_s");
		String edate=request.getParameter("classcdate_e");
		String cname=request.getParameter("classname");
		StringBuffer str=new StringBuffer();
		if(cflag!=null&&!cflag.equals("")){
			if(cflag.equals("1")||cflag.equals("0")){
				str.append(" and classesFlag="+cflag);
			}
		}
		if(sdate!=null&&!sdate.equals("")){
			str.append(" and classesCDate>="+sdate);
		}
		if(edate!=null&&!edate.equals("")){
			str.append(" and classesCDate<="+edate);
		}
		
		if(cname!=null&&!cname.equals("")){
			cname=new String(cname.getBytes("ISO-8859-1"),"UTF-8");
			str.append(" and classesName like '%"+cname+"%' ");
		}
		String cpage=request.getParameter("page");
		String limit=request.getParameter("limit");
		//String cpage="1";
		//String limit="3";
		if(cpage!=null&&!cpage.equals("")){
			Page page=new Page();
			page.setCurrentPage(Integer.parseInt(cpage));
			page.setCount(Integer.parseInt(limit));
			List<Classes> list=dao.getAllClassesByPage(str.toString(), page);
			request.setAttribute("classes", list);
			int total=dao.getCount(str.toString());
			System.out.println(list);
			JSONObject jo=new JSONObject();
			jo.put("code", 0);
			jo.put("msg", "你好");
			jo.put("count",total );
			jo.put("data", list);
			System.out.println(jo.toJSONString());
			System.out.println(total);
			PrintWriter out=response.getWriter();
			out.print(jo.toString());
			out.flush();
			out.close();
			
			
		}
		//若没有分页
		else{
			//重定向（请求转发）
			request.getRequestDispatcher("/view/classes/ClassesListByPage.jsp").forward(request, response);
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
