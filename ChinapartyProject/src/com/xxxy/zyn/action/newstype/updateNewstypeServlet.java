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

/**
 * Servlet implementation class updateNewstypeServlet
 */
@WebServlet("/updateNewstypeServlet")
public class updateNewstypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateNewstypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String cname = request.getParameter("cname");
		String cflag = request.getParameter("cflag");
		String cdate = request.getParameter("cdate");
		String cuuid = request.getParameter("cid");
		// 构造对象
		Newstype model = new Newstype();
		model.setNewstype_id(cuuid);
		model.setNewstypeName(cname);
		model.setNewstypeFlag(cflag);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			model.setNewsstypeCDate(f.parse(cdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 执行更新方法
		NewstypeDao dao = new NewstypeDao();
		String str = dao.updateNewstype(model);
		System.out.println(str);
		// 向页面输出操作结果
		PrintWriter out = response.getWriter();
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
