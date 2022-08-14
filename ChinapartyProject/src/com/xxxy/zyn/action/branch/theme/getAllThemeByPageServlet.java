package com.xxxy.zyn.action.branch.theme;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.theme;
import com.xxxy.zyn.dao.ThemeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Servlet implementation class getAllThemeByPageServlet
 */
@WebServlet("/getAllThemeByPageServlet")
public class getAllThemeByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllThemeByPageServlet() {
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
	    ThemeDao dao=new ThemeDao();
	    String cflag=request.getParameter("cflag");
	    String sdate=request.getParameter("sdate");
	    String edate=request.getParameter("edate");
	    String ctitle=request.getParameter("ctitle");
	    StringBuffer str=new StringBuffer();
	    if(cflag!=null&&!cflag.equals("")){
	    	if(cflag.equals("0")||cflag.equals("1")||cflag.equals("2")){
	    		str.append(" and themeFlag='"+cflag+"'");
	    	}
	    }
	    if(sdate!=null&&!sdate.equals("")){
	    	str.append(" and themeCDate>='"+sdate+"'");
	    }
	    if(edate!=null&&!edate.equals("")){
	    	str.append(" and themeCDate<='"+edate+" 23:59:59'");
	    }
	   
	    if(ctitle!=null&&!ctitle.equals("")){
	    	//cname=new String(cname.getBytes("ISO-8859-1"),"utf-8");
	    	str.append(" and themeTitle like '%"+ctitle+"%' ");
	    } 
	    String cpage=request.getParameter("page");
	    String limit=request.getParameter("limit");
	    /*String cpage="1";
	    String limit="3";*/
	    if(cpage!=null&&!cpage.equals("")){
	    	Page page=new Page();
	    	page.setCurrentPage(Integer.parseInt(cpage));
	    	page.setCount(Integer.parseInt(limit));
	    	List<theme> list=dao.getAllThemeByPage(str.toString(), page);
	    	request.setAttribute("mds", list);
	    	int total=dao.getCount(str.toString());
	    	System.out.println(list);
	    	JSONObject jo=new JSONObject();
	    	jo.put("code", 0);
	    	jo.put("msg", "你好");
	    	jo.put("count", total);
	    	jo.put("data", list);
	    	System.out.println(jo.toJSONString());
	    	PrintWriter out=response.getWriter();
	    	out.print(jo.toString());
	    	out.flush();
	    	out.close();
	    }else{
	    	request.getRequestDispatcher("/view/theme/ThemeListByPage.jsp").forward(request, response);
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
