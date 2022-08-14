package com.xxxy.zyn.action.processtype;

import com.xxxy.zyn.bean.Processtype;
import com.xxxy.zyn.dao.ProcesstypeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;



/**
 * Servlet implementation class addClassesServlet
 */
@WebServlet("/addProcesstypeServlet")
public class addProcesstypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProcesstypeServlet() {
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
	    String cname=request.getParameter("classname");
	    String cflag=request.getParameter("cflag");
	   
	    String cuuid=UUID.randomUUID().toString().replace("-", "");
	    //随机
	    Processtype cls=new Processtype();
	    cls.setProcesstype_id(cuuid);
	    cls.setPtypeName(cname);
	    cls.setPtypeFlag(cflag);
	    
   
    
	    ProcesstypeDao dao=new ProcesstypeDao();
	    String str=dao.addProcesstype(cls);
	    /*if(str.equals("Ok")){
	    	str="添加成功";
	    }else if(str.equals("Err")){
	    	str="添加失败";
	    }else{
	    	str="存在同名班级";
	    }
	    request.setAttribute("flag", str);
	    request.getRequestDispatcher("/Classes.jsp").forward(request, response);*/
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
