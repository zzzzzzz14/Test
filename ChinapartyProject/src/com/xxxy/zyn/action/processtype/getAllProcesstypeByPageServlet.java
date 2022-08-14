package com.xxxy.zyn.action.processtype;

import com.alibaba.fastjson.JSONObject;
import com.xxxy.zyn.bean.Page;
import com.xxxy.zyn.bean.Processtype;
import com.xxxy.zyn.dao.ProcesstypeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * Servlet implementation class getAllClassesByPageServlet
 */
@WebServlet("/getAllProcesstypeByPageServlet")
public class getAllProcesstypeByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllProcesstypeByPageServlet() {
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
		 String cname=request.getParameter("cname");
	    String cflag=request.getParameter("cflag");
	   
	   
		 String whereStr="";
		 StringBuffer str=new StringBuffer();
		 if(cflag!=null&&!cflag.equals("")){
		      if(cflag.equals("1")||cflag.equals("0")){
		       str.append(" and ptypeFlag="+cflag);
		      }
		     }
		 
		  if(cname!=null&&!cname.equals("")){
		     //  cname=new String(cname.getBytes("ISO-8859-1"),"utf-8");// 用get方式提交
		      str.append(" and ptypeName like '%"+cname+"%' ");
		     }
		     String cpage=request.getParameter("page");
		     String limit=request.getParameter("limit");
//		     String cpage="1";
//		     String limit="3";
		 if(cpage!=null&&!cpage.equals("")){
		    	 Page page=new Page();
		    	 page.setCurrentPage(Integer.parseInt(cpage));
		    	 page.setCount(Integer.parseInt(limit));
		    	 List<Processtype> list=dao.getAllProcesstypeByPage(str.toString(),page);
		    	 request.setAttribute("cls", list);
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
		    	 request.getRequestDispatcher("/view/processtype/ProcesstypeListByPage.jsp").forward(request, response);
				 
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
