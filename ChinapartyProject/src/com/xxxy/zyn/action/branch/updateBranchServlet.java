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
/**
 * Servlet implementation class updateBranchServlet
 */
@WebServlet("/updateBranchServlet")
public class updateBranchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateBranchServlet() {
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
		//获取页面用户输入的值
	    String cname=request.getParameter("cname");
	    String cflag=request.getParameter("cflag");
	    String cdate=request.getParameter("cdate");
	    String cpbname=request.getParameter("pbnames");
	    String cuuid=request.getParameter("cid");
	    //构造对象
	    Branch model=new Branch();
	    model.setBranch_id(cuuid);
	    model.setBranchName(cname);
	    model.setBranchFlag(cflag);
	    model.setBranchPid(cpbname);
	    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
			model.setBranchCDate(f.parse(cdate));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       //执行更新方法
        BranchDao dao=new BranchDao();
        String str=dao.updateBranch(model);
        System.out.println(str);
        //向页面输出结果
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
