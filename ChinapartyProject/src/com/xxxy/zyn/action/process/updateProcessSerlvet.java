package com.xxxy.zyn.action.process;

import com.xxxy.zyn.bean.Process;
import com.xxxy.zyn.dao.ProcessDao;

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
 * Servlet implementation class updateDepartmentSerlvet
 */
@WebServlet("/updateProcessSerlvet")
public class updateProcessSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateProcessSerlvet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String uid = request.getParameter("process_id");
		String utid = request.getParameter("ptnames");
		String udate = request.getParameter("processDate");
		String uplace = request.getParameter("processPlace");
		String uhandler = request.getParameter("processHandler");
		String ucontent = request.getParameter("processContent");
		String utime = request.getParameter("cdate");
		String ulid = request.getParameter("logins_id");

		Process cls = new Process();
		cls.setProcess_id(uid);
		cls.setProcesstype_id(utid);
		cls.setProcessDate(udate);
		cls.setProcessPlace(uplace);
		cls.setProcessHandler(uhandler);
		cls.setProcessContent(ucontent);
		cls.setLogins_id(ulid);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			cls.setProcessTime(f.parse(utime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ProcessDao dao = new ProcessDao();
		String str = dao.updateProcess(cls);
		System.out.println(str);

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
