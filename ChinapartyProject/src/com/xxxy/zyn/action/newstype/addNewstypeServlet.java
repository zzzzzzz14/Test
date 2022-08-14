package com.xxxy.zyn.action.newstype;

import com.xxxy.zyn.bean.Newstype;
import com.xxxy.zyn.dao.NewstypeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
/**
 * Servlet implementation class addDepartmentServlet
 */
@WebServlet("/addNewstypeServlet")
public class addNewstypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addNewstypeServlet() {
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
		response.setContentType("text/html:charset=utf-8");
		//获取页面输入的值
		//定义数据库classes中的属性
		String cname=request.getParameter("cname");
		String cflag=request.getParameter("cflag");
		String cdate=request.getParameter("cdate");
		String cuuid=UUID.randomUUID().toString().replace("-", "");
		Newstype model=new Newstype();
		model.setNewstype_id(cuuid);
		model.setNewstypeName(cname);
		model.setNewstypeFlag(cflag);
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			model.setNewsstypeCDate(f.parse(cdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NewstypeDao dao=new NewstypeDao();
		String str=dao.addNewstype(model);
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
