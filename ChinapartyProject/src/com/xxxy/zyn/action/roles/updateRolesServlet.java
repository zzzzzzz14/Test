package com.xxxy.zyn.action.roles;

import com.xxxy.zyn.bean.Roles;
import com.xxxy.zyn.dao.RolesDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class UpdateDepartmentServlet
 */
@WebServlet("/updateRolesServlet")
public class updateRolesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateRolesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//请求响应编解码设置
		request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    //页面数据获取
	    String cname=request.getParameter("cname");
		String cdate=request.getParameter("cdate");
		String cuuid=request.getParameter("cid");
	    
	    //构造对象
	    Roles model=new Roles();
	    model.setRoles_id(cuuid);
	    model.setRolesName(cname);
	    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
			model.setRolesCDate(f.parse(cdate));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //执行更新方法
        RolesDao dao =new RolesDao();
        String str=dao.updateRoles(model);
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
