package com.xxxy.zhangyaning.servlet;

import com.xxxy.zhangyaning.bean.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class jiaofeiServlet
 */
@WebServlet("/jiaofeiServlet")
public class jiaofeiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jiaofeiServlet() {
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
		//获取页面信息(jsp)
		String sname=request.getParameter("sname");
		String snum=request.getParameter("snum");
		String sdept=request.getParameter("sdept");
		String jiner=request.getParameter("jiner");
		String yuer=request.getParameter("yuer");
		//对"元"进行处理，即去掉元
		yuer=yuer.substring(0,yuer.length()-1);
		//构造对象
		Users user=new Users();
		user.setSname(sname);
		user.setSnum(snum);
		user.setSdept(sdept);
		//将余额和金额的类型进行转换
		float yu=Float.parseFloat(yuer);
		float jin=Float.parseFloat(jiner);
		user.setJiner(jin);
		//将余额加到金额里
		user.setYuer(yu+jin);
		//将构造的对象存到域里（设置请求域对象）
		request.setAttribute("user",user);
		//重定向
		request.getRequestDispatcher("/jiaofei.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
