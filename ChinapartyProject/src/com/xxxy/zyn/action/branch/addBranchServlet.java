package com.xxxy.zyn.action.branch;

import com.xxxy.zyn.bean.Branch;
import com.xxxy.zyn.dao.BranchDao;

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
 * Servlet implementation class addBranchServlet
 */
@WebServlet("/addBranchServlet")
public class addBranchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addBranchServlet() {
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
		// 获取页面用户输入的值
		String cname = request.getParameter("cname");
		String cflag = request.getParameter("cflag");
		String cdate = request.getParameter("cdate");
		String cpbname = request.getParameter("pbnames");
		String cuuid = UUID.randomUUID().toString().replace("-", "");
		Branch model = new Branch();
		model.setBranch_id(cuuid);
		model.setBranchName(cname);
		model.setBranchFlag(cflag);
		model.setBranchPid(cpbname);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			model.setBranchCDate(f.parse(cdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BranchDao dao = new BranchDao();
		String str = dao.addBranch(model);
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
