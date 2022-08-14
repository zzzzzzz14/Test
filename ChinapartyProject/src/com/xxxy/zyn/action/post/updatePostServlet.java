package com.xxxy.zyn.action.post;

import com.xxxy.zyn.bean.Post;
import com.xxxy.zyn.dao.PostDao;

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
 * Servlet implementation class updatePostServlet
 */
@WebServlet("/updatePostServlet")
public class updatePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updatePostServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//TODO Auto-generated method stub
		// response.getWriter().append("Served
		// at:").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		  response.setCharacterEncoding("utf-8");
		  response.setContentType("text/html;charset=utf-8");
		  // 获取页面数据
		  String cname = request.getParameter("cname");
		  String cdate = request.getParameter("cdate");
		  String cuuid = request.getParameter("cid");
		  // 构造对象
		  Post model = new Post();
		  model.setPost_id(cuuid);
		  model.setPostName(cname);
		  SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  try {
		   model.setPostCDate(f.parse(cdate));
		  } catch (ParseException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  // 执行更新方法
		  PostDao dao = new PostDao();
		  String str = dao.updatePost(model);
		  System.out.println(str);
		  // 向页面输出结果
		  PrintWriter out = response.getWriter();
		  out.write(str);
		  out.flush();
		  out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
