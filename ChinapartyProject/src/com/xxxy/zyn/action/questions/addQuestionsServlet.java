package com.xxxy.zyn.action.questions;

import com.xxxy.zyn.bean.Questions;
import com.xxxy.zyn.dao.QuestionsDao;

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
@WebServlet("/addQuestionsServlet")
public class addQuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addQuestionsServlet() {
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
	    //获取页面用户输入的值
	    
	    String cthid=request.getParameter("pdnames");
	    String cname=request.getParameter("quesTitle");
	    String cflag=request.getParameter("qflag");
	    String cdate=request.getParameter("quesdate");
	    String qa=request.getParameter("quesA");//A选项
	    String qb=request.getParameter("quesB");//B选项
	    String qc=request.getParameter("quesC");//C选项
	    String qd=request.getParameter("quesD");//D选项
	    String score=request.getParameter("quesScore");
	    String stand=request.getParameter("quesStand");
	    String cuuid=UUID.randomUUID().toString().replace("-", "");
	    
	    Questions que=new Questions();
	    que.setQuestions_id(cuuid);
	    que.setTheme_id(cthid);
	    que.setQuesTitle(cname);
	    que.setQuesFlag(cflag);
	    SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
			que.setQuesCDate(f.parse(cdate));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        que.setQuesA(qa);
        que.setQuesB(qb);
        que.setQuesC(qc);
        que.setQuesD(qd);
        que.setQuesScore(score);
        que.setQuesStand(stand);
        QuestionsDao dao=new QuestionsDao();
        String str=dao.addQuestions(que);
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
