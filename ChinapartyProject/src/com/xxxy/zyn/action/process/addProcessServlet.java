package com.xxxy.zyn.action.process;

import com.xxxy.zyn.dao.ProcessDao;
import com.xxxy.zyn.bean.Process;

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
 * Servlet implementation class addProcessServlet
 */
@WebServlet("/addProcessServlet")
public class addProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addProcessServlet() {
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
	  
		String processtype_id=request.getParameter("ptnames");
	    String processDate=request.getParameter("processDate");
		String processPlace=request.getParameter("processPlace");
		String processHandler=request.getParameter("processHandler");
		String processContent=request.getParameter("processContent");
		String logins_id=request.getParameter("logins_id");
		String cdate=request.getParameter("cdate");
		/*String cuuid=UUID.randomUUID().toString().replace("-", "");*/
		String cuuid=UUID.randomUUID().toString().replace("-", "");
		
		Process pcs=new Process();
		pcs.setProcess_id(cuuid);
		pcs.setProcesstype_id(processtype_id);
		pcs.setProcessDate(processDate);
		pcs.setProcessPlace(processPlace);
		pcs.setProcessHandler(processHandler);
		pcs.setProcessContent(processContent);
		pcs.setLogins_id(logins_id);
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			pcs.setProcessTime(f.parse(cdate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProcessDao dao=new ProcessDao();
		String str=dao.addProcess(pcs);
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
