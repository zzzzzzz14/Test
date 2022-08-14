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




/**
 * Servlet implementation class updateProcesstypeServlet
 */
@WebServlet("/updateProcesstypeServlet")
public class updateProcesstypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProcesstypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		// TODO Auto-generated method stub
				//response.getWriter().append("Served at: ").append(request.getContextPath());
					  //response.getWriter().append("Served at: ").append(request.getContextPath());
					  //璇锋眰鍝嶅簲缂栬В鐮佽缃?
					  request.setCharacterEncoding("utf-8");
					  response.setCharacterEncoding("utf-8");
					  response.setContentType("text/html;charset=utf-8");
					  //椤甸潰鏁版嵁鑾峰彇
					    String cname=request.getParameter("cname");
					    String cflag=request.getParameter("cflag");
					    String cuuid=request.getParameter("cid");
					  
					  //鏋勯€犵彮绾у璞?
					  Processtype cls=new Processtype();
					  cls.setProcesstype_id(cuuid);
					  cls.setPtypeName(cname);
					  cls.setPtypeFlag(cflag);
					 
					  
					  ProcesstypeDao dao=new ProcesstypeDao();//姝ゅ湴鍖朌ao瀵硅薄
					  String str=dao.updateProcesstype(cls);
					  System.out.println(str);//杈撳嚭鎿嶄綔缁撴灉
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
